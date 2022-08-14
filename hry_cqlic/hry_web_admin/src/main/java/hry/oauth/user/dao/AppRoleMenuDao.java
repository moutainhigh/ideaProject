/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Liu Shilei
 * @version:      V1.0 
 * @Date:        2015年12月9日 下午7:19:24
 */
package hry.oauth.user.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.oauth.menu.model.AppMenu;
import hry.oauth.user.model.AppRoleMenu;

import java.util.List;
import java.util.Map;

/**
 * 角色  应用菜单关联表
 * <p> TODO</p>
 * @author:         Liu Shilei 
 * @Date :          2016年6月16日 上午10:35:03
 */
public interface AppRoleMenuDao extends  BaseDao<AppRoleMenu, Long>{

    List<String> findUserMenuRoleByuserId (Map<String, Object> param);

    List<AppMenu> findMenuRoleByuserId (Map<String, Object> param);

    List<AppMenu> loadmenubyroleid (Map<String, Object> param);
}
