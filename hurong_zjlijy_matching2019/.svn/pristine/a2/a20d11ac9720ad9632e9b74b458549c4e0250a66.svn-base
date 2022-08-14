package hry.social.manage.remote.api.yunxin;

import hry.manage.remote.model.RemoteResult;

public interface RemoteYunXinService {

    /**
     * 创建群
     *
     * @param tname           群名称，最大长度64字符
     * @param owner           群主用户帐号，最大长度32字符
     * @param members         ["aaa","bbb"](JSONArray对应的accid，如果解析出错会报414)，一次最多拉200个成员
     * @param announcement    群公告，最大长度1024字符
     * @param intro           群描述，最大长度512字符
     * @param msg             邀请发送的文字，最大长度150字符
     * @param magree          管理后台建群时，0不需要被邀请人同意加入群，1需要被邀请人同意才可以加入群。其它会返回414
     * @param joinmode        群建好后，sdk操作时，0不用验证，1需要验证,2不允许任何人加入。其它返回414
     * @param custom          自定义高级群扩展属性，第三方可以跟据此属性自定义扩展自己的群属性。（建议为json）,最大长度1024字符
     * @param icon            群头像，最大长度1024字符
     * @param beinvitemode    被邀请人同意方式，0-需要同意(默认),1-不需要同意。其它返回414
     * @param invitemode      谁可以邀请他人入群，0-管理员(默认),1-所有人。其它返回414
     * @param uptinfomode     谁可以修改群资料，0-管理员(默认),1-所有人。其它返回414
     * @param upcustommode    谁可以更新群自定义属性，0-管理员(默认),1-所有人。其它返回414
     * @param teamMemberLimit 该群最大人数(包含群主)，范围：2至应用定义的最大群人数(默认:200)。其它返回414
     * @return
     */
    RemoteResult createGroup(String tname, String owner, String members, String announcement, String intro, String msg, String magree, String joinmode, String custom, String icon, String beinvitemode, String invitemode, String uptinfomode, String upcustommode, String teamMemberLimit);


    /**
     * 拉人入群
     *
     * @param tid     网易云通信服务器产生，群唯一标识，创建群时会返回，最大长度128字符
     * @param owner   群主用户帐号，最大长度32字符
     * @param members ["aaa","bbb"](JSONArray对应的accid，如果解析出错会报414)，一次最多拉200个成员
     * @param msg     管理后台建群时，0不需要被邀请人同意加入群，1需要被邀请人同意才可以加入群。其它会返回414
     * @param magree  邀请发送的文字，最大长度150字符
     * @param attach  自定义扩展字段，最大长度512
     * @return
     */
    RemoteResult addToGroup(String tid, String owner, String members, String msg, String magree, String attach);

    /**
     * 踢人出群
     *
     * @param tid     网易云通信服务器产生，群唯一标识，创建群时会返回，最大长度128字符
     * @param owner   群主的accid，用户帐号，最大长度32字符
     * @param member  被移除人的accid，用户账号，最大长度32字符;注：member或members任意提供一个，优先使用member参数
     * @param members ["aaa","bbb"]（JSONArray对应的accid，如果解析出错，会报414）一次最多操作200个accid; 注：member或members任意提供一个，优先使用member参数
     * @param attach  自定义扩展字段，最大长度512
     * @return
     */
    RemoteResult kickOutGroup(String tid, String owner, String member, String members, String attach);

    /**
     * 删除整个群，会解散该群，需要提供群主accid，谨慎操作！
     *
     * @param tid   网易云通信服务器产生，群唯一标识，创建群时会返回，最大长度128字符
     * @param owner 群主用户帐号，最大长度32字符
     * @return
     */
    RemoteResult removeGroup(String tid, String owner);

    /**
     * 高级群基本信息修改
     *
     * @param tid             网易云通信服务器产生，群唯一标识，创建群时会返回
     * @param tname           群名称，最大长度64字符
     * @param owner           群主用户帐号，最大长度32字符
     * @param announcement    群公告，最大长度1024字符
     * @param intro           群描述，最大长度512字符
     * @param joinmode        群建好后，sdk操作时，0不用验证，1需要验证,2不允许任何人加入。其它返回414
     * @param custom          自定义高级群扩展属性，第三方可以跟据此属性自定义扩展自己的群属性。（建议为json）,最大长度1024字符
     * @param icon            群头像，最大长度1024字符
     * @param beinvitemode    被邀请人同意方式，0-需要同意(默认),1-不需要同意。其它返回414
     * @param invitemode      谁可以邀请他人入群，0-管理员(默认),1-所有人。其它返回414
     * @param uptinfomode     谁可以修改群资料，0-管理员(默认),1-所有人。其它返回414
     * @param upcustommode    谁可以更新群自定义属性，0-管理员(默认),1-所有人。其它返回414
     * @param teamMemberLimit 该群最大人数(包含群主)，范围：2至应用定义的最大群人数(默认:200)。其它返回414
     * @return
     */
    RemoteResult updateGroup(String tid, String tname, String owner, String announcement, String intro, String joinmode, String custom, String icon, String beinvitemode, String invitemode, String uptinfomode, String upcustommode, String teamMemberLimit);


    /**
     * 查询指定群的详细信息（群信息+成员详细信息）
     *
     * @param tid 群id，群唯一标识，创建群时会返回
     * @return
     */
    RemoteResult getGroupDetail(String tid);

    /**
     * 修改指定账号在群内的昵称
     *
     * @param tid    群唯一标识，创建群时网易云通信服务器产生并返回
     * @param owner  群主 accid
     * @param accid  要修改群昵称的群成员 accid
     * @param nick   accid 对应的群昵称，最大长度32字符
     * @param custom 自定义扩展字段，最大长度1024字节
     * @return
     */
    RemoteResult updateNickInGroup(String tid, String owner, String accid, String nick, String custom);

    /**
     * 高级群主动退群
     *
     * @param tid   网易云通信服务器产生，群唯一标识，创建群时会返回
     * @param accid 退群的accid
     * @return
     */
    RemoteResult leaveGroup(String tid, String accid);

    /**
     * 高级群修改消息提醒开关
     *
     * @param tid   网易云通信服务器产生，群唯一标识，创建群时会返回
     * @param accid 要操作的群成员accid
     * @param ope   1：关闭消息提醒，2：打开消息提醒，其他值无效
     * @return
     */
    RemoteResult muteTeam(String tid, String accid, int ope);

    /**
     * 获取某个用户所加入高级群的群信息
     *
     * @param accid 要查询用户的accid
     * @return
     */
    RemoteResult getJoinGroup(String accid);

    /**
     * 注册云信账户
     *
     * @param accId      accId
     * @param nickName   昵称
     * @param customerId 用户ID
     * @return
     */
    RemoteResult registerYunXin(String accId, String nickName, Long customerId);

    /**
     * 刷新云信token（密码）
     *
     * @param accId
     * @return
     */
    RemoteResult refreshYunXinToken(String accId);

}