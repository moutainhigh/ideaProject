package hry.lend.enums;

/**
 * @author <a href="mailto:HelloHeSir@gmail.com">HeC</a>
 * @date 2018/12/11 15:12
 */
public enum LendType {
//    TYPE1(1,"委托"),
    TYPE2(2,"转入到杠杆账户"),
    TYPE3(3,"传出到普通账户"),
    TYPE4(4,"借款"),
    TYPE5(5,"还款"),
//    TYPE6(6,"爆仓"),
    TYPE7(7,"还利息"),
    ;

    LendType(Integer type, String msg) {
        this.type = type;
        this.msg = msg;
    }

    private Integer type;

    private String msg;

    public Integer getType() {
        return type;
    }
}
