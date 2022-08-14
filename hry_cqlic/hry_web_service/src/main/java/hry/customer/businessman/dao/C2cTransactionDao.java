/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2017-12-07 14:06:38 
 */
package hry.customer.businessman.dao;

import java.util.List;
import java.util.Map;

import hry.core.mvc.dao.base.BaseDao;
import hry.customer.businessman.model.C2cTransaction;


/**
 * <p> C2cTransactionDao </p>
 * @author:         liushilei
 * @Date :          2017-12-07 14:06:38  
 */
public interface C2cTransactionDao extends  BaseDao<C2cTransaction, Long> {
	
	/**
	 * 查询成交的一个币的 订单数量，按商户分组
	 * @param map
	 * @return 
	 */
	List<C2cTransaction> groupByBusinessmanId(Map<String, Object> map);
	
	/**
	 * 查询前台订单
	 * @param params
	 * @return
	 */
	List<C2cTransaction> c2clist(Map<String, String> params);
	
	/**
	 * sql分页
	 * @param map
	 * @return
	 */
	List<C2cTransaction> findPageBySql(Map<String, Object> map);

}
