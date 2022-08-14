package hry.social.mq.producer.service;

import java.io.Serializable;
import java.math.BigDecimal;

public class GatherMessage implements Serializable {

    Long id;    //奖励ID
    BigDecimal fr;    //好友收取率
    String coinCode;    //果子币代码
    Long customerId;    //收取人ID
    Long gatheredId;    //被收取人ID
    Integer selfStat;    //自己收取状态（1是自己收取的，0是好友收取）
    BigDecimal gatherNum;    //收取量
    BigDecimal residualNum;    //收取前剩余量

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getFr() {
        return fr;
    }

    public void setFr(BigDecimal fr) {
        this.fr = fr;
    }

    public String getCoinCode() {
        return coinCode;
    }

    public void setCoinCode(String coinCode) {
        this.coinCode = coinCode;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getGatheredId() {
        return gatheredId;
    }

    public void setGatheredId(Long gatheredId) {
        this.gatheredId = gatheredId;
    }

    public Integer getSelfStat() {
        return selfStat;
    }

    public void setSelfStat(Integer selfStat) {
        this.selfStat = selfStat;
    }

    public BigDecimal getGatherNum() {
        return gatherNum;
    }

    public void setGatherNum(BigDecimal gatherNum) {
        this.gatherNum = gatherNum;
    }

    public BigDecimal getResidualNum() {
        return residualNum;
    }

    public void setResidualNum(BigDecimal residualNum) {
        this.residualNum = residualNum;
    }
}
