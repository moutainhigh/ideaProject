/**
 * Copyright:   
 * @author:      lixin
 * @version:     V1.0 
 * @Date:        2019-05-13 10:21:08 
 */
package hry.social.sns.service.impl;


import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.social.manage.remote.model.sns.SocialSnsReward;
import hry.social.sns.service.SocialSnsRewardService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p> SocialSnsRewardService </p>
 * @author:         lixin
 * @Date :          2019-05-13 10:21:08  
 */
@Service("socialSnsRewardService")
public class SocialSnsRewardServiceImpl extends BaseServiceImpl<SocialSnsReward, Long> implements SocialSnsRewardService {
	
	@Resource(name="socialSnsRewardDao")
	@Override
	public void setDao(BaseDao<SocialSnsReward, Long> dao) {
		super.dao = dao;
	}
	

}
