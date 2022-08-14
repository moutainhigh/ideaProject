package hry.trade.redis.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class EntrustTrade implements Serializable{
	private Long id;
	private String entrustNum;
	private String inEntrustNum;
	private Long customerId;
	private Long accountId;
	private Long coinAccountId;
	private String coinCode;
	private Integer fixPriceType; // 0真实货币1虚拟币
	private String fixPriceCoinCode; // 定价币种
	private Integer type;
	private Integer status;
	private BigDecimal entrustPrice;
	private BigDecimal entrustCount;
	private BigDecimal entrustSum;
	private BigDecimal surplusEntrustCount;
	private BigDecimal transactionSum;
	private Date entrustTime;
	private String userCode;
	private Long entrustTime_long;

	private BigDecimal transactionFeeRate;
	private BigDecimal transactionFee;
	private BigDecimal floatUpPrice;
	private BigDecimal floatDownPrice;

	// 委托的来源 (1. 表示人工pc 2. 表示 机器人3.表示人工移动端,4.强制平仓系统生成8 api )

	private Integer source;
	private String customerIp; // 下单用户的IP
	private Integer entrustWay;
	private String userName;
	private String trueName; // 名字
	private String surName;// 姓氏
	private BigDecimal processedPrice;
	private Integer cancelKeepN; // 撤销保留最新n条
	private Integer appointOpponentType;   //1,指定对手单，2，制定接单人
	private String appointOpponentRemark;//如果是1那就是委托2，人的id

	private Integer isOpenCoinFee;  //下单：0正常手续费收取1，平台币收取手续费）
	private String transactionFeeRateDiscount; //平台手续费的折扣
	private BigDecimal transactionFeePlat;  //平台手续费
	private String platCoin; //平台币
	private BigDecimal oneDiffRate;  //深度机器人差值率
	private Integer isType;   //0主交易1杠杆

	private Integer isThird; // 是否去第三方平台

	private String platform; // 第三方平台名字

	private String orderId; // 第三方平台订单号


	private Integer isTrigger; //'1未触发 ,2触发中 3已触发',
	private BigDecimal entrustPriceTrigger;  //触发价格
	private Integer functionType; //'1正常委托2，计划委托',
	private Integer triggerComparePrcie;  //触发价格界限1小于2大于
	private Integer transactionType;  //0直接成交 1匹配成交


	public Integer getTriggerComparePrcie() {
		return triggerComparePrcie;
	}

	public void setTriggerComparePrcie(Integer triggerComparePrcie) {
		this.triggerComparePrcie = triggerComparePrcie;
	}

	public Integer getIsTrigger() {
		return isTrigger;
	}

	public void setIsTrigger(Integer isTrigger) {
		this.isTrigger = isTrigger;
	}

	public BigDecimal getEntrustPriceTrigger() {
		return entrustPriceTrigger;
	}

	public void setEntrustPriceTrigger(BigDecimal entrustPriceTrigger) {
		this.entrustPriceTrigger = entrustPriceTrigger;
	}

	public Integer getFunctionType() {
		return functionType;
	}

	public void setFunctionType(Integer functionType) {
		this.functionType = functionType;
	}

	public Integer getIsThird() {
		return isThird;
	}

	public void setIsThird(Integer isThird) {
		this.isThird = isThird;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getInEntrustNum() {
		return inEntrustNum;
	}

	public void setInEntrustNum(String inEntrustNum) {
		this.inEntrustNum = inEntrustNum;
	}

	public BigDecimal getOneDiffRate() {
		return oneDiffRate;
	}

	public void setOneDiffRate(BigDecimal oneDiffRate) {
		this.oneDiffRate = oneDiffRate;
	}

	public String getPlatCoin() {
		return platCoin;
	}

	public void setPlatCoin(String platCoin) {
		this.platCoin = platCoin;
	}

	public Integer getIsOpenCoinFee() {
		return isOpenCoinFee;
	}

	public void setIsOpenCoinFee(Integer isOpenCoinFee) {
		this.isOpenCoinFee = isOpenCoinFee;
	}

	public String getTransactionFeeRateDiscount() {
		return transactionFeeRateDiscount;
	}

	public void setTransactionFeeRateDiscount(String transactionFeeRateDiscount) {
		this.transactionFeeRateDiscount = transactionFeeRateDiscount;
	}

	public BigDecimal getTransactionFeePlat() {
		return transactionFeePlat;
	}

	public void setTransactionFeePlat(BigDecimal transactionFeePlat) {
		this.transactionFeePlat = transactionFeePlat;
	}

	public Integer getCancelKeepN() {
		return cancelKeepN;
	}

	public void setCancelKeepN(Integer cancelKeepN) {
		this.cancelKeepN = cancelKeepN;
	}

	public String getSurName() {
		return surName;
	}

	public void setSurName(String surName) {
		this.surName = surName;
	}

	public String getTrueName() {
		return trueName;
	}

	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}

	public BigDecimal getProcessedPrice() {
		return processedPrice;
	}

	public void setProcessedPrice(BigDecimal processedPrice) {
		this.processedPrice = processedPrice;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public BigDecimal getTransactionFee() {
		return transactionFee;
	}

	public void setTransactionFee(BigDecimal transactionFee) {
		this.transactionFee = transactionFee;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getEntrustWay() {
		return entrustWay;
	}

	public void setEntrustWay(Integer entrustWay) {
		this.entrustWay = entrustWay;
	}

	public BigDecimal getSurplusEntrustCount() {
		return surplusEntrustCount;
	}

	public void setSurplusEntrustCount(BigDecimal surplusEntrustCount) {
		this.surplusEntrustCount = surplusEntrustCount;
	}

	public BigDecimal getTransactionSum() {
		return transactionSum;
	}

	public void setTransactionSum(BigDecimal transactionSum) {
		this.transactionSum = transactionSum;
	}

	public String getEntrustNum() {
		return entrustNum;
	}

	public void setEntrustNum(String entrustNum) {
		this.entrustNum = entrustNum;
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

	public Long getCoinAccountId() {
		return coinAccountId;
	}

	public void setCoinAccountId(Long coinAccountId) {
		this.coinAccountId = coinAccountId;
	}

	public String getCoinCode() {
		return coinCode;
	}

	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}

	public Integer getFixPriceType() {
		return fixPriceType;
	}

	public void setFixPriceType(Integer fixPriceType) {
		this.fixPriceType = fixPriceType;
	}

	public String getFixPriceCoinCode() {
		return fixPriceCoinCode;
	}

	public void setFixPriceCoinCode(String fixPriceCoinCode) {
		this.fixPriceCoinCode = fixPriceCoinCode;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public BigDecimal getEntrustPrice() {
		return entrustPrice;
	}

	public void setEntrustPrice(BigDecimal entrustPrice) {
		this.entrustPrice = entrustPrice;
	}

	public BigDecimal getEntrustCount() {
		return entrustCount;
	}

	public void setEntrustCount(BigDecimal entrustCount) {
		this.entrustCount = entrustCount;
	}

	public BigDecimal getEntrustSum() {
		return entrustSum;
	}

	public void setEntrustSum(BigDecimal entrustSum) {
		this.entrustSum = entrustSum;
	}

	public Date getEntrustTime() {
		return entrustTime;
	}

	public void setEntrustTime(Date entrustTime) {
		this.entrustTime = entrustTime;
	}

	public BigDecimal getTransactionFeeRate() {
		return transactionFeeRate;
	}

	public void setTransactionFeeRate(BigDecimal transactionFeeRate) {
		this.transactionFeeRate = transactionFeeRate;
	}

	public BigDecimal getFloatUpPrice() {
		return floatUpPrice;
	}

	public void setFloatUpPrice(BigDecimal floatUpPrice) {
		this.floatUpPrice = floatUpPrice;
	}

	public BigDecimal getFloatDownPrice() {
		return floatDownPrice;
	}

	public void setFloatDownPrice(BigDecimal floatDownPrice) {
		this.floatDownPrice = floatDownPrice;
	}

	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	public String getCustomerIp() {
		return customerIp;
	}

	public void setCustomerIp(String customerIp) {
		this.customerIp = customerIp;
	}

	public Long getEntrustTime_long() {
		return entrustTime_long;
	}

	public void setEntrustTime_long(Long entrustTime_long) {
		this.entrustTime_long = entrustTime_long;
	}

	public Integer getAppointOpponentType() {
		return appointOpponentType;
	}

	public void setAppointOpponentType(Integer appointOpponentType) {
		this.appointOpponentType = appointOpponentType;
	}

	public String getAppointOpponentRemark() {
		return appointOpponentRemark;
	}

	public void setAppointOpponentRemark(String appointOpponentRemark) {
		this.appointOpponentRemark = appointOpponentRemark;
	}

	public Integer getIsType() {
		return isType;
	}

	public void setIsType(Integer isType) {
		this.isType = isType;
	}

	public Integer getTransactionType () {
		return transactionType;
	}

	public void setTransactionType (Integer transactionType) {
		this.transactionType = transactionType;
	}

	@Override
	public String toString () {
		return "EntrustTrade{" +
				"id=" + id +
				", entrustNum='" + entrustNum + '\'' +
				", inEntrustNum='" + inEntrustNum + '\'' +
				", customerId=" + customerId +
				", accountId=" + accountId +
				", coinAccountId=" + coinAccountId +
				", coinCode='" + coinCode + '\'' +
				", fixPriceType=" + fixPriceType +
				", fixPriceCoinCode='" + fixPriceCoinCode + '\'' +
				", type=" + type +
				", status=" + status +
				", entrustPrice=" + entrustPrice +
				", entrustCount=" + entrustCount +
				", entrustSum=" + entrustSum +
				", surplusEntrustCount=" + surplusEntrustCount +
				", transactionSum=" + transactionSum +
				", entrustTime=" + entrustTime +
				", userCode='" + userCode + '\'' +
				", entrustTime_long=" + entrustTime_long +
				", transactionFeeRate=" + transactionFeeRate +
				", transactionFee=" + transactionFee +
				", floatUpPrice=" + floatUpPrice +
				", floatDownPrice=" + floatDownPrice +
				", source=" + source +
				", customerIp='" + customerIp + '\'' +
				", entrustWay=" + entrustWay +
				", userName='" + userName + '\'' +
				", trueName='" + trueName + '\'' +
				", surName='" + surName + '\'' +
				", processedPrice=" + processedPrice +
				", cancelKeepN=" + cancelKeepN +
				", appointOpponentType=" + appointOpponentType +
				", appointOpponentRemark='" + appointOpponentRemark + '\'' +
				", isOpenCoinFee=" + isOpenCoinFee +
				", transactionFeeRateDiscount='" + transactionFeeRateDiscount + '\'' +
				", transactionFeePlat=" + transactionFeePlat +
				", platCoin='" + platCoin + '\'' +
				", oneDiffRate=" + oneDiffRate +
				", isType=" + isType +
				", isThird=" + isThird +
				", platform='" + platform + '\'' +
				", orderId='" + orderId + '\'' +
				", isTrigger=" + isTrigger +
				", entrustPriceTrigger=" + entrustPriceTrigger +
				", functionType=" + functionType +
				", triggerComparePrcie=" + triggerComparePrcie +
				", transactionType=" + transactionType +
				'}';
	}
}
