/**
 * Copyright:    
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-04-28 15:24:04 
 */
package hry.klg.reward.dao;

import java.math.BigDecimal;
import java.util.Map;

import hry.core.mvc.dao.base.BaseDao;
import hry.klg.reward.model.KlgReward;


/**
 * <p> KlgRewardDao </p>
 * @author:         lzy
 * @Date :          2019-04-28 15:24:04  
 */
public interface KlgRewardDao extends  BaseDao<KlgReward, Long> {
	
	/**
     * 获取用户获取的奖励总和
     * @param customerId 用户id
     * @param rewardType 奖励类型1见点奖 2级差奖
     * @param coinCode 币种
     * @return
     */
    BigDecimal getRewardSumByTypeAndCustomerId(Map<String ,Object> map);

}
