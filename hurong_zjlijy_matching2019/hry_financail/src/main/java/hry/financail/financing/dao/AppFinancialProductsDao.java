/**
 * Copyright:    
 * @author:      jidn
 * @version:     V1.0 
 * @Date:        2019-04-03 11:06:50 
 */
package hry.financail.financing.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.financail.financing.model.AppFinancialProducts;

import java.util.List;
import java.util.Map;


/**
 * <p> AppFinancialProductsDao </p>
 * @author:         jidn
 * @Date :          2019-04-03 11:06:50  
 */
public interface AppFinancialProductsDao extends  BaseDao<AppFinancialProducts, String> {



    List<hry.remote.model.AppFinancialProducts> findFinaningProductList(Map<String,String> paramMap);

    List<hry.remote.model.AppFinancialProducts> findUserFinaningProductList(Map<String, String> paramMap);

    hry.remote.model.AppFinancialProducts findOne(Map<String, String> paramMap);

    int findBetweenProduct();

    hry.remote.model.AppFinancialProducts findOne_cy(Map<String, String> paramMap);
}
