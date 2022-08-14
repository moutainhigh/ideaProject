/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      denghf
 * @version:     V1.0 
 * @Date:        2018-08-10 15:10:34 
 */
package hry.otc.releaseAdvertisement.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import hry.core.mvc.model.BaseModel;

/**
 * <p> OtcAppLog </p>
 * @author:         denghf
 * @Date :          2018-08-10 15:10:34  
 */
@Table(name="otc_app_log")
public class OtcAppLog extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //id
	
	@Column(name= "type")
	private String type;  //类型
	
	@Column(name= "userId")
	private Long userId;  //用户ID
	
	@Column(name= "userName")
	private String userName;  //用户名
	
	@Column(name= "loginTime")
	private Date loginTime;  //登录时间
	
	@Column(name= "logoutTime")
	private Date logoutTime;  //登出时间
	
	@Column(name= "tradeTime")
	private Date tradeTime;  //第一次交易时间
	
	@Column(name= "ip")
	private String ip;  //ip
	
	@Column(name= "status")
	private String status;  //status
	
	
	
	/**
	 * <p>id</p>
	 * @author:  denghf
	 * @return:  Long 
	 * @Date :   2018-08-10 15:10:34    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>id</p>
	 * @author:  denghf
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-08-10 15:10:34   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>类型</p>
	 * @author:  denghf
	 * @return:  String 
	 * @Date :   2018-08-10 15:10:34    
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * <p>类型</p>
	 * @author:  denghf
	 * @param:   @param type
	 * @return:  void 
	 * @Date :   2018-08-10 15:10:34   
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	
	/**
	 * <p>用户ID</p>
	 * @author:  denghf
	 * @return:  Long 
	 * @Date :   2018-08-10 15:10:34    
	 */
	public Long getUserId() {
		return userId;
	}
	
	/**
	 * <p>用户ID</p>
	 * @author:  denghf
	 * @param:   @param userId
	 * @return:  void 
	 * @Date :   2018-08-10 15:10:34   
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	
	/**
	 * <p>用户名</p>
	 * @author:  denghf
	 * @return:  String 
	 * @Date :   2018-08-10 15:10:34    
	 */
	public String getUserName() {
		return userName;
	}
	
	/**
	 * <p>用户名</p>
	 * @author:  denghf
	 * @param:   @param userName
	 * @return:  void 
	 * @Date :   2018-08-10 15:10:34   
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
	/**
	 * <p>登录时间</p>
	 * @author:  denghf
	 * @return:  Date 
	 * @Date :   2018-08-10 15:10:34    
	 */
	public Date getLoginTime() {
		return loginTime;
	}
	
	/**
	 * <p>登录时间</p>
	 * @author:  denghf
	 * @param:   @param loginTime
	 * @return:  void 
	 * @Date :   2018-08-10 15:10:34   
	 */
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	
	
	/**
	 * <p>登出时间</p>
	 * @author:  denghf
	 * @return:  Date 
	 * @Date :   2018-08-10 15:10:34    
	 */
	public Date getLogoutTime() {
		return logoutTime;
	}
	
	/**
	 * <p>登出时间</p>
	 * @author:  denghf
	 * @param:   @param logoutTime
	 * @return:  void 
	 * @Date :   2018-08-10 15:10:34   
	 */
	public void setLogoutTime(Date logoutTime) {
		this.logoutTime = logoutTime;
	}
	
	
	/**
	 * <p>第一次交易时间</p>
	 * @author:  denghf
	 * @return:  Date 
	 * @Date :   2018-08-10 15:10:34    
	 */
	public Date getTradeTime() {
		return tradeTime;
	}
	
	/**
	 * <p>第一次交易时间</p>
	 * @author:  denghf
	 * @param:   @param tradeTime
	 * @return:  void 
	 * @Date :   2018-08-10 15:10:34   
	 */
	public void setTradeTime(Date tradeTime) {
		this.tradeTime = tradeTime;
	}
	
	
	/**
	 * <p>ip</p>
	 * @author:  denghf
	 * @return:  String 
	 * @Date :   2018-08-10 15:10:34    
	 */
	public String getIp() {
		return ip;
	}
	
	/**
	 * <p>ip</p>
	 * @author:  denghf
	 * @param:   @param ip
	 * @return:  void 
	 * @Date :   2018-08-10 15:10:34   
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	
	/**
	 * <p>status</p>
	 * @author:  denghf
	 * @return:  String 
	 * @Date :   2018-08-10 15:10:34    
	 */
	public String getStatus() {
		return status;
	}
	
	/**
	 * <p>status</p>
	 * @author:  denghf
	 * @param:   @param status
	 * @return:  void 
	 * @Date :   2018-08-10 15:10:34   
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
}
