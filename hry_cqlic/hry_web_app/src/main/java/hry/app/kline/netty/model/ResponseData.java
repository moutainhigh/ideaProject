package hry.app.kline.netty.model;

import java.math.BigDecimal;

/**
 * Copyright:   互融云
 *
 * @author: StayBlank
 * @version: V6.0
 * @Date: 2018/12/5 20:23
 */
public class ResponseData {
    private BigDecimal base_vol;
    private BigDecimal close;
    private BigDecimal count;
    private BigDecimal high;
    private BigDecimal id;
    private BigDecimal low;
    private BigDecimal open;
    private BigDecimal quote_vol;
    private BigDecimal seq;
    private String type;

    public BigDecimal getBase_vol() {
        return base_vol;
    }

    public void setBase_vol(BigDecimal base_vol) {
        this.base_vol = base_vol;
    }

    public BigDecimal getClose() {
        return close;
    }

    public void setClose(BigDecimal close) {
        this.close = close;
    }

    public BigDecimal getCount() {
        return count;
    }

    public void setCount(BigDecimal count) {
        this.count = count;
    }

    public BigDecimal getHigh() {
        return high;
    }

    public void setHigh(BigDecimal high) {
        this.high = high;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getLow() {
        return low;
    }

    public void setLow(BigDecimal low) {
        this.low = low;
    }

    public BigDecimal getOpen() {
        return open;
    }

    public void setOpen(BigDecimal open) {
        this.open = open;
    }

    public BigDecimal getQuote_vol() {
        return quote_vol;
    }

    public void setQuote_vol(BigDecimal quote_vol) {
        this.quote_vol = quote_vol;
    }

    public BigDecimal getSeq() {
        return seq;
    }

    public void setSeq(BigDecimal seq) {
        this.seq = seq;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
