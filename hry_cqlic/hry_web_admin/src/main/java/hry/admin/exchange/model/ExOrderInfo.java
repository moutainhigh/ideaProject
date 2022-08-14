/**
 * Copyright:   
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-13 11:26:42 
 */
package hry.admin.exchange.model;


import hry.admin.customer.model.AppPersonInfo;
import hry.bean.BaseModel;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p> ExOrderInfo </p>
 * @author:         liushilei
 * @Date :          2018-06-13 11:26:42  
 */
@Table(name="ex_order_info")
public class ExOrderInfo extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "orderNum")
	private String orderNum;  //
	
	@Column(name= "coinCode")
	private String coinCode;  //
	
	@Column(name= "transactionPrice")
	private BigDecimal transactionPrice;  //
	
	@Column(name= "transactionCount")
	private BigDecimal transactionCount;  //
	
	@Column(name= "transactionTime")
	private Date transactionTime;  //
	
	@Column(name= "transactionSum")
	private BigDecimal transactionSum;  //
	
	@Column(name= "transactionBuyFee")
	private BigDecimal transactionBuyFee;  //
	
	@Column(name= "transactionSellFee")
	private BigDecimal transactionSellFee;  //
	
	@Column(name= "buyEntrustNum")
	private String buyEntrustNum;  //
	
	@Column(name= "sellEntrustNum")
	private String sellEntrustNum;  //
	
	@Column(name= "buyCustomId")
	private Long buyCustomId;  //
	
	@Column(name= "sellCustomId")
	private Long sellCustomId;  //
	
	@Column(name= "saasId")
	private String saasId;  //
	
	@Column(name= "transactionBuyFeeRate")
	private BigDecimal transactionBuyFeeRate;  //
	
	@Column(name= "transactionSellFeeRate")
	private BigDecimal transactionSellFeeRate;  //
	
	@Column(name= "productName")
	private String productName;  //
	
	@Column(name= "currencyType")
	private String currencyType;  //
	
	@Column(name= "website")
	private String website;  //
	
	@Column(name= "orderTimestapm")
	private Long orderTimestapm;  //
	
	@Column(name= "inOrOutTransaction")
	private String inOrOutTransaction;  //
	
	@Column(name= "buyUserCode")
	private String buyUserCode;  //
	
	@Column(name= "sellUserCode")
	private String sellUserCode;  //
	
	@Column(name= "sellUserName")
	private String sellUserName;  //
	
	@Column(name= "buyUserName")
	private String buyUserName;  //
	
	@Column(name= "type")
	private Integer type;  //
	
	@Column(name= "customerId")
	private Long customerId;  //
	
	@Column(name= "userCode")
	private String userCode;  //
	
	@Column(name= "userName")
	private String userName;  //
	
	@Column(name= "transactionFee")
	private BigDecimal transactionFee;  //
	
	@Column(name= "transactionFeeRate")
	private BigDecimal transactionFeeRate;  //
	
	@Column(name= "entrustNum")
	private String entrustNum;  //
	
	@Column(name= "profitandlossMoney")
	private BigDecimal profitandlossMoney;  //
	
	@Column(name= "trueName")
	private String trueName;  //
	
	@Column(name= "buyTrueName")
	private String buyTrueName;  //
	
	@Column(name= "sellTrueName")
	private String sellTrueName;  //
	
	@Column(name= "fixPriceCoinCode")
	private String fixPriceCoinCode;  //
	
	@Column(name= "fixPriceType")
	private Integer fixPriceType;  //
	
	@Column(name= "isCulAtferMoney")
	private Integer isCulAtferMoney;  //

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

	@Transient //不与数据库映射字段
	@Column(name = "keepDecimalForCoin")
	private Integer keepDecimalForCoin;

	@Transient //不与数据库映射字段
	@Column(name = "keepDecimalForCurrency")
	private Integer keepDecimalForCurrency;

	@Transient
	private AppPersonInfo buyPersonInfo;

	@Transient
	private AppPersonInfo sellPersonInfo;

	public Integer getKeepDecimalForCoin() {
		return keepDecimalForCoin;
	}

	public void setKeepDecimalForCoin(Integer keepDecimalForCoin) {
		this.keepDecimalForCoin = keepDecimalForCoin;
	}

	public Integer getKeepDecimalForCurrency() {
		return keepDecimalForCurrency;
	}

	public void setKeepDecimalForCurrency(Integer keepDecimalForCurrency) {
		this.keepDecimalForCurrency = keepDecimalForCurrency;
	}

	public AppPersonInfo getSellPersonInfo() {
		return sellPersonInfo;
	}

	public void setSellPersonInfo(AppPersonInfo sellPersonInfo) {
		this.sellPersonInfo = sellPersonInfo;
	}

	public AppPersonInfo getBuyPersonInfo() {
		return buyPersonInfo;
	}

	public void setBuyPersonInfo(AppPersonInfo buyPersonInfo) {
		this.buyPersonInfo = buyPersonInfo;
	}

	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Long 
	 * @Date :   2018-06-13 11:26:42    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-06-13 11:26:42   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 11:26:42    
	 */
	public String getOrderNum() {
		return orderNum;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param orderNum
	 * @return:  void 
	 * @Date :   2018-06-13 11:26:42   
	 */
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 11:26:42    
	 */
	public String getCoinCode() {
		return coinCode;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param coinCode
	 * @return:  void 
	 * @Date :   2018-06-13 11:26:42   
	 */
	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  BigDecimal 
	 * @Date :   2018-06-13 11:26:42    
	 */
	public BigDecimal getTransactionPrice() {
		return transactionPrice;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param transactionPrice
	 * @return:  void 
	 * @Date :   2018-06-13 11:26:42   
	 */
	public void setTransactionPrice(BigDecimal transactionPrice) {
		this.transactionPrice = transactionPrice;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  BigDecimal 
	 * @Date :   2018-06-13 11:26:42    
	 */
	public BigDecimal getTransactionCount() {
		return transactionCount;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param transactionCount
	 * @return:  void 
	 * @Date :   2018-06-13 11:26:42   
	 */
	public void setTransactionCount(BigDecimal transactionCount) {
		this.transactionCount = transactionCount;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Date 
	 * @Date :   2018-06-13 11:26:42    
	 */
	public Date getTransactionTime() {
		return transactionTime;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param transactionTime
	 * @return:  void 
	 * @Date :   2018-06-13 11:26:42   
	 */
	public void setTransactionTime(Date transactionTime) {
		this.transactionTime = transactionTime;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  BigDecimal 
	 * @Date :   2018-06-13 11:26:42    
	 */
	public BigDecimal getTransactionSum() {
		return transactionSum;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param transactionSum
	 * @return:  void 
	 * @Date :   2018-06-13 11:26:42   
	 */
	public void setTransactionSum(BigDecimal transactionSum) {
		this.transactionSum = transactionSum;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  BigDecimal 
	 * @Date :   2018-06-13 11:26:42    
	 */
	public BigDecimal getTransactionBuyFee() {
		return transactionBuyFee;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param transactionBuyFee
	 * @return:  void 
	 * @Date :   2018-06-13 11:26:42   
	 */
	public void setTransactionBuyFee(BigDecimal transactionBuyFee) {
		this.transactionBuyFee = transactionBuyFee;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  BigDecimal 
	 * @Date :   2018-06-13 11:26:42    
	 */
	public BigDecimal getTransactionSellFee() {
		return transactionSellFee;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param transactionSellFee
	 * @return:  void 
	 * @Date :   2018-06-13 11:26:42   
	 */
	public void setTransactionSellFee(BigDecimal transactionSellFee) {
		this.transactionSellFee = transactionSellFee;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 11:26:42    
	 */
	public String getBuyEntrustNum() {
		return buyEntrustNum;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param buyEntrustNum
	 * @return:  void 
	 * @Date :   2018-06-13 11:26:42   
	 */
	public void setBuyEntrustNum(String buyEntrustNum) {
		this.buyEntrustNum = buyEntrustNum;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 11:26:42    
	 */
	public String getSellEntrustNum() {
		return sellEntrustNum;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param sellEntrustNum
	 * @return:  void 
	 * @Date :   2018-06-13 11:26:42   
	 */
	public void setSellEntrustNum(String sellEntrustNum) {
		this.sellEntrustNum = sellEntrustNum;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Long 
	 * @Date :   2018-06-13 11:26:42    
	 */
	public Long getBuyCustomId() {
		return buyCustomId;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param buyCustomId
	 * @return:  void 
	 * @Date :   2018-06-13 11:26:42   
	 */
	public void setBuyCustomId(Long buyCustomId) {
		this.buyCustomId = buyCustomId;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Long 
	 * @Date :   2018-06-13 11:26:42    
	 */
	public Long getSellCustomId() {
		return sellCustomId;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param sellCustomId
	 * @return:  void 
	 * @Date :   2018-06-13 11:26:42   
	 */
	public void setSellCustomId(Long sellCustomId) {
		this.sellCustomId = sellCustomId;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 11:26:42    
	 */
	public String getSaasId() {
		return saasId;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param saasId
	 * @return:  void 
	 * @Date :   2018-06-13 11:26:42   
	 */
	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  BigDecimal 
	 * @Date :   2018-06-13 11:26:42    
	 */
	public BigDecimal getTransactionBuyFeeRate() {
		return transactionBuyFeeRate;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param transactionBuyFeeRate
	 * @return:  void 
	 * @Date :   2018-06-13 11:26:42   
	 */
	public void setTransactionBuyFeeRate(BigDecimal transactionBuyFeeRate) {
		this.transactionBuyFeeRate = transactionBuyFeeRate;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  BigDecimal 
	 * @Date :   2018-06-13 11:26:42    
	 */
	public BigDecimal getTransactionSellFeeRate() {
		return transactionSellFeeRate;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param transactionSellFeeRate
	 * @return:  void 
	 * @Date :   2018-06-13 11:26:42   
	 */
	public void setTransactionSellFeeRate(BigDecimal transactionSellFeeRate) {
		this.transactionSellFeeRate = transactionSellFeeRate;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 11:26:42    
	 */
	public String getProductName() {
		return productName;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param productName
	 * @return:  void 
	 * @Date :   2018-06-13 11:26:42   
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 11:26:42    
	 */
	public String getCurrencyType() {
		return currencyType;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param currencyType
	 * @return:  void 
	 * @Date :   2018-06-13 11:26:42   
	 */
	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 11:26:42    
	 */
	public String getWebsite() {
		return website;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param website
	 * @return:  void 
	 * @Date :   2018-06-13 11:26:42   
	 */
	public void setWebsite(String website) {
		this.website = website;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Long 
	 * @Date :   2018-06-13 11:26:42    
	 */
	public Long getOrderTimestapm() {
		return orderTimestapm;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param orderTimestapm
	 * @return:  void 
	 * @Date :   2018-06-13 11:26:42   
	 */
	public void setOrderTimestapm(Long orderTimestapm) {
		this.orderTimestapm = orderTimestapm;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 11:26:42    
	 */
	public String getInOrOutTransaction() {
		return inOrOutTransaction;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param inOrOutTransaction
	 * @return:  void 
	 * @Date :   2018-06-13 11:26:42   
	 */
	public void setInOrOutTransaction(String inOrOutTransaction) {
		this.inOrOutTransaction = inOrOutTransaction;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 11:26:42    
	 */
	public String getBuyUserCode() {
		return buyUserCode;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param buyUserCode
	 * @return:  void 
	 * @Date :   2018-06-13 11:26:42   
	 */
	public void setBuyUserCode(String buyUserCode) {
		this.buyUserCode = buyUserCode;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 11:26:42    
	 */
	public String getSellUserCode() {
		return sellUserCode;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param sellUserCode
	 * @return:  void 
	 * @Date :   2018-06-13 11:26:42   
	 */
	public void setSellUserCode(String sellUserCode) {
		this.sellUserCode = sellUserCode;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 11:26:42    
	 */
	public String getSellUserName() {
		return sellUserName;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param sellUserName
	 * @return:  void 
	 * @Date :   2018-06-13 11:26:42   
	 */
	public void setSellUserName(String sellUserName) {
		this.sellUserName = sellUserName;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 11:26:42    
	 */
	public String getBuyUserName() {
		return buyUserName;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param buyUserName
	 * @return:  void 
	 * @Date :   2018-06-13 11:26:42   
	 */
	public void setBuyUserName(String buyUserName) {
		this.buyUserName = buyUserName;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Integer 
	 * @Date :   2018-06-13 11:26:42    
	 */
	public Integer getType() {
		return type;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param type
	 * @return:  void 
	 * @Date :   2018-06-13 11:26:42   
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Long 
	 * @Date :   2018-06-13 11:26:42    
	 */
	public Long getCustomerId() {
		return customerId;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param customerId
	 * @return:  void 
	 * @Date :   2018-06-13 11:26:42   
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 11:26:42    
	 */
	public String getUserCode() {
		return userCode;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param userCode
	 * @return:  void 
	 * @Date :   2018-06-13 11:26:42   
	 */
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 11:26:42    
	 */
	public String getUserName() {
		return userName;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param userName
	 * @return:  void 
	 * @Date :   2018-06-13 11:26:42   
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  BigDecimal 
	 * @Date :   2018-06-13 11:26:42    
	 */
	public BigDecimal getTransactionFee() {
		return transactionFee;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param transactionFee
	 * @return:  void 
	 * @Date :   2018-06-13 11:26:42   
	 */
	public void setTransactionFee(BigDecimal transactionFee) {
		this.transactionFee = transactionFee;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  BigDecimal 
	 * @Date :   2018-06-13 11:26:42    
	 */
	public BigDecimal getTransactionFeeRate() {
		return transactionFeeRate;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param transactionFeeRate
	 * @return:  void 
	 * @Date :   2018-06-13 11:26:42   
	 */
	public void setTransactionFeeRate(BigDecimal transactionFeeRate) {
		this.transactionFeeRate = transactionFeeRate;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 11:26:42    
	 */
	public String getEntrustNum() {
		return entrustNum;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param entrustNum
	 * @return:  void 
	 * @Date :   2018-06-13 11:26:42   
	 */
	public void setEntrustNum(String entrustNum) {
		this.entrustNum = entrustNum;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  BigDecimal 
	 * @Date :   2018-06-13 11:26:42    
	 */
	public BigDecimal getProfitandlossMoney() {
		return profitandlossMoney;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param profitandlossMoney
	 * @return:  void 
	 * @Date :   2018-06-13 11:26:42   
	 */
	public void setProfitandlossMoney(BigDecimal profitandlossMoney) {
		this.profitandlossMoney = profitandlossMoney;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 11:26:42    
	 */
	public String getTrueName() {
		return trueName;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param trueName
	 * @return:  void 
	 * @Date :   2018-06-13 11:26:42   
	 */
	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 11:26:42    
	 */
	public String getBuyTrueName() {
		return buyTrueName;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param buyTrueName
	 * @return:  void 
	 * @Date :   2018-06-13 11:26:42   
	 */
	public void setBuyTrueName(String buyTrueName) {
		this.buyTrueName = buyTrueName;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 11:26:42    
	 */
	public String getSellTrueName() {
		return sellTrueName;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param sellTrueName
	 * @return:  void 
	 * @Date :   2018-06-13 11:26:42   
	 */
	public void setSellTrueName(String sellTrueName) {
		this.sellTrueName = sellTrueName;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 11:26:42    
	 */
	public String getFixPriceCoinCode() {
		return fixPriceCoinCode;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param fixPriceCoinCode
	 * @return:  void 
	 * @Date :   2018-06-13 11:26:42   
	 */
	public void setFixPriceCoinCode(String fixPriceCoinCode) {
		this.fixPriceCoinCode = fixPriceCoinCode;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Integer 
	 * @Date :   2018-06-13 11:26:42    
	 */
	public Integer getFixPriceType() {
		return fixPriceType;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param fixPriceType
	 * @return:  void 
	 * @Date :   2018-06-13 11:26:42   
	 */
	public void setFixPriceType(Integer fixPriceType) {
		this.fixPriceType = fixPriceType;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Integer 
	 * @Date :   2018-06-13 11:26:42    
	 */
	public Integer getIsCulAtferMoney() {
		return isCulAtferMoney;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param isCulAtferMoney
	 * @return:  void 
	 * @Date :   2018-06-13 11:26:42   
	 */
	public void setIsCulAtferMoney(Integer isCulAtferMoney) {
		this.isCulAtferMoney = isCulAtferMoney;
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
}
