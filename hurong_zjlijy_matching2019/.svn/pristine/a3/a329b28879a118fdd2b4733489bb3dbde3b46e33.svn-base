package hry.cm4.mq;

import com.rabbitmq.client.Channel;
import hry.cm4.log.service.Cm4ExceptionLogService;
import hry.cm4.profitone.service.Cm4ProfitOneService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;

import javax.annotation.Resource;

/***
 * 发矿工动态收益 拿一代静态收益
 * @author lenovo
 *
 */
public class MessageCmProfit1 implements ChannelAwareMessageListener {
	    private Logger log = LoggerFactory.getLogger(MessageCmProfit1.class);

	    private static final String NAME = "MessageCmProfit1";
	    
		@Resource
		private Cm4ExceptionLogService cmExceptionLogService;
		@Resource
		private Cm4ProfitOneService cmProfitOneService;
	    
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
