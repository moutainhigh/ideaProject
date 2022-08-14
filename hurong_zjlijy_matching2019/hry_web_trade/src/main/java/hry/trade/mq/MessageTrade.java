package hry.trade.mq;

import hry.trade.entrust.service.TradeService;
import hry.util.sys.ContextUtil;
import org.apache.log4j.Logger;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

import java.io.UnsupportedEncodingException;

public class MessageTrade implements MessageListener {
	private Logger logger = Logger.getLogger(MessageTrade.class);

	@Override
	public void onMessage(Message message) {
		TradeService tradeService = (TradeService) ContextUtil.getBean("tradeService");
		try {
			tradeService.matchExtrustToOrderQueue(new String(message.getBody(),"utf-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
