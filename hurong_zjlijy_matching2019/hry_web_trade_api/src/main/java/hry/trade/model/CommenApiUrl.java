package hry.trade.model;

import java.io.Serializable;

public class CommenApiUrl implements Serializable {
	
	public static final String kkcoin_urlPrice="https://api.kkcoin.com/rest/trades?symbol="; //kkcoin可酷
	
	public static final String okcoin_urlPrice="https://www.okcoin.com/api/v1/ticker.do?symbol=";//;okcoin=ltc_usd
	public static final String bittrex_urlPrice = "https://bittrexExDmTransactionServiceImpl.com/api/v1.1/public/getticker?market=";//

	public static final String okex_urlPrice = "";

	public static String zb_urlPrice="http://api.zb.com/data/v1/ticker?market=";

	public static final String huobi_urlPrice="https://api.huobi.br.com/market/history/trade?symbol=";

}
