/**

 * Copyright:   北京互融时代软件有限公司
 * @author:      Wu Shuiming
 * @version:      V1.0 
 * @Date:        2016年5月31日 下午7:33:55
 */
package hry.web.app.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import hry.core.mvc.model.BaseModel;

import java.util.Date;

/**
 * <p> TODO</p>
 * @author:         Wu Shuiming
 * @Date :          2016年5月31日 下午7:33:55 
 */
@Table(name="message_as_customer")
public class MessageAsCustomer extends BaseModel{
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	
	// 消息id
	@Column(name = "messageId")
	private Long messageId;
	
	// 接收人id
	@Column(name = "customerId")
	private Long customerId;
		
	// 是否以查看 1未读   2已读  3删除
	@Column(name = "state")
	private Integer state;
	
	// 接收人 姓名
	@Column(name = "customerName")
	private String customerName;
	
	@Column(name = "trueName")
	private String trueName;

	@Column(name = "readDate")
	private Date readDate; // 阅读时间

	@Column(name = "deleteDate")
	private Date deleteDate; // 删除时间

	// 接收人邮箱
	@Transient
	private String email;
	
	// 接收人手机号
	@Transient
	private String mobilePhone;

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

	public String getTrueName() {
		return trueName;
	}
	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getMessageId() {
		return messageId;
	}
	public void setMessageId(Long messageId) {
		this.messageId = messageId;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}

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
	
	
	public MessageAsCustomer(Long id, Long mseeageId, Long customerId,
			Long sendUserId, Integer state, String customerName, String userName) {
		super();
		this.id = id;
		this.messageId = mseeageId;
		this.customerId = customerId;
		this.state = state;
		this.customerName = customerName;
	}
	public MessageAsCustomer() {
		super();
	}
	
	@Override
	public String toString() {
		return "MessageAsCustomer [id=" + id + ", mseeageId=" + messageId
				+ ", customerId=" + customerId + ", sendUserId="
				+ ", state=" + state + ", customerName=" + customerName
				+ ", userName="+ "]";
	}


}
