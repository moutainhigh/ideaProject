/**
 * Copyright:   
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-13 13:59:41 
 */
package hry.admin.exchange.model;


import hry.admin.customer.model.AppPersonInfo;
import hry.bean.BaseModel;

import java.math.BigDecimal;

import javax.persistence.*;

/**
 * <p> ExDmTransaction </p>
 * @author:         liushilei
 * @Date :          2018-06-13 13:59:41  
 */
@Table(name="ex_dm_transaction")
public class ExDmTransaction extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "transactionNum")
	private String transactionNum;  //
	
	@Column(name= "customerId")
	private Long customerId;  //
	
	@Column(name= "customerName")
	private String customerName;  //
	
	@Column(name= "accountId")
	private Long accountId;  //
	
	@Column(name= "transactionType")
	private Integer transactionType;  //
	
	@Column(name= "transactionMoney")
	private BigDecimal transactionMoney;  //
	
	@Column(name= "status")
	private Integer status;  //
	
	@Column(name= "userId")
	private Long userId;  //
	
	@Column(name= "currencyType")
	private String currencyType;  //
	
	@Column(name= "saasId")
	private String saasId;  //
	
	@Column(name= "coinCode")
	private String coinCode;  //
	
	@Column(name= "website")
	private String website;  //
	
	@Column(name= "fee")
	private BigDecimal fee;  //
	
	@Column(name= "inAddress")
	private String inAddress;  //
	
	@Column(name= "outAddress")
	private String outAddress;  //
	
	@Column(name= "time")
	private String time;  //
	
	@Column(name= "confirmations")
	private String confirmations;  //
	
	@Column(name= "timereceived")
	private String timereceived;  //
	
	@Column(name= "blocktime")
	private String blocktime;  //
	
	@Column(name= "rejectionReason")
	private String rejectionReason;  //
	
	@Column(name= "ourAccountNumber")
	private String ourAccountNumber;  //
	
	@Column(name= "orderNo")
	private String orderNo;  //
	
	@Column(name= "trueName")
	private String trueName;  //
	
	@Column(name= "remark")
	private String remark;  //
	
	@Column(name= "remark2")
	private String remark2;  //
	
	@Column(name= "btcDate")
	private String btcDate;  //
	
	@Column(name= "btcCount")
	private BigDecimal btcCount;  //
	
	@Column(name= "surname")
	private String surname;  //

	// 操作类型  '默认为0，1-充币，2-提笔，3-内部互转，4-手动充币，5-手动提币，6-注册送币，7-实名送币，8-邀请送币，9-c2c,10-锁仓冻结',
	@Column(name = "optType")
	private Integer optType;
	
	@Column(name= "isOpenCheck")
	private Integer isOpenCheck;  //
	
	@Column(name= "memo")
	private String memo;  //

	@Transient // 不与数据库映射的字段
	private AppPersonInfo appPersonInfo;

	public AppPersonInfo getAppPersonInfo() {
		return appPersonInfo;
	}

	public void setAppPersonInfo(AppPersonInfo appPersonInfo) {
		this.appPersonInfo = appPersonInfo;
	}

	public String getRemark2() {
		return remark2;
	}

	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}

	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Long 
	 * @Date :   2018-06-13 13:59:41    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-06-13 13:59:41   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 13:59:41    
	 */
	public String getTransactionNum() {
		return transactionNum;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param transactionNum
	 * @return:  void 
	 * @Date :   2018-06-13 13:59:41   
	 */
	public void setTransactionNum(String transactionNum) {
		this.transactionNum = transactionNum;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Long 
	 * @Date :   2018-06-13 13:59:41    
	 */
	public Long getCustomerId() {
		return customerId;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param customerId
	 * @return:  void 
	 * @Date :   2018-06-13 13:59:41   
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 13:59:41    
	 */
	public String getCustomerName() {
		return customerName;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param customerName
	 * @return:  void 
	 * @Date :   2018-06-13 13:59:41   
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Long 
	 * @Date :   2018-06-13 13:59:41    
	 */
	public Long getAccountId() {
		return accountId;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param accountId
	 * @return:  void 
	 * @Date :   2018-06-13 13:59:41   
	 */
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Integer 
	 * @Date :   2018-06-13 13:59:41    
	 */
	public Integer getTransactionType() {
		return transactionType;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param transactionType
	 * @return:  void 
	 * @Date :   2018-06-13 13:59:41   
	 */
	public void setTransactionType(Integer transactionType) {
		this.transactionType = transactionType;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  BigDecimal 
	 * @Date :   2018-06-13 13:59:41    
	 */
	public BigDecimal getTransactionMoney() {
		return transactionMoney;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param transactionMoney
	 * @return:  void 
	 * @Date :   2018-06-13 13:59:41   
	 */
	public void setTransactionMoney(BigDecimal transactionMoney) {
		this.transactionMoney = transactionMoney;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Integer 
	 * @Date :   2018-06-13 13:59:41    
	 */
	public Integer getStatus() {
		return status;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param status
	 * @return:  void 
	 * @Date :   2018-06-13 13:59:41   
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Long 
	 * @Date :   2018-06-13 13:59:41    
	 */
	public Long getUserId() {
		return userId;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param userId
	 * @return:  void 
	 * @Date :   2018-06-13 13:59:41   
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 13:59:41    
	 */
	public String getCurrencyType() {
		return currencyType;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param currencyType
	 * @return:  void 
	 * @Date :   2018-06-13 13:59:41   
	 */
	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 13:59:41    
	 */
	public String getSaasId() {
		return saasId;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param saasId
	 * @return:  void 
	 * @Date :   2018-06-13 13:59:41   
	 */
	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 13:59:41    
	 */
	public String getCoinCode() {
		return coinCode;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param coinCode
	 * @return:  void 
	 * @Date :   2018-06-13 13:59:41   
	 */
	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 13:59:41    
	 */
	public String getWebsite() {
		return website;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param website
	 * @return:  void 
	 * @Date :   2018-06-13 13:59:41   
	 */
	public void setWebsite(String website) {
		this.website = website;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  BigDecimal 
	 * @Date :   2018-06-13 13:59:41    
	 */
	public BigDecimal getFee() {
		return fee;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param fee
	 * @return:  void 
	 * @Date :   2018-06-13 13:59:41   
	 */
	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 13:59:41    
	 */
	public String getInAddress() {
		return inAddress;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param inAddress
	 * @return:  void 
	 * @Date :   2018-06-13 13:59:41   
	 */
	public void setInAddress(String inAddress) {
		this.inAddress = inAddress;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 13:59:41    
	 */
	public String getOutAddress() {
		return outAddress;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param outAddress
	 * @return:  void 
	 * @Date :   2018-06-13 13:59:41   
	 */
	public void setOutAddress(String outAddress) {
		this.outAddress = outAddress;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 13:59:41    
	 */
	public String getTime() {
		return time;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param time
	 * @return:  void 
	 * @Date :   2018-06-13 13:59:41   
	 */
	public void setTime(String time) {
		this.time = time;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 13:59:41    
	 */
	public String getConfirmations() {
		return confirmations;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param confirmations
	 * @return:  void 
	 * @Date :   2018-06-13 13:59:41   
	 */
	public void setConfirmations(String confirmations) {
		this.confirmations = confirmations;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 13:59:41    
	 */
	public String getTimereceived() {
		return timereceived;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param timereceived
	 * @return:  void 
	 * @Date :   2018-06-13 13:59:41   
	 */
	public void setTimereceived(String timereceived) {
		this.timereceived = timereceived;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 13:59:41    
	 */
	public String getBlocktime() {
		return blocktime;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param blocktime
	 * @return:  void 
	 * @Date :   2018-06-13 13:59:41   
	 */
	public void setBlocktime(String blocktime) {
		this.blocktime = blocktime;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 13:59:41    
	 */
	public String getRejectionReason() {
		return rejectionReason;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param rejectionReason
	 * @return:  void 
	 * @Date :   2018-06-13 13:59:41   
	 */
	public void setRejectionReason(String rejectionReason) {
		this.rejectionReason = rejectionReason;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 13:59:41    
	 */
	public String getOurAccountNumber() {
		return ourAccountNumber;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param ourAccountNumber
	 * @return:  void 
	 * @Date :   2018-06-13 13:59:41   
	 */
	public void setOurAccountNumber(String ourAccountNumber) {
		this.ourAccountNumber = ourAccountNumber;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 13:59:41    
	 */
	public String getOrderNo() {
		return orderNo;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param orderNo
	 * @return:  void 
	 * @Date :   2018-06-13 13:59:41   
	 */
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 13:59:41    
	 */
	public String getTrueName() {
		return trueName;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param trueName
	 * @return:  void 
	 * @Date :   2018-06-13 13:59:41   
	 */
	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 13:59:41    
	 */
	public String getRemark() {
		return remark;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param remark
	 * @return:  void 
	 * @Date :   2018-06-13 13:59:41   
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 13:59:41    
	 */
	public String getBtcDate() {
		return btcDate;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param btcDate
	 * @return:  void 
	 * @Date :   2018-06-13 13:59:41   
	 */
	public void setBtcDate(String btcDate) {
		this.btcDate = btcDate;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  BigDecimal 
	 * @Date :   2018-06-13 13:59:41    
	 */
	public BigDecimal getBtcCount() {
		return btcCount;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param btcCount
	 * @return:  void 
	 * @Date :   2018-06-13 13:59:41   
	 */
	public void setBtcCount(BigDecimal btcCount) {
		this.btcCount = btcCount;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 13:59:41    
	 */
	public String getSurname() {
		return surname;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param surname
	 * @return:  void 
	 * @Date :   2018-06-13 13:59:41   
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Integer 
	 * @Date :   2018-06-13 13:59:41    
	 */
	public Integer getOptType() {
		return optType;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param optType
	 * @return:  void 
	 * @Date :   2018-06-13 13:59:41   
	 */
	public void setOptType(Integer optType) {
		this.optType = optType;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Integer 
	 * @Date :   2018-06-13 13:59:41    
	 */
	public Integer getIsOpenCheck() {
		return isOpenCheck;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param isOpenCheck
	 * @return:  void 
	 * @Date :   2018-06-13 13:59:41   
	 */
	public void setIsOpenCheck(Integer isOpenCheck) {
		this.isOpenCheck = isOpenCheck;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 13:59:41    
	 */
	public String getMemo() {
		return memo;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param memo
	 * @return:  void 
	 * @Date :   2018-06-13 13:59:41   
	 */
	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	

}
