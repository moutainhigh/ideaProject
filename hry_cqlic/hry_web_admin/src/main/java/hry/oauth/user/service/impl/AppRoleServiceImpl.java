/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Liu Shilei
 * @version:      V1.0 
 * @Date:        2015年9月21日 上午11:28:30
 */
package hry.oauth.user.service.impl;

import hry.bean.JsonResult;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.oauth.menu.model.AppMenu;
import hry.oauth.menu.service.AppMenuService;
import hry.oauth.user.dao.AppResourceDao;
import hry.oauth.user.dao.AppRoleResourceDao;
import hry.oauth.user.model.AppResource;
import hry.oauth.user.model.AppRole;
import hry.oauth.user.model.AppRoleMenu;
import hry.oauth.user.model.AppUserRole;
import hry.oauth.user.service.AppRoleMenuService;
import hry.oauth.user.service.AppRoleResourceService;
import hry.oauth.user.service.AppRoleService;
import hry.oauth.user.service.AppUserRoleService;
import hry.util.BeanUtil;
import hry.util.QueryFilter;
import hry.util.sys.ContextUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * <p> TODO</p>
 * @author:         Liu Shilei 
 * @Date :          2015年9月21日 上午11:28:30 
 */
@Service("appRoleService")
public class AppRoleServiceImpl extends BaseServiceImpl<AppRole, Long> implements AppRoleService {
	private static Logger logger = Logger.getLogger(AppRoleServiceImpl.class);
	@Resource(name= "appRoleDao")
	@Override
	public void setDao(BaseDao<AppRole, Long> dao) {
		super.dao = dao;
	}
	
	@Resource
	private AppResourceDao appResourceDao;
	@Resource
	private AppRoleResourceService appRoleResourceService;
	@Resource
	private AppRoleResourceDao appRoleResourceDao;
	
	@Resource
	private AppUserRoleService appUserRoleService;
	
	@Resource
	private AppRoleMenuService appRoleMenuService;
	
	@Resource
	private AppMenuService appMenuService;
	

	@Override
	public JsonResult add(HttpServletRequest request, AppRole appRole) {
		String oauthids = request.getParameter("oauthids");
		
		JsonResult jsonResult = new JsonResult();
		//判空
		if(StringUtils.isEmpty(oauthids)){
			jsonResult.setSuccess(false);
			jsonResult.setMsg("权限不能为空");
			return jsonResult;
		}else{
			//保存角色
			this.save(appRole);
			logger.error(appRole.getId());
			String[] ids = org.apache.commons.lang3.StringUtils.split(oauthids, ",");
			for(String id : ids){
				AppRoleMenu appRoleMenuTree = new AppRoleMenu();
				appRoleMenuTree.setRoleid(appRole.getId());
				appRoleMenuTree.setMenukey(id);
				appRoleMenuService.save(appRoleMenuTree);
			}
			
			jsonResult.setSuccess(true);
			return jsonResult;
		}
	}

	@Override
	public JsonResult remove(String id) {
		JsonResult jsonResult = new JsonResult();
		//判空
		if(StringUtils.isEmpty(id)){
			jsonResult.setSuccess(false);
			jsonResult.setMsg("id不能为空");
			return jsonResult;
		}else{
			//保存关联信息
			AppRole appRole = this.get(Long.valueOf(id));
			
			QueryFilter filter = new QueryFilter(AppUserRole.class);
			filter.addFilter("roleid=", id);
			List<AppUserRole> findByAppRole = appUserRoleService.find(filter);
			
			if(findByAppRole!=null&&findByAppRole.size()>0){
				jsonResult.setSuccess(false);
				jsonResult.setMsg("有用户关联此角色，请先解除关联");
				return jsonResult;
			}
			//删除角色对应的权限
			QueryFilter queryFilter = new QueryFilter(AppRoleMenu.class);
			queryFilter.addFilter("roleid=", appRole.getId());
			appRoleMenuService.delete(queryFilter);
			//删除角色
			dao.delete(appRole);
			jsonResult.setSuccess(true);
			return jsonResult;
		}
		
	}

	@Override
	public Set<AppResource> getAppResourceSet(AppRole appRole) {
		return new HashSet<AppResource>(appResourceDao.findByAppRole(appRole.getId(),ContextUtil.getSaasId()));
	}

	@Override
	public JsonResult modify(HttpServletRequest request, AppRole appRole) {
		String oauthids = request.getParameter("oauthids");
		JsonResult jsonResult = new JsonResult();
		//判空
		if(StringUtils.isEmpty(oauthids)){
			jsonResult.setSuccess(false);
			jsonResult.setMsg("权限不能为空");
			return jsonResult;
		}else{
			
			AppRole _appRole = this.get(appRole.getId());
			BeanUtil.copyNotNullProperties(appRole, _appRole);
			//修改角色
			this.update(_appRole);
			//删除所有的关联关系
			QueryFilter queryFilter = new QueryFilter(AppRoleMenu.class);
			queryFilter.addFilter("roleid=", appRole.getId());
			appRoleMenuService.delete(queryFilter);
			//保存新的关联关系
			String[] ids = org.apache.commons.lang3.StringUtils.split(oauthids, ",");
			for(String id : ids){
				AppRoleMenu appRoleMenuTree = new AppRoleMenu();
				appRoleMenuTree.setRoleid(appRole.getId());
				appRoleMenuTree.setMenukey(id);
				appRoleMenuService.save(appRoleMenuTree);
			}
			
			jsonResult.setSuccess(true);
			return jsonResult;
		}	

	}
	
	
	@Override
	public JsonResult testAdd(HttpServletRequest request) {
		return null;
	}



	@Override
	public JsonResult loadmenubyroleid(Long roleid) {

		Map<String,ArrayList<AppMenu> > map = new HashMap<String,ArrayList<AppMenu> >();
		List<AppMenu> list = appMenuService.find(new QueryFilter(AppMenu.class).addFilter("name=", "root"));
		for(AppMenu appMenu : list){
			map.put(appMenu.getMkey(),new ArrayList<AppMenu>());
		}

		List<AppMenu> find = appRoleMenuService.loadmenubyroleid(roleid);

		if(find!=null&&find.size()>0){
			for(AppMenu appMenu : find){
				if (appMenu != null) {
					ArrayList<AppMenu> appMenus = map.get(appMenu.getAppname());
					if(appMenus!=null){
						appMenus.add(appMenu);
					}
				}
			}
		}

		JsonResult jsonResult = new JsonResult();
		
		return jsonResult.setSuccess(true).setObj(map);
	}
	
	@Override
	public JsonResult findMyResourceAndNohasResource(Long roleid) {
		
		JsonResult jr = loadmenubyroleid(roleid);
		Map<String,Map<String,List<AppMenu>>>  map = new HashMap<String,Map<String,List<AppMenu>>>();
		map.put("has", (Map<String,List<AppMenu>>)jr.getObj());
		
		Map<String,List<AppMenu>> nohas = new HashMap<String,List<AppMenu>>();

		List<AppMenu> list = appMenuService.find(new QueryFilter(AppMenu.class).addFilter("name=", "root"));
		for(AppMenu appMenu : list){
			nohas.put(appMenu.getMkey(),new ArrayList<AppMenu>());
		}


		QueryFilter qf = new QueryFilter(AppMenu.class);
		qf.setOrderby("orderno");
		List<AppMenu> find = appMenuService.find(qf);
		
		if(find!=null&&find.size()>0){
			for(AppMenu appMenu : find){
				if (appMenu != null) {
					List<AppMenu> nohasList = nohas.get(appMenu.getAppname());
					if(nohasList!=null){
						nohasList.add(appMenu);
					}
				}
			}
		}

		map.put("nohas", nohas);
		
		return new JsonResult().setSuccess(true).setObj(map);
		
	}
	


}
