/**
 * Copyright:   
 * @author:      tianpengyu
 * @version:     V1.0 
 * @Date:        2018-06-25 19:49:22 
 */
package hry.admin.commend.model;


import hry.admin.customer.model.AppPersonInfo;
import hry.bean.BaseModel;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p> AppCommendMoney </p>
 * @author:         tianpengyu
 * @Date :          2018-06-25 19:49:22  
 */
@Table(name="app_commend_money")
public class AppCommendMoney extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "custromerId")
	private Long custromerId;  //
	
	@Column(name= "custromerName")
	private String custromerName;  //
	
	@Column(name= "refecode")
	private String refecode;  //
	
	@Column(name= "moneyNum")
	private BigDecimal moneyNum;  //
	
	@Column(name= "paidMoney")
	private BigDecimal paidMoney;  //
	
	@Column(name= "fixPriceType")
	private Integer fixPriceType;  //
	
	@Column(name= "coinCode")
	private String coinCode;  //
	
	@Column(name= "dealType")
	private Long dealType;  //
	
	@Column(name= "userCode")
	private String userCode;  //
	
	@Column(name= "commendOne")
	private BigDecimal commendOne;  //
	
	@Column(name= "commendTwo")
	private BigDecimal commendTwo;  //
	
	@Column(name= "commendThree")
	private BigDecimal commendThree;  //
	
	@Column(name= "commendLater")
	private BigDecimal commendLater;  //
	
	@Column(name= "lastPaidTime")
	private Date lastPaidTime;  //
	
	@Column(name= "saasId")
	private String saasId;  //

	@Transient   //不与数据库映射的字段
	private AppPersonInfo appPersonInfo;

	@Transient
	private String retentionNumber;//保留几位小数



	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  Integer 
	 * @Date :   2018-06-25 19:49:22    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-06-25 19:49:22   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  Long 
	 * @Date :   2018-06-25 19:49:22    
	 */
	public Long getCustromerId() {
		return custromerId;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param custromerId
	 * @return:  void 
	 * @Date :   2018-06-25 19:49:22   
	 */
	public void setCustromerId(Long custromerId) {
		this.custromerId = custromerId;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-06-25 19:49:22    
	 */
	public String getCustromerName() {
		return custromerName;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param custromerName
	 * @return:  void 
	 * @Date :   2018-06-25 19:49:22   
	 */
	public void setCustromerName(String custromerName) {
		this.custromerName = custromerName;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-06-25 19:49:22    
	 */
	public String getRefecode() {
		return refecode;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param refecode
	 * @return:  void 
	 * @Date :   2018-06-25 19:49:22   
	 */
	public void setRefecode(String refecode) {
		this.refecode = refecode;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  BigDecimal 
	 * @Date :   2018-06-25 19:49:22    
	 */
	public BigDecimal getMoneyNum() {
		return moneyNum;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param moneyNum
	 * @return:  void 
	 * @Date :   2018-06-25 19:49:22   
	 */
	public void setMoneyNum(BigDecimal moneyNum) {
		this.moneyNum = moneyNum;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  BigDecimal 
	 * @Date :   2018-06-25 19:49:22    
	 */
	public BigDecimal getPaidMoney() {
		return paidMoney;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param paidMoney
	 * @return:  void 
	 * @Date :   2018-06-25 19:49:22   
	 */
	public void setPaidMoney(BigDecimal paidMoney) {
		this.paidMoney = paidMoney;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  Integer 
	 * @Date :   2018-06-25 19:49:22    
	 */
	public Integer getFixPriceType() {
		return fixPriceType;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param fixPriceType
	 * @return:  void 
	 * @Date :   2018-06-25 19:49:22   
	 */
	public void setFixPriceType(Integer fixPriceType) {
		this.fixPriceType = fixPriceType;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-06-25 19:49:22    
	 */
	public String getCoinCode() {
		return coinCode;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param coinCode
	 * @return:  void 
	 * @Date :   2018-06-25 19:49:22   
	 */
	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  Long 
	 * @Date :   2018-06-25 19:49:22    
	 */
	public Long getDealType() {
		return dealType;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param dealType
	 * @return:  void 
	 * @Date :   2018-06-25 19:49:22   
	 */
	public void setDealType(Long dealType) {
		this.dealType = dealType;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-06-25 19:49:22    
	 */
	public String getUserCode() {
		return userCode;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param userCode
	 * @return:  void 
	 * @Date :   2018-06-25 19:49:22   
	 */
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  BigDecimal 
	 * @Date :   2018-06-25 19:49:22    
	 */
	public BigDecimal getCommendOne() {
		return commendOne;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param commendOne
	 * @return:  void 
	 * @Date :   2018-06-25 19:49:22   
	 */
	public void setCommendOne(BigDecimal commendOne) {
		this.commendOne = commendOne;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  BigDecimal 
	 * @Date :   2018-06-25 19:49:22    
	 */
	public BigDecimal getCommendTwo() {
		return commendTwo;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param commendTwo
	 * @return:  void 
	 * @Date :   2018-06-25 19:49:22   
	 */
	public void setCommendTwo(BigDecimal commendTwo) {
		this.commendTwo = commendTwo;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  BigDecimal 
	 * @Date :   2018-06-25 19:49:22    
	 */
	public BigDecimal getCommendThree() {
		return commendThree;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param commendThree
	 * @return:  void 
	 * @Date :   2018-06-25 19:49:22   
	 */
	public void setCommendThree(BigDecimal commendThree) {
		this.commendThree = commendThree;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  BigDecimal 
	 * @Date :   2018-06-25 19:49:22    
	 */
	public BigDecimal getCommendLater() {
		return commendLater;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param commendLater
	 * @return:  void 
	 * @Date :   2018-06-25 19:49:22   
	 */
	public void setCommendLater(BigDecimal commendLater) {
		this.commendLater = commendLater;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  Date 
	 * @Date :   2018-06-25 19:49:22    
	 */
	public Date getLastPaidTime() {
		return lastPaidTime;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param lastPaidTime
	 * @return:  void 
	 * @Date :   2018-06-25 19:49:22   
	 */
	public void setLastPaidTime(Date lastPaidTime) {
		this.lastPaidTime = lastPaidTime;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-06-25 19:49:22    
	 */
	public String getSaasId() {
		return saasId;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param saasId
	 * @return:  void 
	 * @Date :   2018-06-25 19:49:22   
	 */
	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}

	public AppPersonInfo getAppPersonInfo() {
		return appPersonInfo;
	}

	public void setAppPersonInfo(AppPersonInfo appPersonInfo) {
		this.appPersonInfo = appPersonInfo;
	}

	public String getRetentionNumber() {
		return retentionNumber;
	}

	public void setRetentionNumber(String retentionNumber) {
		this.retentionNumber = retentionNumber;
	}
}
