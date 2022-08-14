/**
 * Copyright:  北京互融时代软件有限公司
 * @author:    Wu Shuiming
 * @version:   V1.0 
 * @Date:      2016年7月21日 上午10:19:19
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
 * @date 2016年7月21日 上午10:19:19
 */
@SuppressWarnings("serial")
@Table(name="app_trade_factorage")
public class AppTradeFactorage extends BaseModel{

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "custromerId")
	private Long custromerId;  // 订单申请人id
	
	@Column(name = "custromerName")
	private String custromerName;  // 订单申请人姓名
	
	@Column(name = "ordertNum")
	private String ordertNum;  // 成交单号
	
	@Column(name = "oneParentName")
	private String oneParentName;  // 一级父姓名
	
	@Column(name = "oneParentMoney")
	private BigDecimal oneParentMoney;  //'给一级父交的钱'	
	
	@Column(name = "oneCommissionRate")
	private BigDecimal oneCommissionRate;  // 一级佣金费率
	
	@Column(name = "twoParentName")
	private String twoParentName;  // 二级父姓名
	
	@Column(name = "twoParentMoney")
	private BigDecimal twoParentMoney;  // 给二级父交的钱
	
	@Column(name = "twoCommissionRate")
	private BigDecimal twoCommissionRate;  // 二级父的费率
	
	@Column(name = "threeParentName")
	private String threeParentName; // 三级父姓名
	
	@Column(name = "threeParentMoney")
	private BigDecimal threeParentMoney;  // 给三级父交的钱
	
	@Column(name = "threeCommissionRate")
	private BigDecimal threeCommissionRate;  // 给三级父的佣金费率
	
	
	@Column(name= "fixPriceCoinCode")
	private String fixPriceCoinCode;  //定价币种
	@Column(name= "fixPriceType")
	private Integer fixPriceType;  //0真实货币1虚拟币
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getCustromerId() {
		return custromerId;
	}
	public void setCustromerId(Long custromerId) {
		this.custromerId = custromerId;
	}
	public String getCustromerName() {
		return custromerName;
	}
	public void setCustromerName(String custromerName) {
		this.custromerName = custromerName;
	}
	public String getOrdertNum() {
		return ordertNum;
	}
	public void setOrdertNum(String ordertNum) {
		this.ordertNum = ordertNum;
	}
	public String getOneParentName() {
		return oneParentName;
	}
	public void setOneParentName(String oneParentName) {
		this.oneParentName = oneParentName;
	}
	public BigDecimal getOneParentMoney() {
		return oneParentMoney;
	}
	public void setOneParentMoney(BigDecimal oneParentMoney) {
		this.oneParentMoney = oneParentMoney;
	}
	public BigDecimal getOneCommissionRate() {
		return oneCommissionRate;
	}
	public void setOneCommissionRate(BigDecimal oneCommissionRate) {
		this.oneCommissionRate = oneCommissionRate;
	}
	public String getTwoParentName() {
		return twoParentName;
	}
	public void setTwoParentName(String twoParentName) {
		this.twoParentName = twoParentName;
	}
	public BigDecimal getTwoParentMoney() {
		return twoParentMoney;
	}
	public void setTwoParentMoney(BigDecimal twoParentMoney) {
		this.twoParentMoney = twoParentMoney;
	}
	public BigDecimal getTwoCommissionRate() {
		return twoCommissionRate;
	}
	public void setTwoCommissionRate(BigDecimal twoCommissionRate) {
		this.twoCommissionRate = twoCommissionRate;
	}
	public String getThreeParentName() {
		return threeParentName;
	}
	public void setThreeParentName(String threeParentName) {
		this.threeParentName = threeParentName;
	}
	public BigDecimal getThreeParentMoney() {
		return threeParentMoney;
	}
	public void setThreeParentMoney(BigDecimal threeParentMoney) {
		this.threeParentMoney = threeParentMoney;
	}
	public BigDecimal getThreeCommissionRate() {
		return threeCommissionRate;
	}
	public void setThreeCommissionRate(BigDecimal threeCommissionRate) {
		this.threeCommissionRate = threeCommissionRate;
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
	@Override
	public String toString() {
		return "appTradeFactorage [id=" + id + ", custromerId=" + custromerId
				+ ", custromerName=" + custromerName + ", ordertNum="
				+ ordertNum + ", oneParentName=" + oneParentName
				+ ", oneParentMoney=" + oneParentMoney + ", oneCommissionRate="
				+ oneCommissionRate + ", twoParentName=" + twoParentName
				+ ", twoParentMoney=" + twoParentMoney + ", twoCommissionRate="
				+ twoCommissionRate + ", threeParentName=" + threeParentName
				+ ", threeParentMoney=" + threeParentMoney
				+ ", threeCommissionRate=" + threeCommissionRate + "]";
	}
	
	
}







