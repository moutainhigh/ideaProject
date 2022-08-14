package hry.manage.remote.model;


import hry.bean.BaseModel;

import java.util.Date;


public class AppCustomer extends BaseModel {

	
	private Long id;
	
	private String userName;   //用户名
	
	private String passWord;  //密码
	
	private String accountPassWord;  //交易密码
	
	private Integer isLock;  //是否锁定   0没锁定  1锁定
	
	private Date lockTime;  //锁定时间
	
	private Integer isChange;  //是否能交易  0可以交易1不能交易
	
	private Integer isDelete;  //是否禁用  0没有禁用 1禁用
	
	// 状态(  0 未实名 1 待审核 2 已实名 3 已拒绝)
	private Integer states;
	
	private Integer isReal;  //是否实名  0没有实名  1实名
	
	private Integer isRealUsd; //是否国际站实名  0 没有实名  1实名
	
	private String salt;  //盐
	
	private String userCode;  //用户唯一ID标识 系统生成
	
	private Integer integral;  //积分
	
	private String type;  //integral  积分用户
	
	private Object appPersonInfo;
	
    private String referralCode;
    
	private String trueName;  //真实名

	private String surname;  //真实姓
   
    private String googleKey;
    
	public Integer hasEmail;
	
	private String company;

	private Integer trustNum;

	private Integer shieldNum;

	private String nickNameOtc;

	private String commonLanguage; //常用语言

	private Integer safeLoginType = 1; //1登录密码,2登录密码加手机，3登录密码加google或者手机  ,默认为1

	private Integer safeTixianType = 1; //1登录密码,2登录密码加手机，3登录密码加google或者手机  ,默认为1

	private Integer safeTradeType = 1; //1登录密码,2登录密码加手机，3登录密码加google或者手机  ,默认为1

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
	public Integer getStates() {
		return states;
	}
	public void setStates(Integer states) {
		this.states = states;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getAccountPassWord() {
		return accountPassWord;
	}
	public void setAccountPassWord(String accountPassWord) {
		this.accountPassWord = accountPassWord;
	}
	public Integer getIsLock() {
		return isLock;
	}
	public void setIsLock(Integer isLock) {
		this.isLock = isLock;
	}
	public Date getLockTime() {
		return lockTime;
	}
	public void setLockTime(Date lockTime) {
		this.lockTime = lockTime;
	}
	public Integer getIsChange() {
		return isChange;
	}
	public void setIsChange(Integer isChange) {
		this.isChange = isChange;
	}
	public Integer getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	public Integer getIsReal() {
		return isReal;
	}
	public void setIsReal(Integer isReal) {
		this.isReal = isReal;
	}
	public Integer getIsRealUsd() {
		return isRealUsd;
	}
	public void setIsRealUsd(Integer isRealUsd) {
		this.isRealUsd = isRealUsd;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public Integer getIntegral() {
		return integral;
	}
	public void setIntegral(Integer integral) {
		this.integral = integral;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Object getAppPersonInfo() {
		return appPersonInfo;
	}
	public void setAppPersonInfo(Object appPersonInfo) {
		this.appPersonInfo = appPersonInfo;
	}
	public String getReferralCode() {
		return referralCode;
	}
	public void setReferralCode(String referralCode) {
		this.referralCode = referralCode;
	}
	public Integer getHasEmail() {
		return hasEmail;
	}
	public void setHasEmail(Integer hasEmail) {
		this.hasEmail = hasEmail;
	}
	public String getGoogleKey() {
		return googleKey;
	}
	public void setGoogleKey(String googleKey) {
		this.googleKey = googleKey;
	}

	public Integer getTrustNum () {
		return trustNum;
	}

	public void setTrustNum (Integer trustNum) {
		this.trustNum = trustNum;
	}

	public Integer getShieldNum () {
		return shieldNum;
	}

	public void setShieldNum (Integer shieldNum) {
		this.shieldNum = shieldNum;
	}

	public String getNickNameOtc () {
		return nickNameOtc;
	}

	public void setNickNameOtc (String nickNameOtc) {
		this.nickNameOtc = nickNameOtc;
	}

	public Integer getSafeLoginType () {
		return safeLoginType;
	}

	public void setSafeLoginType (Integer safeLoginType) {
		this.safeLoginType = safeLoginType;
	}

	public Integer getSafeTixianType () {
		return safeTixianType;
	}

	public void setSafeTixianType (Integer safeTixianType) {
		this.safeTixianType = safeTixianType;
	}

	public Integer getSafeTradeType () {
		return safeTradeType;
	}

	public void setSafeTradeType (Integer safeTradeType) {
		this.safeTradeType = safeTradeType;
	}
}
