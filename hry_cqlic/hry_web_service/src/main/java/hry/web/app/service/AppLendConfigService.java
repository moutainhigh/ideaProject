/**
 * Copyright:  北京互融时代软件有限公司
 * @author:    Gao Mimi
 * @version:   V1.0 
 * @Date:      2015年10月10日  18:41:55
 */
package hry.web.app.service;


import hry.core.mvc.model.AppConfig;
import hry.core.mvc.service.base.BaseService;

import java.util.List;
import java.util.Map;

/**
 * <p> TODO</p>
 * @author:     Gao Mimi     
 * @Date :      2015年10月10日  18:41:55
 */
public interface AppLendConfigService extends BaseService<AppConfig, Long> {
	/**
	 *
	 * <p> 根据key拿到对应的值</p>
	 * @author:         Gao Mimi
	 * @param:    @param key
	 * @param:    @return
	 * @return: AppConfig
	 * @Date :          2015年10月10日 下午6:57:23
	 * @throws:
	 */
	public String getBykey(String key);

	/**
	 *
	 * <p> 设置key的值到数据库</p>
	 * @author:         Gao Mimid
	 * @param:    @param key
	 * @param:    @return
	 * @return: AppConfig
	 * @Date :          2015年10月10日 下午6:57:23
	 * @throws:
	 */
	public void setBykeyToDB(String key, String value);
	/**
	 *
	 * <p> 得到key的值从数据库</p>
	 * @author:         Gao Mimid
	 * @param:    @param key
	 * @param:    @return
	 * @return: AppConfig
	 * @Date :          2015年10月10日 下午6:57:23
	 * @throws:
	 */
	public String getBykeyfromDB(String key);

	/**
	 * <p> TODO</p>
	 * @author:         Zhang Xiaofang
	 * @param:    @param filter
	 * @return: void
	 * @Date :          2016年5月3日 下午6:58:19
	 * @throws:
	 */
	public List<AppConfig> findKey();



	List<AppConfig> getConfig(Map<String, String> map);


    List<AppConfig> findLendKey();
}


