/**
 * Copyright:   
 * @author:      javalx
 * @version:     V1.0 
 * @Date:        2019-06-03 19:58:03 
 */
package hry.social.task.service.impl;

import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;

import hry.social.manage.remote.model.task.SocialTaskItem;
import hry.social.task.service.SocialTaskItemService;
import org.springframework.stereotype.Service;

/**
 * <p> SocialTaskItemService </p>
 * @author:         javalx
 * @Date :          2019-06-03 19:58:03  
 */
@Service("socialTaskItemService")
public class SocialTaskItemServiceImpl extends BaseServiceImpl<SocialTaskItem, Long> implements SocialTaskItemService {
	
	@Resource(name="socialTaskItemDao")
	@Override
	public void setDao(BaseDao<SocialTaskItem, Long> dao) {
		super.dao = dao;
	}
	

}
