package hry.lend.enums;

public enum TradeRemark {
    TYPE1("爆仓：还交易币"),
    TYPE2("爆仓：还定价币"),

    ;

    private String msg;

    TradeRemark(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
