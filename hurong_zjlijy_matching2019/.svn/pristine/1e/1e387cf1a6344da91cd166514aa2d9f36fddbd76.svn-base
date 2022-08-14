package hry.mq.consumer;

import com.rabbitmq.client.Channel;
import hry.core.thread.ThreadPool;
import hry.util.AccountRunnable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;

/**
 * 消费用户处理（注册）消息
 *
 * @author javal
 * @title: MessageUser
 * @projectName hurong_matching2019
 * @description: TODO
 * @date 2019/8/610:20
 */
public class MessageSyncAccount implements ChannelAwareMessageListener {
    private Logger log = LoggerFactory.getLogger(MessageSyncAccount.class);

    private static final String NAME = "syncAccount";

    @Override
    public void onMessage(Message message, Channel channel) {
        try {
            ThreadPool.exe(new AccountRunnable(new String(message.getBody())));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
