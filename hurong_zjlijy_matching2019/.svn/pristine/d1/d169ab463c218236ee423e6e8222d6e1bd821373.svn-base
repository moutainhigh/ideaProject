package hry.mq.consumer;

import com.rabbitmq.client.Channel;
import hry.core.thread.ThreadPool;
import hry.mq.message.SendCaution;
import hry.social.miningreward.service.SocialMiningGatherRecordService;
import hry.social.user.service.SocialUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;

import javax.annotation.Resource;

/**
 * 消费用户处理（注册）消息
 *
 * @author javal
 * @title: MessageUser
 * @projectName hurong_matching2019
 * @description: TODO
 * @date 2019/8/610:20
 */
public class MessageUser implements ChannelAwareMessageListener {
    private Logger log = LoggerFactory.getLogger(MessageUser.class);

    private static final String NAME = "toUserDeal";

    @Resource
    private SocialUserService socialUserService;

    @Override
    public void onMessage(Message message, Channel channel) {
        try {
            socialUserService.registDeal(new String(message.getBody()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
