package hry.licqb.award.model;

import java.math.BigDecimal;

/**
 * @author zhouming
 * @date 2019/8/27 14:11
 * @Version 1.0
 */
public class UserCommendAsset {

    private BigDecimal teamAllAsset; // 社区总资产

    private BigDecimal oneAllAsset; // 直推总资产

    private BigDecimal twoAllAsset; // 二代总资产

    private BigDecimal threeAllAsset; // 三代以上总资产

    private BigDecimal lastMonthAsset; // 上个月总资产

    private BigDecimal thisMonthAsset; // 本月业绩

    public BigDecimal getTeamAllAsset() {
        return teamAllAsset;
    }

    public void setTeamAllAsset(BigDecimal teamAllAsset) {
        this.teamAllAsset = teamAllAsset;
    }

    public BigDecimal getOneAllAsset() {
        return oneAllAsset;
    }

    public void setOneAllAsset(BigDecimal oneAllAsset) {
        this.oneAllAsset = oneAllAsset;
    }

    public BigDecimal getTwoAllAsset() {
        return twoAllAsset;
    }

    public void setTwoAllAsset(BigDecimal twoAllAsset) {
        this.twoAllAsset = twoAllAsset;
    }

    public BigDecimal getThreeAllAsset() {
        return threeAllAsset;
    }

    public void setThreeAllAsset(BigDecimal threeAllAsset) {
        this.threeAllAsset = threeAllAsset;
    }

    public BigDecimal getLastMonthAsset() {
        return lastMonthAsset;
    }

    public void setLastMonthAsset(BigDecimal lastMonthAsset) {
        this.lastMonthAsset = lastMonthAsset;
    }

    public BigDecimal getThisMonthAsset() {
        return thisMonthAsset;
    }

    public void setThisMonthAsset(BigDecimal thisMonthAsset) {
        this.thisMonthAsset = thisMonthAsset;
    }
}
