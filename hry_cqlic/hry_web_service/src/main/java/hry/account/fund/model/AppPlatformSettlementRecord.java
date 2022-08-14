/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Liu Shilei
 * @version:      V1.0 
 * @Date:        2016年3月31日 下午6:17:33
 */
package hry.account.fund.model;

import static javax.persistence.GenerationType.IDENTITY;
import hry.core.mvc.model.BaseModel;
import hry.core.mvc.model.ModelUtil;
import hry.customer.person.model.AppPersonInfo;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

/**平台结算记录
 * 
 * <p> TODO</p>
 * @author:         Zhang Lei 
 * @Date :          2017年4月7日 下午5:24:14
 */
@Table(name="app_platform_settlement_record")
public class AppPlatformSettlementRecord extends BaseModel {
	
	/**  
	 * @Fields : TODO   
	 */
	private static final long serialVersionUID = 1123123L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	
	@Column(name="settlementDay")
	private Date settlementDay; //结算时间
	
	@Column(name="accountMoneyNewBefore")
	private BigDecimal accountMoneyNewBefore; //结算前总账户金额
	
	@Column(name="accountMoneyNewAfter")
	private BigDecimal accountMoneyNewAfter; //结算后总账户金额
	
	@Column(name="rechargeMoneyOneSettlement")
	private BigDecimal rechargeMoneyOneSettlement; //上次结算到此次结算充值总额
	
	@Column(name="withdrawalsMoneyOneSettlement")
	private BigDecimal withdrawalsMoneyOneSettlement; //上次结算到此次结算提现额
	
	@Column(name="transactionFeeOneSettlement")
	private BigDecimal transactionFeeOneSettlement; //上次结算到此次结算交易手续费总额
	
	@Column(name="rechargeOrWithdrawalsFeeOneSettlement")
	private BigDecimal rechargeOrWithdrawalsFeeOneSettlement; //上次结算到此次结算充值提现手续费总额

	/**
	 * <p> TODO</p>
	 * @return:     Long
	 */
	public Long getId() {
		return id;
	}

	/** 
	 * <p> TODO</p>
	 * @return: Long
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * <p> TODO</p>
	 * @return:     Date
	 */
	public Date getSettlementDay() {
		return settlementDay;
	}

	/** 
	 * <p> TODO</p>
	 * @return: Date
	 */
	public void setSettlementDay(Date settlementDay) {
		this.settlementDay = settlementDay;
	}

	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getAccountMoneyNewBefore() {
		return accountMoneyNewBefore;
	}

	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setAccountMoneyNewBefore(BigDecimal accountMoneyNewBefore) {
		this.accountMoneyNewBefore = accountMoneyNewBefore;
	}

	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getAccountMoneyNewAfter() {
		return accountMoneyNewAfter;
	}

	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setAccountMoneyNewAfter(BigDecimal accountMoneyNewAfter) {
		this.accountMoneyNewAfter = accountMoneyNewAfter;
	}

	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getRechargeMoneyOneSettlement() {
		return rechargeMoneyOneSettlement;
	}

	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setRechargeMoneyOneSettlement(BigDecimal rechargeMoneyOneSettlement) {
		this.rechargeMoneyOneSettlement = rechargeMoneyOneSettlement;
	}

	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getWithdrawalsMoneyOneSettlement() {
		return withdrawalsMoneyOneSettlement;
	}

	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setWithdrawalsMoneyOneSettlement(
			BigDecimal withdrawalsMoneyOneSettlement) {
		this.withdrawalsMoneyOneSettlement = withdrawalsMoneyOneSettlement;
	}



	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getTransactionFeeOneSettlement() {
		return transactionFeeOneSettlement;
	}

	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setTransactionFeeOneSettlement(
			BigDecimal transactionFeeOneSettlement) {
		this.transactionFeeOneSettlement = transactionFeeOneSettlement;
	}

	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getRechargeOrWithdrawalsFeeOneSettlement() {
		return rechargeOrWithdrawalsFeeOneSettlement;
	}

	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setRechargeOrWithdrawalsFeeOneSettlement(
			BigDecimal rechargeOrWithdrawalsFeeOneSettlement) {
		this.rechargeOrWithdrawalsFeeOneSettlement = rechargeOrWithdrawalsFeeOneSettlement;
	}
	
}
