/**
 * Copyright:   
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-02-26 13:46:47 
 */
package hry.admin.ico.account.service;

import hry.bean.PageResult;
import hry.core.mvc.service.base.BaseService;
import hry.admin.ico.account.model.IcoTransferAccounts;
import hry.util.QueryFilter;


/**
 * <p> IcoTransferAccountsService </p>
 * @author:         lzy
 * @Date :          2019-02-26 13:46:47 
 */
public interface IcoTransferAccountsService  extends BaseService<IcoTransferAccounts, Long>{

    /**
     * sql分页查询
     * @param filter
     * @return
     */
    public PageResult findPageBySql(QueryFilter filter);

}
