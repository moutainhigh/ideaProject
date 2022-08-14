/**
 * Copyright:
 *
 * @author: lixin
 * @version: V1.0
 * @Date: 2019-05-13 10:22:31
 */
package hry.social.sns.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.social.manage.remote.model.sns.SocialSnsVideo;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * <p> SocialSnsVideoDao </p>
 *
 * @author: lixin
 * @Date :          2019-05-13 10:22:31
 */
public interface SocialSnsVideoDao extends BaseDao<SocialSnsVideo,Long> {

    /**
     * 查询帖子视频
     *
     * @param postsId
     * @return
     */
    List<String> getVideos(@Param("postsId") Long postsId);
}
