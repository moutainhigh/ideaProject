/**
 * Copyright:
 *
 * @author: javalx
 * @version: V1.0
 * @Date: 2019-06-10 11:13:59
 */
package hry.social.miningreward.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.model.social.miningreward.SocialMiningGatherRecord;
import hry.model.social.miningreward.SocialMiningRewardRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * <p> SocialMiningGatherRecordDao </p>
 *
 * @author: javalx
 * @Date :          2019-06-10 11:13:59
 */
public interface SocialMiningGatherRecordDao extends BaseDao<SocialMiningGatherRecord,Long> {

    List<SocialMiningGatherRecord> findPageList(Map<String,Object> map);

    SocialMiningGatherRecord footing(Map<String,Object> map);

    /**
     * 查询收取记录
     *
     * @param customerId 收取用户ID
     * @param pickId     果园奖励ID
     * @return
     */
    int hasPick(@Param("customerId") Long customerId, @Param("pickId") Long pickId);

    /**
     * @param customerId
     * @param friendId
     * @return
     */
    SocialMiningRewardRecord getGatherInfo(@Param("customerId") Long customerId, @Param("friendId") Long friendId);

    /**
     * 好友动态分页查询
     *
     * @param params
     * @return
     */
    List<SocialMiningGatherRecord> findFrontPage(Map<String,String> params);

    /**
     * 首页动态分页查询
     *
     * @param params
     * @return
     */

    List<SocialMiningGatherRecord> miningRewardRecord(Map<String,String> params);

    /**
     * 查询所有用户ID
     * @return
     */
    List<Long> getAllUser();

}
