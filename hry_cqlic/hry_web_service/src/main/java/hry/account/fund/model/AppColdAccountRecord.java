/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Liu Shilei
 * @version:      V1.0 
 * @Date:        2016年3月31日 下午6:27:49
 */
package hry.account.fund.model;

import hry.core.mvc.model.BaseModel;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * <p> TODO</p>
 * @author:         Liu Shilei 
 * @Date :          2016年3月31日 下午6:27:49 
 */
@Table(name="app_cold_account_record")
public class AppColdAccountRecord extends BaseModel {
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	
	@Column(name="accountId")
	private Long accountId;  //账户ID
	
	@Column(name="customerId")
	private Long customerId;  //用户ID
	
	@Column(name="userName")
	private String userName;  //用户账号
	
	@Column(name="recordType")
	private Integer recordType;   //流水类型   1增加  2减少
	 
	@Column(name="transactionMoney")
	private BigDecimal transactionMoney;  //交易金额
	
	@Column(name="transactionNum")
	private String transactionNum;  //业务单号
	
	@Column(name="status")
	private Integer status;   //0处理中   5成功  10失败
	
	@Column(name="remark")
	private String remark; //交易备注
	
	@Column(name="currencyType")
	private String currencyType; //货币类型
	
	@Column(name="website")
	private String website;//站点类别默认cn
	
	@Column(name="trueName")
	private String trueName;

	@Column(name="created")
	private Date created;

	@Column(name="balanceMoney")
	private BigDecimal balanceMoney;

	@Column(name="orderNum")
	private String orderNum;

	public BigDecimal getBalanceMoney () {
		return balanceMoney;
	}

	public void setBalanceMoney (BigDecimal balanceMoney) {
		this.balanceMoney = balanceMoney;
	}

	public String getOrderNum () {
		return orderNum;
	}

	public void setOrderNum (String orderNum) {
		this.orderNum = orderNum;
	}
	@Override
	public Date getCreated() {
		return created;
	}

	@Override
	public void setCreated(Date created) {
		this.created = created;
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
	 * @return:     String
	 */
	public String getUserName() {
		return userName;
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
	 * @return:     Integer
	 */
	public Integer getRecordType() {
		return recordType;
	}

	/** 
	 * <p> TODO</p>
	 * @return: Integer
	 */
	public void setRecordType(Integer recordType) {
		this.recordType = recordType;
	}

	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getTransactionMoney() {
		return transactionMoney;
	}

	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setTransactionMoney(BigDecimal transactionMoney) {
		this.transactionMoney = transactionMoney;
	}

	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getTransactionNum() {
		return transactionNum;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setTransactionNum(String transactionNum) {
		this.transactionNum = transactionNum;
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

	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getRemark() {
		return remark;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getCurrencyType() {
		return currencyType;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}
	
	
	
	
}
