/**
 * Copyright:   
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-20 14:43:26 
 */
package hry.admin.web.model;


import hry.bean.BaseModel;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> AppLoginLog </p>
 * @author:         liushilei
 * @Date :          2018-06-20 14:43:26  
 */
@Table(name="app_login_log")
public class AppLoginLog extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "userId")
	private Long userId;  //
	
	@Column(name= "userName")
	private String userName;  //
	
	@Column(name= "loginTime")
	private Date loginTime;  //
	
	@Column(name= "logoutTime")
	private Date logoutTime;  //
	
	@Column(name= "ip")
	private String ip;  //
	
	@Column(name= "saasId")
	private String saasId;  //
	
	
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Long 
	 * @Date :   2018-06-20 14:43:26    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-06-20 14:43:26   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Long 
	 * @Date :   2018-06-20 14:43:26    
	 */
	public Long getUserId() {
		return userId;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param userId
	 * @return:  void 
	 * @Date :   2018-06-20 14:43:26   
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-20 14:43:26    
	 */
	public String getUserName() {
		return userName;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param userName
	 * @return:  void 
	 * @Date :   2018-06-20 14:43:26   
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Date 
	 * @Date :   2018-06-20 14:43:26    
	 */
	public Date getLoginTime() {
		return loginTime;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param loginTime
	 * @return:  void 
	 * @Date :   2018-06-20 14:43:26   
	 */
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Date 
	 * @Date :   2018-06-20 14:43:26    
	 */
	public Date getLogoutTime() {
		return logoutTime;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param logoutTime
	 * @return:  void 
	 * @Date :   2018-06-20 14:43:26   
	 */
	public void setLogoutTime(Date logoutTime) {
		this.logoutTime = logoutTime;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-20 14:43:26    
	 */
	public String getIp() {
		return ip;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param ip
	 * @return:  void 
	 * @Date :   2018-06-20 14:43:26   
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-20 14:43:26    
	 */
	public String getSaasId() {
		return saasId;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param saasId
	 * @return:  void 
	 * @Date :   2018-06-20 14:43:26   
	 */
	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}
	
	

}
