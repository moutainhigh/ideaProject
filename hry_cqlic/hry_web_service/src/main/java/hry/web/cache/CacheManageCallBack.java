/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Zhang Xiaofang
 * @version:      V1.0 
 * @Date:        2016年5月6日 下午3:22:22
 */
package hry.web.cache;

import java.util.Map;

/**
 * <p> TODO</p>
 * @author:         Zhang Xiaofang 
 * @Date :          2016年5月6日 下午3:22:22 
 */
public interface CacheManageCallBack {
	/**
	 * <p> 把需要被管里的缓存保存起来</p>
	 * @author:         Zhang Xiaofang
	 * @param:    
	 * @return: void 
	 * @Date :          2016年5月4日 下午6:20:14   
	 * @throws:
	 */
	void callback(Class serviceName, String cacheKey, String name);
}
