package hry.calculate.mvc.dao;

import hry.calculate.mvc.po.CalculatePo;
import hry.core.mvc.dao.base.BaseDao;

import java.math.BigDecimal;
import java.sql.Date;

import org.apache.ibatis.annotations.Param;



public interface AppFinanceDao extends BaseDao<CalculatePo,Long> {

	/**
	 * 
	 * beginDate 开始时间(必填)
	 * endDate 结束时间(必填)
	 * type 类型 --- 1甲类  2乙类  3丙类
	 * 
	 * 
	 * @author:    Wu Shuiming
	 * @version:   V1.0 
	 * @date:      2016年8月22日 上午11:16:37
	 */
	public Integer createMemberNum(@Param(value = "beginDate") Date beginDate, @Param(value = "endDate") Date endDate, @Param(value = "type") Integer type);

	
	/**
	 * 查询用户的资金变更的用户数量
	 * 
	 * beginDate 开始时间
	 * endDate 结束时间
	 * type 类型 --- 1甲类  2乙类  3丙类
	 * 
	 * @author:    Wu Shuiming
	 * @version:   V1.0 
	 * @date:      2016年8月22日 上午11:58:37
	 */
	public Integer activityMemberNum(@Param(value = "beginDate") Date beginDate, @Param(value = "endDate") Date endDate, @Param(value = "type") Integer type);

	
	/**
	 * 有成交记录的甲类客户
	 * 
	 * beginDate 开始时间
	 * endDate 结束时间
	 * type 类型 --- 1甲类  2乙类  3丙类
	 * 
	 * @author:    Wu Shuiming
	 * @version:   V1.0 
	 * @date:      2016年8月22日 上午11:58:37
	 */
	public Integer transactionMemberNum(@Param(value = "beginDate") Date beginDate, @Param(value = "endDate") Date endDate, @Param(value = "type") Integer type);
		
	
	/**
	 * 期末客户总数(指定日期查询当天的活跃用户数量)
	 * date(当天的日期)
	 * type 类型 --- 1甲类  2乙类  3丙类
	 * 
	 * @author:    Wu Shuiming
	 * @version:   V1.0 
	 * @date:      2016年8月22日 下午2:47:16
	 */
	public Integer memberNum(@Param(value = "date") Date date, @Param(value = "type") Integer type);
	
	/**
	 * 
	 * 查询指定时间内的充值总数量  条件为用户类型 以及订单的状态
	 * 
	 * beginDate 开始时间
	 * endDate 结束时间
	 * states 订单的状态
	 * type 用户的类型
	 * 
	 * @author:    Wu Shuiming
	 * @version:   V1.0 
	 * @date:      2016年8月22日 下午3:58:37
	 */
	public BigDecimal memberRechargeNum(@Param(value = "beginDate") Date beginDate, @Param(value = "endDate") Date endDate, @Param(value = "states") String states, @Param(value = "type") Integer type);
	
	
	/**
	 * 
	 * 查询指定时间内提现的总数量 
	 * 
	 * @author:    Wu Shuiming
	 * @version:   V1.0 
	 * @date:      2016年8月22日 下午4:23:37
	 */
	public BigDecimal memberWithdrawNum(@Param(value = "beginDate") Date beginDate, @Param(value = "endDate") Date endDate, @Param(value = "states") String states, @Param(value = "type") Integer type);
	
	
	/**
	 * 查询指定时间段内提现的总手续费是多少
	 * 
	 * @author:    Wu Shuiming
	 * @version:   V1.0 
	 * @date:      2016年8月22日 下午5:01:47
	 */
	public BigDecimal memberWithdrawFeeNum(@Param(value = "beginDate") Date beginDate, @Param(value = "endDate") Date endDate, @Param(value = "states") String states, @Param(value = "type") Integer type);
	
	
	/**
	 * 
	 * 查询指定时间段内成交单的总手续费
	 * 
	 * @author:    Wu Shuiming
	 * @version:   V1.0 
	 * @date:      2016年8月22日 下午5:19:47
	 */
	public BigDecimal memberTransactionFeeNum(@Param(value = "beginDate") Date beginDate, @Param(value = "endDate") Date endDate, @Param(value = "states") String states, @Param(value = "type") Integer type);
	
	
	/**
	 * 指定时间段内查询用户实际提现的总金额数
	 * 
	 * 
	 * @author:    Wu Shuiming
	 * @version:   V1.0 
	 * @date:      2016年8月22日 下午6:13:53
	 */
	public BigDecimal memberOutFinanceNum(@Param(value = "beginDate") Date beginDate, @Param(value = "endDate") Date endDate, @Param(value = "states") String states, @Param(value = "type") Integer type);
	
	
	/**
	 * 会员期初资金：不扣除委托冻结  
	 * states 0表示不可用     1可用
	 * type 表示用户的等级
	 * 
	 * @author:    Wu Shuiming
	 * @version:   V1.0 
	 * @date:      2016年8月22日 下午7:26:36
	 */
	public BigDecimal memberBeginFund(@Param(value = "states") Integer states, @Param(value = "type") Integer type);
	
	
	/**
	 * 
	 * type: 交易类型(1充币 ，2提币)
	 * 
	 * @author:    Wu Shuiming
	 * @version:   V1.0 
	 * @date:      2016年8月23日 上午10:05:12
	 */
	public BigDecimal memberCoinTransaferToNum(@Param(value = "beginDate") Date beginDate, @Param(value = "endDate") Date endDate, @Param(value = "type") Integer type, @Param(value = "coinCode") String coinCode);
	
	
	/**
	 * type 用户的级别(甲 乙 丙)
	 * 
	 * @author:    Wu Shuiming
	 * @version:   V1.0 
	 * @date:      2016年8月23日 上午10:59:29
	 */
	public BigDecimal memberBeginPositions(@Param(value = "beginDate") Date beginDate, @Param(value = "endDate") Date endDate, @Param(value = "type") Integer type, @Param(value = "coinCode") String coinCode);
	
	
	/**
	 * 
	 * 
	 * @author:    Wu Shuiming
	 * @version:   V1.0 
	 * @date:      2016年8月23日 上午11:39:57
	 */
	public BigDecimal memberBuyTurnover(@Param(value = "beginDate") Date beginDate, @Param(value = "endDate") Date endDate, Integer type,
                                        String coinCode);
	
	/**
	 * 会员买成交额：
	 * type : (1甲  2乙  3丙)    
	 * orderType : (1买   2卖)
	 * 
	 * @author:    Wu Shuiming
	 * @version:   V1.0 
	 * @date:      2016年8月23日 下午3:12:16
	 */
	public BigDecimal memberTurnover(@Param(value = "beginDate") Date beginDate, @Param(value = "endDate") Date endDate, @Param(value = "type") Integer type,
                                     @Param(value = "coinCode") String coinCode, @Param(value = "orderType") Integer orderType);
	
	
	/**
	 * 会员买成交量：
	 * 
	 * type : (1甲  2乙  3丙)    
	 * orderType : (1买   2卖)
	 * 
	 * @author:    Wu Shuiming
	 * @version:   V1.0 
	 * @date:      2016年8月23日 下午3:56:38
	 */
	public BigDecimal memberTransactionNum(@Param(value = "beginDate") Date beginDate, @Param(value = "endDate") Date endDate, @Param(value = "type") Integer type,
                                           @Param(value = "coinCode") String coinCode, @Param(value = "orderType") Integer orderType);
	
	
	/**
	 * 
	 * 期初会员总币数：不扣除委托冻结 
	 * 
	 * 
	 * @author:    Wu Shuiming
	 * @version:   V1.0 
	 * @date:      2016年8月23日 下午4:28:00
	 */
	public BigDecimal memberBeginFinance(@Param(value = "type") Integer type, @Param(value = "coinCode") String coinCode);
	
	
	

	
}





