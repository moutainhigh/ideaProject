/**
 * Copyright:
 *
 * @author: lixin
 * @version: V1.0
 * @Date: 2019-05-13 10:17:43
 */
package hry.social.sns.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.social.manage.remote.model.sns.SocialSnsComment;
import org.apache.ibatis.annotations.Param;


/**
 * <p> SocialSnsCommentDao </p>
 *
 * @author: lixin
 * @Date :          2019-05-13 10:17:43
 */
public interface SocialSnsCommentDao extends BaseDao<SocialSnsComment,Long> {

    /**
     * 查询帖子评论数
     *
     * @param postsId
     * @return
     */
    int countComment(@Param("postsId") Long postsId);

    /**
     * 查询帖子是否被用户评论
     *
     * @param postsId
     * @param commentId
     * @return
     */
    int hasComment(@Param("postsId") Long postsId, @Param("commentId") String commentId);
}
