/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Wu Shuiming
 * @version:      V1.0 
 * @Date:        2016年3月28日 下午6:10:02
 */
package hry.app.account.service;

import hry.core.mvc.service.base.BaseService;
import hry.otc.manage.remote.model.account.ExDigitalmoneyAccount;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * TODO
 * </p>
 * 
 * @author: Wu Shuiming
 * @Date : 2016年3月28日 下午6:10:02
 */
public interface ExDigitalmoneyAccountService extends
		BaseService<ExDigitalmoneyAccount, Long> {
	/**
	 * 
	 * <p> 并发保存</p>
	 * @author:         Gao Mimi
	 * @param:    @param account
	 * @param:    @return
	 * @return: String[] 
	 * @Date :          2016年5月12日 下午7:00:48   
	 * @throws:
	 */
	public String[] updateAccount (ExDigitalmoneyAccount account);
	
	public ExDigitalmoneyAccount getByCustomerIdAndType (Long customerId, String coinCode, String currencyType, String website);
	/**
	 * 查询分红账户
	 * @return
	 */
	public List<ExDigitalmoneyAccount> findBonusUserList (Map<String, Object> map);
	/**
	 * 查询分红账户
	 * @return
	 */
	public int findBonusUserListCount (Map<String, Object> map);
	
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
	public Integer findCustomerCoinCount (Map<String, Object> PageMap);
}
