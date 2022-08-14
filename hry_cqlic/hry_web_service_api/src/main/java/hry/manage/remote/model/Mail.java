package hry.manage.remote.model;

import java.io.Serializable;

public class Mail implements Serializable{


	
	
	private Long id;  //id
	
	private String title;  //title
	
	private String content;  //content
	
	private String address;  //address
	
	private String errorCode;  //errorCode
	
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
