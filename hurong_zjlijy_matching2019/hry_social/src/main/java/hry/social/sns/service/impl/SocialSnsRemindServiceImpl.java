/**
 * Copyright:   
 * @author:      lixin
 * @version:     V1.0 
 * @Date:        2019-05-13 10:20:50 
 */
package hry.social.sns.service.impl;

import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.social.manage.remote.model.sns.SocialSnsRemind;
import hry.social.sns.service.SocialSnsRemindService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p> SocialSnsRemindService </p>
 * @author:         lixin
 * @Date :          2019-05-13 10:20:50  
 */
@Service("socialSnsRemindService")
public class SocialSnsRemindServiceImpl extends BaseServiceImpl<SocialSnsRemind, Long> implements SocialSnsRemindService {
	
	@Resource(name="socialSnsRemindDao")
	@Override
	public void setDao(BaseDao<SocialSnsRemind, Long> dao) {
		super.dao = dao;
	}
	

}
