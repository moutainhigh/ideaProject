/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Zhang Xiaofang
 * @version:      V1.0 
 * @Date:        2016年8月17日 下午2:29:45
 */
package hry.calculate.mvc.service;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p> TODO</p>
 * @author:         Zhang Xiaofang 
 * @Date :          2016年8月17日 下午2:29:45 
 */
public interface AppFinanceService {

	
	
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
	public Integer createMemberNum(Date beginDate, Date endDate, Integer type);
	
	
	
	/**
	 * 
	 * 有数字变动的客户，包括出入金、转入、交易等引起的变动
	 * @author:         Zhang Xiaofang
	 * @param:    @param beginDate
	 * @param:    @param endDate
	 * @param:    @param type
	 * @param:    @return
	 * @return: String 
	 * @Date :          2016年8月17日 下午2:35:16   
	 * @throws:
	 */
	public Integer activityMemberNum(Date beginDate, Date endDate, Integer type);

	
	
	/**
	 * 
	 * 有成交记录的甲类客户
	 * @author:         Zhang Xiaofang
	 * @param:    @param beginDate
	 * @param:    @param endDates
	 * @param:    @param type
	 * @param:    @return
	 * @return: String 
	 * @Date :          2016年8月17日 下午2:36:33   
	 * @throws:
	 */
	public Integer transactionMemberNum(Date beginDate, Date endDate, Integer type);

	
	
	/**
	 * 
	 * 期末客户总数(当天的开盘时间到闭盘时间  中用户的交易的客户总数)
	 * @author:         Zhang Xiaofang
	 * @param:    @param date (日期当天的日期)
	 * @param:    @param type
	 * @param:    @return
	 * @return: String 
	 * @Date :          2016年8月17日 下午2:38:29   
	 * @throws:
	 */
	public Integer memberNum(Date date, Integer type);





  /**
   * 
   * 账户充值总额(不计未确认的订单)
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
	public BigDecimal memberRechargeNum(Date beginDate, Date endDate, Integer type, String status);


	
	/**
	 * 
	 * 提现总额
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
	public BigDecimal memberWithdrawNum(Date beginDate, Date endDate, Integer type, String status);

	/**
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
	public BigDecimal memberWithdrawFeeNum(Date beginDate, Date endDate, Integer type, String status);

	
	
	
	/**
	 * 
	 *交易手续费总额：
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
	public BigDecimal memberTransactionFeeNum(Date beginDate, Date endDate, Integer type, String status);

	
	/**
	 * 
	 * 出金总额：不计未确认额。
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
	public BigDecimal memberOutFinanceNum(Date beginDate, Date endDate, Integer type, String status);

	/**
	 * 
	 * 会员期初资金：不扣除委托冻结          
	 * 
	 * @author:         Zhang Xiaofang
	 * @param:    @param beginDate
	 * @param:    @param endDate
	 * @param:    @param type
	 * @param:    @param status
	 * @param:    @return
	 * @return: String 
	 * @Date :          2016年8月17日 下午2:41:49   
	 * @throws:
	 */
	public BigDecimal memberBeginFund(Integer type, Integer status);

	

// 	public String MemberEndFund(Date beginDate ,Date endDate ,Integer type);

	
	
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
	public BigDecimal memberCoinTransaferToNum(Date beginDate, Date endDate, Integer type, String coinCode);
		
	
	
	/**
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
	public BigDecimal memberBeginPositions(Date beginDate, Date endDate, Integer type, String coinCode);

	
	/**
	 * 
	 * 会员期末持仓：
	 * @author:         Zhang Xiaofang
	 * @param:    @param beginDate
	 * @param:    @param endDate
	 * @param:    @param type
	 * @param:    @param coinCode
	 * @param:    @return
	 * @return: String 
	 * @Date :          2016年8月17日 下午2:47:23   
	 * @throws:
	 */
// 	public String MemberEndPositions(Date beginDate ,Date endDate ,Integer type,String  coinCode);

	
	
	/**
	 * 
	 * 会员买成交额：
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
	public BigDecimal memberBuyTurnover(Date beginDate, Date endDate, Integer type, String coinCode, Integer orderType);

	
	/**
	 * 
	 * 会员卖成交额：
	 * @author:         Zhang Xiaofang
	 * @param:    @param beginDate
	 * @param:    @param endDate
	 * @param:    @param type
	 * @param:    @param coinCode
	 * @param:    @return
	 * @return: String 
	 * @Date :          2016年8月17日 下午2:49:07   
	 * @throws:
	 */
	public BigDecimal memberSellTurnover(Date beginDate, Date endDate, Integer type, String coinCode);

	/**
	 * 
	 * type : (1甲  2乙  3丙)    
	 * 
	 * 
	 *会员买成交量：
	 * @author:         Zhang Xiaofang
	 * @param:    @param beginDate
	 * @param:    @param endDate
	 * @param:    @param type
	 * @param:    @param coinCode
	 * @param:    @return
	 * @return: String 
	 * @Date :          2016年8月17日 下午2:55:33   
	 * @throws:
	 */
	public BigDecimal memberBuyTransactionNum(Date beginDate, Date endDate, Integer type, String coinCode);

	
	/**
	 * 
	 * 
	 *会员卖成交量：
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
	public BigDecimal memberSellTransactionNum(Date beginDate, Date endDate, Integer type, String coinCode);

	
	/**
	 * 
	 * 期初资金：不扣除委托冻结
	 * @author:         Zhang Xiaofang
	 * @param:    @param beginDate
	 * @param:    @param endDate
	 * @param:    @param type
	 * @param:    @param coinCode
	 * @param:    @return
	 * @return: String 
	 * @Date :          2016年8月17日 下午3:01:26   
	 * @throws:
	 */
	public BigDecimal memberBeginFinance(Integer type, String coinCode);

	
	
	/**
	 * 
	 * 期末资金：不扣除委托冻结
	 * @author:         Zhang Xiaofang
	 * @param:    @param beginDate
	 * @param:    @param endDate
	 * @param:    @param type
	 * @param:    @param coinCode
	 * @param:    @return
	 * @return: String 
	 * @Date :          2016年8月17日 下午3:01:47   
	 * @throws:
	 */
// 	public String MemberEndFinance(Date beginDate ,Date endDate ,Integer type,String  coinCode);




}	

