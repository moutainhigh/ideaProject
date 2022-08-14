/**
 * Copyright:   
 * @author:      jidn
 * @version:     V1.0 
 * @Date:        2019-04-03 11:06:50 
 */
package hry.financail.financing.service;

import hry.bean.FrontPage;
import hry.core.mvc.service.base.BaseService;
import hry.financail.financing.model.AppFinancialProducts;

import java.util.List;
import java.util.Map;


/**
 * <p> AppFinancialProductsService </p>
 * @author:         jidn
 * @Date :          2019-04-03 11:06:50 
 */
public interface AppFinancialProductsService  extends BaseService<AppFinancialProducts, String>{


    List<hry.remote.model.AppFinancialProducts> findFinaningProductList(Map<String,String> paramMap);

    FrontPage findUserFinaningProductList(Map<String,String> paramMap);

    hry.remote.model.AppFinancialProducts findOne(Map<String,String> paramMap);

    hry.remote.model.AppFinancialProducts findOne_cy(Map<String,String> paramMap);
}
