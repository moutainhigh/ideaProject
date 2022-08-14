package hry.licqb.award.model;

import java.math.BigDecimal;

/**
 * @author zhouming
 * @date 2019/8/17 17:24
 * @Version 1.0
 */
public class Upgrade{

    public Long customerId;

    public Integer userNum;  // 直推人数

    public Integer levelNum; // 等级数

    public BigDecimal assetSum; // 团队业绩

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Integer getUserNum() {
        return userNum;
    }

    public void setUserNum(Integer userNum) {
        this.userNum = userNum;
    }

    public Integer getLevelNum() {
        return levelNum;
    }

    public void setLevelNum(Integer levelNum) {
        this.levelNum = levelNum;
    }

    public BigDecimal getAssetSum() {
        return assetSum;
    }

    public void setAssetSum(BigDecimal assetSum) {
        this.assetSum = assetSum;
    }
}
