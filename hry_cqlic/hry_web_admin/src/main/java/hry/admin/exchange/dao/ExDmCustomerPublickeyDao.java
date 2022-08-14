/**
 * Copyright:    
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-13 10:54:15 
 */
package hry.admin.exchange.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.admin.exchange.model.ExDmCustomerPublickey;

import java.util.List;
import java.util.Map;


/**
 * <p> ExDmCustomerPublickeyDao </p>
 * @author:         liushilei
 * @Date :          2018-06-13 10:54:15  
 */
public interface ExDmCustomerPublickeyDao extends  BaseDao<ExDmCustomerPublickey, Long> {
    /**
     * 新sql分页
     * @param map
     * @return
     */
    List<ExDmCustomerPublickey> findPageBySql(Map<String, Object> map);
}
