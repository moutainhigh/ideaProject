/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Gao Mimi
 * @version:      V1.0 
 * @Date:        2016年4月27日 下午7:06:11
 */
package hry.trade.websoketContext;

import hry.util.sys.ContextUtil;
import hry.redis.common.utils.RedisService;

/**
 * <p> dsfadfa</p>
 * @author:         Gao Mimi 
 * @Date :          2016年4月27日 下午7:06:11 
 */
public class PushData {
	public static void pushEntrust(String pustdata,String currencyType) {
	    String key=currencyType+":pushEntrust";
		RedisService redisService=(RedisService)ContextUtil.getBean("redisService");
			String preData= redisService.get(key);
			 if(null==preData||!preData.equals(pustdata)){
					redisService.save(key,pustdata); 	// 推送委托变动信息 买或者卖，只一种类型
		 }
	}
	
	public static void pushNewRecordList(String pustdata,String currencyType) {
		 String key=currencyType+":pushNewRecordList";
		RedisService redisService=(RedisService)ContextUtil.getBean("redisService");
		 String preData= redisService.get(key);
		 if(null==preData||!preData.equals(pustdata)){
				redisService.save(key,pustdata); 	// 推送成交记录
		 }
	}
	
	
	public static void pushIndex(String pustdata,String currencyType) {
		     String key=currencyType+":pushIndex";
		     RedisService redisService=(RedisService)ContextUtil.getBean("redisService");
		     String preData= redisService.get(key);
			 if(null==preData||!preData.equals(pustdata)){
					redisService.save(key,pustdata); 	// 首页
			   }
	}
	   
	
	   //委托-----行情板块 数据获取
	   //currencyType=1,2,3
	public static void pushEntrusMarket(String pustdata, String header) {
		String key = header + ":pushEntrusMarket";
		RedisService redisService = (RedisService) ContextUtil.getBean("redisService");
		String preData = redisService.get(key);
		if (null == preData || !preData.equals(pustdata)) {
			redisService.save(key, pustdata); // 推送委托变动信息，两张类型的
		}
	}

	public static void pushtheSeatEntrustCenter(String pustdata, String currencyType) {
		String key = currencyType + ":pushtheSeatEntrustCenter";
		RedisService redisService = (RedisService) ContextUtil.getBean("redisService");
		String preData = redisService.get(key);
		if (null == preData || !preData.equals(pustdata)) {
			redisService.save(key, pustdata); // 推送委托变动信息，两张类型的
		}

	}

	public static void pushtheSeatEntrustDephCenter(String pustdata, String currencyType, String key1) {
		String key = currencyType + ":pushtheSeatEntrustCenter" + key1;
		RedisService redisService = (RedisService) ContextUtil.getBean("redisService");
		String preData = redisService.get(key);
		if (null == preData || !preData.equals(pustdata)) {
			redisService.save(key, pustdata); // 推送委托变动信息，两张类型的
		}

	}

	public static void pushEntrusDephMarket(String pustdata, String currencyType, String key1) {
		String key = currencyType + ":pushEntrusMarket" + key1;
		RedisService redisService = (RedisService) ContextUtil.getBean("redisService");
		String preData = redisService.get(key);
		if (null == preData || !preData.equals(pustdata)) {
			redisService.save(key, pustdata); // 推送委托变动信息，两张类型的
		}
	}
	   //成交记录全集的推送
	public static void pushNewListRecordMarketAsc(String pustdata, String currencyType) {
		String key = currencyType + ":pushNewListRecordMarket";
		RedisService redisService = (RedisService) ContextUtil.getBean("redisService");
		redisService.save(key, pustdata);
	}
	   //成交记录全集的推送
	public static void pushNewListRecordMarketDesc(String pustdata, String header) {
		String key = header + ":pushNewListRecordMarketDesc";
		RedisService redisService = (RedisService) ContextUtil.getBean("redisService");
		redisService.save(key, pustdata);
	}
	   //成交记录增量的推送
	public static void pushNewListRecordMarketnewAdd(String pustdata, String currencyType) {
		String key = currencyType + ":pushNewListRecordMarketnewAdd";
		RedisService redisService = (RedisService) ContextUtil.getBean("redisService");
		if (!"".equals(pustdata)) {
			redisService.save(key, pustdata);
		}
	}
	   //end行情，=====================================================================================
	   
	   
}
