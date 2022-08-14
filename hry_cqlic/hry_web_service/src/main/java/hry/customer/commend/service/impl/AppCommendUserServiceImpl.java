/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      menwei
 * @version:     V1.0 
 * @Date:        2017-11-28 15:30:38 
 */
package hry.customer.commend.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.customer.commend.dao.AppCommendUserDao;
import hry.customer.commend.model.AppCommendUser;
import hry.customer.commend.service.AppCommendUserService;
import hry.customer.user.model.AppCustomer;
import hry.customer.user.service.AppCustomerService;
import hry.manage.remote.model.base.FrontPage;
import hry.util.PageFactory;
import hry.util.QueryFilter;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p> AppCommendUserService </p>
 * @author:         menwei
 * @Date :          2017-11-28 15:30:38  
 */
@Service("appCommendUserService")
public class AppCommendUserServiceImpl extends BaseServiceImpl<AppCommendUser, Long> implements AppCommendUserService{
	private static Logger logger = Logger.getLogger(AppCommendUserServiceImpl.class);
	@Resource(name="appCommendUserDao")
	@Override
	public void setDao(BaseDao<AppCommendUser, Long> dao) {
		super.dao = dao;
	}
	@Resource(name="appCommendUserDao")
	private AppCommendUserDao appCommendUserDao;
	
	@Resource(name="appCustomerService")
	public AppCustomerService appCustomerService;
	@Resource(name="appCommendUserService")
	public AppCommendUserService appCommendUserService;

	private Boolean flag = true;

	private Object lock = new Object();
	
	StringBuffer s = new StringBuffer(); //推荐树
	int level; //级数
	@Override
	public synchronized void  saveObj(AppCustomer customer) {
		level=1;
		s = new StringBuffer("");
		AppCustomer AppCustomer =null;
		QueryFilter filter = new QueryFilter(AppCustomer.class);
		//filter.addFilter("referralCode=", customer.getCommendCode()); 
		if(customer.getCommendCode()!=null&&!customer.getCommendCode().equals("")){//有没有推荐码
		    //filter.addFilter("commendCode=", customer.getReferralCode()); 
			filter.addFilter("referralCode=", customer.getCommendCode()); 
			AppCustomer = appCustomerService.get(filter);
			if(AppCustomer!=null){  
			   AppCustomer.setOneNumber(AppCustomer.getOneNumber()+1);
			}
			addSid(AppCustomer);
		}
		if(s==null||s.equals("")){
			AppCommendUser appCommend=new AppCommendUser();
			appCommend.setUid(customer.getId());
			appCommend.setUsername(customer.getUserName());
			appCommend.setPname(AppCustomer.getUserName());
			appCommend.setPid(AppCustomer.getId());
			appCommend.setIsFrozen(0);
			appCommend.setReceCode(customer.getReferralCode());
			s.toString().lastIndexOf(",", 1);
			logger.error(AppCustomer.getId());
			appCommendUserService.save(appCommend);
		}else{
			QueryFilter filteru = new QueryFilter(AppCommendUser.class);
			filteru.addFilter("uid=", customer.getId());
			AppCommendUser appCommendUser = appCommendUserService.get(filteru);
			if(appCommendUser!=null){
				appCommendUser.setSid(s.toString().substring(0, s.length()-1));
				String str = s.toString().substring(0, s.length()-1);
				appCommendUser.setSid(","+str+",");
				int one = str.lastIndexOf(",");
				String maxid = str.substring((one+1),str.length());
				appCommendUser.setMaxId(Long.valueOf(maxid));
				appCommendUserService.update(appCommendUser);
			}else{
				AppCommendUser appcommend=new AppCommendUser();
				if(s.toString()!=null&&!s.toString().equals("")){
				String str = s.toString().substring(0, s.length()-1);
				appcommend.setSid(","+str+",");
				int one = str.lastIndexOf(",");
				String maxid = str.substring((one+1),str.length()).split("-")[1];
				appcommend.setMaxId(Long.valueOf(maxid));
				}
				appcommend.setUid(customer.getId());
				appcommend.setUsername(customer.getUserName());
				appcommend.setIsFrozen(0);
				appcommend.setReceCode(customer.getReferralCode());  //设置自己的注册码
				if(AppCustomer!=null){
					appcommend.setPname(AppCustomer.getUserName()); 
					appcommend.setPid(AppCustomer.getId());
				}
				appCommendUserService.save(appcommend);
			}
		}
		
	}
	
	//递归文本路径
	public String addSid(AppCustomer customer){  
		AppCustomer appCustomer = null;
        if(customer!=null){  
        	//s.insert(0, customer.getId()+",");
        	s.append( level+"-"+customer.getId()+",");
         }else{
        	 return null;
         }
    	QueryFilter filter = new QueryFilter(AppCustomer.class);
    	if(customer.getCommendCode()!=null&&!"".equals(customer.getCommendCode())){
    		
    		filter.addFilter("referralCode=", customer.getCommendCode()); 		
    		 appCustomer = appCustomerService.get(filter);
    		 level++;
    	}else{
    		return null;
    	}
        return  addSid(appCustomer);  
        
    }  
	
	
	
	@Override
	public List<AppCommendUser> saveAppTradeFactor(AppCustomer buyCustomer, BigDecimal transactionBuyFee,
			String fixPriceCoinCode, Integer fixPriceType) {
		// TODO Auto-generated method stub
		return null;
	}
/*	public static void main(String[] args) {
		StringBuffer s=new StringBuffer();
		String s2="13333,";
		  logger.error(s2.substring(0, s2.length()-1));
	}*/

	@Override
	public int findLen(String  id) {
		// TODO Auto-generated method stub
		return appCommendUserDao.findLen(id);
	}

	@Override
	public JsonResult forzen(String ids) {
		// TODO Auto-generated method stub
		return appCommendUserDao.forzen(ids);
	}

	@Override
	public JsonResult noforzen(String ids) {
		// TODO Auto-generated method stub
		return appCommendUserDao.noforzen(ids);
	}

	@Override
	public List<AppCommendUser> findLikeBySid(String pid) {
		Map<String,String> map = new HashMap<>();
		map.put("pid1",pid);
		map.put("pid2",pid);
		return appCommendUserDao.findLikeBySid(map);
	}

	@Override
	public List<AppCommendUser> findByAloneMoneyIsNotZero(AppCommendUser appCommendUser) {
        String lastSid;
        String sid = appCommendUser.getSid();
        if(sid!=null){
            String[] sids = sid.split(",");
            lastSid = sids[sids.length-1];
        }else{
            lastSid = appCommendUser.getUid().toString();
        }
        Map<String,String> map = new HashMap<>();
        map.put("uid",appCommendUser.getUid().toString());
        map.put("sid",lastSid);
        return appCommendUserDao.findByAloneMoneyIsNotZero(map);
	}


	public static void main(String[] args) {
		String s="1,2,3,4,";
		String str = s.toString().substring(0, s.length()-1);
		int one = str.lastIndexOf(",");
		String maxid = str.substring((one+1),str.length());
		logger.error(str.substring((one+1),str.length()));
	}

	@Override
	public PageResult findPageBySql(QueryFilter filter) {
		

		
		//----------------------分页查询头部外壳------------------------------
		//创建PageResult对象
		PageResult pageResult = new PageResult();
		Page<AppCustomer> page = null;
		if(filter.getPageSize().compareTo(Integer.valueOf(-1))==0){
			//pageSize = -1 时  
			//pageHelper传pageSize参数传0查询全部
			page= PageHelper.startPage(filter.getPage(), 0);
		}else{
			page = PageHelper.startPage(filter.getPage(), filter.getPageSize());
		}
		//----------------------分页查询头部外壳------------------------------
		
		//----------------------查询开始------------------------------
		
		String email = filter.getRequest().getParameter("email");
		String mobilePhone = filter.getRequest().getParameter("mobilePhone");
		
		Map<String,Object> map = new HashMap<String,Object>();
		if(!StringUtils.isEmpty(email)){
			map.put("email", "%"+email+"%");
		}
		if(!StringUtils.isEmpty(mobilePhone)){
			map.put("mobilePhone", "%"+mobilePhone+"%");
		}
		
		
		
		((AppCommendUserDao)dao).findPageBySql(map);
		//----------------------查询结束------------------------------
		
		//----------------------分页查询底部外壳------------------------------
		//设置分页数据
		pageResult.setRows(page.getResult());
		//设置总记录数
		pageResult.setRecordsTotal(page.getTotal());
		pageResult.setDraw(filter.getDraw());
		pageResult.setPage(filter.getPage());
		pageResult.setPageSize(filter.getPageSize());
		//----------------------分页查询底部外壳------------------------------
		
		return pageResult;
		
	}

	@Override
	public List<AppCommendUser> culCommendCount(Map<String,String> map) {
		String customerId=map.get("customerId");
		QueryFilter AppCommendUser = new QueryFilter(AppCommendUser.class);
		AppCommendUser.addFilter("uid=", customerId);
		AppCommendUser appuser = this.get(AppCommendUser);
		Integer oneNumber =appCommendUserDao.findLen("1-"+appuser.getUid());
		Integer twoNumber =appCommendUserDao.findLen("2-"+appuser.getUid());
		Integer threeNumber =appCommendUserDao.findLen("3-"+appuser.getUid());
		int selectAll=appCommendUserService.findLen("-"+appuser.getUid().toString());
		
		AppCommendUser appCommendUser =new AppCommendUser();
		appCommendUser.setOneNumber(oneNumber);
		appCommendUser.setTwoNumber(twoNumber);
		appCommendUser.setThreeNumber(threeNumber);
		appCommendUser.setLaterNumber(selectAll-oneNumber-twoNumber-threeNumber);
		List<AppCommendUser> list=new ArrayList<AppCommendUser>();
		list.add(appCommendUser);
		return list;
	}

	@Override
	public void addNumber() throws Exception {
		synchronized (lock) {
			if (flag) {
				flag = false;

//查询出没有被追加过级数，且没有被冻结的
				List<AppCommendUser> stringsList=appCommendUserDao.findByIsCulCommend(0);
				if( stringsList != null && stringsList.size() > 0){
					for (AppCommendUser app : stringsList) {
						//得到sid
						if( app.getSid() != null && ! "".equals(app.getSid()) ){
							String[] sidss = app.getSid().split(",");
							if( sidss != null ){
								for (String string : sidss) {
									if(!"".equals(string)) {
										String[] sidSplit = string.split("-");
										if (sidSplit != null) {
											//sidSplit[0] 代表向上几sidSplit[0]级   sidSplit[1] 代表的第sidSplit[0]级的 uid
											QueryFilter queryFilter = new QueryFilter(AppCommendUser.class);
											queryFilter.addFilter("uid=", Long.parseLong(sidSplit[1]));
											AppCommendUser appCommendUser = this.get(queryFilter);
											switch (Integer.parseInt(sidSplit[0])) {
												case 1:
													appCommendUser.setOneNumber(appCommendUser.getOneNumber() + 1);
													break;
												case 2:
													appCommendUser.setTwoNumber(appCommendUser.getTwoNumber() + 1);
													break;
												case 3:
													appCommendUser.setThreeNumber(appCommendUser.getThreeNumber() + 1);
													break;
												default:
													appCommendUser.setLaterNumber(appCommendUser.getLaterNumber() + 1);
													break;
											}
											//保存
											this.update(appCommendUser);

										}
									}
								}
							}
						}
						//把isCulCommend设置为1:追加过推荐人级数中的个数
						appCommendUserDao.updateIsCulCommendById( 1 , app.getId() );
					}
				}


			}
			flag = true;
		}



		
	}

	@Override
	public PageResult findConmmendPageBySql(QueryFilter filter, Long id ,Integer number ,Integer startpage,
			Integer lengthpage) throws Exception {
		
		//----------------------分页查询头部外壳------------------------------
		//创建PageResult对象
		//----------------------分页查询头部外壳------------------------------
		//创建PageResult对象
		PageResult pageResult = new PageResult();
		pageResult.setPage(startpage);
		pageResult.setPageSize(lengthpage);
		//----------------------分页查询头部外壳------------------------------
		
		//----------------------查询开始------------------------------
	    Integer start=startpage*lengthpage;
		QueryFilter queryFilter = new QueryFilter(AppCommendUser.class);
		queryFilter.addFilter("id=", id ) ;
		AppCommendUser appCommendUser = this.get(queryFilter);
		String sid = "%,"+number + "-" + appCommendUser.getUid() +",%";
		List<AppCommendUser> list = ((AppCommendUserDao)dao).findConmmendPageBySql( sid ,start ,lengthpage);
		Long count=appCommendUserDao.findConmmendCountBySid(sid);
		//----------------------查询结束------------------------------
		
		//----------------------分页查询底部外壳------------------------------
		//设置分页数据
		pageResult.setRows(list);
		//设置总记录数
		pageResult.setRecordsTotal(count);
		pageResult.setDraw(filter.getDraw());
		pageResult.setPage(filter.getPage());
		pageResult.setPageSize(filter.getPageSize());
		//----------------------分页查询底部外壳------------------------------
		
		return pageResult;
	}

	@Override
	public FrontPage findConmmendForntPageBySql(Map<String, String> params) throws Exception{
		QueryFilter queryFilter = new QueryFilter(AppCommendUser.class);
		queryFilter.addFilter("uid=", Long.parseLong(params.get("id")) ) ;
		AppCommendUser user = this.get(queryFilter);
		String number = params.get("number");
		String sid = number + "-" + user.getUid();
		String [] param;
		if(Integer.parseInt(number)==4){
			List<String> arr = ((AppCommendUserDao)dao).findSid("-" + user.getUid() +",");
			String [] sidarr = arr.get(0).split(",");
			int paramlength = 0;
			for (int i = 1; i <sidarr.length ; i++) {
				String tempid = sidarr[i].split("-")[1];
				if(tempid.equals(user.getUid().toString())){//匹配当前uid是第几级推荐人
					paramlength = i+1;//设置查询参数array长度
				}
			}
			param= new String[paramlength-4];//去掉前三级推荐
			for (int i = 4; i <paramlength ; i++) {
				param[i-4]=i+"-"+user.getUid();
			}
		}else{
			param = new String[1];
			param[0] = sid;
		}
		//----------------------分页查询头部外壳------------------------------
		Page<AppCommendUser> page = PageFactory.getPage(params);
		//----------------------查询开始------------------------------
		List<AppCommendUser> list = ((AppCommendUserDao) dao).findConmmendPageByArray(param);
		return new FrontPage(list, page.getTotal(), page.getPages(), page.getPageSize());
	}

	@Override
	public int countLen(String sid) {
		return ((AppCommendUserDao)dao).countLen(sid);
	}

	@Override
	public int countOneLevel(String pid) {
		return ((AppCommendUserDao)dao).countOneLevel(pid);
	}
}
