/**
 * Copyright:   
 * @author:      yaozh
 * @version:     V1.0 
 * @Date:        2019-07-30 11:33:21 
 */
package hry.cm.customerminer.model;


import hry.bean.BaseModel;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * <p> CmCustomerMiner </p>
 * @author:         yaozh
 * @Date :          2019-07-30 11:33:21  
 */
@Table(name="cm_customer_miner")
public class CmCustomerMiner extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "orderId")
	private Long orderId;  //订单ID
	
	@Column(name= "minerId")
	private Long minerId;  //矿机ID
	
	@Column(name= "customerId")
	private Long customerId;  //购买人Id
	
	@Column(name= "transactionNum")
	private String transactionNum;  //订单号
	
	@Column(name= "minerName")
	private String minerName;  //矿机名称
	
	@Column(name= "minerCode")
	private String minerCode;  //矿机编号
	
	@Column(name= "minerPrice")
	private BigDecimal minerPrice;  //矿机价钱
	
	@Column(name= "minerProfitType")
	private Integer minerProfitType;  //收益领取类型：1自动发放 2果树领取
	
	@Column(name= "payCoinCode")
	private String payCoinCode;  //支付币种
	
	@Column(name= "profitCoinCode")
	private String profitCoinCode;  //收益币种
	
	@Column(name= "profitRate")
	private BigDecimal profitRate;  //收益倍数
	
	@Column(name= "totalProfit")
	private BigDecimal totalProfit;  //总收益
	
	@Column(name= "dayProfit")
	private BigDecimal dayProfit;  //日收益
	
	@Column(name= "effectiveTimeLength")
	private Integer effectiveTimeLength;  //有效时长
	
	@Column(name= "timeLengthCompany")
	private Integer timeLengthCompany;  //有效时长单位 1：年 ，2：天
	
	@Column(name= "generateCycle")
	private Integer generateCycle;  //产币周期 1:天  2:小时
	
	@Column(name= "profit1")
	private BigDecimal profit1;  //已领取收益
	
	@Column(name= "profit2")
	private BigDecimal profit2;  //未领取收益
	
	@Column(name= "profit3")
	private BigDecimal profit3;  //总产出收益
	
	@Column(name= "remark")
	private String remark;  //备注
	
	@Column(name= "saasId")
	private String saasId;  //
	
	@Column(name= "startDate")
	private Date startDate;  //运行时间
	
	@Column(name= "endDate")
	private Date endDate;  //到期时间
	
	@Column(name= "status")
	private Integer status;  //矿机状态 1：待运行 2：运行中 3:已结束
	
	
	@Transient
	private Integer minerNum;  //矿机数量
	
	
	
	
	public Integer getMinerNum() {
		return minerNum;
	}

	public void setMinerNum(Integer minerNum) {
		this.minerNum = minerNum;
	}

	/**
	 * <p></p>
	 * @author:  yaozh
	 * @return:  Long 
	 * @Date :   2019-07-30 11:33:21    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  yaozh
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-07-30 11:33:21   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>订单ID</p>
	 * @author:  yaozh
	 * @return:  Long 
	 * @Date :   2019-07-30 11:33:21    
	 */
	public Long getOrderId() {
		return orderId;
	}
	
	/**
	 * <p>订单ID</p>
	 * @author:  yaozh
	 * @param:   @param orderId
	 * @return:  void 
	 * @Date :   2019-07-30 11:33:21   
	 */
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	
	
	/**
	 * <p>矿机ID</p>
	 * @author:  yaozh
	 * @return:  Long 
	 * @Date :   2019-07-30 11:33:21    
	 */
	public Long getMinerId() {
		return minerId;
	}
	
	/**
	 * <p>矿机ID</p>
	 * @author:  yaozh
	 * @param:   @param minerId
	 * @return:  void 
	 * @Date :   2019-07-30 11:33:21   
	 */
	public void setMinerId(Long minerId) {
		this.minerId = minerId;
	}
	
	
	/**
	 * <p>购买人Id</p>
	 * @author:  yaozh
	 * @return:  Long 
	 * @Date :   2019-07-30 11:33:21    
	 */
	public Long getCustomerId() {
		return customerId;
	}
	
	/**
	 * <p>购买人Id</p>
	 * @author:  yaozh
	 * @param:   @param customerId
	 * @return:  void 
	 * @Date :   2019-07-30 11:33:21   
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	
	/**
	 * <p>订单号</p>
	 * @author:  yaozh
	 * @return:  String 
	 * @Date :   2019-07-30 11:33:21    
	 */
	public String getTransactionNum() {
		return transactionNum;
	}
	
	/**
	 * <p>订单号</p>
	 * @author:  yaozh
	 * @param:   @param transactionNum
	 * @return:  void 
	 * @Date :   2019-07-30 11:33:21   
	 */
	public void setTransactionNum(String transactionNum) {
		this.transactionNum = transactionNum;
	}
	
	
	/**
	 * <p>矿机名称</p>
	 * @author:  yaozh
	 * @return:  String 
	 * @Date :   2019-07-30 11:33:21    
	 */
	public String getMinerName() {
		return minerName;
	}
	
	/**
	 * <p>矿机名称</p>
	 * @author:  yaozh
	 * @param:   @param minerName
	 * @return:  void 
	 * @Date :   2019-07-30 11:33:21   
	 */
	public void setMinerName(String minerName) {
		this.minerName = minerName;
	}
	
	
	/**
	 * <p>矿机编号</p>
	 * @author:  yaozh
	 * @return:  String 
	 * @Date :   2019-07-30 11:33:21    
	 */
	public String getMinerCode() {
		return minerCode;
	}
	
	/**
	 * <p>矿机编号</p>
	 * @author:  yaozh
	 * @param:   @param minerCode
	 * @return:  void 
	 * @Date :   2019-07-30 11:33:21   
	 */
	public void setMinerCode(String minerCode) {
		this.minerCode = minerCode;
	}
	
	
	/**
	 * <p>矿机价钱</p>
	 * @author:  yaozh
	 * @return:  BigDecimal 
	 * @Date :   2019-07-30 11:33:21    
	 */
	public BigDecimal getMinerPrice() {
		return minerPrice;
	}
	
	/**
	 * <p>矿机价钱</p>
	 * @author:  yaozh
	 * @param:   @param minerPrice
	 * @return:  void 
	 * @Date :   2019-07-30 11:33:21   
	 */
	public void setMinerPrice(BigDecimal minerPrice) {
		this.minerPrice = minerPrice;
	}
	
	
	/**
	 * <p>收益领取类型：1自动发放 2果树领取</p>
	 * @author:  yaozh
	 * @return:  Integer 
	 * @Date :   2019-07-30 11:33:21    
	 */
	public Integer getMinerProfitType() {
		return minerProfitType;
	}
	
	/**
	 * <p>收益领取类型：1自动发放 2果树领取</p>
	 * @author:  yaozh
	 * @param:   @param minerProfitType
	 * @return:  void 
	 * @Date :   2019-07-30 11:33:21   
	 */
	public void setMinerProfitType(Integer minerProfitType) {
		this.minerProfitType = minerProfitType;
	}
	
	
	/**
	 * <p>支付币种</p>
	 * @author:  yaozh
	 * @return:  String 
	 * @Date :   2019-07-30 11:33:21    
	 */
	public String getPayCoinCode() {
		return payCoinCode;
	}
	
	/**
	 * <p>支付币种</p>
	 * @author:  yaozh
	 * @param:   @param payCoinCode
	 * @return:  void 
	 * @Date :   2019-07-30 11:33:21   
	 */
	public void setPayCoinCode(String payCoinCode) {
		this.payCoinCode = payCoinCode;
	}
	
	
	/**
	 * <p>收益币种</p>
	 * @author:  yaozh
	 * @return:  String 
	 * @Date :   2019-07-30 11:33:21    
	 */
	public String getProfitCoinCode() {
		return profitCoinCode;
	}
	
	/**
	 * <p>收益币种</p>
	 * @author:  yaozh
	 * @param:   @param profitCoinCode
	 * @return:  void 
	 * @Date :   2019-07-30 11:33:21   
	 */
	public void setProfitCoinCode(String profitCoinCode) {
		this.profitCoinCode = profitCoinCode;
	}
	
	
	/**
	 * <p>收益倍数</p>
	 * @author:  yaozh
	 * @return:  BigDecimal 
	 * @Date :   2019-07-30 11:33:21    
	 */
	public BigDecimal getProfitRate() {
		return profitRate;
	}
	
	/**
	 * <p>收益倍数</p>
	 * @author:  yaozh
	 * @param:   @param profitRate
	 * @return:  void 
	 * @Date :   2019-07-30 11:33:21   
	 */
	public void setProfitRate(BigDecimal profitRate) {
		this.profitRate = profitRate;
	}
	
	
	/**
	 * <p>总收益</p>
	 * @author:  yaozh
	 * @return:  BigDecimal 
	 * @Date :   2019-07-30 11:33:21    
	 */
	public BigDecimal getTotalProfit() {
		return totalProfit;
	}
	
	/**
	 * <p>总收益</p>
	 * @author:  yaozh
	 * @param:   @param totalProfit
	 * @return:  void 
	 * @Date :   2019-07-30 11:33:21   
	 */
	public void setTotalProfit(BigDecimal totalProfit) {
		this.totalProfit = totalProfit;
	}
	
	
	/**
	 * <p>日收益</p>
	 * @author:  yaozh
	 * @return:  BigDecimal 
	 * @Date :   2019-07-30 11:33:21    
	 */
	public BigDecimal getDayProfit() {
		return dayProfit;
	}
	
	/**
	 * <p>日收益</p>
	 * @author:  yaozh
	 * @param:   @param dayProfit
	 * @return:  void 
	 * @Date :   2019-07-30 11:33:21   
	 */
	public void setDayProfit(BigDecimal dayProfit) {
		this.dayProfit = dayProfit;
	}
	
	
	/**
	 * <p>有效时长</p>
	 * @author:  yaozh
	 * @return:  Integer 
	 * @Date :   2019-07-30 11:33:21    
	 */
	public Integer getEffectiveTimeLength() {
		return effectiveTimeLength;
	}
	
	/**
	 * <p>有效时长</p>
	 * @author:  yaozh
	 * @param:   @param effectiveTimeLength
	 * @return:  void 
	 * @Date :   2019-07-30 11:33:21   
	 */
	public void setEffectiveTimeLength(Integer effectiveTimeLength) {
		this.effectiveTimeLength = effectiveTimeLength;
	}
	
	
	/**
	 * <p>有效时长单位 1：年 ，2：天</p>
	 * @author:  yaozh
	 * @return:  Integer 
	 * @Date :   2019-07-30 11:33:21    
	 */
	public Integer getTimeLengthCompany() {
		return timeLengthCompany;
	}
	
	/**
	 * <p>有效时长单位 1：年 ，2：天</p>
	 * @author:  yaozh
	 * @param:   @param timeLengthCompany
	 * @return:  void 
	 * @Date :   2019-07-30 11:33:21   
	 */
	public void setTimeLengthCompany(Integer timeLengthCompany) {
		this.timeLengthCompany = timeLengthCompany;
	}
	
	
	/**
	 * <p>产币周期 1:天  2:小时</p>
	 * @author:  yaozh
	 * @return:  Integer 
	 * @Date :   2019-07-30 11:33:21    
	 */
	public Integer getGenerateCycle() {
		return generateCycle;
	}
	
	/**
	 * <p>产币周期 1:天  2:小时</p>
	 * @author:  yaozh
	 * @param:   @param generateCycle
	 * @return:  void 
	 * @Date :   2019-07-30 11:33:21   
	 */
	public void setGenerateCycle(Integer generateCycle) {
		this.generateCycle = generateCycle;
	}
	
	
	/**
	 * <p>已领取收益</p>
	 * @author:  yaozh
	 * @return:  BigDecimal 
	 * @Date :   2019-07-30 11:33:21    
	 */
	public BigDecimal getProfit1() {
		return profit1;
	}
	
	/**
	 * <p>已领取收益</p>
	 * @author:  yaozh
	 * @param:   @param profit1
	 * @return:  void 
	 * @Date :   2019-07-30 11:33:21   
	 */
	public void setProfit1(BigDecimal profit1) {
		this.profit1 = profit1;
	}
	
	
	/**
	 * <p>未领取收益</p>
	 * @author:  yaozh
	 * @return:  BigDecimal 
	 * @Date :   2019-07-30 11:33:21    
	 */
	public BigDecimal getProfit2() {
		return profit2;
	}
	
	/**
	 * <p>未领取收益</p>
	 * @author:  yaozh
	 * @param:   @param profit2
	 * @return:  void 
	 * @Date :   2019-07-30 11:33:21   
	 */
	public void setProfit2(BigDecimal profit2) {
		this.profit2 = profit2;
	}
	
	
	/**
	 * <p>总产出收益</p>
	 * @author:  yaozh
	 * @return:  BigDecimal 
	 * @Date :   2019-07-30 11:33:21    
	 */
	public BigDecimal getProfit3() {
		return profit3;
	}
	
	/**
	 * <p>总产出收益</p>
	 * @author:  yaozh
	 * @param:   @param profit3
	 * @return:  void 
	 * @Date :   2019-07-30 11:33:21   
	 */
	public void setProfit3(BigDecimal profit3) {
		this.profit3 = profit3;
	}
	
	
	/**
	 * <p>备注</p>
	 * @author:  yaozh
	 * @return:  String 
	 * @Date :   2019-07-30 11:33:21    
	 */
	public String getRemark() {
		return remark;
	}
	
	/**
	 * <p>备注</p>
	 * @author:  yaozh
	 * @param:   @param remark
	 * @return:  void 
	 * @Date :   2019-07-30 11:33:21   
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	/**
	 * <p></p>
	 * @author:  yaozh
	 * @return:  String 
	 * @Date :   2019-07-30 11:33:21    
	 */
	public String getSaasId() {
		return saasId;
	}
	
	/**
	 * <p></p>
	 * @author:  yaozh
	 * @param:   @param saasId
	 * @return:  void 
	 * @Date :   2019-07-30 11:33:21   
	 */
	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}
	
	
	/**
	 * <p>运行时间</p>
	 * @author:  yaozh
	 * @return:  Date 
	 * @Date :   2019-07-30 11:33:21    
	 */
	public Date getStartDate() {
		return startDate;
	}
	
	/**
	 * <p>运行时间</p>
	 * @author:  yaozh
	 * @param:   @param startDate
	 * @return:  void 
	 * @Date :   2019-07-30 11:33:21   
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	
	/**
	 * <p>到期时间</p>
	 * @author:  yaozh
	 * @return:  Date 
	 * @Date :   2019-07-30 11:33:21    
	 */
	public Date getEndDate() {
		return endDate;
	}
	
	/**
	 * <p>到期时间</p>
	 * @author:  yaozh
	 * @param:   @param endDate
	 * @return:  void 
	 * @Date :   2019-07-30 11:33:21   
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	
	/**
	 * <p>矿机状态 1：待运行 2：运行中 3:已结束</p>
	 * @author:  yaozh
	 * @return:  Integer 
	 * @Date :   2019-07-30 11:33:21    
	 */
	public Integer getStatus() {
		return status;
	}
	
	/**
	 * <p>矿机状态 1：待运行 2：运行中 3:已结束</p>
	 * @author:  yaozh
	 * @param:   @param status
	 * @return:  void 
	 * @Date :   2019-07-30 11:33:21   
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	

}
