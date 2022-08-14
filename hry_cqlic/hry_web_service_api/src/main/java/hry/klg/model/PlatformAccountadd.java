/**
 * 111
 */

package hry.klg.model;

import java.io.Serializable;

public class PlatformAccountadd implements Serializable {

    private String serialNumber;//流水号

    private String accountType;//账户类型

    private String money;//金额 加减±表示

    private Integer type;//交易类型 101 转入102 转出103 充值 等

    private String remark;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
