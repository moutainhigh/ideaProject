package hry.cm.remote.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

public class CmCustomerMinerRemote implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "用户矿机Id" )
	private Long id;  //
	@ApiModelProperty(value = "订单ID")
	private Long orderId;  //订单ID
	
	@ApiModelProperty(value = "矿机ID")
	private Long minerId;  //矿机ID
	
	@ApiModelProperty(value = "购买人Id")
	private Long customerId;  //购买人Id
	
	@ApiModelProperty(value = "订单号")
	private String transactionNum;  //订单号
	
	@ApiModelProperty(value = "矿机名称")
	private String minerName;  //矿机名称
	
	@ApiModelProperty(value = "矿机编号")
	private String minerCode;  //矿机编号
	
	@ApiModelProperty(value = "矿机价钱")
	private BigDecimal minerPrice;  //矿机价钱
	
	@ApiModelProperty(value = "收益领取类型：1自动发放 2果树领取")
	private Integer minerProfitType;  //收益领取类型：1自动发放 2果树领取
	
	@ApiModelProperty(value = "支付币种")
	private String payCoinCode;  //支付币种
	
	@ApiModelProperty(value = "收益币种")
	private String profitCoinCode;  //收益币种
	
	@ApiModelProperty(value = "收益倍数")
	private BigDecimal profitRate;  //收益倍数
	
	@ApiModelProperty(value = "总收益")
	private BigDecimal totalProfit;  //总收益
	
	@ApiModelProperty(value = "日收益")
	private BigDecimal dayProfit;  //日收益
	
	@ApiModelProperty(value = "有效时长")
	private Integer effectiveTimeLength;  //有效时长
	
	@ApiModelProperty(value = "有效时长单位 1：年 ，2：天")
	private Integer timeLengthCompany;  //有效时长单位 1：年 ，2：天
	
	@ApiModelProperty(value = "产币周期 1:天  2:小时")
	private Integer generateCycle;  //产币周期 1:天  2:小时
	
	@ApiModelProperty(value = "已领取收益")
	private BigDecimal profit1;  //已领取收益
	
	@ApiModelProperty(value = "未领取收益")
	private BigDecimal profit2;  //未领取收益
	
	@ApiModelProperty(value = "总产出收益")
	private BigDecimal profit3;  //总产出收益
	
	
	@ApiModelProperty(value = "运行时间")
	private Date startDate;  //运行时间
	
	@ApiModelProperty(value = "到期时间")
	private Date endDate;  //到期时间
	
	@ApiModelProperty(value = "矿机状态 1：待运行 2：运行中 3:已结束")
	private Integer status;  //矿机状态 1：待运行 2：运行中 3:已结束

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getMinerId() {
		return minerId;
	}

	public void setMinerId(Long minerId) {
		this.minerId = minerId;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getTransactionNum() {
		return transactionNum;
	}

	public void setTransactionNum(String transactionNum) {
		this.transactionNum = transactionNum;
	}

	public String getMinerName() {
		return minerName;
	}

	public void setMinerName(String minerName) {
		this.minerName = minerName;
	}

	public String getMinerCode() {
		return minerCode;
	}

	public void setMinerCode(String minerCode) {
		this.minerCode = minerCode;
	}

	public BigDecimal getMinerPrice() {
		return minerPrice;
	}

	public void setMinerPrice(BigDecimal minerPrice) {
		this.minerPrice = minerPrice;
	}

	public Integer getMinerProfitType() {
		return minerProfitType;
	}

	public void setMinerProfitType(Integer minerProfitType) {
		this.minerProfitType = minerProfitType;
	}

	public String getPayCoinCode() {
		return payCoinCode;
	}

	public void setPayCoinCode(String payCoinCode) {
		this.payCoinCode = payCoinCode;
	}

	public String getProfitCoinCode() {
		return profitCoinCode;
	}

	public void setProfitCoinCode(String profitCoinCode) {
		this.profitCoinCode = profitCoinCode;
	}

	public BigDecimal getProfitRate() {
		return profitRate;
	}

	public void setProfitRate(BigDecimal profitRate) {
		this.profitRate = profitRate;
	}

	public BigDecimal getTotalProfit() {
		return totalProfit;
	}

	public void setTotalProfit(BigDecimal totalProfit) {
		this.totalProfit = totalProfit;
	}

	public BigDecimal getDayProfit() {
		return dayProfit;
	}

	public void setDayProfit(BigDecimal dayProfit) {
		this.dayProfit = dayProfit;
	}

	public Integer getEffectiveTimeLength() {
		return effectiveTimeLength;
	}

	public void setEffectiveTimeLength(Integer effectiveTimeLength) {
		this.effectiveTimeLength = effectiveTimeLength;
	}

	public Integer getTimeLengthCompany() {
		return timeLengthCompany;
	}

	public void setTimeLengthCompany(Integer timeLengthCompany) {
		this.timeLengthCompany = timeLengthCompany;
	}

	public Integer getGenerateCycle() {
		return generateCycle;
	}

	public void setGenerateCycle(Integer generateCycle) {
		this.generateCycle = generateCycle;
	}

	public BigDecimal getProfit1() {
		return profit1;
	}

	public void setProfit1(BigDecimal profit1) {
		this.profit1 = profit1;
	}

	public BigDecimal getProfit2() {
		return profit2;
	}

	public void setProfit2(BigDecimal profit2) {
		this.profit2 = profit2;
	}

	public BigDecimal getProfit3() {
		return profit3;
	}

	public void setProfit3(BigDecimal profit3) {
		this.profit3 = profit3;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}


}
