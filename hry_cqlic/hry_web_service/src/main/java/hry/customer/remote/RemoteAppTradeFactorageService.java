/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Wu shuiming
 * @version:      V1.0 
 * @Date:        2016年3月28日 下午12:18:00
 */
package hry.customer.remote;

import hry.bean.JsonResult;
import hry.exchange.subscription.model.ExSubscriptionPlan;
import hry.trade.entrust.model.ExOrderInfo;

import java.math.BigDecimal;


/**
 * 
 * @author Wu shuiming
 * @date 2016年7月22日 上午10:46:50
 */
public interface RemoteAppTradeFactorageService {

	/**
	 * 保存成交单佣金
	 * 
	 * @author:    Wu Shuiming
	 * @version:   V1.0 
	 * @date:      2016年8月3日 下午3:01:45
	 */
 	public Boolean saveTradeFactoryge(String orderNum, Integer type);

	/**
	 * 
	 * 保存订单佣金  
	 * 
	 * @author:    Wu Shuiming
	 * @version:   V1.0 
	 * @date:      2016年8月3日 下午3:00:37
	 */
	public Boolean saveTrackOrder(String orderNum, int type);
	/**
	 * 保存认购返佣金额
	 * <p> TODO</p>
	 * @author:         Zeng Hao
	 * @param:    @param plan
	 * @param:    @param customerId
	 * @param:    @param buyTotalNum
	 * @param:    @return
	 * @return: Boolean 
	 * @Date :          2016年12月12日 下午3:20:59   
	 * @throws:
	 */
	public Boolean dealCommissionBySubscription(ExSubscriptionPlan plan, Long customerId,
                                                BigDecimal buyTotalNum, String transactionNum);
	
	public Boolean saveTrade(String orderNum);
	
	/**
	 * 订单回购
	 * @param exOrderInfo
	 * @return
	 */
	public JsonResult buyBackOrder(ExOrderInfo exOrderInfo,BigDecimal buyBackProportion);


}
