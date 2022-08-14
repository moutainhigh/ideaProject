package hry.common.deal;

/**
 * 获取设计基础配置信息
 */
public class SocialConfigUtil {

    /***
     * BTC/USDT  交易对价格的位数
     * @param contractKey
     * @return
     */
    public static int getCoinExchangeKeepDecimal(String contractKey){
        int keepDecimalForCurrency=2;

        //todo 取动态
        return keepDecimalForCurrency;
    }
}
