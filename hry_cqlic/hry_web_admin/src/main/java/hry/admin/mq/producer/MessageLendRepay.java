package hry.admin.mq.producer;

import org.apache.log4j.Logger;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

/**
 * 充币监听
 * @author CHINA_LSL
 *
 */
public class MessageLendRepay implements MessageListener {
	private Logger logger = Logger.getLogger(MessageLendRepay.class);

	@Override
	public void onMessage(Message message) {

	}

}