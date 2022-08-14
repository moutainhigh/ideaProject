/**
 * Copyright:
 *
 * @author: javalx
 * @version: V1.0
 * @Date: 2019-06-04 09:42:35
 */
package hry.social.friend.service;

import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.mvc.service.base.BaseService;
import hry.social.manage.remote.model.friend.SocialFriendCircle;
import hry.util.QueryFilter;


/**
 * <p> SocialFriendCircleService </p>
 *
 * @author: javalx
 * @Date :          2019-06-04 09:42:35
 */
public interface SocialFriendCircleService extends BaseService<SocialFriendCircle,Long> {

    /**
     * 分页查询
     *
     * @param filter
     * @return
     */
    PageResult findPageList(QueryFilter filter);

    /**
     * 查看详情
     *
     * @param id 帖子ID
     * @return
     */
    SocialFriendCircle see(Long id);

    /**
     * 批量更新
     *
     * @param ids 帖子ID
     * @return
     */
    JsonResult updateBatch(String ids);

}
