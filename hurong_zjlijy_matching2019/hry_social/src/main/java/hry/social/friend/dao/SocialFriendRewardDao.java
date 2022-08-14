/**
 * Copyright:
 *
 * @author: javalx
 * @version: V1.0
 * @Date: 2019-06-04 10:07:52
 */
package hry.social.friend.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.social.manage.remote.model.friend.SocialFriendReward;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


/**
 * <p> SocialFriendRewardDao </p>
 *
 * @author: javalx
 * @Date :          2019-06-04 10:07:52
 */
public interface SocialFriendRewardDao extends BaseDao<SocialFriendReward,Long> {

    SocialFriendReward footing();

    /**
     * 根据帖子ID查询对应帖子的总打赏值
     *
     * @param circleId
     * @return
     */
    Integer sumRewards(@Param("circleId") Long circleId);

    /**
     * 查询总打赏值
     *
     * @param customerId
     * @return
     */
    BigDecimal friendReward(@Param("customerId") Long customerId);

    /**
     * 判断是否打赏过
     *
     * @param sfcId
     * @param customerId
     * @return
     */
    int hasReward(@Param("sfcId") Long sfcId, @Param("customerId") Long customerId);

    /**
     * 查询帖子打赏信息
     *
     * @param sfcId
     * @return
     */
    List<SocialFriendReward> getRewards(@Param("customerId") Long customerId, @Param("sfcId") Long sfcId);

    /**
     * 查询帖子的总打赏量
     *
     * @param sfcId
     * @return
     */
    BigDecimal totalRewards(@Param("sfcId") Long sfcId);

    /**
     * 分页查询打赏就
     *
     * @param params
     * @return
     */
    List<SocialFriendReward> findPageList(Map<String,String> params);

}
