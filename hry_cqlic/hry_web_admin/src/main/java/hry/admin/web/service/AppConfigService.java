/**
 * Copyright:   
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-14 11:18:59 
 */
package hry.admin.web.service;

import hry.admin.web.model.AppConfig;
import hry.core.mvc.service.base.BaseService;

import java.util.Map;


/**
 * <p> AppConfigService </p>
 * @author:         liushilei
 * @Date :          2018-06-14 11:18:59 
 */
public interface AppConfigService  extends BaseService<AppConfig, Long>{

    public String getBykeyfromDB(String key);

    /**
     * 初始化缓存
     */
    public void initCache();

    /**
     * 查询缓存从redis中取值
     * @param key
     * @return
     */
    public String getBykeyfromRedis(String key);

    public String getBykey(String key);


    Map<String, String> getQuotaData (String rkey, String urlConfig);
}
