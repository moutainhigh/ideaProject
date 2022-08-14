/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Wu Shuiming
 * @version:      V1.0 
 * @Date:        2016年3月28日 下午6:56:17
 */
package hry.exchange.transaction.service;

import hry.bean.PageResult;
import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;
import hry.exchange.transaction.model.ExDmCustomerPublicKey;

/**
 * <p>
 * TODO
 * </p>
 * 
 * @author: Wu Shuiming
 * @Date : 2016年3月28日 下午6:56:17
 */
public interface ExDmCustomerPublicKeyService extends BaseService<ExDmCustomerPublicKey, Long> {
	
	/**
	 * sql分页
	 * @param filter
	 * @return
	 */
	PageResult findPageBySql(QueryFilter filter);

	/**
	 * 新sql分页
	 * @param filter
	 * @return
	 */
	PageResult findPageBySqlList(QueryFilter filter);

}
