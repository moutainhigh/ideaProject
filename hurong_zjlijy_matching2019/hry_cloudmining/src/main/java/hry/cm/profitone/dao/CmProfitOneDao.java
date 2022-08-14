/**
 * Copyright:    
 * @author:      yaozh
 * @version:     V1.0 
 * @Date:        2019-07-31 20:25:56 
 */
package hry.cm.profitone.dao;

import hry.core.mvc.dao.base.BaseDao;

import java.math.BigDecimal;

import org.apache.ibatis.annotations.Param;

import hry.cm.profitone.model.CmProfitOne;


/**
 * <p> CmProfitOneDao </p>
 * @author:         yaozh
 * @Date :          2019-07-31 20:25:56  
 */
public interface CmProfitOneDao extends  BaseDao<CmProfitOne, Long> {
	
	/**
	 * 查询直推用户今天动态收益总和
	 * @param customerId
	 * @return
	 */
	public BigDecimal getTeamProfitSumByCustomerId(@Param("customerId") Long customerId,@Param("type")Integer type);
	
	/**
	 * 查询用户今天动态收益总和
	 * @param customerId
	 * @return
	 */
	public BigDecimal getProfitSumByCustomerId(@Param("customerId") Long customerId);
	
	/**
	 * 查询用户今天烧伤收益总和
	 * @param customerId
	 * @return
	 */
	public BigDecimal getProfit3SumByCustomerId(@Param("customerId") Long customerId);
	
	/**
	 * 查询用户动态收益总和
	 * @param customerId
	 * @return
	 */
	public BigDecimal getProfitAllByCustomerId(@Param("customerId") Long customerId);

}
