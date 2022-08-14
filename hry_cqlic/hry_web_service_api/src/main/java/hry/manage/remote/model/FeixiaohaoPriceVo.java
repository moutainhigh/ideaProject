package hry.manage.remote.model;

/**
 * @author zhouming
 * @date 2019/9/3 17:35
 * @Version 1.0
 */
public class FeixiaohaoPriceVo {
    private String name;  //币种编码
    private String price; //币种价格 (对应人民币价格)
    private String increase;  //涨跌幅，下降为负数
    private String circulation;//流通量
    private String marketvalue;//市值
    private String turnover;//24H成交额

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getIncrease() {
        return increase;
    }

    public void setIncrease(String increase) {
        this.increase = increase;
    }

    public String getCirculation() {
        return circulation;
    }

    public void setCirculation(String circulation) {
        this.circulation = circulation;
    }

    public String getMarketvalue() {
        return marketvalue;
    }

    public void setMarketvalue(String marketvalue) {
        this.marketvalue = marketvalue;
    }

    public String getTurnover() {
        return turnover;
    }

    public void setTurnover(String turnover) {
        this.turnover = turnover;
    }
}
