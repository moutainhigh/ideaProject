package hry.cm2.remote.model;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class CmAccountRecordRemote  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@ApiModelProperty(value ="customerId")
	private Long customerId;  //用户id
	
	@ApiModelProperty(value ="币种")
	private String coinCode;  //币种
	
	@ApiModelProperty(value ="流水类型 （ 1增加 2减少）")
	private Integer recordType;  //流水类型 （ 1增加 2减少）
	
	@ApiModelProperty(value ="交易金额")
	private BigDecimal transactionMoney;  //交易金额
	
	@ApiModelProperty(value ="交易订单号")
	private String transactionNum;  //
	
	@ApiModelProperty(value ="交易备注")
	private String remark;  //交易备注
	
	@ApiModelProperty(value ="交易备注1,2,3")
	private Integer remarkkey;  //交易备注1,2,3
	
	@ApiModelProperty(value ="1热账户2冷账户3已用账户")
	private Integer monteyType;  //1热账户2冷账户3已用账户
	
	@ApiModelProperty(value ="交易时间")
	private Date created;  //交易时间
	
	

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getCoinCode() {
		return coinCode;
	}

	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getRemarkkey() {
		return remarkkey;
	}

	public void setRemarkkey(Integer remarkkey) {
		this.remarkkey = remarkkey;
	}

	public Integer getMonteyType() {
		return monteyType;
	}

	public void setMonteyType(Integer monteyType) {
		this.monteyType = monteyType;
	}
	
	

}
