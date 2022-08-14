/**
 * Copyright:   
 * @author:      yaozh
 * @version:     V1.0 
 * @Date:        2019-11-21 10:04:26 
 */
package hry.cm4.profitone.service;

import hry.core.mvc.service.base.BaseService;
import hry.cm4.profitone.model.Cm4ProfitOne;

import java.math.BigDecimal;


/**
 * <p> Cm4ProfitOneService </p>
 * @author:         yaozh
 * @Date :          2019-11-21 10:04:26 
 */
public interface Cm4ProfitOneService  extends BaseService<Cm4ProfitOne, Long>{

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
