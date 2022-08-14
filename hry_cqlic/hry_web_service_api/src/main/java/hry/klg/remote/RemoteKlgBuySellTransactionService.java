package hry.klg.remote;

import java.util.Map;

import hry.bean.JsonResult;
import hry.klg.remote.model.BuyPayMessage;

/**
 * 快乐购 买单 买单service
 * @author lenovo
 *
 */
public interface RemoteKlgBuySellTransactionService {
	
	/**
	 * 买家补交剩余金额
	 * @param params
	 * @return
	 */
	JsonResult payBackBuyTransaction(Map<String, String> params);
	
	/**
	 * 补缴尾款后，处理消息方法
	 * @param buyPayMessage
	 */
	void buyPayMessage(BuyPayMessage buyPayMessage);
	
	/**
	 * 抢单
	 * @param params
	 * @return
	 */
	JsonResult robBuyTransaction(Map<String, String> params);
	
	/**
	 * 抢单消息处理
	 * @param buyPayMessage
	 */
	void robBuyTransactionMessage(BuyPayMessage buyPayMessage);

}
