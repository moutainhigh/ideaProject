/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Wu Shuiming
 * @version:      V1.0 
 * @Date:        2016年3月28日 下午5:55:59
 */
package hry.app.account.dao;

import hry.core.mvc.dao.base.BaseDao;
import hry.otc.manage.remote.model.account.ExDigitalmoneyAccount;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * TODO
 * </p>
 * 
 * @author: Wu Shuiming
 * @Date : 2016年3月28日 下午5:55:59
 */
public interface ExDigitalmoneyAccountDao extends
		BaseDao<ExDigitalmoneyAccount, Long> {
	
	
	/**
	 * 查询分红账户
	 * @return
	 */
	public List<ExDigitalmoneyAccount> findBonusUserList (Map<String, Object> map);
	/**
	 * 查询分红账户数量
	 * @return
	 */
	public int findBonusUserListCount (Map<String, Object> map);
	/**
	 * 查询挖矿币账户
	 * @Function: ExDigitalmoneyAccountDao.java
	 * @Description: 
	 * @author: zjj
	 * @date: 2018年8月6日 上午10:24:26
	 */
	public List<ExDigitalmoneyAccount> findMiningOrderList (Map<String, Object> PageMap);
	
	public Integer findMiningOrderListcount ();
	/**
	 * 分红查找用户
	 * @Function: ExDigitalmoneyAccountDao.java
	 * @Description: 
	 * @author: zjj
	 * @date: 2018年8月6日 下午5:23:08
	 */
	public List<ExDigitalmoneyAccount> findCustomerCoin (Map<String, Object> PageMap);
	/**
	 * 分红查找用户
	 * @Function: ExDigitalmoneyAccountDao.java
	 * @Description: 
	 * @author: zjj
	 * @date: 2018年8月6日 下午5:23:08
	 */
	public int findCustomerCoinCount (Map<String, Object> PageMap);
}
