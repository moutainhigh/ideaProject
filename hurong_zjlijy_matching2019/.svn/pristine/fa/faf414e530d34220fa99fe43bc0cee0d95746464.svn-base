/**
 * Copyright:   
 * @author:      yaozh
 * @version:     V1.0 
 * @Date:        2019-10-15 09:46:22 
 */
package hry.cm2.profitone.service;

import hry.core.mvc.service.base.BaseService;
import hry.cm2.profitone.model.Cm2ProfitOne;

import java.math.BigDecimal;


/**
 * <p> Cm2ProfitOneService </p>
 * @author:         yaozh
 * @Date :          2019-10-15 09:46:22 
 */
public interface Cm2ProfitOneService  extends BaseService<Cm2ProfitOne, Long>{

    /**
     * 发矿工动态收益
     * @param messageStr
     */
    public void profitOne(String messageStr);


    /**
     * 查询直推用户今天动态收益总和
     * @param customerId
     * @return
     */
    public BigDecimal getTeamProfitSumByCustomerId(Long customerId, Integer type);

    /**
     * 查询用户今天动态收益总和
     * @param customerId
     * @return
     */
    public BigDecimal getProfitSumByCustomerId(Long customerId);

    /**
     * 查询用户今天烧伤收益总和
     * @param customerId
     * @return
     */
    public BigDecimal getProfit3SumByCustomerId(Long customerId);

    /**
     * 查询用户动态收益总和
     * @param customerId
     * @return
     */
    public BigDecimal getProfitAllByCustomerId(Long customerId);

}
