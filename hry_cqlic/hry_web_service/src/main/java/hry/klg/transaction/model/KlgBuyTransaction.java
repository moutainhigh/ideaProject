/**
 * Copyright:   
 * @author:      yaozhuo
 * @version:     V1.0 
 * @Date:        2019-04-18 15:00:12 
 */
package hry.klg.transaction.model;


import hry.bean.BaseModel;

import java.math.BigDecimal;

import javax.persistence.*;

/**
 * <p> KlgBuyTransaction </p>
 * @author:         yaozhuo
 * @Date :          2019-04-18 15:00:12  
 */
@Table(name="klg_buy_transaction")
public class KlgBuyTransaction extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "customerId")
	private Long customerId;  //用户id
	
	@Column(name= "transactionNum")
	private String transactionNum;  //流水号
	
	@Column(name= "accountId")
	private Long accountId;  //数字货币账户id
	
	@Column(name= "coinCode")
	private String coinCode;  //币种
	
	@Column(name= "smeMoney")
	private BigDecimal smeMoney;  //买入平台币金额
	
	@Column(name= "usdtMoney")
	private BigDecimal usdtMoney;  //需要支付USDT金额
	
	@Column(name= "firstMoney")
	private BigDecimal firstMoney;  //保证金
	
	@Column(name= "lastMoney")
	private BigDecimal lastMoney;  //尾款
	
	@Column(name= "interestMoney")
	private BigDecimal interestMoney;  //利息
	
	@Column(name= "trusteeshipStatus")
	private Integer trusteeshipStatus;  //托管状态：1否 2是
	
	@Column(name= "rushOrders")
	private Integer rushOrders;  //是否抢单：1否 2是
	
	@Column(name= "eatStatus")
	private Integer eatStatus;  //是否平台吃单：1否 2是
	
	@Column(name= "status")
	private Integer status;  //订单状态：1排队中 2排队成功待支付 3待收款 4已成交 5已作废  6平台吃单待支付
	
	@Column(name= "userId")
	private Long userId;  //操作人id
	
	@Column(name= "saasId")
	private String saasId;  //saasId
	
	@Column(name= "remark")
	private String remark;  //

	@Transient
	private String mobilePhone;  //

	@Transient
	private String email;  //

	@Column(name= "timeStamp")
	private Long timeStamp;  //排队开始时间戳
	
	@Column(name= "customerGrade")
	private Integer customerGrade;  //排单时用户等级
	
	
	
	public Integer getCustomerGrade() {
		return customerGrade;
	}

	public void setCustomerGrade(Integer customerGrade) {
		this.customerGrade = customerGrade;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getEatStatus() {
		return eatStatus;
	}

	public void setEatStatus(Integer eatStatus) {
		this.eatStatus = eatStatus;
	}

	public KlgBuyTransaction() {
		super();
		// TODO Auto-generated constructor stub
	}

	public KlgBuyTransaction(Long id, Long customerId, String transactionNum, Long accountId, String coinCode,
			BigDecimal smeMoney, BigDecimal usdtMoney, BigDecimal firstMoney, BigDecimal lastMoney,
			BigDecimal interestMoney, Integer trusteeshipStatus, Integer rushOrders, Integer status, Long userId,
			String saasId, String remark, Long timeStamp) {
		super();
		this.id = id;
		this.customerId = customerId;
		this.transactionNum = transactionNum;
		this.accountId = accountId;
		this.coinCode = coinCode;
		this.smeMoney = smeMoney;
		this.usdtMoney = usdtMoney;
		this.firstMoney = firstMoney;
		this.lastMoney = lastMoney;
		this.interestMoney = interestMoney;
		this.trusteeshipStatus = trusteeshipStatus;
		this.rushOrders = rushOrders;
		this.status = status;
		this.userId = userId;
		this.saasId = saasId;
		this.remark = remark;
		this.timeStamp = timeStamp;
	}

	/**
	 * <p></p>
	 * @author:  yaozhuo
	 * @return:  Long 
	 * @Date :   2019-04-18 15:00:12    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  yaozhuo
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-04-18 15:00:12   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>用户id</p>
	 * @author:  yaozhuo
	 * @return:  Long 
	 * @Date :   2019-04-18 15:00:12    
	 */
	public Long getCustomerId() {
		return customerId;
	}
	
	/**
	 * <p>用户id</p>
	 * @author:  yaozhuo
	 * @param:   @param customerId
	 * @return:  void 
	 * @Date :   2019-04-18 15:00:12   
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	
	/**
	 * <p>流水号</p>
	 * @author:  yaozhuo
	 * @return:  String 
	 * @Date :   2019-04-18 15:00:12    
	 */
	public String getTransactionNum() {
		return transactionNum;
	}
	
	/**
	 * <p>流水号</p>
	 * @author:  yaozhuo
	 * @param:   @param transactionNum
	 * @return:  void 
	 * @Date :   2019-04-18 15:00:12   
	 */
	public void setTransactionNum(String transactionNum) {
		this.transactionNum = transactionNum;
	}
	
	
	/**
	 * <p>数字货币账户id</p>
	 * @author:  yaozhuo
	 * @return:  Long 
	 * @Date :   2019-04-18 15:00:12    
	 */
	public Long getAccountId() {
		return accountId;
	}
	
	/**
	 * <p>数字货币账户id</p>
	 * @author:  yaozhuo
	 * @param:   @param accountId
	 * @return:  void 
	 * @Date :   2019-04-18 15:00:12   
	 */
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	
	
	/**
	 * <p>币种</p>
	 * @author:  yaozhuo
	 * @return:  String 
	 * @Date :   2019-04-18 15:00:12    
	 */
	public String getCoinCode() {
		return coinCode;
	}
	
	/**
	 * <p>币种</p>
	 * @author:  yaozhuo
	 * @param:   @param coinCode
	 * @return:  void 
	 * @Date :   2019-04-18 15:00:12   
	 */
	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}
	
	
	/**
	 * <p>买入平台币金额</p>
	 * @author:  yaozhuo
	 * @return:  BigDecimal 
	 * @Date :   2019-04-18 15:00:12    
	 */
	public BigDecimal getSmeMoney() {
		return smeMoney;
	}
	
	/**
	 * <p>买入平台币金额</p>
	 * @author:  yaozhuo
	 * @param:   @param smeMoney
	 * @return:  void 
	 * @Date :   2019-04-18 15:00:12   
	 */
	public void setSmeMoney(BigDecimal smeMoney) {
		this.smeMoney = smeMoney;
	}
	
	
	/**
	 * <p>需要支付USDT金额</p>
	 * @author:  yaozhuo
	 * @return:  BigDecimal 
	 * @Date :   2019-04-18 15:00:12    
	 */
	public BigDecimal getUsdtMoney() {
		return usdtMoney;
	}
	
	/**
	 * <p>需要支付USDT金额</p>
	 * @author:  yaozhuo
	 * @param:   @param usdtMoney
	 * @return:  void 
	 * @Date :   2019-04-18 15:00:12   
	 */
	public void setUsdtMoney(BigDecimal usdtMoney) {
		this.usdtMoney = usdtMoney;
	}
	
	
	/**
	 * <p>保证金</p>
	 * @author:  yaozhuo
	 * @return:  BigDecimal 
	 * @Date :   2019-04-18 15:00:12    
	 */
	public BigDecimal getFirstMoney() {
		return firstMoney;
	}
	
	/**
	 * <p>保证金</p>
	 * @author:  yaozhuo
	 * @param:   @param firstMoney
	 * @return:  void 
	 * @Date :   2019-04-18 15:00:12   
	 */
	public void setFirstMoney(BigDecimal firstMoney) {
		this.firstMoney = firstMoney;
	}
	
	
	/**
	 * <p>尾款</p>
	 * @author:  yaozhuo
	 * @return:  BigDecimal 
	 * @Date :   2019-04-18 15:00:12    
	 */
	public BigDecimal getLastMoney() {
		return lastMoney;
	}
	
	/**
	 * <p>尾款</p>
	 * @author:  yaozhuo
	 * @param:   @param lastMoney
	 * @return:  void 
	 * @Date :   2019-04-18 15:00:12   
	 */
	public void setLastMoney(BigDecimal lastMoney) {
		this.lastMoney = lastMoney;
	}
	
	
	/**
	 * <p>利息</p>
	 * @author:  yaozhuo
	 * @return:  BigDecimal 
	 * @Date :   2019-04-18 15:00:12    
	 */
	public BigDecimal getInterestMoney() {
		return interestMoney;
	}
	
	/**
	 * <p>利息</p>
	 * @author:  yaozhuo
	 * @param:   @param interestMoney
	 * @return:  void 
	 * @Date :   2019-04-18 15:00:12   
	 */
	public void setInterestMoney(BigDecimal interestMoney) {
		this.interestMoney = interestMoney;
	}
	
	
	/**
	 * <p>托管状态：1否 2是</p>
	 * @author:  yaozhuo
	 * @return:  Integer 
	 * @Date :   2019-04-18 15:00:12    
	 */
	public Integer getTrusteeshipStatus() {
		return trusteeshipStatus;
	}
	
	/**
	 * <p>托管状态：1否 2是</p>
	 * @author:  yaozhuo
	 * @param:   @param trusteeshipStatus
	 * @return:  void 
	 * @Date :   2019-04-18 15:00:12   
	 */
	public void setTrusteeshipStatus(Integer trusteeshipStatus) {
		this.trusteeshipStatus = trusteeshipStatus;
	}
	
	
	/**
	 * <p>是否抢单：1否 2是</p>
	 * @author:  yaozhuo
	 * @return:  Integer 
	 * @Date :   2019-04-18 15:00:12    
	 */
	public Integer getRushOrders() {
		return rushOrders;
	}
	
	/**
	 * <p>是否抢单：1否 2是</p>
	 * @author:  yaozhuo
	 * @param:   @param rushOrders
	 * @return:  void 
	 * @Date :   2019-04-18 15:00:12   
	 */
	public void setRushOrders(Integer rushOrders) {
		this.rushOrders = rushOrders;
	}
	
	
	/**
	 * <p>订单状态：1排队中 2排队成功待支付 3待收款 4已成交 5已作废 6平台吃单待支付 </p>
	 * @author:  yaozhuo
	 * @return:  Integer 
	 * @Date :   2019-04-18 15:00:12    
	 */
	public Integer getStatus() {
		return status;
	}
	
	/**
	 * <p>订单状态：1排队中 2排队成功待支付 3待收款 4已成交 5已作废 6平台吃单待支付 </p>
	 * @author:  yaozhuo
	 * @param:   @param status
	 * @return:  void 
	 * @Date :   2019-04-18 15:00:12   
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
	/**
	 * <p>操作人id</p>
	 * @author:  yaozhuo
	 * @return:  Long 
	 * @Date :   2019-04-18 15:00:12    
	 */
	public Long getUserId() {
		return userId;
	}
	
	/**
	 * <p>操作人id</p>
	 * @author:  yaozhuo
	 * @param:   @param userId
	 * @return:  void 
	 * @Date :   2019-04-18 15:00:12   
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	
	/**
	 * <p>saasId</p>
	 * @author:  yaozhuo
	 * @return:  String 
	 * @Date :   2019-04-18 15:00:12    
	 */
	public String getSaasId() {
		return saasId;
	}
	
	/**
	 * <p>saasId</p>
	 * @author:  yaozhuo
	 * @param:   @param saasId
	 * @return:  void 
	 * @Date :   2019-04-18 15:00:12   
	 */
	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}
	
	
	/**
	 * <p></p>
	 * @author:  yaozhuo
	 * @return:  String 
	 * @Date :   2019-04-18 15:00:12    
	 */
	public String getRemark() {
		return remark;
	}
	
	/**
	 * <p></p>
	 * @author:  yaozhuo
	 * @param:   @param remark
	 * @return:  void 
	 * @Date :   2019-04-18 15:00:12   
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	/**
	 * <p>排队开始时间戳</p>
	 * @author:  yaozhuo
	 * @return:  Long 
	 * @Date :   2019-04-18 15:00:12    
	 */
	public Long getTimeStamp() {
		return timeStamp;
	}
	
	/**
	 * <p>排队开始时间戳</p>
	 * @author:  yaozhuo
	 * @param:   @param timeStamp
	 * @return:  void 
	 * @Date :   2019-04-18 15:00:12   
	 */
	public void setTimeStamp(Long timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	

}
