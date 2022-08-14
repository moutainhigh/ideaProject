/**
 * Copyright:   
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-20 15:40:16 
 */
package hry.admin.web.model;


import hry.bean.BaseModel;


import javax.persistence.*;

/**
 * <p> MailTemplate </p>
 * @author:         liushilei
 * @Date :          2018-06-20 15:40:16  
 */
@Table(name="mail_template")
public class MailTemplate extends BaseModel {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //

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
	private Integer language;  //  废弃字段

	@Column(name= "saasId")
	private String saasId;  //

	@Column(name= "languageDic")
	private String languageDic;  //语言  取数据字典中的key进行保存
	@Transient
	private  String customName;
	@Transient
	private  String emailUser;
	@Transient
	private  String itemName;//数据字典中的name
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Long 
	 * @Date :   2018-06-20 15:40:16    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-06-20 15:40:16   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-20 15:40:16    
	 */
	public String getTempName() {
		return tempName;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param tempName
	 * @return:  void 
	 * @Date :   2018-06-20 15:40:16   
	 */
	public void setTempName(String tempName) {
		this.tempName = tempName;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-20 15:40:16    
	 */
	public String getTempKey() {
		return tempKey;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param tempKey
	 * @return:  void 
	 * @Date :   2018-06-20 15:40:16   
	 */
	public void setTempKey(String tempKey) {
		this.tempKey = tempKey;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-20 15:40:16    
	 */
	public String getTempContent() {
		return tempContent;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param tempContent
	 * @return:  void 
	 * @Date :   2018-06-20 15:40:16   
	 */
	public void setTempContent(String tempContent) {
		this.tempContent = tempContent;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-20 15:40:16    
	 */
	public String getRemark() {
		return remark;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param remark
	 * @return:  void 
	 * @Date :   2018-06-20 15:40:16   
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Integer 
	 * @Date :   2018-06-20 15:40:16    
	 */
	public Integer getTempStatus() {
		return tempStatus;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param tempStatus
	 * @return:  void 
	 * @Date :   2018-06-20 15:40:16   
	 */
	public void setTempStatus(Integer tempStatus) {
		this.tempStatus = tempStatus;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Long 
	 * @Date :   2018-06-20 15:40:16    
	 */
	public Long getMailConfigId() {
		return mailConfigId;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param mailConfigId
	 * @return:  void 
	 * @Date :   2018-06-20 15:40:16   
	 */
	public void setMailConfigId(Long mailConfigId) {
		this.mailConfigId = mailConfigId;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Integer 
	 * @Date :   2018-06-20 15:40:16    
	 */
	public Integer getLanguage() {
		return language;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param language
	 * @return:  void 
	 * @Date :   2018-06-20 15:40:16   
	 */
	public void setLanguage(Integer language) {
		this.language = language;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-20 15:40:16    
	 */
	public String getSaasId() {
		return saasId;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param saasId
	 * @return:  void 
	 * @Date :   2018-06-20 15:40:16   
	 */
	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}

	public String getLanguageDic() {
		return languageDic;
	}

	public void setLanguageDic(String languageDic) {
		this.languageDic = languageDic;
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

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
}
