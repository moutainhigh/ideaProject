/**
 * Copyright:   
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-07-11 14:27:21 
 */
package hry.admin.web.model;


import hry.bean.BaseModel;

import javax.persistence.*;
import java.util.Date;

/**
 * <p> WhiteList </p>
 * @author:         liushilei
 * @Date :          2018-07-11 14:27:21  
 */
@Table(name="white_list")
public class WhiteList extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "userId")
	private Long userId;  //
	
	@Column(name= "userName")
	private String userName;  //
	
	@Column(name= "tel")
	private String tel;  //
	
	@Column(name= "email")
	private String email;  //
	
	@Column(name= "trueName")
	private String trueName;  //
	
	@Column(name= "ip")
	private String ip;  //
	
	@Column(name= "loginNum")
	private Long loginNum;  //
	
	@Column(name= "type")
	private Integer type;  //
	
	@Column(name= "loginLast")
	private Date loginLast;  //
	
	@Column(name= "saasId")
	private String saasId;  //
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Long 
	 * @Date :   2018-07-11 14:27:21    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-07-11 14:27:21   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Long 
	 * @Date :   2018-07-11 14:27:21    
	 */
	public Long getUserId() {
		return userId;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param userId
	 * @return:  void 
	 * @Date :   2018-07-11 14:27:21   
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-07-11 14:27:21    
	 */
	public String getUserName() {
		return userName;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param userName
	 * @return:  void 
	 * @Date :   2018-07-11 14:27:21   
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-07-11 14:27:21    
	 */
	public String getTel() {
		return tel;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param tel
	 * @return:  void 
	 * @Date :   2018-07-11 14:27:21   
	 */
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-07-11 14:27:21    
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param email
	 * @return:  void 
	 * @Date :   2018-07-11 14:27:21   
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-07-11 14:27:21    
	 */
	public String getTrueName() {
		return trueName;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param trueName
	 * @return:  void 
	 * @Date :   2018-07-11 14:27:21   
	 */
	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-07-11 14:27:21    
	 */
	public String getIp() {
		return ip;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param ip
	 * @return:  void 
	 * @Date :   2018-07-11 14:27:21   
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Long 
	 * @Date :   2018-07-11 14:27:21    
	 */
	public Long getLoginNum() {
		return loginNum;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param loginNum
	 * @return:  void 
	 * @Date :   2018-07-11 14:27:21   
	 */
	public void setLoginNum(Long loginNum) {
		this.loginNum = loginNum;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Integer 
	 * @Date :   2018-07-11 14:27:21    
	 */
	public Integer getType() {
		return type;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param type
	 * @return:  void 
	 * @Date :   2018-07-11 14:27:21   
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Date 
	 * @Date :   2018-07-11 14:27:21    
	 */
	public Date getLoginLast() {
		return loginLast;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param loginLast
	 * @return:  void 
	 * @Date :   2018-07-11 14:27:21   
	 */
	public void setLoginLast(Date loginLast) {
		this.loginLast = loginLast;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-07-11 14:27:21    
	 */
	public String getSaasId() {
		return saasId;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param saasId
	 * @return:  void 
	 * @Date :   2018-07-11 14:27:21   
	 */
	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}
	
	

}
