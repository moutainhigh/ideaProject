/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      menwei
 * @version:     V1.0 
 * @Date:        2017-11-29 10:05:55 
 */
package hry.customer.commend.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import hry.core.mvc.model.BaseModel;
import hry.customer.person.model.AppPersonInfo;

/**
 * <p> AppCommendMoney </p>
 * @author:         menwei
 * @Date :          2017-11-29 10:05:55  
 */
@Table(name="app_commend_money")
public class AppCommendMoney extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //id
	
	@Column(name= "custromerId")
	private Long custromerId;  //custromerId
	
	@Column(name= "custromerName")
	private String custromerName;  //custromerName
	
	@Column(name= "refecode")
	private String refecode;  //邀请码
	
	@Column(name= "moneyNum")
	private BigDecimal moneyNum;  //总佣金(commendOne+commendTwo+commendThree+commendLater)
	
	@Column(name= "paidMoney")
	private BigDecimal paidMoney;  //已派发佣金
	
	@Column(name= "lastPaidTime")
	private Date lastPaidTime;  //最后一次派发时间
	
	@Column(name= "fixPriceType")
	private Integer fixPriceType;  //fixPriceType
	
	
	@Column(name= "coinCode")
	private String coinCode;  //佣金币种
	

	@Column(name = "dealType")
	private Integer dealType;
	// 编号
	@Column(name = "userCode")
	private String userCode;
	
	@Column(name = "commendOne")
	private BigDecimal commendOne;
	@Column(name = "commendTwo")
	private BigDecimal commendTwo;
	@Column(name = "commendThree")
	private BigDecimal commendThree;
	@Column(name = "commendLater")
	private BigDecimal commendLater;
	
	@Transient   //不与数据库映射的字段
	private AppPersonInfo appPersonInfo;
	@Transient
	private String retentionNumber;//保留几位小数
	
	

	public String getRetentionNumber() {
		return retentionNumber;
	}

	public void setRetentionNumber(String retentionNumber) {
		this.retentionNumber = retentionNumber;
	}

	public AppPersonInfo getAppPersonInfo() {
		return appPersonInfo;
	}

	public void setAppPersonInfo(AppPersonInfo appPersonInfo) {
		this.appPersonInfo = appPersonInfo;
	}

	public Integer getDealType() {
		return dealType;
	}

	public void setDealType(Integer dealType) {
		this.dealType = dealType;
	}



	public String getCoinCode() {
		return coinCode;
	}

	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}


	public BigDecimal getCommendLater() {
		return commendLater;
	}

	public void setCommendLater(BigDecimal commendLater) {
		this.commendLater = commendLater;
	}

	public BigDecimal getCommendOne() {
		return commendOne;
	}

	public void setCommendOne(BigDecimal commendOne) {
		this.commendOne = commendOne;
	}

	public BigDecimal getCommendTwo() {
		return commendTwo;
	}

	public void setCommendTwo(BigDecimal commendTwo) {
		this.commendTwo = commendTwo;
	}

	public BigDecimal getCommendThree() {
		return commendThree;
	}

	public void setCommendThree(BigDecimal commendThree) {
		this.commendThree = commendThree;
	}


	
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * <p>custromerId</p>
	 * @author:  menwei
	 * @return:  Long 
	 * @Date :   2017-11-29 10:05:55    
	 */
	public Long getCustromerId() {
		return custromerId;
	}
	
	/**
	 * <p>custromerId</p>
	 * @author:  menwei
	 * @param:   @param custromerId
	 * @return:  void 
	 * @Date :   2017-11-29 10:05:55   
	 */
	public void setCustromerId(Long custromerId) {
		this.custromerId = custromerId;
	}
	
	
	/**
	 * <p>custromerName</p>
	 * @author:  menwei
	 * @return:  String 
	 * @Date :   2017-11-29 10:05:55    
	 */
	public String getCustromerName() {
		return custromerName;
	}
	
	/**
	 * <p>custromerName</p>
	 * @author:  menwei
	 * @param:   @param custromerName
	 * @return:  void 
	 * @Date :   2017-11-29 10:05:55   
	 */
	public void setCustromerName(String custromerName) {
		this.custromerName = custromerName;
	}
	
	
	/**
	 * <p>邀请码</p>
	 * @author:  menwei
	 * @return:  String 
	 * @Date :   2017-11-29 10:05:55    
	 */
	public String getRefecode() {
		return refecode;
	}
	
	/**
	 * <p>邀请码</p>
	 * @author:  menwei
	 * @param:   @param refecode
	 * @return:  void 
	 * @Date :   2017-11-29 10:05:55   
	 */
	public void setRefecode(String refecode) {
		this.refecode = refecode;
	}
	
	
	/**
	 * <p>总佣金</p>
	 * @author:  menwei
	 * @return:  BigDecimal 
	 * @Date :   2017-11-29 10:05:55    
	 */
	public BigDecimal getMoneyNum() {
		return moneyNum;
	}
	
	/**
	 * <p>总佣金</p>
	 * @author:  menwei
	 * @param:   @param moneyNum
	 * @return:  void 
	 * @Date :   2017-11-29 10:05:55   
	 */
	public void setMoneyNum(BigDecimal moneyNum) {
		this.moneyNum = moneyNum;
	}
	
	
	/**
	 * <p>已派发佣金</p>
	 * @author:  menwei
	 * @return:  BigDecimal 
	 * @Date :   2017-11-29 10:05:55    
	 */
	public BigDecimal getPaidMoney() {
		return paidMoney;
	}
	
	/**
	 * <p>已派发佣金</p>
	 * @author:  menwei
	 * @param:   @param paidMoney
	 * @return:  void 
	 * @Date :   2017-11-29 10:05:55   
	 */
	public void setPaidMoney(BigDecimal paidMoney) {
		this.paidMoney = paidMoney;
	}
	
	
	/**
	 * <p>fixPriceType</p>
	 * @author:  menwei
	 * @return:  Integer 
	 * @Date :   2017-11-29 10:05:55    
	 */
	public Integer getFixPriceType() {
		return fixPriceType;
	}
	
	/**
	 * <p>fixPriceType</p>
	 * @author:  menwei
	 * @param:   @param fixPriceType
	 * @return:  void 
	 * @Date :   2017-11-29 10:05:55   
	 */
	public void setFixPriceType(Integer fixPriceType) {
		this.fixPriceType = fixPriceType;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public Date getLastPaidTime() {
		return lastPaidTime;
	}

	public void setLastPaidTime(Date lastPaidTime) {
		this.lastPaidTime = lastPaidTime;
	}
	
	

	

}
