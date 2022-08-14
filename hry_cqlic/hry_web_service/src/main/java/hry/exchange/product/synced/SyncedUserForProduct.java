/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Wu Shuiming
 * @version:      V1.0 
 * @Date:        2016年5月26日 下午6:46:19
 */
package hry.exchange.product.synced;

import hry.account.fund.model.AppAccount;
import hry.account.fund.service.AppAccountService;
import hry.util.QueryFilter;
import hry.util.RemoteQueryFilter;
import hry.util.UUIDUtil;
import hry.util.idgenerate.IdGenerate;
import hry.util.properties.PropertiesUtils;
import hry.util.sys.ContextUtil;
import hry.customer.person.model.AppPersonInfo;
import hry.customer.remote.RemoteAppCustomerService;
import hry.customer.remote.RemoteAppPersonInfoService;
import hry.customer.user.model.AppCustomer;
import hry.exchange.account.model.ExDigitalmoneyAccount;
import hry.exchange.account.service.ExDigitalmoneyAccountService;
import hry.exchange.product.model.ExProduct;
import hry.exchange.purse.CoinInterfaceUtil;
import hry.front.redis.model.UserRedis;
import hry.redis.common.dao.RedisUtil;
import hry.trade.redis.model.ExDigitalmoneyAccountRedis;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;

/**
 * <p>
 * TODO
 * </p>
 * 
 * @author: Wu Shuiming
 * @Date : 2016年5月26日 下午6:46:19
 * 
 *       当商品的状态修改发行的时候 同步当前系统上的所有用户的虚拟账户
 * 
 */
public class SyncedUserForProduct {
	private static Logger logger = Logger.getLogger(SyncedUserForProduct.class);
	
	public void syncedUser(ExProduct exProduct) {

		//Map<String, String> loadWeb = PropertiesUtils.getLoadWeb();
		Map<String, String> loadWeb = new HashMap<String, String>();
		loadWeb.put("cn", exProduct.getLanguage());
		Set<Entry<String, String>> entrySet = loadWeb.entrySet();
		Iterator<Entry<String, String>> iterator = entrySet.iterator();
		while (iterator.hasNext()) {
			Entry<String, String> next = iterator.next();
			String website = next.getKey();
			String currencyType = next.getValue();

			String code = exProduct.getCoinCode();
			ExDigitalmoneyAccountService exDigitalmoneyAccountService = (ExDigitalmoneyAccountService) ContextUtil.getBean("exDigitalmoneyAccountService");
			RemoteAppCustomerService remoteAppCustomerService = (RemoteAppCustomerService) ContextUtil.getBean("remoteAppCustomerService");

			RemoteQueryFilter rFilter = new RemoteQueryFilter(AppCustomer.class);
			rFilter.setSaasId(ContextUtil.getSaasId());
			//rFilter.addFilter("isReal=", 1);
			List<AppCustomer> list = remoteAppCustomerService.find(rFilter);
			RemoteAppPersonInfoService remoteAppPersonInfoService = (RemoteAppPersonInfoService) ContextUtil.getBean("remoteAppPersonInfoService");

			// 判断所有用户的虚拟账户是否有当前发布的虚拟币所对应的虚拟账户
			for (AppCustomer customer : list) {
				
				//如果中国站没实名则进行下一 
				/*if(ContextUtil.CN.equals(website)){
					if(customer.getIsReal().intValue()==0){
						continue;
					}
				}
				
				//如果国际站没实名则进行下一次循环
				if(ContextUtil.EN.equals(website)){
					if(customer.getIsRealUsd().intValue()==0){
						continue;
					}
				}*/
				
				//查询是否已经存在币账户
				QueryFilter ff = new QueryFilter(ExDigitalmoneyAccount.class);
				ff.addFilter("customerId=", customer.getId());
				ff.addFilter("coinCode=", code);
				ExDigitalmoneyAccount old=exDigitalmoneyAccountService.get(ff);
				if(old!=null){
					logger.error("已经存在：------"+customer.getId());
					continue;
				}
				
				AppPersonInfo ap = remoteAppPersonInfoService.getByCustomerId(customer.getId());
				customer.setAppPersonInfo(ap);

				// 没有币账户就开通
				ExDigitalmoneyAccount exDigitalmoneyAccount = this.pushExDigitalmoneyAccount(customer, exProduct,website,currencyType);
				exDigitalmoneyAccountService.save(exDigitalmoneyAccount);
				logger.error("虚拟币账户ID"+exDigitalmoneyAccount.getId());
				
				//查redis
				RedisUtil<UserRedis> redisUtil = new RedisUtil<UserRedis>(UserRedis.class);
				UserRedis userRedis = redisUtil.get(customer.getId().toString());
				if(userRedis!=null){
					Map<String, Long> dmAccountIdMap = userRedis.getDmAccountId();
					dmAccountIdMap.put(exDigitalmoneyAccount.getCoinCode(), exDigitalmoneyAccount.getId());
					redisUtil.put(userRedis, customer.getId().toString());
				}
				
				
				RedisUtil<ExDigitalmoneyAccountRedis>  a = new RedisUtil<ExDigitalmoneyAccountRedis>(ExDigitalmoneyAccountRedis.class);
				ExDigitalmoneyAccountRedis exDigitalmoneyAccountRedis = new ExDigitalmoneyAccountRedis();
				exDigitalmoneyAccountRedis.setId(exDigitalmoneyAccount.getId());
				exDigitalmoneyAccountRedis.setCustomerId(exDigitalmoneyAccount.getCustomerId());
				exDigitalmoneyAccountRedis.setHotMoney(exDigitalmoneyAccount.getHotMoney());
				exDigitalmoneyAccountRedis.setColdMoney(exDigitalmoneyAccount.getColdMoney());
				exDigitalmoneyAccountRedis.setUserName(exDigitalmoneyAccount.getUserName());
				exDigitalmoneyAccountRedis.setCoinCode(exDigitalmoneyAccount.getCoinCode());
				a.put(exDigitalmoneyAccountRedis, exDigitalmoneyAccount.getId().toString());
				
			}

		}

	}

	// 传入一个用户 返回一个虚拟账户对象(瞬时态)
	public ExDigitalmoneyAccount pushExDigitalmoneyAccount(AppCustomer customer, ExProduct exProduct,String website,String currencyType) {
		ExDigitalmoneyAccount exDigitalmoneyAccount = new ExDigitalmoneyAccount();
		exDigitalmoneyAccount.setCustomerId(customer.getId());
		exDigitalmoneyAccount.setUserName(customer.getUserName());
		
		AppPersonInfo personInfo = (AppPersonInfo) customer.getAppPersonInfo();
		
		exDigitalmoneyAccount.setAccountNum(IdGenerate.accountNum(customer.getId().intValue(), exProduct.getCoinCode()));
		exDigitalmoneyAccount.setCoinCode(exProduct.getCoinCode());
		exDigitalmoneyAccount.setWebsite(website);
		exDigitalmoneyAccount.setCurrencyType(currencyType);
		exDigitalmoneyAccount.setCoinName(exProduct.getName());
		exDigitalmoneyAccount.setStatus(1);
		exDigitalmoneyAccount.setPrivateKey(UUIDUtil.getUUID());
//		String ss=CoinInterfaceUtil.create(customer.getUserName(), exProduct.getCoinCode());
//		if(ss!=null&&!ss.contains("<")){
//			String address= JSONObject.parseObject(ss).get("address").toString();
//			if(!address.equals("")){
//				  exDigitalmoneyAccount.setPublicKey(address);
//			}
//		}
//		else{
//			  exDigitalmoneyAccount.setPublicKey("");
//		}
	
		exDigitalmoneyAccount.setSaasId(customer.getSaasId());
		return exDigitalmoneyAccount;
	}

	public void delistForProduct(ExProduct exProduct) {

		ExDigitalmoneyAccountService exDigitalmoneyAccountService = (ExDigitalmoneyAccountService) ContextUtil.getBean("exDigitalmoneyAccountService");

		// 如果 是退市 将要将所有的对应的虚拟币的账号的状态标为不用状态
		QueryFilter filter = new QueryFilter(ExDigitalmoneyAccount.class);
		filter.addFilter("currencyType=", exProduct.getCoinCode());
		filter.addFilter("status!=", 0);
		List<ExDigitalmoneyAccount> list2 = exDigitalmoneyAccountService.find(filter);
		for (ExDigitalmoneyAccount exDigitalmoneyAccount : list2) {
			exDigitalmoneyAccount.setStatus(0);
			exDigitalmoneyAccountService.update(exDigitalmoneyAccount);
		}
	}


}
