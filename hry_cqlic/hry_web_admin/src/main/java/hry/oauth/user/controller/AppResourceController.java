/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Liu Shilei
 * @version:      V1.0 
 * @Date:        2015年9月21日 上午11:43:39
 */
package hry.oauth.user.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import hry.core.annotation.base.MethodName;
import hry.core.mvc.controller.base.BaseController;
import hry.core.mvc.service.base.BaseService;
import hry.oauth.user.model.AppResource;
import hry.oauth.user.service.AppRoleService;
import hry.oauth.user.service.AppUserService;
import hry.util.QueryFilter;
import hry.util.properties.PropertiesUtils;

/**
 * <p> TODO</p>     AppResource 
 * 					存在用意，因为hibernate多态查询  子类找不了父类，而父类可以查子类，所以这里直接用父类baseApp来查询
 * @author:         Liu Shilei 
 * @Date :          2015年10月12日16:19:21
 */
@Controller
@RequestMapping("/user/appresource")
public class AppResourceController extends BaseController<AppResource, Long> {
	
	@Resource(name="appResourceService")
	@Override
	public void setService(BaseService<AppResource, Long> service) {
		super.service = service;
		
	}
	
	@Resource
	private AppUserService appUserService;
	
	@Resource
	private AppRoleService appRoleService;
	
	
	@MethodName(name = "查找resource列表,返回json,用于rolesAdd.jsp页面")
	@RequestMapping("/findToJsonOnRolesAdd")
	@ResponseBody
	public List<AppResource>	findToJsonOnRolesAdd(HttpServletRequest request){
		if(PropertiesUtils.APP.getProperty("app.admin").equals("")){
		//	QueryFilter filter = new QueryFilter();
			
			QueryFilter filter = new QueryFilter(AppResource.class);
			
			return super.service.find(filter);
		}else{
			Set<AppResource> set = new HashSet<AppResource>();
			//Set<AppRole> appRoleSet = appUserService.getAppRoleSet(null);
			//Iterator<AppRole> iterator = appRoleSet.iterator();
//			while (iterator.hasNext()) {
//				set.addAll(appRoleService.getAppResourceSet(iterator.next()));
//			}
			return new ArrayList<AppResource>(set);
		}
		
	}
	
	

}
