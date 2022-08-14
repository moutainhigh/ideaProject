/**
 * Copyright:
 *
 * @author: javalx
 * @version: V1.0
 * @Date: 2019-06-11 17:44:45
 */
package hry.social.mood.service;

import hry.core.mvc.service.base.BaseService;
import hry.social.manage.remote.model.mood.SocialMoodRecord;

import java.util.List;

/**
 * <p> SocialMoodRecordService </p>
 *
 * @author: javalx
 * @Date :          2019-06-11 17:44:45
 */
public interface SocialMoodRecordService extends BaseService<SocialMoodRecord,Long> {

    /**
     * 更新签名
     *
     * @param customerId
     * @param mood
     */
    void updateMood(Long customerId, String mood);

    /**
     * 历史心情
     *
     * @param customerId
     * @return
     */
    List<SocialMoodRecord> findHistoryMood(Long customerId);
}
