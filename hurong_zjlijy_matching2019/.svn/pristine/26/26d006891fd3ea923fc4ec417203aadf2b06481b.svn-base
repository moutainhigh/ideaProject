/**
 * Copyright:
 *
 * @author: javalx
 * @version: V1.0
 * @Date: 2019-06-11 17:44:45
 */
package hry.social.manage.remote.mood;

import hry.bean.JsonResult;
import hry.manage.remote.RemoteManageService;
import hry.manage.remote.model.RemoteResult;
import hry.manage.remote.model.User;
import hry.social.manage.remote.api.mood.RemoteMoodService;
import hry.social.manage.remote.model.mood.SocialMoodRecord;
import hry.social.manage.remote.model.user.AppPersonInfo;
import hry.social.mood.dao.SocialMoodRecordDao;
import hry.social.mood.service.SocialMoodRecordService;
import hry.util.SpringUtil;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p> RemoteMoodServiceImpl </p>
 *
 * @author: javalx
 * @Date :          2019-06-11 17:44:45
 */
public class RemoteMoodServiceImpl implements RemoteMoodService {

    @Resource
    SocialMoodRecordService socialMoodRecordService;

    /**
     * 发表心情
     *
     * @param mood
     * @param customerId
     * @return
     */
    @Override
    public RemoteResult publishMood(String mood, Long customerId, String oldMood) {
        RemoteResult remoteResult = new RemoteResult();
        socialMoodRecordService.updateMood(customerId, mood);
        if (!StringUtils.isEmpty(oldMood)) {
            SocialMoodRecord socialMoodRecord = new SocialMoodRecord();
            socialMoodRecord.setCustomerId(customerId);
            socialMoodRecord.setMood(oldMood);
            socialMoodRecordService.save(socialMoodRecord);
        }
        remoteResult.setSuccess(true);
        return remoteResult;
    }

    /**
     * 历史心情
     *
     * @param customerId
     * @return
     */
    @Override
    public RemoteResult historyMood(Long customerId) {
        RemoteResult remoteResult = new RemoteResult();
        List<SocialMoodRecord> list = socialMoodRecordService.findHistoryMood(customerId);
        remoteResult.setSuccess(true).setObj(list);
        return remoteResult;
    }
}
