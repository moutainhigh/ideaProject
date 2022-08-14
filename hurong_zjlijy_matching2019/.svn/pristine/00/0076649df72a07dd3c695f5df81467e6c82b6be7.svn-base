/**
 * Copyright:    
 * @author:      yaozh
 * @version:     V1.0 
 * @Date:        2019-08-09 11:44:39 
 */
package hry.cm.profittwo.dao;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import hry.cm.profittwo.model.CmProfitTwo;
import hry.core.mvc.dao.base.BaseDao;


/**
 * <p> CmProfitTwoDao </p>
 * @author:         yaozh
 * @Date :          2019-08-09 11:44:39  
 */
public interface CmProfitTwoDao extends  BaseDao<CmProfitTwo, Long> {
	
	/**
	 * 统计每个用户所有未发放收益的总数
	 * @return
	 */
	public List<CmProfitTwo> findProfitSum();
	
	/**
	 * 更新未发放收益为已发放
	 * @param customerId
	 */
	void updateStatus(@Param("customerId")Long customerId);
	
	/**
	 * 用户今天收益的总数 
	 * @param customerId
	 * @return
	 */
	public BigDecimal getTodayProfitSum(@Param("customerId")Long customerId);
	
	/**
	 * 用户总收益的总数 
	 * @param customerId
	 * @return
	 */
	public BigDecimal getTotalProfitSum(@Param("customerId")Long customerId);


}
