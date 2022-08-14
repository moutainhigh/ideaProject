package hry.app.kline.model;

import java.io.Serializable;

public class LastKLine implements Serializable{
	
	private Integer version = 1;
	
	private String msgType = "lastKLine";
	
	private String symbolId = "btccny";
	
	private LastKLinePayload  payload = new LastKLinePayload();

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

	public String getSymbolId() {
		return symbolId;
	}

	public void setSymbolId(String symbolId) {
		this.symbolId = symbolId;
	}

	public LastKLinePayload getPayload() {
		return payload;
	}

	public void setPayload(LastKLinePayload payload) {
		this.payload = payload;
	}
	
	
}
