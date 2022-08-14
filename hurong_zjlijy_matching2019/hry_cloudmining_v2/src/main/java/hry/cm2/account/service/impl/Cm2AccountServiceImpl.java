/**
 * Copyright:   
 * @author:      yaozh
 * @version:     V1.0 
 * @Date:        2019-10-14 13:54:29 
 */
package hry.cm2.account.service.impl;

import com.alibaba.fastjson.JSON;
import hry.cm2.account.dao.Cm2AccountDao;
import hry.cm2.account.dao.Cm2AccountRecordDao;
import hry.cm2.account.model.Cm2Account;
import hry.cm2.account.model.Cm2AccountRecord;
import hry.cm2.account.service.Cm2AccountService;
import hry.cm2.deal.CmDealUtil;
import hry.cm2.dto.Accountadd;
import hry.cm2.dto.CmAccountRedis;
import hry.cm2.enums.CmAccountRemarkEnum;
import hry.cm2.util.CmRedis;
import hry.cm2.util.comparator.AccountaddComparator;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.redis.common.utils.RedisService;
import hry.util.SpringUtil;
import hry.util.idgenerate.IdGenerate;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p> Cm2AccountService </p>
 * @author:         yaozh
 * @Date :          2019-10-14 13:54:29  
 */
@Service("cm2AccountService")
public class Cm2AccountServiceImpl extends BaseServiceImpl<Cm2Account, Long> implements Cm2AccountService{
	
	@Resource(name="cm2AccountDao")
	@Override
	public void setDao(BaseDao<Cm2Account, Long> dao) {
		super.dao = dao;
	}

	@Resource
	private Cm2AccountRecordDao cmAccountRecordDao;

	@Override
	public Boolean accountaddQueue(String accoutadds){

		try(Jedis jedis = CmRedis.JEDIS_POOL.getResource()) {
			redis.clients.jedis.Transaction transaction = jedis.multi();
			dealaccount(transaction, accoutadds);
			transaction.exec();

		}catch(Exception e){
			e.printStackTrace();
		}
		List<Accountadd> accountaddlist = JSON.parseArray(accoutadds, Accountadd.class);
		saveAccount( accountaddlist);
		return true;
	}
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
		CmAccountRedis coinaccount =null;

		for (Accountadd accountadd : accountaddlist) {
			if(accountadd.getMoney().compareTo(BigDecimal.ZERO)==0){
				continue;
			}
			Integer type=null;

			if(null==coinaccountId||accountadd.getAccountId().compareTo(coinaccountId)!=0){
				if(null!=coinaccount){
					CmDealUtil.setCmAccount(coinaccount,transaction);

				}

				coinaccount = CmDealUtil.getCmAccount(accountadd.getCustomerId(),accountadd.getCoinCode());
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
			CmDealUtil.setCmAccount(coinaccount,transaction);
		}
		return flag;

	}

	@Override
	public void saveAccount(List<Accountadd> accountaddlist){
		Set<Long> setaccountcoin = new HashSet<Long>();
		List<Cm2AccountRecord> listehar1=new ArrayList<Cm2AccountRecord>();
		for(Accountadd accountadd:accountaddlist){
			setaccountcoin.add(accountadd.getAccountId());
			Cm2AccountRecord contractedOneAccountRecord = createRecord(accountadd);
			if(contractedOneAccountRecord.getTransactionMoney().compareTo(new BigDecimal("9999999999"))==-1){
				listehar1.add(contractedOneAccountRecord);
			}
		}

		if (null != listehar1 && listehar1.size() > 0) {
			cmAccountRecordDao.insertRecord(listehar1);
		}


		// 账户批量入库


		Iterator<Long> iteratorc = setaccountcoin.iterator();
		List<CmAccountRedis> listd=new ArrayList<CmAccountRedis>();
		while (iteratorc.hasNext()) {
			Long id = iteratorc.next();
			Cm2Account CmAccount = this.get(id);
			if(CmAccount != null){
				CmAccountRedis coinaccountreids = CmDealUtil.getCmAccount(CmAccount.getCustomerId(),CmAccount.getCoinCode());
				coinaccountreids.setHotMoneyDouble(coinaccountreids.getHotMoney().doubleValue());
				coinaccountreids.setColdMoneyDouble(coinaccountreids.getColdMoney().doubleValue());
				/*coinaccountreids.setEmptyCoinMoneyAllDouble(coinaccountreids.getEmptyCoinMoneyAll().doubleValue());
				coinaccountreids.setEmptyCoinMoneyDouble(coinaccountreids.getEmptyCoinMoney().doubleValue());*/
				coinaccountreids.setModified(new Date());
				if(coinaccountreids.getHotMoneyDouble().compareTo(new Double("9999999999"))==-1&&
						coinaccountreids.getHotMoneyDouble().compareTo(new Double("-9999999999"))==1&&
						coinaccountreids.getColdMoneyDouble().compareTo(new Double("9999999999"))==-1
						/*&&coinaccountreids.getEmptyCoinMoneyAllDouble().compareTo(new Double("-9999999999"))==1&&
						coinaccountreids.getEmptyCoinMoneyDouble().compareTo(new Double("9999999999"))==-1*/
				){
					listd.add(coinaccountreids);
				}else{

				}
			}
		}

		if(null!=listd&&listd.size()>0){
			((Cm2AccountDao)dao).updateAccount(listd);
		}


	}
	public Cm2AccountRecord createRecord(Accountadd rccountadd){
		//资金池5(完成)，托管0(处理中)将来通过配置文件来获取判断
		Cm2AccountRecord CmAccountDetail = new Cm2AccountRecord();
		CmAccountDetail.setAccountId(rccountadd.getAccountId());
		CmAccountDetail.setCustomerId(rccountadd.getCustomerId());
		CmAccountDetail.setTransactionNum(rccountadd.getTransactionNum());
		CmAccountDetail.setRemark(CmAccountRemarkEnum.getName(rccountadd.getRemarks()));
		CmAccountDetail.setRemarkkey(rccountadd.getRemarks());
		CmAccountDetail.setMonteyType(rccountadd.getMonteyType());
		CmAccountDetail.setCoinCode(rccountadd.getCoinCode());
		CmAccountDetail.setOrderNum(IdGenerate.transactionNum("CM"));

		if(rccountadd.getMoney().compareTo(BigDecimal.ZERO)>0){
			CmAccountDetail.setTransactionMoney(rccountadd.getMoney());
			CmAccountDetail.setRecordType(1);
		}else if(rccountadd.getMoney().compareTo(BigDecimal.ZERO)<=0){
			CmAccountDetail.setRecordType(2);
			CmAccountDetail.setTransactionMoney(BigDecimal.ZERO.subtract(rccountadd.getMoney()));
		}
		return CmAccountDetail;

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
	

}
