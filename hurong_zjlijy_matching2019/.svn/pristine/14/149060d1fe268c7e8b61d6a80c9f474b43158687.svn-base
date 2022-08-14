/**
 * Copyright:
 *
 * @author: javalx
 * @version: V1.0
 * @Date: 2019-06-10 11:05:17
 */
package hry.social.miningreward.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.model.social.miningreward.SocialMiningRewardRecord;
import hry.model.social.miningreward.SocialPickRedis;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * <p> SocialMiningRewardRecordDao </p>
 *
 * @author: javalx
 * @Date :          2019-06-10 11:05:17
 */
public interface SocialMiningRewardRecordDao extends BaseDao<SocialMiningRewardRecord,Long> {

    /**
     * 是否超过最大未收取奖励
     *
     * @param customerId
     * @param maxUncollect
     * @return
     */
    int hasAboveMax(@Param("customerId") Long customerId, @Param("maxUncollect") Integer maxUncollect);

    /**
     * 查询用户可缓存的果园奖励数据
     *
     * @param customerId
     * @param limt
     * @return
     */
    List<SocialPickRedis> findPicked(@Param("customerId") Long customerId, @Param("limt") int limt);

    /**
     * 更新奖励状态
     *
     * @param id
     */
    void updateStates(@Param("id") Long id);

    /**
     * 奖励超时时效
     * @param id
     */
    void expiryStates(@Param("id") Long id);

    void expiryReward(@Param("expiryHour") Integer expiryHour);

}
