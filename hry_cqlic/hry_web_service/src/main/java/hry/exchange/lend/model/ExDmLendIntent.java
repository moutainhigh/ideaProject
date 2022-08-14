/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Gao Mimi 
 * @version:      V1.0 
 * @Date:       2016年5月23日 下午6:28:57
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
@Table(name = "ex_dm_lend_intent")
public class ExDmLendIntent extends BaseExModel {

	// 主键
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	// 借款单号
	@Column(name = "intentNum")
	private String intentNum;
	// 借款人的币账号id
	@Column(name = "lendId")
	private Long lendId;
	// 借款人
	@Column(name = "customerId")
	private Long customerId;
	// 借款人的币账号id
	@Column(name = "accountId")
	private Long accountId;
	// 借的币种可以是money还是币种virtualCoin
	@Column(name = "lendCoinType")
	private String lendCoinType;
	// 借的币种可以是cny，可以是bit
	@Column(name = "lendCoin")
	private String lendCoin;
	// 应还币时间
	@Column(name = "intentTime")
	private Date intentTime;
	// 实际还币时间
	@Column(name = "factTime")
	private Date factTime;
	// 还币的数量
	@Column(name = "repayCount")
	private BigDecimal repayCount;
	// interest利息principal本金
	@Column(name = "intentType")
	private String intentType;
	
	@Column(name = "userName")
	private String userName;
	
	@Column(name = "trueName")
	private String trueName;


	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getTrueName() {
		return trueName;
	}

	public void setTrueName(String trueName) {
		this.trueName = trueName;
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
	 * @return:     String
	 */
	public String getIntentNum() {
		return intentNum;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setIntentNum(String intentNum) {
		this.intentNum = intentNum;
	}

	/**
	 * <p> TODO</p>
	 * @return:     Long
	 */
	public Long getLendId() {
		return lendId;
	}

	/** 
	 * <p> TODO</p>
	 * @return: Long
	 */
	public void setLendId(Long lendId) {
		this.lendId = lendId;
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
	 * @return:     Date
	 */
	public Date getIntentTime() {
		return intentTime;
	}

	/** 
	 * <p> TODO</p>
	 * @return: Date
	 */
	public void setIntentTime(Date intentTime) {
		this.intentTime = intentTime;
	}

	/**
	 * <p> TODO</p>
	 * @return:     Date
	 */
	public Date getFactTime() {
		return factTime;
	}

	/** 
	 * <p> TODO</p>
	 * @return: Date
	 */
	public void setFactTime(Date factTime) {
		this.factTime = factTime;
	}

	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getRepayCount() {
		return repayCount;
	}

	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setRepayCount(BigDecimal repayCount) {
		this.repayCount = repayCount;
	}


	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getIntentType() {
		return intentType;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setIntentType(String intentType) {
		this.intentType = intentType;
	}

	public ExDmLendIntent() {
		super();
	}

	@Override
	public String toString() {
		return "EcEntrust [id=" + id + ", intentNum=" + intentNum
				+ ", customerId=" + customerId + ", accountId=" + accountId
				+  ", repayCount=" + repayCount + ", intentType="
				+ intentType + ", factTime=" + factTime + ", intentTime=" + intentTime 
			+ "]";
	}

}
