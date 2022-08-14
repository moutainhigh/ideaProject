/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Wu Shuiming
 * @version:      V1.0 
 * @Date:        2016年3月28日 下午5:59:49
 */
package hry.exchange.transaction.dao;

import java.util.List;
import java.util.Map;

import hry.core.mvc.dao.base.BaseDao;
import hry.exchange.transaction.model.ExDmCustomerPublicKey;

/**
 * <p>
 * TODO
 * </p>
 * 
 * @author: Wu Shuiming
 * @Date : 2016年3月28日 下午5:59:49
 */
public interface ExDmCustomerPublicKeyDao extends BaseDao<ExDmCustomerPublicKey, Long> {
	
	/**
	 * sql分页
	 * @param map
	 * @return
	 */
	List<ExDmCustomerPublicKey> findPageBySql(Map<String, Object> map);

	/**
	 * 新sql分页
	 * @param map
	 * @return
	 */
	List<ExDmCustomerPublicKey> findPageBySqlList(Map<String, Object> map);
	
	/**
	 * 根据条件查询客户信息
	 * @param map
	 * @return
	 */
	List<String> findCustomerByCondition(Map<String, Object> map);

	/**
	 * 查询总条数
	 * @param map
	 * @return
	 */
	Long findPageBySqlCount(Map<String, Object> map);

}
