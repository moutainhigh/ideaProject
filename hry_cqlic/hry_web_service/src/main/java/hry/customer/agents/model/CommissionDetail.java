/**
 * Copyright:  北京互融时代软件有限公司
 * @author:    Wu Shuiming
 * @version:   V1.0 
 * @Date:      2016年7月18日 上午10:26:17
 */
package hry.customer.agents.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import hry.core.mvc.model.BaseModel;




/**
 * @author Wu shuiming
 * @date 2016年7月18日 上午10:26:17
 */
@SuppressWarnings("serial")
@Table(name="commission_detail")
public class CommissionDetail extends BaseModel {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	
	@Column(name="agentsId")
	private Long agentsId;  // 代理商id
	
	@Column(name="agentsName")
	private String agentsName;  // 代理商姓名
	
	@Column(name="orderNum")
	private String orderNum;  //  订单号
	
	@Column(name="deliveryName")
	private String deliveryName;  // 交款人姓名
	
	@Column(name="deliveryId")
	private Long deliveryId;  // 交款人id
	
	@Column(name="deliveryMoney")
	private BigDecimal deliveryMoney;  // 交款的总钱数
	
	@Column(name="agentsRank")
	private Integer agentsRank;  // 代理级别
	
	@Column(name="states")
	private Integer states;   // 状态(0或1 ---  默认是  1 表示成功  )
	
	@Column(name="category")
	private Integer category;  //  类型( 1 表示提现    21 交易买    22 交易买 ,4表示认购)
	
	@Column(name="rate")
	private BigDecimal rate;  //  费率的百分比
	
	@Column(name="totalFee")
	private BigDecimal totalFee; // 总共收的手续费
	
	@Column(name= "fixPriceCoinCode")
	private String fixPriceCoinCode;  //定价币种
	
	@Column(name= "fixPriceType")
	private Integer fixPriceType;  //0真实货币1虚拟币
	
	public BigDecimal getTotalFee() {
		return totalFee;
	}
	public void setTotalFee(BigDecimal totalFee) {
		this.totalFee = totalFee;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getAgentsId() {
		return agentsId;
	}
	public void setAgentsId(Long agentsId) {
		this.agentsId = agentsId;
	}
	public String getAgentsName() {
		return agentsName;
	}
	public void setAgentsName(String agentsName) {
		this.agentsName = agentsName;
	}
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	public String getDeliveryName() {
		return deliveryName;
	}
	public void setDeliveryName(String deliveryName) {
		this.deliveryName = deliveryName;
	}
	public Long getDeliveryId() {
		return deliveryId;
	}
	public void setDeliveryId(Long deliveryId) {
		this.deliveryId = deliveryId;
	}
	public BigDecimal getDeliveryMoney() {
		return deliveryMoney;
	}
	public void setDeliveryMoney(BigDecimal deliveryMoney) {
		this.deliveryMoney = deliveryMoney;
	}
	public Integer getAgentsRank() {
		return agentsRank;
	}
	public void setAgentsRank(Integer agentsRank) {
		this.agentsRank = agentsRank;
	}
	public Integer getStates() {
		return states;
	}
	public void setStates(Integer states) {
		this.states = states;
	}
	public Integer getCategory() {
		return category;
	}
	public void setCategory(Integer category) {
		this.category = category;
	}
	public BigDecimal getRate() {
		return rate;
	}
	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}
	
	@Override
	public String toString() {
		return "commissionDetail [id=" + id + ", agentsId=" + agentsId
				+ ", agentsName=" + agentsName + ", orderNum=" + orderNum
				+ ", deliveryName=" + deliveryName + ", deliveryId="
				+ deliveryId + ", deliveryMoney=" + deliveryMoney
				+ ", agentsRank=" + agentsRank + ", states=" + states
				+ ", category=" + category + ", rate=" + rate + "]";
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
