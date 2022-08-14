package hry.trade.model;

import hry.redis.common.utils.RedisService;
import hry.redis.common.utils.RedisTradeService;
import hry.trade.entrust.model.ExOrderInfo;
import hry.trade.redis.model.EntrustByUser;
import hry.trade.redis.model.EntrustTrade;
import hry.util.sys.ContextUtil;

import java.math.BigDecimal;
import java.util.List;

public class TradeMatchOneEndOtherRunable implements Runnable {
	
	
	private EntrustTrade exEntrust;
	private List<EntrustTrade> listed;
	private BigDecimal matchonePrice;
	private List<ExOrderInfo> eoinfolists;
	public TradeMatchOneEndOtherRunable(EntrustTrade exEntrust,
			 List<EntrustTrade> listed, BigDecimal matchonePrice,List<ExOrderInfo> eoinfolists ){
		this.exEntrust=exEntrust;
		this.listed=listed;
		this.matchonePrice=matchonePrice;
		this.eoinfolists=eoinfolists;
	}
	@Override
	public void run() {
		
		
		//通知定时器需要标记需要深度计算
		RedisTradeService redisTradeService = (RedisTradeService) ContextUtil.getBean("redisTradeService");
		redisTradeService.save(TradeRedis.getEntrustTimeFlag(exEntrust.getCoinCode(), exEntrust.getFixPriceCoinCode()),"1");
	
		RedisService redisService = (RedisService) ContextUtil.getBean("redisService");
		String key = "RedisDB:" +EntrustByUser.class.getName().replace(".", ":")+ ":" +exEntrust.getCustomerId().toString();
		for (EntrustTrade entrust : listed) {
			TradeRedis.putEntrustByUser(entrust);
		}
       /* Boolean a=true;
        int i=0;
        while(a){
        	 Boolean flag=redisService.lock(key);
            if(flag){
            	for (EntrustTrade entrust : listed) {
        			TradeRedis.putEntrustByUser(entrust);
        		}
        		redisService.unLock(key);
        		System.out.println("redisService.unLock(key)111");
        		a=false;
                break;
            }else{
                try {
					Thread.sleep(300);
					System.out.println("Thread.sleep(300)2i="+i);
				   i++;
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        }*/
	}
	
}
