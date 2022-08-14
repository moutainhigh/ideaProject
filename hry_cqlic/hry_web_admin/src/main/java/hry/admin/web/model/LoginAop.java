/**
 * Copyright:   
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-20 14:42:28 
 */
package hry.admin.web.model;


import hry.admin.customer.model.AppCustomer;
import hry.admin.customer.model.AppPersonInfo;
import hry.bean.BaseModel;


import javax.persistence.*;

/**
 * <p> LoginAop </p>
 * @author:         liushilei
 * @Date :          2018-06-20 14:42:28  
 */
@Table(name="login_aop")
public class LoginAop extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "userId")
	private Long userId;  //
	
	@Column(name= "userName")
	private String userName;  //
	
	@Column(name= "ip")
	private String ip;  //
	
	@Column(name= "type")
	private String type;  //
	
	@Column(name= "methodName")
	private String methodName;  //
	
	@Column(name= "args")
	private String args;  //
	
	@Column(name= "target")
	private String target;  //
	
	@Column(name= "saasId")
	private String saasId;  //

	@Transient
	private Integer loginCount; //登录次数

	//关联表
	@Transient
	private AppCustomer appCustomer; //用户信息

	@Transient
	private AppPersonInfo appPersonInfo; //个人信息

	public AppCustomer getAppCustomer() {
		return appCustomer;
	}

	public void setAppCustomer(AppCustomer appCustomer) {
		this.appCustomer = appCustomer;
	}

	public Integer getLoginCount() {
		return loginCount;
	}

	public void setLoginCount(Integer loginCount) {
		this.loginCount = loginCount;
	}

	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Long 
	 * @Date :   2018-06-20 14:42:28    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-06-20 14:42:28   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Long 
	 * @Date :   2018-06-20 14:42:28    
	 */
	public Long getUserId() {
		return userId;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param userId
	 * @return:  void 
	 * @Date :   2018-06-20 14:42:28   
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-20 14:42:28    
	 */
	public String getUserName() {
		return userName;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param userName
	 * @return:  void 
	 * @Date :   2018-06-20 14:42:28   
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-20 14:42:28    
	 */
	public String getIp() {
		return ip;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param ip
	 * @return:  void 
	 * @Date :   2018-06-20 14:42:28   
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-20 14:42:28    
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param type
	 * @return:  void 
	 * @Date :   2018-06-20 14:42:28   
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-20 14:42:28    
	 */
	public String getMethodName() {
		return methodName;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param methodName
	 * @return:  void 
	 * @Date :   2018-06-20 14:42:28   
	 */
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-20 14:42:28    
	 */
	public String getArgs() {
		return args;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param args
	 * @return:  void 
	 * @Date :   2018-06-20 14:42:28   
	 */
	public void setArgs(String args) {
		this.args = args;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-20 14:42:28    
	 */
	public String getTarget() {
		return target;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param target
	 * @return:  void 
	 * @Date :   2018-06-20 14:42:28   
	 */
	public void setTarget(String target) {
		this.target = target;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-20 14:42:28    
	 */
	public String getSaasId() {
		return saasId;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param saasId
	 * @return:  void 
	 * @Date :   2018-06-20 14:42:28   
	 */
	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}
	
	

}
