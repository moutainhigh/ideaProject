package hry.oauth.authorization.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import hry.oauth.authorization.model.Authorization;
import hry.oauth.authorization.service.AuthorizationService;
import hry.oauth.user.model.AppUser;
import hry.oauth.user.service.AppUserService;

/**
 * client 端访问调用权限查询service
 * <p> TODO</p>
 * @author:         Liu Shilei 
 * @Date :          2015年11月4日 上午10:11:35
 */
@Service
public class AuthorizationServiceImpl implements AuthorizationService {
	
	@Resource
	private AppUserService appUserService;

	@Override
	public Set<String> findRoles(String saasId,String appKey, AppUser user) {
		
		return new HashSet<String>();
		
	}

	@Override
	public Set<String> findPermissions(String username) {
		return appUserService.findUserShiroUrl(username);
	}




}
