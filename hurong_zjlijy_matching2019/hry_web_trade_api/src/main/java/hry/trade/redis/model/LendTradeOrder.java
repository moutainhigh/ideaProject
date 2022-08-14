package hry.trade.redis.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class LendTradeOrder implements Serializable {

    private String orderNum;    // 交易订单号
    private String coinCode; // 币的代码
    private String fixPriceCoinCode;  //定价币种
    private BigDecimal transactionPrice;     // 成交价（单价)
    private BigDecimal transactionCount;      // 成交数量
    private BigDecimal transactionSum;      // 交易总金额
    private Date transactionTime;     // 成交时间
    private Long customerId;  // id
    private BigDecimal transactionFee; // 交易手续费
    private String entrustNum; // 委托单号
    private Integer type;//1买2卖
    private Long orderTimestapm; //成交时间戳
    private Integer fixPriceType;  //0真实货币1虚拟币

    public String getOrderNum() {
        return orderNum;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
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

    public BigDecimal getTransactionPrice() {
        return transactionPrice;
    }

    public void setTransactionPrice(BigDecimal transactionPrice) {
        this.transactionPrice = transactionPrice;
    }

    public BigDecimal getTransactionCount() {
        return transactionCount;
    }

    public void setTransactionCount(BigDecimal transactionCount) {
        this.transactionCount = transactionCount;
    }

    public BigDecimal getTransactionSum() {
        return transactionSum;
    }

    public void setTransactionSum(BigDecimal transactionSum) {
        this.transactionSum = transactionSum;
    }

    public Date getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(Date transactionTime) {
        this.transactionTime = transactionTime;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public BigDecimal getTransactionFee() {
        return transactionFee;
    }

    public void setTransactionFee(BigDecimal transactionFee) {
        this.transactionFee = transactionFee;
    }

    public String getEntrustNum() {
        return entrustNum;
    }

    public void setEntrustNum(String entrustNum) {
        this.entrustNum = entrustNum;
    }

    public Long getOrderTimestapm() {
        return orderTimestapm;
    }

    public void setOrderTimestapm(Long orderTimestapm) {
        this.orderTimestapm = orderTimestapm;
    }

    public Integer getFixPriceType() {
        return fixPriceType;
    }

    public void setFixPriceType(Integer fixPriceType) {
        this.fixPriceType = fixPriceType;
    }
}
