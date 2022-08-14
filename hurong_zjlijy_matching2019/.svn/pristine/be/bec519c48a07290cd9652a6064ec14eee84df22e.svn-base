/**
 * Copyright:   
 * @author:      yaozh
 * @version:     V1.0 
 * @Date:        2019-11-21 10:00:54 
 */
package hry.cm4.customerminer.service;

import hry.cm4.customerminer.model.Cm4CustomerMiner;
import hry.cm4.customerminer.model.Cm4CustomerMinerProfit;
import hry.core.mvc.service.base.BaseService;

import java.math.BigDecimal;


/**
 * <p> Cm4CustomerMinerProfitService </p>
 * @author:         yaozh
 * @Date :          2019-11-21 10:00:54 
 */
public interface Cm4CustomerMinerProfitService  extends BaseService<Cm4CustomerMinerProfit, Long>{

    /**
     * 插入收益记录
     * @param cmCustomerMiner
     * @param status 发放状态 1.未发放 2已发放
     * @param coinCode 发放币种
     * @param profitProportion 发放比例
     * @param saasId 币种行情字符串
     * @param coinNum 收益数量
     */
    void insertProit(Cm4CustomerMiner cmCustomerMiner, Integer status, String coinCode, BigDecimal profitProportion, String saasId,BigDecimal coinNum);

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
