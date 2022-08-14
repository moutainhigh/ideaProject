/**
 * Copyright:
 *
 * @author: javalx
 * @version: V1.0
 * @Date: 2019-06-10 11:13:59
 */
package hry.social.miningreward.service;

import hry.bean.PageResult;
import hry.core.mvc.service.base.BaseService;
import hry.model.social.miningreward.SocialMiningGatherRecord;
import hry.util.QueryFilter;


/**
 * <p> SocialMiningGatherRecordService </p>
 *
 * @author: javalx
 * @Date :          2019-06-10 11:13:59
 */
public interface SocialMiningGatherRecordService extends BaseService<SocialMiningGatherRecord,Long> {

    /**
     * 果园奖励收取消费处理
     *
     * @param message
     */
    void miningReward(String message);

    /**
     * 外部奖励消费处理
     *
     * @param message
     */
    void outReward(String message);

    /**
     * 同步奖励缓存并推送奖励信息
     *
     * @param message
     */
    void syncCacheReward(String message);

    /**
     * 清除过期缓存奖励
     */
    void cleanupCacheReward();
}
