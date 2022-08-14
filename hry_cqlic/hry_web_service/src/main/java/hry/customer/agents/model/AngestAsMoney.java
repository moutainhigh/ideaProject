/**
 * Copyright:  北京互融时代软件有限公司
 * @author:    Wu Shuiming
 * @version:   V1.0 
 * @Date:      2016年8月4日 下午2:07:49
 */
package hry.customer.agents.model;

import hry.core.mvc.model.BaseModel;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

import static javax.persistence.GenerationType.IDENTITY;


/**
 * @author Wu shuiming
 * @date 2016年8月4日 下午2:07:49
 */
@Table(name="angest_as_money")
public class AngestAsMoney extends BaseModel{
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Long id;
	
	@Column(name = "agentName")
	private String agentName;
	
	@Column(name = "drawalMoney")
	private BigDecimal drawalMoney;
	
	@Column(name = "userName")
	private String userName;  // 操作人
	
	@Column(name = "trueName")
	private String trueName;
	
	@Column(name = "state")
	private Integer state;
	
	@Column(name= "fixPriceCoinCode")
	private String fixPriceCoinCode;  //定价币种
	@Column(name= "fixPriceType")
	private Integer fixPriceType;  //0真实货币1虚拟币
	@Column(name= "transactionNum")
	private String transactionNum;  
	
	//
	@Column(name= "customerId")
	private Long customerId;  
	


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
	
	public BigDecimal getDrawalMoney() {
		return drawalMoney;
	}
	
	public void setDrawalMoney(BigDecimal drawalMoney) {
		this.drawalMoney = drawalMoney;
	}
	
	public Integer getState() {
		return state;
	}
	
	public void setState(Integer state) {
		this.state = state;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}


	
	public AngestAsMoney(Long id, String agentName, BigDecimal drawalMoney,
			String userName, Integer state) {
		super();
		this.id = id;
		this.agentName = agentName;
		this.drawalMoney = drawalMoney;
		this.userName = userName;
		this.state = state;
	}

	public AngestAsMoney() {
		super();
	}

	public String getFixPriceCoinCode() {
		return fixPriceCoinCode;
	}

	public void setFixPriceCoinCode(String fixPriceCoinCode) {
		this.fixPriceCoinCode = fixPriceCoinCode;
	}

	public Integer getFixPriceType() {
		return fixPriceType;
	}

	public void setFixPriceType(Integer fixPriceType) {
		this.fixPriceType = fixPriceType;
	}
	
	
	
	
}	
