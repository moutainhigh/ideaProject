/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Wu Shuiming
 * @version:      V1.0 
 * @Date:        2016年3月24日 下午2:04:29
 */

package hry.trade.entrust.service.impl;

import com.alibaba.fastjson.JSON;
import hry.core.thread.ThreadPool;
import hry.redis.common.utils.RedisTradeService;
import hry.trade.entrust.service.ExEntrustService;
import hry.trade.entrust.service.ExOrderService;
import hry.trade.entrust.service.WebSocketScheduleService;
import hry.trade.model.ExEntrutKlineRunable;
import hry.trade.model.TradeRedis;
import hry.util.sys.ContextUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * <p> TODO</p>
 * @author:         Gao Mimi 
 * @Date :          2016年4月12日 下午4:45:50 
 */
@Service("webSocketScheduleService")
public class WebSocketScheduleServiceImpl implements WebSocketScheduleService {
	@Resource
	private ExOrderService exOrderService;
	@Resource
	private ExEntrustService entrustService;
	
	//交易大厅主要数据
	@Override
	public void pushMarket() {
		Integer i=0;
		Integer a=0;
			String productListStr = TradeRedis.getStringData("cn:productFixList");
			if (!StringUtils.isEmpty(productListStr)) {
				List<String> productList = JSON.parseArray(productListStr, String.class);
				for(String coinCodetwo : productList){
					
					//通知定时器需要标记需要深度计算
					RedisTradeService redisTradeService = (RedisTradeService) ContextUtil.getBean("redisTradeService");
				    i++;
					String[] coinCodeArr=coinCodetwo.split("_");
					String coinCode=coinCodeArr[0];
					String fixPriceCoinCode=coinCodeArr[1];
					String flagstrkey=TradeRedis.getEntrustTimeFlag(coinCode,fixPriceCoinCode);
					String flag=redisTradeService.get(flagstrkey);
				
					if(null!=flag&&flag.equals("1")){
						 a++;
						String header= TradeRedis.getHeader(coinCode, fixPriceCoinCode, null);
						ExEntrutKlineRunable exEntrutKlineRunable = new ExEntrutKlineRunable(coinCode,fixPriceCoinCode,header);
						ThreadPool.exe(exEntrutKlineRunable);
						
						redisTradeService.save(TradeRedis.getEntrustTimeFlag(coinCode,fixPriceCoinCode),"0");

					}
					
				
				
					/*	// 委托-----行情板块
						PushData.pushEntrusMarket(entrustService.getExEntrustChangeMarket(coinCode,fixPriceCoinCode, 18),header);
						// 成交订单
						PushData.pushNewListRecordMarketDesc(exOrderService.getNewListMarket(header, "desc"), header);
				*/		
					}
			}
		
		long end = System.currentTimeMillis();
	//	LogFactory.info("定时委单深度个数i：" + i.toString());
	//	LogFactory.info("定时委单深度个数a：" + a.toString());
	}
	//交易大厅深度数据
		@Override
		public void pushEntrusDephMarket(){/*
			Map<String, String> mapLoadWeb = PropertiesUtils.getLoadWeb();
			for (String Website : mapLoadWeb.keySet()) {
				String productListStr = TradeRedis.getStringData(Website + ":productFixList");
				if (!StringUtils.isEmpty(productListStr)) {
					List<String> productList = JSON.parseArray(productListStr, String.class);
					for(String coinCodetwo : productList){
					
						String[] coinCodeArr=coinCodetwo.split("_");
						String coinCode=coinCodeArr[0];
						String fixPriceCoinCode=coinCodeArr[1];
						String header= TradeRedis.getHeader(coinCode, fixPriceCoinCode, null);
						for(Integer j=1;j<=5;j++){
							BigDecimal depth1=new BigDecimal(j);
							PushData.pushEntrusDephMarket(entrustService.getExEntrustChangeDephMarket(coinCode,fixPriceCoinCode, 5,depth1),header,j.toString());
						}
			    }
			  }
			}
		*/}
	 
}