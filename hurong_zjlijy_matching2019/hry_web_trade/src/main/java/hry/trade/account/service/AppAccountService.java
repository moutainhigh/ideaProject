/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Liu Shilei
 * @version:      V1.0 
 * @Date:        2016年3月31日 下午6:52:11
 */
package hry.trade.account.service;

import hry.account.fund.model.AppAccount;
import hry.account.fund.model.AppColdAccountRecord;
import hry.account.fund.model.AppHotAccountRecord;
import hry.core.mvc.service.base.BaseService;
import hry.trade.redis.model.AppAccountRedis;

import java.math.BigDecimal;

/**
 * <p> TODO</p>
 * @author:         Liu Shilei 
 * @Date :          2016年3月31日 下午6:52:11 
 */
public interface AppAccountService extends BaseService<AppAccount, Long>{
	public AppColdAccountRecord createColdRecord(Integer recordType,AppAccount account, BigDecimal freezeMoney, BigDecimal balanceMoney,String transactionNum,String orderNum,Integer remark);
	public AppHotAccountRecord createHotRecord(Integer recordType,AppAccount account, BigDecimal freezeMoney, BigDecimal balanceMoney,String transactionNum,String orderNum,Integer remark);

	public AppAccountRedis getAppAccountByRedis(String id);
	
	public void setAppAccounttoRedis(AppAccountRedis appAccount);
}
