package hry.cm.enums;

public enum CmAccountRemarkEnum {
	
	TYPE1("主账户转入到云矿机账户", 1),
    TYPE2("云矿机账户转出到主账户", 2),
    TYPE3("购买矿机", 3),
    TYPE4("直推奖励", 4),
    TYPE5("果树领取", 5),
    TYPE6("分红收益", 6),
    TYPE7("矿机收益", 7),
    TYPE8("矿工静态收益", 8),
    TYPE9("矿场收益", 9),
    TYPE10("矿工动态收益", 10),
    TYPE11("商城购物", 11),
    TYPE12("商城退款", 12),

    TYPE999999999("其他", 999999999);
    
    private String name;
    private int index;
    private CmAccountRemarkEnum(String name, int index) {
        this.name = name;
        this.index = index;
    }
    public static String getName(int index) {
        for (CmAccountRemarkEnum c : CmAccountRemarkEnum.values()) {
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
