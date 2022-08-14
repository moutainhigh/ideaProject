/**
 * Copyright:   
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-13 10:54:15 
 */
package hry.admin.exchange.service.impl;

import com.github.pagehelper.Page;
import hry.admin.customer.model.AppCustomer;
import hry.admin.exchange.dao.ExDmCustomerPublickeyDao;
import hry.admin.exchange.model.ExDmCustomerPublickey;
import hry.admin.exchange.service.ExDmCustomerPublickeyService;
import hry.bean.PageResult;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;

import hry.util.PageFactory;
import hry.util.QueryFilter;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p> ExDmCustomerPublickeyService </p>
 * @author:         liushilei
 * @Date :          2018-06-13 10:54:15  
 */
@Service("exDmCustomerPublickeyService")
public class ExDmCustomerPublickeyServiceImpl extends BaseServiceImpl<ExDmCustomerPublickey, Long> implements ExDmCustomerPublickeyService{
	
	@Resource(name="exDmCustomerPublickeyDao")
	@Override
	public void setDao(BaseDao<ExDmCustomerPublickey, Long> dao) {
		super.dao = dao;
	}

	@Override
	public PageResult findPageBySql(QueryFilter filter) {
		//----------------------分页查询头部外壳------------------------------
		// 分页参数处理


		//创建PageResult对象
		Page<ExDmCustomerPublickey> page = PageFactory.getPage(filter);

		//----------------------查询开始------------------------------

		Map<String,Object> map = new HashMap<String,Object>();


		String email = filter.getRequest().getParameter("email");
		String mobilePhone = filter.getRequest().getParameter("mobilePhone");
		String trueName = filter.getRequest().getParameter("trueName");
		String surname = filter.getRequest().getParameter("surname");
		String currencyType = filter.getRequest().getParameter("currencyType");

		// 币的类型
		if(!StringUtils.isEmpty(currencyType)){
			map.put("currencyType", currencyType);
		}

		Map<String,Object> mapcustomer = new HashMap<String,Object>();
		if(!StringUtils.isEmpty(email)){
			map.put("email", "%"+email+"%");
		}
		if(!StringUtils.isEmpty(mobilePhone)){
			map.put("mobilePhone", "%"+mobilePhone+"%");
		}
		if(!StringUtils.isEmpty(trueName)){
			map.put("trueName", "%"+trueName+"%");
		}
		if(!StringUtils.isEmpty(surname)){
			map.put("surname", "%"+surname+"%");
		}

		((ExDmCustomerPublickeyDao)dao).findPageBySql(map);

		//----------------------分页查询底部外壳------------------------------

		return new PageResult(page, filter.getPage(),filter.getPageSize());

	}
}
