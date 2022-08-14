/**
 * Copyright:    
 * @author:      yaozhuo
 * @version:     V1.0 
 * @Date:        2019-04-19 11:31:31 
 */
package hry.klg.buysellaccount.dao;

import java.util.Map;

import hry.core.mvc.dao.base.BaseDao;
import hry.klg.buysellaccount.model.KlgBuySellAccount;


/**
 * <p> KlgBuySellAccountDao </p>
 * @author:         yaozhuo
 * @Date :          2019-04-19 11:31:31  
 */
public interface KlgBuySellAccountDao extends  BaseDao<KlgBuySellAccount, Long> {
	
	/**
	 * 根据账户名称加减账户
	 * @param map
	 */
	void updateMoneyByAccountName(Map<String,Object> map);

}
