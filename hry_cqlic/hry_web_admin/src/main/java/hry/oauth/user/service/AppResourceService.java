/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Liu Shilei
 * @version:      V1.0 
 * @Date:        2015年9月21日 上午11:41:33
 */
package hry.oauth.user.service;

import hry.core.mvc.service.base.BaseService;
import hry.oauth.user.model.AppResource;

/**
 * <p> TODO</p>
 * @author:         Liu Shilei 
 * @Date :          2015年9月21日 上午11:41:33 
 */
public interface AppResourceService extends BaseService<AppResource, Long>{
	

	/**
	 * <p> TODO</p> 初始化权限缓存
	 * @author:         Liu Shilei
	 * @param:    
	 * @return: void 
	 * @Date :          2015年10月21日 下午4:26:01   
	 * @throws:
	 */
	void initCache();

	


	
}
