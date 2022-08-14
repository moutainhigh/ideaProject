/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Liu Shilei
 * @version:      V1.0 
 * @Date:        2015年9月21日 上午11:30:01
 */
package hry.oauth.user.controller;

import com.alibaba.fastjson.JSONObject;
import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.annotation.CommonLog;
import hry.core.annotation.MyRequiresPermissions;
import hry.core.annotation.base.MethodName;
import hry.core.mvc.controller.base.BaseController;
import hry.core.mvc.service.base.BaseService;
import hry.oauth.menu.model.AppMenu;
import hry.oauth.menu.service.AppMenuService;
import hry.oauth.user.model.AppResource;
import hry.oauth.user.model.AppRole;
import hry.oauth.user.service.AppRoleService;
import hry.util.QueryFilter;
import hry.util.SetPropertyEditorSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


/**
 * <p> TODO</p>
 * @author:         Liu Shilei 
 * @Date :          2015年9月21日 上午11:30:01 
 */
@Controller
@RequestMapping("/user/approle")
public class AppRoleController extends BaseController<AppRole, Long> {
	
	@Resource(name = "appRoleService")
	@Override
	public void setService(BaseService<AppRole, Long> service) {
		super.service = service;
	}
	
	@Autowired
	private AppMenuService appMenuService;

	/**
	 * User自己的initBinder
	 * 
	 * @param binder
	 */
	@InitBinder
	public void initBinderDemoUser(ServletRequestDataBinder binder) {
		// 由于用户里面的角色信息是个Set类型，所以这地方要转一下才行，前台传递过来的是个一个字符串，类似"1,2,3"这种，后台要自动转成Set
		binder.registerCustomEditor(Set.class, "appResourceSet", new SetPropertyEditorSupport(AppResource.class));
		
	}
	
	
	@MethodName(name = "查看")
	@RequestMapping(value="/see/{id}",method=RequestMethod.GET)
	@ResponseBody
	public ModelAndView see(@PathVariable Long id){
		AppRole appRole = service.get(id);
		ModelAndView mav = new ModelAndView("oauth/user/approlesee");

		//查询根节点
		List<AppMenu> list = appMenuService.find(new QueryFilter(AppMenu.class).addFilter("name=", "root"));
		if(list!=null&&!list.isEmpty()){
			List<JSONObject> listJson = new ArrayList<JSONObject>();
			for(AppMenu appMenu : list){
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("name",appMenu.getPkey());
				jsonObject.put("key",appMenu.getMkey());
				listJson.add(jsonObject);
			}
			mav.addObject("adminMenuList",listJson);
		}
		mav.addObject("model", appRole);
		return mav;
	}


	
	@MethodName(name="查看权限树")
	@RequestMapping(value="/loadmenubyroleid")
	@ResponseBody
	public JsonResult loadmenubyroleid(Long roleid){
		return ((AppRoleService) service).loadmenubyroleid(roleid);
	}


	@MethodName(name = "增加页面")
	@RequestMapping(value="/addpage",method=RequestMethod.GET)
	@ResponseBody
	public ModelAndView addpage(){
		ModelAndView mav = new ModelAndView("oauth/user/approleadd");

		//查询根节点
		List<AppMenu> list = appMenuService.find(new QueryFilter(AppMenu.class).addFilter("name=", "root"));
		if(list!=null&&!list.isEmpty()){
			List<JSONObject> listJson = new ArrayList<JSONObject>();
			for(AppMenu appMenu : list){
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("name",appMenu.getPkey());
				jsonObject.put("key",appMenu.getMkey());
				listJson.add(jsonObject);
			}
			mav.addObject("adminMenuList",listJson);
		}

		return mav;
	}
	
	@MethodName(name="增加")
	@RequestMapping(value="/add", method = RequestMethod.POST)
	@ResponseBody
	@CommonLog(name = "角色管理-添加角色")
	public JsonResult add(HttpServletRequest request,AppRole appRole){
		return ((AppRoleService) service).add(request,appRole);
	}
	
	
	@MethodName(name = "编辑查看")
	@RequestMapping(value="/modifysee/{id}")
	@ResponseBody
	public ModelAndView modifysee(@PathVariable Long id){
		AppRole appRole = service.get(id);
		ModelAndView mav = new ModelAndView("oauth/user/approlemodify");
		//查询根节点
		List<AppMenu> list = appMenuService.find(new QueryFilter(AppMenu.class).addFilter("name=", "root"));
		if(list!=null&&!list.isEmpty()){
			List<JSONObject> listJson = new ArrayList<JSONObject>();
			for(AppMenu appMenu : list){
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("name",appMenu.getPkey());
				jsonObject.put("key",appMenu.getMkey());
				listJson.add(jsonObject);
			}
			mav.addObject("adminMenuList",listJson);
		}
		mav.addObject("model", appRole);
		return mav;
	}
	
	@MethodName(name="修改")
	@RequestMapping(value="/modify", method = RequestMethod.POST)
	@ResponseBody
	@CommonLog(name = "角色管理-编辑角色")
	public JsonResult modify(HttpServletRequest request,AppRole appRole){
		return ((AppRoleService) service).modify(request,appRole);
	}
	
	@MethodName(name="删除")
	@RequestMapping(value="/remove", method = RequestMethod.POST)
	@MyRequiresPermissions
	@ResponseBody
	@CommonLog(name = "角色管理-删除角色")
	public JsonResult remove(String ids){
		return ((AppRoleService) service).remove(ids);
	}
	
	@MethodName(name = "列表")
	@RequestMapping("/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(AppRole.class,request);
		return super.findPage(filter);
	}
	
	@MethodName(name = "查询全部，返回list")
	@RequestMapping("/findall")
	@ResponseBody
	public List<AppRole> findall(HttpServletRequest request){
		return super.findAll();
	}
	
	
	@MethodName(name = "查找roles列表,返回json,用于userAdd.jsp页面")
	@RequestMapping("/findToJsonOnUserAdd")
	@ResponseBody
	public List	findToJsonOnUserAdd(HttpServletRequest request){
		return super.service.findAll();
	}
	
	
	@MethodName(name = "查找角色拥有的权限和未拥有的权限")
	@RequestMapping("/findMyResourceAndNohasResource")
	@ResponseBody
	public JsonResult findMyResourceAndNohasResource(Long roleid){
		
		return ((AppRoleService) service).findMyResourceAndNohasResource(roleid);

	}
	
	
	//------------------------------------

	
	
	
}
