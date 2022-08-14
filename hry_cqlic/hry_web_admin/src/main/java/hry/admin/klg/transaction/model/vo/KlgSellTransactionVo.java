package hry.admin.klg.transaction.model.vo;

import java.math.BigDecimal;

import hry.admin.klg.transaction.model.KlgSellTransaction;

public class KlgSellTransactionVo extends KlgSellTransaction{
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
	private BigDecimal addCandyNum;  //递增糖果比率
	private Integer count;  //
	private String shij;  //排单时间距离卖出时间间隔(分钟)
	
	
	
	public String getShij() {
		return shij;
	}
	public void setShij(String shij) {
		this.shij = shij;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public BigDecimal getAddCandyNum() {
		return addCandyNum;
	}
	public void setAddCandyNum(BigDecimal addCandyNum) {
		this.addCandyNum = addCandyNum;
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
