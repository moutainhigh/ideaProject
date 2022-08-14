/**
 * Copyright:   
 * @author:      tianpengyu
 * @version:     V1.0 
 * @Date:        2018-06-25 19:50:18 
 */
package hry.admin.commend.service.impl;

import com.github.pagehelper.Page;
import hry.admin.commend.dao.AppCommendTradeDao;
import hry.admin.commend.model.AppCommendTrade;
import hry.admin.commend.service.AppCommendTradeService;
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
 * <p> AppCommendTradeService </p>
 * @author:         tianpengyu
 * @Date :          2018-06-25 19:50:18  
 */
@Service("appCommendTradeService")
public class AppCommendTradeServiceImpl extends BaseServiceImpl<AppCommendTrade, Long> implements AppCommendTradeService{
	
	@Resource(name="appCommendTradeDao")
	@Override
	public void setDao(BaseDao<AppCommendTrade, Long> dao) {
		super.dao = dao;
	}


	@Override
	public PageResult findPageBySql(QueryFilter filter) {

		//----------------------分页查询头部外壳------------------------------
		Page<AppCommendTrade> page = PageFactory.getPage(filter);
		//----------------------分页查询头部外壳------------------------------
		//----------------------查询开始------------------------------
		String email = filter.getRequest().getParameter("email");
		String mobilePhone = filter.getRequest().getParameter("mobilePhone");
		String deliveryEmail = filter.getRequest().getParameter("deliveryEmail");
		String deliveryMobilePhone = filter.getRequest().getParameter("deliveryMobilePhone");
		String coinCode = filter.getRequest().getParameter("coinCode");
		String personType = filter.getRequest().getParameter("personType");

		Map<String,Object> map = new HashMap<String,Object>();
		if(!StringUtils.isEmpty(email)){
			map.put("email", "%"+email+"%");
		}
		if(!StringUtils.isEmpty(personType)){
			map.put("personType", personType);
		}
		if(!StringUtils.isEmpty(mobilePhone)){
			map.put("mobilePhone", "%"+mobilePhone+"%");
		}
		if(!StringUtils.isEmpty(deliveryEmail)){
			map.put("deliveryEmail", "%"+deliveryEmail+"%");
		}
		if(!StringUtils.isEmpty(deliveryMobilePhone)){
			map.put("deliveryMobilePhone", "%"+deliveryMobilePhone+"%");
		}
		if(!StringUtils.isEmpty(coinCode)){
			map.put("coinCode", coinCode);
		}
		List<AppCommendTrade> pageBySql = ((AppCommendTradeDao)dao).findPageBySql(map);
		//----------------------查询结束------------------------------


		return new PageResult(page, filter.getPage(),filter.getPageSize());

	}
}
