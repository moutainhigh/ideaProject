/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Liu Shilei
 * @version:      V1.0 
 * @Date:        2016年5月23日 上午11:33:01
 */
package hry.core.mvc.model.log;

import hry.core.mvc.model.BaseModel;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> TODO</p>
 * @author:         Liu Shilei 
 * @Date :          2016年5月23日 上午11:33:01 
 */
@Table(name = "app_log_login")
public class AppLogLogin extends BaseModel {
	
	@Id
	@Column(name="id")
	private Long id ;
	
	/**
	 * 用户类别
	 * app_user   后台用户 
	 * app_customer  前台用户 
	 * 
	 */
	@Column(name="type")   
	private String type;   
	
	@Column(name="userId")   
	private Long userId;   //用户ID
	
	@Column(name="userName")  
	private String userName; //用户名
	
	@Column(name="loginTime")
	private String loginTime; //登录时间
	
	@Column(name="ip")
	private String ip; //登录IP
	
	
	@Column(name="状态")
	private String status;  //0失败  ，1 成功


	/**
	 * <p> TODO</p>
	 * @return:     Long
	 */
	public Long getId() {
		return id;
	}

	/** 
	 * <p> TODO</p>
	 * @return: Long
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * <p> TODO</p>
	 * @return:     Long
	 */
	public Long getUserId() {
		return userId;
	}

	/** 
	 * <p> TODO</p>
	 * @return: Long
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getUserName() {
		return userName;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getLoginTime() {
		return loginTime;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}

	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getIp() {
		return ip;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}


	

	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getStatus() {
		return status;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getType() {
		return type;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setType(String type) {
		this.type = type;
	}

		
	
}
