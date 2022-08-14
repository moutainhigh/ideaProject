package hry.cm.mq;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;

import com.rabbitmq.client.Channel;

import hry.cm.log.service.CmExceptionLogService;
import hry.cm.profitone.service.CmProfitOneService;

/***
 * 发矿工动态收益 拿一代静态收益
 * @author lenovo
 *
 */
public class MessageCmProfit1 implements ChannelAwareMessageListener {
	    private Logger log = LoggerFactory.getLogger(MessageCmProfit1.class);

	    private static final String NAME = "MessageCmProfit1";
	    
		@Resource
		private CmExceptionLogService cmExceptionLogService;
		@Resource
		private CmProfitOneService cmProfitOneService;
	    
	    @Override
	    public void onMessage(Message message, Channel channel) {
	        try {
	        	cmProfitOneService.profitOne(new String(message.getBody()));
	            //正常确认
	            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
	        } catch (Exception e) {
	            log.error("队列：{}  消费失败",NAME,e);
	            try {
	            	channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
	            } catch (Exception e1) {
	                log.error("队列：{}  ACK确认失败",NAME,e1);
	            }
	            //插入异常日志
    			cmExceptionLogService.insertlog(e, "MessageCmProfit1.onMessage", new String(message.getBody()));
	        }
	    }
}
