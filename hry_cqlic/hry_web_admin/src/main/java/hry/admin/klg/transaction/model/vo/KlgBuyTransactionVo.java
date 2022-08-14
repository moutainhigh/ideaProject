package hry.admin.klg.transaction.model.vo;

import java.math.BigDecimal;

import hry.admin.klg.transaction.model.KlgBuyTransaction;

public class KlgBuyTransactionVo extends  KlgBuyTransaction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String trueName;  //购买人姓
	private String surName;  //购买人名
	private String mobilePhone;  //购买人手机号
	private String email;  //购买人邮箱
	private BigDecimal smeMoneySum;  //sme总和
	private BigDecimal usdtMoneySum;  //usdt总和
	private Integer count;  //
	private BigDecimal yesterdaySurplus;  //预测昨天剩余
	private BigDecimal todaySum;  //今天排单数量
	
	
	public BigDecimal getYesterdaySurplus() {
		return yesterdaySurplus;
	}
	public void setYesterdaySurplus(BigDecimal yesterdaySurplus) {
		this.yesterdaySurplus = yesterdaySurplus;
	}
	public BigDecimal getTodaySum() {
		return todaySum;
	}
	public void setTodaySum(BigDecimal todaySum) {
		this.todaySum = todaySum;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public BigDecimal getSmeMoneySum() {
		return smeMoneySum;
	}
	public void setSmeMoneySum(BigDecimal smeMoneySum) {
		this.smeMoneySum = smeMoneySum;
	}
	public BigDecimal getUsdtMoneySum() {
		return usdtMoneySum;
	}
	public void setUsdtMoneySum(BigDecimal usdtMoneySum) {
		this.usdtMoneySum = usdtMoneySum;
	}
	public String getTrueName() {
		return trueName;
	}
	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}
	public String getSurName() {
		return surName;
	}
	public void setSurName(String surName) {
		this.surName = surName;
	}
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

}
