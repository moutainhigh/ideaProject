/**
 * Copyright:    
 * @author:      yaozh
 * @version:     V1.0 
 * @Date:        2019-10-15 09:46:22 
 */
package hry.cm2.profitone.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.cm2.profitone.model.Cm2ProfitOne;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;


/**
 * <p> Cm2ProfitOneDao </p>
 * @author:         yaozh
 * @Date :          2019-10-15 09:46:22  
 */
public interface Cm2ProfitOneDao extends  BaseDao<Cm2ProfitOne, Long> {

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
