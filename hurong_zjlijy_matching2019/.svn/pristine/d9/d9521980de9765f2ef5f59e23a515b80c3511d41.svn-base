package hry.mq.consumer.social.miningreward;

import java.io.Serializable;
import java.math.BigDecimal;

public class MiningRewardMessage implements Serializable {


    private Long rewardId;  //奖励记录ID

    private String coinCode;  //奖励币种

    private Long gatherId;  //收取人ID

    private Long customerId;  //奖励用户ID（被收取人ID）

    private Integer gatherType;  //收取状态 0:好友收取 1:本人收取

    private BigDecimal gatherRatio;    //好友收取率

    private BigDecimal gatherNum;  //收取量

    private BigDecimal residualNum;    //收取前剩余量

    private String rewardSource;  //奖励来源

    public String getRewardSource() {
        return rewardSource;
    }

    public void setRewardSource(String rewardSource) {
        this.rewardSource = rewardSource;
    }

    /**
     * 奖励记录ID
     *
     * @return
     */
    public Long getRewardId() {
        return rewardId;
    }

    /**
     * 奖励记录ID
     *
     * @return
     */
    public void setRewardId(Long rewardId) {
        this.rewardId = rewardId;
    }

    /**
     * 奖励币种
     *
     * @return
     */
    public String getCoinCode() {
        return coinCode;
    }

    /**
     * 奖励币种
     *
     * @return
     */
    public void setCoinCode(String coinCode) {
        this.coinCode = coinCode;
    }

    /**
     * 收取人ID
     *
     * @return
     */
    public Long getGatherId() {
        return gatherId;
    }

    /**
     * 收取人ID
     *
     * @return
     */
    public void setGatherId(Long gatherId) {
        this.gatherId = gatherId;
    }

    /**
     * 奖励用户ID（被收取人ID）
     *
     * @return
     */
    public Long getCustomerId() {
        return customerId;
    }

    /**
     * 奖励用户ID（被收取人ID）
     *
     * @return
     */
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    /**
     * 收取状态 0:好友收取 1:本人收取
     *
     * @return
     */
    public Integer getGatherType() {
        return gatherType;
    }

    /**
     * 收取状态 0:好友收取 1:本人收取
     *
     * @return
     */
    public void setGatherType(Integer gatherType) {
        this.gatherType = gatherType;
    }

    /**
     * 好友收取率
     *
     * @return
     */
    public BigDecimal getGatherRatio() {
        return gatherRatio;
    }

    /**
     * 好友收取率
     *
     * @return
     */
    public void setGatherRatio(BigDecimal gatherRatio) {
        this.gatherRatio = gatherRatio;
    }

    /**
     * 收取量
     *
     * @return
     */
    public BigDecimal getGatherNum() {
        return gatherNum;
    }

    /**
     * 收取量
     *
     * @return
     */
    public void setGatherNum(BigDecimal gatherNum) {
        this.gatherNum = gatherNum;
    }

    /**
     * 收取前剩余量
     *
     * @return
     */
    public BigDecimal getResidualNum() {
        return residualNum;
    }

    /**
     * 收取前剩余量
     *
     * @return
     */
    public void setResidualNum(BigDecimal residualNum) {
        this.residualNum = residualNum;
    }
}
