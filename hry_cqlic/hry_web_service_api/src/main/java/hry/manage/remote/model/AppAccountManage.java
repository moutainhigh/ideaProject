/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Liu Shilei
 * @version:      V1.0 
 * @Date:        2016年3月31日 下午6:17:33
 */
package hry.manage.remote.model;

import hry.bean.BaseModel;

import java.math.BigDecimal;


/**
 * <p> TODO</p>
 * @author:         Liu Shilei 
 * @Date :          2016年3月31日 下午6:17:33 
 */
public class AppAccountManage extends BaseModel {
	
	private Long id;
	
	private String website;//站点类别默认cn
	
	private Long customerId;   //用户id
	
	private String userName;  //用户名
	
	private BigDecimal hotMoney; //可用金额
	
	private BigDecimal coldMoney; //冻结金额
	
	private Integer version;  //版本号
	
	private String accountNum;  //虚拟账号
	
	private String currencyType;  //币种
	
	private Integer status;  //账户状态  0不可用  1可用
	// 借款总额
	private BigDecimal lendMoney;
	
	private AppPersonInfoManage appPersonInfo;
	
	private String trueName;
	
	private BigDecimal rmbAccountNetAsse;
	
	private BigDecimal rmbLendMoneySum;
	private BigDecimal sumRmbfund;
	private BigDecimal sumCommissionMoney;//累计佣金
	private BigDecimal hotCurrency;//可用币
	private BigDecimal coldCurrency;//冻结币
	
	

	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getRmbAccountNetAsse() {
		return rmbAccountNetAsse;
	}

	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setRmbAccountNetAsse(BigDecimal rmbAccountNetAsse) {
		this.rmbAccountNetAsse = rmbAccountNetAsse;
	}

	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getRmbLendMoneySum() {
		return rmbLendMoneySum;
	}

	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setRmbLendMoneySum(BigDecimal rmbLendMoneySum) {
		this.rmbLendMoneySum = rmbLendMoneySum;
	}

	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getSumRmbfund() {
		return sumRmbfund;
	}

	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setSumRmbfund(BigDecimal sumRmbfund) {
		this.sumRmbfund = sumRmbfund;
	}

	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getTrueName() {
		return trueName;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setTrueName(String trueName) {
		this.trueName = trueName;
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
	 * @return:     BigDecimal
	 */
	public BigDecimal getLendMoney() {
		return lendMoney;
	}

	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setLendMoney(BigDecimal lendMoney) {
		this.lendMoney = lendMoney;
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
	 * @return:     BigDecimal
	 */
	public BigDecimal getHotMoney() {
		return hotMoney;
	}

	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setHotMoney(BigDecimal hotMoney) {
		this.hotMoney = hotMoney;
	}

	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getColdMoney() {
		return coldMoney;
	}

	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setColdMoney(BigDecimal coldMoney) {
		this.coldMoney = coldMoney;
	}

	/**
	 * <p> TODO</p>
	 * @return:     Integer
	 */
	public Integer getVersion() {
		return version;
	}

	/** 
	 * <p> TODO</p>
	 * @return: Integer
	 */
	public void setVersion(Integer version) {
		this.version = version;
	}

	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getAccountNum() {
		return accountNum;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
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
	 * @return:     Integer
	 */
	public Integer getStatus() {
		return status;
	}

	/** 
	 * <p> TODO</p>
	 * @return: Integer
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}


	public BigDecimal getSumCommissionMoney() {
		return sumCommissionMoney;
	}

	public void setSumCommissionMoney(BigDecimal sumCommissionMoney) {
		this.sumCommissionMoney = sumCommissionMoney;
	}

	public BigDecimal getHotCurrency() {
		return hotCurrency;
	}

	public void setHotCurrency(BigDecimal hotCurrency) {
		this.hotCurrency = hotCurrency;
	}

	public BigDecimal getColdCurrency() {
		return coldCurrency;
	}

	public void setColdCurrency(BigDecimal coldCurrency) {
		this.coldCurrency = coldCurrency;
	}

	public AppPersonInfoManage getAppPersonInfo() {
		return appPersonInfo;
	}

	public void setAppPersonInfo(AppPersonInfoManage appPersonInfo) {
		this.appPersonInfo = appPersonInfo;
	}
	
}
