package hry.trade.kline;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import hry.manage.remote.model.Coin2;
import hry.redis.common.utils.RedisService;
import hry.trade.entrust.ExchangeDataCache;
import hry.trade.entrust.dao.ExOrderDao;
import hry.trade.entrust.model.ExOrder;
import hry.trade.entrust.service.ExOrderService;
import hry.util.QueryFilter;
import hry.util.date.DateUtil;
import hry.util.klinedata.InsertRunnable;
import hry.util.klinedata.TransactionOrder;
import hry.util.properties.PropertiesUtils;
import hry.util.sys.ContextUtil;
import hry.web.app.model.AppHolidayConfig;
import hry.web.remote.RemoteAppConfigService;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * K线生成方法
 * <p>
 * TODO
 * </p>
 * 
 * @author: Liu Shilei
 * @Date : 2016年7月26日 上午10:05:04
 */
public class KlineEngine {

	private static final Logger logger = Logger.getLogger(KlineEngine.class);

	// 数据库中保留k线的数据条数
	public static final int HOLD_COUNT = 2000;

	private static ExecutorService executors = Executors.newFixedThreadPool(10);


	/**
	 * 时间分钟数校正
	 * <p>
	 * TODO
	 * </p>
	 * 
	 * @author: Liu Shilei
	 * @param: @param
	 *             date
	 * @param: @param
	 *             space
	 * @param: @return
	 *             返回时间区间开始值
	 * @return: Date
	 * @Date : 2016年5月3日 下午5:15:55
	 * @throws:
	 */
	public static Date timeRevise(Date date, int space) {
		String mm = DateUtil.dateToString(date, "mm");
		int i = Integer.valueOf(mm).intValue() % space;
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MINUTE, -i);
		return cal.getTime();
	}

	public static void addKlineNode(RedisService redisService, String coinCode, TransactionOrder order, JSONObject rootObj, String period) {
		if (rootObj != null) {
			JSONObject obj = rootObj.getJSONObject(period);
			if (obj != null) {
				if (obj.getString("startTime").equals(order.getTransactionTime())) {// 如果和备份节点时间区间相同
					order.setMinPrice(obj.getBigDecimal("priceLow"));
					order.setMaxPrice(obj.getBigDecimal("priceHigh"));
					order.setStartPrice(obj.getBigDecimal("priceOpen"));
					order.setEndPrice(obj.getBigDecimal("priceLast"));
					order.setTransactionCount(obj.getBigDecimal("amount"));
				} else {
					String currencyStr = redisService.get(coinCode + ":PeriodLastKLineList");
					if (!StringUtils.isEmpty(currencyStr)) {
						JSONArray parseArray = JSON.parseArray(currencyStr);
						for (int i = 0; i < parseArray.size(); i++) {
							JSONObject jsonObject = parseArray.getJSONObject(i);
							if (period.equals(jsonObject.getString("period"))) {
								if (jsonObject.getString("startTime").equals(order.getTransactionTime())) {// 如果在一个区间
									order.setMinPrice(jsonObject.getBigDecimal("priceLow"));
									order.setMaxPrice(jsonObject.getBigDecimal("priceHigh"));
									order.setStartPrice(jsonObject.getBigDecimal("priceOpen"));
									order.setEndPrice(jsonObject.getBigDecimal("priceLast"));
									order.setTransactionCount(jsonObject.getBigDecimal("amount"));
								} else {
									order.setMinPrice(jsonObject.getBigDecimal("priceLast"));
									order.setMaxPrice(jsonObject.getBigDecimal("priceLast"));
									order.setStartPrice(jsonObject.getBigDecimal("priceLast"));
									order.setEndPrice(jsonObject.getBigDecimal("priceLast"));
									// order.setTransactionCount(obj.getBigDecimal("amount"));
								}
							}
						}
					}

				}
			} else {
				String currencyStr = redisService.get(coinCode + ":PeriodLastKLineList");
				if (!StringUtils.isEmpty(currencyStr)) {
					JSONArray parseArray = JSON.parseArray(currencyStr);
					for (int i = 0; i < parseArray.size(); i++) {
						JSONObject jsonObject = parseArray.getJSONObject(i);
						if (period.equals(jsonObject.getString("period"))) {
							if (jsonObject.getString("startTime").equals(order.getTransactionTime())) {// 如果在一个区间
								order.setMinPrice(jsonObject.getBigDecimal("priceLow"));
								order.setMaxPrice(jsonObject.getBigDecimal("priceHigh"));
								order.setStartPrice(jsonObject.getBigDecimal("priceOpen"));
								order.setEndPrice(jsonObject.getBigDecimal("priceLast"));
								order.setTransactionCount(jsonObject.getBigDecimal("amount"));
							} else {
								order.setMinPrice(jsonObject.getBigDecimal("priceLast"));
								order.setMaxPrice(jsonObject.getBigDecimal("priceLast"));
								order.setStartPrice(jsonObject.getBigDecimal("priceLast"));
								order.setEndPrice(jsonObject.getBigDecimal("priceLast"));
								// order.setTransactionCount(jsonObject.getBigDecimal("amount"));
							}
						}
					}
				}
			}
		} else {

			String currencyStr = redisService.get(coinCode + ":PeriodLastKLineList");
			if (!StringUtils.isEmpty(currencyStr)) {
				JSONArray parseArray = JSON.parseArray(currencyStr);
				for (int i = 0; i < parseArray.size(); i++) {
					JSONObject jsonObject = parseArray.getJSONObject(i);
					if (period.equals(jsonObject.getString("period"))) {
						if (jsonObject.getString("startTime").equals(order.getTransactionTime())) {// 如果在一个区间
							order.setMinPrice(jsonObject.getBigDecimal("priceLow"));
							order.setMaxPrice(jsonObject.getBigDecimal("priceHigh"));
							order.setStartPrice(jsonObject.getBigDecimal("priceOpen"));
							order.setEndPrice(jsonObject.getBigDecimal("priceLast"));
							order.setTransactionCount(jsonObject.getBigDecimal("amount"));
						} else {
							order.setMinPrice(jsonObject.getBigDecimal("priceLast"));
							order.setMaxPrice(jsonObject.getBigDecimal("priceLast"));
							order.setStartPrice(jsonObject.getBigDecimal("priceLast"));
							order.setEndPrice(jsonObject.getBigDecimal("priceLast"));
							// order.setTransactionCount(jsonObject.getBigDecimal("amount"));
						}
					}
				}
			}

		}

	}

	/**
	 * 计算方法
	 * <p>
	 * TODO
	 * </p>
	 * 
	 * @author: Liu Shilei
	 * @param: @param
	 *             time
	 * @param: @param
	 *             website
	 * @param: @param
	 *             currencyType
	 * @param: @param
	 *             coinCode
	 * @return: void
	 * @Date : 2016年7月28日 下午2:49:14
	 * @throws:
	 */
	private static TransactionOrder compute2(Integer time, String website, String currencyType, String coinCode) {


		Date nowDate = new Date();

		logger.error("触发" + time + "分钟" + coinCode + "k线数据生成器.............................." + DateUtil.dateToString(nowDate));
		// 如果是做市模式，就开启判断当前时间 在不在 开市时间
		boolean openTrade = isOpenTrade(time, nowDate);
		if (!openTrade) {
			logger.info(DateUtil.dateToString(nowDate) + "还没开市不生成K线");
			return null;
		}

		String table = coinCode + ":klinedata:TransactionOrder_" + coinCode + "_" + time;
		String newtable = coinCode + ":newklinedata:TransactionOrder_" + coinCode + "_" + time;

		// k线数据超过800条就将老的数据删除
		RedisService redisService = (RedisService) ContextUtil.getBean("redisService");
		List<TransactionOrder> orderList = JSON.parseArray(redisService.get(table), TransactionOrder.class);
		if (orderList == null) {
			orderList = new ArrayList<TransactionOrder>();
		}
		// 如果redis中老数据超过800条，只去最新的800条，其他的舍弃
		if (orderList != null && orderList.size() > HOLD_COUNT) {
			orderList = orderList.subList(0, HOLD_COUNT);
		}

		// 获得时间区间的前区间
		Date minDate = DateUtil.dateAddMinute(nowDate, -time);
		if (time == 30000) {// 如果传的值为30000，表示是月周期，则前区间以当前时间向前推一个月
			minDate = DateUtil.addMonth(nowDate, -1);
		}

		// 创建结点
		TransactionOrder order = new TransactionOrder();
		order.setId(DateUtil.dateToString(nowDate, "yyyyMMddHHmm"));
		order.setTransactionTime(DateUtil.dateToString(minDate, "yyyy-MM-dd HH:mm"));
		order.setTransactionEndTime(DateUtil.dateToString(nowDate, "yyyy-MM-dd HH:mm"));

		logger.error(order.getTransactionTime() + "---------" + order.getTransactionEndTime());

		order.setTransactionCount(new BigDecimal(0));
		// 由于当前时间段没有交易数据，所以全部设置为历史数据的收盘价
		order.setMinPrice(new BigDecimal(0));
		order.setMaxPrice(new BigDecimal(0));
		order.setStartPrice(new BigDecimal(0));
		order.setEndPrice(new BigDecimal(0));

		orderList.add(0, order);

		String backups = redisService.get(coinCode + ":PeriodLastKLineList_backups");
		JSONObject rootObj = JSON.parseObject(backups);
		if (time == 1) {
			addKlineNode(redisService, coinCode, order, rootObj, "1min");
		} else if (time == 5) {
			addKlineNode(redisService, coinCode, order, rootObj, "5min");
		} else if (time == 15) {
			addKlineNode(redisService, coinCode, order, rootObj, "15min");
		} else if (time == 30) {
			addKlineNode(redisService, coinCode, order, rootObj, "30min");
		} else if (time == 60) {
			addKlineNode(redisService, coinCode, order, rootObj, "60min");
		} else if (time == 1440) {
			addKlineNode(redisService, coinCode, order, rootObj, "1day");
			order.setTransactionTime(DateUtil.dateToString(minDate, "yyyy-MM-dd 00:00:00"));
			order.setTransactionEndTime(DateUtil.dateToString(nowDate, "yyyy-MM-dd 00:00:00"));

			// 更新昨日收盘价到cn:coinInfoList中
			String coinStr = redisService.get("cn:coinInfoList2");
			if (!StringUtils.isEmpty(coinStr)) {
				List<Coin2> coins = JSON.parseArray(coinStr, Coin2.class);
				// 判断coins是否有交易对信息
				boolean has = false;
				for (Coin2 c : coins) {
					if (coinCode.equals(c.getCoinCode() + "_" + c.getFixPriceCoinCode())) {
						c.setYesterdayPrice(order.getEndPrice().toString());
						redisService.save("cn:coinInfoList2", JSON.toJSONString(coins));
						has = true;
					}
				}
				// 如果没有交易对信息
				if (!has) {
					String[] split = coinCode.split("_");
					Coin2 coin2 = new Coin2();
					coin2.setCoinCode(split[0]);
					coin2.setFixPriceCoinCode(split[1]);
					coin2.setYesterdayPrice(order.getEndPrice().toString());
					coins.add(coin2);
					redisService.save("cn:coinInfoList2", JSON.toJSONString(coins));
				}
			} else {
				String[] split = coinCode.split("_");
				Coin2 coin2 = new Coin2();
				coin2.setCoinCode(split[0]);
				coin2.setFixPriceCoinCode(split[1]);
				coin2.setYesterdayPrice(order.getEndPrice().toString());
				List<Coin2> coins = new ArrayList<Coin2>();
				coins.add(coin2);
				redisService.save("cn:coinInfoList2", JSON.toJSONString(coins));
			}

		} else if (time == 10080) {
			addKlineNode(redisService, coinCode, order, rootObj, "1week");
		} else if (time == 30000) {
			addKlineNode(redisService, coinCode, order, rootObj, "1mon");
		}

		// 最后保存数据
		redisService.save(table, JSON.toJSONString(orderList));

		// 保存到redis
        redisService.zream(newtable,new Double(order.getId()));
		redisService.zadd(newtable,new Double(order.getId()),JSON.toJSONString(order));

		return order;
	}

	/**
	 * 计算方法
	 * <p>
	 * TODO
	 * </p>
	 * 
	 * @author: Liu Shilei
	 * @param: @param
	 *             time
	 * @param: @param
	 *             website
	 * @param: @param
	 *             currencyType
	 * @param: @param
	 *             coinCode
	 * @return: void
	 * @Date : 2016年7月28日 下午2:49:14
	 * @throws:
	 */
	public static void compute(Integer time, String website, String currencyType, String coinCode) {

		Date nowDate = new Date();

		logger.info("触发" + time + "分钟k线数据生成器.............................." + DateUtil.dateToString(nowDate));
		// 如果是做市模式，就开启判断当前时间 在不在 开市时间
		boolean openTrade = isOpenTrade(time, nowDate);
		if (!openTrade) {
			logger.info(DateUtil.dateToString(nowDate) + "还没开市不生成K线");
			return;
		}

		/**
		 * 算法介绍 一、获得当前时间,记录当前时间到上一个时间的K线结点 如果当前此节点没有数据，就查最后一天交易记录所在的K线时间段内的结点记录
		 * 
		 * 比如现在12:00，查11.59——12:00之间的数据，算出结点， 如果11.59——12:00之间没有得出结点数据,则查询最后一条交易记录，
		 * 比如最后一条交易记录在10：45 那么就查10:45所在的时间得出结点数据
		 * 
		 * 二、时间结点定位 1分钟就是按每1分钟间隔 3分钟从0.00开始 3,6,9,12.......60 5分钟 5,10,15......60 15分钟
		 * 15,30......60
		 * 
		 * 
		 */
		// 获得当前时间 格式化到分钟数

		String table = "TransactionOrder_" + website + "_" + currencyType + "_" + coinCode + "_" + time;

		// k线数据超过800条就将老的数据删除
		RedisService redisService = (RedisService) ContextUtil.getBean("redisService");
		List<TransactionOrder> orderList = JSON.parseArray(redisService.get(table), TransactionOrder.class);
		if (orderList == null) {
			orderList = new ArrayList<TransactionOrder>();
		}
		// 如果redis中老数据超过800条，只去最新的800条，其他的舍弃
		if (orderList != null && orderList.size() > HOLD_COUNT) {
			orderList = orderList.subList(0, HOLD_COUNT);
		}

		// 获得时间区间的前区间
		Date minDate = DateUtil.dateAddMinute(nowDate, -time);
		if (time == 30000) {// 如果传的值为30000，表示是月周期，则前区间以当前时间向前推一个月
			minDate = DateUtil.addMonth(nowDate, -1);
		}

		ExOrderDao exOrderDao = (ExOrderDao) ContextUtil.getBean("exOrderDao");
		Map<String, Object> query = new HashMap<String, Object>();
		query.put("minDate", DateUtil.dateToString(minDate, "yyyy-MM-dd HH:mm"));
		query.put("maxDate", DateUtil.dateToString(nowDate, "yyyy-MM-dd HH:mm"));
		query.put("website", website);
		query.put("currencyType", currencyType);
		String[] split = coinCode.split("_");
		query.put("coinCode", split[0]);
		query.put("fixPriceCoinCode", split[1]);
		List<TransactionOrder> list = exOrderDao.findOrder(query);
		// 如果两个时间差之间的数据不等于空
		if (list != null && list.size() > 0) {
			TransactionOrder exOrder = list.get(0);
			if (exOrder != null) {// 如果当前时间结点数据不为空
				// 如果数据库中没有当前结点,创建结点
				TransactionOrder transactionOrder = new TransactionOrder();
				transactionOrder.setId(DateUtil.dateToString(nowDate, "yyyyMMddHHmm"));
				transactionOrder.setTransactionTime(DateUtil.dateToString(minDate, "yyyy-MM-dd HH:mm"));
				transactionOrder.setTransactionEndTime(DateUtil.dateToString(nowDate, "yyyy-MM-dd HH:mm"));

				transactionOrder.setTransactionCount(exOrder.getTransactionCount());
				transactionOrder.setMinPrice(exOrder.getMinPrice());
				transactionOrder.setMaxPrice(exOrder.getMaxPrice());

				transactionOrder.setStartPrice(exOrder.getStartPrice());
				transactionOrder.setEndPrice(exOrder.getEndPrice());
				// 将新元素放到list的第一个位置
				orderList.add(0, transactionOrder);
			} else {// 如果当前时间结点数据为空,查询最近的一条交易记录,计算出所在的时间结点
				QueryFilter filter = new QueryFilter(ExOrder.class);
				filter.setSaasId(PropertiesUtils.APP.getProperty("app.saasId"));
				filter.addFilter("website=", website);
				filter.addFilter("currencyType=", currencyType);
				filter.addFilter("coinCode=", split[0]);
				filter.addFilter("fixPriceCoinCode=", split[1]);
				filter.setOrderby("transactionTime desc");
				Page<ExOrder> page = PageHelper.startPage(0, 1);

				ExOrderService exOrderService = (ExOrderService) ContextUtil.getBean("exOrderService");
				List<ExOrder> find = exOrderService.find(filter);
				List<ExOrder> result = page.getResult();
				if (find != null && find.size() > 0) {
					ExOrder exOrder2 = find.get(0);
					// 二次查询，查找最近一条交易订单的时间，计算出开始时间和结束时间区间值
					Date minDate2 = timeRevise(exOrder2.getTransactionTime(), time);
					Date maxDate2 = DateUtil.dateAddMinute(minDate2, time);

					Map<String, Object> query2 = new HashMap<String, Object>();
					query2.put("minDate", DateUtil.dateToString(minDate2, "yyyy-MM-dd HH:mm"));
					query2.put("maxDate", DateUtil.dateToString(maxDate2, "yyyy-MM-dd HH:mm"));
					query2.put("website", website);
					query2.put("currencyType", currencyType);
					query2.put("coinCode", split[0]);
					query2.put("fixPriceCoinCode", split[1]);
					List<TransactionOrder> list2 = exOrderDao.findOrder(query2);

					if (list2 != null && list2.size() > 0 && list2.get(0) != null) {
						TransactionOrder _transactionOrder2 = list2.get(0);
						// 判断这个前一个结点不为空,正常情况百分百不为空
						if (_transactionOrder2 != null) {// 如果数据库中没有当前结点,创建结点
							TransactionOrder transactionOrder2 = new TransactionOrder();
							transactionOrder2.setId(DateUtil.dateToString(nowDate, "yyyyMMddHHmm"));
							transactionOrder2.setTransactionTime(DateUtil.dateToString(minDate, "yyyy-MM-dd HH:mm"));
							transactionOrder2.setTransactionEndTime(DateUtil.dateToString(nowDate, "yyyy-MM-dd HH:mm"));

							transactionOrder2.setTransactionCount(new BigDecimal(0));
							// 由于当前时间段没有交易数据，所以全部设置为历史数据的收盘价
							transactionOrder2.setMinPrice(_transactionOrder2.getEndPrice());
							transactionOrder2.setMaxPrice(_transactionOrder2.getEndPrice());
							transactionOrder2.setStartPrice(_transactionOrder2.getEndPrice());
							transactionOrder2.setEndPrice(_transactionOrder2.getEndPrice());

							orderList.add(0, transactionOrder2);
						}
					}
				} else {// 历史节点为空
					// 如果数据库中没有当前结点,创建结点
					TransactionOrder transactionOrder0 = new TransactionOrder();
					transactionOrder0.setId(DateUtil.dateToString(nowDate, "yyyyMMddHHmm"));
					transactionOrder0.setTransactionTime(DateUtil.dateToString(minDate, "yyyy-MM-dd HH:mm"));
					transactionOrder0.setTransactionEndTime(DateUtil.dateToString(nowDate, "yyyy-MM-dd HH:mm"));

					BigDecimal bigDecimal = new BigDecimal(0);
					transactionOrder0.setTransactionCount(bigDecimal);
					transactionOrder0.setMinPrice(bigDecimal);
					transactionOrder0.setMaxPrice(bigDecimal);
					transactionOrder0.setStartPrice(bigDecimal);
					transactionOrder0.setEndPrice(bigDecimal);
					orderList.add(0, transactionOrder0);
				}
			}
		}

		// 最后保存数据
		redisService.save(table, JSON.toJSONString(orderList));
	}


	/**
	 * 定时入口
	 * <p>
	 * TODO
	 * </p>
	 * 
	 * @author: Liu Shilei
	 * @param: @param
	 *             time
	 * @return: void
	 * @Date : 2016年7月28日 下午2:49:44
	 * @throws:
	 */
	public static void generateKlineData(Integer time) {
		logger.info("进入定时器-----" + time);
		String productListStr = ExchangeDataCache.getStringData("cn:productFixList");
		if (!StringUtils.isEmpty(productListStr)) {
			List<String> productList = JSON.parseArray(productListStr, String.class);
			for (String coinCode : productList) {

			    //返回节点
                TransactionOrder transactionOrder = compute2(time, "cn", "cny", coinCode);
                if(transactionOrder!=null) {
                    //节点保存到数据库
                    String period = period2String(time);
                    List<TransactionOrder> list = new ArrayList<TransactionOrder>();
                    list.add(transactionOrder);
                    executors.execute(new InsertRunnable(coinCode, period, list));
                }

			}
		}

	}

	private static String period2String(Integer time){
		String period = "";
		switch (time){
			case 1 : period="1min"; break;
			case 5 : period="5min"; break;
			case 15 : period="15min"; break;
			case 30 : period="30min"; break;
			case 60 : period="60min"; break;
			case 1440 : period="1day"; break;
			case 10080 : period="1week"; break;
			case 30000 : period="1month"; break;
			default:period="";
		}
		return  period;
	}







	/**
	 * 获取app_config配置
	 * <p>
	 * TODO
	 * </p>
	 * 
	 * @author: Zhang Lei
	 * @param: @param
	 *             type
	 * @param: @return
	 * @return: String
	 * @Date : 2016年12月8日 上午10:50:01
	 * @throws:
	 */
	public static String getCnfigValue(String type) {
		RemoteAppConfigService remoteAppConfigService = (RemoteAppConfigService) ContextUtil.getBean("remoteAppConfigService");
		String value = remoteAppConfigService.getValueByKey(type);
		return value;
	}

	/**
	 * 判断是否是周末
	 * 
	 * @return
	 */
	public static boolean isWeekend(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 
	 * <p>
	 * 是否是开闭盘时间
	 * </p>
	 * 
	 * @author: Liu Shilei
	 * @param: @return
	 * @return: boolean
	 * @Date : 2016年9月21日 下午6:15:31
	 * @throws:
	 */
	public static boolean isOpenTrade(int time, Date date) {
		// 判断周末是否开盘
		String isWeekend = getCnfigValue("isWeekend");// 0 开启 1关闭
		if ("1".equals(isWeekend)) {// 关闭
			// 继续判断今天是否是周六或周日
			boolean flag = isWeekend(new Date());
			if (flag) {
				return false;
			}
		}

		// 计算是否是节假日
		RedisService redisService = (RedisService) ContextUtil.getBean("redisService");
		String str = redisService.get("appholidayConfig");
		if (!StringUtils.isEmpty(str)) {
			// 判断否是节假日
			List<AppHolidayConfig> list = JSON.parseArray(str, AppHolidayConfig.class);
			if (list != null && list.size() > 0) {
				for (AppHolidayConfig ahc : list) {
					if (time == 1440) {// 日线
						// 5分钟=300秒(s)=300000毫秒(ms)
						Long t = date.getTime() - 300000l;
						// 因为是每天凌晨生成K线，所以判断日线生成的时候需注意：例如：生成24日数据，生成k线时已经为25日，所以需要往后退一段时间（5分钟）
						if (t > ahc.getBeginDate().getTime() && t < ahc.getEndDate().getTime()) {
							return false;
						}
					} else {
						if (date.getTime() > ahc.getBeginDate().getTime() && date.getTime() < ahc.getEndDate().getTime()) {
							return false;
						}
					}
				}
			}
		}

		// 这样的计算时分，其他只计算节假日
		if (time == 1 || time == 5 || time == 15 || time == 30 || time == 60) {
			// 计算是否是开闭盘
			RemoteAppConfigService remoteAppConfigService = (RemoteAppConfigService) ContextUtil.getBean("remoteAppConfigService");
			String financeByKey = remoteAppConfigService.getFinanceByKey("openAndclosePlateTime");
			if (!org.springframework.util.StringUtils.isEmpty(financeByKey)) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);
				int hours = calendar.get(Calendar.HOUR_OF_DAY);
				int minutes = calendar.get(Calendar.MINUTE);
				String[] split = financeByKey.split(",");
				boolean flag = true;
				for (int i = 0; i < split.length; i++) {
					if (i % 2 == 0) {
						int h = new Integer(split[i].split(":")[0]).intValue();
						int m = new Integer(split[i].split(":")[1]).intValue();
						if (hours == h) {
							if (minutes < m) {
								flag = false;
							}
						}
						if (hours < h) {
							flag = false;
						}
					}
					if (i % 2 == 1) {
						int h = new Integer(split[i].split(":")[0]).intValue();
						int m = new Integer(split[i].split(":")[1]).intValue();

						if (hours == h) {
							if (minutes > m) {
								flag = false;
							}
						}
						if (hours > h) {
							flag = false;
						}
					}

					if (!flag) {
						return flag;
					}
				}

				return flag;
			} else {// 如果缓存为空 直接返回true 让K线正常执行
				return true;
			}
		} else {
			return true;
		}
	}

	/**
	 * klineJob 定时器
	 * <p>
	 * TODO
	 * </p>
	 * 
	 * @author: Liu Shilei
	 * @param:
	 * @return: void
	 * @Date : 2016年7月28日 下午2:40:26
	 * @throws:
	 */
	public static void klineJob() {
		/*ScheduleJob job1 = new ScheduleJob();
		job1.setBeanClass("hry.trade.kline.KlineEngine");
		job1.setMethodName("generateKlineData");
		Object[] object1 = { 1 };
		job1.setMethodArgs(object1);
		QuartzManager.addJob("klineData1", job1, QuartzJob.class, "0 0/1 * * * ?");

		ScheduleJob job5 = new ScheduleJob();
		job5.setBeanClass("hry.trade.kline.KlineEngine");
		job5.setMethodName("generateKlineData");
		Object[] object5 = { 5 };
		job5.setMethodArgs(object5);
		QuartzManager.addJob("klineData5", job5, QuartzJob.class, "0 0/5 * * * ?");

		ScheduleJob job15 = new ScheduleJob();
		job15.setBeanClass("hry.trade.kline.KlineEngine");
		job15.setMethodName("generateKlineData");
		Object[] object15 = { 15 };
		job15.setMethodArgs(object15);
		QuartzManager.addJob("klineData15", job15, QuartzJob.class, "0 0/15 * * * ?");

		ScheduleJob job30 = new ScheduleJob();
		job30.setBeanClass("hry.trade.kline.KlineEngine");
		job30.setMethodName("generateKlineData");
		Object[] object30 = { 30 };
		job30.setMethodArgs(object30);
		QuartzManager.addJob("klineData30", job30, QuartzJob.class, "0 0/30 * * * ?");

		ScheduleJob job60 = new ScheduleJob();
		job60.setBeanClass("hry.trade.kline.KlineEngine");
		job60.setMethodName("generateKlineData");
		Object[] object60 = { 60 };
		job60.setMethodArgs(object60);
		QuartzManager.addJob("klineData60", job60, QuartzJob.class, "0 0 0/1 * * ?");

		// 每天0点触发
		ScheduleJob job1440 = new ScheduleJob();
		job1440.setBeanClass("hry.trade.kline.KlineEngine");
		job1440.setMethodName("generateKlineData");
		Object[] object1440 = { 1440 };
		job1440.setMethodArgs(object1440);
		QuartzManager.addJob("klineData1440", job1440, QuartzJob.class, "0 0 0 * * ?");

		// 每周一触发
		ScheduleJob job10080 = new ScheduleJob();
		job10080.setBeanClass("hry.trade.kline.KlineEngine");
		job10080.setMethodName("generateKlineData");
		Object[] object10080 = { 10080 };
		job10080.setMethodArgs(object10080);
		QuartzManager.addJob("klineData10080", job10080, QuartzJob.class, "0 0 0 ? * MON");

		// 每月1号0点触发
		ScheduleJob jobMon = new ScheduleJob();
		jobMon.setBeanClass("hry.trade.kline.KlineEngine");
		jobMon.setMethodName("generateKlineData");
		Object[] object30000 = { 30000 };
		jobMon.setMethodArgs(object30000);
		QuartzManager.addJob("klineData30000", jobMon, QuartzJob.class, "0 0 0 1 * ?");*/

	}


}
