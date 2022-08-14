/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Zhang Xiaofang
 * @version:      V1.0 
 * @Date:        2016年8月17日 下午2:29:45
 */
package hry.exchange.financialReports;

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
	 * @author:         Zhang Xiaofang
	 * @param:    @return
	 * @return: String 
	 * @Date :          2016年8月17日 下午2:32:22   
	 * @throws:
	 */
	public String createMemberNum(Date beginDate, Date endDate, String type);
	
	
	
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
	public String activityMemberNum(Date beginDate, Date endDate, String type);

	
	
	/**
	 * 
	 * 有成交记录的甲类客户
	 * @author:         Zhang Xiaofang
	 * @param:    @param beginDate
	 * @param:    @param endDate
	 * @param:    @param type
	 * @param:    @return
	 * @return: String 
	 * @Date :          2016年8月17日 下午2:36:33   
	 * @throws:
	 */
	public String transactionMemberNum(Date beginDate, Date endDate, String type);

	
	
	/**
	 * 
	 * 期末客户总数
	 * @author:         Zhang Xiaofang
	 * @param:    @param beginDate
	 * @param:    @param endDate
	 * @param:    @param type
	 * @param:    @return
	 * @return: String 
	 * @Date :          2016年8月17日 下午2:38:29   
	 * @throws:
	 */
	public String MemberNum(Date beginDate, Date endDate, String type);





  /**
   * 
   * 账户充值总额
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
	public String MemberRechargeNum(Date beginDate, Date endDate, String type, String status);


	
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
	public String MemberWithdrawNum(Date beginDate, Date endDate, String type, String status);

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
	public String MemberWithdrawFeeNum(Date beginDate, Date endDate, String type, String status);

	
	
	
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
	public String MemberTransactionFeeNum(Date beginDate, Date endDate, String type, String status);

	
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
	public String MemberOutFinanceNum(Date beginDate, Date endDate, String type, String status);

	/**
	 * 
	 * 会员期初资金：不扣除委托冻结
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
	public String MemberBeginFund(Date beginDate, Date endDate, String type, String status);

	
	/**
	 * 
	 * 会员期末资金：不扣除委托冻结
	 * @author:         Zhang Xiaofang
	 * @param:    @param beginDate
	 * @param:    @param endDate
	 * @param:    @param type
	 * @param:    @param status
	 * @param:    @return
	 * @return: String 
	 * @Date :          2016年8月17日 下午2:43:13   
	 * @throws:
	 */
	public String MemberEndFund(Date beginDate, Date endDate, String type);

	
	
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
	public String MemberCoinTransaferToNum(Date beginDate, Date endDate, String type, String coinCode);

	
	
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
	public String MemberBeginPositions(Date beginDate, Date endDate, String type, String coinCode);

	
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
	public String MemberEndPositions(Date beginDate, Date endDate, String type, String coinCode);

	
	
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
	public String MemberBuyTurnover(Date beginDate, Date endDate, String type, String coinCode);

	
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
	public String MemberSellTurnover(Date beginDate, Date endDate, String type, String coinCode);

	/**
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
	public String MemberBuyTransactionNum(Date beginDate, Date endDate, String type, String coinCode);

	
	/**
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
	public String MemberSellTransactionNum(Date beginDate, Date endDate, String type, String coinCode);

	
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
	public String MemberBeginFinance(Date beginDate, Date endDate, String type, String coinCode);

	
	
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
	public String MemberEndFinance(Date beginDate, Date endDate, String type, String coinCode);

}	

