package hry.exchange.lock.model.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author liuchenghui
 *	查询用户的账户信息和流水记录信息
 */
@SuppressWarnings("serial")
public class DigitalmoneyAccountAndTransaction implements Serializable {
	private String email; //邮箱
	private String mobilePhone; // 手机号
	private String customerId; // 用户id
	private String country; // 国家
	private String surname; // 真实姓
	private String trueName; // 真实名
	private String coinCode; // 币种类名称
	private BigDecimal transactionMoney; // 交易金额
	private BigDecimal coldMoneySum; // 流水中冻结金额
	private String id; // 账户id
	private BigDecimal hotMoney; // 可用金额
	private BigDecimal coldMoney; // 冻结金额
	private BigDecimal accountBalance; // 账户余额
	private Date lockEndTime; // 冻结结束时间
	
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getTrueName() {
		return trueName;
	}
	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}
	public String getCoinCode() {
		return coinCode;
	}
	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}
	public BigDecimal getColdMoneySum() {
		return coldMoneySum;
	}
	public void setColdMoneySum(BigDecimal coldMoneySum) {
		this.coldMoneySum = coldMoneySum;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public BigDecimal getHotMoney() {
		return hotMoney;
	}
	public void setHotMoney(BigDecimal hotMoney) {
		this.hotMoney = hotMoney;
	}
	public BigDecimal getColdMoney() {
		return coldMoney;
	}
	public void setColdMoney(BigDecimal coldMoney) {
		this.coldMoney = coldMoney;
	}
	public Date getLockEndTime() {
		return lockEndTime;
	}
	public void setLockEndTime(Date lockEndTime) {
		this.lockEndTime = lockEndTime;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	public BigDecimal getAccountBalance() {
		return accountBalance;
	}
	public void setAccountBalance(BigDecimal accountBalance) {
		this.accountBalance = accountBalance;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	public BigDecimal getTransactionMoney() {
		return transactionMoney;
	}
	public void setTransactionMoney(BigDecimal transactionMoney) {
		this.transactionMoney = transactionMoney;
	}
	@Override
	public String toString() {
		return "DigitalmoneyAccountAndTransaction [email=" + email + ", mobilePhone=" + mobilePhone + ", customerId="
				+ customerId + ", country=" + country + ", surname=" + surname + ", trueName=" + trueName
				+ ", coinCode=" + coinCode + ", transactionMoney=" + transactionMoney + ", coldMoneySum=" + coldMoneySum
				+ ", id=" + id + ", hotMoney=" + hotMoney + ", coldMoney=" + coldMoney + ", accountBalance="
				+ accountBalance + ", lockEndTime=" + lockEndTime + "]";
	}
	
}
