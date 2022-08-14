/**
 * Copyright:   
 * @author:      yaozh
 * @version:     V1.0 
 * @Date:        2019-08-01 10:27:19 
 */
package hry.cm.customer.service;

import hry.cm.customer.model.CmCustomer;
import hry.core.mvc.service.base.BaseService;



/**
 * <p> CmCustomerService </p>
 * @author:         yaozh
 * @Date :          2019-08-01 10:27:19 
 */
public interface CmCustomerService  extends BaseService<CmCustomer, Long>{
	
	/**
	 * 主动更新等级,只更新自己的等级
	 * @param customerId 用户id
	 */
	void updateGrade(String messageStr);
	
	/**
	 * 矿机到期  购买矿机更新等级,更新自己和上级的等级
	 * @param customerId 用户id
	 */
	void buyAndcloseMinerUpdateGrade(String messageStr);
	
	/**
	 * 查询用户等级信息
	 * @param customerId
	 * @return
	 */
	public CmCustomer getCustomerByCustomerId(Long customerId);
	


}
