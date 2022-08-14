/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Liu Shilei
 * @version:      V1.0 
 * @Date:        2015年9月21日 上午11:27:58
 */
package hry.oauth.user.service;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import hry.bean.JsonResult;
import hry.core.mvc.service.base.BaseService;
import hry.oauth.user.model.AppResource;
import hry.oauth.user.model.AppRole;

/**
 * <p> TODO</p>
 * @author:         Liu Shilei 
 * @Date :          2015年9月21日 上午11:27:58 
 */
public interface AppRoleService  extends BaseService<AppRole, Long>{

	/**
	 * <p> TODO</p>  添加角色
	 * @author:         Liu Shilei
	 * @param:    @param request
	 * @param:    @param appRole
	 * @param:    @return
	 * @return: JsonResult 
	 * @Date :          2015年12月10日 下午1:30:46   
	 * @throws:
	 */
	JsonResult add(HttpServletRequest request, AppRole appRole);

	/**
	 * <p> TODO</p>删除角色---同时删除角色下对应的权限
	 * @author:         Liu Shilei
	 * @param:    @param id
	 * @param:    @return
	 * @return: JsonResult 
	 * @Date :          2015年12月10日 下午1:52:36   
	 * @throws:
	 */
	JsonResult remove(String ids);

	/**
	 * <p> TODO</p> 获得角色下的所有权限
	 * @author:         Liu Shilei
	 * @param:    @param appRole
	 * @return: void 
	 * @Date :          2015年12月10日 下午2:36:02   
	 * @throws:
	 */
	Set<AppResource> getAppResourceSet(AppRole appRole);

	/**
	 * <p> TODO</p> 修改角色---更新角色权限关联表
	 * @author:         Liu Shilei
	 * @param:    @param request
	 * @param:    @param appRole
	 * @param:    @return
	 * @return: JsonResult 
	 * @Date :          2015年12月10日 下午4:36:17   
	 * @throws:
	 */
	JsonResult modify(HttpServletRequest request, AppRole appRole);

	/**
	 * <p> TODO</p>
	 * @author:         Liu Shilei
	 * @param:    @param request
	 * @return: void 
	 * @Date :          2016年2月18日 下午6:59:25   
	 * @throws:
	 */
	JsonResult testAdd(HttpServletRequest request);
	
	/**
	 * 查看角色下的所有权限,权限按应用区分
	 * @param roleid
	 * @return
	 */
	JsonResult loadmenubyroleid(Long roleid);
	
	/**
	 * 查询角色所有权限，和权限库中所有的权限
	 * json 结构
	 * has 
	 * 	   app1
	 *     app2
	 * nohas 
	 * 	   app1
	 * 	   app2
	 * 
	 * 
	 * @param roleid
	 * @return
	 */
	JsonResult findMyResourceAndNohasResource(Long roleid);




}
