/**
 * Copyright:
 *
 * @author: javalx
 * @version: V1.0
 * @Date: 2019-06-04 09:48:29
 */
package hry.social.friend.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.social.manage.remote.model.friend.SocialFriendPicture;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * <p> SocialFriendPictureDao </p>
 *
 * @author: javalx
 * @Date :          2019-06-04 09:48:29
 */
public interface SocialFriendPictureDao extends BaseDao<SocialFriendPicture,Long> {

    List<SocialFriendPicture> findPageList();

    /**
     * 根据帖子ID查询对应的图片
     *
     * @param circleId
     * @return
     */
    List<SocialFriendPicture> findPictures(@Param("circleId") Long circleId);

    /**
     * 查询朋友圈图片集
     *
     * @param customerId 用户ID
     * @return
     */
    List<String> findCirclePictures(@Param("customerId") Long customerId);

    /**
     * 根据帖子ID查询对应的图片
     * @param sfcId
     * @return
     */
    List<String> findUrls(@Param("sfcId") Long sfcId);

}
