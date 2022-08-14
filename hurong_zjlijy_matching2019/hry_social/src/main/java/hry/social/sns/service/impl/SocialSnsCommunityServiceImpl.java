/**
 * Copyright:   
 * @author:      lixin
 * @version:     V1.0 
 * @Date:        2019-05-13 10:18:05 
 */
package hry.social.sns.service.impl;


import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.social.manage.remote.model.sns.SocialSnsCommunity;
import hry.social.sns.service.SocialSnsCommunityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p> SocialSnsCommunityService </p>
 * @author:         lixin
 * @Date :          2019-05-13 10:18:05  
 */
@Service("socialSnsCommunityService")
public class SocialSnsCommunityServiceImpl extends BaseServiceImpl<SocialSnsCommunity, Long> implements SocialSnsCommunityService {
	
	@Resource(name="socialSnsCommunityDao")
	@Override
	public void setDao(BaseDao<SocialSnsCommunity, Long> dao) {
		super.dao = dao;
	}
	

}
