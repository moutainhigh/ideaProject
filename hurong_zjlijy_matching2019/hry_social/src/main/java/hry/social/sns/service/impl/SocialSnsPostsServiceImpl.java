/**
 * Copyright:   
 * @author:      lixin
 * @version:     V1.0 
 * @Date:        2019-05-10 15:08:37 
 */
package hry.social.sns.service.impl;

import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.social.manage.remote.model.sns.SocialSnsPosts;
import hry.social.sns.service.SocialSnsPostsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p> SocialSnsPostsService </p>
 * @author:         lixin
 * @Date :          2019-05-10 15:08:37  
 */
@Service("socialSnsPostsService")
public class SocialSnsPostsServiceImpl extends BaseServiceImpl<SocialSnsPosts, Long> implements SocialSnsPostsService {
	
	@Resource(name="socialSnsPostsDao")
	@Override
	public void setDao(BaseDao<SocialSnsPosts, Long> dao) {
		super.dao = dao;
	}
	

}
