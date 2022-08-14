/**
 * Copyright:
 *
 * @author: javalx
 * @version: V1.0
 * @Date: 2019-06-03 20:11:45
 */
package hry.social.user.service;

import hry.bean.JsonResult;
import hry.core.mvc.service.base.BaseService;
import hry.manage.remote.model.base.FrontPage;
import hry.social.manage.remote.model.friend.SocialFriendRelation;

import java.util.List;
import java.util.Map;


/**
 * <p> SocialFriendRelationService </p>
 *
 * @author: javalx
 * @Date :          2019-06-03 20:11:45
 */
public interface SocialUserService extends BaseService<SocialFriendRelation,Long> {

    /**
     * 手动添加好友
     *
     * @param customerId 根ID
     * @param friendId   好友ID
     * @param type       类型:1直接加好友，2请求加好友，3同意加好友，4拒绝加好友)
     * @return
     */
    JsonResult addFriend(Long customerId, Long friendId, int type);

    /**
     * 添加本地好友
     *
     * @param customerId 加好友-发起者ID
     * @param friendId   加好友-接收者ID
     * @return
     */
    JsonResult addLocalFriend(Long customerId, Long friendId, String friendsNote);

    /**
     * 查询是否存在好友关系，0否1是
     *
     * @param customerId
     * @param friendId
     * @return
     */
    int hasExistence(Long customerId, Long friendId);

    /**
     * 修改备注
     *
     * @param customerId
     * @param friendId
     * @param remark
     * @return
     */
    void updateFriendsNote(Long customerId, Long friendId, String remark);

    /**
     * 更新云信账户密码（tokem）
     *
     * @param accid
     * @param token
     */
    void updateYunxinToken(String accid, String token);

    /**
     * 初始化好友关系
     *
     * @param customerId
     */
    void initFrient(Long customerId);

    /**
     * 更新最新的粉丝群ID
     *
     * @param fansGroupId
     * @param inviteUserId
     */
    void updateFansGruop(String fansGroupId, Long inviteUserId);

    /**
     * 用户注册 init
     *
     * @param userMsg
     */
    void registDeal(String userMsg);
}
