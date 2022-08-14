/**
 * Copyright:   
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-13 10:56:33 
 */
package hry.admin.exchange.model;


import hry.bean.BaseModel;

import java.math.BigDecimal;

import javax.persistence.*;

/**
 * <p> ExDigitalmoneyAccount </p>
 * @author:         liushilei
 * @Date :          2018-06-13 10:56:33  
 */
@Table(name="ex_digitalmoney_account")
public class ExDigitalmoneyAccount extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "version")
	private Integer version;  //
	
	@Column(name= "customerId")
	private Long customerId;  //
	
	@Column(name= "hotMoney")
	private BigDecimal hotMoney;  //
	
	@Column(name= "coldMoney")
	private BigDecimal coldMoney;  //
	
	@Column(name= "userName")
	private String userName;  //
	
	@Column(name= "accountNum")
	private String accountNum;  //
	
	@Column(name= "currencyType")
	private String currencyType;  //
	
	@Column(name= "status")
	private Integer status;  //
	
	@Column(name= "publicKey")
	private String publicKey;  //
	
	@Column(name= "privateKey")
	private String privateKey;  //
	
	@Column(name= "saasId")
	private String saasId;  //
	
	@Column(name= "lendMoney")
	private BigDecimal lendMoney;  //
	
	@Column(name= "coinName")
	private String coinName;  //
	
	@Column(name= "coinCode")
	private String coinCode;  //
	
	@Column(name= "website")
	private String website;  //
	
	@Column(name= "psitioNaveragePrice")
	private BigDecimal psitioNaveragePrice;  //
	
	@Column(name= "psitioProtectPrice")
	private BigDecimal psitioProtectPrice;  //
	
	@Column(name= "sumCost")
	private BigDecimal sumCost;  //
	
	@Column(name= "disableMoney")
	private BigDecimal disableMoney;  //
	
	@Column(name= "trueName")
	private String trueName;  //
	
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
	 * @Date :   2018-06-13 10:56:33    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-06-13 10:56:33   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Integer 
	 * @Date :   2018-06-13 10:56:33    
	 */
	public Integer getVersion() {
		return version;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param version
	 * @return:  void 
	 * @Date :   2018-06-13 10:56:33   
	 */
	public void setVersion(Integer version) {
		this.version = version;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Long 
	 * @Date :   2018-06-13 10:56:33    
	 */
	public Long getCustomerId() {
		return customerId;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param customerId
	 * @return:  void 
	 * @Date :   2018-06-13 10:56:33   
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  BigDecimal 
	 * @Date :   2018-06-13 10:56:33    
	 */
	public BigDecimal getHotMoney() {
		return hotMoney;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param hotMoney
	 * @return:  void 
	 * @Date :   2018-06-13 10:56:33   
	 */
	public void setHotMoney(BigDecimal hotMoney) {
		this.hotMoney = hotMoney;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  BigDecimal 
	 * @Date :   2018-06-13 10:56:33    
	 */
	public BigDecimal getColdMoney() {
		return coldMoney;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param coldMoney
	 * @return:  void 
	 * @Date :   2018-06-13 10:56:33   
	 */
	public void setColdMoney(BigDecimal coldMoney) {
		this.coldMoney = coldMoney;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 10:56:33    
	 */
	public String getUserName() {
		return userName;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param userName
	 * @return:  void 
	 * @Date :   2018-06-13 10:56:33   
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 10:56:33    
	 */
	public String getAccountNum() {
		return accountNum;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param accountNum
	 * @return:  void 
	 * @Date :   2018-06-13 10:56:33   
	 */
	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 10:56:33    
	 */
	public String getCurrencyType() {
		return currencyType;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param currencyType
	 * @return:  void 
	 * @Date :   2018-06-13 10:56:33   
	 */
	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Integer 
	 * @Date :   2018-06-13 10:56:33    
	 */
	public Integer getStatus() {
		return status;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param status
	 * @return:  void 
	 * @Date :   2018-06-13 10:56:33   
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 10:56:33    
	 */
	public String getPublicKey() {
		return publicKey;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param publicKey
	 * @return:  void 
	 * @Date :   2018-06-13 10:56:33   
	 */
	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 10:56:33    
	 */
	public String getPrivateKey() {
		return privateKey;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param privateKey
	 * @return:  void 
	 * @Date :   2018-06-13 10:56:33   
	 */
	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 10:56:33    
	 */
	public String getSaasId() {
		return saasId;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param saasId
	 * @return:  void 
	 * @Date :   2018-06-13 10:56:33   
	 */
	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  BigDecimal 
	 * @Date :   2018-06-13 10:56:33    
	 */
	public BigDecimal getLendMoney() {
		return lendMoney;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param lendMoney
	 * @return:  void 
	 * @Date :   2018-06-13 10:56:33   
	 */
	public void setLendMoney(BigDecimal lendMoney) {
		this.lendMoney = lendMoney;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 10:56:33    
	 */
	public String getCoinName() {
		return coinName;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param coinName
	 * @return:  void 
	 * @Date :   2018-06-13 10:56:33   
	 */
	public void setCoinName(String coinName) {
		this.coinName = coinName;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 10:56:33    
	 */
	public String getCoinCode() {
		return coinCode;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param coinCode
	 * @return:  void 
	 * @Date :   2018-06-13 10:56:33   
	 */
	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 10:56:33    
	 */
	public String getWebsite() {
		return website;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param website
	 * @return:  void 
	 * @Date :   2018-06-13 10:56:33   
	 */
	public void setWebsite(String website) {
		this.website = website;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  BigDecimal 
	 * @Date :   2018-06-13 10:56:33    
	 */
	public BigDecimal getPsitioNaveragePrice() {
		return psitioNaveragePrice;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param psitioNaveragePrice
	 * @return:  void 
	 * @Date :   2018-06-13 10:56:33   
	 */
	public void setPsitioNaveragePrice(BigDecimal psitioNaveragePrice) {
		this.psitioNaveragePrice = psitioNaveragePrice;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  BigDecimal 
	 * @Date :   2018-06-13 10:56:33    
	 */
	public BigDecimal getPsitioProtectPrice() {
		return psitioProtectPrice;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param psitioProtectPrice
	 * @return:  void 
	 * @Date :   2018-06-13 10:56:33   
	 */
	public void setPsitioProtectPrice(BigDecimal psitioProtectPrice) {
		this.psitioProtectPrice = psitioProtectPrice;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  BigDecimal 
	 * @Date :   2018-06-13 10:56:33    
	 */
	public BigDecimal getSumCost() {
		return sumCost;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param sumCost
	 * @return:  void 
	 * @Date :   2018-06-13 10:56:33   
	 */
	public void setSumCost(BigDecimal sumCost) {
		this.sumCost = sumCost;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  BigDecimal 
	 * @Date :   2018-06-13 10:56:33    
	 */
	public BigDecimal getDisableMoney() {
		return disableMoney;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param disableMoney
	 * @return:  void 
	 * @Date :   2018-06-13 10:56:33   
	 */
	public void setDisableMoney(BigDecimal disableMoney) {
		this.disableMoney = disableMoney;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 10:56:33    
	 */
	public String getTrueName() {
		return trueName;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param trueName
	 * @return:  void 
	 * @Date :   2018-06-13 10:56:33   
	 */
	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 10:56:33    
	 */
	public String getSurname() {
		return surname;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param surname
	 * @return:  void 
	 * @Date :   2018-06-13 10:56:33   
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	

}
