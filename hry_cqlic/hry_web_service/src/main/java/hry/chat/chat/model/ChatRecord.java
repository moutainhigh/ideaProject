/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      sunyujie
 * @version:     V1.0 
 * @Date:        2018-04-27 18:12:14 
 */
package hry.chat.chat.model;

import static javax.persistence.GenerationType.IDENTITY;
import hry.core.mvc.model.BaseModel;


import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> ChatRecord </p>
 * @author:         sunyujie
 * @Date :          2018-04-27 18:12:14  
 */
@Table(name="chat_record")
public class ChatRecord extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //id
	
	@Column(name= "customerId")
	private Long customerId;  //用户id
	
	@Column(name= "userName")
	private String userName;  //登录名
	
	@Column(name= "content")
	private String content;  //聊天内容
	
	@Column(name= "saasid")
	private String saasid;  //saasid
	
	
	
	
	/**
	 * <p>id</p>
	 * @author:  sunyujie
	 * @return:  Long 
	 * @Date :   2018-04-27 18:12:14    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>id</p>
	 * @author:  sunyujie
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-04-27 18:12:14   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>用户id</p>
	 * @author:  sunyujie
	 * @return:  Long 
	 * @Date :   2018-04-27 18:12:14    
	 */
	public Long getCustomerId() {
		return customerId;
	}
	
	/**
	 * <p>用户id</p>
	 * @author:  sunyujie
	 * @param:   @param customerId
	 * @return:  void 
	 * @Date :   2018-04-27 18:12:14   
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	
	/**
	 * <p>登录名</p>
	 * @author:  sunyujie
	 * @return:  String 
	 * @Date :   2018-04-27 18:12:14    
	 */
	public String getUserName() {
		return userName;
	}
	
	/**
	 * <p>登录名</p>
	 * @author:  sunyujie
	 * @param:   @param userName
	 * @return:  void 
	 * @Date :   2018-04-27 18:12:14   
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
	/**
	 * <p>聊天内容</p>
	 * @author:  sunyujie
	 * @return:  String 
	 * @Date :   2018-04-27 18:12:14    
	 */
	public String getContent() {
		return content;
	}
	
	/**
	 * <p>聊天内容</p>
	 * @author:  sunyujie
	 * @param:   @param content
	 * @return:  void 
	 * @Date :   2018-04-27 18:12:14   
	 */
	public void setContent(String content) {
		this.content = content;
	}
	
	
	/**
	 * <p>saasid</p>
	 * @author:  sunyujie
	 * @return:  String 
	 * @Date :   2018-04-27 18:12:14    
	 */
	public String getSaasid() {
		return saasid;
	}
	
	/**
	 * <p>saasid</p>
	 * @author:  sunyujie
	 * @param:   @param saasid
	 * @return:  void 
	 * @Date :   2018-04-27 18:12:14   
	 */
	public void setSaasid(String saasid) {
		this.saasid = saasid;
	}
	
	

}
