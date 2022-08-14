/**
 * Copyright:   
 * @author:      tianpengyu
 * @version:     V1.0 
 * @Date:        2018-09-20 17:20:28 
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
 * <p> AppException </p>
 * @author:         tianpengyu
 * @Date :          2018-09-20 17:20:28  
 */
@Table(name="app_exception")
public class AppException extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "ip")
	private String ip;  //
	
	@Column(name= "name")
	private String name;  //
	
	@Column(name= "notes")
	private String notes;  //
	
	@Column(name= "parameter")
	private String parameter;  //
	
	@Column(name= "requestaddress")
	private String requestaddress;  //
	
	@Column(name= "requestmethod")
	private String requestmethod;  //
	
	@Column(name= "time")
	private Date time;  //
	
	@Column(name= "type")
	private String type;  //
	
	@Column(name= "SassId")
	private String SassId;  //
	
	@Column(name= "saasId")
	private String saasId;  //
	
	
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  Long 
	 * @Date :   2018-09-20 17:20:28    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-09-20 17:20:28   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-09-20 17:20:28    
	 */
	public String getIp() {
		return ip;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param ip
	 * @return:  void 
	 * @Date :   2018-09-20 17:20:28   
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-09-20 17:20:28    
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param name
	 * @return:  void 
	 * @Date :   2018-09-20 17:20:28   
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-09-20 17:20:28    
	 */
	public String getNotes() {
		return notes;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param notes
	 * @return:  void 
	 * @Date :   2018-09-20 17:20:28   
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-09-20 17:20:28    
	 */
	public String getParameter() {
		return parameter;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param parameter
	 * @return:  void 
	 * @Date :   2018-09-20 17:20:28   
	 */
	public void setParameter(String parameter) {
		this.parameter = parameter;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-09-20 17:20:28    
	 */
	public String getRequestaddress() {
		return requestaddress;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param requestaddress
	 * @return:  void 
	 * @Date :   2018-09-20 17:20:28   
	 */
	public void setRequestaddress(String requestaddress) {
		this.requestaddress = requestaddress;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-09-20 17:20:28    
	 */
	public String getRequestmethod() {
		return requestmethod;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param requestmethod
	 * @return:  void 
	 * @Date :   2018-09-20 17:20:28   
	 */
	public void setRequestmethod(String requestmethod) {
		this.requestmethod = requestmethod;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  Date 
	 * @Date :   2018-09-20 17:20:28    
	 */
	public Date getTime() {
		return time;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param time
	 * @return:  void 
	 * @Date :   2018-09-20 17:20:28   
	 */
	public void setTime(Date time) {
		this.time = time;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-09-20 17:20:28    
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param type
	 * @return:  void 
	 * @Date :   2018-09-20 17:20:28   
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-09-20 17:20:28    
	 */
	public String getSassId() {
		return SassId;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param SassId
	 * @return:  void 
	 * @Date :   2018-09-20 17:20:28   
	 */
	public void setSassId(String SassId) {
		this.SassId = SassId;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-09-20 17:20:28    
	 */
	public String getSaasId() {
		return saasId;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param saasId
	 * @return:  void 
	 * @Date :   2018-09-20 17:20:28   
	 */
	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}
	
	

}
