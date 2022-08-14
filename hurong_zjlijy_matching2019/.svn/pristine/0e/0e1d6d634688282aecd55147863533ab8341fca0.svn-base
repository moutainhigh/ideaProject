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
     * 给社交发送积分消息
     * @param message
     */
    public void toSocialReward(Object message)  {  
    	try {
            amqpTemplate.convertAndSend("toSocialRewardKey", message);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

	/**
	 * 用户开通端口返佣
	 * @param message
	 */
	public void distributeRecommendRewards(Object message) {
		try {
			amqpTemplate.convertAndSend("distributeRecommendRewardsKey", message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
