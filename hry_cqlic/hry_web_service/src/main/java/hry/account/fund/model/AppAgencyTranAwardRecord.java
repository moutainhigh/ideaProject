package hry.account.fund.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.math.BigDecimal;

import hry.core.mvc.model.BaseModel;
import hry.customer.person.model.AppPersonInfo;
import hry.trade.entrust.model.ExOrderInfo;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 卖币交易给推荐人返佣记录
 * 
 * <p>
 * TODO
 * </p>
 * 
 * @author: Zhang Lei
 * @Date : 2017年3月9日 上午11:37:34
 */
@SuppressWarnings("serial")
@Table(name = "app_agencytranaward_record")
public class AppAgencyTranAwardRecord extends BaseModel {

	// '主键id'
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "agentId")
	private Long agentId;// 推荐人的personinfoid
	
	@Column(name = "exOrderInfoId")
	private Long exOrderInfoId;// 交易流水的Id
	
	@Column(name = "sellPersonId")
	private Long sellPersonId;// 卖币人的personid（不是customerid）
	
	//返佣金额
	@Column(name="awardMoney")
	private BigDecimal awardMoney;
	
	// 流水状态(0成功  1失败)
	@Column(name="status")
	private Integer status;
	
	// 失败原因
	@Column(name="failMsg")
	private String failMsg;
	
	
	
	@Transient
	private AppPersonInfo agentPerson;// 推荐人
	
	@Transient
	private AppPersonInfo sellPerson;// 卖币人人
	
	@Transient
	private ExOrderInfo exOrderInfo;// 交易流水

	
	
	
	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getAwardMoney() {
		return awardMoney;
	}

	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setAwardMoney(BigDecimal awardMoney) {
		this.awardMoney = awardMoney;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAgentId() {
		return agentId;
	}

	public void setAgentId(Long agentId) {
		this.agentId = agentId;
	}

	public Long getExOrderInfoId() {
		return exOrderInfoId;
	}

	public void setExOrderInfoId(Long exOrderInfoId) {
		this.exOrderInfoId = exOrderInfoId;
	}

	public Long getSellPersonId() {
		return sellPersonId;
	}

	public void setSellPersonId(Long sellPersonId) {
		this.sellPersonId = sellPersonId;
	}

	public AppPersonInfo getAgentPerson() {
		return agentPerson;
	}

	public void setAgentPerson(AppPersonInfo agentPerson) {
		this.agentPerson = agentPerson;
	}

	public AppPersonInfo getSellPerson() {
		return sellPerson;
	}

	public void setSellPerson(AppPersonInfo sellPerson) {
		this.sellPerson = sellPerson;
	}

	public ExOrderInfo getExOrderInfo() {
		return exOrderInfo;
	}

	public void setExOrderInfo(ExOrderInfo exOrderInfo) {
		this.exOrderInfo = exOrderInfo;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getFailMsg() {
		return failMsg;
	}

	public void setFailMsg(String failMsg) {
		this.failMsg = failMsg;
	}

}
