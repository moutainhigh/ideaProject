package hry.licqb.util;

/**
 * @author zhouming
 * @date 2019/8/15 10:49
 * @Version 1.0
 */
public enum DealEnum {

    TYPE1("静态收益","1"),
    TYPE2("见点奖","2"),
    TYPE3("管理奖","3"),
    TYPE4("级别奖","4"),
    TYPE5("共建社区奖励","5"),
    TYPE6("出局","6"),
    TYPE7("兑入","7"),
    TYPE8("投入","8"),
    TYPE9("社区奖励周释放","9"),
    TYPE10("社区奖励月释放","10"),
    TYPE11("社区奖励年释放","11"),
    TYPE12("兑出","12"),
    TYPE13("理财","13"),
    TYPE14("提币","14"),
    TYPE15("充币","15"),

    SITE1("静态收益发放","81"),
    SITE2("见点奖发放","82"),
    SITE3("管理奖发放","83"),
    SITE4("级别奖发放","84"),
    SITE5("共建社区奖励发放-冻结","85"),
    SITE6("收益达到顶峰出局","86"),
    SITE7("用户兑入","87"),
    SITE8("用户投入","88"),
    SITE9("社区奖励周发放","89"),
    SITE10("社区奖励月发放","810"),
    SITE11("社区奖励年发放","811"),
    SITE12("用户兑出","812"),
    SITE13("用户理财","813"),
    SITE14("用户提币","814"),
    SITE15("用户充币","815"),
    SITE16("手动充币","816");


    private String name;  //用于获取说明，
    private String index;  //获取数值
    private DealEnum(String name, String index) {
        this.name = name;
        this.index = index;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getIndex() {
        return index;
    }
    public void setIndex(String index) {
        this.index = index;
    }

    public static String getName(String index) {
        String name = "";
        for (DealEnum e : DealEnum.values()) {
            if (index.equals(e.index)) {
                name = e.name;
            }
        }
        return name;
    }
}
