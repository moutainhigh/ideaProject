package hry.redis.otc.mdel;/**
 * @title: RedisModel
 * @projectName hurong_matching2019
 * @description: TODO
 * @author javal
 * @date 2019/7/2219:23
 */

/**
 * @author javal
 * @title: RedisModel
 * @projectName hurong_matching2019
 * @description: TODO
 * @date 2019/7/2219:23
 */
public class RedisModel {
    private Long userId;
    private String coinCode;
    private Integer transactionMode;
    private String tradeNum;

    public RedisModel() {
    }

    public String getTradeNum() {
        return this.tradeNum;
    }

    public void setTradeNum(String tradeNum) {
        this.tradeNum = tradeNum;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getCoinCode() {
        return this.coinCode;
    }

    public void setCoinCode(String coinCode) {
        this.coinCode = coinCode;
    }

    public Integer getTransactionMode() {
        return this.transactionMode;
    }

    public void setTransactionMode(Integer transactionMode) {
        this.transactionMode = transactionMode;
    }

}
