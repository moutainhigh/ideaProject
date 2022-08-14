/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Wu Shuiming
 * @version:      V1.0 
 * @Date:        2016年3月24日 下午2:04:29
 */
package hry.trade.entrust.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import hry.core.constant.StringConstant;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.exchange.product.service.ProductCommonService;
import hry.manage.remote.model.Coin;
import hry.redis.common.utils.RedisService;
import hry.trade.entrust.DifCustomer;
import hry.trade.entrust.ExchangeDataCache;
import hry.trade.entrust.dao.ExOrderDao;
import hry.trade.entrust.model.ExOrder;
import hry.trade.entrust.model.ExOrderInfo;
import hry.trade.entrust.service.ExOrderService;
import hry.trade.websoketContext.PushData;
import hry.trade.websoketContext.model.MarketTrades;
import hry.trade.websoketContext.model.MarketTradesSelf;
import hry.trade.websoketContext.model.MarketTradesSub;
import hry.trade.websoketContext.model.Total;
import hry.util.date.DateUtil;
import hry.util.klinedata.TransactionOrder;
import hry.util.sys.ContextUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * <p> TODO</p>
 * @author:         Gao Mimi 
 * @Date :          2016年4月12日 下午4:45:50 
 */
@Service("exOrderService")
public class ExOrderServiceImpl extends BaseServiceImpl<ExOrder, Long>
		implements ExOrderService {

	@Resource(name = "exOrderDao")
	@Override
	public void setDao(BaseDao<ExOrder, Long> dao) {
		super.dao = dao;
	}
	
	@Resource
	public RedisService redisService;
	@Resource
	public ProductCommonService productCommonService;
	@Override
	public void setCurrentExchangPrice(String header) {
		ExOrderDao exOrderDao=(ExOrderDao)this.dao;
		String[] headarry=header.split(":");
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("website", headarry[0]);
		map.put("currencyType", headarry[1]);
		map.put("coinCode", headarry[2].split("_")[0]);
		map.put("fixPriceCoinCode", headarry[2].split("_")[1]);
		List<ExOrder> list= exOrderDao.getCurrentExchangPrice(map);
		if(null!=list&&list.size()!=0){
			ExchangeDataCache.setStringData(header+":"+ExchangeDataCache.CurrentExchangPrice, list.get(0).getTransactionPrice().toString());
			ExchangeDataCache.setStringData(header+":"+ExchangeDataCache.CurrentExchangDate, DateUtil.dateToString(list.get(0).getTransactionTime(), StringConstant.DATE_FORMAT_YMD).toString());
			ExchangeDataCache.setStringData(header+":"+ExchangeDataCache.CurrentExchangTime, DateUtil.dateToString(list.get(0).getTransactionTime(),StringConstant.DATE_FORMAT_FULL).toString());
		}else{
			ExchangeDataCache.setStringData(header+":"+ExchangeDataCache.CurrentExchangPrice, "0.00");
			ExchangeDataCache.setStringData(header+":"+ExchangeDataCache.CurrentExchangDate, DateUtil.dateToString(new Date(),StringConstant.DATE_FORMAT_YMD).toString());
			ExchangeDataCache.setStringData(header+":"+ExchangeDataCache.CurrentExchangTime, DateUtil.dateToString(new Date(),StringConstant.DATE_FORMAT_FULL).toString());
		}
	}

	@Override
	public void setLastExchangPrice(String header) {
		ExOrderDao exOrderDao=(ExOrderDao)this.dao;
		String[] headarry=header.split(":");
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("website", headarry[0]);
		map.put("currencyType", headarry[1]);
		map.put("coinCode", headarry[2].split("_")[0]);
		map.put("fixPriceCoinCode", headarry[2].split("_")[1]);
		List<ExOrder> list= exOrderDao.getLastExchangPrice(map);
		
		if(null!=list&&list.size()!=0){
			if(list.size()==2){
				ExchangeDataCache.setStringData(header+":"+ExchangeDataCache.LastExchangPrice, list.get(1).getTransactionPrice().toString());
			}else if(list.size()==1){
				ExchangeDataCache.setStringData(header+":"+ExchangeDataCache.LastExchangPrice, list.get(0).getTransactionPrice().toString());
			}
		}else{
			ExchangeDataCache.setStringData(header+":"+ExchangeDataCache.LastExchangPrice, "0.00");
		}
	}
	
	
	@Override
	public void setOpenedExchangPrice(String header,BigDecimal issuePrice) {
		ExOrderDao exOrderDao=(ExOrderDao)this.dao;
		String[] headarry=header.split(":");
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("website", headarry[0]);
		map.put("currencyType", headarry[1]);
		map.put("coinCode", headarry[2].split("_")[0]);
		map.put("fixPriceCoinCode", headarry[2].split("_")[1]);
		map.put("minDate", DateUtil.dateToString(new Date(), "yyyy-MM-dd"+" 00:00:00"));
		List<ExOrder> list= exOrderDao.getOpenExchangPrice(map);
		if(null!=list&&list.size()!=0){
			ExchangeDataCache.setStringData(header+":"+ExchangeDataCache.OpenedExchangPrice, list.get(0).getTransactionPrice().toString());
		}else{
			//如果当天还没开盘，哪就用最新的交易记录作为开盘价
			List<ExOrder> list1= exOrderDao.getCurrentExchangPrice(map);
			if(null!=list1&&list1.size()!=0){
				ExchangeDataCache.setStringData(header+":"+ExchangeDataCache.OpenedExchangPrice, list1.get(0).getTransactionPrice().toString());
			}else{
				ExchangeDataCache.setStringData(header+":"+ExchangeDataCache.OpenedExchangPrice, issuePrice==null?"0.00":issuePrice.toString());
			}
		}
	}
	@Override
	public void pushNewList(String header ,Integer count) {
		PushData.pushNewRecordList(getNewList(header,count),header);
		PushData.pushNewListRecordMarketAsc(getNewListMarket(header,count,"asc"),header);
		
		
	}
	@Override
	public String getNewList(String header ,Integer count) {
		if(null==count){
			count=10;
		}
		ExOrderDao exOrderDao=(ExOrderDao)this.dao;
		String[] headarry=header.split(":");
		Map<String,Object> seramap=new HashMap<String,Object>();
		seramap.put("website", headarry[0]);
		seramap.put("currencyType", headarry[1]);
		seramap.put("coinCode", headarry[2].split("_")[0]);
		seramap.put("fixPriceCoinCode", headarry[2].split("_")[1]);
		seramap.put("count", count);
		List<ExOrder> list=exOrderDao.findNewList(seramap);
        for(ExOrder l:list){
        	l.setTransactionPrice(l.getTransactionPrice().setScale(4, BigDecimal.ROUND_HALF_UP));
        	l.setTransactionCount(l.getTransactionCount().setScale(4, BigDecimal.ROUND_HALF_UP));
        }
        MarketTradesSelf marketTrades=new MarketTradesSelf();
		marketTrades.setTrades(list);
		SimplePropertyPreFilter  s=new SimplePropertyPreFilter(ExOrder.class,"coinCode","transactionTime","transactionPrice","transactionCount");
		return JSON.toJSONString(marketTrades,s).toString();
	}
	@Override
	//count=60
	//timeorder=desc
	//行情模块-成交数据
	public String getNewListMarket(String header ,Integer count,String timeorder) {
		ProductCommonService productCommonService =(ProductCommonService) ContextUtil.getBean("productCommonService"); 
		String[] headarry=header.split(":");
		Coin productCommon=productCommonService.getProductCommon( headarry[2].split("_")[0], headarry[2].split("_")[1]);
		int keepDecimalForCoin=4;
		int keepDecimalForCurrency=2;
		if(null!=productCommon){
			 keepDecimalForCoin=(null!=productCommon.getKeepDecimalForCoin()?productCommon.getKeepDecimalForCoin():4);
			 keepDecimalForCurrency=(null!=productCommon.getKeepDecimalForCurrency()?productCommon.getKeepDecimalForCurrency():2);
		}
		if(null==count){
			count=10;
		}
		ExOrderDao exOrderDao=(ExOrderDao)this.dao;
		Map<String,Object> seramap=new HashMap<String,Object>();
		seramap.put("website", headarry[0]);
		seramap.put("currencyType", headarry[1]);
		seramap.put("coinCode", headarry[2].split("_")[0]);
		seramap.put("fixPriceCoinCode", headarry[2].split("_")[1]);
		seramap.put("count", count);
		List<ExOrder> list=null;
		if(timeorder.equals("asc")){
			
			 if(DifCustomer.isexOrderIsMemory()){
				 
				   list= redisService.getList(header+":"+ExchangeDataCache.LastOrderRecords);
				   
				   if(null==list||list.size()==0){
					   list=exOrderDao.findNewList(seramap);
					   redisService.setList(header+":"+ExchangeDataCache.LastOrderRecords, list);
				   }else{
					   list= list.subList(list.size()-count,list.size());
				   }
				   
			 }else{
				   list=exOrderDao.findNewList(seramap);
				
			 }
			 
		}else{
			if(DifCustomer.isexOrderIsMemory()){//必须执行这段代码
				  list= redisService.getList(header+":"+ExchangeDataCache.LastOrderRecords);
				  if(null==list||list.size()==0){
					   list=exOrderDao.findNewListDesc(seramap);
					   Collections.reverse(list);   //倒序
					   redisService.setList(header+":"+ExchangeDataCache.LastOrderRecords, list);
				   }else{
					   if(count<list.size()){
						   list= list.subList(list.size()-count,list.size());
					   }
					   Collections.reverse(list);   //倒序
				   }
			 }else{
				 list=exOrderDao.findNewListDesc(seramap);
			 }
		}
		
		List<MarketTradesSub> listsub=new ArrayList<MarketTradesSub>();
        for(ExOrder l:list){
        	MarketTradesSub sub=new MarketTradesSub();
        	sub.setAmount(l.getTransactionCount().setScale(keepDecimalForCoin, BigDecimal.ROUND_HALF_UP));
        	sub.setPrice(l.getTransactionPrice().setScale(keepDecimalForCurrency, BigDecimal.ROUND_HALF_UP));
        	sub.setTid(l.getOrderNum());
        	sub.setType(l.getInOrOutTransaction());
        	sub.setTime(l.getTransactionTime().getTime()/1000);
        	listsub.add(sub);
        }
		MarketTrades marketTrades=new MarketTrades();
		marketTrades.setTrades(listsub);
		return JSON.toJSONString(marketTrades).toString();
	}
	
	@Override
	public String findNewListMarketnewAdd(String header,String minDate,String maxDate){
		ExOrderDao exOrderDao=(ExOrderDao)this.dao;
		String[] headarry=header.split(":");
		Map<String,Object> seramap=new HashMap<String,Object>();
		seramap.put("website", headarry[0]);
		seramap.put("currencyType", headarry[1]);
		seramap.put("coinCode", headarry[2].split("_")[0]);
		seramap.put("fixPriceCoinCode", headarry[2].split("_")[1]);
		seramap.put("minDate", minDate);
		seramap.put("maxDate", maxDate);
		List<ExOrder> list=new ArrayList<ExOrder>();
		if(DifCustomer.isexOrderIsMemory()){
			  list= redisService.getList(header+":"+ExchangeDataCache.LastOrderRecordAdds);
			  if(null==list||list.size()==0){
				   list=exOrderDao.findNewListnewAdd(seramap);
				   redisService.setList(header+":"+ExchangeDataCache.LastOrderRecords, list);
			   }else{
				   Collections.reverse(list);   //倒序
				   //取一次就清空一次，所以取得就是增量
				   redisService.setList(header+":"+ExchangeDataCache.LastOrderRecordAdds, new ArrayList<ExOrder>());
			   }
			  
		 }else{
			 list=exOrderDao.findNewListnewAdd(seramap);
		 }
		List<MarketTradesSub> listsub=new ArrayList<MarketTradesSub>();
        if(null!=list&&list.size()>0){
        	for(ExOrder l:list){
            	MarketTradesSub sub=new MarketTradesSub();
            	sub.setAmount(l.getTransactionCount().setScale(4, BigDecimal.ROUND_HALF_UP));
            	sub.setPrice(l.getTransactionPrice().setScale(2, BigDecimal.ROUND_HALF_UP));
            	sub.setTid(l.getOrderNum());
            	sub.setType(l.getInOrOutTransaction());
            	sub.setTime(l.getTransactionTime().getTime()/1000);
            	listsub.add(sub);
            }
        	MarketTrades marketTrades=new MarketTrades();
    		marketTrades.setTrades(listsub);
    		return JSON.toJSONString(marketTrades).toString();
        }
        return "";
		
       
	}
	@Override
	//首页推送数据
	public void pushTotal(String coinCode) {
		StringBuffer sb=new StringBuffer("{\"coinCode\":\"" + coinCode + "\",\"data\":"+getTotal(coinCode)+"}");
		PushData.pushIndex(sb.toString(),coinCode);
	}

	@Override
	public String getTotal(String header) {
		String[] headarry=header.split(":");
		Coin productCommon=productCommonService.getProductCommon( headarry[2].split("_")[0], headarry[2].split("_")[1]);
		int keepDecimalForCoin=4;
		int keepDecimalForCurrency=2;
		if(null!=productCommon){
			 keepDecimalForCoin=(null!=productCommon.getKeepDecimalForCoin()?productCommon.getKeepDecimalForCoin():4);
			 keepDecimalForCurrency=(null!=productCommon.getKeepDecimalForCurrency()?productCommon.getKeepDecimalForCurrency():2);
		}
		Total total=new Total();
		//最新成交价
		String currentExchangPrice=ExchangeDataCache.getStringData(header+":"+ExchangeDataCache.CurrentExchangPrice);
		currentExchangPrice=(null!=currentExchangPrice?currentExchangPrice:new BigDecimal("0.00").toString());
		
		//最新成交价上一个
		String lastExchangPrice=ExchangeDataCache.getStringData(header+":"+ExchangeDataCache.LastExchangPrice);
		lastExchangPrice=(null!=lastExchangPrice?lastExchangPrice:new BigDecimal("0.00").toString());
		total.setCurrentExchangPrice(new BigDecimal(currentExchangPrice).setScale(keepDecimalForCurrency, BigDecimal.ROUND_HALF_EVEN).toString());
		total.setLastExchangPrice(new BigDecimal(lastExchangPrice).setScale(keepDecimalForCurrency, BigDecimal.ROUND_HALF_EVEN).toString());
		//开盘价
		String openedExchangPrice=ExchangeDataCache.getStringData(header+":"+ExchangeDataCache.OpenedExchangPrice);
		openedExchangPrice=(null!=openedExchangPrice?openedExchangPrice:new BigDecimal("0.00").toString());
		
		
		//日跌涨幅
		BigDecimal  riseAndFall=new BigDecimal("0.00");
		if(openedExchangPrice!=null&& (new BigDecimal(openedExchangPrice).compareTo(BigDecimal.ZERO)>0)){
			BigDecimal fenmu = BigDecimal.ONE;
			String table="TransactionOrder_" + headarry[0] + "_" + headarry[1] + "_" + headarry[2] + "_1440";
			List<TransactionOrder> list=JSON.parseArray(redisService.get(table),TransactionOrder.class);
			if(list!=null&&list.size()>0){
				TransactionOrder _transactionOrder =list.get(0);
				fenmu = _transactionOrder.getEndPrice();//收盘价
				if(fenmu.compareTo(BigDecimal.ZERO)!=0){
					BigDecimal bigDecimal = (new BigDecimal(currentExchangPrice).subtract(fenmu)).multiply(new BigDecimal(100)).divide(fenmu,2);
					riseAndFall=bigDecimal.setScale(5, BigDecimal.ROUND_HALF_UP);
				}
			}
		}
		total.setRiseAndFall(riseAndFall.toString());
		 //价格趋势
		 RedisService redisService=(RedisService)ContextUtil.getBean("redisService");
		 String  jsonStringList=redisService.get(header+":"+"pushPriceTrendList");
		 List<BigDecimal[]> pushPriceTrendList=( List<BigDecimal[]>)JSON.parse(jsonStringList);
		 total.setPriceTrend(pushPriceTrendList);
		 
	
		Map<String,Object> seramap=new HashMap<String,Object>();
		seramap.put("website", headarry[0]);
		seramap.put("currencyType", headarry[1]);
		seramap.put("coinCode", headarry[2].split("_")[0]);
		seramap.put("fixPriceCoinCode", headarry[2].split("_")[1]);
		seramap.put("minDate", DateUtil.dateToString(DateUtil.addDay(new Date(), -1), "yyyy-MM-dd HH:mm:ss"));
		seramap.put("maxDate", DateUtil.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
		List<TransactionOrder> list = ((ExOrderDao)dao).getTotalData(seramap);
		if(null!=list&&list.size()!=0){
			TransactionOrder o=list.get(0);
			if(null!=o){
				total.setMaxPrice(o.getMaxPrice().setScale(keepDecimalForCurrency, BigDecimal.ROUND_HALF_EVEN).toString());
				total.setMinPrice(o.getMinPrice().setScale(keepDecimalForCurrency, BigDecimal.ROUND_HALF_EVEN).toString());
				total.setTransactionCount(o.getTransactionCount().setScale(keepDecimalForCoin, BigDecimal.ROUND_HALF_EVEN).toString());
				total.setTransactionSum(null==o.getAgvPrice()?"0.00":o.getAgvPrice().setScale(keepDecimalForCoin, BigDecimal.ROUND_HALF_EVEN).toString());
			}else{
				total.setMaxPrice("0.00");
				total.setMinPrice("0.00");
				total.setTransactionCount("0.0000");
				total.setTransactionSum("0.0000");
			}
			
		}
		return JSON.toJSONString(total);
		
	}

	@Override
	public List<ExOrder> exAveragePrice(String coinCode) {
		ExOrderDao exOrderDao=(ExOrderDao)this.dao;
		return exOrderDao.exAveragePrice(coinCode);
	}

	@Override
	public void setExchangeDataCache(ExOrderInfo exOrderInfo, ExOrder exOrder) {
		// TODO Auto-generated method stub
		
	}

}
