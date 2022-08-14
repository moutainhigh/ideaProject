package hry.manage.remote.model;

import java.util.Date;

import hry.bean.BaseModel;
import io.swagger.annotations.ApiModelProperty;

public class User extends BaseModel {
	
	@ApiModelProperty(value = "用户名，系统生成" )
	private String username;

	@ApiModelProperty(value = "登录方式(email邮箱 mobile手机)")
	private String loginType; //email 或 mobile
	
	@ApiModelProperty(value = "密码" )
	private String password;
	
	@ApiModelProperty(value = "用户唯一标识")
	private String userCode;
	
	@ApiModelProperty(value = "是否实名(0没有实名,1实名)" )
	private  int isReal;  
	
	@ApiModelProperty(value = "是否能交易(0可以交易,1不能交易)" )
	private  int isChange;
	
	@ApiModelProperty(value = "是否禁用(0没有禁用,1禁用)" )
	private int isDelete; 
	
	@ApiModelProperty(value = "是否锁定(0没锁定,1锁定)" )
	private int isLock;
	
	@ApiModelProperty(value = "交易密码" )
	private String accountPassWord;
	
	@ApiModelProperty(value = "用户id" )
	private Long customerId;

	@ApiModelProperty(value = "手机号" )
	private String mobile;
	
	@ApiModelProperty(value = "真实名" )
	private String truename;

	@ApiModelProperty(value = "真实姓" )
	private String surname;

	//`customerType` int(5) DEFAULT '1' COMMENT '客户类型customerType甲类账户1(普通的，默认)，乙类账号2，丙类账户3'
	private int customerType;

	@ApiModelProperty(value = "盐" )
	private String salt;
	
	@ApiModelProperty(value = "身份证" )
	private String cardcode;
	
	@ApiModelProperty(value = "邮箱" )
	private String email;

	@ApiModelProperty(value = "是否激活邮箱（0未激活,1激活)" )
	private Integer hasEmail;
	
	@ApiModelProperty(value = "性别" )
	private Integer sex;
	
	@ApiModelProperty(value = "详细地址" )
	private String postalAddress;

	@ApiModelProperty(value = "googleKey" )
	private String googleKey;

	@ApiModelProperty(value = "谷歌认证状态(0否，1是)" )
	private Integer googleState;
	
	
	private String messIp;
	
	private Date passDate;

	@ApiModelProperty(value = "手机认证状态(0否，1是)" )
	private Integer phoneState;
	
	private Integer states;
	
	private String tokenId;
	
	private String cardCurrency;
	
	private String uncardCurrency;
	
	private String company;
	
	private String uuid;
	
	//是否展示推荐返佣
	private Integer commend;
	
	//国家地址字段
	private String country;
	
	//登录后显示的用户名，可能是邮箱或者手机号,取决用户用什么方式登录
	private String nickName;
	
	
	private Integer safeLoginType = 1; //1登录密码,2登录密码加手机，3登录密码加google或者手机  ,默认为1
	
	private Integer safeTixianType = 1; //1登录密码,2登录密码加手机，3登录密码加google或者手机  ,默认为1
	
	private Integer safeTradeType = 1; //1登录密码,2登录密码加手机，3登录密码加google或者手机  ,默认为1
	
	private Integer isAdmin;//是否管理员  0否 1 是
	
	private Integer isBlacklist; // 0否,1是,默认为0 2为白名单

	private int isOpenCoinFee; //平台币支付手续费：0否 1是
	
	private String nickNameOtc; // otc昵称
	
	private String AreaCode;//区号

	private String commonLanguage; //常用语言

	private String  busNumber;//总线唯一标识

	private String  feature;//人脸待征码

	public String getFeature() {
		return feature;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}

	public String getBusNumber() {
		return busNumber;
	}

	public void setBusNumber(String busNumber) {
		this.busNumber = busNumber;
	}

	public String getCommonLanguage() {
		return commonLanguage;
	}

	public void setCommonLanguage(String commonLanguage) {
		this.commonLanguage = commonLanguage;
	}

	public String getAreaCode() {
		return AreaCode;
	}

	public void setAreaCode(String areaCode) {
		AreaCode = areaCode;
	}

	public String getNickNameOtc() {
		return nickNameOtc;
	}

	public void setNickNameOtc(String nickNameOtc) {
		this.nickNameOtc = nickNameOtc;
	}

	public int getIsOpenCoinFee () {
		return isOpenCoinFee;
	}

	public void setIsOpenCoinFee (int isOpenCoinFee) {
		this.isOpenCoinFee = isOpenCoinFee;
	}

	public Integer getIsBlacklist() {
		return isBlacklist;
	}

	public void setIsBlacklist(Integer isBlacklist) {
		this.isBlacklist = isBlacklist;
	}

	public Integer getSafeLoginType() {
		return safeLoginType;
	}

	public void setSafeLoginType(Integer safeLoginType) {
		this.safeLoginType = safeLoginType;
	}

	public Integer getSafeTixianType() {
		return safeTixianType;
	}

	public void setSafeTixianType(Integer safeTixianType) {
		this.safeTixianType = safeTixianType;
	}

	public Integer getSafeTradeType() {
		return safeTradeType;
	}

	public void setSafeTradeType(Integer safeTradeType) {
		this.safeTradeType = safeTradeType;
	}

	public Integer getHasEmail() {
		return hasEmail;
	}

	public void setHasEmail(Integer hasEmail) {
		this.hasEmail = hasEmail;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Integer getPhone_googleState(){
		
		if(this.phoneState ==1 && this.googleState == 1){
			return 1;
		}
		return 0;
	}

	public Integer getCommend() {
		return commend;
	}

	public void setCommend(Integer commend) {
		this.commend = commend;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getCardCurrency() {
		return cardCurrency;
	}

	public void setCardCurrency(String cardCurrency) {
		this.cardCurrency = cardCurrency;
	}

	public String getUncardCurrency() {
		return uncardCurrency;
	}

	public void setUncardCurrency(String uncardCurrency) {
		this.uncardCurrency = uncardCurrency;
	}

	public String getTokenId() {
		return tokenId;
	}

	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getStates() {
		return states;
	}

	public void setStates(Integer states) {
		this.states = states;
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

	public String getGoogleKey() {
		return googleKey;
	}

	public void setGoogleKey(String googleKey) {
		this.googleKey = googleKey;
	}

	public String getPostalAddress() {
		return postalAddress;
	}

	public void setPostalAddress(String postalAddress) {
		this.postalAddress = postalAddress;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCardcode() {
		return cardcode;
	}

	public void setCardcode(String cardcode) {
		this.cardcode = cardcode;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public int getCustomerType() {
		return customerType;
	}

	public void setCustomerType(int customerType) {
		this.customerType = customerType;
	}

	public String getTruename() {
		return truename;
	}

	public void setTruename(String truename) {
		this.truename = truename;
	}

	public String getUsername() {
//		return username.replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2");
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public int getIsReal() {
		return isReal;
	}

	public void setIsReal(int isReal) {
		this.isReal = isReal;
	}

	public int getIsChange() {
		return isChange;
	}

	public void setIsChange(int isChange) {
		this.isChange = isChange;
	}

	public int getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(int isDelete) {
		this.isDelete = isDelete;
	}

	public String getAccountPassWord() {
		return accountPassWord;
	}

	public void setAccountPassWord(String accountPassWord) {
		this.accountPassWord = accountPassWord;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public int getIsLock() {
		return isLock;
	}

	public void setIsLock(int isLock) {
		this.isLock = isLock;
	}

	public Integer getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Integer isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getLoginType() {
		return loginType;
	}

	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}
}
