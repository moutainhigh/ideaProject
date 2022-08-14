package hry.cm4.mq;


import hry.cm4.customerminer.service.Cm4CustomerMinerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

import javax.annotation.Resource;

/**
 * 矿机产币
 * @author yaozh
 *
 */
public class MessageCmMinerCoinageByDay implements MessageListener {
    private Logger log = LoggerFactory.getLogger(MessageCmMinerCoinageByDay.class);

    private static final String NAME = "toMinerCoinageByDay";

    @Resource
    private Cm4CustomerMinerService cmCustomerMinerService;

    @Override
    public void onMessage(Message message) {
        try {
        	cmCustomerMinerService.minerCoinageByDay(new String(message.getBody()));
        } catch (Exception e) {
            log.error("队列：{}  消费失败",NAME,e);
        }
    }

}
