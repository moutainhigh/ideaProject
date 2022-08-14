/**
 * Copyright:    
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-05-17 13:37:26 
 */
package hry.admin.klg.level.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.admin.klg.level.model.KlgUpgradeRecord;

import java.util.List;
import java.util.Map;


/**
 * <p> KlgUpgradeRecordDao </p>
 * @author:         lzy
 * @Date :          2019-05-17 13:37:26  
 */
public interface KlgUpgradeRecordDao extends  BaseDao<KlgUpgradeRecord, Long> {
    List<KlgUpgradeRecord> findPageBySql(Map<String, Object> map);
}
