package hry.app.thread;

import hry.front.redis.model.UserRedis;
import hry.licqb.remote.RemoteAwardService;
import hry.manage.remote.RemoteManageService;
import hry.redis.common.dao.RedisUtil;
import hry.trade.redis.model.EntrustByUser;
import hry.trade.redis.model.EntrustTrade;
import hry.util.common.SpringUtil;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Map;

/**
 * 登录后更新用户账户id保存到redis中	
 * @author CHINA_LSL
 *
 */
public class UserRedisRunnable implements Runnable{
	
	private final Logger log = Logger.getLogger(UserRedisRunnable.class);
	
	private String id;
	
	
	private UserRedisRunnable () {
	}
	
	public UserRedisRunnable (String id){
		this.id = id;
	}
	
	@Override
	public void run() {
		UserRedis userRedis = new UserRedis();
		userRedis.setId(id);
		
		RemoteManageService remoteManageService = SpringUtil.getBean("remoteManageService");
		Map<String, Long> map = remoteManageService.findAllAccountId(id);
		userRedis.setAccountId(map.get("accountId")==null?null:map.get("accountId"));
		//获取完后，移除
		map.remove("accountId");
		userRedis.setDmAccountId(map);
		
		log.info("初始化账户id到redis------------->>>");
		RedisUtil<UserRedis>  redisUtil = new RedisUtil<UserRedis>(UserRedis.class);
		redisUtil.put(userRedis, userRedis.getId());
         //缓存委托历史记录和当前记录
		RedisUtil<EntrustByUser>  redisUtilEntrustByUser = new RedisUtil<EntrustByUser>(EntrustByUser.class);
		List<Map<String, List<EntrustTrade>>> listml=remoteManageService.findExEntrustBycust(Long.valueOf(id));
		EntrustByUser ebu = new EntrustByUser();
		ebu.setCustomerId(Long.valueOf(id));
		ebu.setEntrustedmap(listml.get(0));
		ebu.setEntrustingmap(listml.get(1));
		redisUtilEntrustByUser.put(ebu, id);

		// 加载收益统计保存
		RemoteAwardService awardService = SpringUtil.getBean("remoteAwardService");
		awardService.awardStatistics(Long.parseLong(id));
	}

}
