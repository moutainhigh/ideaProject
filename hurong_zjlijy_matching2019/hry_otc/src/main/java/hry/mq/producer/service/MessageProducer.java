package hry.mq.producer.service;

import com.alibaba.fastjson.JSON;
import hry.redis.common.utils.RedisService;
import hry.util.dto.AccountaddTrade;
import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

@Service
public class MessageProducer {
	private Logger logger = Logger.getLogger(MessageProducer.class);

    @Resource(name="rabbitTemplate")
    private RabbitTemplate rabbitTemplate;

    @Resource
    private RedisService redisService;
    /**
     * 发送主账户操作信息
     * @param message
     */
    public void toAccount(Object message)  {
        List<AccountaddTrade> accountaddlist = JSON.parseArray(message.toString(), AccountaddTrade.class);
        for(AccountaddTrade accountadd:accountaddlist){
            if(accountadd.getMoney().compareTo(new BigDecimal("999999999"))==1
                    ||accountadd.getMoney().compareTo(new BigDecimal("-999999999"))==-1){
                throw new RuntimeException("资金数据太大");
            }
        }
        String isStop = redisService.get("deal:stop");
        if(org.apache.commons.lang3.StringUtils.isNotEmpty(isStop)){
            throw new RuntimeException("资金通道发送堵塞请不要进行资金操作");
        }

        try {
            rabbitTemplate.convertAndSend("toAccountKey", message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 发送主账户操作信息
     * @param message
     */

    public void otcToTrade(Object message)  {
        try {
            rabbitTemplate.convertAndSend("otcToTradeKey", message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void toOtcAccount(Object message)  {
        try {
            rabbitTemplate.convertAndSend("toOtcAccountKey", message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
