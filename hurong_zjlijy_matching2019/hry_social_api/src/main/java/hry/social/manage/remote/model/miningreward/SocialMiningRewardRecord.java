/**
 * Copyright:
 *
 * @author: javalx
 * @version: V1.0
 * @Date: 2019-06-10 11:05:17
 */
package hry.social.manage.remote.model.miningreward;


import hry.bean.BaseModel;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;

/**
 * <p> SocialMiningRewardRecord </p>
 * 挖矿奖励记录表
 *
 * @author: javalx
 * @Date :          2019-06-10 11:05:17
 */
@Table(name = "social_mining_reward_record")
public class SocialMiningRewardRecord extends BaseModel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;  //主键

    @Column(name = "coinCode")
    private String coinCode;  //奖励币种

    @Column(name = "baseNum")
    private BigDecimal baseNum;  //奖励基数

    @Column(name = "forceRatio")
    private BigDecimal forceRatio;  //算力占比

    @Column(name = "rewardNum")
    private BigDecimal rewardNum;  //奖励数量

    @Column(name = "rewardType")
    private Integer rewardType;  //奖励类型 1:算力奖励 2:任务奖励 3:矿机奖励

    @Column(name = "rewardSource")
    private String rewardSource;  //奖励来源

    @Column(name = "gatherNum")
    private BigDecimal gatherNum;  //本人收取量

    @Column(name = "states")
    private Integer states;  //状态 0:已生成 1:已缓存 2:已收取

    @Column(name = "gatherTime")
    private Date gatherTime;  //本人收取时间

    @Column(name = "customerId")
    private Long customerId;  //奖励用户ID

    @Transient
    private String name;//用户昵称

    @Transient
    private String mobilePhone;//用户手机号

    @Transient
    private String surname;//用户姓氏

    @Transient
    private String trueName;//用户名字

    @Transient
    private BigDecimal Num;//收取次数

    public String getRewardSource() {
        return rewardSource;
    }

    public void setRewardSource(String rewardSource) {
        this.rewardSource = rewardSource;
    }

    public BigDecimal getNum() {
        return Num;
    }

    public void setNum(BigDecimal num) {
        Num = num;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    /**
     * <p>主键</p>
     *
     * @author: javalx
     * @return: Long
     * @Date :   2019-06-10 11:05:17
     */
    public Long getId() {
        return id;
    }

    /**
     * <p>主键</p>
     *
     * @author: javalx
     * @param: @param id
     * @return: void
     * @Date :   2019-06-10 11:05:17
     */
    public void setId(Long id) {
        this.id = id;
    }


    /**
     * <p>奖励币种</p>
     *
     * @author: javalx
     * @return: String
     * @Date :   2019-06-10 11:05:17
     */
    public String getCoinCode() {
        return coinCode;
    }

    /**
     * <p>奖励币种</p>
     *
     * @author: javalx
     * @param: @param coinCode
     * @return: void
     * @Date :   2019-06-10 11:05:17
     */
    public void setCoinCode(String coinCode) {
        this.coinCode = coinCode;
    }


    /**
     * <p>奖励基数</p>
     *
     * @author: javalx
     * @return: BigDecimal
     * @Date :   2019-06-10 11:05:17
     */
    public BigDecimal getBaseNum() {
        return baseNum;
    }

    /**
     * <p>奖励基数</p>
     *
     * @author: javalx
     * @param: @param baseNum
     * @return: void
     * @Date :   2019-06-10 11:05:17
     */
    public void setBaseNum(BigDecimal baseNum) {
        this.baseNum = baseNum;
    }


    /**
     * <p>算力占比</p>
     *
     * @author: javalx
     * @return: BigDecimal
     * @Date :   2019-06-10 11:05:17
     */
    public BigDecimal getForceRatio() {
        return forceRatio;
    }

    /**
     * <p>算力占比</p>
     *
     * @author: javalx
     * @param: @param forceRatio
     * @return: void
     * @Date :   2019-06-10 11:05:17
     */
    public void setForceRatio(BigDecimal forceRatio) {
        this.forceRatio = forceRatio;
    }


    /**
     * <p>奖励数量</p>
     *
     * @author: javalx
     * @return: BigDecimal
     * @Date :   2019-06-10 11:05:17
     */
    public BigDecimal getRewardNum() {
        return rewardNum;
    }

    /**
     * <p>奖励数量</p>
     *
     * @author: javalx
     * @param: @param rewardNum
     * @return: void
     * @Date :   2019-06-10 11:05:17
     */
    public void setRewardNum(BigDecimal rewardNum) {
        this.rewardNum = rewardNum;
    }


    /**
     * <p>奖励类型 1:算力奖励 2:任务奖励 3:矿机奖励</p>
     *
     * @author: javalx
     * @return: Integer
     * @Date :   2019-06-10 11:05:17
     */
    public Integer getRewardType() {
        return rewardType;
    }

    /**
     * <p>奖励类型 1:算力奖励 2:任务奖励 3:矿机奖励</p>
     *
     * @author: javalx
     * @param: @param rewardType
     * @return: void
     * @Date :   2019-06-10 11:05:17
     */
    public void setRewardType(Integer rewardType) {
        this.rewardType = rewardType;
    }


    /**
     * <p>本人收取量</p>
     *
     * @author: javalx
     * @return: BigDecimal
     * @Date :   2019-06-10 11:05:17
     */
    public BigDecimal getGatherNum() {
        return gatherNum;
    }

    /**
     * <p>本人收取量</p>
     *
     * @author: javalx
     * @param: @param gatherNum
     * @return: void
     * @Date :   2019-06-10 11:05:17
     */
    public void setGatherNum(BigDecimal gatherNum) {
        this.gatherNum = gatherNum;
    }


    /**
     * <p>状态 0:已生成 1:已缓存 2:已收取</p>
     *
     * @author: javalx
     * @return: Integer
     * @Date :   2019-06-10 11:05:17
     */
    public Integer getStates() {
        return states;
    }

    /**
     * <p>状态 0:已生成 1:已缓存 2:已收取</p>
     *
     * @author: javalx
     * @param: @param states
     * @return: void
     * @Date :   2019-06-10 11:05:17
     */
    public void setStates(Integer states) {
        this.states = states;
    }


    /**
     * <p>本人收取时间</p>
     *
     * @author: javalx
     * @return: Date
     * @Date :   2019-06-10 11:05:17
     */
    public Date getGatherTime() {
        return gatherTime;
    }

    /**
     * <p>本人收取时间</p>
     *
     * @author: javalx
     * @param: @param gatherTime
     * @return: void
     * @Date :   2019-06-10 11:05:17
     */
    public void setGatherTime(Date gatherTime) {
        this.gatherTime = gatherTime;
    }


    /**
     * <p>奖励用户ID</p>
     *
     * @author: javalx
     * @return: Long
     * @Date :   2019-06-10 11:05:17
     */
    public Long getCustomerId() {
        return customerId;
    }

    /**
     * <p>奖励用户ID</p>
     *
     * @author: javalx
     * @param: @param customerId
     * @return: void
     * @Date :   2019-06-10 11:05:17
     */
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }


    @Override
    public String toString() {
        return "SocialMiningRewardRecord{" +
                "id=" + id +
                ", coinCode='" + coinCode + '\'' +
                ", baseNum=" + baseNum +
                ", forceRatio=" + forceRatio +
                ", rewardNum=" + rewardNum +
                ", rewardType=" + rewardType +
                ", gatherNum=" + gatherNum +
                ", states=" + states +
                ", gatherTime=" + gatherTime +
                ", customerId=" + customerId +
                ", name='" + name + '\'' +
                ", mobilePhone='" + mobilePhone + '\'' +
                ", surname='" + surname + '\'' +
                ", trueName='" + trueName + '\'' +
                '}';
    }
}
