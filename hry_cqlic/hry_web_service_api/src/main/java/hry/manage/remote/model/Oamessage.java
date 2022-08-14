package hry.manage.remote.model;

import hry.bean.BaseModel;

import java.util.Date;

/**
 * 站内信
 * @author tzw
 *
 * 2017年7月19日
 */
public class Oamessage extends BaseModel {

	//id
	private Long id;
	
	//状态  是否发送(0 是未读  1 表示已读   2 表示删除)
	private Integer state;
	
	//内容
	private String content;

	//发送时间
	private Date sendDate;
	
	private Long sendDate_long;
	
	//消息类型
	private String categoryName;
	
	//消息标题
	private String title;
	
	//简介
	private String sortTitle;
	
	//用户名
	private String customerName;
	
	private Long customerId;

	
	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getSendDate() {
		return sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSortTitle() {
		return sortTitle;
	}

	public void setSortTitle(String sortTitle) {
		this.sortTitle = sortTitle;
	}
	
	

	public Long getSendDate_long() {
		if(this.sendDate!=null){
			return this.sendDate.getTime();
		}
		return 0L;
	}

	public void setSendDate_long(Long sendDate_long) {
		this.sendDate_long = sendDate_long;
	}

	@Override
	public String toString() {
		return "Oamessage [id=" + id + ", state=" + state + ", content=" + content + ", sendDate=" + sendDate
				+ ", categoryName=" + categoryName + ", title=" + title + ", sortTitle=" + sortTitle + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((categoryName == null) ? 0 : categoryName.hashCode());
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((sendDate == null) ? 0 : sendDate.hashCode());
		result = prime * result + ((sortTitle == null) ? 0 : sortTitle.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Oamessage other = (Oamessage) obj;
		if (categoryName == null) {
			if (other.categoryName != null)
				return false;
		} else if (!categoryName.equals(other.categoryName))
			return false;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (sendDate == null) {
			if (other.sendDate != null)
				return false;
		} else if (!sendDate.equals(other.sendDate))
			return false;
		if (sortTitle == null) {
			if (other.sortTitle != null)
				return false;
		} else if (!sortTitle.equals(other.sortTitle))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	
	public Oamessage() {
		super();
	}
	
	
	
}
