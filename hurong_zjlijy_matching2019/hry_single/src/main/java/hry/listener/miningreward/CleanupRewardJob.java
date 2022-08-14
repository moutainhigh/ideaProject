package hry.listener.miningreward;

import hry.social.miningreward.service.SocialMiningGatherRecordService;
import hry.util.SpringUtil;

/**
 * @author javal
 * @title: CleanupRewardJob
 * @projectName hurong_matching2019
 * @description: TODO
 * @date 2019/9/1610:04
 */
public class CleanupRewardJob {

    /**
     * 清除过期缓存奖励
     */
    public void cleanupCacheReward() {
        SocialMiningGatherRecordService socialMiningGatherRecordService = SpringUtil.getBean("socialMiningGatherRecordService");
        socialMiningGatherRecordService.cleanupCacheReward();
    }
}
