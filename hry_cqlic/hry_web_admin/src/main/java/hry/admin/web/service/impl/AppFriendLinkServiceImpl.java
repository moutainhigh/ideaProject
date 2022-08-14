/**
 * Copyright:   
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-20 11:56:58 
 */
package hry.admin.web.service.impl;

import hry.admin.web.model.AppFriendLink;
import hry.admin.web.service.AppFriendLinkService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p> AppFriendLinkService </p>
 * @author:         liushilei
 * @Date :          2018-06-20 11:56:58  
 */
@Service("appFriendLinkService")
public class AppFriendLinkServiceImpl extends BaseServiceImpl<AppFriendLink, Long> implements AppFriendLinkService{
	
	@Resource(name="appFriendLinkDao")
	@Override
	public void setDao(BaseDao<AppFriendLink, Long> dao) {
		super.dao = dao;
	}
	

}
