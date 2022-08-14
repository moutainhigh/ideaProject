package hry.mq.producer;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;

import hry.util.sys.SpringContextUtil;
import hry.mq.producer.service.MessageProducer;
import hry.trade.redis.model.Accountadd;

public class MessageAccountUtil {
	
	private static final Logger log = Logger.getLogger(MessageAccountUtil.class);
	
	/**
	 * 充币方法
	 * @param exdigaccountId  充币账户
	 * @param count  充币数量  
	 * @param transactionNum  订单号 
	 */
	public static void addCoin(Long exdigaccountId,BigDecimal count,String transactionNum) {
		
		try {
			MessageProducer messageProducer = (MessageProducer) SpringContextUtil.getBean("messageProducer");
			
			Accountadd accountadd = new Accountadd();
			accountadd.setAccountId(exdigaccountId);
			accountadd.setMoney(count);
			accountadd.setMonteyType(1);
			accountadd.setAcccountType(1);
			accountadd.setRemarks(31);
			accountadd.setTransactionNum(transactionNum);
			
			List<Accountadd> list= new ArrayList<Accountadd>();
			list.add(accountadd);
			messageProducer.toAccount(JSON.toJSONString(list));
		} catch (Exception e) {
			log.error("充币失败");
		}
	
		
	}

}
