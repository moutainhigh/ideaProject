/**
 * Copyright:    
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-01-26 10:52:07 
 */
package hry.ico.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.ico.model.IcoAwardRecord;
import org.apache.ibatis.annotations.Param;


/**
 * <p> IcoAwardRecordDao </p>
 * @author:         lzy
 * @Date :          2019-01-26 10:52:07  
 */
public interface IcoAwardRecordDao extends  BaseDao<IcoAwardRecord, Long> {
    //统计推荐奖励（首推+推荐5%）
    String recommendedLockSum(@Param("customerId")Long customerId);
}
