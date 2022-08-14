/**
 * Copyright:   
 * @author:      jidn
 * @version:     V1.0 
 * @Date:        2019-05-08 11:13:13 
 */
package hry.financail.financing.service.impl;

import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.financail.financing.service.AppFinancialRecommendTransactionService;
import hry.financail.financing.model.AppFinancialRecommendTransaction;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p> AppFinancialRecommendTransactionService </p>
 * @author:         jidn
 * @Date :          2019-05-08 11:13:13  
 */
@Service("appFinancialRecommendTransactionService")
public class AppFinancialRecommendTransactionServiceImpl extends BaseServiceImpl<AppFinancialRecommendTransaction, Long> implements AppFinancialRecommendTransactionService {
	
	@Resource(name="appFinancialRecommendTransactionDao")
	@Override
	public void setDao(BaseDao<AppFinancialRecommendTransaction, Long> dao) {
		super.dao = dao;
	}
	

}
