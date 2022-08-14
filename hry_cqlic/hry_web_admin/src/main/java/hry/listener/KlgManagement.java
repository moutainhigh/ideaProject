package hry.listener;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;

import hry.admin.klg.assetsrecord.model.KlgAssetsRecord;
import hry.admin.klg.assetsrecord.service.KlgAssetsRecordService;
import hry.admin.klg.forecast.model.KlgForecast;
import hry.admin.klg.forecast.service.KlgForecastService;
import hry.admin.klg.level.model.KlgLevelConfig;
import hry.admin.klg.level.service.KlgLevelConfigService;
import hry.admin.klg.log.model.KlgExceptionLog;
import hry.admin.klg.log.service.KlgExceptionLogService;
import hry.admin.klg.log.service.KlgTaskLogService;
import hry.admin.klg.platform.model.PlatformAccountUtil;
import hry.admin.klg.platform.service.KlgPlatformAccountService;
import hry.admin.klg.prizedraw.model.KlPrizedrawCustomer;
import hry.admin.klg.prizedraw.model.KlPrizedrawIssue;
import hry.admin.klg.prizedraw.model.KlPrizedrawSelection;
import hry.admin.klg.prizedraw.service.KlPrizedrawCustomerService;
import hry.admin.klg.prizedraw.service.KlPrizedrawIssueService;
import hry.admin.klg.prizedraw.service.KlPrizedrawSelectionService;
import hry.admin.klg.transaction.model.KlgBuyTransaction;
import hry.admin.klg.transaction.model.vo.KlgBuyTransactionVo;
import hry.admin.klg.transaction.model.vo.KlgSellTransactionVo;
import hry.admin.klg.transaction.service.KlgBuyTransactionService;
import hry.admin.klg.transaction.service.KlgSellTransactionService;
import hry.admin.klg.utils.RedisStaticStringUtil;
import hry.admin.mq.producer.service.MessageProducer;
import hry.front.redis.model.UserRedis;
import hry.klg.enums.TriggerPointEnum;
import hry.klg.model.KlgPlatformCurrency;
import hry.klg.model.PlatformAccountadd;
import hry.klg.model.RulesConfig;
import hry.klg.remote.RemoteKlgService;
import hry.message.model.MessageType;
import hry.message.model.RemoteMessage;
import hry.redis.common.dao.RedisUtil;
import hry.redis.common.utils.RedisService;
import hry.trade.redis.model.Accountadd;
import hry.trade.redis.model.ExDigitalmoneyAccountRedis;
import hry.util.QueryFilter;
import hry.util.SpringUtil;
import hry.util.UserRedisUtils;
import hry.util.idgenerate.IdGenerate;
import hry.util.idgenerate.NumConstant;

/***
 * KLG-定时任务
 * 
 * @author lenovo
 *
 */
public class KlgManagement {

	private static Logger logger = Logger.getLogger(KlgManagement.class);

	/**
	 * 给排单超出得订单添加糖果
	 */
	public void klgSellAddCandy() {
		Date startDate = new Date();
		/**
		 * 1.查询排队中的排队时长大于排单是用户等级的基础奖励时长的卖单 2.遍历结果给卖单添加糖果，插入平台糖果账户记录
		 */
		KlgExceptionLogService klgExceptionLogService = SpringUtil.getBean("klgExceptionLogService");
		KlgTaskLogService klgTaskLogService = SpringUtil.getBean("klgTaskLogService");
		MessageProducer messageProducer = SpringUtil.getBean("messageProducer");
		KlgSellTransactionService klgSellTransactionService = SpringUtil.getBean("klgSellTransactionService");
		KlgLevelConfigService klgLevelConfigService = SpringUtil.getBean("klgLevelConfigService");		
		List<KlgSellTransactionVo> list = klgSellTransactionService.findBeyondList();
		if (list != null && list.size() >= 0) {
			List<PlatformAccountadd> platformAccounts = null;// 平台账户
			PlatformAccountadd prizeadd = null;
			for (KlgSellTransactionVo sellT : list) {
				try {
					//查询用户等级配置信息
					KlgLevelConfig klgLevelConfig = klgLevelConfigService.get(Long.valueOf(sellT.getCustomerGrade()==null?0:sellT.getCustomerGrade()));
					//最高奖励时长
					int maxRewardTime = -1;
					if(klgLevelConfig!=null){
						//最高奖励时长
						maxRewardTime = klgLevelConfig.getMaxRewardTime();
					}
					
					//计算排单时间与当前时间是否大于最高奖励时长，如果大于则不发奖励
					Long dayL = getInterval(sellT.getCreated(), new Date());
					if(maxRewardTime == -1||Integer.valueOf(String.valueOf(dayL))<=maxRewardTime){
						// 计算增加糖果数量
						BigDecimal addCandy = sellT.getSmeMoney().multiply(sellT.getAddCandyNum())
								.multiply(new BigDecimal(0.01)).setScale(4, BigDecimal.ROUND_HALF_DOWN);
						klgSellTransactionService.updateCandySmeMoneyById(sellT.getId(), addCandy);
					}
					
					
					// 糖果账户,改：卖单成交时糖果账户扣除糖果
					/*
					 * prizeadd =
					 * PlatformAccountUtil.accountSub(KlgPlatformCurrency.
					 * SMECandyAccount.getKey(),
					 * String.valueOf(addCandy),"添加糖果"); platformAccounts = new
					 * ArrayList<>(); platformAccounts.add(prizeadd);
					 * messageProducer.toPlatformCurrency(JSON.toJSONString(
					 * platformAccounts));
					 */
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					// 插入异常日志
					KlgExceptionLog klgExceptionLog = new KlgExceptionLog();
					klgExceptionLog.setCustomerId(sellT.getCustomerId().toString());
					klgExceptionLog.setFunctionName("klgSellAddCandy SellId:" + sellT.getId());
					String str = e.toString();
					if (str.length() <= 230) {
						klgExceptionLog.setExceptionText(str);
					} else {
						klgExceptionLog.setExceptionText(str.substring(0, 230));
					}
					klgExceptionLogService.save(klgExceptionLog);
				}
			}
		}

		Date endDate = new Date();
		String functionName = "KlgSellAddCandy";
		klgTaskLogService.saveLog(functionName, 0, startDate, endDate);
	}

	/**
	 * 修改超时未支付的买单状态为已作废4
	 */
	public void updateOverdueOrder() {
		Date startDate = new Date();

		KlgExceptionLogService klgExceptionLogService = SpringUtil.getBean("klgExceptionLogService");
		KlgTaskLogService klgTaskLogService = SpringUtil.getBean("klgTaskLogService");
		MessageProducer messageProducer = SpringUtil.getBean("messageProducer");
		KlgBuyTransactionService klgBuyTransactionService = SpringUtil.getBean("klgBuyTransactionService");
		RemoteKlgService remoteKlgService = SpringUtil.getBean("remoteKlgService");
		RedisService redisService = SpringUtil.getBean("redisService");
		KlgAssetsRecordService klgAssetsRecordService = SpringUtil.getBean("klgAssetsRecordService");

		// 查询超时间配置
		String waitingTime = (String) remoteKlgService.getPlatformRule1sConfig("waitingTime");
		// 查询超时支付并且订单状态再待支付的订单列表
		List<KlgBuyTransactionVo> list = klgBuyTransactionService.findDiscardList(Integer.valueOf(waitingTime));
		if (list != null && list.size() > 0) {
			KlgBuyTransaction klgBuyTransaction = null;
			List<Accountadd> listAccount = null;
			KlgAssetsRecord klgAssetsRecordCold = null;
			List<PlatformAccountadd> platformAccounts = null;// 平台账户
			PlatformAccountadd prizeadd = null;
			for (KlgBuyTransactionVo buyT : list) {
				try {
					// 更新订单状态
					Boolean lock = redisService
							.lock(RedisStaticStringUtil.KLG_BUY_PAY_TRANSACTIONNUM + buyT.getTransactionNum());
					if (lock) {
						listAccount = new ArrayList<Accountadd>();
						// 重新查询订单信息，保证用户没有再定时任务期间支付尾款
						klgBuyTransaction = klgBuyTransactionService.get(buyT.getId());
						if (klgBuyTransaction.getStatus() != 2) {
							continue;
						}
						klgBuyTransaction.setStatus(5);
						klgBuyTransactionService.update(klgBuyTransaction);
						// 查询用户币账户信息，保证金冻结减少
						ExDigitalmoneyAccountRedis exaccount = this.selectAccount(klgBuyTransaction.getCustomerId(),
								klgBuyTransaction.getCoinCode());
						// usdt冷账户减少
						listAccount.add(getAccountAdd(exaccount.getId(),
								new BigDecimal("-" + klgBuyTransaction.getFirstMoney()), 2, 1, 2,
								klgBuyTransaction.getTransactionNum()));
						klgAssetsRecordCold = new KlgAssetsRecord(exaccount.getCustomerId(), exaccount.getId(),
								IdGenerate.transactionNum(NumConstant.Ex_Dm_Transaction), 2, exaccount.getCoinCode(),
								klgBuyTransaction.getFirstMoney(), 2, TriggerPointEnum.OvertimeOrder.getKey(),
								klgBuyTransaction.getTransactionNum(), "订单支付超时");
						klgAssetsRecordService.save(klgAssetsRecordCold);
						// 平台保证金没收账户增加
						prizeadd = PlatformAccountUtil.accountAdd(KlgPlatformCurrency.USDTConfiscationAccount.getKey(),
								String.valueOf(klgBuyTransaction.getFirstMoney()),
								"保证金没收,订单:" + klgBuyTransaction.getTransactionNum());
						platformAccounts = new ArrayList<>();
						platformAccounts.add(prizeadd);

						messageProducer.toPlatformCurrency(JSON.toJSONString(platformAccounts));
						// messageProducer.toAccount(JSON.toJSONString(list));

						Map<String, String> paramM = new HashMap<>();
						paramM.put("${transactionNum}", buyT.getTransactionNum());
						RemoteMessage message = new RemoteMessage();
						message.setParam(paramM);
						message.setMsgKey(MessageType.MESSAGE_KLG_BUY_CANCEL.getKey());// 消息类型
																						// 模板KEY//
						message.setMsgType("1,2");// 1.站内信，2.短信,
						message.setUserId(buyT.getCustomerId().toString());
						messageProducer.toMessageWarn(JSON.toJSONString(message));// 消息提醒
						messageProducer.toAccount(JSON.toJSONString(listAccount));
						redisService
								.unLock(RedisStaticStringUtil.KLG_BUY_PAY_TRANSACTIONNUM + buyT.getTransactionNum());

					}
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					redisService.unLock(RedisStaticStringUtil.KLG_BUY_PAY_TRANSACTIONNUM + buyT.getTransactionNum());
					// 插入异常日志
					KlgExceptionLog klgExceptionLog = new KlgExceptionLog();
					klgExceptionLog.setCustomerId(buyT.getCustomerId().toString());
					klgExceptionLog.setFunctionName("updateOverdueOrder buyId:" + buyT.getId());
					String str = e.toString();
					if (str.length() <= 230) {
						klgExceptionLog.setExceptionText(str);
					} else {
						klgExceptionLog.setExceptionText(str.substring(0, 230));
					}
					klgExceptionLogService.save(klgExceptionLog);
				}
			}
		}

		Date endDate = new Date();
		String functionName = "updateOverdueOrder";
		klgTaskLogService.saveLog(functionName, 0, startDate, endDate);

	}

	/**
	 * 排队超时的买单增加利息
	 */
	public void klgAddInterestBuyOrder() {
		Date startDate = new Date();

		KlgExceptionLogService klgExceptionLogService = SpringUtil.getBean("klgExceptionLogService");
		KlgTaskLogService klgTaskLogService = SpringUtil.getBean("klgTaskLogService");
		KlgBuyTransactionService klgBuyTransactionService = SpringUtil.getBean("klgBuyTransactionService");
		RemoteKlgService remoteKlgService = SpringUtil.getBean("remoteKlgService");
		MessageProducer messageProducer = SpringUtil.getBean("messageProducer");
		KlgAssetsRecordService klgAssetsRecordService = SpringUtil.getBean("klgAssetsRecordService");
		// 查询平台币
		String ptbCoinCode = (String) remoteKlgService.getPlatformRule1sConfig("coinCode");

		// 查询保证金起息天数（天）
		String marginDays = (String) remoteKlgService.getPlatformRule1sConfig("marginDays");
		// 查询保证金利率
		String marginInterestRate = (String) remoteKlgService.getPlatformRule1sConfig("marginInterestRate");
		// 查询订单状态为排队状态且时间大于起息时间的订单列表
		List<KlgBuyTransactionVo> list = klgBuyTransactionService.findDiscardListByDay(Integer.valueOf(marginDays));
		if (list != null && list.size() > 0) {
			List<Accountadd> listAccount = null;
			List<PlatformAccountadd> platformAccounts = null;// 平台账户
			PlatformAccountadd prizeadd = null;
			KlgAssetsRecord klgAssetsRecordCold = null;
			for (KlgBuyTransactionVo buyT : list) {
				try {
					listAccount = new ArrayList<>();
					// 计算本次应发的利息
					BigDecimal interest = buyT.getFirstMoney().multiply(new BigDecimal(marginInterestRate))
							.multiply(new BigDecimal(0.01)).setScale(4, BigDecimal.ROUND_HALF_DOWN);
					// 更新订单利息
					klgBuyTransactionService.updateInterestMoney(interest, buyT.getId());
					// 查询用户平台币币账户信息，冻结增加
					ExDigitalmoneyAccountRedis exaccount = this.selectAccount(buyT.getCustomerId(), ptbCoinCode);
					listAccount.add(getAccountAdd(exaccount.getId(), interest, 2, 1, 2, buyT.getTransactionNum()));

					klgAssetsRecordCold = new KlgAssetsRecord(exaccount.getCustomerId(), exaccount.getId(),
							IdGenerate.transactionNum(NumConstant.Ex_Dm_Transaction), 2, exaccount.getCoinCode(),
							interest, 1, TriggerPointEnum.BuyovertimeInterest.getKey(), buyT.getTransactionNum(),
							"买单超时利息");
					klgAssetsRecordService.save(klgAssetsRecordCold);

					// 糖果账户
					prizeadd = PlatformAccountUtil.accountSub(KlgPlatformCurrency.SMECandyAccount.getKey(),
							String.valueOf(interest), "添加利息");
					platformAccounts = new ArrayList<>();
					platformAccounts.add(prizeadd);
					messageProducer.toPlatformCurrency(JSON.toJSONString(platformAccounts));
					messageProducer.toAccount(JSON.toJSONString(listAccount));
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					// 插入异常日志
					KlgExceptionLog klgExceptionLog = new KlgExceptionLog();
					klgExceptionLog.setCustomerId(buyT.getCustomerId().toString());
					klgExceptionLog.setFunctionName("klgAddInterestBuyOrder buyId:" + buyT.getId());
					String str = e.toString();
					if (str.length() <= 230) {
						klgExceptionLog.setExceptionText(str);
					} else {
						klgExceptionLog.setExceptionText(str.substring(0, 230));
					}
					klgExceptionLogService.save(klgExceptionLog);
				}
			}
		}

		Date endDate = new Date();
		String functionName = "klgAddInterestBuyOrder";
		klgTaskLogService.saveLog(functionName, 0, startDate, endDate);
	}

	/**
	 * 预测排单数量
	 */
	public void forecast() {
		System.out.println("forecast");
		Date startDate = new Date();
		KlgExceptionLogService klgExceptionLogService = SpringUtil.getBean("klgExceptionLogService");
		KlgTaskLogService klgTaskLogService = SpringUtil.getBean("klgTaskLogService");
		RemoteKlgService remoteKlgService = SpringUtil.getBean("remoteKlgService");
		KlgForecastService klgForecastService = SpringUtil.getBean("klgForecastService");
		KlgBuyTransactionService klgBuyTransactionService = SpringUtil.getBean("klgBuyTransactionService");
		Map<String, Object> map = new HashMap<>();
		// 查询排单间隔时长
		Integer lineUpTime = Integer.valueOf((String) remoteKlgService.getPlatformRule1sConfig("lineUpTime"));

		/** 更新今天剩余 */
		// 查询今天预测数据
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 0);
		Date d = cal.getTime();
		SimpleDateFormat sp = new SimpleDateFormat("yyyy-MM-dd");
		String today = sp.format(d);// 获取今天日期
		QueryFilter klgForecastFileter = new QueryFilter(KlgForecast.class);
		klgForecastFileter.addFilter("forecastTime>=", today);
		KlgForecast klgForecast = klgForecastService.get(klgForecastFileter);
		if (klgForecast != null) {
			// 查询目前为止今天的预测剩余购买量
			map.clear();
			map.put("dayp", klgForecast.getForecastInterval() - 1);
			BigDecimal smeMoneyToDayS = klgBuyTransactionService.getForecastSum(map);
			klgForecast.setTodaySurplus(smeMoneyToDayS);
			klgForecastService.update(klgForecast);
		}

		/** 预测明天数据 */
		Calendar calt = Calendar.getInstance();
		calt.add(Calendar.DATE, 1);
		Date dt = calt.getTime();
		String tomorrow = sp.format(dt);// 获取明天日期

		// 预测明天数量
		map.clear();
		map.put("dayp", lineUpTime);
		BigDecimal smeMoneyToDayT = klgBuyTransactionService.getForecastSum(map);

		KlgForecast klgForecastTom = new KlgForecast();
		klgForecastTom.setForecastInterval(lineUpTime);
		klgForecastTom.setForecastTime(dt);
		klgForecastTom.setTodayForecast(smeMoneyToDayT);
		if (klgForecast != null) {
			klgForecastTom.setYesterdaySurplus(klgForecast.getTodaySurplus());
		} else {
			klgForecastTom.setYesterdaySurplus(new BigDecimal(0));
		}

		klgForecastService.save(klgForecastTom);

		Date endDate = new Date();
		String functionName = "forecast";
		klgTaskLogService.saveLog(functionName, 0, startDate, endDate);
	}

	/**
	 * 更新抽奖期数
	 * 
	 * @throws ParseException
	 */
	public void updateDrawing() throws ParseException {
		System.out.println("updateDrawing");
		Date startDate = new Date();
		KlgExceptionLogService klgExceptionLogService = SpringUtil.getBean("klgExceptionLogService");
		KlgTaskLogService klgTaskLogService = SpringUtil.getBean("klgTaskLogService");
		KlPrizedrawIssueService klPrizedrawIssueService = SpringUtil.getBean("klPrizedrawIssueService");

		try {
			// 查询本期开奖信息
			QueryFilter filterThisIssue = new QueryFilter(KlPrizedrawIssue.class);
			filterThisIssue.addFilter("status=", 2);
			filterThisIssue.setOrderby("issueNumber desc");
			KlPrizedrawIssue klgPrizedrawLssueThis = klPrizedrawIssueService.get(filterThisIssue);
			if (klgPrizedrawLssueThis != null) {
				// 更新本期状态为3已生效
				klgPrizedrawLssueThis.setStatus(3);
				klPrizedrawIssueService.update(klgPrizedrawLssueThis);
			}

			// 查询开始时间为本周的信息
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			String dateString = df.format(new Date());
			Date currentTime_1 = df.parse(dateString);
			QueryFilter filterIssue = new QueryFilter(KlPrizedrawIssue.class);
			filterIssue.addFilter("status=", 1);
			filterIssue.addFilter("startDate=", currentTime_1);
			filterIssue.setOrderby("startDate asc");
			KlPrizedrawIssue klgPrizedrawIssue = klPrizedrawIssueService.get(filterIssue);
			if (klgPrizedrawIssue == null) {
				klgPrizedrawIssue = new KlPrizedrawIssue();
				if (klgPrizedrawLssueThis != null) {
					klgPrizedrawIssue.setIssueNumber(klgPrizedrawLssueThis.getIssueNumber() + 1);
				} else {
					klgPrizedrawIssue.setIssueNumber(1);
				}

				klgPrizedrawIssue.setStartDate(currentTime_1);

				Calendar c = Calendar.getInstance();
				c.setTime(currentTime_1);
				c.add(Calendar.DAY_OF_MONTH, 7);// +1天
				klgPrizedrawIssue.setEndDate(c.getTime());
				klgPrizedrawIssue.setStatus(2);// 生效中
				klPrizedrawIssueService.save(klgPrizedrawIssue);
			} else {
				klgPrizedrawIssue.setStatus(2);// 生效中
				klPrizedrawIssueService.update(klgPrizedrawIssue);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			// 插入异常日志
			KlgExceptionLog klgExceptionLog = new KlgExceptionLog();
			klgExceptionLog.setFunctionName("updateDrawing");
			String str = e.toString();
			if (str.length() <= 230) {
				klgExceptionLog.setExceptionText(str);
			} else {
				klgExceptionLog.setExceptionText(str.substring(0, 230));
			}
			klgExceptionLogService.save(klgExceptionLog);
		}

		Date endDate = new Date();
		String functionName = "updateDrawing";
		klgTaskLogService.saveLog(functionName, 0, startDate, endDate);

	}

	/**
	 * 计算开奖号码
	 * 
	 * 中奖规则： 从星期一开始，到星期天结束，公布每天预约成功的第一个手机号码，下个星期一公布上个星期的中奖号码：
	 * 1.将上星期的7个手机号码相加得出一个数A； 2.本星期一预约成功的第一个手机号为数B； 3. 电脑再随机抽取一个100至1000以内的质数C。
	 * 4.(A-B)/C得出一个数D，忽略D的前六个数，从第七个数开始到第十四个数字共8个数，就是中奖号码。
	 * 5.8个数都对，一等奖；7个数对，二等奖；6个数对，三等奖。 6.大奖基金每次拿出总额的30%进行奖励。一等奖50%，二等奖30%，三等奖20%。
	 * 7.星级越高，每轮可填中奖号码机会越多。
	 */
	public void CalculationWinningNumbers() {
		System.out.println("CalculationWinningNumbers");
		Date startDate = new Date();
		KlgExceptionLogService klgExceptionLogService = SpringUtil.getBean("klgExceptionLogService");
		KlgTaskLogService klgTaskLogService = SpringUtil.getBean("klgTaskLogService");
		KlPrizedrawIssueService klPrizedrawIssueService = SpringUtil.getBean("klPrizedrawIssueService");
		KlPrizedrawCustomerService klPrizedrawCustomerService = SpringUtil.getBean("klPrizedrawCustomerService");

		try {
			// 查询上一期开奖信息
			QueryFilter filterIssue = new QueryFilter(KlPrizedrawIssue.class);
			filterIssue.addFilter("status=", 3);
			filterIssue.setOrderby("issueNumber desc");
			KlPrizedrawIssue klgPrizedrawLssue = klPrizedrawIssueService.get(filterIssue);
			Long A = 1L;
			if (klgPrizedrawLssue != null) {
				if (klgPrizedrawLssue.getPrimeNumber() == null || klgPrizedrawLssue.getPrimeNumber().equals("")) {
					// 生成质数
					int primeNumber = getPrimeNumber();
					klgPrizedrawLssue.setPrimeNumber(primeNumber);
				}
				// 计算A
				A = Long.valueOf(
						klgPrizedrawLssue.getMondayNumber() == null ? "0" : klgPrizedrawLssue.getMondayNumber())
						+ Long.valueOf(klgPrizedrawLssue.getTuesdayNumber() == null ? "0"
								: klgPrizedrawLssue.getTuesdayNumber())
						+ Long.valueOf(klgPrizedrawLssue.getWednesdayNumber() == null ? "0"
								: klgPrizedrawLssue.getWednesdayNumber())
						+ Long.valueOf(klgPrizedrawLssue.getThursdayNumber() == null ? "0"
								: klgPrizedrawLssue.getThursdayNumber())
						+ Long.valueOf(
								klgPrizedrawLssue.getFridayNumber() == null ? "0" : klgPrizedrawLssue.getFridayNumber())
						+ Long.valueOf(klgPrizedrawLssue.getSaturdayNumber() == null ? "0"
								: klgPrizedrawLssue.getSaturdayNumber())
						+ Long.valueOf(klgPrizedrawLssue.getSundayNumber() == null ? "0"
								: klgPrizedrawLssue.getSundayNumber());

			}

			// 查询本期开奖信息
			QueryFilter filterThisIssue = new QueryFilter(KlPrizedrawIssue.class);
			filterThisIssue.addFilter("status=", 2);
			KlPrizedrawIssue klgPrizedrawLssueThis = klPrizedrawIssueService.get(filterThisIssue);

			// 计算B
			String numberB = "12345678900";
			if (klgPrizedrawLssueThis != null) {
				numberB = klgPrizedrawLssueThis.getMondayNumber();
				if (numberB == null || numberB.equals("")) {
					numberB = klgPrizedrawLssueThis.getTuesdayNumber();
					if (numberB == null || numberB.equals("")) {
						numberB = klgPrizedrawLssueThis.getWednesdayNumber();
						if (numberB == null || numberB.equals("")) {
							numberB = klgPrizedrawLssueThis.getThursdayNumber();
							if (numberB == null || numberB.equals("")) {
								numberB = klgPrizedrawLssueThis.getFridayNumber();
								if (numberB == null || numberB.equals("")) {
									numberB = "12345678900";
								}
							}
						}
					}
				}
			}

			Long B = Long.valueOf(numberB);
			if (klgPrizedrawLssue != null) {
				// 计算D
				Long C = A - B;
				BigDecimal bigD = new BigDecimal(C).divide(new BigDecimal(klgPrizedrawLssue.getPrimeNumber()), 20,
						BigDecimal.ROUND_FLOOR);
				String strD = String.valueOf(bigD);
				strD = strD.replace(".", "").trim();
				if(strD.length()<20){
					strD = strD+"00000000000000000000";
				}
				strD = strD.substring(6, 14);

				// 更新上一期开奖号码
				klgPrizedrawLssue.setLotteryNumber(strD);
				klPrizedrawIssueService.update(klgPrizedrawLssue);

			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			// 插入异常日志
			KlgExceptionLog klgExceptionLog = new KlgExceptionLog();
			klgExceptionLog.setFunctionName("CalculationWinningNumbers");
			String str = e.toString();
			if (str.length() <= 230) {
				klgExceptionLog.setExceptionText(str);
			} else {
				klgExceptionLog.setExceptionText(str.substring(0, 230));
			}
			klgExceptionLogService.save(klgExceptionLog);
		}

		Date endDate = new Date();
		String functionName = "CalculationWinningNumbers";
		klgTaskLogService.saveLog(functionName, 0, startDate, endDate);
	}

	/**
	 * 对比中奖号码，插入中奖信息 8个数都对，一等奖；7个数对，二等奖；6个数对，三等奖。
	 */
	public void winningThePrize() {

		System.out.println("winningThePrize");
		Date startDate = new Date();
		KlgExceptionLogService klgExceptionLogService = SpringUtil.getBean("klgExceptionLogService");
		KlgTaskLogService klgTaskLogService = SpringUtil.getBean("klgTaskLogService");
		KlPrizedrawIssueService klPrizedrawIssueService = SpringUtil.getBean("klPrizedrawIssueService");
		KlPrizedrawCustomerService klPrizedrawCustomerService = SpringUtil.getBean("klPrizedrawCustomerService");
		RemoteKlgService remoteKlgService = SpringUtil.getBean("remoteKlgService");
		KlgPlatformAccountService klgPlatformAccountService = SpringUtil.getBean("klgPlatformAccountService");
		RedisService redisService = SpringUtil.getBean("redisService");

		try {
			// 查询上一期开奖信息a
			QueryFilter filterIssue = new QueryFilter(KlPrizedrawIssue.class);
			filterIssue.addFilter("status=", 3);
			filterIssue.setOrderby("issueNumber desc");
			KlPrizedrawIssue klgPrizedrawLssue = klPrizedrawIssueService.get(filterIssue);
			System.out.println("11111"+klgPrizedrawLssue.getFridayNumber());
			if (klgPrizedrawLssue != null) {
				// 查询USDT周奖账户余额
				String redisKey = RulesConfig.PLATFORM_NUMBER + KlgPlatformCurrency.USDTWeeklyAccount.getKey();
				String weekAccountMoney = redisService.get(redisKey);

				// 查询周奖总发放比例
				String bonusProportion = (String) remoteKlgService.getPlatformRule1sConfig("bonusProportion");

				/** 本次发放总奖金 */
				BigDecimal thisWeekAccountMoney = new BigDecimal(weekAccountMoney).multiply(new BigDecimal(bonusProportion))
						.multiply(new BigDecimal(0.01)).setScale(2, BigDecimal.ROUND_FLOOR);

				// 开奖号码
				String prizedraw = klgPrizedrawLssue.getLotteryNumber();
				List<Integer> prizedrawList = StringToInt(prizedraw);
				System.out.println("开奖号码："+prizedraw);

				// 查询上期参与抽奖信息集合
				QueryFilter filterKlPrizedrawCustomer = new QueryFilter(KlPrizedrawCustomer.class);
				filterKlPrizedrawCustomer.addFilter("issueNumber=", klgPrizedrawLssue.getIssueNumber());
				filterKlPrizedrawCustomer.addFilter("status=", 1);//未开奖
				List<KlPrizedrawCustomer> klPrizedrawCustomerList = klPrizedrawCustomerService
						.find(filterKlPrizedrawCustomer);
				System.out.println(klPrizedrawCustomerList.size());
				if (klPrizedrawCustomerList != null && klPrizedrawCustomerList.size() > 0) {
					ExecutorService cachedThreadPool = Executors.newScheduledThreadPool(10);
					for (KlPrizedrawCustomer klPrizedrawCustomer : klPrizedrawCustomerList) {
						//System.out.println(klPrizedrawCustomer.getCustomerId());
						/*cachedThreadPool.execute(new Runnable() {
							@Override
							public void run() {
								// 对比中奖号码
								winningThePrizeBranch(klPrizedrawCustomer, prizedrawList, thisWeekAccountMoney, klgPrizedrawLssue);
							}
						});*/
						winningThePrizeBranch(klPrizedrawCustomer, prizedrawList, thisWeekAccountMoney, klgPrizedrawLssue);
					}
					cachedThreadPool.shutdown();
				}
				
				// 更新用户上期抽奖数据为已开奖
				klPrizedrawCustomerService.updateStatusByIssueNumber(klgPrizedrawLssue.getIssueNumber());
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			// 插入异常日志
			KlgExceptionLog klgExceptionLog = new KlgExceptionLog();
			klgExceptionLog.setFunctionName("winningThePrize");
			String str = e.toString();
			if (str.length() <= 230) {
				klgExceptionLog.setExceptionText(str);
			} else {
				klgExceptionLog.setExceptionText(str.substring(0, 230));
			}
			klgExceptionLogService.save(klgExceptionLog);
		}

		Date endDate = new Date();
		String functionName = "winningThePrize";
		klgTaskLogService.saveLog(functionName, 0, startDate, endDate);

	}
	
	/**
	 * 计算奖金  发放奖金
	 */
	public void bonus(){
		System.out.println("bonus");
		Date startDate = new Date();
		KlgExceptionLogService klgExceptionLogService = SpringUtil.getBean("klgExceptionLogService");
		KlgTaskLogService klgTaskLogService = SpringUtil.getBean("klgTaskLogService");
		KlPrizedrawIssueService klPrizedrawIssueService = SpringUtil.getBean("klPrizedrawIssueService");
		KlPrizedrawCustomerService klPrizedrawCustomerService = SpringUtil.getBean("klPrizedrawCustomerService");
		RemoteKlgService remoteKlgService = SpringUtil.getBean("remoteKlgService");
		KlgPlatformAccountService klgPlatformAccountService = SpringUtil.getBean("klgPlatformAccountService");
		RedisService redisService = SpringUtil.getBean("redisService");
		KlPrizedrawSelectionService klPrizedrawSelectionService = SpringUtil.getBean("klPrizedrawSelectionService");
		
		
		// 查询USDT周奖账户余额
		String redisKey = RulesConfig.PLATFORM_NUMBER + KlgPlatformCurrency.USDTWeeklyAccount.getKey();
		String weekAccountMoney = redisService.get(redisKey);

		// 查询周奖总发放比例
		String bonusProportion = (String) remoteKlgService.getPlatformRule1sConfig("bonusProportion");

		/** 本次发放总奖金 */
		BigDecimal thisWeekAccountMoney = new BigDecimal(weekAccountMoney).multiply(new BigDecimal(bonusProportion))
				.multiply(new BigDecimal(0.01)).setScale(6, BigDecimal.ROUND_FLOOR);
		
		/**计算一等奖金额*/
		//查询一等奖比例
		String firstProportion = (String) remoteKlgService.getPlatformRule1sConfig("firstProportion");
		BigDecimal firstMoney = thisWeekAccountMoney.multiply(new BigDecimal(firstProportion)).multiply(new BigDecimal(0.01)).setScale(6, BigDecimal.ROUND_FLOOR);
		
		/**计算二等等奖金额*/
		//查询二等奖比例
		String secondProportion = (String) remoteKlgService.getPlatformRule1sConfig("secondProportion");
		BigDecimal secondMoney = thisWeekAccountMoney.multiply(new BigDecimal(secondProportion)).multiply(new BigDecimal(0.01)).setScale(6, BigDecimal.ROUND_FLOOR);

		/**计算三等奖金额*/
		//查询三等奖比例
		String thirdProportion = (String) remoteKlgService.getPlatformRule1sConfig("thirdProportion");
		BigDecimal thirdMoney = thisWeekAccountMoney.multiply(new BigDecimal(thirdProportion)).multiply(new BigDecimal(0.01)).setScale(6, BigDecimal.ROUND_FLOOR);

		
		
		//查询上期开奖信息
		QueryFilter filterIssue = new QueryFilter(KlPrizedrawIssue.class);
		filterIssue.addFilter("status=", 3);
		filterIssue.setOrderby("issueNumber desc");
		KlPrizedrawIssue klgPrizedrawLssue = klPrizedrawIssueService.get(filterIssue);
		if(klgPrizedrawLssue!=null){
			
			//查询一等奖中奖信息
			QueryFilter filter1 = new QueryFilter(KlPrizedrawSelection.class);
			filter1.addFilter("issueNumber=", klgPrizedrawLssue.getIssueNumber());
			filter1.addFilter("prizedrawGrade=", 1);
			List<KlPrizedrawSelection> klPrized1 = klPrizedrawSelectionService.find(filter1);
			if(klPrized1!=null&&klPrized1.size()>0){
				this.bonusBranch(firstMoney, klPrized1, klgPrizedrawLssue, 1);
			}
			
			//查询二等奖中奖信息
			QueryFilter filter2 = new QueryFilter(KlPrizedrawSelection.class);
			filter2.addFilter("issueNumber=", klgPrizedrawLssue.getIssueNumber());
			filter2.addFilter("prizedrawGrade=", 2);
			List<KlPrizedrawSelection> klPrized2 = klPrizedrawSelectionService.find(filter2);
			if(klPrized2!=null&&klPrized2.size()>0){
				this.bonusBranch(secondMoney, klPrized2, klgPrizedrawLssue, 2);
			}
			
			//查询三等奖中奖信息
			QueryFilter filter3 = new QueryFilter(KlPrizedrawSelection.class);
			filter3.addFilter("issueNumber=", klgPrizedrawLssue.getIssueNumber());
			filter3.addFilter("prizedrawGrade=", 3);
			List<KlPrizedrawSelection> klPrized3 = klPrizedrawSelectionService.find(filter3);
			if(klPrized3!=null&&klPrized3.size()>0){
				this.bonusBranch(thirdMoney, klPrized3, klgPrizedrawLssue, 3);
			}
		}
		
		
		Date endDate = new Date();
		String functionName = "bonus";
		klgTaskLogService.saveLog(functionName, 0, startDate, endDate);
	}
	
	
	
	private void bonusBranch(BigDecimal moneyAll,List<KlPrizedrawSelection> klPrized1,KlPrizedrawIssue klgPrizedrawLssue,int prizedrawGrade){
		KlgAssetsRecordService klgAssetsRecordService = SpringUtil.getBean("klgAssetsRecordService");
		MessageProducer messageProducer = SpringUtil.getBean("messageProducer");
		KlPrizedrawSelectionService klPrizedrawSelectionService = SpringUtil.getBean("klPrizedrawSelectionService");
		
		//计算每人中奖金额
		BigDecimal money1 = moneyAll.divide(new BigDecimal(klPrized1.size()), 6, BigDecimal.ROUND_FLOOR);
		try {
			List<Accountadd> list = null;
			List<PlatformAccountadd> platformAccounts = null;// 平台账户
			PlatformAccountadd prizeadd = null;
			
			//更新中奖信息  ，发放奖金
			for(KlPrizedrawSelection klPrizedrawSelection : klPrized1){
				list = new ArrayList<>();
				klPrizedrawSelection.setMoney(money1);
				klPrizedrawSelection.setStatus(2);
				klPrizedrawSelectionService.update(klPrizedrawSelection);
				// 查询用户币账户信息
				ExDigitalmoneyAccountRedis exaccount = this.selectAccount(klPrizedrawSelection.getCustomerId(),
						"USDT");
				// usdt热账户增加
				list.add(getAccountAdd(exaccount.getId(),money1, 1, 1, 2,
						"111"));
				KlgAssetsRecord klgAssetsRecord = new KlgAssetsRecord(exaccount.getCustomerId(), exaccount.getId(),
						IdGenerate.transactionNum(NumConstant.Ex_Dm_Transaction), 1, exaccount.getCoinCode(),
						money1, 1, TriggerPointEnum.Bonus.getKey(),
						"", prizedrawGrade+"等奖,期号:"+klgPrizedrawLssue.getIssueNumber());
				klgAssetsRecordService.save(klgAssetsRecord);
				// 周奖账户减少
				prizeadd = PlatformAccountUtil.accountSub(KlgPlatformCurrency.USDTWeeklyAccount.getKey(),
						String.valueOf(money1),
						"发奖金,"+prizedrawGrade+"等奖，用户："+klPrizedrawSelection.getCustomerId()+",期号:" + klgPrizedrawLssue.getIssueNumber());
				platformAccounts = new ArrayList<>();
				platformAccounts.add(prizeadd);

				messageProducer.toPlatformCurrency(JSON.toJSONString(platformAccounts));
				System.out.println("币账户增加消息:"+list);
				messageProducer.toAccount(JSON.toJSONString(list));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	

	/**
	 * 
	 * @param klPrizedrawCustomer
	 *            用户抽奖信息
	 * @param prizedrawList
	 *            中奖号码数组
	 * @param thisWeekAccountMoney
	 *            周奖发放总金额
	 * @param prizedraw
	 *            本期开奖信息
	 */
	private void winningThePrizeBranch(KlPrizedrawCustomer klPrizedrawCustomer, List<Integer> prizedrawList,
			BigDecimal thisWeekAccountMoney, KlPrizedrawIssue klgPrizedrawLssue) {
		KlgExceptionLogService klgExceptionLogService = SpringUtil.getBean("klgExceptionLogService");
		try {
			int count = 0;// 记录正确的数字数量
			List<Integer> cusPList = StringToInt(klPrizedrawCustomer.getPrizedrawNumber());
			if (cusPList.size() == prizedrawList.size()) {
				for (int i = 0; i < prizedrawList.size(); i++) {
					if (prizedrawList.get(i) == cusPList.get(i)) {
						count++;
					}
				}
			}
			//System.out.println("yonghu:"+klPrizedrawCustomer.getCustomerId()+"===="+count);
			switch (count) {
			case 8:// 一等奖
					// 插入中奖信息
				this.insertPrizedDraw(klPrizedrawCustomer, klgPrizedrawLssue, 1, thisWeekAccountMoney);
				break;
			case 7:// 二等奖
				this.insertPrizedDraw(klPrizedrawCustomer, klgPrizedrawLssue, 2, thisWeekAccountMoney);
				break;
			case 6:// 三等奖
				this.insertPrizedDraw(klPrizedrawCustomer, klgPrizedrawLssue, 3, thisWeekAccountMoney);
				break;

			default:
				break;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			// 插入异常日志
			KlgExceptionLog klgExceptionLog = new KlgExceptionLog();
			klgExceptionLog.setFunctionName("winningThePrizeBranch");
			String str = e.toString();
			if (str.length() <= 230) {
				klgExceptionLog.setExceptionText(str);
			} else {
				klgExceptionLog.setExceptionText(str.substring(0, 230));
			}
			klgExceptionLogService.save(klgExceptionLog);
		}

	}

	/**
	 * 插入中奖信息
	 * 
	 * @param klPrizedrawCustomer
	 *            中奖用户信息
	 * @param prizedraw
	 *            中奖号码
	 * @param prizedrawGrade
	 *            中奖等级
	 * @param thisWeekAccountMoney
	 *            周奖发放总金额
	 */
	private void insertPrizedDraw(KlPrizedrawCustomer klPrizedrawCustomer, KlPrizedrawIssue klgPrizedrawLssue, int prizedrawGrade,
			BigDecimal thisWeekAccountMoney) {
		KlPrizedrawSelectionService klPrizedrawSelectionService = SpringUtil.getBean("klPrizedrawSelectionService");
		KlPrizedrawSelection klPrizedrawSelection = new KlPrizedrawSelection();
		klPrizedrawSelection.setCustomerId(klPrizedrawCustomer.getCustomerId());
		klPrizedrawSelection.setIssueNumber(klgPrizedrawLssue.getIssueNumber());
		klPrizedrawSelection.setLotteryNumber(klgPrizedrawLssue.getLotteryNumber());
		klPrizedrawSelection.setPrimeNumber(klgPrizedrawLssue.getPrimeNumber());
		klPrizedrawSelection.setPrizedrawGrade(prizedrawGrade);
		klPrizedrawSelection.setPrizedrawNumber(klPrizedrawCustomer.getPrizedrawNumber());
		klPrizedrawSelection.setStartDate(klPrizedrawCustomer.getStartDate());
		klPrizedrawSelection.setStatus(1);
		
		klPrizedrawSelectionService.save(klPrizedrawSelection);

	}

	/**
	 * String 转 List<Integer>
	 * 
	 * @param arrs
	 * @return
	 */
	public List<Integer> StringToInt(String arrs) {
		List<Integer> list = new ArrayList<Integer>();

		for (int i = 0; i < arrs.length(); i++) {
			list.add(Integer.valueOf(arrs.charAt(i)));
		}

		return list;
	}

	/**
	 * 生成1000以内的随机质数
	 * 
	 * @return
	 */
	private static int getPrimeNumber() {
		// 生成1000以内的所有质数
		int i, j;
		int num;
		/*
		 * List<Integer> list = new ArrayList<Integer>(); for (i = 1; i <= 1000;
		 * i++) { for (j = 2; j < i; j++) { if (i % j == 0) break; }
		 * 
		 * if (i == j) list.add(j); }
		 */
		/**
		 * 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71,
				73, 79, 83, 89, 97, 
		 */
		List<Integer> list = Arrays.asList(101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181,
				191, 193, 197, 199, 211, 223, 227, 229, 233, 239, 241, 251, 257, 263, 269, 271, 277, 281, 283, 293, 307,
				311, 313, 317, 331, 337, 347, 349, 353, 359, 367, 373, 379, 383, 389, 397, 401, 409, 419, 421, 431, 433,
				439, 443, 449, 457, 461, 463, 467, 479, 487, 491, 499, 503, 509, 521, 523, 541, 547, 557, 563, 569, 571,
				577, 587, 593, 599, 601, 607, 613, 617, 619, 631, 641, 643, 647, 653, 659, 661, 673, 677, 683, 691, 701,
				709, 719, 727, 733, 739, 743, 751, 757, 761, 769, 773, 787, 797, 809, 811, 821, 823, 827, 829, 839, 853,
				857, 859, 863, 877, 881, 883, 887, 907, 911, 919, 929, 937, 941, 947, 953, 967, 971, 977, 983, 991,
				997);
		Random random = new Random();
		int n = random.nextInt(list.size());
		num = list.get(n);
		return num;
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		// System.out.println(getPrimeNumber());
		/*Long A = 78945612312L;
		Long B = 1234567L;
		// Long C = (A-B)/859;

		BigDecimal bigd = new BigDecimal(A).divide(new BigDecimal(getPrimeNumber()), 20, BigDecimal.ROUND_FLOOR);
		System.out.println(bigd);
		String strD = String.valueOf(bigd);
		strD = strD.replace(".", "").trim();
		strD = strD.substring(6, 13);
		System.out.println(strD);*/
		/*String date2="2019-07-01 00:00:00";
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date d1=sdf.parse(date2);
		
		System.out.println(getInterval(new Date(),d1));
		*/

	}
	
	public long getInterval(Date begin_date, Date end_date) throws Exception{
	    long day = 0;
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	    if(begin_date != null){
	        String begin = sdf.format(begin_date);
	        begin_date  = sdf.parse(begin);
	    }
	    if(end_date!= null){
	        String end= sdf.format(end_date);
	        end_date= sdf.parse(end);
	    }
	    day = (end_date.getTime()-begin_date.getTime())/(24*60*60*1000);
	    return day;
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

}
