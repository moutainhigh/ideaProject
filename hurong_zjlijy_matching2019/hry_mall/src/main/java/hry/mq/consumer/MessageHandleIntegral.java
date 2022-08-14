package hry.mq.consumer;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;

import com.rabbitmq.client.Channel;

import hry.core.thread.ThreadPool;
import hry.mall.integral.service.IntegralDetailService;
import hry.mq.message.SendCaution;

public class MessageHandleIntegral implements ChannelAwareMessageListener {
    private Logger log = LoggerFactory.getLogger(MessageHandleIntegral.class);
    private static final String NAME = "toIntegral";
	
   @Resource
   public IntegralDetailService  integralDetailService; 
	
	@Override
	public void onMessage(Message message, Channel channel) throws Exception {
		// TODO Auto-generated method stub
	     try {
	    	    integralDetailService.handleIntegral(new String(message.getBody(),"utf-8"));;
	            CautionMessage.inspect(channel, NAME, false, message);
	        } catch (Exception e) {
	            log.error("队列：{}  消费失败", NAME, e);
	            try {
	                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
	                int messageCount = channel.queueDeclarePassive(NAME).getMessageCount();
	                ThreadPool.exe(new SendCaution(NAME, messageCount));
	            } catch (Exception e1) {
	                log.error("队列：{}  ACK确认失败", NAME, e1);
	            }
	        }
	    }

}
