/**
 * Copyright:
 *
 * @author: lixin
 * @version: V1.0
 * @Date: 2019-05-10 15:08:37
 */
package hry.social.sns.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.social.manage.remote.model.sns.SocialSnsPosts;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * <p> SocialSnsPostsDao </p>
 *
 * @author: lixin
 * @Date :          2019-05-10 15:08:37
 */
public interface SocialSnsPostsDao extends BaseDao<SocialSnsPosts,Long> {

    /**
     * 分页查询用户关注的帖子
     *
     * @param params
     * @return
     */
    List<SocialSnsPosts> concernedPostPage(Map<String,String> params);

    /**
     * 最近(新)的帖子分页查询
     *
     * @param params
     * @return
     */
    List<SocialSnsPosts> recentPost(Map<String,String> params);

    /**
     * 发现频道的帖子分页查询
     *
     * @param params
     * @return
     */
    List<SocialSnsPosts> discoverPost(Map<String,String> params);

    /**
     * 帖子详情查询
     *
     * @param postsId
     * @return
     */
    List<SocialSnsPosts> postDetails(@Param("postsId") Long postsId);
}
