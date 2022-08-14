/**
 * Copyright:   
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-15 13:08:06 
 */
package hry.admin.customer.service;

import hry.bean.PageResult;
import hry.core.mvc.service.base.BaseService;
import hry.admin.customer.model.AppAccount;
import hry.util.QueryFilter;



/**
 * <p> AppAccountService </p>
 * @author:         liushilei
 * @Date :          2018-06-15 13:08:06 
 */
public interface AppAccountService  extends BaseService<AppAccount, Long>{


    PageResult findPageBySqlList(QueryFilter filter);
}
