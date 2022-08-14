/**
 * Copyright:   
 * @author:      yaozhuo
 * @version:     V1.0 
 * @Date:        2019-06-11 14:53:18 
 */
package hry.klg.prizedraw.model;


import hry.bean.BaseModel;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * <p> KlgPrizedrawCustomer </p>
 * @author:         yaozhuo
 * @Date :          2019-06-11 14:53:18  
 */
@Table(name="klg_prizedraw_customer")
public class KlgPrizedrawCustomer extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "customerId")
	private Long customerId;  //用户id
	
	@Column(name= "issueNumber")
	private Integer issueNumber;  //期号
	
	@Column(name= "prizedrawNumber")
	private String prizedrawNumber;  //抽奖号码
	
	@Column(name= "startDate")
	private Date startDate;  //开奖时间
	
	@Column(name= "saasId")
	private String saasId;  //
	
	@Column(name= "status")
	private Integer status;  //状态(1.未开奖 2.已开奖)
	
	@Transient
	private String trueName;  //姓
	@Transient
	private String surName;  //名
	@Transient
	private String mobilePhone;  //手机号
	@Transient
	private String email;  //邮箱
	
	
	
	public String getTrueName() {
		return trueName;
	}

	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}

	public String getSurName() {
		return surName;
	}

	public void setSurName(String surName) {
		this.surName = surName;
	}

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
	 * <p></p>
	 * @author:  yaozhuo
	 * @return:  Long 
	 * @Date :   2019-06-11 14:53:18    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  yaozhuo
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-06-11 14:53:18   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>用户id</p>
	 * @author:  yaozhuo
	 * @return:  Long 
	 * @Date :   2019-06-11 14:53:18    
	 */
	public Long getCustomerId() {
		return customerId;
	}
	
	/**
	 * <p>用户id</p>
	 * @author:  yaozhuo
	 * @param:   @param customerId
	 * @return:  void 
	 * @Date :   2019-06-11 14:53:18   
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	
	/**
	 * <p>期号</p>
	 * @author:  yaozhuo
	 * @return:  Integer 
	 * @Date :   2019-06-11 14:53:18    
	 */
	public Integer getIssueNumber() {
		return issueNumber;
	}
	
	/**
	 * <p>期号</p>
	 * @author:  yaozhuo
	 * @param:   @param issueNumber
	 * @return:  void 
	 * @Date :   2019-06-11 14:53:18   
	 */
	public void setIssueNumber(Integer issueNumber) {
		this.issueNumber = issueNumber;
	}
	
	
	/**
	 * <p>抽奖号码</p>
	 * @author:  yaozhuo
	 * @return:  String 
	 * @Date :   2019-06-11 14:53:18    
	 */
	public String getPrizedrawNumber() {
		return prizedrawNumber;
	}
	
	/**
	 * <p>抽奖号码</p>
	 * @author:  yaozhuo
	 * @param:   @param prizedrawNumber
	 * @return:  void 
	 * @Date :   2019-06-11 14:53:18   
	 */
	public void setPrizedrawNumber(String prizedrawNumber) {
		this.prizedrawNumber = prizedrawNumber;
	}
	
	
	/**
	 * <p>开奖时间</p>
	 * @author:  yaozhuo
	 * @return:  Date 
	 * @Date :   2019-06-11 14:53:18    
	 */
	public Date getStartDate() {
		return startDate;
	}
	
	/**
	 * <p>开奖时间</p>
	 * @author:  yaozhuo
	 * @param:   @param startDate
	 * @return:  void 
	 * @Date :   2019-06-11 14:53:18   
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	
	/**
	 * <p></p>
	 * @author:  yaozhuo
	 * @return:  String 
	 * @Date :   2019-06-11 14:53:18    
	 */
	public String getSaasId() {
		return saasId;
	}
	
	/**
	 * <p></p>
	 * @author:  yaozhuo
	 * @param:   @param saasId
	 * @return:  void 
	 * @Date :   2019-06-11 14:53:18   
	 */
	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}
	
	
	/**
	 * <p>状态(1.未开奖 2.已开奖)</p>
	 * @author:  yaozhuo
	 * @return:  Integer 
	 * @Date :   2019-06-11 14:53:18    
	 */
	public Integer getStatus() {
		return status;
	}
	
	/**
	 * <p>状态(1.未开奖 2.已开奖)</p>
	 * @author:  yaozhuo
	 * @param:   @param status
	 * @return:  void 
	 * @Date :   2019-06-11 14:53:18   
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	

}
