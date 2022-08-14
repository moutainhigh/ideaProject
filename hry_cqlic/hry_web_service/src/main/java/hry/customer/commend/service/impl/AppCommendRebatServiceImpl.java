/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      menwei
 * @version:     V1.0 
 * @Date:        2017-11-28 17:40:59 
 */
package hry.customer.commend.service.impl;

import hry.core.mvc.dao.base.BaseDao;
import hry.bean.PageResult;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.util.QueryFilter;
import hry.customer.commend.dao.AppCommendRebatDao;
import hry.customer.commend.dao.AppCommendTradeDao;
import hry.customer.commend.model.AppCommendRebat;
import hry.customer.commend.model.AppCommendTrade;
import hry.customer.commend.service.AppCommendRebatService;
import hry.customer.person.model.AppPersonInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

/**
 * <p> AppCommendTradeService </p>
 * @author:         menwei
 * @Date :          2017-11-28 17:40:59  
 */
@Service("appCommendRebatService")
public class AppCommendRebatServiceImpl extends BaseServiceImpl<AppCommendRebat, Long> implements AppCommendRebatService{
	
	@Resource(name="appCommendRebatDao")
	@Override
	public void setDao(BaseDao<AppCommendRebat, Long> dao) {
		super.dao = dao;
	}
	@Resource(name="appCommendRebatDao")
	public AppCommendRebatDao appCommendRebatDao;
	@Resource(name="appCommendTradeDao")
	public AppCommendTradeDao appCommendTradeDao;
	
	private static final Logger log = LoggerFactory.getLogger(AppCommendRebatServiceImpl.class);

	@Override
	public PageResult findPageBySqlList(String email, String mobilePhone, int startpage, int lengthpage) {


	
		
		//----------------------分页查询头部外壳------------------------------
		//创建PageResult对象
		PageResult pageResult = new PageResult();
		pageResult.setPage(startpage);
		pageResult.setPageSize(lengthpage);
		//----------------------查询开始------------------------------

		Map<String,Object> map = new HashMap<String,Object>();
	    Integer start=startpage*lengthpage;
		Integer end=(startpage+1)*lengthpage;
		map.put("start", start);
		map.put("end", end);
		
		Map<String,Object> mapcustomer = new HashMap<String,Object>();
		if(!StringUtils.isEmpty(email)){
			
			mapcustomer.put("email", email);
		}
		if(!StringUtils.isEmpty(mobilePhone)){
			mapcustomer.put("mobilePhone", mobilePhone);
		}
		if(mapcustomer.size()>0){
			List<AppPersonInfo> listpersoninfo=appCommendTradeDao.findCustomerIdByemailAndphone(mapcustomer);
			if(listpersoninfo.size()>0){
				map.put("cusomerId", listpersoninfo.get(0).getCustomerId());
			}else{
				List<AppCommendTrade>  list=new ArrayList<AppCommendTrade>();  
				//设置分页数据
				pageResult.setRows(list);
				//设置总记录数
				pageResult.setRecordsTotal(Long.valueOf("0"));
				return pageResult;
				
			}
			
		}
		
		Long count=appCommendRebatDao.findPageBySqlCount(map);
		List<AppCommendTrade>  list=appCommendRebatDao.findPageBySqlList(map);
		//----------------------查询结束------------------------------
		
		//----------------------分页查询底部外壳------------------------------
		//设置分页数据
		pageResult.setRows(list);
		//设置总记录数
		pageResult.setRecordsTotal(count);
	
		//----------------------分页查询底部外壳------------------------------
		
		return pageResult;
		
	
		
	
	}
	@Override
	public PageResult findPageBySql(QueryFilter filter) {
		//----------------------分页查询头部外壳------------------------------
		Map<String,Object> map = new HashMap<String,Object>();
		//创建PageResult对象
		PageResult pageResult = new PageResult();
		Page<AppCommendRebat> page = null;
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
		
		
		if(!StringUtils.isEmpty(email)){
			map.put("email", "%"+email+"%");
		}
		if(!StringUtils.isEmpty(mobilePhone)){
			map.put("mobilePhone", "%"+mobilePhone+"%");
		}
		List<AppCommendRebat> pageBySql = appCommendRebatDao.findPageBySql(map);
		//----------------------查询结束------------------------------
		
		//----------------------分页查询底部外壳------------------------------
		//设置分页数据
		pageResult.setRows(pageBySql);
		//设置总记录数
		pageResult.setRecordsTotal(page.getTotal());
		pageResult.setDraw(filter.getDraw());
		pageResult.setPage(filter.getPage());
		pageResult.setPageSize(filter.getPageSize());
		//----------------------分页查询底部外壳------------------------------
		return pageResult;
	}

}
