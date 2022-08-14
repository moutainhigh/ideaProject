package hry.cm2.constant;

public class ExchangeDataCacheRedis {

    public final static String CM2_INIT_CM2OPERATE = "cm2:init:cmOperate";

    public final static String CM2_INIT_COINCODELIST = "cm2:init:coinCodeList";

    public final static String CM2_INIT_COINCODE = "cm2:init:coinCode";

    public final static String CM2_INIT_CM2OPTIONMODEL = "cm2:init:cmOptionModel";

    public final static String CM2_INIT_SETTLEMENTTIMEMIN = "cm2:init:settlementTimeMin";

    public final static String CM2_INIT_CYCLEMIN = "cm2:init:cycleMin";

    public final static  String  CM2_SETTLEMNETFLAG="cm2:settlemnet:klineFlag";

    public final static  String  CM2_ORDER="cm2:order:condition";
    /**
     * 获得cm账户key hash
     */
    public static String getAccountKey(String coinCode) {
      return "cm2:accounts:customer:" + coinCode;
    }

    /**
     * 获得历史订单
     */
    public static String getOrderedKey(String coinCode) {
        return "cm2:order:record:ed:" + coinCode;
    }
    /**
     * 获得进行中订单
     */
    public static String getOrderedingKey(String coinCode) {
        return "cm2:order:record:ing:" + coinCode;
    }
    /**
     * 获得期权模型key
     */
    public static String getcmOptionModelKey(String cointocoinKey) {
        return CM2_INIT_CM2OPTIONMODEL+":" + cointocoinKey;
    }

    /**
     *  最新结算的结果key
     */
    public static String settlementlastKey (String coinCode) {
        return "cm2:settlementlast:"+coinCode;
    }

    public static String getKline(String cointocoinKey) {
        return "cm2:kline:" + cointocoinKey;
    }

    public static String getNewprice(String cointocoinKey) {
        return "cm2:newprice:" + cointocoinKey;
    }
    public static String getOutBaseNewprice(String cointocoinKey) {
        return "cm2:settlemnet:outBaseNewprice:" + cointocoinKey;
    }

    /**
     * 上一次的结算价
     * @param coinCode
     * @return
     */
    public static String getUpperPrice(String coinCode) {
        return "cm2:settlemnet:upperPrice:" + coinCode;
    }
    /**
     * 检测时间段保存的最新价
     * @param coinCode
     * @return
     */
    public static String getBeforePrice(String coinCode) {
        return "cm2:settlemnet:beforePrice:" + coinCode;
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
