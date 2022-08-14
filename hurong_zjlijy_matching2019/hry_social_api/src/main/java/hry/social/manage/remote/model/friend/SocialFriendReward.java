/**
 * Copyright:
 *
 * @author: javalx
 * @version: V1.0
 * @Date: 2019-06-04 10:07:52
 */
package hry.social.manage.remote.model.friend;


import hry.bean.BaseModel;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p> SocialFriendReward </p>
 *
 * @author: javalx
 * @Date :          2019-06-04 10:07:52
 */
@Table(name = "social_friend_reward")
public class SocialFriendReward extends BaseModel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;  //主键

    @Column(name = "circleId")
    private Long circleId;  //帖子ID(social_friend_circle)

    @Column(name = "customerId")
    private Long customerId;  //发帖人ID

    @Column(name = "rewardId")
    private Long rewardId;  //打赏人ID

    @Column(name = "rewardTime")
    private Date rewardTime;  //打赏时间

    @Column(name = "coinCode")
    private String coinCode;  //打赏币种

    @Column(name = "rewardNum")
    private BigDecimal rewardNum;  //打赏金额

    @Column(name = "actualReward")
    private BigDecimal actualReward;  //发帖人奖金

    @Column(name = "fee")
    private BigDecimal fee;  //平台收入


    /**
     * <p>主键</p>
     *
     * @author: javalx
     * @return: Long
     * @Date :   2019-06-04 10:07:52
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
     * @Date :   2019-06-04 10:07:52
     */
    public void setId(Long id) {
        this.id = id;
    }


    /**
     * <p>帖子ID(social_friend_circle)</p>
     *
     * @author: javalx
     * @return: Long
     * @Date :   2019-06-04 10:07:52
     */
    public Long getCircleId() {
        return circleId;
    }

    /**
     * <p>帖子ID(social_friend_circle)</p>
     *
     * @author: javalx
     * @param: @param circleId
     * @return: void
     * @Date :   2019-06-04 10:07:52
     */
    public void setCircleId(Long circleId) {
        this.circleId = circleId;
    }


    /**
     * <p>发帖人ID</p>
     *
     * @author: javalx
     * @return: Long
     * @Date :   2019-06-04 10:07:52
     */
    public Long getCustomerId() {
        return customerId;
    }

    /**
     * <p>发帖人ID</p>
     *
     * @author: javalx
     * @param: @param customerId
     * @return: void
     * @Date :   2019-06-04 10:07:52
     */
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }


    /**
     * <p>打赏人ID</p>
     *
     * @author: javalx
     * @return: Long
     * @Date :   2019-06-04 10:07:52
     */
    public Long getRewardId() {
        return rewardId;
    }

    /**
     * <p>打赏人ID</p>
     *
     * @author: javalx
     * @param: @param rewardId
     * @return: void
     * @Date :   2019-06-04 10:07:52
     */
    public void setRewardId(Long rewardId) {
        this.rewardId = rewardId;
    }


    /**
     * <p>打赏时间</p>
     *
     * @author: javalx
     * @return: Date
     * @Date :   2019-06-04 10:07:52
     */
    public Date getRewardTime() {
        return rewardTime;
    }

    /**
     * <p>打赏时间</p>
     *
     * @author: javalx
     * @param: @param rewardTime
     * @return: void
     * @Date :   2019-06-04 10:07:52
     */
    public void setRewardTime(Date rewardTime) {
        this.rewardTime = rewardTime;
    }


    /**
     * <p>打赏币种</p>
     *
     * @author: javalx
     * @return: String
     * @Date :   2019-06-04 10:07:52
     */
    public String getCoinCode() {
        return coinCode;
    }

    /**
     * <p>打赏币种</p>
     *
     * @author: javalx
     * @param: @param coinCode
     * @return: void
     * @Date :   2019-06-04 10:07:52
     */
    public void setCoinCode(String coinCode) {
        this.coinCode = coinCode;
    }


    /**
     * <p>打赏金额</p>
     *
     * @author: javalx
     * @return: BigDecimal
     * @Date :   2019-06-04 10:07:52
     */
    public BigDecimal getRewardNum() {
        return rewardNum;
    }

    /**
     * <p>打赏金额</p>
     *
     * @author: javalx
     * @param: @param rewardNum
     * @return: void
     * @Date :   2019-06-04 10:07:52
     */
    public void setRewardNum(BigDecimal rewardNum) {
        this.rewardNum = rewardNum;
    }


    /**
     * <p>发帖人奖金</p>
     *
     * @author: javalx
     * @return: BigDecimal
     * @Date :   2019-06-04 10:07:52
     */
    public BigDecimal getActualReward() {
        return actualReward;
    }

    /**
     * <p>发帖人奖金</p>
     *
     * @author: javalx
     * @param: @param actualReward
     * @return: void
     * @Date :   2019-06-04 10:07:52
     */
    public void setActualReward(BigDecimal actualReward) {
        this.actualReward = actualReward;
    }


    /**
     * <p>平台收入</p>
     *
     * @author: javalx
     * @return: BigDecimal
     * @Date :   2019-06-04 10:07:52
     */
    public BigDecimal getFee() {
        return fee;
    }

    /**
     * <p>平台收入</p>
     *
     * @author: javalx
     * @param: @param fee
     * @return: void
     * @Date :   2019-06-04 10:07:52
     */
    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }


}
