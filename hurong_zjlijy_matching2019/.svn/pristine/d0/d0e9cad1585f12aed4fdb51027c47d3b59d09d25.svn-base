/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Liu Shilei
 * @version:      V1.0 
 * @Date:        2016年3月24日 下午2:59:48
 */
package hry.customer.user.model;

import static javax.persistence.GenerationType.IDENTITY;
import hry.core.mvc.model.BaseModel;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * <p>客户基础表---登录账号信息</p>
 * @author:         Liu Shilei 
 * @Date :          2016年3月24日 下午2:59:48 
 */
@Table(name="app_customer")
public class AppCustomer extends BaseModel{
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	
	@Column(name="userName")
	private String userName;   //用户名
	
	@Column(name="passWord")
	private String passWord;  //密码
	
	@Column(name="accountPassWord")
	private String accountPassWord;  //交易密码
	
	@Column(name="isLock")
	private Integer isLock;  //是否锁定   0没锁定  1锁定
	
	@Column(name="lockTime")
	private Date lockTime;  //锁定时间
	
	@Column(name="isChange")
	private Integer isChange;  //是否能交易  0可以交易1不能交易
	
	@Column(name="isDelete")
	private Integer isDelete;  //是否禁用  0没有禁用 1禁用
	
	
	
	@Column(name="isReal")
	private Integer isReal;  //是否实名  0没有实名  1实名
	
	@Column(name="isRealUsd")
	private Integer isRealUsd; //是否国际站实名  0 没有实名  1实名
	
	@Column(name="salt")  
	private String salt;  //盐
	
	@Column(name="userCode")  
	private String userCode;  //用户唯一ID标识 系统生成
	
	@Column(name="integral")
	private Integer integral;  //积分
	
	@Column(name="type")
	private String type;  //integral  积分用户
	
	@Transient   //不与数据库映射的字段
	private Object appPersonInfo;
	
	// 注册码
	@Column(name="referralCode")
    private String referralCode;
	
	// 用户是否注册邮箱
	@Column(name="hasEmail")
	public Integer hasEmail;
	
	@Column(name="googleKey")
	private String googleKey;
	
	@Column(name="googleState")
	private Integer googleState;//谷歌认证状态(0否，1是)
	
	
	@Column(name="googleDate")
	private Date googleDate;//停用时间
	
	

	@Column(name="isProving")
	private Integer isProving;//二次验证是否开启（0 否,1是）

	@Column(name="messIp")
	private String messIp;//二次验证是否开启（0 否,1是）
	
	
	@Column(name="passDate")
	private Date passDate;//二次验证是否开启（0 否,1是）
	
	
	@Column(name="phone")
	private String phone;
	
	@Column(name="phoneState")
	private Integer phoneState;//谷歌认证状态(0否，1是)
	
	
	@Column(name="openPhone")
	private Integer openPhone;//谷歌认证状态(0否，1是)
	
	// 状态( 1 表示申请中  2 表示申请通过 3 表示已删除)
	@Column(name="states")
	private Integer states;
	
	@Column(name="uuid")
	private String uuid;
	
	@Transient
	private Long customerId;

	@Transient
	private String truename;
	
	@Transient
	private String surname;
	
	
	@Column(name="commendCode")
	private String commendCode;
	
	@Column(name="receCode")
	private String receCode;
	
	
	
	
	public String getReceCode() {
		return receCode;
	}

	public void setReceCode(String receCode) {
		this.receCode = receCode;
	}

	public String getCommendCode() {
		return commendCode;
	}

	public void setCommendCode(String commendCode) {
		this.commendCode = commendCode;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getTruename() {
		return truename;
	}

	public void setTruename(String truename) {
		this.truename = truename;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Integer getStates() {
		return states;
	}

	public void setStates(Integer states) {
		this.states = states;
	}



	public Integer getOpenPhone() {
		return openPhone;
	}

	public void setOpenPhone(Integer openPhone) {
		this.openPhone = openPhone;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getPhoneState() {
		return phoneState;
	}

	public void setPhoneState(Integer phoneState) {
		this.phoneState = phoneState;
	}

	public Date getPassDate() {
		return passDate;
	}

	public void setPassDate(Date passDate) {
		this.passDate = passDate;
	}

	public String getMessIp() {
		return messIp;
	}

	public void setMessIp(String messIp) {
		this.messIp = messIp;
	}

	public Integer getGoogleState() {
		return googleState;
	}

	public void setGoogleState(Integer googleState) {
		this.googleState = googleState;
	}
	public Date getGoogleDate() {
		return googleDate;
	}

	public void setGoogleDate(Date googleDate) {
		this.googleDate = googleDate;
	}

	public Integer getIsProving() {
		return isProving;
	}

	public void setIsProving(Integer isProving) {
		this.isProving = isProving;
	}

	/**
	 * <p> TODO</p>
	 * @return:     Integer
	 */
	public Integer getIsChange() {
		return isChange;
	}

	/** 
	 * <p> TODO</p>
	 * @return: Integer
	 */
	public void setIsChange(Integer isChange) {
		this.isChange = isChange;
	}

	public Integer getHasEmail() {
		return hasEmail;
	}

	public void setHasEmail(Integer hasEmail) {
		this.hasEmail = hasEmail;
	}

	/**
	 * @return the referralCode
	 */
	public String getReferralCode() {
		return referralCode;
	}

	/**
	 * @param referralCode the referralCode to set
	 */
	public void setReferralCode(String referralCode) {
		this.referralCode = referralCode;
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
	 * @return:     String
	 */
	public String getPassWord() {
		return passWord;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getAccountPassWord() {
		return accountPassWord;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setAccountPassWord(String accountPassWord) {
		this.accountPassWord = accountPassWord;
	}

	/**
	 * <p> TODO</p>
	 * @return:     Integer
	 */
	public Integer getIsLock() {
		return isLock;
	}

	/** 
	 * <p> TODO</p>
	 * @return: Integer
	 */
	public void setIsLock(Integer isLock) {
		this.isLock = isLock;
	}

	/**
	 * <p> TODO</p>
	 * @return:     Date
	 */
	public Date getLockTime() {
		return lockTime;
	}

	/** 
	 * <p> TODO</p>
	 * @return: Date
	 */
	public void setLockTime(Date lockTime) {
		this.lockTime = lockTime;
	}

	/**
	 * <p> TODO</p>
	 * @return:     Integer
	 */
	public Integer getIsDelete() {
		return isDelete;
	}

	/** 
	 * <p> TODO</p>
	 * @return: Integer
	 */
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	/**
	 * <p> TODO</p>
	 * @return:     Integer
	 */
	public Integer getIsReal() {
		return isReal;
	}

	/** 
	 * <p> TODO</p>
	 * @return: Integer
	 */
	public void setIsReal(Integer isReal) {
		this.isReal = isReal;
	}

	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getSalt() {
		return salt;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setSalt(String salt) {
		this.salt = salt;
	}

	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getUserCode() {
		return userCode;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}


	/**
	 * <p> TODO</p>
	 * @return:     Object
	 */
	public Object getAppPersonInfo() {
		return appPersonInfo;
	}

	/** 
	 * <p> TODO</p>
	 * @return: Object
	 */
	public void setAppPersonInfo(Object appPersonInfo) {
		this.appPersonInfo = appPersonInfo;
	}

	public Integer getIsRealUsd() {
		return isRealUsd;
	}

	public void setIsRealUsd(Integer isRealUsd) {
		this.isRealUsd = isRealUsd;
	}

	/**
	 * <p> TODO</p>
	 * @return:     Integer
	 */
	public Integer getIntegral() {
		return integral;
	}

	/** 
	 * <p> TODO</p>
	 * @return: Integer
	 */
	public void setIntegral(Integer integral) {
		this.integral = integral;
	}

	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getType() {
		return type;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setType(String type) {
		this.type = type;
	}

	public String getGoogleKey() {
		return googleKey;
	}

	public void setGoogleKey(String googleKey) {
		this.googleKey = googleKey;
	}

	
	
}
