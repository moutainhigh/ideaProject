package hry.admin.exchange.model;

import java.io.Serializable;

public class CommenApiUrl implements Serializable {
    public static final String kkcoin_urlPrice = "https://api.kkcoin.com/rest/trades?symbol=";
    public static final String okcoin_urlPrice = "https://www.okcoin.com/api/v1/ticker.do?symbol=";
    public static final String bittrex_urlPrice = "https://bittrex.com/api/v1.1/public/getticker?market=";
    public static final String okex_urlPrice = "https://www.okex.com/api/v1/ticker.do?symbol=";

    public CommenApiUrl() {
    }
}