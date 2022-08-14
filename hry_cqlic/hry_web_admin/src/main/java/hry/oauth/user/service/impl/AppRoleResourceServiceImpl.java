/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Liu Shilei
 * @version:      V1.0 
 * @Date:        2015年12月9日 下午7:27:20
 */
package hry.oauth.user.service.impl;

import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.oauth.user.model.AppRoleResource;
import hry.oauth.user.service.AppRoleResourceService;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

/**
 * <p> TODO</p>
 * @author:         Liu Shilei 
 * @Date :          2015年12月9日 下午7:27:20 
 */
@Service("appRoleResourceService")
public class AppRoleResourceServiceImpl extends BaseServiceImpl<AppRoleResource, Long>  implements AppRoleResourceService  {
	
	@Resource(name = "appRoleResourceDao")
	@Override
	public void setDao(BaseDao<AppRoleResource, Long> dao) {
		super.dao = dao;
	}

}

	