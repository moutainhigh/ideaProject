/**
 * Copyright:   
 * @author:      yaozh
 * @version:     V1.0 
 * @Date:        2019-10-14 16:46:22 
 */
package hry.cm2.customerminer.service;

import hry.cm2.customer.model.Cm2Customer;
import hry.core.mvc.service.base.BaseService;
import hry.cm2.customerminer.model.Cm2CustomerMiner;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;


/**
 * <p> Cm2CustomerMinerService </p>
 * @author:         yaozh
 * @Date :          2019-10-14 16:46:22 
 */
public interface Cm2CustomerMinerService  extends BaseService<Cm2CustomerMiner, Long>{

    /**
     * admin定时任务矿机产币,按天产币
     */
    void minerCoinageByDay(String message);

    /**
     * 更新矿机收益  正数加   负数减
     * @param id 用户矿机Id
     * @param profit1 已领取收益
     * @param profit2 未领取收益
     * @param profit3 总产出收益
     */
    void updateMinerProfit(Long id,BigDecimal profit1,BigDecimal profit2,BigDecimal profit3);


    /**
     * 查询用户有效投入总数
     * @param customerId
     * @return
     */
    public BigDecimal getMinerPriceSumByCustomerId(Long customerId);
    /**
     * 查询用户伞下有效投入总数
     * @param customerId
     * @return
     */
    public BigDecimal getTeamMinerPriceSumByCustomerId(Long customerId);
    /**
     * 查询用户最大线 最小线
     * @param customerId
     * @return
     */
    public Cm2Customer getTeamMaxAndMinByCustomerId(Long customerId);
    /**
     * 查询用户团队人数
     * @param customerId
     * @return
     */
    public Integer getTeamNumByCustomerId(Long customerId);
    /**
     * 查询用户有效直推人数
     * @param customerId
     * @return
     */
    public Integer getYouxiaoZtNumByCustomerId(Long customerId);
    /**
     * 更新到期矿机状态
     */
    void updateCloseMiner();
    /**
     * 更新待运行矿机状态
     */
    void updateWaitMiner();

    /**
     * 查询用户所有矿机统计
     * @param customerId
     * @return
     */
    public List<Cm2CustomerMiner> getMyMinerCustomerId(Long customerId);

    /**
     * 查询用户待运行+运行中的矿机总数
     * @param customerId
     * @return
     */
    public Integer getMinigNumByCustomerId(Long customerId);

    /**
     * 查询用户矿机总产出
     * @param customerId
     * @return
     */
    public BigDecimal getMinigProFitByCustomerId(Long customerId);

    /**
     * 根据矿机id查询用户待运行+运行中的矿机数量
     * @param customerId
     * @return
     */
    public Integer getMinigNumByCustomerIdAndMinerId(Long customerId,Long minerId);

    /**
     * 查询当前层级用户今天产币总和
     * @param customerId 用户ID
     * @param hierarchy 层级
     * @return
     */
    public BigDecimal getProfitSumByCustomerIdAndHierarchy(Long customerId,Integer hierarchy);


}
