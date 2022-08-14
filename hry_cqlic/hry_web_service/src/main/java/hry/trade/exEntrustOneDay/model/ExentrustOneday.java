/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      shangxl
 * @version:     V1.0 
 * @Date:        2017-06-14 17:35:13 
 */
package hry.trade.exEntrustOneDay.model;

import static javax.persistence.GenerationType.IDENTITY;
import hry.core.mvc.model.BaseModel;

import java.util.Date;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> ExentrustOneday </p>
 * @author:         shangxl
 * @Date :          2017-06-14 17:35:13  
 */
@Table(name="ex_entrust_oneday")
public class ExentrustOneday extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //id
	
	@Column(name= "entrustNum")
	private String entrustNum;  //委托单号
	
	@Column(name= "customerId")
	private Long customerId;  //委托人
	
	@Column(name= "accountId")
	private Long accountId;  //委托人的币账户id
	
	@Column(name= "coinCode")
	private String coinCode;  //币的代码
	
	@Column(name= "type")
	private Integer type;  //1 买 ,2卖
	
	@Column(name= "status")
	private Integer status;  //0未成交　1部分成交　2已完成　 3部分成交已撤销 4已撤销   7队列中 
	
	@Column(name= "entrustPrice")
	private BigDecimal entrustPrice;  //委托价格
	
	@Column(name= "entrustCount")
	private BigDecimal entrustCount;  //委托数量(市价买单，代表买入金额)
	
	@Column(name= "entrustSum")
	private BigDecimal entrustSum;  //entrustSum
	
	@Column(name= "entrustTime")
	private Date entrustTime;  //委托时间
	
	@Column(name= "entrustWay")
	private Integer entrustWay;  //委托方式(1限价,2市价)
	
	@Column(name= "surplusEntrustCount")
	private BigDecimal surplusEntrustCount;  //剩余委托数量(市价买单，代表剩余买入金额)
	
	@Column(name= "source")
	private Integer source;  //来源(1人工,2机器人)
	
	// 手机号
	@Column(name = "userName")
	private String userName;
	
	@Column(name= "customerType")
	private Integer customerType;  //客户类型accuountTyp甲类账户1，乙类账号2，丙类账户3
	
	@Column(name= "floatUpPrice")
	private BigDecimal floatUpPrice;  ////可以向上线浮动的价格
	
	@Column(name= "floatDownPrice")
	private BigDecimal floatDownPrice;  ////可以向下线浮动的价格
	

	@Column(name= "matchPriority")
	private Integer matchPriority;  ////匹配优先级 普通1，平仓5
	
	@Column(name= "currencyType")
	private String currencyType;  //币种（CNY）
	
	@Column(name= "website")
	private String website;  //website
	

	@Column(name= "entrustPriceDouble")
	private BigDecimal entrustPriceDouble;  //entrustPriceDouble
	// 成交的的总手续费
	@Column(name = "transactionFee")
	private BigDecimal transactionFee;
	// 成交的金额
	@Column(name = "transactionSum")
	private BigDecimal transactionSum;
	// 成交的的总手续费
	@Column(name = "transactionFeeRate")
	private BigDecimal transactionFeeRate;
	@Column(name= "fixPriceType")
	private Integer fixPriceType;  //0真实货币1虚拟币
	// 币的代码
	@Column(name = "fixPriceCoinCode")
	private String fixPriceCoinCode;  //定价币种
	
	
	
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public BigDecimal getTransactionFeeRate() {
		return transactionFeeRate;
	}
	public void setTransactionFeeRate(BigDecimal transactionFeeRate) {
		this.transactionFeeRate = transactionFeeRate;
	}
	public BigDecimal getTransactionFee() {
		return transactionFee;
	}
	public void setTransactionFee(BigDecimal transactionFee) {
		this.transactionFee = transactionFee;
	}
	public BigDecimal getTransactionSum() {
		return transactionSum;
	}
	public void setTransactionSum(BigDecimal transactionSum) {
		this.transactionSum = transactionSum;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public String getCoinCode() {
		return coinCode;
	}
	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
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
	public Integer getSource() {
		return source;
	}
	public void setSource(Integer source) {
		this.source = source;
	}

	public Integer getCustomerType() {
		return customerType;
	}
	public void setCustomerType(Integer customerType) {
		this.customerType = customerType;
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
	public Integer getMatchPriority() {
		return matchPriority;
	}
	public void setMatchPriority(Integer matchPriority) {
		this.matchPriority = matchPriority;
	}
	public String getCurrencyType() {
		return currencyType;
	}
	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public BigDecimal getEntrustPriceDouble() {
		return entrustPriceDouble;
	}
	public void setEntrustPriceDouble(BigDecimal entrustPriceDouble) {
		this.entrustPriceDouble = entrustPriceDouble;
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
	

}
