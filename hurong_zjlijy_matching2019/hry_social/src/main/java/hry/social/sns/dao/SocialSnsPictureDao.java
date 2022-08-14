/**
 * Copyright:
 *
 * @author: lixin
 * @version: V1.0
 * @Date: 2019-05-13 10:19:51
 */
package hry.social.sns.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.social.manage.remote.model.sns.SocialSnsPicture;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * <p> SocialSnsPictureDao </p>
 *
 * @author: lixin
 * @Date :          2019-05-13 10:19:51
 */
public interface SocialSnsPictureDao extends BaseDao<SocialSnsPicture,Long> {

    /**
     * 查询帖子图片
     *
     * @param postsId
     * @return
     */
    List<String> getPics(@Param("postsId") Long postsId);

    /**
     * 查询帖子第一张图片
     *
     * @param postsId
     * @return
     */
    String getFirstPics(@Param("postsId") Long postsId);
}
