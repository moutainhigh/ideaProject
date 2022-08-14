/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      liuchenghui
 * @version:     V1.0 
 * @Date:        2018-04-27 16:33:26 
 */
package hry.exchange.lock.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.exchange.lock.model.ExDmLockRecord;

import java.util.List;
import java.util.Map;


/**
 * <p> ExDmLockRecordDao </p>
 * @author:         liuchenghui
 * @Date :          2018-04-27 16:33:26  
 */
public interface ExDmLockRecordDao extends  BaseDao<ExDmLockRecord, Long> {

	public List<ExDmLockRecord> findPageBySql(Map<String, Object> map);

	List<ExDmLockRecord> getRecordBycurdate (Map<String, Object> map);
}
