/**
 * Copyright:   
 * @author:      jidn
 * @version:     V1.0 
 * @Date:        2019-04-03 11:07:31 
 */
package hry.financail.financing.service.impl;

import hry.financail.financing.dao.AppFinancialRedAcountDao;
import hry.financail.financing.model.AppFinancialRedAcount;
import hry.financail.financing.service.AppFinancialRedAcountService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * <p> AppFinancialRedAcountService </p>
 * @author:         jidn
 * @Date :          2019-04-03 11:07:31  
 */
@Service("appFinancialRedAcountService")
public class AppFinancialRedAcountServiceImpl extends BaseServiceImpl<AppFinancialRedAcount, String> implements AppFinancialRedAcountService{
	
	@Resource(name="appFinancialRedAcountDao")
	@Override
	public void setDao(BaseDao<AppFinancialRedAcount, String> dao) {
		super.dao = dao;
	}


	@Override
	public AppFinancialRedAcount getFinancialRedAccount(String customerId, String coinCode) {
		Map<String,String> paramMap = new HashMap<>(2);
		paramMap.put("customerId",customerId);
		paramMap.put("coinCode",coinCode);
		return ((AppFinancialRedAcountDao)dao).findUserRedAccount(paramMap);
	}
}
