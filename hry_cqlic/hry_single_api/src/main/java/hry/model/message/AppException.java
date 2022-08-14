/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Liu Shilei
 * @version:      V1.0 
 * @Date:        2015年9月17日 上午11:14:43
 */
package hry.model.message;


import hry.bean.BaseModel;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * <p> 异常日志</p>
 * @author:         Liu Shilei 
 * @Date :          2015年9月17日 上午11:14:43 
 */

@Table(name = "app_exception")
public class AppException extends BaseModel {
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	
	/**  
	 * @Fields : 异常名称   
	 */
	@Column(name = "name")
	private String name;
	
	/**  
	 * @Fields : 异常详细信息描述   
	 */
	@Column(name = "notes")
	private String notes;
	
	/**  
	 * @Fields : 异常类型   sync同步请求  async异步请求
	 */
	@Column(name = "type")
	private String type;
	
	/**  
	 * @Fields : 请求ip地址
	 */
	@Column(name = "ip")
	private String ip;
	
	/**  
	 * @Fields : 请求参数
	 */
	@Column(name = "parameter")
	private String parameter;
	
	/**  
	 * @Fields : 请求方式    post  get
	 */
	@Column(name = "requestmethod")
	private String requestmethod;
	
	/**  
	 * @Fields : 请求url地址
	 */
	@Column(name = "requestaddress")
	private String requestaddress;
	
	
	
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
	public String getName() {
		return name;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getNotes() {
		return notes;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setNotes(String notes) {
		this.notes = notes;
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
	public String getParameter() {
		return parameter;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getRequestmethod() {
		return requestmethod;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setRequestmethod(String requestmethod) {
		this.requestmethod = requestmethod;
	}

	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getRequestaddress() {
		return requestaddress;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setRequestaddress(String requestaddress) {
		this.requestaddress = requestaddress;
	}
	
	

	
	
	
	
}
