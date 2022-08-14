package hry.social.manage.remote.model.runnable;

import hry.social.manage.remote.api.task.RemoteTaskService;
import hry.social.manage.remote.model.task.SocialTaskReward;
import hry.util.sys.ContextUtil;

/**
 * 注册用户初始化好友关系&算力账户
 * 只能在注册方法中调用，其它地方请另行编写
 */
public class TaskRewardRunnable implements Runnable {

    private Long customerId;  // 用户ID

    private String taskMark;  // 任务标识

    private TaskRewardRunnable() {
    }

    public TaskRewardRunnable(Long customerId, String taskMark) {
        this.customerId = customerId;
        this.taskMark = taskMark;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1500);    //延时1.5秒
            /** 任务奖励处理 */
            RemoteTaskService remoteTaskService = (RemoteTaskService) ContextUtil.getBean("remoteTaskService");
            //1.注册云信账户------------------------------
            SocialTaskReward socialTaskReward = remoteTaskService.dealTaskReward(customerId, taskMark);
            System.out.println("    ====>    任务奖励处理完成：" + socialTaskReward);
        } catch (Exception e) {
        }
    }
}
