/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      liuchenghui
 * @version:     V1.0 
 * @Date:        2018-05-03 09:47:26 
 */
package hry.exchange.lock.dao;

import java.util.List;
import java.util.Map;

import hry.core.mvc.dao.base.BaseDao;
import hry.exchange.lock.model.ExDmLock;
import hry.exchange.lock.model.vo.DigitalmoneyAccountAndTransaction;
import hry.exchange.transaction.model.ExDmTransaction;


/**
 * <p> ExDmManualUnlockDao </p>
 * @author:         liuchenghui
 * @Date :          2018-05-03 09:47:26  
 */
public interface ExDmManualUnlockDao extends  BaseDao<ExDmTransaction, Long> {

	public List<DigitalmoneyAccountAndTransaction> findPageByCondition(Map<String, Object> params);
	
	public void updateByAccountId(Long accountId);
	
	public List<ExDmLock> selectLockRecordByTime();
}
