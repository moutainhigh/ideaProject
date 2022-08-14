/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Wu Shuiming
 * @version:      V1.0 
 * @Date:        2016年3月28日 下午7:05:06
 */
package hry.exchange.transaction.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import hry.core.mvc.dao.base.BaseDao;
import hry.bean.PageResult;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.util.QueryFilter;
import hry.customer.user.model.AppCustomer;
import hry.exchange.transaction.dao.ExDmCustomerPublicKeyDao;
import hry.exchange.transaction.model.ExDmCustomerPublicKey;
import hry.exchange.transaction.service.ExDmCustomerPublicKeyService;

/**
 * <p>
 * TODO
 * </p>
 * 
 * @author: Wu Shuiming
 * @Date : 2016年3月28日 下午7:05:06
 */
@Service("exDmCustomerPublicKeyService")
public class ExDmCustomerPublicKeyServiceImpl extends
		BaseServiceImpl<ExDmCustomerPublicKey, Long> implements
		ExDmCustomerPublicKeyService {

	@Resource(name = "exDmCustomerPublicKeyDao")
	@Override
	public void setDao(BaseDao<ExDmCustomerPublicKey, Long> dao) {
		super.dao = dao;
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
		String trueName = filter.getRequest().getParameter("trueName");
		String coinCode = filter.getRequest().getParameter("coinCode");
		
		Map<String,Object> map = new HashMap<String,Object>();
		if(!StringUtils.isEmpty(email)){
			map.put("email", "%"+email+"%");
		}
		if(!StringUtils.isEmpty(mobilePhone)){
			map.put("mobilePhone", "%"+mobilePhone+"%");
		}
		if(!StringUtils.isEmpty(trueName)){
			map.put("trueName", "%"+trueName+"%");
		}
		if(!StringUtils.isEmpty(coinCode)){
			map.put("coinCode", coinCode);
		}
		
		
		((ExDmCustomerPublicKeyDao)dao).findPageBySql(map);
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
	public PageResult findPageBySqlList(QueryFilter filter) {
		//----------------------分页查询头部外壳------------------------------
		// 分页参数处理
		String startStr = filter.getRequest().getParameter("start");
		String lengthStr = filter.getRequest().getParameter("length");
		Integer startpage = Integer.valueOf(startStr);
		Integer lengthpage = Integer.valueOf(lengthStr);
		if( lengthpage == null || lengthpage == 0 ){
			lengthpage = 10;
		}
		startpage = startpage/lengthpage;
		// 分页参数处理结束

		//创建PageResult对象
		PageResult pageResult = new PageResult();
		pageResult.setPage(startpage);
		pageResult.setPageSize(lengthpage);
		//----------------------查询开始------------------------------

		Map<String,Object> map = new HashMap<String,Object>();
		Integer start = startpage * lengthpage;
		map.put("start", start);
		map.put("end", lengthpage);
		
		String email = filter.getRequest().getParameter("email");
		String mobilePhone = filter.getRequest().getParameter("mobilePhone");
		String trueName = filter.getRequest().getParameter("trueName");
		String currencyType = filter.getRequest().getParameter("currencyType");
		
		// 币的类型
		if(!StringUtils.isEmpty(currencyType)){
			map.put("currencyType", currencyType);
		}
		
		Map<String,Object> mapcustomer = new HashMap<String,Object>();
		if(!StringUtils.isEmpty(email)){
			mapcustomer.put("email", email);
		}
		if(!StringUtils.isEmpty(mobilePhone)){
			mapcustomer.put("mobilePhone", mobilePhone);
		}
		if(!StringUtils.isEmpty(trueName)){
			mapcustomer.put("trueName", trueName);
		}
		if(mapcustomer.size()>0){
			List<String> listpersoninfo = ((ExDmCustomerPublicKeyDao)dao).findCustomerByCondition(mapcustomer);
			if(listpersoninfo.size()>0){
				map.put("customerId", listpersoninfo);
			}else{
				List<ExDmCustomerPublicKey> list = new ArrayList<ExDmCustomerPublicKey>();  
				//设置分页数据
				pageResult.setRows(list);
				//设置总记录数
				pageResult.setRecordsTotal(Long.valueOf("0"));
				return pageResult;
			}
		}

		Long count = ((ExDmCustomerPublicKeyDao)dao).findPageBySqlCount(map);
		List<ExDmCustomerPublicKey>  list = ((ExDmCustomerPublicKeyDao)dao).findPageBySqlList(map);
		//----------------------查询结束------------------------------

		//----------------------分页查询底部外壳------------------------------
		//设置分页数据
		pageResult.setRows(list);
		//设置总记录数
		pageResult.setRecordsTotal(count);

		//----------------------分页查询底部外壳------------------------------

		return pageResult;
	
	}

}
