/**
 * Copyright:   
 * @author:      yaozhuo
 * @version:     V1.0 
 * @Date:        2019-04-19 11:31:31 
 */
package hry.klg.buysellaccount.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.front.redis.model.UserRedis;
import hry.klg.assetsrecord.model.KlgAssetsRecord;
import hry.klg.assetsrecord.service.KlgAssetsRecordService;
import hry.klg.buysellaccount.dao.KlgBuySellAccountDao;
import hry.klg.buysellaccount.model.KlgBuySellAccount;
import hry.klg.buysellaccount.model.KlgBuySellAccountrecordRecord;
import hry.klg.buysellaccount.service.KlgBuySellAccountService;
import hry.klg.buysellaccount.service.KlgBuySellAccountrecordRecordService;
import hry.klg.enums.TriggerPointEnum;
import hry.klg.level.service.KlgCustomerLevelService;
import hry.klg.log.model.KlgExceptionLog;
import hry.klg.log.service.KlgExceptionLogService;
import hry.klg.model.KlgPlatformCurrency;
import hry.klg.model.PlatformAccountadd;
import hry.klg.platform.model.PlatformAccountUtil;
import hry.klg.remote.RemoteKlgService;
import hry.klg.remote.model.ToBuySellAccountMessage;
import hry.klg.transaction.model.KlgBuyTransaction;
import hry.klg.transaction.model.KlgSellTransaction;
import hry.klg.transaction.service.KlgBuyTransactionService;
import hry.klg.transaction.service.KlgSellTransactionService;
import hry.klg.utils.BuySellAccountType;
import hry.message.model.MessageType;
import hry.message.model.RemoteMessage;
import hry.mq.producer.service.MessageProducer;
import hry.redis.common.dao.RedisUtil;
import hry.redis.common.utils.RedisService;
import hry.trade.redis.model.Accountadd;
import hry.trade.redis.model.ExDigitalmoneyAccountRedis;
import hry.util.QueryFilter;
import hry.util.SpringUtil;
import hry.util.idgenerate.IdGenerate;
import hry.util.idgenerate.NumConstant;
import hry.web.message.service.AppMessageService;
import util.UserRedisUtils;

/**
 * <p>
 * KlgBuySellAccountService
 * </p>
 * 
 * @author: yaozhuo
 * @Date : 2019-04-19 11:31:31
 */
@Service("klgBuySellAccountService")
public class KlgBuySellAccountServiceImpl extends BaseServiceImpl<KlgBuySellAccount, Long>
		implements KlgBuySellAccountService {

	@Resource(name = "klgBuySellAccountDao")
	@Override
	public void setDao(BaseDao<KlgBuySellAccount, Long> dao) {
		super.dao = dao;
	}

	@Resource
	private KlgBuyTransactionService klgBuyTransactionService;
	@Resource
	private MessageProducer messageProducer;
	@Resource
	private RedisService redisService;
	@Resource
	private AppMessageService appMessageService;
	@Resource
	private KlgSellTransactionService klgSellTransactionService;
	@Resource
	private RemoteKlgService remoteKlgService;
	@Resource
	private KlgBuySellAccountrecordRecordService klgBuySellAccountrecordRecordService;
	@Resource
	private KlgAssetsRecordService klgAssetsRecordService;
	@Resource
	private KlgCustomerLevelService klgCustomerLevelService;

	@Override
	public void toBuySellAccountMessage(List<ToBuySellAccountMessage> list) {
		// TODO Auto-generated method stub

		for (ToBuySellAccountMessage toBuySellAccountMessage : list) {
			try {
				// 判断变动账户类型
				String accountType = toBuySellAccountMessage.getAcconutType();
				// 消息类型 1.匹配 2.调控 3.吃单 4.抢单 5.支付 6.买卖剩余账户转出
				switch (toBuySellAccountMessage.getMessageType()) {
				case 2:
					// 平台调控
					this.regulation(toBuySellAccountMessage);
					break;
				case 6:
					// 买卖剩余账户转出
					this.outSurplus(toBuySellAccountMessage);
					break;
				default:// 1 3 4 5
					if (accountType.equals(BuySellAccountType.BUYSURPLUSACCOUNT)) {
						// 买单支付剩余变动
						this.buySurplusAccount(toBuySellAccountMessage);
					} else if (accountType.equals(BuySellAccountType.SELLSURPLUSACCOUNT)) {
						// 卖单卖出剩余变动
						this.sellSurplusAccount(toBuySellAccountMessage);
					}
					break;
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				KlgExceptionLogService klgExceptionLogService = SpringUtil.getBean("klgExceptionLogService");
				// 插入异常日志
				KlgExceptionLog klgExceptionLog = new KlgExceptionLog();
				String str = JSON.toJSONString(toBuySellAccountMessage);
				str = "toBuySellAccountMessage消息:"+str;
				if (str.length() <= 230) {
					klgExceptionLog.setFunctionName(str);
				} else {
					klgExceptionLog.setFunctionName(str.substring(0, 230));
				}
				String strE = e.toString();
				if (strE.length() <= 230) {
					klgExceptionLog.setExceptionText(strE);
				} else {
					klgExceptionLog.setExceptionText(strE.substring(0, 230));
				}
				klgExceptionLogService.save(klgExceptionLog);
			}
			

		}

		// 清理卖单
		this.clearSellOrder();
		// 清理买单
		this.clearBuyOrder();

	}

	/**
	 * 处理买单变动逻辑
	 * 
	 * @param toBuySellAccountMessage
	 */
	private void buySurplusAccount(ToBuySellAccountMessage toBuySellAccountMessage) {

		/* 查询变动订单号 */
		QueryFilter filterKlgBuyTransaction = new QueryFilter(KlgBuyTransaction.class);
		filterKlgBuyTransaction.addFilter("transactionNum=", toBuySellAccountMessage.getTransactionNum());
		KlgBuyTransaction klgBuyTransaction = klgBuyTransactionService.get(filterKlgBuyTransaction);
		if (klgBuyTransaction != null) {
			/** 判断是否是平台吃单 */
			if (klgBuyTransaction.getEatStatus() == 2) {
				// 是平台吃单
				this.chidan(klgBuyTransaction);
			} else {
				// 不是平台吃单
				// 买方支付剩余
				this.buyPaySurplus(klgBuyTransaction);
			}
		}

	}

	/**
	 * 处理卖单变动逻辑
	 * 
	 * @param toBuySellAccountMessage
	 */
	private void sellSurplusAccount(ToBuySellAccountMessage toBuySellAccountMessage) {
		String ptbCoinCode = (String) remoteKlgService.getPlatformRule1sConfig("coinCode");
		List<Accountadd> list = new ArrayList<Accountadd>();
		List<KlgAssetsRecord> listKlgAssetsRecord = new ArrayList<>();// 记录流水
		KlgAssetsRecord klgAssetsRecordCold = null;
		/** 查询变动订单号信息 */
		QueryFilter filterKlgSellTransaction = new QueryFilter(KlgSellTransaction.class);
		filterKlgSellTransaction.addFilter("transactionNum=", toBuySellAccountMessage.getTransactionNum());
		KlgSellTransaction klgSellTransaction = klgSellTransactionService.get(filterKlgSellTransaction);
		if (klgSellTransaction != null) {
			// 查询SME币账户信息 冻结减少
			ExDigitalmoneyAccountRedis exaccountSme = this.selectAccount(klgSellTransaction.getCustomerId(),
					ptbCoinCode);
			if (exaccountSme != null) {
				list.add(getAccountAdd(exaccountSme.getId(), new BigDecimal("-"+klgSellTransaction.getSmeMoney()), 2, 1, 11,
						klgSellTransaction.getTransactionNum()));
				klgAssetsRecordCold = new KlgAssetsRecord(exaccountSme.getCustomerId(), exaccountSme.getId(),
						IdGenerate.transactionNum(NumConstant.Ex_Dm_Transaction), 2, exaccountSme.getCoinCode(),
						klgSellTransaction.getSmeMoney(), 2, TriggerPointEnum.SellOut.getKey(),
						klgSellTransaction.getTransactionNum(), "卖出,平台卖出剩余增加");
				// 修改订单状态为待收款
				klgSellTransaction.setStatus(3);
				klgSellTransactionService.update(klgSellTransaction);
				// 平台卖出剩余账户增加
				this.updateMoneyByAccountName("sellSurplusAccount",
						klgSellTransaction.getSmeMoney().add(klgSellTransaction.getCandySmeMoney()), 1,
						klgSellTransaction.getTransactionNum(), "", "卖单排队成功进入待收款状态");

				listKlgAssetsRecord.add(klgAssetsRecordCold);
				klgAssetsRecordService.saveAll(listKlgAssetsRecord);// 插入币账户流水记录
				// 发送币账户消息
				messageProducer.toAccount(JSON.toJSONString(list));
			}

		}

	}

	/**
	 * 平台调控
	 * 
	 * @param toBuySellAccountMessage
	 */
	private void regulation(ToBuySellAccountMessage toBuySellAccountMessage) {
		this.updateMoneyByAccountName(toBuySellAccountMessage.getAcconutType(),
				toBuySellAccountMessage.getChangeMoney(), 1, "", "", "平台调控");
		// 粮食局账户SME减少
		List<PlatformAccountadd> platformAccounts = new ArrayList<>();// 平台账户
		PlatformAccountadd prizeadd = null;
		if (toBuySellAccountMessage.getAcconutType().equals(BuySellAccountType.BUYSURPLUSACCOUNT)) {
			// 调控支付剩余账户，需要粮食局USDT减少
			prizeadd = PlatformAccountUtil.accountSub(KlgPlatformCurrency.USDTFinanceBureauAccount.getKey(),
					String.valueOf(toBuySellAccountMessage.getChangeMoney()),"平台调控");
			platformAccounts.add(prizeadd);
		} else if (toBuySellAccountMessage.getAcconutType().equals(BuySellAccountType.SELLSURPLUSACCOUNT)) {
			// 调控卖出剩余账户，需要粮食局SME减少
			prizeadd = PlatformAccountUtil.accountSub(KlgPlatformCurrency.SMEFoodBureauAccount.getKey(),
					String.valueOf(toBuySellAccountMessage.getChangeMoney()),"平台调控");
			platformAccounts.add(prizeadd);
		}
		messageProducer.toPlatformCurrency(JSON.toJSONString(platformAccounts));
	}
	/**
	 * 平台买卖剩余账户转出
	 * 
	 * @param toBuySellAccountMessage
	 */
	private void outSurplus(ToBuySellAccountMessage toBuySellAccountMessage) {
		this.updateMoneyByAccountName(toBuySellAccountMessage.getAcconutType(),
				toBuySellAccountMessage.getChangeMoney(), 2, "", "", "剩余转出");
		List<PlatformAccountadd> platformAccounts = new ArrayList<>();// 平台账户
		PlatformAccountadd prizeadd = null;
		if (toBuySellAccountMessage.getAcconutType().equals(BuySellAccountType.BUYSURPLUSACCOUNT)) {
			//需要USDT财政局账户增加
			prizeadd = PlatformAccountUtil.accountAdd(KlgPlatformCurrency.USDTFinanceBureauAccount.getKey(),
					String.valueOf(toBuySellAccountMessage.getChangeMoney()),"剩余转出");
			platformAccounts.add(prizeadd);
		} else if (toBuySellAccountMessage.getAcconutType().equals(BuySellAccountType.SELLSURPLUSACCOUNT)) {
			//SME粮食局账户增加
			prizeadd = PlatformAccountUtil.accountAdd(KlgPlatformCurrency.SMEFoodBureauAccount.getKey(),
					String.valueOf(toBuySellAccountMessage.getChangeMoney()),"剩余转出");
			platformAccounts.add(prizeadd);
		}
		messageProducer.toPlatformCurrency(JSON.toJSONString(platformAccounts));
	}

	/**
	 * 吃单类型订单支付
	 * 
	 * @param klgBuyTransaction
	 */
	private void chidan(KlgBuyTransaction klgBuyTransaction) {
		String ptbCoinCode = (String) remoteKlgService.getPlatformRule1sConfig("coinCode");
		List<Accountadd> list = new ArrayList<Accountadd>();
		List<KlgAssetsRecord> listKlgAssetsRecord = new ArrayList<>();// 记录流水
		KlgAssetsRecord klgAssetsRecordHot = null;
		KlgAssetsRecord klgAssetsRecordCold = null;
		// 卖单卖出剩余账户>买单买入的SME数量 ,清理买单
		// 查询买单SME账户信息 增加
		ExDigitalmoneyAccountRedis exaccountBuySme = this.selectAccount(klgBuyTransaction.getCustomerId(), ptbCoinCode);
		list.add(getAccountAdd(exaccountBuySme.getId(), klgBuyTransaction.getSmeMoney(), 1, 1, 9,
				klgBuyTransaction.getTransactionNum()));
		klgAssetsRecordHot = new KlgAssetsRecord(exaccountBuySme.getCustomerId(), exaccountBuySme.getId(),
				IdGenerate.transactionNum(NumConstant.Ex_Dm_Transaction), 1, exaccountBuySme.getCoinCode(),
				klgBuyTransaction.getSmeMoney(), 1, TriggerPointEnum.Purchase.getKey(),
				klgBuyTransaction.getTransactionNum(), "APP支付,平台吃单,买入");
		// 查询USDT账户，USDT冻结减少
		ExDigitalmoneyAccountRedis exaccountBuyUsdt = this.selectAccount(klgBuyTransaction.getCustomerId(),
				klgBuyTransaction.getCoinCode());
		list.add(getAccountAdd(exaccountBuyUsdt.getId(), new BigDecimal("-" + klgBuyTransaction.getUsdtMoney()), 2, 1,
				2, klgBuyTransaction.getTransactionNum()));
		klgAssetsRecordCold = new KlgAssetsRecord(exaccountBuyUsdt.getCustomerId(), exaccountBuyUsdt.getId(),
				IdGenerate.transactionNum(NumConstant.Ex_Dm_Transaction), 2, exaccountBuyUsdt.getCoinCode(),
				klgBuyTransaction.getUsdtMoney(), 2, TriggerPointEnum.Purchase.getKey(),
				klgBuyTransaction.getTransactionNum(), "APP支付,平台吃单,买入");
		listKlgAssetsRecord.add(klgAssetsRecordHot);
		listKlgAssetsRecord.add(klgAssetsRecordCold);
		klgAssetsRecordService.saveAll(listKlgAssetsRecord);// 插入币账户流水记录
		// 修改订单状态
		klgBuyTransaction.setStatus(4);
		klgBuyTransactionService.update(klgBuyTransaction);
		// 粮食局账户SME减少
		List<PlatformAccountadd> platformAccounts = new ArrayList<>();// 平台账户
		PlatformAccountadd prizeadd = null;
		prizeadd = PlatformAccountUtil.accountSub(KlgPlatformCurrency.SMEFoodBureauAccount.getKey(),
				String.valueOf(klgBuyTransaction.getSmeMoney()),"平台吃单:"+klgBuyTransaction.getTransactionNum());
		platformAccounts.add(prizeadd);
		// USDT财政局账户增加
		prizeadd = PlatformAccountUtil.accountAdd(KlgPlatformCurrency.USDTFinanceBureauAccount.getKey(),
				String.valueOf(klgBuyTransaction.getUsdtMoney()),"平台吃单:"+klgBuyTransaction.getTransactionNum());
		platformAccounts.add(prizeadd);
		messageProducer.toPlatformCurrency(JSON.toJSONString(platformAccounts));
		// 发送币账户消息
		messageProducer.toAccount(JSON.toJSONString(list));
	}

	/**
	 * 买方支付剩余
	 * 
	 * @param klgBuyTransaction
	 */
	private void buyPaySurplus(KlgBuyTransaction klgBuyTransaction) {
		List<Accountadd> list = new ArrayList<Accountadd>();
		List<KlgAssetsRecord> listKlgAssetsRecord = new ArrayList<>();// 记录流水
		/** 查询买单USDT币账户信息，冻结减少，平台买单支付剩余增加 */
		ExDigitalmoneyAccountRedis exaccountUsdt = this.selectAccount(klgBuyTransaction.getCustomerId(), klgBuyTransaction.getCoinCode());
		list.add(getAccountAdd(exaccountUsdt.getId(), new BigDecimal("-"+klgBuyTransaction.getUsdtMoney()), 2, 1, 2,
				klgBuyTransaction.getTransactionNum()));
		KlgAssetsRecord klgAssetsRecordCold = new KlgAssetsRecord(exaccountUsdt.getCustomerId(), exaccountUsdt.getId(),
				IdGenerate.transactionNum(NumConstant.Ex_Dm_Transaction), 2, exaccountUsdt.getCoinCode(),
				klgBuyTransaction.getUsdtMoney(), 2, TriggerPointEnum.BuyPayTailMoney.getKey(),
				klgBuyTransaction.getTransactionNum(), "APP买方支付剩余,平台支付剩余增加");
		this.updateMoneyByAccountName("buySurplusAccount", klgBuyTransaction.getUsdtMoney(), 1,
				klgBuyTransaction.getTransactionNum(), "", "买方支付剩余");

		listKlgAssetsRecord.add(klgAssetsRecordCold);
		klgAssetsRecordService.saveAll(listKlgAssetsRecord);// 插入币账户流水记录
		// 发送币账户消息
		messageProducer.toAccount(JSON.toJSONString(list));

	}

	/**
	 * 清理卖单
	 */
	private void clearSellOrder() {
		List<Accountadd> list = new ArrayList<Accountadd>();
		List<KlgAssetsRecord> listKlgAssetsRecord = new ArrayList<>();// 记录流水
		KlgAssetsRecord klgAssetsRecordHot = null;
		/** 查询买单支付剩余账户信息 */
		QueryFilter filterKlgBuyAccount = new QueryFilter(KlgBuySellAccount.class);
		filterKlgBuyAccount.addFilter("accountName=", "buySurplusAccount");
		KlgBuySellAccount klgBuyAccount = super.get(filterKlgBuyAccount);
		/** 查询所有待收款状态的卖单 */
		QueryFilter filterKlgSellTransaction = new QueryFilter(KlgSellTransaction.class);
		filterKlgSellTransaction.addFilter("status=", 3);
		filterKlgSellTransaction.setOrderby("timeStamp asc");
		List<KlgSellTransaction> listKlgSellTransaction = klgSellTransactionService.find(filterKlgSellTransaction);
		// 遍历卖单，满足添加清单
		if (listKlgSellTransaction != null && listKlgSellTransaction.size() > 0) {
			for (KlgSellTransaction klgSellTransaction : listKlgSellTransaction) {
				try {
					list = new ArrayList<Accountadd>();
					klgBuyAccount = super.get(filterKlgBuyAccount);
					// 对比卖单 卖出sme获得USDT+卖出糖果获得USDT总数
					BigDecimal sellUsdt = (klgSellTransaction.getSmeMoney().add(klgSellTransaction.getCandySmeMoney()))
							.multiply(klgSellTransaction.getSmeusdtRate());
					BigDecimal accountMoney = klgBuyAccount.getMoney();
					if (accountMoney.compareTo(sellUsdt) >= 0) {
						// 买单支付剩余大于等于卖单需求USDT数量，清理卖单，卖方USDT热账户增加，买单支付剩余账户减少
						// 修改卖单状态为已完成
						klgSellTransaction.setStatus(4);
						klgSellTransactionService.update(klgSellTransaction);
						// 查询卖方USDT热账户 增加
						// 计算卖单应获取的USDT总数
						BigDecimal sellSum = (klgSellTransaction.getSmeMoney()
								.add((klgSellTransaction.getCandySmeMoney().multiply(klgSellTransaction.getOneselfRate().multiply(new BigDecimal(0.01)))))
										.multiply(klgSellTransaction.getSmeusdtRate()));
						ExDigitalmoneyAccountRedis exaccountSellUsdt = this
								.selectAccount(klgSellTransaction.getCustomerId(), klgSellTransaction.getCoinCode());
						list.add(getAccountAdd(exaccountSellUsdt.getId(), sellSum, 1, 1, 7,
								klgSellTransaction.getTransactionNum()));
						klgAssetsRecordHot = new KlgAssetsRecord(exaccountSellUsdt.getCustomerId(), exaccountSellUsdt.getId(),
								IdGenerate.transactionNum(NumConstant.Ex_Dm_Transaction), 1, exaccountSellUsdt.getCoinCode(),
								sellSum, 1, TriggerPointEnum.SellOut.getKey(), klgSellTransaction.getTransactionNum(), "卖出");
						this.updateMoneyByAccountName("buySurplusAccount", sellUsdt, 2,
								klgSellTransaction.getTransactionNum(), "", "清理卖单");
						listKlgAssetsRecord.add(klgAssetsRecordHot);
						
						//糖果账户减少
						List<PlatformAccountadd> platformAccounts = new ArrayList<>();// 平台账户
						PlatformAccountadd prizeadd = null;
						prizeadd = PlatformAccountUtil.accountSub(KlgPlatformCurrency.SMECandyAccount.getKey(),
								String.valueOf(klgSellTransaction.getCandySmeMoney()),"平台吃单");
						platformAccounts.add(prizeadd);
						messageProducer.toPlatformCurrency(JSON.toJSONString(platformAccounts));
						//站内信  短信
						Map<String, String> paramM = new HashMap<>();
				        paramM.put("${transactionNum}", klgSellTransaction.getTransactionNum());
				        RemoteMessage message=new RemoteMessage();
				        message.setParam(paramM);
				        message.setMsgKey(MessageType.MESSAGE_KLG_SELL_DEAL.getKey());//消息类型 模板KEY//
				        message.setMsgType("1,2");// 1.站内信，2.短信,
				        message.setUserId(klgSellTransaction.getCustomerId().toString());
				        messageProducer.toMessageWarn(JSON.toJSONString(message));//消息提醒
						// 发送币账户消息
						messageProducer.toAccount(JSON.toJSONString(list));
						messageProducer.toAward(JSON.toJSONString(klgSellTransaction));
					}
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					KlgExceptionLogService klgExceptionLogService = SpringUtil.getBean("klgExceptionLogService");
					// 插入异常日志
					KlgExceptionLog klgExceptionLog = new KlgExceptionLog();
					klgExceptionLog.setFunctionName("清理卖单,买单订单号:"+klgSellTransaction.getTransactionNum());
					String strE = e.toString();
					if (strE.length() <= 230) {
						klgExceptionLog.setExceptionText(strE);
					} else {
						klgExceptionLog.setExceptionText(strE.substring(0, 230));
					}
					klgExceptionLogService.save(klgExceptionLog);
				}

			}
			klgAssetsRecordService.saveAll(listKlgAssetsRecord);//插入币账户流水记录
		}

	}

	/**
	 * 清理买单
	 */
	private void clearBuyOrder() {
		String ptbCoinCode = (String) remoteKlgService.getPlatformRule1sConfig("coinCode");
		List<Accountadd> list = new ArrayList<Accountadd>();
		List<KlgAssetsRecord> listKlgAssetsRecord = new ArrayList<>();//记录流水
		KlgAssetsRecord  klgAssetsRecordHot = null;
		/** 查询卖单剩余卖出账户信息 */
		QueryFilter filterKlgSellAccount = new QueryFilter(KlgBuySellAccount.class);
		filterKlgSellAccount.addFilter("accountName=", "sellSurplusAccount");
		KlgBuySellAccount klgSellAccount = super.get(filterKlgSellAccount);
		/** 查询所有待收款状态的买单 */
		QueryFilter filterKlgBuyTransaction = new QueryFilter(KlgBuyTransaction.class);
		filterKlgBuyTransaction.addFilter("status=", 3);
		filterKlgBuyTransaction.setOrderby("timeStamp asc");
		List<KlgBuyTransaction> listKlgBuyTransaction = klgBuyTransactionService.find(filterKlgBuyTransaction);
		if (listKlgBuyTransaction != null && listKlgBuyTransaction.size() > 0) {
			// 遍历买单，满足条件清单
			for (KlgBuyTransaction klgBuyTransaction : listKlgBuyTransaction) {
				try {
					list = new ArrayList<Accountadd>();
					klgSellAccount = super.get(filterKlgSellAccount);
					// 对比卖单卖出剩余账户与买单买入的SME数量
					BigDecimal buySme = klgBuyTransaction.getSmeMoney();
					BigDecimal accountMoney = klgSellAccount.getMoney();
					if (accountMoney.compareTo(buySme) >= 0) {
						// 卖单卖出剩余账户>买单买入的SME数量 ,清理买单
						// 查询买单SME账户信息 增加
						ExDigitalmoneyAccountRedis exaccountBuySme = this.selectAccount(klgBuyTransaction.getCustomerId(),
								ptbCoinCode);
						list.add(getAccountAdd(exaccountBuySme.getId(), buySme, 1, 1, 9,
								klgBuyTransaction.getTransactionNum()));
						//插入币账户流水
						klgAssetsRecordHot = new KlgAssetsRecord(exaccountBuySme.getCustomerId(), exaccountBuySme.getId(),
								IdGenerate.transactionNum(NumConstant.Ex_Dm_Transaction), 1, exaccountBuySme.getCoinCode(),
								buySme, 1, TriggerPointEnum.Purchase.getKey(), klgBuyTransaction.getTransactionNum(), "买入");
						this.updateMoneyByAccountName("sellSurplusAccount", buySme, 2,
								klgBuyTransaction.getTransactionNum(), "", "清理买单");
						klgBuyTransaction.setStatus(4);
						klgBuyTransactionService.update(klgBuyTransaction);
						
						listKlgAssetsRecord.add(klgAssetsRecordHot);
						// 发送币账户消息
						messageProducer.toAccount(JSON.toJSONString(list));
						
						//发奖励额度的
						klgCustomerLevelService.buyRewardQuotaAdd(klgBuyTransaction.getCustomerId(), klgBuyTransaction.getLastMoney());

						
						//站内信  短信
						Map<String, String> paramM = new HashMap<>();
				        paramM.put("${transactionNum}", klgBuyTransaction.getTransactionNum());
				        RemoteMessage message=new RemoteMessage();
				        message.setParam(paramM);
				        message.setMsgKey(MessageType.MESSAGE_KLG_BUY_DEAL.getKey());//消息类型 模板KEY//
				        message.setMsgType("1,2");// 1.站内信，2.短信,
				        message.setUserId(klgBuyTransaction.getCustomerId().toString());
				        messageProducer.toMessageWarn(JSON.toJSONString(message));//消息提醒
					}
				} catch (Exception e) {
					// TODO: handle exception
					KlgExceptionLogService klgExceptionLogService = SpringUtil.getBean("klgExceptionLogService");
					// 插入异常日志
					KlgExceptionLog klgExceptionLog = new KlgExceptionLog();
					klgExceptionLog.setFunctionName("清理买单,买单订单号:"+klgBuyTransaction.getTransactionNum());
					String strE = e.toString();
					if (strE.length() <= 230) {
						klgExceptionLog.setExceptionText(strE);
					} else {
						klgExceptionLog.setExceptionText(strE.substring(0, 230));
					}
					klgExceptionLogService.save(klgExceptionLog);
				}
				
			}
			klgAssetsRecordService.saveAll(listKlgAssetsRecord);//插入币账户流水记录
		}
	}

	/**
	 * 插入发送消息信息
	 * 
	 * @param accountId
	 *            币账户id
	 * @param Money
	 *            金额
	 * @param monteyType
	 *            //1热账户，2，冷账号
	 * @param acccountType
	 *            //0资金账号，1币账户
	 * @param remark
	 *            //备注 必须填 安照AccountRemarkEnum.java
	 * @param transactionNum
	 *            订单号
	 * @return
	 */
	private Accountadd getAccountAdd(Long accountId, BigDecimal Money, Integer monteyType, Integer acccountType,
			Integer remark, String transactionNum) {
		Accountadd accountadd = new Accountadd();
		accountadd.setAccountId(accountId);
		accountadd.setMoney(Money);
		accountadd.setMonteyType(monteyType);
		accountadd.setAcccountType(acccountType);
		accountadd.setRemarks(remark);
		accountadd.setTransactionNum(transactionNum);
		return accountadd;
	}

	/**
	 * 查询币账户信息
	 * 
	 * @param customerId
	 * @param coinCode
	 * @return
	 */
	private ExDigitalmoneyAccountRedis selectAccount(Long customerId, String coinCode) {
		try {
			// 查redis缓存
			RedisUtil<UserRedis> redisUtil = new RedisUtil<UserRedis>(UserRedis.class);
			UserRedis userRedis = redisUtil.get(customerId.toString());
			// 获得币账户
			ExDigitalmoneyAccountRedis exaccount = UserRedisUtils
					.getAccount(userRedis.getDmAccountId(coinCode).toString(), coinCode);
			return exaccount;
		} catch (Exception e) {
			// TODO: handle exception
			// e.printStackTrace();
			System.out.println("用户" + customerId + ":货币" + coinCode + "账户异常");
			return null;
		}

	}

	@Override
	public void updateMoneyByAccountName(String accountName, BigDecimal changeMoney, Integer changeType,
			String orderNum, String remark, String triggered) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<>();
		map.put("accountName", accountName);
		if (changeType == 2) {
			map.put("changeMoney", new BigDecimal("-" + changeMoney));
		} else {
			map.put("changeMoney", changeMoney);
		}
		// 更新账户
		((KlgBuySellAccountDao) dao).updateMoneyByAccountName(map);
		// 插入更新记录
		KlgBuySellAccountrecordRecord klgBuySellAccountrecordRecord = new KlgBuySellAccountrecordRecord();
		klgBuySellAccountrecordRecord.setAccountName(accountName);
		klgBuySellAccountrecordRecord.setChangeMoney(changeMoney);
		klgBuySellAccountrecordRecord.setChangeType(changeType);
		klgBuySellAccountrecordRecord.setOrderNum(orderNum);
		klgBuySellAccountrecordRecord.setRemark(remark);
		klgBuySellAccountrecordRecord.setTriggered(triggered);
		klgBuySellAccountrecordRecordService.save(klgBuySellAccountrecordRecord);

	}

}
