/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Zhang Xiaofang
 * @version:      V1.0 
 * @Date:        2016年5月6日 下午3:22:22
 */
package hry.web.cache;

/**
 * <p> TODO</p>
 * @author:         Zhang Xiaofang 
 * @Date :          2016年5月6日 下午3:22:22 
 */
public interface CacheManageService {
	/**
	 * <p> 初始化 配置信息到 缓存 缓存 key
	 *    并把该缓存的key值保存在另一个缓存中。
	 *  </p>
	 * @author:         Zhang Xiaofang
	 * @param:    
	 * @return: void 
	 * @Date :          2016年5月4日 下午6:20:14   
	 * @throws:
	 */
	void initCache(CacheManageCallBack callback);
	
}
