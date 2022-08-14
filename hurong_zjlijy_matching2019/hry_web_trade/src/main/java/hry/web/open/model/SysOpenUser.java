/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      shangxl
 * @version:     V1.0 
 * @Date:        2017-07-31 10:05:11 
 */
package hry.web.open.model;

import static javax.persistence.GenerationType.IDENTITY;
import hry.core.mvc.model.BaseModel;


import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> SysOpenUser </p>
 * @author:         shangxl
 * @Date :          2017-07-31 10:05:11  
 */
@Table(name="sys_open_user")
public class SysOpenUser extends BaseModel {
	
	
	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	/**  
	 * @Fields : TODO   
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //id
	
	@Column(name= "userName")
	private String userName;  //open后台用户名
	
	@Column(name= "passWord")
	private String passWord;  //open后台password
	
	@Column(name= "appKey")
	private String appKey;  //公钥
	
	@Column(name= "secret")
	private String secret;  //秘钥
	
	
	
	
	/**
	 * <p>id</p>
	 * @author:  shangxl
	 * @return:  Long 
	 * @Date :   2017-07-31 10:05:11    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>id</p>
	 * @author:  shangxl
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2017-07-31 10:05:11   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>open后台用户名</p>
	 * @author:  shangxl
	 * @return:  String 
	 * @Date :   2017-07-31 10:05:11    
	 */
	public String getUserName() {
		return userName;
	}
	
	/**
	 * <p>open后台用户名</p>
	 * @author:  shangxl
	 * @param:   @param userName
	 * @return:  void 
	 * @Date :   2017-07-31 10:05:11   
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
	
	/**
	 * <p>加密key</p>
	 * @author:  shangxl
	 * @return:  String 
	 * @Date :   2017-07-31 10:05:11    
	 */
	public String getSecret() {
		return secret;
	}
	
	/**
	 * <p>加密key</p>
	 * @author:  shangxl
	 * @param:   @param secret
	 * @return:  void 
	 * @Date :   2017-07-31 10:05:11   
	 */
	public void setSecret(String secret) {
		this.secret = secret;
	}
	
	

}
