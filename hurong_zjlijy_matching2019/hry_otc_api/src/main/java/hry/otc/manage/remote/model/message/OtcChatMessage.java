/**
 * Copyright:   
 * @author:      xubb
 * @version:     V1.0 
 * @Date:        2019-07-03 10:07:13 
 */
package hry.otc.manage.remote.model.message;


import hry.bean.BaseModel;

import javax.persistence.*;

/**
 * <p> OtcChatMessage </p>
 * @author:         xubb
 * @Date :          2019-07-03 10:07:13  
 */
@Table(name="otc_chat_message")
public class OtcChatMessage extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "orderId")
	private Long orderId;  //订单id
	
	@Column(name= "fromID")
	private Long fromID;  //发送者id
	
	@Column(name= "toID")
	private Long toID;  //接收者id

	@Column(name= "fromName")
	private String fromName;  //发送者昵称

	@Column(name= "toName")
	private String toName;  //接收者昵称
	
	@Column(name= "type")
	private Integer type;  //类型 ： 1 正常消息 2 图片 3 系统提示消息

	/*系统消息取content字段为type
	1、2 摘单
	3 买家确认付款
	4 卖家确认收款
	5 买家撤销订单
	6、7 申诉
	8 平台回复
	9 平台取消订单
	10 平台成立订单
	11 超时取消订单
	12 平台通过申诉
	13 平台驳回申诉
	14 平台取消订单*/
	@Column(name= "content")
	private String content;  //消息内容
	
	@Column(name= "saasId")
	private String saasId;  //saasId

	@Column(name= "isRead")
	private Integer isRead;  //是否已读：0未读，1已读

	public Integer getIsRead () {
		return isRead;
	}

	public void setIsRead (Integer isRead) {
		this.isRead = isRead;
	}

	/**
	 * <p></p>
	 * @author:  xubb
	 * @return:  Long 
	 * @Date :   2019-07-03 10:07:13    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  xubb
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2019-07-03 10:07:13   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p>订单id</p>
	 * @author:  xubb
	 * @return:  Long 
	 * @Date :   2019-07-03 10:07:13    
	 */
	public Long getOrderId() {
		return orderId;
	}
	
	/**
	 * <p>订单id</p>
	 * @author:  xubb
	 * @param:   @param orderId
	 * @return:  void 
	 * @Date :   2019-07-03 10:07:13   
	 */
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	
	
	/**
	 * <p>发送者id</p>
	 * @author:  xubb
	 * @return:  Long 
	 * @Date :   2019-07-03 10:07:13    
	 */
	public Long getFromID() {
		return fromID;
	}
	
	/**
	 * <p>发送者id</p>
	 * @author:  xubb
	 * @param:   @param fromID
	 * @return:  void 
	 * @Date :   2019-07-03 10:07:13   
	 */
	public void setFromID(Long fromID) {
		this.fromID = fromID;
	}
	
	
	/**
	 * <p>接收者id</p>
	 * @author:  xubb
	 * @return:  Long 
	 * @Date :   2019-07-03 10:07:13    
	 */
	public Long getToID() {
		return toID;
	}
	
	/**
	 * <p>接收者id</p>
	 * @author:  xubb
	 * @param:   @param toID
	 * @return:  void 
	 * @Date :   2019-07-03 10:07:13   
	 */
	public void setToID(Long toID) {
		this.toID = toID;
	}
	
	
	/**
	 * <p>类型 ： 1 正常消息 2 图片 3 系统提示消息</p>
	 * @author:  xubb
	 * @return:  Integer 
	 * @Date :   2019-07-03 10:07:13    
	 */
	public Integer getType() {
		return type;
	}
	
	/**
	 * <p>类型 ： 1 正常消息 2 图片 3 系统提示消息</p>
	 * @author:  xubb
	 * @param:   @param type
	 * @return:  void 
	 * @Date :   2019-07-03 10:07:13   
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	
	
	/**
	 * <p>消息内容</p>
	 * @author:  xubb
	 * @return:  String 
	 * @Date :   2019-07-03 10:07:13    
	 */
	public String getContent() {
		return content;
	}
	
	/**
	 * <p>消息内容</p>
	 * @author:  xubb
	 * @param:   @param content
	 * @return:  void 
	 * @Date :   2019-07-03 10:07:13   
	 */
	public void setContent(String content) {
		this.content = content;
	}
	
	
	/**
	 * <p>saasId</p>
	 * @author:  xubb
	 * @return:  String 
	 * @Date :   2019-07-03 10:07:13    
	 */
	public String getSaasId() {
		return saasId;
	}
	
	/**
	 * <p>saasId</p>
	 * @author:  xubb
	 * @param:   @param saasId
	 * @return:  void 
	 * @Date :   2019-07-03 10:07:13   
	 */
	public void setSaasId(String saasId) {
		this.saasId = saasId;
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
