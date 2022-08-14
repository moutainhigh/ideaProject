package hry.admin.klg.transaction.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;

import hry.admin.customer.service.AppCustomerService;
import hry.admin.exchange.model.ExDigitalmoneyAccount;
import hry.admin.exchange.service.ExDigitalmoneyAccountService;
import hry.admin.klg.assetsrecord.model.KlgAssetsRecord;
import hry.admin.klg.assetsrecord.service.KlgAssetsRecordService;
import hry.admin.klg.buysellaccount.model.KlgBuySellAccount;
import hry.admin.klg.buysellaccount.service.KlgBuySellAccountService;
import hry.admin.klg.log.model.KlgExceptionLog;
import hry.admin.klg.log.service.KlgExceptionLogService;
import hry.admin.klg.platform.model.PlatformAccountUtil;
import hry.admin.klg.transaction.model.KlgBuyTransaction;
import hry.admin.klg.transaction.model.KlgSellTransaction;
import hry.admin.klg.transaction.model.vo.KlgBuyTransactionVo;
import hry.admin.klg.transaction.model.vo.KlgSellTransactionVo;
import hry.admin.klg.transaction.service.KlgBuySellMatchingService;
import hry.admin.klg.transaction.service.KlgBuyTransactionService;
import hry.admin.klg.transaction.service.KlgSellTransactionService;
import hry.admin.klg.utils.RedisStaticStringUtil;
import hry.admin.mq.producer.service.MessageProducer;
import hry.admin.web.service.AppMessageService;
import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.front.redis.model.UserRedis;
import hry.klg.enums.TriggerPointEnum;
import hry.klg.model.KlgPlatformCurrency;
import hry.klg.model.PlatformAccountadd;
import hry.klg.remote.RemoteKlgService;
import hry.klg.remote.model.ToBuySellAccountMessage;
import hry.message.model.MessageType;
import hry.message.model.RemoteMessage;
import hry.redis.common.dao.RedisUtil;
import hry.redis.common.utils.RedisService;
import hry.trade.redis.model.Accountadd;
import hry.trade.redis.model.ExDigitalmoneyAccountRedis;
import hry.util.PageFactory;
import hry.util.QueryFilter;
import hry.util.SpringUtil;
import hry.util.UserRedisUtils;
import hry.util.idgenerate.IdGenerate;
import hry.util.idgenerate.NumConstant;

@Service("klgBuySellMatchingService")
public class KlgBuySellMatchingServiceImpl extends BaseServiceImpl<KlgBuyTransaction, Long>
		implements KlgBuySellMatchingService {

	@Resource(name = "klgBuyTransactionDao")
	@Override
	public void setDao(BaseDao<KlgBuyTransaction, Long> dao) {
		super.dao = dao;
	}

	@Resource
	private RedisService redisService;
	@Resource
	private KlgBuyTransactionService klgBuyTransactionService;
	@Resource
	private KlgSellTransactionService klgSellTransactionService;
	@Resource
	private KlgBuySellAccountService klgBuySellAccountService;
	@Resource
	private MessageProducer messageProducer;
	@Resource
	private RemoteKlgService remoteKlgService;
	@Resource
	private AppMessageService appMessageService;
	@Resource
	private AppCustomerService appCustomerService;
	@Resource
	private KlgAssetsRecordService klgAssetsRecordService;
	@Resource
	private ExDigitalmoneyAccountService exDigitalmoneyAccountService;

	@Override
	public PageResult pipeiSelectPage(QueryFilter filter) {
		// TODO Auto-generated method stub
		String overAllIds = filter.getRequest().getParameter("overAllIds");// 选中买单的id
		if (overAllIds == null || overAllIds.equals("")) {
			return new PageResult();
		}
		/** 查询选中的买单 */
		BigDecimal buySum = klgBuyTransactionService.getBuyTransactionByIdINIds(overAllIds);
		QueryFilter filter1 = new QueryFilter(KlgSellTransaction.class);
		filter1.setRequest(null);
		List<KlgSellTransactionVo> sellList = klgSellTransactionService.findListBySql(filter1);
		List<KlgSellTransactionVo> list = new ArrayList<>();
		BigDecimal bdSellSum = new BigDecimal(0);
		List<Long> sellListArray = new ArrayList<>();// 记录匹配的卖单id
		for (KlgSellTransactionVo klgSellTransactionVo : sellList) {
			bdSellSum = bdSellSum.add(klgSellTransactionVo.getSmeMoney().add(klgSellTransactionVo.getCandySmeMoney()));
			if (buySum.compareTo(bdSellSum) < 0) {
				break;
			}
			list.add(klgSellTransactionVo);
			sellListArray.add(klgSellTransactionVo.getId());
		}
		// ----------------------分页查询头部外壳------------------------------
		Page<KlgSellTransactionVo> page = PageFactory.getPage(filter);
		// ----------------------分页查询头部外壳------------------------------
		if(sellListArray.size()<=0){
			return new PageResult(page, filter.getPage(), filter.getPageSize());
		}
		klgSellTransactionService.findSellTransactionByIdINIds(sellListArray);
		return new PageResult(page, filter.getPage(), filter.getPageSize());
	}

	@Override
	public JsonResult selectBuySellData() {
		// TODO Auto-generated method stub
		JsonResult jsonResult = new JsonResult();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			/* 查询买单排队成功待支付 */
			map.clear();
			map.put("status", 2);
			KlgBuyTransactionVo klgBuyTransactionVo = klgBuyTransactionService.getUsdtMoneySumByStatus(map);
			/* 查询买单排队成功待收款 */
			map.clear();
			map.put("status", 3);
			KlgBuyTransactionVo klgBuyTrVo = klgBuyTransactionService.getUsdtMoneySumByStatus(map);

			/* 查询卖单信息排队成功 */
			map.clear();
			map.put("status", 2);
			KlgSellTransactionVo klgSellTransactionVo = klgSellTransactionService
					.getSmeMoneySumAndUsdtMoneyByStatus(map);

			/* 查询卖单信息待收款 */
			map.clear();
			map.put("status", 3);
			KlgSellTransactionVo klgSellTrVo = klgSellTransactionService.getSmeMoneySumAndUsdtMoneyByStatus(map);
			/** 查询买单支付剩余账户金额USDT */
			QueryFilter filterKlgBuyAccount = new QueryFilter(KlgBuySellAccount.class);
			filterKlgBuyAccount.addFilter("accountName=", "buySurplusAccount");
			KlgBuySellAccount klgBuyAccount = klgBuySellAccountService.get(filterKlgBuyAccount);
			/** 查询卖单卖出剩余账户金额SME */
			QueryFilter filterKlgSellAccount = new QueryFilter(KlgBuySellAccount.class);
			filterKlgSellAccount.addFilter("accountName=", "sellSurplusAccount");
			KlgBuySellAccount klgSellAccount = klgBuySellAccountService.get(filterKlgSellAccount);
			map.clear();
			if (klgBuyTransactionVo != null) {
				map.put("buyPay1",
						klgBuyTransactionVo.getUsdtMoneySum() == null ? 0 : klgBuyTransactionVo.getUsdtMoneySum());// 买单待支付usdt
			}
			if (klgBuyTrVo != null) {
				map.put("buyPay2", klgBuyTrVo.getSmeMoneySum() == null ? 0 : klgBuyTrVo.getSmeMoneySum());// 买单待收款sme
			}
			if (klgSellTransactionVo != null) {
				map.put("sellPay1",
						klgSellTransactionVo.getSmeMoneySum() == null ? 0 : klgSellTransactionVo.getSmeMoneySum());// 排队成功
			}
			if (klgSellTrVo != null) {
				map.put("sellPay2", klgSellTrVo.getUsdtMoneySum() == null ? 0 : klgSellTrVo.getUsdtMoneySum());// 卖单待收款usdt
			}
			map.put("buyPay3", klgBuyAccount.getMoney());// 买单已支付剩余usdt
			map.put("sellPay3", klgSellAccount.getMoney());// 卖单剩余sme
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			// return jsonResult.setSuccess(false).setMsg("系统异常");
		}

		return jsonResult.setSuccess(true).setObj(map);
	}

	@Override
	public Map<String, Object> selectPipeiData(QueryFilter filter, String ids) {
		// TODO Auto-generated method stub
		/** 查询选中的买单 */
		BigDecimal buySum = klgBuyTransactionService.getBuyTransactionByIdINIds(ids);
		filter.setRequest(null);
		List<KlgSellTransactionVo> sellList = klgSellTransactionService.findListBySql(filter);
		List<KlgSellTransactionVo> list = new ArrayList<>();
		BigDecimal bdSellSum = new BigDecimal(0);
		if(buySum!=null){
			for (KlgSellTransactionVo klgSellTransactionVo : sellList) {
				bdSellSum = bdSellSum.add(klgSellTransactionVo.getSmeMoney().add(klgSellTransactionVo.getCandySmeMoney()));
				if (buySum.compareTo(bdSellSum) < 0) {
					bdSellSum = bdSellSum
							.subtract(klgSellTransactionVo.getSmeMoney().add(klgSellTransactionVo.getCandySmeMoney()));
					break;
				}
				list.add(klgSellTransactionVo);
			}
		}
		
		Map<String, Object> map = new HashMap<>();
		map.put("buySum", buySum==null?new BigDecimal(0):buySum);
		map.put("sellSum", bdSellSum);

		KlgSellTransactionVo klgSellTrVo = klgSellTransactionService.getSmeMoneySumAndUsdtMoneyByStatus(map);
		/** 查询买单支付剩余账户金额USDT */
		QueryFilter filterKlgBuyAccount = new QueryFilter(KlgBuySellAccount.class);
		filterKlgBuyAccount.addFilter("accountName=", "buySurplusAccount");
		KlgBuySellAccount klgBuyAccount = klgBuySellAccountService.get(filterKlgBuyAccount);
		/** 查询卖单卖出剩余账户金额SME */
		QueryFilter filterKlgSellAccount = new QueryFilter(KlgBuySellAccount.class);
		filterKlgSellAccount.addFilter("accountName=", "sellSurplusAccount");
		KlgBuySellAccount klgSellAccount = klgBuySellAccountService.get(filterKlgSellAccount);
		map.put("buyPay", klgBuyAccount.getMoney()==null?new BigDecimal(0):klgBuyAccount.getMoney());// 买单已支付剩余usdt
		map.put("sellPay", klgSellAccount.getMoney()==null?new BigDecimal(0):klgSellAccount.getMoney());// 卖单剩余sme
		return map;
	}

	@Override
	public JsonResult peipeiSubmit(String ids, QueryFilter filter) {
		// TODO Auto-generated method stub
		JsonResult jsonResult = new JsonResult();
		List<Accountadd> list = new ArrayList<Accountadd>();
		Map<String, Object> map = new HashMap<>();
		Boolean lock = redisService.lock(RedisStaticStringUtil.KLG_BUY_PAY_SURPLUS_ACCOUNT);
		try {
			if (lock) {
				List<KlgSellTransactionVo> listsell = new ArrayList<>();// 记录对应的卖单
				List<Long> sellListArray = new ArrayList<>();// 记录匹配的卖单id
				List<ToBuySellAccountMessage> listAccount = new ArrayList<>();
				BigDecimal bdSellSum = new BigDecimal(0);
				/**
				 * 查询匹配的卖单， 修改订单状态为2排队成功
				 */
				BigDecimal buySum = klgBuyTransactionService.getBuyTransactionByIdINIds(ids);
				if(buySum==null){
					redisService.unLock(RedisStaticStringUtil.KLG_BUY_PAY_SURPLUS_ACCOUNT);
					return jsonResult.setSuccess(false).setMsg("选中订单不正确");
				}
				filter.setRequest(null);
				List<KlgSellTransactionVo> sellList = klgSellTransactionService.findListBySql(filter);
				
				for (KlgSellTransactionVo klgSellTransactionVo : sellList) {
					bdSellSum = bdSellSum
							.add(klgSellTransactionVo.getSmeMoney().add(klgSellTransactionVo.getCandySmeMoney()));
					if (buySum.compareTo(bdSellSum) < 0) {
						break;
					}
					listsell.add(klgSellTransactionVo);
					sellListArray.add(klgSellTransactionVo.getId());
					ToBuySellAccountMessage toBuySellAccountMessage = new ToBuySellAccountMessage(new BigDecimal(0), 1,
							1, "sellSurplusAccount", klgSellTransactionVo.getTransactionNum(), "买单匹配卖单");
					listAccount.add(toBuySellAccountMessage);
			        RemoteMessage message=new RemoteMessage();
			        message.setMsgKey(MessageType.MESSAGE_KLG_SELL_QUEUE_SUCCESS.getKey());//消息类型 模板KEY//
			        message.setMsgType("1,2");// 1.站内信，2.短信,
			        message.setUserId(klgSellTransactionVo.getCustomerId().toString());
			        messageProducer.toMessageWarn(JSON.toJSONString(message));//消息提醒
				}
				if(sellListArray.size()>0){
					// 更新状态
					map.clear();
					map.put("ids", sellListArray);
					klgSellTransactionService.updateStatus(map);
				}
				/**
				 * 查询选中买单中状态的订单， 修改订单状态为2排队成功待支付
				 */
				map.clear();
				String[] strarray = ids.split(",");
				List<String> strsToList = Arrays.asList(strarray);
				map.put("ids", strsToList);
				klgBuyTransactionService.updateStatus(map);
				
				/** ======查询出选中的订单，站内信===== */
				List<KlgBuyTransactionVo> listBuyM = klgBuyTransactionService.findBuyTransactionByIdINIds(ids, null);
				for (KlgBuyTransactionVo klgBuyTM : listBuyM) {
					Map<String, String> paramM = new HashMap<>();
			        paramM.put("${firstMoney}", klgBuyTM.getFirstMoney().toString());
			        paramM.put("${lastMoney}", klgBuyTM.getLastMoney().toString());
			        RemoteMessage message=new RemoteMessage();
			        message.setParam(paramM);
			        message.setMsgKey(MessageType.MESSAGE_KLG_BUY_QUEUE_SUCCESS.getKey());//消息类型 模板KEY//
			        message.setMsgType("1,2");// 1.站内信，2.短信,
			        message.setUserId(klgBuyTM.getCustomerId().toString());
			        messageProducer.toMessageWarn(JSON.toJSONString(message));//消息提醒
				}

				/** ======查询出托管状态的订单，发送付款操作消息===== */
				List<KlgBuyTransactionVo> listBuy = klgBuyTransactionService.findBuyTransactionByIdINIds(ids, 2);
				List<KlgAssetsRecord> listKlgAssetsRecord = new ArrayList<>();// 记录流水
				KlgAssetsRecord klgAssetsRecordHot = null;
				KlgAssetsRecord klgAssetsRecordCold = null;
				for (KlgBuyTransactionVo klgBuyTransaction : listBuy) {
					try {
						list = new ArrayList<Accountadd>();
						/** 查询用户币账户信息 */
						ExDigitalmoneyAccountRedis exaccount = this.selectAccount(klgBuyTransaction.getCustomerId(),
								klgBuyTransaction.getCoinCode());
						// 获取热账户
						BigDecimal hotMoney = exaccount.getHotMoney();
						// 判断热账户是否充足
						if (hotMoney.compareTo(klgBuyTransaction.getLastMoney()) < 0) {
							// return jsonResult.setSuccess(false).setMsg("账户余额不足");
							/*** 发送站内信,余额不足 ***/
						} else {
							// usdt热账户减少 冷账户增加
							list.add(
									getAccountAdd(exaccount.getId(), new BigDecimal("-" + klgBuyTransaction.getLastMoney()),
											1, 1, 2, klgBuyTransaction.getTransactionNum()));
							list.add(
									getAccountAdd(exaccount.getId(), new BigDecimal("+" + klgBuyTransaction.getLastMoney()),
											2, 1, 2, klgBuyTransaction.getTransactionNum()));
							messageProducer.toAccount(JSON.toJSONString(list));
							// 插入币账户流水
							klgAssetsRecordHot = new KlgAssetsRecord(exaccount.getCustomerId(), exaccount.getId(),
									IdGenerate.transactionNum(NumConstant.Ex_Dm_Transaction), 1, exaccount.getCoinCode(),
									klgBuyTransaction.getLastMoney(), 2, TriggerPointEnum.BuyPayTailMoney.getKey(),
									klgBuyTransaction.getTransactionNum(), "匹配成功,托管支付,转冻结");

							klgAssetsRecordCold = new KlgAssetsRecord(exaccount.getCustomerId(), exaccount.getId(),
									IdGenerate.transactionNum(NumConstant.Ex_Dm_Transaction), 2, exaccount.getCoinCode(),
									klgBuyTransaction.getLastMoney(), 1, TriggerPointEnum.BuyPayTailMoney.getKey(),
									klgBuyTransaction.getTransactionNum(), "匹配成功,托管支付,转冻结");

							listKlgAssetsRecord.add(klgAssetsRecordHot);
							listKlgAssetsRecord.add(klgAssetsRecordCold);
							/** 更新订单状态为3待收款 */
							// 更新订单状态
							klgBuyTransaction.setStatus(3);
							klgBuyTransactionService.update(klgBuyTransaction);

							ToBuySellAccountMessage toBuySellAccountMessage = new ToBuySellAccountMessage(new BigDecimal(0),
									1, 5, "buySurplusAccount", klgBuyTransaction.getTransactionNum(), "买单托管支付");
							listAccount.add(toBuySellAccountMessage);
						}
					} catch (Exception e) {
						// TODO: handle exception
						KlgExceptionLogService klgExceptionLogService = SpringUtil.getBean("klgExceptionLogService");
						// 插入异常日志
						KlgExceptionLog klgExceptionLog = new KlgExceptionLog();
						klgExceptionLog.setCustomerId(klgBuyTransaction.getCustomerId().toString());
						klgExceptionLog.setFunctionName("KlgBuySellMatchingServiceImpl.peipeiSubmit buyId:" + klgBuyTransaction.getId());
						String str = e.toString();
						if (str.length() <= 230) {
							klgExceptionLog.setExceptionText(str);
						} else {
							klgExceptionLog.setExceptionText(str.substring(0, 230));
						}
						klgExceptionLogService.save(klgExceptionLog);
					}

				}
				klgAssetsRecordService.saveAll(listKlgAssetsRecord);// 插入币账户流水记录
				messageProducer.toBuySellAccount(JSON.toJSONString(listAccount));

				/** ======================= **/

				redisService.unLock(RedisStaticStringUtil.KLG_BUY_PAY_SURPLUS_ACCOUNT);
				return jsonResult.setSuccess(true).setMsg("操作成功!");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			redisService.unLock(RedisStaticStringUtil.KLG_BUY_PAY_SURPLUS_ACCOUNT);
		}

		return jsonResult.setSuccess(false).setMsg("系统繁忙，请稍后再试!");
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
		QueryFilter filterKlgAccount = new QueryFilter(ExDigitalmoneyAccount.class);
		filterKlgAccount.addFilter("customerId=", customerId);
		filterKlgAccount.addFilter("coinCode=", coinCode);
		ExDigitalmoneyAccount exDigitalmoneyAccount = exDigitalmoneyAccountService.get(filterKlgAccount);
		// 获得币账户
		/*ExDigitalmoneyAccountRedis exaccount = UserRedisUtils.getAccount(userRedis.getDmAccountId(coinCode).toString(),
				coinCode);*/
		ExDigitalmoneyAccountRedis exaccount = UserRedisUtils.getAccount(exDigitalmoneyAccount.getId().toString(),
				coinCode);
		return exaccount;
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
	public Map<String, Object> selectTiaokongData() {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<>();
		/* 查询买单排队成功待支付，usdt首款在冻结中，尾款为支付，sme未买入 */
		map.clear();
		map.put("status", 2);
		KlgBuyTransactionVo klgBuyTransaction = klgBuyTransactionService.getUsdtMoneySumByStatus(map);
		if (klgBuyTransaction == null) {
			klgBuyTransaction = new KlgBuyTransactionVo();
			klgBuyTransaction.setSmeMoneySum(new BigDecimal(0));
			klgBuyTransaction.setUsdtMoneySum(new BigDecimal(0));
		}
		/* 查询买单排队成功待收款，usdt首款尾款在冻结账户中，sme未买入 */
		map.clear();
		map.put("status", 3);
		KlgBuyTransactionVo klgBuyTrVo = klgBuyTransactionService.getUsdtMoneySumByStatus(map);
		if (klgBuyTrVo == null) {
			klgBuyTrVo = new KlgBuyTransactionVo();
			klgBuyTrVo.setSmeMoneySum(new BigDecimal(0));
			klgBuyTrVo.setUsdtMoneySum(new BigDecimal(0));
		}
		/* 查询卖单信息排队成功，sme在冻结中，usdt为收入 */
		map.clear();
		map.put("status", 2);
		KlgSellTransactionVo klgSellTransactionVo = klgSellTransactionService.getSmeMoneySumAndUsdtMoneyByStatus(map);
		if (klgSellTransactionVo == null) {
			klgSellTransactionVo = new KlgSellTransactionVo();
			klgSellTransactionVo.setSmeMoneySum(new BigDecimal(0));
			klgSellTransactionVo.setUsdtMoneySum(new BigDecimal(0));
		}
		/* 查询卖单信息待收款，sme在剩余账户中，usdt为收入 */
		map.clear();
		map.put("status", 3);
		KlgSellTransactionVo klgSellTrVo = klgSellTransactionService.getSmeMoneySumAndUsdtMoneyByStatus(map);
		if (klgSellTrVo == null) {
			klgSellTrVo = new KlgSellTransactionVo();
			klgSellTrVo.setSmeMoneySum(new BigDecimal(0));
			klgSellTrVo.setUsdtMoneySum(new BigDecimal(0));
		}
		/** 查询买单支付剩余账户金额USDT */
		QueryFilter filterKlgBuyAccount = new QueryFilter(KlgBuySellAccount.class);
		filterKlgBuyAccount.addFilter("accountName=", "buySurplusAccount");
		KlgBuySellAccount klgBuyAccount = klgBuySellAccountService.get(filterKlgBuyAccount);
		/** 查询卖单卖出剩余账户金额SME */
		QueryFilter filterKlgSellAccount = new QueryFilter(KlgBuySellAccount.class);
		filterKlgSellAccount.addFilter("accountName=", "sellSurplusAccount");
		KlgBuySellAccount klgSellAccount = klgBuySellAccountService.get(filterKlgSellAccount);

		/** 匹配成功买单付款USDT数量 */
		// 买单排队成功待支付+买单排队成功待收款+买单支付剩余
		BigDecimal buyBuyUsdtNum = klgBuyTransaction.getUsdtMoneySum().add(klgBuyTrVo.getUsdtMoneySum())
				.add(klgBuyAccount.getMoney());
		/** 匹配成功买单买入SME数量 */
		// 买单排队成功待支付+买单排队成功待收款
		BigDecimal buyBuySmeNum = klgBuyTransaction.getSmeMoneySum().add(klgBuyTrVo.getSmeMoneySum());
		/** 匹配成功卖单卖出SME数量 */
		// 卖单信息排队成功+卖单卖出剩余
		BigDecimal sellSellSmeSum = klgSellTransactionVo.getSmeMoneySum().add(klgSellAccount.getMoney());
		/** 匹配成功卖单收入USDT数量 */
		// 卖单信息排队成功+卖单信息待收款
		BigDecimal sellSellUsdtSum = klgSellTransactionVo.getUsdtMoneySum().add(klgSellTrVo.getUsdtMoneySum());

		/** 需要调控sme数量 */
		// 匹配成功买单买入SME数量-匹配成功卖单卖出SME数量
		BigDecimal tiaokongSme = buyBuySmeNum.subtract(sellSellSmeSum);

		/** 需要调控usdt数量 */
		// 匹配成功卖单收入USDT数量-匹配成功买单付款USDT数量
		BigDecimal tiaokongUsdt = sellSellUsdtSum.subtract(buyBuyUsdtNum);
		if (tiaokongUsdt.compareTo(new BigDecimal(0)) <= 0) {
			tiaokongUsdt = new BigDecimal(0);
		}
		map.clear();
		map.put("buyBuyUsdtNum", buyBuyUsdtNum);
		map.put("buyBuySmeNum", buyBuySmeNum);
		map.put("sellSellSmeSum", sellSellSmeSum);
		map.put("sellSellUsdtSum", sellSellUsdtSum);
		map.put("tiaokongSme", 0);
		map.put("tiaokongUsdt", 0);
		map.put("buySurplus", klgBuyAccount.getMoney());
		return map;
	}

	@Override
	public JsonResult tiaokongSubmit(String tiaokongSme, String tiaokongUsdt, QueryFilter filter) {
		// TODO Auto-generated method stub
		JsonResult jsonResult = new JsonResult();
		if (tiaokongSme == null) {
			tiaokongSme = "0";
		}
		if (tiaokongUsdt == null) {
			tiaokongUsdt = "0";
		}
		// 获取分布式锁
		if (redisService.lock(RedisStaticStringUtil.KLG_BUY_PAY_SURPLUS_ACCOUNT)) {
			// 发送清理订单消息
			List<ToBuySellAccountMessage> listAccount = new ArrayList<>();
			ToBuySellAccountMessage toBuySellAccountMessage = new ToBuySellAccountMessage(new BigDecimal(tiaokongSme),
					2, 2, "sellSurplusAccount", "", "平台调控");
			listAccount.add(toBuySellAccountMessage);
			ToBuySellAccountMessage toBuySellAMessage = new ToBuySellAccountMessage(new BigDecimal(tiaokongUsdt), 2, 2,
					"buySurplusAccount", "", "平台调控");
			listAccount.add(toBuySellAMessage);
			messageProducer.toBuySellAccount(JSON.toJSONString(listAccount));
			// 释放锁
			redisService.unLock(RedisStaticStringUtil.KLG_BUY_PAY_SURPLUS_ACCOUNT);
		} else {
			return jsonResult.setSuccess(false).setMsg("系统繁忙，请稍后再试！");
		}
		return jsonResult.setSuccess(true).setMsg("调控成功");
	}

	@Override
	public JsonResult chidanSubmit(String ids) {
		// TODO Auto-generated method stub
		JsonResult jsonResult = new JsonResult();
		Boolean lock = redisService.lock(RedisStaticStringUtil.KLG_BUY_PAY_SURPLUS_ACCOUNT);
		try {
			if (lock) {
				String[] strarray = ids.split(",");
				List<String> strsToList = Arrays.asList(strarray);
				KlgBuyTransaction klgBuyTransaction = null;
				for (String id : strsToList) {
					/* 根据id查询买单 */
					klgBuyTransaction = klgBuyTransactionService.get(Long.valueOf(id));
					if (klgBuyTransaction != null) {
						// 平台吃单
						// 判断订单状态，平台只能吃状态为排队中的订单
						if (klgBuyTransaction.getStatus() == 1) {
							/** 修改订单吃单订单状态，等待用户支付 */
							klgBuyTransaction.setStatus(2);
							klgBuyTransaction.setEatStatus(2);
							klgBuyTransactionService.update(klgBuyTransaction);
						}
					}
				}
				/** ======查询出托管状态的订单，发送付款操作消息===== */
				List<ToBuySellAccountMessage> listAccount = new ArrayList<>();
				List<Accountadd> list = new ArrayList<Accountadd>();
				List<KlgBuyTransactionVo> listBuy = klgBuyTransactionService.findBuyTransactionByIdINIds(ids, 2);
				List<KlgAssetsRecord> listKlgAssetsRecord = new ArrayList<>();// 记录流水
				KlgAssetsRecord klgAssetsRecordHot = null;
				KlgAssetsRecord klgAssetsRecordCold = null;
				for (KlgBuyTransactionVo klgBuyTransactionVo : listBuy) {
					try {
						/** 查询用户币账户信息 */
						ExDigitalmoneyAccountRedis exaccount = this.selectAccount(klgBuyTransactionVo.getCustomerId(),
								klgBuyTransactionVo.getCoinCode());
						if(exaccount==null){
							klgBuyTransactionVo.setStatus(1);
							klgBuyTransactionService.update(klgBuyTransactionVo);
							continue;
						}
						// 获取热账户
						BigDecimal hotMoney = exaccount.getHotMoney();
						// 判断热账户是否充足
						if (hotMoney.compareTo(klgBuyTransactionVo.getLastMoney()) < 0) {
							// return jsonResult.setSuccess(false).setMsg("账户余额不足");
							/*** 发送站内信,余额不足 ***/
						} else {
							// usdt热账户减少 冷账户增加
							list = new ArrayList<Accountadd>();
							list.add(getAccountAdd(exaccount.getId(),
									new BigDecimal("-" + klgBuyTransactionVo.getLastMoney()), 1, 1, 2,
									klgBuyTransactionVo.getTransactionNum()));
							// 插入币账户流水
							klgAssetsRecordHot = new KlgAssetsRecord(exaccount.getCustomerId(), exaccount.getId(),
									IdGenerate.transactionNum(NumConstant.Ex_Dm_Transaction), 1, exaccount.getCoinCode(),
									klgBuyTransactionVo.getLastMoney(), 2, TriggerPointEnum.EatBuy.getKey(),
									klgBuyTransactionVo.getTransactionNum(), "平台吃单,托管支付,转冻结");
							list.add(getAccountAdd(exaccount.getId(),
									new BigDecimal("+" + klgBuyTransactionVo.getLastMoney()), 2, 1, 2,
									klgBuyTransactionVo.getTransactionNum()));
							
							messageProducer.toAccount(JSON.toJSONString(list));
							
							klgAssetsRecordCold = new KlgAssetsRecord(exaccount.getCustomerId(), exaccount.getId(),
									IdGenerate.transactionNum(NumConstant.Ex_Dm_Transaction), 2, exaccount.getCoinCode(),
									klgBuyTransactionVo.getLastMoney(), 1, TriggerPointEnum.EatBuy.getKey(),
									klgBuyTransactionVo.getTransactionNum(), "平台吃单,托管支付,转冻结");
							listKlgAssetsRecord.add(klgAssetsRecordHot);
							listKlgAssetsRecord.add(klgAssetsRecordCold);
							/** 更新订单状态为3待收款 */
							// 更新订单状态
							klgBuyTransactionVo.setStatus(3);
							klgBuyTransactionService.update(klgBuyTransactionVo);
							ToBuySellAccountMessage toBuySellAccountMessage = new ToBuySellAccountMessage(new BigDecimal(0),
									1, 5, "buySurplusAccount", klgBuyTransactionVo.getTransactionNum(), "买单托管支付");
							listAccount.add(toBuySellAccountMessage);
						}
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
						KlgExceptionLogService klgExceptionLogService = SpringUtil.getBean("klgExceptionLogService");
						// 插入异常日志
						KlgExceptionLog klgExceptionLog = new KlgExceptionLog();
						klgExceptionLog.setCustomerId(klgBuyTransactionVo.getCustomerId().toString());
						klgExceptionLog.setFunctionName("KlgBuySellMatchingServiceImpl.chidanSubmit buyId:" + klgBuyTransactionVo.getId());
						String str = e.toString();
						if (str.length() <= 230) {
							klgExceptionLog.setExceptionText(str);
						} else {
							klgExceptionLog.setExceptionText(str.substring(0, 230));
						}
						klgExceptionLogService.save(klgExceptionLog);
					}
				}
				klgAssetsRecordService.saveAll(listKlgAssetsRecord);//插入币账户流水记录
				messageProducer.toBuySellAccount(JSON.toJSONString(listAccount));
				redisService.unLock(RedisStaticStringUtil.KLG_BUY_PAY_SURPLUS_ACCOUNT);
			} else {
				return jsonResult.setSuccess(false).setMsg("系统繁忙,请稍后再试！");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			redisService.unLock(RedisStaticStringUtil.KLG_BUY_PAY_SURPLUS_ACCOUNT);
			return jsonResult.setSuccess(false).setMsg("系统异常,请稍后再试！");
		}

		return jsonResult.setSuccess(true).setMsg("吃单成功,等待买方支付！");
	}

	@Override
	public Map<String, Object> selectPipeiSellData(QueryFilter filter, String ids) {
		// TODO Auto-generated method stub
		/** 查询选中的卖单 */
		BigDecimal sellSum = klgSellTransactionService.getSellTransactionByIdINIds(ids);
		List<KlgBuyTransactionVo> sellList = klgBuyTransactionService.findListBySql(filter);
		List<KlgBuyTransactionVo> list = new ArrayList<>();
		BigDecimal bdBuySum = new BigDecimal(0);
		if(sellSum!=null){
			for (KlgBuyTransactionVo klgBuyTransactionVo : sellList) {
				bdBuySum = bdBuySum.add(klgBuyTransactionVo.getSmeMoney());
				if (sellSum.compareTo(bdBuySum) < 0) {
					bdBuySum = bdBuySum.subtract(klgBuyTransactionVo.getSmeMoney());
					break;
				}
				list.add(klgBuyTransactionVo);
			}
		}
		
		Map<String, Object> map = new HashMap<>();
		map.put("sellSum", sellSum==null?new BigDecimal(0):sellSum);
		map.put("buySum", bdBuySum==null?new BigDecimal(0):bdBuySum);
		return map;
	}

	@Override
	public PageResult pipeiSellSelectPage(QueryFilter filter) {
		// TODO Auto-generated method stub
		String overAllIds = filter.getRequest().getParameter("overAllIds");// 选中卖单的id
		if (overAllIds == null || overAllIds.equals("")) {
			return new PageResult();
		}
		/** 查询选中的卖单 */
		BigDecimal sellSum = klgSellTransactionService.getSellTransactionByIdINIds(overAllIds);
		QueryFilter filter1 = new QueryFilter(KlgBuyTransaction.class);
		List<KlgBuyTransactionVo> buyList = klgBuyTransactionService.findListBySql(filter1);
		List<KlgBuyTransactionVo> list = new ArrayList<>();
		BigDecimal bdBuySum = new BigDecimal(0);
		List<Long> buyListArray = new ArrayList<>();// 记录匹配的卖单id
		for (KlgBuyTransactionVo klgBuyTransactionVo : buyList) {
			bdBuySum = bdBuySum.add(klgBuyTransactionVo.getSmeMoney());
			if (sellSum.compareTo(bdBuySum) < 0) {
				break;
			}
			list.add(klgBuyTransactionVo);
			buyListArray.add(klgBuyTransactionVo.getId());
		}
		// ----------------------分页查询头部外壳------------------------------
		Page<KlgBuyTransactionVo> page = PageFactory.getPage(filter);
		// ----------------------分页查询头部外壳------------------------------
		if(buyListArray==null||buyListArray.size()<=0){
			return new PageResult(page, filter.getPage(), filter.getPageSize());
		}
		klgBuyTransactionService.findBuyTransactionByIdINIds(buyListArray);
		return new PageResult(page, filter.getPage(), filter.getPageSize());
	}

	@Override
	public JsonResult peipeiSellSubmit(String ids, QueryFilter filter) {
		// TODO Auto-generated method stub
		JsonResult jsonResult = new JsonResult();
		List<Accountadd> list = new ArrayList<Accountadd>();
		Map<String, Object> map = new HashMap<>();
		Boolean lock = redisService.lock(RedisStaticStringUtil.KLG_BUY_PAY_SURPLUS_ACCOUNT);
		try {
			if (lock) {
				/**
				 * 查询匹配的买单， 修改订单状态为2排队成功
				 */
				BigDecimal sellSum = klgSellTransactionService.getSellTransactionByIdINIds(ids);
				if(sellSum==null){
					redisService.unLock(RedisStaticStringUtil.KLG_BUY_PAY_SURPLUS_ACCOUNT);
					return jsonResult.setSuccess(false).setMsg("选中订单不正确");
				}
				List<KlgBuyTransactionVo> buyList = klgBuyTransactionService.findListBySql(filter);
				List<KlgBuyTransactionVo> listbuy = new ArrayList<>();// 记录对应的卖单
				List<Long> buyListArray = new ArrayList<>();// 记录匹配的卖单id
				List<ToBuySellAccountMessage> listAccount = new ArrayList<>();
				BigDecimal bdBuySum = new BigDecimal(0);
				StringBuffer strb = new StringBuffer("");
				for (KlgBuyTransactionVo klgBuyTransactionVo : buyList) {
					bdBuySum = bdBuySum.add(klgBuyTransactionVo.getSmeMoney());
					if (sellSum.compareTo(bdBuySum) < 0) {
						break;
					}
					listbuy.add(klgBuyTransactionVo);
					buyListArray.add(klgBuyTransactionVo.getId());
					strb.append("," + klgBuyTransactionVo.getId());
				}
				if(buyListArray.size()>0){
					// 更新状态
					map.clear();
					map.put("ids", buyListArray);
					klgBuyTransactionService.updateStatus(map);
				}

				/**
				 * 查询选中卖单中状态的订单， 修改订单状态为2排队成功待支付
				 */
				map.clear();
				String[] strarray = ids.split(",");
				List<String> strsToList = Arrays.asList(strarray);
				map.put("ids", strsToList);
				klgSellTransactionService.updateStatus(map);
				/** 发送消息 */
				// 查询选中的卖单
				List<KlgSellTransactionVo> listVo = klgSellTransactionService
						.findSellTransactionByIdINIdsStr(strsToList);
				ToBuySellAccountMessage toBuySellAccountMessage = null;
				for (KlgSellTransactionVo klgSellTransactionVo : listVo) {
					toBuySellAccountMessage = new ToBuySellAccountMessage(new BigDecimal(0), 1, 1, "sellSurplusAccount",
							klgSellTransactionVo.getTransactionNum(), "卖单匹配买单");
					listAccount.add(toBuySellAccountMessage);
				}

				/** ======查询出托管状态的订单，发送付款操作消息===== */
				List<KlgBuyTransactionVo> listBuy = klgBuyTransactionService
						.findBuyTransactionByIdINIds(strb.toString(), 2);
				List<KlgAssetsRecord> listKlgAssetsRecord = new ArrayList<>();// 记录流水
				KlgAssetsRecord klgAssetsRecordHot = null;
				KlgAssetsRecord klgAssetsRecordCold = null;
				for (KlgBuyTransactionVo klgBuyTransaction : listBuy) {
					list = new ArrayList<Accountadd>();
					/** 查询用户币账户信息 */
					ExDigitalmoneyAccountRedis exaccount = this.selectAccount(klgBuyTransaction.getCustomerId(),
							klgBuyTransaction.getCoinCode());
					// 获取热账户
					BigDecimal hotMoney = exaccount.getHotMoney();
					// 判断热账户是否充足
					if (hotMoney.compareTo(klgBuyTransaction.getLastMoney()) < 0) {
						// return jsonResult.setSuccess(false).setMsg("账户余额不足");
						/*** 发送站内信,余额不足 ***/
					} else {
						// usdt热账户减少 冷账户增加
						list.add(
								getAccountAdd(exaccount.getId(), new BigDecimal("-" + klgBuyTransaction.getLastMoney()),
										1, 1, 2, klgBuyTransaction.getTransactionNum()));
						list.add(
								getAccountAdd(exaccount.getId(), new BigDecimal("+" + klgBuyTransaction.getLastMoney()),
										2, 1, 2, klgBuyTransaction.getTransactionNum()));
						messageProducer.toAccount(JSON.toJSONString(list));
						// 插入币账户流水
						klgAssetsRecordHot = new KlgAssetsRecord(exaccount.getCustomerId(), exaccount.getId(),
								IdGenerate.transactionNum(NumConstant.Ex_Dm_Transaction), 1, exaccount.getCoinCode(),
								klgBuyTransaction.getLastMoney(), 2, TriggerPointEnum.BuyPayTailMoney.getKey(),
								klgBuyTransaction.getTransactionNum(), "匹配成功,托管支付,转冻结");

						klgAssetsRecordCold = new KlgAssetsRecord(exaccount.getCustomerId(), exaccount.getId(),
								IdGenerate.transactionNum(NumConstant.Ex_Dm_Transaction), 2, exaccount.getCoinCode(),
								klgBuyTransaction.getLastMoney(), 1, TriggerPointEnum.BuyPayTailMoney.getKey(),
								klgBuyTransaction.getTransactionNum(), "匹配成功,托管支付,转冻结");
						listKlgAssetsRecord.add(klgAssetsRecordHot);
						listKlgAssetsRecord.add(klgAssetsRecordCold);
						/** 更新订单状态为3待收款 */
						// 更新订单状态
						klgBuyTransaction.setStatus(3);
						klgBuyTransactionService.update(klgBuyTransaction);
						ToBuySellAccountMessage toBuySellAccountMessage1 = new ToBuySellAccountMessage(
								new BigDecimal(0), 1, 5, "buySurplusAccount", klgBuyTransaction.getTransactionNum(),
								"买单托管支付");
						listAccount.add(toBuySellAccountMessage1);
					}
					
				}
				klgAssetsRecordService.saveAll(listKlgAssetsRecord);// 插入币账户流水记录
				messageProducer.toBuySellAccount(JSON.toJSONString(listAccount));

				/** ======================= **/

				redisService.unLock(RedisStaticStringUtil.KLG_BUY_PAY_SURPLUS_ACCOUNT);
				return jsonResult.setSuccess(true).setMsg("操作成功!");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			redisService.unLock(RedisStaticStringUtil.KLG_BUY_PAY_SURPLUS_ACCOUNT);
		}

		return jsonResult.setSuccess(false).setMsg("系统繁忙，请稍后再试!");
	}

	@Override
	public JsonResult chidanSellSubmit(String ids) {
		// TODO Auto-generated method stub
		JsonResult jsonResult = new JsonResult();
		Boolean lock = redisService.lock(RedisStaticStringUtil.KLG_BUY_PAY_SURPLUS_ACCOUNT);
		try {
			if (lock) {
				String[] strarray = ids.split(",");
				List<String> strsToList = Arrays.asList(strarray);
				KlgSellTransaction klgSellTransaction = null;
				String ptbCoinCode = (String) remoteKlgService.getPlatformRule1sConfig("coinCode");
				List<Accountadd> list = new ArrayList<Accountadd>();
				List<KlgAssetsRecord> listKlgAssetsRecord = new ArrayList<>();// 记录流水
				KlgAssetsRecord klgAssetsRecordHot = null;
				KlgAssetsRecord klgAssetsRecordCold = null;
				for (String id : strsToList) {
					/* 根据id查询买单 */
					klgSellTransaction = klgSellTransactionService.get(Long.valueOf(id));
					if (klgSellTransaction != null) {
						// 平台吃单
						// 判断订单状态，平台只能吃状态为排队中的订单
						if (klgSellTransaction.getStatus() == 1) {
							list = new ArrayList<Accountadd>();
							/** 修改订单吃单订单状态，已完成 */
							klgSellTransaction.setStatus(4);
							klgSellTransaction.setEatStatus(2);
							klgSellTransactionService.update(klgSellTransaction);
							/** 查询用户币账户信息,SME冻结减少 USDT增加 */
							ExDigitalmoneyAccountRedis exaccountSme = this
									.selectAccount(klgSellTransaction.getCustomerId(), ptbCoinCode);
							list.add(getAccountAdd(exaccountSme.getId(), new BigDecimal("-"+klgSellTransaction.getSmeMoney()), 2, 1, 11,
									klgSellTransaction.getTransactionNum()));
							// 插入币账户流水
							klgAssetsRecordCold = new KlgAssetsRecord(exaccountSme.getCustomerId(),
									exaccountSme.getId(), IdGenerate.transactionNum(NumConstant.Ex_Dm_Transaction), 2,
									exaccountSme.getCoinCode(), klgSellTransaction.getSmeMoney(), 2,
									TriggerPointEnum.EatSell.getKey(), klgSellTransaction.getTransactionNum(),
									"卖单卖出，SME冻结减少");

							// 计算卖单应获取的USDT总数
							BigDecimal sellSum = (klgSellTransaction.getSmeMoney()
									.add((klgSellTransaction.getCandySmeMoney()
											.multiply(klgSellTransaction.getOneselfRate().multiply(new BigDecimal(0.01))))))
													.multiply(klgSellTransaction.getSmeusdtRate());
							ExDigitalmoneyAccountRedis exaccountSellUsdt = this.selectAccount(
									klgSellTransaction.getCustomerId(), klgSellTransaction.getCoinCode());
							list.add(getAccountAdd(exaccountSellUsdt.getId(), sellSum, 1, 1, 7,
									klgSellTransaction.getTransactionNum()));
							// 插入币账户流水
							klgAssetsRecordHot = new KlgAssetsRecord(exaccountSellUsdt.getCustomerId(),
									exaccountSellUsdt.getId(), IdGenerate.transactionNum(NumConstant.Ex_Dm_Transaction),
									1, exaccountSellUsdt.getCoinCode(), sellSum, 1,
									TriggerPointEnum.EatSell.getKey(), klgSellTransaction.getTransactionNum(),
									"卖单卖出，USDT热账户增加");
							listKlgAssetsRecord.add(klgAssetsRecordHot);
							listKlgAssetsRecord.add(klgAssetsRecordCold);
							// 粮食局账户SME增加
							List<PlatformAccountadd> platformAccounts = new ArrayList<>();// 平台账户
							PlatformAccountadd prizeadd = null;
							prizeadd = PlatformAccountUtil.accountAdd(KlgPlatformCurrency.SMEFoodBureauAccount.getKey(),
									String.valueOf(klgSellTransaction.getSmeMoney()
											.add(klgSellTransaction.getCandySmeMoney())),"平台吃单");
							platformAccounts.add(prizeadd);
							// USDT财政局账户减少
							prizeadd = PlatformAccountUtil
									.accountSub(KlgPlatformCurrency.USDTFinanceBureauAccount.getKey(),
											String.valueOf((klgSellTransaction.getSmeMoney()
													.add(klgSellTransaction.getCandySmeMoney()
															.multiply(klgSellTransaction.getSmeusdtRate())))),"平台吃单");
							platformAccounts.add(prizeadd);
							//糖果账户减少
							prizeadd = PlatformAccountUtil.accountSub(KlgPlatformCurrency.SMECandyAccount.getKey(),
									String.valueOf(klgSellTransaction.getCandySmeMoney()),"平台吃单");
							platformAccounts.add(prizeadd);
							messageProducer.toPlatformCurrency(JSON.toJSONString(platformAccounts));
							// 发送币账户消息
							messageProducer.toAccount(JSON.toJSONString(list));
							/*********** 发送奖励消息 *************/
							messageProducer.toAward(JSON.toJSONString(klgSellTransaction));
						}
					}
				}
				klgAssetsRecordService.saveAll(listKlgAssetsRecord);// 插入币账户流水记录
				redisService.unLock(RedisStaticStringUtil.KLG_BUY_PAY_SURPLUS_ACCOUNT);
			} else {
				return jsonResult.setSuccess(false).setMsg("系统繁忙,请稍后再试！");
			}
		} catch (Exception e) {
			// TODO: handle exception
			redisService.unLock(RedisStaticStringUtil.KLG_BUY_PAY_SURPLUS_ACCOUNT);
			return jsonResult.setSuccess(false).setMsg("系统异常,请稍后再试！");
		}

		return jsonResult.setSuccess(true).setMsg("吃单成功,等待买方支付！");
	}

	@Override
	public JsonResult outSurplusSubmit(String outSme, String outUsdt, QueryFilter filter) {
		// TODO Auto-generated method stub
		JsonResult jsonResult = new JsonResult();
		if (outSme == null||outSme.equals("")) {
			outSme = "0";
		}
		if (outUsdt == null||outUsdt.equals("")) {
			outUsdt = "0";
		}
		try {
			// 获取分布式锁
			if (redisService.lock(RedisStaticStringUtil.KLG_BUY_PAY_SURPLUS_ACCOUNT)) {
				// 发送转出消息
				List<ToBuySellAccountMessage> listAccount = new ArrayList<>();
				ToBuySellAccountMessage toBuySellAccountMessage = new ToBuySellAccountMessage(new BigDecimal(outSme),
						2, 6, "sellSurplusAccount", "", "平台卖出剩余账户转出到SME粮食局账户");
				listAccount.add(toBuySellAccountMessage);
				ToBuySellAccountMessage toBuySellAMessage = new ToBuySellAccountMessage(new BigDecimal(outUsdt), 2, 6,
						"buySurplusAccount", "", "平台买单剩余账户转出到USDT财政局账户");
				listAccount.add(toBuySellAMessage);
				messageProducer.toBuySellAccount(JSON.toJSONString(listAccount));
				// 释放锁
				redisService.unLock(RedisStaticStringUtil.KLG_BUY_PAY_SURPLUS_ACCOUNT);
			} else {
				return jsonResult.setSuccess(false).setMsg("系统繁忙，请稍后再试！");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return jsonResult.setSuccess(false).setMsg("系统异常，请稍后再试！");
		}
		
		return jsonResult.setSuccess(true).setMsg("转出成功");
	}

	@Override
	public JsonResult buyAlonePipeiSubmit(String ids, QueryFilter filter) {
		// TODO Auto-generated method stub
		JsonResult jsonResult = new JsonResult();
		List<Accountadd> list = new ArrayList<Accountadd>();
		Map<String, Object> map = new HashMap<>();
		Boolean lock = redisService.lock(RedisStaticStringUtil.KLG_BUY_PAY_SURPLUS_ACCOUNT);
		try {
			if (lock) {
				List<ToBuySellAccountMessage> listAccount = new ArrayList<>();
				/**
				 * 查询选中买单中状态的订单， 修改订单状态为2排队成功待支付
				 */
				map.clear();
				String[] strarray = ids.split(",");
				List<String> strsToList = Arrays.asList(strarray);
				map.put("ids", strsToList);
				klgBuyTransactionService.updateStatus(map);
				
				/** ======查询出选中的订单，站内信===== */
				List<KlgBuyTransactionVo> listBuyM = klgBuyTransactionService.findBuyTransactionByIdINIds(ids, null);
				for (KlgBuyTransactionVo klgBuyTM : listBuyM) {
					Map<String, String> paramM = new HashMap<>();
			        paramM.put("${firstMoney}", klgBuyTM.getFirstMoney().toString());
			        paramM.put("${lastMoney}", klgBuyTM.getLastMoney().toString());
			        RemoteMessage message=new RemoteMessage();
			        message.setParam(paramM);
			        message.setMsgKey(MessageType.MESSAGE_KLG_BUY_QUEUE_SUCCESS.getKey());//消息类型 模板KEY//
			        message.setMsgType("1,2");// 1.站内信，2.短信,
			        message.setUserId(klgBuyTM.getCustomerId().toString());
			        messageProducer.toMessageWarn(JSON.toJSONString(message));//消息提醒
				}

				/** ======查询出托管状态的订单，发送付款操作消息===== */
				List<KlgBuyTransactionVo> listBuy = klgBuyTransactionService.findBuyTransactionByIdINIds(ids, 2);
				List<KlgAssetsRecord> listKlgAssetsRecord = new ArrayList<>();// 记录流水
				KlgAssetsRecord klgAssetsRecordHot = null;
				KlgAssetsRecord klgAssetsRecordCold = null;
				for (KlgBuyTransactionVo klgBuyTransaction : listBuy) {
					try {
						list = new ArrayList<Accountadd>();
						/** 查询用户币账户信息 */
						ExDigitalmoneyAccountRedis exaccount = this.selectAccount(klgBuyTransaction.getCustomerId(),
								klgBuyTransaction.getCoinCode());
						// 获取热账户
						BigDecimal hotMoney = exaccount.getHotMoney();
						// 判断热账户是否充足
						if (hotMoney.compareTo(klgBuyTransaction.getLastMoney()) < 0) {
							// return jsonResult.setSuccess(false).setMsg("账户余额不足");
							/*** 发送站内信,余额不足 ***/
						} else {
							// usdt热账户减少 冷账户增加
							list.add(
									getAccountAdd(exaccount.getId(), new BigDecimal("-" + klgBuyTransaction.getLastMoney()),
											1, 1, 2, klgBuyTransaction.getTransactionNum()));
							list.add(
									getAccountAdd(exaccount.getId(), new BigDecimal("+" + klgBuyTransaction.getLastMoney()),
											2, 1, 2, klgBuyTransaction.getTransactionNum()));
							messageProducer.toAccount(JSON.toJSONString(list));
							// 插入币账户流水
							klgAssetsRecordHot = new KlgAssetsRecord(exaccount.getCustomerId(), exaccount.getId(),
									IdGenerate.transactionNum(NumConstant.Ex_Dm_Transaction), 1, exaccount.getCoinCode(),
									klgBuyTransaction.getLastMoney(), 2, TriggerPointEnum.BuyPayTailMoney.getKey(),
									klgBuyTransaction.getTransactionNum(), "匹配成功,托管支付,转冻结");

							klgAssetsRecordCold = new KlgAssetsRecord(exaccount.getCustomerId(), exaccount.getId(),
									IdGenerate.transactionNum(NumConstant.Ex_Dm_Transaction), 2, exaccount.getCoinCode(),
									klgBuyTransaction.getLastMoney(), 1, TriggerPointEnum.BuyPayTailMoney.getKey(),
									klgBuyTransaction.getTransactionNum(), "匹配成功,托管支付,转冻结");

							listKlgAssetsRecord.add(klgAssetsRecordHot);
							listKlgAssetsRecord.add(klgAssetsRecordCold);
							/** 更新订单状态为3待收款 */
							// 更新订单状态
							klgBuyTransaction.setStatus(3);
							klgBuyTransactionService.update(klgBuyTransaction);

							/*
							 * ToBuySellAccountMessage toBuySellAccountMessage = new
							 * ToBuySellAccountMessage( "buySurplusAccount",
							 * klgBuyTransaction.getTransactionNum(), "买单托管支付");
							 */
							ToBuySellAccountMessage toBuySellAccountMessage = new ToBuySellAccountMessage(new BigDecimal(0),
									1, 5, "buySurplusAccount", klgBuyTransaction.getTransactionNum(), "买单托管支付");
							listAccount.add(toBuySellAccountMessage);
						}
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
						KlgExceptionLogService klgExceptionLogService = SpringUtil.getBean("klgExceptionLogService");
						// 插入异常日志
						KlgExceptionLog klgExceptionLog = new KlgExceptionLog();
						klgExceptionLog.setCustomerId(klgBuyTransaction.getCustomerId().toString());
						klgExceptionLog.setFunctionName("KlgBuySellMatchingServiceImpl.buyAlonePipeiSubmit buyId:" + klgBuyTransaction.getId());
						String str = e.toString();
						if (str.length() <= 230) {
							klgExceptionLog.setExceptionText(str);
						} else {
							klgExceptionLog.setExceptionText(str.substring(0, 230));
						}
						klgExceptionLogService.save(klgExceptionLog);
					}
				}
				klgAssetsRecordService.saveAll(listKlgAssetsRecord);// 插入币账户流水记录
				
				messageProducer.toBuySellAccount(JSON.toJSONString(listAccount));

				/** ======================= **/

				redisService.unLock(RedisStaticStringUtil.KLG_BUY_PAY_SURPLUS_ACCOUNT);
				return jsonResult.setSuccess(true).setMsg("操作成功!");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			redisService.unLock(RedisStaticStringUtil.KLG_BUY_PAY_SURPLUS_ACCOUNT);
		}

		return jsonResult.setSuccess(false).setMsg("系统繁忙，请稍后再试!");
	}

	@Override
	public JsonResult sellAlonePipeiSubmit(String ids, QueryFilter filter) {
		// TODO Auto-generated method stub
		JsonResult jsonResult = new JsonResult();
		List<Accountadd> list = new ArrayList<Accountadd>();
		Map<String, Object> map = new HashMap<>();
		Boolean lock = redisService.lock(RedisStaticStringUtil.KLG_BUY_PAY_SURPLUS_ACCOUNT);
		try {
			if (lock) {
				/**
				 * 查询匹配的买单， 修改订单状态为2排队成功
				 */
				BigDecimal sellSum = klgSellTransactionService.getSellTransactionByIdINIds(ids);
				if(sellSum==null){
					redisService.unLock(RedisStaticStringUtil.KLG_BUY_PAY_SURPLUS_ACCOUNT);
					return jsonResult.setSuccess(false).setMsg("选中订单不正确");
				}
				List<KlgBuyTransactionVo> buyList = klgBuyTransactionService.findListBySql(filter);
				List<KlgBuyTransactionVo> listbuy = new ArrayList<>();// 记录对应的卖单
				List<Long> buyListArray = new ArrayList<>();// 记录匹配的卖单id
				List<ToBuySellAccountMessage> listAccount = new ArrayList<>();
				BigDecimal bdBuySum = new BigDecimal(0);
				StringBuffer strb = new StringBuffer("");
				for (KlgBuyTransactionVo klgBuyTransactionVo : buyList) {
					bdBuySum = bdBuySum.add(klgBuyTransactionVo.getSmeMoney());
					if (sellSum.compareTo(bdBuySum) < 0) {
						break;
					}
					listbuy.add(klgBuyTransactionVo);
					buyListArray.add(klgBuyTransactionVo.getId());
					strb.append("," + klgBuyTransactionVo.getId());
				}
				if(buyListArray.size()>0){
					// 更新状态
					map.clear();
					map.put("ids", buyListArray);
					klgBuyTransactionService.updateStatus(map);
				}
				/**
				 * 查询选中卖单中状态的订单， 修改订单状态为2排队成功待支付
				 */
				map.clear();
				String[] strarray = ids.split(",");
				List<String> strsToList = Arrays.asList(strarray);
				map.put("ids", strsToList);
				klgSellTransactionService.updateStatus(map);
				/** 发送消息 */
				// 查询选中的卖单
				List<KlgSellTransactionVo> listVo = klgSellTransactionService
						.findSellTransactionByIdINIdsStr(strsToList);
				ToBuySellAccountMessage toBuySellAccountMessage = null;
				for (KlgSellTransactionVo klgSellTransactionVo : listVo) {
					toBuySellAccountMessage = new ToBuySellAccountMessage(new BigDecimal(0), 1, 1, "sellSurplusAccount",
							klgSellTransactionVo.getTransactionNum(), "卖单匹配买单");
					listAccount.add(toBuySellAccountMessage);
				}
				
				List<KlgAssetsRecord> listKlgAssetsRecord = new ArrayList<>();// 记录流水
				klgAssetsRecordService.saveAll(listKlgAssetsRecord);// 插入币账户流水记录
				messageProducer.toAccount(JSON.toJSONString(list));
				messageProducer.toBuySellAccount(JSON.toJSONString(listAccount));

				/** ======================= **/

				redisService.unLock(RedisStaticStringUtil.KLG_BUY_PAY_SURPLUS_ACCOUNT);
				return jsonResult.setSuccess(true).setMsg("操作成功!");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			redisService.unLock(RedisStaticStringUtil.KLG_BUY_PAY_SURPLUS_ACCOUNT);
		}

		return jsonResult.setSuccess(false).setMsg("系统繁忙，请稍后再试!");
	}

}
