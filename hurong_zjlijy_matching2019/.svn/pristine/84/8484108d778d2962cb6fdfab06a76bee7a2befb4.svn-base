package hry.trade.mq.service;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageProducer {
	private Logger logger = Logger.getLogger(MessageProducer.class);

    @Resource(name="amqpTemplate")
    private AmqpTemplate amqpTemplate;


    public void toTrade(Object message)  {
    	try {
    		amqpTemplate.convertAndSend("toTradeKey", message);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

    public void toAccount(Object message)  {
    	try {
    		amqpTemplate.convertAndSend("toAccountKey", message);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    public void reidsToRedisLog(Object message)  {
    	try {
    		amqpTemplate.convertAndSend("reidsToRedisLogKey", message);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    public void reidsToMysql(Object message)  {
    	try {
    		amqpTemplate.convertAndSend("reidsToMysqlKey", message);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    public void mimingCustomerRobot(Object message)  {
    	try {
    		amqpTemplate.convertAndSend("mimingCustomerRobotKey", message);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }

	/**
	 * 发送账户同步消息
	 *
	 * @param message
	 */
	public void syncAccount(Object message) {
		try {
			amqpTemplate.convertAndSend("syncAccountKey", message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
