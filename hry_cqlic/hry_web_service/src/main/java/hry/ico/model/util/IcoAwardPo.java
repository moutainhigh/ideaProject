package hry.ico.model.util;

import java.io.Serializable;
import java.math.BigDecimal;

public class IcoAwardPo implements Serializable{
    private   Long customerId;
    private BigDecimal currentLockSum;  //当前锁仓总数

    private String account_type;//（0101 锁仓奖励，0102 注册赠送，0201 锁仓扣除，0202 释放扣除)

    private Long time;//当前时间

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public String getAccount_type() {
        return account_type;
    }

    public void setAccount_type(String account_type) {
        this.account_type = account_type;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public BigDecimal getCurrentLockSum() {
        return currentLockSum;
    }

    public void setCurrentLockSum(BigDecimal currentLockSum) {
        this.currentLockSum = currentLockSum;
    }
}
