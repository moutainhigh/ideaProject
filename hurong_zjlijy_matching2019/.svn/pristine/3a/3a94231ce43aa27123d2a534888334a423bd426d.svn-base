/**
 * Copyright:    
 * @author:      yaozh
 * @version:     V1.0 
 * @Date:        2019-10-14 16:46:22 
 */
package hry.cm2.customerminer.dao;

import hry.cm2.customer.model.Cm2Customer;
import hry.core.mvc.dao.base.BaseDao;
import hry.cm2.customerminer.model.Cm2CustomerMiner;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;


/**
 * <p> Cm2CustomerMinerDao </p>
 * @author:         yaozh
 * @Date :          2019-10-14 16:46:22  
 */
public interface Cm2CustomerMinerDao extends  BaseDao<Cm2CustomerMiner, Long> {

    /**
     * 更新矿机收益  正数加   负数减
     * @param id 用户矿机Id
     * @param profit1 已领取收益
     * @param profit2 未领取收益
     * @param profit3 总产出收益
     */
    void updateMinerProfit(@Param("id")Long id, @Param("profit1")BigDecimal profit1, @Param("profit2")BigDecimal profit2, @Param("profit3")BigDecimal profit3);

    /**
     * 查询用户有效投入总数
     * @param customerId
     * @return
     */
    public BigDecimal getMinerPriceSumByCustomerId(@Param("customerId") Long customerId);
    /**
     * 查询用户伞下有效投入总数
     * @param customerId
     * @return
     */
    public BigDecimal getTeamMinerPriceSumByCustomerId(@Param("customerId") Long customerId);
    /**
     * 查询用户最大线 最小线
     * @param customerId
     * @return
     */
    public Cm2Customer getTeamMaxAndMinByCustomerId(@Param("customerId") Long customerId);
    /**
     * 查询用户团队人数
     * @param customerId
     * @return
     */
    public Integer getTeamNumByCustomerId(@Param("customerId") Long customerId);
    /**
     /* 查询用户有效直推人数
     * @param customerId
     * @return
     */
    public Integer getYouxiaoZtNumByCustomerId(@Param("customerId") Long customerId);

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
    public List<Cm2CustomerMiner> getMyMinerCustomerId(@Param("customerId") Long customerId);

    /**
     * 查询用户待运行+运行中的矿机总数
     * @param customerId
     * @return
     */
    public Integer getMinigNumByCustomerId(@Param("customerId") Long customerId);

    /**
     * 查询用户矿机总产出
     * @param customerId
     * @return
     */
    public BigDecimal getMinigProFitByCustomerId(@Param("customerId") Long customerId);

    /**
     * 根据矿机Id查询用户待运行+运行中的矿机总数
     * @param customerId
     * @return
     */
    public Integer getMinigNumByCustomerIdAndMinerId(@Param("customerId") Long customerId,@Param("minerId") Long minerId);

    /**
     * 查询当前层级用户今天产币总和
     * @param customerId
     * @param hierarchy
     * @return
     */
    public BigDecimal getProfitSumByCustomerIdAndHierarchy(@Param("customerId") Long customerId,@Param("hierarchy") Integer hierarchy);


}
