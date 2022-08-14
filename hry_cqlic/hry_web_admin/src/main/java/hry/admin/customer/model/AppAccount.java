/**
 * Copyright:   
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-15 13:08:06 
 */
package hry.admin.customer.model;


import hry.bean.BaseModel;

import java.math.BigDecimal;

import javax.persistence.*;

/**
 * <p> AppAccount </p>
 * @author:         liushilei
 * @Date :          2018-06-15 13:08:06  
 */
@Table(name="app_account")
public class AppAccount extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "customerId")
	private Long customerId;  //
	
	@Column(name= "userName")
	private String userName;  //
	
	@Column(name= "hotMoney")
	private BigDecimal hotMoney;  //
	
	@Column(name= "coldMoney")
	private BigDecimal coldMoney;  //
	
	@Column(name= "version")
	private Integer version;  //
	
	@Column(name= "accountNum")
	private String accountNum;  //
	
	@Column(name= "currencyType")
	private String currencyType;  //
	
	@Column(name= "status")
	private Integer status;  //
	
	@Column(name= "saasId")
	private String saasId;  //
	
	@Column(name= "lendMoney")
	private BigDecimal lendMoney;  //
	
	@Column(name= "website")
	private String website;  //
	
	@Column(name= "trueName")
	private String trueName;  //
	
	@Column(name= "rewardMoney")
	private BigDecimal rewardMoney;  //
	
	@Column(name= "hasRewardMoney")
	private BigDecimal hasRewardMoney;  //
	
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
	 * @Date :   2018-06-15 13:08:06    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-06-15 13:08:06   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Long 
	 * @Date :   2018-06-15 13:08:06    
	 */
	public Long getCustomerId() {
		return customerId;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param customerId
	 * @return:  void 
	 * @Date :   2018-06-15 13:08:06   
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-15 13:08:06    
	 */
	public String getUserName() {
		return userName;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param userName
	 * @return:  void 
	 * @Date :   2018-06-15 13:08:06   
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  BigDecimal 
	 * @Date :   2018-06-15 13:08:06    
	 */
	public BigDecimal getHotMoney() {
		return hotMoney;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param hotMoney
	 * @return:  void 
	 * @Date :   2018-06-15 13:08:06   
	 */
	public void setHotMoney(BigDecimal hotMoney) {
		this.hotMoney = hotMoney;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  BigDecimal 
	 * @Date :   2018-06-15 13:08:06    
	 */
	public BigDecimal getColdMoney() {
		return coldMoney;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param coldMoney
	 * @return:  void 
	 * @Date :   2018-06-15 13:08:06   
	 */
	public void setColdMoney(BigDecimal coldMoney) {
		this.coldMoney = coldMoney;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Integer 
	 * @Date :   2018-06-15 13:08:06    
	 */
	public Integer getVersion() {
		return version;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param version
	 * @return:  void 
	 * @Date :   2018-06-15 13:08:06   
	 */
	public void setVersion(Integer version) {
		this.version = version;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-15 13:08:06    
	 */
	public String getAccountNum() {
		return accountNum;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param accountNum
	 * @return:  void 
	 * @Date :   2018-06-15 13:08:06   
	 */
	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-15 13:08:06    
	 */
	public String getCurrencyType() {
		return currencyType;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param currencyType
	 * @return:  void 
	 * @Date :   2018-06-15 13:08:06   
	 */
	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Integer 
	 * @Date :   2018-06-15 13:08:06    
	 */
	public Integer getStatus() {
		return status;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param status
	 * @return:  void 
	 * @Date :   2018-06-15 13:08:06   
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-15 13:08:06    
	 */
	public String getSaasId() {
		return saasId;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param saasId
	 * @return:  void 
	 * @Date :   2018-06-15 13:08:06   
	 */
	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  BigDecimal 
	 * @Date :   2018-06-15 13:08:06    
	 */
	public BigDecimal getLendMoney() {
		return lendMoney;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param lendMoney
	 * @return:  void 
	 * @Date :   2018-06-15 13:08:06   
	 */
	public void setLendMoney(BigDecimal lendMoney) {
		this.lendMoney = lendMoney;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-15 13:08:06    
	 */
	public String getWebsite() {
		return website;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param website
	 * @return:  void 
	 * @Date :   2018-06-15 13:08:06   
	 */
	public void setWebsite(String website) {
		this.website = website;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-15 13:08:06    
	 */
	public String getTrueName() {
		return trueName;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param trueName
	 * @return:  void 
	 * @Date :   2018-06-15 13:08:06   
	 */
	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  BigDecimal 
	 * @Date :   2018-06-15 13:08:06    
	 */
	public BigDecimal getRewardMoney() {
		return rewardMoney;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param rewardMoney
	 * @return:  void 
	 * @Date :   2018-06-15 13:08:06   
	 */
	public void setRewardMoney(BigDecimal rewardMoney) {
		this.rewardMoney = rewardMoney;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  BigDecimal 
	 * @Date :   2018-06-15 13:08:06    
	 */
	public BigDecimal getHasRewardMoney() {
		return hasRewardMoney;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param hasRewardMoney
	 * @return:  void 
	 * @Date :   2018-06-15 13:08:06   
	 */
	public void setHasRewardMoney(BigDecimal hasRewardMoney) {
		this.hasRewardMoney = hasRewardMoney;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-15 13:08:06    
	 */
	public String getSurname() {
		return surname;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param surname
	 * @return:  void 
	 * @Date :   2018-06-15 13:08:06   
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	

}
