/**
 * Copyright:   
 * @author:      tianpengyu
 * @version:     V1.0 
 * @Date:        2018-06-25 19:50:18 
 */
package hry.admin.commend.model;


import hry.admin.customer.model.AppPersonInfo;
import hry.bean.BaseModel;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;

/**
 * <p> AppCommendTrade </p>
 * @author:         tianpengyu
 * @Date :          2018-06-25 19:50:18  
 */
@Table(name="app_commend_trade")
public class AppCommendTrade extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;  //
	
	@Column(name= "custromerId")
	private Long custromerId;  //
	
	@Column(name= "custromerName")
	private String custromerName;  //
	
	@Column(name= "ordertNum")
	private String ordertNum;  //
	
	@Column(name= "coinCode")
	private String coinCode;  //
	
	@Column(name= "fixPriceCoinCode")
	private String fixPriceCoinCode;  //
	
	@Column(name= "fixPriceType")
	private Integer fixPriceType;  //
	
	@Column(name= "rewardmoney")
	private BigDecimal rewardmoney;  //
	
	@Column(name= "deliveryName")
	private String deliveryName;  //
	
	@Column(name= "deliveryId")
	private Integer deliveryId;  //
	
	@Column(name= "hierarchy")
	private Integer hierarchy;  //
	
	@Column(name= "userMoney")
	private BigDecimal userMoney;  //
	
	@Column(name= "ratetype")
	private String ratetype;  //
	
	@Column(name= "personType")
	private Integer personType;  //
	
	@Column(name= "basemoney")
	private BigDecimal basemoney;  //
	
	@Column(name= "changeMoney")
	private BigDecimal changeMoney;  //
	
	@Column(name= "currentMoney")
	private BigDecimal currentMoney;  //
	
	@Column(name= "feeamout")
	private BigDecimal feeamout;  //
	
	@Column(name= "transactionTime")
	private Date transactionTime;  //
	
	@Column(name= "saasId")
	private String saasId;  //
	
	@Column(name= "status")
	private Integer status;  //
	
	@Transient
	private BigDecimal oneMoney;

	@Transient
	private BigDecimal twoMoney;

	@Transient
	private BigDecimal threeMoney;

	@Transient
	private BigDecimal feemoney;
	@Transient
	private String deliveryEmail;
	@Transient
	private String deliveryMobilePhone;
	@Transient   //不与数据库映射的字段
	private AppPersonInfo appPersonInfo;

	public BigDecimal getOneMoney() {
		return oneMoney;
	}

	public void setOneMoney(BigDecimal oneMoney) {
		this.oneMoney = oneMoney;
	}

	public BigDecimal getTwoMoney() {
		return twoMoney;
	}

	public void setTwoMoney(BigDecimal twoMoney) {
		this.twoMoney = twoMoney;
	}

	public BigDecimal getThreeMoney() {
		return threeMoney;
	}

	public void setThreeMoney(BigDecimal threeMoney) {
		this.threeMoney = threeMoney;
	}

	public BigDecimal getFeemoney() {
		return feemoney;
	}

	public void setFeemoney(BigDecimal feemoney) {
		this.feemoney = feemoney;
	}

	public String getDeliveryEmail() {
		return deliveryEmail;
	}

	public void setDeliveryEmail(String deliveryEmail) {
		this.deliveryEmail = deliveryEmail;
	}

	public String getDeliveryMobilePhone() {
		return deliveryMobilePhone;
	}

	public void setDeliveryMobilePhone(String deliveryMobilePhone) {
		this.deliveryMobilePhone = deliveryMobilePhone;
	}

	public AppPersonInfo getAppPersonInfo() {
		return appPersonInfo;
	}

	public void setAppPersonInfo(AppPersonInfo appPersonInfo) {
		this.appPersonInfo = appPersonInfo;
	}

	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  Integer 
	 * @Date :   2018-06-25 19:50:18    
	 */
	public Integer getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-06-25 19:50:18   
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  Long 
	 * @Date :   2018-06-25 19:50:18    
	 */
	public Long getCustromerId() {
		return custromerId;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param custromerId
	 * @return:  void 
	 * @Date :   2018-06-25 19:50:18   
	 */
	public void setCustromerId(Long custromerId) {
		this.custromerId = custromerId;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-06-25 19:50:18    
	 */
	public String getCustromerName() {
		return custromerName;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param custromerName
	 * @return:  void 
	 * @Date :   2018-06-25 19:50:18   
	 */
	public void setCustromerName(String custromerName) {
		this.custromerName = custromerName;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-06-25 19:50:18    
	 */
	public String getOrdertNum() {
		return ordertNum;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param ordertNum
	 * @return:  void 
	 * @Date :   2018-06-25 19:50:18   
	 */
	public void setOrdertNum(String ordertNum) {
		this.ordertNum = ordertNum;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-06-25 19:50:18    
	 */
	public String getCoinCode() {
		return coinCode;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param coinCode
	 * @return:  void 
	 * @Date :   2018-06-25 19:50:18   
	 */
	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-06-25 19:50:18    
	 */
	public String getFixPriceCoinCode() {
		return fixPriceCoinCode;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param fixPriceCoinCode
	 * @return:  void 
	 * @Date :   2018-06-25 19:50:18   
	 */
	public void setFixPriceCoinCode(String fixPriceCoinCode) {
		this.fixPriceCoinCode = fixPriceCoinCode;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  Integer 
	 * @Date :   2018-06-25 19:50:18    
	 */
	public Integer getFixPriceType() {
		return fixPriceType;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param fixPriceType
	 * @return:  void 
	 * @Date :   2018-06-25 19:50:18   
	 */
	public void setFixPriceType(Integer fixPriceType) {
		this.fixPriceType = fixPriceType;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  BigDecimal 
	 * @Date :   2018-06-25 19:50:18    
	 */
	public BigDecimal getRewardmoney() {
		return rewardmoney;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param rewardmoney
	 * @return:  void 
	 * @Date :   2018-06-25 19:50:18   
	 */
	public void setRewardmoney(BigDecimal rewardmoney) {
		this.rewardmoney = rewardmoney;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-06-25 19:50:18    
	 */
	public String getDeliveryName() {
		return deliveryName;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param deliveryName
	 * @return:  void 
	 * @Date :   2018-06-25 19:50:18   
	 */
	public void setDeliveryName(String deliveryName) {
		this.deliveryName = deliveryName;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  Integer 
	 * @Date :   2018-06-25 19:50:18    
	 */
	public Integer getDeliveryId() {
		return deliveryId;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param deliveryId
	 * @return:  void 
	 * @Date :   2018-06-25 19:50:18   
	 */
	public void setDeliveryId(Integer deliveryId) {
		this.deliveryId = deliveryId;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  Integer 
	 * @Date :   2018-06-25 19:50:18    
	 */
	public Integer getHierarchy() {
		return hierarchy;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param hierarchy
	 * @return:  void 
	 * @Date :   2018-06-25 19:50:18   
	 */
	public void setHierarchy(Integer hierarchy) {
		this.hierarchy = hierarchy;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  BigDecimal 
	 * @Date :   2018-06-25 19:50:18    
	 */
	public BigDecimal getUserMoney() {
		return userMoney;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param userMoney
	 * @return:  void 
	 * @Date :   2018-06-25 19:50:18   
	 */
	public void setUserMoney(BigDecimal userMoney) {
		this.userMoney = userMoney;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-06-25 19:50:18    
	 */
	public String getRatetype() {
		return ratetype;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param ratetype
	 * @return:  void 
	 * @Date :   2018-06-25 19:50:18   
	 */
	public void setRatetype(String ratetype) {
		this.ratetype = ratetype;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  Integer 
	 * @Date :   2018-06-25 19:50:18    
	 */
	public Integer getPersonType() {
		return personType;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param personType
	 * @return:  void 
	 * @Date :   2018-06-25 19:50:18   
	 */
	public void setPersonType(Integer personType) {
		this.personType = personType;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  BigDecimal 
	 * @Date :   2018-06-25 19:50:18    
	 */
	public BigDecimal getBasemoney() {
		return basemoney;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param basemoney
	 * @return:  void 
	 * @Date :   2018-06-25 19:50:18   
	 */
	public void setBasemoney(BigDecimal basemoney) {
		this.basemoney = basemoney;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  BigDecimal 
	 * @Date :   2018-06-25 19:50:18    
	 */
	public BigDecimal getChangeMoney() {
		return changeMoney;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param changeMoney
	 * @return:  void 
	 * @Date :   2018-06-25 19:50:18   
	 */
	public void setChangeMoney(BigDecimal changeMoney) {
		this.changeMoney = changeMoney;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  BigDecimal 
	 * @Date :   2018-06-25 19:50:18    
	 */
	public BigDecimal getCurrentMoney() {
		return currentMoney;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param currentMoney
	 * @return:  void 
	 * @Date :   2018-06-25 19:50:18   
	 */
	public void setCurrentMoney(BigDecimal currentMoney) {
		this.currentMoney = currentMoney;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  BigDecimal 
	 * @Date :   2018-06-25 19:50:18    
	 */
	public BigDecimal getFeeamout() {
		return feeamout;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param feeamout
	 * @return:  void 
	 * @Date :   2018-06-25 19:50:18   
	 */
	public void setFeeamout(BigDecimal feeamout) {
		this.feeamout = feeamout;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  Date 
	 * @Date :   2018-06-25 19:50:18    
	 */
	public Date getTransactionTime() {
		return transactionTime;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param transactionTime
	 * @return:  void 
	 * @Date :   2018-06-25 19:50:18   
	 */
	public void setTransactionTime(Date transactionTime) {
		this.transactionTime = transactionTime;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-06-25 19:50:18    
	 */
	public String getSaasId() {
		return saasId;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param saasId
	 * @return:  void 
	 * @Date :   2018-06-25 19:50:18   
	 */
	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
	

}
