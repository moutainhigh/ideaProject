/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Gao Mimi
 * @version:      V1.0 
 * @Date:        2015年11月20日 下午2:11:12
 */
package hry.core.mvc.model;

import java.math.BigDecimal;

import javax.persistence.MappedSuperclass;

/**
 * <p> TODO</p>
 * @author:         Gao Mimi 
 * @Date :          2015年11月20日 下午2:11:12 
 */
@MappedSuperclass
public class ProjectBase extends BaseModel{
	//财务相关字段,读取paramsetting默认值要赋值上保存到数据库，
	
	protected BigDecimal projectMoney;
	protected java.util.Date startDate; // 贷款日期
	protected java.util.Date intentDate; //还款日期
	protected Integer payintentPeriod; //还款期数,
	//都是跟款项生成有关，但别的时候也不需要，可以把有些字段单独存到另一张表start
	protected String repaymentDate; //是否按还款日还款,
	protected Integer payintentPerioDate; //每期还款日,
	protected String repaymentMethod;  //还款方式
	protected String repaymentCycle;  //还款周期
	protected Integer dayOfEveryPeriod; //自定义周期的天数,
	protected String ccalculateFirstAndEnd;//算头算尾isCcalculateFirstAndEnd，notCcalculateFirstAndEnd
	protected String interestByOneTime;//是否一次性还款付息isInterestByOneTime，notInterestByOneTime
	protected String preposePayAccrual;//前置付息isPreposePayAccrual，notPreposePayAccrual
	protected String dateModel;   //日期模型third,notthird
	protected String intentDateType;
	
	protected String serviceMoneyWay;   //财务服务费【不随息，按本金计算，前置一次性付费】
	protected String consultationMoneyWay;   //咨询管理费【随息】
	protected String otherOneFundMoneyWay;   //预留给客户的收费1【不随息，按本金计算，后置一次性付费】
	protected String otherTwoFundMoneyWay;   //预留给客户的收费2【不随息，按本金计算，每期付费】
	protected String otherTreeFundMoneyWay;   //预留给客户的收费3
	
	protected BigDecimal loanInterestMoneyMonthRate; //利息利率
	protected BigDecimal serviceMoneyMonthRate; //财务服务费利率利率
	protected BigDecimal consultationMoneyMonthRate; //咨询管理费利率
	protected BigDecimal otherOneFundMoneyMonthRate; //其它款项1利率
	protected BigDecimal otherTwoFundMoneyMonthRate; //其它款项3利率
	protected BigDecimal otherTreeFundMoneyMonthRate; //其它款项2利率
	
	protected BigDecimal loanInterestMoneyDayRate; //利息利率
	protected BigDecimal serviceMoneyDayRate; //财务服务费利率利率
	protected BigDecimal consultationMoneyDayRate; //咨询管理费利率
	protected BigDecimal otherOneFundMoneyDayRate; //其它款项1利率
	protected BigDecimal otherTwoFundMoneyDayRate; //其它款项3利率
	protected BigDecimal otherTreeFundMoneyDayRate; //其它款项2利率
	
	protected BigDecimal loanInterestMoneyYearRate; //利息利率
	protected BigDecimal serviceMoneyYearRate; //财务服务费利率利率
	protected BigDecimal consultationMoneyYearRate; //咨询管理费利率
	protected BigDecimal otherOneFundMoneyYearRate; //其它款项1利率
	protected BigDecimal otherTwoFundMoneyYearRate; //其它款项3利率
	protected BigDecimal otherTreeFundMoneyYearRate; //其它款项2利率
	//可以把有些字段单独存到另一张表end
	//财务相关字段
	
	
	/**
	 * <p> TODO</p>
	 * @return:     java.util.Date
	 */
	public java.util.Date getStartDate() {
		return startDate;
	}
	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getLoanInterestMoneyDayRate() {
		return loanInterestMoneyDayRate;
	}
	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setLoanInterestMoneyDayRate(BigDecimal loanInterestMoneyDayRate) {
		this.loanInterestMoneyDayRate = loanInterestMoneyDayRate;
	}
	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getServiceMoneyDayRate() {
		return serviceMoneyDayRate;
	}
	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setServiceMoneyDayRate(BigDecimal serviceMoneyDayRate) {
		this.serviceMoneyDayRate = serviceMoneyDayRate;
	}
	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getConsultationMoneyDayRate() {
		return consultationMoneyDayRate;
	}
	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setConsultationMoneyDayRate(BigDecimal consultationMoneyDayRate) {
		this.consultationMoneyDayRate = consultationMoneyDayRate;
	}
	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getOtherOneFundMoneyDayRate() {
		return otherOneFundMoneyDayRate;
	}
	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setOtherOneFundMoneyDayRate(BigDecimal otherOneFundMoneyDayRate) {
		this.otherOneFundMoneyDayRate = otherOneFundMoneyDayRate;
	}
	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getOtherTwoFundMoneyDayRate() {
		return otherTwoFundMoneyDayRate;
	}
	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setOtherTwoFundMoneyDayRate(BigDecimal otherTwoFundMoneyDayRate) {
		this.otherTwoFundMoneyDayRate = otherTwoFundMoneyDayRate;
	}
	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getOtherTreeFundMoneyDayRate() {
		return otherTreeFundMoneyDayRate;
	}
	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setOtherTreeFundMoneyDayRate(BigDecimal otherTreeFundMoneyDayRate) {
		this.otherTreeFundMoneyDayRate = otherTreeFundMoneyDayRate;
	}
	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getLoanInterestMoneyYearRate() {
		return loanInterestMoneyYearRate;
	}
	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setLoanInterestMoneyYearRate(BigDecimal loanInterestMoneyYearRate) {
		this.loanInterestMoneyYearRate = loanInterestMoneyYearRate;
	}
	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getServiceMoneyYearRate() {
		return serviceMoneyYearRate;
	}
	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setServiceMoneyYearRate(BigDecimal serviceMoneyYearRate) {
		this.serviceMoneyYearRate = serviceMoneyYearRate;
	}
	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getConsultationMoneyYearRate() {
		return consultationMoneyYearRate;
	}
	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setConsultationMoneyYearRate(BigDecimal consultationMoneyYearRate) {
		this.consultationMoneyYearRate = consultationMoneyYearRate;
	}
	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getOtherOneFundMoneyYearRate() {
		return otherOneFundMoneyYearRate;
	}
	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setOtherOneFundMoneyYearRate(BigDecimal otherOneFundMoneyYearRate) {
		this.otherOneFundMoneyYearRate = otherOneFundMoneyYearRate;
	}
	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getOtherTwoFundMoneyYearRate() {
		return otherTwoFundMoneyYearRate;
	}
	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setOtherTwoFundMoneyYearRate(BigDecimal otherTwoFundMoneyYearRate) {
		this.otherTwoFundMoneyYearRate = otherTwoFundMoneyYearRate;
	}
	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getOtherTreeFundMoneyYearRate() {
		return otherTreeFundMoneyYearRate;
	}
	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setOtherTreeFundMoneyYearRate(BigDecimal otherTreeFundMoneyYearRate) {
		this.otherTreeFundMoneyYearRate = otherTreeFundMoneyYearRate;
	}
	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getIntentDateType() {
		return intentDateType;
	}
	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setIntentDateType(String intentDateType) {
		this.intentDateType = intentDateType;
	}
	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getProjectMoney() {
		return projectMoney;
	}
	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setProjectMoney(BigDecimal projectMoney) {
		this.projectMoney = projectMoney;
	}
	/** 
	 * <p> TODO</p>
	 * @return: java.util.Date
	 */
	public void setStartDate(java.util.Date startDate) {
		this.startDate = startDate;
	}
	/**
	 * <p> TODO</p>
	 * @return:     java.util.Date
	 */
	public java.util.Date getIntentDate() {
		return intentDate;
	}
	/** 
	 * <p> TODO</p>
	 * @return: java.util.Date
	 */
	public void setIntentDate(java.util.Date intentDate) {
		this.intentDate = intentDate;
	}
	/**
	 * <p> TODO</p>
	 * @return:     Integer
	 */
	public Integer getPayintentPeriod() {
		return payintentPeriod;
	}
	/** 
	 * <p> TODO</p>
	 * @return: Integer
	 */
	public void setPayintentPeriod(Integer payintentPeriod) {
		this.payintentPeriod = payintentPeriod;
	}
	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getRepaymentDate() {
		return repaymentDate;
	}
	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setRepaymentDate(String repaymentDate) {
		this.repaymentDate = repaymentDate;
	}
	/**
	 * <p> TODO</p>
	 * @return:     Integer
	 */
	public Integer getPayintentPerioDate() {
		return payintentPerioDate;
	}
	/** 
	 * <p> TODO</p>
	 * @return: Integer
	 */
	public void setPayintentPerioDate(Integer payintentPerioDate) {
		this.payintentPerioDate = payintentPerioDate;
	}
	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getRepaymentMethod() {
		return repaymentMethod;
	}
	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setRepaymentMethod(String repaymentMethod) {
		this.repaymentMethod = repaymentMethod;
	}
	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getRepaymentCycle() {
		return repaymentCycle;
	}
	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setRepaymentCycle(String repaymentCycle) {
		this.repaymentCycle = repaymentCycle;
	}
	/**
	 * <p> TODO</p>
	 * @return:     Integer
	 */
	public Integer getDayOfEveryPeriod() {
		return dayOfEveryPeriod;
	}
	/** 
	 * <p> TODO</p>
	 * @return: Integer
	 */
	public void setDayOfEveryPeriod(Integer dayOfEveryPeriod) {
		this.dayOfEveryPeriod = dayOfEveryPeriod;
	}
	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getCcalculateFirstAndEnd() {
		return ccalculateFirstAndEnd;
	}
	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setCcalculateFirstAndEnd(String ccalculateFirstAndEnd) {
		this.ccalculateFirstAndEnd = ccalculateFirstAndEnd;
	}
	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getInterestByOneTime() {
		return interestByOneTime;
	}
	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setInterestByOneTime(String interestByOneTime) {
		this.interestByOneTime = interestByOneTime;
	}
	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getPreposePayAccrual() {
		return preposePayAccrual;
	}
	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setPreposePayAccrual(String preposePayAccrual) {
		this.preposePayAccrual = preposePayAccrual;
	}
	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getDateModel() {
		return dateModel;
	}
	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setDateModel(String dateModel) {
		this.dateModel = dateModel;
	}
	/**
	 * <p> TODO</p>
	 * @return:     java.math.BigDecimal
	 */
	public java.math.BigDecimal getLoanInterestMoneyMonthRate() {
		return loanInterestMoneyMonthRate;
	}
	/** 
	 * <p> TODO</p>
	 * @return: java.math.BigDecimal
	 */
	public void setLoanInterestMoneyMonthRate(
			java.math.BigDecimal loanInterestMoneyMonthRate) {
		this.loanInterestMoneyMonthRate = loanInterestMoneyMonthRate;
	}
	/**
	 * <p> TODO</p>
	 * @return:     java.math.BigDecimal
	 */
	public java.math.BigDecimal getServiceMoneyMonthRate() {
		return serviceMoneyMonthRate;
	}
	/** 
	 * <p> TODO</p>
	 * @return: java.math.BigDecimal
	 */
	public void setServiceMoneyMonthRate(java.math.BigDecimal serviceMoneyMonthRate) {
		this.serviceMoneyMonthRate = serviceMoneyMonthRate;
	}
	/**
	 * <p> TODO</p>
	 * @return:     java.math.BigDecimal
	 */
	public java.math.BigDecimal getConsultationMoneyMonthRate() {
		return consultationMoneyMonthRate;
	}
	/** 
	 * <p> TODO</p>
	 * @return: java.math.BigDecimal
	 */
	public void setConsultationMoneyMonthRate(
			java.math.BigDecimal consultationMoneyMonthRate) {
		this.consultationMoneyMonthRate = consultationMoneyMonthRate;
	}
	/**
	 * <p> TODO</p>
	 * @return:     java.math.BigDecimal
	 */
	public java.math.BigDecimal getOtherOneFundMoneyMonthRate() {
		return otherOneFundMoneyMonthRate;
	}
	/** 
	 * <p> TODO</p>
	 * @return: java.math.BigDecimal
	 */
	public void setOtherOneFundMoneyMonthRate(
			java.math.BigDecimal otherOneFundMoneyMonthRate) {
		this.otherOneFundMoneyMonthRate = otherOneFundMoneyMonthRate;
	}
	/**
	 * <p> TODO</p>
	 * @return:     java.math.BigDecimal
	 */
	public java.math.BigDecimal getOtherTwoFundMoneyMonthRate() {
		return otherTwoFundMoneyMonthRate;
	}
	/** 
	 * <p> TODO</p>
	 * @return: java.math.BigDecimal
	 */
	public void setOtherTwoFundMoneyMonthRate(
			java.math.BigDecimal otherTwoFundMoneyMonthRate) {
		this.otherTwoFundMoneyMonthRate = otherTwoFundMoneyMonthRate;
	}
	/**
	 * <p> TODO</p>
	 * @return:     java.math.BigDecimal
	 */
	public java.math.BigDecimal getOtherTreeFundMoneyMonthRate() {
		return otherTreeFundMoneyMonthRate;
	}
	/** 
	 * <p> TODO</p>
	 * @return: java.math.BigDecimal
	 */
	public void setOtherTreeFundMoneyMonthRate(
			java.math.BigDecimal otherTreeFundMoneyMonthRate) {
		this.otherTreeFundMoneyMonthRate = otherTreeFundMoneyMonthRate;
	}
	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getServiceMoneyWay() {
		return serviceMoneyWay;
	}
	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setServiceMoneyWay(String serviceMoneyWay) {
		this.serviceMoneyWay = serviceMoneyWay;
	}
	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getConsultationMoneyWay() {
		return consultationMoneyWay;
	}
	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setConsultationMoneyWay(String consultationMoneyWay) {
		this.consultationMoneyWay = consultationMoneyWay;
	}
	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getOtherOneFundMoneyWay() {
		return otherOneFundMoneyWay;
	}
	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setOtherOneFundMoneyWay(String otherOneFundMoneyWay) {
		this.otherOneFundMoneyWay = otherOneFundMoneyWay;
	}
	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getOtherTwoFundMoneyWay() {
		return otherTwoFundMoneyWay;
	}
	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setOtherTwoFundMoneyWay(String otherTwoFundMoneyWay) {
		this.otherTwoFundMoneyWay = otherTwoFundMoneyWay;
	}
	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getOtherTreeFundMoneyWay() {
		return otherTreeFundMoneyWay;
	}
	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setOtherTreeFundMoneyWay(String otherTreeFundMoneyWay) {
		this.otherTreeFundMoneyWay = otherTreeFundMoneyWay;
	}
	
	
}
