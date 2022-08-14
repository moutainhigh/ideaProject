package hry.ico.remote.model;

import hry.bean.BaseModel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class RemoteIcoTransactionRecord extends BaseModel implements Serializable{
    private String id;  //

    private String projectNumber;  //流水号

    private Integer type;  //类型( 11.锁仓12.释放 13锁仓扣币21转账入22转账出31分红32推荐奖励 41.充币42.提币51.买入52.卖出)

    private String coinCode;  //币种Code

    private BigDecimal transactionCount;  //交易量

    private BigDecimal totalMoney;  //总数量

    private BigDecimal hotMoney;  //可用数量

    private BigDecimal coldMoney;  //冻结数量

    private Long customerId;  //用户Id

    private Integer state;  //状态类型(101冻结 默认102解冻201.支出202.收入)

    private String remark;  //备注

    private Object obj;

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectNumber() {
        return projectNumber;
    }

    public void setProjectNumber(String projectNumber) {
        this.projectNumber = projectNumber;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getCoinCode() {
        return coinCode;
    }

    public void setCoinCode(String coinCode) {
        this.coinCode = coinCode;
    }

    public BigDecimal getTransactionCount() {
        return transactionCount;
    }

    public void setTransactionCount(BigDecimal transactionCount) {
        this.transactionCount = transactionCount;
    }

    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
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

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
