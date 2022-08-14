/**
 * Copyright:   
 * @author:      lzy
 * @version:     V1.0 
 * @Date:        2019-04-15 16:35:25 
 */
package hry.admin.klg.platform.service.impl;

import com.alibaba.fastjson.JSON;
import hry.admin.klg.platform.dao.KlgPlatformAccountDao;
import hry.admin.klg.platform.model.KlgPlatformAccount;
import hry.admin.klg.platform.model.KlgPlatformAccountRecord;
import hry.admin.klg.platform.service.KlgPlatformAccountRecordService;
import hry.admin.klg.platform.service.KlgPlatformAccountService;
import hry.admin.mq.producer.service.MessageProducer;
import hry.bean.JsonResult;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import javax.annotation.Resource;

import hry.klg.model.PlatformAccountadd;
import hry.klg.model.RulesConfig;
import hry.klg.remote.RemoteKlgService;
import hry.redis.common.utils.RedisService;
import hry.util.SpringUtil;
import hry.util.idgenerate.IdGenerate;
import hry.util.idgenerate.NumConstant;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p> KlgPlatformAccountService </p>
 * @author:         lzy
 * @Date :          2019-04-15 16:35:25  
 */
@Service("klgPlatformAccountService")
public class KlgPlatformAccountServiceImpl extends BaseServiceImpl<KlgPlatformAccount, Long> implements KlgPlatformAccountService{
	@Resource
	private KlgPlatformAccountRecordService accountRecordService;
	@Resource
	private RedisService redisService;
	@Resource
	private MessageProducer messageProducer;
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
	public JsonResult recharge(String money, String account){
		/*Map<String,String> hMap=new HashMap<>();
		hMap.put("type",account);
		hMap.put("money",money);*/
		RemoteKlgService remoteKlgService= SpringUtil.getBean("remoteKlgService");
		String num=(String)remoteKlgService.getPlatformAccount(account);
	/*	KlgPlatformAccountRecord accountRecord=new KlgPlatformAccountRecord();
		accountRecord.setMoney(new BigDecimal(money));
		accountRecord.setNowMoney(new BigDecimal(num));
		accountRecord.setRemark("后台充值");
		accountRecord.setType(103);
		accountRecord.setAccountId(account);*/
		String serialNumber= IdGenerate.transactionNum(NumConstant.Ex_Dm_Transaction);
		//accountRecord.setSerialNumber(serialNumber);
		//accountRecordService.save(accountRecord);
		List<PlatformAccountadd> platformAccounts=new ArrayList<>();
		PlatformAccountadd accountadd=new PlatformAccountadd();
		accountadd.setType(103);
		accountadd.setSerialNumber(serialNumber);
		accountadd.setRemark("后台充值");
		accountadd.setAccountType(account);
		accountadd.setMoney(money);
		platformAccounts.add(accountadd);
		messageProducer.toPlatformCurrency(JSON.toJSONString(platformAccounts));
		return new JsonResult().setSuccess(true).setMsg("成功");
	}

	@Override
	public JsonResult transfer(String money, String account,String toAccount){
		RemoteKlgService remoteKlgService= SpringUtil.getBean("remoteKlgService");
		Map<String,String> hMap=new HashMap<>();
		hMap.put("account",account);
		hMap.put("toAccount",toAccount);
		hMap.put("money",money);
		JsonResult jsonResult= remoteKlgService.platformTransfer(hMap);
		return jsonResult;
	}

}
