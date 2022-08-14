package hry.trade.mq;

import com.rabbitmq.client.Channel;
import hry.core.thread.ThreadPool;
import hry.trade.entrust.service.TradeService;
import hry.trade.mq.task.SendCaution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;

import javax.annotation.Resource;

public class MessageAccount implements ChannelAwareMessageListener {
    private Logger log = LoggerFactory.getLogger(MessageAccount.class);

    private static final String NAME = "toAccount";

    @Resource
    private TradeService tradeService;

    @Override
    public void onMessage(Message message, Channel channel) {
        try {
            tradeService.accountaddQueue(new String(message.getBody()));
            CautionMessage.inspect(channel, NAME, false, message);
        } catch (Exception e) {
            log.error("队列：{}  消费失败",NAME,e);
            try {
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
                int messageCount = channel.queueDeclarePassive(NAME).getMessageCount();
                ThreadPool.exe(new SendCaution(NAME,messageCount));
            } catch (Exception e1) {
                log.error("队列：{}  ACK确认失败",NAME,e1);
            }
        }
    }

}
