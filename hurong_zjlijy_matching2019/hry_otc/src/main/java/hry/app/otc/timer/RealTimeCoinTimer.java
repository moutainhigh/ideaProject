package hry.app.otc.timer;//package hry.app.otc;

import hry.otc.manage.remote.api.RemoteNewAdvertisementService;
import hry.redis.common.utils.RedisService;
import hry.util.sys.ContextUtil;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Set;

@Component
@EnableScheduling
public class RealTimeCoinTimer {
	@Resource
	private RedisService redisService;
	static Integer offset = 0;
    static Integer limit=10;
	/**
	 * 30秒钟跑一次取消订单
	 */
	@Scheduled(fixedRate = 30000)
	public void cancelTransaction() {
		findPageWait();
	}

	public static void main(String[] args) {
		Jedis j = new Jedis("192.168.232.128",6379);
		j.auth("Credit2016Admin");

		/*Set<Tuple> tuples = j.zrangeWithScores("BCH_ETH:newklinedata:TransactionOrder_BCH_ETH_5", 0, 0);
		for (Tuple tuple : tuples) {
			System.out.println(tuple.getElement() + ":" + new BigDecimal(tuple.getScore()).toString());
		}
		System.out.println(tuples);*/
		Long mykey = j.setnx("mykey", 60 + " 111");
		System.out.println(mykey);
		String mykey1 = j.get("mykey");
		System.out.println(mykey1);
		j.set("mykey", "3");

	}

	private void findPageWait() {
		//获取索引为0的第一个节点，如果条件不满足，就没必要往后循环了，否则就循环处理
		int start = 0;
		while (true){
			Set<Tuple> tuples = redisService.zrangeWithScores("otc:tradeNum", start, start);
			if(tuples.size() > 0){
				String tradeNum = "";
				String score = "";
				for (Tuple tuple : tuples) {
					tradeNum = tuple.getElement().toString();
					score = new BigDecimal(tuple.getScore()).toString();
				}

				if(System.currentTimeMillis() / 1000 > (Long.valueOf(score))){ // 过期
					RemoteNewAdvertisementService remoteNewAdvertisementService = (RemoteNewAdvertisementService)ContextUtil.getBean("remoteNewAdvertisementService");
					remoteNewAdvertisementService.recoveryReleaseAdvertisement(tradeNum,0L, 222222);// 取消订单

					// 删除该订单
					redisService.zrem("otc:tradeNum", tradeNum);
				}else{
					break;
				}

				start++;
			}
			break;
		}
	}


}
