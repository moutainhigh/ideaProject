/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Wu Shuiming
 * @version:      V1.0 
 * @Date:        2016年3月28日 下午6:00:18
 */
package hry.exchange.transaction.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import hry.core.mvc.dao.base.BaseDao;
import hry.exchange.transaction.model.ExDmTransaction;
import hry.manage.remote.model.ExDmTransactionManage;

/**
 * <p>
 * TODO
 * </p>
 * 
 * @author: Wu Shuiming
 * @Date : 2016年3月28日 下午6:00:18
 */
public interface ExDmTransactionDao extends BaseDao<ExDmTransaction, Long> {

	
	
	List<ExDmTransaction> findPageBySql(Map<String, Object> map);
	
	public ExDmTransaction findLastTrasaction();
	
	/**
	 * 根据用户的账号 查询他当天充值或提现的总数量
	 * 
	 * @author:    Wu Shuiming
	 * @version:   V1.0 
	 * @date:      2016年8月31日 下午3:31:27
	 */
	public BigDecimal findGetNumByCustomer(@Param(value = "customerId") Long customerId, @Param(value = "coinCode") String coinCode, @Param(value = "type") String type);
	
	public List<ExDmTransactionManage> findExdmtransaction(Map<String, String> map);

	public List<ExDmTransactionManage> findFrontPage(Map<String, String> params);

	List<ExDmTransactionManage> findExdmtransactionRecord (Map<String, String> params);
}
