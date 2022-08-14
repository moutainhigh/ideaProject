package hry.mq.licqb;

import com.rabbitmq.client.Channel;
import hry.core.thread.ThreadPool;
import hry.licqb.remote.RemoteLevelService;
import hry.mq.message.SendCaution;
import hry.util.SpringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;

/**
 * @author zhouming
 * @date 2019/8/20 14:13
 * @Version 1.0
 */
public class MessageUserUpGrade implements ChannelAwareMessageListener {
    private Logger log = LoggerFactory.getLogger(MessageUserUpGrade.class);

    private static final String NAME = "toUserUpGrade";
    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        try {
            String customerId = new String(message.getBody());
            RemoteLevelService remoteLevelService = SpringUtil.getBean("remoteLevelService");
            remoteLevelService.upgradeUserGrade(Long.parseLong(customerId));
            log.info("个人等级升级队列消费成功");
        } catch (Exception e) {
            log.error("队列：{}  消费失败",NAME,e);
            // 再次推送
            // channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
            int messageCount = channel.queueDeclarePassive(NAME).getMessageCount();
            ThreadPool.exe(new SendCaution(NAME,messageCount));
        } finally {
            //正常确认
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        }
    }
}
