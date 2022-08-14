package hry.exchange.kline2.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class ReqKLinePayload implements Serializable {
	
	//币种类型
	private String symbolId = "btccny";
	//K线分时   1分 5分 15分
	private String period = "1min";
	//时间  默认300个
	private Long[] time = {};
	//开盘价
	private BigDecimal[] priceOpen = {}; 
	//最高价
	private BigDecimal[] priceHigh = {};
	//最低价
	private BigDecimal[] priceLow = {};
	//收盘价
	private BigDecimal[] priceLast = {};
	//成交量
	private BigDecimal[] amount = {};
	//待定。。。
	private BigDecimal[] volume = {};
	//待定。。。
	private BigDecimal[] count = {};
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
	public Long[] getTime() {
		return time;
	}
	public void setTime(Long[] time) {
		this.time = time;
	}
	public BigDecimal[] getPriceOpen() {
		return priceOpen;
	}
	public void setPriceOpen(BigDecimal[] priceOpen) {
		this.priceOpen = priceOpen;
	}
	public BigDecimal[] getPriceHigh() {
		return priceHigh;
	}
	public void setPriceHigh(BigDecimal[] priceHigh) {
		this.priceHigh = priceHigh;
	}
	public BigDecimal[] getPriceLow() {
		return priceLow;
	}
	public void setPriceLow(BigDecimal[] priceLow) {
		this.priceLow = priceLow;
	}
	public BigDecimal[] getPriceLast() {
		return priceLast;
	}
	public void setPriceLast(BigDecimal[] priceLast) {
		this.priceLast = priceLast;
	}
	public BigDecimal[] getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal[] amount) {
		this.amount = amount;
	}
	public BigDecimal[] getVolume() {
		return volume;
	}
	public void setVolume(BigDecimal[] volume) {
		this.volume = volume;
	}
	public BigDecimal[] getCount() {
		return count;
	}
	public void setCount(BigDecimal[] count) {
		this.count = count;
	}
	
	
}
