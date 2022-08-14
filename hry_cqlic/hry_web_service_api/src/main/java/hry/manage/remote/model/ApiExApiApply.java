/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Zhang Xiaofang
 * @version:      V1.0 
 * @Date:        2016年5月12日 上午9:59:57
 */
package hry.manage.remote.model;

import hry.bean.BaseModel;

/**
 * <p> TODO</p>
 * @author:         Zhang Xiaofang 
 * @Date :          2016年5月12日 上午9:59:57 
 */

public class ApiExApiApply extends BaseModel {
	
    private Long id;
	
	
    private String ipAddress;
	
	//API访问密钥
    private String accessKey;
	
	
	//密钥的状态：0使用中  1已过期
    private int state;
	
	
	//密钥的状态：0使用中  1已过期
    private Long customerId;
	
     private int  expiryDate;//有效期
	
	
     private String  userName;//用户名

     private String description;


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
 * @return:     int
 */
public int getExpiryDate() {
	return expiryDate;
}


/** 
 * <p> TODO</p>
 * @return: int
 */
public void setExpiryDate(int expiryDate) {
	this.expiryDate = expiryDate;
}


	/**
	 * <p> TODO</p>
	 * @return:     Long
	 */
	public Long getId() {
		return id;
	}


	/**
	 * <p> TODO</p>
	 * @return:     Long
	 */
	public Long getCustomerId() {
		return customerId;
	}


	/** 
	 * <p> TODO</p>
	 * @return: Long
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
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
	 * @return:     String
	 */
	public String getIpAddress() {
		return ipAddress;
	}


	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}


	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getAccessKey() {
		return accessKey;
	}


	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}


	/**
	 * <p> TODO</p>
	 * @return:     int
	 */
	public int getState() {
		return state;
	}


	/** 
	 * <p> TODO</p>
	 * @return: int
	 */
	public void setState(int state) {
		this.state = state;
	}


	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getDescription() {
		return description;
	}


	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setDescription(String description) {
		this.description = description;
	}


  
}
