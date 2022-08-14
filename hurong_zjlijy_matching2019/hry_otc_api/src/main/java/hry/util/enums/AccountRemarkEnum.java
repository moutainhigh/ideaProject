package hry.util.enums;

public enum AccountRemarkEnum {
    TYPE1("转账，主账户往otc账号", 1),
    TYPE2("转账，otc账户往主账户", 2),
    TYPE3("发布广告，计算账户", 3),
    TYPE4("买,计算币", 4),
    TYPE5("卖,计算币", 5),
    TYPE6("买卖方退款,计算币", 6),
    TYPE7("取消订单,计算币", 7),
    TYPE8("完成订单,计算币", 8),
    TYPE9("解除冻结,并关闭此条广告,计算币", 9),
    TYPE10("恢复广告,解冻币", 10),
    TYPE11("申诉完成订单,计算币", 11),
    TYPE12("申诉取消订单,计算币", 12),
    ;
    private String name;
    private int index;
    private AccountRemarkEnum(String name, int index) {
        this.name = name;
        this.index = index;
    }
    public static String getName(int index) {
        for (AccountRemarkEnum c : AccountRemarkEnum.values()) {
            if (c.getIndex() == index) {
                return c.name;
            }
        }
        return String.valueOf(index);
    }
    // get set 方法
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getIndex() {
        return index;
    }
    public void setIndex(int index) {
        this.index = index;
    }
}
