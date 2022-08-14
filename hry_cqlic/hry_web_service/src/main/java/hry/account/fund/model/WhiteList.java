/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      denghf
 * @version:     V1.0 
 * @Date:        2018-06-15 10:24:41 
 */
package hry.account.fund.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import hry.core.mvc.model.BaseModel;

/**
 * <p> WhiteList </p>
 * @author:         denghf
 * @Date :          2018-06-15 10:24:41  
 */
@Table(name="white_list")
public class WhiteList extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //id
	
	@Column(name= "userId")
	private Long userId;  //用户ID
	
	@Column(name= "userName")
	private String userName;  //用户名
	
	@Column(name= "tel")
	private String tel;  //手机号
	
	@Column(name= "email")
	private String email;  //邮箱
	
	@Column(name= "trueName")
	private String trueName;  //姓名
	
	@Column(name= "ip")
	private String ip;  //ip
	
	@Column(name= "loginNum")
	private Long loginNum;  //登录次数
	
	@Column(name= "type")
	private Integer type;  //1手动添加 2自动添加
	
	@Column(name= "loginLast")
	private Date loginLast;  //最后一次登录时间
	
	/**
	 * <p>id</p>
	 * @author:  denghf
	 * @return:  Long 
	 * @Date :   2018-06-15 10:24:41    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>id</p>
	 * @author:  denghf
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-06-15 10:24:41   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>用户ID</p>
	 * @author:  denghf
	 * @return:  Long 
	 * @Date :   2018-06-15 10:24:41    
	 */
	public Long getUserId() {
		return userId;
	}
	
	/**
	 * <p>用户ID</p>
	 * @author:  denghf
	 * @param:   @param userId
	 * @return:  void 
	 * @Date :   2018-06-15 10:24:41   
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	
	/**
	 * <p>用户名</p>
	 * @author:  denghf
	 * @return:  String 
	 * @Date :   2018-06-15 10:24:41    
	 */
	public String getUserName() {
		return userName;
	}
	
	/**
	 * <p>用户名</p>
	 * @author:  denghf
	 * @param:   @param userName
	 * @return:  void 
	 * @Date :   2018-06-15 10:24:41   
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
	/**
	 * <p>手机号</p>
	 * @author:  denghf
	 * @return:  String 
	 * @Date :   2018-06-15 10:24:41    
	 */
	public String getTel() {
		return tel;
	}
	
	/**
	 * <p>手机号</p>
	 * @author:  denghf
	 * @param:   @param tel
	 * @return:  void 
	 * @Date :   2018-06-15 10:24:41   
	 */
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	
	/**
	 * <p>邮箱</p>
	 * @author:  denghf
	 * @return:  String 
	 * @Date :   2018-06-15 10:24:41    
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * <p>邮箱</p>
	 * @author:  denghf
	 * @param:   @param email
	 * @return:  void 
	 * @Date :   2018-06-15 10:24:41   
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	/**
	 * <p>姓名</p>
	 * @author:  denghf
	 * @return:  String 
	 * @Date :   2018-06-15 10:24:41    
	 */
	public String getTrueName() {
		return trueName;
	}
	
	/**
	 * <p>姓名</p>
	 * @author:  denghf
	 * @param:   @param trueName
	 * @return:  void 
	 * @Date :   2018-06-15 10:24:41   
	 */
	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}
	
	
	/**
	 * <p>ip</p>
	 * @author:  denghf
	 * @return:  String 
	 * @Date :   2018-06-15 10:24:41    
	 */
	public String getIp() {
		return ip;
	}
	
	/**
	 * <p>ip</p>
	 * @author:  denghf
	 * @param:   @param ip
	 * @return:  void 
	 * @Date :   2018-06-15 10:24:41   
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	
	/**
	 * <p>登录次数</p>
	 * @author:  denghf
	 * @return:  Long 
	 * @Date :   2018-06-15 10:24:41    
	 */
	public Long getLoginNum() {
		return loginNum;
	}
	
	/**
	 * <p>登录次数</p>
	 * @author:  denghf
	 * @param:   @param loginNum
	 * @return:  void 
	 * @Date :   2018-06-15 10:24:41   
	 */
	public void setLoginNum(Long loginNum) {
		this.loginNum = loginNum;
	}
	
	
	/**
	 * <p>1手动添加 2自动添加</p>
	 * @author:  denghf
	 * @return:  Integer 
	 * @Date :   2018-06-15 10:24:41    
	 */
	public Integer getType() {
		return type;
	}
	
	/**
	 * <p>1手动添加 2自动添加</p>
	 * @author:  denghf
	 * @param:   @param type
	 * @return:  void 
	 * @Date :   2018-06-15 10:24:41   
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	
	
	/**
	 * <p>最后一次登录时间</p>
	 * @author:  denghf
	 * @return:  Date 
	 * @Date :   2018-06-15 10:24:41    
	 */
	public Date getLoginLast() {
		return loginLast;
	}
	
	/**
	 * <p>最后一次登录时间</p>
	 * @author:  denghf
	 * @param:   @param loginLast
	 * @return:  void 
	 * @Date :   2018-06-15 10:24:41   
	 */
	public void setLoginLast(Date loginLast) {
		this.loginLast = loginLast;
	}
	
	

}
