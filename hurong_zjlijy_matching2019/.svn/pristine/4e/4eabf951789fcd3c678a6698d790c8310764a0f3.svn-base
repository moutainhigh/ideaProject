/**
 * Copyright:
 *
 * @author: javalx
 * @version: V1.0
 * @Date: 2019-06-03 19:58:03
 */
package hry.social.manage.remote.api.task;

import hry.manage.remote.model.AppDic;
import hry.manage.remote.model.RemoteResult;
import hry.social.manage.remote.model.task.SocialTaskItem;
import hry.social.manage.remote.model.task.SocialTaskReward;

import java.util.List;

/**
 * <p> SocialTaskItemService </p>
 *
 * @author: javalx
 * @Date :          2019-06-03 19:58:03
 */
public interface RemoteTaskService {

    List<AppDic> categoryList();

    List<SocialTaskItem> itemList();

    List<SocialTaskItem> repetitionList(Long customerId, String categoryKey);

    SocialTaskItem invite(String identifying);

    /**
     * 任务奖励处理
     *
     * @param customerId
     * @param taskMark
     * @return
     */
    SocialTaskReward dealTaskReward(Long customerId, String taskMark);

    /**
     * 查询用户邀请信息
     *
     * @param customerId
     * @param inviteCode
     * @return
     */
    RemoteResult getInviteInfo(Long customerId, String inviteCode);

    /**
     * /统计刷新算力
     *
     * @param customerId
     */
    void calculateForce(Long customerId);
}
