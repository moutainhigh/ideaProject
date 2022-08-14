/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Wu Shuiming
 * @version:      V1.0 
 * @Date:        2016年3月28日 下午4:25:12
 */
package hry.trade.entrust.model;

import hry.core.mvc.model.BaseExModel;
import hry.customer.person.model.AppPersonInfo;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * <p>
 * TODO
 * </p>
 * 
 * @author: Wu Shuiming
 * @Date : 2016年3月28日 下午4:25:12
 */
@SuppressWarnings("serial")
@Table(name = "ex_order_info")
public class ExOrderInfo extends BaseExModel {

	// 主键
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	// 交易订单号
	@Column(name = "orderNum")
	private String orderNum;
	
	// 虚拟币名称
	@Column(name = "productName")
	private String productName;
	
	// 币的代码
	@Column(name = "coinCode")
	private String coinCode;
	// 成交价（单价）
	@Column(name = "transactionPrice")
	private BigDecimal transactionPrice;
	// 成交数量
	@Column(name = "transactionCount")
	private BigDecimal transactionCount;
	// 交易总金额
	@Column(name = "transactionSum")
	private BigDecimal transactionSum;
	// 成交时间
	@Column(name = "transactionTime")
	private Date transactionTime;
	
	// TYPE 类型 1:买 ,    2:卖
	@Column(name = "type")
	private Integer type;
	// 卖id
	@Column(name = "customerId")
	private Long customerId;
	// 编号
	@Column(name = "userCode")
	private String userCode;
	// 编号
	@Column(name = "userName")
	private String userName;
	@Column(name = "trueName")
	private String trueName;
	// 交易手续费
	@Column(name = "transactionFee")
	private BigDecimal transactionFee;
	@Column(name = "transactionFeeRate")
	private BigDecimal transactionFeeRate;

	// 委托单号
	@Column(name = "entrustNum")
	private String entrustNum;

	//卖家平台币手续费
	@Column(name = "transactionSellFeePlat")
	private BigDecimal transactionSellFeePlat;
	//买家平台币手续费
	@Column(name = "transactionBuyFeePlat")
	private BigDecimal transactionBuyFeePlat;
	@Column(name = "buyPlatCoin")
	private String buyPlatCoin; //平台币
	@Column(name = "sellPlatCoin")
	private String sellPlatCoin; //平台币

	//当前买方交易币/平台币的当前价格
	@Column(name = "coinCodePrice")
	private BigDecimal coinCodePrice;
	//当前卖方定价币/平台币的当前价格
	@Column(name = "fixPriceCoinCodePrice")
	private BigDecimal fixPriceCoinCodePrice;

	@Column(name = "transactionBuyFeeRate")
	private BigDecimal transactionBuyFeeRate;
	// 交易手续费(本订单买方手续费)
	@Column(name = "transactionBuyFee")
	private BigDecimal transactionBuyFee;
	@Column(name = "transactionSellFeeRate")
	private BigDecimal transactionSellFeeRate;
	// 交易手续费(本订单卖方手续费)
	@Column(name = "transactionSellFee")
	private BigDecimal transactionSellFee;
	// 委托单号（买方）
	@Column(name = "buyEntrustNum")
	private String buyEntrustNum;
	// 委托单号（卖方）
	@Column(name = "sellEntrustNum")
	private String sellEntrustNum;
	@Column(name = "sellTrueName")
	private String sellTrueName;
	// 买方的id
	@Column(name = "buyCustomId")
	private Long buyCustomId;
	// 买方客户编号
	@Column(name = "buyUserCode")
	private String buyUserCode;
	// 买方客户编号
	@Column(name = "buyUserName")
	private String buyUserName;
	
	@Column(name = "buyTrueName")
	private String buyTrueName;
	// 卖方的id
	@Column(name = "sellCustomId")
	private Long sellCustomId;
	// 卖方编号
	@Column(name = "sellUserCode")
	private String sellUserCode;
	// 卖方编号
	@Column(name = "sellUserName")
	private String sellUserName;
	//成交时间戳
	@Column(name = "orderTimestapm")
	private Long orderTimestapm;
	@Column(name= "fixPriceCoinCode")
	private String fixPriceCoinCode;  //定价币种
	@Column(name= "fixPriceType")
	private Integer fixPriceType;  //0真实货币1虚拟币
	//数据库没有
	@Transient
	private String recordTypeName;
	//内盘buy外盘sell
	@Column(name = "inOrOutTransaction")
	private String inOrOutTransaction;
	
	// 
	@Column(name = "profitandlossMoney")
	private BigDecimal profitandlossMoney;
	
	//是否算过佣金和我方手续费0没有收1已经收了
	@Column(name = "isCulAtferMoney")
	private Integer isCulAtferMoney;
	
	//是否回购 0未回购 1已 回购 
	@Column(name = "isBuyBack")
	private Integer isBuyBack;
	
	
	@Transient
	private AppPersonInfo buyPersonInfo;
	
	@Transient
	private AppPersonInfo sellPersonInfo;
	
	
	public AppPersonInfo getBuyPersonInfo() {
		return buyPersonInfo;
	}

	public void setBuyPersonInfo(AppPersonInfo buyPersonInfo) {
		this.buyPersonInfo = buyPersonInfo;
	}

	public AppPersonInfo getSellPersonInfo() {
		return sellPersonInfo;
	}


	public void setSellPersonInfo(AppPersonInfo sellPersonInfo) {
		this.sellPersonInfo = sellPersonInfo;
	}


	public Integer getIsCulAtferMoney() {
		return isCulAtferMoney;
	}


	public void setIsCulAtferMoney(Integer isCulAtferMoney) {
		this.isCulAtferMoney = isCulAtferMoney;
	}


	public String getFixPriceCoinCode() {
		return fixPriceCoinCode;
	}


	public void setFixPriceCoinCode(String fixPriceCoinCode) {
		this.fixPriceCoinCode = fixPriceCoinCode;
	}


	public Integer getFixPriceType() {
		return fixPriceType;
	}


	public void setFixPriceType(Integer fixPriceType) {
		this.fixPriceType = fixPriceType;
	}


	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getProfitandlossMoney() {
		return profitandlossMoney;
	}


	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setProfitandlossMoney(BigDecimal profitandlossMoney) {
		this.profitandlossMoney = profitandlossMoney;
	}


	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getEntrustNum() {
		return entrustNum;
	}


	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setEntrustNum(String entrustNum) {
		this.entrustNum = entrustNum;
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
	 * @return:     String
	 */
	public String getUserCode() {
		return userCode;
	}


	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setUserCode(String userCode) {
		this.userCode = userCode;
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
	 * @return:     BigDecimal
	 */
	public BigDecimal getTransactionFee() {
		return transactionFee;
	}


	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setTransactionFee(BigDecimal transactionFee) {
		this.transactionFee = transactionFee;
	}


	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getTransactionFeeRate() {
		return transactionFeeRate;
	}


	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setTransactionFeeRate(BigDecimal transactionFeeRate) {
		this.transactionFeeRate = transactionFeeRate;
	}


	/**
	 * <p> TODO</p>
	 * @return:     Integer
	 */
	public Integer getType() {
		return type;
	}


	/** 
	 * <p> TODO</p>
	 * @return: Integer
	 */
	public void setType(Integer type) {
		this.type = type;
	}


	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getBuyUserName() {
		return buyUserName;
	}


	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setBuyUserName(String buyUserName) {
		this.buyUserName = buyUserName;
	}


	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getSellUserName() {
		return sellUserName;
	}


	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setSellUserName(String sellUserName) {
		this.sellUserName = sellUserName;
	}


	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getInOrOutTransaction() {
		return inOrOutTransaction;
	}


	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setInOrOutTransaction(String inOrOutTransaction) {
		this.inOrOutTransaction = inOrOutTransaction;
	}


	/**
	 * <p> TODO</p>
	 * @return:     Long
	 */
	public Long getOrderTimestapm() {
		return orderTimestapm;
	}


	/** 
	 * <p> TODO</p>
	 * @return: Long
	 */
	public void setOrderTimestapm(Long orderTimestapm) {
		this.orderTimestapm = orderTimestapm;
	}


	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getRecordTypeName() {
		return recordTypeName;
	}


	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setRecordTypeName(String recordTypeName) {
		this.recordTypeName = recordTypeName;
	}


	public ExOrderInfo(Long id, String orderNum, String coinCode,
			BigDecimal transactionPrice, BigDecimal transactionCount, Date transactionTime,
			BigDecimal transactionSum,BigDecimal transactionBuyFee,BigDecimal transactionSellFee,
			String buyEntrustNum,String sellEntrustNum,Long buyCustomId,Long sellCustomId,BigDecimal transactionBuyFeeRate,BigDecimal transactionSellFeeRate) {
		super();
		this.id = id;
		this.orderNum = orderNum;
		this.coinCode = coinCode;
		this.transactionPrice = transactionPrice;
		this.transactionCount = transactionCount;
		this.transactionTime = transactionTime;
		this.transactionSum = transactionSum;
		this.transactionBuyFee = transactionBuyFee;
		this.transactionSellFee = transactionSellFee;
		this.buyEntrustNum = buyEntrustNum;
		this.sellEntrustNum = sellEntrustNum;
		this.buyCustomId = buyCustomId;
		this.sellCustomId = sellCustomId;
		this.transactionBuyFeeRate = transactionBuyFeeRate;
		this.transactionSellFeeRate = transactionSellFeeRate;
	}


	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getTransactionBuyFeeRate() {
		return transactionBuyFeeRate;
	}


	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setTransactionBuyFeeRate(BigDecimal transactionBuyFeeRate) {
		this.transactionBuyFeeRate = transactionBuyFeeRate;
	}


	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getTransactionSellFeeRate() {
		return transactionSellFeeRate;
	}


	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setTransactionSellFeeRate(BigDecimal transactionSellFeeRate) {
		this.transactionSellFeeRate = transactionSellFeeRate;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}

	
	public String getOrderNum() {
		return orderNum;
	}


	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	
	public String getCoinCode() {
		return coinCode;
	}

	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}


	public BigDecimal getTransactionPrice() {
		return transactionPrice;
	}


	public void setTransactionPrice(BigDecimal transactionPrice) {
		this.transactionPrice = transactionPrice;
	}

	
	public BigDecimal getTransactionCount() {
		return transactionCount;
	}


	public void setTransactionCount(BigDecimal transactionCount) {
		this.transactionCount = transactionCount;
	}


	public Date getTransactionTime() {
		return transactionTime;
	}

	public void setTransactionTime(Date transactionTime) {
		this.transactionTime = transactionTime;
	}


	public BigDecimal getTransactionSum() {
		return transactionSum;
	}

	public void setTransactionSum(BigDecimal transactionSum) {
		this.transactionSum = transactionSum;
	}

	
	public BigDecimal getTransactionBuyFee() {
		return transactionBuyFee;
	}

	
	public void setTransactionBuyFee(BigDecimal transactionBuyFee) {
		this.transactionBuyFee = transactionBuyFee;
	}

	
	public BigDecimal getTransactionSellFee() {
		return transactionSellFee;
	}


	public void setTransactionSellFee(BigDecimal transactionSellFee) {
		this.transactionSellFee = transactionSellFee;
	}


	public String getBuyEntrustNum() {
		return buyEntrustNum;
	}

	
	public void setBuyEntrustNum(String buyEntrustNum) {
		this.buyEntrustNum = buyEntrustNum;
	}

	
	public String getSellEntrustNum() {
		return sellEntrustNum;
	}

	
	public void setSellEntrustNum(String sellEntrustNum) {
		this.sellEntrustNum = sellEntrustNum;
	}

	public Long getBuyCustomId() {
		return buyCustomId;
	}


	public void setBuyCustomId(Long buyCustomId) {
		this.buyCustomId = buyCustomId;
	}

	
	public Long getSellCustomId() {
		return sellCustomId;
	}


	public void setSellCustomId(Long sellCustomId) {
		this.sellCustomId = sellCustomId;
	}

	public ExOrderInfo() {
		super();
	}

	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getBuyUserCode() {
		return buyUserCode;
	}


	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setBuyUserCode(String buyUserCode) {
		this.buyUserCode = buyUserCode;
	}


	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getSellUserCode() {
		return sellUserCode;
	}


	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setSellUserCode(String sellUserCode) {
		this.sellUserCode = sellUserCode;
	}


	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getTrueName() {
		return trueName;
	}


	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}


	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getSellTrueName() {
		return sellTrueName;
	}


	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setSellTrueName(String sellTrueName) {
		this.sellTrueName = sellTrueName;
	}


	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getBuyTrueName() {
		return buyTrueName;
	}


	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setBuyTrueName(String buyTrueName) {
		this.buyTrueName = buyTrueName;
	}

	public BigDecimal getTransactionSellFeePlat () {
		return transactionSellFeePlat;
	}

	public void setTransactionSellFeePlat (BigDecimal transactionSellFeePlat) {
		this.transactionSellFeePlat = transactionSellFeePlat;
	}

	public BigDecimal getTransactionBuyFeePlat () {
		return transactionBuyFeePlat;
	}

	public void setTransactionBuyFeePlat (BigDecimal transactionBuyFeePlat) {
		this.transactionBuyFeePlat = transactionBuyFeePlat;
	}

	public String getBuyPlatCoin () {
		return buyPlatCoin;
	}

	public void setBuyPlatCoin (String buyPlatCoin) {
		this.buyPlatCoin = buyPlatCoin;
	}

	public String getSellPlatCoin () {
		return sellPlatCoin;
	}

	public void setSellPlatCoin (String sellPlatCoin) {
		this.sellPlatCoin = sellPlatCoin;
	}

	public BigDecimal getCoinCodePrice () {
		return coinCodePrice;
	}

	public void setCoinCodePrice (BigDecimal coinCodePrice) {
		this.coinCodePrice = coinCodePrice;
	}

	public BigDecimal getFixPriceCoinCodePrice () {
		return fixPriceCoinCodePrice;
	}

	public void setFixPriceCoinCodePrice (BigDecimal fixPriceCoinCodePrice) {
		this.fixPriceCoinCodePrice = fixPriceCoinCodePrice;
	}
	

	public Integer getIsBuyBack() {
		return isBuyBack;
	}

	public void setIsBuyBack(Integer isBuyBack) {
		this.isBuyBack = isBuyBack;
	}

	@Override
	public String toString () {
		return "ExOrderInfo{" +
				"id=" + id +
				", orderNum='" + orderNum + '\'' +
				", productName='" + productName + '\'' +
				", coinCode='" + coinCode + '\'' +
				", transactionPrice=" + transactionPrice +
				", transactionCount=" + transactionCount +
				", transactionSum=" + transactionSum +
				", transactionTime=" + transactionTime +
				", type=" + type +
				", customerId=" + customerId +
				", userCode='" + userCode + '\'' +
				", userName='" + userName + '\'' +
				", trueName='" + trueName + '\'' +
				", transactionFee=" + transactionFee +
				", transactionFeeRate=" + transactionFeeRate +
				", entrustNum='" + entrustNum + '\'' +
				", transactionSellFeePlat=" + transactionSellFeePlat +
				", transactionBuyFeePlat=" + transactionBuyFeePlat +
				", buyPlatCoin='" + buyPlatCoin + '\'' +
				", sellPlatCoin='" + sellPlatCoin + '\'' +
				", coinCodePrice=" + coinCodePrice +
				", fixPriceCoinCodePrice=" + fixPriceCoinCodePrice +
				", transactionBuyFeeRate=" + transactionBuyFeeRate +
				", transactionBuyFee=" + transactionBuyFee +
				", transactionSellFeeRate=" + transactionSellFeeRate +
				", transactionSellFee=" + transactionSellFee +
				", buyEntrustNum='" + buyEntrustNum + '\'' +
				", sellEntrustNum='" + sellEntrustNum + '\'' +
				", sellTrueName='" + sellTrueName + '\'' +
				", buyCustomId=" + buyCustomId +
				", buyUserCode='" + buyUserCode + '\'' +
				", buyUserName='" + buyUserName + '\'' +
				", buyTrueName='" + buyTrueName + '\'' +
				", sellCustomId=" + sellCustomId +
				", sellUserCode='" + sellUserCode + '\'' +
				", sellUserName='" + sellUserName + '\'' +
				", orderTimestapm=" + orderTimestapm +
				", fixPriceCoinCode='" + fixPriceCoinCode + '\'' +
				", fixPriceType=" + fixPriceType +
				", recordTypeName='" + recordTypeName + '\'' +
				", inOrOutTransaction='" + inOrOutTransaction + '\'' +
				", profitandlossMoney=" + profitandlossMoney +
				", isCulAtferMoney=" + isCulAtferMoney +
				", isBuyBack=" + isBuyBack +
				", buyPersonInfo=" + buyPersonInfo +
				", sellPersonInfo=" + sellPersonInfo +
				'}';
	}
}
