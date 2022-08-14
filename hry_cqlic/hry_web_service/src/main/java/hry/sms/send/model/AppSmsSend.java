/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Liu Shilei
 * @version:      V1.0 
 * @Date:        2016年5月24日 下午3:07:59
 */
package hry.sms.send.model;

import hry.core.mvc.model.BaseModel;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * <p>短信发送记录</p>
 * @author:         Liu Shilei 
 * @Date :          2016年5月24日 下午3:07:59 
 */
@Table(name="app_sms_send")
public class AppSmsSend extends BaseModel {
	
	@Id
	@Column(name="id")
	private Long id ;
	
	@Column(name="type")   
	private String type;   
	
	@Column(name="serverUrl")   
	private String serverUrl;     //接口SDK地址
	
	@Column(name="postParam")   
	private String postParam;     //请求参数
	
	@Column(name="receiveStatus")   
	private String receiveStatus = "0";  //接收状态    表示hurong_sms是否接收到这个    0没有接收  1接收到
	
	@Column(name="sendStatus")
	private String sendStatus = "0";   //发送状态  表示hurong_sms是否向第三方短信发送  0没有发送  1发送成功
	
	@Column(name="thirdPartyResult")
	private String thirdPartyResult;
	
	@Column(name="sendContent")
	private String sendContent;  //发送的短信内容
	
	@Transient
	private String mobileNum;  //
	
	@Transient
	private String thirdTemplateId;  //
	

	public String getMobileNum() {
		return mobileNum;
	}

	public void setMobileNum(String mobileNum) {
		this.mobileNum = mobileNum;
	}

	public String getThirdTemplateId() {
		return thirdTemplateId;
	}

	public void setThirdTemplateId(String thirdTemplateId) {
		this.thirdTemplateId = thirdTemplateId;
	}

	/**
	 * <p> TODO</p>
	 * @return:     Long
	 */
	public Long getId() {
		return id;
	}

	/** 
	 * <p> TODO</p>
	 * @return: Long
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getType() {
		return type;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getServerUrl() {
		return serverUrl;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setServerUrl(String serverUrl) {
		this.serverUrl = serverUrl;
	}

	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getPostParam() {
		return postParam;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setPostParam(String postParam) {
		this.postParam = postParam;
	}

	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getReceiveStatus() {
		return receiveStatus;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setReceiveStatus(String receiveStatus) {
		this.receiveStatus = receiveStatus;
	}

	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getSendStatus() {
		return sendStatus;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setSendStatus(String sendStatus) {
		this.sendStatus = sendStatus;
	}

	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getThirdPartyResult() {
		return thirdPartyResult;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setThirdPartyResult(String thirdPartyResult) {
		this.thirdPartyResult = thirdPartyResult;
	}

	/**
	 * <p> TODO</p>
	 * @return:     String
	 */
	public String getSendContent() {
		return sendContent;
	}

	/** 
	 * <p> TODO</p>
	 * @return: String
	 */
	public void setSendContent(String sendContent) {
		this.sendContent = sendContent;
	}

	
	
	
	
}
