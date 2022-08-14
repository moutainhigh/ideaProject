package hry.exchange.kline2.model;

import java.io.Serializable;

public class ReqMsgSubscribe implements Serializable {
	/**  
	 * @Fields : TODO   
	 */
	private static final long serialVersionUID = 1L;

	private Integer version = 1;
	
	private String msgType = "reqMsgSubscribe";
	
	private String requestIndex = "1468829950278";
	
	private Integer retCode = 200;
	
	private String retMsg = "";
	
	private String payload = "";

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public String getRequestIndex() {
		return requestIndex;
	}

	public void setRequestIndex(String requestIndex) {
		this.requestIndex = requestIndex;
	}

	public String getRetMsg() {
		return retMsg;
	}

	public void setRetMsg(String retMsg) {
		this.retMsg = retMsg;
	}

	public String getPayload() {
		return payload;
	}

	public void setPayload(String payload) {
		this.payload = payload;
	}

	public Integer getRetCode() {
		return retCode;
	}

	public void setRetCode(Integer retCode) {
		this.retCode = retCode;
	}
	
	
	
	
	
	
	
}
