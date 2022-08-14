/**
 * Copyright:  北京互融时代软件有限公司
 * @author:    Wu Shuiming
 * @version:   V1.0 
 * @Date:      2016年9月3日 上午11:59:15
 */
package hry.calculate.mvc.po;

import java.math.BigDecimal;

/**
 * 
 * leverageMoney
 * 平台资金单日结算报表
 * 
 * @author Wu shuiming
 * @date 2016年9月3日 上午11:59:15
 */
public class TotalAccountForReport {
	
	private BigDecimal postTotalMoney;  // 充值的总钱数
	
	private BigDecimal postFee;  // 充值的总手续费
	
	private BigDecimal getTotalMoney;  // 提现的总钱数
	
	private BigDecimal getFee;  // 提现的总手续费
	
	private BigDecimal orderSumMoney;  // 交易的总钱数
	
	private BigDecimal buySumMoney;  // 买交易的总钱数
	
	private BigDecimal sellSumMoney;  // 卖交易的总钱数
	
	private BigDecimal orderSumFee; // 交易的总手续费 
	
	private BigDecimal leverageMoney; // 平台已借出的钱
	
	private BigDecimal leverageFee;  // 平台锁收的杠杆手续费
	
	
	private BigDecimal rechargeDoingTotalMoney;  // 充值处理中总金额
	
	private BigDecimal availableTotalMoney;  // 可用资金总额
	
	private BigDecimal frozenTotalMoney;  // 冻结资金总额
	
	private BigDecimal userTotalMoney;  // 用户总资产

	

	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getUserTotalMoney() {
		return userTotalMoney;
	}

	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setUserTotalMoney(BigDecimal userTotalMoney) {
		this.userTotalMoney = userTotalMoney;
	}

	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getRechargeDoingTotalMoney() {
		return rechargeDoingTotalMoney;
	}

	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setRechargeDoingTotalMoney(BigDecimal rechargeDoingTotalMoney) {
		this.rechargeDoingTotalMoney = rechargeDoingTotalMoney;
	}

	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getAvailableTotalMoney() {
		return availableTotalMoney;
	}

	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setAvailableTotalMoney(BigDecimal availableTotalMoney) {
		this.availableTotalMoney = availableTotalMoney;
	}

	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getFrozenTotalMoney() {
		return frozenTotalMoney;
	}

	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setFrozenTotalMoney(BigDecimal frozenTotalMoney) {
		this.frozenTotalMoney = frozenTotalMoney;
	}

	public BigDecimal getBuySumMoney() {
		return buySumMoney;
	}

	public void setBuySumMoney(BigDecimal buySumMoney) {
		this.buySumMoney = buySumMoney;
	}

	public BigDecimal getSellSumMoney() {
		return sellSumMoney;
	}

	public void setSellSumMoney(BigDecimal sellSumMoney) {
		this.sellSumMoney = sellSumMoney;
	}

	public BigDecimal getPostTotalMoney() {
		return postTotalMoney;
	}

	public void setPostTotalMoney(BigDecimal postTotalMoney) {
		this.postTotalMoney = postTotalMoney;
	}

	public BigDecimal getPostFee() {
		return postFee;
	}

	public void setPostFee(BigDecimal postFee) {
		this.postFee = postFee;
	}

	public BigDecimal getGetTotalMoney() {
		return getTotalMoney;
	}

	public void setGetTotalMoney(BigDecimal getTotalMoney) {
		this.getTotalMoney = getTotalMoney;
	}

	public BigDecimal getGetFee() {
		return getFee;
	}

	public void setGetFee(BigDecimal getFee) {
		this.getFee = getFee;
	}

	public BigDecimal getOrderSumMoney() {
		return orderSumMoney;
	}

	public void setOrderSumMoney(BigDecimal orderSumMoney) {
		this.orderSumMoney = orderSumMoney;
	}

	public BigDecimal getOrderSumFee() {
		return orderSumFee;
	}

	public void setOrderSumFee(BigDecimal orderSumFee) {
		this.orderSumFee = orderSumFee;
	}

	public BigDecimal getLeverageMoney() {
		return leverageMoney;
	}

	public void setLeverageMoney(BigDecimal leverageMoney) {
		this.leverageMoney = leverageMoney;
	}

	public BigDecimal getLeverageFee() {
		return leverageFee;
	}

	public void setLeverageFee(BigDecimal leverageFee) {
		this.leverageFee = leverageFee;
	}
	
	
	
	
	
	
	
	

}
