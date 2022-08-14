/**
 * Copyright:   互融云
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2017-05-27 16:05:55 
 */
package hry.oauth.user.service.impl;

import com.github.pagehelper.Page;
import hry.bean.BaseManageUser;
import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.core.shiro.PasswordHelper;
import hry.oauth.menu.model.AppMenu;
import hry.oauth.menu.service.AppMenuService;
import hry.oauth.user.dao.AppUserDao;
import hry.oauth.user.model.*;
import hry.oauth.user.model.vo.AppUserVO;
import hry.oauth.user.service.*;
import hry.util.PageFactory;
import hry.util.QueryFilter;
import hry.util.properties.PropertiesUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * <p> AppUserService </p>
 * @author:         liushilei
 * @Date :          2017-05-27 16:05:55  
 */
@Service("appUserService")
public class AppUserServiceImpl extends BaseServiceImpl<AppUser, Long> implements AppUserService{
	
	@Resource(name="appUserDao")
	@Override
	public void setDao(BaseDao<AppUser, Long> dao) {
		super.dao = dao;
	}
	
	@Resource
	private AppOrganizationService appOrganizationService;
	@Resource
	private AppUserOrganizationService appUserOrganizationService;
	@Resource
	private AppUserRoleService appUserRoleService;
	@Resource
	private AppRoleMenuService appRoleMenuService;
	@Resource
	private AppMenuService appMenuService;

	@Override
	//查基库
	public BaseManageUser checkUsername(String username) {


		//1、查账号
		QueryFilter queryFilter = new QueryFilter(AppUser.class);
		queryFilter.addFilter("username=", username);
		AppUser appUser = super.get(queryFilter);
		if(appUser!=null){
			BaseManageUser user = new BaseManageUser();
			user.setId(appUser.getId());
			user.setUsername(appUser.getUsername());
			user.setPassword(appUser.getPassword());
			user.setSalt(appUser.getSalt());
			return user;
		}else{//2、查邮箱
			QueryFilter queryFilter2 = new QueryFilter(AppUser.class);
			queryFilter2.addFilter("email=", username);
			AppUser appUser2 = super.get(queryFilter2);
			if(appUser2!=null){

				BaseManageUser user = new BaseManageUser();
				user.setId(appUser2.getId());
				user.setUsername(appUser2.getUsername());
				user.setPassword(appUser2.getPassword());
				user.setSalt(appUser2.getSalt());
				return user;
			}else{//3、查手机号
				QueryFilter queryFilter3 = new QueryFilter(AppUser.class);
				queryFilter3.addFilter("mobile=", username);
				AppUser appUser3 = super.get(queryFilter3);
				if(appUser3!=null){
					BaseManageUser user = new BaseManageUser();
					user.setUsername(appUser3.getUsername());
					user.setPassword(appUser3.getPassword());
					user.setSalt(appUser3.getSalt());
					return user;
				}
			}
		}
		return null;
	
	
	}

	@Override
	public PageResult findPageBySql(HttpServletRequest request) {
		
		String organizationid = request.getParameter("organizationid");
			
		if(!StringUtils.isEmpty(organizationid)){
			AppOrganization appOrganization = appOrganizationService.get(Long.valueOf(organizationid));
			//递归查找当前组织下的所有组织 返回 ids 1,2,3
			String ids = appOrganizationService.findSonsToIds(appOrganization);
			
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("ids", ids.split(","));
			
			//封装必要参数
			QueryFilter filter = new QueryFilter(AppUser.class, request);
			//分页插件
			Page<AppUser> page = PageFactory.getPage(filter);
			
			//查询方法
			List<AppUserVO> list = ((AppUserDao)dao).findPageBySql(map);
			
			return new PageResult(page, filter.getPage(),filter.getPageSize());
		}
		return null;
		
	}

	@Override
	public JsonResult add(AppUser appUser, Long department_id,Long roleid) {
		
		QueryFilter filter = new QueryFilter(AppUser.class);
		filter.addFilter("username=", appUser.getUsername());
		AppUser _appuser = super.get(filter);
		if(_appuser!=null){
			return new JsonResult().setMsg("账号重复!");
		}
		
		//生成用户名
		//appUser.setUsername(UUIDUtil.getUUIDtoUpperCase());
		//加密密码
		String[] encryptPassword = PasswordHelper.encryptPassword(appUser.getPassword());
		appUser.setSalt(encryptPassword[0]);
		appUser.setPassword(encryptPassword[1]);
		//保存用户
		super.save(appUser);
		
		//保存角色
		AppUserRole appUserRole = new AppUserRole();
		appUserRole.setUserid(appUser.getId());
		appUserRole.setRoleid(roleid);
		appUserRoleService.save(appUserRole);

		AppOrganization department = appOrganizationService.get(department_id);
		if(!"root".equals(department.getType())) {
			//保存部门
			AppUserOrganization appUserOrganization = new AppUserOrganization();
			appUserOrganization.setUserid(appUser.getId());
			appUserOrganization.setOrganizationid(department_id);
			appUserOrganization.setType("department");
			appUserOrganizationService.save(appUserOrganization);

			//保存公司
			AppUserOrganization company = new AppUserOrganization();
			company.setUserid(appUser.getId());
			company.setOrganizationid(appOrganizationService.get(department_id).getPid());
			company.setType("company");
			appUserOrganizationService.save(company);
		}else{
			//保存公司
			AppUserOrganization company = new AppUserOrganization();
			company.setUserid(appUser.getId());
			company.setOrganizationid(department_id);
			company.setType("company");
			appUserOrganizationService.save(company);
		}
		
		return new JsonResult().setSuccess(true);
	}
	

	@Override
	public Set<String> findUserShiroUrl(String username) {
		
		QueryFilter f = new QueryFilter(AppUser.class);
		f.addFilter("username=", username);
		AppUser appUser = get(f);
		
		Set<String> set = new HashSet<String>();
		if(appUser!=null){

			String userName = PropertiesUtils.APP.getProperty("app.admin");

			if(userName.equals(appUser.getUsername())){

				List<AppMenu> list = appMenuService.findAll();
				if(list!=null&&!list.isEmpty()){
					for(AppMenu appMenu : list){
						if (!StringUtils.isEmpty(appMenu.getShirourl())) {
							set.add(appMenu.getShirourl());
						}
					}
				}
				//超级管理员
				set.add("/superadmin");

			}else{

				QueryFilter filter = new QueryFilter(AppUserRole.class);
				filter.addFilter("userid=", appUser.getId());
				List<AppUserRole> urList = appUserRoleService.find(filter);
				if (urList != null && urList.size() > 0) {
					for (AppUserRole appUserRole : urList) {
						QueryFilter filter2 = new QueryFilter(AppRoleMenu.class);
						filter2.addFilter("roleid=", appUserRole.getRoleid());
						List<AppRoleMenu> rmList = appRoleMenuService.find(filter2);
						if (rmList != null && rmList.size() > 0) {
							for (AppRoleMenu appRoleMenu : rmList) {
								AppMenu appMenu = appMenuService.get(new QueryFilter(AppMenu.class).addFilter("mkey=", appRoleMenu.getMenukey()));
								if (appMenu != null) {
									String shirourl = appMenu.getShirourl();
									if (!StringUtils.isEmpty(shirourl)) {
										set.add(shirourl);
									}
								}
							}
						}

					}
				}
			}
		}
		return set;
	}

	@Override
	public JsonResult modify(AppUser appUser, Long department_id, Long roleid) {
		
		//保存用户
		super.update(appUser);
		
		//删除角色
		appUserRoleService.delete(new QueryFilter(AppUserRole.class).addFilter("userid=", appUser.getId()));
		//保存角色
		AppUserRole appUserRole = new AppUserRole();
		appUserRole.setUserid(appUser.getId());
		appUserRole.setRoleid(roleid);
		appUserRoleService.save(appUserRole);
		
//		//删除部门和公司
//		appUserOrganizationService.delete(new QueryFilter(AppUserOrganization.class).addFilter("userid=", appUser.getId()));
//		//保存部门
//		AppUserOrganization appUserOrganization = new AppUserOrganization();
//		appUserOrganization.setUserid(appUser.getId());
//		appUserOrganization.setOrganizationid(department_id);
//		appUserOrganization.setType("department");
//		appUserOrganizationService.save(appUserOrganization);
//
//		//保存公司
//		AppUserOrganization company = new AppUserOrganization();
//		company.setUserid(appUser.getId());
//		company.setOrganizationid(appOrganizationService.get(department_id).getPid());
//		company.setType("company");
//		appUserOrganizationService.save(company);
		
		return new JsonResult().setSuccess(true);
	
	}
	


	@Override
	public JsonResult remove(Long ids) {
		AppUser appUser = get(ids);
		//删除用户
		delete(ids);
		//删除角色
		appUserRoleService.delete(new QueryFilter(AppUserRole.class).addFilter("userid=", ids));
		//删除部门和公司
		appUserOrganizationService.delete(new QueryFilter(AppUserOrganization.class).addFilter("userid=", ids));
		
		return new JsonResult().setSuccess(true);
	}

	@Override
	public JsonResult resetpw(String id, String password) {
		AppUser appUser = get(Long.valueOf(id));
		//加密密码
		String[] encryptPassword = PasswordHelper.encryptPassword(password);
		appUser.setSalt(encryptPassword[0]);
		appUser.setPassword(encryptPassword[1]);
		//保存用户
		super.update(appUser);
		return  new JsonResult().setSuccess(true);
	}

	@Override
	public JsonResult modifyOrganization(AppUser appUser, Long department_id) {
		//删除部门和公司
		appUserOrganizationService.delete(new QueryFilter(AppUserOrganization.class).addFilter("userid=", appUser.getId()));

		AppOrganization department = appOrganizationService.get(department_id);
		if(!"root".equals(department.getType())) {
			//保存部门
			AppUserOrganization appUserOrganization = new AppUserOrganization();
			appUserOrganization.setUserid(appUser.getId());
			appUserOrganization.setOrganizationid(department_id);
			appUserOrganization.setType("department");
			appUserOrganizationService.save(appUserOrganization);

			//保存公司
			AppUserOrganization company = new AppUserOrganization();
			company.setUserid(appUser.getId());
			company.setOrganizationid(appOrganizationService.get(department_id).getPid());
			company.setType("company");
			appUserOrganizationService.save(company);
		}else{
			//保存公司
			AppUserOrganization company = new AppUserOrganization();
			company.setUserid(appUser.getId());
			company.setOrganizationid(department_id);
			company.setType("company");
			appUserOrganizationService.save(company);
		}
		return new JsonResult().setSuccess(true);
	}

	@Override
	public void initAdmin() {
		String userName = PropertiesUtils.APP.getProperty("app.admin");
		//超级管理员账户ID为1
		AppUser appUser = get(1L);
		if(appUser!=null) {
			appUser.setUsername(userName);
			update(appUser);
		}
	}


}
