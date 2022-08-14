package hry.cm.mq;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import hry.cm.log.service.CmExceptionLogService;
import hry.cm.runnable.ProfitTwoRunnable;
import hry.util.SpringUtil;

/***
 * 统计矿场收益记录
 * @author lenovo
 *
 */
public class MessageCmProfit2 implements MessageListener {
	    private Logger log = LoggerFactory.getLogger(MessageCmProfit2.class);

	    private static final String NAME = "MessageCmProfit2";
	    
		@Resource
		private CmExceptionLogService cmExceptionLogService;
	    
	    @Override
	    public void onMessage(Message message) {
	        try {
	        	ThreadPoolTaskExecutor taskExecutor= SpringUtil.getBean("serverManagementTaskExecutor");
	        	taskExecutor.execute(new ProfitTwoRunnable(new String(message.getBody())));
	        	
	        } catch (Exception e) {
	            log.error("队列：{}  消费失败",NAME,e);
	            //插入异常日志
    			cmExceptionLogService.insertlog(e, "MessageCmProfit1.onMessage", new String(message.getBody()));
	        }
	    }
}
