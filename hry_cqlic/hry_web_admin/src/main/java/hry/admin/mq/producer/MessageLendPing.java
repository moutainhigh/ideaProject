package hry.admin.mq.producer;

import org.apache.log4j.Logger;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

/**
 * 充币监听
 * @author CHINA_LSL
 *
 */
public class MessageLendPing implements MessageListener {
	private Logger logger = Logger.getLogger(MessageLendPing.class);

	@Override
	public void onMessage(Message message) {	String str = new String(message.getBody());

	
	}

}