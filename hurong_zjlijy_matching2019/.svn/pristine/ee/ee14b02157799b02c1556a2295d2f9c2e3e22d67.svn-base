package hry.social.manage.remote.model.runnable;

import hry.manage.remote.model.RemoteResult;
import hry.redis.common.utils.RedisService;
import hry.social.manage.remote.api.friend.RemoteFriendService;
import hry.social.manage.remote.api.task.RemoteTaskService;
import hry.social.manage.remote.model.task.SocialTaskReward;
import hry.util.sys.ContextUtil;
import org.apache.log4j.Logger;

import java.util.Map;

/**
 * 注册用户初始化好友关系&算力账户
 * 只能在注册方法中调用，其它地方请另行编写
 */
public class RegistInitRunnable implements Runnable {

    private final Logger log = Logger.getLogger(RegistInitRunnable.class);

    private Long customerId;  // 用户ID

    private RegistInitRunnable() {
    }

    public RegistInitRunnable(Long customerId) {
        this.customerId = customerId;
    }

    @Override
    public void run() {
        try {
            RemoteFriendService remoteFriendService = (RemoteFriendService) ContextUtil.getBean("remoteFriendService");
            //1.注册云信账户------------------------------
            RemoteResult remoteResult = remoteFriendService.registInit(customerId);
            Boolean success = remoteResult.getSuccess();
            if (!success) {
                System.out.println("    ====>    注册用户初始化好友关系&算力账户 失败");
                return;
            }
            System.out.println("    ====>    注册用户初始化好友关系&算力账户 成功");
            // 异步任务奖励--社交
            RedisService redisService = (RedisService) ContextUtil.getBean("redisService");
            Map<String,String> taskMap = redisService.getMap("DIC:task_mark");
            String appReg = taskMap.get("appReg");
            RemoteTaskService remoteTaskService = (RemoteTaskService)ContextUtil.getBean("remoteTaskService");
            SocialTaskReward socialTaskReward = remoteTaskService.dealTaskReward(this.customerId, appReg);
            System.out.println("    ====>    任务奖励处理完成：" + socialTaskReward);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
