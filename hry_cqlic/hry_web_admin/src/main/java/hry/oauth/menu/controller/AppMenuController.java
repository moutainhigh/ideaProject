/**
* Copyright:    互融云
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2017-06-01 19:44:41 
 */
package hry.oauth.menu.controller;


import hry.bean.BaseManageUser;
import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.mvc.controller.base.BaseController;
import hry.core.mvc.service.base.BaseService;
import hry.oauth.menu.model.AppMenu;
import hry.oauth.menu.model.LeftMenu;
import hry.oauth.menu.service.AppMenuService;
import hry.oauth.user.service.AppRoleMenuService;
import hry.oauth.user.service.AppUserRoleService;
import hry.util.QueryFilter;
import hry.util.UUIDUtil;
import hry.util.properties.PropertiesUtils;
import hry.util.sys.ContextUtil;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Copyright:   互融云
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2017-06-01 19:44:41 
 */
@Controller
@RequestMapping("/menu/appmenu")
public class AppMenuController extends BaseController<AppMenu, Long> {
	
	@Resource(name = "appMenuService")
	@Override
	public void setService(BaseService<AppMenu, Long> service) {
		super.service = service;
	}

	@Resource
	private AppMenuService appMenuService;

	@Resource
	private AppUserRoleService appUserRoleService;

	@Resource
	private AppRoleMenuService appRoleMenuService;
	
	
	@RequestMapping(value="/see/{id}")
	public ModelAndView see(@PathVariable Long id){
		AppMenu appMenu = service.get(id);
		ModelAndView mav = new ModelAndView("menu/AppMenuSee");
		mav.addObject("model", appMenu);
		return mav;
	}
	
	
	@RequestMapping(value="/add")
	@ResponseBody
	public JsonResult add(HttpServletRequest request,AppMenu appMenu){
		appMenu.setMkey(UUIDUtil.getUUID());
		return super.save(appMenu);
	}
	
	@RequestMapping(value="/modifyview/{id}")
	public ModelAndView modifyview(@PathVariable Long id){
		AppMenu appMenu = service.get(id);
		ModelAndView mav = new ModelAndView("oauth/menu/appmenumodify");
		mav.addObject("model", appMenu);
		return mav;
	}
	
	@RequestMapping(value="/modify")
	@ResponseBody
	public JsonResult modify(HttpServletRequest request,AppMenu appMenu){
		if(StringUtils.isEmpty(appMenu.getType())){
			return new JsonResult().setSuccess(false).setMsg("请选择类型");
		}
		 AppMenu _appmenu = service.get(appMenu.getId());
		 _appmenu.setName(appMenu.getName());
		 _appmenu.setUrl(appMenu.getUrl());
		 _appmenu.setShirourl(appMenu.getShirourl());
		 _appmenu.setOrderno(appMenu.getOrderno());
		 _appmenu.setType(appMenu.getType());
		 _appmenu.setIcon(appMenu.getIcon());
		 service.updateNull(_appmenu);
		 return new JsonResult().setSuccess(true).setObj(appMenu.getId());
	}
	
	
	
	@RequestMapping(value="/remove")
	@ResponseBody
	public JsonResult remove(Long id){
		return super.delete(id);
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(AppMenu.class,request);
		return super.findPage(filter);
	}
	
	@RequestMapping(value="/loadtree")
	@ResponseBody
	public List<AppMenu> loadtree(String appname){
		QueryFilter filter = new QueryFilter(AppMenu.class);
		filter.addFilter("appName=", appname);
		filter.setOrderby("orderno");
		return  find(filter);
	}
	
	
	/**
	 * saas用户查询自己拥有的菜单资源
	 * @param appname
	 * @return
	 */
	@RequestMapping(value="/loadtreeowner")
	@ResponseBody
	public List<AppMenu> loadtreeowner(String appname){
		QueryFilter filter = new QueryFilter(AppMenu.class);
		filter.addFilter("appName=", appname);
		filter.setOrderby("orderno");
		return  find(filter);
	}
	
	
	/**
	 * 加载应用的菜单
	 * @param appname
	 * @return
	 */
	@RequestMapping(value="/loadleft")
	public ModelAndView loadleft(String appname){
		
		ModelAndView mav = new ModelAndView("base/lefturl");
		mav.addObject("appname", appname);

		BaseManageUser baseManageUser = ContextUtil.getCurrentUser();

		String userName = PropertiesUtils.APP.getProperty("app.admin");

		List<AppMenu> appAll = new ArrayList<AppMenu>();
		if(userName.equals(baseManageUser.getUsername())) {
			//查询超管下的全部菜单
			QueryFilter filter = new QueryFilter(AppMenu.class);
			filter.addFilter("appName=", appname);
			filter.addFilter("pkey!=", "root");
			filter.addFilter("type!=", "button");
			filter.setOrderby(" orderno ");
			appAll = find(filter);
		}else{
            appAll = appRoleMenuService.findMenuRoleByuserId(baseManageUser.getId());
		}

		List<LeftMenu> onelist = new ArrayList<LeftMenu>();
		if(appAll!=null&&appAll.size()>0){
			//一级菜单遍历
			for(AppMenu menu : appAll){
				if(appname.equals(menu.getPkey())){
					LeftMenu leftMenu = new LeftMenu();
					leftMenu.setName(menu.getName());
					leftMenu.setUrl(menu.getUrl());
					leftMenu.setType(menu.getType());
					leftMenu.setMkey(menu.getMkey());
					leftMenu.setPkey(menu.getPkey());
					leftMenu.setIcon(menu.getIcon());
					//二级菜单遍历抓取
					List<LeftMenu> secondlist = new ArrayList<LeftMenu>();
					for(AppMenu menu2 : appAll){
						if(leftMenu.getMkey().equals(menu2.getPkey())){
							LeftMenu leftMenu2 = new LeftMenu();
							leftMenu2.setName(menu2.getName());
							leftMenu2.setUrl(menu2.getUrl());
							leftMenu2.setType(menu2.getType());
							leftMenu2.setMkey(menu2.getMkey());
							leftMenu2.setPkey(menu2.getPkey());
							leftMenu2.setIcon(menu2.getIcon());
							
							//三级菜单遍历抓取
							List<LeftMenu> thirdlist = new ArrayList<LeftMenu>();
							for(AppMenu menu3 : appAll){
								if(leftMenu2.getMkey().equals(menu3.getPkey())){
									LeftMenu leftMenu3 = new LeftMenu();
									leftMenu3.setName(menu3.getName());
									leftMenu3.setUrl(menu3.getUrl());
									leftMenu3.setType(menu3.getType());
									leftMenu3.setMkey(menu3.getMkey());
									leftMenu3.setPkey(menu3.getPkey());
									leftMenu3.setIcon(menu3.getIcon());
									
									
									thirdlist.add(leftMenu3);
								}
							}
							//二级关联三级
							leftMenu2.setSonmenus(thirdlist);
							secondlist.add(leftMenu2);
						}
					}
					//一级关联二级
					leftMenu.setSonmenus(secondlist);
					onelist.add(leftMenu);
				}
			}
		}
		
		mav.addObject("menulist", onelist);
		return mav;
		
		
	}
	
	
	
}
