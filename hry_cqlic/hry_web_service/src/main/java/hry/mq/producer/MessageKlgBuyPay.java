package hry.mq.producer;

import org.apache.log4j.Logger;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.Channel;

import hry.core.thread.ThreadPool;
import hry.klg.remote.RemoteKlgBuySellTransactionService;
import hry.klg.remote.model.BuyPayMessage;
import hry.mq.producer.task.SendCaution;
import hry.util.sys.ContextUtil;

public class MessageKlgBuyPay implements ChannelAwareMessageListener{
	private Logger logger = Logger.getLogger(MessageKlgBuyPay.class);
	private static final String NAME = "toKlgBuyPay";
	@Override
	public void onMessage(Message message, Channel channel) {
		String str = new String(message.getBody());
		logger.info("@@@receives KlgBuyPay rabbitmq message======="+str);
		
		try {
			BuyPayMessage buyPayMessage = JSON.parseObject(str, BuyPayMessage.class);
			RemoteKlgBuySellTransactionService remoteKlgBuySellTransactionService = (RemoteKlgBuySellTransactionService) ContextUtil.getBean("remoteKlgBuySellTransactionService");
			remoteKlgBuySellTransactionService.buyPayMessage(buyPayMessage);
            CautionMessage.inspect(channel, NAME, false, message);
        } catch (Exception e) {
        	logger.error("队列：{}  消费失败");
        	e.printStackTrace();
            try {
            	/***消息异常，修改订单***/
            	/***消息异常，修改订单***/
            	/***消息异常，修改订单***/
            	/***消息异常，修改订单***/
            	/***消息异常，修改订单***/
            	/***消息异常，修改订单***/
            	channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
                //channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
                //int messageCount = channel.queueDeclarePassive(NAME).getMessageCount();
                //ThreadPool.exe(new SendCaution(NAME,messageCount));
            } catch (Exception e1) {
            	logger.error("队列：{}  ACK确认失败");
            }
        }
	}

}
