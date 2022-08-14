package hry.cmson.constant;

public class ExchangeDataCacheRedis {

    public final static String CM_INIT_cmOPERATE = "cmson:init:cmOperate";

    public final static String CM_INIT_COINCODELIST = "cmson:init:coinCodeList";

    public final static String CM_INIT_COINCODE = "cmson:init:coinCode";

    public final static String CM_INIT_cmOPTIONMODEL = "cmson:init:cmOptionModel";

    public final static String CM_INIT_SETTLEMENTTIMEMIN = "cmson:init:settlementTimeMin";

    public final static String CM_INIT_CYCLEMIN = "cmson:init:cycleMin";

    public final static  String  CM_SETTLEMNETFLAG="cmson:settlemnet:klineFlag";

    public final static  String  CM_ORDER="cmson:order:condition";
    /**
     * 获得cm账户key hash
     */
    public static String getAccountKey(String coinCode) {
      return "cmson:accounts:customer:" + coinCode;
    }

    /**
     * 获得历史订单
     */
    public static String getOrderedKey(String coinCode) {
        return "cmson:order:record:ed:" + coinCode;
    }
    /**
     * 获得进行中订单
     */
    public static String getOrderedingKey(String coinCode) {
        return "cmson:order:record:ing:" + coinCode;
    }
    /**
     * 获得期权模型key
     */
    public static String getcmOptionModelKey(String cointocoinKey) {
        return CM_INIT_cmOPTIONMODEL+":" + cointocoinKey;
    }

    /**
     *  最新结算的结果key
     */
    public static String settlementlastKey (String coinCode) {
        return "cmson:settlementlast:"+coinCode;
    }

    public static String getKline(String cointocoinKey) {
        return "cmson:kline:" + cointocoinKey;
    }

    public static String getNewprice(String cointocoinKey) {
        return "cmson:newprice:" + cointocoinKey;
    }
    public static String getOutBaseNewprice(String cointocoinKey) {
        return "cmson:settlemnet:outBaseNewprice:" + cointocoinKey;
    }

    /**
     * 上一次的结算价
     * @param coinCode
     * @return
     */
    public static String getUpperPrice(String coinCode) {
        return "cmson:settlemnet:upperPrice:" + coinCode;
    }
    /**
     * 检测时间段保存的最新价
     * @param coinCode
     * @return
     */
    public static String getBeforePrice(String coinCode) {
        return "cmson:settlemnet:beforePrice:" + coinCode;
    }
    
    /**
     * 获取币种配置key hash
     *
     * @return
     */
    public static String getCoinConfig () {
        return "cm:init:config:coins";
    }
}
