package hry.exchange.kline2.model;

import java.io.Serializable;

public class MarketDetail implements Serializable {
	
	private Integer version = 1;
	
	//时间
	private Integer _id = 1468833041;
	
	private Integer idCur = 1468833041;
	
	private Integer idPrev = 0;
	
	private String msgType = "marketDetail0";
	
	private String symbolId = "btccny";
	
	private MarketPayload payload = new MarketPayload();

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Integer get_id() {
		return _id;
	}

	public void set_id(Integer _id) {
		this._id = _id;
	}

	public Integer getIdCur() {
		return idCur;
	}

	public void setIdCur(Integer idCur) {
		this.idCur = idCur;
	}

	public Integer getIdPrev() {
		return idPrev;
	}

	public void setIdPrev(Integer idPrev) {
		this.idPrev = idPrev;
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

	public MarketPayload getPayload() {
		return payload;
	}

	public void setPayload(MarketPayload payload) {
		this.payload = payload;
	}
	
	
	
	
}
