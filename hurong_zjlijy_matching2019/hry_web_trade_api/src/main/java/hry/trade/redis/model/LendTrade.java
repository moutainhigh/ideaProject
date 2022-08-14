package hry.trade.redis.model;


import java.io.Serializable;
import java.util.List;


public class LendTrade implements Serializable {
    /**
     * 已成交记录
     */
    private List<LendTradeOrder> listorder;

    /**
     * 相关委托
     */
    private List<LendTradeEntrust> listentrust;

    /**
     * 主观方委托
     */
    private LendTradeEntrust lendTradeEntrusting;

    /**
     * 1下单未成交2下单成交3撤销  4撤销失败没有再list里面，说明已经完成或者撤销过了
     */
    public int type;

    public List<LendTradeOrder> getListorder() {
        return listorder;
    }

    public void setListorder(List<LendTradeOrder> listorder) {
        this.listorder = listorder;
    }

    public List<LendTradeEntrust> getListentrust() {
        return listentrust;
    }

    public void setListentrust(List<LendTradeEntrust> listentrust) {
        this.listentrust = listentrust;
    }

    public LendTradeEntrust getLendTradeEntrusting() {
        return lendTradeEntrusting;
    }

    public void setLendTradeEntrusting(LendTradeEntrust lendTradeEntrusting) {
        this.lendTradeEntrusting = lendTradeEntrusting;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
