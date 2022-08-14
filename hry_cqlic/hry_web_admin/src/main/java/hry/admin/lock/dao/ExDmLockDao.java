/**
 * Copyright:    
 * @author:      liuchenghui
 * @version:     V1.0 
 * @Date:        2018-06-29 11:44:56 
 */
package hry.admin.lock.dao;

import hry.admin.lock.model.ExDmLock;
import hry.core.mvc.dao.base.BaseDao;

import java.util.List;
import java.util.Map;


/**
 * <p> ExDmLockDao </p>
 * @author:         liuchenghui
 * @Date :          2018-06-29 11:44:56  
 */
public interface ExDmLockDao extends  BaseDao<ExDmLock, Long> {

    List<ExDmLock> findPageBySql (Map<String, Object> map);
}
