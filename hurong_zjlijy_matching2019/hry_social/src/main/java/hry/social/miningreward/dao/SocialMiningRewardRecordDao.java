/**
 * Copyright:
 *
 * @author: javalx
 * @version: V1.0
 * @Date: 2019-06-10 11:05:17
 */
package hry.social.miningreward.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.social.manage.remote.model.miningreward.SocialMiningRewardRecord;
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

    List<SocialMiningRewardRecord> findPageList(Map<String,Object> map);

    SocialMiningRewardRecord footing(Map<String,Object> map);
}
