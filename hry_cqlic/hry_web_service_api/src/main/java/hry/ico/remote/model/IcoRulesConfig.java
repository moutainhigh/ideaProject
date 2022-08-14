package hry.ico.remote.model;

import java.io.Serializable;

/**
 * ITX 平台规则设置
 */
public class IcoRulesConfig implements Serializable{

    //平台发币设置
    private String coinName;//币种名称
    private String coinCode;//币种Code
    private String issueNum;//发行数量
    private String saleNum;//可售数量
    private String issuePrice;//货币价格
    private String setValue;//设定值


    //ICO设置
    private String isLock;//自动锁仓   0是1否
    private String icoLockStartTime;//ico锁仓开始时间
    private String icoLockEndTime;//ico锁仓结束时间
    private String icoLockday;//ico锁仓天数
    private String icoLockExperienceDeduct;//ico锁仓经验扣除
    private String icoLockItxDeduct;//ico锁仓itx扣除

    //常规业务设置
    private String regExperience;//注册赠送经验值
    private String lockExperienceDeduct;//锁仓扣除经验
    private String ItxDeduct;//Itx扣除
    private String releaseExperienceDeduct;//释放扣除经验值

    //推荐佣金设置
    private String firstPrize;//首持奖励 固定值
    private String recommendReward;//推荐奖励 百分币%

    //基本运营设置
    private String appVersion;//APP版本号
    private String weChatNumber;//客服微信号
    private String contactPhone;//联系电话


    public String getCoinName() {
        return coinName;
    }

    public void setCoinName(String coinName) {
        this.coinName = coinName;
    }

    public String getCoinCode() {
        return coinCode;
    }

    public void setCoinCode(String coinCode) {
        this.coinCode = coinCode;
    }

    public String getIssueNum() {
        return issueNum;
    }

    public void setIssueNum(String issueNum) {
        this.issueNum = issueNum;
    }

    public String getSaleNum() {
        return saleNum;
    }

    public void setSaleNum(String saleNum) {
        this.saleNum = saleNum;
    }

    public String getIssuePrice() {
        return issuePrice;
    }

    public void setIssuePrice(String issuePrice) {
        this.issuePrice = issuePrice;
    }

    public String getIsLock() {
        return isLock;
    }

    public void setIsLock(String isLock) {
        this.isLock = isLock;
    }

    public String getSetValue() {
        return setValue;
    }

    public void setSetValue(String setValue) {
        this.setValue = setValue;
    }

    public String getIcoLockStartTime() {
        return icoLockStartTime;
    }

    public void setIcoLockStartTime(String icoLockStartTime) {
        this.icoLockStartTime = icoLockStartTime;
    }

    public String getIcoLockEndTime() {
        return icoLockEndTime;
    }

    public void setIcoLockEndTime(String icoLockEndTime) {
        this.icoLockEndTime = icoLockEndTime;
    }

    public String getIcoLockday() {
        return icoLockday;
    }

    public void setIcoLockday(String icoLockday) {
        this.icoLockday = icoLockday;
    }

    public String getIcoLockExperienceDeduct() {
        return icoLockExperienceDeduct;
    }

    public void setIcoLockExperienceDeduct(String icoLockExperienceDeduct) {
        this.icoLockExperienceDeduct = icoLockExperienceDeduct;
    }

    public String getIcoLockItxDeduct() {
        return icoLockItxDeduct;
    }

    public void setIcoLockItxDeduct(String icoLockItxDeduct) {
        this.icoLockItxDeduct = icoLockItxDeduct;
    }

    public String getRegExperience() {
        return regExperience;
    }

    public void setRegExperience(String regExperience) {
        this.regExperience = regExperience;
    }

    public String getLockExperienceDeduct() {
        return lockExperienceDeduct;
    }

    public void setLockExperienceDeduct(String lockExperienceDeduct) {
        this.lockExperienceDeduct = lockExperienceDeduct;
    }

    public String getItxDeduct() {
        return ItxDeduct;
    }

    public void setItxDeduct(String itxDeduct) {
        ItxDeduct = itxDeduct;
    }

    public String getReleaseExperienceDeduct() {
        return releaseExperienceDeduct;
    }

    public void setReleaseExperienceDeduct(String releaseExperienceDeduct) {
        this.releaseExperienceDeduct = releaseExperienceDeduct;
    }

    public String getFirstPrize() {
        return firstPrize;
    }

    public void setFirstPrize(String firstPrize) {
        this.firstPrize = firstPrize;
    }

    public String getRecommendReward() {
        return recommendReward;
    }

    public void setRecommendReward(String recommendReward) {
        this.recommendReward = recommendReward;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getWeChatNumber() {
        return weChatNumber;
    }

    public void setWeChatNumber(String weChatNumber) {
        this.weChatNumber = weChatNumber;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }
}
