/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      tianpy
 * @version:     V1.0 
 * @Date:        2018-01-08 20:43:31 
 */
package hry.web.mail.model;

import hry.core.mvc.model.BaseModel;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * <p> Mail </p>
 * @author:         tianpy
 * @Date :          2018-01-08 20:43:31  
 */
@Table(name="mail")
public class Mail extends BaseModel implements Serializable{
	
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //id
	
	@Column(name= "title")
	private String title;  //title
	
	@Column(name= "content")
	private String content;  //content
	
	@Column(name= "address")
	private String address;  //address
	
	@Column(name= "errorCode")
	private String errorCode;  //errorCode
	
	@Column(name= "errorContent")
	private String errorContent;  //errorContent
	
	
	
	
	/**
	 * <p>id</p>
	 * @author:  tianpy
	 * @return:  Long 
	 * @Date :   2018-01-08 20:43:31    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>id</p>
	 * @author:  tianpy
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-01-08 20:43:31   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>title</p>
	 * @author:  tianpy
	 * @return:  String 
	 * @Date :   2018-01-08 20:43:31    
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * <p>title</p>
	 * @author:  tianpy
	 * @param:   @param title
	 * @return:  void 
	 * @Date :   2018-01-08 20:43:31   
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	
	/**
	 * <p>content</p>
	 * @author:  tianpy
	 * @return:  String 
	 * @Date :   2018-01-08 20:43:31    
	 */
	public String getContent() {
		return content;
	}
	
	/**
	 * <p>content</p>
	 * @author:  tianpy
	 * @param:   @param content
	 * @return:  void 
	 * @Date :   2018-01-08 20:43:31   
	 */
	public void setContent(String content) {
		this.content = content;
	}
	
	
	/**
	 * <p>address</p>
	 * @author:  tianpy
	 * @return:  String 
	 * @Date :   2018-01-08 20:43:31    
	 */
	public String getAddress() {
		return address;
	}
	
	/**
	 * <p>address</p>
	 * @author:  tianpy
	 * @param:   @param address
	 * @return:  void 
	 * @Date :   2018-01-08 20:43:31   
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	
	
	/**
	 * <p>errorCode</p>
	 * @author:  tianpy
	 * @return:  String 
	 * @Date :   2018-01-08 20:43:31    
	 */
	public String getErrorCode() {
		return errorCode;
	}
	
	/**
	 * <p>errorCode</p>
	 * @author:  tianpy
	 * @param:   @param errorCode
	 * @return:  void 
	 * @Date :   2018-01-08 20:43:31   
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	
	
	/**
	 * <p>errorContent</p>
	 * @author:  tianpy
	 * @return:  String 
	 * @Date :   2018-01-08 20:43:31    
	 */
	public String getErrorContent() {
		return errorContent;
	}
	
	/**
	 * <p>errorContent</p>
	 * @author:  tianpy
	 * @param:   @param errorContent
	 * @return:  void 
	 * @Date :   2018-01-08 20:43:31   
	 */
	public void setErrorContent(String errorContent) {
		this.errorContent = errorContent;
	}
	
	

}
