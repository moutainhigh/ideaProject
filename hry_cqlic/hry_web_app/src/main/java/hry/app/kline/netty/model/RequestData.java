package hry.app.kline.netty.model;

/**
 * Copyright:   互融云
 *
 * @author: StayBlank
 * @version: V6.0
 * @Date: 2018/12/7 16:16
 */
public class RequestData {
    private String type; //0 币币交易， 1杠杆交易
    private String coin; //交易对
    private String firstReq; //是否是历史数据
    private String time; //时间间隔

    public String getFirstReq() {
        return firstReq;
    }

    public void setFirstReq(String firstReq) {
        this.firstReq = firstReq;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCoin() {
        return coin;
    }

    public void setCoin(String coin) {
        this.coin = coin;
    }
}
