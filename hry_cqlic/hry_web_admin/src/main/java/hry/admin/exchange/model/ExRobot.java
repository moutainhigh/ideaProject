/**
 * Copyright:   
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-12 16:33:43 
 */
package hry.admin.exchange.model;


import hry.bean.BaseModel;

import java.math.BigDecimal;

import javax.persistence.*;

/**
 * <p> ExRobot </p>
 * @author:         liushilei
 * @Date :          2018-06-12 16:33:43  
 */
@Table(name="ex_robot")
public class ExRobot extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "coinCode")
	private String coinCode;  //
	
	@Column(name= "fixPriceCoinCode")
	private String fixPriceCoinCode;  //
	
	@Column(name= "fixPriceType")
	private Integer fixPriceType;  //
	
	@Column(name= "exCointoCoinId")
	private Long exCointoCoinId;  //
	
	@Column(name= "robotType")
	private Integer robotType;  //
	
	@Column(name= "isSratAuto")
	private Integer isSratAuto;  //
	
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
	
	@Column(name= "isRobotSelf")
	private Integer isRobotSelf;  //
	
	@Column(name= "isStartThirdPrice")
	private Integer isStartThirdPrice;  //
	
	@Column(name= "priceSource")
	private String priceSource;  //
	
	@Column(name= "saasId")
	private String saasId;  //

	@Transient
	private String mobilePhone;  //手机号
	@Transient
	private String email;  //邮箱

	@Transient
	private BigDecimal coinCodeNumer;  //交易币种余额
	@Transient
	private BigDecimal fixCoinCodeNumer;  //交易区余额

	@Transient
	private BigDecimal nowPrice;  //当前价格
	@Transient
	private BigDecimal buyEntNum;  //买方下单量
	@Transient
	private BigDecimal sellEntNum;  //买方下单量

	@Transient
	private String klineData;  //5分钟走势图

	public String getKlineData() {
		return klineData;
	}

	public void setKlineData(String klineData) {
		this.klineData = klineData;
	}

	public BigDecimal getNowPrice() {
		return nowPrice;
	}

	public void setNowPrice(BigDecimal nowPrice) {
		this.nowPrice = nowPrice;
	}

	public BigDecimal getBuyEntNum() {
		return buyEntNum;
	}

	public void setBuyEntNum(BigDecimal buyEntNum) {
		this.buyEntNum = buyEntNum;
	}

	public BigDecimal getSellEntNum() {
		return sellEntNum;
	}

	public void setSellEntNum(BigDecimal sellEntNum) {
		this.sellEntNum = sellEntNum;
	}

	public BigDecimal getCoinCodeNumer() {
		return coinCodeNumer;
	}

	public void setCoinCodeNumer(BigDecimal coinCodeNumer) {
		this.coinCodeNumer = coinCodeNumer;
	}

	public BigDecimal getFixCoinCodeNumer() {
		return fixCoinCodeNumer;
	}

	public void setFixCoinCodeNumer(BigDecimal fixCoinCodeNumer) {
		this.fixCoinCodeNumer = fixCoinCodeNumer;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Long 
	 * @Date :   2018-06-12 16:33:43    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-06-12 16:33:43   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-12 16:33:43    
	 */
	public String getCoinCode() {
		return coinCode;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param coinCode
	 * @return:  void 
	 * @Date :   2018-06-12 16:33:43   
	 */
	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-12 16:33:43    
	 */
	public String getFixPriceCoinCode() {
		return fixPriceCoinCode;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param fixPriceCoinCode
	 * @return:  void 
	 * @Date :   2018-06-12 16:33:43   
	 */
	public void setFixPriceCoinCode(String fixPriceCoinCode) {
		this.fixPriceCoinCode = fixPriceCoinCode;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Integer 
	 * @Date :   2018-06-12 16:33:43    
	 */
	public Integer getFixPriceType() {
		return fixPriceType;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param fixPriceType
	 * @return:  void 
	 * @Date :   2018-06-12 16:33:43   
	 */
	public void setFixPriceType(Integer fixPriceType) {
		this.fixPriceType = fixPriceType;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Long 
	 * @Date :   2018-06-12 16:33:43    
	 */
	public Long getExCointoCoinId() {
		return exCointoCoinId;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param exCointoCoinId
	 * @return:  void 
	 * @Date :   2018-06-12 16:33:43   
	 */
	public void setExCointoCoinId(Long exCointoCoinId) {
		this.exCointoCoinId = exCointoCoinId;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Integer 
	 * @Date :   2018-06-12 16:33:43    
	 */
	public Integer getRobotType() {
		return robotType;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param robotType
	 * @return:  void 
	 * @Date :   2018-06-12 16:33:43   
	 */
	public void setRobotType(Integer robotType) {
		this.robotType = robotType;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Integer 
	 * @Date :   2018-06-12 16:33:43    
	 */
	public Integer getIsSratAuto() {
		return isSratAuto;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param isSratAuto
	 * @return:  void 
	 * @Date :   2018-06-12 16:33:43   
	 */
	public void setIsSratAuto(Integer isSratAuto) {
		this.isSratAuto = isSratAuto;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-12 16:33:43    
	 */
	public String getAutoUsername() {
		return autoUsername;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param autoUsername
	 * @return:  void 
	 * @Date :   2018-06-12 16:33:43   
	 */
	public void setAutoUsername(String autoUsername) {
		this.autoUsername = autoUsername;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  BigDecimal 
	 * @Date :   2018-06-12 16:33:43    
	 */
	public BigDecimal getAutoPrice() {
		return autoPrice;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param autoPrice
	 * @return:  void 
	 * @Date :   2018-06-12 16:33:43   
	 */
	public void setAutoPrice(BigDecimal autoPrice) {
		this.autoPrice = autoPrice;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  BigDecimal 
	 * @Date :   2018-06-12 16:33:43    
	 */
	public BigDecimal getAutoPriceFloat() {
		return autoPriceFloat;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param autoPriceFloat
	 * @return:  void 
	 * @Date :   2018-06-12 16:33:43   
	 */
	public void setAutoPriceFloat(BigDecimal autoPriceFloat) {
		this.autoPriceFloat = autoPriceFloat;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  BigDecimal 
	 * @Date :   2018-06-12 16:33:43    
	 */
	public BigDecimal getAutoCount() {
		return autoCount;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param autoCount
	 * @return:  void 
	 * @Date :   2018-06-12 16:33:43   
	 */
	public void setAutoCount(BigDecimal autoCount) {
		this.autoCount = autoCount;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  BigDecimal 
	 * @Date :   2018-06-12 16:33:43    
	 */
	public BigDecimal getAutoCountFloat() {
		return autoCountFloat;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param autoCountFloat
	 * @return:  void 
	 * @Date :   2018-06-12 16:33:43   
	 */
	public void setAutoCountFloat(BigDecimal autoCountFloat) {
		this.autoCountFloat = autoCountFloat;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Long 
	 * @Date :   2018-06-12 16:33:43    
	 */
	public Long getCustomerId() {
		return customerId;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param customerId
	 * @return:  void 
	 * @Date :   2018-06-12 16:33:43   
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Integer 
	 * @Date :   2018-06-12 16:33:43    
	 */
	public Integer getAtuoPriceType() {
		return atuoPriceType;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param atuoPriceType
	 * @return:  void 
	 * @Date :   2018-06-12 16:33:43   
	 */
	public void setAtuoPriceType(Integer atuoPriceType) {
		this.atuoPriceType = atuoPriceType;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  BigDecimal 
	 * @Date :   2018-06-12 16:33:43    
	 */
	public BigDecimal getUpFloatPer() {
		return upFloatPer;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param upFloatPer
	 * @return:  void 
	 * @Date :   2018-06-12 16:33:43   
	 */
	public void setUpFloatPer(BigDecimal upFloatPer) {
		this.upFloatPer = upFloatPer;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Integer 
	 * @Date :   2018-06-12 16:33:43    
	 */
	public Integer getIsRobotSelf() {
		return isRobotSelf;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param isRobotSelf
	 * @return:  void 
	 * @Date :   2018-06-12 16:33:43   
	 */
	public void setIsRobotSelf(Integer isRobotSelf) {
		this.isRobotSelf = isRobotSelf;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Integer 
	 * @Date :   2018-06-12 16:33:43    
	 */
	public Integer getIsStartThirdPrice() {
		return isStartThirdPrice;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param isStartThirdPrice
	 * @return:  void 
	 * @Date :   2018-06-12 16:33:43   
	 */
	public void setIsStartThirdPrice(Integer isStartThirdPrice) {
		this.isStartThirdPrice = isStartThirdPrice;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-12 16:33:43    
	 */
	public String getPriceSource() {
		return priceSource;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param priceSource
	 * @return:  void 
	 * @Date :   2018-06-12 16:33:43   
	 */
	public void setPriceSource(String priceSource) {
		this.priceSource = priceSource;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-12 16:33:43    
	 */
	public String getSaasId() {
		return saasId;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param saasId
	 * @return:  void 
	 * @Date :   2018-06-12 16:33:43   
	 */
	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}
	
	

}
