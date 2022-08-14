/**
 * 111
 */

package hry.klg.remote.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class RemoteSellTransaction  implements Serializable {

    private Long id;  //

    private Long customerId;  //用户id

    private Integer customerGrade;  //排单时用户等级

    private String transactionNum;  //流水号

    private Long accountId;  //数字货币账户id

    private String coinCode;  //币种

    private BigDecimal usdtMoney;  //卖出后获取瓜分后总USDT

    private BigDecimal smeMoney;  //卖出平台币金额

    private BigDecimal candySmeMoney;  //当前应获糖果总金额(SME）

    private BigDecimal smeusdtRate;  //排队时SME-USDT汇率

    private Integer status;  //1排队中 2排队成功 3待收款 4已完成

    private Long userId;  //操作人id

    private String remark;  //

    private Long timeStamp;  //排队开始时间戳

    private BigDecimal oneselfRate;  //本人获取糖果比例

    private BigDecimal platformRate;  //平台获取糖果比例

    private BigDecimal seePointRate;  //见点糖果比例

    private BigDecimal gradationRate;  //级差糖果比例

    private BigDecimal prizeRate;  //周奖糖果比例

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

    public Integer getCustomerGrade() {
        return customerGrade;
    }

    public void setCustomerGrade(Integer customerGrade) {
        this.customerGrade = customerGrade;
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

    public BigDecimal getUsdtMoney() {
        return usdtMoney;
    }

    public void setUsdtMoney(BigDecimal usdtMoney) {
        this.usdtMoney = usdtMoney;
    }

    public BigDecimal getSmeMoney() {
        return smeMoney;
    }

    public void setSmeMoney(BigDecimal smeMoney) {
        this.smeMoney = smeMoney;
    }

    public BigDecimal getCandySmeMoney() {
        return candySmeMoney;
    }

    public void setCandySmeMoney(BigDecimal candySmeMoney) {
        this.candySmeMoney = candySmeMoney;
    }

    public BigDecimal getSmeusdtRate() {
        return smeusdtRate;
    }

    public void setSmeusdtRate(BigDecimal smeusdtRate) {
        this.smeusdtRate = smeusdtRate;
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

    public BigDecimal getOneselfRate() {
        return oneselfRate;
    }

    public void setOneselfRate(BigDecimal oneselfRate) {
        this.oneselfRate = oneselfRate;
    }

    public BigDecimal getPlatformRate() {
        return platformRate;
    }

    public void setPlatformRate(BigDecimal platformRate) {
        this.platformRate = platformRate;
    }

    public BigDecimal getSeePointRate() {
        return seePointRate;
    }

    public void setSeePointRate(BigDecimal seePointRate) {
        this.seePointRate = seePointRate;
    }

    public BigDecimal getGradationRate() {
        return gradationRate;
    }

    public void setGradationRate(BigDecimal gradationRate) {
        this.gradationRate = gradationRate;
    }

    public BigDecimal getPrizeRate() {
        return prizeRate;
    }

    public void setPrizeRate(BigDecimal prizeRate) {
        this.prizeRate = prizeRate;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
}
