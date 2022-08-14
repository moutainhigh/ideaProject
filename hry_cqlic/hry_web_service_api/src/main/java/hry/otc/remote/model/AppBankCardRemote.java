/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Liu Shilei
 * @version:      V1.0 
 * @Date:        2016年3月31日 下午6:17:33
 */
package hry.otc.remote.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(value = "OTC银行卡实体类")
public class AppBankCardRemote implements Serializable{
	
	private Long id;

	@ApiModelProperty(value = "虚拟账户ID")
	private Long accountId;  //账户ID

	@ApiModelProperty(value = "用户id")
	private Long customerId;   //用户id

	@ApiModelProperty(value = "用户名")
	private String userName;  //用户名

	@ApiModelProperty(value = "名")
	private String trueName;

	@ApiModelProperty(value = "姓")
	private String surName;

	@ApiModelProperty(value = "货币类型")
	private String currencyType;

	@ApiModelProperty(value = "银行卡持有人")
	private String cardName;

	@ApiModelProperty(value = "银行卡号")
	private String cardNumber;

	@ApiModelProperty(value = "开户银行")
	private String cardBank;

	@ApiModelProperty(value = "开户行所在地  开户市")
	private String bankAddress;

	@ApiModelProperty(value = "开户支行")
	private String subBank;

	@ApiModelProperty(value = "开户支行银行机构代码")
	private String subBankNum;

	@ApiModelProperty(value = "站点类别默认cn")
	private String website;

	@ApiModelProperty(value = "开户省份")
	private String bankProvince;

	@ApiModelProperty(value = "签约银行通道")
	private String signBank;

	@ApiModelProperty(value = "二维码")
	private String thingUrl;

	@ApiModelProperty(value = "类型  1-银行卡  2-支付宝  3-微信")
	private Integer type;

	@ApiModelProperty(value = "是否为默认,1-是 0-否")
	private Integer isDefault;
	
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
