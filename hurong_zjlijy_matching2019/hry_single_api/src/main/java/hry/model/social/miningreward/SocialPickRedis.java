package hry.model.social.miningreward;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author javal
 * @title: SocialPickRedis
 * @projectName hurong_matching2019
 * @description: TODO
 * @date 2019/6/311:05
 */
public class SocialPickRedis implements Serializable {
    private Long id;                    //奖励记录id
    private Long customerId;            //用户id
    private BigDecimal residualNum;     //剩余果子数
    private BigDecimal awardCoinNum;    //奖励数量
    private BigDecimal guaranteedNum;   //保底数量
    private String onlySelf;            //是否只能本人采摘状态（0否;1是，此时为保底剩余量）
    private String rewardSource;        //奖励来源
    private Integer rewardType;         //奖励类型
    private String imgNum;              //结晶图片类型
    private String imgPath;             //结晶图片
    private String coinCode;            //奖励币种
    private Long expiryTime = -1L;            //失效时间
    private Date created;               //生成时间

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Long getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(Long expiryTime) {
        this.expiryTime = expiryTime;
    }

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

    public BigDecimal getAwardCoinNum() {
        return awardCoinNum;
    }

    public void setAwardCoinNum(BigDecimal awardCoinNum) {
        this.awardCoinNum = awardCoinNum;
    }

    public BigDecimal getGuaranteedNum() {
        return guaranteedNum;
    }

    public void setGuaranteedNum(BigDecimal guaranteedNum) {
        this.guaranteedNum = guaranteedNum;
    }

    public String getOnlySelf() {
        return onlySelf;
    }

    public void setOnlySelf(String onlySelf) {
        this.onlySelf = onlySelf;
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

    @Override
    public String toString() {
        return "SocialPickRedis{" + "id=" + id + ", customerId=" + customerId + ", residualNum=" + residualNum + ", awardCoinNum=" + awardCoinNum + ", guaranteedNum=" + guaranteedNum + ", onlySelf='" + onlySelf + '\'' + '}';
    }
}
