package hry.mq.producer.service;

import com.alibaba.fastjson.JSON;
import hry.cmson.dto.AccountaddTrade;
import hry.redis.common.utils.RedisService;
import org.apache.log4j.Logger;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

@Service
public class CmSonMessageProducer {
	private Logger logger = Logger.getLogger(CmSonMessageProducer.class);

    @Resource(name="amqpTemplate")
    private AmqpTemplate rabbitTemplate;

    @Resource
	private RedisService redisService;

	/**
	 * 发送Cm账户操作信息
	 * @param message
	 */
	public void toCmSonAccount(Object message)  {
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
			rabbitTemplate.convertAndSend("toCmSonAccountKey", message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
