/**
 * Copyright:   北京互融时代软件有限公司
 * @author:       Gao Mimi 
 * @version:      V1.0 
 * @Date:        2016年5月23日 下午6:28:57
 */
package hry.manage.remote.model;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * <p> TODO</p>
 * @author:         Gao Mimi 
 * @Date :          2016年5月23日 下午6:28:57
 */
public class Lend  implements Serializable{

	private Long id;
	// 借款单号
	private String lendNum;
	// 借款人
	private Long customerId;
	// 客户编号
	private String userCode;


	// 借款人的币账号id
	private Long accountId;
	// 借的币种可以是1借钱2借币
	private String lendCoinType;
	// 借的币种可以是cny，可以是bit
	private String lendCoin;
	// 借款数量
	private BigDecimal lendCount;
	// 已还借款金额
	private BigDecimal repayLendCount;
	// 借款时间
	private Date lendTime;
	// 借款利率
	private BigDecimal lendRate;
	// 1(借款中，2，部分还款款，3，全部还完)
	private Integer status;
	//到目前为止一共产生的利息之和
	private BigDecimal interestCount;
	//已经还的利息之和
	private BigDecimal repayInterestCount;
	//已借款的天数
	private Integer lendDay;
	// 利息计算的截至时间
	private Date interestcalEndTime;
	// 是否生成了款项0否1shi
	private Integer isCreateIntent;
	// 是否允许部分还款
	private Integer isPartRepay;
	private String userName;
	private String trueName;
	
	
	
	
	private BigDecimal notrepayInterestCount;
	private BigDecimal notrepayLendCount;
	
	//已还总额
	private BigDecimal totalRepay;
	
	//应还总额
	private BigDecimal totalCount;
	
  /**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getTotalRepay() {
		return totalRepay;
	}



	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getTotalCount() {
		return totalCount;
	}



	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setTotalRepay(BigDecimal totalRepay) {
		this.totalRepay = totalRepay;
	}



	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setTotalCount(BigDecimal totalCount) {
		this.totalCount = totalCount;
	}



	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getUserCode() {
		return userCode;
	}



	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getLendCoin() {
		return lendCoin;
	}



	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setLendCoin(String lendCoin) {
		this.lendCoin = lendCoin;
	}



	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getUserName() {
		return userName;
	}



	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getLendCoinType() {
		return lendCoinType;
	}



	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setLendCoinType(String lendCoinType) {
		this.lendCoinType = lendCoinType;
	}



	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getNotrepayInterestCount() {
		return notrepayInterestCount;
	}

	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setNotrepayInterestCount(BigDecimal notrepayInterestCount) {
		this.notrepayInterestCount = notrepayInterestCount;
	}

	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getNotrepayLendCount() {
		return notrepayLendCount;
	}

	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setNotrepayLendCount(BigDecimal notrepayLendCount) {
		this.notrepayLendCount = notrepayLendCount;
	}

	/**
	 * <p> TODO</p>
	 * @return:     Integer
	 */
	public Integer getIsPartRepay() {
		return isPartRepay;
	}

	/** 
	 * <p> TODO</p>
	 * @return: Integer
	 */
	public void setIsPartRepay(Integer isPartRepay) {
		this.isPartRepay = isPartRepay;
	}

	/**
	 * <p> TODO</p>
	 * @return:     Date
	 */
	public Date getInterestcalEndTime() {
		return interestcalEndTime;
	}

	/**
	 * <p> TODO</p>
	 * @return:     Integer
	 */
	public Integer getIsCreateIntent() {
		return isCreateIntent;
	}

	/** 
	 * <p> TODO</p>
	 * @return: Integer
	 */
	public void setIsCreateIntent(Integer isCreateIntent) {
		this.isCreateIntent = isCreateIntent;
	}

	/** 
	 * <p> TODO</p>
	 * @return: Date
	 */
	public void setInterestcalEndTime(Date interestcalEndTime) {
		this.interestcalEndTime = interestcalEndTime;
	}

	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getRepayInterestCount() {
		return repayInterestCount;
	}

	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setRepayInterestCount(BigDecimal repayInterestCount) {
		this.repayInterestCount = repayInterestCount;
	}

	/**
	 * <p> TODO</p>
	 * @return:     Long
	 */
	public Long getId() {
		return id;
	}

	/** 
	 * <p> TODO</p>
	 * @return: Long
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getInterestCount() {
		return interestCount;
	}

	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setInterestCount(BigDecimal interestCount) {
		this.interestCount = interestCount;
	}

	/**
	 * <p> TODO</p>
	 * @return:     Integer
	 */
	public Integer getLendDay() {
		return lendDay;
	}

	/** 
	 * <p> TODO</p>
	 * @return: Integer
	 */
	public void setLendDay(Integer lendDay) {
		this.lendDay = lendDay;
	}

	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getLendNum() {
		return lendNum;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setLendNum(String lendNum) {
		this.lendNum = lendNum;
	}

	/**
	 * <p> TODO</p>
	 * @return:     Long
	 */
	public Long getCustomerId() {
		return customerId;
	}

	/** 
	 * <p> TODO</p>
	 * @return: Long
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	/**
	 * <p> TODO</p>
	 * @return:     Long
	 */
	public Long getAccountId() {
		return accountId;
	}

	/** 
	 * <p> TODO</p>
	 * @return: Long
	 */
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}


	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getLendCount() {
		return lendCount;
	}

	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setLendCount(BigDecimal lendCount) {
		this.lendCount = lendCount;
	}



	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getRepayLendCount() {
		return repayLendCount;
	}

	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setRepayLendCount(BigDecimal repayLendCount) {
		this.repayLendCount = repayLendCount;
	}

	/**
	 * <p> TODO</p>
	 * @return:     Date
	 */
	public Date getLendTime() {
		return lendTime;
	}

	/** 
	 * <p> TODO</p>
	 * @return: Date
	 */
	public void setLendTime(Date lendTime) {
		this.lendTime = lendTime;
	}




	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getLendRate() {
		return lendRate;
	}

	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setLendRate(BigDecimal lendRate) {
		this.lendRate = lendRate;
	}

	/**
	 * <p> TODO</p>
	 * @return:     Integer
	 */
	public Integer getStatus() {
		return status;
	}

	/** 
	 * <p> TODO</p>
	 * @return: Integer
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	public Lend() {
		super();
	}
	
	



	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getTrueName() {
		return trueName;
	}



	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}




}
