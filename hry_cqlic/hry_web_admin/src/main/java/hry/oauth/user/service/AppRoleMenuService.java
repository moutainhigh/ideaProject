/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Liu Shilei
 * @version:      V1.0 
 * @Date:        2015年12月9日 下午7:25:37
 */
package hry.oauth.user.service;

import hry.core.mvc.service.base.BaseService;
import hry.oauth.menu.model.AppMenu;
import hry.oauth.user.model.AppRoleMenu;

import java.util.List;

/**
 * 
 * <p> TODO</p>
 * @author:         Liu Shilei 
 * @Date :          2016年6月16日 上午10:35:55
 */
public interface AppRoleMenuService extends BaseService<AppRoleMenu, Long>{

    List<String> findUserMenuRoleByuserId (String s);

    List<AppMenu> findMenuRoleByuserId (Object id);

    List<AppMenu> loadmenubyroleid (Long roleid);
}
