/**
 * Copyright:    
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-15 13:08:06 
 */
package hry.admin.customer.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.admin.customer.model.AppAccount;

import java.util.List;
import java.util.Map;


/**
 * <p> AppAccountDao </p>
 * @author:         liushilei
 * @Date :          2018-06-15 13:08:06  
 */
public interface AppAccountDao extends  BaseDao<AppAccount, Long> {

    List<AppAccount> findPageBySqlList(Map<String, Object> map);

    List<String> findCustomerByCondition(Map<String, Object> mapcustomer);
}
