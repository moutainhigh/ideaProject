package hry.cm.mq;


import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;

import com.rabbitmq.client.Channel;

import hry.cm.account.service.CmAccountService;


public class MessageCmAccount implements ChannelAwareMessageListener {
    private Logger log = LoggerFactory.getLogger(MessageCmAccount.class);

    private static final String NAME = "toAccount";

    @Resource
    private CmAccountService cmAccountService;

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
