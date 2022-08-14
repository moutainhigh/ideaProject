/**
 * Copyright:    
 * @author:      yaozh
 * @version:     V1.0 
 * @Date:        2019-10-14 16:56:38 
 */
package hry.cm2.customerminer.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.cm2.customerminer.model.Cm2CustomerMinerProfit;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;


/**
 * <p> Cm2CustomerMinerProfitDao </p>
 * @author:         yaozh
 * @Date :          2019-10-14 16:56:38  
 */
public interface Cm2CustomerMinerProfitDao extends  BaseDao<Cm2CustomerMinerProfit, Long> {

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
