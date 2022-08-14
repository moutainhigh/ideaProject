/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Zhang Xiaofang
 * @version:      V1.0 
 * @Date:        2016年5月12日 上午9:59:57
 */
package hry.admin.exchange.model;

import hry.bean.BaseModel;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * <p> TODO</p>
 * @author:         Zhang Xiaofang 
 * @Date :          2016年5月12日 上午9:59:57 
 */

@Table(name="ex_api_apply")
public class ExApiApply extends BaseModel {
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
    private Long id;
	
	
	@Column(name = "ipAddress")
    private String ipAddress;
	
	//API访问密钥
	@Column(name = "accessKey")
    private String accessKey;
	
	
	//密钥的状态：0使用中  1已过期
	@Column(name = "state")
    private int state;
	
	
	//密钥的状态：0使用中  1已过期
	@Column(name = "customerId")
    private Long customerId;
	
	@Transient //不与数据库映射字段
     private int  expiryDate;//有效期
	
	
     @Transient //不与数据库映射字段
     private String  userName;//用户名




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


	@Column(name = "description")
    private String description;
}
