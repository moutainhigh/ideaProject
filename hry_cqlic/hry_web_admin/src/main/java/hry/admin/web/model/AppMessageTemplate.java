/**
 * Copyright:   
 * @author:      liuchenghui
 * @version:     V1.0 
 * @Date:        2018-07-05 10:23:25 
 */
package hry.admin.web.model;


import hry.bean.BaseModel;


import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> AppMessageTemplate </p>
 * @author:         liuchenghui
 * @Date :          2018-07-05 10:23:25  
 */
@Table(name="app_message_template")
public class AppMessageTemplate extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "templateId")
	private Long templateId;  //
	
	@Column(name= "title")
	private String title;  //
	
	@Column(name= "content")
	private String content;  //
	
	@Column(name= "saasId")
	private String saasId;  //
	
	
	
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @return:  Long 
	 * @Date :   2018-07-05 10:23:25    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-07-05 10:23:25   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @return:  Long 
	 * @Date :   2018-07-05 10:23:25    
	 */
	public Long getTemplateId() {
		return templateId;
	}
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @param:   @param templateId
	 * @return:  void 
	 * @Date :   2018-07-05 10:23:25   
	 */
	public void setTemplateId(Long templateId) {
		this.templateId = templateId;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @return:  String 
	 * @Date :   2018-07-05 10:23:25    
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @param:   @param title
	 * @return:  void 
	 * @Date :   2018-07-05 10:23:25   
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @return:  String 
	 * @Date :   2018-07-05 10:23:25    
	 */
	public String getContent() {
		return content;
	}
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @param:   @param content
	 * @return:  void 
	 * @Date :   2018-07-05 10:23:25   
	 */
	public void setContent(String content) {
		this.content = content;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @return:  String 
	 * @Date :   2018-07-05 10:23:25    
	 */
	public String getSaasId() {
		return saasId;
	}
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @param:   @param saasId
	 * @return:  void 
	 * @Date :   2018-07-05 10:23:25   
	 */
	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}
	
	

}
