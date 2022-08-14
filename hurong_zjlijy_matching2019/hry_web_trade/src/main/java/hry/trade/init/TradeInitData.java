package hry.trade.init;

/**
 * @author <a href="mailto:HelloHeSir@gmail.com">Mr_He</a>
 * @Copyright (c)</   b> HeC<br/>
 * @createTime 2018/6/4 11:50
 * @Description:
 */
public class TradeInitData {

    private static boolean isInit = false;

    private TradeInitData() {
    }

    public static boolean isIsInit() {
        return isInit;
    }

    public static void setIsInit(boolean isInit) {
        TradeInitData.isInit = isInit;
    }
}