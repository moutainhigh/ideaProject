/**
 * Copyright:
 * @author:      lzy
 * @version:     V1.0
 * @Date:        2019-04-17 14:01:32
 */
package hry.klg.level.service;

import hry.bean.JsonResult;
import hry.core.mvc.service.base.BaseService;
import hry.customer.user.model.AppCustomer;
import hry.klg.level.model.KlgCustomerLevel;

import java.math.BigDecimal;


/**
 * <p> KlgCustomerLevelService </p>
 * @author:         lzy
 * @Date :          2019-04-17 14:01:32
 */
public interface KlgCustomerLevelService  extends BaseService<KlgCustomerLevel, Long>{

    void addUserLevel(AppCustomer customer);

    /**
     * 根据用户获取当前等级配置
     * @param customerId
     * @return
     */
    KlgCustomerLevel getLevelConfigByCustomerId(Long customerId);

    /**
     * 减少奖励额度
     * @param customerId
     * @param rewardNum
     * @return
     */
    public JsonResult reduceReward(Long customerId, BigDecimal rewardNum);

    /**
     *获得奖励额度
     */
    public void buyRewardQuotaAdd(Long customerId,BigDecimal buyNum);

    //预约获得20% 奖励额度
    public void bookingRewardQuotaAdd(Long customerId,BigDecimal buyNum);

    /**
     * 升级操作
     * @param leveId
     * @param customerId
     * @return
     */
    public JsonResult upgradeUserLevel(Long leveId,Long customerId);

    /**
     * 重置奖励额度
     */
    public void resetRewardQuota();

    //下面星级用户统计
    Integer sumStarCount(Long customerId,Integer level);

    //修改用户 级差配置
    void updateCustomerRewardConfig(Long customerId);
}
