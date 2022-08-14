/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Liu Shilei
 * @version:      V1.0 
 * @Date:        2015年9月21日 上午11:42:06
 */
package hry.oauth.user.service.impl;

import hry.core.cache.StaticCache;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.oauth.user.dao.AppResourceDao;
import hry.oauth.user.dao.AppRoleResourceDao;
import hry.oauth.user.dao.AppUserRoleDao;
import hry.oauth.user.model.AppResource;
import hry.oauth.user.service.AppResourceService;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

/**
 * <p> TODO</p>
 * @author:         Liu Shilei 
 * @Date :          2015年9月21日 上午11:42:06 
 */
@Service("appResourceService")
public class AppResourceServiceImpl extends BaseServiceImpl<AppResource, Long> implements AppResourceService{
	
	@Resource(name="appResourceDao")
	@Override
	public void setDao(BaseDao<AppResource, Long> dao) {
		super.dao = dao;
		
	}
	
	@Resource
	private AppUserRoleDao appUserRoleDao;
	@Resource
	private AppRoleResourceDao appRoleResourceDao;
	@Resource
	private AppResourceDao appResourceDao;
	


	@Override
	public void initCache() {
	//	List<AppResource> find = dao.findAll();
		List<AppResource> find = dao.selectAll();
		for(AppResource AppResource : find){
			StaticCache.BASE_APPRESOURCE_CACHE.put(AppResource.getUrl(), AppResource.getUrl());
		}
	}

	
}
