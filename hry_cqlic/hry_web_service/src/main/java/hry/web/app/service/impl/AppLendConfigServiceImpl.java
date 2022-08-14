/**
 * Copyright:  北京互融时代软件有限公司
 * @author:    Gao Mimi
 * @version:   V1.0 
 * @Date:      2015年10月10日  18:41:55
 */
package hry.web.app.service.impl;


import com.alibaba.fastjson.JSON;
import hry.core.constant.StringConstant;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.model.AppConfig;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.util.QueryFilter;
import hry.redis.common.utils.RedisService;
import hry.web.app.dao.AppLendConfigDao;
import hry.web.app.service.AppLendConfigService;
import hry.web.article.dao.AppBannerDao;
import hry.web.cache.CacheManageCallBack;
import hry.web.cache.CacheManageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p> TODO</p>
 * @author:   Gao Mimi        
 * @Date :    2015年10月10日  18:41:55     
 */
@Service("appLendConfigService")
public class AppLendConfigServiceImpl extends BaseServiceImpl<AppConfig, Long> implements AppLendConfigService,CacheManageService{

	@Resource(name = "redisService")
	private RedisService redisService;

	@Resource(name = "appBannerDao")
	private AppBannerDao appBannerDao;

	@Resource(name = "appLendConfigDao")
	@Override
	public void setDao(BaseDao<AppConfig, Long> dao) {
		super.dao = dao;
	}

	@Override
	public String getBykey(String key) {
		QueryFilter filter = new QueryFilter(AppConfig.class);
		if (key != null) {
			filter.addFilter("configkey=", key);
		}
		
		
		List<AppConfig> list = super.dao.selectByExample(filter.setNosaas().getExample());
		return list==null?null:list.get(0).getValue();
	}


	
	@Override
	public 	List<AppConfig> findKey() {
		
		return ((AppLendConfigDao)dao).findKey();
	}
	
	
	@Override
	public void initCache(CacheManageCallBack cacheManageCallBack) {
		List<AppConfig> typeList = ((AppLendConfigDao) dao).findKey();

		for (AppConfig config : typeList) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("typekey", config.getTypekey());
			List<AppConfig> list = ((AppLendConfigDao) dao).getConfig(map);

			String data = JSON.toJSONString(list);
			redisService.save(StringConstant.CONFIG_CACHE + ":" + config.getTypekey(), data);
		}
		Map<String,String> queryMap = new HashMap<>();
		queryMap.put("typekey","financeLendConfig");
		queryMap.put("typekey1","financeProtocol");
		List<AppConfig> list = ((AppLendConfigDao) dao).getConfig(queryMap);
		Map<String, String> map = new HashMap<String, String>();
		for (AppConfig app : list) {
			if (null != app.getConfigkey() && !"".equals(app.getConfigkey())) {
				map.put(app.getConfigkey(), app.getValue());
			}
		}
		String data = JSON.toJSONString(map);
		redisService.save(StringConstant.CONFIG_CACHE + ":financeLendConfig", data);
		cacheManageCallBack.callback(AppLendConfigService.class, StringConstant.CONFIG_CACHE + ":financeLendConfig", "杠杆配置信息缓存");

	}

	@Override
	public List<AppConfig> getConfig(Map<String, String> map) {
		List<AppConfig> list=((AppLendConfigDao)dao).getConfig(map);
		return list;
	}

	@Override
	public List<AppConfig> findLendKey() {
		return ((AppLendConfigDao)dao).findLendKey();
	}

	@Override
	public void setBykeyToDB(String key,String value) {
		QueryFilter filter = new QueryFilter(AppConfig.class);
		if (key != null) {
			filter.addFilter("configkey=", key);
		}
		AppConfig appConfig=this.get(filter);
		if(null!=appConfig){
			appConfig.setValue(value);
			this.update(appConfig);
		}
	}


	/**
	 * 0 开启，1 不开启
	 */
	@Override
	public String getBykeyfromDB(String key) {
		QueryFilter filter = new QueryFilter(AppConfig.class);
		if (key != null) {
			filter.addFilter("configkey=", key);
		}
		AppConfig appConfig=this.get(filter);
		if(null!=appConfig){
			return appConfig.getValue();
		}
		return null;
	}


	

}