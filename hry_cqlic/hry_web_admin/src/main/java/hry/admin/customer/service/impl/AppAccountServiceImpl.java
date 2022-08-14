/**
 * Copyright:   
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-15 13:08:06 
 */
package hry.admin.customer.service.impl;

import com.github.pagehelper.Page;
import hry.admin.customer.dao.AppAccountDao;
import hry.admin.customer.model.AppAccount;
import hry.admin.customer.service.AppAccountService;
import hry.bean.PageResult;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.util.PageFactory;
import hry.util.QueryFilter;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p> AppAccountService </p>
 * @author:         liushilei
 * @Date :          2018-06-15 13:08:06  
 */
@Service("appAccountService")
public class AppAccountServiceImpl extends BaseServiceImpl<AppAccount, Long> implements AppAccountService{
	
	@Resource(name="appAccountDao")
	@Override
	public void setDao(BaseDao<AppAccount, Long> dao) {
		super.dao = dao;
	}


	@Override
	public PageResult findPageBySqlList(QueryFilter filter) {

		//----------------------分页查询头部外壳------------------------------
		Page<AppAccount> page = PageFactory.getPage(filter);
		//----------------------分页查询头部外壳------------------------------

		Map<String,Object> map = new HashMap<String,Object>();

		//----------------------查询开始------------------------------

		String email = filter.getRequest().getParameter("email");
		String mobilePhone = filter.getRequest().getParameter("mobilePhone");
		String trueName = filter.getRequest().getParameter("trueName");

		if(!StringUtils.isEmpty(email)){
			map.put("email", "%"+email+"%");
		}
		if(!StringUtils.isEmpty(trueName)){
			map.put("trueName", "%"+trueName+"%");
		}
		if(!StringUtils.isEmpty(mobilePhone)){
			map.put("mobilePhone", "%"+mobilePhone+"%");
		}


		List<AppAccount>  list = ((AppAccountDao)dao).findPageBySqlList(map);
		//----------------------查询结束------------------------------


		return new PageResult(page, filter.getPage(),filter.getPageSize());


	}
}
