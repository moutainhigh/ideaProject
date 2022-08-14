/**
 * Copyright:   
 * @author:      lixin
 * @version:     V1.0 
 * @Date:        2019-05-13 10:22:18 
 */
package hry.social.sns.service.impl;

import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.social.manage.remote.model.sns.SocialSnsTopic;
import hry.social.sns.service.SocialSnsTopicService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p> SocialSnsTopicService </p>
 * @author:         lixin
 * @Date :          2019-05-13 10:22:18  
 */
@Service("socialSnsTopicService")
public class SocialSnsTopicServiceImpl extends BaseServiceImpl<SocialSnsTopic, Long> implements SocialSnsTopicService {
	
	@Resource(name="socialSnsTopicDao")
	@Override
	public void setDao(BaseDao<SocialSnsTopic, Long> dao) {
		super.dao = dao;
	}
	

}
