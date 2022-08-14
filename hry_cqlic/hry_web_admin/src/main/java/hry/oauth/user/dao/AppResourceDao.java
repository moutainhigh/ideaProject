/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Liu Shilei
 * @version:      V1.0 
 * @Date:        2015年9月21日 上午11:39:33
 */
package hry.oauth.user.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.oauth.user.model.AppResource;

import java.util.List;

/**
 * <p> TODO</p>
 * @author:         Liu Shilei 
 * @Date :          2015年9月21日 上午11:39:33 
 */

public interface AppResourceDao extends BaseDao<AppResource, Long> {

	/**
	 * <p> TODO</p> 根据appRole 查询出所有的权限
	 * @author:         Liu Shilei
	 * @param:    @param appRole
	 * @param:    @return
	 * @return: List<AppResource> 
	 * @Date :          2015年12月10日 下午2:39:52   
	 * @throws:
	 */
	List<AppResource> findByAppRole(Long appRoleId,String saasId);
	
}
