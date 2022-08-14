/**
 * Copyright:    
 * @author:      yaozh
 * @version:     V1.0 
 * @Date:        2019-08-01 10:27:19 
 */
package hry.cm.customer.dao;

import hry.core.mvc.dao.base.BaseDao;

import org.apache.ibatis.annotations.Param;

import hry.cm.customer.model.CmCustomer;


/**
 * <p> CmCustomerDao </p>
 * @author:         yaozh
 * @Date :          2019-08-01 10:27:19  
 */
public interface CmCustomerDao extends  BaseDao<CmCustomer, Long> {
	
	/**
	 * 查询用户等级信息
	 * @param customerId
	 * @return
	 */
	public CmCustomer getCustomerByCustomerId(@Param("customerId") Long customerId);

}
