package hry.cm.mq;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;

import com.rabbitmq.client.Channel;

import hry.cm.customer.service.CmCustomerService;
import hry.cm.log.service.CmExceptionLogService;

public class MessageCmUpdateGrade implements ChannelAwareMessageListener {
	    private Logger log = LoggerFactory.getLogger(MessageCmAccount.class);

	    private static final String NAME = "toUpdateGrade";
	    
		@Resource
		private CmExceptionLogService cmExceptionLogService;
		@Resource
		private CmCustomerService cmCustomerService;
	    
	    @Override
	    public void onMessage(Message message, Channel channel) {
	        try {
	            cmCustomerService.updateGrade(new String(message.getBody()));
	            //正常确认
	            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
	        } catch (Exception e) {
	            log.error("队列：{}  消费失败",NAME,e);
	            try {
	                //channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
	            	channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
	            } catch (Exception e1) {
	                log.error("队列：{}  ACK确认失败",NAME,e1);
	            }
	            //插入异常日志
    			cmExceptionLogService.insertlog(e, "MessageCmUpdateGrade.onMessage", new String(message.getBody()));
	        }
	    }
}
