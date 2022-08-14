/**
 * Copyright:   
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-14 11:18:59 
 */
package hry.admin.web.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import hry.admin.HryCache;
import hry.admin.web.dao.AppConfigDao;
import hry.admin.web.model.AppConfig;
import hry.admin.web.service.AppConfigService;
import hry.core.constant.StringConstant;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.redis.common.utils.RedisService;
import hry.util.QueryFilter;
import hry.util.RSAUtil;
import hry.util.httpRequest.HttpConnectionUtil;
import hry.util.properties.PropertiesUtil;
import hry.util.properties.PropertiesUtils;
import hry.util.rsa.FXHParam;
import net.sf.json.util.JSONUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p> AppConfigService </p>
 * @author:         liushilei
 * @Date :          2018-06-14 11:18:59  
 */
@Service("appConfigService")
public class AppConfigServiceImpl extends BaseServiceImpl<AppConfig, Long> implements AppConfigService{

	private final static Logger log = Logger.getLogger(AppConfigServiceImpl.class);
	
	@Resource(name="appConfigDao")
	@Override
	public void setDao(BaseDao<AppConfig, Long> dao) {
		super.dao = dao;
	}

	@Resource
	private RedisService redisService;

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
	public void initCache() {
		List<AppConfig> typeList = ((AppConfigDao) dao).findType();

		for (AppConfig config : typeList) {
			QueryFilter queryFilter = new QueryFilter(AppConfig.class);
			queryFilter.addFilter("typekey=",config.getTypekey());
			//queryFilter.addFilter("ishidden=",0);
			List<AppConfig> list = find(queryFilter);

			String data = JSON.toJSONString(list);
			redisService.save(StringConstant.CONFIG_CACHE + ":" + config.getTypekey(), data);
		}

		//List<AppConfig> list = find(new QueryFilter(AppConfig.class).addFilter("ishidden=",0));
		List<AppConfig> list = findAll();
		Map<String, String> map = new HashMap<String, String>();
		for (AppConfig app : list) {
			if (null != app.getConfigkey() && !"".equals(app.getConfigkey())) {
				map.put(app.getConfigkey(), app.getValue());
			}
		}
		String data = JSON.toJSONString(map);
		redisService.save(StringConstant.CONFIG_CACHE + ":all", data);
		log.info("初始化系统配置信息缓存"+StringConstant.CONFIG_CACHE + ":all");

		cache_appconfig(list);
	}

	/**
	 * 缓存部分值装载到HryCache中
	 */
	public void cache_appconfig(List<AppConfig> list){

		for (AppConfig app : list) {
			if ("extraParamConfig".equals(app.getTypekey())&&"managerSysLogo".equals(app.getConfigkey())) {
				HryCache.cache_appconfig.put("extraParamConfig_managerSysLogo", app.getValue());
			}
			if ("extraParamConfig".equals(app.getTypekey())&&"managerName".equals(app.getConfigkey())) {
				HryCache.cache_appconfig.put("extraParamConfig_managerName", app.getValue());
			}
			if ("baseConfig".equals(app.getTypekey())&&"siteCopyright".equals(app.getConfigkey())) {
				HryCache.cache_appconfig.put("baseConfig_siteCopyright", app.getValue());
			}
		}

	}


	@Override
	public String getBykeyfromRedis(String key) {
		String all = redisService.get(StringConstant.CONFIG_CACHE + ":all");
		if(!StringUtils.isEmpty(all)){
			JSONObject jsonObject = JSON.parseObject(all);
			return jsonObject.getString(key);
		}
		return null;
	}

	/**
	 * 获取各接口配额
	 * @param rkey redis对应的key
	 * @param urlConfig app配置文件中接口地址的key
	 * @return
	 */
	@Override
	public Map<String, String> getQuotaData(String rkey, String urlConfig){
		Map<String, String> returnMap = new HashMap<>();
		FXHParam fxhParam = new FXHParam();

		JSONObject.parseArray(redisService.get(rkey)).forEach(app -> {
			AppConfig appConfig = JSONObject.parseObject(app.toString(), AppConfig.class);
			switch (appConfig.getConfigkey()) {
				case "businessNumber":
					fxhParam.setCompanyCode(appConfig.getValue());
					break;
				case "publickey":
					fxhParam.setPublicKey(appConfig.getValue());
					break;
				case "privatekey":
					fxhParam.setPrivateKey(appConfig.getValue());
					break;
			}
		});

		String url = PropertiesUtils.APP.getProperty(urlConfig);
		fxhParam.setPayUrl(url);
		Map<String, Object> map = new HashMap<>(16);

		try {
			String sign = RSAUtil.encryptByPrivateKey(JSONObject.toJSONString(map), fxhParam.getPrivateKey());
			Map<String, String> paramMap = new HashMap<>();
			paramMap.put("companyCode", fxhParam.getCompanyCode());
			paramMap.put("sign", sign);

			String returnMsg = HttpConnectionUtil.doPostQuery(fxhParam.getPayUrl(), paramMap);
			if (!StringUtils.isEmpty(returnMsg)) {
				JSONObject jsonObject = JSONObject.parseObject(returnMsg);
				if ("8888".equals(jsonObject.getString("resultCode"))) {
					String result = RSAUtil.decryptByPublicKey(jsonObject.getString("data"), fxhParam.getPublicKey());
					if (!StringUtils.isEmpty(result)) {
						Map parse = JSON.parseObject(result, Map.class);
						if (parse != null || !parse.isEmpty()) {
							if (parse.get("usenum") != null) {
								returnMap.put("useNum", parse.get("usenum").toString());
							}
							if (parse.get("surplusnum") != null) {
								returnMap.put("releaseNum", parse.get("surplusnum").toString());
							}
							if (parse.get("totalnum") != null) {
								returnMap.put("totalnum", parse.get("totalnum").toString());
							}
							if (parse.get("balance") != null) {
								returnMap.put("balance", parse.get("balance").toString());
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnMap;
	}

}
