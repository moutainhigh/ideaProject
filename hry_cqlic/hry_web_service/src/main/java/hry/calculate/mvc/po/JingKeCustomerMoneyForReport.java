/**
 * Copyright:  北京互融时代软件有限公司
 * @author:    Wu Shuiming
 * @version:   V1.0 
 * @Date:      2016年9月3日 上午11:59:15
 */
package hry.calculate.mvc.po;

import java.math.BigDecimal;

/**
 * 金科新加的用户资金报表
 * <p> TODO</p>
 * @author:         Zhang Lei 
 * @Date :          2017年4月12日 上午11:02:19
 */
public class JingKeCustomerMoneyForReport {
	
	private Long customerId;  // 用户ID
	
	private String userName;  // 用户手机号
	
	private String mobilePhone;  // 手机号
	
	private BigDecimal totalAvailableMoney;  // 总可用金额
	
	private BigDecimal totalFrozenMoney;  // 总冻结金额
	
	private BigDecimal totalRechargeMoney;  //总充值金额
	
	private BigDecimal totalWithdrawalsMoney;  // 总提现金额
	
	private BigDecimal moneyChangeRate; // 资金变动率
	
	private BigDecimal profitRate; // 收益率

	/**
	 * <p> TODO</p>
	 * @return:     Long
	 */
	public Long getCustomerId() {
		return customerId;
	}

	/** 
	 * <p> TODO</p>
	 * @return: Long
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getUserName() {
		return userName;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getMobilePhone() {
		return mobilePhone;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getTotalAvailableMoney() {
		return totalAvailableMoney;
	}

	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setTotalAvailableMoney(BigDecimal totalAvailableMoney) {
		this.totalAvailableMoney = totalAvailableMoney;
	}

	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getTotalFrozenMoney() {
		return totalFrozenMoney;
	}

	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setTotalFrozenMoney(BigDecimal totalFrozenMoney) {
		this.totalFrozenMoney = totalFrozenMoney;
	}

	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getTotalRechargeMoney() {
		return totalRechargeMoney;
	}

	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setTotalRechargeMoney(BigDecimal totalRechargeMoney) {
		this.totalRechargeMoney = totalRechargeMoney;
	}

	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getTotalWithdrawalsMoney() {
		return totalWithdrawalsMoney;
	}

	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setTotalWithdrawalsMoney(BigDecimal totalWithdrawalsMoney) {
		this.totalWithdrawalsMoney = totalWithdrawalsMoney;
	}

	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getMoneyChangeRate() {
		return moneyChangeRate;
	}

	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setMoneyChangeRate(BigDecimal moneyChangeRate) {
		this.moneyChangeRate = moneyChangeRate;
	}

	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getProfitRate() {
		return profitRate;
	}

	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setProfitRate(BigDecimal profitRate) {
		this.profitRate = profitRate;
	}
	
	
	

	
	
	
	
	
	

}
