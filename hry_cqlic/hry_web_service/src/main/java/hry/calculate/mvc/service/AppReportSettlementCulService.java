/**
 * Copyright:  北京互融时代软件有限公司
 * @author:    Wu Shuiming
 * @version:   V1.0 
 * @Date:      2016年8月23日 下午9:13:09
 */
package hry.calculate.mvc.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import hry.calculate.mvc.po.AccountFundInfo;
import hry.calculate.settlement.model.AppReportSettlement;
import hry.calculate.settlement.model.AppReportSettlementcoin;
import hry.bean.PageResult;
import hry.core.mvc.service.base.BaseService;
import hry.util.QueryFilter;
import hry.customer.user.model.AppCustomer;

/**
 * @author Wu shuiming
 * @date 2016年8月23日 下午9:13:09
 */
public interface AppReportSettlementCulService extends BaseService<AppReportSettlement, Long>{

	public Map<String,BigDecimal> getTransactionByExEntrust(Long customerId, String currencyType, String website
            , String beginDateString, String endDateString);
	public Map<String,BigDecimal> getLendByExDmLend(Long customerId, String type, String currencyType, String website
            , String beginDateString, String endDateString);
	public Map<String,BigDecimal> getRepayByExDmLendIntent(Long customerId, String currencyType, String type, String website
            , String beginDateString, String endDateString);
	
	
	public Map<String,BigDecimal> getTranCoinByExEntrust(Long customerId, String coinCode, String currencyType, String website
            , String beginDateString, String endDateString);
	public Map<String,BigDecimal> getInOutByExDmTransaction(Long customerId, String coinCode, String currencyType, String website
            , String beginDateString, String endDateString);
	public Map<String,BigDecimal> getLendcoinByExDmLend(Long customerId, String coinCode, String currencyType, String website
            , String beginDateString, String endDateString);
	public Map<String,BigDecimal> getRepaycoinExDmLendIntent(Long customerId, String coinCode, String currencyType, String website
            , String beginDateString, String endDateString);
	Map<String,BigDecimal> getReWiByAppTransaction(Long customerId, String currencyType, String website
            , String beginDateString, String endDateString);
	public Map<String,BigDecimal> getWithdrawcoldcount(Long customerId, String coinCode, String currencyType, String website
            , String beginDateString, String endDateString);
	
	
	public Map<String,Object>  culAccountByCustomer(Long customerId, String currencyType, String website, Boolean isSave, Boolean iserrorright);
	
	public Map<String,BigDecimal> getCoinByAccountDisable(Long customerId, String coinCode, String currencyType, String website
            , String beginDateString, String endDateString);
	public Map<String,BigDecimal> getTranfixPriceCoinCodeByExEntrust(Long customerId, String fixPriceCoinCode, String currencyType, String website
            , String beginDateString, String endDateString);
}
