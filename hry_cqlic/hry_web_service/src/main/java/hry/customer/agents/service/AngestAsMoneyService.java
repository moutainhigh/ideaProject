/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Wu Shuiming
 * @version:      V1.0 
 * @Date:        2016年7月6日 下午6:02:18
 */
package hry.customer.agents.service;

import java.math.BigDecimal;

import hry.bean.JsonResult;
import hry.core.mvc.service.base.BaseService;
import hry.customer.agents.model.AngestAsMoney;
import hry.customer.agents.model.AppTradeFactorage;

/**
 * <p> TODO</p>
 * @author:         Wu Shuiming
 * @Date :          2016年7月6日 下午6:02:18 
 */
public interface AngestAsMoneyService extends BaseService<AngestAsMoney, Long> {
	
	/**
	 * 通过代理商id派发佣金
	 * 
	 * @author:    Wu Shuiming
	 * @version:   V1.0 
	 * @date:      2016年8月4日 下午4:38:59
	 */
	//public JsonResult postMoneyById(Long id,BigDecimal money);

	JsonResult postMoneyById(Long id, BigDecimal money, String FixPriceCoinCode);
	
	
	
}                                                                                             
