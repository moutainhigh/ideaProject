/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2020-01-08 14:05:03 
 */
package hry.cm5.miner.model;


import hry.bean.BaseModel;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> Cm5MinerOrder </p>
 * @author:         zhouming
 * @Date :          2020-01-08 14:05:03  
 */
@Table(name="cm5_miner_order")
public class Cm5MinerOrder extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "inheritNum")
	private String inheritNum;  //继承码(用于矿机继承)
	
	@Column(name= "minerId")
	private Long minerId;  //矿机ID
	
	@Column(name= "customerId")
	private Long customerId;  //购买人Id
	
	@Column(name= "orderNum")
	private String orderNum;  //订单号
	
	@Column(name= "minerName")
	private String minerName;  //矿机名称
	
	@Column(name= "minerCode")
	private String minerCode;  //矿机编号
	
	@Column(name= "pricingCoinCode")
	private String pricingCoinCode;  //定价币种
	
	@Column(name= "pricingMarketPrice")
	private BigDecimal pricingMarketPrice;  //定价币种市价
	
	@Column(name= "minerPrice")
	private BigDecimal minerPrice;  //矿机价钱
	
	@Column(name= "profitRate")
	private BigDecimal profitRate;  //预计收益倍数
	
	@Column(name= "totalProfit")
	private BigDecimal totalProfit;  //预计总收益
	
	@Column(name= "payCoinCode")
	private String payCoinCode;  //支付币种
	
	@Column(name= "payMarketPrice")
	private BigDecimal payMarketPrice;  //支付币种市价
	
	@Column(name= "orderPrice")
	private BigDecimal orderPrice;  //订单总金额
	
	@Column(name= "orderActualPrice")
	private BigDecimal orderActualPrice;  //订单实际总金额
	
	@Column(name= "startDate")
	private Date startDate;  //订单开始时间
	
	@Column(name= "endDate")
	private Date endDate;  //订单到期时间
	
	@Column(name= "orderType")
	private Integer orderType;  //订单类型 1 购买 2 升级
	
	@Column(name= "status")
	private Integer status;  //矿机状态 1：运行中 2：已升级(低版本升高版本) 3:已结束(已出局)
	
	@Column(name= "runDays")
	private Long runDays;  //运行天数
	
	@Column(name= "saasId")
	private String saasId;  //
	
	
	
	
	/**
	 * <p></p>
	 * @author:  zhouming
	 * @return:  Long 
	 * @Date :   2020-01-08 14:05:03    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  zhouming
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2020-01-08 14:05:03   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>继承码(用于矿机继承)</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2020-01-08 14:05:03    
	 */
	public String getInheritNum() {
		return inheritNum;
	}
	
	/**
	 * <p>继承码(用于矿机继承)</p>
	 * @author:  zhouming
	 * @param:   @param inheritNum
	 * @return:  void 
	 * @Date :   2020-01-08 14:05:03   
	 */
	public void setInheritNum(String inheritNum) {
		this.inheritNum = inheritNum;
	}
	
	
	/**
	 * <p>矿机ID</p>
	 * @author:  zhouming
	 * @return:  Long 
	 * @Date :   2020-01-08 14:05:03    
	 */
	public Long getMinerId() {
		return minerId;
	}
	
	/**
	 * <p>矿机ID</p>
	 * @author:  zhouming
	 * @param:   @param minerId
	 * @return:  void 
	 * @Date :   2020-01-08 14:05:03   
	 */
	public void setMinerId(Long minerId) {
		this.minerId = minerId;
	}
	
	
	/**
	 * <p>购买人Id</p>
	 * @author:  zhouming
	 * @return:  Long 
	 * @Date :   2020-01-08 14:05:03    
	 */
	public Long getCustomerId() {
		return customerId;
	}
	
	/**
	 * <p>购买人Id</p>
	 * @author:  zhouming
	 * @param:   @param customerId
	 * @return:  void 
	 * @Date :   2020-01-08 14:05:03   
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	
	/**
	 * <p>订单号</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2020-01-08 14:05:03    
	 */
	public String getOrderNum() {
		return orderNum;
	}
	
	/**
	 * <p>订单号</p>
	 * @author:  zhouming
	 * @param:   @param orderNum
	 * @return:  void 
	 * @Date :   2020-01-08 14:05:03   
	 */
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	
	
	/**
	 * <p>矿机名称</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2020-01-08 14:05:03    
	 */
	public String getMinerName() {
		return minerName;
	}
	
	/**
	 * <p>矿机名称</p>
	 * @author:  zhouming
	 * @param:   @param minerName
	 * @return:  void 
	 * @Date :   2020-01-08 14:05:03   
	 */
	public void setMinerName(String minerName) {
		this.minerName = minerName;
	}
	
	
	/**
	 * <p>矿机编号</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2020-01-08 14:05:03    
	 */
	public String getMinerCode() {
		return minerCode;
	}
	
	/**
	 * <p>矿机编号</p>
	 * @author:  zhouming
	 * @param:   @param minerCode
	 * @return:  void 
	 * @Date :   2020-01-08 14:05:03   
	 */
	public void setMinerCode(String minerCode) {
		this.minerCode = minerCode;
	}
	
	
	/**
	 * <p>定价币种</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2020-01-08 14:05:03    
	 */
	public String getPricingCoinCode() {
		return pricingCoinCode;
	}
	
	/**
	 * <p>定价币种</p>
	 * @author:  zhouming
	 * @param:   @param pricingCoinCode
	 * @return:  void 
	 * @Date :   2020-01-08 14:05:03   
	 */
	public void setPricingCoinCode(String pricingCoinCode) {
		this.pricingCoinCode = pricingCoinCode;
	}
	
	
	/**
	 * <p>定价币种市价</p>
	 * @author:  zhouming
	 * @return:  BigDecimal 
	 * @Date :   2020-01-08 14:05:03    
	 */
	public BigDecimal getPricingMarketPrice() {
		return pricingMarketPrice;
	}
	
	/**
	 * <p>定价币种市价</p>
	 * @author:  zhouming
	 * @param:   @param pricingMarketPrice
	 * @return:  void 
	 * @Date :   2020-01-08 14:05:03   
	 */
	public void setPricingMarketPrice(BigDecimal pricingMarketPrice) {
		this.pricingMarketPrice = pricingMarketPrice;
	}
	
	
	/**
	 * <p>矿机价钱</p>
	 * @author:  zhouming
	 * @return:  BigDecimal 
	 * @Date :   2020-01-08 14:05:03    
	 */
	public BigDecimal getMinerPrice() {
		return minerPrice;
	}
	
	/**
	 * <p>矿机价钱</p>
	 * @author:  zhouming
	 * @param:   @param minerPrice
	 * @return:  void 
	 * @Date :   2020-01-08 14:05:03   
	 */
	public void setMinerPrice(BigDecimal minerPrice) {
		this.minerPrice = minerPrice;
	}
	
	
	/**
	 * <p>预计收益倍数</p>
	 * @author:  zhouming
	 * @return:  BigDecimal 
	 * @Date :   2020-01-08 14:05:03    
	 */
	public BigDecimal getProfitRate() {
		return profitRate;
	}
	
	/**
	 * <p>预计收益倍数</p>
	 * @author:  zhouming
	 * @param:   @param profitRate
	 * @return:  void 
	 * @Date :   2020-01-08 14:05:03   
	 */
	public void setProfitRate(BigDecimal profitRate) {
		this.profitRate = profitRate;
	}
	
	
	/**
	 * <p>预计总收益</p>
	 * @author:  zhouming
	 * @return:  BigDecimal 
	 * @Date :   2020-01-08 14:05:03    
	 */
	public BigDecimal getTotalProfit() {
		return totalProfit;
	}
	
	/**
	 * <p>预计总收益</p>
	 * @author:  zhouming
	 * @param:   @param totalProfit
	 * @return:  void 
	 * @Date :   2020-01-08 14:05:03   
	 */
	public void setTotalProfit(BigDecimal totalProfit) {
		this.totalProfit = totalProfit;
	}
	
	
	/**
	 * <p>支付币种</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2020-01-08 14:05:03    
	 */
	public String getPayCoinCode() {
		return payCoinCode;
	}
	
	/**
	 * <p>支付币种</p>
	 * @author:  zhouming
	 * @param:   @param payCoinCode
	 * @return:  void 
	 * @Date :   2020-01-08 14:05:03   
	 */
	public void setPayCoinCode(String payCoinCode) {
		this.payCoinCode = payCoinCode;
	}
	
	
	/**
	 * <p>支付币种市价</p>
	 * @author:  zhouming
	 * @return:  BigDecimal 
	 * @Date :   2020-01-08 14:05:03    
	 */
	public BigDecimal getPayMarketPrice() {
		return payMarketPrice;
	}
	
	/**
	 * <p>支付币种市价</p>
	 * @author:  zhouming
	 * @param:   @param payMarketPrice
	 * @return:  void 
	 * @Date :   2020-01-08 14:05:03   
	 */
	public void setPayMarketPrice(BigDecimal payMarketPrice) {
		this.payMarketPrice = payMarketPrice;
	}
	
	
	/**
	 * <p>订单总金额</p>
	 * @author:  zhouming
	 * @return:  BigDecimal 
	 * @Date :   2020-01-08 14:05:03    
	 */
	public BigDecimal getOrderPrice() {
		return orderPrice;
	}
	
	/**
	 * <p>订单总金额</p>
	 * @author:  zhouming
	 * @param:   @param orderPrice
	 * @return:  void 
	 * @Date :   2020-01-08 14:05:03   
	 */
	public void setOrderPrice(BigDecimal orderPrice) {
		this.orderPrice = orderPrice;
	}
	
	
	/**
	 * <p>订单实际总金额</p>
	 * @author:  zhouming
	 * @return:  BigDecimal 
	 * @Date :   2020-01-08 14:05:03    
	 */
	public BigDecimal getOrderActualPrice() {
		return orderActualPrice;
	}
	
	/**
	 * <p>订单实际总金额</p>
	 * @author:  zhouming
	 * @param:   @param orderActualPrice
	 * @return:  void 
	 * @Date :   2020-01-08 14:05:03   
	 */
	public void setOrderActualPrice(BigDecimal orderActualPrice) {
		this.orderActualPrice = orderActualPrice;
	}
	
	
	/**
	 * <p>订单开始时间</p>
	 * @author:  zhouming
	 * @return:  Date 
	 * @Date :   2020-01-08 14:05:03    
	 */
	public Date getStartDate() {
		return startDate;
	}
	
	/**
	 * <p>订单开始时间</p>
	 * @author:  zhouming
	 * @param:   @param startDate
	 * @return:  void 
	 * @Date :   2020-01-08 14:05:03   
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	
	/**
	 * <p>订单到期时间</p>
	 * @author:  zhouming
	 * @return:  Date 
	 * @Date :   2020-01-08 14:05:03    
	 */
	public Date getEndDate() {
		return endDate;
	}
	
	/**
	 * <p>订单到期时间</p>
	 * @author:  zhouming
	 * @param:   @param endDate
	 * @return:  void 
	 * @Date :   2020-01-08 14:05:03   
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	
	/**
	 * <p>订单类型 1 购买 2 升级</p>
	 * @author:  zhouming
	 * @return:  Integer 
	 * @Date :   2020-01-08 14:05:03    
	 */
	public Integer getOrderType() {
		return orderType;
	}
	
	/**
	 * <p>订单类型 1 购买 2 升级</p>
	 * @author:  zhouming
	 * @param:   @param orderType
	 * @return:  void 
	 * @Date :   2020-01-08 14:05:03   
	 */
	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}
	
	
	/**
	 * <p>矿机状态 1：运行中 2：已升级(低版本升高版本) 3:已结束(已出局)</p>
	 * @author:  zhouming
	 * @return:  Integer 
	 * @Date :   2020-01-08 14:05:03    
	 */
	public Integer getStatus() {
		return status;
	}
	
	/**
	 * <p>矿机状态 1：运行中 2：已升级(低版本升高版本) 3:已结束(已出局)</p>
	 * @author:  zhouming
	 * @param:   @param status
	 * @return:  void 
	 * @Date :   2020-01-08 14:05:03   
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
	/**
	 * <p>运行天数</p>
	 * @author:  zhouming
	 * @return:  Long 
	 * @Date :   2020-01-08 14:05:03    
	 */
	public Long getRunDays() {
		return runDays;
	}
	
	/**
	 * <p>运行天数</p>
	 * @author:  zhouming
	 * @param:   @param runDays
	 * @return:  void 
	 * @Date :   2020-01-08 14:05:03   
	 */
	public void setRunDays(Long runDays) {
		this.runDays = runDays;
	}
	
	
	/**
	 * <p></p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2020-01-08 14:05:03    
	 */
	public String getSaasId() {
		return saasId;
	}
	
	/**
	 * <p></p>
	 * @author:  zhouming
	 * @param:   @param saasId
	 * @return:  void 
	 * @Date :   2020-01-08 14:05:03   
	 */
	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}
	
	

}
