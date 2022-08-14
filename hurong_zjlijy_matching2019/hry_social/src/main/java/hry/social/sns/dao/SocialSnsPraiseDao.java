/**
 * Copyright:
 *
 * @author: lixin
 * @version: V1.0
 * @Date: 2019-05-13 10:20:37
 */
package hry.social.sns.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.social.manage.remote.model.sns.SocialSnsPraise;
import org.apache.ibatis.annotations.Param;


/**
 * <p> SocialSnsPraiseDao </p>
 *
 * @author: lixin
 * @Date :          2019-05-13 10:20:37
 */
public interface SocialSnsPraiseDao extends BaseDao<SocialSnsPraise,Long> {

    /**
     * 统计点赞数
     *
     * @param postsId
     * @return
     */
    int countPraise(@Param("postsId") Long postsId);

    /**
     * 查询帖子是否被用户点赞
     *
     * @param postsId
     * @param praiseId
     * @return
     */
    int hasPraise(@Param("postsId") Long postsId, @Param("praiseId") String praiseId);

    /**
     * 删除点赞
     *
     * @param postsId
     * @param customerId
     */
    void deletePraise(@Param("postsId") Long postsId, @Param("praiseId") Long customerId);
}
