/**
 * Copyright:
 *
 * @author: lixin
 * @version: V1.0
 * @Date: 2019-05-13 10:19:34
 */
package hry.social.sns.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.social.manage.remote.model.sns.SocialSnsFavorite;
import org.apache.ibatis.annotations.Param;


/**
 * <p> SocialSnsFavoriteDao </p>
 *
 * @author: lixin
 * @Date :          2019-05-13 10:19:34
 */
public interface SocialSnsFavoriteDao extends BaseDao<SocialSnsFavorite,Long> {

    /**
     * 查询帖子收藏数
     *
     * @param postsId
     * @return
     */
    int countFavorite(@Param("postsId") Long postsId);

    /**
     * 查询帖子是否被用户收藏
     *
     * @param postsId
     * @param favoriteId
     * @return
     */
    int hasFavorite(@Param("postsId") Long postsId, @Param("favoriteId") String favoriteId);

    /**
     * 删除收藏
     *
     * @param postsId
     * @param customerId
     */
    void deleteFavorite(@Param("postsId") Long postsId, @Param("favoriteId") Long customerId);
}
