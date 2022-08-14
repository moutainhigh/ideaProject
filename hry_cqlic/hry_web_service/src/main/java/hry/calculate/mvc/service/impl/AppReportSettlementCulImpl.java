/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Wu Shuiming
 * @version:      V1.0 
 * @Date:        2016年7月5日 上午10:42:10
 */
package hry.calculate.mvc.service.impl;

import hry.account.fund.dao.AppAccountDao;
import hry.account.fund.model.AppAccount;
import hry.account.fund.model.AppAccountSureold;
import hry.account.fund.model.AppTransaction;
import hry.account.fund.service.AppAccountService;
import hry.account.remote.RemoteAppAccountService;
import hry.account.remote.RemoteAppAccountSureoldService;
import hry.account.remote.RemoteAppTransactionService;
import hry.calculate.mvc.po.AccountFundInfo;
import hry.calculate.mvc.po.HistoryAccountFundInfo;
import hry.calculate.mvc.service.AppReportSettlementCulService;
import hry.calculate.mvc.service.AppReportSettlementcoinService;
import hry.calculate.settlement.model.AppReportSettlement;
import hry.calculate.settlement.model.AppReportSettlementcoin;
import hry.change.remote.account.RemoteExDigitalmoneyAccountService;
import hry.change.remote.dmtransaction.RemoteExDmTransactionService;
import hry.change.remote.exEntrust.RemoteExEntrustService;
import hry.change.remote.exEntrust.RemoteExOrderService;
import hry.change.remote.lend.RemoteExDmLendService;
import hry.core.constant.CodeConstant;
import hry.core.constant.StringConstant;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.util.QueryFilter;
import hry.util.RemoteQueryFilter;
import hry.util.date.DateUtil;
import hry.util.sys.ContextUtil;
import hry.customer.agents.dao.AngestAsMoneyDao;
import hry.customer.agents.model.AngestAsMoney;
import hry.customer.agents.service.AngestAsMoneyService;
import hry.customer.person.model.AppPersonInfo;
import hry.customer.remote.RemoteAppCustomerService;
import hry.customer.remote.RemoteAppPersonInfoService;
import hry.customer.user.model.AppCustomer;
import hry.exchange.account.model.AppAccountDisable;
import hry.exchange.account.model.ExDigitalmoneyAccount;
import hry.exchange.account.service.ExDigitalmoneyAccountService;
import hry.exchange.lend.model.ExDmLend;
import hry.exchange.lend.model.ExDmLendIntent;
import hry.exchange.transaction.model.ExDmTransaction;
import hry.redis.common.dao.RedisUtil;
import hry.redis.common.utils.RedisService;
import hry.trade.entrust.ExchangeDataCache;
import hry.trade.entrust.model.ExEntrust;
import hry.trade.entrust.model.ExOrderInfo;
import hry.trade.redis.model.AppAccountRedis;
import hry.trade.redis.model.ExDigitalmoneyAccountRedis;
import hry.web.remote.RemoteAppConfigService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import tk.mybatis.mapper.util.StringUtil;

import com.alibaba.fastjson.JSON;

/**
 * <p>
 * TODO
 * </p>
 * 
 * @author: Wu Shuiming
 * @Date : 2016年7月5日 上午10:42:10
 */
@Service("appReportSettlementCulService")
public class AppReportSettlementCulImpl extends BaseServiceImpl<AppReportSettlement, Long> implements AppReportSettlementCulService {
	private static Logger logger = Logger.getLogger(AppReportSettlementCulImpl.class);
	@Resource(name = "appReportSettlementDao")
	@Override
	public void setDao(BaseDao<AppReportSettlement, Long> dao) {
		super.dao = dao;
	}

	@Resource(name = "remoteAppCustomerService")
	public RemoteAppCustomerService appCustomerService;
	@Resource(name = "remoteExEntrustService")
	public RemoteExEntrustService exEntrustService;
	@Resource(name = "remoteExOrderService")
	public RemoteExOrderService exOrderInfoService;
	@Resource(name = "remoteExDigitalmoneyAccountService")
	public RemoteExDigitalmoneyAccountService remoteExDigitalmoneyAccountService;
	@Resource(name = "appReportSettlementcoinService")
	public AppReportSettlementcoinService appReportSettlementcoinService;
	@Resource(name = "remoteExDmTransactionService")
	public RemoteExDmTransactionService remoteExDmTransactionService;
	@Resource
	public RemoteExEntrustService remoteExEntrustService;

	@Resource
	public RemoteExDmLendService remoteExDmLendService;
	@Resource
	private RedisService redisService;
	@Resource
	private RemoteAppTransactionService remoteAppTransactionService;
	@Resource
	public RemoteAppAccountSureoldService remoteAppAccountSureoldService;
	@Resource
	public AppReportSettlementCulService appReportSettlementCulService;
	@Resource
    public AngestAsMoneyService angestAsMoneyService;
	@Resource
    public  AngestAsMoneyDao angestAsMoneyDao;
	@Resource
    public  ExDigitalmoneyAccountService exDigitalmoneyAccountService;
	@Resource
    public  AppAccountService appAccountService;
	@Override
	public Map<String, BigDecimal> getReWiByAppTransaction(Long customerId, String currencyType, String website, String beginDateString, String endDateString) {

		Map<String, BigDecimal> map = new HashMap<String, BigDecimal>();

		// 充值额,提现额，提现手续费
		RemoteAppTransactionService remoteAppTransactionService = (RemoteAppTransactionService) ContextUtil.getBean("remoteAppTransactionService");
		// 查出所以充值提现的成功数据
		List<AppTransaction> rechargeList = remoteAppTransactionService.record(customerId, null, "2", beginDateString, endDateString, null, null);
		BigDecimal rechargeMoney = new BigDecimal("0");
		BigDecimal rechargeFee = new BigDecimal("0");
		BigDecimal withdrawMoney = new BigDecimal("0");
		BigDecimal withdrawFee = new BigDecimal("0");
		for (AppTransaction at : rechargeList) {
			if (at.getTransactionType() == 1 || at.getTransactionType() == 3 || at.getTransactionType() == 5) {
				rechargeMoney = rechargeMoney.add(at.getTransactionMoney());
				rechargeFee = rechargeFee.add(at.getFee());
			} else {
				withdrawMoney = withdrawMoney.add(at.getTransactionMoney());
				withdrawFee = withdrawFee.add(at.getFee());
			}

		}

		map.put("rechargeMoney", rechargeMoney);
		map.put("withdrawMoney", withdrawMoney);
		map.put("withdrawFee", withdrawFee);
		map.put("rechargeFee", rechargeFee);
		return map;
	}

	@Override
	public Map<String, BigDecimal> getTransactionByExEntrust(Long customerId, String currencyType, String website, String beginDateString, String endDateString) {

		Map<String, BigDecimal> map = new HashMap<String, BigDecimal>();

		// 买成交额,卖成交额，成交手续费
		RemoteQueryFilter transactionMoneyfilter1 = new RemoteQueryFilter(ExEntrust.class);
		transactionMoneyfilter1.addFilter("customerId=", customerId);
		// transactionMoneyfilter1.addFilter("currencyType=",currencyType);
		// transactionMoneyfilter1.addFilter("website=",website);
		transactionMoneyfilter1.addFilter("type=", 1);
		transactionMoneyfilter1.addFilter("fixPriceType=", 0);// 真实货币
		transactionMoneyfilter1.addFilter("status<", 2);
		if (null != beginDateString) {
			transactionMoneyfilter1.addFilter("entrustTime>=", beginDateString);
		}
		if (null != endDateString) {
			transactionMoneyfilter1.addFilter("entrustTime<", endDateString);
		}
		List<ExEntrust> buyTransactionMoneylist = remoteExEntrustService.find(transactionMoneyfilter1);
		BigDecimal buyTransactionMoney = new BigDecimal("0");
		BigDecimal sellTransactionMoney = new BigDecimal("0");
		BigDecimal transactionFee = new BigDecimal("0");
		BigDecimal coldEntrustMoney = new BigDecimal("0"); // 委托冻结
		for (ExEntrust e : buyTransactionMoneylist) {
			coldEntrustMoney = coldEntrustMoney.add(e.getEntrustSum().subtract(e.getTransactionSum()));
		}

		// 计算币的买成交量，卖成交量
		RemoteQueryFilter transactionMoneyfilterbuy = new RemoteQueryFilter(ExOrderInfo.class);
		transactionMoneyfilterbuy.addFilter("buyCustomId=", customerId);
		// transactionMoneyfilterbuy.addFilter("currencyType=",currencyType);
		// transactionMoneyfilterbuy.addFilter("website=",website);
		transactionMoneyfilterbuy.addFilter("fixPriceType=", 0);// 真实货币
		if (null != beginDateString) {
			transactionMoneyfilterbuy.addFilter("transactionTime>=", beginDateString);
		}
		if (null != endDateString) {
			transactionMoneyfilterbuy.addFilter("transactionTime<", endDateString);
		}
		List<ExOrderInfo> Transactioncountlistbuy = exOrderInfoService.find(transactionMoneyfilterbuy);
		for (ExOrderInfo e : Transactioncountlistbuy) {
				buyTransactionMoney = buyTransactionMoney.add(e.getTransactionSum());
		}

		// 计算币的买成交量，卖成交量
		RemoteQueryFilter transactionMoneyfiltersell = new RemoteQueryFilter(ExOrderInfo.class);
		transactionMoneyfiltersell.addFilter("sellCustomId=", customerId);
		// transactionMoneyfilter.addFilter("currencyType=",currencyType);
		// transactionMoneyfilter.addFilter("website=",website);
		transactionMoneyfiltersell.addFilter("fixPriceType=", 0);// 真实货币
		if (null != beginDateString) {
			transactionMoneyfiltersell.addFilter("transactionTime>=", beginDateString);
		}
		if (null != endDateString) {
			transactionMoneyfiltersell.addFilter("transactionTime<", endDateString);
		}
		List<ExOrderInfo> Transactioncountlistsell = exOrderInfoService.find(transactionMoneyfiltersell);
		for (ExOrderInfo a : Transactioncountlistsell) {
			sellTransactionMoney = sellTransactionMoney.add(a.getTransactionSum());
			transactionFee = transactionFee.add(a.getTransactionSellFee());
		}
		map.put("buyTransactionMoney", buyTransactionMoney);
		map.put("sellTransactionMoney", sellTransactionMoney);
		map.put("transactionFee", transactionFee);
		map.put("coldEntrustMoney", coldEntrustMoney);
		return map;
	}

	@Override
	public Map<String, BigDecimal> getLendByExDmLend(Long customerId, String type, String currencyType, String website, String beginDateString, String endDateString) {
		Map<String, BigDecimal> map = new HashMap<String, BigDecimal>();
		// 融资
		RemoteQueryFilter remoteQueryFilter = new RemoteQueryFilter(ExDmLend.class);
		remoteQueryFilter.addFilter("customerId=", customerId);
		if (null != beginDateString) {
			remoteQueryFilter.addFilter("lendTime>=", beginDateString);
		}
		if (null != endDateString) {
			remoteQueryFilter.addFilter("lendTime<", endDateString);
		}

		remoteQueryFilter.addFilter("lendCoin=", type);
		// remoteQueryFilter.addFilter("currencyType=",currencyType);
		// remoteQueryFilter.addFilter("website=",website);
		List<ExDmLend> listedl = remoteExDmLendService.find(remoteQueryFilter);
		BigDecimal lendMoney = new BigDecimal("0");
		BigDecimal notInterestMoney = new BigDecimal("0");
		BigDecimal lendRate = new BigDecimal("0");
		for (ExDmLend e : listedl) {
			notInterestMoney = notInterestMoney.add(e.getInterestCount().subtract(e.getRepayInterestCount()));
			lendMoney = lendMoney.add(e.getLendCount());
			lendRate = e.getLendRate();
		}
		if (null == endDateString && null == beginDateString) {

			map.put("notInterestMoney", notInterestMoney);
		}
		map.put("lendMoney", lendMoney);
		map.put("lendRate", lendRate);
		return map;
	}

	@Override
	public Map<String, BigDecimal> getRepayByExDmLendIntent(Long customerId, String currencyType, String type, String website, String beginDateString, String endDateString) {

		Map<String, BigDecimal> map = new HashMap<String, BigDecimal>();
		// 已还融资
		RemoteQueryFilter edliFilter = new RemoteQueryFilter(ExDmLendIntent.class);
		if (null != beginDateString) {
			edliFilter.addFilter("factTime>=", beginDateString);
		}
		if (null != endDateString) {
			edliFilter.addFilter("factTime<", endDateString);
		}
		edliFilter.addFilter("customerId=", customerId);
		edliFilter.addFilter("lendCoin=", currencyType);
		// edliFilter.addFilter("currencyType=",currencyType);
		// edliFilter.addFilter("website=",website);
		if (null != type) {
			edliFilter.addFilter("intentType=", type);// "interest"
		}

		List<ExDmLendIntent> listedli = remoteExDmLendService.findintent(edliFilter);
		BigDecimal repaylendMoney = new BigDecimal("0");
		for (ExDmLendIntent e : listedli) {
			repaylendMoney = repaylendMoney.add(e.getRepayCount());
		}

		map.put("repaylendMoney", repaylendMoney);
		return map;
	}

	@Override
	public Map<String, BigDecimal> getTranCoinByExEntrust(Long customerId, String coinCode, String currencyType, String website, String beginDateString, String endDateString) {

		Map<String, BigDecimal> map = new HashMap<String, BigDecimal>();
		RemoteQueryFilter transactionMoneyfilter1 = new RemoteQueryFilter(ExEntrust.class);
		transactionMoneyfilter1.addFilter("customerId=", customerId);
		// transactionMoneyfilter1.addFilter("currencyType=",currencyType);
		// transactionMoneyfilter1.addFilter("website=",website);
		transactionMoneyfilter1.addFilter("coinCode=", coinCode);
		transactionMoneyfilter1.addFilter("type=", 2);
		transactionMoneyfilter1.addFilter("status<", 2);
		if (null != beginDateString) {
			transactionMoneyfilter1.addFilter("entrustTime>=", beginDateString);
		}
		if (null != endDateString) {
			transactionMoneyfilter1.addFilter("entrustTime<", endDateString);
		}
		List<ExEntrust> buyTransactionMoneylist = remoteExEntrustService.find(transactionMoneyfilter1);
		BigDecimal edcoldEntrustCount = new BigDecimal("0"); // 委托冻结
		for (ExEntrust e : buyTransactionMoneylist) {
			edcoldEntrustCount = edcoldEntrustCount.add(e.getSurplusEntrustCount());
		}
		// 计算币的买成交量，卖成交量
		RemoteQueryFilter transactionMoneyfilterbuy = new RemoteQueryFilter(ExOrderInfo.class);
		transactionMoneyfilterbuy.addFilter("buyCustomId=", customerId);
		// transactionMoneyfilter.addFilter("currencyType=",currencyType);
		// transactionMoneyfilter.addFilter("website=",website);
		if (null != beginDateString) {
			transactionMoneyfilterbuy.addFilter("transactionTime>=", beginDateString);
		}
		if (null != endDateString) {
			transactionMoneyfilterbuy.addFilter("transactionTime<", endDateString);
		}
		transactionMoneyfilterbuy.addFilter("coinCode=", coinCode);
		List<ExOrderInfo> Transactioncountlistbuy = exOrderInfoService.find(transactionMoneyfilterbuy);
		BigDecimal buyTransactioncount = new BigDecimal("0");
		BigDecimal sellTransactioncount = new BigDecimal("0");
		BigDecimal transactionFee = new BigDecimal("0");
		for (ExOrderInfo e : Transactioncountlistbuy) {
			buyTransactioncount = buyTransactioncount.add(e.getTransactionCount());
			transactionFee = transactionFee.add(e.getTransactionBuyFee());
		}

		// 计算币的买成交量，卖成交量
		RemoteQueryFilter transactionMoneyfiltersell = new RemoteQueryFilter(ExOrderInfo.class);
		transactionMoneyfiltersell.addFilter("sellCustomId=", customerId);
		// transactionMoneyfilter.addFilter("currencyType=",currencyType);
		// transactionMoneyfilter.addFilter("website=",website);
		if (null != beginDateString) {
			transactionMoneyfiltersell.addFilter("transactionTime>=", beginDateString);
		}
		if (null != endDateString) {
			transactionMoneyfiltersell.addFilter("transactionTime<", endDateString);
		}
		transactionMoneyfiltersell.addFilter("coinCode=", coinCode);
		List<ExOrderInfo> Transactioncountlistsell = exOrderInfoService.find(transactionMoneyfiltersell);
		for (ExOrderInfo e : Transactioncountlistsell) {
			sellTransactioncount = sellTransactioncount.add(e.getTransactionCount());
		}
		map.put("buyTransactioncount", buyTransactioncount);
		map.put("sellTransactioncount", sellTransactioncount);
		map.put("edcoldEntrustCount", edcoldEntrustCount);
		map.put("transactionFeecoin", transactionFee);
		return map;
	}

	@Override
	public Map<String, BigDecimal> getTranfixPriceCoinCodeByExEntrust(Long customerId, String fixPriceCoinCode, String currencyType, String website, String beginDateString, String endDateString) {

		Map<String, BigDecimal> map = new HashMap<String, BigDecimal>();

		// 买成交额,卖成交额，成交手续费
		RemoteQueryFilter transactionMoneyfilter1 = new RemoteQueryFilter(ExEntrust.class);
		transactionMoneyfilter1.addFilter("customerId=", customerId);
		// transactionMoneyfilter1.addFilter("currencyType=",currencyType);
		// transactionMoneyfilter1.addFilter("website=",website);
		transactionMoneyfilter1.addFilter("type=", 1);
		transactionMoneyfilter1.addFilter("fixPriceType=", 1);// 虚拟货币
		transactionMoneyfilter1.addFilter("fixPriceCoinCode=", fixPriceCoinCode);
		transactionMoneyfilter1.addFilter("status<", 2);
		if (null != beginDateString) {
			transactionMoneyfilter1.addFilter("entrustTime>=", beginDateString);
		}
		if (null != endDateString) {
			transactionMoneyfilter1.addFilter("entrustTime<", endDateString);
		}
		List<ExEntrust> buyTransactionMoneylist = remoteExEntrustService.find(transactionMoneyfilter1);
		BigDecimal buyTransactionMoney = new BigDecimal("0");
		BigDecimal sellTransactionMoney = new BigDecimal("0");
		BigDecimal transactionFee = new BigDecimal("0");
		BigDecimal coldEntrustMoney = new BigDecimal("0"); // 委托冻结
		for (ExEntrust e : buyTransactionMoneylist) {
			coldEntrustMoney = coldEntrustMoney.add(e.getEntrustSum().subtract(e.getTransactionSum()));
		}

		// 计算币的买成交量，卖成交量
		RemoteQueryFilter transactionMoneyfilterbuy = new RemoteQueryFilter(ExOrderInfo.class);
		transactionMoneyfilterbuy.addFilter("buyCustomId=", customerId);
		// transactionMoneyfilter.addFilter("currencyType=",currencyType);
		transactionMoneyfilterbuy.addFilter("fixPriceType=", 1);// 虚拟货币
		transactionMoneyfilterbuy.addFilter("fixPriceCoinCode=", fixPriceCoinCode);
		// transactionMoneyfilter.addFilter("website=",website);
		if (null != beginDateString) {
			transactionMoneyfilterbuy.addFilter("transactionTime>=", beginDateString);
		}
		if (null != endDateString) {
			transactionMoneyfilterbuy.addFilter("transactionTime<", endDateString);
		}
		List<ExOrderInfo> Transactioncountlistbuy = exOrderInfoService.find(transactionMoneyfilterbuy);
		for (ExOrderInfo e : Transactioncountlistbuy) {
			buyTransactionMoney = buyTransactionMoney.add(e.getTransactionSum());

		}

		// 计算币的买成交量，卖成交量
		RemoteQueryFilter transactionMoneyfiltersell = new RemoteQueryFilter(ExOrderInfo.class);
		transactionMoneyfiltersell.addFilter("sellCustomId=", customerId);
		// transactionMoneyfilter.addFilter("currencyType=",currencyType);
		transactionMoneyfiltersell.addFilter("fixPriceType=", 1);// 虚拟货币
		transactionMoneyfiltersell.addFilter("fixPriceCoinCode=", fixPriceCoinCode);
		// transactionMoneyfilter.addFilter("website=",website);
		if (null != beginDateString) {
			transactionMoneyfiltersell.addFilter("transactionTime>=", beginDateString);
		}
		if (null != endDateString) {
			transactionMoneyfiltersell.addFilter("transactionTime<", endDateString);
		}
		List<ExOrderInfo> Transactioncountlist = exOrderInfoService.find(transactionMoneyfiltersell);
		for (ExOrderInfo e : Transactioncountlist) {
			sellTransactionMoney = sellTransactionMoney.add(e.getTransactionSum());
			transactionFee = transactionFee.add(e.getTransactionSellFee());

		}
		map.put("buyTransactionFixPrice", buyTransactionMoney);
		map.put("sellTransactionFixPrice", sellTransactionMoney);
		map.put("transactionFeeFixPrice", transactionFee);
		map.put("coldEntrustFixPrice", coldEntrustMoney);
		return map;
	}

	@Override
	public Map<String, BigDecimal> getInOutByExDmTransaction(Long customerId, String coinCode, String currencyType, String website, String beginDateString, String endDateString) {

		Map<String, BigDecimal> map = new HashMap<String, BigDecimal>();
		// 币的转入转出量
		RemoteQueryFilter edtfilter = new RemoteQueryFilter(ExDmTransaction.class);
		edtfilter.addFilter("customerId=", customerId);
		edtfilter.addFilter("status=", 2);
		edtfilter.addFilter("coinCode=", coinCode);
		if (null != beginDateString) {
			edtfilter.addFilter("modified>=", beginDateString);
		}
		if (null != endDateString) {
			edtfilter.addFilter("modified<", endDateString);
		}
		// edtfilter.addFilter("currencyType=",currencyType);
		// edtfilter.addFilter("website=",website);
		List<ExDmTransaction> edtlist = remoteExDmTransactionService.find(edtfilter);
		BigDecimal inCoinCount = new BigDecimal("0");
		BigDecimal outCoinCount = new BigDecimal("0");
		BigDecimal inCoinFee = new BigDecimal("0");
		BigDecimal outCoinFee = new BigDecimal("0");
		for (ExDmTransaction edt : edtlist) {
			if (edt.getTransactionType() == 1) {
				inCoinCount = inCoinCount.add(edt.getTransactionMoney());
				inCoinFee = inCoinFee.add(null == edt.getFee() ? new BigDecimal("0") : edt.getFee());
			} else {
				outCoinCount = outCoinCount.add(edt.getTransactionMoney());
				outCoinFee = outCoinFee.add(null == edt.getFee() ? new BigDecimal("0") : edt.getFee());
			}
		}
		map.put("inCoinFee", inCoinFee);
		map.put("outCoinFee", outCoinFee);
		map.put("inCoinCount", inCoinCount);
		map.put("outCoinCount", outCoinCount);
		return map;
	}

	@Override
	public Map<String, BigDecimal> getLendcoinByExDmLend(Long customerId, String coinCode, String currencyType, String website, String beginDateString, String endDateString) {

		Map<String, BigDecimal> map = new HashMap<String, BigDecimal>();
		// 融币
		RemoteQueryFilter listedlFilter = new RemoteQueryFilter(ExDmLend.class);
		listedlFilter.addFilter("customerId=", customerId);
		if (null != beginDateString) {
			listedlFilter.addFilter("lendTime>=", beginDateString);
		}
		if (null != endDateString) {
			listedlFilter.addFilter("lendTime<", endDateString);
		}
		listedlFilter.addFilter("lendCoin=", currencyType);
		// listedlFilter.addFilter("currencyType=",currencyType);
		// listedlFilter.addFilter("website=",website);
		listedlFilter.addFilter("intentType=", "interest");
		List<ExDmLend> listedlcoin = remoteExDmLendService.find(listedlFilter);
		BigDecimal lendCoin = new BigDecimal("0");
		for (ExDmLend e : listedlcoin) {
			lendCoin = lendCoin.add(e.getLendCount());
		}
		map.put("inCoinFee", lendCoin);
		return map;
	}

	@Override
	public Map<String, BigDecimal> getRepaycoinExDmLendIntent(Long customerId, String coinCode, String currencyType, String website, String beginDateString, String endDateString) {

		Map<String, BigDecimal> map = new HashMap<String, BigDecimal>();
		// 已还融币
		RemoteQueryFilter edlicoinFilter = new RemoteQueryFilter(ExDmLendIntent.class);
		edlicoinFilter.addFilter("customerId=", customerId);
		if (null != beginDateString) {
			edlicoinFilter.addFilter("factTime>=", beginDateString);
		}
		if (null != endDateString) {
			edlicoinFilter.addFilter("factTime<", endDateString);
		}
		edlicoinFilter.addFilter("lendCoin=", coinCode);
		// edlicoinFilter.addFilter("currencyType=",currencyType);
		// edlicoinFilter.addFilter("website=",website);
		List<ExDmLendIntent> listedlicoin = remoteExDmLendService.findintent(edlicoinFilter);
		BigDecimal repaylendcoin = new BigDecimal("0");
		for (ExDmLendIntent e : listedlicoin) {
			repaylendcoin = repaylendcoin.add(e.getRepayCount());
		}
		map.put("repaylendcoin", repaylendcoin);
		return map;
	}

	@Override
	public Map<String, BigDecimal> getWithdrawcoldcount(Long customerId, String coinCode, String currencyType, String website, String beginDateString, String endDateString) {
		Map<String, BigDecimal> map = new HashMap<String, BigDecimal>();
		// 提币2审核中1，4所有记录
		RemoteQueryFilter withdrawedtfilter = new RemoteQueryFilter(ExDmTransaction.class);
		withdrawedtfilter.addFilter("customerId=", customerId);
		withdrawedtfilter.addFilter("status_in", "1,4");
		withdrawedtfilter.addFilter("transactionType_in", "2");
		withdrawedtfilter.addFilter("coinCode=", coinCode);
	//	withdrawedtfilter.addFilter("currencyType=", currencyType);
		if (null != beginDateString) {
			withdrawedtfilter.addFilter("modified>=", beginDateString);
		}

	//	withdrawedtfilter.addFilter("website=", website);
		List<ExDmTransaction> widthedtlist = remoteExDmTransactionService.find(withdrawedtfilter);
		BigDecimal withdrawcoldcount = new BigDecimal("0");
		for (ExDmTransaction at : widthedtlist) {
			withdrawcoldcount = withdrawcoldcount.add(at.getTransactionMoney());
		}
		map.put("withdrawcoldcount", withdrawcoldcount);
		return map;
	}
	public Map<String, BigDecimal> getAngestAsMoney(Long customerId, String fixPriceCoinCode, String userName, String website, String beginDateString, String endDateString) {
		Map<String, BigDecimal> map = new HashMap<String, BigDecimal>();
		// 佣金派发记录
		QueryFilter angestAsMoneyfilter = new QueryFilter(AngestAsMoney.class);
		angestAsMoneyfilter.addFilter("agentName=",userName);
		angestAsMoneyfilter.addFilter("fixPriceCoinCode=", fixPriceCoinCode);
		if (null != beginDateString) {
			angestAsMoneyfilter.addFilter("modified>=", beginDateString);
		}
		List<AngestAsMoney> angestAsMoneylist = angestAsMoneyService.find(angestAsMoneyfilter);
/*		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("agentName", userName);
		map1.put("fixPriceCoinCode", fixPriceCoinCode);
		List<AngestAsMoney> angestAsMoneylist=angestAsMoneyDao.getAngestAsMoney(map1);*/
		BigDecimal drawalMoney = new BigDecimal("0");
		for (AngestAsMoney at : angestAsMoneylist) {
			drawalMoney = drawalMoney.add(at.getDrawalMoney());
		}
		map.put("drawalMoney", drawalMoney);
		return map;
	}
	public String productListsb(String website) {

		String productListStr = ExchangeDataCache.getStringData(website + ":productList");
		List<String> productList = JSON.parseArray(productListStr, String.class);
		StringBuffer productListsb = new StringBuffer();
		if (null != productList && productList.size() > 0) {
			int a = 0;
			while (a < productList.size()) {
				productListsb.append(productList.get(a));
				if (a < productList.size() - 1) {
					productListsb.append(",");
				}
				a++;
			}
		}
		return productListsb.toString();
	}
	public  String getRMBCode() {
		RemoteAppConfigService remoteAppConfigService = (RemoteAppConfigService) ContextUtil.getBean("remoteAppConfigService");
		String language_code = remoteAppConfigService.getFinanceByKey("language_code");
		return language_code;
	}
	@Override
	public Map<String, Object> culAccountByCustomer(Long customerId, String currencyType, String website, Boolean isSave, Boolean iserrorright) {
		RemoteAppAccountService remoteAppAccountService = (RemoteAppAccountService) ContextUtil.getBean("remoteAppAccountService");
		int rmbsacle = AccountFundInfo.sacle;
		int sacle = AccountFundInfo.sacle;;
		if (null == isSave) {
			isSave = false;
		}
		if (null == iserrorright) {
			iserrorright = false;
		}
		AppCustomer appCustomer = appCustomerService.getById(customerId);
		String productListsb = productListsb(website);
		Map<String, Object> map = new HashMap<String, Object>();
		List<AccountFundInfo> newlist = new ArrayList<AccountFundInfo>();
		List<AccountFundInfo> oldlist = new ArrayList<AccountFundInfo>();

		if (null == appCustomer) {
			return null;
		}
		AppAccount appAccount = remoteAppAccountService.getByCustomerIdAndType(customerId, null, null);
		if (null == appAccount) {
			return null;
		}
		AccountFundInfo afundrmb = new AccountFundInfo();
		afundrmb.setCoinCode(currencyType);
		afundrmb.setUserName(appCustomer.getUserName());
		afundrmb.setHotMoney(appAccount.getHotMoney());
		afundrmb.setColdMoney(appAccount.getColdMoney());
		afundrmb.setLendMoney(appAccount.getLendMoney());

		afundrmb.setWebsite(website);
		afundrmb.setCurrencyType(currencyType);
		oldlist.add(afundrmb);

		RemoteQueryFilter edafilter = new RemoteQueryFilter(ExDigitalmoneyAccount.class);
		edafilter.addFilter("customerId=", customerId);
	//	edafilter.addFilter("currencyType=", currencyType);
	//	edafilter.addFilter("website=", website);
		//edafilter.addFilter("coinCode_in", productListsb);
		List<ExDigitalmoneyAccount> listeda = remoteExDigitalmoneyAccountService.find(edafilter);

		for (ExDigitalmoneyAccount eda : listeda) {
			AccountFundInfo afundcoin = new AccountFundInfo();
			afundcoin.setCoinCode(eda.getCoinCode());
			afundcoin.setUserName(appCustomer.getUserName());
			afundcoin.setHotMoney(eda.getHotMoney());
			afundcoin.setColdMoney(eda.getColdMoney());
			afundcoin.setLendMoney(eda.getLendMoney());

			afundcoin.setWebsite(website);
			afundcoin.setCurrencyType(currencyType);
			oldlist.add(afundcoin);
		}
		String beginDateString = null;
		List<AppAccountSureold> sureoldlist = new ArrayList<AppAccountSureold>();
	//	AppAccountSureold aasaccount = remoteAppAccountSureoldService.getBycustomerIdAndcoinCode(customerId, appCustomer.getUserName(), currencyType, currencyType, website);
		BigDecimal sureoldHotMoney = new BigDecimal("0");
		BigDecimal sureoldColdMoney = new BigDecimal("0");
		BigDecimal sureoldLendMony = new BigDecimal("0");
		/*if (null != aasaccount) {
			beginDateString = DateUtil.dateToString(aasaccount.getSureTime(), StringConstant.DATE_FORMAT_FULL);
			sureoldHotMoney = aasaccount.getHotMoney();
			sureoldColdMoney = aasaccount.getColdMoney();
			sureoldLendMony = aasaccount.getLendMoney();
			sureoldlist.add(aasaccount);
		} else {*/
			AppAccountSureold appAccountSureoldinit = new AppAccountSureold(null, currencyType, new BigDecimal("0"), new BigDecimal("0"), new BigDecimal("0"));
			sureoldlist.add(appAccountSureoldinit);

	//	}

		// 查出所以充值提现的成功数据,充值额,提现额，提现手续费
		Map<String, BigDecimal> getReWiByAppTransaction = getReWiByAppTransaction(customerId, currencyType, website, beginDateString, null);
		BigDecimal rechargeMoney = getReWiByAppTransaction.get("rechargeMoney");
		BigDecimal withdrawMoney = getReWiByAppTransaction.get("withdrawMoney");
		BigDecimal withdrawFee = getReWiByAppTransaction.get("withdrawFee");
		BigDecimal rechargeFee = getReWiByAppTransaction.get("rechargeFee");
		// 买成交额,卖成交额，成交手续费
		Map<String, BigDecimal> getTransactionByExEntrust = getTransactionByExEntrust(customerId, currencyType, website, beginDateString, null);
		BigDecimal buyTransactionMoney = getTransactionByExEntrust.get("buyTransactionMoney");
		BigDecimal sellTransactionMoney = getTransactionByExEntrust.get("sellTransactionMoney");
		BigDecimal transactionFee = getTransactionByExEntrust.get("transactionFee");
		BigDecimal coldEntrustMoney = getTransactionByExEntrust.get("coldEntrustMoney");

		//佣金收入 
		Map<String, BigDecimal> getAngestAsMoney=getAngestAsMoney(customerId, getRMBCode(), appCustomer.getUserName(), null, null, null);
		BigDecimal drawalMoney = getAngestAsMoney.get("drawalMoney");

		
		
		Map<String, BigDecimal> getRepayByExDmLendIntent = appReportSettlementCulService.getRepayByExDmLendIntent(customerId, currencyType, "interest", website, beginDateString, null);
		BigDecimal repaylendMoney = getRepayByExDmLendIntent.get("repaylendMoney");
		BigDecimal LendMoney = appAccount.getLendMoney();

		BigDecimal coldMoney = coldEntrustMoney;
		BigDecimal hotMoney = sureoldHotMoney.subtract(sureoldLendMony).add(rechargeMoney).subtract(withdrawMoney).subtract(rechargeFee).add(sellTransactionMoney).subtract(buyTransactionMoney)
				.subtract(transactionFee).subtract(coldEntrustMoney).add(sureoldColdMoney).
				add(LendMoney).subtract(repaylendMoney)
				.add(drawalMoney);

	
		
		// 提现2,4审核1,4中的记录
		List<AppTransaction> withdrawListcold = remoteAppTransactionService.record(customerId, "2,4", "1,4", null, null, null, null);
		BigDecimal withdrawcoldMoney = new BigDecimal("0");
		for (AppTransaction at : withdrawListcold) {
			withdrawcoldMoney = withdrawcoldMoney.add(at.getTransactionMoney());
		}
		coldMoney = coldMoney.add(withdrawcoldMoney);// 总冻结=委托冻结+提现冻结

		hotMoney = hotMoney.subtract(withdrawcoldMoney);
		AccountFundInfo newafundrmb = new AccountFundInfo();
		newafundrmb.setCoinCode(currencyType);
		newafundrmb.setUserName(appCustomer.getUserName());
		newafundrmb.setHotMoney(hotMoney);
		newafundrmb.setColdMoney(coldMoney);
		newafundrmb.setLendMoney(LendMoney);
		newafundrmb.setWebsite(website);
		newafundrmb.setCurrencyType(currencyType);
		newlist.add(newafundrmb);

		List<HistoryAccountFundInfo> hislist = new ArrayList<HistoryAccountFundInfo>();
		HistoryAccountFundInfo cnyHistoryAccountFundInfo = new HistoryAccountFundInfo();

		// 充值提现
		cnyHistoryAccountFundInfo.setRechargeMoney(rechargeMoney.setScale(rmbsacle, BigDecimal.ROUND_HALF_EVEN));
		cnyHistoryAccountFundInfo.setRechargeMoneyFee(rechargeFee.setScale(rmbsacle, BigDecimal.ROUND_HALF_EVEN));
		cnyHistoryAccountFundInfo.setWithdrawMoney(withdrawMoney.setScale(rmbsacle, BigDecimal.ROUND_HALF_EVEN));
		cnyHistoryAccountFundInfo.setWithdrawcoldMoney(withdrawcoldMoney.setScale(rmbsacle, BigDecimal.ROUND_HALF_EVEN));
		cnyHistoryAccountFundInfo.setWithdrawFee(withdrawFee.setScale(rmbsacle, BigDecimal.ROUND_HALF_EVEN));

		// 作为定价币:
		cnyHistoryAccountFundInfo.setSellTransactionFixPrice(sellTransactionMoney.setScale(rmbsacle, BigDecimal.ROUND_HALF_EVEN));
		cnyHistoryAccountFundInfo.setBuyTransactionFixPrice(buyTransactionMoney.setScale(rmbsacle, BigDecimal.ROUND_HALF_EVEN));
		cnyHistoryAccountFundInfo.setTransactionFeeFixPrice(transactionFee.setScale(rmbsacle, BigDecimal.ROUND_HALF_EVEN));
		cnyHistoryAccountFundInfo.setColdEntrustFixPrice(coldEntrustMoney.setScale(rmbsacle, BigDecimal.ROUND_HALF_EVEN));
        //佣金
		cnyHistoryAccountFundInfo.setDrawalMoney(drawalMoney.setScale(rmbsacle, BigDecimal.ROUND_HALF_EVEN));
		cnyHistoryAccountFundInfo.setLendMoney(LendMoney);
		cnyHistoryAccountFundInfo.setRepaylendMoney(repaylendMoney);
		cnyHistoryAccountFundInfo.setCurrencyType(currencyType);
		cnyHistoryAccountFundInfo.setCoinCode(currencyType);
		cnyHistoryAccountFundInfo.setUserName(appCustomer.getUserName());
		cnyHistoryAccountFundInfo.setWebsite(website);
		hislist.add(cnyHistoryAccountFundInfo);

		// 计算币

		for (ExDigitalmoneyAccount eda : listeda) {

	//		AppAccountSureold aasaccountcoin = remoteAppAccountSureoldService.getBycustomerIdAndcoinCode(customerId, appCustomer.getUserName(), eda.getCoinCode(), currencyType, website);

			String coinbeginDateString = null;
			BigDecimal coinsureoldHotCount = new BigDecimal("0");
			BigDecimal coinsureoldColdCount = new BigDecimal("0");
			BigDecimal coinsureoldLendCount = new BigDecimal("0");
		/*	if (null != aasaccountcoin) {
				coinbeginDateString = DateUtil.dateToString(aasaccountcoin.getSureTime(), StringConstant.DATE_FORMAT_FULL);
				coinsureoldHotCount = aasaccountcoin.getHotMoney();
				coinsureoldColdCount = aasaccountcoin.getColdMoney();
				coinsureoldLendCount = aasaccountcoin.getLendMoney();
				sureoldlist.add(aasaccountcoin);
			} else {*/
				AppAccountSureold appAccountSureoldinitcoin = new AppAccountSureold(null, eda.getCoinCode(), new BigDecimal("0"), new BigDecimal("0"), new BigDecimal("0"));
				sureoldlist.add(appAccountSureoldinitcoin);

		//	}
			// 作为交易币：计算币的买成交量，卖成交量
			Map<String, BigDecimal> getTranCoinByExEntrust = getTranCoinByExEntrust(customerId, eda.getCoinCode(), currencyType, website, coinbeginDateString, null);
			BigDecimal buyTransactioncount = getTranCoinByExEntrust.get("buyTransactioncount");
			BigDecimal sellTransactioncount = getTranCoinByExEntrust.get("sellTransactioncount");
			BigDecimal edcoldEntrustCount = getTranCoinByExEntrust.get("edcoldEntrustCount");// 委托冻结币?????????
			BigDecimal transactionFeecoin = getTranCoinByExEntrust.get("transactionFeecoin");

			// 作为定价币：买成交额,卖成交额，成交手续费
			Map<String, BigDecimal> getTranfixPriceCoinCodeByExEntrust = getTranfixPriceCoinCodeByExEntrust(customerId, eda.getCoinCode(), currencyType, website, beginDateString, null);
			BigDecimal buyTransactionFixPrice = getTranfixPriceCoinCodeByExEntrust.get("buyTransactionFixPrice");
			BigDecimal sellTransactionFixPrice = getTranfixPriceCoinCodeByExEntrust.get("sellTransactionFixPrice");
			BigDecimal transactionFeeFixPrice = getTranfixPriceCoinCodeByExEntrust.get("transactionFeeFixPrice");
			BigDecimal coldEntrustFixPrice = getTranfixPriceCoinCodeByExEntrust.get("coldEntrustFixPrice");

			//佣金收入 
			Map<String, BigDecimal> getAngestAsMoneycoin=getAngestAsMoney(customerId, eda.getCoinCode(), appCustomer.getUserName(), null, null, null);
			BigDecimal coindrawalMoney = getAngestAsMoneycoin.get("drawalMoney");

			
			// 币的转入转出量
			Map<String, BigDecimal> getInOutByExDmTransaction = getInOutByExDmTransaction(customerId, eda.getCoinCode(), currencyType, website, coinbeginDateString, null);
			BigDecimal inCoinCount = getInOutByExDmTransaction.get("inCoinCount");
			BigDecimal outCoinCount = getInOutByExDmTransaction.get("outCoinCount");
			BigDecimal inCoinFee = getInOutByExDmTransaction.get("inCoinFee");
			BigDecimal outCoinFee = getInOutByExDmTransaction.get("outCoinFee");
			// 币禁用
			Map<String, BigDecimal> getCoinByAccountDisable = getCoinByAccountDisable(customerId, eda.getCoinCode(), currencyType, website, coinbeginDateString, null);
			BigDecimal disabletCount = getCoinByAccountDisable.get("disabletCount");

			ExDigitalmoneyAccount edappAccount = remoteExDigitalmoneyAccountService.getByCustomerIdAndType(customerId, eda.getCoinCode(), null, null);
			BigDecimal edLendMoney = edappAccount.getLendMoney();
			Map<String, BigDecimal> getRepaycoinExDmLendIntent = appReportSettlementCulService.getRepaycoinExDmLendIntent(customerId, eda.getCoinCode(), null, null, coinbeginDateString, null);
			BigDecimal repaylendcoin = getRepaycoinExDmLendIntent.get("repaylendcoin");

			BigDecimal edcoldCount = edcoldEntrustCount.add(coldEntrustFixPrice).add(disabletCount);// 总冻结金额

			BigDecimal edhotMoney = coinsureoldHotCount.subtract(coinsureoldLendCount).add(inCoinCount).subtract(inCoinFee).subtract(outCoinCount)
					.add(buyTransactioncount).subtract(sellTransactioncount).subtract(edcoldEntrustCount)
					.add(coinsureoldColdCount).add(edLendMoney).subtract(repaylendcoin)
					.add(sellTransactionFixPrice).subtract(buyTransactionFixPrice)
					.subtract(transactionFeeFixPrice).subtract(transactionFeecoin).subtract(coldEntrustFixPrice).add(coindrawalMoney);

			// 提币2审核中1，4所有记录
			Map<String, BigDecimal> getWithdrawcoldcount = getWithdrawcoldcount(customerId, eda.getCoinCode(), currencyType, website, null, null);
			BigDecimal withdrawcoldcount = getWithdrawcoldcount.get("withdrawcoldcount");
			if (withdrawcoldcount.compareTo(edhotMoney.add(edcoldEntrustCount).subtract(edLendMoney)) > 0) {
				logger.error(customerId + "币提现大于资产需要驳回:(withdrawcoldcount:" + withdrawcoldcount + "," + edhotMoney.add(edcoldEntrustCount).subtract(edLendMoney));
			}

			edcoldCount = edcoldCount.add(withdrawcoldcount);
			edhotMoney = edhotMoney.subtract(withdrawcoldcount);
			AccountFundInfo newafundcoin = new AccountFundInfo();
			newafundcoin.setCoinCode(eda.getCoinCode());
			newafundcoin.setUserName(appCustomer.getUserName());
			newafundcoin.setHotMoney(edhotMoney);
			newafundcoin.setColdMoney(edcoldCount);
			newafundcoin.setLendMoney(edLendMoney);

			newafundcoin.setWebsite(website);
			newafundcoin.setCurrencyType(currencyType);
			newlist.add(newafundcoin);

			HistoryAccountFundInfo coinHistoryAccountFundInfo = new HistoryAccountFundInfo();
			// 充值提现
			coinHistoryAccountFundInfo.setRechargeMoney(inCoinCount.setScale(sacle, BigDecimal.ROUND_HALF_DOWN));
			coinHistoryAccountFundInfo.setRechargeMoneyFee(inCoinFee.setScale(sacle, BigDecimal.ROUND_HALF_DOWN));
			coinHistoryAccountFundInfo.setWithdrawMoney(outCoinCount.setScale(sacle, BigDecimal.ROUND_HALF_DOWN));
			coinHistoryAccountFundInfo.setWithdrawcoldMoney(withdrawcoldcount.setScale(sacle, BigDecimal.ROUND_HALF_DOWN));
			coinHistoryAccountFundInfo.setRechargeMoneyFee(inCoinFee.setScale(sacle, BigDecimal.ROUND_HALF_DOWN));
			coinHistoryAccountFundInfo.setWithdrawFee(outCoinFee.setScale(sacle, BigDecimal.ROUND_HALF_DOWN));
			// 作为交易币
			coinHistoryAccountFundInfo.setSellTransactionMoney(sellTransactioncount.setScale(sacle, BigDecimal.ROUND_HALF_DOWN));
			coinHistoryAccountFundInfo.setBuyTransactionMoney(buyTransactioncount.setScale(sacle, BigDecimal.ROUND_HALF_DOWN));
			coinHistoryAccountFundInfo.setColdEntrustMoney(edcoldEntrustCount.setScale(sacle, BigDecimal.ROUND_HALF_DOWN));
			coinHistoryAccountFundInfo.setTransactionFee(transactionFeecoin.setScale(sacle, BigDecimal.ROUND_HALF_DOWN));

			// 作为定价币
			coinHistoryAccountFundInfo.setSellTransactionFixPrice(sellTransactionFixPrice.setScale(sacle, BigDecimal.ROUND_HALF_DOWN));
			coinHistoryAccountFundInfo.setBuyTransactionFixPrice(buyTransactionFixPrice.setScale(sacle, BigDecimal.ROUND_HALF_DOWN));
			coinHistoryAccountFundInfo.setTransactionFeeFixPrice(transactionFeeFixPrice.setScale(sacle, BigDecimal.ROUND_HALF_DOWN));
			coinHistoryAccountFundInfo.setColdEntrustFixPrice(coldEntrustFixPrice.setScale(sacle, BigDecimal.ROUND_HALF_DOWN));
            //佣金
			coinHistoryAccountFundInfo.setDrawalMoney(coindrawalMoney.setScale(sacle, BigDecimal.ROUND_HALF_DOWN));
			coinHistoryAccountFundInfo.setLendMoney(edLendMoney);
			coinHistoryAccountFundInfo.setRepaylendMoney(repaylendcoin);
			coinHistoryAccountFundInfo.setDisableMoney(disabletCount);
			coinHistoryAccountFundInfo.setCurrencyType(currencyType);
			coinHistoryAccountFundInfo.setCoinCode(eda.getCoinCode());
			coinHistoryAccountFundInfo.setUserName(appCustomer.getUserName());
			coinHistoryAccountFundInfo.setWebsite(website);
			hislist.add(coinHistoryAccountFundInfo);
		}
		if (iserrorright) { // true全部查出

			map.put("oldAccountFundInfolist", oldlist);
			map.put("newAccountFundInfolist", newlist);
			map.put("hisorylist", hislist);
			map.put("sureoldlist", sureoldlist);

			return map;
		}
		int i = 0;
		Boolean flag = true;
		while (i < newlist.size()) {
			if (!oldlist.get(i).toString().equals(newlist.get(i).toString())) {
				flag = false;
				break;
			}
			/*if (oldlist.get(i).toString().contains("-")) {
				flag = false;
				break;
			}*/
			i++;
		}
		if (!flag) {
			map.put("oldAccountFundInfolist", oldlist);
			map.put("newAccountFundInfolist", newlist);
			map.put("hisorylist", hislist);
			map.put("sureoldlist", sureoldlist);

			if (isSave) {
				// 保存资金账户
				appAccount.setHotMoney(hotMoney);
				appAccount.setColdMoney(coldMoney);
				appAccount.setLendMoney(LendMoney);
				remoteAppAccountService.updateAccount(appAccount);


				AppAccountRedis appAccountRedis=getAppAccountByRedis(appAccount.getId().toString());
				if (null != appAccountRedis) {
					appAccountRedis.setHotMoney(hotMoney);
					appAccountRedis.setColdMoney(coldMoney);
					this.setAppAccounttoRedis(appAccountRedis);
				}

				// 保存币账户
				for (ExDigitalmoneyAccount eda : listeda) {
					for (AccountFundInfo newafundcoin : newlist) {
						if (eda.getCoinCode().equals(newafundcoin.getCoinCode())) {
							eda.setHotMoney(newafundcoin.getHotMoney());
							eda.setColdMoney(newafundcoin.getColdMoney());
							eda.setLendMoney(newafundcoin.getLendMoney());
							remoteExDigitalmoneyAccountService.updateAccount(eda);
							
							ExDigitalmoneyAccountRedis exDigitalmoneyAccountRedis=getExDigitalmoneyAccountByRedis(eda.getId().toString());
							if (null != exDigitalmoneyAccountRedis) {
								exDigitalmoneyAccountRedis.setHotMoney(newafundcoin.getHotMoney());
								exDigitalmoneyAccountRedis.setColdMoney(newafundcoin.getColdMoney());
								this.setExDigitalmoneyAccounttoRedis(exDigitalmoneyAccountRedis);
							}
						}

					}

				}

				// 如果有数据改变并且保存到数据库的话就要保存日志
				/*
				 * MongoUtil<UpdateAccountFundInfoLog, Long> mongoUtil = new
				 * MongoUtil<UpdateAccountFundInfoLog, Long>(
				 * UpdateAccountFundInfoLog.class,"update_accountfundinfo_log");
				 * UpdateAccountFundInfoLog updateAccountFundInfoLog=new
				 * UpdateAccountFundInfoLog();
				 * updateAccountFundInfoLog.setWebsite(website);
				 * updateAccountFundInfoLog.setCurrencyType(currencyType);
				 * updateAccountFundInfoLog.setOperatorName(ContextUtil.
				 * getCurrentUser().getUsername());
				 * updateAccountFundInfoLog.setUserName(appCustomer.getUserName(
				 * )); updateAccountFundInfoLog.setNewAccountFundInfo(Mapper.
				 * objectToJson(newlist));
				 * updateAccountFundInfoLog.setOldAccountFundInfo(Mapper.
				 * objectToJson(oldlist));
				 * updateAccountFundInfoLog.setCreatDate(new Date());
				 * mongoUtil.save(updateAccountFundInfoLog);
				 */

			}
			return map;
		} else {
			
			return null;
			/*
			// 资金正确
			// 保存币账户
			for (AccountFundInfo newafundcoin : newlist) {
				AppAccountSureold eda = remoteAppAccountSureoldService.getBycustomerIdAndcoinCode(customerId, appCustomer.getUserName(), newafundcoin.getCoinCode(), currencyType, website);
				if (null != eda) {
					eda.setHotMoney(newafundcoin.getHotMoney());
					eda.setColdMoney(newafundcoin.getColdMoney());
					eda.setLendMoney(newafundcoin.getLendMoney());
					eda.setSureTime(new Date());
					remoteAppAccountSureoldService.updateAccount(eda);
				} else {
					eda = new AppAccountSureold();
					eda.setCustomerId(customerId);
					eda.setUserName(appCustomer.getUserName());
					eda.setCoinCode(newafundcoin.getCoinCode());
					eda.setCurrencyType(currencyType);
					eda.setWebsite(website);
					eda.setAccountId(Long.valueOf("1"));
					eda.setHotMoney(newafundcoin.getHotMoney());
					eda.setColdMoney(newafundcoin.getColdMoney());
					eda.setLendMoney(newafundcoin.getLendMoney());
					eda.setSureTime(new Date());
					remoteAppAccountSureoldService.saveAccount(eda);

				}
			}

			return null;
		*/}

	}
	
	public ExDigitalmoneyAccountRedis getExDigitalmoneyAccountByRedis(String id) {
		RedisUtil redisUtilExDigitalmoneyAccount = new RedisUtil(ExDigitalmoneyAccountRedis.class);
		Object obj=redisUtilExDigitalmoneyAccount.get(id);
		if(null==obj){
			// 去数据拿
			ExDigitalmoneyAccount account =exDigitalmoneyAccountService.get(Long.valueOf(id));
			ExDigitalmoneyAccountRedis accountr=(ExDigitalmoneyAccountRedis)JSON.parseObject(JSON.toJSONString( account),ExDigitalmoneyAccountRedis.class);
			return accountr;
	
	
		}else{
			ExDigitalmoneyAccountRedis accountr=(ExDigitalmoneyAccountRedis)obj;
			return accountr;
		}
		
	
	}
	public void setExDigitalmoneyAccounttoRedis(ExDigitalmoneyAccountRedis exDigitalmoneyAccount) {
		RedisUtil redisUtilExDigitalmoneyAccount = new RedisUtil(ExDigitalmoneyAccountRedis.class);
		redisUtilExDigitalmoneyAccount.put(exDigitalmoneyAccount, exDigitalmoneyAccount.getId().toString());
	}

	public AppAccountRedis getAppAccountByRedis(String id) {
		 RedisUtil redisUtilAppAccount = new RedisUtil(AppAccountRedis.class);
		Object obj=redisUtilAppAccount.get(id);
		if(null==obj){
			AppAccount account =appAccountService.get(Long.valueOf(id));
			AppAccountRedis accountr=(AppAccountRedis)JSON.parseObject(JSON.toJSONString( account),AppAccountRedis.class);
			return accountr;
		}else{
			AppAccountRedis accountr=(AppAccountRedis)obj;
			return accountr;
		}
		
	}

	public void setAppAccounttoRedis(AppAccountRedis appAccount) {
		RedisUtil redisUtilAppAccount = new RedisUtil(AppAccountRedis.class);
		redisUtilAppAccount.put(appAccount, appAccount.getId().toString());
	}
	public String[] settlementByCustomerId(AppCustomer l, Date comeDate, Date nowDate, Date endDate, String currencyType, String website) {
		String[] relt = new String[2];
		Long customerId = l.getId();
		String beginDateString = DateUtil.dateToString(endDate, StringConstant.DATE_FORMAT_FULL);
		String endDateString = DateUtil.dateToString(nowDate, StringConstant.DATE_FORMAT_FULL);
		String productListsb = productListsb(website);
		AppReportSettlement exSettlementFinance = new AppReportSettlement();
		exSettlementFinance.setUserCode(l.getUserCode());
		exSettlementFinance.setStutus(-1);
		exSettlementFinance.setCustomerId(l.getId());
		exSettlementFinance.setSettleDate(comeDate);
		exSettlementFinance.setCurrencyType(currencyType);
		exSettlementFinance.setWebsite(website);
		exSettlementFinance.setUserName(l.getUserName());
		exSettlementFinance.setStartSettleDate(endDate);
		exSettlementFinance.setEndSettleDate(nowDate);

		RemoteAppPersonInfoService remoteAppPersonInfoService = (RemoteAppPersonInfoService) ContextUtil.getBean("remoteAppPersonInfoService");
		AppPersonInfo appPersonInfo = remoteAppPersonInfoService.getByCustomerId(l.getId());
		exSettlementFinance.setTrueName(appPersonInfo.getTrueName());
		exSettlementFinance.setCustomerType(appPersonInfo.getCustomerType());
		// 查出所以充值提现的成功数据,充值额,提现额，提现手续费
		Map<String, BigDecimal> getReWiByAppTransaction = getReWiByAppTransaction(customerId, currencyType, website, beginDateString, endDateString);
		exSettlementFinance.setRechargeMoney(getReWiByAppTransaction.get("rechargeMoney"));
		exSettlementFinance.setWithdrawMoney(getReWiByAppTransaction.get("withdrawMoney"));
		exSettlementFinance.setWithdrawFee(getReWiByAppTransaction.get("withdrawFee"));

		// 买成交额,卖成交额，成交手续费
		Map<String, BigDecimal> getTransactionByExEntrust = getTransactionByExEntrust(customerId, currencyType, website, beginDateString, endDateString);
		exSettlementFinance.setBuyTransactionMoney(getTransactionByExEntrust.get("buyTransactionMoney"));
		exSettlementFinance.setSellTransactionMoney(getTransactionByExEntrust.get("sellTransactionMoney"));
		exSettlementFinance.setTransactionFee(getTransactionByExEntrust.get("transactionFee"));

		// 期初金额，期末金额
		RemoteAppAccountService remoteAppAccountService = (RemoteAppAccountService) ContextUtil.getBean("remoteAppAccountService");
		AppAccount appAccount = remoteAppAccountService.getByCustomerIdAndType(l.getId(), currencyType, website);
		if (null == appAccount) {
			logger.error(l.getId() + currencyType + website + "资金账号为空");
			relt[0] = CodeConstant.CODE_FAILED;
			relt[1] = "资金账号为空";
			return relt;
		} else {
			// 期末资金
			exSettlementFinance.setEndMoney(appAccount.getHotMoney().add(appAccount.getColdMoney()));
			// 期末净资产
			BigDecimal rMBNetAsset = remoteExDigitalmoneyAccountService.getRMBNetAsset(l.getId(), null, currencyType, website);
			exSettlementFinance.setEndNetAsset(rMBNetAsset);
		}
		QueryFilter fiar = new QueryFilter(AppReportSettlement.class);
		fiar.addFilter("customerId=", l.getId());
		fiar.addFilter("currencyType=", currencyType);
		fiar.addFilter("website=", website);
		List<AppReportSettlement> fiarsclist = this.find(fiar);
		if (null == fiarsclist || fiarsclist.size() == 0) {
			exSettlementFinance.setBeginMoney(new BigDecimal("0")); // 期初资产
			exSettlementFinance.setBeginNetAsset(new BigDecimal("0"));// 期初净资产
		} else {
			exSettlementFinance.setBeginMoney(fiarsclist.get(fiarsclist.size() - 1).getEndMoney()); // 期初资产

			exSettlementFinance.setBeginNetAsset(fiarsclist.get(fiarsclist.size() - 1).getEndNetAsset());// 期末净资产
		}
		// 融资
		Map<String, BigDecimal> getLendByExDmLend = getLendByExDmLend(customerId, currencyType, currencyType, website, beginDateString, endDateString);
		exSettlementFinance.setLendMoney(getLendByExDmLend.get("lendMoney"));
		// 已还融资
		exSettlementFinance.setRepaylendMoney(getLendByExDmLend.get("repaylendMoney"));

		// 盈亏
		BigDecimal profitAndLossMoney = new BigDecimal("0");
		profitAndLossMoney = exSettlementFinance.getEndNetAsset().subtract(exSettlementFinance.getBeginNetAsset());
		profitAndLossMoney = profitAndLossMoney.subtract(exSettlementFinance.getRechargeMoney()).add(exSettlementFinance.getWithdrawMoney());

		// 计算币
		RemoteQueryFilter edafilter = new RemoteQueryFilter(ExDigitalmoneyAccount.class);
		edafilter.addFilter("customerId=", l.getId());
		edafilter.addFilter("currencyType=", currencyType);
		edafilter.addFilter("website=", website);
		edafilter.addFilter("coinCode_in", productListsb);
		edafilter.addFilter("currencyType=", currencyType);
		edafilter.addFilter("website=", website);
		List<ExDigitalmoneyAccount> listeda = remoteExDigitalmoneyAccountService.find(edafilter);
		for (ExDigitalmoneyAccount eda : listeda) {
			AppReportSettlementcoin coin = new AppReportSettlementcoin();
			coin.setUserCode(l.getUserCode());
			coin.setStutus(-1);
			coin.setCustomerId(l.getId());
			coin.setSettleDate(nowDate);
			coin.setStartSettleDate(endDate);
			coin.setEndSettleDate(nowDate);
			coin.setCoinCode(eda.getCoinCode());
			coin.setCurrencyType(currencyType);
			coin.setWebsite(website);
			coin.setAccountId(eda.getId());
			coin.setTrueName(appPersonInfo.getTrueName());
			coin.setCustomerType(appPersonInfo.getCustomerType());
			coin.setUserName(l.getUserName());

			String coinCode = eda.getCoinCode();
			// 计算币的买成交量，卖成交量
			Map<String, BigDecimal> getTranCoinByExEntrust = getTranCoinByExEntrust(customerId, coinCode, currencyType, website, beginDateString, endDateString);
			coin.setBuyTransactionCount(getTranCoinByExEntrust.get("buyTransactioncount"));
			coin.setSellTransactionCount(getTranCoinByExEntrust.get("sellTransactioncount"));

			// 币的期末持仓，期末资产
			coin.setEndCoinCount(eda.getHotMoney().add(eda.getColdMoney()));// 期末持仓
			String currentExchangPrices = ExchangeDataCache.getStringData(eda.getWebsite() + ":" + eda.getCurrencyType() + ":" + eda.getCoinCode() + ":" + ExchangeDataCache.CurrentExchangPrice);
			BigDecimal currentExchangPrice = new BigDecimal("0");
			if (!StringUtil.isEmpty(currentExchangPrices)) {
				currentExchangPrice = new BigDecimal(currentExchangPrices);
			}
			if (null != currentExchangPrice && currentExchangPrice.compareTo(new BigDecimal("0")) != 0) {
				coin.setEndMoney(coin.getEndCoinCount().multiply(currentExchangPrice)); // 期末资产
			}

			// 币的期初持仓，期初资产
			QueryFilter fi = new QueryFilter(AppReportSettlementcoin.class);
			fi.addFilter("customerId=", l.getId());
			fi.addFilter("coinCode=", eda.getCoinCode());
			fi.addFilter("currencyType=", currencyType);
			fi.addFilter("website=", website);
			List<AppReportSettlementcoin> arsclist = appReportSettlementcoinService.find(fi);
			if (null == arsclist || arsclist.size() == 0) {
				coin.setBeginCoinCount(new BigDecimal("0")); // 期初持仓
				coin.setBeginMoney(new BigDecimal("0")); // 期初资产
			} else {
				coin.setBeginCoinCount(arsclist.get(arsclist.size() - 1).getEndCoinCount()); // 期初持仓
				coin.setBeginMoney(arsclist.get(arsclist.size() - 1).getEndMoney()); // 期初资产
			}

			// 币的转入转出量
			Map<String, BigDecimal> getInOutByExDmTransaction = getInOutByExDmTransaction(customerId, coinCode, currencyType, website, beginDateString, endDateString);
			coin.setInCoinFee(getInOutByExDmTransaction.get("inCoinFee"));
			coin.setOutCoinFee(getInOutByExDmTransaction.get("outCoinFee"));
			coin.setInCoinCount(getInOutByExDmTransaction.get("inCoinCount"));
			coin.setOutCoinCount(getInOutByExDmTransaction.get("outCoinCount"));

			// 融币
			Map<String, BigDecimal> getLendcoinByExDmLend = getLendcoinByExDmLend(customerId, coinCode, currencyType, website, beginDateString, endDateString);
			coin.setLendMoney(getLendcoinByExDmLend.get("lendCoin"));

			// 已还融币
			Map<String, BigDecimal> getRepaycoinExDmLendIntent = getRepaycoinExDmLendIntent(customerId, coinCode, currencyType, website, beginDateString, endDateString);
			coin.setRepaylendMoney(getRepaycoinExDmLendIntent.get("repaylendcoin"));

			profitAndLossMoney = profitAndLossMoney.add((coin.getOutCoinCount().subtract(coin.getInCoinCount())).multiply(currentExchangPrice));

			appReportSettlementcoinService.save(coin);

		}

		// 盈亏

		exSettlementFinance.setProfitAndLossMoney(profitAndLossMoney);

		// 保存结算单
		this.save(exSettlementFinance);
		relt[0] = CodeConstant.CODE_SUCCESS;
		relt[1] = "";
		return relt;
	}

	@Override
	public Map<String, BigDecimal> getCoinByAccountDisable(Long customerId, String coinCode, String currencyType, String website, String beginDateString, String endDateString) {

		Map<String, BigDecimal> map = new HashMap<String, BigDecimal>();
		RemoteQueryFilter transactionMoneyfilter1 = new RemoteQueryFilter(AppAccountDisable.class);
		transactionMoneyfilter1.addFilter("customerId=", customerId);
		transactionMoneyfilter1.addFilter("currencyType=", currencyType);
		transactionMoneyfilter1.addFilter("website=", website);
		transactionMoneyfilter1.addFilter("coinCode=", coinCode);
		transactionMoneyfilter1.addFilter("status=", 1);
		List<AppAccountDisable> appAccountDisablelist = remoteExDigitalmoneyAccountService.findAppAccountDisable(transactionMoneyfilter1);
		BigDecimal disabletCount = new BigDecimal("0");
		for (AppAccountDisable e : appAccountDisablelist) {
			disabletCount = disabletCount.add(e.getTransactionCount());
		}

		map.put("disabletCount", disabletCount);
		return map;
	}
}
