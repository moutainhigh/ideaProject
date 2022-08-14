package hry.mq.consumer;

import com.rabbitmq.client.Channel;
import hry.core.thread.ThreadPool;
import hry.mq.message.SendCaution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;

public class MessageMiningReward implements ChannelAwareMessageListener {
    private Logger log = LoggerFactory.getLogger(MessageMiningReward.class);

    private static final String NAME = "toGather";

    @Override
    public void onMessage(Message message, Channel channel) {
        try {
            //socialMiningGatherRecordService.miningReward(new String(message.getBody()));
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
