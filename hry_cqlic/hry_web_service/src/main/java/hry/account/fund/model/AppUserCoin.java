/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      denghf
 * @version:     V1.0 
 * @Date:        2018-05-10 19:18:42 
 */
package hry.account.fund.model;

import static javax.persistence.GenerationType.IDENTITY;
import hry.core.mvc.model.BaseModel;


import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * <p> AppUserCoin </p>
 * @author:         denghf
 * @Date :          2018-05-10 19:18:42  
 */
@Table(name="app_user_coin")
public class AppUserCoin implements Serializable{
	
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //id
	
	@Column(name= "userName")
	private String userName;  //用户名
	
	@Column(name= "passWord")
	private String passWord;  //密码
	
	@Column(name= "salt")
	private String salt;  //盐值

	/**
	 * 创建时间
	 */
	@Column(name="created")
	private Date created;

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	/**
	 * <p>id</p>
	 * @author:  denghf
	 * @return:  Long 
	 * @Date :   2018-05-10 19:18:42    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>id</p>
	 * @author:  denghf
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-05-10 19:18:42   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>用户名</p>
	 * @author:  denghf
	 * @return:  String 
	 * @Date :   2018-05-10 19:18:42    
	 */
	public String getUserName() {
		return userName;
	}
	
	/**
	 * <p>用户名</p>
	 * @author:  denghf
	 * @param:   @param userName
	 * @return:  void 
	 * @Date :   2018-05-10 19:18:42   
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
	/**
	 * <p>密码</p>
	 * @author:  denghf
	 * @return:  String 
	 * @Date :   2018-05-10 19:18:42    
	 */
	public String getPassWord() {
		return passWord;
	}
	
	/**
	 * <p>密码</p>
	 * @author:  denghf
	 * @param:   @param passWord
	 * @return:  void 
	 * @Date :   2018-05-10 19:18:42   
	 */
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	
	
	/**
	 * <p>盐值</p>
	 * @author:  denghf
	 * @return:  String 
	 * @Date :   2018-05-10 19:18:42    
	 */
	public String getSalt() {
		return salt;
	}
	
	/**
	 * <p>盐值</p>
	 * @author:  denghf
	 * @param:   @param salt
	 * @return:  void 
	 * @Date :   2018-05-10 19:18:42   
	 */
	public void setSalt(String salt) {
		this.salt = salt;
	}
	
	

}
