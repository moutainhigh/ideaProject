/**
 * Copyright:   互融云
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2017-06-01 19:44:41 
 */
package hry.oauth.menu.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import hry.core.mvc.service.base.BaseService;
import hry.oauth.menu.model.AppMenu;



/**
 * <p> AppMenuService </p>
 * @author:         liushilei
 * @Date :          2017-06-01 19:44:41  
 */
public interface AppMenuService  extends BaseService<AppMenu, Long>{

	List<AppMenu> listTree(HttpServletRequest request);


}
