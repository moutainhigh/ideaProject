/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Gao Mimi
 * @version:      V1.0 
 * @Date:        2016年4月26日 下午3:05:11
 */
package hry.trade.redis.model;

import java.util.HashMap;

/**
 * <p> TODO</p>
 * @author:         Gao Mimi 
 * @Date :          2016年4月26日 下午3:05:11 
 */
public class ExchangeDataCacheRedis {
public static final String CurrentExchangPrice="currentExchangPrice";	//当前成交价
public static final String CurrentExchangDate="currentExchangDate";	 //当前成交价的日期
public static final String CurrentExchangTime="currentExchangTime";	 //当前成交价的时间
public static final String LastExchangPrice="lastExchangPrice";	//当前上个成交价
public static final String OpenedExchangPrice="openedExchangPrice";	 //开盘成交价
public static final String ScheduleWebSocketTime="scheduleWebSocketTime";	 //定时推送的时间记录
public static final String PeriodLastKLineList="periodLastKLineList"; //分期LastKline(每一期最后一个节点，当前区间的节点数据)  list

public static final String LastOrderRecords="LastOrderRecords";	//最新成交数据
public static final Integer LastOrderRecordsLmit=30;	//最新成交数
public static final String LastOrderRecordAdds="LastOrderRecordAdds";	//最新成交增量
protected static  HashMap<String,String> map = new HashMap<String,String>(); // Cache table

public static final String IsMatch="isMatch";	//是否发生成交了0，否，1是
public static final String Coin="coin";	
public static final String CNY="cny";	

public static final String ProductTransactionType="productTransactionType";//产品的交易类型
public static final String OpenAndclosePlateTime="openAndclosePlateTime";//产品的开盘币盘时间

public static final String ExorderInfoS = "exorderinfos"; // 缓存成交记录
public static final String AccountAddS = "Accountadds"; // 资金增量记录
public static final String ChangeEntrust = "changeEntrust"; // 交易过的委托单，就是改变了数据的委托单
public static final String BuyOnePrice = "buyonePrice"; // 买一
public static final String SellOnePrice = "sellonePrice"; // 卖一
public static final String TradeDealEntrustChangeNum="deal:tradeDealEntrustChangeNum"; //委托，撤销的处理顺序的号码
public static final String TradeDealEntrustChange="deal:tradeDealEntrustChange"; //
public static final String TradeDealEntrustChangeNumList1="deal:tradeDealEntrustChangeList1"; //
public static final String TradeDealEntrustChangeNumList2="deal:tradeDealEntrustChangeList2"; //
public static final String TradeDealEntrustChangeNumFlag="deal:tradeDealEntrustChangeListFlag"; //

public static final String TradeDealAccountChangeNum="deal:tradeDealAccountChangeNum"; //委托，撤销的处理顺序的号码
public static final String TradeDealAccountChange="deal:TradeDealAccountChange"; //
public static final String TradeDealAccountChangeNumList1="deal:tradeDealAccountChangeNumList1"; //
public static final String TradeDealAccountChangeNumList2="deal:tradeDealAccountChangeNumList2"; //
public static final String TradeDealAccountChangeNumFlag="deal:tradeDealAccountChangeNumFlag"; //

public static final String TradeDealOrderInfoChangeNum="deal:tradeDealOrderInfoChangeNum"; //委托，撤销的处理顺序的号码
public static final String TradeDealOrderInfoChange="deal:tradeDealOrderInfoChange"; //
public static final String TradeDealOrderInfoChangeNumList1="deal:TradeDealOrderInfoChangeNumList1"; //
public static final String TradeDealOrderInfoChangeNumList2="deal:TradeDealOrderInfoChangeNumList2"; //
public static final String TradeDealOrderInfoChangeNumFlag="deal:TradeDealOrderInfoChangeNumFlag"; //1,表示正在往1里面存2，表示正文里面存2



  public static final String RedisChannel="tradeKline";
    public static String getwaitTrigger(String coincode,String fixPriceCoinCode,Integer triggerComparePrcie) {

            String header = "trigger:wait:"+coincode+"_"+fixPriceCoinCode+":"+triggerComparePrcie;
            return header;





    }
}
