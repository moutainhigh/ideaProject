/**
 * Copyright:
 *
 * @author: lixin
 * @version: V1.0
 * @Date: 2019-05-13 10:21:08
 */
package hry.social.manage.remote.model.sns;


import hry.bean.BaseModel;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * <p> SocialSnsReward </p>
 * 帖子打赏记录实体
 *
 * @author: lixin
 * @Date : 2019-05-13 10:21:08
 */
@Table(name = "social_sns_reward")
public class SocialSnsReward extends BaseModel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;  //ID

    @Column(name = "postsId")
    private Long postsId;  //帖子ID

    @Column(name = "customerId")
    private Long customerId;  //发帖人ID

    @Column(name = "rewardId")
    private Long rewardId;  //打赏人ID

    @Column(name = "coinCode")
    private String coinCode;  //打赏币种

    @Column(name = "rewardNum")
    private BigDecimal rewardNum;  //打赏金额

    @Column(name = "actualReward")
    private BigDecimal actualReward;  //发帖人奖励

    @Column(name = "fee")
    private BigDecimal fee;  //平台手续费


    /**
     * <p>ID</p>
     *
     * @author: lixin
     * @return: Long
     * @Date :   2019-05-13 10:21:08
     */
    public Long getId() {
        return id;
    }

    /**
     * <p>ID</p>
     *
     * @author: lixin
     * @param: @param id
     * @return: void
     * @Date :   2019-05-13 10:21:08
     */
    public void setId(Long id) {
        this.id = id;
    }


    /**
     * <p>帖子ID</p>
     *
     * @author: lixin
     * @return: Long
     * @Date :   2019-05-13 10:21:08
     */
    public Long getPostsId() {
        return postsId;
    }

    /**
     * <p>帖子ID</p>
     *
     * @author: lixin
     * @param: @param postsId
     * @return: void
     * @Date :   2019-05-13 10:21:08
     */
    public void setPostsId(Long postsId) {
        this.postsId = postsId;
    }


    /**
     * <p>发帖人ID</p>
     *
     * @author: lixin
     * @return: Long
     * @Date :   2019-05-13 10:21:08
     */
    public Long getCustomerId() {
        return customerId;
    }

    /**
     * <p>发帖人ID</p>
     *
     * @author: lixin
     * @param: @param customerId
     * @return: void
     * @Date :   2019-05-13 10:21:08
     */
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }


    /**
     * <p>打赏人ID</p>
     *
     * @author: lixin
     * @return: Long
     * @Date :   2019-05-13 10:21:08
     */
    public Long getRewardId() {
        return rewardId;
    }

    /**
     * <p>打赏人ID</p>
     *
     * @author: lixin
     * @param: @param rewardId
     * @return: void
     * @Date :   2019-05-13 10:21:08
     */
    public void setRewardId(Long rewardId) {
        this.rewardId = rewardId;
    }


    /**
     * <p>打赏币种</p>
     *
     * @author: lixin
     * @return: String
     * @Date :   2019-05-13 10:21:08
     */
    public String getCoinCode() {
        return coinCode;
    }

    /**
     * <p>打赏币种</p>
     *
     * @author: lixin
     * @param: @param coinCode
     * @return: void
     * @Date :   2019-05-13 10:21:08
     */
    public void setCoinCode(String coinCode) {
        this.coinCode = coinCode;
    }


    /**
     * <p>打赏金额</p>
     *
     * @author: lixin
     * @return: BigDecimal
     * @Date :   2019-05-13 10:21:08
     */
    public BigDecimal getRewardNum() {
        return rewardNum;
    }

    /**
     * <p>打赏金额</p>
     *
     * @author: lixin
     * @param: @param rewardNum
     * @return: void
     * @Date :   2019-05-13 10:21:08
     */
    public void setRewardNum(BigDecimal rewardNum) {
        this.rewardNum = rewardNum;
    }


    /**
     * <p>发帖人奖励</p>
     *
     * @author: lixin
     * @return: BigDecimal
     * @Date :   2019-05-13 10:21:08
     */
    public BigDecimal getActualReward() {
        return actualReward;
    }

    /**
     * <p>发帖人奖励</p>
     *
     * @author: lixin
     * @param: @param actualReward
     * @return: void
     * @Date :   2019-05-13 10:21:08
     */
    public void setActualReward(BigDecimal actualReward) {
        this.actualReward = actualReward;
    }


    /**
     * <p>平台手续费</p>
     *
     * @author: lixin
     * @return: BigDecimal
     * @Date :   2019-05-13 10:21:08
     */
    public BigDecimal getFee() {
        return fee;
    }

    /**
     * <p>平台手续费</p>
     *
     * @author: lixin
     * @param: @param fee
     * @return: void
     * @Date :   2019-05-13 10:21:08
     */
    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    @Override
    public String toString() {
        return "SocialSnsReward{" + "id=" + id + ", postsId=" + postsId + ", customerId=" + customerId + ", rewardId=" + rewardId + ", coinCode='" + coinCode + '\'' + ", rewardNum=" + rewardNum + ", actualReward=" + actualReward + ", fee=" + fee + '}';
    }
}
