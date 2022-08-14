/**
 * Copyright:   
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-13 11:12:40 
 */
package hry.admin.exchange.model;


import hry.admin.customer.model.AppPersonInfo;
import hry.bean.BaseModel;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p> ExEntrust </p>
 * @author:         liushilei
 * @Date :          2018-06-13 11:12:40  
 */
@Table(name="ex_entrust")
public class ExEntrust extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "entrustNum")
	private String entrustNum;  //
	
	@Column(name= "customerId")
	private Long customerId;  //
	
	@Column(name= "accountId")
	private Long accountId;  //
	
	@Column(name= "coinCode")
	private String coinCode;  //
	
	@Column(name= "type")
	private Integer type;  //1 买 ,2卖
	
	@Column(name= "status")
	private Integer status;  //
	
	@Column(name= "entrustPrice")
	private BigDecimal entrustPrice;  //
	
	@Column(name= "entrustCount")
	private BigDecimal entrustCount;  //
	
	@Column(name= "entrustSum")
	private BigDecimal entrustSum;  //
	
	@Column(name= "entrustTime")
	private Date entrustTime;  //
	
	@Column(name= "entrustWay")
	private Integer entrustWay;  //
	
	@Column(name= "surplusEntrustCount")
	private BigDecimal surplusEntrustCount;  //
	
	@Column(name= "source")
	private Integer source;  //
	
	@Column(name= "transactionFee")
	private BigDecimal transactionFee;  //
	
	@Column(name= "transactionSum")
	private BigDecimal transactionSum;  //
	
	@Column(name= "processedPrice")
	private BigDecimal processedPrice;  //
	
	@Column(name= "saasId")
	private String saasId;  //
	
	@Column(name= "transactionFeeRate")
	private BigDecimal transactionFeeRate;  //
	
	@Column(name= "customerType")
	private Integer customerType;  //
	
	@Column(name= "floatUpPrice")
	private BigDecimal floatUpPrice;  //
	
	@Column(name= "floatDownPrice")
	private BigDecimal floatDownPrice;  //
	
	@Column(name= "matchPriority")
	private Integer matchPriority;  //
	
	@Column(name= "currencyType")
	private String currencyType;  //
	
	@Column(name= "website")
	private String website;  //
	
	@Column(name= "userCode")
	private String userCode;  //
	
	@Column(name= "userName")
	private String userName;  //
	
	@Column(name= "trueName")
	private String trueName;  //
	
	@Column(name= "projectType")
	private Integer projectType;  //
	
	@Column(name= "transactionTime")
	private Date transactionTime;  //
	
	@Column(name= "customerIp")
	private String customerIp;  //
	
	@Column(name= "fixPriceType")
	private Integer fixPriceType;  //
	
	@Column(name= "fixPriceCoinCode")
	private String fixPriceCoinCode;  //
	
	@Column(name= "coinAccountId")
	private Long coinAccountId;  //
	
	@Column(name= "surName")
	private String surName;  //

	// 成交的的总手续费率平台币折扣
	@Column(name = "transactionFeeRateDiscount")
	private BigDecimal transactionFeeRateDiscount;
	// 成交的的平台币总手续费
	@Column(name = "transactionFeePlat")
	private BigDecimal transactionFeePlat;
	@Column(name = "isOpenCoinFee")
	private Integer isOpenCoinFee;  //下单：0正常手续费收取1，平台币收取手续费）
	@Column(name = "platCoin")
	private String platCoin; //平台币

	@Transient //不与数据库映射字段
	@Column(name = "keepDecimalForCoin")
	private Integer keepDecimalForCoin;

	@Transient //不与数据库映射字段
	@Column(name = "keepDecimalForCurrency")
	private Integer keepDecimalForCurrency;

	@Column(name = "isType")
	private Integer isType;

	@Transient
	private AppPersonInfo appPersonInfo;

	public Integer getIsType() {
		return isType;
	}

	public void setIsType(Integer isType) {
		this.isType = isType;
	}

	public AppPersonInfo getAppPersonInfo() {
		return appPersonInfo;
	}

	public void setAppPersonInfo(AppPersonInfo appPersonInfo) {
		this.appPersonInfo = appPersonInfo;
	}

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

	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Long 
	 * @Date :   2018-06-13 11:12:40    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-06-13 11:12:40   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 11:12:40    
	 */
	public String getEntrustNum() {
		return entrustNum;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param entrustNum
	 * @return:  void 
	 * @Date :   2018-06-13 11:12:40   
	 */
	public void setEntrustNum(String entrustNum) {
		this.entrustNum = entrustNum;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Long 
	 * @Date :   2018-06-13 11:12:40    
	 */
	public Long getCustomerId() {
		return customerId;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param customerId
	 * @return:  void 
	 * @Date :   2018-06-13 11:12:40   
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Long 
	 * @Date :   2018-06-13 11:12:40    
	 */
	public Long getAccountId() {
		return accountId;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param accountId
	 * @return:  void 
	 * @Date :   2018-06-13 11:12:40   
	 */
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 11:12:40    
	 */
	public String getCoinCode() {
		return coinCode;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param coinCode
	 * @return:  void 
	 * @Date :   2018-06-13 11:12:40   
	 */
	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Integer 
	 * @Date :   2018-06-13 11:12:40    
	 */
	public Integer getType() {
		return type;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param type
	 * @return:  void 
	 * @Date :   2018-06-13 11:12:40   
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Integer 
	 * @Date :   2018-06-13 11:12:40    
	 */
	public Integer getStatus() {
		return status;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param status
	 * @return:  void 
	 * @Date :   2018-06-13 11:12:40   
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  BigDecimal 
	 * @Date :   2018-06-13 11:12:40    
	 */
	public BigDecimal getEntrustPrice() {
		return entrustPrice;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param entrustPrice
	 * @return:  void 
	 * @Date :   2018-06-13 11:12:40   
	 */
	public void setEntrustPrice(BigDecimal entrustPrice) {
		this.entrustPrice = entrustPrice;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  BigDecimal 
	 * @Date :   2018-06-13 11:12:40    
	 */
	public BigDecimal getEntrustCount() {
		return entrustCount;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param entrustCount
	 * @return:  void 
	 * @Date :   2018-06-13 11:12:40   
	 */
	public void setEntrustCount(BigDecimal entrustCount) {
		this.entrustCount = entrustCount;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  BigDecimal 
	 * @Date :   2018-06-13 11:12:40    
	 */
	public BigDecimal getEntrustSum() {
		return entrustSum;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param entrustSum
	 * @return:  void 
	 * @Date :   2018-06-13 11:12:40   
	 */
	public void setEntrustSum(BigDecimal entrustSum) {
		this.entrustSum = entrustSum;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Date 
	 * @Date :   2018-06-13 11:12:40    
	 */
	public Date getEntrustTime() {
		return entrustTime;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param entrustTime
	 * @return:  void 
	 * @Date :   2018-06-13 11:12:40   
	 */
	public void setEntrustTime(Date entrustTime) {
		this.entrustTime = entrustTime;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Integer 
	 * @Date :   2018-06-13 11:12:40    
	 */
	public Integer getEntrustWay() {
		return entrustWay;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param entrustWay
	 * @return:  void 
	 * @Date :   2018-06-13 11:12:40   
	 */
	public void setEntrustWay(Integer entrustWay) {
		this.entrustWay = entrustWay;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  BigDecimal 
	 * @Date :   2018-06-13 11:12:40    
	 */
	public BigDecimal getSurplusEntrustCount() {
		return surplusEntrustCount;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param surplusEntrustCount
	 * @return:  void 
	 * @Date :   2018-06-13 11:12:40   
	 */
	public void setSurplusEntrustCount(BigDecimal surplusEntrustCount) {
		this.surplusEntrustCount = surplusEntrustCount;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Integer 
	 * @Date :   2018-06-13 11:12:40    
	 */
	public Integer getSource() {
		return source;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param source
	 * @return:  void 
	 * @Date :   2018-06-13 11:12:40   
	 */
	public void setSource(Integer source) {
		this.source = source;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  BigDecimal 
	 * @Date :   2018-06-13 11:12:40    
	 */
	public BigDecimal getTransactionFee() {
		return transactionFee;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param transactionFee
	 * @return:  void 
	 * @Date :   2018-06-13 11:12:40   
	 */
	public void setTransactionFee(BigDecimal transactionFee) {
		this.transactionFee = transactionFee;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  BigDecimal 
	 * @Date :   2018-06-13 11:12:40    
	 */
	public BigDecimal getTransactionSum() {
		return transactionSum;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param transactionSum
	 * @return:  void 
	 * @Date :   2018-06-13 11:12:40   
	 */
	public void setTransactionSum(BigDecimal transactionSum) {
		this.transactionSum = transactionSum;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  BigDecimal 
	 * @Date :   2018-06-13 11:12:40    
	 */
	public BigDecimal getProcessedPrice() {
		return processedPrice;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param processedPrice
	 * @return:  void 
	 * @Date :   2018-06-13 11:12:40   
	 */
	public void setProcessedPrice(BigDecimal processedPrice) {
		this.processedPrice = processedPrice;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 11:12:40    
	 */
	public String getSaasId() {
		return saasId;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param saasId
	 * @return:  void 
	 * @Date :   2018-06-13 11:12:40   
	 */
	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  BigDecimal 
	 * @Date :   2018-06-13 11:12:40    
	 */
	public BigDecimal getTransactionFeeRate() {
		return transactionFeeRate;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param transactionFeeRate
	 * @return:  void 
	 * @Date :   2018-06-13 11:12:40   
	 */
	public void setTransactionFeeRate(BigDecimal transactionFeeRate) {
		this.transactionFeeRate = transactionFeeRate;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Integer 
	 * @Date :   2018-06-13 11:12:40    
	 */
	public Integer getCustomerType() {
		return customerType;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param customerType
	 * @return:  void 
	 * @Date :   2018-06-13 11:12:40   
	 */
	public void setCustomerType(Integer customerType) {
		this.customerType = customerType;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  BigDecimal 
	 * @Date :   2018-06-13 11:12:40    
	 */
	public BigDecimal getFloatUpPrice() {
		return floatUpPrice;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param floatUpPrice
	 * @return:  void 
	 * @Date :   2018-06-13 11:12:40   
	 */
	public void setFloatUpPrice(BigDecimal floatUpPrice) {
		this.floatUpPrice = floatUpPrice;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  BigDecimal 
	 * @Date :   2018-06-13 11:12:40    
	 */
	public BigDecimal getFloatDownPrice() {
		return floatDownPrice;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param floatDownPrice
	 * @return:  void 
	 * @Date :   2018-06-13 11:12:40   
	 */
	public void setFloatDownPrice(BigDecimal floatDownPrice) {
		this.floatDownPrice = floatDownPrice;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Integer 
	 * @Date :   2018-06-13 11:12:40    
	 */
	public Integer getMatchPriority() {
		return matchPriority;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param matchPriority
	 * @return:  void 
	 * @Date :   2018-06-13 11:12:40   
	 */
	public void setMatchPriority(Integer matchPriority) {
		this.matchPriority = matchPriority;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 11:12:40    
	 */
	public String getCurrencyType() {
		return currencyType;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param currencyType
	 * @return:  void 
	 * @Date :   2018-06-13 11:12:40   
	 */
	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 11:12:40    
	 */
	public String getWebsite() {
		return website;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param website
	 * @return:  void 
	 * @Date :   2018-06-13 11:12:40   
	 */
	public void setWebsite(String website) {
		this.website = website;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 11:12:40    
	 */
	public String getUserCode() {
		return userCode;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param userCode
	 * @return:  void 
	 * @Date :   2018-06-13 11:12:40   
	 */
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 11:12:40    
	 */
	public String getUserName() {
		return userName;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param userName
	 * @return:  void 
	 * @Date :   2018-06-13 11:12:40   
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 11:12:40    
	 */
	public String getTrueName() {
		return trueName;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param trueName
	 * @return:  void 
	 * @Date :   2018-06-13 11:12:40   
	 */
	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Integer 
	 * @Date :   2018-06-13 11:12:40    
	 */
	public Integer getProjectType() {
		return projectType;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param projectType
	 * @return:  void 
	 * @Date :   2018-06-13 11:12:40   
	 */
	public void setProjectType(Integer projectType) {
		this.projectType = projectType;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Date 
	 * @Date :   2018-06-13 11:12:40    
	 */
	public Date getTransactionTime() {
		return transactionTime;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param transactionTime
	 * @return:  void 
	 * @Date :   2018-06-13 11:12:40   
	 */
	public void setTransactionTime(Date transactionTime) {
		this.transactionTime = transactionTime;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 11:12:40    
	 */
	public String getCustomerIp() {
		return customerIp;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param customerIp
	 * @return:  void 
	 * @Date :   2018-06-13 11:12:40   
	 */
	public void setCustomerIp(String customerIp) {
		this.customerIp = customerIp;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Integer 
	 * @Date :   2018-06-13 11:12:40    
	 */
	public Integer getFixPriceType() {
		return fixPriceType;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param fixPriceType
	 * @return:  void 
	 * @Date :   2018-06-13 11:12:40   
	 */
	public void setFixPriceType(Integer fixPriceType) {
		this.fixPriceType = fixPriceType;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 11:12:40    
	 */
	public String getFixPriceCoinCode() {
		return fixPriceCoinCode;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param fixPriceCoinCode
	 * @return:  void 
	 * @Date :   2018-06-13 11:12:40   
	 */
	public void setFixPriceCoinCode(String fixPriceCoinCode) {
		this.fixPriceCoinCode = fixPriceCoinCode;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Long 
	 * @Date :   2018-06-13 11:12:40    
	 */
	public Long getCoinAccountId() {
		return coinAccountId;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param coinAccountId
	 * @return:  void 
	 * @Date :   2018-06-13 11:12:40   
	 */
	public void setCoinAccountId(Long coinAccountId) {
		this.coinAccountId = coinAccountId;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-13 11:12:40    
	 */
	public String getSurName() {
		return surName;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param surName
	 * @return:  void 
	 * @Date :   2018-06-13 11:12:40   
	 */
	public void setSurName(String surName) {
		this.surName = surName;
	}

	public BigDecimal getTransactionFeeRateDiscount () {
		return transactionFeeRateDiscount;
	}

	public void setTransactionFeeRateDiscount (BigDecimal transactionFeeRateDiscount) {
		this.transactionFeeRateDiscount = transactionFeeRateDiscount;
	}

	public BigDecimal getTransactionFeePlat () {
		return transactionFeePlat;
	}

	public void setTransactionFeePlat (BigDecimal transactionFeePlat) {
		this.transactionFeePlat = transactionFeePlat;
	}

	public Integer getIsOpenCoinFee () {
		return isOpenCoinFee;
	}

	public void setIsOpenCoinFee (Integer isOpenCoinFee) {
		this.isOpenCoinFee = isOpenCoinFee;
	}

	public String getPlatCoin () {
		return platCoin;
	}

	public void setPlatCoin (String platCoin) {
		this.platCoin = platCoin;
	}
}
