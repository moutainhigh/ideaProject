/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Wu Shuiming
 * @version:      V1.0 
 * @Date:        2016年3月24日 下午2:04:29
 */
package hry.trade.entrust;

import com.alibaba.fastjson.JSON;
import hry.change.remote.exEntrust.RemoteExOrderInfoServiceNoTr;
import hry.core.constant.CodeConstant;
import hry.exchange.kline2.model.LastKLinePayload;
import hry.redis.common.utils.RedisService;
import hry.trade.MQmanager.MQEnter;
import hry.trade.entrust.dao.ExEntrustDao;
import hry.trade.entrust.model.ExEntrust;
import hry.trade.entrust.model.ExOrder;
import hry.trade.entrust.model.ExOrderInfo;
import hry.trade.entrust.service.ExEntrustService;
import hry.trade.entrust.service.ExOrderInfoService;
import hry.trade.exEntrustOneDay.model.ExentrustOneday;
import hry.trade.exEntrustOneDay.service.ExentrustOnedayService;
import hry.util.date.DateUtil;
import hry.util.serialize.Mapper;
import hry.util.sys.ContextUtil;
import org.apache.log4j.Logger;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * TODO
 * </p>
 * 
 * @author: Gao Mimi
 * @Date : 2016年4月12日 下午4:45:50
 */
public class RemoteExOrderInfoServiceNoTrImpl implements RemoteExOrderInfoServiceNoTr {
	private static Logger logger = Logger.getLogger(RemoteExOrderInfoServiceNoTrImpl.class);
	@Resource
	private ExentrustOnedayService exentrustOnedayService;
	@Resource
	private ExEntrustService exEntrustService;
	@Resource
	private ExOrderInfoService exOrderInfoService;
	@Resource
	private RedisService redisService;
	@Resource
	private ExEntrustDao exEntrustDao;
	/**
	 * 
	 * <p>
	 * </p>
	 * 
	 * @author: Gao Mimi
	 * @param: @param exEntrust
	 * @return: void
	 * @Date : 2016年4月15日 下午2:56:57
	 * @throws:
	 * 
	 *          买家:限价（1000元，1个）市价（1500元） 卖家:限价（1000元，1个）市价（2个）
	 *
	 */
	@Override
	public void matchExtrustToOrder(String exentrust) {
		ExEntrust entrust = (ExEntrust) Mapper.JSONToObj(exentrust, ExEntrust.class);
			// 撤销
		if (entrust.getAccountId() == null) {
			exEntrustService.mqcancelExEntrust(entrust);
			// 委托
		} else if (null==entrust.getId()) {
		     mqsaveExEntrust(entrust);
			// 匹配
		} else {
			matchExtrustToOrder(entrust);
		}
	}

	public void matchExtrustToOrder(ExEntrust exEntrust, String saasId) {
		// type类型 1 ： 买 2 ： 卖
		if (exEntrust.getType().equals(1)) {
	//		logger.error("匹配单号为：" + exEntrust.getEntrustNum() + "  的买单");
		//	long startTime = System.currentTimeMillis(); // 获取开始时间
			buyExchange(exEntrust, exEntrust.getSaasId());
		//	long endTime = System.currentTimeMillis(); // 获取结束时间
		//	logger.error("该买单匹配时间： " + (endTime - startTime) + "ms");
		} else if (exEntrust.getType().equals(2)) {
		//	logger.error("匹配单号为：" + exEntrust.getEntrustNum() + "	     的卖单");
		//	long startTime = System.currentTimeMillis(); // 获取开始时间
			sellExchange(exEntrust, exEntrust.getSaasId());
		//	long endTime = System.currentTimeMillis(); // 获取结束时间
		//	logger.error("该卖单匹配时间： " + (endTime - startTime) + "ms");
		}
	}

	/**
	 * 
	 * 委托单成交后调用的方法
	 * 
	 * @version: V1.0
	 * @date: 2016年8月10日 上午9:33:40
	 */
	public void endTransaction(ExOrderInfo exOrderInfo, ExOrder exOrder, ExEntrust buyexEntrust, ExEntrust sellentrust) {
		ExOrderInfoService exOrderInfoService = (ExOrderInfoService) ContextUtil.getBean("exOrderInfoService");
		long start2 = System.currentTimeMillis();
		String[] relt = exOrderInfoService.endTransaction(exOrderInfo, exOrder, buyexEntrust, sellentrust);
		long end2 = System.currentTimeMillis();
		logger.error("endTransaction：" + (end2 - start2) + "毫秒");
		if (relt[0].equals(CodeConstant.CODE_SUCCESS)) {
			try {
				long start1 = System.currentTimeMillis();
				// 设置缓存
				setExchangeDataCache(exOrderInfo, exOrder);
				long end1 = System.currentTimeMillis();
				logger.error("成交之后设置缓存：" + (end1 - start1) + "毫秒");
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("匹配成功，设置缓存失败！！！");
			}
		}
	}

	public void setExchangeDataCache(ExOrderInfo exOrderInfo, ExOrder exOrder) {
		RedisService redisService = (RedisService) ContextUtil.getBean("redisService");
		long start1 = System.currentTimeMillis();
		  String header=exEntrustService.getHeader(exOrderInfo);
		// 设置当前最新成交价
		 ExchangeDataCache.setStringData(header + ":" + ExchangeDataCache.CurrentExchangPrice, exOrderInfo
				.getTransactionPrice().setScale(5, BigDecimal.ROUND_HALF_UP).toString());
		// 已经成交
		ExchangeDataCache.setStringData(header + ":" + ExchangeDataCache.IsMatch, "1");
		// 保存这条成交记录到re
//		List<ExOrder> list = redisService.getList1(header + ":" + ExchangeDataCache.LastOrderRecords);
//		if (list.size() > 100) {
//			list.remove(0);
//		}
//		list.add(exOrder);
//		redisService.setList(header + ":" + ExchangeDataCache.LastOrderRecords, list);

		long end1 = System.currentTimeMillis();
		logger.error("成交之后设置缓存保存分期数据11：" + (end1 - start1) + "毫秒");
	}

	/**
	 * 保存分期最后一个节点的数据
	 * 
	 * @param exOrderInfo
	 */
	public void savePeriodLastKLineList(ExOrderInfo exOrderInfo) {
		String header=exEntrustService.getHeader(exOrderInfo);
		String[] periods = { "1min", "5min", "15min", "30min", "60min", "1day", "1week", "1mon" };
		// 查询reids是否存在这个缓存对象
		String redisList = ExchangeDataCache.getStringData(header + ":" + ExchangeDataCache.PeriodLastKLineList);
		List<LastKLinePayload> periodList = JSON.parseArray(redisList, LastKLinePayload.class);

		// 获得当前成交订单的交易时间区间值
		Map<String, Date> periodDate = DateUtil.getPeriodDate2(exOrderInfo.getTransactionTime());

		// 如果不存在则第一次生成
		if (periodList == null) {
			periodList = new ArrayList<LastKLinePayload>();
			for (String p : periods) {// 遍历数组
				LastKLinePayload lastKLinePayload = new LastKLinePayload();
				lastKLinePayload.setPeriod(p);
				lastKLinePayload.setPriceOpen(exOrderInfo.getTransactionPrice());
				lastKLinePayload.setPriceHigh(exOrderInfo.getTransactionPrice());
				lastKLinePayload.setPriceLow(exOrderInfo.getTransactionPrice());
				lastKLinePayload.setPriceLast(exOrderInfo.getTransactionPrice());
				lastKLinePayload.setAmount(exOrderInfo.getTransactionCount());
				if ("1min".equals(p)) {
					lastKLinePayload.set_id(periodDate.get("1min").getTime() / 1000);
					lastKLinePayload.setTime(periodDate.get("1min").getTime() / 1000);
				}
				if ("5min".equals(p)) {
					lastKLinePayload.set_id(periodDate.get("5min").getTime() / 1000);
					lastKLinePayload.setTime(periodDate.get("5min").getTime() / 1000);
				}
				if ("15min".equals(p)) {
					lastKLinePayload.set_id(periodDate.get("15min").getTime() / 1000);
					lastKLinePayload.setTime(periodDate.get("15min").getTime() / 1000);
				}
				if ("30min".equals(p)) {
					lastKLinePayload.set_id(periodDate.get("30min").getTime() / 1000);
					lastKLinePayload.setTime(periodDate.get("30min").getTime() / 1000);
				}
				if ("60min".equals(p)) {
					lastKLinePayload.set_id(periodDate.get("60min").getTime() / 1000);
					lastKLinePayload.setTime(periodDate.get("60min").getTime() / 1000);
				}
				if ("1day".equals(p)) {
					lastKLinePayload.set_id(periodDate.get("1day").getTime() / 1000);
					lastKLinePayload.setTime(periodDate.get("1day").getTime() / 1000);
				}
				if ("1week".equals(p)) {
					lastKLinePayload.set_id(periodDate.get("1week").getTime() / 1000);
					lastKLinePayload.setTime(periodDate.get("1week").getTime() / 1000);
				}
				if ("1mon".equals(p)) {
					lastKLinePayload.set_id(periodDate.get("1mon").getTime() / 1000);
					lastKLinePayload.setTime(periodDate.get("1mon").getTime() / 1000);
				}
				periodList.add(lastKLinePayload);
			}
			ExchangeDataCache.setStringData(header + ":" + ExchangeDataCache.PeriodLastKLineList,
					JSON.toJSONString(periodList));
		} else {// 如果存在则更新
			for (LastKLinePayload lastKLinePayload : periodList) {
				String period = lastKLinePayload.getPeriod();
				if ("1min".equals(period)) {
					flushLastKlinePayLoad(periodDate, lastKLinePayload, exOrderInfo, period);
				}
				if ("5min".equals(period)) {
					flushLastKlinePayLoad(periodDate, lastKLinePayload, exOrderInfo, period);
				}
				if ("15min".equals(period)) {
					flushLastKlinePayLoad(periodDate, lastKLinePayload, exOrderInfo, period);
				}
				if ("30min".equals(period)) {
					flushLastKlinePayLoad(periodDate, lastKLinePayload, exOrderInfo, period);
				}
				if ("60min".equals(period)) {
					flushLastKlinePayLoad(periodDate, lastKLinePayload, exOrderInfo, period);
				}
				if ("1day".equals(period)) {
					flushLastKlinePayLoad(periodDate, lastKLinePayload, exOrderInfo, period);
				}
				if ("1week".equals(period)) {
					flushLastKlinePayLoad(periodDate, lastKLinePayload, exOrderInfo, period);
				}
				if ("1mon".equals(period)) {
					flushLastKlinePayLoad(periodDate, lastKLinePayload, exOrderInfo, period);
				}
			}
			// 更新缓存
			ExchangeDataCache.setStringData(header + ":" + ExchangeDataCache.PeriodLastKLineList,
					JSON.toJSONString(periodList));
		}
	}

	/**
	 * 刷新lastKline
	 * 
	 * @param periodDate
	 * @param lastKLinePayload
	 * @param exOrderInfo
	 */
	public void flushLastKlinePayLoad(Map<String, Date> periodDate, LastKLinePayload lastKLinePayload,
			ExOrderInfo exOrderInfo, String period) {

		// 获得当前这笔交易订单的所有时间区间与其比较
		Date date = periodDate.get(period);
		logger.error(period + "|" + DateUtil.dateToString(date) + "|"
				+ DateUtil.dateToString(new Date(lastKLinePayload.getTime() * 1000)));
		logger.error(period + "|" + lastKLinePayload.getTime() + "|" + date.getTime() / 1000);
		if (lastKLinePayload.getTime().compareTo(date.getTime() / 1000) == 0) {// 如果发现在同一个区间的话则只进行比较,比较最高价，最低价，设置收盘价为当前这笔订单的价格
			if (exOrderInfo.getTransactionPrice().compareTo(lastKLinePayload.getPriceHigh()) > 0) {// 比较成交价格是否大于最高价
				lastKLinePayload.setPriceHigh(exOrderInfo.getTransactionPrice());
			}
			if (exOrderInfo.getTransactionPrice().compareTo(lastKLinePayload.getPriceLow()) < 0) {// 比较成交价格是否小于最高价
				lastKLinePayload.setPriceLow(exOrderInfo.getTransactionPrice());
			}
			lastKLinePayload.setPriceLast(exOrderInfo.getTransactionPrice());// 刷新最新成交价格
			lastKLinePayload.setAmount(lastKLinePayload.getAmount().add(exOrderInfo.getTransactionCount()));// 累加成交量

			// 金科新加，成交额
			if ("1day".equals(period)) {
				lastKLinePayload.setDayTotalDealAmount(lastKLinePayload.getDayTotalDealAmount().add(
						exOrderInfo.getTransactionSum()));
			}
		} else {// 如果发现不在同一个敬意的话则重新刷新这个缓存区间的时间值,同时重新所有数据
			lastKLinePayload.setPriceOpen(exOrderInfo.getTransactionPrice());
			lastKLinePayload.setPriceHigh(exOrderInfo.getTransactionPrice());
			lastKLinePayload.setPriceLow(exOrderInfo.getTransactionPrice());
			lastKLinePayload.setPriceLast(exOrderInfo.getTransactionPrice());
			lastKLinePayload.setAmount(exOrderInfo.getTransactionCount());
			lastKLinePayload.set_id(date.getTime() / 1000);
			lastKLinePayload.setTime(date.getTime() / 1000);

			// 金科新加，成交额
			if ("1day".equals(period)) {
				lastKLinePayload.setDayTotalDealAmount(exOrderInfo.getTransactionSum());
			}
		}

	}

	/*
	 * public void mongoRemoveEntrust(ExEntrust buyexEntrust,ExEntrust
	 * sellentrust){ if(buyexEntrust.getStatus().equals(1)){
	 * MongoUtil<ExEntrust, Long> mongoUtil = new MongoUtil<ExEntrust, Long>(
	 * ExEntrust.class,buyexEntrust.getWebsite()+buyexEntrust.getCurrencyType()
	 * +buyexEntrust.getCoinCode()+"_buy_entrust");
	 * mongoUtil.save(buyexEntrust);
	 * 
	 * 
	 * } if(buyexEntrust.getStatus().equals(2)){ MongoUtil<ExEntrust, Long>
	 * mongoUtil = new MongoUtil<ExEntrust, Long>(
	 * ExEntrust.class,buyexEntrust.getWebsite()+buyexEntrust.getCurrencyType()
	 * +buyexEntrust.getCoinCode()+"_buy_entrust"); MongoQueryFilter
	 * mongoQueryFilter = new MongoQueryFilter();
	 * mongoQueryFilter.addFilter("entrustNum=", buyexEntrust.getEntrustNum());
	 * mongoUtil.delete(mongoQueryFilter);
	 * 
	 * } if(sellentrust.getStatus().equals(1)){ MongoUtil<ExEntrust, Long>
	 * mongoUtil = new MongoUtil<ExEntrust, Long>(
	 * ExEntrust.class,sellentrust.getWebsite()+sellentrust.getCurrencyType()
	 * +sellentrust.getCoinCode()+"_sell_entrust"); mongoUtil.save(sellentrust);
	 * } if(sellentrust.getStatus().equals(2)){ MongoUtil<ExEntrust, Long>
	 * mongoUtil = new MongoUtil<ExEntrust, Long>(
	 * ExEntrust.class,sellentrust.getWebsite()+sellentrust.getCurrencyType()
	 * +sellentrust.getCoinCode()+"_sell_entrust"); MongoQueryFilter
	 * mongoQueryFilter = new MongoQueryFilter();
	 * mongoQueryFilter.setSaasId(buyexEntrust.getSaasId());
	 * mongoQueryFilter.addFilter("entrustNum=", sellentrust.getEntrustNum());
	 * mongoUtil.delete(mongoQueryFilter);
	 * 
	 * }
	 * 
	 * }
	 */// (1)买家限价，卖家限价
	public void oneCase(ExEntrust buyexEntrust, ExEntrust sellentrust, String initiative) {
		long start = System.currentTimeMillis();
		// 谁小取谁，获取本次交易币的个数
		BigDecimal tradeCount = buyexEntrust.getSurplusEntrustCount().compareTo(sellentrust.getSurplusEntrustCount()) <= 0 ? buyexEntrust
				.getSurplusEntrustCount() : sellentrust.getSurplusEntrustCount();
		// 本次交易数量不能为0
		if (tradeCount.compareTo(BigDecimal.ZERO) == 0) {
			return;
		}
		// 交易单价为买单的交易价
		BigDecimal tradePrice = buyexEntrust.getEntrustPrice();
		// 本次交易单价不能为0
		if (tradePrice.compareTo(BigDecimal.ZERO) == 0) {
			return;
		}
		// 不相等的情况说明是有浮动的，得求最优成交价（卖家限价，并且价格不相等）
		if (sellentrust.getEntrustWay().equals(1)
				&& buyexEntrust.getEntrustPrice().compareTo(sellentrust.getEntrustPrice()) != 0) {
			BigDecimal[] array = new BigDecimal[4];
			// 买家上限浮动的价格
			array[0] = buyexEntrust.getEntrustPrice().add(buyexEntrust.getFloatUpPrice());
			// 买家下限浮动的价格
			array[1] = buyexEntrust.getEntrustPrice().subtract(buyexEntrust.getFloatDownPrice());
			// 卖家上限浮动的价格
			array[2] = sellentrust.getEntrustPrice().add(sellentrust.getFloatUpPrice());
			// 卖家下限浮动的价格
			array[3] = sellentrust.getEntrustPrice().subtract(sellentrust.getFloatDownPrice());
			java.util.Arrays.sort(array);
			if (initiative.equals("buy")) {// 买主动
				tradePrice = array[1];// 买家当然是价格越低越好
			} else if (initiative.equals("sell")) {// 卖主动
				tradePrice = array[2];// 卖家当然然是价格越低高越好
			}

		}
		long end = System.currentTimeMillis();
		logger.error("matching2，共耗时：" + (end - start));
		
		long start1 = System.currentTimeMillis();
		ExOrderInfo exOrderInfo = exOrderInfoService.createExOrderInfo(1, buyexEntrust, sellentrust, tradeCount, tradePrice);
		exOrderInfo.setInOrOutTransaction(initiative.equals("buy") ? "sell" : "buy");
		ExOrder exOrder = exOrderInfoService.createExOrder(exOrderInfo);
		updatebuyExEntrust(buyexEntrust, sellentrust, exOrderInfo);
		updatesellExEntrust(buyexEntrust, sellentrust, exOrderInfo);
		long end1 = System.currentTimeMillis();
		logger.error("matching3，共耗时：" + (end1 - start1));
		// 委托之后的资金处理
		endTransaction(exOrderInfo, exOrder, buyexEntrust, sellentrust);
	}

	// (2)买家限价，卖家市价
	public void twoCase(ExEntrust buyexEntrust, ExEntrust sellentrust, String initiative) {
		oneCase(buyexEntrust, sellentrust, initiative);
	}

	// (3)买家市价，卖家限价
	public void threeCase(ExEntrust buyexEntrust, ExEntrust sellentrust) {

		// 买家剩余委托金额
		BigDecimal buysurplusEntrusMoney = buyexEntrust.getEntrustSum().subtract(buyexEntrust.getTransactionSum());
		// 卖家剩余委托总金额
		BigDecimal sellsurplusEntrusMoney = sellentrust.getSurplusEntrustCount()
				.multiply(sellentrust.getEntrustPrice());

		BigDecimal tradeCount = new BigDecimal("0");
		if (buysurplusEntrusMoney.compareTo(sellsurplusEntrusMoney) <= 0) {
			tradeCount = buysurplusEntrusMoney.divide(sellentrust.getEntrustPrice(), 4, BigDecimal.ROUND_DOWN);
			buyexEntrust.setStatus(2);
		}
		if (buysurplusEntrusMoney.compareTo(sellsurplusEntrusMoney) == 1) {
			tradeCount = sellentrust.getSurplusEntrustCount();
		}
		if (tradeCount.compareTo(new BigDecimal(0)) == 0) {
			return;
		}
		BigDecimal tradePrice = sellentrust.getEntrustPrice();

		if (tradePrice.compareTo(new BigDecimal(0)) == 0) {
			return;
		}
		ExOrderInfo exOrderInfo =  exOrderInfoService.createExOrderInfo(1, buyexEntrust, sellentrust, tradeCount, tradePrice);
		exOrderInfo.setInOrOutTransaction("sell");
		ExOrder exOrder = exOrderInfoService.createExOrder(exOrderInfo);
		updatebuyExEntrust(buyexEntrust, sellentrust, exOrderInfo);
		updatesellExEntrust(buyexEntrust, sellentrust, exOrderInfo);
		endTransaction(exOrderInfo, exOrder, buyexEntrust, sellentrust);
	}

	// (4)买家市价，卖家市价
	public void fourCase(ExEntrust buyexEntrust, ExEntrust sellentrust) {

		String tradePricestring = ExchangeDataCache.getStringData(buyexEntrust.getWebsite() + ":"
				+ buyexEntrust.getCurrencyType() + ":" + buyexEntrust.getCoinCode() + ":"
				+ ExchangeDataCache.CurrentExchangPrice);

		if (null != tradePricestring && new BigDecimal(tradePricestring).compareTo(new BigDecimal("0")) != 0) {
			BigDecimal tradePrice = new BigDecimal(tradePricestring);
			if (tradePrice.compareTo(new BigDecimal(0)) == 0) {
				return;
			}
			// 买家剩余委托金额
			BigDecimal buysurplusEntrusMoney = buyexEntrust.getEntrustSum().subtract(buyexEntrust.getTransactionSum());
			// 卖家剩余委托总金额
			BigDecimal sellsurplusEntrusMoney = sellentrust.getSurplusEntrustCount().multiply(tradePrice);
			BigDecimal tradeCount = new BigDecimal("0");
			if (buysurplusEntrusMoney.compareTo(sellsurplusEntrusMoney) <= 0) {
				tradeCount = buysurplusEntrusMoney.divide(tradePrice, 4, BigDecimal.ROUND_DOWN);
				buyexEntrust.setStatus(2);
			}
			if (buysurplusEntrusMoney.compareTo(sellsurplusEntrusMoney) == 1) {
				tradeCount = sellentrust.getSurplusEntrustCount();

			}
			if (tradeCount.compareTo(new BigDecimal(0)) == 0) {
				return;
			}
			ExOrderInfo exOrderInfo =  exOrderInfoService.createExOrderInfo(1, buyexEntrust, sellentrust, tradeCount, tradePrice);
			exOrderInfo.setInOrOutTransaction("sell"); // ????
			ExOrder exOrder = exOrderInfoService.createExOrder(exOrderInfo);
			updatebuyExEntrust(buyexEntrust, sellentrust, exOrderInfo);
			updatesellExEntrust(buyexEntrust, sellentrust, exOrderInfo);
			endTransaction(exOrderInfo, exOrder, buyexEntrust, sellentrust);
		}
	}

	/**
	 * 
	 * <p>
	 * (1)买家限价，卖家限价 (2)买家限价，卖家市价 (3)买家市价，卖家限价 (4)买家市价，卖家市价 暂不考虑
	 * </p>
	 * 
	 * @author: Gao Mimi
	 * @param: @param exEntrust
	 * @param: @param saasId
	 * @return: void
	 * @Date : 2016年4月19日 下午5:21:18
	 * @throws:
	 */
	public void buyExchange(ExEntrust buyexEntrust, String saasId) {
		if (buyexEntrust.getEntrustWay().equals(1)) {// 买家限价（必须相等才匹配）
		//	logger.error("进入买单匹配方法-买家限价");
			// 获取能够匹配的委托单
			long start = System.currentTimeMillis();
			List<ExentrustOneday> list = exEntrustService.listMatchByBuyLimitedPrice(saasId, buyexEntrust,
					getMatchCustomerType(buyexEntrust));
			long end = System.currentTimeMillis();
			logger.error("查询买匹配列表耗时：" + (end - start) + "毫秒");
			if (null != list && list.size() > 0) {
				for (ExentrustOneday sellentrust : list) {
					long start1 = System.currentTimeMillis();
					// 每次被匹配一次主动发起的要去数据库再查下，以防止数据库有变
					ExEntrust sellentrust1 = exEntrustDao.getById(sellentrust.getId());
					// 每次匹配的主动发起的也要去数据库再查下，以防止数据库有变
				//	buyexEntrust = exEntrustDao.getById(buyexEntrust.getId());
					long end1 = System.currentTimeMillis();
					logger.error("查询数据库以防有变：" + (end1 - start1) + "毫秒");
					if (null == sellentrust1)
						continue;
					if (sellentrust1.getId().compareTo(buyexEntrust.getId()) == 1) {
						continue;
					}
					matching(buyexEntrust, sellentrust1, "buy");
					// 如果匹配完了走出循环
					if (buyexEntrust.getStatus().equals(2)) {
						return;
					}
				}
			}
		} else if (buyexEntrust.getEntrustWay().equals(2)) { // 买家市价
																// //只要未完成的卖家都可以
		//	logger.error("进入买单匹配方法-买家市价");
			List<ExentrustOneday> list = exEntrustService.listMatchByBuyMarketPrice(saasId, buyexEntrust,
					getMatchCustomerType(buyexEntrust));
			if (null != list && list.size() > 0) {
				for (ExentrustOneday sellentrust : list) {
					ExEntrust sellentrust1 = exEntrustDao.getById(sellentrust.getId());// 每次被匹配一次主动发起的要去数据库再查下，以防止数据库有变
				//	buyexEntrust = exEntrustDao.getById(buyexEntrust.getId()); // 每次匹配的主动发起的也要去数据库再查下，以防止数据库有变
					if (null == sellentrust1)
						break;
					if (sellentrust1.getId().compareTo(buyexEntrust.getId()) == 1) {
						continue;
					}
					matching(buyexEntrust, sellentrust1, "buy");
					// 如果匹配完了走出循环
					if (buyexEntrust.getStatus().equals(2)) {
						return;
					}
				}
			}

		}
	}

	/**
	 * 
	 * <p>
	 * (1)卖家限价，买家限价 (3)卖家限价，买家市价 (2)卖家市价，买家限价 (4)卖家市价，买家市价 暂不考虑
	 * </p>
	 * 
	 * @author: Gao Mimi
	 * @param: @param exEntrust
	 * @param: @param saasId
	 * @return: void
	 * @Date : 2016年4月19日 下午5:21:18
	 * @throws:
	 */
	public void sellExchange(ExEntrust sellentrust, String saasId) {
		if (sellentrust.getEntrustWay().equals(1)) {// 卖家限价 //必须相等才匹配
			long start = System.currentTimeMillis();
			List<ExentrustOneday> list = exEntrustService.listMatchBySellLimitedPrice(saasId, sellentrust,
					getMatchCustomerType(sellentrust));
			long end = System.currentTimeMillis();
			
			logger.error("查询卖匹配列表耗时：" + (end - start) + "毫秒");
			if (null != list && list.size() > 0) {
				for (ExentrustOneday buyexEntrust : list) {
					
					ExEntrust buyexEntrust1 =  exEntrustDao.getById(buyexEntrust.getId());// 每次被匹配一次主动发起的要去数据库再查下，以防止数据库有变
			//		sellentrust =  exEntrustDao.getById(sellentrust.getId()); // 每次匹配的主动发起的也要去数据库再查下，以防止数据库有变
					if (null == buyexEntrust1){
						continue;
					}
						
					////不能匹配此卖单后面挂出的买单、使用created判断
				/*	if (buyexEntrust1.getCreated().getTime()>sellentrust.getCreated().getTime()) {
						continue;
					}*/
					if (buyexEntrust1.getId().compareTo(sellentrust.getId()) == 1) {
						continue;
					}
					matching(buyexEntrust1, sellentrust, "sell");
					// 如果匹配完了走出循环
					if (sellentrust.getStatus().equals(2)) {
						return;
					}
				}
			}
		} else if (sellentrust.getEntrustWay().equals(2)) { // 卖家市价
															// //只要未完成的卖家都可以
			List<ExentrustOneday> list = exEntrustService.listMatchBySellMarketPrice(saasId, sellentrust,
					getMatchCustomerType(sellentrust));
			if (null != list && list.size() > 0) {
				for (ExentrustOneday buyexEntrust : list) {
					ExEntrust buyexEntrust1 =  exEntrustDao.getById(buyexEntrust.getId());
			//		sellentrust =  exEntrustDao.getById(sellentrust.getId()); // 每次匹配的主动发起的也要去数据库再查下，以防止数据库有变
					if (null == buyexEntrust1)
						break;
					if (buyexEntrust1.getId().compareTo(sellentrust.getId()) == 1) {
						continue;
					}
					matching(buyexEntrust1, sellentrust, "sell");
					// 如果匹配完了走出循环
					if (sellentrust.getStatus().equals(2)) {
						// break;
						return;
					}
				}
			}
		}
	}

	public void updatesellExEntrust(ExEntrust buyExEntrust, ExEntrust sellentrust, ExOrderInfo exOrderInfo) {

		sellentrust.setSurplusEntrustCount(sellentrust.getSurplusEntrustCount().subtract(
				exOrderInfo.getTransactionCount()));
		sellentrust.setTransactionFee(sellentrust.getTransactionFee().add(exOrderInfo.getTransactionSellFee()));
		sellentrust.setTransactionSum(sellentrust.getTransactionSum().add(exOrderInfo.getTransactionSum()));
		// 平均价格
		sellentrust.setProcessedPrice(sellentrust.getTransactionSum().divide(
				sellentrust.getEntrustCount().subtract(sellentrust.getSurplusEntrustCount()), 5,
				BigDecimal.ROUND_HALF_UP));
		// 剩余个数为0，说明已完成（卖家不管是限价还是市价就会有余额这个值）
		sellentrust.setStatus(1);
		if (sellentrust.getSurplusEntrustCount().compareTo(new BigDecimal(0)) <= 0) {
			sellentrust.setStatus(2);
		}
	}

	public void updatebuyExEntrust(ExEntrust buyExEntrust, ExEntrust sellentrust, ExOrderInfo exOrderInfo) {

		buyExEntrust.setSurplusEntrustCount(buyExEntrust.getSurplusEntrustCount().subtract(
				exOrderInfo.getTransactionCount()));
		buyExEntrust.setTransactionFee(buyExEntrust.getTransactionFee().add(exOrderInfo.getTransactionBuyFee()));
		buyExEntrust.setTransactionSum(buyExEntrust.getTransactionSum().add(exOrderInfo.getTransactionSum()));
		buyExEntrust.setProcessedPrice(buyExEntrust.getTransactionSum().divide(
				buyExEntrust.getEntrustCount().subtract(buyExEntrust.getSurplusEntrustCount()), 5,
				BigDecimal.ROUND_HALF_UP));

		// 如果是市价
		if (buyExEntrust.getEntrustWay().equals(2)) {

			// 市价：委托总金额-交易金额数如果小于购买0.0001个币的时候说明已完成
			/*
			 * BigDecimal surplusEntrusMoney=
			 * buyExEntrust.getEntrustSum().subtract
			 * (buyExEntrust.getTransactionSum()); BigDecimal
			 * surplusEntrusCount=
			 * surplusEntrusMoney.divide(exOrderInfo.getTransactionPrice
			 * (),BigDecimal.ROUND_HALF_UP,4); ExProduct
			 * exProduct=exProductService
			 * .findByCoinCode(buyExEntrust.getCoinCode
			 * (),buyExEntrust.getSaasId());
			 * 
			 * if(surplusEntrusCount.compareTo(exProduct.getSplitMinCoin())==-1){
			 * buyExEntrust.setStatus(2); }else{
			 */
			if (!buyExEntrust.getStatus().equals(2)) {
				buyExEntrust.setStatus(1);
			}

			// }
		} else {// 是限价，还是普通价格优先都有剩余个数
			// 剩余个数为0，说明已完成
			if (buyExEntrust.getSurplusEntrustCount().compareTo(BigDecimal.ZERO) <= 0) {
				buyExEntrust.setStatus(2);
			} else {
				buyExEntrust.setStatus(1);

			}
		}

	}

	/*public ExOrderInfo createExOrderInfo(Integer type, ExEntrust buyExEntrust, ExEntrust sellentrust,
			BigDecimal tradeCount, BigDecimal tradePrice) {
		RemoteAppPersonInfoService remoteAppPersonInfoService = (RemoteAppPersonInfoService) ContextUtil
				.getBean("remoteAppPersonInfoService");
		// 订单开始详细
		ExOrderInfo exOrderInfo = new ExOrderInfo();
		exOrderInfo.setType(type);
		exOrderInfo.setSaasId(buyExEntrust.getSaasId());
		exOrderInfo.setWebsite(buyExEntrust.getWebsite());
		exOrderInfo.setCurrencyType(buyExEntrust.getCurrencyType());
		String transactionNum = IdGenerate.transactionNum(NumConstant.Ex_Order);
		if (!DifCustomer.getIsCommon()) {
			exOrderInfo.setOrderNum("T" + transactionNum.substring(2, transactionNum.length()));
		} else {
			exOrderInfo.setOrderNum(transactionNum);
		}

		exOrderInfo.setBuyEntrustNum(buyExEntrust.getEntrustNum());
		exOrderInfo.setBuyCustomId(buyExEntrust.getCustomerId());
		exOrderInfo.setSellCustomId(sellentrust.getCustomerId());
		exOrderInfo.setSellEntrustNum(sellentrust.getEntrustNum());
		exOrderInfo.setCoinCode(buyExEntrust.getCoinCode());
		exOrderInfo.setTransactionBuyFeeRate(buyExEntrust.getTransactionFeeRate());
		exOrderInfo.setTransactionSellFeeRate(sellentrust.getTransactionFeeRate());
		exOrderInfo.setSellUserCode(sellentrust.getUserCode());
		exOrderInfo.setBuyUserCode(buyExEntrust.getUserCode());
		exOrderInfo.setBuyUserName(buyExEntrust.getUserName());
		exOrderInfo.setSellUserName(sellentrust.getUserName());

		AppPersonInfo appPersonInfo1 = remoteAppPersonInfoService.getByCustomerId(exOrderInfo.getBuyCustomId());
		exOrderInfo.setBuyTrueName(appPersonInfo1.getTrueName());

		AppPersonInfo appPersonInfo = remoteAppPersonInfoService.getByCustomerId(exOrderInfo.getSellCustomId());
		exOrderInfo.setSellTrueName(appPersonInfo.getTrueName());
		// {
		exOrderInfo.setTransactionCount(tradeCount);
		exOrderInfo.setTransactionPrice(tradePrice);
		exOrderInfo.setTransactionSum(tradePrice.multiply(tradeCount));

		// 只有普通类型的客户需要交手续费
		if (buyExEntrust.getCustomerType().equals(1)) {
			exOrderInfo.setTransactionBuyFee(exOrderInfo.getTransactionSum()
					.multiply(exOrderInfo.getTransactionBuyFeeRate()).divide(new BigDecimal("100")));
		} else {
			exOrderInfo.setTransactionBuyFee(BigDecimal.ZERO);
		}
		if (sellentrust.getCustomerType().equals(1)) {
			exOrderInfo.setTransactionSellFee(exOrderInfo.getTransactionSum()
					.multiply(exOrderInfo.getTransactionSellFeeRate()).divide(new BigDecimal("100")));
		} else {
			exOrderInfo.setTransactionSellFee(BigDecimal.ZERO);
		}

		// }

		exOrderInfo.setTransactionTime(new Date());
		exOrderInfo.setOrderTimestapm(exOrderInfo.getTransactionTime().getTime());

		if (type == 1) {
			exOrderInfo.setCustomerId(exOrderInfo.getBuyCustomId());
			exOrderInfo.setUserCode(exOrderInfo.getBuyUserCode());
			exOrderInfo.setUserName(exOrderInfo.getBuyUserName());
			exOrderInfo.setTrueName(exOrderInfo.getBuyTrueName());
			exOrderInfo.setTransactionFee(exOrderInfo.getTransactionBuyFee());
			exOrderInfo.setTransactionFeeRate(exOrderInfo.getTransactionBuyFeeRate());
			exOrderInfo.setEntrustNum(exOrderInfo.getBuyEntrustNum());
		} else {
			exOrderInfo.setCustomerId(exOrderInfo.getSellCustomId());
			exOrderInfo.setUserCode(exOrderInfo.getSellUserCode());
			exOrderInfo.setUserName(exOrderInfo.getSellUserName());
			exOrderInfo.setTrueName(exOrderInfo.getSellTrueName());
			exOrderInfo.setTransactionFee(exOrderInfo.getTransactionSellFee());
			exOrderInfo.setTransactionFeeRate(exOrderInfo.getTransactionSellFeeRate());
			exOrderInfo.setEntrustNum(exOrderInfo.getSellEntrustNum());
		}
		exOrderInfo.setFixPriceCoinCode(buyExEntrust.getFixPriceCoinCode());
		exOrderInfo.setFixPriceType(buyExEntrust.getFixPriceType());
		// 订单结束详细
		return exOrderInfo;
	}*/

/*	public ExOrder createExOrder(ExOrderInfo exOrderInfo) {

		// 订单开始
		ExOrder exOrder = new ExOrder();
		exOrder.setSaasId(exOrderInfo.getSaasId());
		exOrder.setCurrencyType(exOrderInfo.getCurrencyType());
		exOrder.setWebsite(exOrderInfo.getWebsite());
		exOrder.setOrderNum(exOrderInfo.getOrderNum());
		exOrder.setTransactionCount(exOrderInfo.getTransactionCount());
		exOrder.setTransactionPrice(exOrderInfo.getTransactionPrice());
		exOrder.setTransactionSum(exOrderInfo.getTransactionSum());
		exOrder.setTransactionTime(exOrderInfo.getTransactionTime());
		exOrder.setCoinCode(exOrderInfo.getCoinCode());
		exOrder.setWebsite(exOrderInfo.getWebsite());
		exOrder.setCurrencyType(exOrderInfo.getCurrencyType());
		exOrder.setProductName(exOrderInfo.getProductName());
		exOrder.setOrderTimestapm(exOrderInfo.getOrderTimestapm());
		exOrder.setInOrOutTransaction(exOrderInfo.getInOrOutTransaction());
		exOrder.setFixPriceCoinCode(exOrderInfo.getFixPriceCoinCode());
		exOrder.setFixPriceType(exOrderInfo.getFixPriceType());
		// 订单结束
		return exOrder;

	}*/

	/**
	 * 
	 * <p>
	 * mq队列的处理方法
	 * </p>
	 * 
	 * @author: Gao Mimi
	 * @param: @param exEntrust
	 * @return: void
	 * @Date : 2016年4月23日 下午3:14:17
	 * @throws:
	 */

	public void matchExtrustToOrder(ExEntrust ex) {
		long start = System.currentTimeMillis();
		/*try {
			// 应该是等待数据库保存成功
			Thread.sleep(400);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/
		logger.error("委托单：id=" + ex.getId() + "进入mq进行匹配");
		ExEntrust exEntrust = exEntrustService.get(ex.getId());
		if (null == exEntrust) {
			logger.error("-委托单还未保存到数据库中-");
		}
		// 开始匹配
		if (exEntrust.getStatus().equals(0) || exEntrust.getStatus().equals(1)) {
			matchExtrustToOrder(exEntrust, exEntrust.getSaasId());
		}
		
		long end = System.currentTimeMillis();
		logger.error("匹配成功，共耗时：" + (end - start));
	}

	public String getMatchCustomerType(ExEntrust exEntrust) {
		return DifCustomer.getMatchCustomerType(exEntrust);
	}

	public void matching(ExEntrust buyexEntrust, ExEntrust sellentrust, String initiative) {
		long start = System.currentTimeMillis();

		ExEntrustService exEntrustService = (ExEntrustService) ContextUtil.getBean("exEntrustService");
		  String header=exEntrustService.getHeader(sellentrust);
		if (buyexEntrust.getStatus() > 1) {// 如果状态达到2，3，4都不能再交易,并且要从临时表中删除
			exentrustOnedayService.delByEntrustNumber(buyexEntrust.getId());
		//	exEntrustService.delExEntrust(buyexEntrust);
			return;
		}
 		
		if (sellentrust.getStatus() > 1) {// 如果状态达到2，3，4都不能再交易,并且要从临时表中删除
			exentrustOnedayService.delByEntrustNumber(sellentrust.getId());
		//	exEntrustService.delExEntrust(sellentrust);
			return;
		}
		if (buyexEntrust.getEntrustWay() == 2 && sellentrust.getEntrustWay() == 1 && initiative.equals("sell")) {// 如果是买市价，卖限价
			// 取卖币得最低价，看该匹配订单是否跳过了最小卖币价
			String sellOnePrices=redisService.get(header+":"+ExchangeDataCache.SellOnePrice);
	 		BigDecimal sellOnePrice=new BigDecimal("0.00");
	 		if(null!=sellOnePrices){
	 			sellOnePrice=new BigDecimal(sellOnePrices);
	 		}
			if (sellOnePrice.compareTo(sellentrust.getEntrustPrice()) == -1) {
				return;
			}
		}
		// 如果是买限价，卖市价
		if (buyexEntrust.getEntrustWay() == 1 && sellentrust.getEntrustWay() == 2 && initiative.equals("buy")) {
			BigDecimal buyOnePrice=new BigDecimal("0.00");
	 		String buyOnePrices=redisService.get(header+":"+ExchangeDataCache.BuyOnePrice);
	 		if(null!=buyOnePrices){
	 			buyOnePrice=new BigDecimal(buyOnePrices);
	 		}
			if (buyOnePrice.compareTo(buyexEntrust.getEntrustPrice()) == 1) {
				return;
			}
		}
		long end = System.currentTimeMillis();
		logger.error("matching1，共耗时：" + (end - start));
		// 判断结束，开始匹配，以下是匹配规则：
		/*
		 * 1.买家限价 1.卖家限价 2.卖家市价 2.买家市价 1.卖家限价 2.卖家市价
		 */
		// 买家限价（必须相等才匹配）
		if (buyexEntrust.getEntrustWay().equals(1)) {
			// (1)买家限价，卖家限价
			if (sellentrust.getEntrustWay().equals(1)) {
				logger.error("买家限价，卖家限价");
				oneCase(buyexEntrust, sellentrust, initiative);
				// (2)买家限价，卖家市价
			} else if (sellentrust.getEntrustWay().equals(2)) {
				logger.error("买家限价，卖家市价");
				twoCase(buyexEntrust, sellentrust, initiative);
			}
			// 买家市价 只要未完成的卖家都可以
		} else if (buyexEntrust.getEntrustWay().equals(2)) {
			// (3)买家市价，卖家限价
			if (sellentrust.getEntrustWay().equals(1)) {
				logger.error("买家市价，卖家限价");
				threeCase(buyexEntrust, sellentrust);
				// (4)买家市价，卖家市价
			} else if (sellentrust.getEntrustWay().equals(2)) {
				logger.error("买家市价，卖家市价");
				fourCase(buyexEntrust, sellentrust);
			}
		}

		// 感觉下面一步多此一举，暂时注掉 by shangxl
		/*
		 * //卖家限价必须相等才匹配 if(sellentrust.getEntrustWay().equals(1)){ //卖家限价
		 * if(buyexEntrust.getEntrustWay().equals(1)){//(1)买家限价，卖家限价
		 * oneCase(buyexEntrust,sellentrust,initiative); }else
		 * if(buyexEntrust.getEntrustWay().equals(2)){ ////(3)买家市价，卖家限价
		 * threeCase(buyexEntrust,sellentrust); } }else
		 * if(sellentrust.getEntrustWay().equals(2)){ //卖家市价 //只要未完成的卖家都可以
		 * //卖家是限价 if(buyexEntrust.getEntrustWay().equals(1)){ //(2)买家限价，卖家市价
		 * twoCase(buyexEntrust,sellentrust,initiative); }else
		 * if(buyexEntrust.getEntrustWay().equals(2)){// (4)买家市价，卖家市价
		 * fourCase(buyexEntrust,sellentrust); } }
		 */

		// 这步一般系统不会走
		if (!DifCustomer.getIsCommon()) {
			// 判断是否要清除指定配对
			try {
				exEntrustService.isCanelOnlyentrustNum(buyexEntrust);
				exEntrustService.isCanelOnlyentrustNum(sellentrust);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("清除配对报错");
			}
		}
	}

/*	public void AfterMatchSpecialDeal(ExEntrust exEntrust) {
		ExEntrustService exEntrustService = (ExEntrustService) ContextUtil.getBean("exEntrustService");

		if (exEntrust.getCustomerType().equals(2)) {
			if (exEntrust.getStatus().equals(1)) {
				exEntrustService.cancelExEntrust(exEntrust.getEntrustNum(), exEntrust.getCustomerId(), "主动发起匹配多余要撤单");
			}
		}
		if (exEntrust.getCustomerType().equals(2)) {
			if (exEntrust.getStatus().equals(1)) {
				exEntrustService.cancelExEntrust(exEntrust.getEntrustNum(), exEntrust.getCustomerId(), "主动发起匹配多余要撤单");
			}

		}
	}*/
	
	public String[] mqsaveExEntrust(ExEntrust exEntrust) {
		long start = System.currentTimeMillis();
		String[] relt = new String[2];
		// 根据委托单类型（买、卖）判断账户金额是否满足委托条件、
		// 冻结金额或者币、增加冷热钱包记录
		relt = exEntrustService.mqsaveExEntrust(exEntrust);
		if (relt[0].equals(CodeConstant.CODE_SUCCESS)) {
			String header=exEntrustService.getHeader(exEntrust);
			ExchangeDataCache.setStringData(header + ":" + ExchangeDataCache.IsMatch, "1"); //要推送
			// 下面的失败不会影响委托订单的生成， 因为只是推送出错，不会影响数据的正确性
			try {
				if (DifCustomer.isInOpenplateAndCloseplate()) {
					// 放进mq队列进行匹配
					MQEnter.pushExEntrustMQ(exEntrust);
				}
			} catch (Exception e) {
				logger.error("委托成功订单保存成功。放进mq失败");
			}
			long end = System.currentTimeMillis();
			logger.error("委托mq中，耗时：" + (end - start));
			return relt;
		} else {
			return relt;
		}

	}
}
