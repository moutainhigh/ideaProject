/**
 * Copyright:   
 * @author:      yaozh
 * @version:     V1.0 
 * @Date:        2019-07-30 11:33:21 
 */
package hry.cm.customerminer.service;

import java.math.BigDecimal;
import java.util.List;

import hry.cm.customer.model.CmCustomer;
import hry.cm.customerminer.model.CmCustomerMiner;
import hry.core.mvc.service.base.BaseService;



/**
 * <p> CmCustomerMinerService </p>
 * @author:         yaozh
 * @Date :          2019-07-30 11:33:21 
 */
public interface CmCustomerMinerService  extends BaseService<CmCustomerMiner, Long>{


	/**
	 * admin定时任务矿机产币,按天产币
	 */
	void minerCoinageByDay(String message);
	
	/**
	 * 更新矿机收益  正数加   负数减
	 * @param id 用户矿机Id
	 * @param profit1 已领取收益
	 * @param profit2 未领取收益
	 * @param profit3 总产出收益
	 */
	void updateMinerProfit(Long id,BigDecimal profit1,BigDecimal profit2,BigDecimal profit3);
	
	
	/**
	 * 查询用户有效投入总数
	 * @param customerId
	 * @return
	 */
	public BigDecimal getMinerPriceSumByCustomerId(Long customerId);
	/**
	 * 查询用户伞下有效投入总数
	 * @param customerId
	 * @return
	 */
	public BigDecimal getTeamMinerPriceSumByCustomerId(Long customerId);
	/**
	 * 查询用户最大线 最小线
	 * @param customerId
	 * @return
	 */
	public CmCustomer getTeamMaxAndMinByCustomerId(Long customerId);
	/**
	 * 查询用户团队人数
	 * @param customerId
	 * @return
	 */
	public Integer getTeamNumByCustomerId(Long customerId);
	/**
	 * 更新到期矿机状态
	 */
	void updateCloseMiner();
	/**
	 * 更新待运行矿机状态
	 */
	void updateWaitMiner();
	
	/**
	 * 查询用户所有矿机统计
	 * @param customerId
	 * @return
	 */
	public List<CmCustomerMiner> getMyMinerCustomerId(Long customerId);
	
	/**
	 * 查询用户待运行+运行中的矿机总数
	 * @param customerId
	 * @return
	 */
	public Integer getMinigNumByCustomerId(Long customerId);
	
	/**
	 * 查询用户矿机总产出
	 * @param customerId
	 * @return
	 */
	public BigDecimal getMinigProFitByCustomerId(Long customerId);
	
	/**
	 * 根据矿机id查询用户待运行+运行中的矿机数量
	 * @param customerId
	 * @return
	 */
	public Integer getMinigNumByCustomerIdAndMinerId(Long customerId,Long minerId);
	
}
