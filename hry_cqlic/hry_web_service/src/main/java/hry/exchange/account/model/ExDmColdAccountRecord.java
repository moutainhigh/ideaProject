/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Wu Shuiming
 * @version:      V1.0 
 * @Date:        2016年3月28日 下午5:36:56
 */
package hry.exchange.account.model;

import hry.core.mvc.model.BaseModel;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * <p>
 * TODO
 * </p>
 * 
 * @author: Wu Shuiming
 * @Date : 2016年3月28日 下午5:36:56
 */
@SuppressWarnings("serial")
@Table(name = "ex_dm_cold_account_record")
public class ExDmColdAccountRecord extends BaseModel {

	// 主键
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	// 数字货币账户id（dm_account）
	@Column(name = "accountId")
	private Long accountId;
	// 用户id
	@Column(name = "customerId")
	private Long customerId;
	// 用户登录名
	@Column(name = "userName")
	private String userName;
	
	@Column(name = "trueName")
	private String trueName;
	
	// 流水类型 （ 1增加 2减少）
	@Column(name = "recordType")
	private Integer recordType;
	// 交易金额
	@Column(name = "transactionMoney")
	private BigDecimal transactionMoney;
	// 交易订单号(业务订单号) 不能做唯一性约束
	@Column(name = "transactionNum")
	private String transactionNum;
	// 流水状态( 0处理中 5成功 10失败 )
	@Column(name = "status")
	private Integer status;
	// 交易备注
	@Column(name = "remark")
	private String remark;
	
	//交易账户类型cny usd
	@Column(name = "currencyType")
	private String currencyType;
	// 币的类型btc ltc
	@Column(name = "coinCode")
	private String coinCode;
	
	//站点类别 en ,cn
	@Column(name = "website")
	private String website;

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
	public String getTrueName() {
		return trueName;
	}

	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getRecordType() {
		return recordType;
	}

	public void setRecordType(Integer recordType) {
		this.recordType = recordType;
	}

	public BigDecimal getTransactionMoney() {
		return transactionMoney;
	}

	public void setTransactionMoney(BigDecimal transactionMoney) {
		this.transactionMoney = transactionMoney;
	}

	public String getTransactionNum() {
		return transactionNum;
	}

	public void setTransactionNum(String transactionNum) {
		this.transactionNum = transactionNum;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}

	public ExDmColdAccountRecord(Long id, Long accountId, Long customerId,
			String userName, Integer recordType, BigDecimal transactionMoney,
			String transactionNum, Integer status, String remark,
			String currencyType) {
		super();
		this.id = id;
		this.accountId = accountId;
		this.customerId = customerId;
		this.userName = userName;
		this.recordType = recordType;
		this.transactionMoney = transactionMoney;
		this.transactionNum = transactionNum;
		this.status = status;
		this.remark = remark;
		this.currencyType = currencyType;
	}

	public ExDmColdAccountRecord() {
		super();
	}

	@Override
	public String toString() {
		return "ExDmColdAccountRecord [id=" + id + ", accountId=" + accountId
				+ ", customerId=" + customerId + ", userName=" + userName
				+ ", recordType=" + recordType + ", transactionMoney="
				+ transactionMoney + ", transactionNum=" + transactionNum
				+ ", status=" + status + ", remark=" + remark
				+ ", currencyType=" + currencyType + "]";
	}

	public String getCoinCode() {
		return coinCode;
	}

	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}
	
	
}
