package util;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.util.StringUtil;
import hry.bean.ObjectUtil;
import hry.otc.releaseAdvertisement.model.OtcAppTransaction;
import hry.otc.releaseAdvertisement.model.OtcCompletionRate;
import hry.otc.releaseAdvertisement.service.OtcAppTransactionService;
import hry.otc.releaseAdvertisement.service.OtcCompletionRateService;
import hry.otc.remote.model.RedisModel;
import hry.redis.common.utils.RedisService;
import hry.util.QueryFilter;
import hry.util.ServerUtil;
import hry.util.SpringUtil;
import org.apache.log4j.Logger;
import redis.clients.jedis.JedisPubSub;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * @Author: denghf
 * @Date: 2018/12/21 16:35
 * @Description: RedisConsumer.java
 */
public class RedisConsumer extends JedisPubSub {

    private static Logger logger = Logger.getLogger(RedisConsumer.class);
    /*static {
        Jedis j = new Jedis("192.168.232.128", 6379);
        j.auth("Credit2016Admin");
    }*/

    // 取得订阅的消息后的处理
    @Override
    public void onMessage(String channel, String message) {
        logger.info("redis 信道：" + channel + "，消息内容：" + message);
        if(StringUtil.isNotEmpty(message)){ // 消息即为用户ID
            RedisService redisService = SpringUtil.getBean("redisService");

            while(true){
                String lpop = redisService.lpop("otc:queue"); // 队列中是否存在数据，并移除头部数据
                if(StringUtil.isNotEmpty(lpop)){
                    RedisModel redisModel = JSON.parseObject(lpop, RedisModel.class);

                    OtcCompletionRateService otcCompletionRateService = SpringUtil.getBean("otcCompletionRateService");
                    OtcCompletionRate ocr = otcCompletionRateService.get(new QueryFilter(OtcCompletionRate.class).addFilter("customerId=", redisModel.getUserId()).addFilter("coinCode=", redisModel.getCoinCode()));
                    if(ocr != null){
                        OtcAppTransactionService otcAppTransactionService = SpringUtil.getBean("otcAppTransactionService");
                        // 统计总数量
                        String state = "2,3,4,5,6,7,8,9,10,11,12,13,14,15,16";
                        List<OtcAppTransaction> listAll = otcAppTransactionService.find(new QueryFilter(OtcAppTransaction.class).addFilter("customerId=", redisModel.getUserId()).addFilter("status_in", state));
                        // 统计已完成 14
                        List<OtcAppTransaction> list14 = otcAppTransactionService.find(new QueryFilter(OtcAppTransaction.class).addFilter("customerId=", redisModel.getUserId()).addFilter("status_in", state));

                        ocr.setTradeOk(Long.valueOf(list14.size()));
                        ocr.setTradeAll(Long.valueOf(listAll.size()));
                        BigDecimal rate = (new BigDecimal(list14.size()).divide(new BigDecimal(listAll.size())).setScale(2, BigDecimal.ROUND_HALF_UP)).multiply(new BigDecimal(100));
                        ocr.setCompletionRate(rate);
                        otcCompletionRateService.update(ocr);
                    }else{
                        OtcCompletionRate o = new OtcCompletionRate();
                        o.setCustomerId(redisModel.getUserId());
                        o.setCoinCode(redisModel.getCoinCode());
                        o.setTradeOk(1L);
                        o.setTradeAll(1L);
                        o.setCompletionRate(new BigDecimal(100));
                        otcCompletionRateService.save(o);
                    }
                }else{
                    break;
                }
            }
        }
    }

    // 取得按匹配方式订阅的消息后的处理
    @Override
    public void onPMessage(String pattern, String channel, String message) {

    }

    // 初始化订阅时候的处理
    @Override
    public void onSubscribe(String channel, int subscribedChannels) {

    }

    // 取消订阅时候的处理
    @Override
    public void onUnsubscribe(String channel, int subscribedChannels) {

    }

    // 取消按匹配方式订阅时候的处理
    @Override
    public void onPUnsubscribe(String pattern, int subscribedChannels) {

    }

    // 初始化按匹配方式订阅时候的处理
    @Override
    public void onPSubscribe(String pattern, int subscribedChannels) {

    }
}
