/**
 * Copyright:   
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-04-24 14:07:27 
 */
package hry.klg.platform.service.impl;

import hry.klg.model.RulesConfig;
import hry.klg.platform.dao.KlgPlatformAccountDao;
import hry.klg.platform.model.KlgPlatformAccount;
import hry.klg.platform.service.KlgPlatformAccountService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;

import hry.redis.common.utils.RedisService;
import hry.util.QueryFilter;
import org.springframework.stereotype.Service;

/**
 * <p> KlgPlatformAccountService </p>
 * @author:         lzy
 * @Date :          2019-04-24 14:07:27  
 */
@Service("klgPlatformAccountService")
public class KlgPlatformAccountServiceImpl extends BaseServiceImpl<KlgPlatformAccount, Long> implements KlgPlatformAccountService{

	@Resource
	private RedisService redisService;

	@Resource(name="klgPlatformAccountDao")
	@Override
	public void setDao(BaseDao<KlgPlatformAccount, Long> dao) {
		super.dao = dao;
	}


	@Override
	public int updatePlatformAccount(String money, String account) {
		return ((KlgPlatformAccountDao)dao).updatePlatformAccount(money,account);
	}

	@Override
	public String getPlatformAccountByAccountType(String account) {
		String redisKey= RulesConfig.PLATFORM_NUMBER+account;
		String num= redisService.get(redisKey);
		if(num==null||num.equals("")) {
			QueryFilter queryFilter = new QueryFilter(KlgPlatformAccount.class);
			queryFilter.addFilter("type=", account);
			KlgPlatformAccount accountRecord = super.get(queryFilter);
			String money=accountRecord.getMoney()==null?"0":accountRecord.getMoney().stripTrailingZeros().toPlainString();
			redisService.save(redisKey,money);
			num=money;
		}
		return num;
	}
}
