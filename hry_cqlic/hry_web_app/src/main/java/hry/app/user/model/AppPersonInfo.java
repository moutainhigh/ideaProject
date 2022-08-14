/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      zhangcb
 * @version:     V1.0
 * @Date:        2016-11-22 18:25:51
 */
package hry.app.user.model;


import hry.bean.BaseModel;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p> AppPersonInfo </p>
 * @author:         menwei
 */
public class AppPersonInfo extends BaseModel {


	private Long id;  //id

	private Long customerId;  //用户ID

	private Integer customerType;  //客户类型customerType甲类账户1(普通的，默认)，乙类账号2，丙类账户3

	private String mobilePhone;  //手机号

	private String email;  //邮箱

	private String trueName;  //真实姓名

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


	private String personCard;


	private String frontpersonCard;


	private String personBank;


	// 平台币支付手续费：0否 1是
	@Column(name = "isOpenCoinFee")
	private int isOpenCoinFee;

	public int getIsOpenCoinFee () {
		return isOpenCoinFee;
	}

	public void setIsOpenCoinFee (int isOpenCoinFee) {
		this.isOpenCoinFee = isOpenCoinFee;
	}




	public String getPersonCard() {
		return personCard;
	}

	public void setPersonCard(String personCard) {
		this.personCard = personCard;
	}

	public String getFrontpersonCard() {
		return frontpersonCard;
	}

	public void setFrontpersonCard(String frontpersonCard) {
		this.frontpersonCard = frontpersonCard;
	}

	public String getPersonBank() {
		return personBank;
	}

	public void setPersonBank(String personBank) {
		this.personBank = personBank;
	}

	//金科项目新加字段--------------------开始-------------------------------------
	private Integer jkAgentType;  //金科的代理商类型    0 会员   1 区代（县代）  2 市代   3省代

	private String jkAgentName;  //金科的代理商类型名称（例：山东省省代，洛阳市市代）

	private String jkAgentProvince;  //金科所属省

	private String jkAgentProvinceCode;  //金科所属省编码

	private String jkAgentCity;  //金科所属市

	private String jkAgentCityCode;  //金科所属市编码

	private String jkAgentCounty;  //金科所属区或县

	private String jkAgentCountyCode;  //金科所属区或县编码



	private Integer jkApplyState;  //金科的申请代理商状态   -1 无申请  0申请中  1审核成功  2 审核拒绝

	private Integer jkApplyType;  //金科的申请成为代理商类型   0 会员 1 区代（县代）  2 市代   3省代

	private String jkApplyAuthorizationCode;  //金科的申请成为代理商授权码（由后台手动生成）

	private Integer jkApplyAuthorizationCodeState;  //金科的申请成为代理商授权码状态（0未使用，1已使用）

	private String jkApplyAgentProvince;  //金科申请所属省

	private String jkApplyAgentProvinceCode;  //金科申请所属省编码

	private String jkApplyAgentCity;  //金科申请所属市

	private String jkApplyAgentCityCode;  //金科申请所属市编码

	private String jkApplyAgentCounty;  //金科申请所属区或县

	private String jkApplyAgentCountyCode;  //金科申请所属区或县编码


	private String isGivePerAgentRecommendCoin;  //是否已经给推荐人返还推荐奖励   0  没给  1 已经给了   默认0

	private Integer hasRecommendNum;  //自己推荐了多少个有效会员  默认0

	//金科项目新加字段--------------------结束-------------------------------------


	/**金科项目用户资金报表不与数据库映射     开始*/
	private BigDecimal totalAvailableMoney;  // 总可用金额
	private BigDecimal totalFrozenMoney;  // 总冻结金额
	private BigDecimal totalRechargeMoney;  //总充值金额
	private BigDecimal totalWithdrawalsMoney;  // 总提现金额
	private BigDecimal moneyChangeRate; // 资金变动率
	private BigDecimal profitRate; // 收益率
	/**金科项目用户资金报表不与数据库映射       结束*/



















	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getTotalAvailableMoney() {
		return totalAvailableMoney;
	}

	/**
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setTotalAvailableMoney(BigDecimal totalAvailableMoney) {
		this.totalAvailableMoney = totalAvailableMoney;
	}

	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getTotalFrozenMoney() {
		return totalFrozenMoney;
	}

	/**
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setTotalFrozenMoney(BigDecimal totalFrozenMoney) {
		this.totalFrozenMoney = totalFrozenMoney;
	}

	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getTotalRechargeMoney() {
		return totalRechargeMoney;
	}

	/**
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setTotalRechargeMoney(BigDecimal totalRechargeMoney) {
		this.totalRechargeMoney = totalRechargeMoney;
	}

	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getTotalWithdrawalsMoney() {
		return totalWithdrawalsMoney;
	}

	/**
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setTotalWithdrawalsMoney(BigDecimal totalWithdrawalsMoney) {
		this.totalWithdrawalsMoney = totalWithdrawalsMoney;
	}

	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getMoneyChangeRate() {
		return moneyChangeRate;
	}

	/**
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setMoneyChangeRate(BigDecimal moneyChangeRate) {
		this.moneyChangeRate = moneyChangeRate;
	}

	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getProfitRate() {
		return profitRate;
	}

	/**
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setProfitRate(BigDecimal profitRate) {
		this.profitRate = profitRate;
	}

	private String bankName;//银行名称

	private String bankCardNumber;//银行卡号



	public Integer getJkApplyAuthorizationCodeState() {
		return jkApplyAuthorizationCodeState;
	}

	public void setJkApplyAuthorizationCodeState(
			Integer jkApplyAuthorizationCodeState) {
		this.jkApplyAuthorizationCodeState = jkApplyAuthorizationCodeState;
	}

	public String getJkAgentProvinceCode() {
		return jkAgentProvinceCode;
	}

	public void setJkAgentProvinceCode(String jkAgentProvinceCode) {
		this.jkAgentProvinceCode = jkAgentProvinceCode;
	}

	public String getJkAgentCityCode() {
		return jkAgentCityCode;
	}

	public void setJkAgentCityCode(String jkAgentCityCode) {
		this.jkAgentCityCode = jkAgentCityCode;
	}

	public String getJkAgentCountyCode() {
		return jkAgentCountyCode;
	}

	public void setJkAgentCountyCode(String jkAgentCountyCode) {
		this.jkAgentCountyCode = jkAgentCountyCode;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankCardNumber() {
		return bankCardNumber;
	}

	public void setBankCardNumber(String bankCardNumber) {
		this.bankCardNumber = bankCardNumber;
	}

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



	public Integer getJkAgentType() {
		return jkAgentType;
	}

	public void setJkAgentType(Integer jkAgentType) {
		this.jkAgentType = jkAgentType;
	}

	public String getJkAgentProvince() {
		return jkAgentProvince;
	}

	public void setJkAgentProvince(String jkAgentProvince) {
		this.jkAgentProvince = jkAgentProvince;
	}

	public String getJkAgentCity() {
		return jkAgentCity;
	}

	public void setJkAgentCity(String jkAgentCity) {
		this.jkAgentCity = jkAgentCity;
	}

	public String getJkAgentCounty() {
		return jkAgentCounty;
	}

	public void setJkAgentCounty(String jkAgentCounty) {
		this.jkAgentCounty = jkAgentCounty;
	}

	public String getIsGivePerAgentRecommendCoin() {
		return isGivePerAgentRecommendCoin;
	}

	public void setIsGivePerAgentRecommendCoin(String isGivePerAgentRecommendCoin) {
		this.isGivePerAgentRecommendCoin = isGivePerAgentRecommendCoin;
	}

	public Integer getHasRecommendNum() {
		return hasRecommendNum;
	}

	public void setHasRecommendNum(Integer hasRecommendNum) {
		this.hasRecommendNum = hasRecommendNum;
	}



	public String getJkAgentName() {
		return jkAgentName;
	}

	public void setJkAgentName(String jkAgentName) {
		this.jkAgentName = jkAgentName;
	}

	public Integer getJkApplyState() {
		return jkApplyState;
	}

	public void setJkApplyState(Integer jkApplyState) {
		this.jkApplyState = jkApplyState;
	}

	public Integer getJkApplyType() {
		return jkApplyType;
	}

	public void setJkApplyType(Integer jkApplyType) {
		this.jkApplyType = jkApplyType;
	}

	public String getJkApplyAuthorizationCode() {
		return jkApplyAuthorizationCode;
	}

	public void setJkApplyAuthorizationCode(String jkApplyAuthorizationCode) {
		this.jkApplyAuthorizationCode = jkApplyAuthorizationCode;
	}

	public String getJkApplyAgentProvince() {
		return jkApplyAgentProvince;
	}

	public void setJkApplyAgentProvince(String jkApplyAgentProvince) {
		this.jkApplyAgentProvince = jkApplyAgentProvince;
	}

	public String getJkApplyAgentProvinceCode() {
		return jkApplyAgentProvinceCode;
	}

	public void setJkApplyAgentProvinceCode(String jkApplyAgentProvinceCode) {
		this.jkApplyAgentProvinceCode = jkApplyAgentProvinceCode;
	}

	public String getJkApplyAgentCity() {
		return jkApplyAgentCity;
	}

	public void setJkApplyAgentCity(String jkApplyAgentCity) {
		this.jkApplyAgentCity = jkApplyAgentCity;
	}

	public String getJkApplyAgentCityCode() {
		return jkApplyAgentCityCode;
	}

	public void setJkApplyAgentCityCode(String jkApplyAgentCityCode) {
		this.jkApplyAgentCityCode = jkApplyAgentCityCode;
	}

	public String getJkApplyAgentCounty() {
		return jkApplyAgentCounty;
	}

	public void setJkApplyAgentCounty(String jkApplyAgentCounty) {
		this.jkApplyAgentCounty = jkApplyAgentCounty;
	}

	public String getJkApplyAgentCountyCode() {
		return jkApplyAgentCountyCode;
	}

	public void setJkApplyAgentCountyCode(String jkApplyAgentCountyCode) {
		this.jkApplyAgentCountyCode = jkApplyAgentCountyCode;
	}

	public static void main(String[] args) {
		List<String> list=new ArrayList<String>();
		list.add(0,"1");
		list.add(0,"2");
		list.add(0,"3");
		list.add(0,"4");
		list.add(0,"5");
		list.add(0,"6");
	}
}
