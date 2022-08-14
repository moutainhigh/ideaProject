package hry.admin.mq.producer;

import hry.message.remote.RemoteRemindService;
import hry.util.SpringUtil;
import org.apache.log4j.Logger;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

/**
 * 预警消息
 * @author CHINA_LSL
 *
 */
public class MessageToWarn implements MessageListener {
	private Logger logger = Logger.getLogger(MessageToWarn.class);

	@Override
	public void onMessage(Message message) {
		String str = new String(message.getBody());
		RemoteRemindService remoteRemindService=SpringUtil.getBean("remoteRemindService");
		remoteRemindService.sendAllRemind(str);
	}
}