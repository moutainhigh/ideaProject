/**
 * Copyright:
 *
 * @author: javalx
 * @version: V1.0
 * @Date: 2019-06-11 11:08:54
 */
package hry.social.manage.remote.feedback;

import hry.bean.PageResult;
import hry.social.feedback.dao.SocialFeedbackDao;
import hry.social.manage.remote.api.feedback.RemoteFeedbackService;
import hry.social.manage.remote.model.feedback.SocialFeedback;
import hry.util.QueryFilter;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * <p> RemoteFeedbackServiceImpl </p>
 *
 * @author: javalx
 * @Date :          2019-06-11 11:08:54
 */
public class RemoteFeedbackServiceImpl implements RemoteFeedbackService {


    @Resource
    SocialFeedbackDao socialFeedbackDao;

    @Override
    public PageResult findPageList(QueryFilter filter) {
        return null;
    }

    /*
     *  意见反馈
     * */
    @Override
    public void addFeedback(Long customerId, String feedbackContent, String contactNumber) {
        SocialFeedback socialFeedback = new SocialFeedback();
        UUID uuid = UUID.randomUUID();
        socialFeedback.setContent(feedbackContent);
        socialFeedback.setCustomerId(customerId);
        socialFeedback.setOrderNum(uuid.toString());
        socialFeedback.setMobilePhone(contactNumber);
        socialFeedbackDao.insert(socialFeedback);
    }

}
