/**
 * Copyright:  北京互融时代软件有限公司
 * @author:    Wu Shuiming
 * @version:   V1.0 
 * @Date:      2016年7月15日 上午11:08:41
 */
package hry.customer.agents.model;

import static javax.persistence.GenerationType.IDENTITY;
import hry.core.mvc.model.BaseModel;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Wu shuiming
 * @date 2016年7月15日 上午11:08:41
 */
@SuppressWarnings("serial")
@Table(name="commission_deploy")
public class CommissionDeploy extends BaseModel{

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	
	@Column(name="costName")
	private String costName;
	
	@Column(name="costId")
	private Integer costId;
	
	@Column(name="oneStandardValue")
	private BigDecimal oneStandardValue;
	
	@Column(name="oneRankRatio")
	private BigDecimal oneRankRatio;
	
	@Column(name="twoStandardValue")
	private BigDecimal twoStandardValue;
	
	@Column(name="twoRankRatio")
	private BigDecimal twoRankRatio;
	
	@Column(name="threeStandardValue")
	private BigDecimal threeStandardValue;
	
	@Column(name="threeRankRatio")
	private BigDecimal threeRankRatio;
	
	@Column(name="settlementMoney")
	private BigDecimal settlementMoney;
	
	@Column(name="states")
	private Integer states;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCostName() {
		return costName;
	}
	public void setCostName(String costName) {
		this.costName = costName;
	}
	public Integer getCostId() {
		return costId;
	}
	public void setCostId(Integer costId) {
		this.costId = costId;
	}
	public BigDecimal getOneStandardValue() {
		return oneStandardValue;
	}
	public void setOneStandardValue(BigDecimal oneStandardValue) {
		this.oneStandardValue = oneStandardValue;
	}

	public BigDecimal getTwoStandardValue() {
		return twoStandardValue;
	}
	public void setTwoStandardValue(BigDecimal twoStandardValue) {
		this.twoStandardValue = twoStandardValue;
	}

	public BigDecimal getThreeStandardValue() {
		return threeStandardValue;
	}
	public void setThreeStandardValue(BigDecimal threeStandardValue) {
		this.threeStandardValue = threeStandardValue;
	}

	public BigDecimal getSettlementMoney() {
		return settlementMoney;
	}
	public void setSettlementMoney(BigDecimal settlementMoney) {
		this.settlementMoney = settlementMoney;
	}


	public Integer getStates() {
		return states;
	}
	public void setStates(Integer states) {
		this.states = states;
	}

	public BigDecimal getOneRankRatio() {
		return oneRankRatio;
	}
	public void setOneRankRatio(BigDecimal oneRankRatio) {
		this.oneRankRatio = oneRankRatio;
	}
	public BigDecimal getTwoRankRatio() {
		return twoRankRatio;
	}
	public void setTwoRankRatio(BigDecimal twoRankRatio) {
		this.twoRankRatio = twoRankRatio;
	}
	public BigDecimal getThreeRankRatio() {
		return threeRankRatio;
	}
	public void setThreeRankRatio(BigDecimal threeRankRatio) {
		this.threeRankRatio = threeRankRatio;
	}

	
	
	
}
