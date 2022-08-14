/**
 * Copyright:   
 * @author:      denghf
 * @version:     V1.0 
 * @Date:        2018-10-26 18:12:38 
 */
package hry.admin.exchange.service;

import hry.bean.PageResult;
import hry.core.mvc.service.base.BaseService;
import hry.admin.exchange.model.OtcAppTransaction;
import hry.util.QueryFilter;


/**
 * <p> OtcAppTransactionService </p>
 * @author:         denghf
 * @Date :          2018-10-26 18:12:38 
 */
public interface OtcAppTransactionService  extends BaseService<OtcAppTransaction, Long>{

    PageResult findPageBySql (QueryFilter filter, Integer type);
}
