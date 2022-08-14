/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Liu Shilei
 * @version:      V1.0 
 * @Date:        2015年12月2日 下午4:33:16
 */
package hry.oauth.user.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import hry.bean.JsonResult;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.core.shiro.PasswordHelper;
import hry.oauth.user.dao.AppOrganizationDao;
import hry.oauth.user.dao.AppRoleDao;
import hry.oauth.user.dao.AppRoleResourceDao;
import hry.oauth.user.dao.AppUserDao;
import hry.oauth.user.dao.AppUserOrganizationDao;
import hry.oauth.user.dao.AppUserRoleDao;
import hry.oauth.user.model.AppOrganization;
import hry.oauth.user.model.AppRole;
import hry.oauth.user.model.AppRoleResource;
import hry.oauth.user.model.AppUser;
import hry.oauth.user.model.AppUserOrganization;
import hry.oauth.user.model.AppUserRole;
import hry.oauth.user.service.AppOrganizationService;
import hry.oauth.user.service.AppRoleResourceService;
import hry.oauth.user.service.AppRoleService;
import hry.oauth.user.service.AppUserOrganizationService;
import hry.oauth.user.service.AppUserRoleService;
import hry.oauth.user.service.AppUserService;
import hry.util.QueryFilter;
import hry.util.sys.ContextUtil;

/**
 * 
 * <p> TODO</p>
 * @author:         Liu Shilei 
 * @Date :          2015年12月2日 下午4:33:16
 */
@Service("appOrganizationService")
public class AppOrganizationServiceImpl extends BaseServiceImpl<AppOrganization, Long>  implements AppOrganizationService {
	
	@Resource(name = "appOrganizationDao")
	@Override
	public void setDao(BaseDao<AppOrganization, Long> dao) {
		super.dao = dao;
	}
	
	
	@Resource
	private AppUserRoleDao appUserRoleDao;
	
	@Resource
	private AppUserRoleService appUserRoleService;
	
	@Resource
	private AppRoleResourceDao appRoleResourceDao;
	
	@Resource
	private AppRoleResourceService appRoleResourceService;
	
	@Resource
	private  AppRoleDao appRoleDao;
	
	@Resource
	private AppRoleService appRoleService;
	
	@Resource
	private AppUserDao appUserDao;
	
	@Resource
	private AppUserService appUserService;
	
	@Resource
	private AppUserOrganizationDao appUserOrganizationDao;
	
	@Resource 
	private AppUserOrganizationService appUserOrganizationService;
	
	
	
	@Override
	public JsonResult remove(Long id) {
		JsonResult jsonResult = new JsonResult();
		//判空
		if(StringUtils.isEmpty(id)){
			jsonResult.setSuccess(false);
			jsonResult.setMsg("id不能为空");
			return jsonResult;
		}else{
			QueryFilter filter = new QueryFilter(AppUserOrganization.class);
			filter.addFilter("organizationid=", id);
			List<AppUserOrganization> findByAppOrganization =  appUserOrganizationService.find(filter);
			
			if(findByAppOrganization!=null&&findByAppOrganization.size()>0){
				jsonResult.setSuccess(false);
				jsonResult.setMsg("该部门下存在用户,请先删除用户");
				return jsonResult;
			}
			super.delete(Long.valueOf(id));
			jsonResult.setSuccess(true);
			return jsonResult;
		}
	}

	@Override
	public JsonResult removeSubCompanty(String[] ids) {
		return null;
	}

	@Override
	public JsonResult addSubCompany(HttpServletRequest request, AppUser appuser,AppOrganization appOrganization) {
		JsonResult jsonResult = new JsonResult();
		
		try {
			String permissions = request.getParameter("permissions");
			if(StringUtils.isEmpty(permissions)){
				jsonResult.setSuccess(false);
				jsonResult.setMsg("添加非法，权限列表为空");
				return jsonResult;
			}
			
			String[] permissionsArr = permissions.split(",");
			
			//----------------------为分公司增加分公司管理员账号的角色--------------------------------------------
			AppRole appRole = new AppRole();
			appRole.setName("【分公司管理角色】_"+appOrganization.getName());
			appRole.setRemark("【分公司管理角色】_"+appOrganization.getName());
			appRole.setType("subcompany");   //标记些角色为分公司角色
			appRoleService.save(appRole);
			
			
			//--------------------------增加角色权限关联表--------------------------------------------------
			for(String str : permissionsArr){
				AppRoleResource appRoleResource = new AppRoleResource();
				appRoleResource.setRoleid(appRole.getId());
				appRoleResource.setResourceid(Long.valueOf(str));
				appRoleResourceService.save(appRoleResource);
			}
			
			//--------------------------分公司增加管理员账号------------------------------------------------------
			//设置appuser账号前缀
			//appuser.setAppuserprefix(ContextUtil.getCurrentUser().getAppuserprefix());
			//appuser密码加密
			PasswordHelper  passwordHelper = new PasswordHelper();
			//passwordHelper.encryptPassword(appuser);
			appUserService.save(appuser);
			
			
			//--------------------------增加用户角色关联表------------------------------------------------------
			AppUserRole appUserRole = new AppUserRole();
			appUserRole.setUserid(appuser.getId());
			appUserRole.setRoleid(appRole.getId());
			appUserRoleService.save(appUserRole);
			
			//-------------------------增加分公司---------------------------------------------------
			super.save(appOrganization);
			
			//-------------------------增加用户公司关联表---------------------------------------------------
			AppUserOrganization appUserOrganization  = new AppUserOrganization();
			//appUserOrganization.setUserId(appuser.getId());
			appUserOrganization.setOrganizationid(appOrganization.getId());
			appUserOrganization.setType("subcompany");
			appUserOrganizationService.save(appUserOrganization);
			
			//清空session
			ContextUtil.getSession().removeAttribute("appUser");
			ContextUtil.getSession().removeAttribute("appOrganization");
			jsonResult.setSuccess(true);
			
		} catch (Exception e) {
			jsonResult.setSuccess(false);
			jsonResult.setMsg("增加异常");
		}
		
		return jsonResult;
		
	}

	@Override
	public List<AppOrganization> findByCompanyId(String companyId, String type) {
		return findRecursive(companyId,type,new ArrayList<AppOrganization>());
	}
		
	public  List<AppOrganization> findRecursive(String companyId,String type,List<AppOrganization> allList){
		
		QueryFilter filter = new QueryFilter(AppOrganization.class);
		
		if(!StringUtils.isEmpty(companyId)){
			filter.addFilter("pid=", companyId);
		}
		if(!StringUtils.isEmpty(type)){
			filter.addFilter("type=", type);
		}
		List<AppOrganization> dataList = dao.selectByExample(filter.getExample());
		if(dataList!=null&&dataList.size()>0){
			for(AppOrganization appOrganization : dataList){
				allList.add(appOrganization);
				findRecursive(appOrganization.getId().toString(), type, allList);
			}
		}
		return allList;
	}

	@Override
	public List<AppOrganization> findSons(AppOrganization appOrganization) {
		return findRecursive(appOrganization.getId().toString(),null,new ArrayList<AppOrganization>());
	}

	@Override
	public String findSonsToIds(AppOrganization appOrganization) {
		List<AppOrganization> findSons = findSons(appOrganization);
		String ids ="";
		if(findSons!=null){
			for(int i = 0 ; i < findSons.size() ; i++ ){
				ids += findSons.get(i).getId().toString();
				if(i!=findSons.size()-1){
					ids += ",";
				}
			}
			ids = ids + "," + appOrganization.getId();
		}
		if(ids.length()==0){
			ids = appOrganization.getId()+"";
		}
		return ids;
	}

	@Override
	public boolean hasSonOrganization(AppOrganization appOrganization) {
		List<AppOrganization> list = ((AppOrganizationDao)dao).findSonOrganization(appOrganization.getId());
		if(list!=null&&list.size()>0){
			return true;
		}
		return false;
	
	}
	
}
