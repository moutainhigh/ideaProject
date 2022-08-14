package hry.mq.licqb;

import com.rabbitmq.client.Channel;
import hry.licqb.remote.RemoteAwardService;
import hry.util.SpringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;

import java.util.Date;

/**
 * @author zhouming
 * @date 2020/9/9 14:32
 * @Version 1.0
 */
public class MessageUpdateOutInfo implements ChannelAwareMessageListener {
    private Logger log = LoggerFactory.getLogger(MessageUpdateOutInfo.class);
    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        String jsonMap = new String(message.getBody());
        RemoteAwardService remoteAwardService = SpringUtil.getBean("remoteAwardService");
        remoteAwardService.mqUpdateOutInfo(jsonMap);
        log.info(new Date()+"修改OutInfo额度进入mq");
    }
}
