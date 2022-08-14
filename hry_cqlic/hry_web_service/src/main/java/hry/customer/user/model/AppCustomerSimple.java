/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Liu Shilei
 * @version:      V1.0 
 * @Date:        2016年5月31日 下午6:20:27
 */
package hry.customer.user.model;

import java.io.Serializable;

/**
 * <p> TODO</p>
 * @author:         Liu Shilei 
 * @Date :          2016年5月31日 下午6:20:27 
 */
public class AppCustomerSimple implements Serializable {
	
	private Long id ; 
	private String userName;
	private String trueName;
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
	public String getTrueName() {
		return trueName;
	}
	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}
	
	
	
	
}
