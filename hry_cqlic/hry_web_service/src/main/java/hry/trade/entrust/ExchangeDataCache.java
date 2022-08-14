/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Gao Mimi
 * @version:      V1.0 
 * @Date:        2016年4月26日 下午3:05:11
 */
package hry.trade.entrust;

import hry.util.sys.ContextUtil;
import hry.redis.common.utils.RedisService;

import java.util.HashMap;

/**
 * <p> TODO</p>
 * @author:         Gao Mimi 
 * @Date :          2016年4月26日 下午3:05:11 
 */
public class ExchangeDataCache {
public static final String CurrentExchangPrice="currentExchangPrice";	//当前成交价
public static final String CurrentExchangDate="currentExchangDate";	 //当前成交价的日期
public static final String CurrentExchangTime="currentExchangTime";	 //当前成交价的时间
public static final String LastExchangPrice="lastExchangPrice";	//当前上个成交价
public static final String OpenedExchangPrice="openedExchangPrice";	 //开盘成交价
public static final String ScheduleWebSocketTime="scheduleWebSocketTime";	 //定时推送的时间记录
public static final String PeriodLastKLineList="periodLastKLineList"; //分期LastKline(每一期最后一个节点，当前区间的节点数据)  list
public static final String BuyOnePrice="buyOnePrice";	//买一价
public static final String SellOnePrice="sellOnePrice";	//卖一价

public static final String LastOrderRecords="LastOrderRecords";	//最新成交数据钱100条
public static final String LastOrderRecordAdds="LastOrderRecordAdds";	//最新成交增量
protected static  HashMap<String,String> map = new HashMap<String,String>(); // Cache table

public static final String IsMatch="isMatch";	//是否发生成交了0，否，1是

public static final String Coin="coin";	
public static final String CNY="cny";	
protected static  HashMap<String,String> mapAccount = new HashMap<String,String>(); // Cache table
public static String getStringData(String key) {
	RedisService redisService=(RedisService)ContextUtil.getBean("redisService");
	String v= redisService.get(key);
// v = map.get(key);
    return v;
    
}
public static void setStringData(String key,String val) {
	RedisService redisService=(RedisService)ContextUtil.getBean("redisService");
	String preData= redisService.save(key,val);
//	map.put(key,val);
    
}
public static String getBigDeciamlMap(String key) {
	RedisService redisService=(RedisService)ContextUtil.getBean("redisService");
	String v= redisService.get(key);
// v = map.get(key);
    return v;
    
}
public static void setBigDeciamlMap(String key,String val) {
	RedisService redisService=(RedisService)ContextUtil.getBean("redisService");
	String preData= redisService.save(key,val);
//	map.put(key,val);
    
}
public static final String NoentrustNum="noentrustNum";	//全局指定不匹配的委托号
public static final String OnlyentrustNum="onlyentrustNum";	//全局指定匹配的委托号
public static final String ProductTransactionType="productTransactionType";//产品的交易类型
public static final String OpenAndclosePlateTime="openAndclosePlateTime";//产品的开盘币盘时间
}
