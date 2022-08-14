/**
 * Copyright:    
 * @author:      houz
 * @version:     V1.0 
 * @Date:        2019-01-14 17:16:18 
 */
package hry.admin.ico.award.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.admin.ico.award.model.IcoAwardRecord;

import java.util.List;
import java.util.Map;


/**
 * <p> IcoAwardRecordDao </p>
 * @author:         houz
 * @Date :          2019-01-14 17:16:18  
 */
public interface IcoAwardRecordDao extends  BaseDao<IcoAwardRecord, Long> {

    List<IcoAwardRecord> findPageBySql(Map<String, Object> map);

}
