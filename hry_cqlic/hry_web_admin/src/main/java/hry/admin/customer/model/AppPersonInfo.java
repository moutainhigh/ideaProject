/**
 * Copyright:   
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-13 09:45:27 
 */
package hry.admin.customer.model;


import hry.bean.BaseModel;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p> AppPersonInfo </p>
 * @author:         liushilei
 * @Date :          2018-06-13 09:45:27  
 */
@Table(name="app_person_info")
public class AppPersonInfo extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "customerId")
	private Long customerId;  //
	
	@Column(name= "customerType")
	private Integer customerType;  //
	
	@Column(name= "mobilePhone")
	private String mobilePhone;  //
	
	@Column(name= "email")
	private String email;  //
	
	@Column(name= "trueName")
	private String trueName;  //
	
	@Column(name= "sex")
	private Integer sex;  //
	
	@Column(name= "birthday")
	private String birthday;  //
	
	@Column(name= "country")
	private String country;  //
	
	@Column(name= "cardType")
	private Integer cardType;  //
	
	@Column(name= "cardId")
	private String cardId;  //
	
	@Column(name= "customerSource")
	private Integer customerSource;  //
	
	@Column(name= "saasId")
	private String saasId;  //
	
	@Column(name= "realTime")
	private Date realTime;  //
	
	@Column(name= "emailCode")
	private String emailCode;  //
	
	@Column(name= "cardIdUsd")
	private String cardIdUsd;  //
	
	@Column(name= "cardIdValidPeriod")
	private String cardIdValidPeriod;  //
	
	@Column(name= "postalAddress")
	private String postalAddress;  //
	
	@Column(name= "postCode")
	private Integer postCode;  //
	
	@Column(name= "contacts")
	private String contacts;  //
	
	@Column(name= "stage")
	private Integer stage;  //
	
	@Column(name= "contactsTel")
	private String contactsTel;  //
	
	@Column(name= "handIdCardUrl")
	private String handIdCardUrl;  //
	
	@Column(name= "idCardFrontUrl")
	private String idCardFrontUrl;  //
	
	@Column(name= "idCardBackUrl")
	private String idCardBackUrl;  //
	
	@Column(name= "handDealUrl")
	private String handDealUrl;  //
	
	@Column(name= "isExamine")
	private Integer isExamine;  //
	
	@Column(name= "eamineRefusalReason")
	private String eamineRefusalReason;  //
	
	@Column(name= "isCancle")
	private Integer isCancle;  //
	
	@Column(name= "cancleReason")
	private String cancleReason;  //
	
	@Column(name= "withdrawCheckMoney")
	private BigDecimal withdrawCheckMoney;  //
	
	@Column(name= "personBank")
	private String personBank;  //
	
	@Column(name= "personCard")
	private String personCard;  //
	
	@Column(name= "frontpersonCard")
	private String frontpersonCard;  //
	
	@Column(name= "surname")
	private String surname;  //
	
	@Column(name= "papersType")
	private String papersType;  //
	
	@Column(name= "btcDate")
	private String btcDate;  //
	
	@Column(name= "btcCount")
	private BigDecimal btcCount;  //
	
	@Column(name= "lendTimes")
	private BigDecimal lendTimes;  //
	
	@Column(name= "lengPing")
	private BigDecimal lengPing;  //
	
	@Column(name= "lengRiskRate")
	private BigDecimal lengRiskRate;  //

	// 平台币支付手续费：0否 1是
	@Column(name = "isOpenCoinFee")
	private int isOpenCoinFee;

	@Transient
	private Long oneParentId;

	@Transient
	private String oneParentEmail;

	@Transient
	private String oneParentMobile;

	@Transient
	private Integer belongLevel;

	@Transient
	private Integer levelTotal;

	@Transient
	private String parentEmail;

	@Transient
	private String parentMobile;


	public int getIsOpenCoinFee () {
		return isOpenCoinFee;
	}

	public void setIsOpenCoinFee (int isOpenCoinFee) {
		this.isOpenCoinFee = isOpenCoinFee;
	}


	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Long 
	 * @Date :   2018-06-13 09:45:27    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-06-13 09:45:27   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Long 
	 * @Date :   2018-06-13 09:45:27    
	 */
	public Long getCustomerId() {
		return customerId;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param customerId
	 * @return:  void 
	 * @Date :   2018-06-13 09:45:27   
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Integer 
	 * @Date :   2018-06-13 09:45:27    
	 */
	public Integer getCustomerType() {
		return customerType;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param customerType
	 * @return:  void 
	 * @Date :   2018-06-13 09:45:27   
	 */
	public void setCustomerType(Integer customerType) {
		this.customerType = customerType;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 09:45:27    
	 */
	public String getMobilePhone() {
		return mobilePhone;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param mobilePhone
	 * @return:  void 
	 * @Date :   2018-06-13 09:45:27   
	 */
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 09:45:27    
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param email
	 * @return:  void 
	 * @Date :   2018-06-13 09:45:27   
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 09:45:27    
	 */
	public String getTrueName() {
		return trueName;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param trueName
	 * @return:  void 
	 * @Date :   2018-06-13 09:45:27   
	 */
	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Integer 
	 * @Date :   2018-06-13 09:45:27    
	 */
	public Integer getSex() {
		return sex;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param sex
	 * @return:  void 
	 * @Date :   2018-06-13 09:45:27   
	 */
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 09:45:27    
	 */
	public String getBirthday() {
		return birthday;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param birthday
	 * @return:  void 
	 * @Date :   2018-06-13 09:45:27   
	 */
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 09:45:27    
	 */
	public String getCountry() {
		return country;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param country
	 * @return:  void 
	 * @Date :   2018-06-13 09:45:27   
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Integer 
	 * @Date :   2018-06-13 09:45:27    
	 */
	public Integer getCardType() {
		return cardType;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param cardType
	 * @return:  void 
	 * @Date :   2018-06-13 09:45:27   
	 */
	public void setCardType(Integer cardType) {
		this.cardType = cardType;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 09:45:27    
	 */
	public String getCardId() {
		return cardId;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param cardId
	 * @return:  void 
	 * @Date :   2018-06-13 09:45:27   
	 */
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Integer 
	 * @Date :   2018-06-13 09:45:27    
	 */
	public Integer getCustomerSource() {
		return customerSource;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param customerSource
	 * @return:  void 
	 * @Date :   2018-06-13 09:45:27   
	 */
	public void setCustomerSource(Integer customerSource) {
		this.customerSource = customerSource;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 09:45:27    
	 */
	public String getSaasId() {
		return saasId;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param saasId
	 * @return:  void 
	 * @Date :   2018-06-13 09:45:27   
	 */
	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Date 
	 * @Date :   2018-06-13 09:45:27    
	 */
	public Date getRealTime() {
		return realTime;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param realTime
	 * @return:  void 
	 * @Date :   2018-06-13 09:45:27   
	 */
	public void setRealTime(Date realTime) {
		this.realTime = realTime;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 09:45:27    
	 */
	public String getEmailCode() {
		return emailCode;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param emailCode
	 * @return:  void 
	 * @Date :   2018-06-13 09:45:27   
	 */
	public void setEmailCode(String emailCode) {
		this.emailCode = emailCode;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 09:45:27    
	 */
	public String getCardIdUsd() {
		return cardIdUsd;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param cardIdUsd
	 * @return:  void 
	 * @Date :   2018-06-13 09:45:27   
	 */
	public void setCardIdUsd(String cardIdUsd) {
		this.cardIdUsd = cardIdUsd;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 09:45:27    
	 */
	public String getCardIdValidPeriod() {
		return cardIdValidPeriod;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param cardIdValidPeriod
	 * @return:  void 
	 * @Date :   2018-06-13 09:45:27   
	 */
	public void setCardIdValidPeriod(String cardIdValidPeriod) {
		this.cardIdValidPeriod = cardIdValidPeriod;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 09:45:27    
	 */
	public String getPostalAddress() {
		return postalAddress;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param postalAddress
	 * @return:  void 
	 * @Date :   2018-06-13 09:45:27   
	 */
	public void setPostalAddress(String postalAddress) {
		this.postalAddress = postalAddress;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Integer 
	 * @Date :   2018-06-13 09:45:27    
	 */
	public Integer getPostCode() {
		return postCode;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param postCode
	 * @return:  void 
	 * @Date :   2018-06-13 09:45:27   
	 */
	public void setPostCode(Integer postCode) {
		this.postCode = postCode;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 09:45:27    
	 */
	public String getContacts() {
		return contacts;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param contacts
	 * @return:  void 
	 * @Date :   2018-06-13 09:45:27   
	 */
	public void setContacts(String contacts) {
		this.contacts = contacts;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Integer 
	 * @Date :   2018-06-13 09:45:27    
	 */
	public Integer getStage() {
		return stage;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param stage
	 * @return:  void 
	 * @Date :   2018-06-13 09:45:27   
	 */
	public void setStage(Integer stage) {
		this.stage = stage;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 09:45:27    
	 */
	public String getContactsTel() {
		return contactsTel;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param contactsTel
	 * @return:  void 
	 * @Date :   2018-06-13 09:45:27   
	 */
	public void setContactsTel(String contactsTel) {
		this.contactsTel = contactsTel;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 09:45:27    
	 */
	public String getHandIdCardUrl() {
		return handIdCardUrl;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param handIdCardUrl
	 * @return:  void 
	 * @Date :   2018-06-13 09:45:27   
	 */
	public void setHandIdCardUrl(String handIdCardUrl) {
		this.handIdCardUrl = handIdCardUrl;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 09:45:27    
	 */
	public String getIdCardFrontUrl() {
		return idCardFrontUrl;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param idCardFrontUrl
	 * @return:  void 
	 * @Date :   2018-06-13 09:45:27   
	 */
	public void setIdCardFrontUrl(String idCardFrontUrl) {
		this.idCardFrontUrl = idCardFrontUrl;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 09:45:27    
	 */
	public String getIdCardBackUrl() {
		return idCardBackUrl;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param idCardBackUrl
	 * @return:  void 
	 * @Date :   2018-06-13 09:45:27   
	 */
	public void setIdCardBackUrl(String idCardBackUrl) {
		this.idCardBackUrl = idCardBackUrl;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 09:45:27    
	 */
	public String getHandDealUrl() {
		return handDealUrl;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param handDealUrl
	 * @return:  void 
	 * @Date :   2018-06-13 09:45:27   
	 */
	public void setHandDealUrl(String handDealUrl) {
		this.handDealUrl = handDealUrl;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Integer 
	 * @Date :   2018-06-13 09:45:27    
	 */
	public Integer getIsExamine() {
		return isExamine;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param isExamine
	 * @return:  void 
	 * @Date :   2018-06-13 09:45:27   
	 */
	public void setIsExamine(Integer isExamine) {
		this.isExamine = isExamine;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 09:45:27    
	 */
	public String getEamineRefusalReason() {
		return eamineRefusalReason;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param eamineRefusalReason
	 * @return:  void 
	 * @Date :   2018-06-13 09:45:27   
	 */
	public void setEamineRefusalReason(String eamineRefusalReason) {
		this.eamineRefusalReason = eamineRefusalReason;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Integer 
	 * @Date :   2018-06-13 09:45:27    
	 */
	public Integer getIsCancle() {
		return isCancle;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param isCancle
	 * @return:  void 
	 * @Date :   2018-06-13 09:45:27   
	 */
	public void setIsCancle(Integer isCancle) {
		this.isCancle = isCancle;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 09:45:27    
	 */
	public String getCancleReason() {
		return cancleReason;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param cancleReason
	 * @return:  void 
	 * @Date :   2018-06-13 09:45:27   
	 */
	public void setCancleReason(String cancleReason) {
		this.cancleReason = cancleReason;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  BigDecimal 
	 * @Date :   2018-06-13 09:45:27    
	 */
	public BigDecimal getWithdrawCheckMoney() {
		return withdrawCheckMoney;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param withdrawCheckMoney
	 * @return:  void 
	 * @Date :   2018-06-13 09:45:27   
	 */
	public void setWithdrawCheckMoney(BigDecimal withdrawCheckMoney) {
		this.withdrawCheckMoney = withdrawCheckMoney;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 09:45:27    
	 */
	public String getPersonBank() {
		return personBank;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param personBank
	 * @return:  void 
	 * @Date :   2018-06-13 09:45:27   
	 */
	public void setPersonBank(String personBank) {
		this.personBank = personBank;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 09:45:27    
	 */
	public String getPersonCard() {
		return personCard;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param personCard
	 * @return:  void 
	 * @Date :   2018-06-13 09:45:27   
	 */
	public void setPersonCard(String personCard) {
		this.personCard = personCard;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 09:45:27    
	 */
	public String getFrontpersonCard() {
		return frontpersonCard;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param frontpersonCard
	 * @return:  void 
	 * @Date :   2018-06-13 09:45:27   
	 */
	public void setFrontpersonCard(String frontpersonCard) {
		this.frontpersonCard = frontpersonCard;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 09:45:27    
	 */
	public String getSurname() {
		return surname;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param surname
	 * @return:  void 
	 * @Date :   2018-06-13 09:45:27   
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 09:45:27    
	 */
	public String getPapersType() {
		return papersType;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param papersType
	 * @return:  void 
	 * @Date :   2018-06-13 09:45:27   
	 */
	public void setPapersType(String papersType) {
		this.papersType = papersType;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 09:45:27    
	 */
	public String getBtcDate() {
		return btcDate;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param btcDate
	 * @return:  void 
	 * @Date :   2018-06-13 09:45:27   
	 */
	public void setBtcDate(String btcDate) {
		this.btcDate = btcDate;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  BigDecimal 
	 * @Date :   2018-06-13 09:45:27    
	 */
	public BigDecimal getBtcCount() {
		return btcCount;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param btcCount
	 * @return:  void 
	 * @Date :   2018-06-13 09:45:27   
	 */
	public void setBtcCount(BigDecimal btcCount) {
		this.btcCount = btcCount;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  BigDecimal 
	 * @Date :   2018-06-13 09:45:27    
	 */
	public BigDecimal getLendTimes() {
		return lendTimes;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param lendTimes
	 * @return:  void 
	 * @Date :   2018-06-13 09:45:27   
	 */
	public void setLendTimes(BigDecimal lendTimes) {
		this.lendTimes = lendTimes;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  BigDecimal 
	 * @Date :   2018-06-13 09:45:27    
	 */
	public BigDecimal getLengPing() {
		return lengPing;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param lengPing
	 * @return:  void 
	 * @Date :   2018-06-13 09:45:27   
	 */
	public void setLengPing(BigDecimal lengPing) {
		this.lengPing = lengPing;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  BigDecimal 
	 * @Date :   2018-06-13 09:45:27    
	 */
	public BigDecimal getLengRiskRate() {
		return lengRiskRate;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param lengRiskRate
	 * @return:  void 
	 * @Date :   2018-06-13 09:45:27   
	 */
	public void setLengRiskRate(BigDecimal lengRiskRate) {
		this.lengRiskRate = lengRiskRate;
	}


	public Long getOneParentId() {
		return oneParentId;
	}

	public void setOneParentId(Long oneParentId) {
		this.oneParentId = oneParentId;
	}

	public String getOneParentEmail() {
		return oneParentEmail;
	}

	public void setOneParentEmail(String oneParentEmail) {
		this.oneParentEmail = oneParentEmail;
	}

	public String getOneParentMobile() {
		return oneParentMobile;
	}

	public void setOneParentMobile(String oneParentMobile) {
		this.oneParentMobile = oneParentMobile;
	}

	public Integer getBelongLevel() {
		return belongLevel;
	}

	public void setBelongLevel(Integer belongLevel) {
		this.belongLevel = belongLevel;
	}

	public Integer getLevelTotal() {
		return levelTotal;
	}

	public void setLevelTotal(Integer levelTotal) {
		this.levelTotal = levelTotal;
	}

	public String getParentEmail() {
		return parentEmail;
	}

	public void setParentEmail(String parentEmail) {
		this.parentEmail = parentEmail;
	}

	public String getParentMobile() {
		return parentMobile;
	}

	public void setParentMobile(String parentMobile) {
		this.parentMobile = parentMobile;
	}
}
