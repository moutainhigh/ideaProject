/**
 * Copyright:   
 * @author:      tianpengyu
 * @version:     V1.0 
 * @Date:        2018-06-21 14:45:04 
 */
package hry.admin.exchange.model;


import hry.bean.BaseModel;


import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> ExDmPing </p>
 * @author:         tianpengyu
 * @Date :          2018-06-21 14:45:04  
 */
@Table(name="ex_dm_ping")
public class ExDmPing extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "pingNum")
	private String pingNum;  //
	
	@Column(name= "customerId")
	private Long customerId;  //
	
	@Column(name= "status")
	private Integer status;  //
	
	@Column(name= "saasId")
	private String saasId;  //
	
	@Column(name= "currencyType")
	private String currencyType;  //
	
	@Column(name= "website")
	private String website;  //
	
	@Column(name= "userCode")
	private String userCode;  //
	
	@Column(name= "userName")
	private String userName;  //
	
	@Column(name= "trueName")
	private String trueName;  //
	
	
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  Long 
	 * @Date :   2018-06-21 14:45:04    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-06-21 14:45:04   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-06-21 14:45:04    
	 */
	public String getPingNum() {
		return pingNum;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param pingNum
	 * @return:  void 
	 * @Date :   2018-06-21 14:45:04   
	 */
	public void setPingNum(String pingNum) {
		this.pingNum = pingNum;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  Long 
	 * @Date :   2018-06-21 14:45:04    
	 */
	public Long getCustomerId() {
		return customerId;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param customerId
	 * @return:  void 
	 * @Date :   2018-06-21 14:45:04   
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  Integer 
	 * @Date :   2018-06-21 14:45:04    
	 */
	public Integer getStatus() {
		return status;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param status
	 * @return:  void 
	 * @Date :   2018-06-21 14:45:04   
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-06-21 14:45:04    
	 */
	public String getSaasId() {
		return saasId;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param saasId
	 * @return:  void 
	 * @Date :   2018-06-21 14:45:04   
	 */
	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-06-21 14:45:04    
	 */
	public String getCurrencyType() {
		return currencyType;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param currencyType
	 * @return:  void 
	 * @Date :   2018-06-21 14:45:04   
	 */
	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-06-21 14:45:04    
	 */
	public String getWebsite() {
		return website;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param website
	 * @return:  void 
	 * @Date :   2018-06-21 14:45:04   
	 */
	public void setWebsite(String website) {
		this.website = website;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-06-21 14:45:04    
	 */
	public String getUserCode() {
		return userCode;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param userCode
	 * @return:  void 
	 * @Date :   2018-06-21 14:45:04   
	 */
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-06-21 14:45:04    
	 */
	public String getUserName() {
		return userName;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param userName
	 * @return:  void 
	 * @Date :   2018-06-21 14:45:04   
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-06-21 14:45:04    
	 */
	public String getTrueName() {
		return trueName;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param trueName
	 * @return:  void 
	 * @Date :   2018-06-21 14:45:04   
	 */
	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}
	
	

}
