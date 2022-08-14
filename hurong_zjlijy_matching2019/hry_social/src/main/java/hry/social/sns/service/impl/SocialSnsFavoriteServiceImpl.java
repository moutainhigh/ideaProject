/**
 * Copyright:   
 * @author:      lixin
 * @version:     V1.0 
 * @Date:        2019-05-13 10:19:34 
 */
package hry.social.sns.service.impl;


import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.social.manage.remote.model.sns.SocialSnsFavorite;
import hry.social.sns.service.SocialSnsFavoriteService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p> SocialSnsFavoriteService </p>
 * @author:         lixin
 * @Date :          2019-05-13 10:19:34  
 */
@Service("socialSnsFavoriteService")
public class SocialSnsFavoriteServiceImpl extends BaseServiceImpl<SocialSnsFavorite, Long> implements SocialSnsFavoriteService {
	
	@Resource(name="socialSnsFavoriteDao")
	@Override
	public void setDao(BaseDao<SocialSnsFavorite, Long> dao) {
		super.dao = dao;
	}
	

}
