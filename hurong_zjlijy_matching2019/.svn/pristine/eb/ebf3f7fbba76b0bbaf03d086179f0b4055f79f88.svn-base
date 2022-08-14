/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Wu Shuiming
 * @version:      V1.0
 * @Date:        2016年3月24日 下午2:04:29
 */
package hry.trade.entrust.service.impl;

import com.alibaba.fastjson.JSON;
import hry.account.fund.model.AppAccount;
import hry.account.fund.model.AppColdAccountRecord;
import hry.account.fund.model.AppHotAccountRecord;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.exchange.account.model.ExDigitalmoneyAccount;
import hry.exchange.account.model.ExDmColdAccountRecord;
import hry.exchange.account.model.ExDmHotAccountRecord;
import hry.redis.common.utils.RedisService;
import hry.trade.account.dao.*;
import hry.trade.account.service.*;
import hry.trade.comparator.AscBigDecimalComparator;
import hry.trade.entrust.dao.ExEntrustDao;
import hry.trade.entrust.dao.ExOrderInfoDao;
import hry.trade.entrust.model.ExEntrust;
import hry.trade.entrust.model.ExOrder;
import hry.trade.entrust.model.ExOrderInfo;
import hry.trade.entrust.service.ExEntrustService;
import hry.trade.entrust.service.ExOrderInfoService;
import hry.trade.entrust.service.ExOrderService;
import hry.trade.mq.service.MessageProducer;
import hry.trade.redis.model.*;
import hry.util.idgenerate.IdGenerate;
import hry.util.idgenerate.NumConstant;
import hry.util.sys.ContextUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * <p>
 * TODO
 * </p>
 *
 * @author: Gao Mimi
 * @Date : 2016年4月12日 下午4:45:50
 */
@Service("exOrderInfoService")
public class ExOrderInfoServiceImpl extends BaseServiceImpl<ExOrderInfo, Long> implements ExOrderInfoService {

	private final Logger log = Logger.getLogger(ExOrderInfoServiceImpl.class);

	@Resource(name = "exOrderInfoDao")
	@Override
	public void setDao(BaseDao<ExOrderInfo, Long> dao) {
		super.dao = dao;
	}

	@Resource(name = "exOrderService")
	public ExOrderService exOrderService;

	@Resource
	public ExEntrustService exEntrustService;
	@Resource
	private RedisService redisService;
	@Resource
	private ExOrderInfoService exOrderInfoService;
	@Resource
	public ExDigitalmoneyAccountService exDigitalmoneyAccountService;
	@Resource
	public AppAccountService appAccountService;
	@Resource
	public ExDigitalmoneyAccountDao exDigitalmoneyAccountDao;
	@Resource
	public AppAccountDao appAccountDao;
	@Resource
	private ExEntrustDao exEntrustDao;
	@Resource
	private ExOrderInfoDao exOrderInfoDao;
	@Resource
	private ExDmColdAccountRecordService exDmColdAccountRecordService;
	@Resource
	private ExDmHotAccountRecordService exDmHotAccountRecordService;
	@Resource
	private AppColdAccountRecordService appColdAccountRecordService;
	@Resource
	private AppHotAccountRecordService appHotAccountRecordService;
	@Resource
	private ExDmColdAccountRecordDao exDmColdAccountRecordDao;
	@Resource
	private ExDmHotAccountRecordDao exDmHotAccountRecordDao;
	@Resource
	private AppColdAccountRecordDao appColdAccountRecordDao;
	@Resource
	private AppHotAccountRecordDao appHotAccountRecordDao;

	public ExOrderInfo createExOrderInfo(Integer type, EntrustTrade buyExEntrust, EntrustTrade sellentrust, BigDecimal tradeCount, BigDecimal tradePrice) {
		// 订单开始详细
		ExOrderInfo exOrderInfo = new ExOrderInfo();
		exOrderInfo.setType(type);
		String transactionNum = IdGenerate.transactionNum(NumConstant.Ex_Order);
		exOrderInfo.setOrderNum("T" + transactionNum.substring(2, transactionNum.length()));
		exOrderInfo.setTransactionCount(tradeCount);
		exOrderInfo.setTransactionPrice(tradePrice);
		exOrderInfo.setTransactionSum(tradePrice.multiply(tradeCount));
		exOrderInfo.setTransactionTime(new Date());
		exOrderInfo.setOrderTimestapm(exOrderInfo.getTransactionTime().getTime());
		exOrderInfo.setTransactionBuyFeeRate(buyExEntrust.getTransactionFeeRate());
		exOrderInfo.setTransactionSellFeeRate(sellentrust.getTransactionFeeRate());
		exOrderInfo.setTransactionBuyFee(exOrderInfo.getTransactionCount().multiply(exOrderInfo.getTransactionBuyFeeRate()).divide(new BigDecimal("100")));
		exOrderInfo.setTransactionSellFee(exOrderInfo.getTransactionSum().multiply(exOrderInfo.getTransactionSellFeeRate()).divide(new BigDecimal("100")));
		exOrderInfo.setBuyCustomId(buyExEntrust.getCustomerId());
		exOrderInfo.setSellCustomId(sellentrust.getCustomerId());
		exOrderInfo.setWebsite("cn");
		exOrderInfo.setCurrencyType("cny");
		exOrderInfo.setFixPriceCoinCode(buyExEntrust.getFixPriceCoinCode());
		exOrderInfo.setFixPriceType(buyExEntrust.getFixPriceType());
		exOrderInfo.setCoinCode(buyExEntrust.getCoinCode());

		// 订单开始详细
		exOrderInfo.setBuyEntrustNum(buyExEntrust.getEntrustNum());
		exOrderInfo.setSellEntrustNum(sellentrust.getEntrustNum());

		exOrderInfo.setBuyUserName(buyExEntrust.getUserName());
		exOrderInfo.setSellUserName(sellentrust.getUserName());
		exOrderInfo.setTransactionTime(new Date());
		exOrderInfo.setOrderTimestapm(exOrderInfo.getTransactionTime().getTime());
		exOrderInfo.setIsTypeBuy(buyExEntrust.getIsType());
		exOrderInfo.setIsTypeSell(sellentrust.getIsType());
		exOrderInfo.setIfMining(0);
		// 订单结束详细
		return exOrderInfo;
	}

	@Override
	public ExOrder createExOrder(ExOrderInfo exOrderInfo) {

		// 订单开始
		ExOrder exOrder = new ExOrder();
		exOrder.setOrderNum(exOrderInfo.getOrderNum());
		exOrder.setTransactionTime(exOrderInfo.getTransactionTime());
		exOrder.setOrderTimestapm(exOrderInfo.getOrderTimestapm());
		exOrder.setSaasId(exOrderInfo.getSaasId());
		exOrder.setCurrencyType(exOrderInfo.getCurrencyType());
		exOrder.setWebsite(exOrderInfo.getWebsite());
		exOrder.setTransactionCount(exOrderInfo.getTransactionCount());
		exOrder.setTransactionPrice(exOrderInfo.getTransactionPrice());
		exOrder.setTransactionSum(exOrderInfo.getTransactionSum());
		exOrder.setCoinCode(exOrderInfo.getCoinCode());
		exOrder.setWebsite(exOrderInfo.getWebsite());
		exOrder.setCurrencyType(exOrderInfo.getCurrencyType());
		exOrder.setProductName(exOrderInfo.getProductName());
		exOrder.setInOrOutTransaction(exOrderInfo.getInOrOutTransaction());
		exOrder.setFixPriceCoinCode(exOrderInfo.getFixPriceCoinCode());
		exOrder.setFixPriceType(exOrderInfo.getFixPriceType());

		// 订单结束
		return exOrder;

	}

	@Resource
	private JedisPool jedisPool;

	@Override
	public void reidsToMysql() {
		Jedis jedis = jedisPool.getResource();
		try{
			Transaction transaction = jedis.multi();

			long start = System.currentTimeMillis();
			// 委托信息入库
			Map<String, EntrustTrade> map = new HashMap<String, EntrustTrade>();
			long start4 = System.currentTimeMillis();
			//	Set<String> keysTradeDealEntrustChange = redisService.noPerkeys(ExchangeDataCacheRedis.TradeDealEntrustChange + ":");
			String TradeDealEntrustChangeNumListkey="";
			String tradeDealEntrustChangeNumFlag=redisService.get(ExchangeDataCacheRedis.TradeDealEntrustChangeNumFlag);
			if(StringUtils.isEmpty(tradeDealEntrustChangeNumFlag)||tradeDealEntrustChangeNumFlag.equals("1")){
				TradeDealEntrustChangeNumListkey=ExchangeDataCacheRedis.TradeDealEntrustChangeNumList2;
			}else{
				TradeDealEntrustChangeNumListkey=ExchangeDataCacheRedis.TradeDealEntrustChangeNumList1;
			}
			String tradeDealEntrustChangeNumListstr=redisService.get(TradeDealEntrustChangeNumListkey);

			List<String> tradeDealEntrustChangeNumList =new ArrayList<String>();
			if(!StringUtils.isEmpty(tradeDealEntrustChangeNumListstr)){
				tradeDealEntrustChangeNumList = JSON.parseArray(tradeDealEntrustChangeNumListstr, String.class);
			}
			long end4 = System.currentTimeMillis();
			//	log.info("TradeDealEntrustChange入库总耗时：" + (end4 - start4) + "ms");
			List<BigDecimal> list2 = new ArrayList<BigDecimal>();
			for(String keystr:tradeDealEntrustChangeNumList){
				BigDecimal ks = new BigDecimal(keystr.split(":")[2]);
				list2.add(ks);
			}
			Collections.sort(list2, new AscBigDecimalComparator());
			long start5 = System.currentTimeMillis();
			for (BigDecimal l : list2) {
				String keystr = ExchangeDataCacheRedis.TradeDealEntrustChange + ":" + l;

				List<EntrustTrade> entrustTradeSlist = JSON.parseArray(redisService.get(keystr), EntrustTrade.class);
				if (null != entrustTradeSlist) {
					for (EntrustTrade es : entrustTradeSlist) {
						map.put(es.getEntrustNum(), es);
					}
				}
				//	redisService.delete(keystr);
			}
			long end5 = System.currentTimeMillis();
			//		log.info("TradeDealEntrustChange入库总耗时：" + (end5 - start5) + "ms");
			List<EntrustTrade> entrustlisted = new ArrayList<EntrustTrade>(map.values());
			if (null != entrustlisted && entrustlisted.size() > 0) {
				List<ExEntrust> entrustupdatelist = exEntrustDao.getExEntrustListByNumstoMysql(entrustlisted);
				List<EntrustTrade> entrustnewlist = new ArrayList<EntrustTrade>();
				if(null != entrustupdatelist && entrustupdatelist.size()>0){
					int k = 0;
					int size = entrustlisted.size();
					while (k < size) {
						int i = 0;
						EntrustTrade entrusted = entrustlisted.get(k);
						for (ExEntrust entrustUpdate : entrustupdatelist) {
							if (entrusted.getEntrustNum().equals(entrustUpdate.getEntrustNum())) {
								entrustUpdate.setStatusint(entrusted.getStatus().intValue());
								entrustUpdate.setSurplusEntrustCountDouble(entrusted.getSurplusEntrustCount().doubleValue());
								entrustUpdate.setTransactionSumDouble(entrusted.getTransactionSum().doubleValue());
								entrustUpdate.setTransactionFeeDouble(entrusted.getTransactionFee().doubleValue());
								if(null!=entrusted.getTransactionFeePlat()){
									entrustUpdate.setTransactionFeePlatDouble(entrusted.getTransactionFeePlat().doubleValue());
								}else{
									entrustUpdate.setTransactionFeePlatDouble(entrusted.getTransactionFeePlat().doubleValue());

								}
								if(null==entrusted.getProcessedPrice()){
									entrustUpdate.setProcessedPriceDouble(Double.valueOf("0"));
								}else{
									entrustUpdate.setProcessedPriceDouble(entrusted.getProcessedPrice().doubleValue());
								}

								entrustUpdate.setModified(new Date());
								i++;
								break;
							}
						}
						if (i == 0) {
							if(null==entrusted.getProcessedPrice()){
								entrusted.setProcessedPrice(new BigDecimal("0"));
							}
							entrustnewlist.add(entrusted);
						}
						k++;
					}
				}else{
					entrustnewlist=entrustlisted;
				}

				if (null != entrustupdatelist && entrustupdatelist.size() > 0) {//



					if(entrustupdatelist.size() < 5000){
						exEntrustDao.updateExEntrust(entrustupdatelist);
					}else{
						//总记录数
						Integer totalCount = entrustupdatelist.size();
						//分多少次处理
						Integer requestCount = totalCount / 5000;
						for (int i = 0; i <= requestCount; i++) {
							Integer fromIndex = i * 5000;
							//如果总数少于PAGE_SIZE,为了防止数组越界,toIndex直接使用totalCount即可
							int toIndex = Math.min(totalCount, (i + 1) * 5000);
							List<ExEntrust> subList = entrustupdatelist.subList(fromIndex, toIndex);
							exEntrustDao.updateExEntrust(subList);
							if (toIndex == totalCount) {
								break;
							}
						}
					}




				}

				if (null != entrustnewlist && entrustnewlist.size() > 0) {//



					if(entrustnewlist.size() < 5000){
						exEntrustDao.insertExEntrust(entrustnewlist);
					}else{
						//总记录数
						Integer totalCount = entrustnewlist.size();
						//分多少次处理
						Integer requestCount = totalCount / 5000;
						for (int i = 0; i <= requestCount; i++) {
							Integer fromIndex = i * 5000;
							//如果总数少于PAGE_SIZE,为了防止数组越界,toIndex直接使用totalCount即可
							int toIndex = Math.min(totalCount, (i + 1) * 5000);
							List<EntrustTrade> subList = entrustnewlist.subList(fromIndex, toIndex);
							exEntrustDao.insertExEntrust(subList);
							if (toIndex == totalCount) {
								break;
							}
						}
					}




				}

			}





			// 成交信息入库
			List<ExOrderInfo> eExOrderInfolistss = new ArrayList<ExOrderInfo>();
			//	Set<String> keysTradeDealOrderInfoChange = redisService.noPerkeys(ExchangeDataCacheRedis.TradeDealOrderInfoChange + ":");
			//	Iterator<String> iteratorTradeDealOrderInfoChange = keysTradeDealOrderInfoChange.iterator();

			String TradeDealOrderInfoChangeNumListkey="";
			String tradeDealOrderInfoChangeNumFlag=redisService.get(ExchangeDataCacheRedis.TradeDealOrderInfoChangeNumFlag);
			if(StringUtils.isEmpty(tradeDealOrderInfoChangeNumFlag)||tradeDealOrderInfoChangeNumFlag.equals("1")){
				TradeDealOrderInfoChangeNumListkey=ExchangeDataCacheRedis.TradeDealOrderInfoChangeNumList2;
			}else{
				TradeDealOrderInfoChangeNumListkey=ExchangeDataCacheRedis.TradeDealOrderInfoChangeNumList1;
			}
			String tradeDealOrderInfoChangeNumListstr=redisService.get(TradeDealOrderInfoChangeNumListkey);

			List<String> tradeDealOrderInfoChangeNumList =new ArrayList<String>();
			if(!StringUtils.isEmpty(tradeDealOrderInfoChangeNumListstr)){
				tradeDealOrderInfoChangeNumList = JSON.parseArray(tradeDealOrderInfoChangeNumListstr, String.class);
			}
			for(String keystr:tradeDealOrderInfoChangeNumList){
				List<ExOrderInfo> accountaddSlist = JSON.parseArray(redisService.get(keystr), ExOrderInfo.class);
				if (null != accountaddSlist) {
					eExOrderInfolistss.addAll(accountaddSlist);
				}
			}


			if (null != eExOrderInfolistss && eExOrderInfolistss.size() > 0) {

				if(eExOrderInfolistss.size() < 5000){
					exOrderInfoDao.insertExorderInfos(eExOrderInfolistss);
				}else{
					//总记录数
					Integer totalCount = eExOrderInfolistss.size();
					//分多少次处理
					Integer requestCount = totalCount / 5000;
					for (int i = 0; i <= requestCount; i++) {
						Integer fromIndex = i * 5000;
						//如果总数少于PAGE_SIZE,为了防止数组越界,toIndex直接使用totalCount即可
						int toIndex = Math.min(totalCount, (i + 1) * 5000);
						List<ExOrderInfo> subList = eExOrderInfolistss.subList(fromIndex, toIndex);
						exOrderInfoDao.insertExorderInfos(subList);
						if (toIndex == totalCount) {
							break;
						}
					}
				}


			}

			//	log.info("eExOrderInfolistss.size=" + eExOrderInfolistss.size());


			//最后才删
			transaction.del(TradeDealOrderInfoChangeNumListkey);
			for (String keystr:tradeDealOrderInfoChangeNumList) {
				transaction.del(keystr);
			}
			transaction.del(TradeDealEntrustChangeNumListkey);
			for (String keystr:tradeDealEntrustChangeNumList) {
				transaction.del(keystr);
			}
			if(StringUtils.isEmpty(tradeDealEntrustChangeNumFlag)||tradeDealEntrustChangeNumFlag.equals("1")){
				transaction.set(ExchangeDataCacheRedis.TradeDealEntrustChangeNumFlag, "2");
			}else{
				transaction.set(ExchangeDataCacheRedis.TradeDealEntrustChangeNumFlag, "1");
			}

			if(StringUtils.isEmpty(tradeDealOrderInfoChangeNumFlag)||tradeDealOrderInfoChangeNumFlag.equals("1")){
				transaction.set(ExchangeDataCacheRedis.TradeDealOrderInfoChangeNumFlag, "2");
			}else{
				transaction.set(ExchangeDataCacheRedis.TradeDealOrderInfoChangeNumFlag, "1");
			}
			long end = System.currentTimeMillis();
			long time=end - start;
			if(time>800){
				log.info("redis(委托单和成交单)入库总耗时：" + (time) + "ms");
			}
			transaction.exec();
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			jedis.close();
		}
	}

	@Override
	public void reidsToredisLog() {	// 资金流水入库
		long start = System.currentTimeMillis();
		Set<Long> setaccount = new HashSet<Long>();
		Set<Long> setaccountcoin = new HashSet<Long>();
		List<Accountadd> accountaddSlistss = new ArrayList<Accountadd>();
	//	Set<String> keysTradeDealAccountChange = redisService.noPerkeys(ExchangeDataCacheRedis.TradeDealAccountChange + ":");
	//	Iterator<String> iteratorTradeDealAccountChange = keysTradeDealAccountChange.iterator();
		String TradeDealAccountChangeNumListkey="";
		String tradeDealAccountChangeNumFlag=redisService.get(ExchangeDataCacheRedis.TradeDealAccountChangeNumFlag);
		if(StringUtils.isEmpty(tradeDealAccountChangeNumFlag)||tradeDealAccountChangeNumFlag.equals("1")){
			TradeDealAccountChangeNumListkey=ExchangeDataCacheRedis.TradeDealAccountChangeNumList2;
		}else{
			TradeDealAccountChangeNumListkey=ExchangeDataCacheRedis.TradeDealAccountChangeNumList1;
		}
		String TradeDealAccountChangeNumListstr=redisService.get(TradeDealAccountChangeNumListkey);
		
		
		List<String> tradeDealAccountChangeNumList =new ArrayList<String>();
		if(!StringUtils.isEmpty(TradeDealAccountChangeNumListstr)){
			tradeDealAccountChangeNumList = JSON.parseArray(TradeDealAccountChangeNumListstr, String.class);
		}
		for (String keystr:tradeDealAccountChangeNumList) {
			List<Accountadd> accountaddSlist = JSON.parseArray(redisService.get(keystr), Accountadd.class);
			if (null != accountaddSlist) {
				accountaddSlistss.addAll(accountaddSlist);
			}
			//redisService.delete(keystr);
		}
		List<AppHotAccountRecord> listahar=new ArrayList<AppHotAccountRecord>();
		List<AppColdAccountRecord> listacar=new ArrayList<AppColdAccountRecord>();
		List<ExDmHotAccountRecord> listehar=new ArrayList<ExDmHotAccountRecord>();
		List<ExDmColdAccountRecord> listedcar=new ArrayList<ExDmColdAccountRecord>();
		if (null != accountaddSlistss) {
			for (Accountadd accountadd : accountaddSlistss) {
				if (accountadd.getAcccountType().equals(0)) { // 资金账户
					AppAccount appAccount = appAccountService.get(accountadd.getAccountId());
					if(null!=appAccount){
						if (accountadd.getMonteyType().equals(1)) { // 热账户
							if (accountadd.getMoney().compareTo(BigDecimal.ZERO) == -1) {
								AppHotAccountRecord appHotAccountRecord = appAccountService.createHotRecord(2, appAccount, BigDecimal.ZERO.subtract(accountadd.getMoney()),accountadd.getBalanceMoney(), accountadd.getTransactionNum(),accountadd.getOrderNum(), accountadd.getRemarks());
								listahar.add(appHotAccountRecord);
							} else if (accountadd.getMoney().compareTo(BigDecimal.ZERO) == 1) {
								AppHotAccountRecord appHotAccountRecord = appAccountService.createHotRecord(1, appAccount, accountadd.getMoney(),accountadd.getBalanceMoney(), accountadd.getTransactionNum(),accountadd.getOrderNum(), accountadd.getRemarks());
								listahar.add(appHotAccountRecord);
							}

						} else { // 冷账户
							if (accountadd.getMoney().compareTo(BigDecimal.ZERO) == -1) {
								AppColdAccountRecord AppColdAccountRecord = appAccountService.createColdRecord(2, appAccount, BigDecimal.ZERO.subtract(accountadd.getMoney()),accountadd.getBalanceMoney(), accountadd.getTransactionNum(),accountadd.getOrderNum(), accountadd.getRemarks());
								listacar.add(AppColdAccountRecord);
							} else if (accountadd.getMoney().compareTo(BigDecimal.ZERO) == 1) {
								AppColdAccountRecord AppColdAccountRecord = appAccountService.createColdRecord(1, appAccount, accountadd.getMoney(),accountadd.getBalanceMoney(), accountadd.getTransactionNum(),accountadd.getOrderNum(), accountadd.getRemarks());
								listacar.add(AppColdAccountRecord);
							}
						}
						setaccount.add(appAccount.getId());
					}
					
				} else {// 币账户
					ExDigitalmoneyAccount exDigitalmoneyAccount = exDigitalmoneyAccountService.get(accountadd.getAccountId());
					if(null!=exDigitalmoneyAccount){
						if (accountadd.getMonteyType().equals(1)) { // 热账户
							if (accountadd.getMoney().compareTo(BigDecimal.ZERO) == -1) {
								ExDmHotAccountRecord exDmHotAccountRecord = exDigitalmoneyAccountService.createHotRecord(2, exDigitalmoneyAccount, BigDecimal.ZERO.subtract(accountadd.getMoney()),accountadd.getBalanceMoney(), accountadd.getTransactionNum(),accountadd.getOrderNum(), accountadd.getRemarks());
								listehar.add(exDmHotAccountRecord);
							} else if (accountadd.getMoney().compareTo(BigDecimal.ZERO) == 1) {
								ExDmHotAccountRecord exDmHotAccountRecord = exDigitalmoneyAccountService.createHotRecord(1, exDigitalmoneyAccount, accountadd.getMoney(),accountadd.getBalanceMoney(),  accountadd.getTransactionNum(),accountadd.getOrderNum(), accountadd.getRemarks());
							 	listehar.add(exDmHotAccountRecord);
							}
						} else { // 冷账户
							if (accountadd.getMoney().compareTo(BigDecimal.ZERO) == -1) {
								ExDmColdAccountRecord exDmColdAccountRecord = exDigitalmoneyAccountService.createColdRecord(2, exDigitalmoneyAccount, BigDecimal.ZERO.subtract(accountadd.getMoney()),accountadd.getBalanceMoney(),  accountadd.getTransactionNum(), accountadd.getOrderNum(),accountadd.getRemarks());
								listedcar.add(exDmColdAccountRecord);
							} else if (accountadd.getMoney().compareTo(BigDecimal.ZERO) == 1) {
								ExDmColdAccountRecord exDmColdAccountRecord = exDigitalmoneyAccountService.createColdRecord(1, exDigitalmoneyAccount, accountadd.getMoney(),accountadd.getBalanceMoney(),  accountadd.getTransactionNum(),accountadd.getOrderNum(), accountadd.getRemarks());
								listedcar.add(exDmColdAccountRecord);
							}
						}

						setaccountcoin.add(exDigitalmoneyAccount.getId());
					}
					
				}
			}
		}
		
		
		//批量入库流水记录
		List<AppHotAccountRecord> listahar1=new ArrayList<AppHotAccountRecord>(); 
		for(AppHotAccountRecord ahar:listahar){
			if(ahar.getTransactionMoney().compareTo(new BigDecimal("9999999999"))==-1){
				listahar1.add(ahar);
			}
		}
		List<AppColdAccountRecord> listacar1=new ArrayList<AppColdAccountRecord>(); 
		for(AppColdAccountRecord ahar:listacar){
			if(ahar.getTransactionMoney().compareTo(new BigDecimal("9999999999"))==-1){
				listacar1.add(ahar);
			}
		}
		List<ExDmHotAccountRecord> listehar1=new ArrayList<ExDmHotAccountRecord>(); 
		for(ExDmHotAccountRecord ehar:listehar){
			if(ehar.getTransactionMoney().compareTo(new BigDecimal("9999999999"))==-1){
				listehar1.add(ehar);
			}
		}
		List<ExDmColdAccountRecord> listedcar1=new ArrayList<ExDmColdAccountRecord>(); 
		for(ExDmColdAccountRecord dcar:listedcar){
			if(dcar.getTransactionMoney().compareTo(new BigDecimal("9999999999"))==-1){
				listedcar1.add(dcar);
			}
		}
		if (null != listahar1 && listahar1.size() > 0) {
			if(listahar1.size() < 5000){
			   appHotAccountRecordDao.insertRecord(listahar1);
			}else{
				 //总记录数
		        Integer totalCount = listahar1.size();
		        //分多少次处理
		        Integer requestCount = totalCount / 5000;
		        for (int i = 0; i <= requestCount; i++) {
		            Integer fromIndex = i * 5000;
		            //如果总数少于PAGE_SIZE,为了防止数组越界,toIndex直接使用totalCount即可
		            int toIndex = Math.min(totalCount, (i + 1) * 5000);
		            List<AppHotAccountRecord> subList = listahar1.subList(fromIndex, toIndex);
		            appHotAccountRecordDao.insertRecord(subList);
		            if (toIndex == totalCount) {
		                break;
		            }
		        }
			}
			
		}
		if (null != listacar1 && listacar1.size() > 0) {
			if(listacar1.size() < 5000){
			   appColdAccountRecordDao.insertRecord(listacar1);
			}else{
				 //总记录数
		        Integer totalCount = listacar1.size();
		        //分多少次处理
		        Integer requestCount = totalCount / 5000;
		        for (int i = 0; i <= requestCount; i++) {
		            Integer fromIndex = i * 5000;
		            //如果总数少于PAGE_SIZE,为了防止数组越界,toIndex直接使用totalCount即可
		            int toIndex = Math.min(totalCount, (i + 1) * 5000);
		            List<AppColdAccountRecord> subList = listacar1.subList(fromIndex, toIndex);
		            appColdAccountRecordDao.insertRecord(subList);
		            if (toIndex == totalCount) {
		                break;
		            }
		        }
			
				
			}
		}
		if (null != listehar1 && listehar1.size() > 0) {
			if(listehar1.size() < 5000){
				exDmHotAccountRecordDao.insertRecord(listehar1);
			}else{
				 //总记录数
		        Integer totalCount = listehar1.size();
		        //分多少次处理
		        Integer requestCount = totalCount / 5000;
		        for (int i = 0; i <= requestCount; i++) {
		            Integer fromIndex = i * 5000;
		            //如果总数少于PAGE_SIZE,为了防止数组越界,toIndex直接使用totalCount即可
		            int toIndex = Math.min(totalCount, (i + 1) * 5000);
		            List<ExDmHotAccountRecord> subList = listehar1.subList(fromIndex, toIndex);
		            exDmHotAccountRecordDao.insertRecord(subList);
		            if (toIndex == totalCount) {
		                break;
		            }
		        }
			
				
			
			}
		
		}
		if (null != listedcar1 && listedcar1.size() > 0) {
			if(listedcar1.size() < 5000){
				exDmColdAccountRecordDao.insertRecord(listedcar1);
			}else{
				 //总记录数
		        Integer totalCount = listedcar1.size();
		        //分多少次处理
		        Integer requestCount = totalCount / 5000;
		        for (int i = 0; i <= requestCount; i++) {
		            Integer fromIndex = i * 5000;
		            //如果总数少于PAGE_SIZE,为了防止数组越界,toIndex直接使用totalCount即可
		            int toIndex = Math.min(totalCount, (i + 1) * 5000);
		            List<ExDmColdAccountRecord> subList = listedcar1.subList(fromIndex, toIndex);
		            exDmColdAccountRecordDao.insertRecord(subList);
		            if (toIndex == totalCount) {
		                break;
		            }
		        }
			}
			
		}
    	
    
    	
		// 账户批量入库
		Iterator<Long> iteratora = setaccount.iterator();
		List<AppAccountRedis> lista=new ArrayList<AppAccountRedis>();
		while (iteratora.hasNext()) {
			Long id = iteratora.next();
			AppAccountRedis appAccountredis = appAccountService.getAppAccountByRedis(id.toString());
			appAccountredis.setHotMoneyDouble(appAccountredis.getHotMoney().doubleValue());
			appAccountredis.setColdMoneyDouble(appAccountredis.getColdMoney().doubleValue());
			
			if(appAccountredis.getHotMoneyDouble().compareTo(new Double("9999999999"))==-1&&
					appAccountredis.getHotMoneyDouble().compareTo(new Double("-9999999999"))==1&&
					appAccountredis.getColdMoneyDouble().compareTo(new Double("9999999999"))==-1&&
					appAccountredis.getColdMoneyDouble().compareTo(new Double("-9999999999"))==1){
				lista.add(appAccountredis);
			}else{
				log.info("appAccountredis.getColdMoneyDouble()="+appAccountredis.getCustomerId()+"==" + appAccountredis.getColdMoneyDouble());
				log.info("appAccountredis.getHotMoneyDouble()="+appAccountredis.getCustomerId() +"=="+ appAccountredis.getHotMoneyDouble());
			}
		}
		if(null!=lista&&lista.size()>0){
			appAccountDao.updateAppAccount(lista);
		}
		Iterator<Long> iteratorc = setaccountcoin.iterator();
		List<ExDigitalmoneyAccountRedis> listd=new ArrayList<ExDigitalmoneyAccountRedis>();
		while (iteratorc.hasNext()) {
			Long id = iteratorc.next();
			ExDigitalmoneyAccountRedis exDigitalmoneyAccountredis = exDigitalmoneyAccountService.getExDigitalmoneyAccountByRedis(id.toString());
			exDigitalmoneyAccountredis.setHotMoneyDouble(exDigitalmoneyAccountredis.getHotMoney().doubleValue());
			exDigitalmoneyAccountredis.setColdMoneyDouble(exDigitalmoneyAccountredis.getColdMoney().doubleValue());
			if(exDigitalmoneyAccountredis.getHotMoneyDouble().compareTo(new Double("9999999999"))==-1&&
					exDigitalmoneyAccountredis.getHotMoneyDouble().compareTo(new Double("-9999999999"))==1&&
					exDigitalmoneyAccountredis.getColdMoneyDouble().compareTo(new Double("9999999999"))==-1&&
					exDigitalmoneyAccountredis.getColdMoneyDouble().compareTo(new Double("-9999999999"))==1){
				listd.add(exDigitalmoneyAccountredis);
			}else{
				log.info("exDigitalmoneyAccountredis.getColdMoneyDouble()="+exDigitalmoneyAccountredis.getCustomerId()+"==" + exDigitalmoneyAccountredis.getColdMoneyDouble());
				log.info("exDigitalmoneyAccountredis.getHotMoneyDouble()="+exDigitalmoneyAccountredis.getCustomerId() +"=="+ exDigitalmoneyAccountredis.getHotMoneyDouble());
			}
		}
		if(null!=listd&&listd.size()>0){
			exDigitalmoneyAccountDao.updateExDigitalmoneyAccount(listd);
		}
	
		
		for (String keystr:tradeDealAccountChangeNumList) {
			redisService.delete(keystr);
		}
		redisService.delete(TradeDealAccountChangeNumListkey);
	
		
		if(StringUtils.isEmpty(tradeDealAccountChangeNumFlag)||tradeDealAccountChangeNumFlag.equals("1")){
			redisService.save(ExchangeDataCacheRedis.TradeDealAccountChangeNumFlag, "2");
		}else{
			redisService.save(ExchangeDataCacheRedis.TradeDealAccountChangeNumFlag, "1");
		}
		long end = System.currentTimeMillis();
		long time=end - start;
		if(time>800){
			log.info("accountredis（账户和资金流水）入库总耗时：" + (time) + "ms");
		}
		
		
		
		
	}

	@Override
	public void reidsToMysqlmq() {
		MessageProducer messageProducer = (MessageProducer) ContextUtil.getBean("messageProducer");
		messageProducer.reidsToMysql("111");
		//System.out.println("发送定时消息");
	}

	@Override
	public void reidsToredisLogmq() {
		MessageProducer messageProducer = (MessageProducer) ContextUtil.getBean("messageProducer");
		messageProducer.reidsToRedisLog("333");

	}
	
	
}
