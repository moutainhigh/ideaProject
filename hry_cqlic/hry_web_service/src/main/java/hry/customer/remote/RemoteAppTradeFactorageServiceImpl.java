/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Liu Shilei
 * @version:      V1.0 
 * @Date:        2016年3月28日 下午12:19:16
 */
package hry.customer.remote;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;




import hry.bean.JsonResult;
import hry.customer.agents.service.AppTradeFactorageService;
import hry.customer.agents.service.CommissionDetailService;
import hry.customer.commend.service.AppCommendTradeService;
import hry.customer.person.model.AppPersonInfo;
import hry.customer.person.service.AppPersonInfoService;
import hry.exchange.subscription.model.ExSubscriptionPlan;
import hry.manage.remote.RemoteManageService;
import hry.redis.common.utils.RedisService;
import hry.trade.entrust.model.ExOrderInfo;
import hry.trade.redis.model.EntrustTrade;


import hry.trade.websoketContext.model.MarketDepths;
import hry.util.SpringUtil;

import javax.annotation.Resource;

import org.springframework.util.StringUtils;

import util.SortListUtil;

import com.alibaba.fastjson.JSON;


/**
 * <p> TODO</p>
 * @author:         Liu Shilei 
 * @Date :          2016年3月28日 下午12:19:16 
 */
public class RemoteAppTradeFactorageServiceImpl implements  RemoteAppTradeFactorageService {
	
	@Resource(name="appTradeFactorageService")
	public AppTradeFactorageService appTradeFactorageService;
	
	@Resource(name="commissionDetailService")
	public CommissionDetailService commissionDetailService;
	
	@Resource(name="appCommendTradeService")
	public AppCommendTradeService appCommendTradeService;
	
	@Resource(name="appPersonInfoService")
	public AppPersonInfoService appPersonInfoService;
	
	@Resource
	public RedisService redisService;
	/**
	 * 
	 * 通过订单号 保存订单产生的手续费所产生的费率  type 参数表示以第二个费率类型来保存数据
	 * 
	 */
	@Override
	public Boolean saveTradeFactoryge(String orderNum,Integer type){
		Boolean boolean1 = appTradeFactorageService.dealCommission(orderNum,type);
		return boolean1;
	}
	
	
	public Boolean saveTrade(String orderNum){
		Boolean boolean1 = appCommendTradeService.dealCommission(orderNum);
		return boolean1;
	}


	/**
	 * 保存订单的明细  type 1 提现  2 是交易 
	 */
	@Override
	public Boolean saveTrackOrder(String orderNum,int type){
		Boolean boolean1 = appTradeFactorageService.dealCommissionByTraction(orderNum,type);
		return boolean1;
	}
	
	/**
	 * 保存认购返佣金额
	 */
	@Override
	public Boolean dealCommissionBySubscription(ExSubscriptionPlan plan,Long customerId,
			BigDecimal buyTotalNum,String transactionNum){ 
		return appTradeFactorageService.dealCommissionBySubscription(plan, customerId, buyTotalNum,transactionNum);
	}


	@Override
	public JsonResult buyBackOrder(ExOrderInfo exOrderInfo,BigDecimal buyBackProportion) {
		JsonResult jsonResult = new JsonResult();
		if(exOrderInfo != null ){
			Long sellCustomId = exOrderInfo.getSellCustomId();//卖单用户
			AppPersonInfo sellAppPersonInfo = appPersonInfoService.getByCustomerId(sellCustomId);
			BigDecimal transactionCount = exOrderInfo.getTransactionCount();//成交量
			BigDecimal transactionPrice = exOrderInfo.getTransactionPrice();//成交单价
			 buyBackProportion = buyBackProportion.divide(new BigDecimal(100));
			 BigDecimal entrustCount = transactionCount.multiply(buyBackProportion);//回购数量
			 
			 //获取当前委托卖1价钱
			String pushEntrusMarket = redisService.get(exOrderInfo.getCoinCode() +"_"+exOrderInfo.getFixPriceCoinCode()+ ":pushEntrusMarket");
			 if (!StringUtils.isEmpty(pushEntrusMarket)) {
	                // 获得委托数据
	                MarketDepths marketDepths = JSON.parseObject(pushEntrusMarket, MarketDepths.class);
	                Map<String, List<BigDecimal[]>> depths = marketDepths.getDepths();
	                if (depths != null && !depths.isEmpty()) {
	                    List<BigDecimal[]> askslist = depths.get("asks");
	                    SortListUtil<BigDecimal[]> sortList = new SortListUtil<BigDecimal[]>();
	                    sortList.SortBigDecimalArray(askslist, 0, null);

	                    if (askslist != null && !askslist.isEmpty()) {
	                    	transactionPrice = askslist.get(0)[0];
	                       /* aprices = new BigDecimal[askslist.size()];
	                        aamounts = new BigDecimal[askslist.size()];
	                        alevels = new BigDecimal[askslist.size()];

	                        for (int i = 0; i < askslist.size(); i++) {
	                            BigDecimal[] b = askslist.get(i);
	                            aprices[i] = b[0];
	                            aamounts[i] = b[1];
	                            alevels[i] = BigDecimal.ONE;
	                        }*/
	                    }
			       }
				}
			 
			 EntrustTrade exEntrust = new EntrustTrade();
				//用户id
				exEntrust.setCustomerId(sellCustomId);
				exEntrust.setTrueName(sellAppPersonInfo.getTrueName());
				exEntrust.setCoinCode(exOrderInfo.getCoinCode());
				exEntrust.setFixPriceCoinCode(exOrderInfo.getFixPriceCoinCode());
				exEntrust.setEntrustWay(1);//限价
				exEntrust.setType(1);//买单
				exEntrust.setSource(8);//回购下单
				exEntrust.setUserName(exOrderInfo.getBuyUserName());
				exEntrust.setSurName(sellAppPersonInfo.getSurname());
				exEntrust.setIsOpenCoinFee(sellAppPersonInfo.getIsOpenCoinFee());
				exEntrust.setIsType(0);
				exEntrust.setEntrustPrice(transactionPrice);
				exEntrust.setEntrustCount(entrustCount);
				RemoteManageService remoteManageService = SpringUtil.getBean("remoteManageService");
				// 委托业务
				String[] relt = remoteManageService.addEntrust(exEntrust);
				if (relt[0].equals("8888")) {
					jsonResult.setSuccess(true);
					jsonResult.setMsg("回购下单成功");
				} else {
					jsonResult.setSuccess(false);
					jsonResult.setMsg(relt[1]);
				}
		}else{
			jsonResult.setMsg("订单不能为空");
			jsonResult.setSuccess(false);
		}
		return jsonResult;
	}
	
	
}   
