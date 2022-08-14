package hry.app.kline.model;

import java.io.Serializable;

public class ReqKLine implements Serializable {
	
	private Integer version = 1;
	
	private String msgType  = "reqKLine";
	
	private String requestIndex = "1468829950314";
	
	private Integer retCode = 200;
	
	private String retMsg = "";
	
	private ReqKLinePayload payload = new ReqKLinePayload();

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

	public Integer getRetCode() {
		return retCode;
	}

	public void setRetCode(Integer retCode) {
		this.retCode = retCode;
	}

	public String getRetMsg() {
		return retMsg;
	}

	public void setRetMsg(String retMsg) {
		this.retMsg = retMsg;
	}

	public ReqKLinePayload getPayload() {
		return payload;
	}

	public void setPayload(ReqKLinePayload payload) {
		this.payload = payload;
	}
	
	
	
}
