/**
 * Copyright:   
 * @author:      javalx
 * @version:     V1.0 
 * @Date:        2019-06-11 14:42:13 
 */
package hry.social.force.service.impl;

import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;

import hry.social.force.service.SocialPermanentForceService;
import hry.social.manage.remote.model.force.SocialPermanentForce;
import org.springframework.stereotype.Service;

/**
 * <p> SocialPermanentForceService </p>
 * @author:         javalx
 * @Date :          2019-06-11 14:42:13  
 */
@Service("socialPermanentForceService")
public class SocialPermanentForceServiceImpl extends BaseServiceImpl<SocialPermanentForce, Long> implements SocialPermanentForceService {
	
	@Resource(name="socialPermanentForceDao")
	@Override
	public void setDao(BaseDao<SocialPermanentForce, Long> dao) {
		super.dao = dao;
	}
	

}
