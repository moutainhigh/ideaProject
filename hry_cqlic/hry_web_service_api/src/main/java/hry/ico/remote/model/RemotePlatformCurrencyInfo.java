package hry.ico.remote.model;

import java.io.Serializable;

public class RemotePlatformCurrencyInfo implements Serializable{

    private String coinCode;//币种Code
    private String issueNum;//发行数量
    private String saleNum;//可售数量
    private String issuePrice;//货币价格

    public String getCoinCode() {
        return coinCode;
    }

    public void setCoinCode(String coinCode) {
        this.coinCode = coinCode;
    }

    public String getIssueNum() {
        return issueNum;
    }

    public void setIssueNum(String issueNum) {
        this.issueNum = issueNum;
    }

    public String getSaleNum() {
        return saleNum;
    }

    public void setSaleNum(String saleNum) {
        this.saleNum = saleNum;
    }

    public String getIssuePrice() {
        return issuePrice;
    }

    public void setIssuePrice(String issuePrice) {
        this.issuePrice = issuePrice;
    }
}
