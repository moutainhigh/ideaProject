/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Gaomm
 * @version:     V1.0 
 * @Date:        2017-11-29 18:36:30 
 */
package hry.manage.remote.model;

import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p> ExDmLendTimes </p>
 * @author:         Gaomm
 * @Date :          2017-11-29 18:36:30  
 */
public class LendTimes implements Serializable{
	
	
	private Long id;  //id
	
	private Long customerId;  //用户id
	
	private String userCode;  //userCode
	
	private String userName;  //userName
	
	private String trueName;  //trueName
	
	private BigDecimal lendTimes;  //申请的杠杆倍数
	
	private Integer status;  //申请状态1申请中，2审批完
	
	private Integer lendTimesStatus;  //申请状态1申请成功，2审批驳回
	
	private Date applyTime;  //申请的时间
	
	private String currencyType;  //币的类型
	
	private String website;  //website
	
	
	
	
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
