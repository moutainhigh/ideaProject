/**
 * Copyright:
 * @author:      xubb
 * @version:     V1.0
 * @Date:        2019-07-04 10:00:54
 */
package hry.app.account.service.impl;

import com.alibaba.fastjson.JSON;
import hry.app.account.dao.OtcAccountDao;
import hry.app.account.dao.OtcAccountRecordDao;
import hry.app.account.service.OtcAccountService;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.otc.manage.remote.model.account.OtcAccount;
import hry.otc.manage.remote.model.account.OtcAccountRecord;
import hry.redis.common.utils.RedisService;
import hry.util.OtcRedis;
import hry.util.SpringUtil;
import hry.util.comparator.AccountaddComparator;
import hry.util.constant.DealConstant;
import hry.util.deal.OtcDealUtil;
import hry.util.dto.Accountadd;
import hry.util.dto.OtcAccountRedis;
import hry.util.enums.AccountRemarkEnum;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p> OtcAccountService </p>
 * @author:         xubb
 * @Date :          2019-07-04 10:00:54
 */
@Service("otcAccountService")
public class OtcAccountServiceImpl extends BaseServiceImpl<OtcAccount, Long> implements OtcAccountService {

	@Resource(name="otcAccountDao")
	@Override
	public void setDao(BaseDao<OtcAccount, Long> dao) {
		super.dao = dao;
	}

	@Resource
	public OtcAccountRecordDao otcAccountRecordDao;

	@Override
	public Boolean accountaddQueue(String accoutadds){
		try(Jedis jedis = OtcRedis.OTC_JEDIS_POOL.getResource()) {
			redis.clients.jedis.Transaction transaction = jedis.multi();
			dealaccount(transaction, accoutadds);
			transaction.exec();
		}catch(Exception e){
			e.printStackTrace();
		}
		return true;
	}
	@Override
	public  Boolean dealaccount(redis.clients.jedis.Transaction transaction,String accoutadds) {
		Boolean  flag=true;
		List<Accountadd> accountaddlist = JSON.parseArray(accoutadds, Accountadd.class);
		for (Accountadd accountadd : accountaddlist) {
			if(null==accountadd.getAccountId()){
				System.out.println("账户有为空的=="+accoutadds);
				return flag;
			}
			if(null==accountadd.getMoney()){
				System.out.println("money为空=="+accoutadds);
				return flag;
			}
		}

		AccountaddComparator accountaddComparator=new AccountaddComparator();
		Collections.sort(accountaddlist,accountaddComparator);
		Long coinaccountId=null;
		OtcAccountRedis coinaccount =null;

		for (Accountadd accountadd : accountaddlist) {
			if(accountadd.getMoney().compareTo(BigDecimal.ZERO)==0){
				continue;
			}
			Integer type=null;

			if(null==coinaccountId||accountadd.getAccountId().compareTo(coinaccountId)!=0){
				if(null!=coinaccount){
					OtcDealUtil.setOtcAccount(coinaccount,transaction);

				}

				coinaccount = OtcDealUtil.getOtcAccount(accountadd.getCustomerId(),accountadd.getCoinCode());
				coinaccountId=coinaccount.getId();
			}
			if (null != coinaccount) {
				if (accountadd.getMonteyType().equals(1)) {
					accountadd.setBalanceMoney(coinaccount.getHotMoney());
					coinaccount.setHotMoney(coinaccount.getHotMoney().add(accountadd.getMoney()));
					type=1;
				} else if (accountadd.getMonteyType().equals(2)){
					accountadd.setBalanceMoney(coinaccount.getColdMoney());
					coinaccount.setColdMoney(coinaccount.getColdMoney().add(accountadd.getMoney()));
					type=2;
				}

			}else{
				System.out.println("mq:redis资金账户没有查到=="+accountadd.getCoinCode()+accountadd.getCustomerId());
			}
			accountadd.setOrderNum(accountOrderNum(new Date(),type));
			accountadd.setCustomerId(coinaccount.getCustomerId());
		}



		if(null!=coinaccount){
			OtcDealUtil.setOtcAccount(coinaccount,transaction);
		}

		try (Jedis jedis = OtcRedis.OTC_JEDIS_POOL.getResource()) {
			jedis.publish(DealConstant.JedisPubSub_SAVEACCONT, JSON.toJSONString(accountaddlist));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return flag;

	}


	@Override
	public void saveAccount(List<Accountadd> accountaddlist){
		Set<Long> setaccountcoin = new HashSet<Long>();
		List<OtcAccountRecord> listehar1=new ArrayList<OtcAccountRecord>();
		for(Accountadd accountadd:accountaddlist){
			setaccountcoin.add(accountadd.getAccountId());
			OtcAccountRecord otcAccountRecord=createRecord(accountadd);
			if(otcAccountRecord.getTransactionMoney().compareTo(new BigDecimal("9999999999"))==-1){
				listehar1.add(otcAccountRecord);
			}
		}
		try {
			if (null != listehar1 && listehar1.size() > 0) {
				otcAccountRecordDao.insertRecord(listehar1);
			}
			// 账户批量入库
			Iterator<Long> iteratorc = setaccountcoin.iterator();
			List<OtcAccountRedis> listd=new ArrayList<OtcAccountRedis>();
			while (iteratorc.hasNext()) {
				Long id = iteratorc.next();
				OtcAccount contractAccountsql = this.get(id);
				System.out.println("=== id ==== OTC日志==== ： " + id);
				System.out.println("=== contractAccountsql ==== OTC日志==== ： " + contractAccountsql);
				OtcAccountRedis coinaccountreids = OtcDealUtil.getOtcAccount(contractAccountsql.getCustomerId(),contractAccountsql.getCoinCode());
				coinaccountreids.setHotMoneyDouble(coinaccountreids.getHotMoney().doubleValue());
				coinaccountreids.setColdMoneyDouble(coinaccountreids.getColdMoney().doubleValue());
				coinaccountreids.setModified(new Date());
				if(coinaccountreids.getHotMoneyDouble().compareTo(new Double("9999999999"))==-1&&
						coinaccountreids.getHotMoneyDouble().compareTo(new Double("-9999999999"))==1&&
						coinaccountreids.getColdMoneyDouble().compareTo(new Double("9999999999"))==-1){
					listd.add(coinaccountreids);
				}else{

				}
			}

			if(null!=listd&&listd.size()>0){
				((OtcAccountDao)dao).updateExDigitalmoneyAccount(listd);
			}
		}catch(Exception e){
			e.printStackTrace();
		}

	}

	public OtcAccountRecord createRecord(Accountadd rccountadd){

		OtcAccountRecord hotAccountRecord=new OtcAccountRecord();
		hotAccountRecord.setAccountId(rccountadd.getAccountId());
		hotAccountRecord.setCustomerId(rccountadd.getCustomerId());
		hotAccountRecord.setTransactionNum(rccountadd.getTransactionNum());
		hotAccountRecord.setRemark(AccountRemarkEnum.getName(rccountadd.getRemarks()));
		hotAccountRecord.setRemarkkey(rccountadd.getRemarks());
		hotAccountRecord.setBalanceMoney(rccountadd.getBalanceMoney());
		hotAccountRecord.setOrderNum(rccountadd.getOrderNum());
		hotAccountRecord.setMonteyType(rccountadd.getMonteyType());
		hotAccountRecord.setCoinCode(rccountadd.getCoinCode());
		if(rccountadd.getMoney().compareTo(BigDecimal.ZERO)>0){
			hotAccountRecord.setRecordType(1);
			hotAccountRecord.setTransactionMoney(rccountadd.getMoney());
		}else if(rccountadd.getMoney().compareTo(BigDecimal.ZERO)<=0){
			hotAccountRecord.setRecordType(2);
			hotAccountRecord.setTransactionMoney(BigDecimal.ZERO.subtract(rccountadd.getMoney()));
		}
		return hotAccountRecord;

	}


	public static String accountOrderNum(Date date,Integer type) {
		String randomStr = RandomStringUtils.random(3, false, true);
		SimpleDateFormat format = new SimpleDateFormat("yyMMddHHmmss");
		String times = format.format(date);
		String time=type+"account"+times;
		RedisService redisService = (RedisService) SpringUtil.getBean("redisService");

		String v = redisService.get(time);
		if (!StringUtils.isEmpty(v)) {
			Integer aa = Integer.valueOf(v) + 1;
			String bb = aa.toString();
			if (aa.compareTo(10) == -1) {
				bb = "000000" + aa.toString();
			}else if (aa.compareTo(100) == -1) {
				bb = "00000" + aa.toString();
			}else if (aa.compareTo(1000) == -1) {
				bb = "0000" + aa.toString();
			}else if (aa.compareTo(10000) == -1) {
				bb = "000" + aa.toString();
			}else if (aa.compareTo(10000) == -1) {
				bb = "000" + aa.toString();
			}else if (aa.compareTo(100000) == -1) {
				bb = "00" + aa.toString();
			}else if (aa.compareTo(1000000) == -1) {
				bb = "0" + aa.toString();
			}
			redisService.save(time, aa.toString(), 1);
			return times + bb + randomStr;
		} else {
			redisService.save(time, "1", 1);
			return times + "0000001" + randomStr;
		}

	}

	@Override
	public void changeCustomerHotAccount(List<Accountadd> aaddlists, Long customerId, String coinCode, Long contractAccountId, BigDecimal count, String transtionNum, Integer remark){
		Accountadd accountadd3 = new Accountadd(customerId,coinCode,contractAccountId, count,
				1, remark,transtionNum);
		aaddlists.add(accountadd3);

	}
	@Override
	public void changeCustomerColdAccount(List<Accountadd> aaddlists, Long customerId, String coinCode, Long contractAccountId, BigDecimal count, String transtionNum, Integer remark){

		Accountadd accountadd3 = new Accountadd(customerId,coinCode,contractAccountId, count,
				2, remark,transtionNum);
		aaddlists.add(accountadd3);

	}
}
