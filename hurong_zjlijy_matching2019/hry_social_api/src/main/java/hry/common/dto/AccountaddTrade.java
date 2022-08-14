package hry.common.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 主账户
 */
public class AccountaddTrade implements Serializable {

	private Long accountId;
	private  BigDecimal money;
	private  Integer monteyType;//1热账户，2，冷账号
	private  Integer acccountType ;//0资金账号，1币账户
	private String transactionNum;// 单号
	private Integer remarks;//备注 必须填 安照AccountRemarkEnum.java

	private  BigDecimal balanceMoney;
	private  String orderNum;
	
	

	public Integer getRemarks() {
		return remarks;
	}
	public void setRemarks(Integer remarks) {
		this.remarks = remarks;
	}
	public Integer getAcccountType() {
		return acccountType;
	}
	public void setAcccountType(Integer acccountType) {
		this.acccountType = acccountType;
	}
	public Long getAccountId() {
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	public BigDecimal getMoney() {
		return money;
	}
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	public Integer getMonteyType() {
		return monteyType;
	}
	public void setMonteyType(Integer monteyType) {
		this.monteyType = monteyType;
	}
	public String getTransactionNum() {
		return transactionNum;
	}
	public void setTransactionNum(String transactionNum) {
		this.transactionNum = transactionNum;
	}

	public BigDecimal getBalanceMoney() {
		return balanceMoney;
	}

	public void setBalanceMoney(BigDecimal balanceMoney) {
		this.balanceMoney = balanceMoney;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
}
