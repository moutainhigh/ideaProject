/**
 * Copyright:    
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2019-08-14 15:22:23 
 */
package hry.admin.licqb.record.dao;

import hry.admin.licqb.platform.model.CustomerLevel;
import hry.admin.licqb.record.model.AppTeamLevel;
import hry.core.mvc.dao.base.BaseDao;
import hry.admin.licqb.record.model.DealRecord;

import java.util.List;
import java.util.Map;


/**
 * <p> DealRecordDao </p>
 * @author:         zhouming
 * @Date :          2019-08-14 15:22:23  
 */
public interface DealRecordDao extends  BaseDao<DealRecord, Long> {

    public List<DealRecord> findPageBySql(Map<String, Object> map);
    public Long findPageBySqlCount(Map<String, Object> map);

    public Integer getUserMaxTier();

    public Integer getUserAllLevel(Long customerId);

    public Integer getUserNum(Long customerId);

    public AppTeamLevel getAppTeamLevel(Long customerId);

    public List<CustomerLevel> findUserTeamInfo(Map<String, Object> map);

    public Long getDealDmMoney(Integer optType);


}
