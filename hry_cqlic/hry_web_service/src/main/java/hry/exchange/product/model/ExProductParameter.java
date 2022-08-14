/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Wu Shuiming
 * @version:      V1.0 
 * @Date:        2016年6月7日 下午2:33:47
 */
package hry.exchange.product.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import hry.core.mvc.model.BaseModel;

/**
 * <p> TODO</p>
 * @author:         Wu Shuiming
 * @Date :          2016年6月7日 下午2:33:47 
 */
@SuppressWarnings("serial")
@Table(name = "ex_product_parameter")
public class ExProductParameter extends BaseModel {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	
	// name 代表这条参数的名称
	@Column(name = "name")
	private String name;
	// '买方手续费率'
	@Column(name = "buyFeeRate")
	private BigDecimal buyFeeRate;
	
	// '卖方手续费率'
	@Column(name = "sellFeeRate")
	private BigDecimal sellFeeRate;
	
	// '买方最小买入金额'
	@Column(name = "buyMinMoney")
	private BigDecimal buyMinMoney;
	
	// '卖方最小卖出币数'
	@Column(name = "sellMinCoin")
	private BigDecimal sellMinCoin;
	
	@Column(name = "state")
	private Integer state;
	
	// 充值手续费
	@Column(name = "prepaidFeeRate")
	private BigDecimal prepaidFeeRate;
	
	// 提现手续费
	@Column(name = "paceFeeRate")
	private BigDecimal paceFeeRate;
	
	// 每次提现的最大数量
	@Column(name = "oneTimePaceNum")
	private BigDecimal oneTimePaceNum;
	
	// 每天提现的最大数量
	@Column(name = "oneDayPaceNum")
	private BigDecimal oneDayPaceNum;
	
	// 提现的最小金额
	@Column(name = "leastPaceNum")
	private BigDecimal leastPaceNum;
	
	
	public BigDecimal getPrepaidFeeRate() {
		return prepaidFeeRate;
	}
	public void setPrepaidFeeRate(BigDecimal prepaidFeeRate) {
		this.prepaidFeeRate = prepaidFeeRate;
	}
	public BigDecimal getPaceFeeRate() {
		return paceFeeRate;
	}
	public void setPaceFeeRate(BigDecimal paceFeeRate) {
		this.paceFeeRate = paceFeeRate;
	}
	public BigDecimal getOneTimePaceNum() {
		return oneTimePaceNum;
	}
	public void setOneTimePaceNum(BigDecimal oneTimePaceNum) {
		this.oneTimePaceNum = oneTimePaceNum;
	}
	public BigDecimal getOneDayPaceNum() {
		return oneDayPaceNum;
	}
	public void setOneDayPaceNum(BigDecimal oneDayPaceNum) {
		this.oneDayPaceNum = oneDayPaceNum;
	}
	public BigDecimal getLeastPaceNum() {
		return leastPaceNum;
	}
	public void setLeastPaceNum(BigDecimal leastPaceNum) {
		this.leastPaceNum = leastPaceNum;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getBuyFeeRate() {
		return buyFeeRate;
	}
	public void setBuyFeeRate(BigDecimal buyFeeRate) {
		this.buyFeeRate = buyFeeRate;
	}
	public BigDecimal getSellFeeRate() {
		return sellFeeRate;
	}
	public void setSellFeeRate(BigDecimal sellFeeRate) {
		this.sellFeeRate = sellFeeRate;
	}
	public BigDecimal getBuyMinMoney() {
		return buyMinMoney;
	}
	public void setBuyMinMoney(BigDecimal buyMinMoney) {
		this.buyMinMoney = buyMinMoney;
	}
	public BigDecimal getSellMinCoin() {
		return sellMinCoin;
	}
	public void setSellMinCoin(BigDecimal sellMinCoin) {
		this.sellMinCoin = sellMinCoin;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public ExProductParameter(Long id, BigDecimal buyFeeRate,
			BigDecimal sellFeeRate, BigDecimal buyMinMoney,
			BigDecimal sellMinCoin, Integer state) {
		super();
		this.id = id;
		this.buyFeeRate = buyFeeRate;
		this.sellFeeRate = sellFeeRate;
		this.buyMinMoney = buyMinMoney;
		this.sellMinCoin = sellMinCoin;
		this.state = state;
	}
	
	public ExProductParameter() {
		super();
	}
	
	@Override
	public String toString() {
		return "ExProductParameter [id=" + id + ", buyFeeRate=" + buyFeeRate
				+ ", sellFeeRate=" + sellFeeRate + ", buyMinMoney="
				+ buyMinMoney + ", sellMinCoin=" + sellMinCoin + ", state="
				+ state + "]";
	}

	
}
