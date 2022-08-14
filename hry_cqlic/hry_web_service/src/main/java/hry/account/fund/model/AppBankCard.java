/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Liu Shilei
 * @version:      V1.0 
 * @Date:        2016年3月31日 下午6:17:33
 */
package hry.account.fund.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import hry.core.mvc.model.BaseModel;
import hry.customer.person.model.AppPersonInfo;

/**
 * <p> TODO</p>
 * @author:         Liu Shilei 
 * @Date :          2016年3月31日 下午6:17:33 
 */
@Table(name="app_bank_card")
public class AppBankCard extends BaseModel {
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	
	@Column(name="accountId")
	private Long accountId;  //账户ID
	
	@Column(name="customerId")
	private Long customerId;   //用户id
	
	@Column(name="userName")
	private String userName;  //用户名
	
	@Column(name="trueName")
	private String trueName;
	
	@Column(name="currencyType")
	private String currencyType;  //货币类型
	
	@Column(name="cardName")
	private String cardName; // 银行卡持有人
	
	@Column(name="cardNumber")
	private String cardNumber; //银行卡号
	
	@Column(name="cardBank")
	private String cardBank;  //开户银行
	
	@Column(name="bankAddress")
	private String bankAddress;  //开户行所在地  开户市
	
	@Column(name="subBank")
	private String subBank;  //开户支行
	
	@Column(name="subBankNum")
	private String subBankNum;  //开户支行银行机构代码
	

	@Column(name="website")
	private String website;//站点类别默认cn

	@Column(name="bankProvince")
	private String bankProvince;  //开户省份
	
	
	@Column(name="signBank")
	private String signBank;  //签约银行通道


	@Column(name="surname")
	private String surname;
	
	
	@Column(name="isDelete")
	private Integer isDelete;
	
	@Column(name= "thingUrl", columnDefinition= "text COLLATE utf8_bin COMMENT '二维码'")
	private String thingUrl;  //二维码
	
	@Column(name= "type", columnDefinition = "int(2) DEFAULT NULL COMMENT '类型（1-银行卡  2-支付宝  3-微信）'")
	private Integer type;  //类型  1-银行卡  2-支付宝  3-微信
	
	@Column(name= "isDefault", columnDefinition = "int(2) DEFAULT 0 COMMENT '是否是默认 0-否  1-是'")
	private Integer isDefault;  //是否为默认,1-是 0-否
	
	
	@Transient   //不与数据库映射的字段
	private AppPersonInfo appPersonInfo;
	
	
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

	public AppPersonInfo getAppPersonInfo() {
		return appPersonInfo;
	}

	public void setAppPersonInfo(AppPersonInfo appPersonInfo) {
		this.appPersonInfo = appPersonInfo;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
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
