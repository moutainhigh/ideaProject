/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Wu Shuiming
 * @version:      V1.0 
 * @Date:        2016年3月28日 下午4:25:12
 */
package hry.exchange.transaction.model;

import static javax.persistence.GenerationType.IDENTITY;
import hry.core.mvc.model.BaseModel;
import hry.customer.person.model.AppPersonInfo;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * <p>
 * TODO
 * </p>
 * 
 * @author: Wu Shuiming
 * @Date : 2016年3月28日 下午4:25:12
 */
@SuppressWarnings("serial")
@Table(name = "ex_dm_transaction")
public class ExDmTransaction extends BaseModel {

	// 主键
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	// 交易订单号
	@Column(name = "transactionNum")
	private String transactionNum;
	// 用户id
	@Column(name = "customerId")
	private Long customerId;
	@Column(name = "customerName")
	private String customerName;

	// 数字货币账户id
	@Column(name = "accountId")
	private Long accountId;
	// 交易类型(1币收入 ，2币支出)
	@Column(name = "transactionType")
	private Integer transactionType;
	// 交易金额
	@Column(name = "transactionMoney")
	private BigDecimal transactionMoney;
	// 状态 1待审核 --2已完成 -- 3以否决
	@Column(name = "status")
	private Integer status;
	// 操作人id
	@Column(name = "userId")
	private Long userId;

	@Column(name = "currencyType")
	private String currencyType;
	// 币的类型
	@Column(name = "coinCode")
	private String coinCode;

	// 站点类别 en ,cn
	@Column(name = "website")
	private String website;

	// 手续费
	@Column(name = "fee")
	private BigDecimal fee;

	// 备注
	@Column(name = "remark")
	private String remark;

	// 备注
	@Column(name = "btcCount")
	private BigDecimal btcCount;

	// 驳回理由
	@Column(name = "rejectionReason")
	private String rejectionReason;

	// 时间
	@Column(name = "btcDate")
	private Date btcDate;

	// 转入钱包地址
	@Column(name = "inAddress")
	private String inAddress;

	// 转出钱包地址
	@Column(name = "outAddress")
	private String outAddress;

	// 确认节点数
	@Column(name = "confirmations")
	private String confirmations;

	// 区块时间
	@Column(name = "blocktime")
	private String blocktime;

	// 时间
	@Column(name = "time")
	private String time;

	// 时间
	@Column(name = "timereceived")
	private String timereceived;

	// 我方币种账号
	@Column(name = "ourAccountNumber")
	private String ourAccountNumber;

	// 订单号
	@Column(name = "orderNo")
	private String orderNo;
	
	// 操作类型  '默认为0，1-充币，2-提笔，3-内部互转，4-手动充币，5-手动提币，6-注册送币，7-实名送币，8-邀请送币，9-c2c,10-锁仓冻结',
	@Column(name = "optType")
	private Integer optType;

	//驳回理由
	@Column(name="memo")
	private String memo ;

	@Transient // 不与数据库映射的字段
	private AppPersonInfo appPersonInfo;
	
	@Transient
	private String truename;
	@Transient
	private String surname;

	// 备注
	@Column(name = "remark2" ,columnDefinition = "varchar(255) default '' comment '地址备注'")
	private String remark2;

	public void setTruename(String truename) {
		this.truename = truename;
	}

	public String getRemark2() {
		return remark2;
	}

	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}

	public String getTruename() {
		return truename;
	}

	public void setTrueName(String truename) {
		this.truename = truename;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	public Date getBtcDate() {
		return btcDate;
	}

	public void setBtcDate(Date btcDate) {
		this.btcDate = btcDate;
	}

	public BigDecimal getBtcCount() {
		return btcCount;
	}

	public void setBtcCount(BigDecimal btcCount) {
		this.btcCount = btcCount;
	}

	/**
	 * <p>
	 * TODO
	 * </p>
	 * 
	 * @return: String
	 */
	public String getOurAccountNumber() {
		return ourAccountNumber;
	}

	/**
	 * <p>
	 * TODO
	 * </p>
	 * 
	 * @return: String
	 */
	public void setOurAccountNumber(String ourAccountNumber) {
		this.ourAccountNumber = ourAccountNumber;
	}

	/**
	 * <p>
	 * TODO
	 * </p>
	 * 
	 * @return: AppPersonInfo
	 */
	public AppPersonInfo getAppPersonInfo() {
		return appPersonInfo;
	}

	/**
	 * <p>
	 * TODO
	 * </p>
	 * 
	 * @return: AppPersonInfo
	 */
	public void setAppPersonInfo(AppPersonInfo appPersonInfo) {
		this.appPersonInfo = appPersonInfo;
	}


	/**
	 * <p>
	 * TODO
	 * </p>
	 * 
	 * @return: String
	 */
	public String getOrderNo() {
		return orderNo;
	}

	/**
	 * <p>
	 * TODO
	 * </p>
	 * 
	 * @return: String
	 */
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	/**
	 * <p>
	 * TODO
	 * </p>
	 * 
	 * @return: String
	 */
	public String getInAddress() {
		return inAddress;
	}

	/**
	 * <p>
	 * TODO
	 * </p>
	 * 
	 * @return: String
	 */
	public String getOutAddress() {
		return outAddress;
	}

	/**
	 * <p>
	 * TODO
	 * </p>
	 * 
	 * @return: String
	 */
	public String getConfirmations() {
		return confirmations;
	}

	/**
	 * <p>
	 * TODO
	 * </p>
	 * 
	 * @return: String
	 */
	public String getBlocktime() {
		return blocktime;
	}

	/**
	 * <p>
	 * TODO
	 * </p>
	 * 
	 * @return: String
	 */
	public String getTime() {
		return time;
	}

	/**
	 * <p>
	 * TODO
	 * </p>
	 * 
	 * @return: String
	 */
	public String getTimereceived() {
		return timereceived;
	}

	/**
	 * <p>
	 * TODO
	 * </p>
	 * 
	 * @return: String
	 */
	public void setInAddress(String inAddress) {
		this.inAddress = inAddress;
	}

	/**
	 * <p>
	 * TODO
	 * </p>
	 * 
	 * @return: String
	 */
	public void setOutAddress(String outAddress) {
		this.outAddress = outAddress;
	}

	/**
	 * <p>
	 * TODO
	 * </p>
	 * 
	 * @return: String
	 */
	public void setConfirmations(String confirmations) {
		this.confirmations = confirmations;
	}

	/**
	 * <p>
	 * TODO
	 * </p>
	 * 
	 * @return: String
	 */
	public void setBlocktime(String blocktime) {
		this.blocktime = blocktime;
	}

	/**
	 * <p>
	 * TODO
	 * </p>
	 * 
	 * @return: String
	 */
	public void setTime(String time) {
		this.time = time;
	}

	/**
	 * <p>
	 * TODO
	 * </p>
	 * 
	 * @return: String
	 */
	public void setTimereceived(String timereceived) {
		this.timereceived = timereceived;
	}

	/**
	 * <p>
	 * TODO
	 * </p>
	 * 
	 * @return: String
	 */
	public String getRejectionReason() {
		return rejectionReason;
	}

	/**
	 * <p>
	 * TODO
	 * </p>
	 * 
	 * @return: String
	 */
	public void setRejectionReason(String rejectionReason) {
		this.rejectionReason = rejectionReason;
	}

	/**
	 * <p>
	 * TODO
	 * </p>
	 * 
	 * @return: BigDecimal
	 */
	public BigDecimal getFee() {
		return fee;
	}

	/**
	 * <p>
	 * TODO
	 * </p>
	 * 
	 * @return: BigDecimal
	 */
	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTransactionNum() {
		return transactionNum;
	}

	public void setTransactionNum(String transactionNum) {
		this.transactionNum = transactionNum;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public Integer getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(Integer transactionType) {
		this.transactionType = transactionType;
	}

	public BigDecimal getTransactionMoney() {
		return transactionMoney;
	}

	public void setTransactionMoney(BigDecimal transactionMoney) {
		this.transactionMoney = transactionMoney;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}


	public String getCoinCode() {
		return coinCode;
	}

	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getOptType() {
		return optType;
	}

	public void setOptType(Integer optType) {
		this.optType = optType;
	}

}
