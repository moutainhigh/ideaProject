/**
 * Copyright:
 *
 * @author: lixin
 * @version: V1.0
 * @Date: 2019-05-13 10:21:08
 */
package hry.social.sns.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.social.manage.remote.model.sns.SocialSnsReward;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;


/**
 * <p> SocialSnsRewardDao </p>
 *
 * @author: lixin
 * @Date :          2019-05-13 10:21:08
 */
public interface SocialSnsRewardDao extends BaseDao<SocialSnsReward,Long> {

    /**
     * 查询帖子打赏数
     *
     * @param postsId
     * @return
     */
    BigDecimal countReward(@Param("postsId") Long postsId);

    /**
     * 查询帖子是否被用户打赏
     *
     * @param postsId
     * @param rewardId
     * @return
     */
    int hasReward(@Param("postsId") Long postsId, @Param("rewardId") String rewardId);
}
