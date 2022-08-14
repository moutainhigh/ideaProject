/**
 * Copyright:   
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-04-28 15:24:04 
 */
package hry.klg.reward.service;

import hry.core.mvc.service.base.BaseService;
import hry.klg.reward.model.KlgReward;

import java.math.BigDecimal;


/**
 * <p> KlgRewardService </p>
 * @author:         lzy
 * @Date :          2019-04-28 15:24:04 
 */
public interface KlgRewardService  extends BaseService<KlgReward, Long>{

    /**
     * 添加奖励记录
     * @param customerId
     * @param money
     * @param type
     */
    void saveKlgRewardRecord(Long foreignKey,String sellTransactionNum,Long customerId,Long accountId, BigDecimal money,Integer type);

    Object saveKlgRewardRecordNew(Long foreignKey,String sellTransactionNum,Long customerId,Long accountId, BigDecimal money,Integer type);

    /**
     * 获取用户获取的奖励总和
     * @param customerId 用户id
     * @param rewardType 奖励类型1见点奖 2级差奖
     * @param coinCode 币种
     * @return
     */
    BigDecimal getRewardSumByTypeAndCustomerId(Long customerId,Integer rewardType,String coinCode);

}
