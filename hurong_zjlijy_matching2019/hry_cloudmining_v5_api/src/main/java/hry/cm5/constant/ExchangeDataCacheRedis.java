package hry.cm5.constant;

public class ExchangeDataCacheRedis {

    public final static String CM5_INIT_CM5OPERATE = "cm5:init:cmOperate";

    public final static String CM5_INIT_COINCODELIST = "cm5:init:coinCodeList";

    public final static String CM5_INIT_COINCODE = "cm5:init:coinCode";

    public final static String CM5_INIT_CM5OPTIONMODEL = "cm5:init:cmOptionModel";

    public final static String CM5_INIT_SETTLEMENTTIMEMIN = "cm5:init:settlementTimeMin";

    public final static String CM5_INIT_CYCLEMIN = "cm5:init:cycleMin";

    public final static  String  CM5_SETTLEMNETFLAG="cm5:settlemnet:klineFlag";

    public final static  String  CM5_ORDER="cm5:order:condition";
    /**
     * 获得cm账户key hash
     */
    public static String getAccountKey(String coinCode) {
      return "cm5:accounts:customer:" + coinCode;
    }

    /**
     * 获得历史订单
     */
    public static String getOrderedKey(String coinCode) {
        return "cm5:order:record:ed:" + coinCode;
    }
    /**
     * 获得进行中订单
     */
    public static String getOrderedingKey(String coinCode) {
        return "cm5:order:record:ing:" + coinCode;
    }
    /**
     * 获得期权模型key
     */
    public static String getcmOptionModelKey(String cointocoinKey) {
        return CM5_INIT_CM5OPTIONMODEL+":" + cointocoinKey;
    }

    /**
     *  最新结算的结果key
     */
    public static String settlementlastKey (String coinCode) {
        return "cm5:settlementlast:"+coinCode;
    }

    public static String getKline(String cointocoinKey) {
        return "cm5:kline:" + cointocoinKey;
    }

    public static String getNewprice(String cointocoinKey) {
        return "cm5:newprice:" + cointocoinKey;
    }
    public static String getOutBaseNewprice(String cointocoinKey) {
        return "cm5:settlemnet:outBaseNewprice:" + cointocoinKey;
    }

    /**
     * 上一次的结算价
     * @param coinCode
     * @return
     */
    public static String getUpperPrice(String coinCode) {
        return "cm5:settlemnet:upperPrice:" + coinCode;
    }
    /**
     * 检测时间段保存的最新价
     * @param coinCode
     * @return
     */
    public static String getBeforePrice(String coinCode) {
        return "cm5:settlemnet:beforePrice:" + coinCode;
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
