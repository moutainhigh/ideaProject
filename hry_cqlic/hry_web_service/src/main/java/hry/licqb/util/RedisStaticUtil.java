package hry.licqb.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import hry.core.constant.StringConstant;
import hry.redis.common.utils.RedisService;
import hry.util.SpringUtil;

/**
 * @author zhouming
 * @date 2019/8/15 9:47
 * @Version 1.0
 */
public class RedisStaticUtil {

    public static String getAppConfigValue(String typeKey, String configKey){
        RedisService redisService = SpringUtil.getBean("redisService");
        String jsonStr = redisService.get(StringConstant.CONFIG_CACHE + ":" +typeKey);
        JSONArray obj = JSON.parseArray(jsonStr);
        for(Object o:obj) {
            JSONObject oo = JSON.parseObject(o.toString());
            if (oo.getString("configkey").equals(configKey)) {
                return oo.getString("value");
            }
        }
        return "";
    }
}
