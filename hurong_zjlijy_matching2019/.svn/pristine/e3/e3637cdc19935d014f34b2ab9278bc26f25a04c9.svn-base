/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      zhangcb
 * @version:     V1.0 
 * @Date:        2016-11-22 18:25:51 
 */
package hry.otc.manage.remote.model.customer.person;

import hry.otc.manage.remote.model.core.BaseModel;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Table(name="app_person_info")
public class AppPersonInfo extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //id
	
	@Column(name= "customerId")
	private Long customerId;  //用户ID
	
	@Column(name= "customerType")
	private Integer customerType;  //客户类型customerType甲类账户1(普通的，默认)，乙类账号2，丙类账户3
	
	@Column(name= "mobilePhone")
	private String mobilePhone;  //手机号
	
	@Column(name= "email")
	private String email;  //邮箱
	
	@Column(name= "trueName")
	private String trueName;  //真实名
	@Column(name= "surname")
	private String surname;  //真实姓
	@Column(name= "sex")
	private Integer sex;  //性别  0男  1女
	
	@Column(name= "birthday")
	private String birthday;  //生日
	
	@Column(name= "country")
	private String country;  //国家
	
	@Column(name= "cardType")
	private Integer cardType;  //证件类型
	
	@Column(name= "cardId")
	private String cardId;  //身份证号
	
	@Column(name= "customerSource")
	private Integer customerSource;  //客户来源   0线上注册   1业务员注册
	
	@Column(name= "realTime")
	private Date realTime;  //realTime
	
	@Column(name= "emailCode")
	private String emailCode;  //email回调的时候的验证码
	
	@Column(name= "cardIdUsd")
	private String cardIdUsd;  //cardIdUsd
	
	@Column(name= "cardIdValidPeriod")
	private String cardIdValidPeriod;  //身份证有效期
	
	@Column(name= "postalAddress")
	private String postalAddress;  //通讯地址
	
	@Column(name= "contacts")
	private String contacts;  //紧急联系人
	
	@Column(name= "postCode")
	private Integer postCode;  //邮编
	
	@Column(name= "stage")
	private Integer stage;  //基础信息完善状态
	
	@Column(name= "contactsTel")
	private String contactsTel;  //紧急联系人电话
	
	@Column(name= "handIdCardUrl")
	private String handIdCardUrl;  //手持身份证照片
	
	@Column(name= "idCardFrontUrl")
	private String idCardFrontUrl;  //身份证正面照片
	
	@Column(name= "idCardBackUrl")
	private String idCardBackUrl;  //身份证背面照片
	
	@Column(name= "handDealUrl")
	private String handDealUrl;  //手持协议照片
	
	@Column(name= "isExamine")
	private Integer isExamine;  //审核状态 0=未审核 1=审核通过、2=审核不通过

	@Column(name= "eamineRefusalReason")
	private String eamineRefusalReason;  //审核拒绝原因
	
	@Column(name= "isCancle")
	private Integer isCancle;  //是否注销 0、未注销  1、注销
	
	@Column(name= "cancleReason")
	private String cancleReason;  //注销原因



	
	/**金科项目用户资金报表不与数据库映射     开始*/
	@Transient
	private BigDecimal totalAvailableMoney;  // 总可用金额
	@Transient
	private BigDecimal totalFrozenMoney;  // 总冻结金额
	@Transient
	private BigDecimal totalRechargeMoney;  //总充值金额
	@Transient
	private BigDecimal totalWithdrawalsMoney;  // 总提现金额
	@Transient
	private BigDecimal moneyChangeRate; // 资金变动率
	@Transient
	private BigDecimal profitRate; // 收益率
	/**金科项目用户资金报表不与数据库映射       结束*/
	
	
	
	@Column(name="personCard")
	private String personCard;
	
	
	@Column(name="frontpersonCard")
	private String frontpersonCard;
	
	
	@Column(name="personBank")
	private String personBank;
	


	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
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

	@Transient   //不与数据库映射的字段
	private String bankName;//银行名称
	
	@Transient   //不与数据库映射的字段
	private String bankCardNumber;//银行卡号
	

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
