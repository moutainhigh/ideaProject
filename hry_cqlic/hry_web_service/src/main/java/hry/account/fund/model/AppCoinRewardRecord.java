package hry.account.fund.model;

import static javax.persistence.GenerationType.IDENTITY;
import hry.core.mvc.model.BaseModel;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 注册或推荐奖励币的记录
 * <p> TODO</p>
 * @author:         Zhang Lei 
 * @Date :          2017年3月9日 上午11:37:34
 */
@SuppressWarnings("serial")
@Table(name="app_coinreward_record")
public class AppCoinRewardRecord extends BaseModel {
	// '主键id'
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	
	//收到币的用户id
	@Column(name="customerId")
	private Long customerId;
	
	//收到币的用户登录名
	@Column(name="customerName")
	private String customerName;
	
	//收到币的用户的手机号
	@Column(name = "customerMobil")
	private String customerMobil;
		
	//类型     1实名得币  2推荐奖励得币  
	@Column(name="recordType")
	private Integer recordType;
	
	//类型     得币的个数
	@Column(name="recordNum")
	private BigDecimal recordNum;
	
	//被推荐人得用户ID
	@Column(name="coverCustomerId")
	private Long coverCustomerId;
	
	//被推荐人得用户手机号
	@Column(name="coverCustomerMobile")
	private String coverCustomerMobile;
	
	//被推荐人得用户名字
	@Column(name="coverCustomerName")
	private String coverCustomerName;
	
	// 流水状态(0成功  1失败)
	@Column(name="status")
	private Integer status;
	
	// 失败原因
	@Column(name="failMsg")
	private String failMsg;
	
	//交易流水信息的id，ExOrderInfo(这笔交易成功时，给卖币人的推荐人，派发推荐奖励了)
	//该字段作用不是太大
	@Column(name="exOrderInfoId")
	private Long exOrderInfoId;
	
	// 交易备注
	@Column(name="remark")
	private String remark;
	
	//审核人
	@Column(name="auditor")
	private String auditor;
	
	// 审核时间
	@Column(name="operationTime")
	private Date operationTime;
	


	
	
	
	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getCoverCustomerMobile() {
		return coverCustomerMobile;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setCoverCustomerMobile(String coverCustomerMobile) {
		this.coverCustomerMobile = coverCustomerMobile;
	}

	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getCoverCustomerName() {
		return coverCustomerName;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setCoverCustomerName(String coverCustomerName) {
		this.coverCustomerName = coverCustomerName;
	}

	public BigDecimal getRecordNum() {
		return recordNum;
	}

	public void setRecordNum(BigDecimal recordNum) {
		this.recordNum = recordNum;
	}

	public String getFailMsg() {
		return failMsg;
	}

	public void setFailMsg(String failMsg) {
		this.failMsg = failMsg;
	}

	public Long getExOrderInfoId() {
		return exOrderInfoId;
	}

	public void setExOrderInfoId(Long exOrderInfoId) {
		this.exOrderInfoId = exOrderInfoId;
	}

	public String getCustomerMobil() {
		return customerMobil;
	}

	public void setCustomerMobil(String customerMobil) {
		this.customerMobil = customerMobil;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Long getCoverCustomerId() {
		return coverCustomerId;
	}

	public void setCoverCustomerId(Long coverCustomerId) {
		this.coverCustomerId = coverCustomerId;
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

	
}
