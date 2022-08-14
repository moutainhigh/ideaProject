/**
 * Copyright:    
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2020-01-08 14:06:05 
 */
package hry.cm5.miner.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.cm5.miner.model.Cm5AwardRecord;

import java.math.BigDecimal;
import java.util.Map;


/**
 * <p> Cm5AwardRecordDao </p>
 * @author:         zhouming
 * @Date :          2020-01-08 14:06:05  
 */
public interface Cm5AwardRecordDao extends  BaseDao<Cm5AwardRecord, Long> {

    public BigDecimal getAwardTotal(Map<String, Object> map);
}
