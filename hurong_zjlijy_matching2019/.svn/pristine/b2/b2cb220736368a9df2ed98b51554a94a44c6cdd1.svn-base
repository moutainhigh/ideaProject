/**
 * Copyright:   
 * @author:      javalx
 * @version:     V1.0 
 * @Date:        2019-06-04 10:25:33 
 */
package hry.social.friend.service.impl;

import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;

import hry.social.friend.service.SocialFriendCommentService;
import hry.social.manage.remote.model.friend.SocialFriendComment;
import org.springframework.stereotype.Service;

/**
 * <p> SocialFriendCommentService </p>
 * @author:         javalx
 * @Date :          2019-06-04 10:25:33  
 */
@Service("socialFriendCommentService")
public class SocialFriendCommentServiceImpl extends BaseServiceImpl<SocialFriendComment, Long> implements SocialFriendCommentService {
	
	@Resource(name="socialFriendCommentDao")
	@Override
	public void setDao(BaseDao<SocialFriendComment, Long> dao) {
		super.dao = dao;
	}
	

}
