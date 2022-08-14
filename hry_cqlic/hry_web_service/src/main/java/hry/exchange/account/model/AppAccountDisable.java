/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Gao mimi
 * @version:     V1.0 
 * @Date:        2016-12-12 19:39:38 
 */
package hry.exchange.account.model;

import static javax.persistence.GenerationType.IDENTITY;
import hry.core.mvc.model.BaseModel;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> AppAccountDisable </p>
 * @author:         Gao mimi
 * @Date :          2016-12-12 19:39:38  
 */
@Table(name="app_account_disable")
public class AppAccountDisable extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //id
	
	@Column(name= "transactionNum")
	private String transactionNum;  //transactionNum
	
	@Column(name= "customerId")
	private Long customerId;  //用户id
	
	@Column(name= "trueName")
	private String trueName;  //trueName
	
	@Column(name= "userName")
	private String userName;  //userName
	
	@Column(name= "accountId")
	private Long accountId;  //账户id
	
	@Column(name= "transactionType")
	private Integer transactionType;  //类型(1钱 ，2币)
	
	@Column(name= "transactionCount")
	private BigDecimal transactionCount;  //禁用量
	
	@Column(name= "status")
	private Integer status;  //1禁用，2，解禁
	
	@Column(name= "remark")
	private String remark;  //remark
	
	@Column(name= "userId")
	private Long userId;  //操作人id
	
	@Column(name= "coinCode")
	private String coinCode;  //币种（CNY，BTC）
	
	@Column(name= "currencyType")
	private String currencyType;  //currencyType
	
	@Column(name= "website")
	private String website;  //website
	
	
	
	
	/**
	 * <p>id</p>
	 * @author:  Gao mimi
	 * @return:  Long 
	 * @Date :   2016-12-12 19:39:38    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>id</p>
	 * @author:  Gao mimi
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2016-12-12 19:39:38   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>transactionNum</p>
	 * @author:  Gao mimi
	 * @return:  String 
	 * @Date :   2016-12-12 19:39:38    
	 */
	public String getTransactionNum() {
		return transactionNum;
	}
	
	/**
	 * <p>transactionNum</p>
	 * @author:  Gao mimi
	 * @param:   @param transactionNum
	 * @return:  void 
	 * @Date :   2016-12-12 19:39:38   
	 */
	public void setTransactionNum(String transactionNum) {
		this.transactionNum = transactionNum;
	}
	
	
	/**
	 * <p>用户id</p>
	 * @author:  Gao mimi
	 * @return:  Long 
	 * @Date :   2016-12-12 19:39:38    
	 */
	public Long getCustomerId() {
		return customerId;
	}
	
	/**
	 * <p>用户id</p>
	 * @author:  Gao mimi
	 * @param:   @param customerId
	 * @return:  void 
	 * @Date :   2016-12-12 19:39:38   
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	
	/**
	 * <p>trueName</p>
	 * @author:  Gao mimi
	 * @return:  String 
	 * @Date :   2016-12-12 19:39:38    
	 */
	public String getTrueName() {
		return trueName;
	}
	
	/**
	 * <p>trueName</p>
	 * @author:  Gao mimi
	 * @param:   @param trueName
	 * @return:  void 
	 * @Date :   2016-12-12 19:39:38   
	 */
	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}
	
	
	/**
	 * <p>userName</p>
	 * @author:  Gao mimi
	 * @return:  String 
	 * @Date :   2016-12-12 19:39:38    
	 */
	public String getUserName() {
		return userName;
	}
	
	/**
	 * <p>userName</p>
	 * @author:  Gao mimi
	 * @param:   @param userName
	 * @return:  void 
	 * @Date :   2016-12-12 19:39:38   
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
	/**
	 * <p>账户id</p>
	 * @author:  Gao mimi
	 * @return:  Long 
	 * @Date :   2016-12-12 19:39:38    
	 */
	public Long getAccountId() {
		return accountId;
	}
	
	/**
	 * <p>账户id</p>
	 * @author:  Gao mimi
	 * @param:   @param accountId
	 * @return:  void 
	 * @Date :   2016-12-12 19:39:38   
	 */
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	
	
	/**
	 * <p>类型(1钱 ，2币)</p>
	 * @author:  Gao mimi
	 * @return:  Integer 
	 * @Date :   2016-12-12 19:39:38    
	 */
	public Integer getTransactionType() {
		return transactionType;
	}
	
	/**
	 * <p>类型(1钱 ，2币)</p>
	 * @author:  Gao mimi
	 * @param:   @param transactionType
	 * @return:  void 
	 * @Date :   2016-12-12 19:39:38   
	 */
	public void setTransactionType(Integer transactionType) {
		this.transactionType = transactionType;
	}
	
	
	/**
	 * <p>禁用量</p>
	 * @author:  Gao mimi
	 * @return:  BigDecimal 
	 * @Date :   2016-12-12 19:39:38    
	 */
	public BigDecimal getTransactionCount() {
		return transactionCount;
	}
	
	/**
	 * <p>禁用量</p>
	 * @author:  Gao mimi
	 * @param:   @param transactionCount
	 * @return:  void 
	 * @Date :   2016-12-12 19:39:38   
	 */
	public void setTransactionCount(BigDecimal transactionCount) {
		this.transactionCount = transactionCount;
	}
	
	
	/**
	 * <p>1禁用，2，解禁</p>
	 * @author:  Gao mimi
	 * @return:  Integer 
	 * @Date :   2016-12-12 19:39:38    
	 */
	public Integer getStatus() {
		return status;
	}
	
	/**
	 * <p>1禁用，2，解禁</p>
	 * @author:  Gao mimi
	 * @param:   @param status
	 * @return:  void 
	 * @Date :   2016-12-12 19:39:38   
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
	/**
	 * <p>remark</p>
	 * @author:  Gao mimi
	 * @return:  String 
	 * @Date :   2016-12-12 19:39:38    
	 */
	public String getRemark() {
		return remark;
	}
	
	/**
	 * <p>remark</p>
	 * @author:  Gao mimi
	 * @param:   @param remark
	 * @return:  void 
	 * @Date :   2016-12-12 19:39:38   
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	/**
	 * <p>操作人id</p>
	 * @author:  Gao mimi
	 * @return:  Long 
	 * @Date :   2016-12-12 19:39:38    
	 */
	public Long getUserId() {
		return userId;
	}
	
	/**
	 * <p>操作人id</p>
	 * @author:  Gao mimi
	 * @param:   @param userId
	 * @return:  void 
	 * @Date :   2016-12-12 19:39:38   
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	
	/**
	 * <p>币种（CNY，BTC）</p>
	 * @author:  Gao mimi
	 * @return:  String 
	 * @Date :   2016-12-12 19:39:38    
	 */
	public String getCoinCode() {
		return coinCode;
	}
	
	/**
	 * <p>币种（CNY，BTC）</p>
	 * @author:  Gao mimi
	 * @param:   @param coinCode
	 * @return:  void 
	 * @Date :   2016-12-12 19:39:38   
	 */
	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}
	
	
	/**
	 * <p>currencyType</p>
	 * @author:  Gao mimi
	 * @return:  String 
	 * @Date :   2016-12-12 19:39:38    
	 */
	public String getCurrencyType() {
		return currencyType;
	}
	
	/**
	 * <p>currencyType</p>
	 * @author:  Gao mimi
	 * @param:   @param currencyType
	 * @return:  void 
	 * @Date :   2016-12-12 19:39:38   
	 */
	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}
	
	
	/**
	 * <p>website</p>
	 * @author:  Gao mimi
	 * @return:  String 
	 * @Date :   2016-12-12 19:39:38    
	 */
	public String getWebsite() {
		return website;
	}
	
	/**
	 * <p>website</p>
	 * @author:  Gao mimi
	 * @param:   @param website
	 * @return:  void 
	 * @Date :   2016-12-12 19:39:38   
	 */
	public void setWebsite(String website) {
		this.website = website;
	}
	
	

}
