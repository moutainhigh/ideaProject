/**

 * Copyright:   北京互融时代软件有限公司
 * @author:      Wu Shuiming
 * @version:      V1.0
 * @Date:        2016年3月25日 下午4:09:31
 */
package hry.trade.entrust.model;

import hry.core.mvc.model.BaseExModel;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * <p>
 *
 * </p>
 *
 * @author: Wu Shuiming
 * @Date : 2016年3月25日 下午4:09:31
 */
@SuppressWarnings("serial")
@Table(name = "ex_entrust")
public class ExEntrust extends BaseExModel {

	// 主键
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	// 委托单号
	@Column(name = "entrustNum")
	private String entrustNum;
	// 委托人
	@Column(name = "customerId")
	private Long customerId;
	// 客户编号
	@Column(name = "userCode")
	private String userCode;
	// 手机号
	@Column(name = "userName")
	private String userName;
	@Column(name = "trueName")
	private String trueName;

	@Column(name = "surname")
	private String surname;
	// 委托人的币账号id
	@Column(name = "accountId")
	private Long accountId;
	// 委托人的币账号id
	@Column(name = "coinAccountId")
	private Long coinAccountId;
	// 币的代码
	@Column(name = "coinCode")
	private String coinCode;
	// TYPE 类型 1 ： 买 2 ： 卖
	@Column(name = "type")
	private Integer type;
	// STATUS 状态 ---> 0未成交 1部分成交 2已完成 3部分成交已撤销 4已撤销
	@Column(name = "status")
	private Integer status;
	// 委托价格
	@Column(name = "entrustPrice")
	private BigDecimal entrustPrice;
	/*
	 * // 委托价格
	 *
	 * @Column(name = "entrustPriceDouble") private double entrustPriceDouble;
	 */
	// 委托数量
	@Column(name = "entrustCount")
	private BigDecimal entrustCount;
	// 委托总金额
	@Column(name = "entrustSum")
	private BigDecimal entrustSum;
	// 委托时间
	@Column(name = "entrustTime")
	private Date entrustTime;
	// 委托方式 1.限价--> 表示以固定的价格 , 2.市价---> 表示以当前市场价，3普通价格优先(谁的价格越低就跟谁匹配)，时间优先5,买市价
	// （1的特殊情况,一定的范围可以撮合用来做市用的不然的话，买向下浮动，卖向上浮动）
	@Column(name = "entrustWay") // （1,2组合 ）（1，2，4组合）
	private Integer entrustWay;
	// 委托剩余数量
	@Column(name = "surplusEntrustCount")
	private BigDecimal surplusEntrustCount;
	@Column(name = "fixPriceType")
	private Integer fixPriceType; // 0真实货币1虚拟币
	// 币的代码
	@Column(name = "fixPriceCoinCode")
	private String fixPriceCoinCode; // 定价币种
	// 成交的的总手续费率
	@Column(name = "transactionFeeRate")
	private BigDecimal transactionFeeRate;
	// 成交的的总手续费率平台币折扣
	@Column(name = "transactionFeeRateDiscount")
	private BigDecimal transactionFeeRateDiscount;
	@Column(name = "isOpenCoinFee")
	private Integer isOpenCoinFee;  //下单：0正常手续费收取1，平台币收取手续费）
	@Column(name = "platCoin")
	private String platCoin; //平台币

	@Column(name = "inEntrustNum")
	private String inEntrustNum;   //内部订单号
	@Column(name = "isType")
	private Integer isType;   //0主交易1杠杆
	public String getPlatCoin() {
		return platCoin;
	}

	public void setPlatCoin(String platCoin) {
		this.platCoin = platCoin;
	}

	// 成交的的总手续费
	@Column(name = "transactionFee")
	private BigDecimal transactionFee;
	// 成交的的平台币总手续费
	@Column(name = "transactionFeePlat")
	private BigDecimal transactionFeePlat;
	// 成交的金额
	@Column(name = "transactionSum")
	private BigDecimal transactionSum;

	// 成交的平均价
	@Column(name = "processedPrice")
	private BigDecimal processedPrice;
	// 客户类型customerType甲类账户1(普通的，默认)，乙类账号2，丙类账户3
	@Column(name = "customerType")
	private Integer customerType;
	// 可以向上线浮动的价格
	@Column(name = "floatUpPrice")
	private BigDecimal floatUpPrice;
	// 可以向下线浮动的价格
	@Column(name = "floatDownPrice")
	private BigDecimal floatDownPrice;

	// 委托的来源 (1. 表示人工pc 2. 表示 机器人3.表示人工移动端
	// 4.强制平仓系统生成,5计划委托，6，止盈平仓系统生成，7止损平仓系统生成)
	@Column(name = "source")
	private Integer source;
	/*
	 * //坚决不匹配的委托，一般坐市用的
	 *
	 * @Column(name = "noMatchExEntrusts") private String noMatchExEntrusts;
	 */
	/*
	 * //只能匹配这些，一般坐市用的
	 *
	 * @Column(name = "onlyMatchExEntrusts")
	 */
	@Transient
	private String onlyMatchExEntrusts;
	// 匹配优先级 普通1，平仓5
	@Column(name = "matchPriority")
	private Integer matchPriority;
	// 成交时间
	@Column(name = "transactionTime")
	private Date transactionTime;

	// 下面是现货特有的字段
	// 1，null虚拟币2现货
	@Column(name = "projectType")
	private Integer projectType;
	@Column(name = "customerIp")
	private String customerIp; // 下单用户的IP
	@Transient
	private double transactionSumDouble;
	@Transient
	private double transactionFeeDouble;
	@Transient
	private double surplusEntrustCountDouble;
	@Transient
	private double processedPriceDouble;
	@Transient
	private double transactionFeePlatDouble;
	@Transient
	private int statusint;



	public Integer getIsOpenCoinFee() {
		return isOpenCoinFee;
	}

	public void setIsOpenCoinFee(Integer isOpenCoinFee) {
		this.isOpenCoinFee = isOpenCoinFee;
	}

	public double getTransactionFeePlatDouble() {
		return transactionFeePlatDouble;
	}

	public void setTransactionFeePlatDouble(double transactionFeePlatDouble) {
		this.transactionFeePlatDouble = transactionFeePlatDouble;
	}

	public int getStatusint() {
		return statusint;
	}

	public void setStatusint(int statusint) {
		this.statusint = statusint;
	}

	public double getProcessedPriceDouble() {
		return processedPriceDouble;
	}

	public void setProcessedPriceDouble(double processedPriceDouble) {
		this.processedPriceDouble = processedPriceDouble;
	}

	public String getFixPriceCoinCode() {
		return fixPriceCoinCode;
	}

	public double getTransactionFeeDouble() {
		return transactionFeeDouble;
	}

	public void setTransactionFeeDouble(double transactionFeeDouble) {
		this.transactionFeeDouble = transactionFeeDouble;
	}

	public double getSurplusEntrustCountDouble() {
		return surplusEntrustCountDouble;
	}

	public void setSurplusEntrustCountDouble(double surplusEntrustCountDouble) {
		this.surplusEntrustCountDouble = surplusEntrustCountDouble;
	}

	public double getTransactionSumDouble() {
		return transactionSumDouble;
	}

	public void setTransactionSumDouble(double transactionSumDouble) {
		this.transactionSumDouble = transactionSumDouble;
	}

	public void setFixPriceCoinCode(String fixPriceCoinCode) {
		this.fixPriceCoinCode = fixPriceCoinCode;
	}

	/**
	 * <p>
	 * TODO
	 * </p>
	 *
	 * @return: String
	 */
	public String getCustomerIp() {
		return customerIp;
	}

	/**
	 * <p>
	 * TODO
	 * </p>
	 *
	 * @return: String
	 */
	public void setCustomerIp(String customerIp) {
		this.customerIp = customerIp;
	}

	/**
	 * <p>
	 * TODO
	 * </p>
	 *
	 * @return: Long
	 */
	public Long getId() {
		return id;
	}

	/**
	 * <p>
	 * TODO
	 * </p>
	 *
	 * @return: Long
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * <p>
	 * TODO
	 * </p>
	 *
	 * @return: String
	 */
	public String getEntrustNum() {
		return entrustNum;
	}

	/**
	 * <p>
	 * TODO
	 * </p>
	 *
	 * @return: String
	 */
	public void setEntrustNum(String entrustNum) {
		this.entrustNum = entrustNum;
	}

	/**
	 * <p>
	 * TODO
	 * </p>
	 *
	 * @return: Long
	 */
	public Long getCustomerId() {
		return customerId;
	}

	/**
	 * <p>
	 * TODO
	 * </p>
	 *
	 * @return: Long
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	/**
	 * <p>
	 * TODO
	 * </p>
	 *
	 * @return: String
	 */
	public String getUserCode() {
		return userCode;
	}

	/**
	 * <p>
	 * TODO
	 * </p>
	 *
	 * @return: String
	 */
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	/**
	 * <p>
	 * TODO
	 * </p>
	 *
	 * @return: String
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * <p>
	 * TODO
	 * </p>
	 *
	 * @return: String
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * <p>
	 * TODO
	 * </p>
	 *
	 * @return: String
	 */
	public String getTrueName() {
		return trueName;
	}

	/**
	 * <p>
	 * TODO
	 * </p>
	 *
	 * @return: String
	 */
	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}

	/**
	 * <p>
	 * TODO
	 * </p>
	 *
	 * @return: Long
	 */
	public Long getAccountId() {
		return accountId;
	}

	/**
	 * <p>
	 * TODO
	 * </p>
	 *
	 * @return: Long
	 */
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	/**
	 * <p>
	 * TODO
	 * </p>
	 *
	 * @return: String
	 */
	public String getCoinCode() {
		return coinCode;
	}

	/**
	 * <p>
	 * TODO
	 * </p>
	 *
	 * @return: String
	 */
	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}

	/**
	 * <p>
	 * TODO
	 * </p>
	 *
	 * @return: Integer
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * <p>
	 * TODO
	 * </p>
	 *
	 * @return: Integer
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * <p>
	 * TODO
	 * </p>
	 *
	 * @return: Integer
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * <p>
	 * TODO
	 * </p>
	 *
	 * @return: Integer
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * <p>
	 * TODO
	 * </p>
	 *
	 * @return: BigDecimal
	 */
	public BigDecimal getEntrustPrice() {
		return entrustPrice;
	}

	/**
	 * <p>
	 * TODO
	 * </p>
	 *
	 * @return: BigDecimal
	 */
	public void setEntrustPrice(BigDecimal entrustPrice) {
		this.entrustPrice = entrustPrice;
	}

	/**
	 * <p>
	 * TODO
	 * </p>
	 *
	 * @return: BigDecimal
	 */
	public BigDecimal getEntrustCount() {
		return entrustCount;
	}

	/**
	 * <p>
	 * TODO
	 * </p>
	 *
	 * @return: BigDecimal
	 */
	public void setEntrustCount(BigDecimal entrustCount) {
		this.entrustCount = entrustCount;
	}

	/**
	 * <p>
	 * TODO
	 * </p>
	 *
	 * @return: BigDecimal
	 */
	public BigDecimal getEntrustSum() {
		return entrustSum;
	}

	/**
	 * <p>
	 * TODO
	 * </p>
	 *
	 * @return: BigDecimal
	 */
	public void setEntrustSum(BigDecimal entrustSum) {
		this.entrustSum = entrustSum;
	}

	/**
	 * <p>
	 * TODO
	 * </p>
	 *
	 * @return: Date
	 */
	public Date getEntrustTime() {
		return entrustTime;
	}

	/**
	 * <p>
	 * TODO
	 * </p>
	 *
	 * @return: Date
	 */
	public void setEntrustTime(Date entrustTime) {
		this.entrustTime = entrustTime;
	}

	/**
	 * <p>
	 * TODO
	 * </p>
	 *
	 * @return: Integer
	 */
	public Integer getEntrustWay() {
		return entrustWay;
	}

	/**
	 * <p>
	 * TODO
	 * </p>
	 *
	 * @return: Integer
	 */
	public void setEntrustWay(Integer entrustWay) {
		this.entrustWay = entrustWay;
	}

	/**
	 * <p>
	 * TODO
	 * </p>
	 *
	 * @return: BigDecimal
	 */
	public BigDecimal getSurplusEntrustCount() {
		return surplusEntrustCount;
	}

	/**
	 * <p>
	 * TODO
	 * </p>
	 *
	 * @return: BigDecimal
	 */
	public void setSurplusEntrustCount(BigDecimal surplusEntrustCount) {
		this.surplusEntrustCount = surplusEntrustCount;
	}

	/**
	 * <p>
	 * TODO
	 * </p>
	 *
	 * @return: BigDecimal
	 */
	public BigDecimal getTransactionFeeRate() {
		return transactionFeeRate;
	}

	/**
	 * <p>
	 * TODO
	 * </p>
	 *
	 * @return: BigDecimal
	 */
	public void setTransactionFeeRate(BigDecimal transactionFeeRate) {
		this.transactionFeeRate = transactionFeeRate;
	}

	/**
	 * <p>
	 * TODO
	 * </p>
	 *
	 * @return: BigDecimal
	 */
	public BigDecimal getTransactionFee() {
		return transactionFee;
	}

	/**
	 * <p>
	 * TODO
	 * </p>
	 *
	 * @return: BigDecimal
	 */
	public void setTransactionFee(BigDecimal transactionFee) {
		this.transactionFee = transactionFee;
	}

	/**
	 * <p>
	 * TODO
	 * </p>
	 *
	 * @return: BigDecimal
	 */
	public BigDecimal getTransactionSum() {
		return transactionSum;
	}

	/**
	 * <p>
	 * TODO
	 * </p>
	 *
	 * @return: BigDecimal
	 */
	public void setTransactionSum(BigDecimal transactionSum) {
		this.transactionSum = transactionSum;
	}

	/**
	 * <p>
	 * TODO
	 * </p>
	 *
	 * @return: BigDecimal
	 */
	public BigDecimal getProcessedPrice() {
		return processedPrice;
	}

	/**
	 * <p>
	 * TODO
	 * </p>
	 *
	 * @return: BigDecimal
	 */
	public void setProcessedPrice(BigDecimal processedPrice) {
		this.processedPrice = processedPrice;
	}

	/**
	 * <p>
	 * TODO
	 * </p>
	 *
	 * @return: Integer
	 */
	public Integer getCustomerType() {
		return customerType;
	}

	/**
	 * <p>
	 * TODO
	 * </p>
	 *
	 * @return: Integer
	 */
	public void setCustomerType(Integer customerType) {
		this.customerType = customerType;
	}

	/**
	 * <p>
	 * TODO
	 * </p>
	 *
	 * @return: BigDecimal
	 */
	public BigDecimal getFloatUpPrice() {
		return floatUpPrice;
	}

	/**
	 * <p>
	 * TODO
	 * </p>
	 *
	 * @return: BigDecimal
	 */
	public void setFloatUpPrice(BigDecimal floatUpPrice) {
		this.floatUpPrice = floatUpPrice;
	}

	/**
	 * <p>
	 * TODO
	 * </p>
	 *
	 * @return: BigDecimal
	 */
	public BigDecimal getFloatDownPrice() {
		return floatDownPrice;
	}

	/**
	 * <p>
	 * TODO
	 * </p>
	 *
	 * @return: BigDecimal
	 */
	public void setFloatDownPrice(BigDecimal floatDownPrice) {
		this.floatDownPrice = floatDownPrice;
	}

	/**
	 * <p>
	 * TODO
	 * </p>
	 *
	 * @return: Integer
	 */
	public Integer getSource() {
		return source;
	}

	/**
	 * <p>
	 * TODO
	 * </p>
	 *
	 * @return: Integer
	 */
	public void setSource(Integer source) {
		this.source = source;
	}

	/**
	 * <p>
	 * TODO
	 * </p>
	 *
	 * @return: String
	 */
	public String getOnlyMatchExEntrusts() {
		return onlyMatchExEntrusts;
	}

	/**
	 * <p>
	 * TODO
	 * </p>
	 *
	 * @return: String
	 */
	public void setOnlyMatchExEntrusts(String onlyMatchExEntrusts) {
		this.onlyMatchExEntrusts = onlyMatchExEntrusts;
	}

	/**
	 * <p>
	 * TODO
	 * </p>
	 *
	 * @return: Integer
	 */
	public Integer getMatchPriority() {
		return matchPriority;
	}

	/**
	 * <p>
	 * TODO
	 * </p>
	 *
	 * @return: Integer
	 */
	public void setMatchPriority(Integer matchPriority) {
		this.matchPriority = matchPriority;
	}

	/**
	 * <p>
	 * TODO
	 * </p>
	 *
	 * @return: Integer
	 */
	public Integer getProjectType() {
		return projectType;
	}

	/**
	 * <p>
	 * TODO
	 * </p>
	 *
	 * @return: Integer
	 */
	public void setProjectType(Integer projectType) {
		this.projectType = projectType;
	}

	/**
	 * <p>
	 * TODO
	 * </p>
	 *
	 * @return: Date
	 */
	public Date getTransactionTime() {
		return transactionTime;
	}

	/**
	 * <p>
	 * TODO
	 * </p>
	 *
	 * @return: Date
	 */
	public void setTransactionTime(Date transactionTime) {
		this.transactionTime = transactionTime;
	}

	public Integer getFixPriceType() {
		return fixPriceType;
	}

	public void setFixPriceType(Integer fixPriceType) {
		this.fixPriceType = fixPriceType;
	}

	public Long getCoinAccountId() {
		return coinAccountId;
	}

	public void setCoinAccountId(Long coinAccountId) {
		this.coinAccountId = coinAccountId;
	}

	public ExEntrust() {
		super();
	}




	public BigDecimal getTransactionFeePlat() {
		return transactionFeePlat;
	}

	public void setTransactionFeePlat(BigDecimal transactionFeePlat) {
		this.transactionFeePlat = transactionFeePlat;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public BigDecimal getTransactionFeeRateDiscount() {
		return transactionFeeRateDiscount;
	}

	public void setTransactionFeeRateDiscount(BigDecimal transactionFeeRateDiscount) {
		this.transactionFeeRateDiscount = transactionFeeRateDiscount;
	}

	public String getInEntrustNum() {
		return inEntrustNum;
	}

	public void setInEntrustNum(String inEntrustNum) {
		this.inEntrustNum = inEntrustNum;
	}

	public Integer getIsType() {
		return isType;
	}

	public void setIsType(Integer isType) {
		this.isType = isType;
	}

	@Override
	public String toString() {
		return "EcEntrust [id=" + id + ", entrustNum=" + entrustNum + ", customerId=" + customerId + ", accountId=" + accountId + ", coinCode=" + coinCode + ", type=" + type + ", status=" + status + ", entrustPrice=" + entrustPrice + ", entrustNum=" + entrustCount + ", entrustTime=" + entrustTime + ", entrustWay=" + entrustWay + ", surplusEntrustNum=" + surplusEntrustCount + ", source=" + source + ", transactionFee=" + transactionFee + ", transactionSum=" + transactionSum + ", processedPrice=" + processedPrice + ", entrustSum=" + entrustSum + ", transactionFeeRate=" + transactionFeeRate + "]";
	}

}
