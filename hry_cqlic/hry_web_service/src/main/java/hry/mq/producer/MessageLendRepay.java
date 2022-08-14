package hry.mq.producer;

import org.apache.log4j.Logger;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

import com.alibaba.fastjson.JSON;

import hry.util.sys.ContextUtil;
import hry.exchange.lend.ExDmPingNorService;
import hry.exchange.lend.service.ExDmPingService;
import hry.manage.remote.RemoteInterfaceCallbackService;
import hry.manage.remote.model.LmcTransfer;

/**
 * 充币监听
 * @author CHINA_LSL
 *
 */
public class MessageLendRepay implements MessageListener {
	private Logger logger = Logger.getLogger(MessageLendRepay.class);

	@Override
	public void onMessage(Message message) {
		String str = new String(message.getBody());
		ExDmPingNorService exDmPingService = (ExDmPingNorService) ContextUtil.getBean("exDmPingNorService");
		exDmPingService.jobRunTimeRepayLendMoney();
		
	}

}