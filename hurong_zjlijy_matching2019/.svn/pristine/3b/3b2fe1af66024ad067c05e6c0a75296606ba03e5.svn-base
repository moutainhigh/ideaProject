/**
 * Copyright:
 *
 * @author: javalx
 * @version: V1.0
 * @Date: 2019-06-03 20:11:45
 */
package hry.social.user.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.social.manage.remote.model.friend.SocialFriendRelation;
import org.apache.ibatis.annotations.Param;

/**
 * <p> SocialFriendRelationDao </p>
 *
 * @author: javalx
 * @Date :          2019-06-03 20:11:45
 */
public interface SocialUserDao extends BaseDao<SocialFriendRelation,Long> {

    /**
     * 根据用户ID查询云信帐号
     *
     * @param customerId
     * @return
     */
    String getAccidByCustomerId(@Param("customerId") Long customerId);

    /**
     * 查询是否存在好友关系，0否1是
     *
     * @param customerId
     * @param friendId
     * @return
     */
    int hasExistence(@Param("customerId") Long customerId, @Param("friendId") Long friendId);

    /**
     * 修改好友备注
     *
     * @param customerId
     * @param friendId
     * @param friendsNote
     */
    void updateFriendsNote(@Param("customerId") Long customerId, @Param("friendId") Long friendId, @Param("friendsNote") String friendsNote);

    /**
     * 更新云信账户密码（tokem）
     *
     * @param customerId
     * @param token
     */
    void updateYunxinToken(@Param("accid") String customerId, @Param("token") String token);

    void updateFansGruop(@Param("fansGroupId") String fansGroupId, @Param("customerId") Long inviteUserId);

    /**
     * 解锁用户
     *
     * @param customerId
     */
    void unLockUser(@Param("customerId") Long customerId);

    /**
     * 锁定用户
     *
     * @param customerId
     */
    void lockUser(@Param("customerId") Long customerId);
}
