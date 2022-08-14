package hry.manage.remote.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 成交记录
 * @author CHINA_LSL
 *
 */
public class Order implements Serializable{


	// 交易订单号
	private String orderNum;
	// 虚拟币名称
	private String productName;
	// 币的代码
	private String coinCode;
	// 成交价（单价）
	private BigDecimal transactionPrice;
	// 成交数量
	private BigDecimal transactionCount;
	// 交易总金额
	private BigDecimal transactionSum;
	// 交易手续费
	private BigDecimal transactionFee;
	// 成交时间
	private Date transactionTime;
	//成交时间戳
	private Long  transactionTime_long;

	// TYPE 类型 1:买 ,    2:卖
	private int type;

	private Long customerId;

	private String userName;

	private String fixPriceCoinCode;

	private String feeCoin; // 手续费币种
	private String coin;
	private String entrustNum; //委托单号





	public String getCoin() {
		return coin;
	}
	public void setCoin(String coin) {
		this.coin = coin;
	}
	public String getFixPriceCoinCode() {
		return fixPriceCoinCode;
	}
	public void setFixPriceCoinCode(String fixPriceCoinCode) {
		this.fixPriceCoinCode = fixPriceCoinCode;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
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
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
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
	public BigDecimal getTransactionSum() {
		return transactionSum;
	}
	public void setTransactionSum(BigDecimal transactionSum) {
		this.transactionSum = transactionSum;
	}
	public Date getTransactionTime() {
		return transactionTime;
	}
	public void setTransactionTime(Date transactionTime) {
		this.transactionTime = transactionTime;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public Long getTransactionTime_long() {
		return transactionTime_long;
	}
	public void setTransactionTime_long(Long transactionTime_long) {
		this.transactionTime_long = transactionTime_long;
	}
	public String getFeeCoin () {
		return feeCoin;
	}

	public String getEntrustNum () {
		return entrustNum;
	}

	public void setEntrustNum (String entrustNum) {
		this.entrustNum = entrustNum;
	}

	public void setFeeCoin (String feeCoin) {
		this.feeCoin = feeCoin;
	}
}
