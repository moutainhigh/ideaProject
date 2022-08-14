/**
 * Copyright:    
 * @author:      yaozh
 * @version:     V1.0 
 * @Date:        2019-11-21 10:04:25 
 */
package hry.cm4.profitone.dao;

import hry.cm4.profitone.model.Cm4ProfitOne;
import hry.core.mvc.dao.base.BaseDao;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;


/**
 * <p> Cm4ProfitOneDao </p>
 * @author:         yaozh
 * @Date :          2019-11-21 10:04:25  
 */
public interface Cm4ProfitOneDao extends  BaseDao<Cm4ProfitOne, Long> {
    /**
     * 查询直推用户今天动态收益总和
     * @param customerId
     * @return
     */
    public BigDecimal getTeamProfitSumByCustomerId(@Param("customerId") Long customerId, @Param("type")Integer type);

    /**
     * 查询用户今天动态收益总和
     * @param customerId
     * @return
     */
    public BigDecimal getProfitSumByCustomerId(@Param("customerId") Long customerId);

    /**
     * 查询用户今天烧伤收益总和
     * @param customerId
     * @return
     */
    public BigDecimal getProfit3SumByCustomerId(@Param("customerId") Long customerId);

    /**
     * 查询用户动态收益总和
     * @param customerId
     * @return
     */
    public BigDecimal getProfitAllByCustomerId(@Param("customerId") Long customerId);

}
