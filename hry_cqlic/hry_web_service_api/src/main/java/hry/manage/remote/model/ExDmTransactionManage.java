/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Wu Shuiming
 * @version:      V1.0 
 * @Date:        2016年3月28日 下午4:25:12
 */
package hry.manage.remote.model;


import hry.bean.BaseModel;

import java.math.BigDecimal;


@SuppressWarnings("serial")
public class ExDmTransactionManage extends BaseModel {

	// 主键
	private Long id;
	// 交易金额
	private BigDecimal transactionMoney;
	// 交易订单号
	private String transactionNum;
	//手续费
	private BigDecimal fee;
	// 状态 1待审核 --2已完成 -- 3以否决
	private Integer status;
	// 用户id
	private Long customerId;
	private String customerName;
	private String trueName;
	// 数字货币账户id
	private Long accountId;
	// 交易类型(1充币 ，2提币)
	private Integer transactionType;
	// 操作人id
	private Long userId;
	
	private String currencyType;
	// 币的类型
	private String coinCode;
	
	//站点类别 en ,cn
	private String website;
	
	private Long created_long;
	
	private AppPersonInfoManage appPersonInfo;

	public AppPersonInfoManage getAppPersonInfo() {
		return appPersonInfo;
	}

	public void setAppPersonInfo(AppPersonInfoManage appPersonInfo) {
		this.appPersonInfo = appPersonInfo;
	}
	
	
	
	public Long getCreated_long() {
		if(super.getCreated()!=null){
			return super.getCreated().getTime();
		}
		return 0L;
	}

	public void setCreated_long(Long created_long) {
		this.created_long = created_long;
	}

	//驳回理由
	private String rejectionReason ;

	
	public String getTrueName() {
		return trueName;
	}

	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}

	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getOurAccountNumber() {
		return ourAccountNumber;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setOurAccountNumber(String ourAccountNumber) {
		this.ourAccountNumber = ourAccountNumber;
	}

	//转入钱包地址
	private String inAddress;

	
	//转出钱包地址
	private String  outAddress;
	
	//确认节点数
	private String confirmations; 
	
	
	//区块时间
	private String blocktime;
	
	//时间
	private String time;
		
		
	//时间
	private String timereceived;
	
	// 我方币种账号     
	private String ourAccountNumber ;
	
	
	//订单号  
	private String orderNo;
	
	// 备注
	private String remark;
	
	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getOrderNo() {
		return orderNo;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getInAddress() {
		return inAddress;
	}

	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getOutAddress() {
		return outAddress;
	}

	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getConfirmations() {
		return confirmations;
	}

	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getBlocktime() {
		return blocktime;
	}

	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getTime() {
		return time;
	}

	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getTimereceived() {
		return timereceived;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setInAddress(String inAddress) {
		this.inAddress = inAddress;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setOutAddress(String outAddress) {
		this.outAddress = outAddress;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setConfirmations(String confirmations) {
		this.confirmations = confirmations;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setBlocktime(String blocktime) {
		this.blocktime = blocktime;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setTime(String time) {
		this.time = time;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setTimereceived(String timereceived) {
		this.timereceived = timereceived;
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

	public ExDmTransactionManage(Long id, String transactionNum, Long customerId,
			String customerName, Long accountId, Integer transactionType,
			BigDecimal transactionMoney, Integer status, Long userId,
			String currencyType) {
		super();
		this.id = id;
		this.transactionNum = transactionNum;
		this.customerId = customerId;
		this.customerName = customerName;
		this.accountId = accountId;
		this.transactionType = transactionType;
		this.transactionMoney = transactionMoney;
		this.status = status;
		this.userId = userId;
		this.currencyType = currencyType;
	}

	public ExDmTransactionManage() {
		super();
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
	
	
	
}
