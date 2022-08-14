/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Wu Shuiming
 * @version:      V1.0 
 * @Date:        2016年3月24日 下午2:04:29
 */
package hry.trade.entrust.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import hry.bean.PageResult;
import hry.change.remote.exEntrust.RemoteExExOrderService;
import hry.core.constant.CodeConstant;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.customer.user.model.AppCustomer;
import hry.exchange.kline2.model.LastKLinePayload;
import hry.manage.remote.model.Order;
import hry.manage.remote.model.base.FrontPage;
import hry.redis.common.utils.RedisService;
import hry.trade.entrust.DifCustomer;
import hry.trade.entrust.ExchangeDataCache;
import hry.trade.entrust.dao.ExOrderInfoDao;
import hry.trade.entrust.model.ExEntrust;
import hry.trade.entrust.model.ExOrder;
import hry.trade.entrust.model.ExOrderInfo;
import hry.trade.entrust.service.ExEntrustService;
import hry.trade.entrust.service.ExOrderInfoService;
import hry.trade.entrust.service.ExOrderService;
import hry.util.PageFactory;
import hry.util.QueryFilter;
import hry.util.date.DateUtil;
import hry.util.idgenerate.IdGenerate;
import hry.util.idgenerate.NumConstant;
import hry.util.sys.ContextUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.*;
/**
 * <p> TODO</p>
 * @author:         Gao Mimi 
 * @Date :          2016年4月12日 下午4:45:50 
 */
@Service("exOrderInfoService")
public class ExOrderInfoServiceImpl extends BaseServiceImpl<ExOrderInfo, Long>
		implements ExOrderInfoService {

	@Resource(name = "exOrderInfoDao")
	@Override
	public void setDao(BaseDao<ExOrderInfo, Long> dao) {
		super.dao = dao;
	}
	@Resource(name = "exOrderService")
	public ExOrderService  exOrderService;

	@Resource
	public ExEntrustService  exEntrustService;
	@Resource
	private RedisService redisService;
	@Resource
	private ExOrderInfoService exOrderInfoService;
	/**
	 *
	 *                            限价（1000元，1个）
	 *  买家:限价（1000元，1个）   ------》                  
	 *                              市价（2个）
	 *                              
	 *                               
	 *                           限价（1000元，1个）
	 *  买家:市价（1500元）   ------》                  
	 *    
	 *                              市价（2个）
	 *                            
	 **/
	/**
	 * 
	 * <p> </p>
	 * @author:         Gao Mimi
	 * @param:    @param exEntrust
	 * @return: void 
	 * @Date :          2016年4月15日 下午2:56:57   
	 * @throws:
	 * 
	 *买家:限价（1000元，1个）市价（1500元）
	 *卖家:限价（1000元，1个）市价（2个）
	 *

	 */
	/*@Override
	public void matchExtrustToOrder(ExEntrust exEntrust,String saasId) {
		  //type类型 1 ： 买 2 ： 卖
		  if(exEntrust.getType().equals(1)){
			  buyExchange(exEntrust,exEntrust.getSaasId());
		  }else if(exEntrust.getType().equals(2)){  
			  sellExchange(exEntrust,exEntrust.getSaasId());
		  }
	}*/
	
	/**
	 * 
	 * 委托单成交后调用的方法
	 * 
	 * @version:   V1.0 
	 * @date:      2016年8月10日 上午9:33:40
	 */
	@Override
	public String[] endTransaction(ExOrderInfo exOrderInfo, ExOrder exOrder, ExEntrust buyexEntrust,
			ExEntrust sellentrust) {
		String[] relt = new String[2];
		if (exOrderInfo.getTransactionCount().compareTo(BigDecimal.ZERO) == 0) {
			relt[0] = CodeConstant.CODE_FAILED;
			relt[1] = "";
			return relt;
		}
		// 买单成交记录
		ExOrderInfo exOrderInfosell = createExOrderInfo(2, buyexEntrust, sellentrust,
				exOrderInfo.getTransactionCount(), exOrderInfo.getTransactionPrice());
		exOrderInfosell.setOrderNum(exOrderInfo.getOrderNum());
		RemoteExExOrderService remoteExEntrustService = (RemoteExExOrderService) ContextUtil
				.getBean("remoteExExOrderService");
		relt = remoteExEntrustService.deductMoney(exOrderInfo, buyexEntrust, sellentrust, exOrderInfosell, exOrder);

		return relt;
	}
    
	public String[] deductMoney(ExOrderInfo exOrderInfo,ExEntrust buyexEntrust,ExEntrust sellentrust){
		String[] relt = new String[2];
		Object object = null;
		Class clazz = null;
		try {
			object = ContextUtil.getBean("exExOrderInfoService");
			clazz = object.getClass();
			
			Class[] argsClass = new Class[3];  
		    argsClass[0] = ExOrderInfo.class;  
		    argsClass[1] = ExEntrust.class;  
		    argsClass[2] = ExEntrust.class;  
			Method m  =clazz.getMethod("deductMoney",argsClass);
			relt=(String[])m.invoke(object,new Object[]{exOrderInfo,buyexEntrust,sellentrust} );
			return relt;
		} catch (Exception e) {
			e.printStackTrace();
			relt[0]=CodeConstant.CODE_FAILED;
			relt[1]="匹配是生成流水失败";
			return relt;
		}
	}
  /*  public void setExchangeDataCache(ExOrderInfo exOrderInfo,ExOrder exOrder){
    		
 
    	  String header=exEntrustService.getHeader(exOrderInfo);
    	  //设置最新成交上一个成交价格
		   ExchangeDataCache.setStringData(header+":"+ExchangeDataCache.LastExchangPrice,ExchangeDataCache.getStringData(header+":"+ExchangeDataCache.CurrentExchangPrice));
		   //设置当前最新成交价
		   ExchangeDataCache.setStringData(header+":"+ExchangeDataCache.CurrentExchangPrice, exOrderInfo.getTransactionPrice().setScale(5, BigDecimal.ROUND_HALF_UP).toString());
		   String currentExchangTime= ExchangeDataCache.getStringData(header+":"+ExchangeDataCache.CurrentExchangDate);
		   String  transactionTime=DateUtil.dateToString(exOrderInfo.getTransactionTime(), StringConstant.DATE_FORMAT_YMD) .toString();
		   //设置开盘价格，如果当前成交日期与之前保存的成交日期不是一样的说明这个是开盘价，从凌晨到开盘这一短时间的开盘价就用最后一个成交价
		   if((null==currentExchangTime)||(null!=currentExchangTime&&!currentExchangTime.equals(transactionTime))){
			   ExchangeDataCache.setStringData(header+":"+ExchangeDataCache.OpenedExchangPrice, exOrderInfo.getTransactionPrice().setScale(5, BigDecimal.ROUND_HALF_UP).toString());
		   }
		   ExchangeDataCache.setStringData(header+":"+ExchangeDataCache.CurrentExchangDate,transactionTime);
		   ExchangeDataCache.setStringData(header+":"+ExchangeDataCache.CurrentExchangTime,DateUtil.dateToString(exOrderInfo.getTransactionTime(), StringConstant.DATE_FORMAT_FULL) .toString());
		   
		   //保存这条成交记录到re
		   
		   List<ExOrder>   list= redisService.getList1(header+":"+ExchangeDataCache.LastOrderRecords);
		   if(list.size()>100){
			   list.remove(0);
		   }
		   list.add(exOrder);
		   redisService.setList(header+":"+ExchangeDataCache.LastOrderRecords, list);
		   //保存增量记录
		   List<ExOrder>   listadd= redisService.getList1(header+":"+ExchangeDataCache.LastOrderRecordAdds);
		   list.add(exOrder);
		   redisService.setList(header+":"+ExchangeDataCache.LastOrderRecordAdds, listadd);
		   //保存分期数据
		   savePeriodLastKLineList(exOrderInfo);
    }*/
    
    /**
     * 保存分期最后一个节点的数据
     * @param exOrderInfo
     */
    public void savePeriodLastKLineList(ExOrderInfo exOrderInfo){
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
     * @param periodDate
     * @param lastKLinePayload
     * @param exOrderInfo
     */
    public void flushLastKlinePayLoad(Map<String, Date> periodDate,LastKLinePayload lastKLinePayload,ExOrderInfo exOrderInfo,String period){

		//获得当前这笔交易订单的所有时间区间与其比较
		Date date = periodDate.get(period);
		if(lastKLinePayload.getTime().compareTo(date.getTime()/1000)==0){//如果发现在同一个区间的话则只进行比较,比较最高价，最低价，设置收盘价为当前这笔订单的价格
			if(exOrderInfo.getTransactionPrice().compareTo(lastKLinePayload.getPriceHigh())>0){//比较成交价格是否大于最高价
				lastKLinePayload.setPriceHigh(exOrderInfo.getTransactionPrice());
			}
			if(exOrderInfo.getTransactionPrice().compareTo(lastKLinePayload.getPriceLow())<0){//比较成交价格是否小于最高价
				lastKLinePayload.setPriceLow(exOrderInfo.getTransactionPrice());
			}
			lastKLinePayload.setPriceLast(exOrderInfo.getTransactionPrice());//刷新最新成交价格
			lastKLinePayload.setAmount(lastKLinePayload.getAmount().add(exOrderInfo.getTransactionCount()));//累加成交量
		}else{//如果发现不在同一个区间的话则重新刷新这个缓存区间的时间值,同时重新所有数据
			lastKLinePayload.setPriceOpen(exOrderInfo.getTransactionPrice());
			lastKLinePayload.setPriceHigh(exOrderInfo.getTransactionPrice());
			lastKLinePayload.setPriceLow(exOrderInfo.getTransactionPrice());
			lastKLinePayload.setPriceLast(exOrderInfo.getTransactionPrice());
			lastKLinePayload.setAmount(exOrderInfo.getTransactionCount());
			lastKLinePayload.set_id(date.getTime()/1000);
			lastKLinePayload.setTime(date.getTime()/1000);
		}
	
    }

	/*@Override
	public void mongoRemoveEntrust(ExEntrust buyexEntrust, ExEntrust sellentrust) {
		if (buyexEntrust.getStatus().equals(1)) {
			MongoUtil<ExEntrust, Long> mongoUtil = new MongoUtil<ExEntrust, Long>(ExEntrust.class,
					buyexEntrust.getWebsite() + buyexEntrust.getCurrencyType() + buyexEntrust.getCoinCode()
							+ "_buy_entrust");
			mongoUtil.save(buyexEntrust);
		}

		if (buyexEntrust.getStatus().equals(2)) {
			MongoUtil<ExEntrust, Long> mongoUtil = new MongoUtil<ExEntrust, Long>(ExEntrust.class,
					buyexEntrust.getWebsite() + buyexEntrust.getCurrencyType() + buyexEntrust.getCoinCode()
							+ "_buy_entrust");
			MongoQueryFilter mongoQueryFilter = new MongoQueryFilter();
			mongoQueryFilter.addFilter("entrustNum=", buyexEntrust.getEntrustNum());
			mongoUtil.delete(mongoQueryFilter);

		}
		if (sellentrust.getStatus().equals(1)) {
			MongoUtil<ExEntrust, Long> mongoUtil = new MongoUtil<ExEntrust, Long>(ExEntrust.class,
					sellentrust.getWebsite() + sellentrust.getCurrencyType() + sellentrust.getCoinCode()
							+ "_sell_entrust");
			mongoUtil.save(sellentrust);
		}
		if (sellentrust.getStatus().equals(2)) {
			MongoUtil<ExEntrust, Long> mongoUtil = new MongoUtil<ExEntrust, Long>(ExEntrust.class,
					sellentrust.getWebsite() + sellentrust.getCurrencyType() + sellentrust.getCoinCode()
							+ "_sell_entrust");
			MongoQueryFilter mongoQueryFilter = new MongoQueryFilter();
			mongoQueryFilter.setSaasId(buyexEntrust.getSaasId());
			mongoQueryFilter.addFilter("entrustNum=", sellentrust.getEntrustNum());
			mongoUtil.delete(mongoQueryFilter);

		}
	}*/
	
	
	//(1)买家限价，卖家限价
	public void oneCase(ExEntrust buyexEntrust,ExEntrust sellentrust,String initiative){
		   //谁小取谁，交易币的个数
		         BigDecimal tradeCount=buyexEntrust.getSurplusEntrustCount().compareTo(sellentrust.getSurplusEntrustCount())<=0?
		    		   buyexEntrust.getSurplusEntrustCount():sellentrust.getSurplusEntrustCount();
		        if(tradeCount.compareTo(new BigDecimal(0))==0){
		    	  return;
		        }		   
		       BigDecimal tradePrice=buyexEntrust.getEntrustPrice();
		       if(tradePrice.compareTo(new BigDecimal(0))==0){
			    	  return;
			      }	
		       //不相等的情况说明是有浮动的，得求最优成交价
		       if(sellentrust.getEntrustWay().equals(1)&&buyexEntrust.getEntrustPrice().compareTo(sellentrust.getEntrustPrice())!=0){
		    	   BigDecimal[] array=new  BigDecimal[4];
		    	   array[0]=buyexEntrust.getEntrustPrice().add(buyexEntrust.getFloatUpPrice());
		    	   array[1]=buyexEntrust.getEntrustPrice().subtract(buyexEntrust.getFloatDownPrice());
		    	   array[2]=sellentrust.getEntrustPrice().add(sellentrust.getFloatUpPrice());
		    	   array[3]=sellentrust.getEntrustPrice().subtract(sellentrust.getFloatDownPrice());
		    	   java.util.Arrays.sort(array);
		    	   if(initiative.equals("buy")){//买主动
		    		   tradePrice=array[1];//买家当然是价格越低越好
		    		   
		    	   }else if(initiative.equals("sell")){//卖主动
		    		   tradePrice=array[2];//卖家当然然是价格越低高越好
		    	   }
		    	   
		       }
			   ExOrderInfo exOrderInfo=createExOrderInfo(1,buyexEntrust,sellentrust,tradeCount,tradePrice);
			   exOrderInfo.setInOrOutTransaction(initiative.equals("buy")?"sell":"buy");
			   ExOrder   exOrder=createExOrder(exOrderInfo);
			   updatebuyExEntrust(buyexEntrust,sellentrust,exOrderInfo);
			   updatesellExEntrust(buyexEntrust,sellentrust,exOrderInfo);
			   endTransaction(exOrderInfo,exOrder,buyexEntrust,sellentrust);
		//这里调mogo保存TODO
	
	}
	//(2)买家限价，卖家市价
    public void twoCase(ExEntrust buyexEntrust,ExEntrust sellentrust,String initiative){
    	
    	oneCase(buyexEntrust,sellentrust,initiative);
    }
    

  /*  //(5)买家限价，卖家是普通价格优先
    public void fiveCase(ExEntrust buyexEntrust,ExEntrust sellentrust,String initiative){
		   //谁小取谁，交易币的个数
	       BigDecimal tradeCount=buyexEntrust.getSurplusEntrustCount().compareTo(sellentrust.getSurplusEntrustCount())<=0?
	    		   buyexEntrust.getSurplusEntrustCount():sellentrust.getSurplusEntrustCount();
		   if(tradeCount.compareTo(new BigDecimal(0))==0){
	    	  return;
	        }	
	       BigDecimal tradePrice=buyexEntrust.getEntrustPrice();
	       if(tradePrice.compareTo(new BigDecimal(0))==0){
		    	  return;
		      }	
	       //卖家是普通价格优先
	       if(buyexEntrust.getEntrustPrice().compareTo(sellentrust.getEntrustPrice())<=0){
	    		
	    		  if(initiative.equals("buy")){//买主动
		    		   tradePrice=buyexEntrust.getEntrustPrice();//买家当然是价格越低越好
		    	   }else if(initiative.equals("sell")){//卖主动
		    		   tradePrice=sellentrust.getEntrustPrice();//卖家当然然是价格越低高越好
		    	   }
	    	  }
		   ExOrderInfo exOrderInfo=createExOrderInfo(1,buyexEntrust,sellentrust,tradeCount,tradePrice);
		   exOrderInfo.setInOrOutTransaction(initiative.equals("buy")?"sell":"buy");
		   ExOrder   exOrder=createExOrder(exOrderInfo);
		   updatebuyExEntrust(buyexEntrust,sellentrust,exOrderInfo);
		   updatesellExEntrust(buyexEntrust,sellentrust,exOrderInfo);
		   endTransaction(exOrderInfo,exOrder,buyexEntrust,sellentrust);
	//这里调mogo保存TODO

}*/
   //(3)买家市价，卖家限价
    public void threeCase(ExEntrust buyexEntrust,ExEntrust sellentrust){

	       //买家剩余委托金额
		   BigDecimal	buysurplusEntrusMoney=buyexEntrust.getEntrustSum().subtract(buyexEntrust.getTransactionSum());
		   //卖家剩余委托总金额
		   BigDecimal	sellsurplusEntrusMoney=sellentrust.getSurplusEntrustCount().multiply(sellentrust.getEntrustPrice());
		   BigDecimal tradeCount=new BigDecimal("0");
		   if(buysurplusEntrusMoney.compareTo(sellsurplusEntrusMoney)<=0){
				 tradeCount=buysurplusEntrusMoney.divide(sellentrust.getEntrustPrice(),4,BigDecimal.ROUND_DOWN);
				 buyexEntrust.setStatus(2);
		   }	
		   if(buysurplusEntrusMoney.compareTo(sellsurplusEntrusMoney)==1){
				 tradeCount=sellentrust.getSurplusEntrustCount();
			   
		   }
		   if(tradeCount.compareTo(new BigDecimal(0))==0){
		    	  return;
		        }	
		   BigDecimal tradePrice=sellentrust.getEntrustPrice();
		   
		   if(tradePrice.compareTo(new BigDecimal(0))==0){
		    	  return;
		      }	
		   ExOrderInfo exOrderInfo=createExOrderInfo(1,buyexEntrust,sellentrust,tradeCount,tradePrice);
		   exOrderInfo.setInOrOutTransaction("sell");
		   ExOrder   exOrder=createExOrder(exOrderInfo);
		   updatebuyExEntrust(buyexEntrust,sellentrust,exOrderInfo);
		   updatesellExEntrust(buyexEntrust,sellentrust,exOrderInfo);
		   endTransaction(exOrderInfo,exOrder,buyexEntrust,sellentrust);
  	}
    //(4)买家市价，卖家市价
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
			ExOrderInfo exOrderInfo = createExOrderInfo(1, buyexEntrust, sellentrust, tradeCount, tradePrice);
			exOrderInfo.setInOrOutTransaction("sell"); // ????
			ExOrder exOrder = createExOrder(exOrderInfo);
			updatebuyExEntrust(buyexEntrust, sellentrust, exOrderInfo);
			updatesellExEntrust(buyexEntrust, sellentrust, exOrderInfo);
			endTransaction(exOrderInfo, exOrder, buyexEntrust, sellentrust);
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
	public  void   updatebuyExEntrust(ExEntrust buyExEntrust,ExEntrust sellentrust,ExOrderInfo exOrderInfo){
		  
		
		buyExEntrust.setSurplusEntrustCount(buyExEntrust.getSurplusEntrustCount().subtract(exOrderInfo.getTransactionCount()));
		buyExEntrust.setTransactionFee(buyExEntrust.getTransactionFee().add(exOrderInfo.getTransactionBuyFee()));
		buyExEntrust.setTransactionSum(buyExEntrust.getTransactionSum().add(exOrderInfo.getTransactionSum()));
		buyExEntrust.setProcessedPrice(buyExEntrust.getTransactionSum().divide(buyExEntrust.getEntrustCount().subtract(buyExEntrust.getSurplusEntrustCount()),5, BigDecimal.ROUND_HALF_UP));
		
		//如果是市价
		if(buyExEntrust.getEntrustWay().equals(2)){
			
			//市价：委托总金额-交易金额数如果小于购买0.0001个币的时候说明已完成
			  if(!buyExEntrust.getStatus().equals(2)){
				  buyExEntrust.setStatus(1);
			  }
		}else{//是限价，还是普通价格优先都有剩余个数
			//剩余个数为0，说明已完成
			 if(buyExEntrust.getSurplusEntrustCount().compareTo(new BigDecimal(0))<=0){
				 buyExEntrust.setStatus(2);
			 }else{
				 buyExEntrust.setStatus(1);
			 }
		}
	}
	
	
	@Override
	public ExOrderInfo createExOrderInfo(Integer type,ExEntrust buyExEntrust,ExEntrust sellentrust,BigDecimal tradeCount,BigDecimal tradePrice){
		//订单开始详细
		ExOrderInfo exOrderInfo=new ExOrderInfo();
		exOrderInfo.setType(type);
		String transactionNum=IdGenerate.transactionNum(NumConstant.Ex_Order);
        if(!DifCustomer.getIsCommon()){
        	exOrderInfo.setOrderNum("T"+transactionNum.substring(2, transactionNum.length()));
		}else{
			exOrderInfo.setOrderNum(transactionNum);
		}
	
		exOrderInfo.setTransactionCount(tradeCount);
		exOrderInfo.setTransactionPrice(tradePrice);
		exOrderInfo.setTransactionSum(tradePrice.multiply(tradeCount));
		exOrderInfo.setTransactionTime(new Date());
		exOrderInfo.setOrderTimestapm(exOrderInfo.getTransactionTime().getTime());
		exOrderInfo.setTransactionBuyFeeRate(buyExEntrust.getTransactionFeeRate());
		exOrderInfo.setTransactionSellFeeRate(sellentrust.getTransactionFeeRate());
		
		exOrderInfo.setBuyUserName(buyExEntrust.getUserName());
		exOrderInfo.setSellUserName(sellentrust.getUserName());
		//只有普通类型的客户需要交手续费
		exOrderInfo.setTransactionBuyFee(exOrderInfo.getTransactionCount().multiply(exOrderInfo.getTransactionBuyFeeRate()).divide(new BigDecimal("100")));
		exOrderInfo.setTransactionSellFee(exOrderInfo.getTransactionSum().multiply(exOrderInfo.getTransactionSellFeeRate()).divide(new BigDecimal("100")));
		exOrderInfo.setBuyCustomId(buyExEntrust.getCustomerId());
		exOrderInfo.setSellCustomId(sellentrust.getCustomerId());
		exOrderInfo.setWebsite(buyExEntrust.getWebsite());
		exOrderInfo.setCurrencyType(buyExEntrust.getCurrencyType());
		exOrderInfo.setFixPriceCoinCode(buyExEntrust.getFixPriceCoinCode());
		exOrderInfo.setFixPriceType(buyExEntrust.getFixPriceType());
		exOrderInfo.setCoinCode(buyExEntrust.getCoinCode());
		//订单结束详细
		return exOrderInfo;
	}
	
	
	
	
	@Override
	public ExOrder createExOrder(ExOrderInfo exOrderInfo){

		//订单开始
		  ExOrder exOrder=new ExOrder();
		  exOrder.setOrderNum(exOrderInfo.getOrderNum());
		  exOrder.setTransactionTime(exOrderInfo.getTransactionTime());
		  exOrder.setOrderTimestapm(exOrderInfo.getOrderTimestapm());
		  exOrder.setSaasId(exOrderInfo.getSaasId());
		  exOrder.setCurrencyType(exOrderInfo.getCurrencyType());
		  exOrder.setWebsite(exOrderInfo.getWebsite());
		  exOrder.setTransactionCount(exOrderInfo.getTransactionCount());
		  exOrder.setTransactionPrice(exOrderInfo.getTransactionPrice());
		  exOrder.setTransactionSum(exOrderInfo.getTransactionSum());
		  exOrder.setCoinCode(exOrderInfo.getCoinCode());
		  exOrder.setWebsite(exOrderInfo.getWebsite());
		  exOrder.setCurrencyType(exOrderInfo.getCurrencyType());
		  exOrder.setProductName(exOrderInfo.getProductName());
		  exOrder.setInOrOutTransaction(exOrderInfo.getInOrOutTransaction());
		  exOrder.setFixPriceCoinCode(exOrderInfo.getFixPriceCoinCode());
		  exOrder.setFixPriceType(exOrderInfo.getFixPriceType());
		
		//订单结束
		return exOrder;
		
	}
	
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
	/*@Override
	public void matchExtrustToOrder(ExEntrust ex) {
		long start=System.currentTimeMillis();
		try {
			Thread.sleep(400);  
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(ex.getId()+"进来了");
		ExEntrust exEntrust = exEntrustService.get(ex.getId());// 做好用extrustNum
		if(null==exEntrust){
			System.out.println("没来得及保存");
		}
		matchExtrustToOrderFuc(exEntrust);
		long end =System.currentTimeMillis();
		System.out.println(ex.getId()+"出来了11");
		System.out.println(end-start);
	}*/
	/*public  void matchExtrustToOrderFuc(ExEntrust exEntrust){
		
		if (exEntrust.getStatus().equals(0)
				|| exEntrust.getStatus().equals(1)) {
			this.matchExtrustToOrder(exEntrust,
					exEntrust.getSaasId());
		}
	}*/
   public  String getMatchCustomerType(ExEntrust exEntrust){
	  return  DifCustomer.getMatchCustomerType(exEntrust);
   }
	
	@Override
	public void AfterMatchSpecialDeal(ExEntrust exEntrust) {/*
		
		if(exEntrust.getCustomerType().equals(2)){
			if(exEntrust.getStatus().equals(1)){
				exEntrustService.cancelExEntrust(exEntrust.getEntrustNum(),exEntrust.getCustomerId(),"主动发起匹配多余要撤单");
			}
		}
        if(exEntrust.getCustomerType().equals(2)){
        	if(exEntrust.getStatus().equals(1)){
        		exEntrustService.cancelExEntrust(exEntrust.getEntrustNum(),exEntrust.getCustomerId(),"主动发起匹配多余要撤单");
			}
			
		}
	*/}

	/**
	 * 获取一个用户的总买币额
	 */
	@Override
	public BigDecimal getTotalBuyMoney(Long buyCustomId) {
		return ((ExOrderInfoDao)dao).getTotalBuyMoney(buyCustomId);
	}

	
	/**
	  * 获取今天的最后一笔成交价
	  * <p> TODO</p>
	  * @author:         Zhang Lei
	  * @param:    @param buyCustomId
	  * @param:    @return
	  * @return: BigDecimal 
	  * @Date :          2017年3月9日 上午10:24:50   
	  * @throws:
	  */
	@Override
	public ExOrderInfo exAveragePrice(String coinCode,String fixPriceCoinCode) {
		return ((ExOrderInfoDao)dao).exAveragePrice(coinCode,fixPriceCoinCode);
	}
	/**
	 * 获取今天的最后一笔成交价
	 * <p> TODO</p>
	 * @author:         Zhang Lei
	 * @param:    @param buyCustomId
	 * @param:    @return
	 * @return: BigDecimal 
	 * @Date :          2017年3月9日 上午10:24:50   
	 * @throws:
	 */
	@Override
	public ExOrderInfo getAveragePriceYesterday(String coinCode) {
		return ((ExOrderInfoDao)dao).getAveragePriceYesterday(coinCode);
	}

	@Override
	public boolean removeExentrustOneDay(ExEntrust buyEntrust,ExEntrust sellEntrust) {
		return false;
	}

	@Override
	public FrontPage findFrontPage(Map<String, String> params) {

		
		Page page = PageFactory.getPage(params);
		
		//查询方法
		List<Order> list = ((ExOrderInfoDao)dao).findFrontPageBySql(params);
		
		return new FrontPage(list, page.getTotal(), page.getPages(), page.getPageSize());
	
		
	
		
	}

	@Override
	public void timingCulAtferMoney() {
	   /*QueryFilter qf=new QueryFilter(ExOrderInfo.class);
	   qf.addFilter("isCulAtferMoney=", 0);
	//   qf.addFilter("type=", 1);
	   List<ExOrderInfo> list=exOrderInfoService.find(qf);
	   for(ExOrderInfo l:list){

			System.out.println("接收到交易佣金    订单号为   "+ l.getOrderNum()+ " ----- "+DateUtil.getNewDate());
			
			// 保存我方手续费
			AppOurAccountService appOurAccountService =(AppOurAccountService)ContextUtil.getBean("appOurAccountService");
			appOurAccountService.tradeIncomeFee(l.getOrderNum());
			
			
			RemoteAppTradeFactorageService remoteAppTradeFactorageService =(RemoteAppTradeFactorageService)ContextUtil.getBean("remoteAppTradeFactorageService");
			// 保存交易单佣金
			Boolean boolean1 = remoteAppTradeFactorageService.saveTrade(l.getOrderNum());
			//Boolean boolean1 = remoteAppTradeFactorageService.saveTradeFactoryge(l.getOrderNum(), 2);
			if(boolean1){
				System.out.println("交易佣金保存成功  "+l.getOrderNum()+" ---- "+DateUtil.getNewDate());
				l.setIsCulAtferMoney(1);

				exOrderInfoService.update(l);
			}else{
				l.setIsCulAtferMoney(1);
				exOrderInfoService.update(l);
				System.out.println("交易佣金保存失败  "+l.getOrderNum()+" ---- "+DateUtil.getNewDate());
			}
		
		   
	   }	*/
	}
	
	/**
	 * 后台
	 */
	public FrontPage selectFee(Map<String, String> params) {
		Page page = PageFactory.getPage(params);
		//查询方法
		List<ExOrderInfo> list = ((ExOrderInfoDao)dao).selectFee(params);
		return new FrontPage(list, page.getTotal(), page.getPages(), page.getPageSize());
	}
	
	/**
	 * 前台后台
	 */
	public FrontPage frontselectFee(Map<String, String> params) {
		Page page = PageFactory.getPage(params);
		//查询方法
		List<Order> list = ((ExOrderInfoDao)dao).frontselectFee(params);
		return new FrontPage(list, page.getTotal(), page.getPages(), page.getPageSize());
	}
	
	public List<BigDecimal> yesterdayPrice(){
		return ((ExOrderInfoDao)dao).yesterdayPrice();
	}

	@Override
	public int selectTransactionCount(String username) {
		// TODO Auto-generated method stub
		return ((ExOrderInfoDao)dao).selectTransactionCount(username);
	}

	@Override
	public List<ExOrderInfo> selectTransaction(String username) {
		// TODO Auto-generated method stub
		return ((ExOrderInfoDao)dao).selectTransaction(username);
	}

	@Override
	public PageResult findPageBySql(QueryFilter filter) {

		
		//----------------------分页查询头部外壳------------------------------
		//创建PageResult对象
		PageResult pageResult = new PageResult();
		Page<AppCustomer> page = null;
		if(filter.getPageSize().compareTo(Integer.valueOf(-1))==0){
			//pageSize = -1 时  
			//pageHelper传pageSize参数传0查询全部
			page= PageHelper.startPage(filter.getPage(), 0);
		}else{
			page = PageHelper.startPage(filter.getPage(), filter.getPageSize());
		}
		//----------------------分页查询头部外壳------------------------------
		
		//----------------------查询开始------------------------------
		
		String coinCode = filter.getRequest().getParameter("coinCode");
		String fixPriceCoinCode = filter.getRequest().getParameter("fixPriceCoinCode");
		String orderNum = filter.getRequest().getParameter("orderNum");
		String source = filter.getRequest().getParameter("source");
		
		String transactionCount_LT = (String) filter.getRequest().getParameter("transactionCount_LT");
		String transactionCount_GT = (String) filter.getRequest().getParameter("transactionCount_GT");
		String buyEmail=filter.getRequest().getParameter("buyEmail");
		String buyMobilePhone = filter.getRequest().getParameter("buyMobilePhone");
		String buyEntrustNum = filter.getRequest().getParameter("buyEntrustNum");
		
		String sellEmail=filter.getRequest().getParameter("sellEmail");
		String sellMobilePhone = filter.getRequest().getParameter("sellMobilePhone");
		String sellEntrustNum = filter.getRequest().getParameter("sellEntrustNum");
		
		String transactionPrice_LT = filter.getRequest().getParameter("transactionPrice_LT");
		String transactionSum_LT = filter.getRequest().getParameter("transactionSum_LT");
		String transactionTime_LT = filter.getRequest().getParameter("transactionTime_LT");
		
		String transactionPrice_GT = filter.getRequest().getParameter("transactionPrice_GT");
		String transactionSum_GT = filter.getRequest().getParameter("transactionSum_GT");
		String transactionTime_GT = filter.getRequest().getParameter("transactionTime_GT");
		
		Map<String,Object> map = new HashMap<>();

		if(!StringUtils.isEmpty(coinCode)){
			map.put("coinCode", coinCode+"%");
		}
		if(!StringUtils.isEmpty(fixPriceCoinCode)){
			map.put("fixPriceCoinCode", fixPriceCoinCode+"%");
		}
		if(!StringUtils.isEmpty(orderNum)){
			map.put("orderNum", orderNum+"%");
		}
		if(!StringUtils.isEmpty(source)){
			map.put("source", source);
		}
		if(!StringUtils.isEmpty(transactionCount_LT)){
			map.put("transactionCount_LT", transactionCount_LT);
		}
		if(!StringUtils.isEmpty(transactionCount_GT)){
			map.put("transactionCount_GT", transactionCount_GT);
		}
		if(!StringUtils.isEmpty(buyEmail)){
			map.put("buyEmail", buyEmail+"%");
		}
		if(!StringUtils.isEmpty(buyMobilePhone)){
			map.put("buyMobilePhone", buyMobilePhone+"%");
		}
		if(!StringUtils.isEmpty(buyEntrustNum)){
			map.put("buyEntrustNum", buyEntrustNum+"%");
		}
		if(!StringUtils.isEmpty(sellEmail)){
			map.put("sellEmail", sellEmail+"%");
		}
		if(!StringUtils.isEmpty(sellMobilePhone)){
			map.put("sellMobilePhone", sellMobilePhone+"%");
		}
		if(!StringUtils.isEmpty(sellEntrustNum)){
			map.put("sellEntrustNum", sellEntrustNum+"%");
		}
		
		if(!StringUtils.isEmpty(transactionPrice_LT)){
			map.put("transactionPrice_LT",transactionPrice_LT);
		}
		if(!StringUtils.isEmpty(transactionSum_LT)){
			map.put("transactionSum_LT",transactionSum_LT);
		}
		if(!StringUtils.isEmpty(transactionTime_LT)){
			map.put("transactionTime_LT",transactionTime_LT);
		}
		
		if(!StringUtils.isEmpty(transactionPrice_GT)){
			map.put("transactionPrice_GT",transactionPrice_GT);
		}
		if(!StringUtils.isEmpty(transactionSum_GT)){
			map.put("transactionSum_GT",transactionSum_GT);
		}
		if(!StringUtils.isEmpty(transactionTime_GT)){
			map.put("transactionTime_GT",transactionTime_GT);
		}
		
		
		((ExOrderInfoDao)dao).findPageBySql(map);
		//----------------------查询结束------------------------------
		
		//----------------------分页查询底部外壳------------------------------
		//设置分页数据
		pageResult.setRows(page.getResult());
		//设置总记录数
		pageResult.setRecordsTotal(page.getTotal());
		pageResult.setDraw(filter.getDraw());
		pageResult.setPage(filter.getPage());
		pageResult.setPageSize(filter.getPageSize());
		//----------------------分页查询底部外壳------------------------------
		
		return pageResult;
	
	}

	@Override
	public PageResult findPageBySqlList(QueryFilter filter) {
		//----------------------分页查询头部外壳------------------------------
		// 分页参数处理
		String startStr = filter.getRequest().getParameter("start");
		String lengthStr = filter.getRequest().getParameter("length");
		Integer startpage = Integer.valueOf(startStr);
		Integer lengthpage = Integer.valueOf(lengthStr);
		if( lengthpage == null || lengthpage == 0 ){
			lengthpage = 10;
		}
		startpage = startpage/lengthpage;
		// 分页参数处理结束
		
		//创建PageResult对象
		PageResult pageResult = new PageResult();
		pageResult.setPage(startpage);
		pageResult.setPageSize(lengthpage);
		
		Map<String,Object> map = new HashMap<String,Object>();
	    Integer start = startpage * lengthpage;
		map.put("start", start);
		map.put("end", lengthpage);
		
		//----------------------查询开始------------------------------
		
		String coinCode = filter.getRequest().getParameter("coinCode");
		String fixPriceCoinCode = filter.getRequest().getParameter("fixPriceCoinCode");
		String orderNum = filter.getRequest().getParameter("orderNum");
		String source = filter.getRequest().getParameter("source");
		
		String transactionCount_LT = (String) filter.getRequest().getParameter("transactionCount_LT");
		String transactionCount_GT = (String) filter.getRequest().getParameter("transactionCount_GT");
		String buyEmail=filter.getRequest().getParameter("buyEmail");
		String buyMobilePhone = filter.getRequest().getParameter("buyMobilePhone");
		String buyEntrustNum = filter.getRequest().getParameter("buyEntrustNum");
		
		String sellEmail=filter.getRequest().getParameter("sellEmail");
		String sellMobilePhone = filter.getRequest().getParameter("sellMobilePhone");
		String sellEntrustNum = filter.getRequest().getParameter("sellEntrustNum");
		
		String transactionPrice_LT = filter.getRequest().getParameter("transactionPrice_LT");
		String transactionSum_LT = filter.getRequest().getParameter("transactionSum_LT");
		String transactionTime_LT = filter.getRequest().getParameter("transactionTime_LT");
		
		String transactionPrice_GT = filter.getRequest().getParameter("transactionPrice_GT");
		String transactionSum_GT = filter.getRequest().getParameter("transactionSum_GT");
		String transactionTime_GT = filter.getRequest().getParameter("transactionTime_GT");
		
		if(!StringUtils.isEmpty(coinCode)){
			map.put("coinCode", coinCode+"%");
		}
		if(!StringUtils.isEmpty(fixPriceCoinCode)){
			map.put("fixPriceCoinCode", fixPriceCoinCode+"%");
		}
		if(!StringUtils.isEmpty(orderNum)){
			map.put("orderNum", orderNum+"%");
		}
		if(!StringUtils.isEmpty(source)){
			map.put("source", source);
		}
		if(!StringUtils.isEmpty(transactionCount_LT)){
			map.put("transactionCount_LT", transactionCount_LT);
		}
		if(!StringUtils.isEmpty(transactionCount_GT)){
			map.put("transactionCount_GT", transactionCount_GT);
		}
		if(!StringUtils.isEmpty(buyEntrustNum)){
			map.put("buyEntrustNum", buyEntrustNum+"%");
		}
		if(!StringUtils.isEmpty(sellEntrustNum)){
			map.put("sellEntrustNum", sellEntrustNum+"%");
		}
		
		if(!StringUtils.isEmpty(transactionPrice_LT)){
			map.put("transactionPrice_LT",transactionPrice_LT);
		}
		if(!StringUtils.isEmpty(transactionSum_LT)){
			map.put("transactionSum_LT",transactionSum_LT);
		}
		if(!StringUtils.isEmpty(transactionTime_LT)){
			map.put("transactionTime_LT",transactionTime_LT);
		}
		
		if(!StringUtils.isEmpty(transactionPrice_GT)){
			map.put("transactionPrice_GT",transactionPrice_GT);
		}
		if(!StringUtils.isEmpty(transactionSum_GT)){
			map.put("transactionSum_GT",transactionSum_GT);
		}
		if(!StringUtils.isEmpty(transactionTime_GT)){
			map.put("transactionTime_GT",transactionTime_GT);
		}
		
		Map<String,Object> mapbuy = new HashMap<String,Object>();
		if(!StringUtils.isEmpty(buyEmail)){
			mapbuy.put("email", buyEmail);
		}
		if(!StringUtils.isEmpty(buyMobilePhone)){
			mapbuy.put("mobilePhone", buyMobilePhone);
		}
		if(mapbuy.size()>0){
			List<String> listpersoninfo = ((ExOrderInfoDao)dao).findPersonInfoByCondition(mapbuy);
			if(listpersoninfo.size()>0){
				map.put("buycustomerId", listpersoninfo);
			}else{
				List<ExOrderInfo> list = new ArrayList<ExOrderInfo>();  
				//设置分页数据
				pageResult.setRows(list);
				//设置总记录数
				pageResult.setRecordsTotal(Long.valueOf("0"));
				return pageResult;
			}
		}
		
		Map<String,Object> mapsell = new HashMap<String,Object>();
		if(!StringUtils.isEmpty(sellEmail)){
			mapsell.put("email", sellEmail);
		}
		if(!StringUtils.isEmpty(sellMobilePhone)){
			mapsell.put("mobilePhone", sellMobilePhone);
		}
		if(mapsell.size()>0){
			List<String> listpersoninfo = ((ExOrderInfoDao)dao).findPersonInfoByCondition(mapsell);
			if(listpersoninfo.size()>0){
				map.put("sellcustomerId", listpersoninfo);
			}else{
				List<ExOrderInfo> list = new ArrayList<ExOrderInfo>();  
				//设置分页数据
				pageResult.setRows(list);
				//设置总记录数
				pageResult.setRecordsTotal(Long.valueOf("0"));
				return pageResult;
			}
		}
		
		Long count = ((ExOrderInfoDao)dao).findPageBySqlCount(map);
		List<ExOrderInfo>  list = ((ExOrderInfoDao)dao).findPageBySqlList(map);
		//----------------------查询结束------------------------------
		
		//----------------------分页查询底部外壳------------------------------
		//设置分页数据
		pageResult.setRows(list);
		//设置总记录数
		pageResult.setRecordsTotal(count);
	
		//----------------------分页查询底部外壳------------------------------
		
		return pageResult;
	}
}
