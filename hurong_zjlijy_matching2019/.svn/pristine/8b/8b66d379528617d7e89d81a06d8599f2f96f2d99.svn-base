/**
 * Copyright:   
 * @author:      yaozh
 * @version:     V1.0 
 * @Date:        2019-08-09 11:44:39 
 */
package hry.cm.profittwo.service;

import java.math.BigDecimal;

import hry.cm.profittwo.model.CmProfitTwo;
import hry.core.mvc.service.base.BaseService;



/**
 * <p> CmProfitTwoService </p>
 * @author:         yaozh
 * @Date :          2019-08-09 11:44:39 
 */
public interface CmProfitTwoService  extends BaseService<CmProfitTwo, Long>{
	
	/**
	 * 发放矿场收益定时任务
	 */
	void grantProfit();
	
	/**
	 * 更新未发放收益为已发放
	 * @param customerId
	 */
	void updateStatus(Long customerId);
	
	/**
	 * 用户今天矿场收益的总数 
	 * @param customerId
	 * @return
	 */
	public BigDecimal getTodayProfitSum(Long customerId);

	/**
	 * 用户矿场总收益的总数 
	 * @param customerId
	 * @return
	 */
	public BigDecimal getTotalProfitSum(Long customerId);
	

}
