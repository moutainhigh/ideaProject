/**
 * Copyright:
 *
 * @author: liuchenghui
 * @version: V1.0
 * @Date: 2018-06-29 11:44:56
 */
package hry.exchange.lock.model;


import hry.bean.BaseModel;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p> ExDmLock </p>
 * @author: liuchenghui
 * @Date :          2018-06-29 11:44:56  
 */
@Table(name = "ex_dm_lock")
public class ExDmLock extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;  //

    //币种代码
    @Column(name = "coinCode")
    private String coinCode;

    //账户锁仓起点额度（个）
    @Column(name = "lockStartLimit")
    private BigDecimal lockStartLimit;

    //锁仓方式：1、持有数量 2、起点额度外
    @Column(name = "lockMethod")
    private Integer lockMethod;

    //锁仓比例
    @Column(name = "lockRatio")
    private BigDecimal lockRatio;

    //规则开始时间
    @Column(name = "lockStartTime")
    private Date lockStartTime;

    //规则持续周期
    @Column(name = "lockDuration")
    private BigDecimal lockDuration;

    //锁仓周期
    @Column(name = "lockCycle")
    private BigDecimal lockCycle;

    //释放方式：1、总释放次数 2、每次释放数量 3、每次释放比例
    @Column(name = "releaseMethod")
    private Integer releaseMethod;

    //释放方式对应的值
    @Column(name = "releaseMethodVal")
    private String releaseMethodVal;

    //是否流通： 1、是 0、否
    @Column(name = "isCirculation")
    private Integer isCirculation;

    //日释放用户比例
    @Column(name = "dailyReleaseOfUserRatio")
    private BigDecimal dailyReleaseOfUserRatio;

    //释放排序方式： 1、按注册时间
    @Column(name = "releaseSortMethod")
    private Integer releaseSortMethod;

    // 锁仓开关 0、关闭 1、开启
    @Column(name = "isLock")
    private Integer isLock;

    // 操作人
    @Column(name = "optUser")
    private String optUser;

    // 规则是否有效，0：有效，1：无效 默认有效，只有当规则周期结束时才无效
    @Column(name = "isValid")
    private Integer isValid;

    // 锁仓释放频率/间隔
    @Column(name = "releaseFrequency")
    private  BigDecimal releaseFrequency;

    @Column(name = "saasId")
    private String saasId;  //

    public Long getId () {
        return id;
    }

    public void setId (Long id) {
        this.id = id;
    }

    public String getCoinCode () {
        return coinCode;
    }

    public void setCoinCode (String coinCode) {
        this.coinCode = coinCode;
    }

    public BigDecimal getLockStartLimit () {
        return lockStartLimit;
    }

    public void setLockStartLimit (BigDecimal lockStartLimit) {
        this.lockStartLimit = lockStartLimit;
    }

    public Integer getLockMethod () {
        return lockMethod;
    }

    public void setLockMethod (Integer lockMethod) {
        this.lockMethod = lockMethod;
    }

    public BigDecimal getLockRatio () {
        return lockRatio;
    }

    public void setLockRatio (BigDecimal lockRatio) {
        this.lockRatio = lockRatio;
    }

    public Date getLockStartTime () {
        return lockStartTime;
    }

    public void setLockStartTime (Date lockStartTime) {
        this.lockStartTime = lockStartTime;
    }

    public BigDecimal getLockDuration () {
        return lockDuration;
    }

    public void setLockDuration (BigDecimal lockDuration) {
        this.lockDuration = lockDuration;
    }

    public BigDecimal getLockCycle () {
        return lockCycle;
    }

    public void setLockCycle (BigDecimal lockCycle) {
        this.lockCycle = lockCycle;
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

    public Integer getIsCirculation () {
        return isCirculation;
    }

    public void setIsCirculation (Integer isCirculation) {
        this.isCirculation = isCirculation;
    }

    public BigDecimal getDailyReleaseOfUserRatio () {
        return dailyReleaseOfUserRatio;
    }

    public void setDailyReleaseOfUserRatio (BigDecimal dailyReleaseOfUserRatio) {
        this.dailyReleaseOfUserRatio = dailyReleaseOfUserRatio;
    }

    public Integer getReleaseSortMethod () {
        return releaseSortMethod;
    }

    public void setReleaseSortMethod (Integer releaseSortMethod) {
        this.releaseSortMethod = releaseSortMethod;
    }

    public String getSaasId () {
        return saasId;
    }

    public void setSaasId (String saasId) {
        this.saasId = saasId;
    }

    public Integer getIsLock () {
        return isLock;
    }

    public void setIsLock (Integer isLock) {
        this.isLock = isLock;
    }

    public String getOptUser () {
        return optUser;
    }

    public void setOptUser (String optUser) {
        this.optUser = optUser;
    }

    public Integer getIsValid () {
        return isValid;
    }

    public void setIsValid (Integer isValid) {
        this.isValid = isValid;
    }

    public BigDecimal getReleaseFrequency () {
        return releaseFrequency;
    }

    public void setReleaseFrequency (BigDecimal releaseFrequency) {
        this.releaseFrequency = releaseFrequency;
    }
}
