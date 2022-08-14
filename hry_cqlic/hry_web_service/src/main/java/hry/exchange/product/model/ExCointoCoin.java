/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      gaomimi
 * @version:     V1.0 
 * @Date:        2017-07-06 19:40:34 
 */
package hry.exchange.product.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.math.BigDecimal;

import hry.core.mvc.model.BaseExModel;
import hry.core.mvc.model.BaseModel;


import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * <p> ExCointoCoin </p>
 * @author:         gaomimi
 * @Date :          2017-07-06 19:40:34  
 */
@Table(name="ex_cointo_coin")
public class ExCointoCoin extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //id
	

	@Column(name= "coinCode")
	private String coinCode;  //交易币种
	
	@Column(name= "fixPriceCoinCode")
	private String fixPriceCoinCode;  //定价币种
	@Column(name= "fixPriceType")
	private Integer fixPriceType;  //0真实货币1虚拟币
	@Column(name= "keepDecimalFixPrice")
	private Integer keepDecimalFixPrice;  //币的保留几位小数

	// 产品的状态 0 ： 准备状态
		// 1 ： 可以交易
		
	@Column(name = "state")
	private Integer state;



	@Column(name= "keepDecimalCoinCode")
	private Integer keepDecimalCoinCode;  //
	
	// 买方手续费 率
	@Column(name = "buyFeeRate")
	private BigDecimal buyFeeRate;

	// 卖方手续费 率
	@Column(name = "sellFeeRate")
	private BigDecimal sellFeeRate;

	// 买方 的成交 金额
	@Column(name = "buyMinMoney")
	private BigDecimal buyMinMoney;

	// 卖方 最小的成交 数量
	@Column(name = "sellMinCoin")
	private BigDecimal sellMinCoin;
	// 价格限制
	@Column(name = "priceLimits")
	private BigDecimal priceLimits;
		
	//涨幅
	@Column(name="rose")
	private BigDecimal rose;
	
	//跌幅
	@Column(name="decline")
	private BigDecimal decline;
	
	//均价（收盘价）
	@Column(name="averagePrice")
	private BigDecimal averagePrice;
		
	
	// 每次下单的最大数量(20170207币银网添加)
	@Column(name = "oneTimeOrderNum")
	private BigDecimal oneTimeOrderNum;
	
	
	
	
	@Column(name = "isSratAuto")
	private Integer isSratAuto;
	@Column(name = "isFromChbtc")
	private Integer isFromChbtc;

	@Column(name="autoPrice")
	private BigDecimal autoPrice;
	@Column(name="autoPriceFloat")
	private BigDecimal autoPriceFloat;
	@Column(name="autoCount")
	private BigDecimal autoCount;
	@Column(name="autoCountFloat")
	private BigDecimal autoCountFloat;
	@Column(name="autoUsername")
	private String autoUsername;
	//1按定价浮动2，按市价浮动
	@Column(name="atuoPriceType")
	private Integer atuoPriceType;
	//如果按市价走的话，比例上浮 	
	@Column(name="upFloatPer")
	private BigDecimal upFloatPer;
	
	
	@Transient
	private String mobilePhone;  //手机号
	@Transient
	private String email;  //邮箱

	public Integer getKeepDecimalCoinCode() {
		return keepDecimalCoinCode;
	}

	public void setKeepDecimalCoinCode(Integer keepDecimalCoinCode) {
		this.keepDecimalCoinCode = keepDecimalCoinCode;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getAtuoPriceType() {
		return atuoPriceType;
	}

	public void setAtuoPriceType(Integer atuoPriceType) {
		this.atuoPriceType = atuoPriceType;
	}

	public BigDecimal getUpFloatPer() {
		return upFloatPer;
	}

	public void setUpFloatPer(BigDecimal upFloatPer) {
		this.upFloatPer = upFloatPer;
	}

	@Column(name = "customerId")
	private Long customerId;
	
	@Transient
	private String yesterdayPrice;
	
	
	

	public String getYesterdayPrice() {
		return yesterdayPrice;
	}

	public void setYesterdayPrice(String yesterdayPrice) {
		this.yesterdayPrice = yesterdayPrice;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getAutoUsername() {
		return autoUsername;
	}

	public void setAutoUsername(String autoUsername) {
		this.autoUsername = autoUsername;
	}

	public Integer getIsSratAuto() {
		return isSratAuto;
	}

	public void setIsSratAuto(Integer isSratAuto) {
		this.isSratAuto = isSratAuto;
	}

	public Integer getIsFromChbtc() {
		return isFromChbtc;
	}

	public void setIsFromChbtc(Integer isFromChbtc) {
		this.isFromChbtc = isFromChbtc;
	}

	public BigDecimal getAutoPrice() {
		return autoPrice;
	}

	public void setAutoPrice(BigDecimal autoPrice) {
		this.autoPrice = autoPrice;
	}

	public BigDecimal getAutoPriceFloat() {
		return autoPriceFloat;
	}

	public void setAutoPriceFloat(BigDecimal autoPriceFloat) {
		this.autoPriceFloat = autoPriceFloat;
	}

	public BigDecimal getAutoCount() {
		return autoCount;
	}

	public void setAutoCount(BigDecimal autoCount) {
		this.autoCount = autoCount;
	}

	public BigDecimal getAutoCountFloat() {
		return autoCountFloat;
	}

	public void setAutoCountFloat(BigDecimal autoCountFloat) {
		this.autoCountFloat = autoCountFloat;
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

	public BigDecimal getPriceLimits() {
		return priceLimits;
	}

	public void setPriceLimits(BigDecimal priceLimits) {
		this.priceLimits = priceLimits;
	}

	public BigDecimal getRose() {
		return rose;
	}

	public void setRose(BigDecimal rose) {
		this.rose = rose;
	}

	public BigDecimal getDecline() {
		return decline;
	}

	public void setDecline(BigDecimal decline) {
		this.decline = decline;
	}

	public BigDecimal getAveragePrice() {
		return averagePrice;
	}

	public void setAveragePrice(BigDecimal averagePrice) {
		this.averagePrice = averagePrice;
	}

	public BigDecimal getOneTimeOrderNum() {
		return oneTimeOrderNum;
	}

	public void setOneTimeOrderNum(BigDecimal oneTimeOrderNum) {
		this.oneTimeOrderNum = oneTimeOrderNum;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	/**
	 * <p>id</p>
	 * @author:  gaomimi
	 * @return:  Long 
	 * @Date :   2017-07-06 19:40:34    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>id</p>
	 * @author:  gaomimi
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2017-07-06 19:40:34   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	

	
	
	

	


	public String getCoinCode() {
		return coinCode;
	}

	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}

	public String getFixPriceCoinCode() {
		return fixPriceCoinCode;
	}

	public void setFixPriceCoinCode(String fixPriceCoinCode) {
		this.fixPriceCoinCode = fixPriceCoinCode;
	}

	public Integer getKeepDecimalFixPrice() {
		return keepDecimalFixPrice;
	}

	public void setKeepDecimalFixPrice(Integer keepDecimalFixPrice) {
		this.keepDecimalFixPrice = keepDecimalFixPrice;
	}

	public Integer getFixPriceType() {
		return fixPriceType;
	}

	public void setFixPriceType(Integer fixPriceType) {
		this.fixPriceType = fixPriceType;
	}


	

}
