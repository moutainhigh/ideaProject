/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Wu Shuiming
 * @version:      V1.0 
 * @Date:        2016年5月30日 下午2:55:39
 */
package hry.web.app.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.util.HtmlUtils;

import hry.core.mvc.model.BaseModel;

/**
 * <p> TODO</p>
 * @author:         Wu Shuiming
 * @Date :          2016年5月30日 下午2:55:39 
 */
@SuppressWarnings("serial")
@Table(name = "app_message")
public class AppMessage extends BaseModel {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	
	// '消息内容'
	@Column(name = "content")
	private String content;
	

	// '发送人名称'
	@Column(name = "sendUserName")
	private String sendUserName;
	
	// '消息类型'
	@Column(name = "categoryName")
	private String categoryName;
	
	// '类别id'
	@Column(name = "categoryId")
	private String categoryId;

	// '消息标题'
	@Column(name = "title")
	private String title;
	
	// 消息图片
	@Column(name="titleImage")
	private String titleImage;
	
	// '短标题'
	@Column(name = "sortTitle")
	private String sortTitle;
	
	// 操作人
	@Column(name = "operator")
	private String operator;
	
	// 是否是全员发送
	@Column(name = "isAll")
	private Integer isAll;
	
	// 是否已发送(0 : 表示没有    1  表示已发送)
	@Column(name = "isSend")
	private Integer isSend;

	@Column(name= "status")
	private Integer status;  //（1 已阅，0 未阅，2 用户删除，3 系统删除）

	// 发送时间
	@Column(name = "sendDate")
	private Date sendDate;
	
	@Column(name = "messageType")
	private Integer messageType;//消息类别

	@Column(name= "isAuto")
	private Integer isAuto;  //0 手动发送 ，1 自动发送

	@Column(name= "messageCategory")
	private String messageCategory; // 语种简称

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMessageCategory() {
		return messageCategory;
	}

	public void setMessageCategory(String messageCategory) {
		this.messageCategory = messageCategory;
	}

	public Integer getIsAuto() {
		return isAuto;
	}

	public void setIsAuto(Integer isAuto) {
		this.isAuto = isAuto;
	}

	public Integer getMessageType() {
		return messageType;
	}

	public void setMessageType(Integer messageType) {
		this.messageType = messageType;
	}

	@Transient
	private MessageAsCustomer messageAsCustomer;

	
	public String getTitleImage() {
		return titleImage;
	}

	public void setTitleImage(String titleImage) {
		this.titleImage = titleImage;
	}

	public MessageAsCustomer getMessageAsCustomer() {
		return messageAsCustomer;
	}

	public void setMessageAsCustomer(MessageAsCustomer messageAsCustomer) {
		this.messageAsCustomer = messageAsCustomer;
	}

	public Integer getIsAll() {
		return isAll;
	}
	
	public void setIsAll(Integer isAll) {
		this.isAll = isAll;
	}

	public Integer getIsSend() {
		return isSend;
	}

	public void setIsSend(Integer isSend) {
		this.isSend = isSend;
	}

	public Date getSendDate() {
		return sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return HtmlUtils.htmlUnescape(content);
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public String getSendUserName() {
		return sendUserName;
	}

	public void setSendUserName(String sendUserName) {
		this.sendUserName = sendUserName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getTitle() {
		return HtmlUtils.htmlUnescape(title);
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSortTitle() {
		return HtmlUtils.htmlUnescape(sortTitle);
	}

	public void setSortTitle(String sortTitle) {
		this.sortTitle = sortTitle;
	}

	public AppMessage(Long id, String content,
			String categoryName, String categoryId, String title,
			String sortTitle) {
		super();
		this.id = id;
		this.content = content;
		this.categoryName = categoryName;
		this.categoryId = categoryId;
		this.title = title;
		this.sortTitle = sortTitle;
	}

	public AppMessage() {
		super();
	}

	@Override
	public String toString() {
		return "AppMessage [id=" + id + ", content=" + content
				+ ", sendUserName=" + sendUserName + ", categoryName="
				+ categoryName + ", categoryId=" + categoryId + ", title="
				+ title + ", titleImage=" + titleImage + ", sortTitle="
				+ sortTitle + ", operator=" + operator + ", isAll=" + isAll
				+ ", isSend=" + isSend + ", sendDate=" + sendDate
				+ ", messageAsCustomer=" + messageAsCustomer + "]";
	}


}

















