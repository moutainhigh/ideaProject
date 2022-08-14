package hry.util.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class Accountadd implements Serializable {

	private Long accountId;
	private  BigDecimal money;
	private  Integer monteyType;//1热账户，2，冷账号,3保證金佔用
    private String transactionNum;// 单号
	private Integer remarks;//备注 必须填 安照AccountRemarkEnum.java
	private Long customerId;
	private String coinCode;
	public Accountadd() {
		super();
	}
	public Accountadd(Long customerId, String coinCode, Long accountId, BigDecimal money, Integer monteyType, Integer remarks, String transactionNum) {

		this.customerId=customerId;
		this.coinCode=coinCode;
		this.money=money;
		this.accountId=accountId;
		this.monteyType=monteyType;
		this.transactionNum=transactionNum;
		this.remarks=remarks;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getCoinCode() {
		return coinCode;
	}

	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
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

	public Integer getRemarks() {
		return remarks;
	}
	public void setRemarks(Integer remarks) {
		this.remarks = remarks;
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

	private  BigDecimal balanceMoney; //不需要填
	private  String orderNum; //不需要填
}
