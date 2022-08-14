/**
 * Copyright:   
 * @author:      sunyujie
 * @version:     V1.0 
 * @Date:        2018-06-28 16:29:34 
 */
package hry.admin.web.model;


import hry.bean.BaseModel;


import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> chatRecord </p>
 * @author:         sunyujie
 * @Date :          2018-06-28 16:29:34  
 */
@Table(name="chat_record")
public class chatRecord extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "customerId")
	private Long customerId;  //
	
	@Column(name= "userName")
	private String userName;  //
	
	@Column(name= "content")
	private String content;  //
	
	
	
	
	/**
	 * <p></p>
	 * @author:  sunyujie
	 * @return:  Long 
	 * @Date :   2018-06-28 16:29:34    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  sunyujie
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-06-28 16:29:34   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p></p>
	 * @author:  sunyujie
	 * @return:  Long 
	 * @Date :   2018-06-28 16:29:34    
	 */
	public Long getCustomerId() {
		return customerId;
	}
	
	/**
	 * <p></p>
	 * @author:  sunyujie
	 * @param:   @param customerId
	 * @return:  void 
	 * @Date :   2018-06-28 16:29:34   
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	
	/**
	 * <p></p>
	 * @author:  sunyujie
	 * @return:  String 
	 * @Date :   2018-06-28 16:29:34    
	 */
	public String getUserName() {
		return userName;
	}
	
	/**
	 * <p></p>
	 * @author:  sunyujie
	 * @param:   @param userName
	 * @return:  void 
	 * @Date :   2018-06-28 16:29:34   
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
	/**
	 * <p></p>
	 * @author:  sunyujie
	 * @return:  String 
	 * @Date :   2018-06-28 16:29:34    
	 */
	public String getContent() {
		return content;
	}
	
	/**
	 * <p></p>
	 * @author:  sunyujie
	 * @param:   @param content
	 * @return:  void 
	 * @Date :   2018-06-28 16:29:34   
	 */
	public void setContent(String content) {
		this.content = content;
	}
	
	

}
