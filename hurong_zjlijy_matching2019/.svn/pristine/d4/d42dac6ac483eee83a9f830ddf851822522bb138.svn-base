/**
 * Copyright:    
 * @author:      yaozh
 * @version:     V1.0 
 * @Date:        2019-07-31 11:12:16 
 */
package hry.cm.customerminer.dao;

import hry.core.mvc.dao.base.BaseDao;

import java.math.BigDecimal;

import org.apache.ibatis.annotations.Param;

import hry.cm.customerminer.model.CmCustomerMinerProfit;


/**
 * <p> CmCustomerMinerProfitDao </p>
 * @author:         yaozh
 * @Date :          2019-07-31 11:12:16  
 */
public interface CmCustomerMinerProfitDao extends  BaseDao<CmCustomerMinerProfit, Long> {
	
	/**
	 * 查询直推用户今天静态收益总数
	 * @param customerId
	 * @return
	 */
	public BigDecimal getTeamProfitSumByCustomerId(@Param("customerId") Long customerId);
	/**
	 * 查询用户今天矿机产币总数
	 * @param customerId
	 * @return
	 */
	public BigDecimal getProfitSumByCustomerId(@Param("customerId") Long customerId);

}
