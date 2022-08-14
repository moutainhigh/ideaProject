/**
 * Copyright:
 *
 * @author: javalx
 * @version: V1.0
 * @Date: 2019-06-03 20:11:45
 */
package hry.social.friend.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.social.manage.remote.model.friend.SocialFriendRelation;
import hry.social.manage.remote.model.friend.SocialSeeFriends;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * <p> SocialFriendRelationDao </p>
 *
 * @author: javalx
 * @Date :          2019-06-03 20:11:45
 */
public interface SocialFriendRelationDao extends BaseDao<SocialFriendRelation,Long> {

    /**
     * 根据用户ID查询云信帐号
     *
     * @param customerId
     * @return
     */
    String getAccidByCustomerId(@Param("customerId") Long customerId);

    /**
     * 根据用户ID查询好友列表
     *
     * @param customerId
     * @return
     */
    List<SocialFriendRelation> findPalList(Long customerId);

    /**
     * 我的好友列表中根据昵称、备注、邮箱和手机号搜索好友
     *
     * @param name
     * @param customerId
     * @return
     */
    List<SocialFriendRelation> searchMyFriend(@Param("name") String name, @Param("customerId") Long customerId);

    /**
     * 平台内根据昵称、邮箱和手机号搜索用户
     *
     * @param name
     * @return
     */
    List<SocialFriendRelation> searchAddFriend(@Param("name") String name);

    /**
     * 查询是否存在好友关系，0否1是
     *
     * @param customerId
     * @param friendId
     * @return
     */
    int hasExistence(@Param("customerId") Long customerId, @Param("friendId") Long friendId);

    /**
     * 删除好友关系
     *
     * @param customerId
     * @param friendId
     * @return
     */
    void deleteRelation(@Param("customerId") Long customerId, @Param("friendId") Long friendId);

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

    /**
     * 分页查询好友列表
     *
     * @param customerId
     * @return
     */
    List<SocialFriendRelation> findFrontPageBySql(@Param("customerId") Long customerId);

    /**
     * 查询好友详情
     *
     * @param customerId
     * @param friendId
     * @return
     */
    SocialFriendRelation getPalInfo(@Param("customerId") Long customerId, @Param("friendId") Long friendId);

    /**
     *
     * @param accid
     * @return
     */
    Long getCustomerIdByAccid(@Param("accid") String accid);

    void updateFansGruop(@Param("fansGroupId") String fansGroupId, @Param("customerId") Long inviteUserId);

    List<String> getFriendIds(@Param("customerId") Long customerId);
}
