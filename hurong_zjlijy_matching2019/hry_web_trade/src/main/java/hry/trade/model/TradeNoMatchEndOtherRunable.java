package hry.trade.model;

import hry.redis.common.utils.RedisService;
import hry.redis.common.utils.RedisTradeService;
import hry.trade.redis.model.EntrustByUser;
import hry.trade.redis.model.EntrustTrade;
import hry.util.sys.ContextUtil;

public class TradeNoMatchEndOtherRunable implements Runnable {
	
	
	private EntrustTrade entrust;
	public TradeNoMatchEndOtherRunable(EntrustTrade entrust){
		this.entrust=entrust;
	}
	@Override
	public void run() {
		
		//通知定时器需要标记需要深度计算
		RedisTradeService redisTradeService = (RedisTradeService) ContextUtil.getBean("redisTradeService");
		redisTradeService.save(TradeRedis.getEntrustTimeFlag(entrust.getCoinCode(), entrust.getFixPriceCoinCode()),"1");
		
		RedisService redisService = (RedisService) ContextUtil.getBean("redisService");
		String key = "RedisDB:" +EntrustByUser.class.getName().replace(".", ":")+ ":" +entrust.getCustomerId().toString();
		
	//	TradeRedis.putEntrustByUser(this.entrust);
		Boolean a=true;
		int i=0;
	 //   while(a){
	        Boolean flag=redisService.lock(key);
            if(a){
            	TradeRedis.putEntrustByUser(this.entrust);
        //		redisService.unLock(key);
        		System.out.println("redisService.unLock(key)");
        		a=false;
          //      break;
            }/*else{
                try {
					Thread.sleep(300);
					System.out.println("Thread.sleep(300)3i="+i);
					i++;
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }*/
     //   }
	}
	
}
