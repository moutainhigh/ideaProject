/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Wu Shuiming
 * @version:      V1.0 
 * @Date:        2016年7月6日 下午6:02:18
 */
package hry.customer.agents.service;

import hry.core.mvc.service.base.BaseService;
import hry.customer.agents.model.AppTradeFactorage;
import hry.exchange.subscription.model.ExSubscriptionPlan;

import java.math.BigDecimal;

/**
 * <p> TODO</p>
 * @author:         Wu Shuiming
 * @Date :          2016年7月6日 下午6:02:18 
 */
public interface AppTradeFactorageService extends BaseService<AppTradeFactorage, Long> {
  
	
	/**
	 * 
	 * 交易订单 在保存的时候 会计算所交手续费的多少并保存起来 integer type参数是2 表示以第二类费率计算 
	 * 
	 * 
	 * @author:    Wu Shuiming
	 * @version:   V1.0 
	 * @date:      2016年7月22日 上午9:32:30
	 */
 	public Boolean dealCommission(String entrustNum, Integer type);

	/**
	 * 
	 * 根据用户id返回用户的所有父
	 * 
	 * @author:    Wu Shuiming
	 * @version:   V1.0 
	 * @date:      2016年7月23日 下午4:47:10
	 */
	public AppTradeFactorage findFactorageByCustromerId(Long id);

	/**
	 * 通过订单保存佣金
	 * 
	 * @author:    Wu Shuiming
	 * @version:   V1.0 
	 * @date:      2016年8月3日 下午2:37:50
	 */
	public Boolean dealCommissionByTraction(String tractionNum, Integer type);
	/**
	 * 保存认购推荐人佣金
	 * <p> TODO</p>
	 * @author:         Zeng Hao
	 * @param:    @param tractionNum
	 * @param:    @param type
	 * @param:    @return
	 * @return: Boolean 
	 * @Date :          2016年12月12日 下午3:01:14   
	 * @throws:
	 */
	public Boolean dealCommissionBySubscription(ExSubscriptionPlan plan, Long customerId, BigDecimal buyTotalNum, String transactionNum);
	
	

	/**
	 * 
	 * 根据用户id返回用户的所有父
	 * 
	 * @author:    Wu Shuiming
	 * @version:   V1.0 
	 * @date:      2016年7月23日 下午4:47:10
	 */
	public AppTradeFactorage findFactorageByCustromerId1(Long id, String fixPriceCoinCode);
}                                                                                             
