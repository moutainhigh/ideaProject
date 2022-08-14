package hry.admin.exchange.model;

import java.math.BigDecimal;

public class LastKLinePayload {
	
	private BigDecimal amount;
	
	private BigDecimal count;
	
	private BigDecimal dayTotalDealAmount;
	
	private String endTime;
	
	private Long id;
	
	private String period;

	private BigDecimal priceHigh;
	
	private BigDecimal priceLast;
	
	private BigDecimal priceLow;
	
	private BigDecimal priceOpen;
	
	private String startTime;
	
	private String symbolId = "btccny";
	
	private Long time;
	
	private BigDecimal volume;
	
	private int size; // K线条数
	
	
	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getCount() {
		return count;
	}

	public void setCount(BigDecimal count) {
		this.count = count;
	}

	public BigDecimal getDayTotalDealAmount() {
		return dayTotalDealAmount;
	}

	public void setDayTotalDealAmount(BigDecimal dayTotalDealAmount) {
		this.dayTotalDealAmount = dayTotalDealAmount;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public BigDecimal getPriceHigh() {
		return priceHigh;
	}

	public void setPriceHigh(BigDecimal priceHigh) {
		this.priceHigh = priceHigh;
	}

	public BigDecimal getPriceLast() {
		return priceLast;
	}

	public void setPriceLast(BigDecimal priceLast) {
		this.priceLast = priceLast;
	}

	public BigDecimal getPriceLow() {
		return priceLow;
	}

	public void setPriceLow(BigDecimal priceLow) {
		this.priceLow = priceLow;
	}

	public BigDecimal getPriceOpen() {
		return priceOpen;
	}

	public void setPriceOpen(BigDecimal priceOpen) {
		this.priceOpen = priceOpen;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getSymbolId() {
		return symbolId;
	}

	public void setSymbolId(String symbolId) {
		this.symbolId = symbolId;
	}

	public Long getTime() {
		return time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

	public BigDecimal getVolume() {
		return volume;
	}

	public void setVolume(BigDecimal volume) {
		this.volume = volume;
	}
}
