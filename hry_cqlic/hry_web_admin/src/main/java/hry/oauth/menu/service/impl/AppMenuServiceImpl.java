/**
 * Copyright:   互融云
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2017-06-01 19:44:41 
 */
package hry.oauth.menu.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.oauth.menu.model.AppMenu;
import hry.oauth.menu.service.AppMenuService;
import hry.util.QueryFilter;

/**
 * <p> AppMenuService </p>
 * @author:         liushilei
 * @Date :          2017-06-01 19:44:41  
 */
@Service("appMenuService")
public class AppMenuServiceImpl extends BaseServiceImpl<AppMenu, Long> implements AppMenuService{
	
	@Resource(name="appMenuDao")
	@Override
	public void setDao(BaseDao<AppMenu, Long> dao) {
		super.dao = dao;
	}
	
	@Override
	public List<AppMenu> listTree(HttpServletRequest request) {
		
		QueryFilter filter = new QueryFilter(AppMenu.class);
		filter.addFilter("pkey_notlike", "%\\_%");
		List<AppMenu> find = find(filter);
		for(AppMenu appMenu : find){
			recursivefind(appMenu);
		}
		
		return find;
		
	}
	
	/**
	 * 递归查询
	 * <p> TODO</p>
	 * @author:         Liu Shilei
	 * @param:    @param appMenu
	 * @return: void 
	 * @Date :          2016年5月26日 下午2:51:55   
	 * @throws:
	 */
	public void recursivefind(AppMenu appMenu){
//		QueryFilter filter = new QueryFilter(AppMenu.class);
//		//父key等于传进来的mkey
//		filter.addFilter("pkey=", appMenu.getMkey());
//		List<AppMenu> list = this.find(filter);
//		if(list!=null&&list.size()>0){
//			appMenu.setSubMenu(list);
//			for(AppMenu item : list){
//				recursivefind(item);
//			}
//		}
	}
	

}
