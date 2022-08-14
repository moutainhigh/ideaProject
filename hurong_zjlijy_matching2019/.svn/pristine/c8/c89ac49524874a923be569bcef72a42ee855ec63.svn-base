package hry.cm.mq;


import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

import hry.cm.customer.service.CmCustomerService;

/**
 * 矿机产币
 * @author yaozh
 *
 */
public class MessageCmBuyAndcloseMinerUpdateGrade implements MessageListener {
    private Logger log = LoggerFactory.getLogger(MessageCmBuyAndcloseMinerUpdateGrade.class);

    private static final String NAME = "toCmBuyAndcloseMinerUpdateGrade";

    @Resource
    private CmCustomerService cmCustomerService;

    @Override
    public void onMessage(Message message) {
        try {
        	cmCustomerService.buyAndcloseMinerUpdateGrade(new String(message.getBody()));
        } catch (Exception e) {
            log.error("队列：{}  消费失败",NAME,e);
        }
    }

}
