/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      menwei
 * @version:     V1.0 
 * @Date:        2017-11-28 17:40:59 
 */
package hry.customer.commend.model;

import static javax.persistence.GenerationType.IDENTITY;
import hry.core.mvc.model.BaseModel;
import hry.customer.person.model.AppPersonInfo;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * <p> AppCommendTrade </p>
 * @author:         menwei
 * @Date :          2017-11-28 17:40:59  
 */
@Table(name="app_commend_trade")
public class AppCommendTrade extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;  //id
	
	@Column(name= "custromerId")
	private Long custromerId;  //代理商id
	
	@Column(name= "custromerName")
	private String custromerName;  //代理商名称
	
	@Column(name= "ordertNum")
	private String ordertNum;  //订单号

	
	@Column(name= "coinCode")
	private String coinCode;  //佣金币种
	
	
	@Column(name= "fixPriceType")
	private Integer fixPriceType;  //0真实货币1虚拟币
	
	
	@Column(name= "feeamout")
	private BigDecimal feeamout;  //手续费金额 appCommendTrade.setFeeamout(orderInfo.getTransactionCount());
	
	@Column(name= "basemoney")
	private BigDecimal basemoney;  //返佣基数  appCommendTrade.setBasemoney(orderInfo.getTransactionSellFee());
	
	@Column(name= "rewardmoney")
	private BigDecimal rewardmoney;  //总奖励金额
	
	@Column(name= "deliveryName")
	private String deliveryName;  //交款人姓名
	
	@Column(name= "deliveryId")
	private Integer deliveryId;  //交款人id
	
	
	@Column(name= "hierarchy")  //层级
	private Integer hierarchy;  
	
	@Column(name= "transactionTime")  //成交时间
	private Date transactionTime; 
	
	
	@Column(name= "ratetype")
	private String ratetype;  //1:个人单独设置的返佣比例 2:平台设置的返佣比例（根据user，private BigDecimal aloneMoney;//单独奖励比例这个来设置的）
	
	@Column(name= "changeMoney")
	private BigDecimal changeMoney;  //最新价*（佣金币_USDT最新成交价）作用？

	@Column(name="userMoney")
	private BigDecimal userMoney;  //单独奖励的佣金

	@Column(name="personType")
	private Integer personType;  //1买，2卖

	@Column(name="currentMoney")
	private BigDecimal currentMoney; //成交记录里面的成交价格 appCommendTrade.setCurrentMoney(transactionPrice);
	
	@Column(name="status")
	private Integer status;//佣金是否发放 0未发放 1 已发放 2 已作废 3 发送失败;
	
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
	public BigDecimal getFeemoney() {
		return feemoney;
	}

	public void setFeemoney(BigDecimal feemoney) {
		this.feemoney = feemoney;
	}

	public Integer getPersonType() {
		return personType;
	}

	public void setPersonType(Integer personType) {
		this.personType = personType;
	}

	public BigDecimal getUserMoney() {
		return userMoney;
	}

	public void setUserMoney(BigDecimal userMoney) {
		this.userMoney = userMoney;
	}

	public BigDecimal getChangeMoney() {
		return changeMoney;
	}

	public void setChangeMoney(BigDecimal changeMoney) {
		this.changeMoney = changeMoney;
	}

	public BigDecimal getCurrentMoney() {
		return currentMoney;
	}

	public void setCurrentMoney(BigDecimal currentMoney) {
		this.currentMoney = currentMoney;
	}

	public String getRatetype() {
		return ratetype;
	}

	public void setRatetype(String ratetype) {
		this.ratetype = ratetype;
	}

	public String getCoinCode() {
		return coinCode;
	}

	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}

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

	public Integer getHierarchy() {
		return hierarchy;
	}

	public void setHierarchy(Integer hierarchy) {
		this.hierarchy = hierarchy;
	}

	
	
	public Date getTransactionTime() {
		return transactionTime;
	}

	public void setTransactionTime(Date transactionTime) {
		this.transactionTime = transactionTime;
	}

	/**
	 * <p>id</p>
	 * @author:  menwei
	 * @return:  Integer 
	 * @Date :   2017-11-28 17:40:59    
	 */
	public Integer getId() {
		return id;
	}
	
	/**
	 * <p>id</p>
	 * @author:  menwei
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2017-11-28 17:40:59   
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	
	/**
	 * <p>代理商id</p>
	 * @author:  menwei
	 * @return:  Long 
	 * @Date :   2017-11-28 17:40:59    
	 */
	public Long getCustromerId() {
		return custromerId;
	}
	
	/**
	 * <p>代理商id</p>
	 * @author:  menwei
	 * @param:   @param custromerId
	 * @return:  void 
	 * @Date :   2017-11-28 17:40:59   
	 */
	public void setCustromerId(Long custromerId) {
		this.custromerId = custromerId;
	}
	
	
	/**
	 * <p>代理商名称</p>
	 * @author:  menwei
	 * @return:  String 
	 * @Date :   2017-11-28 17:40:59    
	 */
	public String getCustromerName() {
		return custromerName;
	}
	
	/**
	 * <p>代理商名称</p>
	 * @author:  menwei
	 * @param:   @param custromerName
	 * @return:  void 
	 * @Date :   2017-11-28 17:40:59   
	 */
	public void setCustromerName(String custromerName) {
		this.custromerName = custromerName;
	}
	
	
	/**
	 * <p>订单号</p>
	 * @author:  menwei
	 * @return:  String 
	 * @Date :   2017-11-28 17:40:59    
	 */
	public String getOrdertNum() {
		return ordertNum;
	}
	
	/**
	 * <p>订单号</p>
	 * @author:  menwei
	 * @param:   @param ordertNum
	 * @return:  void 
	 * @Date :   2017-11-28 17:40:59   
	 */
	public void setOrdertNum(String ordertNum) {
		this.ordertNum = ordertNum;
	}
	
	
	/**
	 * <p>0真实货币1虚拟币</p>
	 * @author:  menwei
	 * @return:  Integer 
	 * @Date :   2017-11-28 17:40:59    
	 */
	public Integer getFixPriceType() {
		return fixPriceType;
	}
	
	/**
	 * <p>0真实货币1虚拟币</p>
	 * @author:  menwei
	 * @param:   @param fixPriceType
	 * @return:  void 
	 * @Date :   2017-11-28 17:40:59   
	 */
	public void setFixPriceType(Integer fixPriceType) {
		this.fixPriceType = fixPriceType;
	}
	
	
	
	
	
	/**
	 * <p>手续费金额</p>
	 * @author:  menwei
	 * @return:  BigDecimal 
	 * @Date :   2017-11-28 17:40:59    
	 */
	public BigDecimal getFeeamout() {
		return feeamout;
	}
	
	/**
	 * <p>手续费金额</p>
	 * @author:  menwei
	 * @param:   @param feeamout
	 * @return:  void 
	 * @Date :   2017-11-28 17:40:59   
	 */
	public void setFeeamout(BigDecimal feeamout) {
		this.feeamout = feeamout;
	}
	
	
	/**
	 * <p>返佣基数</p>
	 * @author:  menwei
	 * @return:  BigDecimal 
	 * @Date :   2017-11-28 17:40:59    
	 */
	public BigDecimal getBasemoney() {
		return basemoney;
	}
	
	/**
	 * <p>返佣基数</p>
	 * @author:  menwei
	 * @param:   @param basemoney
	 * @return:  void 
	 * @Date :   2017-11-28 17:40:59   
	 */
	public void setBasemoney(BigDecimal basemoney) {
		this.basemoney = basemoney;
	}
	
	
	/**
	 * <p>奖励金额</p>
	 * @author:  menwei
	 * @return:  BigDecimal 
	 * @Date :   2017-11-28 17:40:59    
	 */
	public BigDecimal getRewardmoney() {
		return rewardmoney;
	}
	
	/**
	 * <p>奖励金额</p>
	 * @author:  menwei
	 * @param:   @param rewardmoney
	 * @return:  void 
	 * @Date :   2017-11-28 17:40:59   
	 */
	public void setRewardmoney(BigDecimal rewardmoney) {
		this.rewardmoney = rewardmoney;
	}
	
	
	
	/**
	 * <p>交款人</p>
	 * @author:  menwei
	 * @return:  String 
	 * @Date :   2017-11-28 17:40:59    
	 */
	public String getDeliveryName() {
		return deliveryName;
	}
	
	/**
	 * <p>交款人</p>
	 * @author:  menwei
	 * @param:   @param deliveryName
	 * @return:  void 
	 * @Date :   2017-11-28 17:40:59   
	 */
	public void setDeliveryName(String deliveryName) {
		this.deliveryName = deliveryName;
	}
	
	
	/**
	 * <p>交款人id</p>
	 * @author:  menwei
	 * @return:  Integer 
	 * @Date :   2017-11-28 17:40:59    
	 */
	public Integer getDeliveryId() {
		return deliveryId;
	}
	
	/**
	 * <p>交款人id</p>
	 * @author:  menwei
	 * @param:   @param deliveryId
	 * @return:  void 
	 * @Date :   2017-11-28 17:40:59   
	 */
	public void setDeliveryId(Integer deliveryId) {
		this.deliveryId = deliveryId;
	}

	public AppPersonInfo getAppPersonInfo() {
		return appPersonInfo;
	}

	public void setAppPersonInfo(AppPersonInfo appPersonInfo) {
		this.appPersonInfo = appPersonInfo;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
	

}
