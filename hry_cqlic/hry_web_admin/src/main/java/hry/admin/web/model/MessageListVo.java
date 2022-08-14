/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Wu Shuiming
 * @version:      V1.0 
 * @Date:        2016年6月1日 上午9:25:25
 */
package hry.admin.web.model;

import hry.admin.customer.model.AppPersonInfo;

import java.util.Date;
import java.util.List;

/**
 * <p> TODO</p>
 * @author:         Wu Shuiming
 * @Date :          2016年6月1日 上午9:25:25 
 * 
 * 此类用与封装消息的的发送以及接收人的详细信息 。
 * 
 * 具体用在  后台系统的查询消息的list的列表
 * 
 */
public class MessageListVo {
	
	private Long id;
	private Long receiver;
	private String categoryName;
	private String messageTitle;
	private Integer isSend;
	private Integer status;
	private String sendUserName;
	private String messageCategory;
	private Date sendDate;
	private Date readDate;
	private Date deleteDate;
	private Date created;
	private List<MessageAsCustomer> list;
	private AppPersonInfo appPersonInfo;

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

	public AppPersonInfo getAppPersonInfo() {
		return appPersonInfo;
	}

	public void setAppPersonInfo(AppPersonInfo appPersonInfo) {
		this.appPersonInfo = appPersonInfo;
	}

	public Long getReceiver() {
		return receiver;
	}

	public void setReceiver(Long receiver) {
		this.receiver = receiver;
	}

	public Integer getIsSend() {
		return isSend;
	}
	public void setIsSend(Integer isSend) {
		this.isSend = isSend;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public List<MessageAsCustomer> getList() {
		return list;
	}
	public void setList(List<MessageAsCustomer> list) {
		this.list = list;
	}
	
	public String getSendUserName() {
		return sendUserName;
	}
	public void setSendUserName(String sendUserName) {
		this.sendUserName = sendUserName;
	}


	// 操作人
	private String operator;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getMessageTitle() {
		return messageTitle;
	}
	public void setMessageTitle(String messageTitle) {
		this.messageTitle = messageTitle;
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
	public MessageListVo (Long id, String categoryName, String messageTitle,
                          Date sendDate, String receiveUserName, String sendUserName,
                          String operator) {
		super();
		this.id = id;
		this.categoryName = categoryName;
		this.messageTitle = messageTitle;
		this.sendDate = sendDate;

		this.operator = operator;
	}
	public MessageListVo () {
		super();
	}
	@Override
	public String toString() {
		return "MessageListVo [id=" + id + ", categoryName=" + categoryName
				+ ", messageTitle=" + messageTitle + ", sendDate=" + sendDate
				+ ", receiveUserName=" + ", sendUserName="
				+ ", operator=" + operator + "]";
	}
	
	
}
