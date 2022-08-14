/**
 * Copyright:   
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-01-14 12:02:43 
 */
package hry.admin.ico.account.service;

import hry.bean.PageResult;
import hry.core.mvc.service.base.BaseService;
import hry.admin.ico.account.model.IcoAccount;
import hry.util.QueryFilter;


/**
 * <p> IcoAccountService </p>
 * @author:         lzy
 * @Date :          2019-01-14 12:02:43 
 */
public interface IcoAccountService  extends BaseService<IcoAccount, Long>{

    public PageResult findPageBySql(QueryFilter filter);
}
