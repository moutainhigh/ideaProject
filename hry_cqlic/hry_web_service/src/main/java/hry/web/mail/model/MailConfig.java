/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      sunyujie
 * @version:     V1.0 
 * @Date:        2018-06-04 14:36:50 
 */
package hry.web.mail.model;

import static javax.persistence.GenerationType.IDENTITY;
import hry.core.mvc.model.BaseModel;


import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> MailConfig </p>
 * @author:         sunyujie
 * @Date :          2018-06-04 14:36:50  
 */
@Table(name="mail_config")
public class MailConfig extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //id
	
	@Column(name= "sort")
	private Integer sort;  //排序
	
	@Column(name= "port")
	private String port;  //端口
	
	@Column(name= "host")
	private String host;  //服务器
	
	@Column(name= "protocol")
	private String protocol;  //协议
	
	@Column(name= "auth")
	private Integer auth;  //开启认证   1开启  0未开启 
	
	@Column(name= "emailUser")
	private String emailUser;  //发送用户
	
	@Column(name= "agreedUser")
	private String agreedUser;  //认证用户
	
	@Column(name= "agreedPwd")
	private String agreedPwd;  //认证密码
	
	@Column(name= "customName")
	private String customName;  //自定义发件名称
	
	@Column(name= "sslflag")
	private Integer sslflag;  //是否开启了ssl验证  1开启 0未开启
	
	@Column(name= "starttls")
	private Integer starttls;  //是否开启tls   1开启  0未开启
	
	@Column(name= "status")
	private Integer status; //启用状态     1开启   0关闭

	@Column(name= "prefix")
	private String prefix;  //邮箱标题前缀
	
	
	
	
	/**
	 * <p>id</p>
	 * @author:  sunyujie
	 * @return:  Integer 
	 * @Date :   2018-06-04 14:36:50    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>id</p>
	 * @author:  sunyujie
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-06-04 14:36:50   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>排序</p>
	 * @author:  sunyujie
	 * @return:  Integer 
	 * @Date :   2018-06-04 14:36:50    
	 */
	public Integer getSort() {
		return sort;
	}
	
	/**
	 * <p>排序</p>
	 * @author:  sunyujie
	 * @param:   @param sort
	 * @return:  void 
	 * @Date :   2018-06-04 14:36:50   
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	
	/**
	 * <p>端口</p>
	 * @author:  sunyujie
	 * @return:  String 
	 * @Date :   2018-06-04 14:36:50    
	 */
	public String getPort() {
		return port;
	}
	
	/**
	 * <p>端口</p>
	 * @author:  sunyujie
	 * @param:   @param port
	 * @return:  void 
	 * @Date :   2018-06-04 14:36:50   
	 */
	public void setPort(String port) {
		this.port = port;
	}
	
	
	/**
	 * <p>服务器</p>
	 * @author:  sunyujie
	 * @return:  String 
	 * @Date :   2018-06-04 14:36:50    
	 */
	public String getHost() {
		return host;
	}
	
	/**
	 * <p>服务器</p>
	 * @author:  sunyujie
	 * @param:   @param host
	 * @return:  void 
	 * @Date :   2018-06-04 14:36:50   
	 */
	public void setHost(String host) {
		this.host = host;
	}
	
	
	/**
	 * <p>协议</p>
	 * @author:  sunyujie
	 * @return:  String 
	 * @Date :   2018-06-04 14:36:50    
	 */
	public String getProtocol() {
		return protocol;
	}
	
	/**
	 * <p>协议</p>
	 * @author:  sunyujie
	 * @param:   @param protocol
	 * @return:  void 
	 * @Date :   2018-06-04 14:36:50   
	 */
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}
	
	
	/**
	 * <p>开启认证   1开启  0未开启 </p>
	 * @author:  sunyujie
	 * @return:  Integer 
	 * @Date :   2018-06-04 14:36:50    
	 */
	public Integer getAuth() {
		return auth;
	}
	
	/**
	 * <p>开启认证   1开启  0未开启 </p>
	 * @author:  sunyujie
	 * @param:   @param auth
	 * @return:  void 
	 * @Date :   2018-06-04 14:36:50   
	 */
	public void setAuth(Integer auth) {
		this.auth = auth;
	}
	
	
	/**
	 * <p>发送用户</p>
	 * @author:  sunyujie
	 * @return:  String 
	 * @Date :   2018-06-04 14:36:50    
	 */
	public String getEmailUser() {
		return emailUser;
	}
	
	/**
	 * <p>发送用户</p>
	 * @author:  sunyujie
	 * @param:   @param emailUser
	 * @return:  void 
	 * @Date :   2018-06-04 14:36:50   
	 */
	public void setEmailUser(String emailUser) {
		this.emailUser = emailUser;
	}
	
	
	/**
	 * <p>认证用户</p>
	 * @author:  sunyujie
	 * @return:  String 
	 * @Date :   2018-06-04 14:36:50    
	 */
	public String getAgreedUser() {
		return agreedUser;
	}
	
	/**
	 * <p>认证用户</p>
	 * @author:  sunyujie
	 * @param:   @param agreedUser
	 * @return:  void 
	 * @Date :   2018-06-04 14:36:50   
	 */
	public void setAgreedUser(String agreedUser) {
		this.agreedUser = agreedUser;
	}
	
	
	/**
	 * <p>认证密码</p>
	 * @author:  sunyujie
	 * @return:  String 
	 * @Date :   2018-06-04 14:36:50    
	 */
	public String getAgreedPwd() {
		return agreedPwd;
	}
	
	/**
	 * <p>认证密码</p>
	 * @author:  sunyujie
	 * @param:   @param agreedPwd
	 * @return:  void 
	 * @Date :   2018-06-04 14:36:50   
	 */
	public void setAgreedPwd(String agreedPwd) {
		this.agreedPwd = agreedPwd;
	}
	
	
	/**
	 * <p>自定义发件名称</p>
	 * @author:  sunyujie
	 * @return:  String 
	 * @Date :   2018-06-04 14:36:50    
	 */
	public String getCustomName() {
		return customName;
	}
	
	/**
	 * <p>自定义发件名称</p>
	 * @author:  sunyujie
	 * @param:   @param customName
	 * @return:  void 
	 * @Date :   2018-06-04 14:36:50   
	 */
	public void setCustomName(String customName) {
		this.customName = customName;
	}
	
	
	/**
	 * <p>是否开启了ssl验证  1开启 0未开启</p>
	 * @author:  sunyujie
	 * @return:  Integer 
	 * @Date :   2018-06-04 14:36:50    
	 */
	public Integer getSslflag() {
		return sslflag;
	}
	
	/**
	 * <p>是否开启了ssl验证  1开启 0未开启</p>
	 * @author:  sunyujie
	 * @param:   @param sslflag
	 * @return:  void 
	 * @Date :   2018-06-04 14:36:50   
	 */
	public void setSslflag(Integer sslflag) {
		this.sslflag = sslflag;
	}
	
	
	/**
	 * <p>是否开启tls   1开启  0未开启</p>
	 * @author:  sunyujie
	 * @return:  Integer 
	 * @Date :   2018-06-04 14:36:50    
	 */
	public Integer getStarttls() {
		return starttls;
	}
	
	/**
	 * <p>是否开启tls   1开启  0未开启</p>
	 * @author:  sunyujie
	 * @param:   @param starttls
	 * @return:  void 
	 * @Date :   2018-06-04 14:36:50   
	 */
	public void setStarttls(Integer starttls) {
		this.starttls = starttls;
	}
	
	
	/**
	 * <p>启用状态     1开启   0关闭</p>
	 * @author:  sunyujie
	 * @return:  Integer 
	 * @Date :   2018-06-04 14:36:50    
	 */
	public Integer getStatus() {
		return status;
	}
	
	/**
	 * <p>启用状态     1开启   0关闭</p>
	 * @author:  sunyujie
	 * @param:   @param status
	 * @return:  void 
	 * @Date :   2018-06-04 14:36:50   
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}


	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
}
