package hry.social.manage.remote.model.runnable;

import hry.manage.remote.model.RemoteResult;
import hry.social.manage.remote.api.friend.RemoteFriendService;
import hry.util.sys.ContextUtil;
import org.apache.log4j.Logger;

/**
 * 好友关系维护
 * 类型:1直接加好友，2请求加好友，3同意加好友，4拒绝加好友，5删除好友
 */
public class YunXinFriendRunnable implements Runnable {

    private final Logger log = Logger.getLogger(YunXinFriendRunnable.class);

    /**
     * 用户ID
     */
    private Long customerId;

    /**
     * 好友ID
     */
    private Long friendId;

    /**
     * 类型:1直接加好友，2请求加好友，3同意加好友，4拒绝加好友，5删除好友")
     */
    private int type;

    private YunXinFriendRunnable() {
    }

    public YunXinFriendRunnable(Long customerId, Long friendId, int type) {
        this.customerId = customerId;
        this.friendId = friendId;
        this.type = type;
    }

    @Override
    public void run() {
        RemoteResult remoteResult = null;
        RemoteFriendService remoteFriendService = (RemoteFriendService) ContextUtil.getBean("remoteFriendService");
        if (type == 5) {
            remoteResult = remoteFriendService.delFriendRun(customerId, friendId);
        } else {
            remoteResult = remoteFriendService.addFriendRun(customerId, friendId, type);
        }
        System.err.println(toString() + "结果 : " + remoteResult.getSuccess());
    }

    @Override
    public String toString() {
        return "YunXinFriendRunnable{" + "customerId=" + customerId + ", friendId=" + friendId + ", type=" + type + '}';
    }
}
