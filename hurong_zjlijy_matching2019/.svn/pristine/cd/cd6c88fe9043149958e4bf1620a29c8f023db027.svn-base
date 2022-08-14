/**
 * Copyright:   
 * @author:      javalx
 * @version:     V1.0 
 * @Date:        2019-06-03 17:49:59 
 */
package hry.social.apply.service.impl;

import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;

import hry.social.apply.service.SocialApplyItemService;
import hry.social.manage.remote.model.apply.SocialApplyItem;
import org.springframework.stereotype.Service;

/**
 * <p> SocialApplyItemService </p>
 * @author:         javalx
 * @Date :          2019-06-03 17:49:59  
 */
@Service("socialApplyItemService")
public class SocialApplyItemServiceImpl extends BaseServiceImpl<SocialApplyItem, Long> implements SocialApplyItemService {
	
	@Resource(name="socialApplyItemDao")
	@Override
	public void setDao(BaseDao<SocialApplyItem, Long> dao) {
		super.dao = dao;
	}
	

}
