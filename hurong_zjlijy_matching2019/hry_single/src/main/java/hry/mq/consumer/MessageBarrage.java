package hry.mq.consumer;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.Channel;
import hry.core.thread.ThreadPool;
import hry.redis.common.utils.RedisService;
import hry.social.manage.remote.model.barrage.BarrageMessage;
import hry.util.HttpClientNodeUtil;
import hry.util.MessageRunnable;
import hry.util.NoticeMessage;
import hry.util.NoticeTemplateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author javal
 * @title: MessageUser
 * @projectName hurong_matching2019
 * @description: 消费通知发送消息
 * @date 2019/8/2113:55
 */
public class MessageBarrage implements ChannelAwareMessageListener {
    private Logger log = LoggerFactory.getLogger(MessageBarrage.class);

    private static final String NAME = "toSendBarrage";
    @Resource
    private RedisService redisService;

    @Override
    public void onMessage(Message message, Channel channel) {
        try {
            String msg = new String(message.getBody());
            System.out.println("通知消息MQ信息====>>>" + msg);
            if (null != msg) {
                BarrageMessage barrageMessage = JSON.parseObject(msg, BarrageMessage.class);
                String uuid = barrageMessage.getUuid();
                Long receiveId = barrageMessage.getReceiveId();
                String mapName = "BARRAGE:" + receiveId.longValue();
                redisService.saveMap(mapName,uuid,msg);
                Map<String,Object> map = new HashMap();
                map.put("destination", receiveId);
                map.put("pushData", barrageMessage);
                String pushUrl = NoticeTemplateUtil.getPushUrl(receiveId);
                if (!StringUtils.isEmpty(pushUrl)) {
                    pushUrl += "/barragePush";
                    String post = HttpClientNodeUtil.sendHttpPost(pushUrl, JSON.toJSONString(map));
                    System.out.println("the result to send barrage ： " + post);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
