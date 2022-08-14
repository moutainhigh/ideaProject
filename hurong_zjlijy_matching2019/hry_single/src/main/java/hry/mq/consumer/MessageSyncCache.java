package hry.mq.consumer;

import com.rabbitmq.client.Channel;
import hry.social.miningreward.service.SocialMiningGatherRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;

import javax.annotation.Resource;

public class MessageSyncCache implements ChannelAwareMessageListener {
    private Logger log = LoggerFactory.getLogger(MessageSyncCache.class);

    private static final String NAME = "syncCacheReward";

    @Resource
    private SocialMiningGatherRecordService socialMiningGatherRecordService;

    @Override
    public void onMessage(Message message, Channel channel) {
        try {
            socialMiningGatherRecordService.syncCacheReward(new String(message.getBody()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
