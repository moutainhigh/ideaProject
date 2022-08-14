package hry.social.manage.remote.api.friend;

import hry.bean.JsonResult;
import hry.manage.remote.model.RemoteResult;
import hry.manage.remote.model.base.FrontPage;
import hry.social.manage.remote.model.friend.SocialFriendApply;
import hry.social.manage.remote.model.friend.SocialFriendRelation;

import java.util.List;
import java.util.Map;

public interface RemoteFriendService {

    /**
     * 查询好友列表
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
    List<SocialFriendRelation> searchMyFriend(String name, Long customerId);

    /**
     * 平台内根据昵称、邮箱和手机号搜索用户（用于添加好友）
     *
     * @param customerId
     * @param name
     * @return
     */
    List<SocialFriendRelation> searchAddFriend(Long customerId, String name);

    /**
     * 申请添加好友
     *
     * @param sfa
     */
    void addFriendApply(SocialFriendApply sfa);

    /**
     * 查询是否好友关系 OR 已申请添加好友
     * 0:不是好友也未申请添加好友，1:已是好友关系，2:已申请添加好友
     *
     * @param customerId
     * @param friendId
     * @return
     */
    int isFriendOrHasApply(Long customerId, Long friendId);

    /**
     * 修改好友备注
     *
     * @param remark
     * @param customerId
     * @param palId
     * @return
     */
    RemoteResult remark(String remark, Long customerId, Long palId);

    /**
     * 查询好友详情
     *
     * @param customerId
     * @param friendId
     * @return
     */
    RemoteResult getPalInfo(Long customerId, Long friendId);
    /**
     * 查询好友详情
     *
     * @param customerId
     * @param accid
     * @return
     */
    RemoteResult palInfo(Long customerId, String accid);

    /**
     *
     * @param friendId
     * @param customerId
     */
    void deleteFriend(Long friendId, Long customerId);

//    //采摘PK查看
//    SocialAwardGatherFriendRecord calculationsVs(Long palId, Long customerId);

    /**
     * 查询好友排行榜记录
     *
     * @param params
     * @return
     */
    FrontPage findPalRanking(Map<String,String> params, Long customerId);

    /**
     * 添加好友
     *
     * @param customerId 根ID
     * @param friendId   好友ID
     * @param type       类型:1直接加好友，2请求加好友，3同意加好友，4拒绝加好友")
     * @return
     */
    RemoteResult addFriendRun(Long customerId, Long friendId, int type);

    /**
     * 删除好友
     *
     * @param customerId 根ID
     * @param friendId   好友ID
     * @return
     */
    RemoteResult delFriendRun(Long customerId, Long friendId);

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

    /**
     * 修改备注
     *
     * @param customerId
     * @param friendId
     * @param remark
     * @return
     */
    RemoteResult updateFriendsNote(Long customerId, Long friendId, String remark);

    /**
     * 注册初始化好友关系&算力账户信息
     *
     * @param customerId
     * @return
     */
    RemoteResult registInit(Long customerId);

    void updateFansGruop(String fansGroupId, Long inviteUserId);
}

