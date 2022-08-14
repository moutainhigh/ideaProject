package hry.cm5.mq;


import com.rabbitmq.client.Channel;
import hry.cm5.account.service.Cm5AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;

import javax.annotation.Resource;


public class MessageCmAccount implements ChannelAwareMessageListener {
    private Logger log = LoggerFactory.getLogger(MessageCmAccount.class);

    private static final String NAME = "toAccount";

    @Resource
    private Cm5AccountService cmAccountService;

    @Override
    public void onMessage(Message message, Channel channel) {
        try {
            cmAccountService.accountaddQueue(new String(message.getBody()));
            //正常确认
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
      //      CautionMessage.inspect(channel, NAME, false, message);
        } catch (Exception e) {
            log.error("队列：{}  消费失败",NAME,e);
            try {
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
            } catch (Exception e1) {
                log.error("队列：{}  ACK确认失败",NAME,e1);
            }
        }
    }

}
