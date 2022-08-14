/**
 * Copyright:
 *
 * @author: javalx
 * @version: V1.0
 * @Date: 2019-06-04 09:48:29
 */
package hry.social.friend.service;

import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.mvc.service.base.BaseService;
import hry.social.manage.remote.model.friend.SocialFriendPicture;
import hry.util.QueryFilter;

import java.util.List;


/**
 * <p> SocialFriendPictureService </p>
 *
 * @author: javalx
 * @Date :          2019-06-04 09:48:29
 */
public interface SocialFriendPictureService extends BaseService<SocialFriendPicture,Long> {

    /**
     * @param ids
     * @return
     */
    JsonResult updateBatch(String ids);

    /**
     * @param filter
     * @return
     */
    PageResult findPageList(QueryFilter filter);

    /**
     * 查询朋友圈图片集
     *
     * @param customerId 用户ID
     * @return
     */
    List<String> findCirclePictures(Long customerId);
}
