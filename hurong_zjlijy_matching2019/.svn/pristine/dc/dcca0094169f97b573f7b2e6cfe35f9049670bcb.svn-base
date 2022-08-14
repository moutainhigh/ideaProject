package hry.util.enums;

public enum AccountRemarkEnum {
    TYPE1("转账，主账户往otc账号", 1),
    TYPE2("转账，otc账户往主账户", 2),
    TYPE3("发布广告，计算账户，可用-", 3),
    TYPE13("发布广告，计算账户，冻结+", 13),
    TYPE4("买,计算币-", 4),
    TYPE14("买,计算币+", 14),
    TYPE5("卖,计算币，可用-", 5),
    TYPE15("卖,计算币，冻结+", 15),
    TYPE6("买卖方退款,计算币，可用+", 6),
    TYPE16("买卖方退款,计算币，冻结-", 16),
    TYPE7("取消订单,计算币，可用+", 7),
    TYPE17("取消订单,计算币，冻结-", 17),
    TYPE8("完成订单,计算币，可用+", 8),
    TYPE18("完成订单,计算币，冻结-", 18),
    TYPE9("解除冻结,并关闭此条广告,计算币，可用+", 9),
    TYPE19("解除冻结,并关闭此条广告,计算币，冻结-", 19),
    TYPE10("恢复广告,解冻币，可用+", 10),
    TYPE20("恢复广告,解冻币，冻结-", 20),
    TYPE11("申诉完成订单,计算币，可用+", 11),
    TYPE21("申诉完成订单,计算币，冻结-", 21),
    TYPE12("申诉取消订单,计算币，可用+", 12),
    TYPE22("申诉取消订单,计算币，冻结-", 22),
    TYPE23("平台关闭广告,计算币，可用+", 23),
    TYPE24("平台关闭广告,计算币，冻结-", 24),


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
