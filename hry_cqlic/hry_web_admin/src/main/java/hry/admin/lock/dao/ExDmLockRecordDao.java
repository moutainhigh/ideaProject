/**
 * Copyright:    
 * @author:      liuchenghui
 * @version:     V1.0 
 * @Date:        2018-06-29 16:05:59 
 */
package hry.admin.lock.dao;

import hry.admin.lock.model.ExDmLockRecord;
import hry.core.mvc.dao.base.BaseDao;

import java.util.List;
import java.util.Map;


/**
 * <p> ExDmLockRecordDao </p>
 * @author:         liuchenghui
 * @Date :          2018-06-29 16:05:59  
 */
public interface ExDmLockRecordDao extends  BaseDao<ExDmLockRecord, Long> {

    List<ExDmLockRecord> findPageBySql(Map<String, Object> map);

    List<ExDmLockRecord> getRecordBycurdate (Map<String, Object> map);
}
