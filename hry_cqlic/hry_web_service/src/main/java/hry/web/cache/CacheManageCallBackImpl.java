/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Zhang Xiaofang
 * @version:      V1.0 
 * @Date:        2016年5月9日 下午2:39:30
 */
package hry.web.cache;

import hry.core.constant.StringConstant;
import hry.util.StringUtil;
import hry.redis.common.utils.RedisService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

/**
 * <p> TODO</p>
 * @author:         Zhang Xiaofang 
 * @Date :          2016年5月9日 下午2:39:30 
 */
@Service("cacheManageCallBack")
public class CacheManageCallBackImpl implements CacheManageCallBack {

	
	@Resource(name="redisService")
	 private RedisService redisService;
	
	
	/**
	 * 用来把需要被管理的某个缓存的一些信息保存在缓存里，方便统一管理
	 * Class serviceName：service的名字
	 * String cacheKey：缓存的key值
	 * String showName：缓存的名字(在后台管理的时候显示)
	 */
	@Override
	public void callback(Class serviceName, String cacheKey, String showName) {
		String[] s = serviceName.getName().split("\\.");
		String name = StringUtil.lowerFirst(s[s.length - 1]);
		String str = redisService.get(StringConstant.CACHE_TYPE);
		if (null != str && !"".equals(str)) {
			// 查询出CACHE_TYPE下的信息，当前的cacheKey不存在，就保存
			Map<String, String> m = new HashMap<String, String>();
			List<Map<String, String>> l = (List<Map<String, String>>) JSON.parse(str);
			String val = "";
			for (Map<String, String> mm : l) {
				String k = mm.get("key");
				val += k;
			}
			if (!val.contains(cacheKey)) {
				m.put("key", cacheKey);
				m.put("name", name);
				m.put("showName", showName);
				l.add(m);
				redisService.save(StringConstant.CACHE_TYPE, JSON.toJSONString(l));
			}
		} else {
			List<Map<String, String>> list = new ArrayList<Map<String, String>>();
			Map<String, String> map = new HashMap<String, String>();
			map.put("key", cacheKey);
			map.put("name", name);
			map.put("showName", showName);
			list.add(map);
			String data = JSON.toJSONString(list);
			redisService.save(StringConstant.CACHE_TYPE, data);
		}

	}

}
