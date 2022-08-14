/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      niuy
 * @version:     V1.0 
 * @Date:        2018-04-25 16:59:26 
 */
package hry.web.AppMessageTemplate.model;

import static javax.persistence.GenerationType.IDENTITY;
import hry.core.mvc.model.BaseModel;


import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JacksonInject;

/**
 * <p> AppMessageTemplate </p>
 * @author:         niuy
 * @Date :          2018-04-25 16:59:26  
 */
@Table(name="app_message_template")
public class AppMessageTemplate extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //id
	
	@Column(name= "templateId")
	private Long templateId;  //templateId
	
	@Column(name= "title")
	private String title;  //title
	
	@Column(name= "content")
	private String content;  //content
	
	
	
	
	/**
	 * <p>id</p>
	 * @author:  niuy
	 * @return:  Long 
	 * @Date :   2018-04-25 16:59:26    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>id</p>
	 * @author:  niuy
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-04-25 16:59:26   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>templateId</p>
	 * @author:  niuy
	 * @return:  Long 
	 * @Date :   2018-04-25 16:59:26    
	 */
	public Long getTemplateId() {
		return templateId;
	}
	
	/**
	 * <p>templateId</p>
	 * @author:  niuy
	 * @param:   @param templateId
	 * @return:  void 
	 * @Date :   2018-04-25 16:59:26   
	 */
	public void setTemplateId(Long templateId) {
		this.templateId = templateId;
	}
	
	
	/**
	 * <p>title</p>
	 * @author:  niuy
	 * @return:  String 
	 * @Date :   2018-04-25 16:59:26    
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * <p>title</p>
	 * @author:  niuy
	 * @param:   @param title
	 * @return:  void 
	 * @Date :   2018-04-25 16:59:26   
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	
	/**
	 * <p>content</p>
	 * @author:  niuy
	 * @return:  String 
	 * @Date :   2018-04-25 16:59:26    
	 */
	public String getContent() {
		return content;
	}
	
	/**
	 * <p>content</p>
	 * @author:  niuy
	 * @param:   @param content
	 * @return:  void 
	 * @Date :   2018-04-25 16:59:26   
	 */
	public void setContent(String content) {
		this.content = content;
	}
	
	

}
