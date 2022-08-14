package hry.mq.message;

import org.apache.log4j.Logger;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SocialMessageProducer {
    private Logger logger = Logger.getLogger(SocialMessageProducer.class);

    @Resource(name = "amqpTemplate")
    private AmqpTemplate amqpTemplate;


    /**
     * 发送账户操作信息
     *
     * @param message
     */
    public void toAccount(Object message) {
        try {
            amqpTemplate.convertAndSend("toAccountKey", message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送果子采集信息
     *
     * @param message
     */
    public void toGather(Object message) {
        try {
            amqpTemplate.convertAndSend("toGatherKey", message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送位置存储消息
     *
     * @param message
     */
    public void toShakeSite(Object message) {
        try {
            amqpTemplate.convertAndSend("toShakeSiteKey", message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
