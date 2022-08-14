/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Wu Shuiming
 * @version:      V1.0 
 * @Date:        2016年7月6日 下午6:02:18
 */
package hry.customer.agents.service;

import hry.account.fund.model.AppTransaction;
import hry.core.mvc.service.base.BaseService;
import hry.customer.agents.model.AppAgentsCustromer;
import hry.customer.agents.model.CommissionDetail;
import hry.customer.user.model.AppCustomer;
import hry.trade.entrust.model.ExOrderInfo;

import java.math.BigDecimal;

/**
 * <p> TODO</p>
 * @author:         Wu Shuiming
 * @Date :          2016年7月6日 下午6:02:18 
 */
public interface CommissionDetailService extends BaseService<CommissionDetail, Long> {
  
	/**
	 * 
	 * 通过订单保存佣金明细
	 * 
	 * @author:    Wu Shuiming
	 * @version:   V1.0 
	 * @date:      2016年7月23日 下午3:01:04
	 * 
	 */
	public Boolean commissionDetailByOrder(String orderNum);
	
	/**
	 * 根据用户的用户名返回用户的佣金总数
	 * 
	 * @author:    Wu Shuiming
	 * @version:   V1.0 
	 * @date:      2016年7月25日 上午11:27:10
	 */
	public BigDecimal findMoneyByCustromerName(String custromerName);

	/**
	 * 
	 * 订单中保存一条佣金明细
	 * 
	 * @author:    Wu Shuiming
	 * @version:   V1.0 
	 * @date:      2016年8月3日 下午2:41:15
	 */
	public void saveCommissionDetail(AppTransaction transaction,
                                     AppAgentsCustromer agentsCustromer, Integer rank);

	
	/**
	 * 通过一个成交单保存一个佣金明细
	 * 
	 * @author:    Wu Shuiming
	 * @version:   V1.0 
	 * @date:      2016年8月30日 上午11:53:25
	 */
	public void saveCommissionDetailForOrder(ExOrderInfo exOrderInfo,
                                             AppAgentsCustromer agentsCustromer, Integer rank);
	
	/**
	 * 认购成功保存佣金明细
	 * 
	 * @author:    zenghao
	 * @version:   V1.0 
	 * @date:      2016年8月30日 上午11:53:25
	 */
	public void saveCommissionDetailSubscription(AppCustomer customer, AppAgentsCustromer agentsCustromerOne, BigDecimal srationMoney, String transactionNum, Integer agentsRank, BigDecimal sratio);
	
	
}                                                                                             
