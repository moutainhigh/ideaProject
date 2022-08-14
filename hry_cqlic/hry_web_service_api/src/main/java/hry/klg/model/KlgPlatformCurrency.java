package hry.klg.model;

public enum KlgPlatformCurrency {
    SMETotalAccount("SME总账户","101"),//
    SMEFoodBureauAccount("粮食局账户","102"),//
    SMECandyAccount("SEM糖果账户","103"), //预约 3
    USDTPrecipitationAccount("USDT奖金沉淀账户","104"), //预约 3
    USDTFinanceBureauAccount("USDT财政局账户","105"), //预约 3
    USDTConfiscationAccount("USDT保证金没收账户","106"), //预约 3
    CandyRecyclingAccount("USDT糖果回收账户","107"), //预约 3
    USDTWeeklyAccount("USDT周奖账户","108"); //预约 3
    private String name;
    private String key;
    private KlgPlatformCurrency( String name,String key) {
        this.name = name;
        this.key = key;
    }
    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
