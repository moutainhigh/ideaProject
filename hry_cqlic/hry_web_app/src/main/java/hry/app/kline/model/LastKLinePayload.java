package hry.app.kline.model;

import java.math.BigDecimal;

public class LastKLinePayload {
	
	//和时间相同
	private Long _id;
	
	private String symbolId = "btccny";
	
	private Long time;
	
	private String period = "1min";
	//开盘价
	private BigDecimal priceOpen = new BigDecimal(0);
	//最高价
	private BigDecimal priceHigh = new BigDecimal(0); 
	//最低价
	private BigDecimal priceLow = new BigDecimal(0); 
	//收盘价
	private BigDecimal priceLast = new BigDecimal(0);
	//成交量
	private BigDecimal amount = new BigDecimal(0);
	//日成交总额(金科新加)
	private BigDecimal dayTotalDealAmount = new BigDecimal(0);
	//待定。。。
	private BigDecimal volume = new BigDecimal(0); 
	//待定。。。
	private BigDecimal count = new BigDecimal(0);



	/**
	 * <p> TODO</p>
	 * @return:     BigDecimal
	 */
	public BigDecimal getDayTotalDealAmount() {
		return dayTotalDealAmount;
	}
	/** 
	 * <p> TODO</p>
	 * @return: BigDecimal
	 */
	public void setDayTotalDealAmount(BigDecimal dayTotalDealAmount) {
		this.dayTotalDealAmount = dayTotalDealAmount;
	}
	public Long get_id() {
		return _id;
	}
	public void set_id(Long _id) {
		this._id = _id;
	}
	public String getSymbolId() {
		return symbolId;
	}
	public void setSymbolId(String symbolId) {
		this.symbolId = symbolId;
	}

	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public BigDecimal getPriceOpen() {
		return priceOpen;
	}
	public void setPriceOpen(BigDecimal priceOpen) {
		this.priceOpen = priceOpen;
	}
	public BigDecimal getPriceHigh() {
		return priceHigh;
	}
	public void setPriceHigh(BigDecimal priceHigh) {
		this.priceHigh = priceHigh;
	}
	public BigDecimal getPriceLow() {
		return priceLow;
	}
	public void setPriceLow(BigDecimal priceLow) {
		this.priceLow = priceLow;
	}
	public BigDecimal getPriceLast() {
		return priceLast;
	}
	public void setPriceLast(BigDecimal priceLast) {
		this.priceLast = priceLast;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public BigDecimal getVolume() {
		return volume;
	}
	public void setVolume(BigDecimal volume) {
		this.volume = volume;
	}
	public BigDecimal getCount() {
		return count;
	}
	public void setCount(BigDecimal count) {
		this.count = count;
	}
	public Long getTime() {
		return time;
	}
	public void setTime(Long time) {
		this.time = time;
	} 
	
	
	
	
	
}
