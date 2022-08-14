/**
 * 111
 */

package hry.klg.remote.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class RemoteBuyTransaction implements Serializable {

    private Long id;  //

    private Long customerId;  //用户id

    private String transactionNum;  //流水号

    private Long accountId;  //数字货币账户id

    private String coinCode;  //币种

    private BigDecimal smeMoney;  //买入平台币金额

    private BigDecimal usdtMoney;  //需要支付USDT金额

    private BigDecimal firstMoney;  //保证金

    private BigDecimal lastMoney;  //尾款

    private BigDecimal interestMoney;  //利息

    private Integer trusteeshipStatus;  //托管状态：1否 2是

    private Integer rushOrders;  //是否抢单：1否 2是

    private Integer eatStatus;  //是否平台吃单：1否 2是

    private Integer status;  //订单状态：1排队中 2排队成功待支付 3待收款 4已成交 5已作废  6平台吃单待支付

    private Long userId;  //操作人id


    private String remark;  //

    private Long timeStamp;  //排队开始时间戳

    private String accountName;//账户名

    private String mobilePhone;

    private String email;
    private Date created;
    
private String saasId;  //saasId
    
    public String getSaasId() {
		return saasId;
	}

	public void setSaasId(String saasId) {
		this.saasId = saasId;
	}
private Date modified;
    

    public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

    public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

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

    public String getTransactionNum() {
        return transactionNum;
    }

    public void setTransactionNum(String transactionNum) {
        this.transactionNum = transactionNum;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getCoinCode() {
        return coinCode;
    }

    public void setCoinCode(String coinCode) {
        this.coinCode = coinCode;
    }

    public BigDecimal getSmeMoney() {
        return smeMoney;
    }

    public void setSmeMoney(BigDecimal smeMoney) {
        this.smeMoney = smeMoney;
    }

    public BigDecimal getUsdtMoney() {
        return usdtMoney;
    }

    public void setUsdtMoney(BigDecimal usdtMoney) {
        this.usdtMoney = usdtMoney;
    }

    public BigDecimal getFirstMoney() {
        return firstMoney;
    }

    public void setFirstMoney(BigDecimal firstMoney) {
        this.firstMoney = firstMoney;
    }

    public BigDecimal getLastMoney() {
        return lastMoney;
    }

    public void setLastMoney(BigDecimal lastMoney) {
        this.lastMoney = lastMoney;
    }

    public BigDecimal getInterestMoney() {
        return interestMoney;
    }

    public void setInterestMoney(BigDecimal interestMoney) {
        this.interestMoney = interestMoney;
    }

    public Integer getTrusteeshipStatus() {
        return trusteeshipStatus;
    }

    public void setTrusteeshipStatus(Integer trusteeshipStatus) {
        this.trusteeshipStatus = trusteeshipStatus;
    }

    public Integer getRushOrders() {
        return rushOrders;
    }

    public void setRushOrders(Integer rushOrders) {
        this.rushOrders = rushOrders;
    }

    public Integer getEatStatus() {
        return eatStatus;
    }

    public void setEatStatus(Integer eatStatus) {
        this.eatStatus = eatStatus;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
}
