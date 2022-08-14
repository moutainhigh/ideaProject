/**
 * Copyright:   
 * @author:      yaozhuo
 * @version:     V1.0 
 * @Date:        2019-04-19 11:31:31 
 */
package hry.klg.buysellaccount.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import hry.core.mvc.service.base.BaseService;
import hry.klg.buysellaccount.model.KlgBuySellAccount;
import hry.klg.remote.model.ToBuySellAccountMessage;



/**
 * <p> KlgBuySellAccountService </p>
 * @author:         yaozhuo
 * @Date :          2019-04-19 11:31:31 
 */
public interface KlgBuySellAccountService  extends BaseService<KlgBuySellAccount, Long>{

	/***
	 * 买卖账户变动消息接收
	 * @param toBuySellAccountMessage
	 */
	void toBuySellAccountMessage(List<ToBuySellAccountMessage> list);
	
	/**
	 * 根据账户名称加减账户
	 * @param accountName 账户名称
	 * @param changeMoney 变动金额
	 * @param changeType 变动类型 1+ 2-
	 * @param orderNum订单号
	 * @param remark 备注
	 * @param triggered 触发点
	 */
	void updateMoneyByAccountName(String accountName,BigDecimal changeMoney,Integer changeType,String orderNum ,String remark,String triggered);

}
