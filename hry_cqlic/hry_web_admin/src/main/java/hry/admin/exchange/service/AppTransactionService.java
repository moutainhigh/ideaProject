/**
 * Copyright:   
 * @author:      tianpengyu
 * @version:     V1.0 
 * @Date:        2018-07-06 14:36:50 
 */
package hry.admin.exchange.service;

import hry.admin.exchange.model.ExDmTransaction;
import hry.core.mvc.service.base.BaseService;
import hry.admin.exchange.model.AppTransaction;
import hry.util.QueryFilter;


/**
 * <p> AppTransactionService </p>
 * @author:         tianpengyu
 * @Date :          2018-07-06 14:36:50 
 */
public interface AppTransactionService  extends BaseService<AppTransaction, Long>{
    public hry.bean.PageResult findPageBySql(QueryFilter filter);

    public boolean dmWithdrawReject(ExDmTransaction exDmTransaction);

    public  boolean undo(Long id, String name);

}
