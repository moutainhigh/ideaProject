/**
 * Copyright:    
 * @author:      jidn
 * @version:     V1.0 
 * @Date:        2019-05-08 11:13:13 
 */
package hry.financail.financing.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.financail.financing.model.AppFinancialRecommendTransaction;

import java.util.Map;


/**
 * <p> AppFinancialRecommendTransactionDao </p>
 * @author:         jidn
 * @Date :          2019-05-08 11:13:13  
 */
public interface AppFinancialRecommendTransactionDao extends  BaseDao<AppFinancialRecommendTransaction, Long> {


    public AppFinancialRecommendTransaction findAppFinancialRecommendTransactionByCustomerId(Map<String, Object> paramMap);

    public AppFinancialRecommendTransaction findAppFinancialTransactionByCustomerId(Long customerId);
}
