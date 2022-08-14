package hry.ico.remote.model;

import java.io.Serializable;

public class RemotePaymentAmount implements Serializable{

    private String payNumber;//支付数量
    private String payCoinCode;//支付币种名
    private String buyCoinCode;//购买币种名

    private String buyPrice;//购买定价

    public String getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(String buyPrice) {
        this.buyPrice = buyPrice;
    }

    public String getBuyCoinCode() {
        return buyCoinCode;
    }

    public void setBuyCoinCode(String buyCoinCode) {
        this.buyCoinCode = buyCoinCode;
    }

    public String getPayCoinCode() {
        return payCoinCode;
    }

    public void setPayCoinCode(String payCoinCode) {
        this.payCoinCode = payCoinCode;
    }

    public String getPayNumber() {
        return payNumber;
    }

    public void setPayNumber(String payNumber) {
        this.payNumber = payNumber;
    }
}
