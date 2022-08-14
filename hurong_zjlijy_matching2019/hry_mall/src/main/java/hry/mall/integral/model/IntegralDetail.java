/**
 * Copyright:
 * @author:      kongdb
 * @version:     V1.0
 * @Date:        2019-01-08 17:32:03
 */
package hry.mall.integral.model;


import hry.bean.BaseModel;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;

/**
 * <p> IntegralDetail </p>
 * @author:         kongdb
 * @Date :          2019-01-08 17:32:03  
 */
@Table(name="shop_integral_detail")
public class IntegralDetail extends BaseModel {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //索引id

	@Column(name= "memberId")
	private Long memberId;  //积分账户主键

	@Column(name= "rewardId")
	private Long rewardId;  //奖励类型id

	@Column(name= "rewardType")
	private Integer rewardType;  //奖励类型 0 购物挖矿 1分销挖矿 2 任务挖矿3算力挖矿,4 积分兑换商品

	@Column(name= "integralCount")
	private BigDecimal integralCount;  //积分值

	@Column(name= "computingCount")
	private BigDecimal computingCount;  //算力值

	@Column(name= "rewardintegralCount")
	private BigDecimal rewardintegralCount;  //积分发放数量

	@Column(name= "callbackintegralCount")
	private BigDecimal callbackintegralCount;  //积分发放数量

	@Column(name= "rewardComputingCount")
	private BigDecimal rewardComputingCount;  //算力发放数量

	@Column(name= "rewardDate")
	private Date rewardDate;  //产出时间

	@Column(name= "finishDate")
	private Date finishDate;  //

	@Column(name= "estimateRewardDate")
	private Date estimateRewardDate;  //预计发放时间

	@Column(name= "orderId")
	private Long orderId;  //订单Id

	@Column(name= "orderSn")
	private String orderSn;  //订单编号

	@Column(name= "orderDate")
	private Date orderDate;  //下单时间

	@Column(name= "orderMoney")
	private BigDecimal orderMoney;  //金额

	@Column(name= "buyerId")
	private Long buyerId;  //买家id

	@Column(name= "buyerName")
	private String buyerName;  //下单用户

	@Column(name= "rewardLevel")
	private String rewardLevel;  //分销级别

	@Column(name= "taskName")
	private String taskName;  //任务名称

	@Column(name= "taskId")
	private Long taskId;  //任务Id

	@Column(name= "detailStatus")
	private Integer detailStatus;  //开启累计0 待发放 1 已发放 2 锁定中

	@Column(name= "memberName")
	private String memberName;  //用户名

	@Column(name= "memberTruename")
	private String memberTruename;  //真实姓名

	@Column(name= "cardNumber")
	private String cardNumber;  //身份证号

	@Column(name= "accountId")
	private Long accountId;  //积分账户表id
	
	@Column(name= "coinCount")
	private BigDecimal coinCount;  //对应平台币数量
	
	@Column(name= "factCoinCount")
	private BigDecimal factCoinCount; //实际到账平台币数量
	
	@Column(name= "coinCode")
	private String coinCode;  //平台币币种

	@Transient
	private BigDecimal todayShopIntegral; //购物今日新增

	@Transient
	private BigDecimal monthShopIntegral; //购物本月新增

	@Transient
	private BigDecimal todayTeamIntegral; //团队今日新增

	@Transient
	private BigDecimal monthTeamIntegral; //团队本月新增

	@Transient
	private String integralCode; //积分代码

	@Column(name= "tradingDetail")
	private String tradingDetail; // 交易详情

	@Column(name= "tradingType")
	private Integer tradingType; // 交易状态 1:交易中 2：交易完成 3：交易失败;

	@Column(name= "requestNo")
	private String requestNo;//流水号  业务表和流水表关联


	@Column(name= "businessType")
	private Integer businessType;//流水类型 1：兑换 2：话费充值 3：转出 4：转入 5：手续费 6:返利(shop_integral_commission)7、会员升级(shop_level_record)9、广告奖励  99:初始化可用积分


	public String getCoinCode() {
		return coinCode;
	}

	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}

	public BigDecimal getCoinCount() {
		return coinCount;
	}

	public void setCoinCount(BigDecimal coinCount) {
		this.coinCount = coinCount;
	}

	public BigDecimal getFactCoinCount() {
		return factCoinCount;
	}

	public void setFactCoinCount(BigDecimal factCoinCount) {
		this.factCoinCount = factCoinCount;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public String getIntegralCode() {
		return integralCode;
	}

	public void setIntegralCode(String integralCode) {
		this.integralCode = integralCode;
	}

	public BigDecimal getTodayShopIntegral() {
		return todayShopIntegral;
	}

	public void setTodayShopIntegral(BigDecimal todayShopIntegral) {
		this.todayShopIntegral = todayShopIntegral;
	}

	public BigDecimal getMonthShopIntegral() {
		return monthShopIntegral;
	}

	public void setMonthShopIntegral(BigDecimal monthShopIntegral) {
		this.monthShopIntegral = monthShopIntegral;
	}

	public BigDecimal getTodayTeamIntegral() {
		return todayTeamIntegral;
	}

	public void setTodayTeamIntegral(BigDecimal todayTeamIntegral) {
		this.todayTeamIntegral = todayTeamIntegral;
	}

	public BigDecimal getMonthTeamIntegral() {
		return monthTeamIntegral;
	}

	public void setMonthTeamIntegral(BigDecimal monthTeamIntegral) {
		this.monthTeamIntegral = monthTeamIntegral;
	}

	public BigDecimal getCallbackintegralCount() {
		return callbackintegralCount;
	}

	public void setCallbackintegralCount(BigDecimal callbackintegralCount) {
		this.callbackintegralCount = callbackintegralCount;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	/**
	 * <p>索引id</p>
	 * @author:  kongdb
	 * @return:  Long
	 * @Date :   2019-01-08 17:32:03
	 */
	public Long getId() {
		return id;
	}

	/**
	 * <p>索引id</p>
	 * @author:  kongdb
	 * @param:   @param id
	 * @return:  void
	 * @Date :   2019-01-08 17:32:03
	 */
	public void setId(Long id) {
		this.id = id;
	}


	/**
	 * <p>积分账户主键</p>
	 * @author:  kongdb
	 * @return:  String
	 * @Date :   2019-01-08 17:32:03
	 */
	public Long getMemberId() {
		return memberId;
	}

	/**
	 * <p>积分账户主键</p>
	 * @author:  kongdb
	 * @param:   @param memberId
	 * @return:  void
	 * @Date :   2019-01-08 17:32:03
	 */
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}


	/**
	 * <p>奖励类型id</p>
	 * @author:  kongdb
	 * @return:  String
	 * @Date :   2019-01-08 17:32:03
	 */
	public Long getRewardId() {
		return rewardId;
	}

	/**
	 * <p>奖励类型id</p>
	 * @author:  kongdb
	 * @param:   @param rewardId
	 * @return:  void
	 * @Date :   2019-01-08 17:32:03
	 */
	public void setRewardId(Long rewardId) {
		this.rewardId = rewardId;
	}


	/**
	 * <p>奖励类型 0 购物挖矿 1分销挖矿 2 任务挖矿3算力挖矿</p>
	 * @author:  kongdb
	 * @return:  Integer
	 * @Date :   2019-01-08 17:32:03
	 */
	public Integer getRewardType() {
		return rewardType;
	}

	/**
	 * <p>奖励类型 0 购物挖矿 1分销挖矿 2 任务挖矿3算力挖矿</p>
	 * @author:  kongdb
	 * @param:   @param rewardType
	 * @return:  void
	 * @Date :   2019-01-08 17:32:03
	 */
	public void setRewardType(Integer rewardType) {
		this.rewardType = rewardType;
	}


	/**
	 * <p>积分值</p>
	 * @author:  kongdb
	 * @return:  BigDecimal
	 * @Date :   2019-01-08 17:32:03
	 */
	public BigDecimal getIntegralCount() {
		return integralCount;
	}

	/**
	 * <p>积分值</p>
	 * @author:  kongdb
	 * @param:   @param integralCount
	 * @return:  void
	 * @Date :   2019-01-08 17:32:03
	 */
	public void setIntegralCount(BigDecimal integralCount) {
		this.integralCount = integralCount;
	}


	/**
	 * <p>算力值</p>
	 * @author:  kongdb
	 * @return:  BigDecimal
	 * @Date :   2019-01-08 17:32:03
	 */
	public BigDecimal getComputingCount() {
		return computingCount;
	}

	/**
	 * <p>算力值</p>
	 * @author:  kongdb
	 * @param:   @param computingCount
	 * @return:  void
	 * @Date :   2019-01-08 17:32:03
	 */
	public void setComputingCount(BigDecimal computingCount) {
		this.computingCount = computingCount;
	}


	/**
	 * <p>积分发放数量</p>
	 * @author:  kongdb
	 * @return:  BigDecimal
	 * @Date :   2019-01-08 17:32:03
	 */
	public BigDecimal getRewardintegralCount() {
		return rewardintegralCount;
	}

	/**
	 * <p>积分发放数量</p>
	 * @author:  kongdb
	 * @param:   @param rewardintegralCount
	 * @return:  void
	 * @Date :   2019-01-08 17:32:03
	 */
	public void setRewardintegralCount(BigDecimal rewardintegralCount) {
		this.rewardintegralCount = rewardintegralCount;
	}


	/**
	 * <p>算力发放数量</p>
	 * @author:  kongdb
	 * @return:  BigDecimal
	 * @Date :   2019-01-08 17:32:03
	 */
	public BigDecimal getRewardComputingCount() {
		return rewardComputingCount;
	}

	/**
	 * <p>算力发放数量</p>
	 * @author:  kongdb
	 * @param:   @param rewardComputingCount
	 * @return:  void
	 * @Date :   2019-01-08 17:32:03
	 */
	public void setRewardComputingCount(BigDecimal rewardComputingCount) {
		this.rewardComputingCount = rewardComputingCount;
	}


	/**
	 * <p>产出时间</p>
	 * @author:  kongdb
	 * @return:  Date
	 * @Date :   2019-01-08 17:32:03
	 */
	public Date getRewardDate() {
		return rewardDate;
	}

	/**
	 * <p>产出时间</p>
	 * @author:  kongdb
	 * @param:   @param rewardDate
	 * @return:  void
	 * @Date :   2019-01-08 17:32:03
	 */
	public void setRewardDate(Date rewardDate) {
		this.rewardDate = rewardDate;
	}


	/**
	 * <p></p>
	 * @author:  kongdb
	 * @return:  Date
	 * @Date :   2019-01-08 17:32:03
	 */
	public Date getFinishDate() {
		return finishDate;
	}

	/**
	 * <p></p>
	 * @author:  kongdb
	 * @param:   @param issueDate
	 * @return:  void
	 * @Date :   2019-01-08 17:32:03
	 */
	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
	}


	/**
	 * <p>预计发放时间</p>
	 * @author:  kongdb
	 * @return:  Date
	 * @Date :   2019-01-08 17:32:03
	 */
	public Date getEstimateRewardDate() {
		return estimateRewardDate;
	}

	/**
	 * <p>预计发放时间</p>
	 * @author:  kongdb
	 * @param:   @param estimateRewardDate
	 * @return:  void
	 * @Date :   2019-01-08 17:32:03
	 */
	public void setEstimateRewardDate(Date estimateRewardDate) {
		this.estimateRewardDate = estimateRewardDate;
	}


	/**
	 * <p>订单Id</p>
	 * @author:  kongdb
	 * @return:  String
	 * @Date :   2019-01-08 17:32:03
	 */
	public Long getOrderId() {
		return orderId;
	}

	/**
	 * <p>订单Id</p>
	 * @author:  kongdb
	 * @param:   @param orderId
	 * @return:  void
	 * @Date :   2019-01-08 17:32:03
	 */
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}


	/**
	 * <p>订单编号</p>
	 * @author:  kongdb
	 * @return:  String
	 * @Date :   2019-01-08 17:32:03
	 */
	public String getOrderSn() {
		return orderSn;
	}

	/**
	 * <p>订单编号</p>
	 * @author:  kongdb
	 * @param:   @param orderSn
	 * @return:  void
	 * @Date :   2019-01-08 17:32:03
	 */
	public void setOrderSn(String orderSn) {
		this.orderSn = orderSn;
	}


	/**
	 * <p>金额</p>
	 * @author:  kongdb
	 * @return:  BigDecimal
	 * @Date :   2019-01-08 17:32:03
	 */
	public BigDecimal getOrderMoney() {
		return orderMoney;
	}

	/**
	 * <p>金额</p>
	 * @author:  kongdb
	 * @param:   @param orderMoney
	 * @return:  void
	 * @Date :   2019-01-08 17:32:03
	 */
	public void setOrderMoney(BigDecimal orderMoney) {
		this.orderMoney = orderMoney;
	}


	/**
	 * <p>买家id</p>
	 * @author:  kongdb
	 * @return:  String
	 * @Date :   2019-01-08 17:32:03
	 */
	public Long getBuyerId() {
		return buyerId;
	}

	/**
	 * <p>买家id</p>
	 * @author:  kongdb
	 * @param:   @param buyerId
	 * @return:  void
	 * @Date :   2019-01-08 17:32:03
	 */
	public void setBuyerId(Long buyerId) {
		this.buyerId = buyerId;
	}


	/**
	 * <p>下单用户</p>
	 * @author:  kongdb
	 * @return:  String
	 * @Date :   2019-01-08 17:32:03
	 */
	public String getBuyerName() {
		return buyerName;
	}

	/**
	 * <p>下单用户</p>
	 * @author:  kongdb
	 * @param:   @param buyerName
	 * @return:  void
	 * @Date :   2019-01-08 17:32:03
	 */
	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}


	/**
	 * <p>分销级别</p>
	 * @author:  kongdb
	 * @return:  String
	 * @Date :   2019-01-08 17:32:03
	 */
	public String getRewardLevel() {
		return rewardLevel;
	}

	/**
	 * <p>分销级别</p>
	 * @author:  kongdb
	 * @param:   @param rewardLevel
	 * @return:  void
	 * @Date :   2019-01-08 17:32:03
	 */
	public void setRewardLevel(String rewardLevel) {
		this.rewardLevel = rewardLevel;
	}


	/**
	 * <p>任务名称</p>
	 * @author:  kongdb
	 * @return:  String
	 * @Date :   2019-01-08 17:32:03
	 */
	public String getTaskName() {
		return taskName;
	}

	/**
	 * <p>任务名称</p>
	 * @author:  kongdb
	 * @param:   @param taskName
	 * @return:  void
	 * @Date :   2019-01-08 17:32:03
	 */
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}


	/**
	 * <p>任务Id</p>
	 * @author:  kongdb
	 * @return:  String
	 * @Date :   2019-01-08 17:32:03
	 */
	public Long getTaskId() {
		return taskId;
	}

	/**
	 * <p>任务Id</p>
	 * @author:  kongdb
	 * @param:   @param taskId
	 * @return:  void
	 * @Date :   2019-01-08 17:32:03
	 */
	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}


	/**
	 * <p>开启累计0 待发放 1 已发放 2 锁定中</p>
	 * @author:  kongdb
	 * @return:  Integer
	 * @Date :   2019-01-08 17:32:03
	 */
	public Integer getDetailStatus() {
		return detailStatus;
	}

	/**
	 * <p>开启累计0 待发放 1 已发放 2 锁定中</p>
	 * @author:  kongdb
	 * @param:   @param detailStatus
	 * @return:  void
	 * @Date :   2019-01-08 17:32:03
	 */
	public void setDetailStatus(Integer detailStatus) {
		this.detailStatus = detailStatus;
	}


	/**
	 * <p>用户名</p>
	 * @author:  kongdb
	 * @return:  String
	 * @Date :   2019-01-08 17:32:03
	 */
	public String getMemberName() {
		return memberName;
	}

	/**
	 * <p>用户名</p>
	 * @author:  kongdb
	 * @param:   @param memberName
	 * @return:  void
	 * @Date :   2019-01-08 17:32:03
	 */
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}


	/**
	 * <p>真实姓名</p>
	 * @author:  kongdb
	 * @return:  String
	 * @Date :   2019-01-08 17:32:03
	 */
	public String getMemberTruename() {
		return memberTruename;
	}

	/**
	 * <p>真实姓名</p>
	 * @author:  kongdb
	 * @param:   @param memberTruename
	 * @return:  void
	 * @Date :   2019-01-08 17:32:03
	 */
	public void setMemberTruename(String memberTruename) {
		this.memberTruename = memberTruename;
	}


	/**
	 * <p>身份证号</p>
	 * @author:  kongdb
	 * @return:  String
	 * @Date :   2019-01-08 17:32:03
	 */
	public String getCardNumber() {
		return cardNumber;
	}

	/**
	 * <p>身份证号</p>
	 * @author:  kongdb
	 * @param:   @param cardNumber
	 * @return:  void
	 * @Date :   2019-01-08 17:32:03
	 */
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getTradingDetail() {
		return tradingDetail;
	}

	public void setTradingDetail(String tradingDetail) {
		this.tradingDetail = tradingDetail;
	}

	public Integer getTradingType() {
		return tradingType;
	}

	public void setTradingType(Integer tradingType) {
		this.tradingType = tradingType;
	}

	public String getRequestNo() {
		return requestNo;
	}

	public void setRequestNo(String requestNo) {
		this.requestNo = requestNo;
	}

	public Integer getBusinessType() {
		return businessType;
	}

	public void setBusinessType(Integer businessType) {
		this.businessType = businessType;
	}
}
