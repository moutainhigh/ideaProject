/**
 * Copyright:   
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-20 15:39:55 
 */
package hry.admin.web.model;


import hry.bean.BaseModel;


import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> MailConfig </p>
 * @author:         liushilei
 * @Date :          2018-06-20 15:39:55  
 */
@Table(name="mail_config")
public class MailConfig extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //

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

	@Column(name= "saasId")
	private String saasId;  //
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Long 
	 * @Date :   2018-06-20 15:39:55    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-06-20 15:39:55   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Integer 
	 * @Date :   2018-06-20 15:39:55    
	 */
	public Integer getSort() {
		return sort;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param sort
	 * @return:  void 
	 * @Date :   2018-06-20 15:39:55   
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-20 15:39:55    
	 */
	public String getPort() {
		return port;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param port
	 * @return:  void 
	 * @Date :   2018-06-20 15:39:55   
	 */
	public void setPort(String port) {
		this.port = port;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-20 15:39:55    
	 */
	public String getHost() {
		return host;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param host
	 * @return:  void 
	 * @Date :   2018-06-20 15:39:55   
	 */
	public void setHost(String host) {
		this.host = host;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-20 15:39:55    
	 */
	public String getProtocol() {
		return protocol;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param protocol
	 * @return:  void 
	 * @Date :   2018-06-20 15:39:55   
	 */
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Integer 
	 * @Date :   2018-06-20 15:39:55    
	 */
	public Integer getAuth() {
		return auth;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param auth
	 * @return:  void 
	 * @Date :   2018-06-20 15:39:55   
	 */
	public void setAuth(Integer auth) {
		this.auth = auth;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-20 15:39:55    
	 */
	public String getEmailUser() {
		return emailUser;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param emailUser
	 * @return:  void 
	 * @Date :   2018-06-20 15:39:55   
	 */
	public void setEmailUser(String emailUser) {
		this.emailUser = emailUser;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-20 15:39:55    
	 */
	public String getAgreedUser() {
		return agreedUser;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param agreedUser
	 * @return:  void 
	 * @Date :   2018-06-20 15:39:55   
	 */
	public void setAgreedUser(String agreedUser) {
		this.agreedUser = agreedUser;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-20 15:39:55    
	 */
	public String getAgreedPwd() {
		return agreedPwd;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param agreedPwd
	 * @return:  void 
	 * @Date :   2018-06-20 15:39:55   
	 */
	public void setAgreedPwd(String agreedPwd) {
		this.agreedPwd = agreedPwd;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-20 15:39:55    
	 */
	public String getCustomName() {
		return customName;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param customName
	 * @return:  void 
	 * @Date :   2018-06-20 15:39:55   
	 */
	public void setCustomName(String customName) {
		this.customName = customName;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Integer 
	 * @Date :   2018-06-20 15:39:55    
	 */
	public Integer getSslflag() {
		return sslflag;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param sslflag
	 * @return:  void 
	 * @Date :   2018-06-20 15:39:55   
	 */
	public void setSslflag(Integer sslflag) {
		this.sslflag = sslflag;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Integer 
	 * @Date :   2018-06-20 15:39:55    
	 */
	public Integer getStarttls() {
		return starttls;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param starttls
	 * @return:  void 
	 * @Date :   2018-06-20 15:39:55   
	 */
	public void setStarttls(Integer starttls) {
		this.starttls = starttls;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-20 15:39:55    
	 */
	public String getPrefix() {
		return prefix;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param prefix
	 * @return:  void 
	 * @Date :   2018-06-20 15:39:55   
	 */
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Integer 
	 * @Date :   2018-06-20 15:39:55    
	 */
	public Integer getStatus() {
		return status;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param status
	 * @return:  void 
	 * @Date :   2018-06-20 15:39:55   
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-20 15:39:55    
	 */
	public String getSaasId() {
		return saasId;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param saasId
	 * @return:  void 
	 * @Date :   2018-06-20 15:39:55   
	 */
	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}
	
	

}
