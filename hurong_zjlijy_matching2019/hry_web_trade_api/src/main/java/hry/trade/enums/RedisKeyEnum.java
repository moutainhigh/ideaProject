package hry.trade.enums;


import hry.trade.constant.ConstantStr;

/**
 * 2018年6月27日11:01:54
 * @author Hec
 */
public enum RedisKeyEnum {
    LEND_CONFIG("杠杆配置缓存",ConstantStr.CONFIG_CACHE+":lendconfig"),
    LEND_ACCOUNT("杠杆账户:用户id","deal:lendAccount");

    RedisKeyEnum(String msg,String key) {
        this.msg = msg;
        this.key = key;
    }

    private String key;

    private String msg;

    public String getKey() {
        return key;
    }

    public String getMsg() {
        return msg;
    }
}
