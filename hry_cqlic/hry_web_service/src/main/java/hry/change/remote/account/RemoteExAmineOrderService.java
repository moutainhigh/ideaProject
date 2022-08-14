/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Zhang Xiaofang
 * @version:      V1.0 
 * @Date:        2016年5月12日 上午11:55:59
 */
package hry.change.remote.account;

import hry.exchange.transaction.model.ExDmTransaction;


/**
 * <p> TODO</p>
 * @author:         Zhang Xiaofang 
 * @Date :          2016年5月12日 上午11:55:59 
 */
public interface RemoteExAmineOrderService {
	
	public String chargeAccount(ExDmTransaction tsx);
}
