/**
 * Copyright:   
 * @author:      lixin
 * @version:     V1.0 
 * @Date:        2019-05-13 10:20:37 
 */
package hry.social.sns.service.impl;

import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.social.manage.remote.model.sns.SocialSnsPraise;
import hry.social.sns.service.SocialSnsPraiseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p> SocialSnsPraiseService </p>
 * @author:         lixin
 * @Date :          2019-05-13 10:20:37  
 */
@Service("socialSnsPraiseService")
public class SocialSnsPraiseServiceImpl extends BaseServiceImpl<SocialSnsPraise, Long> implements SocialSnsPraiseService {
	
	@Resource(name="socialSnsPraiseDao")
	@Override
	public void setDao(BaseDao<SocialSnsPraise, Long> dao) {
		super.dao = dao;
	}
	

}
