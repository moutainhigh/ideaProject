/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      liuchenghui
 * @version:     V1.0 
 * @Date:        2018-04-25 11:46:57 
 */
package hry.exchange.lock.dao;

import java.util.List;
import java.util.Map;

import hry.core.mvc.dao.base.BaseDao;
import hry.exchange.lock.model.ExDmLock;


/**
 * <p> ExDmLockDao </p>
 * @author:         liuchenghui
 * @Date :          2018-04-25 11:46:57  
 */
public interface ExDmLockDao extends  BaseDao<ExDmLock, Long> {

	public List<ExDmLock> findPageBySql(Map<String, Object> map);
}
