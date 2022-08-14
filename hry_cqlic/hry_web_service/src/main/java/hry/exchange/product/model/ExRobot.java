/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      songb
 * @version:     V1.0 
 * @Date:        2018-05-04 11:37:43 
 */
package hry.exchange.product.model;

import static javax.persistence.GenerationType.IDENTITY;
import hry.core.mvc.model.BaseModel;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * <p> ExRobot </p>
 * @author:         songb
 * @Date :          2018-05-04 11:37:43  
 */
@Table(name="ex_robot")
public class ExRobot extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //id
	
	@Column(name= "coinCode")
	private String coinCode;  //交易币
	
	@Column(name= "fixPriceCoinCode")
	private String fixPriceCoinCode;  //定价币
	
	@Column(name= "fixPriceType")
	private Integer fixPriceType;  //0真实货币1虚拟币
	
	@Column(name= "exCointoCoinId")
	private Long exCointoCoinId;  //exCointoCoinId
	
	@Column(name= "robotType")
	private Integer robotType;  //1机器人的类型1提高活跃度机器人，2做多机器人，3做空机器人
	
	@Column(name= "isSratAuto")
	private Integer isSratAuto;  //是否开启自动交易(0不开启1开启)
	
	@Column(name= "autoUsername")
	private String autoUsername;  //刷单账号
	
	@Column(name= "autoPrice")
	private BigDecimal autoPrice;  //刷单价格
	
	@Column(name= "autoPriceFloat")
	private BigDecimal autoPriceFloat;  //价格浮动比例
	
	@Column(name= "autoCount")
	private BigDecimal autoCount;  //刷单量
	
	@Column(name= "autoCountFloat")
	private BigDecimal autoCountFloat;  //量浮动比例
	
	@Column(name= "customerId")
	private Long customerId;  //customerId
	
	@Column(name= "atuoPriceType")
	private Integer atuoPriceType;  //1:定价下单 2：市价下单
	
	@Column(name= "upFloatPer")
	private BigDecimal upFloatPer;  //upFloatPer
	@Column(name= "isRobotSelf")
	private Integer isRobotSelf;  //是否只与自己成交0:否 1：是
	
	@Column(name= "isStartThirdPrice")
	private Integer isStartThirdPrice;  //成交价去第三方获取 0：否  1：是
	
	@Column(name= "priceSource")
	private String priceSource;  //成交价格数据来源地址  格式：地址名称-地址 火币网-www.huobi.com
	
	@Transient
	private String mobilePhone;  //手机号
	@Transient
	private String email;  //邮箱
	
	

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
	
	
	/**
	 * <p>id</p>
	 * @author:  songb
	 * @return:  Long 
	 * @Date :   2018-05-04 11:37:43    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>id</p>
	 * @author:  songb
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-05-04 11:37:43   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>交易币</p>
	 * @author:  songb
	 * @return:  String 
	 * @Date :   2018-05-04 11:37:43    
	 */
	public String getCoinCode() {
		return coinCode;
	}
	
	/**
	 * <p>交易币</p>
	 * @author:  songb
	 * @param:   @param coinCode
	 * @return:  void 
	 * @Date :   2018-05-04 11:37:43   
	 */
	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}
	
	
	/**
	 * <p>定价币</p>
	 * @author:  songb
	 * @return:  String 
	 * @Date :   2018-05-04 11:37:43    
	 */
	public String getFixPriceCoinCode() {
		return fixPriceCoinCode;
	}
	
	/**
	 * <p>定价币</p>
	 * @author:  songb
	 * @param:   @param fixPriceCoinCode
	 * @return:  void 
	 * @Date :   2018-05-04 11:37:43   
	 */
	public void setFixPriceCoinCode(String fixPriceCoinCode) {
		this.fixPriceCoinCode = fixPriceCoinCode;
	}
	
	
	/**
	 * <p>0真实货币1虚拟币</p>
	 * @author:  songb
	 * @return:  Integer 
	 * @Date :   2018-05-04 11:37:43    
	 */
	public Integer getFixPriceType() {
		return fixPriceType;
	}
	
	/**
	 * <p>0真实货币1虚拟币</p>
	 * @author:  songb
	 * @param:   @param fixPriceType
	 * @return:  void 
	 * @Date :   2018-05-04 11:37:43   
	 */
	public void setFixPriceType(Integer fixPriceType) {
		this.fixPriceType = fixPriceType;
	}
	
	
	/**
	 * <p>exCointoCoinId</p>
	 * @author:  songb
	 * @return:  Long 
	 * @Date :   2018-05-04 11:37:43    
	 */
	public Long getExCointoCoinId() {
		return exCointoCoinId;
	}
	
	/**
	 * <p>exCointoCoinId</p>
	 * @author:  songb
	 * @param:   @param exCointoCoinId
	 * @return:  void 
	 * @Date :   2018-05-04 11:37:43   
	 */
	public void setExCointoCoinId(Long exCointoCoinId) {
		this.exCointoCoinId = exCointoCoinId;
	}
	
	
	/**
	 * <p>1机器人的类型1提高活跃度机器人，2做多机器人，3做空机器人</p>
	 * @author:  songb
	 * @return:  Integer 
	 * @Date :   2018-05-04 11:37:43    
	 */
	public Integer getRobotType() {
		return robotType;
	}
	
	/**
	 * <p>1机器人的类型1提高活跃度机器人，2做多机器人，3做空机器人</p>
	 * @author:  songb
	 * @param:   @param robotType
	 * @return:  void 
	 * @Date :   2018-05-04 11:37:43   
	 */
	public void setRobotType(Integer robotType) {
		this.robotType = robotType;
	}
	
	
	/**
	 * <p>是否开启自动交易(0不开启1开启)</p>
	 * @author:  songb
	 * @return:  Integer 
	 * @Date :   2018-05-04 11:37:43    
	 */
	public Integer getIsSratAuto() {
		return isSratAuto;
	}
	
	/**
	 * <p>是否开启自动交易(0不开启1开启)</p>
	 * @author:  songb
	 * @param:   @param isSratAuto
	 * @return:  void 
	 * @Date :   2018-05-04 11:37:43   
	 */
	public void setIsSratAuto(Integer isSratAuto) {
		this.isSratAuto = isSratAuto;
	}
	
	
	/**
	 * <p>刷单账号</p>
	 * @author:  songb
	 * @return:  String 
	 * @Date :   2018-05-04 11:37:43    
	 */
	public String getAutoUsername() {
		return autoUsername;
	}
	
	/**
	 * <p>刷单账号</p>
	 * @author:  songb
	 * @param:   @param autoUsername
	 * @return:  void 
	 * @Date :   2018-05-04 11:37:43   
	 */
	public void setAutoUsername(String autoUsername) {
		this.autoUsername = autoUsername;
	}
	
	
	/**
	 * <p>刷单价格</p>
	 * @author:  songb
	 * @return:  BigDecimal 
	 * @Date :   2018-05-04 11:37:43    
	 */
	public BigDecimal getAutoPrice() {
		return autoPrice;
	}
	
	/**
	 * <p>刷单价格</p>
	 * @author:  songb
	 * @param:   @param autoPrice
	 * @return:  void 
	 * @Date :   2018-05-04 11:37:43   
	 */
	public void setAutoPrice(BigDecimal autoPrice) {
		this.autoPrice = autoPrice;
	}
	
	
	/**
	 * <p>价格浮动比例</p>
	 * @author:  songb
	 * @return:  BigDecimal 
	 * @Date :   2018-05-04 11:37:43    
	 */
	public BigDecimal getAutoPriceFloat() {
		return autoPriceFloat;
	}
	
	/**
	 * <p>价格浮动比例</p>
	 * @author:  songb
	 * @param:   @param autoPriceFloat
	 * @return:  void 
	 * @Date :   2018-05-04 11:37:43   
	 */
	public void setAutoPriceFloat(BigDecimal autoPriceFloat) {
		this.autoPriceFloat = autoPriceFloat;
	}
	
	
	/**
	 * <p>刷单量</p>
	 * @author:  songb
	 * @return:  BigDecimal 
	 * @Date :   2018-05-04 11:37:43    
	 */
	public BigDecimal getAutoCount() {
		return autoCount;
	}
	
	/**
	 * <p>刷单量</p>
	 * @author:  songb
	 * @param:   @param autoCount
	 * @return:  void 
	 * @Date :   2018-05-04 11:37:43   
	 */
	public void setAutoCount(BigDecimal autoCount) {
		this.autoCount = autoCount;
	}
	
	
	/**
	 * <p>量浮动比例</p>
	 * @author:  songb
	 * @return:  BigDecimal 
	 * @Date :   2018-05-04 11:37:43    
	 */
	public BigDecimal getAutoCountFloat() {
		return autoCountFloat;
	}
	
	/**
	 * <p>量浮动比例</p>
	 * @author:  songb
	 * @param:   @param autoCountFloat
	 * @return:  void 
	 * @Date :   2018-05-04 11:37:43   
	 */
	public void setAutoCountFloat(BigDecimal autoCountFloat) {
		this.autoCountFloat = autoCountFloat;
	}
	
	
	/**
	 * <p>customerId</p>
	 * @author:  songb
	 * @return:  Long 
	 * @Date :   2018-05-04 11:37:43    
	 */
	public Long getCustomerId() {
		return customerId;
	}
	
	/**
	 * <p>customerId</p>
	 * @author:  songb
	 * @param:   @param customerId
	 * @return:  void 
	 * @Date :   2018-05-04 11:37:43   
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	
	/**
	 * <p>atuoPriceType</p>
	 * @author:  songb
	 * @return:  Integer 
	 * @Date :   2018-05-04 11:37:43    
	 */
	public Integer getAtuoPriceType() {
		return atuoPriceType;
	}
	
	/**
	 * <p>atuoPriceType</p>
	 * @author:  songb
	 * @param:   @param atuoPriceType
	 * @return:  void 
	 * @Date :   2018-05-04 11:37:43   
	 */
	public void setAtuoPriceType(Integer atuoPriceType) {
		this.atuoPriceType = atuoPriceType;
	}
	
	
	/**
	 * <p>upFloatPer</p>
	 * @author:  songb
	 * @return:  BigDecimal 
	 * @Date :   2018-05-04 11:37:43    
	 */
	public BigDecimal getUpFloatPer() {
		return upFloatPer;
	}
	
	/**
	 * <p>upFloatPer</p>
	 * @author:  songb
	 * @param:   @param upFloatPer
	 * @return:  void 
	 * @Date :   2018-05-04 11:37:43   
	 */
	public void setUpFloatPer(BigDecimal upFloatPer) {
		this.upFloatPer = upFloatPer;
	}
	
	
	/**
	 * <p>刷单价格</p>
	 * @author:  songb
	 * @return:  Integer 
	 * @Date :   2018-05-04 11:37:43    
	 */
	public Integer getIsRobotSelf() {
		return isRobotSelf;
	}
	
	/**
	 * <p>刷单价格</p>
	 * @author:  songb
	 * @param:   @param isRobotSelf
	 * @return:  void 
	 * @Date :   2018-05-04 11:37:43   
	 */
	public void setIsRobotSelf(Integer isRobotSelf) {
		this.isRobotSelf = isRobotSelf;
	}
	
	
	/**
	 * <p>刷单价格</p>
	 * @author:  songb
	 * @return:  Integer 
	 * @Date :   2018-05-04 11:37:43    
	 */
	public Integer getIsStartThirdPrice() {
		return isStartThirdPrice;
	}
	
	/**
	 * <p>刷单价格</p>
	 * @author:  songb
	 * @param:   @param isStartThirdPrice
	 * @return:  void 
	 * @Date :   2018-05-04 11:37:43   
	 */
	public void setIsStartThirdPrice(Integer isStartThirdPrice) {
		this.isStartThirdPrice = isStartThirdPrice;
	}

	/**
	 * 数据成交价来源
	 * @return
	 */
	public String getPriceSource() {
		return priceSource;
	}

	public void setPriceSource(String priceSource) {
		this.priceSource = priceSource;
	}
	
	

}
