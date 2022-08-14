/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Gaomm
 * @version:     V1.0 
 * @Date:        2017-11-29 18:36:30 
 */
package hry.exchange.lend.model;

import static javax.persistence.GenerationType.IDENTITY;
import hry.core.mvc.model.BaseModel;

import java.util.Date;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> ExDmLendTimes </p>
 * @author:         Gaomm
 * @Date :          2017-11-29 18:36:30  
 */
@Table(name="ex_dm_lendTimes")
public class ExDmLendTimes extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //id
	
	@Column(name= "customerId")
	private Long customerId;  //用户id
	
	@Column(name= "userCode")
	private String userCode;  //userCode
	
	@Column(name= "userName")
	private String userName;  //userName
	
	@Column(name= "trueName")
	private String trueName;  //trueName
	
	@Column(name= "lendTimes")
	private BigDecimal lendTimes;  //申请的杠杆倍数
	
	@Column(name= "status")
	private Integer status;  //申请状态1申请中，2审批完
	
	@Column(name= "lendTimesStatus")
	private Integer lendTimesStatus;  //申请状态1申请成功，2审批驳回
	
	@Column(name= "applyTime")
	private Date applyTime;  //申请的时间
	
	@Column(name= "currencyType")
	private String currencyType;  //币的类型
	
	@Column(name= "website")
	private String website;  //website

	/**
	 * 驳回理由
	 */
	@Column(name= "description")
	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	/**
	 * <p>id</p>
	 * @author:  Gaomm
	 * @return:  Long 
	 * @Date :   2017-11-29 18:36:30    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>id</p>
	 * @author:  Gaomm
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2017-11-29 18:36:30   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>用户id</p>
	 * @author:  Gaomm
	 * @return:  Long 
	 * @Date :   2017-11-29 18:36:30    
	 */
	public Long getCustomerId() {
		return customerId;
	}
	
	/**
	 * <p>用户id</p>
	 * @author:  Gaomm
	 * @param:   @param customerId
	 * @return:  void 
	 * @Date :   2017-11-29 18:36:30   
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	
	/**
	 * <p>userCode</p>
	 * @author:  Gaomm
	 * @return:  String 
	 * @Date :   2017-11-29 18:36:30    
	 */
	public String getUserCode() {
		return userCode;
	}
	
	/**
	 * <p>userCode</p>
	 * @author:  Gaomm
	 * @param:   @param userCode
	 * @return:  void 
	 * @Date :   2017-11-29 18:36:30   
	 */
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	
	
	/**
	 * <p>userName</p>
	 * @author:  Gaomm
	 * @return:  String 
	 * @Date :   2017-11-29 18:36:30    
	 */
	public String getUserName() {
		return userName;
	}
	
	/**
	 * <p>userName</p>
	 * @author:  Gaomm
	 * @param:   @param userName
	 * @return:  void 
	 * @Date :   2017-11-29 18:36:30   
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
	/**
	 * <p>trueName</p>
	 * @author:  Gaomm
	 * @return:  String 
	 * @Date :   2017-11-29 18:36:30    
	 */
	public String getTrueName() {
		return trueName;
	}
	
	/**
	 * <p>trueName</p>
	 * @author:  Gaomm
	 * @param:   @param trueName
	 * @return:  void 
	 * @Date :   2017-11-29 18:36:30   
	 */
	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}
	
	
	/**
	 * <p>申请的杠杆倍数</p>
	 * @author:  Gaomm
	 * @return:  BigDecimal 
	 * @Date :   2017-11-29 18:36:30    
	 */
	public BigDecimal getLendTimes() {
		return lendTimes;
	}
	
	/**
	 * <p>申请的杠杆倍数</p>
	 * @author:  Gaomm
	 * @param:   @param lendTimes
	 * @return:  void 
	 * @Date :   2017-11-29 18:36:30   
	 */
	public void setLendTimes(BigDecimal lendTimes) {
		this.lendTimes = lendTimes;
	}
	
	
	/**
	 * <p>申请状态1申请中，2审批完</p>
	 * @author:  Gaomm
	 * @return:  Integer 
	 * @Date :   2017-11-29 18:36:30    
	 */
	public Integer getStatus() {
		return status;
	}
	
	/**
	 * <p>申请状态1申请中，2审批完</p>
	 * @author:  Gaomm
	 * @param:   @param status
	 * @return:  void 
	 * @Date :   2017-11-29 18:36:30   
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
	/**
	 * <p>申请状态1申请成功，2审批驳回</p>
	 * @author:  Gaomm
	 * @return:  Integer 
	 * @Date :   2017-11-29 18:36:30    
	 */
	public Integer getLendTimesStatus() {
		return lendTimesStatus;
	}
	
	/**
	 * <p>申请状态1申请成功，2审批驳回</p>
	 * @author:  Gaomm
	 * @param:   @param lendTimesStatus
	 * @return:  void 
	 * @Date :   2017-11-29 18:36:30   
	 */
	public void setLendTimesStatus(Integer lendTimesStatus) {
		this.lendTimesStatus = lendTimesStatus;
	}
	
	
	/**
	 * <p>申请的时间</p>
	 * @author:  Gaomm
	 * @return:  Date 
	 * @Date :   2017-11-29 18:36:30    
	 */
	public Date getApplyTime() {
		return applyTime;
	}
	
	/**
	 * <p>申请的时间</p>
	 * @author:  Gaomm
	 * @param:   @param applyTime
	 * @return:  void 
	 * @Date :   2017-11-29 18:36:30   
	 */
	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}
	
	
	/**
	 * <p>币的类型</p>
	 * @author:  Gaomm
	 * @return:  String 
	 * @Date :   2017-11-29 18:36:30    
	 */
	public String getCurrencyType() {
		return currencyType;
	}
	
	/**
	 * <p>币的类型</p>
	 * @author:  Gaomm
	 * @param:   @param currencyType
	 * @return:  void 
	 * @Date :   2017-11-29 18:36:30   
	 */
	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}
	
	
	/**
	 * <p>website</p>
	 * @author:  Gaomm
	 * @return:  String 
	 * @Date :   2017-11-29 18:36:30    
	 */
	public String getWebsite() {
		return website;
	}
	
	/**
	 * <p>website</p>
	 * @author:  Gaomm
	 * @param:   @param website
	 * @return:  void 
	 * @Date :   2017-11-29 18:36:30   
	 */
	public void setWebsite(String website) {
		this.website = website;
	}
	
	

}
