/**
 * Copyright:  北京互融时代软件有限公司
 * @author:    Wu Shuiming
 * @version:   V1.0 
 * @Date:      2016年8月23日 下午9:13:48
 */
package hry.calculate.mvc.service.impl;

import hry.calculate.mvc.po.AppCalculate;
import hry.calculate.mvc.po.AppCalculateAllCustomer;
import hry.calculate.mvc.po.TotalCustomerForReport;
import hry.calculate.mvc.service.AppCalculateService;
import hry.calculate.mvc.service.AppFinanceService;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

/**
 * 
 * @author Wu shuiming
 * @date 2016年8月23日 下午9:13:48
 * 
 */
@Service(value="appCalculateService")
public class AppCalculateServiceImpl implements AppCalculateService{
	private static Logger logger = Logger.getLogger(AppCalculateServiceImpl.class);
	@Resource(name="appFinanceService")
	public AppFinanceService appFinanceService;
	
	// public String beginDate = "2016-08-10 14:13:37";
	
	// public String endDate = "2016-08-23 11:03:52";
	
	// public Integer type=1;
	
	// public String coinCode = "BTC";
	
	
	/**
	 * 查询总区分用户类型的总记录数
	 * 
	 */
	@Override
	public AppCalculate findCalculate(String beginDate,String endDate,Integer type,String coinCode) {
		
		AppCalculate appCalculate = new AppCalculate();
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date begin=null;Date end= null;
		try {
			begin = simpleDateFormat.parse(beginDate);
			end = simpleDateFormat.parse(endDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		// 新注册的用户
		Integer num = appFinanceService.createMemberNum(begin, end, type);
		appCalculate.setNewCustromers(num);
		// 活跃的用户   包括有充值提现等操作的用户  
		Integer num2 = appFinanceService.activityMemberNum(begin, end, type);
		appCalculate.setActiveCustromersFortransfer(num2);
		// 必须有成交记录的用户数
		Integer num3 = appFinanceService.transactionMemberNum(begin, end, type);
		appCalculate.setActiveCustromersForflatter(num3);
		// 当闭市的时候会员的总数 
		Integer custromerTotalNum = appFinanceService.memberNum(new Date(), num3);
		appCalculate.setCustromerTotalNum(custromerTotalNum);
		// 会员的总充值钱数
		BigDecimal postTotalMoneyForCustromer = appFinanceService.memberRechargeNum(begin, end, type, "2");
		appCalculate.setPostTotalMoneyForCustromer(postTotalMoneyForCustromer);
		
		// 当期初的时候会员的总钱数    和  当期末的时候的会员的总钱数
	
		BigDecimal custromerTotalMoneyStart = appFinanceService.memberBeginFund(type, 1);
		appCalculate.setCustromerTotalMoneyStart(custromerTotalMoneyStart);
		appCalculate.setCustromerTotalMoneyLast(custromerTotalMoneyStart);


		// 会员转入的总币数
		BigDecimal turnCodeCount = appFinanceService.memberCoinTransaferToNum(begin, end, type, coinCode);
		appCalculate.setTurnCodeCount(turnCodeCount);


		// 期初总持仓的数量     期末持仓的总数量 
		BigDecimal custromerCodeCountStart = appFinanceService.memberBeginFinance(type, coinCode);
		appCalculate.setCustromerCodeCountStart(custromerCodeCountStart);
		appCalculate.setCustromerCodeCountLast(custromerCodeCountStart);

		// 会员买的总成交额度
		BigDecimal custromerBuyForMoney = appFinanceService.memberBuyTurnover(begin, end, type, coinCode, 1);
		appCalculate.setCustromerBuyForMoney(custromerBuyForMoney);

		// 会员卖的总成交额度
		BigDecimal custromerSellForMoney = appFinanceService.memberSellTurnover(begin, end, type, coinCode);
		appCalculate.setCustromerSellForMoney(custromerSellForMoney);

		// 会员买的总成交量
		BigDecimal custromerBuyForNum = appFinanceService.memberBuyTransactionNum(begin, end, type, coinCode);
		appCalculate.setCustromerBuyForNum(custromerBuyForNum);

		// 会员卖的总成交量
		BigDecimal custromerSellForNum = appFinanceService.memberSellTransactionNum(begin, end, type, coinCode);
		appCalculate.setCustromerBuyForNum(custromerSellForNum);
	
		logger.error(appCalculate.toString());
		
		return appCalculate;
		
	}

	
	/**
	 * 
	 * 返回不分用户类型的总统计数
	 * 
	 */
	@Override
	public AppCalculateAllCustomer findCalculateAll(String beginDate,String endDate) {
		
		AppCalculateAllCustomer appCalculateAllCustomer = new AppCalculateAllCustomer();
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date begin=null;Date end= null;
		try {
			begin = simpleDateFormat.parse(beginDate);
			end = simpleDateFormat.parse(endDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		// 总充值的钱数不分用户类型
		BigDecimal postTotalMoney = appFinanceService.memberRechargeNum(begin, end, null, "2");
		appCalculateAllCustomer.setPostTotalMoney(postTotalMoney);
		
		// 提现的总额度    
		BigDecimal getTotalMoney = appFinanceService.memberWithdrawNum(begin, end, null, "2");
		appCalculateAllCustomer.setGetTotalMoney(getTotalMoney);
		
		// 出金总额  (去除手续费后  应出的所有的钱)
		BigDecimal outTotalMoney = appFinanceService.memberOutFinanceNum(begin, end, null, "2");
		appCalculateAllCustomer.setOutTotalMoney(outTotalMoney);
		
		// 交易手续费
		BigDecimal poundageMoney = appFinanceService.memberTransactionFeeNum(begin, end, null, "2");
		appCalculateAllCustomer.setPoundageMoney(poundageMoney);
		
		// 提现手续费总额
		BigDecimal withdrawalMoney = appFinanceService.memberWithdrawFeeNum(begin, end, null, "2");
		appCalculateAllCustomer.setWithdrawalMoney(withdrawalMoney);
		
		// 期初总资金(不分用户类型)
		BigDecimal totalMoneyStart = appFinanceService.memberBeginFund(null, 1);
		appCalculateAllCustomer.setTotalMoneyStart(totalMoneyStart);
		appCalculateAllCustomer.setTotalMoneyLast(totalMoneyStart);
		
		// 转入总量(不分用户类型)
		BigDecimal intoTotalMoney = appFinanceService.memberCoinTransaferToNum(begin, end, null, null);
		appCalculateAllCustomer.setIntoTotalMoney(intoTotalMoney);
		
		// 期初总数量(所用用户)   期末(所有用户) 应该与上面的值相等
		BigDecimal totalNumForCodeStart = appFinanceService.memberBeginFinance(null, null);
		appCalculateAllCustomer.setTotalNumForCodeStart(totalNumForCodeStart);
		appCalculateAllCustomer.setTotalNumForCodeLast(totalNumForCodeStart);
		
		// 成交额(不分用户类型  不分币种类型)
		BigDecimal totalMoneyForTrading = appFinanceService.memberSellTurnover(begin, end, null, null);
		appCalculateAllCustomer.setTotalMoneyForTrading(totalMoneyForTrading);
		
		// 成交量(不分用户类型)
		BigDecimal totalNumForTrading = appFinanceService.memberBuyTransactionNum(begin, end, null, null);
		appCalculateAllCustomer.setTotalNumForTrading(totalNumForTrading);
		
		logger.error(appCalculateAllCustomer.toString());
		
		return appCalculateAllCustomer;
	}


	/**
	 * 
	 * 查询平台会员管理报表                                                         
	 * 
	 */
	@Override
	public TotalCustomerForReport findTotalCustomerForReport() {
		
		return null;
	}

}
