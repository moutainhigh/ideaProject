package hry.app.kline.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class MarketPayload implements Serializable {
	
	private String symbolId = "btccny";
	
	//最新成交价
	private BigDecimal priceNew = BigDecimal.ZERO ;
	//开盘价
	private BigDecimal priceOpen = BigDecimal.ZERO ;
	//最高价
	private BigDecimal priceHigh = BigDecimal.ZERO ;
	//最低价
	private BigDecimal priceLow = BigDecimal.ZERO ;
	//收盘价
	private BigDecimal priceLast = BigDecimal.ZERO ;
	//与最新成交价相等
	private BigDecimal level = BigDecimal.ZERO ;
	//成交量
	private BigDecimal amount = BigDecimal.ZERO ;
	//总成交量
	private BigDecimal totalAmount = BigDecimal.ZERO ;
	//待定。。。
	private Object amp = null;
	
	
	private BigDecimal  yestdayPriceLast = BigDecimal.ONE;
	
	private Integer commissionRatio = 0;
	private Integer poor = 0;
	private Integer updownVolume = 0;
	private Integer updownRatio = 0;
	private Integer priceAverage = 0;
	private Integer volumeRatio = 0;
	private Integer turnVolume = 0;
	private Integer turnoverRate = 0;
	private Integer outerDisc = 0;
	private Integer innerDisc = 0;
	
	//总成交量
	private BigDecimal totalVolume ;
	
	//成交数据
	private MarketPayloadTrades trades = new MarketPayloadTrades();
	//委托买
	private MarketPayloadBids bids = new MarketPayloadBids();
	//委托卖
	private MarketPayloadAsks asks = new MarketPayloadAsks();

	//usdt对人民币汇率
	private BigDecimal usdtToRmb = new BigDecimal(6.6);
	//usdt 数量
	private BigDecimal usdtCount = new BigDecimal(0);

	public BigDecimal getUsdtToRmb() {
		return usdtToRmb;
	}

	public void setUsdtToRmb(BigDecimal usdtToRmb) {
		this.usdtToRmb = usdtToRmb;
	}

	public BigDecimal getUsdtCount() {
		return usdtCount;
	}

	public void setUsdtCount(BigDecimal usdtCount) {
		this.usdtCount = usdtCount;
	}
	
	
	public String getSymbolId() {
		return symbolId;
	}
	public void setSymbolId(String symbolId) {
		this.symbolId = symbolId;
	}
	public BigDecimal getPriceNew() {
		return priceNew;
	}
	public void setPriceNew(BigDecimal priceNew) {
		this.priceNew = priceNew;
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
	public BigDecimal getLevel() {
		return level;
	}
	public void setLevel(BigDecimal level) {
		this.level = level;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
	public Object getAmp() {
		return amp;
	}
	public void setAmp(Object amp) {
		this.amp = amp;
	}
	public Integer getCommissionRatio() {
		return commissionRatio;
	}
	public void setCommissionRatio(Integer commissionRatio) {
		this.commissionRatio = commissionRatio;
	}
	public Integer getPoor() {
		return poor;
	}
	public void setPoor(Integer poor) {
		this.poor = poor;
	}
	public Integer getUpdownVolume() {
		return updownVolume;
	}
	public void setUpdownVolume(Integer updownVolume) {
		this.updownVolume = updownVolume;
	}
	public Integer getUpdownRatio() {
		return updownRatio;
	}
	public void setUpdownRatio(Integer updownRatio) {
		this.updownRatio = updownRatio;
	}
	public Integer getPriceAverage() {
		return priceAverage;
	}
	public void setPriceAverage(Integer priceAverage) {
		this.priceAverage = priceAverage;
	}
	public Integer getVolumeRatio() {
		return volumeRatio;
	}
	public void setVolumeRatio(Integer volumeRatio) {
		this.volumeRatio = volumeRatio;
	}
	public Integer getTurnVolume() {
		return turnVolume;
	}
	public void setTurnVolume(Integer turnVolume) {
		this.turnVolume = turnVolume;
	}
	public Integer getTurnoverRate() {
		return turnoverRate;
	}
	public void setTurnoverRate(Integer turnoverRate) {
		this.turnoverRate = turnoverRate;
	}
	public Integer getOuterDisc() {
		return outerDisc;
	}
	public void setOuterDisc(Integer outerDisc) {
		this.outerDisc = outerDisc;
	}
	public Integer getInnerDisc() {
		return innerDisc;
	}
	public void setInnerDisc(Integer innerDisc) {
		this.innerDisc = innerDisc;
	}
	public BigDecimal getTotalVolume() {
		return totalVolume;
	}
	public void setTotalVolume(BigDecimal totalVolume) {
		this.totalVolume = totalVolume;
	}
	public MarketPayloadTrades getTrades() {
		return trades;
	}
	public void setTrades(MarketPayloadTrades trades) {
		this.trades = trades;
	}
	public MarketPayloadBids getBids() {
		return bids;
	}
	public BigDecimal getYestdayPriceLast() {
		return yestdayPriceLast;
	}
	public void setYestdayPriceLast(BigDecimal yestdayPriceLast) {
		this.yestdayPriceLast = yestdayPriceLast;
	}
	public void setBids(MarketPayloadBids bids) {
		this.bids = bids;
	}
	public MarketPayloadAsks getAsks() {
		return asks;
	}
	public void setAsks(MarketPayloadAsks asks) {
		this.asks = asks;
	}
	
	
	
	
	
}
