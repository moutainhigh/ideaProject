/**
 * Copyright:   互融云
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2017-05-27 16:05:55 
 */
package hry.oauth.user.model;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import hry.bean.BaseModel;

/**
 * <p> AppUser </p>
 * @author:         liushilei
 * @Date :          2017-05-27 16:05:55  
 */
@Table(name="new_app_user")
public class AppUser extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //id
	
	@Column(name= "username")
	private String username;  //用户名
	
	@Column(name= "password")
	private String password;  //密码
	
	@Column(name= "salt")
	private String salt;  //盐值
	
	@Column(name= "isdelete")
	private Integer isdelete;  //是否删除，0否1是
	
	@Column(name= "islock")
	private Integer islock;  //是否锁定，0否1是
	
	@Column(name= "created")
	private Date created;  //created
	
	@Column(name= "modified")
	private Date modified;  //modified
	
	@Column(name= "email")
	private String email;  //邮箱
	
	@Column(name= "mobile")
	private String mobile;  //邮箱
	
	@Transient
	private String roleName;//角色名称
	
	@Transient
	private String departmentName;//部门名称
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * <p>username</p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2017-05-27 16:05:55    
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * <p>username</p>
	 * @author:  liushilei
	 * @param:   @param username
	 * @return:  void 
	 * @Date :   2017-05-27 16:05:55   
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	
	/**
	 * <p>password</p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2017-05-27 16:05:55    
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * <p>password</p>
	 * @author:  liushilei
	 * @param:   @param password
	 * @return:  void 
	 * @Date :   2017-05-27 16:05:55   
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	/**
	 * <p>salt</p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2017-05-27 16:05:55    
	 */
	public String getSalt() {
		return salt;
	}
	
	/**
	 * <p>salt</p>
	 * @author:  liushilei
	 * @param:   @param salt
	 * @return:  void 
	 * @Date :   2017-05-27 16:05:55   
	 */
	public void setSalt(String salt) {
		this.salt = salt;
	}
	
	
	/**
	 * <p>isdelete</p>
	 * @author:  liushilei
	 * @return:  Integer 
	 * @Date :   2017-05-27 16:05:55    
	 */
	public Integer getIsdelete() {
		return isdelete;
	}
	
	/**
	 * <p>isdelete</p>
	 * @author:  liushilei
	 * @param:   @param isdelete
	 * @return:  void 
	 * @Date :   2017-05-27 16:05:55   
	 */
	public void setIsdelete(Integer isdelete) {
		this.isdelete = isdelete;
	}
	
	
	/**
	 * <p>islock</p>
	 * @author:  liushilei
	 * @return:  Integer 
	 * @Date :   2017-05-27 16:05:55    
	 */
	public Integer getIslock() {
		return islock;
	}
	
	/**
	 * <p>islock</p>
	 * @author:  liushilei
	 * @param:   @param islock
	 * @return:  void 
	 * @Date :   2017-05-27 16:05:55   
	 */
	public void setIslock(Integer islock) {
		this.islock = islock;
	}
	
	
	/**
	 * <p>created</p>
	 * @author:  liushilei
	 * @return:  Date 
	 * @Date :   2017-05-27 16:05:55    
	 */
	public Date getCreated() {
		return created;
	}
	
	/**
	 * <p>created</p>
	 * @author:  liushilei
	 * @param:   @param created
	 * @return:  void 
	 * @Date :   2017-05-27 16:05:55   
	 */
	public void setCreated(Date created) {
		this.created = created;
	}
	
	
	/**
	 * <p>modified</p>
	 * @author:  liushilei
	 * @return:  Date 
	 * @Date :   2017-05-27 16:05:55    
	 */
	public Date getModified() {
		return modified;
	}
	
	/**
	 * <p>modified</p>
	 * @author:  liushilei
	 * @param:   @param modified
	 * @return:  void 
	 * @Date :   2017-05-27 16:05:55   
	 */
	public void setModified(Date modified) {
		this.modified = modified;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	
	

}
