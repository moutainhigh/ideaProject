/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Wu Shuiming
 * @version:      V1.0 
 * @Date:        2016年3月24日 下午2:04:29
 */
package hry.trade.entrust.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import hry.core.constant.StringConstant;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.redis.common.utils.RedisService;
import hry.trade.account.service.ExDigitalmoneyAccountService;
import hry.trade.entrust.dao.ExOrderDao;
import hry.trade.entrust.dao.ExOrderInfoDao;
import hry.trade.entrust.model.ExOrder;
import hry.trade.entrust.model.ExOrderInfo;
import hry.trade.entrust.service.ExEntrustService;
import hry.trade.entrust.service.ExOrderService;
import hry.trade.model.DifCustomer;
import hry.trade.model.TradeRedis;
import hry.trade.redis.model.ExchangeDataCacheRedis;
import hry.trade.websoketContext.PushData;
import hry.trade.websoketContext.model.MarketTrades;
import hry.trade.websoketContext.model.MarketTradesSelf;
import hry.trade.websoketContext.model.MarketTradesSub;
import hry.util.SortList;
import hry.util.date.DateUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * <p>
 * TODO
 * </p>
 * 
 * @author: Gao Mimi
 * @Date : 2016年4月12日 下午4:45:50
 */
@Service("exOrderService")
public class ExOrderServiceImpl extends BaseServiceImpl<ExOrder, Long> implements ExOrderService {

	@Resource(name = "exOrderDao")
	@Override
	public void setDao(BaseDao<ExOrder, Long> dao) {
		super.dao = dao;
	}

	@Resource
	public RedisService redisService;
	@Resource
	public ExDigitalmoneyAccountService exDigitalmoneyAccountService;
	@Resource
	public ExEntrustService exEntrustService;
	@Resource
	public ExOrderInfoDao exOrderInfoDao;

	@Override
	public void setCurrentExchangPrice(String header) {
		ExOrderDao exOrderDao = (ExOrderDao) this.dao;
		String[] headarry = header.split(":");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("website", headarry[0]);
		map.put("currencyType", headarry[1]);
		map.put("coinCode", headarry[2].split("_")[0]);
		map.put("fixPriceCoinCode", headarry[2].split("_")[1]);
		List<ExOrder> list = exOrderDao.getCurrentExchangPrice(map);
		if (null != list && list.size() != 0) {
			TradeRedis.setStringData(header + ":" + ExchangeDataCacheRedis.CurrentExchangPrice, list.get(0).getTransactionPrice().toString());
			TradeRedis.setStringData(header + ":" + ExchangeDataCacheRedis.CurrentExchangDate, DateUtil.dateToString(list.get(0).getTransactionTime(), StringConstant.DATE_FORMAT_YMD).toString());
			TradeRedis.setStringData(header + ":" + ExchangeDataCacheRedis.CurrentExchangTime, DateUtil.dateToString(list.get(0).getTransactionTime(), StringConstant.DATE_FORMAT_FULL).toString());
		} else {
			TradeRedis.setStringData(header + ":" + ExchangeDataCacheRedis.CurrentExchangPrice, "0.00");
			TradeRedis.setStringData(header + ":" + ExchangeDataCacheRedis.CurrentExchangDate, DateUtil.dateToString(new Date(), StringConstant.DATE_FORMAT_YMD).toString());
			TradeRedis.setStringData(header + ":" + ExchangeDataCacheRedis.CurrentExchangTime, DateUtil.dateToString(new Date(), StringConstant.DATE_FORMAT_FULL).toString());
		}
	}

	@Override
	public void setLastExchangPrice(String header) {
		ExOrderDao exOrderDao = (ExOrderDao) this.dao;
		String[] headarry = header.split(":");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("website", headarry[0]);
		map.put("currencyType", headarry[1]);
		map.put("coinCode", headarry[2].split("_")[0]);
		map.put("fixPriceCoinCode", headarry[2].split("_")[1]);
		List<ExOrder> list = exOrderDao.getLastExchangPrice(map);

		if (null != list && list.size() != 0) {
			if (list.size() == 2) {
				TradeRedis.setStringData(header + ":" + ExchangeDataCacheRedis.LastExchangPrice, list.get(1).getTransactionPrice().toString());
			} else if (list.size() == 1) {
				TradeRedis.setStringData(header + ":" + ExchangeDataCacheRedis.LastExchangPrice, list.get(0).getTransactionPrice().toString());
			}
		} else {
			TradeRedis.setStringData(header + ":" + ExchangeDataCacheRedis.LastExchangPrice, "0.00");
		}
	}

	@Override
	public void setOpenedExchangPrice(String header, BigDecimal issuePrice) {
		ExOrderDao exOrderDao = (ExOrderDao) this.dao;
		String[] headarry = header.split(":");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("website", headarry[0]);
		map.put("currencyType", headarry[1]);
		map.put("coinCode", headarry[2].split("_")[0]);
		map.put("fixPriceCoinCode", headarry[2].split("_")[1]);
		map.put("minDate", DateUtil.dateToString(new Date(), "yyyy-MM-dd" + " 00:00:00"));
		List<ExOrder> list = exOrderDao.getOpenExchangPrice(map);
		if (null != list && list.size() != 0) {
			TradeRedis.setStringData(header + ":" + ExchangeDataCacheRedis.OpenedExchangPrice, list.get(0).getTransactionPrice().toString());
		} else {
			// 如果当天还没开盘，哪就用最新的交易记录作为开盘价
			List<ExOrder> list1 = exOrderDao.getCurrentExchangPrice(map);
			if (null != list1 && list1.size() != 0) {
				TradeRedis.setStringData(header + ":" + ExchangeDataCacheRedis.OpenedExchangPrice, list1.get(0).getTransactionPrice().toString());
			} else {
				TradeRedis.setStringData(header + ":" + ExchangeDataCacheRedis.OpenedExchangPrice, issuePrice == null ? "0.00" : issuePrice.toString());
			}
		}
	}

	@Override
	public void pushNewList(String header, Integer count) {
		PushData.pushNewRecordList(getNewList(header, count), header);
		PushData.pushNewListRecordMarketAsc(getNewListMarket(header, "asc"), header);

	}

	@Override
	public String getNewList(String header, Integer count) {
		if (null == count) {
			count = 10;
		}
		ExOrderDao exOrderDao = (ExOrderDao) this.dao;
		String[] headarry = header.split(":");
		Map<String, Object> seramap = new HashMap<String, Object>();
		seramap.put("website", headarry[0]);
		seramap.put("currencyType", headarry[1]);
		seramap.put("coinCode", headarry[2].split("_")[0]);
		seramap.put("fixPriceCoinCode", headarry[2].split("_")[1]);
		seramap.put("count", count);
		List<ExOrder> list = exOrderDao.findNewList(seramap);
		for (ExOrder l : list) {
			l.setTransactionPrice(l.getTransactionPrice().setScale(4, BigDecimal.ROUND_HALF_UP));
			l.setTransactionCount(l.getTransactionCount().setScale(4, BigDecimal.ROUND_HALF_UP));
		}
		MarketTradesSelf marketTrades = new MarketTradesSelf();
		marketTrades.setTrades(list);
		SimplePropertyPreFilter s = new SimplePropertyPreFilter(ExOrder.class, "coinCode", "transactionTime", "transactionPrice", "transactionCount");
		return JSON.toJSONString(marketTrades, s).toString();
	}

	@Override
	// count=60
	// timeorder=desc
	// 行情模块-成交数据
	public String getNewListMarket(String header, String timeorder) {
		String[] headarry = header.split("_");
		int keepDecimalForCoin = 8;
		int keepDecimalForCurrency = 8;
		//读取配置
		String str = redisService.get("cn:coinInfoList");
		if(!StringUtils.isEmpty(str)){
			JSONArray pa = JSON.parseArray(str);
			if(pa!=null){
				for(int i = 0 ; i < pa.size() ; i++){
					JSONObject obj = pa.getJSONObject(i);
					if(header.equals(obj.getString("coinCode")+"_"+obj.getString("fixPriceCoinCode"))){
						keepDecimalForCoin = obj.getIntValue("keepDecimalForCoin");
						keepDecimalForCurrency = obj.getIntValue("keepDecimalForCurrency");
						break;
					}
				}
			}
		}
		
		Map<String, Object> seramap = new HashMap<String, Object>();
		seramap.put("coinCode", headarry[0]);
		seramap.put("fixPriceCoinCode", headarry[1]);
		seramap.put("count", ExchangeDataCacheRedis.LastOrderRecordsLmit);
		List<ExOrderInfo> list = null;
		if (timeorder.equals("asc")) {
			String v=header + ":" + ExchangeDataCacheRedis.LastOrderRecords;
			if (!StringUtils.isEmpty(v)) {
				list = exOrderInfoDao.findNewList(seramap);
				redisService.save(header + ":" + ExchangeDataCacheRedis.LastOrderRecords, JSON.toJSONString(list));
			}else{
				 list = JSON.parseArray(v, ExOrderInfo.class);
			}

		} else {
			String v=redisService.get(header + ":" + ExchangeDataCacheRedis.LastOrderRecords);
			if (StringUtils.isEmpty(v)) {
				list = exOrderInfoDao.findNewListDesc(seramap);
				Collections.reverse(list); // 倒序
				redisService.save(header + ":" + ExchangeDataCacheRedis.LastOrderRecords, JSON.toJSONString(list));
			} else {
				list = JSON.parseArray(v, ExOrderInfo.class);
				Collections.reverse(list); // 倒序
			}
		}

		List<MarketTradesSub> listsub = new ArrayList<MarketTradesSub>();
		for (ExOrderInfo l : list) {
			MarketTradesSub sub = new MarketTradesSub();
			sub.setAmount(l.getTransactionCount().setScale(keepDecimalForCoin, BigDecimal.ROUND_HALF_UP));
			sub.setPrice(l.getTransactionPrice().setScale(keepDecimalForCurrency, BigDecimal.ROUND_HALF_UP));
			sub.setTid(l.getOrderNum());
			sub.setType(l.getInOrOutTransaction());
			sub.setTime(l.getTransactionTime().getTime() / 1000);
			listsub.add(sub);
		}
		SortList<MarketTradesSub> sort = new SortList<>();
		sort.Sort(listsub, "getTime", "desc");
		MarketTrades marketTrades = new MarketTrades();
		marketTrades.setTrades(listsub);
		return JSON.toJSONString(marketTrades).toString();
	}

	@Override
	public String findNewListMarketnewAdd(String header, String minDate, String maxDate) {
		ExOrderDao exOrderDao = (ExOrderDao) this.dao;
		String[] headarry = header.split(":");
		Map<String, Object> seramap = new HashMap<String, Object>();
		seramap.put("website", headarry[0]);
		seramap.put("currencyType", headarry[1]);
		seramap.put("coinCode", headarry[2].split("_")[0]);
		seramap.put("fixPriceCoinCode", headarry[2].split("_")[1]);
		seramap.put("minDate", minDate);
		seramap.put("maxDate", maxDate);
		List<ExOrder> list = new ArrayList<ExOrder>();
		if (DifCustomer.isexOrderIsMemory()) {
			list = redisService.getList(header + ":" + ExchangeDataCacheRedis.LastOrderRecordAdds);
			if (null == list || list.size() == 0) {
				list = exOrderDao.findNewListnewAdd(seramap);
				redisService.setList(header + ":" + ExchangeDataCacheRedis.LastOrderRecords, list);
			} else {
				Collections.reverse(list); // 倒序
				// 取一次就清空一次，所以取得就是增量
				redisService.setList(header + ":" + ExchangeDataCacheRedis.LastOrderRecordAdds, new ArrayList<ExOrder>());
			}

		} else {
			list = exOrderDao.findNewListnewAdd(seramap);
		}
		List<MarketTradesSub> listsub = new ArrayList<MarketTradesSub>();
		if (null != list && list.size() > 0) {
			for (ExOrder l : list) {
				MarketTradesSub sub = new MarketTradesSub();
				sub.setAmount(l.getTransactionCount().setScale(4, BigDecimal.ROUND_HALF_UP));
				sub.setPrice(l.getTransactionPrice().setScale(2, BigDecimal.ROUND_HALF_UP));
				sub.setTid(l.getOrderNum());
				sub.setType(l.getInOrOutTransaction());
				sub.setTime(l.getTransactionTime().getTime() / 1000);
				listsub.add(sub);
			}
			MarketTrades marketTrades = new MarketTrades();
			marketTrades.setTrades(listsub);
			return JSON.toJSONString(marketTrades).toString();
		}
		return "";

	}


	@Override
	public List<ExOrder> exAveragePrice(String coinCode) {
		ExOrderDao exOrderDao = (ExOrderDao) this.dao;
		return exOrderDao.exAveragePrice(coinCode);
	}

	@Override
	public void setExchangeDataCache(ExOrderInfo exOrderInfo, ExOrder exOrder) {
		// TODO Auto-generated method stub

	}

}
