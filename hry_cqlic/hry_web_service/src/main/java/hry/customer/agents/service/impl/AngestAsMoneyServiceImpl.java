package hry.customer.agents.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import hry.account.remote.RemoteAppAccountService;
import hry.core.mvc.dao.base.BaseDao;
import hry.bean.JsonResult;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.util.sys.ContextUtil;
import hry.customer.agents.dao.AppAgentsCustromerDao;
import hry.customer.agents.model.AngestAsMoney;
import hry.customer.agents.model.AppAgentsCustromer;
import hry.customer.agents.model.vo.AgentsForMoney;
import hry.customer.agents.service.AngestAsMoneyService;
import hry.customer.agents.service.AppAgentsCustromerService;
import hry.front.redis.model.UserRedis;
import hry.mq.producer.service.MessageProducer;
import hry.redis.common.dao.RedisUtil;
import hry.trade.redis.model.Accountadd;
import hry.web.remote.RemoteAppConfigService;

import javax.annotation.Resource;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

@Service("angestAsMoneyService")
public class AngestAsMoneyServiceImpl extends BaseServiceImpl<AngestAsMoney,Long> implements AngestAsMoneyService {

	@Resource(name="angestAsMoneyDao")
	@Override
	public void setDao(BaseDao<AngestAsMoney, Long> dao) {
		super.dao = dao;
	}
	
	@Resource(name="appAgentsCustromerService")
	public AppAgentsCustromerService appAgentsCustromerService;
	
	@Resource(name="appAgentsCustromerDao")
	public AppAgentsCustromerDao appAgentsCustromerDao;
	@Resource
	private MessageProducer messageProducer;

	public JsonResult postMoneyById(Long id, BigDecimal money,String fixPriceCoinCode) {
		
		JsonResult result = new JsonResult();
		result.setSuccess(false);
		
		AppAgentsCustromer agentsCustromer = appAgentsCustromerService.get(id);
		
		if(null != agentsCustromer){
		
			List<AgentsForMoney> list = appAgentsCustromerDao.findAgentsForMoney(agentsCustromer.getCustomerName(),fixPriceCoinCode);
			
			if(list.size()>0){
				AgentsForMoney forMoney = list.get(0);
				money = forMoney.getSurplusMoney();
				
				/*if(null == supMoney){
					supMoney = BigDecimal.ZERO;
				}
				
				int i = money.compareTo(supMoney);
				
				if(i <= 0){*/
					
					AngestAsMoney angestAsMoney = new AngestAsMoney();
					
					angestAsMoney.setAgentName(agentsCustromer.getCustomerName());
					angestAsMoney.setCustomerId(agentsCustromer.getCustomerId());
					angestAsMoney.setDrawalMoney(money);
					angestAsMoney.setFixPriceCoinCode(forMoney.getFixPriceCoinCode());
					angestAsMoney.setFixPriceType(forMoney.getFixPriceType());
					
					angestAsMoney.setUserName(ContextUtil.getCurrentUserName());
					angestAsMoney.setTransactionNum(transactionNum("01"));
				
					String coin = getCnfigValue("language_code");
					RedisUtil<UserRedis> redisUtil = new RedisUtil<UserRedis>(UserRedis.class);
					UserRedis userRedis = redisUtil.get(agentsCustromer.getCustomerId().toString());
					if(null==userRedis){
						result.setMsg("此用户的缓存账号为空，登陆会生成缓存账号");
						result.setSuccess(false);
						return result;
					}
					
					super.save(angestAsMoney);
					if(fixPriceCoinCode.equals(coin)){
						Long accountId=userRedis.getAccountId();
						//----发送mq消息----start
						//热账户减少
						Accountadd accountadd = new Accountadd();
						accountadd.setAccountId(accountId);
						accountadd.setMoney(money);
						accountadd.setMonteyType(1);
						accountadd.setRemarks(32);
						accountadd.setAcccountType(0);
						accountadd.setTransactionNum(angestAsMoney.getTransactionNum());
						
						List<Accountadd> list2 = new ArrayList<Accountadd>();
						list2.add(accountadd);
						messageProducer.toAccount(JSON.toJSONString(list2));
					}else{
						Long accountId=userRedis.getDmAccountId(fixPriceCoinCode);
						//----发送mq消息----start
						//热账户减少
						Accountadd accountadd = new Accountadd();
						accountadd.setAccountId(accountId);
						accountadd.setMoney(money);
						accountadd.setMonteyType(1);
						accountadd.setRemarks(32);
						accountadd.setAcccountType(1);
						accountadd.setTransactionNum(angestAsMoney.getTransactionNum());
						
						List<Accountadd> list3 = new ArrayList<Accountadd>();
						list3.add(accountadd);
						messageProducer.toAccount(JSON.toJSONString(list3));
					}
					
					result.setMsg("派送成功");
					result.setSuccess(true);
				/*	
				}else{
					result.setMsg("所派送的余额大于可用余额");
				}
			*/
			}else{
				result.setMsg("代理商用户名有误");
			}
		
		}
		
		return result;
	}


	
	public static String getCnfigValue(String type) {
		RemoteAppConfigService remoteAppConfigService = (RemoteAppConfigService) ContextUtil
				.getBean("remoteAppConfigService");
		return remoteAppConfigService.getValueByKey(type);
	}

	
	public static String transactionNum(String bussType){
		 SimpleDateFormat format = new SimpleDateFormat("yyMMddHHmmssSSS");
		 String time = format.format(new Date(System.currentTimeMillis()));
		 String randomStr = RandomStringUtils.random(4, false, true);
		 if(null==bussType){
			 bussType="00";
		 }
		 return bussType+time+randomStr;
	 }
	
	
}






