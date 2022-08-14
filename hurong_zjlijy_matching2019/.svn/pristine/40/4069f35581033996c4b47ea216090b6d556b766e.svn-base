package hry.cm.mq;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

import hry.cm.dividend.service.CmFeeRecordService;
import hry.cm.log.service.CmExceptionLogService;

/***
 * 发放手续费分红
 * @author lenovo
 *
 */
public class MessageCmBonus implements MessageListener {
	    private Logger log = LoggerFactory.getLogger(MessageCmBonus.class);

	    private static final String NAME = "MessageCmBonus";
	    
		@Resource
		private CmExceptionLogService cmExceptionLogService;
		@Resource
		private CmFeeRecordService cmFeeRecordService;
	    
	    @Override
	    public void onMessage(Message message) {
	        try {
	        	cmFeeRecordService.fafangBonus(new String(message.getBody()));
	        } catch (Exception e) {
	            log.error("队列：{}  消费失败",NAME,e);
	            //插入异常日志
    			cmExceptionLogService.insertlog(e, "MessageCmBonus.onMessage", new String(message.getBody()));
	        }
	    }
}
