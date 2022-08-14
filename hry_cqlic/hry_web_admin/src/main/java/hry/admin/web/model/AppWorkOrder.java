/**
 * Copyright:   
 * @author:      sunyujie
 * @version:     V1.0 
 * @Date:        2018-07-02 09:48:18 
 */
package hry.admin.web.model;


import hry.bean.BaseModel;

import java.util.Date;

import javax.persistence.*;

/**
 * <p> appWorkOrder </p>
 * @author:         sunyujie
 * @Date :          2018-07-02 09:48:18  
 */
@Table(name="app_workorder")
public class AppWorkOrder extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "customerId")
	private Long customerId;  //
	
	@Column(name= "workOrderNo")
	private String workOrderNo;  //
	
	@Column(name= "categoryId")
	private Long categoryId;  //
	
	@Column(name= "categoryName")
	private String categoryName;  //
	
	@Column(name= "workContent")
	private String workContent;  //
	
	@Column(name= "state")
	private Integer state;  //
	
	@Column(name= "replyMode")
	private Integer replyMode;  //
	
	@Column(name= "sort")
	private Integer sort;  //
	
	@Column(name= "replyContent")
	private String replyContent;  //
	
	@Column(name= "language")
	private Integer language;  //
	
	@Column(name= "processTime")
	private Date processTime;  //
	
	@Column(name= "saasId")
	private String saasId;  //
	
	@Column(name= "languageDic")
	private String languageDic;  //
	
	@Transient
	private String email; // 用户邮箱

	@Transient
	private String mobilePhone; // 用户手机号


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	/**
	 * <p></p>
	 * @author:  sunyujie
	 * @return:  Long 
	 * @Date :   2018-07-02 09:48:18    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  sunyujie
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-07-02 09:48:18   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p></p>
	 * @author:  sunyujie
	 * @return:  Long 
	 * @Date :   2018-07-02 09:48:18    
	 */
	public Long getCustomerId() {
		return customerId;
	}
	
	/**
	 * <p></p>
	 * @author:  sunyujie
	 * @param:   @param customerId
	 * @return:  void 
	 * @Date :   2018-07-02 09:48:18   
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	
	/**
	 * <p></p>
	 * @author:  sunyujie
	 * @return:  String 
	 * @Date :   2018-07-02 09:48:18    
	 */
	public String getWorkOrderNo() {
		return workOrderNo;
	}
	
	/**
	 * <p></p>
	 * @author:  sunyujie
	 * @param:   @param workOrderNo
	 * @return:  void 
	 * @Date :   2018-07-02 09:48:18   
	 */
	public void setWorkOrderNo(String workOrderNo) {
		this.workOrderNo = workOrderNo;
	}
	
	
	/**
	 * <p></p>
	 * @author:  sunyujie
	 * @return:  Long 
	 * @Date :   2018-07-02 09:48:18    
	 */
	public Long getCategoryId() {
		return categoryId;
	}
	
	/**
	 * <p></p>
	 * @author:  sunyujie
	 * @param:   @param categoryId
	 * @return:  void 
	 * @Date :   2018-07-02 09:48:18   
	 */
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	
	
	/**
	 * <p></p>
	 * @author:  sunyujie
	 * @return:  String 
	 * @Date :   2018-07-02 09:48:18    
	 */
	public String getCategoryName() {
		return categoryName;
	}
	
	/**
	 * <p></p>
	 * @author:  sunyujie
	 * @param:   @param categoryName
	 * @return:  void 
	 * @Date :   2018-07-02 09:48:18   
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	
	/**
	 * <p></p>
	 * @author:  sunyujie
	 * @return:  String 
	 * @Date :   2018-07-02 09:48:18    
	 */
	public String getWorkContent() {
		return workContent;
	}
	
	/**
	 * <p></p>
	 * @author:  sunyujie
	 * @param:   @param workContent
	 * @return:  void 
	 * @Date :   2018-07-02 09:48:18   
	 */
	public void setWorkContent(String workContent) {
		this.workContent = workContent;
	}
	
	
	/**
	 * <p></p>
	 * @author:  sunyujie
	 * @return:  Integer 
	 * @Date :   2018-07-02 09:48:18    
	 */
	public Integer getState() {
		return state;
	}
	
	/**
	 * <p></p>
	 * @author:  sunyujie
	 * @param:   @param state
	 * @return:  void 
	 * @Date :   2018-07-02 09:48:18   
	 */
	public void setState(Integer state) {
		this.state = state;
	}
	
	
	/**
	 * <p></p>
	 * @author:  sunyujie
	 * @return:  Integer 
	 * @Date :   2018-07-02 09:48:18    
	 */
	public Integer getReplyMode() {
		return replyMode;
	}
	
	/**
	 * <p></p>
	 * @author:  sunyujie
	 * @param:   @param replyMode
	 * @return:  void 
	 * @Date :   2018-07-02 09:48:18   
	 */
	public void setReplyMode(Integer replyMode) {
		this.replyMode = replyMode;
	}
	
	
	/**
	 * <p></p>
	 * @author:  sunyujie
	 * @return:  Integer 
	 * @Date :   2018-07-02 09:48:18    
	 */
	public Integer getSort() {
		return sort;
	}
	
	/**
	 * <p></p>
	 * @author:  sunyujie
	 * @param:   @param sort
	 * @return:  void 
	 * @Date :   2018-07-02 09:48:18   
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	
	/**
	 * <p></p>
	 * @author:  sunyujie
	 * @return:  String 
	 * @Date :   2018-07-02 09:48:18    
	 */
	public String getReplyContent() {
		return replyContent;
	}
	
	/**
	 * <p></p>
	 * @author:  sunyujie
	 * @param:   @param replyContent
	 * @return:  void 
	 * @Date :   2018-07-02 09:48:18   
	 */
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
	
	
	/**
	 * <p></p>
	 * @author:  sunyujie
	 * @return:  Integer 
	 * @Date :   2018-07-02 09:48:18    
	 */
	public Integer getLanguage() {
		return language;
	}
	
	/**
	 * <p></p>
	 * @author:  sunyujie
	 * @param:   @param language
	 * @return:  void 
	 * @Date :   2018-07-02 09:48:18   
	 */
	public void setLanguage(Integer language) {
		this.language = language;
	}
	
	
	/**
	 * <p></p>
	 * @author:  sunyujie
	 * @return:  Date 
	 * @Date :   2018-07-02 09:48:18    
	 */
	public Date getProcessTime() {
		return processTime;
	}
	
	/**
	 * <p></p>
	 * @author:  sunyujie
	 * @param:   @param processTime
	 * @return:  void 
	 * @Date :   2018-07-02 09:48:18   
	 */
	public void setProcessTime(Date processTime) {
		this.processTime = processTime;
	}
	
	
	/**
	 * <p></p>
	 * @author:  sunyujie
	 * @return:  String 
	 * @Date :   2018-07-02 09:48:18    
	 */
	public String getSaasId() {
		return saasId;
	}
	
	/**
	 * <p></p>
	 * @author:  sunyujie
	 * @param:   @param saasId
	 * @return:  void 
	 * @Date :   2018-07-02 09:48:18   
	 */
	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}
	
	
	/**
	 * <p></p>
	 * @author:  sunyujie
	 * @return:  String 
	 * @Date :   2018-07-02 09:48:18    
	 */
	public String getLanguageDic() {
		return languageDic;
	}
	
	/**
	 * <p></p>
	 * @author:  sunyujie
	 * @param:   @param languageDic
	 * @return:  void 
	 * @Date :   2018-07-02 09:48:18   
	 */
	public void setLanguageDic(String languageDic) {
		this.languageDic = languageDic;
	}
	
	

}
