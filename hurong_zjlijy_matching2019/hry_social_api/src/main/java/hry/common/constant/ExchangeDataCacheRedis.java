package hry.common.constant;

/**
 * Redis key值类
 */
public class ExchangeDataCacheRedis {
    /**
     * 启动项目初始化
     * 命名规则  子业务名称_init_具体功能名称
     */
    public final static String SOCIAL_INIT_BINARYOPERATE = "social:init:binaryOperate";

    /**
     * 其他
     * 命名规则  子业务名称_功能名称_具体功能名称
     */
    public final static  String  SOCIAL_SETTLEMNETFLAG="social:settlemnet:klineFlag";


    /**
     * 获得子账户key hash
     */
    public static String getAccountKey(String coinCode) {
        return "social:accounts:customer:" + coinCode;
    }
}
