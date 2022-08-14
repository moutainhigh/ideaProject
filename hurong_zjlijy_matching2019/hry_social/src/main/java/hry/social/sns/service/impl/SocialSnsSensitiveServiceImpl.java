/**
 * Copyright:   
 * @author:      lixin
 * @version:     V1.0 
 * @Date:        2019-05-13 10:21:24 
 */
package hry.social.sns.service.impl;

import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.social.manage.remote.model.sns.SocialSnsSensitive;
import hry.social.sns.service.SocialSnsSensitiveService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p> SocialSnsSensitiveService </p>
 * @author:         lixin
 * @Date :          2019-05-13 10:21:24  
 */
@Service("socialSnsSensitiveService")
public class SocialSnsSensitiveServiceImpl extends BaseServiceImpl<SocialSnsSensitive, Long> implements SocialSnsSensitiveService {
	
	@Resource(name="socialSnsSensitiveDao")
	@Override
	public void setDao(BaseDao<SocialSnsSensitive, Long> dao) {
		super.dao = dao;
	}
	

}
