package hry.remote.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Copyright © 北京互融时代软件有限公司
 * @Author: Jidn
 * @Date: 2019/4/17 10:35
 * @Description:
 */
public class AppFinancialRecommendVo implements Serializable {

    private Long id;  //

    private Long pid;  //父节点

    private Long uid;  //用户id

    private Integer vipLevel;  //vip等级 默认0  一共有 0,1,2,3,4,5

    private BigDecimal rewardRatio;  //奖励比例

    private Integer recommendNumber;//推荐人数

    private Integer financialOderNumber;//理财订单数量

    private Integer level;//层级

    private BigDecimal pendingMoney;//待发放金额

    private BigDecimal issuedMoney;//已发放金额

    private String email;

    private String mobilePhone;

    private String coinCode; //理财币种

    private BigDecimal realIncome;//实际收益

    private BigDecimal coinMoney;

    private BigDecimal redPacketsMoney;

    private BigDecimal poundage;

    private Long afuid;//理财人订单id

    public Long getAfuid() {
        return afuid;
    }

    public void setAfuid(Long afuid) {
        this.afuid = afuid;
    }

    public BigDecimal getPendingMoney() {
        return pendingMoney;
    }

    public void setPendingMoney(BigDecimal pendingMoney) {
        this.pendingMoney = pendingMoney;
    }

    public BigDecimal getIssuedMoney() {
        return issuedMoney;
    }

    public void setIssuedMoney(BigDecimal issuedMoney) {
        this.issuedMoney = issuedMoney;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public BigDecimal getRealIncome() {
        return realIncome;
    }

    public void setRealIncome(BigDecimal realIncome) {
        this.realIncome = realIncome;
    }

    public BigDecimal getCoinMoney() {
        return coinMoney;
    }

    public void setCoinMoney(BigDecimal coinMoney) {
        this.coinMoney = coinMoney;
    }

    public BigDecimal getRedPacketsMoney() {
        return redPacketsMoney;
    }

    public void setRedPacketsMoney(BigDecimal redPacketsMoney) {
        this.redPacketsMoney = redPacketsMoney;
    }

    public BigDecimal getPoundage() {
        return poundage;
    }

    public void setPoundage(BigDecimal poundage) {
        this.poundage = poundage;
    }

    public String getCoinCode() {
        return coinCode;
    }

    public void setCoinCode(String coiCode) {
        this.coinCode = coiCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Integer getVipLevel() {
        return vipLevel;
    }

    public void setVipLevel(Integer vipLevel) {
        this.vipLevel = vipLevel;
    }

    public BigDecimal getRewardRatio() {
        return rewardRatio;
    }

    public void setRewardRatio(BigDecimal rewardRatio) {
        this.rewardRatio = rewardRatio;
    }

    public Integer getRecommendNumber() {
        return recommendNumber;
    }

    public void setRecommendNumber(Integer recommendNumber) {
        this.recommendNumber = recommendNumber;
    }

    public Integer getFinancialOderNumber() {
        return financialOderNumber;
    }

    public void setFinancialOderNumber(Integer financialOderNumber) {
        this.financialOderNumber = financialOderNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }
}
