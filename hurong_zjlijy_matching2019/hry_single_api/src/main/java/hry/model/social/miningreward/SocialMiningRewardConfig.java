/**
 * Copyright:
 *
 * @author: javalx
 * @version: V1.0
 * @Date: 2019-06-10 10:50:48
 */
package hry.model.social.miningreward;


import hry.bean.BaseModel;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p> SocialMiningRewardConfig </p>
 * 挖矿奖励收取记录表
 *
 * @author: javalx
 * @Date :          2019-06-10 10:50:48
 */
@Table(name = "social_mining_reward_config")
public class SocialMiningRewardConfig extends BaseModel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;  //主键

    @Column(name = "coinCode")
    private String coinCode;  //奖励币种

    @Column(name = "baseNum")
    private BigDecimal baseNum;  //奖励基数

    @Column(name = "rewardCondition")
    private BigDecimal rewardCondition;  //奖励条件（算力值）

    @Column(name = "keepRatio")
    private BigDecimal keepRatio;  //本人保底率

    @Column(name = "gatherRatio")
    private BigDecimal gatherRatio;  //好友收取率

    @Column(name = "interValue")
    private Integer interValue;  //奖励间隔值

    @Column(name = "timeUnit")
    private Integer timeUnit;  //间隔时间单位 1:分 2:时 3:天 4:周 5:月

    @Column(name = "states")
    private Integer states;  //状态 0:关闭 1:开启

    @Column(name = "openTime")
    private Date openTime;  //开启时间

    @Column(name = "endTime")
    private Date endTime;  //结束时间

    @Column(name = "maxUncollect")
    private Integer maxUncollect;  //最大未收取数

    public Integer getMaxUncollect() {
        return maxUncollect;
    }

    public void setMaxUncollect(Integer maxUncollect) {
        this.maxUncollect = maxUncollect;
    }

    @Override
    public String toString() {
        return "SocialMiningRewardConfig{" + "id=" + id + ", coinCode='" + coinCode + '\'' + ", baseNum=" + baseNum + ", rewardCondition=" + rewardCondition + ", keepRatio=" + keepRatio + ", gatherRatio=" + gatherRatio + ", interValue=" + interValue + ", timeUnit=" + timeUnit + ", states=" + states + ", openTime=" + openTime + ", endTime=" + endTime + '}';
    }

    /**
     * <p>主键</p>
     *
     * @author: javalx
     * @return: Long
     * @Date :   2019-06-10 10:50:48
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
     * @Date :   2019-06-10 10:50:48
     */
    public void setId(Long id) {
        this.id = id;
    }


    /**
     * <p>奖励币种</p>
     *
     * @author: javalx
     * @return: String
     * @Date :   2019-06-10 10:50:48
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
     * @Date :   2019-06-10 10:50:48
     */
    public void setCoinCode(String coinCode) {
        this.coinCode = coinCode;
    }


    /**
     * <p>奖励基数</p>
     *
     * @author: javalx
     * @return: BigDecimal
     * @Date :   2019-06-10 10:50:48
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
     * @Date :   2019-06-10 10:50:48
     */
    public void setBaseNum(BigDecimal baseNum) {
        this.baseNum = baseNum;
    }


    /**
     * <p>奖励条件（算力值）</p>
     *
     * @author: javalx
     * @return: BigDecimal
     * @Date :   2019-06-10 10:50:48
     */
    public BigDecimal getRewardCondition() {
        return rewardCondition;
    }

    /**
     * <p>奖励条件（算力值）</p>
     *
     * @author: javalx
     * @param: @param rewardCondition
     * @return: void
     * @Date :   2019-06-10 10:50:48
     */
    public void setRewardCondition(BigDecimal rewardCondition) {
        this.rewardCondition = rewardCondition;
    }


    /**
     * <p>本人保底率</p>
     *
     * @author: javalx
     * @return: BigDecimal
     * @Date :   2019-06-10 10:50:48
     */
    public BigDecimal getKeepRatio() {
        return keepRatio;
    }

    /**
     * <p>本人保底率</p>
     *
     * @author: javalx
     * @param: @param keepRatio
     * @return: void
     * @Date :   2019-06-10 10:50:48
     */
    public void setKeepRatio(BigDecimal keepRatio) {
        this.keepRatio = keepRatio;
    }


    /**
     * <p>好友收取率</p>
     *
     * @author: javalx
     * @return: BigDecimal
     * @Date :   2019-06-10 10:50:48
     */
    public BigDecimal getGatherRatio() {
        return gatherRatio;
    }

    /**
     * <p>好友收取率</p>
     *
     * @author: javalx
     * @param: @param gatherRatio
     * @return: void
     * @Date :   2019-06-10 10:50:48
     */
    public void setGatherRatio(BigDecimal gatherRatio) {
        this.gatherRatio = gatherRatio;
    }


    /**
     * <p>奖励间隔值</p>
     *
     * @author: javalx
     * @return: Integer
     * @Date :   2019-06-10 10:50:48
     */
    public Integer getInterValue() {
        return interValue;
    }

    /**
     * <p>奖励间隔值</p>
     *
     * @author: javalx
     * @param: @param interValue
     * @return: void
     * @Date :   2019-06-10 10:50:48
     */
    public void setInterValue(Integer interValue) {
        this.interValue = interValue;
    }


    /**
     * <p>间隔时间单位 1:分 2:时 3:天 4:周 5:月</p>
     *
     * @author: javalx
     * @return: Integer
     * @Date :   2019-06-10 10:50:48
     */
    public Integer getTimeUnit() {
        return timeUnit;
    }

    /**
     * <p>间隔时间单位 1:分 2:时 3:天 4:周 5:月</p>
     *
     * @author: javalx
     * @param: @param timeUnit
     * @return: void
     * @Date :   2019-06-10 10:50:48
     */
    public void setTimeUnit(Integer timeUnit) {
        this.timeUnit = timeUnit;
    }


    /**
     * <p>状态 0:关闭 1:开启</p>
     *
     * @author: javalx
     * @return: Integer
     * @Date :   2019-06-10 10:50:48
     */
    public Integer getStates() {
        return states;
    }

    /**
     * <p>状态 0:关闭 1:开启</p>
     *
     * @author: javalx
     * @param: @param states
     * @return: void
     * @Date :   2019-06-10 10:50:48
     */
    public void setStates(Integer states) {
        this.states = states;
    }


    /**
     * <p>开启时间</p>
     *
     * @author: javalx
     * @return: Date
     * @Date :   2019-06-10 10:50:48
     */
    public Date getOpenTime() {
        return openTime;
    }

    /**
     * <p>开启时间</p>
     *
     * @author: javalx
     * @param: @param openTime
     * @return: void
     * @Date :   2019-06-10 10:50:48
     */
    public void setOpenTime(Date openTime) {
        this.openTime = openTime;
    }


    /**
     * <p>结束时间</p>
     *
     * @author: javalx
     * @return: Date
     * @Date :   2019-06-10 10:50:48
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * <p>结束时间</p>
     *
     * @author: javalx
     * @param: @param endTime
     * @return: void
     * @Date :   2019-06-10 10:50:48
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }


}
