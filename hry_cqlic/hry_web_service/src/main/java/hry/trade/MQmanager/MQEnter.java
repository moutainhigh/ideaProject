/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Gao Mimi
 * @version:      V1.0 
 * @Date:        2016年5月3日 上午11:00:12
 */
package hry.trade.MQmanager;


import hry.trade.entrust.model.ExEntrust;
import hry.util.serialize.Mapper;

/**
 * <p> TODO</p>
 * @author:         Gao Mimi 
 * @Date :          2016年5月3日 上午11:00:12 
 */
public class MQEnter {
	/**
	 * 
	 * <p>
	 * 委托订单进入mq队列
	 * </p>
	 * 
	 * @author: Gao Mimi
	 * @param: @param exEntrust
	 * @return: void
	 * @Date : 2016年4月22日 下午4:44:47
	 * @throws:
	 */
	public static void pushExEntrustMQ(ExEntrust exEntrust) {
	//	System.out.println("委托币种："+exEntrust.getCoinCode());
		String exentrust=Mapper.objectToJson(exEntrust);
	//	System.out.println("委托单："+exentrust);
	}

}
