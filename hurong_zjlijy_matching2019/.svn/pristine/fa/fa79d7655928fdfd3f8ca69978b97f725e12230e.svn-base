/**
 * Copyright:   
 * @author:      lixin
 * @version:     V1.0 
 * @Date:        2019-05-13 10:17:43 
 */
package hry.social.sns.service.impl;

import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.social.manage.remote.model.sns.SocialSnsComment;
import hry.social.sns.service.SocialSnsCommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p> SocialSnsCommentService </p>
 * @author:         lixin
 * @Date :          2019-05-13 10:17:43  
 */
@Service("socialSnsCommentService")
public class SocialSnsCommentServiceImpl extends BaseServiceImpl<SocialSnsComment, Long> implements SocialSnsCommentService {
	
	@Resource(name="socialSnsCommentDao")
	@Override
	public void setDao(BaseDao<SocialSnsComment, Long> dao) {
		super.dao = dao;
	}
	

}
