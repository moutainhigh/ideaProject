package hry.util;

import hry.app.user.model.ExDigitalmoneyAccount;
import hry.redis.common.dao.RedisUtil;
import hry.trade.redis.model.AppAccountRedis;

public class UserRedisUtils {

	/**
	 * 获得redis账户
	 * @return
	 */
	public static AppAccountRedis getAccount(String accountId){
		if(accountId!=null&&!"".equals(accountId) ){
			RedisUtil<AppAccountRedis>  a = new RedisUtil<AppAccountRedis>(AppAccountRedis.class);
			AppAccountRedis appAccount = a.get(accountId);
			return appAccount;
		}
		return null;

	}

	/**
	 * 获得redis币账户
	 * @return
	 */
	public static ExDigitalmoneyAccount getAccount(String exAccountId, String coinCode){
		if(exAccountId!=null&&!"".equals(exAccountId) ){
			RedisUtil<ExDigitalmoneyAccount>  a = new RedisUtil<ExDigitalmoneyAccount>(ExDigitalmoneyAccount.class);
			ExDigitalmoneyAccount dmAccount = a.get(exAccountId);
			return dmAccount;
		}
		return null;
	}




}
