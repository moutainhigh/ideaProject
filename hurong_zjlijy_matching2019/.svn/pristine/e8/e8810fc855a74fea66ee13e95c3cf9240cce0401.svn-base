/**
 * Copyright:   
 * @author:      yaozh
 * @version:     V1.0 
 * @Date:        2019-10-14 16:56:38 
 */
package hry.cm2.customerminer.service;

import hry.cm2.customerminer.model.Cm2CustomerMiner;
import hry.core.mvc.service.base.BaseService;
import hry.cm2.customerminer.model.Cm2CustomerMinerProfit;

import java.math.BigDecimal;


/**
 * <p> Cm2CustomerMinerProfitService </p>
 * @author:         yaozh
 * @Date :          2019-10-14 16:56:38 
 */
public interface Cm2CustomerMinerProfitService  extends BaseService<Cm2CustomerMinerProfit, Long>{

    /**
     * 插入收益记录
     * @param cmCustomerMiner
     * @param status 发放状态 1.未发放 2已发放
     */
    void insertProit(Cm2CustomerMiner cmCustomerMiner, Integer status);

    /**
     * 查询用户有效投入总数
     * @param customerId
     * @return
     */
    public BigDecimal getTeamProfitSumByCustomerId(Long customerId);

    /**
     * 查询用户今天矿机产币总数
     * @param customerId
     * @return
     */
    public BigDecimal getProfitSumByCustomerId(Long customerId);

}
