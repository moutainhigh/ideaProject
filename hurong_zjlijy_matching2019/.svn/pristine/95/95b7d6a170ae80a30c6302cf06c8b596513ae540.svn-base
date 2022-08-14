/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Gao Mimi
 * @version:      V1.0 
 * @Date:        2016年5月3日 上午11:00:12
 */
package hry.trade.MQmanager;


import hry.trade.mq.service.MessageProducer;
import hry.trade.redis.model.Accountadd;
import hry.trade.redis.model.EntrustTrade;
import hry.util.serialize.Mapper;
import hry.util.sys.ContextUtil;

import java.util.List;

/**
 * <p> TODO</p>
 * @author:         Gao Mimi 
 * @Date :          2016年5月3日 上午11:00:12 
 */
public class MQEnter {
	/**
	 * 
	 * <p>
	 * 放进匹配队列
	 * </p>
	 * 
	 * @author: Gao Mimi
	 * @param: @param exEntrust
	 * @return: void
	 * @Date : 2016年4月22日 下午4:44:47
	 * @throws:
	 */
	public static void pushExEntrustMQ(EntrustTrade exEntrust) {
		MessageProducer messageProducer =(MessageProducer)ContextUtil.getBean("messageProducer");
		String exentrust=Mapper.objectToJson(exEntrust);
		messageProducer.toTrade(exentrust);
	}
	/**
	 * 放进资金处理队列
	 * @param exEntrust
	 */
	public static void pushDealFundMQ(List<Accountadd> aadds) {
		
		MessageProducer messageProducer =(MessageProducer)ContextUtil.getBean("messageProducer");
	/*	for(Accountadd accountadd:aadds){
			accountadd.setRemarks("11");
		}*/
		String accountadds=Mapper.objectToJson(aadds);
		messageProducer.toAccount(accountadds);
	}

}
