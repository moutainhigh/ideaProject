package hry.common.enums;

public enum SocialAccountRemarkEnum {
    TYPE1("转入", 1),
    TYPE2("转出", 2)

    ;

    private String name;
    private int index;
    private SocialAccountRemarkEnum(String name, int index) {
        this.name = name;
        this.index = index;
    }
    public static String getName(int index) {
        for (SocialAccountRemarkEnum c : SocialAccountRemarkEnum.values()) {
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
