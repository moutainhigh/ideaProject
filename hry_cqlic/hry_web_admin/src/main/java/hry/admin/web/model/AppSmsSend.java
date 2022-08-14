/**
 * Copyright:   
 * @author:      liushilei
 * @version:     V1.0 
 * @Date:        2018-06-20 14:42:04 
 */
package hry.admin.web.model;


import hry.bean.BaseModel;


import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> AppSmsSend </p>
 * @author:         liushilei
 * @Date :          2018-06-20 14:42:04  
 */
@Table(name="app_sms_send")
public class AppSmsSend extends BaseModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;  //
	
	@Column(name= "type")
	private String type;  //
	
	@Column(name= "serverUrl")
	private String serverUrl;  //
	
	@Column(name= "postParam")
	private String postParam;  //
	
	@Column(name= "receiveStatus")
	private String receiveStatus;  //
	
	@Column(name= "sendStatus")
	private String sendStatus;  //
	
	@Column(name= "thirdPartyResult")
	private String thirdPartyResult;  //
	
	@Column(name= "sendContent")
	private String sendContent;  //
	
	@Column(name= "saasId")
	private String saasId;  //
	
	
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  Integer 
	 * @Date :   2018-06-20 14:42:04    
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param id
	 * @return:  void 
	 * @Date :   2018-06-20 14:42:04   
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-20 14:42:04    
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param type
	 * @return:  void 
	 * @Date :   2018-06-20 14:42:04   
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-20 14:42:04    
	 */
	public String getServerUrl() {
		return serverUrl;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param serverUrl
	 * @return:  void 
	 * @Date :   2018-06-20 14:42:04   
	 */
	public void setServerUrl(String serverUrl) {
		this.serverUrl = serverUrl;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-20 14:42:04    
	 */
	public String getPostParam() {
		return postParam;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param postParam
	 * @return:  void 
	 * @Date :   2018-06-20 14:42:04   
	 */
	public void setPostParam(String postParam) {
		this.postParam = postParam;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-20 14:42:04    
	 */
	public String getReceiveStatus() {
		return receiveStatus;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param receiveStatus
	 * @return:  void 
	 * @Date :   2018-06-20 14:42:04   
	 */
	public void setReceiveStatus(String receiveStatus) {
		this.receiveStatus = receiveStatus;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-20 14:42:04    
	 */
	public String getSendStatus() {
		return sendStatus;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param sendStatus
	 * @return:  void 
	 * @Date :   2018-06-20 14:42:04   
	 */
	public void setSendStatus(String sendStatus) {
		this.sendStatus = sendStatus;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-20 14:42:04    
	 */
	public String getThirdPartyResult() {
		return thirdPartyResult;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param thirdPartyResult
	 * @return:  void 
	 * @Date :   2018-06-20 14:42:04   
	 */
	public void setThirdPartyResult(String thirdPartyResult) {
		this.thirdPartyResult = thirdPartyResult;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-20 14:42:04    
	 */
	public String getSendContent() {
		return sendContent;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param sendContent
	 * @return:  void 
	 * @Date :   2018-06-20 14:42:04   
	 */
	public void setSendContent(String sendContent) {
		this.sendContent = sendContent;
	}
	
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @return:  String 
	 * @Date :   2018-06-20 14:42:04    
	 */
	public String getSaasId() {
		return saasId;
	}
	
	/**
	 * <p></p>
	 * @author:  liushilei
	 * @param:   @param saasId
	 * @return:  void 
	 * @Date :   2018-06-20 14:42:04   
	 */
	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}
	
	

}
