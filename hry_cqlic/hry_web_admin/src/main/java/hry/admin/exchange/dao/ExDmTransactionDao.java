/**
 * Copyright:    
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-13 13:59:41 
 */
package hry.admin.exchange.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.admin.exchange.model.ExDmTransaction;

import java.util.List;
import java.util.Map;


/**
 * <p> ExDmTransactionDao </p>
 * @author:         liushilei
 * @Date :          2018-06-13 13:59:41  
 */
public interface ExDmTransactionDao extends  BaseDao<ExDmTransaction, Long> {
    List<ExDmTransaction> findPageBySql(Map<String ,Object> map);

    List<ExDmTransaction> getYesterdayPostOrGet (Map<String, String> map);
}
