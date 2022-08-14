/**
 * Copyright:   
 * @author:      tianpengyu
 * @version:     V1.0 
 * @Date:        2018-07-02 11:44:16 
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
 * <p> AppCoinRewardRecord </p>
 * @author:         tianpengyu
 * @Date :          2018-07-02 11:44:16  
 */
@Table(name="app_coinreward_record")
public class AppCoinRewardRecord extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "customerId")
	private Long customerId;  //
	
	@Column(name= "customerName")
	private String customerName;  //
	
	@Column(name= "customerMobil")
	private String customerMobil;  //
	
	@Column(name= "recordType")
	private Integer recordType;  //
	
	@Column(name= "recordNum")
	private BigDecimal recordNum;  //
	
	@Column(name= "coverCustomerId")
	private Long coverCustomerId;  //
	
	@Column(name= "coverCustomerName")
	private String coverCustomerName;  //
	
	@Column(name= "coverCustomerMobile")
	private String coverCustomerMobile;  //
	
	@Column(name= "status")
	private Integer status;  //
	
	@Column(name= "failMsg")
	private String failMsg;  //
	
	@Column(name= "exOrderInfoId")
	private Long exOrderInfoId;  //
	
	@Column(name= "remark")
	private String remark;  //
	
	@Column(name= "auditor")
	private String auditor;  //
	
	@Column(name= "operationTime")
	private Date operationTime;  //
	
	@Column(name= "saasId")
	private String saasId;  //
	
	
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  Long 
	 * @Date :   2018-07-02 11:44:16    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-07-02 11:44:16   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  Long 
	 * @Date :   2018-07-02 11:44:16    
	 */
	public Long getCustomerId() {
		return customerId;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param customerId
	 * @return:  void 
	 * @Date :   2018-07-02 11:44:16   
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-07-02 11:44:16    
	 */
	public String getCustomerName() {
		return customerName;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param customerName
	 * @return:  void 
	 * @Date :   2018-07-02 11:44:16   
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-07-02 11:44:16    
	 */
	public String getCustomerMobil() {
		return customerMobil;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param customerMobil
	 * @return:  void 
	 * @Date :   2018-07-02 11:44:16   
	 */
	public void setCustomerMobil(String customerMobil) {
		this.customerMobil = customerMobil;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  Integer 
	 * @Date :   2018-07-02 11:44:16    
	 */
	public Integer getRecordType() {
		return recordType;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param recordType
	 * @return:  void 
	 * @Date :   2018-07-02 11:44:16   
	 */
	public void setRecordType(Integer recordType) {
		this.recordType = recordType;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  BigDecimal 
	 * @Date :   2018-07-02 11:44:16    
	 */
	public BigDecimal getRecordNum() {
		return recordNum;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param recordNum
	 * @return:  void 
	 * @Date :   2018-07-02 11:44:16   
	 */
	public void setRecordNum(BigDecimal recordNum) {
		this.recordNum = recordNum;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  Long 
	 * @Date :   2018-07-02 11:44:16    
	 */
	public Long getCoverCustomerId() {
		return coverCustomerId;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param coverCustomerId
	 * @return:  void 
	 * @Date :   2018-07-02 11:44:16   
	 */
	public void setCoverCustomerId(Long coverCustomerId) {
		this.coverCustomerId = coverCustomerId;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-07-02 11:44:16    
	 */
	public String getCoverCustomerName() {
		return coverCustomerName;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param coverCustomerName
	 * @return:  void 
	 * @Date :   2018-07-02 11:44:16   
	 */
	public void setCoverCustomerName(String coverCustomerName) {
		this.coverCustomerName = coverCustomerName;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-07-02 11:44:16    
	 */
	public String getCoverCustomerMobile() {
		return coverCustomerMobile;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param coverCustomerMobile
	 * @return:  void 
	 * @Date :   2018-07-02 11:44:16   
	 */
	public void setCoverCustomerMobile(String coverCustomerMobile) {
		this.coverCustomerMobile = coverCustomerMobile;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  Integer 
	 * @Date :   2018-07-02 11:44:16    
	 */
	public Integer getStatus() {
		return status;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param status
	 * @return:  void 
	 * @Date :   2018-07-02 11:44:16   
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-07-02 11:44:16    
	 */
	public String getFailMsg() {
		return failMsg;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param failMsg
	 * @return:  void 
	 * @Date :   2018-07-02 11:44:16   
	 */
	public void setFailMsg(String failMsg) {
		this.failMsg = failMsg;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  Long 
	 * @Date :   2018-07-02 11:44:16    
	 */
	public Long getExOrderInfoId() {
		return exOrderInfoId;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param exOrderInfoId
	 * @return:  void 
	 * @Date :   2018-07-02 11:44:16   
	 */
	public void setExOrderInfoId(Long exOrderInfoId) {
		this.exOrderInfoId = exOrderInfoId;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-07-02 11:44:16    
	 */
	public String getRemark() {
		return remark;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param remark
	 * @return:  void 
	 * @Date :   2018-07-02 11:44:16   
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-07-02 11:44:16    
	 */
	public String getAuditor() {
		return auditor;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param auditor
	 * @return:  void 
	 * @Date :   2018-07-02 11:44:16   
	 */
	public void setAuditor(String auditor) {
		this.auditor = auditor;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  Date 
	 * @Date :   2018-07-02 11:44:16    
	 */
	public Date getOperationTime() {
		return operationTime;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param operationTime
	 * @return:  void 
	 * @Date :   2018-07-02 11:44:16   
	 */
	public void setOperationTime(Date operationTime) {
		this.operationTime = operationTime;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-07-02 11:44:16    
	 */
	public String getSaasId() {
		return saasId;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param saasId
	 * @return:  void 
	 * @Date :   2018-07-02 11:44:16   
	 */
	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}
	
	

}
