package hry.trade.mq;

import hry.trade.entrust.service.ExOrderInfoService;
import hry.util.sys.ContextUtil;
import org.apache.log4j.Logger;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

public class MessageReidsToRedisLog implements MessageListener {
	private Logger logger = Logger.getLogger(MessageReidsToRedisLog.class);

	@Override
	public void onMessage(Message message) {
		ExOrderInfoService exOrderInfoService = (ExOrderInfoService) ContextUtil.getBean("exOrderInfoService");
		exOrderInfoService.reidsToredisLog();
	}

}
