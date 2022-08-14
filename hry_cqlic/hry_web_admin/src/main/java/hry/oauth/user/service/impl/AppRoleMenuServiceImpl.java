/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Liu Shilei
 * @version:      V1.0 
 * @Date:        2015年12月9日 下午7:27:20
 */
package hry.oauth.user.service.impl;

import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.oauth.menu.model.AppMenu;
import hry.oauth.user.dao.AppRoleMenuDao;
import hry.oauth.user.model.AppRoleMenu;
import hry.oauth.user.service.AppRoleMenuService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p> TODO</p>
 * @author:         Liu Shilei 
 * @Date :          2015年12月9日 下午7:27:20 
 */
@Service("appRoleMenuService")
public class AppRoleMenuServiceImpl extends BaseServiceImpl<AppRoleMenu, Long>  implements AppRoleMenuService  {
	
	@Resource(name = "appRoleMenuDao")
	@Override
	public void setDao(BaseDao<AppRoleMenu, Long> dao) {
		super.dao = dao;
	}

	/**
	 * 根据用户id获取其对应的菜单角色
	 *
	 * @param userId 用户id
	 * @return
	 */
	@Override
	public List<String> findUserMenuRoleByuserId (String userId) {
		Map<String, Object> param = new HashMap<>();
		if (!StringUtils.isEmpty(userId)) {
			param.put("userId", userId);
		}
		return ((AppRoleMenuDao)dao).findUserMenuRoleByuserId(param);
	}

	@Override
	public List<AppMenu> findMenuRoleByuserId (Object userId) {
		Map<String, Object> param = new HashMap<>();
		if (!StringUtils.isEmpty(userId)) {
			param.put("userId", userId);
		}
		return ((AppRoleMenuDao)dao).findMenuRoleByuserId(param);
	}

	@Override
	public List<AppMenu> loadmenubyroleid (Long roleid) {
		Map<String, Object> param = new HashMap<>();
		param.put("roleid", roleid);
		return ((AppRoleMenuDao)dao).loadmenubyroleid(param);
	}
}

	