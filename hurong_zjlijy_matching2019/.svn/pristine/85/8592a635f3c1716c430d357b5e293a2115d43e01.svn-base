/**
 * Copyright:   
 * @author:      luyue
 * @version:     V1.0 
 * @Date:        2019-03-19 15:25:34 
 */
package hry.mall.integral.service;

import hry.bean.JsonResult;
import hry.core.mvc.service.base.BaseService;
import hry.mall.integral.model.IntegralLevel;

import java.math.BigDecimal;


/**
 * <p> IntegralLevelService </p>
 * @author:         luyue
 * @Date :          2019-03-19 15:25:34 
 */
public interface IntegralLevelService  extends BaseService<IntegralLevel, Long>{

    /**
     * 计算用户购买会员的价格
     * @param memberId  用户ID
     * @param levelId    会员等级id
     * @return
     */
    BigDecimal calculationPrice(Long memberId, Long levelId);

    /**
     * 给上级发放推广额度
     * @param memberId  下级用户ID
     * @return
     */
    Boolean giveRecommendQuota(Long memberId);


    /**
     * 激活返佣（1级返直推奖励，其他级别返拓展奖励）
     * @param memberId  用户ID
     * @param levelId    会员等级id
     * @param money    激活金额
     * @param series  返佣层级
     * @return
     */
    JsonResult activateRebate(Long memberId, Long levelId, BigDecimal money, Integer series);


    /**
     * 用户是否已有非默认会员且未过期
     * @param memberId  用户ID
     * @return
     */
    Boolean  isEffectiveLevel(Long memberId);


    /**
     * 通过金额获取对应的会员等级
     * @param account 购买金额
     * @return
     */
    IntegralLevel getLevelByAccount(BigDecimal account);


}
