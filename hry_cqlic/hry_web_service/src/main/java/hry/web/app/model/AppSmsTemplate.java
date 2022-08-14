/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2018-10-25 17:56:24 
 */
package hry.web.app.model;


import hry.bean.BaseModel;

import javax.persistence.*;

/**
 * <p> AppSmsTemplate </p>
 * @author:         zhouming
 * @Date :          2018-10-25 17:56:24  
 */
@Table(name="app_sms_template")
public class AppSmsTemplate extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //主键
	
	@Column(name= "mkey")
	private String mkey;  //模板KEY
	
	@Column(name= "name")
	private String name;  //模板名称
	
	@Column(name= "describes")
	private String describes;  //模板内容
	
	@Column(name= "remark")
	private String remark;  //模板备注
	
	@Column(name= "isOpen")
	private Integer isOpen;  //是否开启（0表示否 1表示是）
	
	@Column(name= "saasId")
	private String saasId;  //
	
	@Column(name= "messageCategory")
	private String messageCategory;  //信息类别-存放系统语种的值
	
	
	
	
	/**
	 * <p>主键</p>
	 * @author:  zhouming
	 * @return:  Long 
	 * @Date :   2018-10-25 17:56:24    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>主键</p>
	 * @author:  zhouming
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-10-25 17:56:24   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>模板KEY</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2018-10-25 17:56:24    
	 */
	public String getMkey() {
		return mkey;
	}
	
	/**
	 * <p>模板KEY</p>
	 * @author:  zhouming
	 * @param:   @param mkey
	 * @return:  void 
	 * @Date :   2018-10-25 17:56:24   
	 */
	public void setMkey(String mkey) {
		this.mkey = mkey;
	}
	
	
	/**
	 * <p>模板名称</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2018-10-25 17:56:24    
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * <p>模板名称</p>
	 * @author:  zhouming
	 * @param:   @param name
	 * @return:  void 
	 * @Date :   2018-10-25 17:56:24   
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	
	/**
	 * <p>模板内容</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2018-10-25 17:56:24    
	 */
	public String getDescribes() {
		return describes;
	}
	
	/**
	 * <p>模板内容</p>
	 * @author:  zhouming
	 * @param:   @param describes
	 * @return:  void 
	 * @Date :   2018-10-25 17:56:24   
	 */
	public void setDescribes(String describes) {
		this.describes = describes;
	}
	
	
	/**
	 * <p>模板备注</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2018-10-25 17:56:24    
	 */
	public String getRemark() {
		return remark;
	}
	
	/**
	 * <p>模板备注</p>
	 * @author:  zhouming
	 * @param:   @param remark
	 * @return:  void 
	 * @Date :   2018-10-25 17:56:24   
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	/**
	 * <p>是否开启（0表示否 1表示是）</p>
	 * @author:  zhouming
	 * @return:  Integer 
	 * @Date :   2018-10-25 17:56:24    
	 */
	public Integer getIsOpen() {
		return isOpen;
	}
	
	/**
	 * <p>是否开启（0表示否 1表示是）</p>
	 * @author:  zhouming
	 * @param:   @param isOpen
	 * @return:  void 
	 * @Date :   2018-10-25 17:56:24   
	 */
	public void setIsOpen(Integer isOpen) {
		this.isOpen = isOpen;
	}
	
	
	/**
	 * <p></p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2018-10-25 17:56:24    
	 */
	public String getSaasId() {
		return saasId;
	}
	
	/**
	 * <p></p>
	 * @author:  zhouming
	 * @param:   @param saasId
	 * @return:  void 
	 * @Date :   2018-10-25 17:56:24   
	 */
	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}
	
	
	/**
	 * <p>信息类别-存放系统语种的值</p>
	 * @author:  zhouming
	 * @return:  String 
	 * @Date :   2018-10-25 17:56:24    
	 */
	public String getMessageCategory() {
		return messageCategory;
	}
	
	/**
	 * <p>信息类别-存放系统语种的值</p>
	 * @author:  zhouming
	 * @param:   @param messageCategory
	 * @return:  void 
	 * @Date :   2018-10-25 17:56:24   
	 */
	public void setMessageCategory(String messageCategory) {
		this.messageCategory = messageCategory;
	}
	
	

}
