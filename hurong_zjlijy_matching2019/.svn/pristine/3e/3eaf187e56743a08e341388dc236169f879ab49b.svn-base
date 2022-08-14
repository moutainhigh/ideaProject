/**
 * Copyright:   
 * @author:      javalx
 * @version:     V1.0 
 * @Date:        2019-06-03 19:54:58 
 */
package hry.social.barrage.service.impl;

import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;

import hry.social.barrage.service.SocialBarrageService;
import hry.social.manage.remote.model.barrage.SocialBarrage;
import org.springframework.stereotype.Service;

/**
 * <p> SocialBarrageService </p>
 * @author:         javalx
 * @Date :          2019-06-03 19:54:58  
 */
@Service("socialBarrageService")
public class SocialBarrageServiceImpl extends BaseServiceImpl<SocialBarrage, Long> implements SocialBarrageService {
	
	@Resource(name="socialBarrageDao")
	@Override
	public void setDao(BaseDao<SocialBarrage, Long> dao) {
		super.dao = dao;
	}
	

}
