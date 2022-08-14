package hry.cm2.mq;


import hry.cm2.customer.service.Cm2CustomerService;
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
public class MessageCmBuyAndcloseMinerUpdateGrade implements MessageListener {
    private Logger log = LoggerFactory.getLogger(MessageCmBuyAndcloseMinerUpdateGrade.class);

    private static final String NAME = "toCmBuyAndcloseMinerUpdateGrade";

    @Resource
    private Cm2CustomerService cmCustomerService;

    @Override
    public void onMessage(Message message) {
        try {
        	cmCustomerService.buyAndcloseMinerUpdateGrade(new String(message.getBody()));
        } catch (Exception e) {
            log.error("队列：{}  消费失败",NAME,e);
        }
    }

}
