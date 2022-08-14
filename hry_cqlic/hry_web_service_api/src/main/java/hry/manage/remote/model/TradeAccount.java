package hry.manage.remote.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class TradeAccount implements Serializable{
	
	private BigDecimal rmb;   // 人民币的可用金额
	
	private BigDecimal coldrmb;//人民币的冻结金额
	
	private BigDecimal coinHotMoney; //虚拟币的可用币
	
	private BigDecimal canBuyCoin;   //能买的最大数量根据余额
	
	private BigDecimal keepDecimalForCoin;  //币的最大小数位
	
	private BigDecimal keepDecimalForCurrency;//人民币金额的最大小数据
	private BigDecimal canSellMoney;  //
	private BigDecimal buyMinMoney;   //市价买的最小金额
	private BigDecimal sellMinCoin;  //卖币的最小个数
	private BigDecimal currentExchangPrice; //当前成交价
	private BigDecimal currentCoinColdMoney;
	private BigDecimal buyOnePrice;  //买一价
	private BigDecimal sellOnePrice;//卖一价
	private BigDecimal oneTimeOrderNum;  // 每次下单的最大数量(20170207币银网添加)
	private String currencyType;
	private BigDecimal buyFeeRate;//买的手续费利率
	private BigDecimal sellFeeRate;//卖的手续费利率
	private BigDecimal circulation; // 融资手续费率
	private BigDecimal coinName;  //币的名字
	public BigDecimal getRmb() {
		if(rmb==null){
			return BigDecimal.ZERO;
		}
		return rmb;
	}
	public void setRmb(BigDecimal rmb) {
		this.rmb = rmb;
	}
	public BigDecimal getColdrmb() {
		return coldrmb;
	}
	public void setColdrmb(BigDecimal coldrmb) {
		this.coldrmb = coldrmb;
	}
	public BigDecimal getCoinHotMoney() {
		return coinHotMoney;
	}
	public void setCoinHotMoney(BigDecimal coinHotMoney) {
		this.coinHotMoney = coinHotMoney;
	}
	public BigDecimal getCanBuyCoin() {
		return canBuyCoin;
	}
	public void setCanBuyCoin(BigDecimal canBuyCoin) {
		this.canBuyCoin = canBuyCoin;
	}
	public BigDecimal getKeepDecimalForCoin() {
		return keepDecimalForCoin;
	}
	public void setKeepDecimalForCoin(BigDecimal keepDecimalForCoin) {
		this.keepDecimalForCoin = keepDecimalForCoin;
	}
	public BigDecimal getKeepDecimalForCurrency() {
		return keepDecimalForCurrency;
	}
	public void setKeepDecimalForCurrency(BigDecimal keepDecimalForCurrency) {
		this.keepDecimalForCurrency = keepDecimalForCurrency;
	}
	public BigDecimal getCanSellMoney() {
		return canSellMoney;
	}
	public void setCanSellMoney(BigDecimal canSellMoney) {
		this.canSellMoney = canSellMoney;
	}
	public BigDecimal getBuyMinMoney() {
		return buyMinMoney;
	}
	public void setBuyMinMoney(BigDecimal buyMinMoney) {
		this.buyMinMoney = buyMinMoney;
	}
	public BigDecimal getSellMinCoin() {
		return sellMinCoin;
	}
	public void setSellMinCoin(BigDecimal sellMinCoin) {
		this.sellMinCoin = sellMinCoin;
	}
	public BigDecimal getCurrentExchangPrice() {
		return currentExchangPrice;
	}
	public void setCurrentExchangPrice(BigDecimal currentExchangPrice) {
		this.currentExchangPrice = currentExchangPrice;
	}
	public BigDecimal getBuyOnePrice() {
		return buyOnePrice;
	}
	public void setBuyOnePrice(BigDecimal buyOnePrice) {
		this.buyOnePrice = buyOnePrice;
	}
	public BigDecimal getSellOnePrice() {
		return sellOnePrice;
	}
	public void setSellOnePrice(BigDecimal sellOnePrice) {
		this.sellOnePrice = sellOnePrice;
	}
	public BigDecimal getOneTimeOrderNum() {
		return oneTimeOrderNum;
	}
	public void setOneTimeOrderNum(BigDecimal oneTimeOrderNum) {
		this.oneTimeOrderNum = oneTimeOrderNum;
	}

	public BigDecimal getBuyFeeRate() {
		return buyFeeRate;
	}
	public void setBuyFeeRate(BigDecimal buyFeeRate) {
		this.buyFeeRate = buyFeeRate;
	}
	public BigDecimal getSellFeeRate() {
		return sellFeeRate;
	}
	public void setSellFeeRate(BigDecimal sellFeeRate) {
		this.sellFeeRate = sellFeeRate;
	}
	public BigDecimal getCirculation() {
		return circulation;
	}
	public void setCirculation(BigDecimal circulation) {
		this.circulation = circulation;
	}
	public BigDecimal getCoinName() {
		return coinName;
	}
	public void setCoinName(BigDecimal coinName) {
		this.coinName = coinName;
	}
	public BigDecimal getCurrentCoinColdMoney() {
		return currentCoinColdMoney;
	}
	public void setCurrentCoinColdMoney(BigDecimal currentCoinColdMoney) {
		this.currentCoinColdMoney = currentCoinColdMoney;
	}
	
	
	
	
}
