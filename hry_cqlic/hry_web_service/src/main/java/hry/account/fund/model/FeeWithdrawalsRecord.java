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
 * 手续费账户提现的记录
 * <p> TODO</p>
 * @author:         Zhang Lei 
 * @Date :          2017年3月9日 上午11:37:34
 */
@SuppressWarnings("serial")
@Table(name="fee_withdrawals_record")
public class FeeWithdrawalsRecord extends BaseModel {
	// '主键id'
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	
	//我方账户的id
	@Column(name="ourAccountId")
	private Long ourAccountId;
	
	// 我方账户号
	@Column(name="ourAccountNum")
	private String ourAccountNum;
	
	//提现金额
	@Column(name="withdrawalsMoney")
	private BigDecimal withdrawalsMoney;
	
	// 流水状态(0成功  1失败)
	@Column(name="status")
	private Integer status;
	
	// 失败原因
	@Column(name="failMsg")
	private String failMsg;
	
	// 备注
	@Column(name="remark")
	private String remark;
	
	//审核人
	@Column(name="auditor")
	private String auditor;
	
	

	
	
	
	
	
	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getOurAccountNum() {
		return ourAccountNum;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setOurAccountNum(String ourAccountNum) {
		this.ourAccountNum = ourAccountNum;
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
	public Long getOurAccountId() {
		return ourAccountId;
	}

	/** 
	 * <p> TODO</p>
	 * @return: Long
	 */
	public void setOurAccountId(Long ourAccountId) {
		this.ourAccountId = ourAccountId;
	}

	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getWithdrawalsMoney() {
		return withdrawalsMoney;
	}

	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setWithdrawalsMoney(BigDecimal withdrawalsMoney) {
		this.withdrawalsMoney = withdrawalsMoney;
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
	public String getFailMsg() {
		return failMsg;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setFailMsg(String failMsg) {
		this.failMsg = failMsg;
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
	public String getAuditor() {
		return auditor;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setAuditor(String auditor) {
		this.auditor = auditor;
	}




	
	
	
}
