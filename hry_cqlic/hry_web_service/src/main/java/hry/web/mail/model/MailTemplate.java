/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      sunyujie
 * @version:     V1.0 
 * @Date:        2018-06-05 14:54:52 
 */
package hry.web.mail.model;

import static javax.persistence.GenerationType.IDENTITY;
import hry.core.mvc.model.BaseModel;


import javax.persistence.*;

/**
 * <p> MailTemplate </p>
 * @author:         sunyujie
 * @Date :          2018-06-05 14:54:52  
 */
@Table(name="mail_template")
public class MailTemplate extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //id
	
	@Column(name= "tempName")
	private String tempName;  //模板名称
	
	@Column(name= "tempKey")
	private String tempKey;  //模板key
	
	@Column(name= "tempContent")
	private String tempContent;  //模板内容
	
	@Column(name= "remark")
	private String remark;  //模板备注
	
	@Column(name= "tempStatus")
	private Integer tempStatus;  //启用状态   1开启  0 关闭
	
	@Column(name= "mailConfigId")
	private Long mailConfigId;  //邮箱账号id
	
	@Column(name= "language")
	private Integer language;  //语言   0 中文  1英文
	 @Column(name= "languageDic")
	private String languageDic;  //语言  取数据字典中的key进行保存
	@Transient
	private  String customName;
	@Transient
	private  String emailUser;
	@Transient
	private  String itemName;//数据字典中的name

	
	/**
	 * <p>id</p>
	 * @author:  sunyujie
	 * @return:  Long 
	 * @Date :   2018-06-05 14:54:52    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p>id</p>
	 * @author:  sunyujie
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-06-05 14:54:52   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>模板名称</p>
	 * @author:  sunyujie
	 * @return:  String 
	 * @Date :   2018-06-05 14:54:52    
	 */
	public String getTempName() {
		return tempName;
	}
	
	/**
	 * <p>模板名称</p>
	 * @author:  sunyujie
	 * @param:   @param tempName
	 * @return:  void 
	 * @Date :   2018-06-05 14:54:52   
	 */
	public void setTempName(String tempName) {
		this.tempName = tempName;
	}
	
	
	/**
	 * <p>模板key</p>
	 * @author:  sunyujie
	 * @return:  String 
	 * @Date :   2018-06-05 14:54:52    
	 */
	public String getTempKey() {
		return tempKey;
	}
	
	/**
	 * <p>模板key</p>
	 * @author:  sunyujie
	 * @param:   @param tempKey
	 * @return:  void 
	 * @Date :   2018-06-05 14:54:52   
	 */
	public void setTempKey(String tempKey) {
		this.tempKey = tempKey;
	}
	
	
	/**
	 * <p>模板内容</p>
	 * @author:  sunyujie
	 * @return:  String 
	 * @Date :   2018-06-05 14:54:52    
	 */
	public String getTempContent() {
		return tempContent;
	}
	
	/**
	 * <p>模板内容</p>
	 * @author:  sunyujie
	 * @param:   @param tempContent
	 * @return:  void 
	 * @Date :   2018-06-05 14:54:52   
	 */
	public void setTempContent(String tempContent) {
		this.tempContent = tempContent;
	}
	
	
	/**
	 * <p>模板备注</p>
	 * @author:  sunyujie
	 * @return:  String 
	 * @Date :   2018-06-05 14:54:52    
	 */
	public String getRemark() {
		return remark;
	}
	
	/**
	 * <p>模板备注</p>
	 * @author:  sunyujie
	 * @param:   @param remark
	 * @return:  void 
	 * @Date :   2018-06-05 14:54:52   
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	/**
	 * <p>启用状态   1开启  0 关闭</p>
	 * @author:  sunyujie
	 * @return:  Integer 
	 * @Date :   2018-06-05 14:54:52    
	 */
	public Integer getTempStatus() {
		return tempStatus;
	}
	
	/**
	 * <p>启用状态   1开启  0 关闭</p>
	 * @author:  sunyujie
	 * @param:   @param tempStatus
	 * @return:  void 
	 * @Date :   2018-06-05 14:54:52   
	 */
	public void setTempStatus(Integer tempStatus) {
		this.tempStatus = tempStatus;
	}
	
	
	/**
	 * <p>邮箱账号id</p>
	 * @author:  sunyujie
	 * @return:  Long 
	 * @Date :   2018-06-05 14:54:52    
	 */
	public Long getMailConfigId() {
		return mailConfigId;
	}
	
	/**
	 * <p>邮箱账号id</p>
	 * @author:  sunyujie
	 * @param:   @param mailConfigId
	 * @return:  void 
	 * @Date :   2018-06-05 14:54:52   
	 */
	public void setMailConfigId(Long mailConfigId) {
		this.mailConfigId = mailConfigId;
	}
	
	
	/**
	 * <p>语言   0 中文  1英文</p>
	 * @author:  sunyujie
	 * @return:  Integer 
	 * @Date :   2018-06-05 14:54:52    
	 */
	public Integer getLanguage() {
		return language;
	}
	
	/**
	 * <p>语言   0 中文  1英文</p>
	 * @author:  sunyujie
	 * @param:   @param language
	 * @return:  void 
	 * @Date :   2018-06-05 14:54:52   
	 */
	public void setLanguage(Integer language) {
		this.language = language;
	}

	public String getCustomName() {
		return customName;
	}

	public void setCustomName(String customName) {
		this.customName = customName;
	}

	public String getEmailUser() {
		return emailUser;
	}

	public void setEmailUser(String emailUser) {
		this.emailUser = emailUser;
	}

	public String getLanguageDic() {
		return languageDic;
	}

	public void setLanguageDic(String languageDic) {
		this.languageDic = languageDic;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
}
