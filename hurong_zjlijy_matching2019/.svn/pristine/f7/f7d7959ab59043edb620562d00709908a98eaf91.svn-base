/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Liu Shilei
 * @version:      V1.0 
 * @Date:        2016年4月1日 上午11:18:14
 */
package hry.trade.check.service.impl;


import hry.redis.common.utils.RedisService;
import hry.trade.check.service.CheckTradeService;
import hry.trade.entrust.service.TradeService;
import hry.util.sys.ContextUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("checkTradeService")
public class CheckTradeServiceImpl implements  CheckTradeService {
	
	private final Logger log = Logger.getLogger(CheckTradeServiceImpl.class);
	@Resource
	public RedisService redisService;
	@Override
	public void testTradeIsLiving() {
		Boolean flag=true;
		try{
		   TradeService tradeService = (TradeService) ContextUtil.getBean("tradeService");
		   if(null==tradeService){
				flag=false;
		   }
		 
		}catch(Exception e){
			
			flag=false;
		}
		
		if(flag){
			redisService.save("projectException:tradeIsLiving","1",55);
		}
		
	}
	

	
	
	
	
	
	
	
	

}
