package hry.ico.remote.model;

import hry.bean.BaseModel;

import java.io.Serializable;
import java.math.BigDecimal;

public class RemoteIcoBuyOrder  extends BaseModel implements Serializable{

    private String orderNumber;  //订单号

    private String customerId;  //用户Id

    private String payCoinName;  //支付币种名称

    private String payCoinCode;  //支付币种code

    private BigDecimal payNumber;  //支付数量

    private BigDecimal buyPrice;  //购买定价

    private String buyCoinCode;  //购买币种名称

    private BigDecimal buyNumber;  //购买数量

    private BigDecimal poundageRate;  //手续费费率

    private BigDecimal poundage;  //手续费

    private String mobilePhone;   //用户手机号

    private String email;      	//用户email

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getPayCoinName() {
        return payCoinName;
    }

    public void setPayCoinName(String payCoinName) {
        this.payCoinName = payCoinName;
    }

    public String getPayCoinCode() {
        return payCoinCode;
    }

    public void setPayCoinCode(String payCoinCode) {
        this.payCoinCode = payCoinCode;
    }

    public BigDecimal getPayNumber() {
        return payNumber;
    }

    public void setPayNumber(BigDecimal payNumber) {
        this.payNumber = payNumber;
    }

    public BigDecimal getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(BigDecimal buyPrice) {
        this.buyPrice = buyPrice;
    }

    public String getBuyCoinCode() {
        return buyCoinCode;
    }

    public void setBuyCoinCode(String buyCoinCode) {
        this.buyCoinCode = buyCoinCode;
    }

    public BigDecimal getBuyNumber() {
        return buyNumber;
    }

    public void setBuyNumber(BigDecimal buyNumber) {
        this.buyNumber = buyNumber;
    }

    public BigDecimal getPoundageRate() {
        return poundageRate;
    }

    public void setPoundageRate(BigDecimal poundageRate) {
        this.poundageRate = poundageRate;
    }

    public BigDecimal getPoundage() {
        return poundage;
    }

    public void setPoundage(BigDecimal poundage) {
        this.poundage = poundage;
    }
}
