/**
 * Copyright:
 *
 * @author: liuchenghui
 * @version: V1.0
 * @Date: 2018-09-14 20:19:30
 */
package hry.exchange.lock.model;

import hry.bean.BaseModel;
import hry.customer.person.model.AppPersonInfo;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * <p> ExDmLockRecord </p>
 *  用于锁仓记录和释放记录
 * @author: liuchenghui
 * @Date :  2018-09-14 20:19:30
 */
@Table(name = "ex_dm_lock_record")
public class ExDmLockRecord extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    //用户id
    @Column(name = "customerId")
    private Long customerId;

    //锁仓规则主键
    @Column(name = "lockId")
    private Long lockId;

    // 资金账户id
    @Column(name = "accountId")
    private Long accountId;

    //币种类型
    @Column(name = "coinCode")
    private String coinCode;

    //账户余额
    @Column(name = "accountBalance")
    private BigDecimal accountBalance;

    //冻结数量
    @Column(name = "coldNum")
    private BigDecimal coldNum;

    //已释放量
    @Column(name = "amountReleased")
    private BigDecimal amountReleased;

    //剩余释放量
    @Column(name = "remainingRelease")
    private BigDecimal remainingRelease;

    //解锁状态 1、未解锁 2、进行中 3、已完成; 默认是1
    @Column(name = "unlockState")
    private Integer unlockState;

    //解锁类型 1、自动解锁 2、手动解锁; 默认是1
    @Column(name="unlockType")
    private Integer unlockType;

    //释放方式：1、总释放次数 2、每次释放数量 3、每次释放比例
    @Column(name = "releaseMethod")
    private Integer releaseMethod;

    //释放方式对应的值
    @Column(name = "releaseMethodVal")
    private String releaseMethodVal;

    //本次释放量--废除
    @Column(name = "currentRelease")
    private BigDecimal currentRelease;

    //操作人
    @Column(name = "optUser")
    private String optUser;

    //操作类型 1、锁仓记录 2、释放记录 3、手动锁仓记录
    @Column(name = "optType")
    private Integer optType;

    // 交易订单号
    @Column(name = "transactionNum")
    private String transactionNum;

    // 定时器每次释放时间
    @Column(name = "releaseTime")
    private String releaseTime;

    // 定时器每次释放值（释放值=冻结数与释放方式值计算，最后一次释放全部）
    // 例如：冻结数10，释放方式按次数3次，释放值为10/3 = 3，最后一次释放4
    @Column(name = "releaseVal")
    private BigDecimal releaseVal;

    @Column(name = "saasId")
    private String saasId;

    @Transient // 不与数据库映射的字段
    private AppPersonInfo appPersonInfo;

    @Transient // 不与数据库映射的字段
    private ExDmLock exDmLock;

    public Long getId () {
        return id;
    }

    public void setId (Long id) {
        this.id = id;
    }

    public Long getCustomerId () {
        return customerId;
    }

    public void setCustomerId (Long customerId) {
        this.customerId = customerId;
    }

    public Long getLockId () {
        return lockId;
    }

    public void setLockId (Long lockId) {
        this.lockId = lockId;
    }

    public Long getAccountId () {
        return accountId;
    }

    public void setAccountId (Long accountId) {
        this.accountId = accountId;
    }

    public String getCoinCode () {
        return coinCode;
    }

    public void setCoinCode (String coinCode) {
        this.coinCode = coinCode;
    }

    public BigDecimal getAccountBalance () {
        return accountBalance;
    }

    public void setAccountBalance (BigDecimal accountBalance) {
        this.accountBalance = accountBalance;
    }

    public BigDecimal getColdNum () {
        return coldNum;
    }

    public void setColdNum (BigDecimal coldNum) {
        this.coldNum = coldNum;
    }

    public BigDecimal getAmountReleased () {
        return amountReleased;
    }

    public void setAmountReleased (BigDecimal amountReleased) {
        this.amountReleased = amountReleased;
    }

    public BigDecimal getRemainingRelease () {
        return remainingRelease;
    }

    public void setRemainingRelease (BigDecimal remainingRelease) {
        this.remainingRelease = remainingRelease;
    }

    public Integer getUnlockState () {
        return unlockState;
    }

    public void setUnlockState (Integer unlockState) {
        this.unlockState = unlockState;
    }

    public BigDecimal getCurrentRelease () {
        return currentRelease;
    }

    public void setCurrentRelease (BigDecimal currentRelease) {
        this.currentRelease = currentRelease;
    }

    public String getOptUser () {
        return optUser;
    }

    public void setOptUser (String optUser) {
        this.optUser = optUser;
    }

    public Integer getOptType () {
        return optType;
    }

    public void setOptType (Integer optType) {
        this.optType = optType;
    }

    public String getSaasId () {
        return saasId;
    }

    public void setSaasId (String saasId) {
        this.saasId = saasId;
    }

    public String getTransactionNum () {
        return transactionNum;
    }

    public void setTransactionNum (String transactionNum) {
        this.transactionNum = transactionNum;
    }

    public AppPersonInfo getAppPersonInfo () {
        return appPersonInfo;
    }

    public void setAppPersonInfo (AppPersonInfo appPersonInfo) {
        this.appPersonInfo = appPersonInfo;
    }

    public ExDmLock getExDmLock () {
        return exDmLock;
    }

    public void setExDmLock (ExDmLock exDmLock) {
        this.exDmLock = exDmLock;
    }

    public String getReleaseTime () {
        return releaseTime;
    }

    public void setReleaseTime (String releaseTime) {
        this.releaseTime = releaseTime;
    }

    public BigDecimal getReleaseVal () {
        return releaseVal;
    }

    public void setReleaseVal (BigDecimal releaseVal) {
        this.releaseVal = releaseVal;
    }

    public Integer getUnlockType () {
        return unlockType;
    }

    public void setUnlockType (Integer unlockType) {
        this.unlockType = unlockType;
    }

    public Integer getReleaseMethod () {
        return releaseMethod;
    }

    public void setReleaseMethod (Integer releaseMethod) {
        this.releaseMethod = releaseMethod;
    }

    public String getReleaseMethodVal () {
        return releaseMethodVal;
    }

    public void setReleaseMethodVal (String releaseMethodVal) {
        this.releaseMethodVal = releaseMethodVal;
    }
}
