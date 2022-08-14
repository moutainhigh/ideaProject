/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      denghf
 * @version:     V1.0 
 * @Date:        2018-05-10 19:18:42 
 */
package hry.manage.remote.model;

import java.io.Serializable;
import java.util.Date;


/**
 * <p> AppUserCoin </p>
 * @author:         denghf
 * @Date :          2018-05-10 19:18:42  
 */
public class AppUserCoinRemote implements Serializable{

	private Long id;  //id
	
	private String userName;  //用户名
	
	private String passWord;  //密码
	
	private String salt;  //盐值

	private Date created;

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getPassWord() {
		return passWord;
	}
	
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	
	public String getSalt() {
		return salt;
	}
	
	public void setSalt(String salt) {
		this.salt = salt;
	}
}
