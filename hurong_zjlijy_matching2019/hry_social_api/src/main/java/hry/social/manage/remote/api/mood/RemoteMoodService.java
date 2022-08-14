/**
 * Copyright:
 *
 * @author: javalx
 * @version: V1.0
 * @Date: 2019-06-11 17:44:45
 */
package hry.social.manage.remote.api.mood;

import hry.manage.remote.model.RemoteResult;

/**
 * <p> SocialMoodRecordService </p>
 * @author: javalx
 * @Date :          2019-06-11 17:44:45 
 */
public interface RemoteMoodService {

    /**
     * 发表心情
     *
     * @param mood
     * @param customerId
     * @param oldMood
     * @return
     */
    RemoteResult publishMood(String mood, Long customerId,String oldMood);

    /**
     * 历史心情
     *
     * @param customerId
     * @return
     */
    RemoteResult historyMood(Long customerId);
}
