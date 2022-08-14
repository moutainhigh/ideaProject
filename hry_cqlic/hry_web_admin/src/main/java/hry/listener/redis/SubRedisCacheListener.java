package hry.listener.redis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import hry.admin.web.service.AppConfigService;
import hry.util.SpringUtil;
import org.apache.log4j.Logger;
import redis.clients.jedis.JedisPubSub;

public class SubRedisCacheListener extends JedisPubSub {
    private final static Logger log = Logger.getLogger(SubRedisCacheListener.class);

    @Override
    public void onMessage(String channel, String message) {
        try {
            JSONObject jsonObject  = JSON.parseObject(message);
            System.out.println(message);
            AppConfigService appConfigService = SpringUtil.getBean("appConfigService");
            appConfigService.initCache();
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
