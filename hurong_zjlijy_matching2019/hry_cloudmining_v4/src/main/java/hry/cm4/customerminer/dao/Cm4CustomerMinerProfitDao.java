/**
 * Copyright:    
 * @author:      yaozh
 * @version:     V1.0 
 * @Date:        2019-11-21 10:00:54 
 */
package hry.cm4.customerminer.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.cm4.customerminer.model.Cm4CustomerMinerProfit;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;


/**
 * <p> Cm4CustomerMinerProfitDao </p>
 * @author:         yaozh
 * @Date :          2019-11-21 10:00:54  
 */
public interface Cm4CustomerMinerProfitDao extends  BaseDao<Cm4CustomerMinerProfit, Long> {
    /**
     * 查询直推用户今天静态收益总数
     * @param customerId
     * @return
     */
    public BigDecimal getTeamProfitSumByCustomerId(@Param("customerId") Long customerId);
    /**
     * 查询用户今天矿机产币总数
     * @param customerId
     * @return
     */
    public BigDecimal getProfitSumByCustomerId(@Param("customerId") Long customerId);

}
