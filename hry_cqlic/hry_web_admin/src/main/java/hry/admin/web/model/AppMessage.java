/**
 * Copyright:   
 * @author:      liuchenghui
 * @version:     V1.0 
 * @Date:        2018-07-05 10:21:54 
 */
package hry.admin.web.model;


import hry.bean.BaseModel;

import javax.persistence.*;
import java.util.Date;

/**
 * <p> AppMessage </p>
 * @author:         liuchenghui
 * @Date :          2018-07-05 10:21:54  
 */
@Table(name="app_message")
public class AppMessage extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "content")
	private String content;  //
	
	@Column(name= "categoryName")
	private String categoryName;  //
	
	@Column(name= "categoryId")
	private Long categoryId;  //
	
	@Column(name= "title")
	private String title;  //
	
	@Column(name= "titleImage")
	private String titleImage;  //
	
	@Column(name= "isSend")
	private Integer isSend;  //'是否已发送(0 : 表示没有    1  表示已发送)
	
	@Column(name= "isAll")
	private Integer isAll;  //
	
	@Column(name= "operator")
	private String operator;  //
	
	@Column(name= "sortTitle")
	private String sortTitle;  //
	
	@Column(name= "sendDate")
	private Date sendDate;


	@Column(name= "readDate")
	private Date readDate;

	@Column(name= "deleteDate")
	private Date deleteDate;  //
	
	@Column(name= "sendUserName")
	private String sendUserName;  //
	
	@Column(name= "messageType")
	private Integer messageType;

	@Column(name= "status")
	private Integer status;  //（1 已阅，0 未阅，2 用户删除，3 系统删除）

	@Column(name= "isAuto")
	private Integer isAuto;  //0 手动发送 ，1 自动发送

	@Column(name= "saasId")
	private String saasId;

	@Column(name= "receiver")
	private String receiver;  //接收人，app_person_info的id

	@Column(name= "messageCategory")
	private String messageCategory; // 语种简称

	@Transient // 不与数据库映射的字段
	private Object appPersonInfo;

    public Date getReadDate() {
        return readDate;
    }

    public void setReadDate(Date readDate) {
        this.readDate = readDate;
    }

    public Date getDeleteDate() {
        return deleteDate;
    }

    public void setDeleteDate(Date deleteDate) {
        this.deleteDate = deleteDate;
    }

    public Object getAppPersonInfo() {
		return appPersonInfo;
	}

	public void setAppPersonInfo(Object appPersonInfo) {
		this.appPersonInfo = appPersonInfo;
	}

	public String getMessageCategory () {
		return messageCategory;
	}

	public void setMessageCategory (String messageCategory) {
		this.messageCategory = messageCategory;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public Integer getIsAuto() {
		return isAuto;
	}

	public void setIsAuto(Integer isAuto) {
		this.isAuto = isAuto;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @return:  Long 
	 * @Date :   2018-07-05 10:21:54    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-07-05 10:21:54   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @return:  String 
	 * @Date :   2018-07-05 10:21:54    
	 */
	public String getContent() {
		return content;
	}
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @param:   @param content
	 * @return:  void 
	 * @Date :   2018-07-05 10:21:54   
	 */
	public void setContent(String content) {
		this.content = content;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @return:  String 
	 * @Date :   2018-07-05 10:21:54    
	 */
	public String getCategoryName() {
		return categoryName;
	}
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @param:   @param categoryName
	 * @return:  void 
	 * @Date :   2018-07-05 10:21:54   
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @return:  Long 
	 * @Date :   2018-07-05 10:21:54    
	 */
	public Long getCategoryId() {
		return categoryId;
	}
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @param:   @param categoryId
	 * @return:  void 
	 * @Date :   2018-07-05 10:21:54   
	 */
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @return:  String 
	 * @Date :   2018-07-05 10:21:54    
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @param:   @param title
	 * @return:  void 
	 * @Date :   2018-07-05 10:21:54   
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @return:  String 
	 * @Date :   2018-07-05 10:21:54    
	 */
	public String getTitleImage() {
		return titleImage;
	}
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @param:   @param titleImage
	 * @return:  void 
	 * @Date :   2018-07-05 10:21:54   
	 */
	public void setTitleImage(String titleImage) {
		this.titleImage = titleImage;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @return:  Integer 
	 * @Date :   2018-07-05 10:21:54    
	 */
	public Integer getIsSend() {
		return isSend;
	}
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @param:   @param isSend
	 * @return:  void 
	 * @Date :   2018-07-05 10:21:54   
	 */
	public void setIsSend(Integer isSend) {
		this.isSend = isSend;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @return:  Integer 
	 * @Date :   2018-07-05 10:21:54    
	 */
	public Integer getIsAll() {
		return isAll;
	}
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @param:   @param isAll
	 * @return:  void 
	 * @Date :   2018-07-05 10:21:54   
	 */
	public void setIsAll(Integer isAll) {
		this.isAll = isAll;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @return:  String 
	 * @Date :   2018-07-05 10:21:54    
	 */
	public String getOperator() {
		return operator;
	}
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @param:   @param operator
	 * @return:  void 
	 * @Date :   2018-07-05 10:21:54   
	 */
	public void setOperator(String operator) {
		this.operator = operator;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @return:  String 
	 * @Date :   2018-07-05 10:21:54    
	 */
	public String getSortTitle() {
		return sortTitle;
	}
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @param:   @param sortTitle
	 * @return:  void 
	 * @Date :   2018-07-05 10:21:54   
	 */
	public void setSortTitle(String sortTitle) {
		this.sortTitle = sortTitle;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @return:  Date 
	 * @Date :   2018-07-05 10:21:54    
	 */
	public Date getSendDate() {
		return sendDate;
	}
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @param:   @param sendDate
	 * @return:  void 
	 * @Date :   2018-07-05 10:21:54   
	 */
	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @return:  String 
	 * @Date :   2018-07-05 10:21:54    
	 */
	public String getSendUserName() {
		return sendUserName;
	}
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @param:   @param sendUserName
	 * @return:  void 
	 * @Date :   2018-07-05 10:21:54   
	 */
	public void setSendUserName(String sendUserName) {
		this.sendUserName = sendUserName;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @return:  Integer 
	 * @Date :   2018-07-05 10:21:54    
	 */
	public Integer getMessageType() {
		return messageType;
	}
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @param:   @param messageType
	 * @return:  void 
	 * @Date :   2018-07-05 10:21:54   
	 */
	public void setMessageType(Integer messageType) {
		this.messageType = messageType;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @return:  String 
	 * @Date :   2018-07-05 10:21:54    
	 */
	public String getSaasId() {
		return saasId;
	}
	
	/**
	 * <p></p>
	 * @author:  liuchenghui
	 * @param:   @param saasId
	 * @return:  void 
	 * @Date :   2018-07-05 10:21:54   
	 */
	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}
	
	

}
