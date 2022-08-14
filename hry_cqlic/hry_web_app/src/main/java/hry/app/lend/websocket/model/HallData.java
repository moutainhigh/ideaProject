package hry.app.lend.websocket.model;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author <a href="mailto:HelloHeSir@gmail.com">Mr_He</a>
 * @Copyright (c)</ b> HeC<br/>
 * @createTime 2018/8/8 17:21
 * @Description: 交易大厅数据归集
 */
public class HallData {

    //当前选中交易对
    private String coinCode;

    //最新价
    private BigDecimal newPrice;

    //买单
    private List<BigDecimal[]> asks;

    //卖单
    private List<BigDecimal[]> bids;

    //深度图
    private DepthMap depthMap;

    //最新成交单
    private List<String> orderList;

    //基本信息
    private CoinData coinData;

    //账户信息
    private AccountData accountData;

    //左侧交易区
    private List<String> leftCoinTab;

    //左侧交易币种和价格
    private List<LeftCoinData> leftPrice;

    private KeepDecimal decimal;

    public static class KeepDecimal{
        //交易币位数
        private Integer tradeDecimal = 6;

        //定价币位数
        private Integer priDecimal = 6;

        public Integer getTradeDecimal() {
            return tradeDecimal;
        }

        public void setTradeDecimal(Integer tradeDecimal) {
            this.tradeDecimal = tradeDecimal;
        }

        public Integer getPriDecimal() {
            return priDecimal;
        }

        public void setPriDecimal(Integer priDecimal) {
            this.priDecimal = priDecimal;
        }
    }

    public static class LeftCoinData{

        //交易对
        private String coinCode;

        //价格
        private BigDecimal price;

        //成交量
        private BigDecimal count = BigDecimal.ZERO;

        //折算法币
        private BigDecimal convertPrice = BigDecimal.ZERO;

        public BigDecimal getConvertPrice() {
            return convertPrice;
        }

        public void setConvertPrice(BigDecimal convertPrice) {
            this.convertPrice = convertPrice;
        }

        //涨跌幅
        private BigDecimal upRatio = BigDecimal.ZERO;

        public BigDecimal getCount() {
            return count;
        }

        public void setCount(BigDecimal count) {
            this.count = count;
        }

        public BigDecimal getUpRatio() {
            return upRatio;
        }

        public void setUpRatio(BigDecimal upRatio) {
            this.upRatio = upRatio;
        }

        public String getCoinCode() {
            return coinCode;
        }

        public void setCoinCode(String coinCode) {
            this.coinCode = coinCode;
        }

        public BigDecimal getPrice() {
            return price;
        }

        public void setPrice(BigDecimal price) {
            this.price = price;
        }
    }

    public static class CoinData{

        //定价币——>基础币——>法币
        private BigDecimal coinToMoney = new BigDecimal(32);

        //涨幅
        private BigDecimal upRatio = BigDecimal.ZERO;

        //今日最高
        private BigDecimal maxPrice = BigDecimal.ZERO;

        //今日最低
        private BigDecimal minPrice = BigDecimal.ZERO;

        //24h交易量
        private BigDecimal upDayCount = BigDecimal.ZERO;

        public BigDecimal getCoinToMoney() {
            return coinToMoney;
        }

        public void setCoinToMoney(BigDecimal coinToMoney) {
            this.coinToMoney = coinToMoney;
        }

        public BigDecimal getUpRatio() {
            return upRatio;
        }

        public void setUpRatio(BigDecimal upRatio) {
            this.upRatio = upRatio;
        }

        public BigDecimal getMaxPrice() {
            return maxPrice;
        }

        public void setMaxPrice(BigDecimal maxPrice) {
            this.maxPrice = maxPrice;
        }

        public BigDecimal getMinPrice() {
            return minPrice;
        }

        public void setMinPrice(BigDecimal minPrice) {
            this.minPrice = minPrice;
        }

        public BigDecimal getUpDayCount() {
            return upDayCount;
        }

        public void setUpDayCount(BigDecimal upDayCount) {
            this.upDayCount = upDayCount;
        }
    }

    public static class DepthMap{
        //买单
        private List<BigDecimal[]> asks;

        //卖单
        private List<BigDecimal[]> bids;

        public List<BigDecimal[]> getAsks() {
            return asks;
        }

        public void setAsks(List<BigDecimal[]> asks) {
            this.asks = asks;
        }

        public List<BigDecimal[]> getBids() {
            return bids;
        }

        public void setBids(List<BigDecimal[]> bids) {
            this.bids = bids;
        }
    }

    public BigDecimal getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(BigDecimal newPrice) {
        this.newPrice = newPrice;
    }

    public KeepDecimal getDecimal() {
        return decimal;
    }

    public void setDecimal(KeepDecimal decimal) {
        this.decimal = decimal;
    }

    public String getCoinCode() {
        return coinCode;
    }

    public void setCoinCode(String coinCode) {
        this.coinCode = coinCode;
    }

    public List<String> getLeftCoinTab() {
        return leftCoinTab;
    }

    public void setLeftCoinTab(List<String> leftCoinTab) {
        this.leftCoinTab = leftCoinTab;
    }

    public List<LeftCoinData> getLeftPrice() {
        return leftPrice;
    }

    public void setLeftPrice(List<LeftCoinData> leftPrice) {
        this.leftPrice = leftPrice;
    }

    public AccountData getAccountData() {
        return accountData;
    }

    public void setAccountData(AccountData accountData) {
        this.accountData = accountData;
    }

    public CoinData getCoinData() {
        return coinData;
    }

    public void setCoinData(CoinData coinData) {
        this.coinData = coinData;
    }

    public DepthMap getDepthMap() {
        return depthMap;
    }

    public void setDepthMap(DepthMap depthMap) {
        this.depthMap = depthMap;
    }

    public List<BigDecimal[]> getAsks() {
        return asks;
    }

    public void setAsks(List<BigDecimal[]> asks) {
        this.asks = asks;
    }

    public List<BigDecimal[]> getBids() {
        return bids;
    }

    public void setBids(List<BigDecimal[]> bids) {
        this.bids = bids;
    }

    public List<String> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<String> orderList) {
        this.orderList = orderList;
    }
}

