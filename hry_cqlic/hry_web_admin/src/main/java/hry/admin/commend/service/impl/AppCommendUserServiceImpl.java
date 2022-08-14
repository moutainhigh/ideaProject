/**
 * Copyright:   
 * @author:      tianpengyu
 * @version:     V1.0 
 * @Date:        2018-06-25 20:10:54 
 */
package hry.admin.commend.service.impl;

import com.github.pagehelper.Page;
import hry.admin.commend.dao.AppCommendUserDao;
import hry.admin.commend.model.AppCommendUser;
import hry.admin.commend.service.AppCommendUserService;
import hry.bean.PageResult;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.util.PageFactory;
import hry.util.QueryFilter;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p> AppCommendUserService </p>
 * @author:         tianpengyu
 * @Date :          2018-06-25 20:10:54  
 */
@Service("appCommendUserService")
public class AppCommendUserServiceImpl extends BaseServiceImpl<AppCommendUser, Long> implements AppCommendUserService{
	private final static Logger log = Logger.getLogger(AppCommendUserServiceImpl.class);
	@Resource(name="appCommendUserDao")
	@Override
	public void setDao(BaseDao<AppCommendUser, Long> dao) {
		super.dao = dao;
	}


	@Override
	public PageResult findPageBySql(QueryFilter filter) {

		//----------------------分页查询头部外壳------------------------------
		Page<AppCommendUser> page = PageFactory.getPage(filter);
		//----------------------分页查询头部外壳------------------------------

		//----------------------查询开始------------------------------

		String email = filter.getRequest().getParameter("email");
		String mobilePhone = filter.getRequest().getParameter("mobilePhone");
		String mycolumn = filter.getRequest().getParameter("mycolumn");
		String search = filter.getRequest().getParameter("search");
		String receCode = filter.getRequest().getParameter("receCode");
		String commendCode = filter.getRequest().getParameter("commendCode");

		Map<String,Object> map = new HashMap<String,Object>();
		if(!StringUtils.isEmpty(email)){
			map.put("email", "%"+email+"%");
		}
		if(!StringUtils.isEmpty(receCode)){
			map.put("receCode", "%"+receCode+"%");
		}
		if(!StringUtils.isEmpty(commendCode)){
			map.put("commendCode", "%"+commendCode+"%");
		}
		if("appPersonInfo.email".equals(mycolumn)&&!StringUtils.isEmpty(search)){
			map.put("email", "%"+search+"%");
		}
		if(!StringUtils.isEmpty(mobilePhone)){
			map.put("mobilePhone", "%"+mobilePhone+"%");
		}
		if("appPersonInfo.mobilePhone".equals(mycolumn)&&!StringUtils.isEmpty(search)){
			map.put("mobilePhone", "%"+search+"%");
		}

		long s1=System.currentTimeMillis();
		((AppCommendUserDao)dao).findPageBySql(map);
		log.info("推荐人查询sql所用时间 ：" + (System.currentTimeMillis()-s1) + "毫秒");
		//----------------------查询结束------------------------------
		/*List<AppCommendUser> result = page.getResult();
		if(result!=null){
			for(AppCommendUser appCommendUser : result){
				if(appCommendUser.getLaterNumber() == 0){
					appCommendUser.setLaterNumber(0);
				}else{
					appCommendUser.setLaterNumber(appCommendUser.getLaterNumber()-appCommendUser.getOneNumber()-appCommendUser.getThreeNumber()-appCommendUser.getTwoNumber());
				}
			}
		}*/
		log.info("推荐人查询返回所用时间 ：" + (System.currentTimeMillis()-s1) + "毫秒");
		return new PageResult(page, filter.getPage(),filter.getPageSize());
	}

	@Override
	public PageResult findConmmendPageBySql(QueryFilter filter) {


        String id = filter.getRequest().getParameter("id");
        String number = filter.getRequest().getParameter("number");

		AppCommendUser user = get(Long.valueOf(id));
		String sid = number + "-" + user.getUid();
		String [] param;
		if(Integer.parseInt(number)==4){
			List<String> arr = ((AppCommendUserDao)dao).findSid("-" + user.getUid() +",");
			// 取出做大层级
			int paramlength = 0;
			for (String s : arr) {
				String [] sidarr = s.split(",");
				if (sidarr.length > paramlength) {
					paramlength = sidarr.length;
				}
			}
			/*有问题*/
			/*String [] sidarr = arr.get(0).split(",");
			for (int i = 1; i <sidarr.length ; i++) {
				String tempid = sidarr[i].split("-")[1];
				if(tempid.equals(user.getUid().toString())){//匹配当前uid是第几级推荐人
					paramlength = i+1;//设置查询参数array长度
				}
			}*/
			param= new String[paramlength-4];//去掉前三级推荐
			for (int i = 4; i <paramlength ; i++) {
				param[i-4]=i+"-"+user.getUid();
			}
		}else{
			param = new String[1];
			param[0] = sid;
		}

        //----------------------分页查询头部外壳------------------------------
        Page<AppCommendUser> page = PageFactory.getPage(filter);
        //----------------------分页查询头部外壳------------------------------

		//----------------------查询开始------------------------------
		((AppCommendUserDao)dao).findConmmendPageByArray(param);
		//----------------------查询结束------------------------------

		return new PageResult(page, filter.getPage(),filter.getPageSize());
	}


	@Override
	public PageResult icoFindPageBySql(QueryFilter filter) {

		//----------------------分页查询头部外壳------------------------------
		Page<Map<String, Object>> page = PageFactory.getPage(filter);

		Map<String,Object> map = new HashMap<String,Object>();

		String mobilePhone = filter.getRequest().getParameter("mobilePhone");
		String level = filter.getRequest().getParameter("level");
		String startTime = filter.getRequest().getParameter("startTime");
		String endTime = filter.getRequest().getParameter("endTime");

		if(!StringUtils.isEmpty(mobilePhone)){
			map.put("mobilePhone", "%"+mobilePhone+"%" );

		}
		if(!StringUtils.isEmpty(level)){
			map.put("level", level);
		}
		if(!StringUtils.isEmpty(startTime)){
			map.put("startTime", startTime);
		}
		if(!StringUtils.isEmpty(endTime)){
			map.put("endTime", endTime);
		}

		long s1=System.currentTimeMillis();
		((AppCommendUserDao)dao).icoFindPageBySql(map);

		return new PageResult(page, filter.getPage(),filter.getPageSize());
	}




	@Override
	public PageResult newResultsList(QueryFilter filter) {
		//----------------------分页查询头部外壳------------------------------
		Page<Map<String, Object>> page = PageFactory.getPage(filter);
		Map<String,Object> map = new HashMap<String,Object>();
		String customerId = filter.getRequest().getParameter("customerId");
		if(!StringUtils.isEmpty(customerId)){
			map.put("customerId", customerId);
		}

		long s1=System.currentTimeMillis();
		((AppCommendUserDao)dao).newResultsList(map);

		return new PageResult(page, filter.getPage(),filter.getPageSize());
	}



	@Override
	public PageResult assetsList(QueryFilter filter) {
//----------------------分页查询头部外壳------------------------------
		Page<Map<String, Object>> page = PageFactory.getPage(filter);

		Map<String,Object> map = new HashMap<String,Object>();
		String customerId = filter.getRequest().getParameter("customerId");
		String email = filter.getRequest().getParameter("email");
		String mobilePhone = filter.getRequest().getParameter("mobilePhone");
		String level = filter.getRequest().getParameter("level");


		if(!StringUtils.isEmpty(customerId)) {
			map.put("customerId", customerId);
		}
		if(!StringUtils.isEmpty(email)){
			map.put("email", "%"+email+"%" );
		}
		if(!StringUtils.isEmpty(mobilePhone)){
			map.put("mobilePhone", "%"+mobilePhone+"%" );
		}
		if(!StringUtils.isEmpty(level)){
			map.put("level", level);
		}

		long s1=System.currentTimeMillis();
		((AppCommendUserDao)dao).assetsList(map);

		return new PageResult(page, filter.getPage(),filter.getPageSize());
	}

}
