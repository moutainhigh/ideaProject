/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Liu Shilei
 * @version:      V1.0 
 * @Date:        2016年3月31日 下午6:17:33
 */
package hry.manage.remote.model;


import hry.bean.BaseModel;

import java.math.BigDecimal;
import java.util.Date;


/**
 * 
 * <p> TODO</p>
 * @author:         Liu Shilei 
 * @Date :          2016年6月2日 上午10:49:11
 */
public class AppOurAccountManage extends BaseModel {
	
	private Long id;
	
	private String accountType ;//账户类型   0银行卡  1币账户   2支付宝
	
	private  String openAccountType;//开户类型    0企业 1个人  /0 充币 1 提币
	
	private String bankName; //银行名称
	
	private String dicBankName;//银行数据字典value
	
	private String accountName; //账户持有人
	
	private String accountNumber; //账户号
	
	private Date openTime; //账户号
	
	private BigDecimal accountMoney; //账户总额
	
	
	private String currencyType;  //货币类型   0人民币  1
	
	private String bankLogo;  //银行logo
	
	private Integer isShow;  //是否展示
	
	private String bankAddress;  //银行地址
	
	private String remark;  //银行地址
	
	private String website;//站点类别默认cn
	
	
	private BigDecimal retainsMoney;//账户保留个数/金额
	
	//钱包服务器币种总额
	private BigDecimal coinTotalMoney;
	
	//提币钱包账户余额
	private BigDecimal withdrawMoney;
	

	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getWithdrawMoney() {
		return withdrawMoney;
	}

	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setWithdrawMoney(BigDecimal withdrawMoney) {
		this.withdrawMoney = withdrawMoney;
	}

	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getCoinTotalMoney() {
		return coinTotalMoney;
	}

	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setCoinTotalMoney(BigDecimal coinTotalMoney) {
		this.coinTotalMoney = coinTotalMoney;
	}

	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getRetainsMoney() {
		return retainsMoney;
	}

	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setRetainsMoney(BigDecimal retainsMoney) {
		this.retainsMoney = retainsMoney;
	}

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
	 * @return:     String
	 */
	public String getAccountType() {
		return accountType;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getOpenAccountType() {
		return openAccountType;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setOpenAccountType(String openAccountType) {
		this.openAccountType = openAccountType;
	}

	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getBankName() {
		return bankName;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getAccountName() {
		return accountName;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getAccountNumber() {
		return accountNumber;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	/**
	 * <p> TODO</p>
	 * @return:     Date
	 */
	public Date getOpenTime() {
		return openTime;
	}

	/** 
	 * <p> TODO</p>
	 * @return: Date
	 */
	public void setOpenTime(Date openTime) {
		this.openTime = openTime;
	}

	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getCurrencyType() {
		return currencyType;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}

	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getBankLogo() {
		return bankLogo;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setBankLogo(String bankLogo) {
		this.bankLogo = bankLogo;
	}

	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public Integer getIsShow() {
		return isShow;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setIsShow(Integer isShow) {
		this.isShow = isShow;
	}

	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getAccountMoney() {
		return accountMoney;
	}

	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setAccountMoney(BigDecimal accountMoney) {
		this.accountMoney = accountMoney;
	}

	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getBankAddress() {
		return bankAddress;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setBankAddress(String bankAddress) {
		this.bankAddress = bankAddress;
	}

	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getRemark() {
		return remark;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getDicBankName() {
		return dicBankName;
	}

	public void setDicBankName(String dicBankName) {
		this.dicBankName = dicBankName;
	}
	
	
	

}
