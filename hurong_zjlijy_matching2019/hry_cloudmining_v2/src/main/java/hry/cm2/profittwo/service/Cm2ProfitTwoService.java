/**
 * Copyright:   
 * @author:      yaozh
 * @version:     V1.0 
 * @Date:        2019-10-15 09:46:51 
 */
package hry.cm2.profittwo.service;

import hry.core.mvc.service.base.BaseService;
import hry.cm2.profittwo.model.Cm2ProfitTwo;

import java.math.BigDecimal;


/**
 * <p> Cm2ProfitTwoService </p>
 * @author:         yaozh
 * @Date :          2019-10-15 09:46:51 
 */
public interface Cm2ProfitTwoService  extends BaseService<Cm2ProfitTwo, Long>{

    /**
     * 发放矿场收益定时任务
     */
    void grantProfit();

    /**
     * 更新未发放收益为已发放
     * @param customerId
     */
    void updateStatus(Long customerId);

    /**
     * 用户今天矿场收益的总数
     * @param customerId
     * @return
     */
    public BigDecimal getTodayProfitSum(Long customerId);

    /**
     * 用户矿场总收益的总数
     * @param customerId
     * @return
     */
    public BigDecimal getTotalProfitSum(Long customerId);


}
