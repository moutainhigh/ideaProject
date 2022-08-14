/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Liu Shilei
 * @version:      V1.0 
 * @Date:        2015年9月18日 上午10:32:03
 */
package hry.oauth.user.controller;

import hry.bean.JsonResult;
import hry.core.annotation.base.MethodName;
import hry.core.mvc.controller.base.BaseController;
import hry.core.mvc.service.base.BaseService;
import hry.oauth.user.model.AppOrganization;
import hry.oauth.user.service.AppOrganizationService;
import hry.oauth.user.service.AppResourceService;
import hry.oauth.user.service.AppRoleService;
import hry.oauth.user.service.AppUserService;
import hry.util.BeanUtil;
import hry.util.QueryFilter;
import hry.util.properties.PropertiesUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * <p> TODO</p>
 * @author:         Liu   Shilei   
 * @Date :          2015年9月18日 上午10:32:03 
 */
@Controller
@RequestMapping("/user/apporganization")
public class AppOrganizationController  extends BaseController<AppOrganization, Long>{
	
	@Resource(name = "appOrganizationService")
	@Override
	public void setService(BaseService<AppOrganization, Long> service) {
		super.service = service;
	}
	
	@Resource
	private AppRoleService appRoleService;
	@Resource
	private AppResourceService appResourceService;
	@Resource
	private AppUserService appUserService;
	
	@MethodName(name = "加载部门树")
	@RequestMapping("/loadtree")
	@ResponseBody
	public List<AppOrganization>  loadTree(){
		QueryFilter filter = new QueryFilter(AppOrganization.class);
		filter.setOrderby("orderNo asc");
		return service.find(filter);
	}
	
	@MethodName(name = "加载自己的公司")
	@RequestMapping("/findMyCompany")
	@ResponseBody
	public List<AppOrganization>  findMyCompany(){
		if(PropertiesUtils.APP.getProperty("app.admin").equals("")){//如果是admin账户查所有的公司包括根集团
			QueryFilter filter = new QueryFilter(AppOrganization.class);
			filter.addFilter("type_in ", "root ,company");
			
			
			return service.find(filter);
		}else{//如果不是admin只有查自己所在的公司
			Set<AppOrganization> companySet = null;//appUserService.getCompanySet(null);
			return new ArrayList<AppOrganization>(companySet);
		}
	}
	
	@MethodName(name = "查询部门")
	@RequestMapping("/listdepartment")
	@ResponseBody
	public List<AppOrganization>  listdepartment(){
		QueryFilter filter = new QueryFilter(AppOrganization.class);
		filter.addFilter("type=", "department");
		return service.find(filter);
	}

	@MethodName(name = "查询全部组织")
	@RequestMapping("/listorganization")
	@ResponseBody
	public List<AppOrganization>  listorganization(){
		QueryFilter filter = new QueryFilter(AppOrganization.class);
		return service.find(filter);
	}
	
	
	
	@MethodName(name="增加部门")
	@RequestMapping("/add")
	//@MyRequiresPermissions({"/add","/addshop"})
	@ResponseBody
	public JsonResult add(HttpServletRequest request,AppOrganization appOrganization){
		appOrganization.setType("department");
		return save(appOrganization);
	}
	
	
	@MethodName(name="查看部门")
	@RequestMapping(value="/see/{id}")
	@ResponseBody
	public AppOrganization see(@PathVariable Long id){
		return service.get(id);
	}
	
	
	@RequestMapping(value="/modifysee/{id}")
	public ModelAndView modifysee(@PathVariable Long id){
		AppOrganization organization = service.get(id);
		ModelAndView mav = new ModelAndView("oauth/user/apporganizationmodify");
		mav.addObject("model", organization);
		return mav;
	}
	
	@MethodName(name="修改部门")
	@RequestMapping("/modify")
	@ResponseBody
	public JsonResult modify(HttpServletRequest request,AppOrganization appOrganization){
		AppOrganization _appOrganization = service.get(appOrganization.getId());
		BeanUtil.copyNotNullProperties(appOrganization, _appOrganization);
		return super.update(_appOrganization);
	}
	
	
	
	@MethodName(name="删除部门")
	@RequestMapping(value="/remove")
	@ResponseBody
	public JsonResult remove(Long id){
		return ((AppOrganizationService) service).remove(id);
	}
	
	
	@MethodName(name="递归查找公司下的所有部门或门店")
	@RequestMapping("/findByCompanyId")
	@ResponseBody
	public List<AppOrganization> findByCompanyId(HttpServletRequest request){
		String companyId = request.getParameter("companySet");
		String type = request.getParameter("type");
		return null;//((AppOrganizationService) service).findByCompanyId(companyId,type);
	}
	
	@MethodName(name="加载root")
	@RequestMapping("/loadRoot")
	@ResponseBody
	public List<AppOrganization> loadRoot(){
		QueryFilter  filter = new QueryFilter(AppOrganization.class);
		filter.addFilter("type=", "root");
		return super.find(filter);
	}
	

}
