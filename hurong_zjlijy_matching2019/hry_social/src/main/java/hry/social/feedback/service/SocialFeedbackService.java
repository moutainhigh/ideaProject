/**
 * Copyright:   
 * @author:      javalx
 * @version:     V1.0 
 * @Date:        2019-06-11 11:08:54 
 */
package hry.social.feedback.service;

import hry.bean.PageResult;
import hry.core.mvc.service.base.BaseService;
import hry.social.manage.remote.model.feedback.SocialFeedback;
import hry.util.QueryFilter;


/**
 * <p> SocialFeedbackService </p>
 * @author:         javalx
 * @Date :          2019-06-11 11:08:54 
 */
public interface SocialFeedbackService  extends BaseService<SocialFeedback, Long>{


    PageResult findPageList(QueryFilter filter);
}
