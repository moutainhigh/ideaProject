/**
 * Copyright:   
 * @author:      lixin
 * @version:     V1.0 
 * @Date:        2019-05-13 10:22:31 
 */
package hry.social.sns.service.impl;

import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.social.manage.remote.model.sns.SocialSnsVideo;
import hry.social.sns.service.SocialSnsVideoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p> SocialSnsVideoService </p>
 * @author:         lixin
 * @Date :          2019-05-13 10:22:31  
 */
@Service("socialSnsVideoService")
public class SocialSnsVideoServiceImpl extends BaseServiceImpl<SocialSnsVideo, Long> implements SocialSnsVideoService {
	
	@Resource(name="socialSnsVideoDao")
	@Override
	public void setDao(BaseDao<SocialSnsVideo, Long> dao) {
		super.dao = dao;
	}
	

}
