/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      zenghao
 * @version:     V1.0 
 * @Date:        2016-11-22 18:36:27 
 */
package hry.exchange.subscription.model;

import static javax.persistence.GenerationType.IDENTITY;
import hry.core.mvc.model.BaseModel;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * <p> ExSubscriptionRecord </p>
 * @author:         zenghao
 * @Date :          2016-11-22 18:36:27  
 */
@Table(name="ex_subscription_record")
public class ExSubscriptionRecord extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //id
	
	@Column(name= "planId")
	private Long planId;  //认购计划id
	
	@Column(name= "customerId")
	private Long customerId;  //客户id
	
	@Column(name= "userName")
	private String userName;  //账户名
	
	@Column(name= "trueName")
	private String trueName;  //真实姓名
	
	@Column(name= "coinCode")
	private String coinCode;  //币种代码
	
	@Column(name= "coinName")
	private String coinName;  //币种名称
	
	@Column(name= "period")
	private Integer period;  //认购期数
	
	@Column(name= "time")
	private Date time;  //认购时间
	
	@Column(name= "price")
	private BigDecimal price;  //认购价格
	
	@Column(name= "amount")
	private BigDecimal amount;  //认购数量
	
	@Column(name= "totalMoney")
	private BigDecimal totalMoney;  //认购总金额
	
	@Column(name= "backAmount")
	private BigDecimal backAmount;  //回购数量
	
	@Column(name= "transactionNum")
	private String transactionNum;  //认购单号
	
	@Column(name= "state")
	private Integer state;  //状态（0申购成功，1部分回购，2全部回购,3回购失效）
	
	@Column(name= "repurchaseFee")
	private BigDecimal repurchaseFee;  //回购手续费
	
	@Column(name= "applyBackNum")
	private BigDecimal applyBackNum;//回购申请中数量
	
	@Column(name= "backEndTime")
	private Date backEndTime;//回购截止时间
	
	@Transient
	private BigDecimal currenNum;//当前认购数量
	@Transient
	private BigDecimal maxNum;//已认购数量，已认购总金额
	@Transient
	private BigDecimal circulation;//发行量or已售量
	
	
	/**
	 * <p>id</p>
	 * @author:  zenghao
	 * @return:  Long 
	 * @Date :   2016-11-22 18:36:27    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>id</p>
	 * @author:  zenghao
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2016-11-22 18:36:27   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>认购计划id</p>
	 * @author:  zenghao
	 * @return:  Long 
	 * @Date :   2016-11-22 18:36:27    
	 */
	public Long getPlanId() {
		return planId;
	}
	
	/**
	 * <p>认购计划id</p>
	 * @author:  zenghao
	 * @param:   @param planId
	 * @return:  void 
	 * @Date :   2016-11-22 18:36:27   
	 */
	public void setPlanId(Long planId) {
		this.planId = planId;
	}
	
	
	/**
	 * <p>客户id</p>
	 * @author:  zenghao
	 * @return:  Long 
	 * @Date :   2016-11-22 18:36:27    
	 */
	public Long getCustomerId() {
		return customerId;
	}
	
	/**
	 * <p>客户id</p>
	 * @author:  zenghao
	 * @param:   @param customerId
	 * @return:  void 
	 * @Date :   2016-11-22 18:36:27   
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	
	/**
	 * <p>账户名</p>
	 * @author:  zenghao
	 * @return:  String 
	 * @Date :   2016-11-22 18:36:27    
	 */
	public String getUserName() {
		return userName;
	}
	
	/**
	 * <p>账户名</p>
	 * @author:  zenghao
	 * @param:   @param userName
	 * @return:  void 
	 * @Date :   2016-11-22 18:36:27   
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
	/**
	 * <p>真实姓名</p>
	 * @author:  zenghao
	 * @return:  String 
	 * @Date :   2016-11-22 18:36:27    
	 */
	public String getTrueName() {
		return trueName;
	}
	
	/**
	 * <p>真实姓名</p>
	 * @author:  zenghao
	 * @param:   @param trueName
	 * @return:  void 
	 * @Date :   2016-11-22 18:36:27   
	 */
	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}
	
	
	/**
	 * <p>币种代码</p>
	 * @author:  zenghao
	 * @return:  String 
	 * @Date :   2016-11-22 18:36:27    
	 */
	public String getCoinCode() {
		return coinCode;
	}
	
	/**
	 * <p>币种代码</p>
	 * @author:  zenghao
	 * @param:   @param coinCode
	 * @return:  void 
	 * @Date :   2016-11-22 18:36:27   
	 */
	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}
	
	
	/**
	 * <p>币种名称</p>
	 * @author:  zenghao
	 * @return:  String 
	 * @Date :   2016-11-22 18:36:27    
	 */
	public String getCoinName() {
		return coinName;
	}
	
	/**
	 * <p>币种名称</p>
	 * @author:  zenghao
	 * @param:   @param coinName
	 * @return:  void 
	 * @Date :   2016-11-22 18:36:27   
	 */
	public void setCoinName(String coinName) {
		this.coinName = coinName;
	}
	
	
	/**
	 * <p>认购期数</p>
	 * @author:  zenghao
	 * @return:  Integer 
	 * @Date :   2016-11-22 18:36:27    
	 */
	public Integer getPeriod() {
		return period;
	}
	
	/**
	 * <p>认购期数</p>
	 * @author:  zenghao
	 * @param:   @param period
	 * @return:  void 
	 * @Date :   2016-11-22 18:36:27   
	 */
	public void setPeriod(Integer period) {
		this.period = period;
	}
	
	
	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	/**
	 * <p>认购价格</p>
	 * @author:  zenghao
	 * @return:  BigDecimal 
	 * @Date :   2016-11-22 18:36:27    
	 */
	public BigDecimal getPrice() {
		return price;
	}
	
	/**
	 * <p>认购价格</p>
	 * @author:  zenghao
	 * @param:   @param price
	 * @return:  void 
	 * @Date :   2016-11-22 18:36:27   
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	
	/**
	 * <p>认购数量</p>
	 * @author:  zenghao
	 * @return:  BigDecimal 
	 * @Date :   2016-11-22 18:36:27    
	 */
	public BigDecimal getAmount() {
		return amount;
	}
	
	/**
	 * <p>认购数量</p>
	 * @author:  zenghao
	 * @param:   @param amount
	 * @return:  void 
	 * @Date :   2016-11-22 18:36:27   
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	
	/**
	 * <p>回购数量</p>
	 * @author:  zenghao
	 * @return:  BigDecimal 
	 * @Date :   2016-11-22 18:36:27    
	 */
	public BigDecimal getBackAmount() {
		return backAmount;
	}
	
	/**
	 * <p>回购数量</p>
	 * @author:  zenghao
	 * @param:   @param backAmount
	 * @return:  void 
	 * @Date :   2016-11-22 18:36:27   
	 */
	public void setBackAmount(BigDecimal backAmount) {
		this.backAmount = backAmount;
	}
	
	
	/**
	 * <p>认购单号</p>
	 * @author:  zenghao
	 * @return:  String 
	 * @Date :   2016-11-22 18:36:27    
	 */
	public String getTransactionNum() {
		return transactionNum;
	}
	
	/**
	 * <p>认购单号</p>
	 * @author:  zenghao
	 * @param:   @param transactionNum
	 * @return:  void 
	 * @Date :   2016-11-22 18:36:27   
	 */
	public void setTransactionNum(String transactionNum) {
		this.transactionNum = transactionNum;
	}
	
	
	/**
	 * <p>状态（0申购成功，1部分回购，2全部回购）</p>
	 * @author:  zenghao
	 * @return:  Integer 
	 * @Date :   2016-11-22 18:36:27    
	 */
	public Integer getState() {
		return state;
	}
	
	/**
	 * <p>状态（0申购成功，1部分回购，2全部回购）</p>
	 * @author:  zenghao
	 * @param:   @param state
	 * @return:  void 
	 * @Date :   2016-11-22 18:36:27   
	 */
	public void setState(Integer state) {
		this.state = state;
	}

	public BigDecimal getRepurchaseFee() {
		return repurchaseFee;
	}

	public void setRepurchaseFee(BigDecimal repurchaseFee) {
		this.repurchaseFee = repurchaseFee;
	}

	public BigDecimal getCurrenNum() {
		return currenNum;
	}

	public void setCurrenNum(BigDecimal currenNum) {
		this.currenNum = currenNum;
	}

	public BigDecimal getMaxNum() {
		return maxNum;
	}

	public void setMaxNum(BigDecimal maxNum) {
		this.maxNum = maxNum;
	}

	public BigDecimal getApplyBackNum() {
		return applyBackNum;
	}

	public void setApplyBackNum(BigDecimal applyBackNum) {
		this.applyBackNum = applyBackNum;
	}

	public BigDecimal getCirculation() {
		return circulation;
	}

	public void setCirculation(BigDecimal circulation) {
		this.circulation = circulation;
	}

	public BigDecimal getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(BigDecimal totalMoney) {
		this.totalMoney = totalMoney;
	}

	public Date getBackEndTime() {
		return backEndTime;
	}

	public void setBackEndTime(Date backEndTime) {
		this.backEndTime = backEndTime;
	}

	
}
