package hry.util;

import hry.front.redis.model.UserRedis;
import hry.trade.redis.model.ExDigitalmoneyAccountRedis;

public class UserRedisUtils {


	/**
	 * 获得redis币账户
	 * @return
	 */
	public static ExDigitalmoneyAccountRedis getAccount(String exAccountId, String coinCode){
		if(exAccountId!=null&&!"".equals(exAccountId) ){
			RedisUtil<ExDigitalmoneyAccountRedis>  a = new RedisUtil<ExDigitalmoneyAccountRedis>(ExDigitalmoneyAccountRedis.class);
			ExDigitalmoneyAccountRedis dmAccount = a.get(exAccountId);
			return dmAccount;
		}
		return null;
	}

	public static ExDigitalmoneyAccountRedis getAccountRedis(String customerId, String coinCode){

		RedisUtil<UserRedis> redisUtil = new RedisUtil<UserRedis>(UserRedis.class);
		UserRedis userRedis = redisUtil.get(customerId);
		if (userRedis != null) {
			ExDigitalmoneyAccountRedis account = UserRedisUtils.getAccount(userRedis.getDmAccountId(coinCode).toString(), coinCode);
			if(account != null){
				return account;
			}
		}
		return null;
	}


}
