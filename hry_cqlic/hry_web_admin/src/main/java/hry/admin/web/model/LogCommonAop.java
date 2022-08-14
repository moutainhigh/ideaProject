/**
 * Copyright:   
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-08-22 13:39:20 
 */
package hry.admin.web.model;


import hry.bean.BaseModel;


import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> LogCommonAop </p>
 * @author:         liushilei
 * @Date :          2018-08-22 13:39:20  
 */
@Table(name="log_common_aop")
public class LogCommonAop extends BaseModel {
	
	
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
	
	@Column(name= "name")
	private String name;  //
	
	@Column(name= "methodName")
	private String methodName;  //
	
	@Column(name= "args")
	private String args;  //
	
	@Column(name= "target")
	private String target;  //
	
	@Column(name= "remark")
	private String remark;  //
	
	
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Long 
	 * @Date :   2018-08-22 13:39:20    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-08-22 13:39:20   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Long 
	 * @Date :   2018-08-22 13:39:20    
	 */
	public Long getUserId() {
		return userId;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param userId
	 * @return:  void 
	 * @Date :   2018-08-22 13:39:20   
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-08-22 13:39:20    
	 */
	public String getUserName() {
		return userName;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param userName
	 * @return:  void 
	 * @Date :   2018-08-22 13:39:20   
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-08-22 13:39:20    
	 */
	public String getIp() {
		return ip;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param ip
	 * @return:  void 
	 * @Date :   2018-08-22 13:39:20   
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-08-22 13:39:20    
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param name
	 * @return:  void 
	 * @Date :   2018-08-22 13:39:20   
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-08-22 13:39:20    
	 */
	public String getMethodName() {
		return methodName;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param methodName
	 * @return:  void 
	 * @Date :   2018-08-22 13:39:20   
	 */
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-08-22 13:39:20    
	 */
	public String getArgs() {
		return args;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param args
	 * @return:  void 
	 * @Date :   2018-08-22 13:39:20   
	 */
	public void setArgs(String args) {
		this.args = args;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-08-22 13:39:20    
	 */
	public String getTarget() {
		return target;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param target
	 * @return:  void 
	 * @Date :   2018-08-22 13:39:20   
	 */
	public void setTarget(String target) {
		this.target = target;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-08-22 13:39:20    
	 */
	public String getRemark() {
		return remark;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param remark
	 * @return:  void 
	 * @Date :   2018-08-22 13:39:20   
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	

}
