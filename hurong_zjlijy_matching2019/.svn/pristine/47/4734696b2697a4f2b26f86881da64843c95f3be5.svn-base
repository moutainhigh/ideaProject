/**
 * Copyright:    
 * @author:      luyue
 * @version:     V1.0 
 * @Date:        2019-05-31 14:56:41 
 */
package hry.mall.integral.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.mall.integral.model.LevelRecord;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;


/**
 * <p> LevelRecordDao </p>
 * @author:         luyue
 * @Date :          2019-05-31 14:56:41  
 */
public interface LevelRecordDao extends  BaseDao<LevelRecord, Long> {

    /**
     * 获取用户的个人业绩
     * @param customerId   用户id
     * @return
     */
    BigDecimal getUserPerformance(@Param("customerId") Long customerId);


    /**
     * 获取用户的团队业绩
     * @param customerIds  团队用户集合
     * @return
     */
    BigDecimal getTeamPerformance(@Param("customerIds") List<Long> customerIds);
}
