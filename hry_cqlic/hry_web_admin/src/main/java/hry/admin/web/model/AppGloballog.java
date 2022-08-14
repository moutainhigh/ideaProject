/**
 * Copyright:   
 * @author:      tianpengyu
 * @version:     V1.0 
 * @Date:        2018-09-20 17:47:16 
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
 * <p> AppGloballog </p>
 * @author:         tianpengyu
 * @Date :          2018-09-20 17:47:16  
 */
@Table(name="app_globallog")
public class AppGloballog extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "log_level")
	private String log_level;  //日志级别
	
	@Column(name= "log_time")
	private Date log_time;  // 时间
	
	@Column(name= "app_id")
	private Integer app_id;  //
	
	@Column(name= "log_detail")
	private String log_detail;  //日志详情
	
	@Column(name= "server_ip")
	private String server_ip;  //
	
	@Column(name= "log_err_location")
	private String log_err_location;  //

	@Column(name= "app_name")
	private String app_name;  //应用名称


	public String getApp_name() {
		return app_name;
	}

	public void setApp_name(String app_name) {
		this.app_name = app_name;
	}

	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  Long 
	 * @Date :   2018-09-20 17:47:16    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-09-20 17:47:16   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-09-20 17:47:16    
	 */
	public String getLog_level() {
		return log_level;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param log_level
	 * @return:  void 
	 * @Date :   2018-09-20 17:47:16   
	 */
	public void setLog_level(String log_level) {
		this.log_level = log_level;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  Date 
	 * @Date :   2018-09-20 17:47:16    
	 */
	public Date getLog_time() {
		return log_time;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param log_time
	 * @return:  void 
	 * @Date :   2018-09-20 17:47:16   
	 */
	public void setLog_time(Date log_time) {
		this.log_time = log_time;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  Integer 
	 * @Date :   2018-09-20 17:47:16    
	 */
	public Integer getApp_id() {
		return app_id;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param app_id
	 * @return:  void 
	 * @Date :   2018-09-20 17:47:16   
	 */
	public void setApp_id(Integer app_id) {
		this.app_id = app_id;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-09-20 17:47:16    
	 */
	public String getLog_detail() {
		return log_detail;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param log_detail
	 * @return:  void 
	 * @Date :   2018-09-20 17:47:16   
	 */
	public void setLog_detail(String log_detail) {
		this.log_detail = log_detail;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-09-20 17:47:16    
	 */
	public String getServer_ip() {
		return server_ip;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param server_ip
	 * @return:  void 
	 * @Date :   2018-09-20 17:47:16   
	 */
	public void setServer_ip(String server_ip) {
		this.server_ip = server_ip;
	}
	
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @return:  String 
	 * @Date :   2018-09-20 17:47:16    
	 */
	public String getLog_err_location() {
		return log_err_location;
	}
	
	/**
	 * <p></p>
	 * @author:  tianpengyu
	 * @param:   @param log_err_location
	 * @return:  void 
	 * @Date :   2018-09-20 17:47:16   
	 */
	public void setLog_err_location(String log_err_location) {
		this.log_err_location = log_err_location;
	}
	
	

}
