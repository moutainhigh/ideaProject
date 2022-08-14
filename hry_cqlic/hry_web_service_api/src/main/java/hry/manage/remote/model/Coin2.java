package hry.manage.remote.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Coin2 implements Serializable {
	/**
	 * @Fields : TODO
	 */
	
	private static final long serialVersionUID = 352272460201788717L;


	private String coinCode;// 币代码

	private String fixPriceCoinCode;// 交易币种

	private String yesterdayPrice;

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

	public String getYesterdayPrice() {
		return yesterdayPrice;
	}

	public void setYesterdayPrice(String yesterdayPrice) {
		this.yesterdayPrice = yesterdayPrice;
	} 
	
	
	
	
	

}
