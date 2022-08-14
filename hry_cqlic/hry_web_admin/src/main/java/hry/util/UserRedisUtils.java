package hry.util;

import com.alibaba.fastjson.JSON;
import hry.admin.customer.model.AppCustomer;
import hry.front.redis.model.UserRedis;
import hry.redis.common.dao.RedisUtil;
import hry.redis.common.utils.RedisService;
import hry.trade.redis.model.AppAccountRedis;
import hry.trade.redis.model.ExDigitalmoneyAccountRedis;

public class UserRedisUtils {
	
	/**
	 * 获得redis账户 
	 * @return
	 */
	public static AppAccountRedis getAccount(String accountId){
		if(accountId!=null&&!"".equals(accountId) ){
			RedisUtil<AppAccountRedis> a = new RedisUtil<AppAccountRedis>(AppAccountRedis.class);
			AppAccountRedis appAccount = a.get(accountId);
			return appAccount;
		}
		return null;
		
	}
	
	/**
	 * 获得redis币账户 
	 * @return
	 */
	public static ExDigitalmoneyAccountRedis getAccount(String exAccountId, String coinCode){
		if(exAccountId!=null&&!"".equals(exAccountId) ){
			RedisUtil<ExDigitalmoneyAccountRedis> a = new RedisUtil<ExDigitalmoneyAccountRedis>(ExDigitalmoneyAccountRedis.class);
			ExDigitalmoneyAccountRedis dmAccount = a.get(exAccountId);
			return dmAccount;
		}
		return null;
	}

	public static ExDigitalmoneyAccountRedis getAccountRedis(String customerId,String coinCode){
		if(coinCode!=null&&!"".equals(coinCode) ){
			RedisUtil<UserRedis> redisUtil = new RedisUtil<UserRedis>(UserRedis.class);
			UserRedis userRedis = redisUtil.get(customerId);
			RedisUtil<ExDigitalmoneyAccountRedis> a = new RedisUtil<ExDigitalmoneyAccountRedis>(ExDigitalmoneyAccountRedis.class);
			if(null!=userRedis.getDmAccountId(coinCode)){
				ExDigitalmoneyAccountRedis dmAccount = a.get(userRedis.getDmAccountId(coinCode).toString());
				return dmAccount;
			}
		}
		return null;
	}
	
	
	/**
	 * 方法名称: userRedisToSession<br>
	 * 描述：解决前台用户登录状态下，后台审核更改该用户状态后前台不实时同步的问题
	 * 将用户userCode存入redis,前台filter拦截同步到对应的session中后立即删除redis中的值,
	 * 避免占用redis内存,redis存储时间比session超时长一点
	 * 作者: 李鑫
	 * 修改日期：2018年4月27日下午8:33:30
	 * @param appCustomer
	 * @param redisService
	 */
	public static void userRedisToSession(AppCustomer appCustomer, RedisService redisService){
		String userCode = appCustomer.getUserCode();
		redisService.save("mobile:" + userCode ,  "{\"mobile\":\"" + userCode + "\",\""+userCode+"\":" + JSON.toJSON(appCustomer).toString() + "}", 1860);
	}
	
	

}
