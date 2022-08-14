/**
 * Copyright:    
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-01-14 19:47:55 
 */
package hry.admin.ico.account.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.admin.ico.account.model.IcoLockRecord;

import java.util.List;
import java.util.Map;


/**
 * <p> IcoLockRecordDao </p>
 * @author:         lzy
 * @Date :          2019-01-14 19:47:55  
 */
public interface IcoLockRecordDao extends  BaseDao<IcoLockRecord, Long> {
        List<IcoLockRecord> findPageBySql(Map<String, Object> map);
}
