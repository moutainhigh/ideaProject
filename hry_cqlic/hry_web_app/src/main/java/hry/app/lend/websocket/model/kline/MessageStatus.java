package hry.app.lend.websocket.model.kline;

/**
 * @author <a href="mailto:HelloHeSir@gmail.com">HeC</a>
 * @Date 2018/11/16 16:32
 * 推送控制模型
 */
public class MessageStatus {

    /**
     * unsub sub req
     */
    private String cmd;

    /**
     * 时段
     */
    private String interval;

    /**
     * 超时时间
     */
    private Long timeout;

    /**
     * 当前时间
     */
    private Long date;

    /**
     * 币种
     */
    private String coinCode;

    private String timeDL;// k线拖动得时间

    private String token;

    private String chatContent; //聊天内容

    private String from; //k线开始时间

    private String to; //k线结束时间

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getChatContent() {
        return chatContent;
    }

    public void setChatContent(String chatContent) {
        this.chatContent = chatContent;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTimeDL() {
        return timeDL;
    }

    public void setTimeDL(String timeDL) {
        this.timeDL = timeDL;
    }

    /**
     * ture sub false unsub
     */
    private boolean sub = false;

    private String type; // 1杠杆 ， 0 高级

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getInterval() {
        return interval;
    }

    public void setInterval(String interval) {
        this.interval = interval;
    }

    public Long getTimeout() {
        return timeout;
    }

    public void setTimeout(Long timeout) {
        this.timeout = timeout;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public String getCoinCode() {
        return coinCode;
    }

    public void setCoinCode(String coinCode) {
        this.coinCode = coinCode;
    }

    public boolean isSub() {
        return sub;
    }

    public void setSub(boolean sub) {
        this.sub = sub;
    }

}