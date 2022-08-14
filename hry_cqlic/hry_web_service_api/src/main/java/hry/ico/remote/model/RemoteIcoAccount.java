package hry.ico.remote.model;

import javax.persistence.Column;
import java.io.Serializable;
import java.math.BigDecimal;

public class RemoteIcoAccount implements Serializable {

    private String coinCode;  //币Code

    private BigDecimal totalMoney;

    private BigDecimal storageMoney;  //存储总额

    private BigDecimal coldMoney;  //冻结总额

    private BigDecimal hotMoney;  //流通总额

    private  BigDecimal lockMoney;//锁仓待释放

    private int keepDigit; //保留小数位数

    private  BigDecimal accountAtio;//自身锁仓比率 自身锁仓数/所有用户的总锁仓数)*40%*(100%+等级加持百分比)=锁仓比

    private String picturePath;

    public BigDecimal getAccountAtio() {
        return accountAtio;
    }

    public void setAccountAtio(BigDecimal accountAtio) {
        this.accountAtio = accountAtio;
    }

    public int getKeepDigit() {
        return keepDigit;
    }

    public void setKeepDigit(int keepDigit) {
        this.keepDigit = keepDigit;
    }

    public BigDecimal getLockMoney() {
        return lockMoney;
    }

    public void setLockMoney(BigDecimal lockMoney) {
        this.lockMoney = lockMoney;
    }

    public String getCoinCode() {
        return coinCode;
    }

    public void setCoinCode(String coinCode) {
        this.coinCode = coinCode;
    }

    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }

    public BigDecimal getStorageMoney() {
        return storageMoney;
    }

    public void setStorageMoney(BigDecimal storageMoney) {
        this.storageMoney = storageMoney;
    }

    public BigDecimal getColdMoney() {
        return coldMoney;
    }

    public void setColdMoney(BigDecimal coldMoney) {
        this.coldMoney = coldMoney;
    }

    public BigDecimal getHotMoney() {
        return hotMoney;
    }

    public void setHotMoney(BigDecimal hotMoney) {
        this.hotMoney = hotMoney;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }
}
