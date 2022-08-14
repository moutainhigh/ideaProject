/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      liuchenghui
 * @version:     V1.0 
 * @Date:        2018-05-03 09:47:26 
 */
package hry.exchange.lock.service;

import hry.bean.PageResult;
import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;
import hry.exchange.transaction.model.ExDmTransaction;



/**
 * <p> ExDmManualUnlockService </p>
 * @author:         liuchenghui
 * @Date :          2018-05-03 09:47:26  
 */
public interface ExDmManualUnlockService  extends BaseService<ExDmTransaction, Long>{

	public PageResult findPageBySql(QueryFilter filter);
	
	public void updateByAccountId(Long accountId, Long customerId);
	
	/**
	 * 锁仓管理-定时解锁账户冻结
	 */
	public void timingUnlockAccountCold();

}
