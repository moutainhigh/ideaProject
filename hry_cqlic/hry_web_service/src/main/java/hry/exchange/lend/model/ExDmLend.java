/**
 * Copyright:   北京互融时代软件有限公司
 * @author:       Gao Mimi 
 * @version:      V1.0 
 * @Date:        2016年5月23日 下午6:28:57
 */
package hry.exchange.lend.model;

import static javax.persistence.GenerationType.IDENTITY;
import hry.core.mvc.model.BaseExModel;
import hry.core.mvc.model.BaseModel;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
/**
 * 
 * <p> TODO</p>
 * @author:         Gao Mimi 
 * @Date :          2016年5月23日 下午6:28:57
 */
@SuppressWarnings("serial")
@Table(name = "ex_dm_lend")
public class ExDmLend extends BaseExModel {

	// 主键
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	// 借款单号
	@Column(name = "lendNum")
	private String lendNum;
	// 借款人
	@Column(name = "customerId")
	private Long customerId;
	// 客户编号
	@Column(name = "userCode")
	private String userCode;


	// 借款人的币账号id
	@Column(name = "accountId")
	private Long accountId;
	// 借的币种可以是money还是币种
	@Column(name = "lendCoinType")
	private String lendCoinType;
	// 借的币种可以是cny，可以是bit
	@Column(name = "lendCoin")
	private String lendCoin;
	// 借款数量
	@Column(name = "lendCount")
	private BigDecimal lendCount;
	// 已还借款金额
	@Column(name = "repayLendCount")
	private BigDecimal repayLendCount;
	// 借款时间
	@Column(name = "lendTime")
	private Date lendTime;
	// 借款利率
	@Column(name = "lendRate")
	private BigDecimal lendRate;
	// 1(借款中，2，部分还款款，3，全部还完)
	@Column(name = "status")
	private Integer status;
	//到目前为止一共产生的利息之和
	@Column(name = "interestCount")
	private BigDecimal interestCount;
	//已经还的利息之和
	@Column(name = "repayInterestCount")
	private BigDecimal repayInterestCount;
	//已借款的天数
	@Column(name = "lendDay")
	private Integer lendDay;
	// 利息计算的截至时间
	@Column(name = "interestcalEndTime")
	private Date interestcalEndTime;
	// 是否生成了款项0否1shi
	@Column(name = "isCreateIntent")
	private Integer isCreateIntent;
	// 是否允许部分还款
	@Column(name = "isPartRepay")
	private Integer isPartRepay;
	@Column(name = "userName")
	private String userName;
	@Column(name = "trueName")
	private String trueName;
	
	
	
	
	// 数据库没有
	@Transient
	private BigDecimal notrepayInterestCount;
	@Transient
	private BigDecimal notrepayLendCount;
	
	//已还总额
	@Transient
	private BigDecimal totalRepay;
	
	//应还总额
	@Transient
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

	public ExDmLend() {
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



	@Override
	public String toString() {
		return "EcEntrust [id=" + id + ", lendNum=" + lendNum
				+ ", customerId=" + customerId + ", accountId=" + accountId
				+ ", lendCount=" + lendCount + ", surplusLendCount="
				+ repayLendCount + ", lendRate=" + lendRate + ", lendTime="
				+ lendTime + ", status=" + status
			    + ", repayInterestCount=" + repayInterestCount
			    + ", interestcalEndTime=" + interestcalEndTime
			+ "]";
	}

}
