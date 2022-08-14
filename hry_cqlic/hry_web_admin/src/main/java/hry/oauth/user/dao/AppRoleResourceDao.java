/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Liu Shilei
 * @version:      V1.0 
 * @Date:        2015年12月9日 下午7:19:24
 */
package hry.oauth.user.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.oauth.user.model.AppRoleResource;

import java.util.List;

/**
 * <p> TODO</p>
 * @author:         Liu Shilei 
 * @Date :          2015年12月9日 下午7:19:24 
 */
public interface AppRoleResourceDao extends  BaseDao<AppRoleResource, Long>{

	/**
	 * <p> TODO</p> 根据角色查出 所有的角色权限关联信息 返回list
	 * @author:         Liu Shilei
	 * @param:    @param appRole
	 * @param:    @return
	 * @return: List<AppRoleResource> 
	 * @Date :          2015年12月10日 下午1:57:29   
	 * @throws:
	 */
	List<AppRoleResource> findByAppRole(String roleId);
	
	/**
	 * <p> TODO</p> 根据角色Id查出 所有的角色权限关联信息 返回list
	 * @author:         Liu Shilei
	 * @param:    @param appRole
	 * @param:    @return
	 * @return: List<AppRoleResource> 
	 * @Date :          2015年12月10日 下午1:57:29   
	 * @throws:
	 */
	List<AppRoleResource> findByAppRoleId(String roleId);



}
