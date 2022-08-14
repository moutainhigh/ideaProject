/**
 * Copyright:   
 * @author:      tianpengyu
 * @version:     V1.0 
 * @Date:        2018-07-06 14:36:50 
 */
package hry.admin.exchange.model;


import hry.admin.customer.model.AppPersonInfo;
import hry.bean.BaseModel;

import java.math.BigDecimal;

import javax.persistence.*;

/**
 * <p> AppTransaction </p>
 * @author:         tianpengyu
 * @Date :          2018-07-06 14:36:50  
 */
@Table(name="app_transaction")
public class AppTransaction extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "transactionNum")
	private String transactionNum;  //
	
	@Column(name= "userName")
	private String userName;  //
	
	@Column(name= "customerId")
	private Long customerId;  //
	
	@Column(name= "accountId")
	private Long accountId;  //
	
	@Column(name= "transactionType")
	private Integer transactionType;  //
	
	@Column(name= "transactionMoney")
	private BigDecimal transactionMoney;  //
	
	@Column(name= "status")
	private Integer status;  //
	
	@Column(name= "userId")
	private Long userId;  //
	
	@Column(name= "bankNum")
	private String bankNum;  //
	
	@Column(name= "style")
	private String style;  //
	
	@Column(name= "remark")
	private String remark;  //
	
	@Column(name= "custromerAccountNumber")
	private String custromerAccountNumber;  //
	
	@Column(name= "ourAccountNumber")
	private String ourAccountNumber;  //
	
	@Column(name= "currencyType")
	private String currencyType;  //
	
	@Column(name= "cardHolder")
	private String cardHolder;  //
	
	@Column(name= "saasId")
	private String saasId;  //
	
	@Column(name= "factorage")
	private BigDecimal factorage;  //
	
	@Column(name= "readyStates")
	private Integer readyStates;  //
	
	@Column(name= "website")
	private String website;  //
	
	@Column(name= "fee")
	private BigDecimal fee;  //
	
	@Column(name= "rejectionReason")
	private String rejectionReason;  //
	
	@Column(name= "trueName")
	private String trueName;  //
	
	@Column(name= "thirdPayName")
	private String thirdPayName;  //
	
	@Column(name= "surname")
	private String surname;  //

	@Transient
	private String bankName;

	@Transient
	private String bankProvince; //开户省份

	@Transient
	private String bankAddress;//开户行所在地  开户市

	@Transient
	private String subBank;  //开户支行

	@Transient
	private String subBankNum;  //开户支行的银行机构代码

	@Transient
	private String email;

	@Transient
	private String phone;

	@Transient
	private AppPersonInfo appPersonInfo;

	public AppPersonInfo getAppPersonInfo() {
		return appPersonInfo;
	}

	public void setAppPersonInfo(AppPersonInfo appPersonInfo) {
		this.appPersonInfo = appPersonInfo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankProvince() {
		return bankProvince;
	}

	public void setBankProvince(String bankProvince) {
		this.bankProvince = bankProvince;
	}

	public String getBankAddress() {
		return bankAddress;
	}

	public void setBankAddress(String bankAddress) {
		this.bankAddress = bankAddress;
	}

	public String getSubBank() {
		return subBank;
	}

	public void setSubBank(String subBank) {
		this.subBank = subBank;
	}

	public String getSubBankNum() {
		return subBankNum;
	}

	public void setSubBankNum(String subBankNum) {
		this.subBankNum = subBankNum;
	}

	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  Long 
	 * @Date :   2018-07-06 14:36:50    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-07-06 14:36:50   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-07-06 14:36:50    
	 */
	public String getTransactionNum() {
		return transactionNum;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param transactionNum
	 * @return:  void 
	 * @Date :   2018-07-06 14:36:50   
	 */
	public void setTransactionNum(String transactionNum) {
		this.transactionNum = transactionNum;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-07-06 14:36:50    
	 */
	public String getUserName() {
		return userName;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param userName
	 * @return:  void 
	 * @Date :   2018-07-06 14:36:50   
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  Long 
	 * @Date :   2018-07-06 14:36:50    
	 */
	public Long getCustomerId() {
		return customerId;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param customerId
	 * @return:  void 
	 * @Date :   2018-07-06 14:36:50   
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  Long 
	 * @Date :   2018-07-06 14:36:50    
	 */
	public Long getAccountId() {
		return accountId;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param accountId
	 * @return:  void 
	 * @Date :   2018-07-06 14:36:50   
	 */
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  Integer 
	 * @Date :   2018-07-06 14:36:50    
	 */
	public Integer getTransactionType() {
		return transactionType;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param transactionType
	 * @return:  void 
	 * @Date :   2018-07-06 14:36:50   
	 */
	public void setTransactionType(Integer transactionType) {
		this.transactionType = transactionType;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  BigDecimal 
	 * @Date :   2018-07-06 14:36:50    
	 */
	public BigDecimal getTransactionMoney() {
		return transactionMoney;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param transactionMoney
	 * @return:  void 
	 * @Date :   2018-07-06 14:36:50   
	 */
	public void setTransactionMoney(BigDecimal transactionMoney) {
		this.transactionMoney = transactionMoney;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  Integer 
	 * @Date :   2018-07-06 14:36:50    
	 */
	public Integer getStatus() {
		return status;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param status
	 * @return:  void 
	 * @Date :   2018-07-06 14:36:50   
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  Long 
	 * @Date :   2018-07-06 14:36:50    
	 */
	public Long getUserId() {
		return userId;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param userId
	 * @return:  void 
	 * @Date :   2018-07-06 14:36:50   
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-07-06 14:36:50    
	 */
	public String getBankNum() {
		return bankNum;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param bankNum
	 * @return:  void 
	 * @Date :   2018-07-06 14:36:50   
	 */
	public void setBankNum(String bankNum) {
		this.bankNum = bankNum;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-07-06 14:36:50    
	 */
	public String getStyle() {
		return style;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param style
	 * @return:  void 
	 * @Date :   2018-07-06 14:36:50   
	 */
	public void setStyle(String style) {
		this.style = style;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-07-06 14:36:50    
	 */
	public String getRemark() {
		return remark;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param remark
	 * @return:  void 
	 * @Date :   2018-07-06 14:36:50   
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-07-06 14:36:50    
	 */
	public String getCustromerAccountNumber() {
		return custromerAccountNumber;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param custromerAccountNumber
	 * @return:  void 
	 * @Date :   2018-07-06 14:36:50   
	 */
	public void setCustromerAccountNumber(String custromerAccountNumber) {
		this.custromerAccountNumber = custromerAccountNumber;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-07-06 14:36:50    
	 */
	public String getOurAccountNumber() {
		return ourAccountNumber;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param ourAccountNumber
	 * @return:  void 
	 * @Date :   2018-07-06 14:36:50   
	 */
	public void setOurAccountNumber(String ourAccountNumber) {
		this.ourAccountNumber = ourAccountNumber;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-07-06 14:36:50    
	 */
	public String getCurrencyType() {
		return currencyType;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param currencyType
	 * @return:  void 
	 * @Date :   2018-07-06 14:36:50   
	 */
	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-07-06 14:36:50    
	 */
	public String getCardHolder() {
		return cardHolder;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param cardHolder
	 * @return:  void 
	 * @Date :   2018-07-06 14:36:50   
	 */
	public void setCardHolder(String cardHolder) {
		this.cardHolder = cardHolder;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-07-06 14:36:50    
	 */
	public String getSaasId() {
		return saasId;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param saasId
	 * @return:  void 
	 * @Date :   2018-07-06 14:36:50   
	 */
	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  BigDecimal 
	 * @Date :   2018-07-06 14:36:50    
	 */
	public BigDecimal getFactorage() {
		return factorage;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param factorage
	 * @return:  void 
	 * @Date :   2018-07-06 14:36:50   
	 */
	public void setFactorage(BigDecimal factorage) {
		this.factorage = factorage;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  Integer 
	 * @Date :   2018-07-06 14:36:50    
	 */
	public Integer getReadyStates() {
		return readyStates;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param readyStates
	 * @return:  void 
	 * @Date :   2018-07-06 14:36:50   
	 */
	public void setReadyStates(Integer readyStates) {
		this.readyStates = readyStates;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-07-06 14:36:50    
	 */
	public String getWebsite() {
		return website;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param website
	 * @return:  void 
	 * @Date :   2018-07-06 14:36:50   
	 */
	public void setWebsite(String website) {
		this.website = website;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  BigDecimal 
	 * @Date :   2018-07-06 14:36:50    
	 */
	public BigDecimal getFee() {
		return fee;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param fee
	 * @return:  void 
	 * @Date :   2018-07-06 14:36:50   
	 */
	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-07-06 14:36:50    
	 */
	public String getRejectionReason() {
		return rejectionReason;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param rejectionReason
	 * @return:  void 
	 * @Date :   2018-07-06 14:36:50   
	 */
	public void setRejectionReason(String rejectionReason) {
		this.rejectionReason = rejectionReason;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-07-06 14:36:50    
	 */
	public String getTrueName() {
		return trueName;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param trueName
	 * @return:  void 
	 * @Date :   2018-07-06 14:36:50   
	 */
	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-07-06 14:36:50    
	 */
	public String getThirdPayName() {
		return thirdPayName;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param thirdPayName
	 * @return:  void 
	 * @Date :   2018-07-06 14:36:50   
	 */
	public void setThirdPayName(String thirdPayName) {
		this.thirdPayName = thirdPayName;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-07-06 14:36:50    
	 */
	public String getSurname() {
		return surname;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param surname
	 * @return:  void 
	 * @Date :   2018-07-06 14:36:50   
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	

}
