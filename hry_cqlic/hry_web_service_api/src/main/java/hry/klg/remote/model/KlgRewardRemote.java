package hry.klg.remote.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.mysql.fabric.xmlrpc.base.Data;

public class KlgRewardRemote implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;  //
	
	private Long customerId;  //用户id
	
	private String transactionNum;  //流水号
	
	private String sellTransactionNum;  //卖单流水号
	
	private Long accountId;  //数字货币账户id
	
	private String coinCode;  //奖励币种
	
	private BigDecimal rewardMoney;  //奖励数量
	
	private Integer rewardType;  //奖励类型 1见点奖 2级差奖
	
	private String saasId;  //saasId
	
	private String remark;  //
	
	private Date created;  //
	
	

	

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
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

	public String getTransactionNum() {
		return transactionNum;
	}

	public void setTransactionNum(String transactionNum) {
		this.transactionNum = transactionNum;
	}

	public String getSellTransactionNum() {
		return sellTransactionNum;
	}

	public void setSellTransactionNum(String sellTransactionNum) {
		this.sellTransactionNum = sellTransactionNum;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public String getCoinCode() {
		return coinCode;
	}

	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}

	public BigDecimal getRewardMoney() {
		return rewardMoney;
	}

	public void setRewardMoney(BigDecimal rewardMoney) {
		this.rewardMoney = rewardMoney;
	}

	public Integer getRewardType() {
		return rewardType;
	}

	public void setRewardType(Integer rewardType) {
		this.rewardType = rewardType;
	}

	public String getSaasId() {
		return saasId;
	}

	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	

}
