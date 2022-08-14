/**
 * Copyright:   
 * @author:      tianpengyu
 * @version:     V1.0 
 * @Date:        2018-07-20 09:55:15 
 */
package hry.admin.exchange.model;


import hry.bean.BaseModel;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> AppAccountRecord </p>
 * @author:         tianpengyu
 * @Date :          2018-07-20 09:55:15  
 */
@Table(name="app_account_record")
public class AppAccountRecord extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "appAccountId")
	private Long appAccountId;  //
	
	@Column(name= "appAccountNum")
	private String appAccountNum;  //
	
	@Column(name= "customerId")
	private Long customerId;  //
	
	@Column(name= "customerName")
	private String customerName;  //
	
	@Column(name= "recordType")
	private Integer recordType;  //
	
	@Column(name= "source")
	private Integer source;  //
	
	@Column(name= "transactionMoney")
	private BigDecimal transactionMoney;  //
	
	@Column(name= "transactionNum")
	private String transactionNum;  //
	
	@Column(name= "status")
	private Integer status;  //
	
	@Column(name= "remark")
	private String remark;  //
	
	@Column(name= "currencyName")
	private String currencyName;  //
	
	@Column(name= "currencyType")
	private String currencyType;  //
	
	@Column(name= "auditor")
	private String auditor;  //
	
	@Column(name= "operationTime")
	private Date operationTime;  //
	
	@Column(name= "customerAccount")
	private String customerAccount;  //
	
	@Column(name= "saasId")
	private String saasId;  //
	
	@Column(name= "factorage")
	private String factorage;  //
	
	@Column(name= "website")
	private String website;  //
	
	@Column(name= "trueName")
	private String trueName;  //
	
	
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  Long 
	 * @Date :   2018-07-20 09:55:15    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-07-20 09:55:15   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  Long 
	 * @Date :   2018-07-20 09:55:15    
	 */
	public Long getAppAccountId() {
		return appAccountId;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param appAccountId
	 * @return:  void 
	 * @Date :   2018-07-20 09:55:15   
	 */
	public void setAppAccountId(Long appAccountId) {
		this.appAccountId = appAccountId;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-07-20 09:55:15    
	 */
	public String getAppAccountNum() {
		return appAccountNum;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param appAccountNum
	 * @return:  void 
	 * @Date :   2018-07-20 09:55:15   
	 */
	public void setAppAccountNum(String appAccountNum) {
		this.appAccountNum = appAccountNum;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  Long 
	 * @Date :   2018-07-20 09:55:15    
	 */
	public Long getCustomerId() {
		return customerId;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param customerId
	 * @return:  void 
	 * @Date :   2018-07-20 09:55:15   
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-07-20 09:55:15    
	 */
	public String getCustomerName() {
		return customerName;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param customerName
	 * @return:  void 
	 * @Date :   2018-07-20 09:55:15   
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  Integer 
	 * @Date :   2018-07-20 09:55:15    
	 */
	public Integer getRecordType() {
		return recordType;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param recordType
	 * @return:  void 
	 * @Date :   2018-07-20 09:55:15   
	 */
	public void setRecordType(Integer recordType) {
		this.recordType = recordType;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  Integer 
	 * @Date :   2018-07-20 09:55:15    
	 */
	public Integer getSource() {
		return source;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param source
	 * @return:  void 
	 * @Date :   2018-07-20 09:55:15   
	 */
	public void setSource(Integer source) {
		this.source = source;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  BigDecimal 
	 * @Date :   2018-07-20 09:55:15    
	 */
	public BigDecimal getTransactionMoney() {
		return transactionMoney;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param transactionMoney
	 * @return:  void 
	 * @Date :   2018-07-20 09:55:15   
	 */
	public void setTransactionMoney(BigDecimal transactionMoney) {
		this.transactionMoney = transactionMoney;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-07-20 09:55:15    
	 */
	public String getTransactionNum() {
		return transactionNum;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param transactionNum
	 * @return:  void 
	 * @Date :   2018-07-20 09:55:15   
	 */
	public void setTransactionNum(String transactionNum) {
		this.transactionNum = transactionNum;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  Integer 
	 * @Date :   2018-07-20 09:55:15    
	 */
	public Integer getStatus() {
		return status;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param status
	 * @return:  void 
	 * @Date :   2018-07-20 09:55:15   
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-07-20 09:55:15    
	 */
	public String getRemark() {
		return remark;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param remark
	 * @return:  void 
	 * @Date :   2018-07-20 09:55:15   
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-07-20 09:55:15    
	 */
	public String getCurrencyName() {
		return currencyName;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param currencyName
	 * @return:  void 
	 * @Date :   2018-07-20 09:55:15   
	 */
	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-07-20 09:55:15    
	 */
	public String getCurrencyType() {
		return currencyType;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param currencyType
	 * @return:  void 
	 * @Date :   2018-07-20 09:55:15   
	 */
	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-07-20 09:55:15    
	 */
	public String getAuditor() {
		return auditor;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param auditor
	 * @return:  void 
	 * @Date :   2018-07-20 09:55:15   
	 */
	public void setAuditor(String auditor) {
		this.auditor = auditor;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  Date 
	 * @Date :   2018-07-20 09:55:15    
	 */
	public Date getOperationTime() {
		return operationTime;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param operationTime
	 * @return:  void 
	 * @Date :   2018-07-20 09:55:15   
	 */
	public void setOperationTime(Date operationTime) {
		this.operationTime = operationTime;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-07-20 09:55:15    
	 */
	public String getCustomerAccount() {
		return customerAccount;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param customerAccount
	 * @return:  void 
	 * @Date :   2018-07-20 09:55:15   
	 */
	public void setCustomerAccount(String customerAccount) {
		this.customerAccount = customerAccount;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-07-20 09:55:15    
	 */
	public String getSaasId() {
		return saasId;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param saasId
	 * @return:  void 
	 * @Date :   2018-07-20 09:55:15   
	 */
	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-07-20 09:55:15    
	 */
	public String getFactorage() {
		return factorage;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param factorage
	 * @return:  void 
	 * @Date :   2018-07-20 09:55:15   
	 */
	public void setFactorage(String factorage) {
		this.factorage = factorage;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-07-20 09:55:15    
	 */
	public String getWebsite() {
		return website;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param website
	 * @return:  void 
	 * @Date :   2018-07-20 09:55:15   
	 */
	public void setWebsite(String website) {
		this.website = website;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-07-20 09:55:15    
	 */
	public String getTrueName() {
		return trueName;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param trueName
	 * @return:  void 
	 * @Date :   2018-07-20 09:55:15   
	 */
	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}
	
	

}
