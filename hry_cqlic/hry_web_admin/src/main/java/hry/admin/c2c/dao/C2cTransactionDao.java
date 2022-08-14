/**
 * Copyright:    
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-13 13:34:46 
 */
package hry.admin.c2c.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.admin.c2c.model.C2cTransaction;

import java.util.List;
import java.util.Map;


/**
 * <p> C2cTransactionDao </p>
 * @author:         liushilei
 * @Date :          2018-06-13 13:34:46  
 */
public interface C2cTransactionDao extends  BaseDao<C2cTransaction, Long> {
    /**
     * sql分页
     * @param map
     * @return
     */
    List<C2cTransaction> findPageBySql(Map<String, Object> map);

    List<C2cTransaction> getC2cList(Map<String,Object> map);
}
