package hry.app.lend.websocket.model;

/**
 * @author Hec
 * 交易大厅数据接收
 */
public class MessageEntrust {

    /**
     * 1买   2卖    3全部
     */
    private Integer currentType;
    private Integer historyType;

    /**
     * 左侧当前选中交易区
     */
    private String leftCoin;

    //变更的交易对
    private String coinCode;

    public String getCoinCode() {
        return coinCode;
    }

    public void setCoinCode(String coinCode) {
        this.coinCode = coinCode;
    }

    public MessageEntrust() {
    }

    public MessageEntrust(String lefCoin) {
        this.leftCoin = lefCoin;
    }

    public MessageEntrust(Integer currentType, Integer historyType, String lefCoin) {
        this.currentType = currentType;
        this.historyType = historyType;
        this.leftCoin = lefCoin;
    }

    public String getLeftCoin() {
        return leftCoin;
    }

    public void setLeftCoin(String leftCoin) {
        this.leftCoin = leftCoin;
    }

    public Integer getCurrentType() {
        return currentType;
    }

    public void setCurrentType(Integer currentType) {
        this.currentType = currentType;
    }

    public Integer getHistoryType() {
        return historyType;
    }

    public void setHistoryType(Integer historyType) {
        this.historyType = historyType;
    }
}
