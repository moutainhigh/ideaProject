package hry.klg.transaction.remote;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;

import hry.bean.JsonResult;
import hry.front.redis.model.UserRedis;
import hry.klg.assetsrecord.model.KlgAssetsRecord;
import hry.klg.assetsrecord.service.KlgAssetsRecordService;
import hry.klg.buysellaccount.model.KlgBuySellAccount;
import hry.klg.buysellaccount.service.KlgBuySellAccountService;
import hry.klg.enums.TriggerPointEnum;
import hry.klg.limit.model.AmountLimitType;
import hry.klg.limit.service.KlgAmountLimitationService;
import hry.klg.log.model.KlgExceptionLog;
import hry.klg.log.service.KlgExceptionLogService;
import hry.klg.model.KlgPlatformCurrency;
import hry.klg.model.PlatformAccountadd;
import hry.klg.platform.model.PlatformAccountUtil;
import hry.klg.remote.RemoteKlgBuySellTransactionService;
import hry.klg.remote.RemoteKlgService;
import hry.klg.remote.model.BuyPayMessage;
import hry.klg.remote.model.ToBuySellAccountMessage;
import hry.klg.transaction.model.KlgBuyTransaction;
import hry.klg.transaction.model.KlgSellTransaction;
import hry.klg.transaction.remote.vo.BuySellModel;
import hry.klg.transaction.service.KlgBuyTransactionService;
import hry.klg.transaction.service.KlgSellTransactionService;
import hry.klg.utils.RedisStaticStringUtil;
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
 * 快乐购买卖service
 * 
 * @author lenovo
 *
 */
public class RemoteKlgBuySellTransactionServiceImpl implements RemoteKlgBuySellTransactionService {
	private static Logger logger = Logger.getLogger(RemoteKlgBuySellTransactionServiceImpl.class);

	@Resource
	private KlgBuyTransactionService klgBuyTransactionService;

	@Resource
	private MessageProducer messageProducer;
	@Resource
	private RedisService redisService;
	@Resource
	private AppMessageService appMessageService;
	@Resource
	private KlgBuySellAccountService klgBuySellAccountService;
	@Resource
	private KlgSellTransactionService klgSellTransactionService;
	@Resource
	private RemoteKlgService remoteKlgService;
	@Resource
	private KlgAssetsRecordService klgAssetsRecordService;
	@Resource
	private KlgAmountLimitationService klgAmountLimitationService;

	@Override
	public JsonResult payBackBuyTransaction(Map<String, String> params) {
		// TODO Auto-generated method stub
		JsonResult jsonResult = new JsonResult();
		List<Accountadd> list = new ArrayList<Accountadd>();
		Long customerId = Long.valueOf(params.get("customerId"));// 用户id
		Long buyTransactionId = Long.valueOf(params.get("buyTransactionId"));// 买单id
		/*** ======判断密码======= **/
		/*** ======判断密码======= **/
		/*** ======判断密码======= **/
		/*** ======判断密码======= **/
		/*** ======判断密码======= **/
		/*** ======判断密码======= **/
		/*** ======判断密码======= **/
		/** 查询订单信息 */
		KlgBuyTransaction klgBuyTransaction = klgBuyTransactionService.get(buyTransactionId);
		if (klgBuyTransaction == null) {
			return jsonResult.setSuccess(false).setMsg("klg_dingdan_bucunzai");//订单不存在
		}
		if (!klgBuyTransaction.getCustomerId().equals(customerId)) {
			return jsonResult.setSuccess(false).setMsg("klg_dingdan_xinxiyichang");//订单信息异常
		}
		if (!klgBuyTransaction.getStatus().equals(2)) {
			return jsonResult.setSuccess(false).setMsg("klg_dingdanzhuangtaibuzhengque");//订单状态不正确
		}

		Boolean lock = redisService
				.lock(RedisStaticStringUtil.KLG_BUY_PAY_TRANSACTIONNUM + klgBuyTransaction.getTransactionNum());
		if (lock) {
			/** 查询用户币账户信息 */
			ExDigitalmoneyAccountRedis exaccount = this.selectAccount(customerId, klgBuyTransaction.getCoinCode());
			// 获取热账户
			BigDecimal hotMoney = exaccount.getHotMoney();
			// 判断热账户是否充足
			if (hotMoney.compareTo(klgBuyTransaction.getLastMoney()) < 0) {
				redisService.unLock(
						RedisStaticStringUtil.KLG_BUY_PAY_TRANSACTIONNUM + klgBuyTransaction.getTransactionNum());
				return jsonResult.setSuccess(false).setMsg("klg_zhanghuyuebuzu");//账户余额不足
			}
			List<KlgAssetsRecord> listKlgAssetsRecord = new ArrayList<>();// 记录流水
			KlgAssetsRecord klgAssetsRecordHot = null;
			KlgAssetsRecord klgAssetsRecordCold = null;
			// usdt热账户减少 冷账户增加
			list.add(getAccountAdd(exaccount.getId(), new BigDecimal("-" + klgBuyTransaction.getLastMoney()), 1, 1, 2,
					klgBuyTransaction.getTransactionNum()));
			klgAssetsRecordHot = new KlgAssetsRecord(exaccount.getCustomerId(), exaccount.getId(),
					IdGenerate.transactionNum(NumConstant.Ex_Dm_Transaction), 1, exaccount.getCoinCode(),
					klgBuyTransaction.getLastMoney(), 2, TriggerPointEnum.BuyPayTailMoney.getKey(),
					klgBuyTransaction.getTransactionNum(), "APP支付剩余，转冻结");

			list.add(getAccountAdd(exaccount.getId(), new BigDecimal("+" + klgBuyTransaction.getLastMoney()), 2, 1, 2,
					klgBuyTransaction.getTransactionNum()));
			klgAssetsRecordCold = new KlgAssetsRecord(exaccount.getCustomerId(), exaccount.getId(),
					IdGenerate.transactionNum(NumConstant.Ex_Dm_Transaction), 2, exaccount.getCoinCode(),
					klgBuyTransaction.getLastMoney(), 1, TriggerPointEnum.BuyPayTailMoney.getKey(),
					klgBuyTransaction.getTransactionNum(), "APP支付剩余，转冻结");
			listKlgAssetsRecord.add(klgAssetsRecordHot);
			listKlgAssetsRecord.add(klgAssetsRecordCold);
			klgAssetsRecordService.saveAll(listKlgAssetsRecord);// 插入币账户流水记录
			/** 更新订单状态为3待收款 */
			// 更新订单状态
			klgBuyTransaction.setStatus(3);
			klgBuyTransactionService.update(klgBuyTransaction);

			// 发送清理订单消息
			List<ToBuySellAccountMessage> listAccount = new ArrayList<>();
			// ToBuySellAccountMessage toBuySellAccountMessage = new
			// ToBuySellAccountMessage("buySurplusAccount",
			// klgBuyTransaction.getTransactionNum(), "买单APP支付");
			ToBuySellAccountMessage toBuySellAccountMessage = new ToBuySellAccountMessage(new BigDecimal(0), 1, 5,
					"buySurplusAccount", klgBuyTransaction.getTransactionNum(), "买单APP支付");
			listAccount.add(toBuySellAccountMessage);

			messageProducer.toAccount(JSON.toJSONString(list));
			messageProducer.toBuySellAccount(JSON.toJSONString(listAccount));

			redisService
					.unLock(RedisStaticStringUtil.KLG_BUY_PAY_TRANSACTIONNUM + klgBuyTransaction.getTransactionNum());

		} else {
			jsonResult.setSuccess(false).setMsg("klg_xitongfanmang");//系统繁忙，请稍后再试!
		}

		return jsonResult.setSuccess(true).setMsg("klg_bujiaochenggong");//补缴成功，等待系统处理
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

	@Override
	public void buyPayMessage(BuyPayMessage buyPayMessage) {
		// TODO Auto-generated method stub
		/** 获取分布式锁 */
		while (redisService.lock(RedisStaticStringUtil.KLG_BUY_PAY_SURPLUS_ACCOUNT)) {
			try {
				/** 查询订单信息 */
				KlgBuyTransaction klgBuyTransaction = klgBuyTransactionService
						.get(buyPayMessage.getKlgBuyTransactionId());
				// 判断订单是否是平台吃单
				if (klgBuyTransaction.getStatus() == 6) {
					// 平台吃单
					this.chidanPay(buyPayMessage, klgBuyTransaction);
				} else {
					// 消息手动确认
					this.buyPayMessageBranch(buyPayMessage);
				}
				// 释放锁
				redisService.unLock(RedisStaticStringUtil.KLG_BUY_PAY_SURPLUS_ACCOUNT);
				break;
			} catch (Exception e) {
				// TODO: handle exception
				// 释放锁
				redisService.unLock(RedisStaticStringUtil.KLG_BUY_PAY_SURPLUS_ACCOUNT);
				e.printStackTrace();
				break;
			}
		}

	}

	/**
	 * 平台吃单
	 * 
	 * @param buyPayMessage
	 */
	private void chidanPay(BuyPayMessage buyPayMessage, KlgBuyTransaction klgBuyTransaction) {
		/**
		 * 用户usdt冻结账户减少，sme账户增加 平台粮食局账户，sme减少，usdt增加
		 */
		/** 查询平台币Code */
		String ptbCoinCode = (String) remoteKlgService.getPlatformRule1sConfig("coinCode");
		List<Accountadd> list = new ArrayList<Accountadd>();
		List<KlgAssetsRecord> listKlgAssetsRecord = new ArrayList<>();//记录流水
		KlgAssetsRecord  klgAssetsRecordHot = null;
		KlgAssetsRecord  klgAssetsRecordCold = null;
		/** 查询用户UDST币账户信息 */
		ExDigitalmoneyAccountRedis exaccount = this.selectAccount(buyPayMessage.getCustomerId(),
				klgBuyTransaction.getCoinCode());
		// 获取冷账户
		BigDecimal coldMoney = exaccount.getColdMoney();
		// 判断冷账户是否充足
		if (coldMoney.compareTo(klgBuyTransaction.getFirstMoney().add(klgBuyTransaction.getLastMoney())) < 0) {
			/** ===========此处需要发送站内辛============= */

			/** ======================== */
			return;
		}
		list.add(getAccountAdd(exaccount.getId(), new BigDecimal("-" + klgBuyTransaction.getUsdtMoney()), 2, 1, 2,
				klgBuyTransaction.getTransactionNum()));
		klgAssetsRecordCold = new KlgAssetsRecord(exaccount.getCustomerId(), exaccount.getId(),
				IdGenerate.transactionNum(NumConstant.Ex_Dm_Transaction), 2, exaccount.getCoinCode(),
				klgBuyTransaction.getUsdtMoney(), 2, TriggerPointEnum.Purchase.getKey(), klgBuyTransaction.getTransactionNum(), "APP支付，平台吃单,支付USDT");
		/** 查询用户sme币账户 */
		ExDigitalmoneyAccountRedis exaccountSme = this.selectAccount(buyPayMessage.getCustomerId(), ptbCoinCode);
		list.add(getAccountAdd(exaccountSme.getId(), klgBuyTransaction.getSmeMoney(), 1, 1, 9,
				klgBuyTransaction.getTransactionNum()));
		klgAssetsRecordHot = new KlgAssetsRecord(exaccount.getCustomerId(), exaccount.getId(),
				IdGenerate.transactionNum(NumConstant.Ex_Dm_Transaction), 1, exaccount.getCoinCode(),
				klgBuyTransaction.getSmeMoney(), 2, TriggerPointEnum.Purchase.getKey(), klgBuyTransaction.getTransactionNum(), "APP支付,平台吃单,用户收入SME");
		listKlgAssetsRecord.add(klgAssetsRecordHot);
		listKlgAssetsRecord.add(klgAssetsRecordCold);
		klgAssetsRecordService.saveAll(listKlgAssetsRecord);//插入币账户流水记录
		/** 粮食局账户sme减少，usdt增加 **/
		// 粮食局账户SME减少
		List<PlatformAccountadd> platformAccounts = new ArrayList<>();// 平台账户
		PlatformAccountadd prizeadd = null;
		prizeadd = PlatformAccountUtil.accountSub(KlgPlatformCurrency.SMEFoodBureauAccount.getKey(),
				String.valueOf(klgBuyTransaction.getSmeMoney()),"平台吃单:"+klgBuyTransaction.getTransactionNum());
		platformAccounts.add(prizeadd);
		// USDT财政局账户增加
		prizeadd = PlatformAccountUtil
				.accountAdd(KlgPlatformCurrency.USDTFinanceBureauAccount.getKey(),
						String.valueOf((klgBuyTransaction.getUsdtMoney())),"平台吃单:"+klgBuyTransaction.getTransactionNum());
		platformAccounts.add(prizeadd);
		messageProducer.toPlatformCurrency(JSON.toJSONString(platformAccounts));
		messageProducer.toAccount(JSON.toJSONString(list));
	}

	private void buyPayMessageBranch(BuyPayMessage buyPayMessage) {
		List<Accountadd> list = new ArrayList<Accountadd>();
		/** 查询平台币Code */
		String ptbCoinCode = (String) remoteKlgService.getPlatformRule1sConfig("coinCode");
		/** 查询订单信息 */
		KlgBuyTransaction klgBuyTransaction = klgBuyTransactionService.get(buyPayMessage.getKlgBuyTransactionId());
		/** 查询用户币账户信息 */
		ExDigitalmoneyAccountRedis exaccount = this.selectAccount(buyPayMessage.getCustomerId(),
				klgBuyTransaction.getCoinCode());
		// 获取冷账户
		BigDecimal coldMoney = exaccount.getColdMoney();
		// 判断冷账户是否充足
		if (coldMoney.compareTo(klgBuyTransaction.getFirstMoney().add(klgBuyTransaction.getLastMoney())) < 0) {
			/** ===========此处需要发送站内辛============= */

			/** ======================== */
			return;
		}
		/** 查询买单支付剩余账户金额USDT */
		QueryFilter filterKlgBuyAccount = new QueryFilter(KlgBuySellAccount.class);
		filterKlgBuyAccount.addFilter("accountName=", "buySurplusAccount");
		KlgBuySellAccount klgBuyAccount = klgBuySellAccountService.get(filterKlgBuyAccount);
		/** 查询卖单卖出剩余账户金额SME */
		QueryFilter filterKlgSellAccount = new QueryFilter(KlgBuySellAccount.class);
		filterKlgSellAccount.addFilter("accountName=", "sellSurplusAccount");
		KlgBuySellAccount klgSellAccount = klgBuySellAccountService.get(filterKlgSellAccount);
		// 买单 usdt 冷账户减少
		// 查询用户SME币账户信息
		list.add(getAccountAdd(exaccount.getId(),
				new BigDecimal("-" + (klgBuyTransaction.getFirstMoney().add(klgBuyTransaction.getLastMoney()))), 2, 1,
				2, klgBuyTransaction.getTransactionNum()));

		// 买单支付剩余账户增加
		klgBuyAccount.setMoney(klgBuyAccount.getMoney().add(klgBuyTransaction.getUsdtMoney()));

		// 修改买单状态为待收款
		klgBuyTransaction.setStatus(3);
		klgBuyTransactionService.update(klgBuyTransaction);

		/** 记录操作账户数据，最后统一发送 **/
		BuySellModel buySellModel = new BuySellModel();
		buySellModel.setKlgBuyAccount(klgBuyAccount);
		buySellModel.setKlgSellAccount(klgSellAccount);
		buySellModel.setList(list);

		/** 计算买单支付剩余账户+当前支付订单USDT数量满足那些卖单 */
		// 判断卖单剩余卖出是否满足买单买入数量
		if (klgSellAccount.getMoney().compareTo(klgBuyTransaction.getSmeMoney()) >= 0) {
			/** 卖单剩余卖出满足买单买入数量,则用卖单剩余数量直接清理买单 */
			// 买单用户SME账户增加
			ExDigitalmoneyAccountRedis exaccountSme = this.selectAccount(buyPayMessage.getCustomerId(), ptbCoinCode);
			list.add(getAccountAdd(exaccountSme.getId(), klgBuyTransaction.getSmeMoney(), 1, 1, 9,
					klgBuyTransaction.getTransactionNum()));
			// 卖单卖出剩余减少
			klgSellAccount.setMoney(klgSellAccount.getMoney().subtract(klgBuyTransaction.getSmeMoney()));
			// 修改买单状态为已完成
			klgBuyTransaction.setStatus(4);
			klgBuyTransactionService.update(klgBuyTransaction);

			// 清理卖单
			buySellModel = this.buyPayMessageBranch_Branch_true(buyPayMessage, klgBuyTransaction, exaccount,
					buySellModel);
		} else {
			/** 卖单剩余卖出不满足买单买入数量，遍历卖单，修改卖单状态 */
			buySellModel = this.buyPayMessageBranch_Branch_false(buyPayMessage, klgBuyTransaction, exaccount,
					buySellModel);
		}

		/** 发送币账户消息，更新买卖单剩余账户 */
		messageProducer.toAccount(JSON.toJSONString(buySellModel.getList()));
		klgBuySellAccountService.update(buySellModel.getKlgBuyAccount());
		klgBuySellAccountService.update(buySellModel.getKlgSellAccount());
	}

	/**
	 * 卖单剩余卖出满足买单买入数量,则用卖单剩余数量直接清理买单
	 * 
	 * @param buyPayMessage
	 * @param klgBuyTransaction
	 * @param exaccount
	 * @param klgBuyAccount
	 * @param klgSellAccount
	 */
	private BuySellModel buyPayMessageBranch_Branch_true(BuyPayMessage buyPayMessage,
			KlgBuyTransaction klgBuyTransaction, ExDigitalmoneyAccountRedis exaccount, BuySellModel buySellModel) {
		/**
		 * 遍历卖单待收款状态的订单,判断买单支付剩余账户是否满足卖单卖出USDT数量, 如果满足，则买单支付剩余账户减少，卖方USDT账户增加，
		 * 修改卖单状态为已成交，发送站内信 发放奖励， 如果不满足，则遍历排队成功的卖单
		 */
		// 查询所有待收款状态的卖单 */
		QueryFilter filterKlgSellTransaction = new QueryFilter(KlgSellTransaction.class);
		filterKlgSellTransaction.addFilter("status=", 3);
		List<KlgSellTransaction> listKlgSellTransaction = klgSellTransactionService.find(filterKlgSellTransaction);
		// 遍历卖单，满足条件的直接清单
		buySellModel = this.buyPayMessageBranch_Branch_true_Branch(listKlgSellTransaction, buySellModel);

		return buySellModel;
	}

	/**
	 * 卖单剩余卖出不满足买单买入数量，遍历卖单，修改卖单状态
	 */
	private BuySellModel buyPayMessageBranch_Branch_false(BuyPayMessage buyPayMessage,
			KlgBuyTransaction klgBuyTransaction, ExDigitalmoneyAccountRedis exaccount, BuySellModel buySellModel) {
		KlgBuySellAccount klgSellAccount = buySellModel.getKlgSellAccount();
		List<Accountadd> list = buySellModel.getList();

		/** 查询平台币Code */
		String ptbCoinCode = (String) remoteKlgService.getPlatformRule1sConfig("coinCode");

		/** 遍历排队成功状态的卖单，用卖单剩余账户数量加上卖单卖出数量，直到满足卖单为止 **/
		// 查询所有排队成功状态的卖单 */
		QueryFilter filterKlgSellTransaction = new QueryFilter(KlgSellTransaction.class);
		filterKlgSellTransaction.addFilter("status=", 2);
		List<KlgSellTransaction> listKlgSellTransaction = klgSellTransactionService.find(filterKlgSellTransaction);
		if (listKlgSellTransaction != null) {
			// 买单买入SME
			BigDecimal buySmeMoney = klgBuyTransaction.getSmeMoney();
			// 卖单待收款
			BigDecimal klgSellAccountMoney = klgSellAccount.getMoney();
			// 遍历卖单 ，用卖单待收款剩余账户数量(SME)加上卖单卖出数量，直到满足买单买入SME数量为止
			boolean b = true;
			for (KlgSellTransaction klgSellTransaction : listKlgSellTransaction) {
				// 修改卖单状态为待收款3
				klgSellTransaction.setStatus(3);
				// 卖单用户sme冻结减少
				// 查询卖单sme币账户信息
				ExDigitalmoneyAccountRedis exaccountSellSme = this.selectAccount(klgSellTransaction.getCustomerId(),
						ptbCoinCode);
				list.add(getAccountAdd(exaccountSellSme.getId(), new BigDecimal("-" + klgSellTransaction.getSmeMoney()),
						2, 1, 9, klgSellTransaction.getTransactionNum()));

				klgSellTransactionService.update(klgSellTransaction);
				// 如果卖出SME数量+待收款剩余 >= 买单买入数量 则清理买单卖单
				klgSellAccountMoney = klgSellTransaction.getSmeMoney().add(klgSellTransaction.getCandySmeMoney())
						.add(klgSellAccountMoney);
				klgSellAccount.setMoney(klgSellAccountMoney);
				klgBuySellAccountService.update(klgSellAccount);
				if (klgSellAccountMoney.compareTo(buySmeMoney) >= 0) {
					b = false;
					// 买单用户SME账户增加
					ExDigitalmoneyAccountRedis exaccountSme = this.selectAccount(buyPayMessage.getCustomerId(),
							ptbCoinCode);
					list.add(getAccountAdd(exaccountSme.getId(), klgBuyTransaction.getSmeMoney(), 1, 1, 9,
							klgBuyTransaction.getTransactionNum()));

					// 卖单卖出剩余减少
					klgSellAccount.setMoney(klgSellAccount.getMoney().subtract(klgBuyTransaction.getSmeMoney()));
					// 修改买单状态为已完成
					klgBuyTransaction.setStatus(4);
					klgBuyTransactionService.update(klgBuyTransaction);

					buySellModel.setKlgSellAccount(klgSellAccount);
					buySellModel.setList(list);
					/** 清理卖单 */
					buySellModel = this.buyPayMessageBranch_Branch_true(buyPayMessage, klgBuyTransaction, exaccount,
							buySellModel);
					break;
				}
			}
			// 剩余卖出+排队成功的卖单不满足买单
			if (b) {
				/** 如果所有排队成功加待收款状态的卖单不满足当前买单，则等待后台下一轮匹配的时候清理 */
			}
		}
		return buySellModel;
	}

	/**
	 * 遍历卖单，满足条件的直接清单
	 */
	private BuySellModel buyPayMessageBranch_Branch_true_Branch(List<KlgSellTransaction> listKlgSellTransaction,
			BuySellModel buySellModel) {
		/** 查询平台币Code */
		String ptbCoinCode = (String) remoteKlgService.getPlatformRule1sConfig("coinCode");
		List<Accountadd> list = buySellModel.getList();
		KlgBuySellAccount klgBuyAccount = buySellModel.getKlgBuyAccount();
		for (KlgSellTransaction klgSellTransaction : listKlgSellTransaction) {
			// 计算卖单应收USDT数量
			BigDecimal sellReceivables = (klgSellTransaction.getCandySmeMoney().add(klgSellTransaction.getSmeMoney()))
					.multiply(klgSellTransaction.getSmeusdtRate());
			// 买单支付剩余账户
			BigDecimal buyAllSum = klgBuyAccount.getMoney();
			if (buyAllSum.compareTo(sellReceivables) >= 0) {
				/**
				 * 已支付金额大于当前卖单卖出总数，清理卖单 买单支付剩余账户减少，卖单用户USDT账户增加，SME账户冻结减少
				 * 修改卖单状态为已成交，发送站内信
				 */
				// 买单支付剩余账户减少
				klgBuyAccount.setMoney(klgBuyAccount.getMoney().subtract(sellReceivables));
				klgBuySellAccountService.update(klgBuyAccount);
				/* 查询卖家币账户信息 */
				// 查询SME账户
				ExDigitalmoneyAccountRedis exaccountSellSme = this.selectAccount(klgSellTransaction.getCustomerId(),
						ptbCoinCode);
				// 如果订单状态为排队成功，则冻结减少
				if (klgSellTransaction.getStatus() == 2) {
					// SME账户冻结减少
					list.add(getAccountAdd(exaccountSellSme.getId(), klgSellTransaction.getSmeMoney(), 1, 2, 11,
							klgSellTransaction.getTransactionNum()));
				}
				// 查询USDT账户
				ExDigitalmoneyAccountRedis exaccountSellUsdt = this.selectAccount(klgSellTransaction.getCustomerId(),
						klgSellTransaction.getCoinCode());
				// 卖单用户USDT账户增加，实际卖出SME获取的USDT
				list.add(getAccountAdd(exaccountSellUsdt.getId(),
						klgSellTransaction.getSmeMoney().multiply(klgSellTransaction.getSmeusdtRate()), 1, 1, 7,
						klgSellTransaction.getTransactionNum()));
				// 卖单用户USDT账户增加，糖果卖出获取的USDT*分成比例
				BigDecimal profit = (klgSellTransaction.getCandySmeMoney()
						.multiply(klgSellTransaction.getSmeusdtRate())).multiply(klgSellTransaction.getOneselfRate())
								.multiply(new BigDecimal(0.01));
				list.add(getAccountAdd(exaccountSellUsdt.getId(), profit, 1, 1, 7,
						klgSellTransaction.getTransactionNum()));
				klgSellTransaction.setStatus(4);
				klgSellTransactionService.update(klgSellTransaction);

				/** =====此处需要发送站内信====== **/

				/** ================ **/

				/** ========此处发送奖励=开始======= **/
				/** ========此处发送奖励=开始======= **/
				/** ========此处发送奖励=开始======= **/
				/** ========此处发送奖励=开始======= **/
				/** ========此处发送奖励=开始======= **/
				/** ========此处发送奖励=开始======= **/

				/** ========此处发送奖励=结束======= **/

			} else {
				break;
			}
		}
		buySellModel.setList(list);
		buySellModel.setKlgBuyAccount(klgBuyAccount);
		return buySellModel;
	}

	/**
	 * 查询币账户信息
	 * 
	 * @param customerId
	 * @param coinCode
	 * @return
	 */
	private ExDigitalmoneyAccountRedis selectAccount(Long customerId, String coinCode) {
		// 查redis缓存
		RedisUtil<UserRedis> redisUtil = new RedisUtil<UserRedis>(UserRedis.class);
		UserRedis userRedis = redisUtil.get(customerId.toString());
		// 获得币账户
		ExDigitalmoneyAccountRedis exaccount = UserRedisUtils.getAccount(userRedis.getDmAccountId(coinCode).toString(),
				coinCode);
		return exaccount;
	}

	@Override
	public JsonResult robBuyTransaction(Map<String, String> params) {
		// TODO Auto-generated method stub
		JsonResult jsonResult = new JsonResult();
		Long customerId = Long.valueOf(params.get("customerId"));// 用户id
		/** 查询用今天是否有抢单订单 */
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String dateStr = formatter.format(date);
		QueryFilter filterKlgBuyTransaction = new QueryFilter(KlgBuyTransaction.class);
		filterKlgBuyTransaction.addFilter("rushOrders=", 2);
		filterKlgBuyTransaction.addFilter("customerId=", customerId);
		filterKlgBuyTransaction.addFilter("created>=", dateStr);
		KlgBuyTransaction klgBuyTransaction = klgBuyTransactionService.get(filterKlgBuyTransaction);
		if (klgBuyTransaction == null) {
			BigDecimal robBuySmeNum = new BigDecimal(params.get("robBuySmeNum"));// 抢单SME数量
			/** 查询抢单额度, ***/
			//BigDecimal robQuota = new BigDecimal("1000");// 暂时写死，等方法直接调用查询
			boolean flg= klgAmountLimitationService.isCheckNum(AmountLimitType.GrabSheet,robBuySmeNum);
			
			// 判断额度是否充足
			if (!flg) {
				return jsonResult.setSuccess(true).setMsg("klg_qiangdanshibai_edubuzu");//本次抢单失败，抢单额度已用完
			}
			/** 查询平台币价格 */
			BigDecimal ptbPrice = new BigDecimal((String) remoteKlgService.getPlatformRule1sConfig("issuePrice"));

			ExDigitalmoneyAccountRedis exaccountUsdt = this.selectAccount(customerId, "USDT");
			// 判断USDT账户是否充足
			BigDecimal usdtHotMoney = exaccountUsdt.getHotMoney();
			if (usdtHotMoney.compareTo(robBuySmeNum.multiply(ptbPrice)) < 0) {
				RemoteMessage message=new RemoteMessage();
		        message.setMsgKey(MessageType.MESSAGE_KLG_BUY_ROB_FAIL.getKey());//消息类型 模板KEY//
		        message.setMsgType("1,2");// 1.站内信，2.短信,
		        message.setUserId(customerId.toString());
		        messageProducer.toMessageWarn(JSON.toJSONString(message));//消息提醒
		        return jsonResult.setSuccess(true).setMsg("klg_qiangdanshibai_zhanghudubuzu");//抢单失败,账户余额不足!
			}
			/** 发送抢单消息 */
			/** 获取分布式锁 */
			messageProducer
					.toRobBuyTransaction(JSON.toJSONString(new BuyPayMessage(customerId, (long) 0, robBuySmeNum)));

		} else {
			return jsonResult.setSuccess(false).setMsg("klg_jintianqiaoguol");//今天已经抢过单了,请明天在试!
		}
		return jsonResult.setSuccess(true).setMsg("klg_qiangdanchenggong_dengdai");//抢单消息发送成功,等待处理结果!
	}

	@Override
	public void robBuyTransactionMessage(BuyPayMessage buyPayMessage) {
		// TODO Auto-generated method stub
		/** 获取分布锁 */
		while (redisService.lock(RedisStaticStringUtil.KLG_BUY_PAY_SURPLUS_ACCOUNT)) {
			try {
				List<Accountadd> list = new ArrayList<Accountadd>();
				// 发送清理订单消息
				List<ToBuySellAccountMessage> listAccount = new ArrayList<>();
				/** 查询用今天是否有抢单订单 */
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				Date date = new Date();
				String dateStr = formatter.format(date);
				QueryFilter filterKlgBuyTransaction = new QueryFilter(KlgBuyTransaction.class);
				filterKlgBuyTransaction.addFilter("rushOrders=", 2);
				filterKlgBuyTransaction.addFilter("customerId=", buyPayMessage.getCustomerId());
				filterKlgBuyTransaction.addFilter("created>=", dateStr);
				KlgBuyTransaction klgBuyTransaction = klgBuyTransactionService.get(filterKlgBuyTransaction);
				if (klgBuyTransaction != null) {
					/** 发送站内信========== **/
					RemoteMessage message=new RemoteMessage();
			        message.setMsgKey(MessageType.MESSAGE_KLG_BUY_ROB_FAIL.getKey());//消息类型 模板KEY//
			        message.setMsgType("1,2");// 1.站内信，2.短信,
			        message.setUserId(buyPayMessage.getCustomerId().toString());
			        messageProducer.toMessageWarn(JSON.toJSONString(message));//消息提醒
					// 释放锁
					redisService.unLock(RedisStaticStringUtil.KLG_BUY_PAY_SURPLUS_ACCOUNT);
					return;
				}
				/** 查询抢单额度, ***/
				boolean flg= klgAmountLimitationService.isCheckNum(AmountLimitType.GrabSheet,buyPayMessage.getRobBuySmeNum());
				
				// 判断额度是否充足
				if (!flg) {
					RemoteMessage message=new RemoteMessage();
			        message.setMsgKey(MessageType.MESSAGE_KLG_BUY_ROB_FAIL.getKey());//消息类型 模板KEY//
			        message.setMsgType("1,2");// 1.站内信，2.短信,
			        message.setUserId(buyPayMessage.getCustomerId().toString());
			        messageProducer.toMessageWarn(JSON.toJSONString(message));//消息提醒
					// 释放锁
					redisService.unLock(RedisStaticStringUtil.KLG_BUY_PAY_SURPLUS_ACCOUNT);
					return;
				}
				/** 额度减少 */
				boolean flgAub= klgAmountLimitationService.reduceLimitQuota(AmountLimitType.GrabSheet,buyPayMessage.getRobBuySmeNum());
				if(!flgAub){
					RemoteMessage message=new RemoteMessage();
			        message.setMsgKey(MessageType.MESSAGE_KLG_BUY_ROB_FAIL.getKey());//消息类型 模板KEY//
			        message.setMsgType("1,2");// 1.站内信，2.短信,
			        message.setUserId(buyPayMessage.getCustomerId().toString());
			        messageProducer.toMessageWarn(JSON.toJSONString(message));//消息提醒
					// 释放锁
					redisService.unLock(RedisStaticStringUtil.KLG_BUY_PAY_SURPLUS_ACCOUNT);
					return;
				}

				/** 查询平台币价格 */
				BigDecimal ptbPrice = new BigDecimal((String) remoteKlgService.getPlatformRule1sConfig("issuePrice"));

				ExDigitalmoneyAccountRedis exaccountUsdt = this.selectAccount(buyPayMessage.getCustomerId(), "USDT");
				// 生成订单号
				String transactionNum = IdGenerate.transactionNum(NumConstant.Ex_Dm_Transaction);
				/** 生成买单 */
				KlgBuyTransaction klgBuyTransactionNew = new KlgBuyTransaction(null, buyPayMessage.getCustomerId(),
						transactionNum, exaccountUsdt.getId(), "USDT", buyPayMessage.getRobBuySmeNum(),
						buyPayMessage.getRobBuySmeNum().multiply(ptbPrice),
						buyPayMessage.getRobBuySmeNum().multiply(ptbPrice), new BigDecimal(0), new BigDecimal(0), 1, 2,
						3, null, "", "", System.currentTimeMillis());
				// 判断USDT账户是否充足
				BigDecimal usdtHotMoney = exaccountUsdt.getHotMoney();
				if (usdtHotMoney.compareTo(buyPayMessage.getRobBuySmeNum().multiply(ptbPrice)) >= 0) {
					List<KlgAssetsRecord> listKlgAssetsRecord = new ArrayList<>();//记录流水
					KlgAssetsRecord  klgAssetsRecordHot = null;
					KlgAssetsRecord  klgAssetsRecordCold = null;
					// 转冻结
					list.add(getAccountAdd(exaccountUsdt.getId(),
							new BigDecimal("-" + buyPayMessage.getRobBuySmeNum().multiply(ptbPrice)), 1, 1, 2,
							transactionNum));
					klgAssetsRecordHot = new KlgAssetsRecord(exaccountUsdt.getCustomerId(), exaccountUsdt.getId(),
							IdGenerate.transactionNum(NumConstant.Ex_Dm_Transaction), 1, exaccountUsdt.getCoinCode(),
							buyPayMessage.getRobBuySmeNum().multiply(ptbPrice), 2, TriggerPointEnum.RobOrder.getKey(), klgBuyTransactionNew.getTransactionNum(), "抢单,转冻结");
					list.add(getAccountAdd(exaccountUsdt.getId(), buyPayMessage.getRobBuySmeNum().multiply(ptbPrice), 2,
							1, 2, transactionNum));
					klgAssetsRecordCold = new KlgAssetsRecord(exaccountUsdt.getCustomerId(), exaccountUsdt.getId(),
							IdGenerate.transactionNum(NumConstant.Ex_Dm_Transaction), 2, exaccountUsdt.getCoinCode(),
							buyPayMessage.getRobBuySmeNum().multiply(ptbPrice), 1, TriggerPointEnum.RobOrder.getKey(), klgBuyTransactionNew.getTransactionNum(), "抢单,转冻结");
					listKlgAssetsRecord.add(klgAssetsRecordHot);
					listKlgAssetsRecord.add(klgAssetsRecordCold);
					klgAssetsRecordService.saveAll(listKlgAssetsRecord);//插入币账户流水记录
					
			        RemoteMessage message=new RemoteMessage();
			        message.setMsgKey(MessageType.MESSAGE_KLG_BUY_ROB_SUCCESS.getKey());//消息类型 模板KEY//
			        message.setMsgType("1,2");// 1.站内信，2.短信,
			        message.setUserId(klgBuyTransactionNew.getCustomerId().toString());
			        messageProducer.toMessageWarn(JSON.toJSONString(message));//消息提醒
			        klgBuyTransactionService.save(klgBuyTransactionNew);
				} else {
					/** 发送站内信,账户余额不足 */
					/** 发送站内信,账户余额不足 */
					/** 发送站内信,账户余额不足 */
					RemoteMessage message=new RemoteMessage();
			        message.setMsgKey(MessageType.MESSAGE_KLG_BUY_ROB_FAIL.getKey());//消息类型 模板KEY//
			        message.setMsgType("1,2");// 1.站内信，2.短信,
			        message.setUserId(klgBuyTransactionNew.getCustomerId().toString());
			        messageProducer.toMessageWarn(JSON.toJSONString(message));//消息提醒
					// 释放锁
					redisService.unLock(RedisStaticStringUtil.KLG_BUY_PAY_SURPLUS_ACCOUNT);
					return;
				}

				/** 匹配排队中的卖单 */
				QueryFilter filterKlgSellTransaction = new QueryFilter(KlgSellTransaction.class);
				filterKlgSellTransaction.addFilter("status=", 1);
				filterKlgSellTransaction.setOrderby("sellEndTime asc");
				List<KlgSellTransaction> listKlgSellTransaction = klgSellTransactionService
						.find(filterKlgSellTransaction);
				if (listKlgSellTransaction != null) {
					BigDecimal sellSum = new BigDecimal(0);
					ToBuySellAccountMessage toBuySellAccountMessage = null;
					for (KlgSellTransaction klgSellTransaction : listKlgSellTransaction) {
						sellSum = sellSum
								.add(klgSellTransaction.getSmeMoney().add(klgSellTransaction.getCandySmeMoney()));
						toBuySellAccountMessage = new ToBuySellAccountMessage(new BigDecimal(0), 1, 1,
								"sellSurplusAccount", klgSellTransaction.getTransactionNum(), "抢单匹配");
						listAccount.add(toBuySellAccountMessage);
						if (sellSum.compareTo(buyPayMessage.getRobBuySmeNum()) >= 0) {
							break;
						}
						klgSellTransaction.setStatus(2);
						klgSellTransactionService.update(klgSellTransaction);
					}

				}
				ToBuySellAccountMessage toBuySellAccountM = new ToBuySellAccountMessage(new BigDecimal(0), 1, 4,
						"buySurplusAccount", klgBuyTransactionNew.getTransactionNum(), "抢单");

				listAccount.add(toBuySellAccountM);
				// 发送币账户消息
				messageProducer.toAccount(JSON.toJSONString(list));

				messageProducer.toBuySellAccount(JSON.toJSONString(listAccount));
				// 释放锁
				redisService.unLock(RedisStaticStringUtil.KLG_BUY_PAY_SURPLUS_ACCOUNT);
				break;
			} catch (Exception e) {
				// TODO: handle exception
				// 释放锁
				redisService.unLock(RedisStaticStringUtil.KLG_BUY_PAY_SURPLUS_ACCOUNT);
				e.printStackTrace();
				KlgExceptionLogService klgExceptionLogService = SpringUtil.getBean("klgExceptionLogService");
				// 插入异常日志
				KlgExceptionLog klgExceptionLog = new KlgExceptionLog();
				klgExceptionLog.setFunctionName("抢单订单Id:"+buyPayMessage.getKlgBuyTransactionId());
				String strE = e.toString();
				if (strE.length() <= 230) {
					klgExceptionLog.setExceptionText(strE);
				} else {
					klgExceptionLog.setExceptionText(strE.substring(0, 230));
				}
				klgExceptionLogService.save(klgExceptionLog);
			}
		}

	}

}
