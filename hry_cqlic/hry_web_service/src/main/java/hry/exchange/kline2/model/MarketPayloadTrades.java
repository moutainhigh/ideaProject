package hry.exchange.kline2.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class MarketPayloadTrades implements Serializable{
	//成交价格60个
	private BigDecimal[] price = {};
	//成交量
	private BigDecimal[] amount = {};
	//成交内盘外盘
	private BigDecimal[] direction = {};
	//成交时间
	private Long[] time = {};
	public BigDecimal[] getPrice() {
		return price;
	}
	public void setPrice(BigDecimal[] price) {
		this.price = price;
	}
	public BigDecimal[] getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal[] amount) {
		this.amount = amount;
	}
	public BigDecimal[] getDirection() {
		return direction;
	}
	public void setDirection(BigDecimal[] direction) {
		this.direction = direction;
	}
	public Long[] getTime() {
		return time;
	}
	public void setTime(Long[] time) {
		this.time = time;
	}
	
	
	
	
}
