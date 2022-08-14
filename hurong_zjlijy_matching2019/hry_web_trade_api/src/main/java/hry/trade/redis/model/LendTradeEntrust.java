package hry.trade.redis.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class LendTradeEntrust implements Serializable {

    private Long id;
    private String entrustNum;
    private Long customerId;
    private String coinCode;    //交易币
    private Integer fixPriceType; // 0真实货币1虚拟币
    private String fixPriceCoinCode; // 定价币种
    private Integer type;   //买 or 卖
    private Integer status; //交易状态 0未成交　1部分成交　2已完成　 3部分成交已撤销 4已撤销
    private BigDecimal entrustPrice;
    private BigDecimal entrustCount;    //委托数量
    private BigDecimal entrustSum;      //委托总额
    private BigDecimal surplusEntrustCount; //剩余数量
    private BigDecimal transactionSum;      //已成交总额
    private Date entrustTime;
    private BigDecimal transactionFee;
    private Integer entrustWay; // 1.限价--> 表示以固定的价格 , 2.市价---> 表示以当前市场价

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEntrustNum() {
        return entrustNum;
    }

    public void setEntrustNum(String entrustNum) {
        this.entrustNum = entrustNum;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCoinCode() {
        return coinCode;
    }

    public void setCoinCode(String coinCode) {
        this.coinCode = coinCode;
    }

    public Integer getFixPriceType() {
        return fixPriceType;
    }

    public void setFixPriceType(Integer fixPriceType) {
        this.fixPriceType = fixPriceType;
    }

    public String getFixPriceCoinCode() {
        return fixPriceCoinCode;
    }

    public void setFixPriceCoinCode(String fixPriceCoinCode) {
        this.fixPriceCoinCode = fixPriceCoinCode;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public BigDecimal getEntrustPrice() {
        return entrustPrice;
    }

    public void setEntrustPrice(BigDecimal entrustPrice) {
        this.entrustPrice = entrustPrice;
    }

    public BigDecimal getEntrustCount() {
        return entrustCount;
    }

    public void setEntrustCount(BigDecimal entrustCount) {
        this.entrustCount = entrustCount;
    }

    public BigDecimal getEntrustSum() {
        return entrustSum;
    }

    public void setEntrustSum(BigDecimal entrustSum) {
        this.entrustSum = entrustSum;
    }

    public BigDecimal getSurplusEntrustCount() {
        return surplusEntrustCount;
    }

    public void setSurplusEntrustCount(BigDecimal surplusEntrustCount) {
        this.surplusEntrustCount = surplusEntrustCount;
    }

    public BigDecimal getTransactionSum() {
        return transactionSum;
    }

    public void setTransactionSum(BigDecimal transactionSum) {
        this.transactionSum = transactionSum;
    }

    public Date getEntrustTime() {
        return entrustTime;
    }

    public void setEntrustTime(Date entrustTime) {
        this.entrustTime = entrustTime;
    }

    public BigDecimal getTransactionFee() {
        return transactionFee;
    }

    public void setTransactionFee(BigDecimal transactionFee) {
        this.transactionFee = transactionFee;
    }

    public Integer getEntrustWay() {
        return entrustWay;
    }

    public void setEntrustWay(Integer entrustWay) {
        this.entrustWay = entrustWay;
    }
}
