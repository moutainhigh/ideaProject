package hry.calculate.mvc.po;

import java.math.BigDecimal;

@SuppressWarnings("serial")
public class FirstCoin {
	private Long id;
	private String coinCode;
	private BigDecimal entrustCount;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCoinCode() {
		return coinCode;
	}
	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}
	public BigDecimal getEntrustCount() {
		return entrustCount;
	}
	public void setEntrustCount(BigDecimal entrustCount) {
		this.entrustCount = entrustCount;
	}
	@Override
	public String toString() {
		return "FirstCoin [id=" + id + ", coinCode=" + coinCode + ", entrustCount=" + entrustCount + "]";
	}
	
}
