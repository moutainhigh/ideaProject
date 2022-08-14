package hry.calculate.mvc.service.impl;

import hry.calculate.mvc.dao.AppFinanceDao;
import hry.calculate.mvc.service.AppFinanceService;

import java.math.BigDecimal;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service(value="appFinanceService")
public class AppFinanceServiceImpl implements AppFinanceService{

	@Resource(name="appFinanceDao")
	public AppFinanceDao appFinanceDao;
	
	/**
	 * 
	 * 新增会员数
	 * 
	 * beginDate 开始时间 必填(格式：'2016-08-10')
	 * 
	 * endDate 结束时间 必填(格式：'2016-08-10')
	 * 
	 * type 表示用户的类型 --- 甲类 1   乙类 2   丙类 3
	 * 
	 * 
	 * @author:         Zhang Xiaofang
	 * @param:    @return
	 * @return: String 
	 * @Date :          2016年8月17日 下午2:32:22   
	 * @throws:
	 */
	@Override
	public Integer createMemberNum(Date beginDate, Date endDate, Integer type) {
		
	// 	java.sql.Date date_sql = );
		
		Integer num = appFinanceDao.createMemberNum(new java.sql.Date(beginDate.getTime()), new java.sql.Date(endDate.getTime()), type);
		
		return num;
	}

	
	/**
	 * 
	 * 有数字变动的客户，包括出入金、转入、交易等引起的变动
	 * 
	 * @author:         Zhang Xiaofang
	 * @param:    @param beginDate
	 * @param:    @param endDate
	 * @param:    @param type
	 * @param:    @return
	 * @return:   String 
	 * @Date :          2016年8月17日 下午2:35:16   
	 * @throws:
	 */
	@Override
	public Integer activityMemberNum(Date beginDate, Date endDate, Integer type) {
		
		Integer num = appFinanceDao.activityMemberNum(new java.sql.Date(beginDate.getTime()), new java.sql.Date(endDate.getTime()), type);
		
		return num;
	}

	
	/**
	 * 
	 * 有成交记录的甲类客户                                                     
	 * 
	 * @author:         Zhang Xiaofang
	 * @param:    @param beginDate
	 * @param:    @param endDate
	 * @param:    @param type
	 * @param:    @return
	 * @return: String 
	 * @Date :          2016年8月17日 下午2:36:33   
	 * @throws:
	 */
	@Override
	public Integer transactionMemberNum(Date beginDate, Date endDate, Integer type) {
		
		Integer num = appFinanceDao.transactionMemberNum(new java.sql.Date(beginDate.getTime()), new java.sql.Date(endDate.getTime()), type);
		
		return num;
	}

	/**
	 * 
	 * 期末客户总数
	 * 
	 * @author:         Zhang Xiaofang
	 * @param:    @param date (当天的日期)
	 * @param:    @param type
	 * @param:    @return
	 * @return: String 
	 * @Date :          2016年8月17日 下午2:38:29   
	 * @throws:
	 */
	@Override
	public Integer memberNum(Date date, Integer type) {
		
		Integer num = appFinanceDao.memberNum(new java.sql.Date(date.getTime()) , type);
		
		return num;
	}

	
	  /**
	   * 
	   * 账户充值总额(不计未确认的订单)
	   * 
	   * status --- 1待审核 2已完成 3以否决
	   * 
	   * @author:         Zhang Xiaofang
	   * @param:    @param beginDate
	   * @param:    @param endDate
	   * @param:    @param type  会员类型
	   * @param:    @status   订单状态
	   * @param:    @return
	   * @return: String 
	   * @Date :          2016年8月17日 下午2:39:08   
	   * @throws:
	   */
	@Override
	public BigDecimal memberRechargeNum(Date beginDate, Date endDate, Integer type,
			String status) {
		
		BigDecimal money = appFinanceDao.memberRechargeNum(new java.sql.Date(beginDate.getTime()), new java.sql.Date(endDate.getTime()), status, type);
		
		return money;
	}
	
	
	
// ----------------------------------------------------	
	
	
	/**
	 * 提现总额
	 * status --- 1待审核 2已完成 3以否决
	 * 
	 * @author:         Zhang Xiaofang
	 * @param:    @param beginDate
	 * @param:    @param endDate
	 * @param:    @param type
	 * @param:    @param status
	 * @param:    @return
	 * @return: String 
	 * @Date :          2016年8月17日 下午2:57:33   
	 * @throws:
	 */
	@Override
	public BigDecimal memberWithdrawNum(Date beginDate, Date endDate, Integer type,
			String status) {
		
		BigDecimal money = appFinanceDao.memberWithdrawNum(new java.sql.Date(beginDate.getTime()), new java.sql.Date(endDate.getTime()), status, type);
		
		return money;
	}

	
	/**
	 * 
	 * type 表示用户的类别(1表示甲类  2表示乙类  3表示丙类)
	 * status 表示订单的状态( 这种格式 1,2)
	 * status --- 1待审核 2已完成 3以否决
	 * 
	 * 提现手续费总额：
	 * @author:         Zhang Xiaofang
	 * @param:    @param beginDate
	 * @param:    @param endDate
	 * @param:    @param type
	 * @param:    @param status
	 * @param:    @return
	 * @return: String 
	 * @Date :          2016年8月17日 下午2:58:16   
	 * @throws:
	 */
	@Override
	public BigDecimal memberWithdrawFeeNum(Date beginDate, Date endDate,
			Integer type, String status) {
		
		BigDecimal money = appFinanceDao.memberWithdrawFeeNum(new java.sql.Date(beginDate.getTime()), new java.sql.Date(endDate.getTime()), status, type);
		
		return money;
	}

	
	/**
	 * 
	 * 交易手续费总额：
	 *
	 * status --- 类型 1:买    2:卖
	 * 
	 *
	 * @author:         Zhang Xiaofang
	 * @param:    @param beginDate
	 * @param:    @param endDate
	 * @param:    @param type
	 * @param:    @param status
	 * @param:    @return
	 * @return: String 
	 * @Date :          2016年8月17日 下午2:58:49   
	 * @throws:
	 */
	@Override
	public BigDecimal memberTransactionFeeNum(Date beginDate, Date endDate,
			Integer type, String status) {
		BigDecimal money = appFinanceDao.memberTransactionFeeNum(new java.sql.Date(beginDate.getTime()), new java.sql.Date(endDate.getTime()), status, type);
		return money;
	}

	
	/**
	 * 
	 * 出金总额：不计未确认额。
	 * 
	 * states --- 1待审核 2已完成 3以否决
	 * 
	 * 
	 * @author:         Zhang Xiaofang
	 * @param:    @param beginDate
	 * @param:    @param endDate
	 * @param:    @param type
	 * @param:    @param status
	 * @param:    @return
	 * @return: String 
	 * @Date :          2016年8月17日 下午3:00:26   
	 * @throws:
	 */
	@Override
	public BigDecimal memberOutFinanceNum(Date beginDate, Date endDate,
			Integer type, String status) {
		BigDecimal money = appFinanceDao.memberOutFinanceNum(new java.sql.Date(beginDate.getTime()), new java.sql.Date(endDate.getTime()), status, type);
		return money;
	}

// -------------------------------------------------------------------
	
	/**
	 * 
	 * custromerTotalMoneyStart
	 * 
	 * 会员期初资金：不扣除委托冻结    
	 * 
	 * type 表示用户是的级别
	 * 
	 * states  0表示不可用     1可用
	 *       
	 *       
	 * @author:   Zhang Xiaofang
	 * @param:    @param beginDate
	 * @param:    @param endDate
	 * @param:    @param type
	 * @param:    @param status
	 * @param:    @return
	 * @return: String 
	 * @Date :          2016年8月17日 下午2:41:49   
	 * @throws:
	 * 
	 */
	@Override
	public BigDecimal memberBeginFund(Integer type,
			Integer status) {
		
		BigDecimal money = appFinanceDao.memberBeginFund(status, type);
		
		return money;
	}

	

//	@Override
//	public String MemberEndFund(Date beginDate, Date endDate, Integer type) {
//		
//		return null;
//	}

	
	/**
	 * 
	 * 会员转入总量：账户转入总圣币量。不计未确认额。
	 * @author:         Zhang Xiaofang
	 * @param:    @param beginDate
	 * @param:    @param endDate
	 * @param:    @param type
	 * @param:    @param coinCode
	 * @param:    @return
	 * @return: String 
	 * @Date :          2016年8月17日 下午2:44:42   
	 * @throws:
	 */
	@Override
	public BigDecimal memberCoinTransaferToNum(Date beginDate, Date endDate,
			Integer type, String coinCode) {
		
		BigDecimal codeNum = appFinanceDao.memberCoinTransaferToNum(new java.sql.Date(beginDate.getTime()), new java.sql.Date(endDate.getTime()), type, coinCode);
		
		return codeNum;
	}

	
	
	/**
	 * type 用户的级别(甲 乙 丙) --- 1(普通的，默认)，乙类账号2，丙类账户3
	 * 
	 * 会员期初持仓：
	 * @author:         Zhang Xiaofang
	 * @param:    @param beginDate
	 * @param:    @param endDate
	 * @param:    @param type
	 * @param:    @param coinCode
	 * @param:    @return
	 * @return: String 
	 * @Date :          2016年8月17日 下午2:46:54   
	 * @throws:
	 */
	@Override
	public BigDecimal memberBeginPositions(Date beginDate, Date endDate,
			Integer type, String coinCode) {
		
		BigDecimal codeNum = appFinanceDao.memberBeginPositions(new java.sql.Date(beginDate.getTime()), new java.sql.Date(endDate.getTime()), type, coinCode);
		
		return codeNum;
	}
	

//	@Override
//	public String MemberEndPositions(Date beginDate, Date endDate, Integer type,
//			String coinCode) {
//		
//		return null;
//	}

	
	
	/**
	 * 会员买成交额：
	 * 
	 * orderType(1买   2卖)
	 * type(1甲  2乙  3丙)
	 * 
	 * @author:         Zhang Xiaofang
	 * @param:    @param beginDate
	 * @param:    @param endDate
	 * @param:    @param type
	 * @param:    @param coinCode
	 * @param:    @return
	 * @return: String 
	 * @Date :          2016年8月17日 下午2:48:46   
	 * @throws:
	 */
	@Override
	public BigDecimal memberBuyTurnover(Date beginDate, Date endDate, Integer type,
			String coinCode,Integer orderType) {
		BigDecimal sumMoney = appFinanceDao.memberTurnover(new java.sql.Date(beginDate.getTime()), new java.sql.Date(endDate.getTime()), type, coinCode, 1);
		return sumMoney;
	}

	
	
	/**
	 * 会员卖成交额：
	 * 
	 * type : 用户的级别
	 * 
	 * coinCode : 'BTC' 'LTC'
	 * 
	 * @author:         Zhang Xiaofang
	 * @param:    @param beginDate
	 * @param:    @param endDate
	 * @param:    @param type
	 * @param:    @param coinCode
	 * @param:    @return
	 * @return: String 
	 * @Date :          2016年8月17日 下午2:49:07   
	 * @throws:
	 * 
	 */
	@Override
	public BigDecimal memberSellTurnover(Date beginDate, Date endDate, Integer type,
			String coinCode) {
		BigDecimal sumMoney = appFinanceDao.memberTurnover(new java.sql.Date(beginDate.getTime()), new java.sql.Date(endDate.getTime()), type, coinCode, 2);
		return sumMoney;
	}

	
	/**
	 * 
	 *	会员买成交量：
	 *
	 *  orderType : (1买   2卖)
	 *  
	 *  最后一个参数是 : (1买   2卖)
	 *
	 * @author:         Zhang Xiaofang
	 * @param:    @param beginDate
	 * @param:    @param endDate
	 * @param:    @param type
	 * @param:    @param coinCode
	 * @param:    @return
	 * @return: String 
	 * @Date :          2016年8月17日 下午2:55:33   
	 * @throws:
	 * 
	 */
	@Override
	public BigDecimal memberBuyTransactionNum(Date beginDate, Date endDate,
			Integer type, String coinCode) {
		BigDecimal sumCount = appFinanceDao.memberTransactionNum(new java.sql.Date(beginDate.getTime()), new java.sql.Date(endDate.getTime()), type, coinCode, 1);
		return sumCount;
	}

	
	/**
	 * 会员卖成交量：
	 * 
	 * @author:         Zhang Xiaofang
	 * @param:    @param beginDate
	 * @param:    @param endDate
	 * @param:    @param type
	 * @param:    @param coinCode
	 * @param:    @return
	 * @return: String 
	 * @Date :          2016年8月17日 下午2:55:59   
	 * @throws:
	 */
	@Override
	public BigDecimal memberSellTransactionNum(Date beginDate, Date endDate,
			Integer type, String coinCode) {
		
		BigDecimal sumCount = appFinanceDao.memberTransactionNum(new java.sql.Date(beginDate.getTime()), new java.sql.Date(endDate.getTime()), type, coinCode, 2);
		
		return sumCount;
	}

	
	/**
	 * custromerCodeCountStart
	 * 
	 * 期初会员总币数：不扣除委托冻结                 
	 * 
	 * @author:         Zhang Xiaofang
	 * @param:    @param beginDate
	 * @param:    @param endDate
	 * @param:    @param type
	 * @param:    @param coinCode
	 * @param:    @return
	 * @return: String 
	 * @Date :          2016年8月17日 下午3:01:26   
	 * @throws:
	 * 
	 */
	@Override
	public BigDecimal memberBeginFinance(Integer type,String coinCode) {
		BigDecimal moneyCount = appFinanceDao.memberBeginFinance(type, coinCode);
		return moneyCount;
	}

	
	

//	@Override
//	public String MemberEndFinance(Date beginDate, Date endDate, Integer type,
//			String coinCode) {
//		
//		return null;
//	}

}
