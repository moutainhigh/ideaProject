package hry.cm.constant;

public class ExchangeDataCacheRedis {

    public final static String CM_INIT_cmOPERATE = "cm:init:cmOperate";

    public final static String CM_INIT_COINCODELIST = "cm:init:coinCodeList";

    public final static String CM_INIT_COINCODE = "cm:init:coinCode";

    public final static String CM_INIT_cmOPTIONMODEL = "cm:init:cmOptionModel";

    public final static String CM_INIT_SETTLEMENTTIMEMIN = "cm:init:settlementTimeMin";

    public final static String CM_INIT_CYCLEMIN = "cm:init:cycleMin";

    public final static  String  CM_SETTLEMNETFLAG="cm:settlemnet:klineFlag";

    public final static  String  CM_ORDER="cm:order:condition";
    /**
     * 获得cm账户key hash
     */
    public static String getAccountKey(String coinCode) {
      return "cm:accounts:customer:" + coinCode;
    }

    /**
     * 获得历史订单
     */
    public static String getOrderedKey(String coinCode) {
        return "cm:order:record:ed:" + coinCode;
    }
    /**
     * 获得进行中订单
     */
    public static String getOrderedingKey(String coinCode) {
        return "cm:order:record:ing:" + coinCode;
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
        return "cm:settlementlast:"+coinCode;
    }

    public static String getKline(String cointocoinKey) {
        return "cm:kline:" + cointocoinKey;
    }

    public static String getNewprice(String cointocoinKey) {
        return "cm:newprice:" + cointocoinKey;
    }
    public static String getOutBaseNewprice(String cointocoinKey) {
        return "cm:settlemnet:outBaseNewprice:" + cointocoinKey;
    }

    /**
     * 上一次的结算价
     * @param coinCode
     * @return
     */
    public static String getUpperPrice(String coinCode) {
        return "cm:settlemnet:upperPrice:" + coinCode;
    }
    /**
     * 检测时间段保存的最新价
     * @param coinCode
     * @return
     */
    public static String getBeforePrice(String coinCode) {
        return "cm:settlemnet:beforePrice:" + coinCode;
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
