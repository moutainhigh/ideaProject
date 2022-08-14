package hry.otc.manage.remote.model;

import java.io.Serializable;
import java.util.Date;

/**
  * 聊天信息  pojo类
  * @Author  gz
  * @CreateDate  2018年1月12日 下午3:23:46 
  * @Description  
  */
public class OtcChatMessageRemote implements Serializable {
	
	private Long id;

	private Long orderId;	//订单id

	private String content;  //消息内容

	private Long fromID;  //发送者id

	private Long toID;    //接收者id

	private String fromName;  //发送者昵称

	private String toName;  //接收者昵称

	private int type;//类型 ： 1 正常消息 2 图片 3 系统提示消息

	private String saasId;  //saasId

	private Date created;

	private int chatType;//类型 ： 1 左边消息 2 右边消息 3 中间消息

	public int getChatType () {
		return chatType;
	}

	public void setChatType (int chatType) {
		this.chatType = chatType;
	}

	public Long getId () {
		return id;
	}

	public void setId (Long id) {
		this.id = id;
	}

	public String getContent () {
		return content;
	}

	public void setContent (String content) {
		this.content = content;
	}

	public Long getFromID () {
		return fromID;
	}

	public void setFromID (Long fromID) {
		this.fromID = fromID;
	}

	public Long getToID () {
		return toID;
	}

	public void setToID (Long toID) {
		this.toID = toID;
	}

	public int getType () {
		return type;
	}

	public void setType (int type) {
		this.type = type;
	}

	public String getSaasId () {
		return saasId;
	}

	public void setSaasId (String saasId) {
		this.saasId = saasId;
	}

	public Date getCreated () {
		return created;
	}

	public void setCreated (Date created) {
		this.created = created;
	}

	public Long getOrderId () {
		return orderId;
	}

	public void setOrderId (Long orderId) {
		this.orderId = orderId;
	}

	public String getFromName () {
		return fromName;
	}

	public void setFromName (String fromName) {
		this.fromName = fromName;
	}

	public String getToName () {
		return toName;
	}

	public void setToName (String toName) {
		this.toName = toName;
	}
}
