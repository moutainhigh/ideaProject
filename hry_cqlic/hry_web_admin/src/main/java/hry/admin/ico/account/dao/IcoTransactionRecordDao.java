/**
 * Copyright:    
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-01-14 16:50:30 
 */
package hry.admin.ico.account.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.admin.ico.account.model.IcoTransactionRecord;

import java.util.List;
import java.util.Map;


/**
 * <p> IcoTransactionRecordDao </p>
 * @author:         lzy
 * @Date :          2019-01-14 16:50:30  
 */
public interface IcoTransactionRecordDao extends  BaseDao<IcoTransactionRecord, Long> {

    List<IcoTransactionRecord> findPageBySql(Map<String, Object> map);
}
