/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      zhangcb
 * @version:     V1.0 
 * @Date:        2016-11-22 18:25:51 
 */
package hry.manage.remote.model;


import hry.bean.BaseModel;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p> AppPersonInfo </p>
 * @author:         zhangcb
 * @Date :          2016-11-22 18:25:51  
 */
@SuppressWarnings("serial")
public class AppPersonInfoManage extends BaseModel {
	
	
	private Long id;  //id
	
	private Long customerId;  //用户ID
	
	private Integer customerType;  //客户类型customerType甲类账户1(普通的，默认)，乙类账号2，丙类账户3
	
	private String mobilePhone;  //手机号
	
	private String email;  //邮箱
	
	private String trueName;  //真实名

	private String surName;  //真实姓
	
	private Integer sex;  //性别  0男  1女
	
	private String birthday;  //生日
	
	private String country;  //国家
	
	private Integer cardType;  //证件类型
	
	private String cardId;  //身份证号
	
	private Integer customerSource;  //客户来源   0线上注册   1业务员注册
	
	private Date realTime;  //realTime
	
	private String emailCode;  //email回调的时候的验证码
	
	private String cardIdUsd;  //cardIdUsd
	
	private String cardIdValidPeriod;  //身份证有效期
	
	private String postalAddress;  //通讯地址
	
	private String contacts;  //紧急联系人
	
	private Integer postCode;  //邮编
	
	private Integer stage;  //基础信息完善状态
	
	private String contactsTel;  //紧急联系人电话
	
	private String handIdCardUrl;  //手持身份证照片
	
	private String idCardFrontUrl;  //身份证正面照片
	
	private String idCardBackUrl;  //身份证背面照片
	
	private String handDealUrl;  //手持协议照片
	
	private Integer isExamine;  //审核状态 0=未审核 1=审核通过、2=审核不通过

	private String eamineRefusalReason;  //审核拒绝原因
	
	private Integer isCancle;  //是否注销 0、未注销  1、注销
	
	private String cancleReason;  //注销原因


	private Long vipUserId;  //会员ID
	
	private Long agentUserId;  //代理商ID
	
	private String vipNumber;  //会员编号
	
	private String agentNumber;  //代理商编号
	
	private String vipName;  //会员名称
	
	private String agentName;  //代理商名称
	
	//提现审核额度（默认为-1，无审核）否则提现大于该值，进入后台审核
	private BigDecimal withdrawCheckMoney; 
	
	
	
	public String getSurName() {
		return surName;
	}

	public void setSurName(String surName) {
		this.surName = surName;
	}

	public AppBankCardManage getAppBankCard() {
		return appBankCard;
	}

	public void setAppBankCard(AppBankCardManage appBankCard) {
		this.appBankCard = appBankCard;
	}

	private AppBankCardManage appBankCard;
	

	public Integer getIsCancle() {
		return isCancle;
	}

	public void setIsCancle(Integer isCancle) {
		this.isCancle = isCancle;
	}
	
	
	
	public String getCancleReason() {
		return cancleReason;
	}

	public void setCancleReason(String cancleReason) {
		this.cancleReason = cancleReason;
	}

	public Long getVipUserId() {
		return vipUserId;
	}

	public void setVipUserId(Long vipUserId) {
		this.vipUserId = vipUserId;
	}

	public Long getAgentUserId() {
		return agentUserId;
	}

	public void setAgentUserId(Long agentUserId) {
		this.agentUserId = agentUserId;
	}

	public String getVipNumber() {
		return vipNumber;
	}

	public void setVipNumber(String vipNumber) {
		this.vipNumber = vipNumber;
	}

	public String getAgentNumber() {
		return agentNumber;
	}

	public void setAgentNumber(String agentNumber) {
		this.agentNumber = agentNumber;
	}

	public String getVipName() {
		return vipName;
	}

	public void setVipName(String vipName) {
		this.vipName = vipName;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public String getEamineRefusalReason() {
		return eamineRefusalReason;
	}

	public void setEamineRefusalReason(String eamineRefusalReason) {
		this.eamineRefusalReason = eamineRefusalReason;
	}

	public Integer getIsExamine() {
		return isExamine;
	}

	public void setIsExamine(Integer isExamine) {
		this.isExamine = isExamine;
	}

	/**
	 * <p>id</p>
	 * @author:  zhangcb
	 * @return:  Long 
	 * @Date :   2016-11-22 18:25:51    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>id</p>
	 * @author:  zhangcb
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2016-11-22 18:25:51   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>用户ID</p>
	 * @author:  zhangcb
	 * @return:  Long 
	 * @Date :   2016-11-22 18:25:51    
	 */
	public Long getCustomerId() {
		return customerId;
	}
	
	/**
	 * <p>用户ID</p>
	 * @author:  zhangcb
	 * @param:   @param customerId
	 * @return:  void 
	 * @Date :   2016-11-22 18:25:51   
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	
	/**
	 * <p>客户类型customerType甲类账户1(普通的，默认)，乙类账号2，丙类账户3</p>
	 * @author:  zhangcb
	 * @return:  Integer 
	 * @Date :   2016-11-22 18:25:51    
	 */
	public Integer getCustomerType() {
		return customerType;
	}
	
	/**
	 * <p>客户类型customerType甲类账户1(普通的，默认)，乙类账号2，丙类账户3</p>
	 * @author:  zhangcb
	 * @param:   @param customerType
	 * @return:  void 
	 * @Date :   2016-11-22 18:25:51   
	 */
	public void setCustomerType(Integer customerType) {
		this.customerType = customerType;
	}
	
	
	/**
	 * <p>手机号</p>
	 * @author:  zhangcb
	 * @return:  String 
	 * @Date :   2016-11-22 18:25:51    
	 */
	public String getMobilePhone() {
		return mobilePhone;
	}
	
	/**
	 * <p>手机号</p>
	 * @author:  zhangcb
	 * @param:   @param mobilePhone
	 * @return:  void 
	 * @Date :   2016-11-22 18:25:51   
	 */
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	
	
	/**
	 * <p>邮箱</p>
	 * @author:  zhangcb
	 * @return:  String 
	 * @Date :   2016-11-22 18:25:51    
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * <p>邮箱</p>
	 * @author:  zhangcb
	 * @param:   @param email
	 * @return:  void 
	 * @Date :   2016-11-22 18:25:51   
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	/**
	 * <p>真实姓名</p>
	 * @author:  zhangcb
	 * @return:  String 
	 * @Date :   2016-11-22 18:25:51    
	 */
	public String getTrueName() {
		return trueName;
	}
	
	/**
	 * <p>真实姓名</p>
	 * @author:  zhangcb
	 * @param:   @param trueName
	 * @return:  void 
	 * @Date :   2016-11-22 18:25:51   
	 */
	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}
	
	
	/**
	 * <p>性别  0男  1女</p>
	 * @author:  zhangcb
	 * @return:  Integer 
	 * @Date :   2016-11-22 18:25:51    
	 */
	public Integer getSex() {
		return sex;
	}
	
	/**
	 * <p>性别  0男  1女</p>
	 * @author:  zhangcb
	 * @param:   @param sex
	 * @return:  void 
	 * @Date :   2016-11-22 18:25:51   
	 */
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	
	
	/**
	 * <p>生日</p>
	 * @author:  zhangcb
	 * @return:  String 
	 * @Date :   2016-11-22 18:25:51    
	 */
	public String getBirthday() {
		return birthday;
	}
	
	/**
	 * <p>生日</p>
	 * @author:  zhangcb
	 * @param:   @param birthday
	 * @return:  void 
	 * @Date :   2016-11-22 18:25:51   
	 */
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	
	
	/**
	 * <p>国家</p>
	 * @author:  zhangcb
	 * @return:  String 
	 * @Date :   2016-11-22 18:25:51    
	 */
	public String getCountry() {
		return country;
	}
	
	/**
	 * <p>国家</p>
	 * @author:  zhangcb
	 * @param:   @param country
	 * @return:  void 
	 * @Date :   2016-11-22 18:25:51   
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	
	
	/**
	 * <p>证件类型</p>
	 * @author:  zhangcb
	 * @return:  Integer 
	 * @Date :   2016-11-22 18:25:51    
	 */
	public Integer getCardType() {
		return cardType;
	}
	
	/**
	 * <p>证件类型</p>
	 * @author:  zhangcb
	 * @param:   @param cardType
	 * @return:  void 
	 * @Date :   2016-11-22 18:25:51   
	 */
	public void setCardType(Integer cardType) {
		this.cardType = cardType;
	}
	
	
	/**
	 * <p>身份证号</p>
	 * @author:  zhangcb
	 * @return:  String 
	 * @Date :   2016-11-22 18:25:51    
	 */
	public String getCardId() {
		return cardId;
	}
	
	/**
	 * <p>身份证号</p>
	 * @author:  zhangcb
	 * @param:   @param cardId
	 * @return:  void 
	 * @Date :   2016-11-22 18:25:51   
	 */
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	
	
	/**
	 * <p>客户来源   0线上注册   1业务员注册</p>
	 * @author:  zhangcb
	 * @return:  Integer 
	 * @Date :   2016-11-22 18:25:51    
	 */
	public Integer getCustomerSource() {
		return customerSource;
	}
	
	/**
	 * <p>客户来源   0线上注册   1业务员注册</p>
	 * @author:  zhangcb
	 * @param:   @param customerSource
	 * @return:  void 
	 * @Date :   2016-11-22 18:25:51   
	 */
	public void setCustomerSource(Integer customerSource) {
		this.customerSource = customerSource;
	}
	
	
	/**
	 * <p>realTime</p>
	 * @author:  zhangcb
	 * @return:  Date 
	 * @Date :   2016-11-22 18:25:51    
	 */
	public Date getRealTime() {
		return realTime;
	}
	
	/**
	 * <p>realTime</p>
	 * @author:  zhangcb
	 * @param:   @param realTime
	 * @return:  void 
	 * @Date :   2016-11-22 18:25:51   
	 */
	public void setRealTime(Date realTime) {
		this.realTime = realTime;
	}
	
	
	/**
	 * <p>email回调的时候的验证码</p>
	 * @author:  zhangcb
	 * @return:  String 
	 * @Date :   2016-11-22 18:25:51    
	 */
	public String getEmailCode() {
		return emailCode;
	}
	
	/**
	 * <p>email回调的时候的验证码</p>
	 * @author:  zhangcb
	 * @param:   @param emailCode
	 * @return:  void 
	 * @Date :   2016-11-22 18:25:51   
	 */
	public void setEmailCode(String emailCode) {
		this.emailCode = emailCode;
	}
	
	
	/**
	 * <p>cardIdUsd</p>
	 * @author:  zhangcb
	 * @return:  String 
	 * @Date :   2016-11-22 18:25:51    
	 */
	public String getCardIdUsd() {
		return cardIdUsd;
	}
	
	/**
	 * <p>cardIdUsd</p>
	 * @author:  zhangcb
	 * @param:   @param cardIdUsd
	 * @return:  void 
	 * @Date :   2016-11-22 18:25:51   
	 */
	public void setCardIdUsd(String cardIdUsd) {
		this.cardIdUsd = cardIdUsd;
	}
	
	
	/**
	 * <p>身份证有效期</p>
	 * @author:  zhangcb
	 * @return:  String 
	 * @Date :   2016-11-22 18:25:51    
	 */
	public String getCardIdValidPeriod() {
		return cardIdValidPeriod;
	}
	
	/**
	 * <p>身份证有效期</p>
	 * @author:  zhangcb
	 * @param:   @param cardIdValidPeriod
	 * @return:  void 
	 * @Date :   2016-11-22 18:25:51   
	 */
	public void setCardIdValidPeriod(String cardIdValidPeriod) {
		this.cardIdValidPeriod = cardIdValidPeriod;
	}
	
	
	/**
	 * <p>通讯地址</p>
	 * @author:  zhangcb
	 * @return:  String 
	 * @Date :   2016-11-22 18:25:51    
	 */
	public String getPostalAddress() {
		return postalAddress;
	}
	
	/**
	 * <p>通讯地址</p>
	 * @author:  zhangcb
	 * @param:   @param postalAddress
	 * @return:  void 
	 * @Date :   2016-11-22 18:25:51   
	 */
	public void setPostalAddress(String postalAddress) {
		this.postalAddress = postalAddress;
	}
	
	
	/**
	 * <p>紧急联系人</p>
	 * @author:  zhangcb
	 * @return:  String 
	 * @Date :   2016-11-22 18:25:51    
	 */
	public String getContacts() {
		return contacts;
	}
	
	/**
	 * <p>紧急联系人</p>
	 * @author:  zhangcb
	 * @param:   @param contacts
	 * @return:  void 
	 * @Date :   2016-11-22 18:25:51   
	 */
	public void setContacts(String contacts) {
		this.contacts = contacts;
	}
	
	
	/**
	 * <p>邮编</p>
	 * @author:  zhangcb
	 * @return:  Integer 
	 * @Date :   2016-11-22 18:25:51    
	 */
	public Integer getPostCode() {
		return postCode;
	}
	
	/**
	 * <p>邮编</p>
	 * @author:  zhangcb
	 * @param:   @param postCode
	 * @return:  void 
	 * @Date :   2016-11-22 18:25:51   
	 */
	public void setPostCode(Integer postCode) {
		this.postCode = postCode;
	}
	
	
	/**
	 * <p>基础信息完善状态</p>
	 * @author:  zhangcb
	 * @return:  Integer 
	 * @Date :   2016-11-22 18:25:51    
	 */
	public Integer getStage() {
		return stage;
	}
	
	/**
	 * <p>基础信息完善状态</p>
	 * @author:  zhangcb
	 * @param:   @param stage
	 * @return:  void 
	 * @Date :   2016-11-22 18:25:51   
	 */
	public void setStage(Integer stage) {
		this.stage = stage;
	}
	
	
	/**
	 * <p>紧急联系人电话</p>
	 * @author:  zhangcb
	 * @return:  String 
	 * @Date :   2016-11-22 18:25:51    
	 */
	public String getContactsTel() {
		return contactsTel;
	}
	
	/**
	 * <p>紧急联系人电话</p>
	 * @author:  zhangcb
	 * @param:   @param contactsTel
	 * @return:  void 
	 * @Date :   2016-11-22 18:25:51   
	 */
	public void setContactsTel(String contactsTel) {
		this.contactsTel = contactsTel;
	}
	
	
	/**
	 * <p>手持身份证照片</p>
	 * @author:  zhangcb
	 * @return:  String 
	 * @Date :   2016-11-22 18:25:51    
	 */
	public String getHandIdCardUrl() {
		return handIdCardUrl;
	}
	
	/**
	 * <p>手持身份证照片</p>
	 * @author:  zhangcb
	 * @param:   @param handIdCardUrl
	 * @return:  void 
	 * @Date :   2016-11-22 18:25:51   
	 */
	public void setHandIdCardUrl(String handIdCardUrl) {
		this.handIdCardUrl = handIdCardUrl;
	}
	
	
	/**
	 * <p>身份证正面照片</p>
	 * @author:  zhangcb
	 * @return:  String 
	 * @Date :   2016-11-22 18:25:51    
	 */
	public String getIdCardFrontUrl() {
		return idCardFrontUrl;
	}
	
	/**
	 * <p>身份证正面照片</p>
	 * @author:  zhangcb
	 * @param:   @param idCardFrontUrl
	 * @return:  void 
	 * @Date :   2016-11-22 18:25:51   
	 */
	public void setIdCardFrontUrl(String idCardFrontUrl) {
		this.idCardFrontUrl = idCardFrontUrl;
	}
	
	
	/**
	 * <p>身份证背面照片</p>
	 * @author:  zhangcb
	 * @return:  String 
	 * @Date :   2016-11-22 18:25:51    
	 */
	public String getIdCardBackUrl() {
		return idCardBackUrl;
	}
	
	/**
	 * <p>身份证背面照片</p>
	 * @author:  zhangcb
	 * @param:   @param idCardBackUrl
	 * @return:  void 
	 * @Date :   2016-11-22 18:25:51   
	 */
	public void setIdCardBackUrl(String idCardBackUrl) {
		this.idCardBackUrl = idCardBackUrl;
	}
	
	
	/**
	 * <p>手持协议照片</p>
	 * @author:  zhangcb
	 * @return:  String 
	 * @Date :   2016-11-22 18:25:51    
	 */
	public String getHandDealUrl() {
		return handDealUrl;
	}
	
	/**
	 * <p>手持协议照片</p>
	 * @author:  zhangcb
	 * @param:   @param handDealUrl
	 * @return:  void 
	 * @Date :   2016-11-22 18:25:51   
	 */
	public void setHandDealUrl(String handDealUrl) {
		this.handDealUrl = handDealUrl;
	}

	public BigDecimal getWithdrawCheckMoney() {
		return withdrawCheckMoney;
	}

	public void setWithdrawCheckMoney(BigDecimal withdrawCheckMoney) {
		this.withdrawCheckMoney = withdrawCheckMoney;
	}
}
