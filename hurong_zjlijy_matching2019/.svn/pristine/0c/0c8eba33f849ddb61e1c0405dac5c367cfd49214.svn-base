/**
 * Copyright:
 *
 * @author: javalx
 * @version: V1.0
 * @Date: 2019-06-03 20:17:08
 */
package hry.social.friend.service;

import hry.core.mvc.service.base.BaseService;
import hry.social.manage.remote.model.friend.SocialFriendApply;

import java.util.List;


/**
 * <p> SocialFriendApplyService </p>
 *
 * @author: javalx
 * @Date :          2019-06-03 20:17:08
 */
public interface SocialFriendApplyService extends BaseService<SocialFriendApply,Long> {

    /**
     * 查看好友申请记录列表
     *
     * @param customerId
     * @return
     */
    List<SocialFriendApply> findApplyList(Long customerId);

    /**
     * 查看未处理的好友申请记录数
     *
     * @param customerId
     * @return
     */
    int hasNotDeal(Long customerId);

    /**
     * 拒绝好友申请
     *
     * @param id
     */
    void refuseApply(Long id);

    /**
     * 接受好友申请
     *
     * @param id
     */
    void acceptApply(Long id);

}
