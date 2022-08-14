package hry.admin.exchange.model;

import java.io.Serializable;

public class CoinKeepDecimal implements Serializable {
	/**
	 * @Fields : TODO
	 */
	
	private static final long serialVersionUID = 352272460201788717L;

	private Long id;
	
	private String name;// 币种名称

	private String coinCode;// 币代码

	private String fixPriceCoinCode;// 交易币种
	private Integer fixPriceType;  //0真实货币1虚拟币
	private Integer keepDecimalForCoin; //交易币的保留几位小数
	private Integer keepDecimalForCurrency; //定价币的保留几位小数
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCoinCode() {
		return coinCode;
	}
	public void setCoinCode(String coinCode) {
		this.coinCode = coinCode;
	}
	public String getFixPriceCoinCode() {
		return fixPriceCoinCode;
	}
	public void setFixPriceCoinCode(String fixPriceCoinCode) {
		this.fixPriceCoinCode = fixPriceCoinCode;
	}
	public Integer getFixPriceType() {
		return fixPriceType;
	}
	public void setFixPriceType(Integer fixPriceType) {
		this.fixPriceType = fixPriceType;
	}
	public Integer getKeepDecimalForCoin() {
		return keepDecimalForCoin;
	}
	public void setKeepDecimalForCoin(Integer keepDecimalForCoin) {
		this.keepDecimalForCoin = keepDecimalForCoin;
	}
	public Integer getKeepDecimalForCurrency() {
		return keepDecimalForCurrency;
	}
	public void setKeepDecimalForCurrency(Integer keepDecimalForCurrency) {
		this.keepDecimalForCurrency = keepDecimalForCurrency;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

	

}
