package hry.exchange.kline2.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class MarketPayloadBids implements Serializable{
	//成交价格18个个
	private BigDecimal[] price = {};
	//成交量
	private BigDecimal[] amount = {};
	//待定。。
	private BigDecimal[] level = {};
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
	public BigDecimal[] getLevel() {
		return level;
	}
	public void setLevel(BigDecimal[] level) {
		this.level = level;
	}
	
}
