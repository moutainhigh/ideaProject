/**
 * Copyright:   
 * @author:      zhouming
 * @version:     V1.0 
 * @Date:        2019-08-13 13:53:38 
 */
package hry.licqb.award.service.impl;

import com.alibaba.fastjson.JSON;
import hry.bean.JsonResult;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.model.AppConfig;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.core.thread.ThreadPool;
import hry.front.redis.model.UserRedis;
import hry.licqb.award.RulesConfig;
import hry.licqb.award.dao.OutInfoDao;
import hry.licqb.award.model.OutInfo;
import hry.licqb.award.service.OutInfoService;
import hry.licqb.level.model.CustomerLevel;
import hry.licqb.level.service.CustomerLevelService;
import hry.licqb.record.model.DealRecord;
import hry.licqb.record.service.CustomerFreezeService;
import hry.licqb.record.service.DealRecordService;
import hry.licqb.util.AccountUtil;
import hry.licqb.util.DealEnum;
import hry.licqb.util.RedisStaticUtil;
import hry.mq.producer.service.MessageProducer;
import hry.redis.common.dao.RedisUtil;
import hry.redis.common.utils.RedisService;
import hry.trade.redis.model.Accountadd;
import hry.trade.redis.model.ExDigitalmoneyAccountRedis;
import hry.util.QueryFilter;
import hry.util.idgenerate.IdGenerate;
import hry.web.app.service.AppConfigService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import util.UserRedisUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * <p> OutInfoService </p>
 * @author:         zhouming
 * @Date :          2019-08-13 13:53:38  
 */
@Service("outInfoService")
public class OutInfoServiceImpl extends BaseServiceImpl<OutInfo, Long> implements OutInfoService{
	private static Logger logger = Logger.getLogger(OutInfoServiceImpl.class);

	@Resource
	private RedisService redisService;
	@Resource
	private AppConfigService appConfigService;
	@Resource
	private MessageProducer messageProducer;
	@Resource
	private OutInfoService outInfoService;
	@Resource
	private DealRecordService dealRecordService;
	@Resource
	private CustomerLevelService customerLevelService;
	@Resource
	private CustomerFreezeService customerFreezeService;

	@Resource(name="outInfoDao")
	@Override
	public void setDao(BaseDao<OutInfo, Long> dao) {
		super.dao = dao;
	}

	@Override
	public synchronized void putIntoStorageMoney(Map<String, Object> map) {
		if (map == null) {
			return;
		}
		/*~~~~~~~~~~~~~~~~~~~~~~~~~获取配置信息--start~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
		// 根据typekey进行查询 --- 投资区间配置
		BigDecimal minInvest = new BigDecimal("0");
		BigDecimal maxInvest = new BigDecimal("0");
		List<AppConfig> investRangeKey = appConfigService.find(new QueryFilter(AppConfig.class).addFilter("typekey=", RulesConfig.InvestRangeKey));
		for (AppConfig appConfig : investRangeKey) {
			if ("minInvest".equals(appConfig.getConfigkey())) {
				minInvest = new BigDecimal(appConfig.getValue());
			}
			if ("maxInvest".equals(appConfig.getConfigkey())) {
				maxInvest = new BigDecimal(appConfig.getValue());
			}
		}
		System.out.println("获取配置信息~~~~最小投资金额："+minInvest+"~~~~最大投资金额："+maxInvest);
		/*~~~~~~~~~~~~~~~~~~~~~~~~~获取配置信息--end~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

		/*~~~~~~~~~~~~~~~~~~~~~~~~~立即冻结--start~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
		// 参数获取
		String customerId = map.get("customerId").toString();
		String accountId = map.get("accountId").toString();
		String transactionNum = map.get("transactionNum").toString();
		String coinCode = map.get("coinCode").toString();
		// 初始化： hotmoney 投入金额 coldMoney 冻结金额 money 实际投入金额
		BigDecimal hotmoney = new BigDecimal(map.get("money").toString());
		BigDecimal coldMoney = new BigDecimal("0");
		BigDecimal money = new BigDecimal("0");

		// 获取币账户信息--查redis缓存
		RedisUtil<UserRedis> redisUtil = new RedisUtil<UserRedis>(UserRedis.class);
		UserRedis userRedis = redisUtil.get(customerId.toString());
		// 获得币账户
		ExDigitalmoneyAccountRedis exaccount = UserRedisUtils.getAccount(userRedis.getDmAccountId(coinCode).toString(),coinCode);
		// 已冻结金额
		// 直接取redis冻结数额不准确
		//coldMoney = exaccount.getColdMoney();
		coldMoney = customerFreezeService.getFreezeMoney(Long.parseLong(customerId));
		// 预计累计投入数
		BigDecimal totalMoney = hotmoney.add(coldMoney);
		// 校验是否达到最高投资金额
		if (totalMoney.compareTo(maxInvest) < 1){
			// 如果冻结金额小于最小投资金额则 投入的money = hotmoney.add(coldMoney)
			if (coldMoney.compareTo(minInvest) == -1) {
				money = hotmoney.add(coldMoney);
			} else {
				money = hotmoney;
			}
		} else {
			// 取差
			money = maxInvest.subtract(coldMoney);
			// 实际冻结数
			hotmoney = money;
		}
		List<Accountadd> list = new ArrayList<Accountadd>();
		/*需求充多少冻结多少---如果出现负数，看操作币账户的mq是否成功执行*/
		// 热账户减少---锁仓冻结（55）
		list.add(AccountUtil.getAccountAdd(exaccount.getId(), new BigDecimal("-" + hotmoney), 1, 1,55,
				transactionNum));
		// 冷账户增加---锁仓冻结（55）
		list.add(AccountUtil.getAccountAdd(exaccount.getId(), new BigDecimal("+" + hotmoney), 2, 1, 55,
				transactionNum));
		messageProducer.toAccount(JSON.toJSONString(list));
		System.out.println("立即冻结~~~~投入金额："+hotmoney+"~~~~冻结金额："+coldMoney+"~~~~实际投入金额:"+money);
		/*~~~~~~~~~~~~~~~~~~~~~~~~~立即冻结--end~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
		/*~~~~~~~~~~~~~~修改冻结金额--start~~~~~~~~~~~~~~~*/
		customerFreezeService.updateFreezeMoney(Long.parseLong(customerId), hotmoney);
		/*~~~~~~~~~~~~~~修改冻结金额--end~~~~~~~~~~~~~~~*/

		/*~~~~~~~~~~~~~~~~~~~~~~~~~添加四倍存储量--start~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
		// 冻结金额达需超过最小投资金额
		if (totalMoney.compareTo(minInvest) > -1 && money.compareTo(BigDecimal.ZERO) == 1) {
			// 额度固定设置为投资金额的4倍
			BigDecimal storageMoney = money.multiply(new BigDecimal(4));
			QueryFilter filter = new QueryFilter(OutInfo.class);
			filter.addFilter("customerId=", customerId);
			filter.addFilter("status=", 0);
			OutInfo outInfo = outInfoService.get(filter);
			if (outInfo != null) {
				outInfo.setBaseMoney(outInfo.getBaseMoney().add(money));
				outInfo.setBaseMaxMoney(maxInvest);
				outInfo.setHotMoney(outInfo.getHotMoney().add(storageMoney));
				outInfo.setAllMoney(outInfo.getAllMoney().add(storageMoney));
				outInfoService.update(outInfo);
			} else {
				outInfo = new OutInfo();
				outInfo.setBaseMoney(money);
				outInfo.setBaseMaxMoney(maxInvest);
				outInfo.setOutMultiple(4);
				outInfo.setAccountId(Long.valueOf(accountId));
				outInfo.setCustomerId(Long.valueOf(customerId));
				outInfo.setHotMoney(storageMoney);
				outInfo.setAllMoney(storageMoney);
				outInfo.setCoinCode(coinCode);
				outInfoService.save(outInfo);
			}
			System.out.println("添加四倍存储量~~~~成功");

			/*~~~~~~~~~~~~~~初始化等级 ~~~~~~~~~~~~~*/
			QueryFilter levelFilter = new QueryFilter(CustomerLevel.class);
			levelFilter.addFilter("customerId=",customerId);
			CustomerLevel level = customerLevelService.get(levelFilter);
			if (level == null) {
				level = new CustomerLevel();
				level.setCustomerId(Long.parseLong(customerId));
				customerLevelService.save(level);
			}

			/*~~~~~~~~~~~~~~添加收益记录 ~~~~~~~~~~~~~*/
			DealRecord dealRecord = new DealRecord();
			dealRecord.setOutInfoId(outInfo.getId());
			dealRecord.setAccountId(outInfo.getAccountId());
			dealRecord.setCustomerId(outInfo.getCustomerId());
			dealRecord.setTransactionNum(transactionNum);
			dealRecord.setCoinCode(coinCode);
			dealRecord.setDealType(Integer.parseInt(map.get("dealType").toString()));
			dealRecord.setDealMoney(money);
			dealRecord.setRemark(map.get("dealRemark").toString());
			dealRecordService.save(dealRecord);
			// 为了app能及时反映 则放入线程中 --- 目前只是先放入redis中(算等级还未用到)
			ThreadPool.exe(() -> {
				// 异步统计
				statistics(Long.parseLong(customerId));
				/*~~~~~~~~~~~~~~更新用户父级用户等级~~~~~~~~~~~~~~~~~~~*/
				messageProducer.toUserUpGrade(customerId);
				/*~~~~~~~~~~~~~~更新用户自己社区等级~~~~~~~~~~~~~~~~~~~*/
				messageProducer.toUserUpTeamGrade(customerId);
			});

		}
		/*~~~~~~~~~~~~~~~~~~~~~~~~~添加四倍存储量--end~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
	}

	/**
	 * 统计
	 * */
	private void statistics(Long customerId) {
		Long start = System.currentTimeMillis();
		// 多线程统计当前用户及当前用户父级用户的 有效直推人数及伞下团队人数
		Map<String, String> paramMap = new HashMap<>();
		paramMap.put("customerId", customerId.toString());
		List<OutInfo> outInfoList = outInfoService.findParentUserList(paramMap);
		OutInfo myinfo = new OutInfo();
		myinfo.setCustomerId(customerId);
		outInfoList.add(0, myinfo);
		List<List<OutInfo>> list = splitListForNum(outInfoList, 5);
		CompletableFuture[] futures = new CompletableFuture[5];
		for (int i = 0; i < list.size(); i++) {
			int finalI = i;
			futures[i] = CompletableFuture.supplyAsync(() -> execute(list.get(finalI)));
		}
		CompletableFuture.allOf(futures).join();
		Long end = System.currentTimeMillis();
		System.out.println("多线程统计共用时："+ (end - start) + "ms");
	}

	public Integer execute (List<OutInfo> outInfos) {
		for (OutInfo info : outInfos) {
			// 查询直推人数
			Integer direcUserNum = outInfoService.findDirecUserNumTwo(info.getCustomerId());
			// 查询用户伞下团队业绩
			String userId = "%-"+info.getCustomerId()+",%";
			BigDecimal teamAsset = outInfoService.findTeamAssetTwo(userId);
			// 查询后存入redis
			Map<String, String> map = new HashMap<>();
			map.put("direcUserNum", direcUserNum.toString());
			map.put("teamAsset", teamAsset.toString());
			redisService.saveMap("USERDETAIL:"+info.getCustomerId(), map);
		}
		return 1;
	}

	/**
	 * 分割list
	 * */
	private List<List<OutInfo>> splitListForNum(List<OutInfo> source, int num){
		List<List<OutInfo>> result = new ArrayList<List<OutInfo>>();
		int remaider = source.size()%num;  //(先计算出余数)
		int number = source.size()/num;  //然后是商
		int offset = 0;//偏移量
		for(int i = 0; i < num; i ++){
			List<OutInfo> value = null;
			if(remaider>0){
				value = source.subList(i*number+offset, (i+1)*number+offset+1);
				remaider--;
				offset++;
			}else{
				value = source.subList(i*number+offset, (i+1)*number+offset);
			}
			result.add(value);
		}
		return result;
	}


	@Override
	public List<OutInfo> findPageInfo(int start, int limit) {
		int offset = (start-1)*limit;
		return outInfoService.findPageInfo(offset,limit);
	}

	@Override
	public int getOutInfoCount() {
		return outInfoService.getOutInfoCount();
	}

	/**
	 * 统一生产收益记录、扣除额度、出局 (重要)
	 * @param outInfo 出局信息实体
	 * @param actualMoney 实际基数---存储本金(静态收益)、投资金额(见点奖)
	 * @param awardRatio 收益比例
	 * @param site 收益产出位置标识
	 * @param type 收益类型
	 * */
	@Override
	public JsonResult createBusiness(OutInfo outInfo, BigDecimal actualMoney,
									 BigDecimal awardRatio, String site, String type) {
		String code = "0000";
		// 当前平台币code
		String platformCode = RedisStaticUtil.getAppConfigValue(RulesConfig.RulesCoinKey, "coinCode");
		// 当前平台币价格
		String value = RedisStaticUtil.getAppConfigValue(RulesConfig.RulesCoinKey, "issuePrice");
		BigDecimal platformPrice = new BigDecimal(value);

		// 获取币账户信息--查redis缓存
		ExDigitalmoneyAccountRedis platExaccount = UserRedisUtils.getAccountRedis(outInfo.getCustomerId().toString(),platformCode);
		// 预计基数
		BigDecimal predictMoney = actualMoney;
		// 实际奖励收益
		BigDecimal actualAward = actualMoney.multiply(awardRatio);
		// 预计消耗额度
		BigDecimal predictExpendLimit = actualAward.multiply(platformPrice);
		// 实际消耗额度
		BigDecimal actualExpendLimit;
		// 是否出局
		Boolean isOut = false;
		// 重新查询出局信息
		QueryFilter filter = new QueryFilter(OutInfo.class);
		filter.addFilter("id=", outInfo.getId());
		filter.addFilter("status=", 0);
		OutInfo info = outInfoService.get(filter);
		if (predictExpendLimit.compareTo(info.getHotMoney()) == -1) {
			actualExpendLimit = predictExpendLimit;
		} else {
			actualExpendLimit = info.getHotMoney();
			// 获取实际奖励收益
			actualAward = actualExpendLimit.divide(platformPrice,8, BigDecimal.ROUND_HALF_UP);
			// 实际基数(注：actualMoney 作为返回值 为实际释放数)
			actualMoney = actualAward.divide(awardRatio, 8, BigDecimal.ROUND_HALF_UP);
			// 出局
			code = "1010";
			isOut = true;
		}
		/*~~~~~~~~~~~~~~平台币账户添加币~~~~~~~~~~~~~*/
		List<Accountadd> list = new ArrayList<Accountadd>();
		// 流水号
		String transactionNum = IdGenerate.transactionNum(site);
		// 热账户增加---分红（58）
		list.add(AccountUtil.getAccountAdd(platExaccount.getId(), new BigDecimal("+" + actualAward), 1, 1,58,
				transactionNum));
		messageProducer.toAccount(JSON.toJSONString(list));

		/*~~~~~~~~~~~~~~扣除消费额度~~~~~~~(记：需要考虑并发--看需不需要把方法提出去)~~~~~~*/
		/*~~~~~~~~~~~~~~修改额度放入mq消费*/
		/*Map<String, String> map = new HashMap<>();
		map.put("infoId", outInfo.getId().toString());
		map.put("actualExpendLimit", actualExpendLimit.toString());
		map.put("isOut", isOut ? "1" : "0" );
		messageProducer.toUpdateOutInfo(JSONObject.toJSONString(map));*/

		OutInfo newInfo = new OutInfo();
		newInfo.setId(info.getId());
		newInfo.setHotMoney(info.getHotMoney().subtract(actualExpendLimit));
		newInfo.setColdMoney(info.getColdMoney().add(actualExpendLimit));
		newInfo.setStatus(isOut ? 1 : 0);
		outInfoService.update(newInfo);


		/*~~~~~~~~~~~~~~添加收益记录~~~~~~~~~~~~~*/
		DealRecord dealRecord = new DealRecord();
		dealRecord.setOutInfoId(info.getId());
		dealRecord.setAccountId(info.getAccountId());
		dealRecord.setCustomerId(info.getCustomerId());
		dealRecord.setTransactionNum(transactionNum);
		dealRecord.setCoinCode("BBGO");
		dealRecord.setDealType(Integer.parseInt(type));
		dealRecord.setDealMoney(actualAward);
		dealRecord.setRatio(awardRatio);
		dealRecord.setCodeValue(platformPrice);
		dealRecord.setPredictExpendlimit(predictExpendLimit);
		dealRecord.setActualExpendlimit(actualExpendLimit);
		dealRecord.setActualMoney(actualMoney);
		dealRecord.setPredictMoney(predictMoney);
		dealRecord.setRemark(DealEnum.getName(site));
		dealRecordService.save(dealRecord);

		/*~~~~~~~~~~~~~~收益达到顶峰--出局~~~~~~~~~~~~~*/
		if (isOut) {
			outInfoService.userOut(info.getId(), info.getCustomerId(), info.getCoinCode());
		}
		return new JsonResult(true).setObj(actualMoney).setCode(code);
	}

	/**
	 * 出局
	 * */
	@Override
	public void userOut(Long outInfoId, Long customerId, String coinCode) {
		/*~~~~~~~~~~~~~~~~~~~~~~币账户清空（USDT）~~~~~~~~~~~~~~~~~~~~~~~~~~*/
		// 获取币账户信息--查redis缓存
		RedisUtil<UserRedis> redisUtil = new RedisUtil<UserRedis>(UserRedis.class);
		UserRedis userRedis = redisUtil.get(customerId.toString());
		ExDigitalmoneyAccountRedis exaccount = UserRedisUtils.getAccount(userRedis.getDmAccountId(coinCode).toString(),coinCode);
		// 清除冻结部分
		// 直接取redis冻结数额不准确
		//BigDecimal coldMoney = exaccount.getColdMoney();
		BigDecimal coldMoney = customerFreezeService.getFreezeMoney(customerId);
		List<Accountadd> list = new ArrayList<Accountadd>();
		// 流水号
		String transactionNum = IdGenerate.transactionNum(DealEnum.SITE6.getIndex());
		// 冷账户减少---冻结扣除（82）
		list.add(AccountUtil.getAccountAdd(exaccount.getId(), new BigDecimal("-" + coldMoney), 2, 1,82,
				transactionNum));
		messageProducer.toAccount(JSON.toJSONString(list));
		// 用户出局更新等级
		messageProducer.toUserUpGrade(customerId);

		/*~~~~~~~~~~~~~~~~~~~~~~添加出局扣除记录~~~~~~~~~~~~~~~~~~~~~~~~~~*/
		DealRecord record = new DealRecord();
		record.setOutInfoId(outInfoId);
		record.setCustomerId(customerId);
		//record.setAccountId(exaccount.getId());
		record.setCoinCode(coinCode);
		record.setTransactionNum(transactionNum);
		record.setDealMoney(coldMoney);
		record.setDealType(Integer.parseInt(DealEnum.TYPE6.getIndex()));
		record.setRatio(new BigDecimal("100"));
		record.setRemark(DealEnum.SITE6.getName());
		dealRecordService.save(record);
		/*~~~~~~~~~清除个人冻结金额~~~~~~~~~~*/
		customerFreezeService.clearFreezeMoney(customerId);
	}

	@Override
	public List<OutInfo> findGiveSeeAward(String customerId,String tierNum) {
		Map<String, String> map = new HashMap<>();
		map.put("customerId",customerId);
		map.put("tierNum",tierNum);
		return ((OutInfoDao)dao).findParentUserList(map);
	}

	@Override
	public List<OutInfo> findParentUserList(Map<String, String> map) {
		return ((OutInfoDao)dao).findParentUserList(map);
	}

	@Override
	public List<OutInfo> findParentUserListTwo(Map<String, String> map) {
		return ((OutInfoDao)dao).findParentUserListTwo(map);
	}

	@Override
	public List<OutInfo> findSonUserList(Map<String, String> map) {
		return ((OutInfoDao)dao).findSonUserList(map);
	}

	@Override
	public int getSonUserCount(Map<String, String> map) {
		return ((OutInfoDao)dao).getSonUserCount(map);
	}

	@Override
	public List<OutInfo> findGiveOutTeamAwardList() {
		return ((OutInfoDao)dao).findGiveOutTeamAwardList();
	}

	@Override
	public Integer findDirecUserNum(Long customerId) {
		return ((OutInfoDao)dao).findDirecUserNum(customerId);
	}

	@Override
	public Integer findDirecUserNumTwo(Long customerId) {
		return ((OutInfoDao)dao).findDirecUserNumTwo(customerId);
	}

	@Override
	public BigDecimal findTeamAsset(Long customerId) {
		return ((OutInfoDao)dao).findTeamAsset(customerId);
	}

	@Override
	public BigDecimal findTeamAssetTwo(String customerId) {
		return ((OutInfoDao)dao).findTeamAssetTwo(customerId);
	}

	@Override
	public Integer findWireNum(Map<String, String> map) {
		return ((OutInfoDao)dao).findWireNum(map);
	}

	@Override
	public Integer findWireNumTwo(Map<String, String> map) {
		return ((OutInfoDao)dao).findWireNumTwo(map);
	}

	@Override
	public List<OutInfo> findDirectUser(Long customerId) {
		return ((OutInfoDao)dao).findDirectUser(customerId);
	}

	@Override
	public OutInfo getOutInfo(Long customerId) {
		Map<String, String> map = new HashMap<>(1);
		map.put("customerId",customerId.toString());
		List<OutInfo> outInfoList = ((OutInfoDao)dao).findOutInfoList(map);
		return outInfoList != null && outInfoList.size() > 0 ? outInfoList.get(0) : null;
	}
}
