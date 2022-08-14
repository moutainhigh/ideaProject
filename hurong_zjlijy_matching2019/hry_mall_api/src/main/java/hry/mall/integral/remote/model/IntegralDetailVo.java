package hry.mall.integral.remote.model;

import hry.bean.BaseModel;

import java.math.BigDecimal;
import java.util.Date;

public class IntegralDetailVo extends BaseModel{
	private Long id;  //索引id

	private Long memberId;  //积分账户主键

	private Long rewardId;  //奖励类型id

	private Integer rewardType;  //奖励类型 0 购物挖矿 1分销挖矿 2 任务挖矿3算力挖矿

	private BigDecimal integralCount;  //积分值

	private BigDecimal computingCount;  //算力值

	private BigDecimal rewardintegralCount;  //积分发放数量

	private BigDecimal callbackintegralCount;  //积分发放数量

	private BigDecimal rewardComputingCount;  //算力发放数量

	private Date rewardDate;  //产出时间

	private Date finishDate;  //

	private Date estimateRewardDate;  //预计发放时间

	private Long orderId;  //订单Id

	private String orderSn;  //订单编号

	private Date orderDate;  //下单时间

	private BigDecimal orderMoney;  //金额

	private Long buyerId;  //买家id

	private String buyerName;  //下单用户

	private String rewardLevel;  //分销级别

	private String taskName;  //任务名称

	private Long taskId;  //任务Id

	private Integer detailStatus;  //开启累计0 待发放 1 已发放 2 锁定中

	private String memberName;  //用户名

	private String memberTruename;  //真实姓名

	private String cardNumber;  //身份证号
	
	private Long accountId;  //积分账户表id
    
	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public Long getRewardId() {
		return rewardId;
	}

	public void setRewardId(Long rewardId) {
		this.rewardId = rewardId;
	}

	public Integer getRewardType() {
		return rewardType;
	}

	public void setRewardType(Integer rewardType) {
		this.rewardType = rewardType;
	}

	public BigDecimal getIntegralCount() {
		return integralCount;
	}

	public void setIntegralCount(BigDecimal integralCount) {
		this.integralCount = integralCount;
	}

	public BigDecimal getComputingCount() {
		return computingCount;
	}

	public void setComputingCount(BigDecimal computingCount) {
		this.computingCount = computingCount;
	}

	public BigDecimal getRewardintegralCount() {
		return rewardintegralCount;
	}

	public void setRewardintegralCount(BigDecimal rewardintegralCount) {
		this.rewardintegralCount = rewardintegralCount;
	}

	public BigDecimal getCallbackintegralCount() {
		return callbackintegralCount;
	}

	public void setCallbackintegralCount(BigDecimal callbackintegralCount) {
		this.callbackintegralCount = callbackintegralCount;
	}

	public BigDecimal getRewardComputingCount() {
		return rewardComputingCount;
	}

	public void setRewardComputingCount(BigDecimal rewardComputingCount) {
		this.rewardComputingCount = rewardComputingCount;
	}

	public Date getRewardDate() {
		return rewardDate;
	}

	public void setRewardDate(Date rewardDate) {
		this.rewardDate = rewardDate;
	}

	public Date getFinishDate() {
		return finishDate;
	}

	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
	}

	public Date getEstimateRewardDate() {
		return estimateRewardDate;
	}

	public void setEstimateRewardDate(Date estimateRewardDate) {
		this.estimateRewardDate = estimateRewardDate;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getOrderSn() {
		return orderSn;
	}

	public void setOrderSn(String orderSn) {
		this.orderSn = orderSn;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public BigDecimal getOrderMoney() {
		return orderMoney;
	}

	public void setOrderMoney(BigDecimal orderMoney) {
		this.orderMoney = orderMoney;
	}

	public Long getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(Long buyerId) {
		this.buyerId = buyerId;
	}

	public String getBuyerName() {
		return buyerName;
	}

	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}

	public String getRewardLevel() {
		return rewardLevel;
	}

	public void setRewardLevel(String rewardLevel) {
		this.rewardLevel = rewardLevel;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	public Integer getDetailStatus() {
		return detailStatus;
	}

	public void setDetailStatus(Integer detailStatus) {
		this.detailStatus = detailStatus;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMemberTruename() {
		return memberTruename;
	}

	public void setMemberTruename(String memberTruename) {
		this.memberTruename = memberTruename;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	
}
