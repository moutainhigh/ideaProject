package hry.mq.consumer;

import com.rabbitmq.client.Channel;
import hry.core.thread.ThreadPool;
import hry.util.MessageRunnable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;

/**
 * @author javal
 * @title: MessageUser
 * @projectName hurong_matching2019
 * @description: 消费通知发送消息
 * @date 2019/8/2113:55
 */
public class MessageNotice implements ChannelAwareMessageListener {
    private Logger log = LoggerFactory.getLogger(MessageNotice.class);

    private static final String NAME = "toSendMsg";

    @Override
    public void onMessage(Message message, Channel channel) {
        try {
            ThreadPool.exe(new MessageRunnable(new String(message.getBody())));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
