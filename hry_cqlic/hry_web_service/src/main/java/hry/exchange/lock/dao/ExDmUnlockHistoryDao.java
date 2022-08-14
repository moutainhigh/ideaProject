/**
 * Copyright:    
 * @author:      liuchenghui
 * @version:     V1.0 
 * @Date:        2018-09-20 17:34:28 
 */
package hry.exchange.lock.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.exchange.lock.model.ExDmUnlockHistory;

import java.util.List;
import java.util.Map;


/**
 * <p> ExDmUnlockHistoryDao </p>
 * @author:         liuchenghui
 * @Date :          2018-09-20 17:34:28  
 */
public interface ExDmUnlockHistoryDao extends  BaseDao<ExDmUnlockHistory, Long> {

    List<ExDmUnlockHistory> findPageByCondition (Map<String, Object> map);
}
