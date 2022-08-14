/**
 * Copyright:    互融云
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2017-05-27 16:05:55 
 */
package hry.oauth.user.controller;


import hry.bean.BaseManageUser;
import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.annotation.CommonLog;
import hry.core.mvc.controller.base.BaseController;
import hry.core.mvc.service.base.BaseService;
import hry.core.shiro.PasswordHelper;
import hry.oauth.user.model.AppUser;
import hry.oauth.user.model.AppUserOrganization;
import hry.oauth.user.model.AppUserRole;
import hry.oauth.user.service.AppOrganizationService;
import hry.oauth.user.service.AppUserOrganizationService;
import hry.oauth.user.service.AppUserRoleService;
import hry.oauth.user.service.AppUserService;
import hry.util.QueryFilter;
import hry.util.StringUtil;
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
 * @Date:        2017-05-27 16:05:55 
 */
@Controller
@RequestMapping("/user/appuser")
public class AppUserController extends BaseController<AppUser, Long> {
	
	@Resource(name = "appUserService")
	@Override
	public void setService(BaseService<AppUser, Long> service) {
		super.service = service;
	}
	
	@Resource
	private AppUserOrganizationService appUserOrganizationService;
	@Resource
	private AppUserRoleService appUserRoleService;
	@Resource
	private AppOrganizationService appOrganizationService;
	
	@RequestMapping(value="/see/{id}")
	public ModelAndView see(@PathVariable Long id){
		AppUser appUser = service.get(id);
		ModelAndView mav = new ModelAndView("oauth/user/appusersee");
		mav.addObject("model", appUser);
		
		QueryFilter filter = new QueryFilter(AppUserOrganization.class);
		filter.addFilter("userid=", id);
		filter.addFilter("type=", "department");
		AppUserOrganization department = appUserOrganizationService.get(filter);
		mav.addObject("department_id", department.getOrganizationid());
		
		QueryFilter filter2 = new QueryFilter(AppUserRole.class);
		filter2.addFilter("userid=", id);
		AppUserRole role = appUserRoleService.get(filter2);
		mav.addObject("roleid", role.getRoleid());
		
		return mav;
	}
	
	
	@RequestMapping(value="/add")
	@ResponseBody
	public JsonResult add(HttpServletRequest request,AppUser appUser){
		String department_id = request.getParameter("department_id");
		String roleid = request.getParameter("roleid");
		if(StringUtils.isEmpty(department_id)){
			return new JsonResult().setMsg("部门不能为空");
		}
		if(StringUtils.isEmpty(roleid)){
			return new JsonResult().setMsg("角色不能为空");
		}
		return ((AppUserService)service).add(appUser,Long.valueOf(department_id),Long.valueOf(roleid));
	}
	
	@RequestMapping(value="/modifysee/{id}")
	public ModelAndView modifysee(@PathVariable Long id){
		AppUser appUser = service.get(id);
		ModelAndView mav = new ModelAndView("oauth/user/appusermodify");
		mav.addObject("model", appUser);
		
		QueryFilter filter = new QueryFilter(AppUserOrganization.class);
		filter.addFilter("userid=", id);
		filter.addFilter("type=", "department");
		AppUserOrganization department = appUserOrganizationService.get(filter);
		if (department != null) {
			mav.addObject("department_id", department.getOrganizationid());
			mav.addObject("department_name", appOrganizationService.get(department.getOrganizationid()).getName());
		}else{
			QueryFilter f = new QueryFilter(AppUserOrganization.class);
			f.addFilter("userid=", id);
			AppUserOrganization company = appUserOrganizationService.get(f);
			mav.addObject("department_id", company.getOrganizationid());
			mav.addObject("department_name", appOrganizationService.get(company.getOrganizationid()).getName());
		}

		QueryFilter filter2 = new QueryFilter(AppUserRole.class);
		filter2.addFilter("userid=", id);
		AppUserRole role = appUserRoleService.get(filter2);
		mav.addObject("roleid", role.getRoleid());
		
		return mav;
	}
	
	@RequestMapping(value="/modify")
	@ResponseBody
	public JsonResult modify(HttpServletRequest request,AppUser appUser){

		String department_id = request.getParameter("department_id");
		String roleid = request.getParameter("roleid");
		if(StringUtils.isEmpty(department_id)){
			return new JsonResult().setMsg("部门不能为空");
		}
		if(StringUtils.isEmpty(roleid)){
			return new JsonResult().setMsg("角色不能为空");
		}
		return ((AppUserService)service).modify(appUser,Long.valueOf(department_id),Long.valueOf(roleid));
	
	}

    @RequestMapping(value="/modifyOrganizationSubmit")
    @ResponseBody
    public JsonResult modifyOrganizationSubmit(HttpServletRequest request,AppUser appUser){

        String department_id = request.getParameter("department_id");
        if(StringUtils.isEmpty(department_id)){
            return new JsonResult().setMsg("部门不能为空");
        }
        return ((AppUserService)service).modifyOrganization(appUser,Long.valueOf(department_id));

    }

	
	
	@RequestMapping(value="/remove")
	@ResponseBody
	public JsonResult remove(Long ids){
		if(StringUtils.isEmpty(ids)){
			return new JsonResult().setMsg("id不能为空");
		}
		return ((AppUserService)service).remove(ids);
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public PageResult list(HttpServletRequest request){
		return ((AppUserService)service).findPageBySql(request);
	}


	@RequestMapping(value="/resetpwview/{id}")
	public ModelAndView resetpwview(@PathVariable Long id){
		AppUser appUser = service.get(id);
		ModelAndView mav = new ModelAndView("oauth/user/appuserresetpw");
		mav.addObject("model", appUser);
		return mav;
	}


	@RequestMapping(value="/modifyOrganization/{id}")
	public ModelAndView modifyOrganization(@PathVariable Long id){
		AppUser appUser = service.get(id);
		ModelAndView mav = new ModelAndView("oauth/user/appusermodifyorganization");
		mav.addObject("model", appUser);
		return mav;
	}


	@RequestMapping(value="/resetpw")
	@ResponseBody
	@CommonLog(name = "账号管理-重置密码")
	public JsonResult resetpw(HttpServletRequest request){
		String password = request.getParameter("password");
		String id = request.getParameter("id");
		return ((AppUserService)service).resetpw(id,password);

	}

	@RequestMapping(value="/resetpw2")
	@ResponseBody
	public JsonResult resetpw2(HttpServletRequest request){
		JsonResult jsonResult = new JsonResult();
		String oldpassword = request.getParameter("oldpassword");
		String password = request.getParameter("password");
		String repassword = request.getParameter("repassword");
		// 判断 Referer 是否以  开头
		String referer=request.getHeader("Referer");
		String appUrl=PropertiesUtils.APP.getProperty("app.url");
		if((referer==null) ||!(referer.trim().startsWith(appUrl))){
			jsonResult.setMsg("地址异常");
			return  jsonResult;
		}
		String id = ContextUtil.getCurrentUser().getId().toString();
		AppUser appUser = service.get(Long.valueOf(id));
		PasswordHelper passwordHelper = new PasswordHelper();
		String newPassword=	passwordHelper.encryString(oldpassword,appUser.getSalt());
		if (!newPassword.equals(appUser.getPassword())) {
			jsonResult.setMsg("原密码输入错误");
			return  jsonResult;
		}
		if(StringUtils.isEmpty(password)){
			jsonResult.setMsg("密码不能为空");
			return  jsonResult;
		}
		if(StringUtils.isEmpty(repassword)){
			jsonResult.setMsg("密码不能为空");
			return jsonResult;
		}
		if(!password.equals(repassword)){
			jsonResult.setMsg("再次密码不一致");
			return jsonResult;
		}


		return ((AppUserService)service).resetpw(id,password);
	}

	@RequestMapping(value="/findUserName")
	@ResponseBody
	@CommonLog(name = "查询后台用户")
	public List<AppUser> findUserName(HttpServletRequest request){
		String username = request.getParameter("username");
		QueryFilter query=new QueryFilter(AppUser.class);
		if(StringUtil.isNull(username)){
			query.addFilter("username_like", "%"+username+"%");
		}
		List<AppUser> appUserList=((AppUserService)service).find(query);
		return appUserList;
	}
}
