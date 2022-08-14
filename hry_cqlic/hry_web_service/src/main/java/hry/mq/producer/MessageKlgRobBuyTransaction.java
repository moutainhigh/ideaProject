package hry.mq.producer;

import org.apache.log4j.Logger;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.Channel;

import hry.klg.log.model.KlgExceptionLog;
import hry.klg.log.service.KlgExceptionLogService;
import hry.klg.remote.RemoteKlgBuySellTransactionService;
import hry.klg.remote.model.BuyPayMessage;
import hry.util.SpringUtil;
import hry.util.sys.ContextUtil;

public class MessageKlgRobBuyTransaction implements ChannelAwareMessageListener{
	private Logger logger = Logger.getLogger(MessageKlgRobBuyTransaction.class);
	private static final String NAME = "toRobBuyTransaction";
	@Override
	public void onMessage(Message message, Channel channel) {
		String str = new String(message.getBody());
		logger.info("@@@receives RobBuyTransaction rabbitmq message======="+str);
		
		try {
			BuyPayMessage buyPayMessage = JSON.parseObject(str, BuyPayMessage.class);
			RemoteKlgBuySellTransactionService remoteKlgBuySellTransactionService = (RemoteKlgBuySellTransactionService) ContextUtil.getBean("remoteKlgBuySellTransactionService");
			remoteKlgBuySellTransactionService.robBuyTransactionMessage(buyPayMessage);
            CautionMessage.inspect(channel, NAME, false, message);
        } catch (Exception e) {
        	logger.error("队列：{}  消费失败");
        	e.printStackTrace();
            try {
            	/***消息异常，修改订单***/
            	channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            	KlgExceptionLogService klgExceptionLogService = SpringUtil.getBean("klgExceptionLogService");
				// 插入异常日志
				KlgExceptionLog klgExceptionLog = new KlgExceptionLog();
				str = "抢单消息:"+str;
				if (str.length() <= 230) {
					klgExceptionLog.setFunctionName(str);
				} else {
					klgExceptionLog.setFunctionName(str.substring(0, 230));
				}
				String strE = e.toString();
				if (strE.length() <= 230) {
					klgExceptionLog.setExceptionText(strE);
				} else {
					klgExceptionLog.setExceptionText(strE.substring(0, 230));
				}
				klgExceptionLogService.save(klgExceptionLog);
            } catch (Exception e1) {
            	logger.error("队列：{}  ACK确认失败");
            }
        }
	}

}
