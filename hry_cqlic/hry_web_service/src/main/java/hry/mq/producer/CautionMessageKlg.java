package hry.mq.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;

import hry.core.thread.ThreadPool;
import hry.mq.producer.task.SendCaution;
import hry.redis.common.utils.RedisService;
import hry.util.properties.PropertiesUtils;
import hry.util.sys.ContextUtil;

/**
 * @author <a href="mailto:HelloHeSir@gmail.com">Mr_He</a>
 * @Copyright (c)</b> HeC<br/>
 * @createTime 2018/9/6 16:57
 * @Description: 消息异常处理器
 */
public class CautionMessageKlg {

    private static final Logger log = LoggerFactory.getLogger(CautionMessageKlg.class);
    private static  Integer	MAX_MESSAGE =1000;
    static{
    	
    	String vals=PropertiesUtils.APP.getProperty("mq.message.max");
    	if(null!=vals){
    		Integer va=Integer.parseInt(vals);
    		MAX_MESSAGE = va;
    	}
    }

    private static RedisService redisService = (RedisService) ContextUtil.getBean("redisService");

    /**
     * @param channel 通道
     * @param queue   队列名
     * @param reject  是否允许拒绝消息
     * @param message 消息
     */
    static void inspect(Channel channel, String queue, boolean reject, Message message) {
        try {
            AMQP.Queue.DeclareOk declareOk = channel.queueDeclarePassive(queue);
            int messageCount = declareOk.getMessageCount();
            if (messageCount > MAX_MESSAGE) {
                ThreadPool.exe(new SendCaution(queue,messageCount));
            }else{
                redisService.delete("deal:stop");
            }
            //正常确认
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            try {
                if (reject) {
                    //channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
                    log.error("[系统严重错误]消息处理异常，消息已拒绝，异常消息：{}", new String(message.getBody(), "utf-8"), e);
                } else {
                    //channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
                    log.error("[系统严重错误]消息处理异常，消息已重新进入队列，异常消息：{}", new String(message.getBody(), "utf-8"), e);
                }
            } catch (Exception e1) {
                log.error("[系统严重错误]消息处理异常", e1);
            }
        }

    }




}