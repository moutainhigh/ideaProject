/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      zenghao
 * @version:     V1.0 
 * @Date:        2016-11-24 16:36:09 
 */
package hry.exchange.subscription.model;

import static javax.persistence.GenerationType.IDENTITY;
import hry.core.mvc.model.BaseModel;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> ExBuybackRecord </p>
 * @author:         zenghao
 * @Date :          2016-11-24 16:36:09  
 */
@Table(name="ex_buy_back_record")
public class ExBuybackRecord extends BaseModel {
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //id
	
	@Column(name= "recordId")
	private Long recordId;  //认购记录id
	
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
	
	@Column(name= "backPrice")
	private BigDecimal backPrice;  //认购价格
	
	@Column(name= "backAmount")
	private BigDecimal backAmount;  //回购数量
	
	@Column(name= "transactionNum")
	private String transactionNum;  //回购单号
	
	@Column(name= "state")
	private Integer state;  //状态（0未审核，1已通过，2已驳回，3用户撤销）
	
	@Column(name= "remarks")
	private String remarks;  //驳回备注
	
	@Column(name= "amount")
	private BigDecimal amount;  //认购数量
	
	@Column(name= "subTransactionNum")
	private String subTransactionNum;  //认购订单号
	
	@Column(name= "buyTotalAmount")
	private BigDecimal buyTotalAmount;  //回购总金额
	
	@Column(name= "repurchaseFee")
	private BigDecimal repurchaseFee;  //回购手续费
	
	@Column(name= "feeMoney")
	private BigDecimal feeMoney;  //回购手续费金额
	
	
	
	/**
	 * <p>id</p>
	 * @author:  zenghao
	 * @return:  Long 
	 * @Date :   2016-11-24 16:36:09    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>id</p>
	 * @author:  zenghao
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2016-11-24 16:36:09   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>认购记录id</p>
	 * @author:  zenghao
	 * @return:  Long 
	 * @Date :   2016-11-24 16:36:09    
	 */
	public Long getRecordId() {
		return recordId;
	}
	
	/**
	 * <p>认购记录id</p>
	 * @author:  zenghao
	 * @param:   @param recordId
	 * @return:  void 
	 * @Date :   2016-11-24 16:36:09   
	 */
	public void setRecordId(Long recordId) {
		this.recordId = recordId;
	}
	
	
	/**
	 * <p>客户id</p>
	 * @author:  zenghao
	 * @return:  Long 
	 * @Date :   2016-11-24 16:36:09    
	 */
	public Long getCustomerId() {
		return customerId;
	}
	
	/**
	 * <p>客户id</p>
	 * @author:  zenghao
	 * @param:   @param customerId
	 * @return:  void 
	 * @Date :   2016-11-24 16:36:09   
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	
	/**
	 * <p>账户名</p>
	 * @author:  zenghao
	 * @return:  String 
	 * @Date :   2016-11-24 16:36:09    
	 */
	public String getUserName() {
		return userName;
	}
	
	/**
	 * <p>账户名</p>
	 * @author:  zenghao
	 * @param:   @param userName
	 * @return:  void 
	 * @Date :   2016-11-24 16:36:09   
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
	/**
	 * <p>真实姓名</p>
	 * @author:  zenghao
	 * @return:  String 
	 * @Date :   2016-11-24 16:36:09    
	 */
	public String getTrueName() {
		return trueName;
	}
	
	/**
	 * <p>真实姓名</p>
	 * @author:  zenghao
	 * @param:   @param trueName
	 * @return:  void 
	 * @Date :   2016-11-24 16:36:09   
	 */
	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}
	
	
	/**
	 * <p>币种代码</p>
	 * @author:  zenghao
	 * @return:  String 
	 * @Date :   2016-11-24 16:36:09    
	 */
	public String getCoinCode() {
		return coinCode;
	}
	
	/**
	 * <p>币种代码</p>
	 * @author:  zenghao
	 * @param:   @param coinCode
	 * @return:  void 
	 * @Date :   2016-11-24 16:36:09   
	 */
	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}
	
	
	/**
	 * <p>币种名称</p>
	 * @author:  zenghao
	 * @return:  String 
	 * @Date :   2016-11-24 16:36:09    
	 */
	public String getCoinName() {
		return coinName;
	}
	
	/**
	 * <p>币种名称</p>
	 * @author:  zenghao
	 * @param:   @param coinName
	 * @return:  void 
	 * @Date :   2016-11-24 16:36:09   
	 */
	public void setCoinName(String coinName) {
		this.coinName = coinName;
	}
	
	
	/**
	 * <p>认购期数</p>
	 * @author:  zenghao
	 * @return:  Integer 
	 * @Date :   2016-11-24 16:36:09    
	 */
	public Integer getPeriod() {
		return period;
	}
	
	/**
	 * <p>认购期数</p>
	 * @author:  zenghao
	 * @param:   @param period
	 * @return:  void 
	 * @Date :   2016-11-24 16:36:09   
	 */
	public void setPeriod(Integer period) {
		this.period = period;
	}
	
	
	/**
	 * <p>认购价格</p>
	 * @author:  zenghao
	 * @return:  BigDecimal 
	 * @Date :   2016-11-24 16:36:09    
	 */
	public BigDecimal getBackPrice() {
		return backPrice;
	}
	
	/**
	 * <p>认购价格</p>
	 * @author:  zenghao
	 * @param:   @param backPrice
	 * @return:  void 
	 * @Date :   2016-11-24 16:36:09   
	 */
	public void setBackPrice(BigDecimal backPrice) {
		this.backPrice = backPrice;
	}
	
	
	/**
	 * <p>回购数量</p>
	 * @author:  zenghao
	 * @return:  BigDecimal 
	 * @Date :   2016-11-24 16:36:09    
	 */
	public BigDecimal getBackAmount() {
		return backAmount;
	}
	
	/**
	 * <p>回购数量</p>
	 * @author:  zenghao
	 * @param:   @param backAmount
	 * @return:  void 
	 * @Date :   2016-11-24 16:36:09   
	 */
	public void setBackAmount(BigDecimal backAmount) {
		this.backAmount = backAmount;
	}
	
	
	/**
	 * <p>回购单号</p>
	 * @author:  zenghao
	 * @return:  String 
	 * @Date :   2016-11-24 16:36:09    
	 */
	public String getTransactionNum() {
		return transactionNum;
	}
	
	/**
	 * <p>回购单号</p>
	 * @author:  zenghao
	 * @param:   @param transactionNum
	 * @return:  void 
	 * @Date :   2016-11-24 16:36:09   
	 */
	public void setTransactionNum(String transactionNum) {
		this.transactionNum = transactionNum;
	}
	
	
	/**
	 * <p>状态（0未审核，1已通过，2已驳回，3用户撤销）</p>
	 * @author:  zenghao
	 * @return:  Integer 
	 * @Date :   2016-11-24 16:36:09    
	 */
	public Integer getState() {
		return state;
	}
	
	/**
	 * <p>状态（0未审核，1已通过，2已驳回，3用户撤销）</p>
	 * @author:  zenghao
	 * @param:   @param state
	 * @return:  void 
	 * @Date :   2016-11-24 16:36:09   
	 */
	public void setState(Integer state) {
		this.state = state;
	}
	
	
	/**
	 * <p>驳回备注</p>
	 * @author:  zenghao
	 * @return:  String 
	 * @Date :   2016-11-24 16:36:09    
	 */
	public String getRemarks() {
		return remarks;
	}
	
	/**
	 * <p>驳回备注</p>
	 * @author:  zenghao
	 * @param:   @param remarks
	 * @return:  void 
	 * @Date :   2016-11-24 16:36:09   
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getSubTransactionNum() {
		return subTransactionNum;
	}

	public void setSubTransactionNum(String subTransactionNum) {
		this.subTransactionNum = subTransactionNum;
	}

	public BigDecimal getBuyTotalAmount() {
		return buyTotalAmount;
	}

	public void setBuyTotalAmount(BigDecimal buyTotalAmount) {
		this.buyTotalAmount = buyTotalAmount;
	}

	public BigDecimal getRepurchaseFee() {
		return repurchaseFee;
	}

	public void setRepurchaseFee(BigDecimal repurchaseFee) {
		this.repurchaseFee = repurchaseFee;
	}

	public Long getPlanId() {
		return planId;
	}

	public void setPlanId(Long planId) {
		this.planId = planId;
	}

	public BigDecimal getFeeMoney() {
		return feeMoney;
	}

	public void setFeeMoney(BigDecimal feeMoney) {
		this.feeMoney = feeMoney;
	}
	
}
