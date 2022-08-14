package hry.app.lend.websocket.model;

/**
 * @author <a href="mailto:HelloHeSir@gmail.com">Mr_He</a>
 * @Copyright (c)</ b> HeC<br/>
 * @createTime 2018/11/2 17:19
 * @Description: 伪session redis数据
 */
public class SessionRedis {

    //推送币种
    private String coinCode;

    //标识id
    private String marginToken;

    public String getMarginToken() {
        return marginToken;
    }

    public void setMarginToken(String marginToken) {
        this.marginToken = marginToken;
    }

    public SessionRedis() {
    }

    public SessionRedis(String coinCode) {
        this.coinCode = coinCode;
    }

    public String getCoinCode() {
        return coinCode;
    }

    public void setCoinCode(String coinCode) {
        this.coinCode = coinCode;
    }
}