/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Liu Shilei
 * @version:      V1.0 
 * @Date:        2016年3月31日 下午6:40:58
 */
package hry.account.fund.model;

import static javax.persistence.GenerationType.IDENTITY;
import hry.core.mvc.model.BaseModel;
import hry.core.mvc.model.ModelUtil;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * <p> TODO</p>
 * @author:         Liu Shilei 
 * @Date :          2016年3月31日 下午6:40:58 
 */
/**
 * <p> TODO</p>
 * @author:         Liu Shilei 
 * @Date :          2016年8月18日 下午6:59:35 
 */
@SuppressWarnings("serial")
@Table(name="app_transaction")
public class AppTransaction extends BaseModel {
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	
	@Column(name="website")
	private String website;//站点类别默认cn
	
	@Column(name="transactionNum")
	private String transactionNum;  //交易单号
	
	@Column(name="userName")
	private String userName ;//用户登录名
	
	@Column(name="userId")
	private Long userId ;// 审核人Id  后台用户
	
	@Column(name="customerId")
	private Long customerId; //用户Id
	
	@Column(name="accountId")
	private Long accountId;//账户ID
	
	@Column(name="cardHolder") // 持卡人姓名
	private String cardHolder;
	
	@Column(name="transactionType")
	private Integer transactionType;   //交易类型(1线上充值,2线上提现 3线下充值 4线下取现 5支付宝充值)
	
	@Column(name="transactionMoney")
	private BigDecimal transactionMoney; //交易金额
	
	@Column(name="fee")
	private BigDecimal fee; //手续费
	
	@Column(name="status")
	private Integer status; //1待审核 2已完成 3以否决  4表示等待成交
	
	// 我方银行卡号     
	@Column(name="ourAccountNumber")
	private String ourAccountNumber ;
	
	@Column(name="custromerAccountNumber")
	private String custromerAccountNumber ;
	
	// 交易的币类型
	@Column(name="currencyType")
	private String currencyType ;
	
	@Column(name="trueName")
	private String trueName;
	
	@Column(name="surname")
	private String surname;
	// 银行简码
	@Column(name="bankNum")
	private String bankNum ;
	
	@Transient
	private String bankName;
	
	@Transient
	private String bankProvince; //开户省份
	
	@Transient
	private String bankAddress;//开户行所在地  开户市
	
	@Transient
	private String subBank;  //开户支行
	
	@Transient
	private String subBankNum;  //开户支行的银行机构代码


	// 后台处理后的修改的状态 (表示后台申请了之后  需要第三方回调的方法。)
	@Column(name="readyStates")
	private Integer readyStates;
	
	@Transient
	private AppOurAccount appOurAccount;
	
	@Column(name="thirdPayName")
	private String thirdPayName; //第三方支付的名字
	
	// 备注
	@Column(name="remark")
	private String remark ;
	
	

	
	//驳回理由
	@Column(name="rejectionReason")
	private String rejectionReason ;
	
	@Transient
	private String email;

	@Transient
	private String phone;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getRejectionReason() {
		return rejectionReason;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setRejectionReason(String rejectionReason) {
		this.rejectionReason = rejectionReason;
	}

	

	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getSubBankNum() {
		return subBankNum;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setSubBankNum(String subBankNum) {
		this.subBankNum = subBankNum;
	}

	public Integer getReadyStates() {
		return readyStates;
	}

	public void setReadyStates(Integer readyStates) {
		this.readyStates = readyStates;
	}


	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}
	

	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getBankProvince() {
		return bankProvince;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setBankProvince(String bankProvince) {
		this.bankProvince = bankProvince;
	}

	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getBankAddress() {
		return bankAddress;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setBankAddress(String bankAddress) {
		this.bankAddress = bankAddress;
	}

	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getSubBank() {
		return subBank;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setSubBank(String subBank) {
		this.subBank = subBank;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	// 充值 类型(0表示 银行卡  --- 1 表示 支付宝 )
	@Column(name="style")
	private Integer style; 

	public String getCardHolder() {
		return cardHolder;
	}

	public void setCardHolder(String cardHolder) {
		this.cardHolder = cardHolder;
	}

	public String getOurAccountNumber() {
		return ourAccountNumber;
	}

	public void setOurAccountNumber(String ourAccountNumber) {
		this.ourAccountNumber = ourAccountNumber;
	}

	public String getCustromerAccountNumber() {
		return custromerAccountNumber;
	}

	public void setCustromerAccountNumber(String custromerAccountNumber) {
		this.custromerAccountNumber = custromerAccountNumber;
	}

	public Integer getStyle() {
		return style;
	}

	public void setStyle(Integer style) {
		this.style = style;
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
	public String getTransactionNum() {
		return transactionNum;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setTransactionNum(String transactionNum) {
		this.transactionNum = transactionNum;
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
	 * @return:     Long
	 */
	public Long getCustomerId() {
		return customerId;
	}

	/** 
	 * <p> TODO</p>
	 * @return: Long
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	/**
	 * <p> TODO</p>
	 * @return:     Long
	 */
	public Long getAccountId() {
		return accountId;
	}

	/** 
	 * <p> TODO</p>
	 * @return: Long
	 */
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	/**
	 * <p> TODO</p>
	 * @return:     Integer
	 */
	public Integer getTransactionType() {
		return transactionType;
	}

	/** 
	 * <p> TODO</p>
	 * @return: Integer
	 */
	public void setTransactionType(Integer transactionType) {
		this.transactionType = transactionType;
	}

	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getTransactionMoney() {
		return transactionMoney;
	}

	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setTransactionMoney(BigDecimal transactionMoney) {
		this.transactionMoney = transactionMoney;
	}

	/**
	 * <p> TODO</p>
	 * @return:     Integer
	 */
	public Integer getStatus() {
		return status;
	}

	/** 
	 * <p> TODO</p>
	 * @return: Integer
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * <p> TODO</p>
	 * @return:     Long
	 */
	public Long getUserId() {
		return userId;
	}

	/** 
	 * <p> TODO</p>
	 * @return: Long
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getCurrencyType() {
		return currencyType;
	}
	

	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getFee() {
		return fee;
	}

	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}


	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getBankNum() {
		return bankNum;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setBankNum(String bankNum) {
		this.bankNum = bankNum;
	}

	public AppOurAccount getAppOurAccount() {
		return appOurAccount;
	}

	public void setAppOurAccount(AppOurAccount appOurAccount) {
		this.appOurAccount = appOurAccount;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getTrueName() {
		return trueName;
	}

	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}

	public String getThirdPayName() {
		return thirdPayName;
	}

	public void setThirdPayName(String thirdPayName) {
		this.thirdPayName = thirdPayName;
	}
	
	
	
}
