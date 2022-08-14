/**
 * Copyright:   
 * @author:      yaozh
 * @version:     V1.0 
 * @Date:        2019-11-21 10:03:56 
 */
package hry.cm4.order.model;


import hry.bean.BaseModel;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> Cm4Order </p>
 * @author:         yaozh
 * @Date :          2019-11-21 10:03:56  
 */
@Table(name="cm4_order")
public class Cm4Order extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "minerId")
	private Long minerId;  //矿机ID
	
	@Column(name= "customerId")
	private Long customerId;  //购买人Id
	
	@Column(name= "transactionNum")
	private String transactionNum;  //订单号
	
	@Column(name= "buyNumber")
	private Integer buyNumber;  //购买数量
	
	@Column(name= "orderPrice")
	private BigDecimal orderPrice;  //订单总金额
	
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
	private String profitCoinCode;  //产出收益币种,使用逗号分隔

	@Column(name= "profitProportionAll")
	private String profitProportionAll;  //每个币种收益比例，使用逗号分隔
	
	@Column(name= "profitProportion")
	private BigDecimal profitProportion;  //每个币种收益比例，使用逗号分隔
	
	@Column(name= "totalProfit")
	private BigDecimal totalProfit;  //总收益
	
	@Column(name= "dayProfit")
	private BigDecimal dayProfit;  //每日产矿价值(USDT)
	
	@Column(name= "effectiveTimeLength")
	private Integer effectiveTimeLength;  //有效时长(天)


	public String getProfitProportionAll() {
		return profitProportionAll;
	}

	public void setProfitProportionAll(String profitProportionAll) {
		this.profitProportionAll = profitProportionAll;
	}

	/**
	 * <p></p>
	 * @author:  yaozh
	 * @return:  Long 
	 * @Date :   2019-11-21 10:03:56    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  yaozh
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-11-21 10:03:56   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>矿机ID</p>
	 * @author:  yaozh
	 * @return:  Long 
	 * @Date :   2019-11-21 10:03:56    
	 */
	public Long getMinerId() {
		return minerId;
	}
	
	/**
	 * <p>矿机ID</p>
	 * @author:  yaozh
	 * @param:   @param minerId
	 * @return:  void 
	 * @Date :   2019-11-21 10:03:56   
	 */
	public void setMinerId(Long minerId) {
		this.minerId = minerId;
	}
	
	
	/**
	 * <p>购买人Id</p>
	 * @author:  yaozh
	 * @return:  Long 
	 * @Date :   2019-11-21 10:03:56    
	 */
	public Long getCustomerId() {
		return customerId;
	}
	
	/**
	 * <p>购买人Id</p>
	 * @author:  yaozh
	 * @param:   @param customerId
	 * @return:  void 
	 * @Date :   2019-11-21 10:03:56   
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	
	/**
	 * <p>订单号</p>
	 * @author:  yaozh
	 * @return:  String 
	 * @Date :   2019-11-21 10:03:56    
	 */
	public String getTransactionNum() {
		return transactionNum;
	}
	
	/**
	 * <p>订单号</p>
	 * @author:  yaozh
	 * @param:   @param transactionNum
	 * @return:  void 
	 * @Date :   2019-11-21 10:03:56   
	 */
	public void setTransactionNum(String transactionNum) {
		this.transactionNum = transactionNum;
	}
	
	
	/**
	 * <p>购买数量</p>
	 * @author:  yaozh
	 * @return:  Integer 
	 * @Date :   2019-11-21 10:03:56    
	 */
	public Integer getBuyNumber() {
		return buyNumber;
	}
	
	/**
	 * <p>购买数量</p>
	 * @author:  yaozh
	 * @param:   @param buyNumber
	 * @return:  void 
	 * @Date :   2019-11-21 10:03:56   
	 */
	public void setBuyNumber(Integer buyNumber) {
		this.buyNumber = buyNumber;
	}
	
	
	/**
	 * <p>订单总金额</p>
	 * @author:  yaozh
	 * @return:  BigDecimal 
	 * @Date :   2019-11-21 10:03:56    
	 */
	public BigDecimal getOrderPrice() {
		return orderPrice;
	}
	
	/**
	 * <p>订单总金额</p>
	 * @author:  yaozh
	 * @param:   @param orderPrice
	 * @return:  void 
	 * @Date :   2019-11-21 10:03:56   
	 */
	public void setOrderPrice(BigDecimal orderPrice) {
		this.orderPrice = orderPrice;
	}
	
	
	/**
	 * <p>备注</p>
	 * @author:  yaozh
	 * @return:  String 
	 * @Date :   2019-11-21 10:03:56    
	 */
	public String getRemark() {
		return remark;
	}
	
	/**
	 * <p>备注</p>
	 * @author:  yaozh
	 * @param:   @param remark
	 * @return:  void 
	 * @Date :   2019-11-21 10:03:56   
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	/**
	 * <p></p>
	 * @author:  yaozh
	 * @return:  String 
	 * @Date :   2019-11-21 10:03:56    
	 */
	public String getSaasId() {
		return saasId;
	}
	
	/**
	 * <p></p>
	 * @author:  yaozh
	 * @param:   @param saasId
	 * @return:  void 
	 * @Date :   2019-11-21 10:03:56   
	 */
	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}
	
	
	/**
	 * <p>运行时间</p>
	 * @author:  yaozh
	 * @return:  Date 
	 * @Date :   2019-11-21 10:03:56    
	 */
	public Date getStartDate() {
		return startDate;
	}
	
	/**
	 * <p>运行时间</p>
	 * @author:  yaozh
	 * @param:   @param startDate
	 * @return:  void 
	 * @Date :   2019-11-21 10:03:56   
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	
	/**
	 * <p>到期时间</p>
	 * @author:  yaozh
	 * @return:  Date 
	 * @Date :   2019-11-21 10:03:56    
	 */
	public Date getEndDate() {
		return endDate;
	}
	
	/**
	 * <p>到期时间</p>
	 * @author:  yaozh
	 * @param:   @param endDate
	 * @return:  void 
	 * @Date :   2019-11-21 10:03:56   
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	
	/**
	 * <p>矿机状态 1：待运行 2：运行中 3:已结束</p>
	 * @author:  yaozh
	 * @return:  Integer 
	 * @Date :   2019-11-21 10:03:56    
	 */
	public Integer getStatus() {
		return status;
	}
	
	/**
	 * <p>矿机状态 1：待运行 2：运行中 3:已结束</p>
	 * @author:  yaozh
	 * @param:   @param status
	 * @return:  void 
	 * @Date :   2019-11-21 10:03:56   
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
	/**
	 * <p>矿机名称</p>
	 * @author:  yaozh
	 * @return:  String 
	 * @Date :   2019-11-21 10:03:56    
	 */
	public String getMinerName() {
		return minerName;
	}
	
	/**
	 * <p>矿机名称</p>
	 * @author:  yaozh
	 * @param:   @param minerName
	 * @return:  void 
	 * @Date :   2019-11-21 10:03:56   
	 */
	public void setMinerName(String minerName) {
		this.minerName = minerName;
	}
	
	
	/**
	 * <p>矿机编号</p>
	 * @author:  yaozh
	 * @return:  String 
	 * @Date :   2019-11-21 10:03:56    
	 */
	public String getMinerCode() {
		return minerCode;
	}
	
	/**
	 * <p>矿机编号</p>
	 * @author:  yaozh
	 * @param:   @param minerCode
	 * @return:  void 
	 * @Date :   2019-11-21 10:03:56   
	 */
	public void setMinerCode(String minerCode) {
		this.minerCode = minerCode;
	}
	
	
	/**
	 * <p>矿机价钱</p>
	 * @author:  yaozh
	 * @return:  BigDecimal 
	 * @Date :   2019-11-21 10:03:56    
	 */
	public BigDecimal getMinerPrice() {
		return minerPrice;
	}
	
	/**
	 * <p>矿机价钱</p>
	 * @author:  yaozh
	 * @param:   @param minerPrice
	 * @return:  void 
	 * @Date :   2019-11-21 10:03:56   
	 */
	public void setMinerPrice(BigDecimal minerPrice) {
		this.minerPrice = minerPrice;
	}
	
	
	/**
	 * <p>收益领取类型：1自动发放 2果树领取</p>
	 * @author:  yaozh
	 * @return:  Integer 
	 * @Date :   2019-11-21 10:03:56    
	 */
	public Integer getMinerProfitType() {
		return minerProfitType;
	}
	
	/**
	 * <p>收益领取类型：1自动发放 2果树领取</p>
	 * @author:  yaozh
	 * @param:   @param minerProfitType
	 * @return:  void 
	 * @Date :   2019-11-21 10:03:56   
	 */
	public void setMinerProfitType(Integer minerProfitType) {
		this.minerProfitType = minerProfitType;
	}
	
	
	/**
	 * <p>支付币种</p>
	 * @author:  yaozh
	 * @return:  String 
	 * @Date :   2019-11-21 10:03:56    
	 */
	public String getPayCoinCode() {
		return payCoinCode;
	}
	
	/**
	 * <p>支付币种</p>
	 * @author:  yaozh
	 * @param:   @param payCoinCode
	 * @return:  void 
	 * @Date :   2019-11-21 10:03:56   
	 */
	public void setPayCoinCode(String payCoinCode) {
		this.payCoinCode = payCoinCode;
	}
	
	
	/**
	 * <p>产出收益币种,使用逗号分隔</p>
	 * @author:  yaozh
	 * @return:  String 
	 * @Date :   2019-11-21 10:03:56    
	 */
	public String getProfitCoinCode() {
		return profitCoinCode;
	}
	
	/**
	 * <p>产出收益币种,使用逗号分隔</p>
	 * @author:  yaozh
	 * @param:   @param profitCoinCode
	 * @return:  void 
	 * @Date :   2019-11-21 10:03:56   
	 */
	public void setProfitCoinCode(String profitCoinCode) {
		this.profitCoinCode = profitCoinCode;
	}
	
	
	/**
	 * <p>每个币种收益比例，使用逗号分隔</p>
	 * @author:  yaozh
	 * @return:  BigDecimal 
	 * @Date :   2019-11-21 10:03:56    
	 */
	public BigDecimal getProfitProportion() {
		return profitProportion;
	}
	
	/**
	 * <p>每个币种收益比例，使用逗号分隔</p>
	 * @author:  yaozh
	 * @param:   @param profitProportion
	 * @return:  void 
	 * @Date :   2019-11-21 10:03:56   
	 */
	public void setProfitProportion(BigDecimal profitProportion) {
		this.profitProportion = profitProportion;
	}
	
	
	/**
	 * <p>总收益</p>
	 * @author:  yaozh
	 * @return:  BigDecimal 
	 * @Date :   2019-11-21 10:03:56    
	 */
	public BigDecimal getTotalProfit() {
		return totalProfit;
	}
	
	/**
	 * <p>总收益</p>
	 * @author:  yaozh
	 * @param:   @param totalProfit
	 * @return:  void 
	 * @Date :   2019-11-21 10:03:56   
	 */
	public void setTotalProfit(BigDecimal totalProfit) {
		this.totalProfit = totalProfit;
	}
	
	
	/**
	 * <p>每日产矿价值(USDT)</p>
	 * @author:  yaozh
	 * @return:  BigDecimal 
	 * @Date :   2019-11-21 10:03:56    
	 */
	public BigDecimal getDayProfit() {
		return dayProfit;
	}
	
	/**
	 * <p>每日产矿价值(USDT)</p>
	 * @author:  yaozh
	 * @param:   @param dayProfit
	 * @return:  void 
	 * @Date :   2019-11-21 10:03:56   
	 */
	public void setDayProfit(BigDecimal dayProfit) {
		this.dayProfit = dayProfit;
	}
	
	
	/**
	 * <p>有效时长(天)</p>
	 * @author:  yaozh
	 * @return:  Integer 
	 * @Date :   2019-11-21 10:03:56    
	 */
	public Integer getEffectiveTimeLength() {
		return effectiveTimeLength;
	}
	
	/**
	 * <p>有效时长(天)</p>
	 * @author:  yaozh
	 * @param:   @param effectiveTimeLength
	 * @return:  void 
	 * @Date :   2019-11-21 10:03:56   
	 */
	public void setEffectiveTimeLength(Integer effectiveTimeLength) {
		this.effectiveTimeLength = effectiveTimeLength;
	}
	
	

}
