/**
 * Copyright:   互融云
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2017-05-27 16:05:55 
 */
package hry.oauth.user.service;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;


import hry.bean.BaseManageUser;
import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.mvc.service.base.BaseService;
import hry.oauth.user.model.AppUser;



/**
 * <p> AppUserService </p>
 * @author:         liushilei
 * @Date :          2017-05-27 16:05:55  
 */
public interface AppUserService  extends BaseService<AppUser, Long>{
	
	/**
	 * 从基库验证username是否存在
	 * @param username
	 * @return
	 */
	public BaseManageUser checkUsername(String username);
	
	/**
	 * sql分页
	 * @param request
	 * @return
	 */
	public PageResult findPageBySql(HttpServletRequest request);
	
	/**
	 * 添加后台用户,同时后台用户保存到基础库中
	 * @param appUser
	 * @param department_id
	 * @param roleid
	 * @return
	 */
	public JsonResult add(AppUser appUser, Long department_id,Long roleid);
	
	
	/**
	 * 查询所有权限
	 * @param userid
	 * @return
	 */
	public Set<String> findUserShiroUrl(String username);
	
	/**
	 * 修改后台用户
	 * @param appUser
	 * @param department_id
	 * @param roleid
	 * @return
	 */
	public JsonResult modify(AppUser appUser, Long department_id, Long roleid);
	
	/**
	 * 删除后台用户，同时删除基库中的appuser
	 * @param ids
	 * @return
	 */
	public JsonResult remove(Long ids);


	/**
	 * 重置密码
	 * @param id
	 * @param password
	 * @return
	 */
    JsonResult resetpw(String id, String password);

	/**
	 * 部门调动
	 * @param appUser
	 * @param aLong
	 * @return
	 */
	JsonResult modifyOrganization(AppUser appUser, Long aLong);

	/**
	 * 初始化超级管理员账号
	 */
    void initAdmin();
}
