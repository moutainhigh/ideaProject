package hry.app.lend.websocket.model;

import hry.lend.model.trade.LendEntrust;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author <a href="mailto:HelloHeSir@gmail.com">Mr_He</a>
 * @Copyright (c)</ b> HeC<br/>
 * @createTime 2018/8/23 10:52
 * @Description: 登录用户数据
 */
public class AccountData {

    /**
     * 交易币可用
     */
    private BigDecimal tradeMoney = BigDecimal.ZERO;

    /**
     * 定价币可用
     */
    private BigDecimal priMoney = BigDecimal.ZERO;

    /**
     * 当前委托
     */
    private List<LendEntrust> currentEntrust;

    /**
     * 历史委托
     */
    private List<LendEntrust> historyEntrust;

    public BigDecimal getTradeMoney() {
        return tradeMoney;
    }

    public void setTradeMoney(BigDecimal tradeMoney) {
        this.tradeMoney = tradeMoney;
    }

    public BigDecimal getPriMoney() {
        return priMoney;
    }

    public void setPriMoney(BigDecimal priMoney) {
        this.priMoney = priMoney;
    }

    public List<LendEntrust> getCurrentEntrust() {
        return currentEntrust;
    }

    public void setCurrentEntrust(List<LendEntrust> currentEntrust) {
        this.currentEntrust = currentEntrust;
    }

    public List<LendEntrust> getHistoryEntrust() {
        return historyEntrust;
    }

    public void setHistoryEntrust(List<LendEntrust> historyEntrust) {
        this.historyEntrust = historyEntrust;
    }
}