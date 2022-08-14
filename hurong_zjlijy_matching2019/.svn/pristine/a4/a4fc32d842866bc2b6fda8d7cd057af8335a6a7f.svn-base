package hry.cm2.mq;

import com.rabbitmq.client.Channel;
import hry.cm2.customer.service.Cm2CustomerService;
import hry.cm2.log.service.Cm2ExceptionLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;

import javax.annotation.Resource;

public class MessageCmUpdateGrade implements ChannelAwareMessageListener {
	    private Logger log = LoggerFactory.getLogger(MessageCmAccount.class);

	    private static final String NAME = "toUpdateGrade";
	    
		@Resource
		private Cm2ExceptionLogService cmExceptionLogService;
		@Resource
		private Cm2CustomerService cmCustomerService;
	    
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
