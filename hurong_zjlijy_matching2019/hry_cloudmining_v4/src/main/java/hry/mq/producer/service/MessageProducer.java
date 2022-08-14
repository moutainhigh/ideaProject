package hry.mq.producer.service;

import com.alibaba.fastjson.JSON;
import hry.redis.common.utils.RedisService;
import hry.trade.redis.model.Accountadd;
import hry.util.SpringUtil;
import org.apache.log4j.Logger;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

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
		List<Accountadd> accountaddlist = JSON.parseArray(message.toString(), Accountadd.class);
		for(Accountadd accountadd:accountaddlist){
			if(accountadd.getMoney().compareTo(new BigDecimal("999999999"))==1
					||accountadd.getMoney().compareTo(new BigDecimal("-999999999"))==-1){
				throw new RuntimeException("资金数据太大");
			}
		}
		RedisService redisService = SpringUtil.getBean("redisService");
		String isStop = redisService.get("deal:stop");
		if(org.apache.commons.lang3.StringUtils.isNotEmpty(isStop)){
			throw new RuntimeException("资金通道发送堵塞请不要进行资金操作");
		}

		try {
			amqpTemplate.convertAndSend("toAccountKey", message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
    
    /**
     * 发送矿机产币消息，按天
     * @param message
     */
    public void toMinerCoinageByDay(Object message)  {  
    	try {  
    		amqpTemplate.convertAndSend("to4MinerCoinageByDayKey", message);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    } 
    
    /**
     * 发送更新用户等级信息消息
     * @param message
     */
    public void toCmUpdateGrade(Object message)  {  
    	try {  
    		amqpTemplate.convertAndSend("toCm4UpdateGradeKey", message);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    } 
    
    /**
     * 发送购买矿机 矿机到期 更新用户等级信息消息
     * @param message
     */
    public void toCmBuyAndcloseMinerUpdateGrade(Object message)  {  
    	try {  
    		amqpTemplate.convertAndSend("toCm4BuyAndcloseMinerUpdateGradeKey", message);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    } 
    
    /**
     * 发送矿工动态收益
     * @param message
     */
    public void toCmProfit1Key(Object message)  {  
    	try {  
    		amqpTemplate.convertAndSend("toCm4Profit1Key", message);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    } 

    
    /**
     * 推送产币消息到果树
     * @param message
     */
    public void toSocialRewardKey(Object message)  {  
    	try {  
    		amqpTemplate.convertAndSend("toSocialRewardKey", message);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    } 
    
}
