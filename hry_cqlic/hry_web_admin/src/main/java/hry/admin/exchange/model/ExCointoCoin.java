/**
 * Copyright:   
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-12 15:52:02 
 */
package hry.admin.exchange.model;


import hry.bean.BaseModel;

import java.math.BigDecimal;

import javax.persistence.*;

/**
 * <p> ExCointoCoin </p>
 * @author:         liushilei
 * @Date :          2018-06-12 15:52:02  
 */
@Table(name="ex_cointo_coin")
	public class ExCointoCoin extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "toProductId")
	private Long toProductId;  //
	
	@Column(name= "fromProductId")
	private Long fromProductId;  //
	
	@Column(name= "coinCode")
	private String coinCode;  //
	
	@Column(name= "fixPriceCoinCode")
	private String fixPriceCoinCode;  //
	
	@Column(name= "fixPriceType")
	private Integer fixPriceType;  //
	
	@Column(name= "keepDecimalFixPrice")
	private Integer keepDecimalFixPrice;  //
	@Column(name= "keepDecimalCoinCode")
	private Integer keepDecimalCoinCode;  //
	
	@Column(name= "state")
	private Integer state;  //0 准备状态\n 1.可以交易  2//已删除
	
	@Column(name= "saasId")
	private String saasId;  //
	
	@Column(name= "decline")
	private BigDecimal decline;  //
	
	@Column(name= "rose")
	private BigDecimal rose;  //
	
	@Column(name= "averagePrice")
	private BigDecimal averagePrice;  //
	
	@Column(name= "buyFeeRate")
	private BigDecimal buyFeeRate;  //
	
	@Column(name= "sellFeeRate")
	private BigDecimal sellFeeRate;  //
	
	@Column(name= "buyMinMoney")
	private BigDecimal buyMinMoney;  //
	
	@Column(name= "sellMinCoin")
	private BigDecimal sellMinCoin;  //
	
	@Column(name= "priceLimits")
	private BigDecimal priceLimits;  //
	
	@Column(name= "oneTimeOrderNum")
	private BigDecimal oneTimeOrderNum;  //
	
	@Column(name= "isSratAuto")
	private Integer isSratAuto;  //
	
	@Column(name= "isFromChbtc")
	private Integer isFromChbtc;  //
	
	@Column(name= "autoUsername")
	private String autoUsername;  //
	
	@Column(name= "autoPrice")
	private BigDecimal autoPrice;  //
	
	@Column(name= "autoPriceFloat")
	private BigDecimal autoPriceFloat;  //
	
	@Column(name= "autoCount")
	private BigDecimal autoCount;  //
	
	@Column(name= "autoCountFloat")
	private BigDecimal autoCountFloat;  //
	
	@Column(name= "customerId")
	private Long customerId;  //
	
	@Column(name= "atuoPriceType")
	private Integer atuoPriceType;  //
	
	@Column(name= "upFloatPer")
	private BigDecimal upFloatPer;  //

	@Transient
	private String yesterdayPrice;

	public Integer getKeepDecimalCoinCode() {
		return keepDecimalCoinCode;
	}

	public void setKeepDecimalCoinCode(Integer keepDecimalCoinCode) {
		this.keepDecimalCoinCode = keepDecimalCoinCode;
	}

	public String getYesterdayPrice() {
		return yesterdayPrice;
	}

	public void setYesterdayPrice(String yesterdayPrice) {
		this.yesterdayPrice = yesterdayPrice;
	}

	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Long 
	 * @Date :   2018-06-12 15:52:02    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-06-12 15:52:02   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Long 
	 * @Date :   2018-06-12 15:52:02    
	 */
	public Long getToProductId() {
		return toProductId;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param toProductId
	 * @return:  void 
	 * @Date :   2018-06-12 15:52:02   
	 */
	public void setToProductId(Long toProductId) {
		this.toProductId = toProductId;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Long 
	 * @Date :   2018-06-12 15:52:02    
	 */
	public Long getFromProductId() {
		return fromProductId;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param fromProductId
	 * @return:  void 
	 * @Date :   2018-06-12 15:52:02   
	 */
	public void setFromProductId(Long fromProductId) {
		this.fromProductId = fromProductId;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-12 15:52:02    
	 */
	public String getCoinCode() {
		return coinCode;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param coinCode
	 * @return:  void 
	 * @Date :   2018-06-12 15:52:02   
	 */
	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-12 15:52:02    
	 */
	public String getFixPriceCoinCode() {
		return fixPriceCoinCode;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param fixPriceCoinCode
	 * @return:  void 
	 * @Date :   2018-06-12 15:52:02   
	 */
	public void setFixPriceCoinCode(String fixPriceCoinCode) {
		this.fixPriceCoinCode = fixPriceCoinCode;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Integer 
	 * @Date :   2018-06-12 15:52:02    
	 */
	public Integer getFixPriceType() {
		return fixPriceType;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param fixPriceType
	 * @return:  void 
	 * @Date :   2018-06-12 15:52:02   
	 */
	public void setFixPriceType(Integer fixPriceType) {
		this.fixPriceType = fixPriceType;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Integer 
	 * @Date :   2018-06-12 15:52:02    
	 */
	public Integer getKeepDecimalFixPrice() {
		return keepDecimalFixPrice;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param keepDecimalFixPrice
	 * @return:  void 
	 * @Date :   2018-06-12 15:52:02   
	 */
	public void setKeepDecimalFixPrice(Integer keepDecimalFixPrice) {
		this.keepDecimalFixPrice = keepDecimalFixPrice;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Integer 
	 * @Date :   2018-06-12 15:52:02    
	 */
	public Integer getState() {
		return state;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param state
	 * @return:  void 
	 * @Date :   2018-06-12 15:52:02   
	 */
	public void setState(Integer state) {
		this.state = state;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-12 15:52:02    
	 */
	public String getSaasId() {
		return saasId;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param saasId
	 * @return:  void 
	 * @Date :   2018-06-12 15:52:02   
	 */
	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  BigDecimal 
	 * @Date :   2018-06-12 15:52:02    
	 */
	public BigDecimal getDecline() {
		return decline;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param decline
	 * @return:  void 
	 * @Date :   2018-06-12 15:52:02   
	 */
	public void setDecline(BigDecimal decline) {
		this.decline = decline;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  BigDecimal 
	 * @Date :   2018-06-12 15:52:02    
	 */
	public BigDecimal getRose() {
		return rose;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param rose
	 * @return:  void 
	 * @Date :   2018-06-12 15:52:02   
	 */
	public void setRose(BigDecimal rose) {
		this.rose = rose;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  BigDecimal 
	 * @Date :   2018-06-12 15:52:02    
	 */
	public BigDecimal getAveragePrice() {
		return averagePrice;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param averagePrice
	 * @return:  void 
	 * @Date :   2018-06-12 15:52:02   
	 */
	public void setAveragePrice(BigDecimal averagePrice) {
		this.averagePrice = averagePrice;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  BigDecimal 
	 * @Date :   2018-06-12 15:52:02    
	 */
	public BigDecimal getBuyFeeRate() {
		return buyFeeRate;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param buyFeeRate
	 * @return:  void 
	 * @Date :   2018-06-12 15:52:02   
	 */
	public void setBuyFeeRate(BigDecimal buyFeeRate) {
		this.buyFeeRate = buyFeeRate;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  BigDecimal 
	 * @Date :   2018-06-12 15:52:02    
	 */
	public BigDecimal getSellFeeRate() {
		return sellFeeRate;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param sellFeeRate
	 * @return:  void 
	 * @Date :   2018-06-12 15:52:02   
	 */
	public void setSellFeeRate(BigDecimal sellFeeRate) {
		this.sellFeeRate = sellFeeRate;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  BigDecimal 
	 * @Date :   2018-06-12 15:52:02    
	 */
	public BigDecimal getBuyMinMoney() {
		return buyMinMoney;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param buyMinMoney
	 * @return:  void 
	 * @Date :   2018-06-12 15:52:02   
	 */
	public void setBuyMinMoney(BigDecimal buyMinMoney) {
		this.buyMinMoney = buyMinMoney;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  BigDecimal 
	 * @Date :   2018-06-12 15:52:02    
	 */
	public BigDecimal getSellMinCoin() {
		return sellMinCoin;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param sellMinCoin
	 * @return:  void 
	 * @Date :   2018-06-12 15:52:02   
	 */
	public void setSellMinCoin(BigDecimal sellMinCoin) {
		this.sellMinCoin = sellMinCoin;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  BigDecimal 
	 * @Date :   2018-06-12 15:52:02    
	 */
	public BigDecimal getPriceLimits() {
		return priceLimits;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param priceLimits
	 * @return:  void 
	 * @Date :   2018-06-12 15:52:02   
	 */
	public void setPriceLimits(BigDecimal priceLimits) {
		this.priceLimits = priceLimits;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  BigDecimal 
	 * @Date :   2018-06-12 15:52:02    
	 */
	public BigDecimal getOneTimeOrderNum() {
		return oneTimeOrderNum;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param oneTimeOrderNum
	 * @return:  void 
	 * @Date :   2018-06-12 15:52:02   
	 */
	public void setOneTimeOrderNum(BigDecimal oneTimeOrderNum) {
		this.oneTimeOrderNum = oneTimeOrderNum;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Integer 
	 * @Date :   2018-06-12 15:52:02    
	 */
	public Integer getIsSratAuto() {
		return isSratAuto;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param isSratAuto
	 * @return:  void 
	 * @Date :   2018-06-12 15:52:02   
	 */
	public void setIsSratAuto(Integer isSratAuto) {
		this.isSratAuto = isSratAuto;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Integer 
	 * @Date :   2018-06-12 15:52:02    
	 */
	public Integer getIsFromChbtc() {
		return isFromChbtc;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param isFromChbtc
	 * @return:  void 
	 * @Date :   2018-06-12 15:52:02   
	 */
	public void setIsFromChbtc(Integer isFromChbtc) {
		this.isFromChbtc = isFromChbtc;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-12 15:52:02    
	 */
	public String getAutoUsername() {
		return autoUsername;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param autoUsername
	 * @return:  void 
	 * @Date :   2018-06-12 15:52:02   
	 */
	public void setAutoUsername(String autoUsername) {
		this.autoUsername = autoUsername;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  BigDecimal 
	 * @Date :   2018-06-12 15:52:02    
	 */
	public BigDecimal getAutoPrice() {
		return autoPrice;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param autoPrice
	 * @return:  void 
	 * @Date :   2018-06-12 15:52:02   
	 */
	public void setAutoPrice(BigDecimal autoPrice) {
		this.autoPrice = autoPrice;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  BigDecimal 
	 * @Date :   2018-06-12 15:52:02    
	 */
	public BigDecimal getAutoPriceFloat() {
		return autoPriceFloat;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param autoPriceFloat
	 * @return:  void 
	 * @Date :   2018-06-12 15:52:02   
	 */
	public void setAutoPriceFloat(BigDecimal autoPriceFloat) {
		this.autoPriceFloat = autoPriceFloat;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  BigDecimal 
	 * @Date :   2018-06-12 15:52:02    
	 */
	public BigDecimal getAutoCount() {
		return autoCount;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param autoCount
	 * @return:  void 
	 * @Date :   2018-06-12 15:52:02   
	 */
	public void setAutoCount(BigDecimal autoCount) {
		this.autoCount = autoCount;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  BigDecimal 
	 * @Date :   2018-06-12 15:52:02    
	 */
	public BigDecimal getAutoCountFloat() {
		return autoCountFloat;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param autoCountFloat
	 * @return:  void 
	 * @Date :   2018-06-12 15:52:02   
	 */
	public void setAutoCountFloat(BigDecimal autoCountFloat) {
		this.autoCountFloat = autoCountFloat;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Long 
	 * @Date :   2018-06-12 15:52:02    
	 */
	public Long getCustomerId() {
		return customerId;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param customerId
	 * @return:  void 
	 * @Date :   2018-06-12 15:52:02   
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Integer 
	 * @Date :   2018-06-12 15:52:02    
	 */
	public Integer getAtuoPriceType() {
		return atuoPriceType;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param atuoPriceType
	 * @return:  void 
	 * @Date :   2018-06-12 15:52:02   
	 */
	public void setAtuoPriceType(Integer atuoPriceType) {
		this.atuoPriceType = atuoPriceType;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  BigDecimal 
	 * @Date :   2018-06-12 15:52:02    
	 */
	public BigDecimal getUpFloatPer() {
		return upFloatPer;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param upFloatPer
	 * @return:  void 
	 * @Date :   2018-06-12 15:52:02   
	 */
	public void setUpFloatPer(BigDecimal upFloatPer) {
		this.upFloatPer = upFloatPer;
	}
	
	

}
