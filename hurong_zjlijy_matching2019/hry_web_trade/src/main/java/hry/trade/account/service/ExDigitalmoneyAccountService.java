/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Wu Shuiming
 * @version:      V1.0 
 * @Date:        2016年3月28日 下午6:10:02
 */
package hry.trade.account.service;

import hry.core.mvc.service.base.BaseService;
import hry.exchange.account.model.ExDigitalmoneyAccount;
import hry.exchange.account.model.ExDmColdAccountRecord;
import hry.exchange.account.model.ExDmHotAccountRecord;
import hry.trade.redis.model.ExDigitalmoneyAccountRedis;

import java.math.BigDecimal;

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

		public ExDigitalmoneyAccountRedis getExDigitalmoneyAccountByRedis(String id);
		
		public void setExDigitalmoneyAccounttoRedis(ExDigitalmoneyAccountRedis exDigitalmoneyAccount);

	public ExDmColdAccountRecord createColdRecord(Integer recordType,ExDigitalmoneyAccount exDigitalmoneyAccount, BigDecimal freezeMoney, BigDecimal balanceMoney,String transactionNum,String orderNum,Integer remark);

	public ExDmHotAccountRecord createHotRecord(Integer recordType,ExDigitalmoneyAccount account, BigDecimal freezeMoney, BigDecimal balanceMoney,String transactionNum,String orderNum,Integer remark);

}
