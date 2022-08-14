/**
 * Copyright:   
 * @author:      sunyujie
 * @version:     V1.0 
 * @Date:        2018-07-19 11:31:39 
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
 * <p> AppServerMonitor </p>
 * @author:         sunyujie
 * @Date :          2018-07-19 11:31:39  
 */
@Table(name="app_server_monitor")
public class AppServerMonitor extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //

	@Column(name= "serverName")
	private String serverName;  //服务名称

	@Column(name= "serverHost")
	private String serverHost;  //服务ip地址
	
	@Column(name= "serverPort")
	private Integer serverPort;  //服务端口
	
	@Column(name= "type")
	private String type;  //服务类型
	
	@Column(name= "serverStruts")
	private Integer serverStruts;  //服务状态
	
	@Column(name= "isOpen")
	private Integer isOpen;  //是否监控
	
	@Column(name= "sendEmails")
	private String sendEmails;  //发送邮件
	
	@Column(name= "sendPhone")
	private String sendPhone;  //发送短信
	
	@Column(name= "failTime")
	private Date failTime;  //上一次错误时间
	
	@Column(name= "saasId")
	private String saasId;  //

	@Column(name= "mailConfigId")
	private Long mailConfigId;  //使用的邮箱服务器id
	
	
	
	
	/**
	 * <p></p>
	 * @author:  sunyujie
	 * @return:  Long 
	 * @Date :   2018-07-19 11:31:39    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  sunyujie
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-07-19 11:31:39   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p></p>
	 * @author:  sunyujie
	 * @return:  String 
	 * @Date :   2018-07-19 11:31:39    
	 */
	public String getServerHost() {
		return serverHost;
	}
	
	/**
	 * <p></p>
	 * @author:  sunyujie
	 * @param:   @param serverHost
	 * @return:  void 
	 * @Date :   2018-07-19 11:31:39   
	 */
	public void setServerHost(String serverHost) {
		this.serverHost = serverHost;
	}
	
	
	/**
	 * <p></p>
	 * @author:  sunyujie
	 * @return:  Integer 
	 * @Date :   2018-07-19 11:31:39    
	 */
	public Integer getServerPort() {
		return serverPort;
	}
	
	/**
	 * <p></p>
	 * @author:  sunyujie
	 * @param:   @param serverPort
	 * @return:  void 
	 * @Date :   2018-07-19 11:31:39   
	 */
	public void setServerPort(Integer serverPort) {
		this.serverPort = serverPort;
	}
	
	
	/**
	 * <p></p>
	 * @author:  sunyujie
	 * @return:  String 
	 * @Date :   2018-07-19 11:31:39    
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * <p></p>
	 * @author:  sunyujie
	 * @param:   @param type
	 * @return:  void 
	 * @Date :   2018-07-19 11:31:39   
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	
	/**
	 * <p></p>
	 * @author:  sunyujie
	 * @return:  Integer 
	 * @Date :   2018-07-19 11:31:39    
	 */
	public Integer getServerStruts() {
		return serverStruts;
	}
	
	/**
	 * <p></p>
	 * @author:  sunyujie
	 * @param:   @param serverStruts
	 * @return:  void 
	 * @Date :   2018-07-19 11:31:39   
	 */
	public void setServerStruts(Integer serverStruts) {
		this.serverStruts = serverStruts;
	}
	
	
	/**
	 * <p></p>
	 * @author:  sunyujie
	 * @return:  Integer 
	 * @Date :   2018-07-19 11:31:39    
	 */
	public Integer getIsOpen() {
		return isOpen;
	}
	
	/**
	 * <p></p>
	 * @author:  sunyujie
	 * @param:   @param isOpen
	 * @return:  void 
	 * @Date :   2018-07-19 11:31:39   
	 */
	public void setIsOpen(Integer isOpen) {
		this.isOpen = isOpen;
	}
	
	
	/**
	 * <p></p>
	 * @author:  sunyujie
	 * @return:  String 
	 * @Date :   2018-07-19 11:31:39    
	 */
	public String getSendEmails() {
		return sendEmails;
	}
	
	/**
	 * <p></p>
	 * @author:  sunyujie
	 * @param:   @param sendEmails
	 * @return:  void 
	 * @Date :   2018-07-19 11:31:39   
	 */
	public void setSendEmails(String sendEmails) {
		this.sendEmails = sendEmails;
	}
	
	
	/**
	 * <p></p>
	 * @author:  sunyujie
	 * @return:  String 
	 * @Date :   2018-07-19 11:31:39    
	 */
	public String getSendPhone() {
		return sendPhone;
	}
	
	/**
	 * <p></p>
	 * @author:  sunyujie
	 * @param:   @param sendPhone
	 * @return:  void 
	 * @Date :   2018-07-19 11:31:39   
	 */
	public void setSendPhone(String sendPhone) {
		this.sendPhone = sendPhone;
	}
	
	
	/**
	 * <p></p>
	 * @author:  sunyujie
	 * @return:  Date 
	 * @Date :   2018-07-19 11:31:39    
	 */
	public Date getFailTime() {
		return failTime;
	}
	
	/**
	 * <p></p>
	 * @author:  sunyujie
	 * @param:   @param failTime
	 * @return:  void 
	 * @Date :   2018-07-19 11:31:39   
	 */
	public void setFailTime(Date failTime) {
		this.failTime = failTime;
	}
	
	
	/**
	 * <p></p>
	 * @author:  sunyujie
	 * @return:  String 
	 * @Date :   2018-07-19 11:31:39    
	 */
	public String getSaasId() {
		return saasId;
	}
	
	/**
	 * <p></p>
	 * @author:  sunyujie
	 * @param:   @param saasId
	 * @return:  void 
	 * @Date :   2018-07-19 11:31:39   
	 */
	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public Long getMailConfigId() {
		return mailConfigId;
	}

	public void setMailConfigId(Long mailConfigId) {
		this.mailConfigId = mailConfigId;
	}
}
