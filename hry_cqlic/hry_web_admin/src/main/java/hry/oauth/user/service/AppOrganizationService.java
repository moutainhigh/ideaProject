/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Liu Shilei
 * @version:      V1.0 
 * @Date:        2015年12月2日 下午4:31:19
 */
package hry.oauth.user.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import hry.bean.JsonResult;
import hry.core.mvc.service.base.BaseService;
import hry.oauth.user.model.AppOrganization;
import hry.oauth.user.model.AppUser;

/**
 * 
 * <p> TODO</p>
 * @author:         Liu Shilei 
 * @Date :          2015年12月2日 下午4:31:19
 */
public interface AppOrganizationService  extends BaseService<AppOrganization, Long>{

	/**
	 * <p> TODO</p> 删除部门
	 * @author:         Liu Shilei
	 * @param:    @param ids
	 * @param:    @return
	 * @return: JsonResult 
	 * @Date :          2015年12月15日 下午5:49:17   
	 * @throws:
	 */
	JsonResult remove(Long id);

	/**
	 * <p> TODO</p>  删除分公司
	 * @author:         Liu Shilei
	 * @param:    @param ids
	 * @param:    @return
	 * @return: JsonResult 
	 * @Date :          2015年12月16日 下午3:01:09   
	 * @throws:
	 */
	JsonResult removeSubCompanty(String[] ids);

	/**
	 * <p> TODO</p> 增加分公司
	 * @author:         Liu Shilei
	 * @param:    @param request
	 * @param:    @param appuser
	 * @param:    @param appOrganization
	 * @return: JsonResult 
	 * @Date :          2015年12月22日 上午11:56:01   
	 * @throws:
	 */
	JsonResult addSubCompany(HttpServletRequest request, AppUser appuser,
			AppOrganization appOrganization);

	/**
	 * <p> TODO</p>  递归查找公司下的所有部门或门店
	 * @author:         Liu Shilei
	 * @param:    @param companyId
	 * @param:    @param type
	 * @param:    @return
	 * @return: List<AppOrganization> 
	 * @Date :          2016年1月6日 下午6:08:26   
	 * @throws:
	 */
	List<AppOrganization> findByCompanyId(String companyId, String type);

	/**
	 * <p> TODO</p>  递归查找当前组织下的所有组织  返回list
	 * @author:         Liu Shilei
	 * @param:    @param appOrganization
	 * @return: void 
	 * @Date :          2016年1月7日 下午3:21:36   
	 * @throws:
	 */
	List<AppOrganization> findSons(AppOrganization appOrganization);

	/**
	 * <p> TODO</p>  递归查找当前组织下的所有组织  返回  ids    1,2,3
	 * @author:         Liu Shilei
	 * @param:    @param appOrganization
	 * @return: void 
	 * @Date :          2016年1月7日 下午3:28:40   
	 * @throws:
	 */
	String findSonsToIds(AppOrganization appOrganization);
	
	/**
	 * <p> TODO</p>     查询一个组织结构下的子组织结构是否为空
	 * @author:         Liu Shilei
	 * @param:    @param boolean true有  false没有--则为空
	 * @return: void 
	 * @Date :          2015年12月25日 下午5:32:33   
	 * @throws:
	 */
	boolean hasSonOrganization(AppOrganization appOrganization);
	


}
