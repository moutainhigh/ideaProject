/**
 * Copyright:   
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-13 09:43:00 
 */
package hry.admin.customer.model;


import hry.bean.BaseModel;

import javax.persistence.*;
import java.util.Date;

/**
 * <p> AppCustomer </p>
 * @author:         liushilei
 * @Date :          2018-06-13 09:43:00  
 */
@Table(name="app_customer")
public class AppCustomer extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "username")
	private String username;  //

	@Transient
	private String trueName; // 真实名

	@Transient
	private String surname; // 真实姓


	@Column(name= "passWord")
	private String passWord;  //
	
	@Column(name= "accountPassWord")
	private String accountPassWord;  //
	
	@Column(name= "isLock")
	private Integer isLock;  //
	
	@Column(name= "lockTime")
	private Date lockTime;  //
	
	@Column(name= "isDelete")
	private Integer isDelete;  //
	
	@Column(name= "isReal")
	private Integer isReal;  //是否实名 0没有实名 1实名
	
	@Column(name= "isRealUsd")
	private Integer isRealUsd;  //
	
	@Column(name= "salt")
	private String salt;  //
	
	@Column(name= "userCode")
	private String userCode;  //
	
	@Column(name= "saasId")
	private String saasId;  //
	
	@Column(name= "referralCode")
	private String referralCode;  //
	
	@Column(name= "hasEmail")
	private Integer hasEmail;  //
	
	@Column(name= "integral")
	private Integer integral;  //
	
	@Column(name= "type")
	private String type;  //
	
	@Column(name= "isChange")
	private Integer isChange;  //0开启，1未开启
	
	@Column(name= "googleKey")
	private String googleKey;  //
	
	@Column(name= "googleState")
	private Integer googleState;  //
	
	@Column(name= "isproving")
	private Integer isproving;  //
	
	@Column(name= "messIp")
	private String messIp;  //
	
	@Column(name= "phoneState")
	private Integer phoneState;  //
	
	@Column(name= "phone")
	private String phone;  //
	
	@Column(name= "googleDate")
	private Date googleDate;  //
	
	@Column(name= "passDate")
	private Date passDate;  //
	
	@Column(name= "openPhone")
	private Integer openPhone;  //
	
	@Column(name= "states")
	private Integer states;  //
	
	@Column(name= "uuid")
	private String uuid;  //
	
	@Column(name= "commendCode")
	private String commendCode;  //
	
	@Column(name= "receCode")
	private String receCode;  //
	
	@Column(name= "isAdmin")
	private Integer isAdmin;  //
	
	@Column(name= "isGag")
	private Integer isGag;  // 李超项目： 是否开启动态收益 ：0 开启 1：关闭
	
	@Column(name= "gagDate")
	private Date gagDate;  //
	
	@Column(name= "gagTime")
	private Integer gagTime;  //
	
	@Column(name= "safeLoginType")
	private Integer safeLoginType;  //
	
	@Column(name= "safeTixianType")
	private Integer safeTixianType;  //
	
	@Column(name= "safeTradeType")
	private Integer safeTradeType;  //
	
	@Column(name= "isBlacklist")
	private Integer isBlacklist;  //

	@Column(name= "commonLanguage")
	private String commonLanguage;

	@Transient // 不与数据库映射的字段
	private Object appPersonInfo;

	//当前级别
	@Column(name= "level")
	private String level;

	//当前经验
	@Column(name= "experience")
	private Long experience;

	@Transient
	private String pEmail; // 父级用户邮箱

	public String getpEmail() {
		return pEmail;
	}

	public void setpEmail(String pEmail) {
		this.pEmail = pEmail;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public Long getExperience() {
		return experience;
	}

	public void setExperience(Long experience) {
		this.experience = experience;
	}


	public String getCommonLanguage() {
		return commonLanguage;
	}

	public void setCommonLanguage(String commonLanguage) {
		this.commonLanguage = commonLanguage;
	}

	public String getTrueName() {
		return trueName;
	}

	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

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
	 * @Date :   2018-06-13 09:43:00    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-06-13 09:43:00   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 09:43:00    
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param username
	 * @return:  void 
	 * @Date :   2018-06-13 09:43:00   
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 09:43:00    
	 */
	public String getPassWord() {
		return passWord;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param passWord
	 * @return:  void 
	 * @Date :   2018-06-13 09:43:00   
	 */
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 09:43:00    
	 */
	public String getAccountPassWord() {
		return accountPassWord;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param accountPassWord
	 * @return:  void 
	 * @Date :   2018-06-13 09:43:00   
	 */
	public void setAccountPassWord(String accountPassWord) {
		this.accountPassWord = accountPassWord;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Integer 
	 * @Date :   2018-06-13 09:43:00    
	 */
	public Integer getIsLock() {
		return isLock;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param isLock
	 * @return:  void 
	 * @Date :   2018-06-13 09:43:00   
	 */
	public void setIsLock(Integer isLock) {
		this.isLock = isLock;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Date 
	 * @Date :   2018-06-13 09:43:00    
	 */
	public Date getLockTime() {
		return lockTime;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param lockTime
	 * @return:  void 
	 * @Date :   2018-06-13 09:43:00   
	 */
	public void setLockTime(Date lockTime) {
		this.lockTime = lockTime;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Integer 
	 * @Date :   2018-06-13 09:43:00    
	 */
	public Integer getIsDelete() {
		return isDelete;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param isDelete
	 * @return:  void 
	 * @Date :   2018-06-13 09:43:00   
	 */
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Integer 
	 * @Date :   2018-06-13 09:43:00    
	 */
	public Integer getIsReal() {
		return isReal;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param isReal
	 * @return:  void 
	 * @Date :   2018-06-13 09:43:00   
	 */
	public void setIsReal(Integer isReal) {
		this.isReal = isReal;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Integer 
	 * @Date :   2018-06-13 09:43:00    
	 */
	public Integer getIsRealUsd() {
		return isRealUsd;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param isRealUsd
	 * @return:  void 
	 * @Date :   2018-06-13 09:43:00   
	 */
	public void setIsRealUsd(Integer isRealUsd) {
		this.isRealUsd = isRealUsd;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 09:43:00    
	 */
	public String getSalt() {
		return salt;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param salt
	 * @return:  void 
	 * @Date :   2018-06-13 09:43:00   
	 */
	public void setSalt(String salt) {
		this.salt = salt;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 09:43:00    
	 */
	public String getUserCode() {
		return userCode;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param userCode
	 * @return:  void 
	 * @Date :   2018-06-13 09:43:00   
	 */
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 09:43:00    
	 */
	public String getSaasId() {
		return saasId;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param saasId
	 * @return:  void 
	 * @Date :   2018-06-13 09:43:00   
	 */
	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 09:43:00    
	 */
	public String getReferralCode() {
		return referralCode;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param referralCode
	 * @return:  void 
	 * @Date :   2018-06-13 09:43:00   
	 */
	public void setReferralCode(String referralCode) {
		this.referralCode = referralCode;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Integer 
	 * @Date :   2018-06-13 09:43:00    
	 */
	public Integer getHasEmail() {
		return hasEmail;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param hasEmail
	 * @return:  void 
	 * @Date :   2018-06-13 09:43:00   
	 */
	public void setHasEmail(Integer hasEmail) {
		this.hasEmail = hasEmail;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Integer 
	 * @Date :   2018-06-13 09:43:00    
	 */
	public Integer getIntegral() {
		return integral;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param integral
	 * @return:  void 
	 * @Date :   2018-06-13 09:43:00   
	 */
	public void setIntegral(Integer integral) {
		this.integral = integral;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 09:43:00    
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param type
	 * @return:  void 
	 * @Date :   2018-06-13 09:43:00   
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Integer 
	 * @Date :   2018-06-13 09:43:00    
	 */
	public Integer getIsChange() {
		return isChange;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param isChange
	 * @return:  void 
	 * @Date :   2018-06-13 09:43:00   
	 */
	public void setIsChange(Integer isChange) {
		this.isChange = isChange;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 09:43:00    
	 */
	public String getGoogleKey() {
		return googleKey;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param googleKey
	 * @return:  void 
	 * @Date :   2018-06-13 09:43:00   
	 */
	public void setGoogleKey(String googleKey) {
		this.googleKey = googleKey;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Integer 
	 * @Date :   2018-06-13 09:43:00    
	 */
	public Integer getGoogleState() {
		return googleState;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param googleState
	 * @return:  void 
	 * @Date :   2018-06-13 09:43:00   
	 */
	public void setGoogleState(Integer googleState) {
		this.googleState = googleState;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Integer 
	 * @Date :   2018-06-13 09:43:00    
	 */
	public Integer getIsproving() {
		return isproving;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param isproving
	 * @return:  void 
	 * @Date :   2018-06-13 09:43:00   
	 */
	public void setIsproving(Integer isproving) {
		this.isproving = isproving;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 09:43:00    
	 */
	public String getMessIp() {
		return messIp;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param messIp
	 * @return:  void 
	 * @Date :   2018-06-13 09:43:00   
	 */
	public void setMessIp(String messIp) {
		this.messIp = messIp;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Integer 
	 * @Date :   2018-06-13 09:43:00    
	 */
	public Integer getPhoneState() {
		return phoneState;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param phoneState
	 * @return:  void 
	 * @Date :   2018-06-13 09:43:00   
	 */
	public void setPhoneState(Integer phoneState) {
		this.phoneState = phoneState;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 09:43:00    
	 */
	public String getPhone() {
		return phone;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param phone
	 * @return:  void 
	 * @Date :   2018-06-13 09:43:00   
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Date 
	 * @Date :   2018-06-13 09:43:00    
	 */
	public Date getGoogleDate() {
		return googleDate;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param googleDate
	 * @return:  void 
	 * @Date :   2018-06-13 09:43:00   
	 */
	public void setGoogleDate(Date googleDate) {
		this.googleDate = googleDate;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Date 
	 * @Date :   2018-06-13 09:43:00    
	 */
	public Date getPassDate() {
		return passDate;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param passDate
	 * @return:  void 
	 * @Date :   2018-06-13 09:43:00   
	 */
	public void setPassDate(Date passDate) {
		this.passDate = passDate;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Integer 
	 * @Date :   2018-06-13 09:43:00    
	 */
	public Integer getOpenPhone() {
		return openPhone;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param openPhone
	 * @return:  void 
	 * @Date :   2018-06-13 09:43:00   
	 */
	public void setOpenPhone(Integer openPhone) {
		this.openPhone = openPhone;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Integer 
	 * @Date :   2018-06-13 09:43:00    
	 */
	public Integer getStates() {
		return states;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param states
	 * @return:  void 
	 * @Date :   2018-06-13 09:43:00   
	 */
	public void setStates(Integer states) {
		this.states = states;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 09:43:00    
	 */
	public String getUuid() {
		return uuid;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param uuid
	 * @return:  void 
	 * @Date :   2018-06-13 09:43:00   
	 */
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 09:43:00    
	 */
	public String getCommendCode() {
		return commendCode;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param commendCode
	 * @return:  void 
	 * @Date :   2018-06-13 09:43:00   
	 */
	public void setCommendCode(String commendCode) {
		this.commendCode = commendCode;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 09:43:00    
	 */
	public String getReceCode() {
		return receCode;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param receCode
	 * @return:  void 
	 * @Date :   2018-06-13 09:43:00   
	 */
	public void setReceCode(String receCode) {
		this.receCode = receCode;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Integer 
	 * @Date :   2018-06-13 09:43:00    
	 */
	public Integer getIsAdmin() {
		return isAdmin;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param isAdmin
	 * @return:  void 
	 * @Date :   2018-06-13 09:43:00   
	 */
	public void setIsAdmin(Integer isAdmin) {
		this.isAdmin = isAdmin;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Integer 
	 * @Date :   2018-06-13 09:43:00    
	 */
	public Integer getIsGag() {
		return isGag;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param isGag
	 * @return:  void 
	 * @Date :   2018-06-13 09:43:00   
	 */
	public void setIsGag(Integer isGag) {
		this.isGag = isGag;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Date 
	 * @Date :   2018-06-13 09:43:00    
	 */
	public Date getGagDate() {
		return gagDate;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param gagDate
	 * @return:  void 
	 * @Date :   2018-06-13 09:43:00   
	 */
	public void setGagDate(Date gagDate) {
		this.gagDate = gagDate;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Integer 
	 * @Date :   2018-06-13 09:43:00    
	 */
	public Integer getGagTime() {
		return gagTime;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param gagTime
	 * @return:  void 
	 * @Date :   2018-06-13 09:43:00   
	 */
	public void setGagTime(Integer gagTime) {
		this.gagTime = gagTime;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Integer 
	 * @Date :   2018-06-13 09:43:00    
	 */
	public Integer getSafeLoginType() {
		return safeLoginType;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param safeLoginType
	 * @return:  void 
	 * @Date :   2018-06-13 09:43:00   
	 */
	public void setSafeLoginType(Integer safeLoginType) {
		this.safeLoginType = safeLoginType;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Integer 
	 * @Date :   2018-06-13 09:43:00    
	 */
	public Integer getSafeTixianType() {
		return safeTixianType;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param safeTixianType
	 * @return:  void 
	 * @Date :   2018-06-13 09:43:00   
	 */
	public void setSafeTixianType(Integer safeTixianType) {
		this.safeTixianType = safeTixianType;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Integer 
	 * @Date :   2018-06-13 09:43:00    
	 */
	public Integer getSafeTradeType() {
		return safeTradeType;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param safeTradeType
	 * @return:  void 
	 * @Date :   2018-06-13 09:43:00   
	 */
	public void setSafeTradeType(Integer safeTradeType) {
		this.safeTradeType = safeTradeType;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Integer 
	 * @Date :   2018-06-13 09:43:00    
	 */
	public Integer getIsBlacklist() {
		return isBlacklist;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param isBlacklist
	 * @return:  void 
	 * @Date :   2018-06-13 09:43:00   
	 */
	public void setIsBlacklist(Integer isBlacklist) {
		this.isBlacklist = isBlacklist;
	}
	
	

}
