package hry.trade.entrust.service.impl;

import hry.account.fund.model.AppAccount;
import hry.exchange.account.model.ExDigitalmoneyAccount;
import hry.front.redis.model.UserRedis;
import hry.redis.common.dao.RedisUtil;
import hry.trade.entrust.dao.CommonDao;
import hry.trade.redis.model.AppAccountRedis;
import hry.trade.redis.model.ExDigitalmoneyAccountRedis;
import hry.util.sys.ContextUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserRedisInitUtil {
	public static UserRedis getUserRedis(Long customerId){
		// 查redis缓存
		RedisUtil<UserRedis> redisUtil = new RedisUtil<UserRedis>(UserRedis.class);
		UserRedis userRedis = redisUtil.get(customerId.toString());
		if(null==userRedis){
			 userRedis = new UserRedis();
			userRedis.setId(customerId.toString());
			
			Map<String, Long> map = findAllAccountId(customerId.toString());
			userRedis.setAccountId(map.get("accountId")==null?null:map.get("accountId"));
			//获取完后，移除
			map.remove("accountId");
			userRedis.setDmAccountId(map);
			
			redisUtil.put(userRedis, userRedis.getId());
		}
		return  userRedis;
	}
	  
	  public static Map<String, Long> findAllAccountId(String id) {
			Map<String, Long> map = new HashMap<String, Long>();
			CommonDao commonDao = (CommonDao) ContextUtil.getBean("commonDao");
			AppAccount appAccount=commonDao.getAppAccount(Long.valueOf(id));
			if (appAccount != null) {
				RedisUtil<AppAccountRedis> redisUtil = new RedisUtil<AppAccountRedis>(AppAccountRedis.class);
				AppAccountRedis appAccountRedis = redisUtil.get(appAccount.getId().toString());
				// 如果redis中的accout为空则重置
				if (appAccountRedis == null || appAccountRedis.getHotMoney() == null ) {
					AppAccountRedis ar = new AppAccountRedis();
					ar.setColdMoney(appAccount.getColdMoney());
					ar.setCustomerId(appAccount.getCustomerId());
					ar.setHotMoney(appAccount.getHotMoney());
					ar.setId(appAccount.getId());
					ar.setUserName(appAccount.getUserName());
					redisUtil.put(ar, ar.getId().toString());
				}
				map.put("accountId", appAccount.getId());
			}

			List<ExDigitalmoneyAccount> list = commonDao.getListExDigitalmoneyAccount(Long.valueOf(id));
			if (list != null && list.size() > 0) {
				for (ExDigitalmoneyAccount exDigitalmoneyAccount : list) {
					RedisUtil<ExDigitalmoneyAccountRedis> redisUtil = new RedisUtil<ExDigitalmoneyAccountRedis>(ExDigitalmoneyAccountRedis.class);
					ExDigitalmoneyAccountRedis exDigitalmoneyAccountRedis = redisUtil.get(exDigitalmoneyAccount.getId().toString());
					// 如果redis中的accout为空则重置
					if (exDigitalmoneyAccountRedis == null || exDigitalmoneyAccountRedis.getHotMoney() == null) {
						ExDigitalmoneyAccountRedis exar = new ExDigitalmoneyAccountRedis();
						exar.setCoinCode(exDigitalmoneyAccount.getCoinCode());
						exar.setColdMoney(exDigitalmoneyAccount.getColdMoney());
						exar.setHotMoney(exDigitalmoneyAccount.getHotMoney());
						exar.setCustomerId(exDigitalmoneyAccount.getCustomerId());
						exar.setId(exDigitalmoneyAccount.getId());
						redisUtil.put(exar, exDigitalmoneyAccount.getId().toString());
					}
					map.put(exDigitalmoneyAccount.getCoinCode(), exDigitalmoneyAccount.getId());
				}
			}

			return map;
		}
  }

