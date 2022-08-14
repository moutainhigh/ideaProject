/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Wu Shuiming
 * @version:      V1.0 
 * @Date:        2016年6月16日 上午10:46:02
 */
package hry.account.fund.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import hry.core.mvc.model.BaseModel;
import hry.core.mvc.model.ModelUtil;

/**
 * <p> TODO</p>
 * @author:         Wu Shuiming
 * @Date :          2016年6月16日 上午10:46:02 
 */
@SuppressWarnings("serial")
@Table(name="app_account_record")
public class AppAccountRecord extends BaseModel {
		
	// '主键id'
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	
	//我方资金账户id（dm_account）
	@Column(name="appAccountId")
	private Long appAccountId;
	
	//我方资金账号
	@Column(name="appAccountNum")
	private String appAccountNum;
	
	//用户id
	@Column(name="customerId")
	private Long customerId;
	
	//用户登录名
	@Column(name="customerName")
	private String customerName;
	
	//流水类型     1充值 2提现 3充值手续费 4提现手续费5表示卖方手续费6表示买方手续费     
	//7表示注册实名奖励（1个币）   8推荐人推荐奖励   9 交易返佣   10.ico提现 11.ico充值
	@Column(name="recordType")
	private Integer recordType;
	
	//交易来源(0 表示线下充值  1 表示线上充值 2表示 3表示交易  4.ico线上)
	@Column(name="source")
	private Integer source;
	
	// 交易金额
	@Column(name="transactionMoney")
	private BigDecimal transactionMoney;
	
	// 交易订单号(业务订单号)
	@Column(name="transactionNum")
	private String transactionNum;
	
	// 流水状态( 0未审核 5已审核 10失败 )
	@Column(name="status")
	private Integer status;
	
	// 交易备注
	@Column(name="remark")
	private String remark;
	
	// 货币类型（人民币,美元 ）
	@Column(name="currencyName")
	private String currencyName;
	
	// 货币类型（RMB,USD ）
	@Column(name="currencyType")
	private String currencyType;
	
	//审核人
	@Column(name="auditor")
	private String auditor;
	
	// 审核时间
	@Column(name="operationTime")
	private Date operationTime;
	
	//用户账户
	@Column(name="customerAccount")
	private String customerAccount;
	
	@Column(name="website")
	private String website;//站点类别默认cn
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getAppAccountId() {
		return appAccountId;
	}
	public void setAppAccountId(Long appAccountId) {
		this.appAccountId = appAccountId;
	}
	public String getAppAccountNum() {
		return appAccountNum;
	}
	public void setAppAccountNum(String apAccountNum) {
		this.appAccountNum = apAccountNum;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public Integer getRecordType() {
		return recordType;
	}
	public void setRecordType(Integer recordType) {
		this.recordType = recordType;
	}
	public Integer getSource() {
		return source;
	}
	public void setSource(Integer source) {
		this.source = source;
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
	public String getCurrencyName() {
		return currencyName;
	}
	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}
	public String getCurrencyType() {
		return currencyType;
	}
	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}
	public String getAuditor() {
		return auditor;
	}
	public void setAuditor(String auditor) {
		this.auditor = auditor;
	}
	public Date getOperationTime() {
		return operationTime;
	}
	public void setOperationTime(Date operationTime) {
		this.operationTime = operationTime;
	}
	public String getCustomerAccount() {
		return customerAccount;
	}
	public void setCustomerAccount(String customerAccount) {
		this.customerAccount = customerAccount;
	}
	
	public AppAccountRecord(){}
	
	public AppAccountRecord(Long id, Long appAccountId, String appAccountNum,
			Long customerId, String customerName, Integer recordType,
			Integer source, BigDecimal transactionMoney, String transactionNum,
			Integer status, String remark, String currencyName,
			String currencyType, String auditor, Date operationTime,
			String customerAccount) {
		super();
		this.id = id;
		this.appAccountId = appAccountId;
		this.appAccountNum = appAccountNum;
		this.customerId = customerId;
		this.customerName = customerName;
		this.recordType = recordType;
		this.source = source;
		this.transactionMoney = transactionMoney;
		this.transactionNum = transactionNum;
		this.status = status;
		this.remark = remark;
		this.currencyName = currencyName;
		this.currencyType = currencyType;
		this.auditor = auditor;
		this.operationTime = operationTime;
		this.customerAccount = customerAccount;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	
	@Override
	public String toString() {
		return "AppAccountRecord [id=" + id + ", appAccountId=" + appAccountId
				+ ", apAccountNum=" + appAccountNum + ", customerId="
				+ customerId + ", customerName=" + customerName
				+ ", recordType=" + recordType + ", source=" + source
				+ ", transactionMoney=" + transactionMoney
				+ ", transactionNum=" + transactionNum + ", status=" + status
				+ ", remark=" + remark + ", currencyName=" + currencyName
				+ ", currencyType=" + currencyType + ", auditor=" + auditor
				+ ", operationTime=" + operationTime + ", customerAccount="
				+ customerAccount + "]";
	}
	
	
	
}
