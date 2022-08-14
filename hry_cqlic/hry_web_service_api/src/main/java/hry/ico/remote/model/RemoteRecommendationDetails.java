package hry.ico.remote.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class RemoteRecommendationDetails implements Serializable{
    private BigDecimal hotMoney;//可用
    private BigDecimal coldMoney;//冻结
    private BigDecimal totalMoney;//总资产

    private Integer teamMember;//团队人数
    private String recommendedLock;//推荐锁仓
    private String recommendedAward;//推荐奖励

    private int keepDigit; //保留小数位数

    public int getKeepDigit() {
        return keepDigit;
    }

    public void setKeepDigit(int keepDigit) {
        this.keepDigit = keepDigit;
    }

    public BigDecimal getHotMoney() {
        return hotMoney;
    }

    public void setHotMoney(BigDecimal hotMoney) {
        this.hotMoney = hotMoney;
    }

    public BigDecimal getColdMoney() {
        return coldMoney;
    }

    public void setColdMoney(BigDecimal coldMoney) {
        this.coldMoney = coldMoney;
    }

    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }

    public Integer getTeamMember() {
        return teamMember;
    }

    public void setTeamMember(Integer teamMember) {
        this.teamMember = teamMember;
    }

    public String getRecommendedLock() {
        return recommendedLock;
    }

    public void setRecommendedLock(String recommendedLock) {
        this.recommendedLock = recommendedLock;
    }

    public String getRecommendedAward() {
        return recommendedAward;
    }

    public void setRecommendedAward(String recommendedAward) {
        this.recommendedAward = recommendedAward;
    }
}
