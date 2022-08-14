/**
 * Copyright:   
 * @author:      lixin
 * @version:     V1.0 
 * @Date:        2019-05-13 10:17:25 
 */
package hry.social.sns.service.impl;

import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.social.manage.remote.model.sns.SocialSnsAttention;
import hry.social.sns.service.SocialSnsAttentionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p> SocialSnsAttentionService </p>
 * @author:         lixin
 * @Date :          2019-05-13 10:17:25  
 */
@Service("socialSnsAttentionService")
public class SocialSnsAttentionServiceImpl extends BaseServiceImpl<SocialSnsAttention, Long> implements SocialSnsAttentionService {
	
	@Resource(name="socialSnsAttentionDao")
	@Override
	public void setDao(BaseDao<SocialSnsAttention, Long> dao) {
		super.dao = dao;
	}
	

}
