package hry.admin.mq.producer.service;

import hry.bean.JsonResult;
import hry.redis.common.utils.RedisService;
import hry.util.SpringUtil;
import org.apache.log4j.Logger;
import org.springframework.amqp.core.AmqpTemplate;

import com.alibaba.fastjson.JSON;

import hry.trade.redis.model.Accountadd;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

public class MessageProducer {
	private Logger logger = Logger.getLogger(MessageProducer.class);

    @Resource(name="amqpTemplate")  
    private AmqpTemplate amqpTemplate;  


    /**
     * 发送账户操作信息
     * @param message
     */
    public void toAccount(Object message)  {  
    	List<Accountadd> accountaddlist = JSON.parseArray(message.toString(), Accountadd.class);
    	for(Accountadd accountadd:accountaddlist){
    		if(accountadd.getMoney().compareTo(new BigDecimal("999999999"))==1
    				||accountadd.getMoney().compareTo(new BigDecimal("-999999999"))==-1){
    			      throw new RuntimeException("资金数据太大");
    		}
    	}
		/*RedisService redisService = SpringUtil.getBean("redisService");
		String isStop = redisService.get("deal:stop");
		if(org.apache.commons.lang3.StringUtils.isNotEmpty(isStop)){
			throw new RuntimeException("资金通道发送堵塞请不要进行资金操作");
		}*/

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
	 * 平台币 {"money":"","type":"type"}
	 * @param message
	 */
	public void toPlatformCurrency(Object message)  {
		try {
			amqpTemplate.convertAndSend("toPlatformCurrencyKey", message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
     * 发送快乐购买方付款消息
     * @param message
     */
    public void toKlgBuyPay(Object message)  {  
    	try {
            amqpTemplate.convertAndSend("toKlgBuyPayKey", message);
		} catch (Exception e) {
			e.printStackTrace();
		}
    } 
    
    /**
     * 发送清理订单消息
     * @param message
     */
    public void toBuySellAccount(Object message)  {  
    	try {
    		amqpTemplate.convertAndSend("toBuySellAccountKey", message);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    } 
    
  //消息
  	public void toMessageWarn(Object message)  {
  		try {
  			amqpTemplate.convertAndSend("toMessageWarnKey", message);
  		} catch (Exception e) {
  			e.printStackTrace();
  		}
  	}
  	
  	/**
	 * klg发奖励toAwardKey
	 */
	public void toAward(Object message)  {
		try {
			amqpTemplate.convertAndSend("toAwardKey", message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
