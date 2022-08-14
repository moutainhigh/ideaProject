/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      denghf
 * @version:     V1.0 
 * @Date:        2018-04-10 18:11:14 
 */
package hry.web.log.model;

import static javax.persistence.GenerationType.IDENTITY;
import hry.core.mvc.model.BaseModel;


import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> LoginSafe </p>
 * @author:         denghf
 * @Date :          2018-04-10 18:11:14  
 */
@Table(name="login_safe")
public class LoginSafe extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //id
	
	@Column(name= "customerId")
	private Long customerId;  //用户id
	
	@Column(name= "userName")
	private String userName;  //登录名
	
	@Column(name= "content")
	private String content;  //ip、浏览器
	
	@Column(name= "type")
	private Integer type;  //0:ip 1:浏览器
	
	
	
	
	/**
	 * <p>id</p>
	 * @author:  denghf
	 * @return:  Long 
	 * @Date :   2018-04-10 18:11:14    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>id</p>
	 * @author:  denghf
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-04-10 18:11:14   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>用户id</p>
	 * @author:  denghf
	 * @return:  Long 
	 * @Date :   2018-04-10 18:11:14    
	 */
	public Long getCustomerId() {
		return customerId;
	}
	
	/**
	 * <p>用户id</p>
	 * @author:  denghf
	 * @param:   @param customerId
	 * @return:  void 
	 * @Date :   2018-04-10 18:11:14   
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	
	/**
	 * <p>登录名</p>
	 * @author:  denghf
	 * @return:  String 
	 * @Date :   2018-04-10 18:11:14    
	 */
	public String getUserName() {
		return userName;
	}
	
	/**
	 * <p>登录名</p>
	 * @author:  denghf
	 * @param:   @param userName
	 * @return:  void 
	 * @Date :   2018-04-10 18:11:14   
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
	/**
	 * <p>ip、浏览器</p>
	 * @author:  denghf
	 * @return:  String 
	 * @Date :   2018-04-10 18:11:14    
	 */
	public String getContent() {
		return content;
	}
	
	/**
	 * <p>ip、浏览器</p>
	 * @author:  denghf
	 * @param:   @param content
	 * @return:  void 
	 * @Date :   2018-04-10 18:11:14   
	 */
	public void setContent(String content) {
		this.content = content;
	}
	
	
	/**
	 * <p>0:ip 1:浏览器</p>
	 * @author:  denghf
	 * @return:  Integer 
	 * @Date :   2018-04-10 18:11:14    
	 */
	public Integer getType() {
		return type;
	}
	
	/**
	 * <p>0:ip 1:浏览器</p>
	 * @author:  denghf
	 * @param:   @param type
	 * @return:  void 
	 * @Date :   2018-04-10 18:11:14   
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	
	

}
