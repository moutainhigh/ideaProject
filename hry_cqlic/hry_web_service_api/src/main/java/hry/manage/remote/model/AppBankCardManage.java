/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Liu Shilei
 * @version:      V1.0 
 * @Date:        2016年3月31日 下午6:17:33
 */
package hry.manage.remote.model;


import hry.bean.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "银行卡对象")
public class AppBankCardManage extends BaseModel {
	
	private Long id;
	
	private Long accountId;  //账户ID
	
	private Long customerId;   //用户id

	@ApiModelProperty(value = "用户名")
	private String userName;  //用户名
	//名
	private String trueName;
	//姓
	private String surName;
	
	private String currencyType;  //货币类型
	
	private String cardName; // 银行卡持有人

	
	private String cardNumber; //银行卡号
	
	private String cardBank;  //开户银行
	
	private String bankAddress;  //开户行所在地  开户市
	
	private String subBank;  //开户支行
	
	private String subBankNum;  //开户支行银行机构代码
	

	private String website;//站点类别默认cn

	private String bankProvince;  //开户省份
	
	private String signBank;  //签约银行通道
	
	private String thingUrl;  //二维码
	
	private Integer type;  //类型  1-银行卡  2-支付宝  3-微信
	
	private Integer isDefault;  //是否为默认,1-是 0-否
	
	

	public String getThingUrl() {
		return thingUrl;
	}

	public void setThingUrl(String thingUrl) {
		this.thingUrl = thingUrl;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}

	public String getSurName() {
		return surName;
	}

	public void setSurName(String surName) {
		this.surName = surName;
	}

	public String getSubBankNum() {
		return subBankNum;
	}

	public void setSubBankNum(String subBankNum) {
		this.subBankNum = subBankNum;
	}

	public String getSignBank() {
		return signBank;
	}

	public void setSignBank(String signBank) {
		this.signBank = signBank;
	}

	public String getTrueName() {
		return trueName;
	}

	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}

	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getBankProvince() {
		return bankProvince;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setBankProvince(String bankProvince) {
		this.bankProvince = bankProvince;
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
	 * @return:     Long
	 */
	public Long getAccountId() {
		return accountId;
	}

	/** 
	 * <p> TODO</p>
	 * @return: Long
	 */
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	
	

	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getCardName() {
		return cardName;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getCardNumber() {
		return cardNumber;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getCardBank() {
		return cardBank;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setCardBank(String cardBank) {
		this.cardBank = cardBank;
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
	public String getSubBank() {
		return subBank;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setSubBank(String subBank) {
		this.subBank = subBank;
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

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}


}
