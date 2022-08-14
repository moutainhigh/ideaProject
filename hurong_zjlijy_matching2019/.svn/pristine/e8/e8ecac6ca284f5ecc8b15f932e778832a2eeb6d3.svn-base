/**
 * Copyright:   
 * @author:      yaozh
 * @version:     V1.0 
 * @Date:        2019-07-31 11:12:16 
 */
package hry.cm.customerminer.service;

import java.math.BigDecimal;

import org.apache.ibatis.annotations.Param;

import hry.cm.customerminer.model.CmCustomerMiner;
import hry.cm.customerminer.model.CmCustomerMinerProfit;
import hry.core.mvc.service.base.BaseService;



/**
 * <p> CmCustomerMinerProfitService </p>
 * @author:         yaozh
 * @Date :          2019-07-31 11:12:16 
 */
public interface CmCustomerMinerProfitService  extends BaseService<CmCustomerMinerProfit, Long>{

	/**
	 * 插入收益记录
	 * @param cmCustomerMiner
	 * @param status 发放状态 1.未发放 2已发放
	 */
	void insertProit(CmCustomerMiner cmCustomerMiner,Integer status);
	
	/**
	 * 查询用户有效投入总数
	 * @param customerId
	 * @return
	 */
	public BigDecimal getTeamProfitSumByCustomerId(Long customerId);
	
	/**
	 * 查询用户今天矿机产币总数
	 * @param customerId
	 * @return
	 */
	public BigDecimal getProfitSumByCustomerId(Long customerId);

}
