/**
 * Copyright:
 *
 * @author: yujl
 * @version: V1.0
 * @Date: 2018-09-28 16:13:45
 */
package hry.model.social.miningreward;


import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p> SocialCalculationAwardGatherRecord </p>
 *
 * @author: yujl
 * @Date :          2018-09-28 16:13:45
 */
public class SocialPickData implements Serializable {

    private Long id;                //奖励记录id
    private Long customerId;        //奖励所属用户id
    private Long gatheredId;        //收取人id
    private BigDecimal residualNum; //剩余果子数
    private int isAbleGather;       //是否可收取，0否1是
    private int isSelfGather;       //是否本人收取完了，0否1是
    private int isNewCache;         //是否新增的果子，0否1是
    private String rewardSource;    //奖励来源
    private Integer rewardType;     //奖励类型 1:算力奖励 2:任务奖励 3:矿机奖励
    private String imgNum;          //结晶图片类型
    private String imgPath;         //结晶图片
    private String coinCode;        //奖励币种

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getCoinCode() {
        return coinCode;
    }

    public void setCoinCode(String coinCode) {
        this.coinCode = coinCode;
    }
    public Integer getRewardType() {
        return rewardType;
    }

    public void setRewardType(Integer rewardType) {
        this.rewardType = rewardType;
    }

    public String getImgNum() {
        return imgNum;
    }

    public void setImgNum(String imgNum) {
        this.imgNum = imgNum;
    }

    public String getRewardSource() {
        return rewardSource;
    }

    public void setRewardSource(String rewardSource) {
        this.rewardSource = rewardSource;
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

    public BigDecimal getResidualNum() {
        return residualNum;
    }

    public void setResidualNum(BigDecimal residualNum) {
        this.residualNum = residualNum;
    }

    public int getIsAbleGather() {
        return isAbleGather;
    }

    public void setIsAbleGather(int isAbleGather) {
        this.isAbleGather = isAbleGather;
    }

    public int getIsSelfGather() {
        return isSelfGather;
    }

    public void setIsSelfGather(int isSelfGather) {
        this.isSelfGather = isSelfGather;
    }

    public int getIsNewCache() {
        return isNewCache;
    }

    public void setIsNewCache(int isNewCache) {
        this.isNewCache = isNewCache;
    }

    public Long getGatheredId() {
        return gatheredId;
    }

    public void setGatheredId(Long gatheredId) {
        this.gatheredId = gatheredId;
    }

    @Override
    public String toString() {
        return "SocialPickData{" + "id=" + id + ", customerId=" + customerId + ", gatheredId=" + gatheredId + ", residualNum=" + residualNum + ", isAbleGather=" + isAbleGather + ", isSelfGather=" + isSelfGather + ", isNewCache=" + isNewCache + '}';
    }
}
