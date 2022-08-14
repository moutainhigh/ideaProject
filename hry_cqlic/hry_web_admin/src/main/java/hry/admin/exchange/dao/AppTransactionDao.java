/**
 * Copyright:    
 * @author:      tianpengyu
 * @version:     V1.0 
 * @Date:        2018-07-06 14:36:50 
 */
package hry.admin.exchange.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.admin.exchange.model.AppTransaction;

import java.util.List;
import java.util.Map;


/**
 * <p> AppTransactionDao </p>
 * @author:         tianpengyu
 * @Date :          2018-07-06 14:36:50  
 */
public interface AppTransactionDao extends  BaseDao<AppTransaction, Long> {
    List<AppTransaction> findPageBySql(Map<String, Object> map);
}
