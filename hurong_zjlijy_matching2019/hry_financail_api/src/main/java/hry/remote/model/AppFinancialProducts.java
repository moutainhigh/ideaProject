/**
 * Copyright:
 * @author:      jidn
 * @version:     V1.0
 * @Date:        2019-04-03 11:06:50
 */
package hry.remote.model;


import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p> AppFinancialProducts </p>
 * @author:         jidn
 * @Date :          2019-04-03 11:06:50
 */
public class AppFinancialProducts implements Serializable {


	private Long id;  //id

	private Long incomeTypeId;  //收益类型id

	private String productNum;  // 产品编号

	private String productName;  // 产品名称

	private String coinCode;  //产品币种(后期扩建时，可创建一张新表通过id进行关联)

	private String incomeYearFloor;  //年化收益百分比   最低0%

	private Integer lockUpPeriod;  //锁仓期

	private Integer investmentPeriod;  //投资期限

	private Integer earlyRedemption;  //能否提前赎回  0否  1是

	private String redemptionRate;  //赎回费率

	private Integer interestBearing;  //计息方式

	private Integer repayment;  //还款方式

	private BigDecimal totalMoney;  // 计划总金额

	private BigDecimal minimumMoney;  //投资起点金额

	private BigDecimal incrementalMoney;  //投资递增金额(最小投资单位)

	private String buyRate;  //购买手续费率

	private BigDecimal investmentCeilingMoney;  //个人投资上限

	private Integer maxNumber;  //参与人数上限

	private Date preheatingTime;  //预热时间

	private Date startTime;  //开始时间

	private Date endTime;  //截止时间

	private Date created;  //创建时间

	private Date startingInterestTime;  //开始计息时间

	private Date returnOfPrincipalTime;  //返还本息时间

	private String remarks;  //产品说明

	private Integer canUseRedPackets;  //能否使用红包 0否 1是

	private Integer isRecommended;  // 是否推荐  1推荐   0 不推荐

	private Integer stage;  //阶段  4 待发放  5 已完成   其他阶段是通过时间来显示的，不存数据库

	private Integer status;  //状态  1开启  0关闭

	private String stageStr;//阶段名称

	private BigDecimal investmentMoney;//

	private BigDecimal expectedIncome;//预期收入

	private BigDecimal realIncome;//实际收入

	private String incomeType; //收益类型

	private String typeRemarks; //收益类型说明

	private int days; //收益倒计时 （当前时间到计息开始时间的间隔天数）

	private Integer isRedeem; //赎回状态  0未赎回   1 申请赎回  2已赎回  3已拒绝

	private BigDecimal redeemPoundage; //赎回手续费

	private BigDecimal redPacketsMoney; //红包数量

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public BigDecimal getRedPacketsMoney() {
		return redPacketsMoney;
	}

	public void setRedPacketsMoney(BigDecimal redPacketsMoney) {
		this.redPacketsMoney = redPacketsMoney;
	}

	public String getStageStr() {
		return stageStr;
	}

	public void setStageStr(String stageStr) {
		this.stageStr = stageStr;
	}
	public BigDecimal getRealIncome() {
		return realIncome;
	}

	public void setRealIncome(BigDecimal realIncome) {
		this.realIncome = realIncome;
	}

	public BigDecimal getExpectedIncome() {
		return expectedIncome;
	}

	public void setExpectedIncome(BigDecimal expectedIncome) {
		this.expectedIncome = expectedIncome;
	}

	public BigDecimal getInvestmentMoney() {
		return investmentMoney;
	}

	public void setInvestmentMoney(BigDecimal investmentMoney) {
		this.investmentMoney = investmentMoney;
	}

	/**
	 * <p>id</p>
	 * @author:  jidn
	 * @return:  Long
	 * @Date :   2019-04-03 11:06:50
	 */
	public Long getId() {
		return id;
	}

	/**
	 * <p>id</p>
	 * @author:  jidn
	 * @param:   @param id
	 * @return:  void
	 * @Date :   2019-04-03 11:06:50
	 */
	public void setId(Long id) {
		this.id = id;
	}


	/**
	 * <p>收益类型id</p>
	 * @author:  jidn
	 * @return:  Long
	 * @Date :   2019-04-03 11:06:50
	 */
	public Long getIncomeTypeId() {
		return incomeTypeId;
	}

	/**
	 * <p>收益类型id</p>
	 * @author:  jidn
	 * @param:   @param incomeTypeId
	 * @return:  void
	 * @Date :   2019-04-03 11:06:50
	 */
	public void setIncomeTypeId(Long incomeTypeId) {
		this.incomeTypeId = incomeTypeId;
	}


	/**
	 * <p> 产品编号</p>
	 * @author:  jidn
	 * @return:  String
	 * @Date :   2019-04-03 11:06:50
	 */
	public String getProductNum() {
		return productNum;
	}

	/**
	 * <p> 产品编号</p>
	 * @author:  jidn
	 * @param:   @param productNum
	 * @return:  void
	 * @Date :   2019-04-03 11:06:50
	 */
	public void setProductNum(String productNum) {
		this.productNum = productNum;
	}


	/**
	 * <p> 产品名称</p>
	 * @author:  jidn
	 * @return:  String
	 * @Date :   2019-04-03 11:06:50
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * <p> 产品名称</p>
	 * @author:  jidn
	 * @param:   @param productName
	 * @return:  void
	 * @Date :   2019-04-03 11:06:50
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}


	/**
	 * <p>产品币种(后期扩建时，可创建一张新表通过id进行关联)</p>
	 * @author:  jidn
	 * @return:  String
	 * @Date :   2019-04-03 11:06:50
	 */
	public String getCoinCode() {
		return coinCode;
	}

	/**
	 * <p>产品币种(后期扩建时，可创建一张新表通过id进行关联)</p>
	 * @author:  jidn
	 * @param:   @param coinCode
	 * @return:  void
	 * @Date :   2019-04-03 11:06:50
	 */
	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}


	/**
	 * <p>年化收益百分比   最低0%</p>
	 * @author:  jidn
	 * @return:  String
	 * @Date :   2019-04-03 11:06:50
	 */
	public String getIncomeYearFloor() {
		return incomeYearFloor;
	}

	/**
	 * <p>年化收益百分比   最低0%</p>
	 * @author:  jidn
	 * @param:   @param incomeYearFloor
	 * @return:  void
	 * @Date :   2019-04-03 11:06:50
	 */
	public void setIncomeYearFloor(String incomeYearFloor) {
		this.incomeYearFloor = incomeYearFloor;
	}


	/**
	 * <p>锁仓期</p>
	 * @author:  jidn
	 * @return:  Integer
	 * @Date :   2019-04-03 11:06:50
	 */
	public Integer getLockUpPeriod() {
		return lockUpPeriod;
	}

	/**
	 * <p>锁仓期</p>
	 * @author:  jidn
	 * @param:   @param lockUpPeriod
	 * @return:  void
	 * @Date :   2019-04-03 11:06:50
	 */
	public void setLockUpPeriod(Integer lockUpPeriod) {
		this.lockUpPeriod = lockUpPeriod;
	}


	/**
	 * <p>投资期限</p>
	 * @author:  jidn
	 * @return:  Integer
	 * @Date :   2019-04-03 11:06:50
	 */
	public Integer getInvestmentPeriod() {
		return investmentPeriod;
	}

	/**
	 * <p>投资期限</p>
	 * @author:  jidn
	 * @param:   @param investmentPeriod
	 * @return:  void
	 * @Date :   2019-04-03 11:06:50
	 */
	public void setInvestmentPeriod(Integer investmentPeriod) {
		this.investmentPeriod = investmentPeriod;
	}


	/**
	 * <p>能否提前赎回  0否  1是</p>
	 * @author:  jidn
	 * @return:  Integer
	 * @Date :   2019-04-03 11:06:50
	 */
	public Integer getEarlyRedemption() {
		return earlyRedemption;
	}

	/**
	 * <p>能否提前赎回  0否  1是</p>
	 * @author:  jidn
	 * @param:   @param earlyRedemption
	 * @return:  void
	 * @Date :   2019-04-03 11:06:50
	 */
	public void setEarlyRedemption(Integer earlyRedemption) {
		this.earlyRedemption = earlyRedemption;
	}


	/**
	 * <p>赎回费率</p>
	 * @author:  jidn
	 * @return:  String
	 * @Date :   2019-04-03 11:06:50
	 */
	public String getRedemptionRate() {
		return redemptionRate;
	}

	/**
	 * <p>赎回费率</p>
	 * @author:  jidn
	 * @param:   @param redemptionRate
	 * @return:  void
	 * @Date :   2019-04-03 11:06:50
	 */
	public void setRedemptionRate(String redemptionRate) {
		this.redemptionRate = redemptionRate;
	}


	/**
	 * <p>计息方式</p>
	 * @author:  jidn
	 * @return:  Integer
	 * @Date :   2019-04-03 11:06:50
	 */
	public Integer getInterestBearing() {
		return interestBearing;
	}

	/**
	 * <p>计息方式</p>
	 * @author:  jidn
	 * @param:   @param interestBearing
	 * @return:  void
	 * @Date :   2019-04-03 11:06:50
	 */
	public void setInterestBearing(Integer interestBearing) {
		this.interestBearing = interestBearing;
	}


	/**
	 * <p>还款方式</p>
	 * @author:  jidn
	 * @return:  Integer
	 * @Date :   2019-04-03 11:06:50
	 */
	public Integer getRepayment() {
		return repayment;
	}

	/**
	 * <p>还款方式</p>
	 * @author:  jidn
	 * @param:   @param repayment
	 * @return:  void
	 * @Date :   2019-04-03 11:06:50
	 */
	public void setRepayment(Integer repayment) {
		this.repayment = repayment;
	}


	/**
	 * <p> 计划总金额</p>
	 * @author:  jidn
	 * @return:  BigDecimal
	 * @Date :   2019-04-03 11:06:50
	 */
	public BigDecimal getTotalMoney() {
		return totalMoney;
	}

	/**
	 * <p> 计划总金额</p>
	 * @author:  jidn
	 * @param:   @param totalMoney
	 * @return:  void
	 * @Date :   2019-04-03 11:06:50
	 */
	public void setTotalMoney(BigDecimal totalMoney) {
		this.totalMoney = totalMoney;
	}


	/**
	 * <p>投资起点金额</p>
	 * @author:  jidn
	 * @return:  BigDecimal
	 * @Date :   2019-04-03 11:06:50
	 */
	public BigDecimal getMinimumMoney() {
		return minimumMoney;
	}

	/**
	 * <p>投资起点金额</p>
	 * @author:  jidn
	 * @param:   @param minimumMoney
	 * @return:  void
	 * @Date :   2019-04-03 11:06:50
	 */
	public void setMinimumMoney(BigDecimal minimumMoney) {
		this.minimumMoney = minimumMoney;
	}


	/**
	 * <p>投资递增金额(最小投资单位)</p>
	 * @author:  jidn
	 * @return:  BigDecimal
	 * @Date :   2019-04-03 11:06:50
	 */
	public BigDecimal getIncrementalMoney() {
		return incrementalMoney;
	}

	/**
	 * <p>投资递增金额(最小投资单位)</p>
	 * @author:  jidn
	 * @param:   @param incrementalMoney
	 * @return:  void
	 * @Date :   2019-04-03 11:06:50
	 */
	public void setIncrementalMoney(BigDecimal incrementalMoney) {
		this.incrementalMoney = incrementalMoney;
	}


	/**
	 * <p>购买手续费率</p>
	 * @author:  jidn
	 * @return:  String
	 * @Date :   2019-04-03 11:06:50
	 */
	public String getBuyRate() {
		return buyRate;
	}

	/**
	 * <p>购买手续费率</p>
	 * @author:  jidn
	 * @param:   @param buyRate
	 * @return:  void
	 * @Date :   2019-04-03 11:06:50
	 */
	public void setBuyRate(String buyRate) {
		this.buyRate = buyRate;
	}


	/**
	 * <p>个人投资上限</p>
	 * @author:  jidn
	 * @return:  BigDecimal
	 * @Date :   2019-04-03 11:06:50
	 */
	public BigDecimal getInvestmentCeilingMoney() {
		return investmentCeilingMoney;
	}

	/**
	 * <p>个人投资上限</p>
	 * @author:  jidn
	 * @param:   @param investmentCeilingMoney
	 * @return:  void
	 * @Date :   2019-04-03 11:06:50
	 */
	public void setInvestmentCeilingMoney(BigDecimal investmentCeilingMoney) {
		this.investmentCeilingMoney = investmentCeilingMoney;
	}


	/**
	 * <p>参与人数上限</p>
	 * @author:  jidn
	 * @return:  Integer
	 * @Date :   2019-04-03 11:06:50
	 */
	public Integer getMaxNumber() {
		return maxNumber;
	}

	/**
	 * <p>参与人数上限</p>
	 * @author:  jidn
	 * @param:   @param maxNumber
	 * @return:  void
	 * @Date :   2019-04-03 11:06:50
	 */
	public void setMaxNumber(Integer maxNumber) {
		this.maxNumber = maxNumber;
	}


	/**
	 * <p>预热时间</p>
	 * @author:  jidn
	 * @return:  Date
	 * @Date :   2019-04-03 11:06:50
	 */
	public Date getPreheatingTime() {
		return preheatingTime;
	}

	/**
	 * <p>预热时间</p>
	 * @author:  jidn
	 * @param:   @param preheatingTime
	 * @return:  void
	 * @Date :   2019-04-03 11:06:50
	 */
	public void setPreheatingTime(Date preheatingTime) {
		this.preheatingTime = preheatingTime;
	}


	/**
	 * <p>开始时间</p>
	 * @author:  jidn
	 * @return:  Date
	 * @Date :   2019-04-03 11:06:50
	 */
	public Date getStartTime() {
		return startTime;
	}

	/**
	 * <p>开始时间</p>
	 * @author:  jidn
	 * @param:   @param startTime
	 * @return:  void
	 * @Date :   2019-04-03 11:06:50
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}


	/**
	 * <p>截止时间</p>
	 * @author:  jidn
	 * @return:  Date
	 * @Date :   2019-04-03 11:06:50
	 */
	public Date getEndTime() {
		return endTime;
	}

	/**
	 * <p>截止时间</p>
	 * @author:  jidn
	 * @param:   @param endTime
	 * @return:  void
	 * @Date :   2019-04-03 11:06:50
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}


	/**
	 * <p>开始计息时间</p>
	 * @author:  jidn
	 * @return:  Date
	 * @Date :   2019-04-03 11:06:50
	 */
	public Date getStartingInterestTime() {
		return startingInterestTime;
	}

	/**
	 * <p>开始计息时间</p>
	 * @author:  jidn
	 * @param:   @param startingInterestTime
	 * @return:  void
	 * @Date :   2019-04-03 11:06:50
	 */
	public void setStartingInterestTime(Date startingInterestTime) {
		this.startingInterestTime = startingInterestTime;
	}


	/**
	 * <p>返还本息时间</p>
	 * @author:  jidn
	 * @return:  Date
	 * @Date :   2019-04-03 11:06:50
	 */
	public Date getReturnOfPrincipalTime() {
		return returnOfPrincipalTime;
	}

	/**
	 * <p>返还本息时间</p>
	 * @author:  jidn
	 * @param:   @param returnOfPrincipalTime
	 * @return:  void
	 * @Date :   2019-04-03 11:06:50
	 */
	public void setReturnOfPrincipalTime(Date returnOfPrincipalTime) {
		this.returnOfPrincipalTime = returnOfPrincipalTime;
	}


	/**
	 * <p>产品说明</p>
	 * @author:  jidn
	 * @return:  String
	 * @Date :   2019-04-03 11:06:50
	 */
	public String getRemarks() {
		return remarks;
	}

	/**
	 * <p>产品说明</p>
	 * @author:  jidn
	 * @param:   @param remarks
	 * @return:  void
	 * @Date :   2019-04-03 11:06:50
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}


	/**
	 * <p>能否使用红包</p>
	 * @author:  jidn
	 * @return:  Integer
	 * @Date :   2019-04-03 11:06:50
	 */
	public Integer getCanUseRedPackets() {
		return canUseRedPackets;
	}

	/**
	 * <p>能否使用红包</p>
	 * @author:  jidn
	 * @param:   @param canUseRedPackets
	 * @return:  void
	 * @Date :   2019-04-03 11:06:50
	 */
	public void setCanUseRedPackets(Integer canUseRedPackets) {
		this.canUseRedPackets = canUseRedPackets;
	}


	/**
	 * <p> 是否推荐  1推荐   0 不推荐</p>
	 * @author:  jidn
	 * @return:  Integer
	 * @Date :   2019-04-03 11:06:50
	 */
	public Integer getIsRecommended() {
		return isRecommended;
	}

	/**
	 * <p> 是否推荐  1推荐   0 不推荐</p>
	 * @author:  jidn
	 * @param:   @param isRecommended
	 * @return:  void
	 * @Date :   2019-04-03 11:06:50
	 */
	public void setIsRecommended(Integer isRecommended) {
		this.isRecommended = isRecommended;
	}


	/**
	 * <p>阶段  4 待发放  5 已完成   其他阶段是通过时间来显示的，不存数据库</p>
	 * @author:  jidn
	 * @return:  Integer
	 * @Date :   2019-04-03 11:06:50
	 */
	public Integer getStage() {
		return stage;
	}

	/**
	 * <p>阶段  4 待发放  5 已完成   其他阶段是通过时间来显示的，不存数据库</p>
	 * @author:  jidn
	 * @param:   @param stage
	 * @return:  void
	 * @Date :   2019-04-03 11:06:50
	 */
	public void setStage(Integer stage) {
		this.stage = stage;
	}


	/**
	 * <p>状态  1开启  0关闭</p>
	 * @author:  jidn
	 * @return:  Integer
	 * @Date :   2019-04-03 11:06:50
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * <p>状态  1开启  0关闭</p>
	 * @author:  jidn
	 * @param:   @param status
	 * @return:  void
	 * @Date :   2019-04-03 11:06:50
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getIncomeType() {
		return incomeType;
	}

	public void setIncomeType(String incomeType) {
		this.incomeType = incomeType;
	}

	public String getTypeRemarks() {
		return typeRemarks;
	}

	public void setTypeRemarks(String typeRemarks) {
		this.typeRemarks = typeRemarks;
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	public Integer getIsRedeem() {
		return isRedeem;
	}

	public void setIsRedeem(Integer isRedeem) {
		this.isRedeem = isRedeem;
	}

	public BigDecimal getRedeemPoundage() {
		return redeemPoundage;
	}

	public void setRedeemPoundage(BigDecimal redeemPoundage) {
		this.redeemPoundage = redeemPoundage;
	}
}
