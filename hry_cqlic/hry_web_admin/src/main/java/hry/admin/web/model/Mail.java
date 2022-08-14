/**
 * Copyright:   
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-20 14:41:28 
 */
package hry.admin.web.model;


import hry.bean.BaseModel;


import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> Mail </p>
 * @author:         liushilei
 * @Date :          2018-06-20 14:41:28  
 */
@Table(name="mail")
public class Mail extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "saasId")
	private String saasId;  //
	
	@Column(name= "title")
	private String title;  //
	
	@Column(name= "content")
	private String content;  //
	
	@Column(name= "address")
	private String address;  //
	
	@Column(name= "errorCode")
	private String errorCode;  //
	
	@Column(name= "errorContent")
	private String errorContent;  //
	
	
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Long 
	 * @Date :   2018-06-20 14:41:28    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-06-20 14:41:28   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-20 14:41:28    
	 */
	public String getSaasId() {
		return saasId;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param saasId
	 * @return:  void 
	 * @Date :   2018-06-20 14:41:28   
	 */
	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-20 14:41:28    
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param title
	 * @return:  void 
	 * @Date :   2018-06-20 14:41:28   
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-20 14:41:28    
	 */
	public String getContent() {
		return content;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param content
	 * @return:  void 
	 * @Date :   2018-06-20 14:41:28   
	 */
	public void setContent(String content) {
		this.content = content;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-20 14:41:28    
	 */
	public String getAddress() {
		return address;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param address
	 * @return:  void 
	 * @Date :   2018-06-20 14:41:28   
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-20 14:41:28    
	 */
	public String getErrorCode() {
		return errorCode;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param errorCode
	 * @return:  void 
	 * @Date :   2018-06-20 14:41:28   
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-20 14:41:28    
	 */
	public String getErrorContent() {
		return errorContent;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param errorContent
	 * @return:  void 
	 * @Date :   2018-06-20 14:41:28   
	 */
	public void setErrorContent(String errorContent) {
		this.errorContent = errorContent;
	}
	
	

}
