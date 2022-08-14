/**
 * Copyright:   
 * @author:      tianpengyu
 * @version:     V1.0 
 * @Date:        2018-09-12 20:31:39 
 */
package hry.admin.exchange.model;


import hry.bean.BaseModel;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;

/**
 * <p> exRobotDeep </p>
 * @author:         tianpengyu
 * @Date :          2018-09-12 20:31:39  
 */
@Table(name="ex_robot_deep")
public class ExRobotDeep extends BaseModel {
	
	
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
	
	@Column(name= "robotType")
	private Integer robotType;  //
	
	@Column(name= "isSratAuto")
	private Integer isSratAuto;  //
	
	@Column(name= "autoUsername")
	private String autoUsername;  //
	
	@Column(name= "customerId")
	private Long customerId;  //
	
	@Column(name= "saasId")
	private String saasId;  //
	
	@Column(name= "buyDeep")
	private Integer buyDeep;  //
	
	@Column(name= "sellDeep")
	private Integer sellDeep;  //
	
	@Column(name= "everyEntrustCount")
	private BigDecimal everyEntrustCount;  //
	
	@Column(name= "stopLine")
	private BigDecimal stopLine;  //
	
	@Column(name= "buyOneDiffRate")
	private BigDecimal buyOneDiffRate;  //
	
	@Column(name= "sellOneDiffRate")
	private BigDecimal sellOneDiffRate;  //
	
	@Column(name= "openTime")
	private Date openTime;  //


	@Transient
	private String mobilePhone;  //手机号
	@Transient
	private String email;  //邮箱

	@Transient
	private BigDecimal coinCodeNumer;  //交易币种余额
	@Transient
	private BigDecimal fixCoinCodeNumer;  //交易区余额

	@Transient
	private BigDecimal buyingNumer;  //机器人委买待成交量

	@Transient
	private BigDecimal sellingNumer;  //机器人委卖待成交量

	@Transient
	private BigDecimal buyedNumer;  //机器人委买已成交量

	@Transient
	private BigDecimal selledNumer;  //机器人委卖已成交量

	public BigDecimal getBuyingNumer() {
		return buyingNumer;
	}

	public void setBuyingNumer(BigDecimal buyingNumer) {
		this.buyingNumer = buyingNumer;
	}

	public BigDecimal getSellingNumer() {
		return sellingNumer;
	}

	public void setSellingNumer(BigDecimal sellingNumer) {
		this.sellingNumer = sellingNumer;
	}

	public BigDecimal getBuyedNumer() {
		return buyedNumer;
	}

	public void setBuyedNumer(BigDecimal buyedNumer) {
		this.buyedNumer = buyedNumer;
	}

	public BigDecimal getSelledNumer() {
		return selledNumer;
	}

	public void setSelledNumer(BigDecimal selledNumer) {
		this.selledNumer = selledNumer;
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

	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  Long 
	 * @Date :   2018-09-12 20:31:39    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-09-12 20:31:39   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-09-12 20:31:39    
	 */
	public String getCoinCode() {
		return coinCode;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param coinCode
	 * @return:  void 
	 * @Date :   2018-09-12 20:31:39   
	 */
	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-09-12 20:31:39    
	 */
	public String getFixPriceCoinCode() {
		return fixPriceCoinCode;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param fixPriceCoinCode
	 * @return:  void 
	 * @Date :   2018-09-12 20:31:39   
	 */
	public void setFixPriceCoinCode(String fixPriceCoinCode) {
		this.fixPriceCoinCode = fixPriceCoinCode;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  Integer 
	 * @Date :   2018-09-12 20:31:39    
	 */
	public Integer getFixPriceType() {
		return fixPriceType;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param fixPriceType
	 * @return:  void 
	 * @Date :   2018-09-12 20:31:39   
	 */
	public void setFixPriceType(Integer fixPriceType) {
		this.fixPriceType = fixPriceType;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  Integer 
	 * @Date :   2018-09-12 20:31:39    
	 */
	public Integer getRobotType() {
		return robotType;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param robotType
	 * @return:  void 
	 * @Date :   2018-09-12 20:31:39   
	 */
	public void setRobotType(Integer robotType) {
		this.robotType = robotType;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  Integer 
	 * @Date :   2018-09-12 20:31:39    
	 */
	public Integer getIsSratAuto() {
		return isSratAuto;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param isSratAuto
	 * @return:  void 
	 * @Date :   2018-09-12 20:31:39   
	 */
	public void setIsSratAuto(Integer isSratAuto) {
		this.isSratAuto = isSratAuto;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-09-12 20:31:39    
	 */
	public String getAutoUsername() {
		return autoUsername;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param autoUsername
	 * @return:  void 
	 * @Date :   2018-09-12 20:31:39   
	 */
	public void setAutoUsername(String autoUsername) {
		this.autoUsername = autoUsername;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  Long 
	 * @Date :   2018-09-12 20:31:39    
	 */
	public Long getCustomerId() {
		return customerId;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param customerId
	 * @return:  void 
	 * @Date :   2018-09-12 20:31:39   
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-09-12 20:31:39    
	 */
	public String getSaasId() {
		return saasId;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param saasId
	 * @return:  void 
	 * @Date :   2018-09-12 20:31:39   
	 */
	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  BigDecimal 
	 * @Date :   2018-09-12 20:31:39    
	 */
	public Integer getBuyDeep() {
		return buyDeep;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param buyDeep
	 * @return:  void 
	 * @Date :   2018-09-12 20:31:39   
	 */
	public void setBuyDeep(Integer buyDeep) {
		this.buyDeep = buyDeep;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  BigDecimal 
	 * @Date :   2018-09-12 20:31:39    
	 */
	public Integer getSellDeep() {
		return sellDeep;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param sellDeep
	 * @return:  void 
	 * @Date :   2018-09-12 20:31:39   
	 */
	public void setSellDeep(Integer sellDeep) {
		this.sellDeep = sellDeep;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  BigDecimal 
	 * @Date :   2018-09-12 20:31:39    
	 */
	public BigDecimal getEveryEntrustCount() {
		return everyEntrustCount;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param everyEntrustCount
	 * @return:  void 
	 * @Date :   2018-09-12 20:31:39   
	 */
	public void setEveryEntrustCount(BigDecimal everyEntrustCount) {
		this.everyEntrustCount = everyEntrustCount;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  BigDecimal 
	 * @Date :   2018-09-12 20:31:39    
	 */
	public BigDecimal getStopLine() {
		return stopLine;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param stopLine
	 * @return:  void 
	 * @Date :   2018-09-12 20:31:39   
	 */
	public void setStopLine(BigDecimal stopLine) {
		this.stopLine = stopLine;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  BigDecimal 
	 * @Date :   2018-09-12 20:31:39    
	 */
	public BigDecimal getBuyOneDiffRate() {
		return buyOneDiffRate;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param buyOneDiffRate
	 * @return:  void 
	 * @Date :   2018-09-12 20:31:39   
	 */
	public void setBuyOneDiffRate(BigDecimal buyOneDiffRate) {
		this.buyOneDiffRate = buyOneDiffRate;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  BigDecimal 
	 * @Date :   2018-09-12 20:31:39    
	 */
	public BigDecimal getSellOneDiffRate() {
		return sellOneDiffRate;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param sellOneDiffRate
	 * @return:  void 
	 * @Date :   2018-09-12 20:31:39   
	 */
	public void setSellOneDiffRate(BigDecimal sellOneDiffRate) {
		this.sellOneDiffRate = sellOneDiffRate;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  Date 
	 * @Date :   2018-09-12 20:31:39    
	 */
	public Date getOpenTime() {
		return openTime;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param openTime
	 * @return:  void 
	 * @Date :   2018-09-12 20:31:39   
	 */
	public void setOpenTime(Date openTime) {
		this.openTime = openTime;
	}
	
	

}
