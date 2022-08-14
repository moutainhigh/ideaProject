package hry.app.listener;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import hry.redis.common.utils.RedisService;
import hry.util.HryCache;
import hry.util.common.SpringUtil;
import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;
import redis.clients.jedis.JedisPubSub;

import java.util.HashMap;
import java.util.Map;

public class SubLanguageCacheListener extends JedisPubSub {
    private final static Logger log = Logger.getLogger(SubLanguageCacheListener.class);

    @Override
    public void onMessage(String channel, String message) {
        try {
            //更新全部
            log.info("同步国际化词条..........开始");
            RedisService redisService =  SpringUtil.getBean("redisService");
            String sysLanguage = redisService.hget("new_app_dic", "sysLanguage");
            if(!StringUtils.isEmpty(sysLanguage)) {
                JSONArray parseArray = JSON.parseArray(sysLanguage);
                if(parseArray!=null) {
                    for(int i =0 ; i<parseArray.size();i++) {
                        JSONObject jsonObject = parseArray.getJSONObject(i);
                        String key = jsonObject.getString("value");
                        //  电脑端词汇加载
                        //查询语言包
                        Map<String, String> hgetall_pc = redisService.hgetall("language:"+key);
                        if(!hgetall_pc.isEmpty()){
                            //添加到缓存中
                            HryCache.pc_cache_language.put(key, hgetall_pc);
                        }
                        // 手机端词汇加载
                        //查询语言包
                        Map<String, String> hgetall_app = redisService.hgetall("app_language:"+key);
                        if(!hgetall_app.isEmpty()){
                            //添加到缓存中
                            HryCache.app_cache_language.put(key, hgetall_app);
                        }
                    }
                }
            }
            log.info("同步国际化词条..........完成");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onPMessage(String pattern, String channel, String message) {

    }

    @Override
    public void onSubscribe(String channel, int subscribedChannels) {

    }

    @Override
    public void onUnsubscribe(String channel, int subscribedChannels) {

    }

    @Override
    public void onPUnsubscribe(String pattern, int subscribedChannels) {

    }

    @Override
    public void onPSubscribe(String pattern, int subscribedChannels) {

    }
}
