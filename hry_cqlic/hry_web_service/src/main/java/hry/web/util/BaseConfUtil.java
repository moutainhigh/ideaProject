package hry.web.util;

import com.alibaba.fastjson.JSON;

import hry.manage.remote.model.AppConfig;
import hry.redis.common.utils.RedisService;
import hry.util.SpringUtil;

import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseConfUtil {

	// 查询查询系统基础配置
	public static Map<String, Object> getConfigByKey(String key) {
		Map<String, Object> map = new HashMap<String, Object>();
		RedisService redisService = SpringUtil.getBean("redisService");
		String text = redisService.get(key);
		if (!StringUtils.isEmpty(text)) {
			List<AppConfig> list = JSON.parseArray(text, AppConfig.class);
			for (AppConfig l : list) {
				map.put(l.getConfigkey(), l.getValue());
			}
		}
		return map;
	}

	// 原系统基础配置被拆分成基本信息配置和附加参数配置，
	// 导致部分获取baseConfig数据获取不全报错问题，
	// 故以下方法解决此问题
	public static Map<String, Object> getConfigByKey (String key, String lang) {
		if (lang == null) {
			lang = "";
		}
		Map<String, Object> returnMap = new HashMap<>();
		// 获取附加参数配置
		Map<String, Object> extramap = getConfigByKey("configCache:extraParamConfig");
		if (!extramap.isEmpty()) {
			returnMap.putAll(extramap);
		}
		// 获取基础配置
		Map<String, Object> basemap = getConfigByKey("configCache:baseConfig" + lang);
		if (!basemap.isEmpty()) {
			returnMap.putAll(basemap);
		}
		return returnMap;
	}


	/**
	 * 查询单个key值
	 * @param typeKey   分类
	 * @param key  key值
	 * @return
	 */
	public static String getConfigSingle(String typeKey,String key) {
		Map<String, Object> map = new HashMap<String, Object>();
		RedisService redisService = SpringUtil.getBean("redisService");
		String text = redisService.get(key);
		if (!StringUtils.isEmpty(text)) {
			List<AppConfig> list = JSON.parseArray(text, AppConfig.class);
			for (AppConfig l : list) {
				map.put(l.getConfigkey(), l.getValue());
			}
		}
		if(!map.isEmpty()) {
			return map.get(key).toString();
		}
		return "";
	}
}
