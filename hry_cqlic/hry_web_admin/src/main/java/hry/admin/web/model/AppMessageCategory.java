/**
 * Copyright:   
 * @author:      liuchenghui
 * @version:     V1.0 
 * @Date:        2018-07-05 10:20:33 
 */
package hry.admin.web.model;


import hry.bean.BaseModel;


import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> AppMessageCategory </p>
 * @author:         liuchenghui
 * @Date :          2018-07-05 10:20:33  
 */
@Table(name="app_message_category")
public class AppMessageCategory extends BaseModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "name")
	private String name;  //
	
	@Column(name= "describes")
	private String describes;  //
	
	@Column(name= "sort")
	private Integer sort;  //
	
	@Column(name= "state")
	private Integer state;  //
	
	@Column(name= "isOpen")
	private Integer isOpen;  //
	
	@Column(name= "messageType")
	private Integer messageType;  //
	
	@Column(name= "saasId")
	private String saasId;  //
	
	@Column(name= "keywords")
	private String keywords;  //

	@Column(name= "messageCategory")
	private String messageCategory; // 语种简称

	@Column(name= "triggerPoint")
	private String trigger; // 触发点key

	@Column(name= "triggerPointLan")
	private String triggerPointLan; // 触发点名称

	public String getTriggerPointLan() {
		return triggerPointLan;
	}

	public void setTriggerPointLan(String triggerPointLan) {
		this.triggerPointLan = triggerPointLan;
	}

	public String getMessageCategory () {
		return messageCategory;
	}

	public void setMessageCategory (String messageCategory) {
		this.messageCategory = messageCategory;
	}

	public String getTrigger() {
		return trigger;
	}

	public void setTrigger(String trigger) {
		this.trigger = trigger;
	}

	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @return:  Long 
	 * @Date :   2018-07-05 10:20:33    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-07-05 10:20:33   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @return:  String 
	 * @Date :   2018-07-05 10:20:33    
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @param:   @param name
	 * @return:  void 
	 * @Date :   2018-07-05 10:20:33   
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @return:  String 
	 * @Date :   2018-07-05 10:20:33    
	 */
	public String getDescribes() {
		return describes;
	}
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @param:   @param describes
	 * @return:  void 
	 * @Date :   2018-07-05 10:20:33   
	 */
	public void setDescribes(String describes) {
		this.describes = describes;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @return:  Integer 
	 * @Date :   2018-07-05 10:20:33    
	 */
	public Integer getSort() {
		return sort;
	}
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @param:   @param sort
	 * @return:  void 
	 * @Date :   2018-07-05 10:20:33   
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @return:  Integer 
	 * @Date :   2018-07-05 10:20:33    
	 */
	public Integer getState() {
		return state;
	}
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @param:   @param state
	 * @return:  void 
	 * @Date :   2018-07-05 10:20:33   
	 */
	public void setState(Integer state) {
		this.state = state;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @return:  Integer 
	 * @Date :   2018-07-05 10:20:33    
	 */
	public Integer getIsOpen() {
		return isOpen;
	}
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @param:   @param isOpen
	 * @return:  void 
	 * @Date :   2018-07-05 10:20:33   
	 */
	public void setIsOpen(Integer isOpen) {
		this.isOpen = isOpen;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @return:  Integer 
	 * @Date :   2018-07-05 10:20:33    
	 */
	public Integer getMessageType() {
		return messageType;
	}
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @param:   @param messageType
	 * @return:  void 
	 * @Date :   2018-07-05 10:20:33   
	 */
	public void setMessageType(Integer messageType) {
		this.messageType = messageType;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @return:  String 
	 * @Date :   2018-07-05 10:20:33    
	 */
	public String getSaasId() {
		return saasId;
	}
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @param:   @param saasId
	 * @return:  void 
	 * @Date :   2018-07-05 10:20:33   
	 */
	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @return:  String 
	 * @Date :   2018-07-05 10:20:33    
	 */
	public String getKeywords() {
		return keywords;
	}
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @param:   @param keywords
	 * @return:  void 
	 * @Date :   2018-07-05 10:20:33   
	 */
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	
	

}
