package hry.admin.mq.producer;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.Channel;
import hry.admin.klg.log.model.KlgExceptionLog;
import hry.admin.klg.log.service.KlgExceptionLogService;
import hry.admin.klg.platform.model.KlgPlatformAccount;
import hry.admin.klg.platform.service.KlgPlatformAccountService;
import hry.klg.model.RulesConfig;
import hry.klg.remote.RemoteKlgService;
import hry.redis.common.utils.RedisService;
import hry.util.SpringUtil;
import hry.util.sys.ContextUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class MessageToPlatformCurrency implements ChannelAwareMessageListener {
    private Logger log = LoggerFactory.getLogger(MessageToPlatformCurrency.class);

    private static final String NAME = "toPlatformCurrency";
    @Resource
    private KlgPlatformAccountService platformAccountService;
    @Resource
    private KlgExceptionLogService klgExceptionLogService;
    @Resource
    private RedisService redisService;
    @Override
    public void onMessage(Message message, Channel channel) {
        try {
            RemoteKlgService remoteKlgService= SpringUtil.getBean("remoteKlgService");
            String msg=new String(message.getBody(),"UTF-8");
            remoteKlgService.PlatformAccountAddQueue(msg);
         /*
              String msg=new String(message.getBody());
              Map map= JSON.parseObject(msg,Map.class);
              String type=map.get("type").toString();//类型
              String money=map.get("money").toString();//资金
              reducePlatformCurrency(type,money);*/
              CautionMessage.inspect(channel, NAME, false, message);
              log.info("队列：{}  消费平台币"+new String(message.getBody()));
        } catch (Exception e) {
             try {
                saveException(new String(message.getBody(),"UTF-8"));
            } catch (UnsupportedEncodingException e1) {
                e1.printStackTrace();
            }
            log.error("队列：{}  消费平台币失败",NAME,e);
            try {
                CautionMessage.inspect(channel, NAME, false, message);
//                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
            } catch (Exception e1) {
                log.error("队列：{}  ACK确认失败",NAME,e1);
            }
        }
    }

    public  boolean saveException(String msg){
        KlgExceptionLog log=new KlgExceptionLog();
        log.setExceptionText(msg);
        klgExceptionLogService.save(log);
        return true;
    }

    private  boolean reducePlatformCurrency(String type,String money){
        JedisPool jedisPool = (JedisPool) ContextUtil.getBean("jedisPool");
        Jedis jedis=jedisPool.getResource();
        String redisKey=RulesConfig.PLATFORM_NUMBER+type;
        String str=  jedis.get(redisKey);
        try {
            if(str==null||"".equals(str)){
                str="0";
            }
            redis.clients.jedis.Transaction tx = jedis.multi();
            BigDecimal num=new BigDecimal(str).add(new BigDecimal(money));
            tx.set(redisKey, num.stripTrailingZeros().toPlainString());
            List<Object> list =tx.exec();
            if(null==list||list.size()==0){
                throw new RuntimeException();
            }
            platformAccountService.updatePlatformAccount(money,type);//入库
        }catch(Exception e	) {
             throw e;
        }finally {
            jedis.close();
        }
        return  true;
    }
}
