/**
 * Copyright:   
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-13 10:42:58 
 */
package hry.admin.customer.model;


import hry.bean.BaseModel;


import javax.persistence.*;

/**
 * <p> AppBankCard </p>
 * @author:         liushilei
 * @Date :          2018-06-13 10:42:58  
 */
@Table(name="app_bank_card")
public class AppBankCard extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "customerId")
	private Long customerId;  //
	
	@Column(name= "userName")
	private String userName;  //
	
	@Column(name= "accountId")
	private Long accountId;  //
	
	@Column(name= "saasId")
	private String saasId;  //
	
	@Column(name= "currencyType")
	private String currencyType;  //
	
	@Column(name= "cardName")
	private String cardName;  //
	
	@Column(name= "cardNumber")
	private String cardNumber;  //
	
	@Column(name= "cardBank")
	private String cardBank;  //
	
	@Column(name= "bankProvince")
	private String bankProvince;  //
	
	@Column(name= "bankAddress")
	private String bankAddress;  //
	
	@Column(name= "subBank")
	private String subBank;  //
	
	@Column(name= "website")
	private String website;  //
	
	@Column(name= "trueName")
	private String trueName;  //
	
	@Column(name= "signBank")
	private String signBank;  //
	
	@Column(name= "subBankNum")
	private String subBankNum;  //
	
	@Column(name= "isDelete")
	private Integer isDelete;  //
	
	@Column(name= "surname")
	private String surname;  //


	@Transient // 不与数据库映射的字段
	private Object appPersonInfo;

	public Object getAppPersonInfo() {
		return appPersonInfo;
	}

	public void setAppPersonInfo(Object appPersonInfo) {
		this.appPersonInfo = appPersonInfo;
	}

	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Long 
	 * @Date :   2018-06-13 10:42:58    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-06-13 10:42:58   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Long 
	 * @Date :   2018-06-13 10:42:58    
	 */
	public Long getCustomerId() {
		return customerId;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param customerId
	 * @return:  void 
	 * @Date :   2018-06-13 10:42:58   
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 10:42:58    
	 */
	public String getUserName() {
		return userName;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param userName
	 * @return:  void 
	 * @Date :   2018-06-13 10:42:58   
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Long 
	 * @Date :   2018-06-13 10:42:58    
	 */
	public Long getAccountId() {
		return accountId;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param accountId
	 * @return:  void 
	 * @Date :   2018-06-13 10:42:58   
	 */
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 10:42:58    
	 */
	public String getSaasId() {
		return saasId;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param saasId
	 * @return:  void 
	 * @Date :   2018-06-13 10:42:58   
	 */
	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 10:42:58    
	 */
	public String getCurrencyType() {
		return currencyType;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param currencyType
	 * @return:  void 
	 * @Date :   2018-06-13 10:42:58   
	 */
	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 10:42:58    
	 */
	public String getCardName() {
		return cardName;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param cardName
	 * @return:  void 
	 * @Date :   2018-06-13 10:42:58   
	 */
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 10:42:58    
	 */
	public String getCardNumber() {
		return cardNumber;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param cardNumber
	 * @return:  void 
	 * @Date :   2018-06-13 10:42:58   
	 */
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 10:42:58    
	 */
	public String getCardBank() {
		return cardBank;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param cardBank
	 * @return:  void 
	 * @Date :   2018-06-13 10:42:58   
	 */
	public void setCardBank(String cardBank) {
		this.cardBank = cardBank;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 10:42:58    
	 */
	public String getBankProvince() {
		return bankProvince;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param bankProvince
	 * @return:  void 
	 * @Date :   2018-06-13 10:42:58   
	 */
	public void setBankProvince(String bankProvince) {
		this.bankProvince = bankProvince;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 10:42:58    
	 */
	public String getBankAddress() {
		return bankAddress;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param bankAddress
	 * @return:  void 
	 * @Date :   2018-06-13 10:42:58   
	 */
	public void setBankAddress(String bankAddress) {
		this.bankAddress = bankAddress;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 10:42:58    
	 */
	public String getSubBank() {
		return subBank;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param subBank
	 * @return:  void 
	 * @Date :   2018-06-13 10:42:58   
	 */
	public void setSubBank(String subBank) {
		this.subBank = subBank;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 10:42:58    
	 */
	public String getWebsite() {
		return website;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param website
	 * @return:  void 
	 * @Date :   2018-06-13 10:42:58   
	 */
	public void setWebsite(String website) {
		this.website = website;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 10:42:58    
	 */
	public String getTrueName() {
		return trueName;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param trueName
	 * @return:  void 
	 * @Date :   2018-06-13 10:42:58   
	 */
	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 10:42:58    
	 */
	public String getSignBank() {
		return signBank;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param signBank
	 * @return:  void 
	 * @Date :   2018-06-13 10:42:58   
	 */
	public void setSignBank(String signBank) {
		this.signBank = signBank;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 10:42:58    
	 */
	public String getSubBankNum() {
		return subBankNum;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param subBankNum
	 * @return:  void 
	 * @Date :   2018-06-13 10:42:58   
	 */
	public void setSubBankNum(String subBankNum) {
		this.subBankNum = subBankNum;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Integer 
	 * @Date :   2018-06-13 10:42:58    
	 */
	public Integer getIsDelete() {
		return isDelete;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param isDelete
	 * @return:  void 
	 * @Date :   2018-06-13 10:42:58   
	 */
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 10:42:58    
	 */
	public String getSurname() {
		return surname;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param surname
	 * @return:  void 
	 * @Date :   2018-06-13 10:42:58   
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	

}
