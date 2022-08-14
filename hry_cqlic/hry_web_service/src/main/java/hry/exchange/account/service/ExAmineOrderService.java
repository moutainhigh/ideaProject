/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Wu Shuiming
 * @version:      V1.0 
 * @Date:        2016年4月1日 下午7:25:45
 */
package hry.exchange.account.service;

import hry.exchange.transaction.model.ExDmTransaction;

import java.util.List;

/**
 * <p>
 * TODO
 * </p>
 * 
 * @author: Wu Shuiming
 * @Date : 2016年4月1日 下午7:25:45
 */
public interface ExAmineOrderService {

	// 审核通过订单
	public String pasePutOrder(Long id);
	
	/**
	 * <p> 充币通过</p>
	 * @author:         Yuan Zhicheng
	 * @param:    @param exTxs
	 * @param:    @return
	 * @return: String 
	 * @Date :          2016年11月16日 上午12:17:44   
	 * @throws:
	 */
	public String rechargeCoin(ExDmTransaction exTxs);

	/**
	 * 
	 * <p> 客户所有提币申请</p>
	 * @author:         Gao Mimi
	 * @param:    @param customerId
	 * @param:    @return
	 * @return: List<ExDmTransaction> 
	 * @Date :          2016年11月1日 下午1:38:56   
	 * @throws:
	 */
	 public List<ExDmTransaction> getlistByapply(Long customerId, String currencyType, String website);
	/**
	 * 
	 * <p> 海香币转成钱</p>
	 * @author:         Gao Mimi
	 * @param:    @param id
	 * @param:    @return
	 * @return: String 
	 * @Date :          2016年11月10日 下午7:55:36   
	 * @throws:
	 */
   public String pasePutOrderToAppAccount(Long id);
	/**
	 * 
	 * <p>海香币驳回</p>
	 * @author:         Gao Mimi
	 * @param:    @param id
	 * @param:    @return
	 * @return: String 
	 * @Date :          2016年11月11日 下午4:39:47   
	 * @throws:
	 */
	public String paseStopeOrderToAppAccount(Long id);
	
	
	/**
	 * 驳回一个订单(ICO)
	 */
	public String paseRefuseOrder(Long id);

}
