package hry.customer.agents.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import hry.core.mvc.model.BaseModel;

@SuppressWarnings("serial")
@Table(name="app_agents_custromer")
public class AppAgentsCustromer extends BaseModel {


	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	
	@Column(name="agentName")
	private String agentName;
	
	@Column(name="address")
	private String address;
	
	@Column(name="customerName")
	private String customerName;
	
	@Column(name="trueName")
	private String trueName;
	
	@Column(name="customerId")
	private Long customerId;
	
	@Column(name="papersType")
	private String papersType;
	
	@Column(name="papersNo")
	private String papersNo;
	
	@Column(name="eagerRelationName")
	private String eagerRelationName;
	
	@Column(name="eagerRelationPhone")
	private String eagerRelationPhone;
	
	@Column(name="versoIDCard")
	private String versoIDCard;
	
	@Column(name="frontIDCard")
	private String frontIDCard;
	
	@Column(name="versolBank")
	private String versolBank;
	
	@Column(name="frontBank")
	private String frontBank;
	
	@Column(name="brokerage")
	private BigDecimal brokerage;
	
	@Column(name="recommendCode")
	private String recommendCode;
	
	// 状态( 1 表示申请中  2 表示申请通过 3 表示已删除)
	@Column(name="states")
	private Integer states;
	
	@Column(name="sex")
	private String sex;
	
	@Column(name="surname")
	private String surname;
	
	@Column(name="handIDCard")
	private String handIDCard;

	@Column(name="modified")
	private Date modified;
	
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

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getPapersType() {
		return papersType;
	}

	public void setPapersType(String papersType) {
		this.papersType = papersType;
	}

	public String getPapersNo() {
		return papersNo;
	}

	public void setPapersNo(String papersNo) {
		this.papersNo = papersNo;
	}

	public String getEagerRelationName() {
		return eagerRelationName;
	}

	public void setEagerRelationName(String eagerRelationName) {
		this.eagerRelationName = eagerRelationName;
	}

	public String getEagerRelationPhone() {
		return eagerRelationPhone;
	}

	public void setEagerRelationPhone(String eagerRelationPhone) {
		this.eagerRelationPhone = eagerRelationPhone;
	}

	public String getVersoIDCard() {
		return versoIDCard;
	}

	public void setVersoIDCard(String versoIDCard) {
		this.versoIDCard = versoIDCard;
	}

	public String getFrontIDCard() {
		return frontIDCard;
	}

	public void setFrontIDCard(String frontIDCard) {
		this.frontIDCard = frontIDCard;
	}

	public String getVersolBank() {
		return versolBank;
	}

	public void setVersolBank(String versolBank) {
		this.versolBank = versolBank;
	}

	public String getFrontBank() {
		return frontBank;
	}

	public void setFrontBank(String frontBank) {
		this.frontBank = frontBank;
	}

	public BigDecimal getBrokerage() {
		return brokerage;
	}

	public void setBrokerage(BigDecimal brokerage) {
		this.brokerage = brokerage;
	}

	public String getRecommendCode() {
		return recommendCode;
	}

	public void setRecommendCode(String recommendCode) {
		this.recommendCode = recommendCode;
	}

	public Integer getStates() {
		return states;
	}

	public void setStates(Integer states) {
		this.states = states;
	}
	
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getHandIDCard() {
		return handIDCard;
	}

	public void setHandIDCard(String handIDCard) {
		this.handIDCard = handIDCard;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

	public AppAgentsCustromer(Long id, String agentName, String address,
			String customerName, Long customerId, String papersType,
			String papersNo, String eagerRelationName,
			String eagerRelationPhone, String versoIDCard, String frontIDCard,
			String versolBank, String frontBank, BigDecimal brokerage,
			String recommendCode, Integer states) {
		super();
		this.id = id;
		this.agentName = agentName;
		this.address = address;
		this.customerName = customerName;
		this.customerId = customerId;
		this.papersType = papersType;
		this.papersNo = papersNo;
		this.eagerRelationName = eagerRelationName;
		this.eagerRelationPhone = eagerRelationPhone;
		this.versoIDCard = versoIDCard;
		this.frontIDCard = frontIDCard;
		this.versolBank = versolBank;
		this.frontBank = frontBank;
		this.brokerage = brokerage;
		this.recommendCode = recommendCode;
		this.states = states;
	}

	public AppAgentsCustromer() {
		super();
	}

	@Override
	public String toString() {
		return "AppAgentsCustromer [id=" + id + ", agentName=" + agentName
				+ ", address=" + address + ", customerName=" + customerName
				+ ", customerId=" + customerId + ", papersType=" + papersType
				+ ", papersNo=" + papersNo + ", eagerRelationName="
				+ eagerRelationName + ", eagerRelationPhone="
				+ eagerRelationPhone + ", versoIDCard=" + versoIDCard
				+ ", frontIDCard=" + frontIDCard + ", versolBank=" + versolBank
				+ ", frontBank=" + frontBank + ", brokerage=" + brokerage
				+ ", recommendCode=" + recommendCode + ", states=" + states + "]";
	}

	
}
