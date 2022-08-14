/**
 * Copyright:   
 * @author:      javalx
 * @version:     V1.0 
 * @Date:        2019-06-11 14:42:53 
 */
package hry.social.force.service.impl;

import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;

import hry.social.force.dao.SocialTerminableForceDao;
import hry.social.force.service.SocialTerminableForceService;
import hry.social.manage.remote.model.force.SocialTerminableForce;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p> SocialTerminableForceService </p>
 * @author:         javalx
 * @Date :          2019-06-11 14:42:53  
 */
@Service("socialTerminableForceService")
public class SocialTerminableForceServiceImpl extends BaseServiceImpl<SocialTerminableForce, Long> implements SocialTerminableForceService {
	
	@Resource(name="socialTerminableForceDao")
	@Override
	public void setDao(BaseDao<SocialTerminableForce, Long> dao) {
		super.dao = dao;
	}


	/**
	 * 有效的时效性算力记录
	 *
	 * @param customerId
	 * @return
	 */
	@Override
	public List<SocialTerminableForce> findTerminablen(Long customerId) {
		return ((SocialTerminableForceDao)dao).findTerminablen(customerId);
	}

	/**
	 * 失效的时效性算力记录
	 *
	 * @param customerId
	 * @return
	 */
	@Override
	public List<SocialTerminableForce> findDisTerminable(Long customerId) {
		return ((SocialTerminableForceDao)dao).findDisTerminable(customerId);
	}
}
