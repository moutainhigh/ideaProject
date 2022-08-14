package hry.financail.mq.producer.service;

import org.apache.log4j.Logger;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MessageProducer {
	private Logger logger = Logger.getLogger(MessageProducer.class);

    @Resource(name="amqpTemplate")  
    private AmqpTemplate amqpTemplate;


    /**
     * 发送账户操作信息
     * @param message
     */
    public void toAccount(Object message)  {  
    	try {  
    		amqpTemplate.convertAndSend("toAccountKey", message);  
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }  
    
    /**
     * 发送委托信息
     * @param message
     */
    public void toTrade(Object message)  {  
    	try {
            amqpTemplate.convertAndSend("toTradeKey", message);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }  
    /**
     * 发送委托信息
     * @param message
     */
    public void toLendRepay(Object message)  {  
    	try {  
    		amqpTemplate.convertAndSend("toLendRepayKey", message);  
		} catch (Exception e) {
			e.printStackTrace();
		}
    } 
    /**
     * 发送委托信息
     * @param message
     */
    public void toLendPing(Object message)  {  
    	try {  
    		amqpTemplate.convertAndSend("toLendPingKey", message);  
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

	/**
	 *卖出部分质押物
	 * @param message
	 */
    public void toSellPartKey(Object message)  {
			try {
				amqpTemplate.convertAndSend("toSellPartKey", message);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	/**
	 * 平仓操作
	 * @param message
	 */
	public void toClosePostionKey(Object message)  {
		try {
			amqpTemplate.convertAndSend("toClosePostionKey", message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 警告消息提醒
	 * @param message
	 * map.put("msgBody",JSON.toJSONString(loanProject));//
	 * map.put("msgKey",msgKey);//消息类型 模板KEY
	 * map.put("msgType","");//是否站内信,短信 1.站内信，2.短信,
	 */
	public void toMessageWarn(Object message)  {
		try {
			amqpTemplate.convertAndSend("toMessageWarnKey", message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 自动审核消息处理
	 * @param message
	 */
	public void toExamine(Object message)  {
		try {
			amqpTemplate.convertAndSend("toExamineKey", message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
