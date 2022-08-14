package hry.trade.model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import hry.core.thread.ThreadPool;
import hry.exchange.product.model.ExCointoCoin;
import hry.redis.common.dao.RedisUtil;
import hry.redis.common.utils.RedisService;
import hry.redis.common.utils.RedisTradeService;
import hry.trade.MQmanager.MQEnter;
import hry.trade.comparator.AscBigDecimalComparator;
import hry.trade.comparator.DescBigDecimalComparator;
import hry.trade.entrust.model.ExOrderInfo;
import hry.trade.model.affiliated.AffiliatedMain;
import hry.trade.redis.model.*;
import hry.util.properties.PropertiesUtils;
import hry.util.sys.ContextUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import tk.mybatis.mapper.util.StringUtil;

import java.math.BigDecimal;
import java.util.*;

public class TradeRedis {
	public static RedisUtil redisUtilAppAccount = new RedisUtil(AppAccountRedis.class);
	public static RedisUtil redisUtilExDigitalmoneyAccount = new RedisUtil(ExDigitalmoneyAccountRedis.class);
	public static RedisUtil redisUtilExEntrust = new RedisUtil(EntrustTrade.class);
	public static RedisUtil redisUtilEntrustByUser = new RedisUtil(EntrustByUser.class);
	public static List<Accountadd> aaddlists = new ArrayList<Accountadd>(); // 一次匹配增量记录
	public static List<ExOrderInfo> eoinfolists = new ArrayList<ExOrderInfo>(); // 一次匹配成交记录
    public static String noSaveEntrustByUser=null;
    public static Integer isNoSaveEntrustByUser=null;
	// public static HashMap<String,List<Entrust>> exEntrustsMemoryMap=new
	// HashMap<String,List<Entrust>>();

	public static String getHeader(EntrustTrade exEntrust) {
		if (exEntrust.getType().equals(2)) {
			String header = exEntrust.getCoinCode() + "_" + exEntrust.getFixPriceCoinCode() + ":sell";
			return header;
		} else {
			String header = exEntrust.getCoinCode() + "_" + exEntrust.getFixPriceCoinCode() + ":buy";
			return header;
		}
	}

	public static String getHeader(String coinCode, String fixPriceCoinCode, Integer type) {
		if (null == type) {
			String header = coinCode + "_" + fixPriceCoinCode;
			return header;
		} else if (type.equals(2)) {
			String header = coinCode + "_" + fixPriceCoinCode + ":sell";
			return header;
		} else {
			String header = coinCode + "_" + fixPriceCoinCode + ":buy";
			return header;
		}
	}

	public static String getHeaderMatch(String coinCode, String fixPriceCoinCode, Integer type) {
		if (null == type) {
			String header = coinCode + "_" + fixPriceCoinCode;
			return header;
		} else if (type.equals(1)) {
			String header = coinCode + "_" + fixPriceCoinCode + ":sell";
			return header;
		} else {
			String header = coinCode + "_" + fixPriceCoinCode + ":buy";
			return header;
		}
	}
	public static String getEntrustTimeFlag(String coinCode, String fixPriceCoinCode) {
			String header = coinCode + "_" + fixPriceCoinCode+":isTimgEntrsutFlag";
			return header;
	
	}
	public static String getHeaderMatch(EntrustTrade exEntrust) {
		if (exEntrust.getType().equals(1)) {
			String header = exEntrust.getCoinCode() + "_" + exEntrust.getFixPriceCoinCode() + ":sell";
			return header;
		} else {
			String header = exEntrust.getCoinCode() + "_" + exEntrust.getFixPriceCoinCode() + ":buy";
			return header;
		}
	}

	public static String getHeaderFront(EntrustTrade exEntrust) {
		String header = exEntrust.getCoinCode() + "_" + exEntrust.getFixPriceCoinCode();
		return header;

	}



/*	public static List<EntrustTrade> getMatch(EntrustTrade exEntrust) {

		String key = getHeaderMatch(exEntrust);
		RedisService redisService = (RedisService) ContextUtil.getBean("redisService");
		String v = redisService.get(key);
		List<EntrustTrade> list = JSON.parseArray(v, EntrustTrade.class);
		return list;
	}
*/
	public static void setSelfonePrice(EntrustTrade entrust,Jedis jedis) {
		String key = getHeaderFront(entrust);
		if (entrust.getType().equals(1)) { // 买
			String buyonePricekey = key + ":" + ExchangeDataCacheRedis.BuyOnePrice; // 自己单的买一
			String buyonePrices = jedis.get(buyonePricekey);
			if (StringUtil.isEmpty(buyonePrices)) {
				jedis.set(buyonePricekey, JSON.toJSONString(entrust.getEntrustPrice()));
			} else {
				if (new BigDecimal(buyonePrices).compareTo(entrust.getEntrustPrice()) == -1) {
					jedis.set(buyonePricekey, JSON.toJSONString(entrust.getEntrustPrice()));
				}
			}

		} else {
			String sellonePricekey = key + ":" + ExchangeDataCacheRedis.SellOnePrice; // 自己单的卖一
			String sellonePrices = jedis.get(sellonePricekey);
			if (StringUtil.isEmpty(sellonePrices)) {
				jedis.set(sellonePricekey, JSON.toJSONString(entrust.getEntrustPrice()));
			} else {
				if (new BigDecimal(sellonePrices).compareTo(entrust.getEntrustPrice()) == 1) {
					jedis.set(sellonePricekey, JSON.toJSONString(entrust.getEntrustPrice()));
				}
			}
		}
	}
	public static String getTradeDealEntrustChangeNum(redis.clients.jedis.Transaction transaction){
		RedisService redisService = (RedisService) ContextUtil.getBean("redisService");
		String v=redisService.get(ExchangeDataCacheRedis.TradeDealEntrustChangeNum);
		String numstr="";
		if(!StringUtil.isEmpty(v)&&!"null".equals(v)){
			String num=String.valueOf((Integer.valueOf(v)+1));
			transaction.set(ExchangeDataCacheRedis.TradeDealEntrustChangeNum,num);
			 numstr= ExchangeDataCacheRedis.TradeDealEntrustChange+":"+num;
		}else{
			transaction.set(ExchangeDataCacheRedis.TradeDealEntrustChangeNum,"0");
			numstr=ExchangeDataCacheRedis.TradeDealEntrustChange+":"+0;
		}
		
		String TradeDealEntrustChangeNumListkey="";
		String tradeDealEntrustChangeNumFlag=redisService.get(ExchangeDataCacheRedis.TradeDealEntrustChangeNumFlag);
		if(StringUtil.isEmpty(tradeDealEntrustChangeNumFlag)||tradeDealEntrustChangeNumFlag.equals("1")){
			TradeDealEntrustChangeNumListkey=ExchangeDataCacheRedis.TradeDealEntrustChangeNumList1;
		}else{
			TradeDealEntrustChangeNumListkey=ExchangeDataCacheRedis.TradeDealEntrustChangeNumList2;
		}
		
		List<String> list=new ArrayList<String>();
		String tradeDealEntrustChangeNumListvar= redisService.get(TradeDealEntrustChangeNumListkey);
		if(!StringUtil.isEmpty(tradeDealEntrustChangeNumListvar)){
			list = JSON.parseArray(tradeDealEntrustChangeNumListvar, String.class);
		}
		list.add(numstr);
		transaction.set(TradeDealEntrustChangeNumListkey,  JSON.toJSONString(list));
	    return numstr;
	}
	public static String getTradeDealAccountChangeNum(redis.clients.jedis.Transaction transaction){
		RedisService redisService = (RedisService) ContextUtil.getBean("redisService");
		String v=redisService.get(ExchangeDataCacheRedis.TradeDealAccountChangeNum);
		String numstr="";
		if(!StringUtil.isEmpty(v)&&!"null".equals(v)){
			String num=String.valueOf((Integer.valueOf(v)+1));
			transaction.set(ExchangeDataCacheRedis.TradeDealAccountChangeNum, num);
			numstr= ExchangeDataCacheRedis.TradeDealAccountChange+":"+num;
		}else{
			transaction.set(ExchangeDataCacheRedis.TradeDealAccountChangeNum,"0");
			numstr=ExchangeDataCacheRedis.TradeDealAccountChange+":"+0;
		}
		
		String TradeDealAccountChangeNumListkey="";
		String tradeDealAccountChangeNumFlag=redisService.get(ExchangeDataCacheRedis.TradeDealAccountChangeNumFlag);
		if(StringUtil.isEmpty(tradeDealAccountChangeNumFlag)||tradeDealAccountChangeNumFlag.equals("1")){
			TradeDealAccountChangeNumListkey=ExchangeDataCacheRedis.TradeDealAccountChangeNumList1;
		}else{
			TradeDealAccountChangeNumListkey=ExchangeDataCacheRedis.TradeDealAccountChangeNumList2;
		}
		List<String> list=new ArrayList<String>();
		String tradeDealEntrustChangeNumListvar= redisService.get(TradeDealAccountChangeNumListkey);
		if(!StringUtil.isEmpty(tradeDealEntrustChangeNumListvar)){
			list = JSON.parseArray(tradeDealEntrustChangeNumListvar, String.class);
		}
		list.add(numstr);
		transaction.set(TradeDealAccountChangeNumListkey,  JSON.toJSONString(list));
		return numstr;
	}
	public static String getTradeDealOrderInfoChangeNum(redis.clients.jedis.Transaction transaction){
		RedisService redisService = (RedisService) ContextUtil.getBean("redisService");
		String v=redisService.get(ExchangeDataCacheRedis.TradeDealOrderInfoChangeNum);
		String numstr="";
		if(!StringUtil.isEmpty(v)&&!"null".equals(v)){
			String num=String.valueOf((Integer.valueOf(v)+1));
			transaction.set(ExchangeDataCacheRedis.TradeDealOrderInfoChangeNum, num);
			numstr= ExchangeDataCacheRedis.TradeDealOrderInfoChange+":"+num;
		}else{
			transaction.set(ExchangeDataCacheRedis.TradeDealOrderInfoChangeNum,"0");
			numstr=ExchangeDataCacheRedis.TradeDealOrderInfoChange+":"+0;
		}
		
		
		String TradeDealOrderInfoChangeNumListkey="";
		String tradeDealOrderInfoChangeNumFlag=redisService.get(ExchangeDataCacheRedis.TradeDealOrderInfoChangeNumFlag);
		if(StringUtil.isEmpty(tradeDealOrderInfoChangeNumFlag)||tradeDealOrderInfoChangeNumFlag.equals("1")){
			TradeDealOrderInfoChangeNumListkey=ExchangeDataCacheRedis.TradeDealOrderInfoChangeNumList1;
		}else{
			TradeDealOrderInfoChangeNumListkey=ExchangeDataCacheRedis.TradeDealOrderInfoChangeNumList2;
		}
		List<String> list=new ArrayList<String>();
		String tradeDealEntrustChangeNumListvar= redisService.get(TradeDealOrderInfoChangeNumListkey);
		if(!StringUtil.isEmpty(tradeDealEntrustChangeNumListvar)){
			list = JSON.parseArray(tradeDealEntrustChangeNumListvar, String.class);
		}
		list.add(numstr);
		transaction.set(TradeDealOrderInfoChangeNumListkey,  JSON.toJSONString(list));
		return numstr;
	}
	public static void NoMatchEnd(EntrustTrade entrust, List<Accountadd> aadds) {
		JedisPool jedisPool = (JedisPool) ContextUtil.getBean("jedisPool");
		Jedis jedis=jedisPool.getResource();
		try {
			jedis.set("testredis1IsSave", "1");  //测试redis1是否不能保存修改值，以防发生数据不一致
			setSelfonePrice(entrust,jedis);
			putSelfEntrustTradelist(entrust);// 进自己的委托单列表// 进匹配委托
			MQEnter.pushDealFundMQ(aadds); //发出mq给缓存里的账号改变金额,添加资金的增量记录

			
			// 已经改变了的委托单；
			List<EntrustTrade> listchange=new ArrayList<EntrustTrade>();
			listchange.add(entrust);
			
			
			redis.clients.jedis.Transaction transaction = jedis.multi();
			
			transaction.set(getTradeDealEntrustChangeNum(transaction), JSON.toJSONString(listchange));
			List<Object> list =transaction.exec();

			putEntrustByUser(entrust);
			//通知定时器需要标记需要深度计算
			RedisTradeService redisTradeService = (RedisTradeService) ContextUtil.getBean("redisTradeService");
			redisTradeService.save(getEntrustTimeFlag(entrust.getCoinCode(), entrust.getFixPriceCoinCode()),"1");
		}finally {
			jedis.close();
		}	

	}
	public static void matchOneEnd(List<Accountadd> exEntrustAccountadd, EntrustTrade exEntrust,
			Map<String, List<EntrustTrade>> maping, List<EntrustTrade> listed, BigDecimal matchonePrice) {
		JedisPool jedisPool = (JedisPool) ContextUtil.getBean("jedisPool");
		Jedis jedis=jedisPool.getResource();
		try {
			jedis.set("testredis1IsSave", "1");  //测试redis1是否不能保存修改值，以防发生数据不一致
			aaddlists.addAll(0, exEntrustAccountadd);
			listed.add(exEntrust);
			if (!exEntrust.getStatus().equals(2)) {
				putSelfEntrustTradelist(exEntrust);// 进自己的委托单列表
				setSelfonePrice(exEntrust,jedis);// 设置自己队列的委一价
			}
			// 改变的对手单
			RedisTradeService redisTradeService = (RedisTradeService) ContextUtil.getBean("redisTradeService");
			for (Map.Entry<String, List<EntrustTrade>> entry : maping.entrySet()) {
				List<EntrustTrade> listing = entry.getValue();
				if (null == listing || listing.size() == 0) {
					redisTradeService.delete(entry.getKey());
				} else {
					redisTradeService.save(entry.getKey(), JSON.toJSONString(listing));
				}
	
			}
			MQEnter.pushDealFundMQ(aaddlists); //发出mq给缓存里的账号改变金额,添加资金的增量记录

			setMatchOnePrice(matchonePrice,exEntrust,jedis); //对手单一价
			String keyFront = getHeaderFront(exEntrust);
			setExchangeDataCache(eoinfolists,keyFront,jedis); //设置成交列表，最新成交价
			
			
			
		    redis.clients.jedis.Transaction transaction = jedis.multi();
		
			transaction.set(getTradeDealEntrustChangeNum(transaction), JSON.toJSONString(listed));// 已经改变了的委托单；
			transaction.set(getTradeDealOrderInfoChangeNum(transaction), JSON.toJSONString(eoinfolists));// 成交信息
			List<Object> list =transaction.exec();

			for (EntrustTrade entrust : listed) {
				putEntrustByUser(entrust);
			}

			//刷新时间分区的最新价，最高价，最低价，收盘价
			Integer klineType=findKlineTypeByName(exEntrust.getCoinCode(),exEntrust.getFixPriceCoinCode());
			if(klineType.intValue()==0){
				//刷新时间分区的最新价，最高价，最低价，收盘价
				List<ExOrderInfo> arr = new ArrayList<ExOrderInfo>();
				arr.addAll(eoinfolists);
				PeriodLastKLineListRunable periodLastKLineListRunable = new PeriodLastKLineListRunable(arr);
				ThreadPool.exe(periodLastKLineListRunable);
			}
			aaddlists.clear();
			eoinfolists.clear();
			//通知定时器需要标记需要深度计算
			redisTradeService.save(getEntrustTimeFlag(exEntrust.getCoinCode(), exEntrust.getFixPriceCoinCode()),"1");
		}finally {
			jedis.close();
		}
	}
	public static void cancelEntrust(EntrustTrade exEntrust, List<EntrustTrade> entrustlist, String key,
			List<Accountadd> aadds) {
		
		JedisPool jedisPool = (JedisPool) ContextUtil.getBean("jedisPool");
		Jedis jedis=jedisPool.getResource();
		try {
			
			jedis.set("testredis1IsSave", "1");  //测试redis1是否不能保存修改值，以防发生数据不一致
			putIngExEntrust(entrustlist, key); // 还没完成的委托单；
			MQEnter.pushDealFundMQ(aadds); //发出mq给缓存里的账号改变金额,添加资金的增量记录

			setcancelSelfOnePrice(exEntrust,jedis);// 必须最后一步
			
			// 已经改变了的委托单；
			RedisService redisService = (RedisService) ContextUtil.getBean("redisService");
			List<EntrustTrade> listchange=new ArrayList<EntrustTrade>();
			listchange.add(exEntrust);
			
			
			redis.clients.jedis.Transaction transaction = jedis.multi();
			
			transaction.set(getTradeDealEntrustChangeNum(transaction), JSON.toJSONString(listchange));
			List<Object> list =transaction.exec();

			putEntrustByUser(exEntrust);
			//通知定时器需要标记需要深度计算
			RedisTradeService redisTradeService = (RedisTradeService) ContextUtil.getBean("redisTradeService");
			redisTradeService.save(getEntrustTimeFlag(exEntrust.getCoinCode(), exEntrust.getFixPriceCoinCode()),"1");
		}finally {
			jedis.close();
		}
			
	}
	
	public static void setcancelSelfOnePrice(EntrustTrade exEntrust,Jedis jedis){
		List<BigDecimal> keyslist = TradeRedis.getSelfkeys(exEntrust);// 查所有的keys
		String keyFront = getHeaderFront(exEntrust);
		if (null != keyslist && keyslist.size() > 0) {
			BigDecimal onePrice=keyslist.get(0);
			if (null != onePrice) {
				if (exEntrust.getType().equals(1)) { // 
					String buyonePricekey = keyFront + ":" + ExchangeDataCacheRedis.BuyOnePrice; // 买一
					jedis.set(buyonePricekey, JSON.toJSONString(onePrice));
				} else {
					String sellonePricekey = keyFront + ":" + ExchangeDataCacheRedis.SellOnePrice; // 卖一
					jedis.set(sellonePricekey, JSON.toJSONString(onePrice));
				}
			}
			
		}else{
			if (exEntrust.getType().equals(1)) { // 
				String buyonePricekey = keyFront + ":" + ExchangeDataCacheRedis.BuyOnePrice; // 买一
				jedis.del(buyonePricekey);
			} else {
				String sellonePricekey = keyFront + ":" + ExchangeDataCacheRedis.SellOnePrice; // 卖一
				jedis.del(sellonePricekey);
			}
			
		}
	}
	
	public static void setMatchOnePrice(BigDecimal matchonePrice,EntrustTrade exEntrust,Jedis jedis){
		RedisService redisService = (RedisService) ContextUtil.getBean("redisService");
		String keyFront = getHeaderFront(exEntrust);
		if (null != matchonePrice) {
			if (exEntrust.getType().equals(2)) { // 卖
				String buyonePricekey = keyFront + ":" + ExchangeDataCacheRedis.BuyOnePrice; // 对手单的买一
				jedis.set(buyonePricekey, JSON.toJSONString(matchonePrice));
			} else {
				String sellonePricekey = keyFront + ":" + ExchangeDataCacheRedis.SellOnePrice; // 对手单的卖一
				jedis.set(sellonePricekey, JSON.toJSONString(matchonePrice));
			}
		}else{

			if (exEntrust.getType().equals(2)) { // 卖
				String buyonePricekey = keyFront + ":" + ExchangeDataCacheRedis.BuyOnePrice; // 对手单的买一
				jedis.del(buyonePricekey);
			} else {
				String sellonePricekey = keyFront + ":" + ExchangeDataCacheRedis.SellOnePrice; // 对手单的卖一
				jedis.set(sellonePricekey, JSON.toJSONString(matchonePrice));
				jedis.del(sellonePricekey);
			}
		
		}
		
	}
	public static void setExchangeDataCache(List<ExOrderInfo> listExOrderInfo,String header,Jedis jedis) {
		ExOrderInfo exOrderInfo=listExOrderInfo.get(listExOrderInfo.size()-1); //最新成交
		// 设置当前最新成交价
		TradeRedis.setStringData(header + ":" + ExchangeDataCacheRedis.CurrentExchangPrice, exOrderInfo.getTransactionPrice().setScale(10, BigDecimal.ROUND_HALF_UP).toString());
		// 保存这条成交记录到re
		String v=jedis.get(header + ":" + ExchangeDataCacheRedis.LastOrderRecords);
		List<ExOrderInfo> list = null;
		if(!StringUtil.isEmpty(v)){
			 list = JSON.parseArray(v, ExOrderInfo.class);
		}else{
			list=new ArrayList<ExOrderInfo>();
		}
		list.addAll(listExOrderInfo);
		if(list.size()>ExchangeDataCacheRedis.LastOrderRecordsLmit){
			List<ExOrderInfo> sublist=list.subList(list.size()-ExchangeDataCacheRedis.LastOrderRecordsLmit, list.size());
			jedis.set(header + ":" +ExchangeDataCacheRedis.LastOrderRecords, JSON.toJSONString(sublist));
		}else{
			jedis.set(header + ":" +ExchangeDataCacheRedis.LastOrderRecords, JSON.toJSONString(list));
			
		}
		//成交后附件得功能都在这
		List<ExOrderInfo> listOrderInfo = new ArrayList<>();
		listOrderInfo.addAll(listExOrderInfo);
		AffiliatedMain.affiliated(listOrderInfo);
		
	}


	public static void putEntrustByUser(EntrustTrade entrust) {
	//	Boolean flag=isSaveEntrustByUser(entrust.getUserName());
		Boolean flag=entrust.getSource().equals(2)?true:false;//深度机器人
		if(!flag){
			EntrustByUser ebu = (EntrustByUser) redisUtilEntrustByUser.get(entrust.getCustomerId().toString());
			if (null == ebu) {
				ebu = new EntrustByUser();
				ebu.addEntrust(entrust);
				ebu.setCustomerId(entrust.getCustomerId());
			} else {
				ebu.addEntrust(entrust);
			}
			if(ebu.getEntrustedmap().size()==0&&ebu.getEntrustingmap().size()==0){
				 TradeRedis.redisUtilEntrustByUser.delete(ebu.getCustomerId().toString());
			}else{
			     TradeRedis.redisUtilEntrustByUser.put(ebu, ebu.getCustomerId().toString());
			}
		}
		
	
		
	}
   public static Boolean isSaveEntrustByUser(String userName){
		if(null!=isNoSaveEntrustByUser){ //第二次开始就走这
				if(null!=noSaveEntrustByUser){
					int i=0;
					String[] rt=noSaveEntrustByUser.split(",");
					while(i<rt.length){
						if(rt[i].equals(userName)){
							return true;
						}
						i++;
					}
					
				}
			return false;
		}else{
			isNoSaveEntrustByUser=new Integer("1"); //启动后的第一次就走这
			String appisNoSaveEntrustByUser=PropertiesUtils.APP.getProperty("app.noSaveEntrustByUser");
			if(!StringUtil.isEmpty(appisNoSaveEntrustByUser)){
				noSaveEntrustByUser=appisNoSaveEntrustByUser;
				int i=0;
				String[] rt=appisNoSaveEntrustByUser.split(",");
				while(i<rt.length){
					if(rt[i].equals(userName)){
						
						return true;
					}
					i++;
				}
	        	
	        }
			return false;
		} 
   }
	public static void putchange(EntrustTrade entrust) {

		RedisService redisService = (RedisService) ContextUtil.getBean("redisService");
		String v = redisService.get(ExchangeDataCacheRedis.ChangeEntrust);
		List<EntrustTrade> list = JSON.parseArray(v, EntrustTrade.class);
		if (null == list) {
			list = new ArrayList<EntrustTrade>();
		}
	//	if (entrust.equals(2)) {
			list.add(entrust);
	//	}

		redisService.save(ExchangeDataCacheRedis.ChangeEntrust, JSON.toJSONString(list));
	}

	public static void matchOneAndOneEnd(ExOrderInfo exOrderInfo, List<Accountadd> aadds) {
		eoinfolists.add(exOrderInfo);
		for (Accountadd aadd : aadds) {
			aaddlists.add(aadd);
		}
	}

/*	public static void putAccountaddlist(List<Accountadd> aadds) {

		RedisService redisService = (RedisService) ContextUtil.getBean("redisService");
		String v = redisService.get(ExchangeDataCacheRedis.AccountAddS);
		List<Accountadd> list = JSON.parseArray(v, Accountadd.class);
		if (null == list) {
			list = new ArrayList<Accountadd>();
		}
		for (Accountadd accountadd : aadds) {
			list.add(accountadd);
		}
		redisService.save(ExchangeDataCacheRedis.AccountAddS, JSON.toJSONString(list));

		
	}*/

	public static void putExOrderInfolist(List<ExOrderInfo> oinfolist) {
		RedisService redisService = (RedisService) ContextUtil.getBean("redisService");
		String v = redisService.get(ExchangeDataCacheRedis.ExorderInfoS);
		List<ExOrderInfo> list = JSON.parseArray(v, ExOrderInfo.class);
		if (null == list) {
			list = new ArrayList<ExOrderInfo>();
		}
		for (ExOrderInfo info : oinfolist) {
			list.add(info);
		}
		redisService.save(ExchangeDataCacheRedis.ExorderInfoS, JSON.toJSONString(list));
	}

	public static void putSelfEntrustTradelist(EntrustTrade exEntrust) {
		String key = getHeader(exEntrust) + ":" + exEntrust.getEntrustPrice().setScale(10, BigDecimal.ROUND_HALF_EVEN).toString();
		RedisTradeService redisTradeService = (RedisTradeService) ContextUtil.getBean("redisTradeService");
		String v = redisTradeService.get(key);
		List<EntrustTrade> list = JSON.parseArray(v, EntrustTrade.class);
		if (null == list) {
			list = new ArrayList<EntrustTrade>();
		}
		list.add(exEntrust);
		redisTradeService.save(key, JSON.toJSONString(list));
	}



	public static BigDecimal getMatchOnePrice(EntrustTrade exEntrust) {
		RedisService redisService = (RedisService) ContextUtil.getBean("redisService");
		String key = "";
		if (exEntrust.getType().equals(1)) {
			key = getHeaderFront(exEntrust) + ":" + ExchangeDataCacheRedis.SellOnePrice;
		} else {
			key = getHeaderFront(exEntrust) + ":" + ExchangeDataCacheRedis.BuyOnePrice;
		}
		String onePrice = redisService.get(key);
		if (StringUtil.isEmpty(onePrice)) {
			return null;
		} else {
			return new BigDecimal(onePrice);
		}

	}

	/*
	 * public static BigDecimal getOnePrice(EntrustTrade exEntrust){
	 * RedisService redisService = (RedisService)
	 * ContextUtil.getBean("redisService"); String key = "";
	 * if(exEntrust.getType().equals(1)){ key =
	 * getHeaderMatch(exEntrust)+":"+SellOnePrice; }else{ key =
	 * getHeaderMatch(exEntrust)+":"+BuyOnePrice; } String onePrice=
	 * redisService.get(key); if(StringUtil.isEmpty(onePrice)){ return null;
	 * }else{ return new BigDecimal(onePrice); }
	 * 
	 * 
	 * }
	 */
	public static void putIngExEntrust(List<EntrustTrade> exEntrustlist, String key) {
		RedisTradeService redisTradeService = (RedisTradeService) ContextUtil.getBean("redisTradeService");
	/*	if (null == exEntrustlist) {
			exEntrustlist = new ArrayList<EntrustTrade>();
		} else {
		   //时间优先
		}*/
		if (null == exEntrustlist || exEntrustlist.size() == 0) {
			redisTradeService.delete(key);
		} else {
			redisTradeService.save(key, JSON.toJSONString(exEntrustlist));
		}
		

	}

	public static void put(List<EntrustTrade> exEntrustlist, String type) {
		if (null == exEntrustlist || exEntrustlist.size() == 0) {
			return;
		}
		RedisService redisService = (RedisService) ContextUtil.getBean("redisService");
		if (type.equals("change")) {
			String v = redisService.get(ExchangeDataCacheRedis.ChangeEntrust);
			List<EntrustTrade> list = JSON.parseArray(v, EntrustTrade.class);
			if (null == list) {
				redisService.save(ExchangeDataCacheRedis.ChangeEntrust, JSON.toJSONString(exEntrustlist));
			} else {
				for (EntrustTrade entrust : exEntrustlist) {
					list.add(entrust);
					putEntrustByUser(entrust);
				}
				redisService.save(ExchangeDataCacheRedis.ChangeEntrust, JSON.toJSONString(list));
			}

		}

	}

	public static void put(ExOrderInfo exOrderInfo) {
		RedisService redisService = (RedisService) ContextUtil.getBean("redisService");
		String v = redisService.get(ExchangeDataCacheRedis.ExorderInfoS);
		List<ExOrderInfo> list = JSON.parseArray(v, ExOrderInfo.class);
		list.add(exOrderInfo);
		redisService.save(ExchangeDataCacheRedis.ExorderInfoS, JSON.toJSONString(list));
	}

	public static void put(Accountadd accountadd) {
		RedisService redisService = (RedisService) ContextUtil.getBean("redisService");
		String v = redisService.get(ExchangeDataCacheRedis.AccountAddS);
		List<Accountadd> list = JSON.parseArray(v, Accountadd.class);
		list.add(accountadd);
		redisService.save(ExchangeDataCacheRedis.AccountAddS, JSON.toJSONString(list));
	}

	public static String getStringData(String key) {
		RedisService redisService = (RedisService) ContextUtil.getBean("redisService");
		String v = redisService.get(key);
		return v;

	}
	public static String getTradeStringData(String key) {
		RedisTradeService redisTradeService = (RedisTradeService) ContextUtil.getBean("redisTradeService");
		
			String v = redisTradeService.get(key);
			return v;

     }
	public static void setStringData(String key, String val) {
		RedisService redisService = (RedisService) ContextUtil.getBean("redisService");
		String preData = redisService.save(key, val);

	}
	public static List<BigDecimal> getSelfkeys(EntrustTrade exEntrust) {
		String key = getHeader(exEntrust);
		RedisTradeService redisTradeService = (RedisTradeService) ContextUtil.getBean("redisTradeService");
		Set<String> keys = redisTradeService.noPerkeys(key + ":");
		if (null == keys) {
			return null;
		} else {
			List<BigDecimal> list2 = new ArrayList<BigDecimal>();
			Iterator<String> iterator = keys.iterator();
			while (iterator.hasNext()) {
				String keystr = iterator.next();
				BigDecimal ks = new BigDecimal(keystr.split(":")[2]);
				list2.add(ks);
			}
			if (exEntrust.getType().equals(2)) {
				Collections.sort(list2, new AscBigDecimalComparator());
			} else {
				Collections.sort(list2, new DescBigDecimalComparator());
			}
			return list2;
		}
	}
	public static List<BigDecimal> getMatchkeys(EntrustTrade exEntrust) {
		String key = getHeaderMatch(exEntrust);
		RedisTradeService redisTradeService = (RedisTradeService) ContextUtil.getBean("redisTradeService");
		Set<String> keys = redisTradeService.noPerkeys(key + ":");
		if (null == keys) {
			return null;
		} else {
			List<BigDecimal> list2 = new ArrayList<BigDecimal>();
			List<BigDecimal> lin = new ArrayList<BigDecimal>();
			Iterator<String> iterator = keys.iterator();
			while (iterator.hasNext()) {
				String keystr = iterator.next();
				BigDecimal ks = new BigDecimal(keystr.split(":")[2]);
				if(ks.compareTo(new BigDecimal("0"))==0){
					lin.add(ks);
				}else{
					
					if(exEntrust.getEntrustWay().intValue()==2){
						list2.add(ks);
					}else{
						
						if (exEntrust.getType().equals(1)&&exEntrust.getEntrustPrice().compareTo(ks)>=0) {
							list2.add(ks);
						}
				    	
						if (exEntrust.getType().equals(2)&&exEntrust.getEntrustPrice().compareTo(ks)<=0) {
							list2.add(ks);
						}
					}
				}
			}
			if (exEntrust.getType().equals(1)) {
				Collections.sort(list2, new AscBigDecimalComparator());
			} else {
				Collections.sort(list2, new DescBigDecimalComparator());
			}
			if(exEntrust.getEntrustWay().intValue()==1&&lin.size()>0){ //如果下单是限价才需要市价对手单
				list2.addAll(lin);
				return list2;
			}
			return list2;
		}
	}
	public static List<BigDecimal> getMatchkeysmarket(EntrustTrade exEntrust) {
		String key = getHeaderMatch(exEntrust);
		RedisTradeService redisTradeService = (RedisTradeService) ContextUtil.getBean("redisTradeService");
		Set<String> keys = redisTradeService.noPerkeys(key + ":");
		if (null == keys) {
			return null;
		} else {
			List<BigDecimal> list2 = new ArrayList<BigDecimal>();
			Iterator<String> iterator = keys.iterator();
			while (iterator.hasNext()) {
				String keystr = iterator.next();
				BigDecimal ks = new BigDecimal(keystr.split(":")[2]);
				if(ks.compareTo(new BigDecimal("0"))==0){
				}else{
			    	list2.add(ks);
				}
			}
			if (exEntrust.getType().equals(1)) {
				Collections.sort(list2, new AscBigDecimalComparator());
			} else {
				Collections.sort(list2, new DescBigDecimalComparator());
			}
		
			return list2;
		}
	}
	public static List<EntrustTrade> getMatchEntrustTradeBykey(String keys) {
		RedisTradeService redisTradeService = (RedisTradeService) ContextUtil.getBean("redisTradeService");
		String v = redisTradeService.get(keys);
		List<EntrustTrade> list = JSON.parseArray(v, EntrustTrade.class);
		return list;
	}
	/*
	 * public static void put(Entrust exEntrust){ String
	 * key=getHeader(exEntrust); List<Entrust> exEntrustlist
	 * =exEntrustsMemoryMap.get(key); exEntrustlist.add(exEntrust);
	 * Collections.sort(exEntrustlist, new DescEntrustComparator());
	 * exEntrustsMemoryMap.put(key, exEntrustlist); } public static
	 * List<Entrust> get(Entrust exEntrust){ String key=getHeader(exEntrust);
	 * List<Entrust> exEntrustlist =exEntrustsMemoryMap.get(key); return
	 * exEntrustlist; }
	 */

	/*根据交易对查找 k线模式
	 * */
	public static Integer findKlineTypeByName(String coinCode,String fixCoinCode){
		RedisService redisService = (RedisService) ContextUtil.getBean("redisService");
		String exCointoCoinStr = redisService.hget("ex:cointoCoin",coinCode+":"+fixCoinCode);
		if(!StringUtil.isEmpty(exCointoCoinStr)){
			ExCointoCoin exCointoCoin = JSONObject.parseObject(exCointoCoinStr,ExCointoCoin.class);
			if(null==exCointoCoin.getKlineType()){

				return 0;
			}else{
				return exCointoCoin.getKlineType();
			}

		}

		return 0;
	}
}
