package hry.admin.mq.producer;

import org.apache.log4j.Logger;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

/**
 * 充币监听
 * @author CHINA_LSL
 *
 */
public class MessageChongbiCallBack implements MessageListener {
	private Logger logger = Logger.getLogger(MessageChongbiCallBack.class);

	@Override
	public void onMessage(Message message) {
		String str = new String(message.getBody());
		logger.info("@@@receives chongbi rabbitmq message======="+str);
		

	}

}