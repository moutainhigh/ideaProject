package hry.trade.model;

import hry.redis.common.utils.RedisService;
import hry.redis.common.utils.RedisTradeService;
import hry.trade.redis.model.EntrustByUser;
import hry.trade.redis.model.EntrustTrade;
import hry.util.sys.ContextUtil;

public class TradeCancelEntrustOtherRunable implements Runnable {
	
	
	private EntrustTrade exEntrust;
	public TradeCancelEntrustOtherRunable(EntrustTrade exEntrust){
		this.exEntrust=exEntrust;
	}
	@Override
	public void run() {
		//通知定时器需要标记需要深度计算
		RedisTradeService redisTradeService = (RedisTradeService) ContextUtil.getBean("redisTradeService");
		redisTradeService.save(TradeRedis.getEntrustTimeFlag(exEntrust.getCoinCode(), exEntrust.getFixPriceCoinCode()),"1");

		RedisService redisService = (RedisService) ContextUtil.getBean("redisService");
		String key = "RedisDB:" +EntrustByUser.class.getName().replace(".", ":")+ ":" +exEntrust.getCustomerId().toString();
		TradeRedis.putEntrustByUser(exEntrust); 
		/*int i=0;
		Boolean a=true;
        while(a){
        	Boolean flag=redisService.lock(key);
            if(flag){
            	TradeRedis.putEntrustByUser(exEntrust);
        		redisService.unLock(key);
        		System.out.println("redisService.unLock(key)");
        		a=false;
                break;
            }else{
                try {
					Thread.sleep(300);
					System.out.println("Thread.sleep(300)1i="+i);
                     i++;
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        }*/
	
			
	}
	
	
}
