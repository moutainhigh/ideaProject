/**
 * Copyright:   
 * @author:      yaozh
 * @version:     V1.0 
 * @Date:        2019-07-31 20:25:57 
 */
package hry.cm.profitone.service;

import java.math.BigDecimal;

import hry.cm.profitone.model.CmProfitOne;
import hry.core.mvc.service.base.BaseService;



/**
 * <p> CmProfitOneService </p>
 * @author:         yaozh
 * @Date :          2019-07-31 20:25:57 
 */
public interface CmProfitOneService  extends BaseService<CmProfitOne, Long>{
	
	/**
	 * 发矿工动态收益
	 * @param messageStr
	 */
	public void profitOne(String messageStr);
	
	
	/**
	 * 查询直推用户今天动态收益总和
	 * @param customerId
	 * @return
	 */
	public BigDecimal getTeamProfitSumByCustomerId(Long customerId,Integer type);

	/**
	 * 查询用户今天动态收益总和
	 * @param customerId
	 * @return
	 */
	public BigDecimal getProfitSumByCustomerId(Long customerId);
	
	/**
	 * 查询用户今天烧伤收益总和
	 * @param customerId
	 * @return
	 */
	public BigDecimal getProfit3SumByCustomerId(Long customerId);
	
	/**
	 * 查询用户动态收益总和
	 * @param customerId
	 * @return
	 */
	public BigDecimal getProfitAllByCustomerId(Long customerId);

}
