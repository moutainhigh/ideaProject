package hry.licqb.record.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author zhouming
 * @date 2021/1/26 10:26
 * @Version 1.0
 */
public class UserAccount implements Serializable {
    private static final long serialVersionUID = -4825890686624512635L;

    private Long customerId;

    private String coinCode;

    private BigDecimal hotMoney;

    private BigDecimal coldMoney;

    private BigDecimal freezeMoney;

    private BigDecimal baseMoney;

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

    public BigDecimal getHotMoney() {
        return hotMoney;
    }

    public void setHotMoney(BigDecimal hotMoney) {
        this.hotMoney = hotMoney;
    }

    public BigDecimal getColdMoney() {
        return coldMoney;
    }

    public void setColdMoney(BigDecimal coldMoney) {
        this.coldMoney = coldMoney;
    }

    public BigDecimal getFreezeMoney() {
        return freezeMoney;
    }

    public void setFreezeMoney(BigDecimal freezeMoney) {
        this.freezeMoney = freezeMoney;
    }

    public BigDecimal getBaseMoney() {
        return baseMoney;
    }

    public void setBaseMoney(BigDecimal baseMoney) {
        this.baseMoney = baseMoney;
    }
}
