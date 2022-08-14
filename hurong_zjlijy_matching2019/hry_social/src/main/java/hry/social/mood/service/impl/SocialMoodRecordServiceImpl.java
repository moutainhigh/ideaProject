/**
 * Copyright:   
 * @author:      javalx
 * @version:     V1.0 
 * @Date:        2019-06-11 17:44:45 
 */
package hry.social.mood.service.impl;

import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;

import hry.social.manage.remote.model.mood.SocialMoodRecord;
import hry.social.mood.dao.SocialMoodRecordDao;
import hry.social.mood.service.SocialMoodRecordService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p> SocialMoodRecordService </p>
 * @author:         javalx
 * @Date :          2019-06-11 17:44:45  
 */
@Service("socialMoodRecordService")
public class SocialMoodRecordServiceImpl extends BaseServiceImpl<SocialMoodRecord, Long> implements SocialMoodRecordService {
	
	@Resource(name="socialMoodRecordDao")
	@Override
	public void setDao(BaseDao<SocialMoodRecord, Long> dao) {
		super.dao = dao;
	}


	/**
	 * 更新签名
	 *
	 * @param customerId
	 * @param mood
	 */
	@Override
	public void updateMood(Long customerId, String mood) {
		((SocialMoodRecordDao)dao).updateMood(customerId, mood);
	}

    /**
     * 历史心情
     *
     * @param customerId
     * @return
     */
    @Override
    public List<SocialMoodRecord> findHistoryMood(Long customerId) {
        return ((SocialMoodRecordDao)dao).findHistoryMood(customerId);
    }
}
