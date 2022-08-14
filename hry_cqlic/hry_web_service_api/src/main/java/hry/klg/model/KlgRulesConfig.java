package hry.klg.model;

import java.io.Serializable;

/**
 * KLG平台规则设置
 */
public class KlgRulesConfig implements Serializable{

    //平台发币设置
    private String coinName;//币种名称
    private String coinCode;//币种Code
    private String issuePrice;//货币价格


    //开关盘设置
    private String isOpen;  //开关 1开0关
    private String startTime; //每天开盘时间
    private String endTime; //每天关盘时间

    //糖果奖励分配
    private String meGain;//本人获得（USDT）%
    private String platformGain;//平台收取（USDT）%
    private String seePointAward;//见点奖（USDT）%
    private String gradationAward;//级差奖（USDT）%
    private String grandPrizeFund;//大奖基金（USDT）%

    //预约买入设置
    private String  marginDays;//保证金起息天数（天）
    private String  marginInterestRate;//保证金利率（%）
    private String  marginRatio;//保证金比例（%）
    private String  waitingTime;//等待尾款时长（时）
    private String  lineUpTime;//排单间隔时长（天）

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

    public String getIssuePrice() {
        return issuePrice;
    }

    public void setIssuePrice(String issuePrice) {
        this.issuePrice = issuePrice;
    }

    public String getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(String isOpen) {
        this.isOpen = isOpen;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getMeGain() {
        return meGain;
    }

    public void setMeGain(String meGain) {
        this.meGain = meGain;
    }

    public String getPlatformGain() {
        return platformGain;
    }

    public void setPlatformGain(String platformGain) {
        this.platformGain = platformGain;
    }

    public String getSeePointAward() {
        return seePointAward;
    }

    public void setSeePointAward(String seePointAward) {
        this.seePointAward = seePointAward;
    }

    public String getGradationAward() {
        return gradationAward;
    }

    public void setGradationAward(String gradationAward) {
        this.gradationAward = gradationAward;
    }

    public String getGrandPrizeFund() {
        return grandPrizeFund;
    }

    public void setGrandPrizeFund(String grandPrizeFund) {
        this.grandPrizeFund = grandPrizeFund;
    }

    public String getMarginDays() {
        return marginDays;
    }

    public void setMarginDays(String marginDays) {
        this.marginDays = marginDays;
    }

    public String getMarginInterestRate() {
        return marginInterestRate;
    }

    public void setMarginInterestRate(String marginInterestRate) {
        this.marginInterestRate = marginInterestRate;
    }


    public String getMarginRatio() {
        return marginRatio;
    }

    public void setMarginRatio(String marginRatio) {
        this.marginRatio = marginRatio;
    }

    public String getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(String waitingTime) {
        this.waitingTime = waitingTime;
    }

    public String getLineUpTime() {
        return lineUpTime;
    }

    public void setLineUpTime(String lineUpTime) {
        this.lineUpTime = lineUpTime;
    }
}
