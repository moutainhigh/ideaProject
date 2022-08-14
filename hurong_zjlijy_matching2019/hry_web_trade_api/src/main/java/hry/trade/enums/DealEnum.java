package hry.trade.enums;

/**
 * @author <a href="mailto:HelloHeSir@gmail.com">Mr_He</a>
 * @Copyright (c)</ b> HeC<br/>
 * @createTime 2018/6/27 20:46
 * @Description:
 */
public enum DealEnum {
    BUY_TYPE("杠杆买入",1),
    SELL_TYPE("杠杆卖出",2),
    PORTION_DEAL("部分成交",1),
    ALL_DEAL("全部成交",2),
    NONE_DEAL("未成交",0),
    RECALL_ALL("全部撤销",4),
    RECALL_part("部分撤销",3),
    ;

    private String msg;

    private Integer code;

    DealEnum(String msg, Integer code) {
        this.msg = msg;
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public String getMsgByCode(int code){
        DealEnum[] values = DealEnum.values();
        for (DealEnum value : values) {
            if(value.getCode()==code){
                return value.getMsg();
            }
        }
        return null;
    }
}