/**
 * 111
 */

package hry.klg.remote.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class RemoteAssetsRecord implements Serializable {
    private Long id;  //
    private Long customerId;  //用户Id

    private Long accountId;  //币账户Id

    private String serialNumber;  //流水号

    private Integer accountType;  //账户类型 1.热账户 2.冷账户

    private String coinCode;  //币种Code

    private BigDecimal changeCount;  //交易量

    private Integer changeType;  //变动类型 1加 2减

    private Integer triggerPoint;  //触发点

    private String triggerSerialNumber;  //触发点流水号

    private String remark;  //备注

    private Date created; //创建时间

    private Date modified; //修改时间

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Integer getAccountType() {
        return accountType;
    }

    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }

    public String getCoinCode() {
        return coinCode;
    }

    public void setCoinCode(String coinCode) {
        this.coinCode = coinCode;
    }

    public BigDecimal getChangeCount() {
        return changeCount;
    }

    public void setChangeCount(BigDecimal changeCount) {
        this.changeCount = changeCount;
    }

    public Integer getChangeType() {
        return changeType;
    }

    public void setChangeType(Integer changeType) {
        this.changeType = changeType;
    }

    public Integer getTriggerPoint() {
        return triggerPoint;
    }

    public void setTriggerPoint(Integer triggerPoint) {
        this.triggerPoint = triggerPoint;
    }

    public String getTriggerSerialNumber() {
        return triggerSerialNumber;
    }

    public void setTriggerSerialNumber(String triggerSerialNumber) {
        this.triggerSerialNumber = triggerSerialNumber;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
