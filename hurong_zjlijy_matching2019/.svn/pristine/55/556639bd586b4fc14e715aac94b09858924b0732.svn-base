/**
 * Copyright:
 *
 * @author: lixin
 * @version: V1.0
 * @Date: 2019-05-13 10:21:43
 */
package hry.social.sns.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.social.manage.remote.model.sns.SocialSnsShare;
import org.apache.ibatis.annotations.Param;


/**
 * <p> SocialSnsShareDao </p>
 *
 * @author: lixin
 * @Date :          2019-05-13 10:21:43
 */
public interface SocialSnsShareDao extends BaseDao<SocialSnsShare,Long> {

    /**
     * 查询帖子分享数
     *
     * @param postsId
     * @return
     */
    int countShare(@Param("postsId") Long postsId);

    /**
     * 查询帖子是否被用户分享
     *
     * @param postsId
     * @param shareId
     * @return
     */
    int hasShare(@Param("postsId") Long postsId, @Param("shareId") String shareId);
}
