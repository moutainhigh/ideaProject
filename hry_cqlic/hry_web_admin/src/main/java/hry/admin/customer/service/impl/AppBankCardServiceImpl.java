/**
 * Copyright:   
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-13 10:42:58 
 */
package hry.admin.customer.service.impl;

import com.github.pagehelper.Page;
import hry.admin.customer.dao.AppBankCardDao;
import hry.admin.customer.model.AppBankCard;
import hry.admin.customer.model.AppCustomer;
import hry.admin.customer.service.AppBankCardService;
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
 * <p> AppBankCardService </p>
 * @author:         liushilei
 * @Date :          2018-06-13 10:42:58  
 */
@Service("appBankCardService")
public class AppBankCardServiceImpl extends BaseServiceImpl<AppBankCard, Long> implements AppBankCardService{
	
	@Resource(name="appBankCardDao")
	@Override
	public void setDao(BaseDao<AppBankCard, Long> dao) {
		super.dao = dao;
	}

	@Override
	public PageResult findPageBySql(QueryFilter filter) {
		//----------------------分页查询头部外壳------------------------------
		// 分页参数处理
		Page<AppBankCard> page = PageFactory.getPage(filter);

		Map<String,Object> map = new HashMap<String,Object>();

		//----------------------查询开始------------------------------

		String email = filter.getRequest().getParameter("email");
		String mobilePhone = filter.getRequest().getParameter("mobilePhone");

		if(!StringUtils.isEmpty(email)){
			map.put("email", "%"+email+"%");
		}
		if(!StringUtils.isEmpty(mobilePhone)){
			map.put("mobilePhone", "%"+mobilePhone+"%");
		}


		((AppBankCardDao)dao).findPageBySql(map);
		//----------------------查询结束------------------------------


		return new PageResult(page, filter.getPage(),filter.getPageSize());
	}
}
