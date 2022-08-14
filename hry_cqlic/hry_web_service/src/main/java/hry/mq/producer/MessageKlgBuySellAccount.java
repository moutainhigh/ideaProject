package hry.mq.producer;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.Channel;

import hry.klg.buysellaccount.service.KlgBuySellAccountService;
import hry.klg.log.model.KlgExceptionLog;
import hry.klg.log.service.KlgExceptionLogService;
import hry.klg.remote.model.BuyPayMessage;
import hry.klg.remote.model.ToBuySellAccountMessage;
import hry.util.SpringUtil;
import hry.util.sys.ContextUtil;

public class MessageKlgBuySellAccount implements ChannelAwareMessageListener{
	private Logger logger = Logger.getLogger(MessageKlgBuySellAccount.class);
	private static final String NAME = "toBuySellAccount";
	@Override
	public void onMessage(Message message, Channel channel) throws Exception {
		String str = new String(message.getBody());
		logger.info("@@@receives toBuySellAccount rabbitmq message======="+str);
		
		try {
			List<ToBuySellAccountMessage> list = JSON.parseArray(new String(message.getBody()), ToBuySellAccountMessage.class);
			KlgBuySellAccountService klgBuySellAccountService = (KlgBuySellAccountService) ContextUtil.getBean("klgBuySellAccountService");
			klgBuySellAccountService.toBuySellAccountMessage(list);
            CautionMessageKlg.inspect(channel, NAME, false, message);
        } catch (Exception e) {
        	logger.error("队列：{}  消费失败");
        	e.printStackTrace();
        	/***消息异常，修改订单***/
        	channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            try {
            	KlgExceptionLogService klgExceptionLogService = SpringUtil.getBean("klgExceptionLogService");
				// 插入异常日志
				KlgExceptionLog klgExceptionLog = new KlgExceptionLog();
				str = "MessageKlgBuySellAccount消息:"+str;
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
                //channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
                //int messageCount = channel.queueDeclarePassive(NAME).getMessageCount();
                //ThreadPool.exe(new SendCaution(NAME,messageCount));
            } catch (Exception e1) {
            	logger.error("队列：{}  ACK确认失败");
            }
        }
	}

}
