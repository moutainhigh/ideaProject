/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2017-12-07 14:06:38 
 */
package hry.customer.businessman.model;

import static javax.persistence.GenerationType.IDENTITY;
import hry.core.mvc.model.BaseModel;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * <p> C2cTransaction </p>
 * @author:         liushilei
 * @Date :          2017-12-07 14:06:38  
 */
@Table(name="c2c_coin")
public class C2cCoin extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //id
	
	@Column(name= "coinCode")
	private String coinCode;  //币种代码

	@Column(name= "isOpen")
	private int isOpen;  //是否开启c2c
	
	@Column(name= "buyPrice")
	private BigDecimal buyPrice;  //买入价
	
	@Column(name= "sellPrice")
	private BigDecimal sellPrice;  //卖出价
	
	@Column(name= "minCount")
	private BigDecimal minCount;  //最小交易数量
	
	@Column(name= "maxCount")
	private BigDecimal maxCount;  //最大交易数量

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCoinCode() {
		return coinCode;
	}

	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}

	public int getIsOpen() {
		return isOpen;
	}

	public void setIsOpen(int isOpen) {
		this.isOpen = isOpen;
	}

	public BigDecimal getBuyPrice() {
		return buyPrice;
	}

	public void setBuyPrice(BigDecimal buyPrice) {
		this.buyPrice = buyPrice;
	}

	public BigDecimal getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(BigDecimal sellPrice) {
		this.sellPrice = sellPrice;
	}

	public BigDecimal getMinCount() {
		return minCount;
	}

	public void setMinCount(BigDecimal minCount) {
		this.minCount = minCount;
	}

	public BigDecimal getMaxCount() {
		return maxCount;
	}

	public void setMaxCount(BigDecimal maxCount) {
		this.maxCount = maxCount;
	}
	
	

}
