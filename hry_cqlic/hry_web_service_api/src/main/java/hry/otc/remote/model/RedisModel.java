package hry.otc.remote.model;

/**
 * @Author: denghf
 * @Date: 2018/12/21 17:33
 * @Description: Redis.java
 */
public class RedisModel {

    private Long userId;

    private String coinCode;

    private Integer transactionMode;

    private String tradeNum;

    public String getTradeNum() {
        return tradeNum;
    }

    public void setTradeNum(String tradeNum) {
        this.tradeNum = tradeNum;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getCoinCode() {
        return coinCode;
    }

    public void setCoinCode(String coinCode) {
        this.coinCode = coinCode;
    }

    public Integer getTransactionMode() {
        return transactionMode;
    }

    public void setTransactionMode(Integer transactionMode) {
        this.transactionMode = transactionMode;
    }
}
