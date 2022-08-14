/**
 * Copyright:    
 * @author:      javalx
 * @version:     V1.0 
 * @Date:        2019-06-11 17:44:45 
 */
package hry.social.mood.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.social.manage.remote.model.mood.SocialMoodRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * <p> SocialMoodRecordDao </p>
 * @author:         javalx
 * @Date :          2019-06-11 17:44:45  
 */
public interface SocialMoodRecordDao extends  BaseDao<SocialMoodRecord, Long> {

    /**
     * 更新签名
     *
     * @param customerId
     * @param mood
     */
    void updateMood(@Param("customerId") Long customerId, @Param("mood") String mood);

    /**
     * 历史心情
     *
     * @param customerId
     * @return
     */
    List<SocialMoodRecord> findHistoryMood(Long customerId);
}
