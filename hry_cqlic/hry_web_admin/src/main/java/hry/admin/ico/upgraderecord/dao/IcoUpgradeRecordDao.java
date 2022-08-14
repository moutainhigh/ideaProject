/**
 * Copyright:    
 * @author:      houz
 * @version:     V1.0 
 * @Date:        2019-01-17 10:48:13 
 */
package hry.admin.ico.upgraderecord.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.admin.ico.upgraderecord.model.IcoUpgradeRecord;
import hry.util.QueryFilter;

import java.util.List;
import java.util.Map;


/**
 * <p> IcoUpgradeRecordDao </p>
 * @author:         houz
 * @Date :          2019-01-17 10:48:13  
 */
public interface IcoUpgradeRecordDao extends  BaseDao<IcoUpgradeRecord, Long> {

    List<IcoUpgradeRecord> findPageBySql(Map<String, Object> map);
}
