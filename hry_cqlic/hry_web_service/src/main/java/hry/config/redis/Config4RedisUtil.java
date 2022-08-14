package hry.config.redis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import hry.core.constant.StringConstant;
import hry.core.mvc.model.AppConfig;
import hry.redis.common.utils.RedisService;
import hry.util.QueryFilter;
import hry.util.SpringUtil;
import hry.web.app.service.AppConfigService;

import java.util.List;

public class Config4RedisUtil {

    public static String getCnfigValue(String key) {

        RedisService redisService = SpringUtil.getBean("redisService");
        AppConfigService appConfigService = SpringUtil.getBean("appConfigService");

        String value="";
        if(null!=key&&!"".equals(key)){
            String data="";
            data=redisService.get(StringConstant.CONFIG_CACHE+":all");
            if(null!=data&&!"".equals(data)){
                JSONObject obj= JSON.parseObject(data);
                if(obj.containsKey(key)){
                    value=obj.get(key).toString();
                }
            }
        }
        if (null==value||"".equals(value)) {
            QueryFilter filter=new QueryFilter(AppConfig.class);
            filter.addFilter("configkey=",key );

            List<AppConfig> list=appConfigService.find(filter);
            if(null!=list&&list.size()>0){
                value=list.get(0).getValue();
            }
        }

        return value;

    }

}
