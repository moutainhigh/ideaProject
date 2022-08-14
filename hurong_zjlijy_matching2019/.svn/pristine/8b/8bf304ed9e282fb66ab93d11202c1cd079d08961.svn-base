package hry.social.manage.remote.model.runnable;

import hry.manage.remote.model.RemoteResult;
import hry.social.manage.remote.api.friend.RemoteFriendService;
import hry.util.sys.ContextUtil;
import org.apache.log4j.Logger;

public class FriendRemarkRunnable implements Runnable {


    private final Logger log = Logger.getLogger(FriendRemarkRunnable.class);

    /**
     * 用户ID
     */
    private Long customerId;

    /**
     * 好友ID
     */
    private Long friendId;

    /**
     * 备注
     */
    private String remark;

    private FriendRemarkRunnable() {
    }

    public FriendRemarkRunnable(Long customerId, Long friendId, String remark) {
        this.customerId = customerId;
        this.friendId = friendId;
        this.remark = remark;
    }

    @Override
    public void run() {
        RemoteFriendService remoteFriendService = (RemoteFriendService) ContextUtil.getBean("remoteFriendService");
        RemoteResult remoteResult = remoteFriendService.updateFriendsNote(customerId, friendId, remark);
        System.err.println(toString() + "结果 : " + remoteResult.getSuccess());
    }

    @Override
    public String toString() {
        return "FriendRemarkRunnable{" + "customerId=" + customerId + ", friendId=" + friendId + ", remark='" + remark + '\'' + '}';
    }
}
