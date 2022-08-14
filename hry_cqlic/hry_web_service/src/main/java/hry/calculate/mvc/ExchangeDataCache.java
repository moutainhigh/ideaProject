/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Gao Mimi
 * @version:      V1.0 
 * @Date:        2016年4月26日 下午3:05:11
 */
package hry.calculate.mvc;

import hry.util.sys.ContextUtil;
import hry.redis.common.utils.RedisService;

import java.util.HashMap;

/**
 * <p> TODO</p>
 * @author:         Gao Mimi 
 * @Date :          2016年4月26日 下午3:05:11 
 */
public class ExchangeDataCache {
public static final String EndSettleDate="endSettleDate";	//结算开始日
public static final String StartSettleDate="startSettleDate";	 //结算结束日
public static final String AveragePrice="averagePrice";	 //均价
public static final String OpenedExchangPrice="openedExchangPrice";	 //开盘成交价
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

}
