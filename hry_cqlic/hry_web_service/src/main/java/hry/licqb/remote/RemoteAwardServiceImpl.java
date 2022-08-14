package hry.licqb.remote;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import hry.bean.FrontPage;
import hry.bean.JsonResult;
import hry.core.constant.StringConstant;
import hry.core.thread.ThreadPool;
import hry.customer.user.model.AppCustomer;
import hry.customer.user.service.AppCustomerService;
import hry.exchange.account.model.ExDigitalmoneyAccount;
import hry.exchange.account.service.ExDigitalmoneyAccountService;
import hry.exchange.transaction.model.ExDmTransaction;
import hry.exchange.transaction.service.ExDmTransactionService;
import hry.licqb.award.RulesConfig;
import hry.licqb.award.model.OutInfo;
import hry.licqb.award.service.OutInfoService;
import hry.licqb.level.model.CustomerLevel;
import hry.licqb.level.model.LevelConfig;
import hry.licqb.level.model.TeamLevelConfig;
import hry.licqb.level.service.CustomerLevelService;
import hry.licqb.level.service.LevelConfigService;
import hry.licqb.level.service.TeamLevelConfigService;
import hry.licqb.record.model.DealRecord;
import hry.licqb.record.model.PlatformTotal;
import hry.licqb.record.model.ReleaseRuleDetails;
import hry.licqb.record.service.*;
import hry.licqb.thread.ChargeRecordRunnable;
import hry.licqb.util.AccountUtil;
import hry.licqb.util.DealEnum;
import hry.licqb.util.RedisStaticUtil;
import hry.mq.producer.service.MessageProducer;
import hry.redis.common.utils.RedisService;
import hry.trade.redis.model.Accountadd;
import hry.trade.redis.model.ExDigitalmoneyAccountRedis;
import hry.util.PageFactory;
import hry.util.QueryFilter;
import hry.util.date.DateUtil;
import hry.util.idgenerate.IdGenerate;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.log4j.Logger;
import util.UserRedisUtils;

import javax.annotation.Resource;
import javax.xml.bind.SchemaOutputResolver;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.CompletableFuture;

/**
 * 奖励发放service
 * @author zhouming
 * @date 2019/8/14 15:36
 * @Version 1.0
 */
public class RemoteAwardServiceImpl implements RemoteAwardService {
    private final static Logger log = Logger.getLogger(RemoteAwardServiceImpl.class);

    private  final static String ChargeRecordKey = "ChargeRecordKey";
    private  final static int pageSize = 1000;
    private static Object rlock = new Object();

    @Resource
    private AppCustomerService appCustomerService;
    @Resource
    private OutInfoService outInfoService;
    @Resource
    private RedisService redisService;
    @Resource
    private DealRecordService dealRecordService;
    @Resource
    private TaskLogService taskLogService;
    @Resource
    private LevelConfigService levelConfigService;
    @Resource
    private CustomerLevelService customerLevelService;
    @Resource
    private TeamLevelConfigService teamLevelConfigService;
    @Resource
    private ReleaseRuleDetailsService releaseRuleDetailsService;
    @Resource
    private ExDmTransactionService dmTransactionService;
    @Resource
    private MessageProducer messageProducer;
    @Resource
    private CustomerFreezeService customerFreezeService;
    @Resource
    private PlatformTotalService platformTotalService;
    @Resource
    private UsdtTotalService usdtTotalService;
    @Resource
    private ExDigitalmoneyAccountService exDigitalmoneyAccountService;
    /**
     * 发放用户静态奖励
     * */
    @Override
    public void giveOutStaticAward() {
        Date startDate = new Date();
        QueryFilter filter = new QueryFilter(OutInfo.class);
        filter.addFilter("status=",0);
        // 查询所有未出局的用户
        List<OutInfo> outInfoList = outInfoService.find(filter);
        // 获取当前天比例
        BigDecimal awardRatio = this.getAwardRatio();
        awardRatio = awardRatio.divide(new BigDecimal("100"));
        for (OutInfo outInfo : outInfoList) {
            JSONObject paramMap = new JSONObject();
            paramMap.put("outInfo",outInfo);
            paramMap.put("awardRatio", awardRatio);
            messageProducer.toOutStaticAward(JSONObject.toJSONString(paramMap));
        }
        Date endDate = new Date();
        /*~~~~~~~~~~~~~定时器执行日志---添加~~~~~~~~~~*/
        taskLogService.saveLog("giveOutStaticAward", 0, startDate, endDate);
    }

    @Override
    public void mqOutStaticAward(String jsonMap) {
        JSONObject jsonObject = JSONObject.parseObject(jsonMap);
        OutInfo outInfo =  JSONObject.parseObject(jsonObject.getString("outInfo"), OutInfo.class);
        BigDecimal awardRatio = new BigDecimal(jsonObject.getString("awardRatio"));
        // 获取币账户信息--查redis缓存
        // 预计基数---存储本金
        //BigDecimal predictMoney = exaccount.getColdMoney();
        BigDecimal predictMoney = customerFreezeService.getFreezeMoney(outInfo.getCustomerId());
        // 最大投资金额
        BigDecimal maxInvest = outInfo.getBaseMaxMoney();
        // 实际基数
        BigDecimal actualMoney;
        if (predictMoney.compareTo(maxInvest) != -1) {
            actualMoney = maxInvest;
        } else {
            actualMoney = predictMoney;
        }
        String site = DealEnum.SITE1.getIndex();
        String type = DealEnum.TYPE1.getIndex();
        /*~~~~~~~~~~~~~~~~~~~~创建收益相关业务(重要)~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
        outInfoService.createBusiness(outInfo, actualMoney,
                awardRatio, site, type);
    }

    @Override
    public void surplusUSDTExchange() {
        QueryFilter filter = new QueryFilter(OutInfo.class);
        filter.addFilter("status=",0);
        // 查询所有未出局的用户
        List<OutInfo> outInfoList = outInfoService.find(filter);
        if (outInfoList != null && outInfoList.size() > 0) {
            for (OutInfo outInfo : outInfoList) {
                JSONObject paramMap = new JSONObject();
                paramMap.put("outInfo",outInfo);
                messageProducer.toSurplusUSDTAward(JSONObject.toJSONString(paramMap));
            }
        }
    }

    @Override
    public void mqSurplusUSDTExchange(String jsonMap) {
        JSONObject jsonObject = JSONObject.parseObject(jsonMap);
        OutInfo outInfo =  JSONObject.parseObject(jsonObject.getString("outInfo"), OutInfo.class);
        // 当前平台币code
        String platformCode = RedisStaticUtil.getAppConfigValue(RulesConfig.RulesCoinKey, "coinCode");
        // 当前平台币价格
        String value = RedisStaticUtil.getAppConfigValue(RulesConfig.RulesCoinKey, "issuePrice");
        BigDecimal platformPrice = new BigDecimal(value);
        // 兑换数量
        BigDecimal bbgoMoney = outInfo.getHotMoney().divide(platformPrice, 8, BigDecimal.ROUND_HALF_UP);
        // 流水号
        String transactionNum = IdGenerate.transactionNum("OX");

        // 用户出局
        OutInfo newInfo = new OutInfo();
        newInfo.setId(outInfo.getId());
        newInfo.setStatus(1);
        newInfo.setSaasid("111");
        outInfoService.update(newInfo);

        /*~~~~~~~~~~~~~~添加收益记录~~~~~~~~~~~~~*/
        DealRecord dealRecord = new DealRecord();
        dealRecord.setOutInfoId(outInfo.getId());
        dealRecord.setAccountId(outInfo.getAccountId());
        dealRecord.setCustomerId(outInfo.getCustomerId());
        dealRecord.setTransactionNum(transactionNum);
        dealRecord.setCoinCode("BBGO");
        dealRecord.setDealType(1);
        dealRecord.setDealMoney(bbgoMoney);
        dealRecord.setRatio(platformPrice);
        dealRecord.setCodeValue(platformPrice);
        dealRecord.setRemark("4倍剩余USDT兑换成BBGO");
        dealRecord.setSaasid("111");
        dealRecordService.save(dealRecord);

        // 获取币账户信息--查redis缓存
        ExDigitalmoneyAccountRedis platExaccount = UserRedisUtils.getAccountRedis(outInfo.getCustomerId().toString(), platformCode);
        ExDigitalmoneyAccountRedis usdtExaccount = UserRedisUtils.getAccountRedis(outInfo.getCustomerId().toString(), "USDT");
        /*~~~~~~~~~~~~~~平台币账户添加币~~~~~~~~~~~~~*/
        List<Accountadd> list = new ArrayList<Accountadd>();
        // 热账户增加---分红（58）
        list.add(AccountUtil.getAccountAdd(platExaccount.getId(), new BigDecimal("+" + bbgoMoney), 1, 1,58,
                transactionNum));
        // 冻结减少
        list.add(AccountUtil.getAccountAdd(usdtExaccount.getId(), new BigDecimal("-" + outInfo.getBaseMoney()), 2, 1,58,
                transactionNum));
        messageProducer.toAccount(JSON.toJSONString(list));
    }

    @Override
    public void surplusBBGOExchange(Map<String, String> map) {
        String exchangeCode = map.get("exchangeCode"); // 要兑换的币种
        QueryFilter filter = new QueryFilter(ExDigitalmoneyAccount.class);
        filter.addFilter("coinCode=", "BBGO");
        filter.addFilter("coldMoney > ", 0);
        List<ExDigitalmoneyAccount> exAccount = exDigitalmoneyAccountService.find(filter);
        if (exAccount != null && exAccount.size() > 0) {
            for (ExDigitalmoneyAccount account : exAccount) {
                JSONObject paramMap = new JSONObject();
                paramMap.put("exAccount", account);
                paramMap.put("exchangeCode", exchangeCode);
                messageProducer.toSurplusBBGOAward(JSONObject.toJSONString(paramMap));
            }
        }
    }

    @Override
    public void mqSurplusBBGOExchange(String jsonMap) {
        JSONObject jsonObject = JSONObject.parseObject(jsonMap);
        ExDigitalmoneyAccount account =  JSONObject.parseObject(jsonObject.getString("exAccount"), ExDigitalmoneyAccount.class);
        // 兑换的币种
        String exchangeCode = jsonObject.get("exchangeCode").toString();
        // 兑换数量
        BigDecimal coldMoney = account.getColdMoney();
        // 当前平台币code
        String platformCode = RedisStaticUtil.getAppConfigValue(RulesConfig.RulesCoinKey, "coinCode");

        // 获取币账户信息--查redis缓存--平台币
        ExDigitalmoneyAccountRedis platExaccount = UserRedisUtils.getAccountRedis(account.getCustomerId().toString(), platformCode);
        // 获取币账户信息--查redis缓存--兑换币
        ExDigitalmoneyAccountRedis exchangeExaccount = UserRedisUtils.getAccountRedis(account.getCustomerId().toString(), exchangeCode);
        // 流水号
        String transactionNum = IdGenerate.transactionNum("EX");
        // 添加使用币兑出记录
        DealRecord sellRecord = new DealRecord();
        sellRecord.setCustomerId(account.getCustomerId());
        sellRecord.setAccountId(platExaccount.getId());
        sellRecord.setCodeValue(new BigDecimal("1"));
        sellRecord.setDealType(Integer.parseInt(DealEnum.TYPE12.getIndex()));
        sellRecord.setDealMoney(coldMoney);
        sellRecord.setTransactionNum(transactionNum);
        sellRecord.setCoinCode(platformCode);
        sellRecord.setRemark(DealEnum.SITE12.getName());
        sellRecord.setSaasid("222");
        dealRecordService.save(sellRecord);

        // 添加兑换币兑入记录
        DealRecord buyRecord = new DealRecord();
        buyRecord.setCustomerId(account.getCustomerId());
        buyRecord.setAccountId(exchangeExaccount.getId());
        buyRecord.setCodeValue(new BigDecimal("1"));
        buyRecord.setDealType(Integer.parseInt(DealEnum.TYPE7.getIndex()));
        buyRecord.setDealMoney(coldMoney);
        buyRecord.setTransactionNum(transactionNum);
        buyRecord.setCoinCode(exchangeCode);
        buyRecord.setRemark(DealEnum.SITE7.getName());
        buyRecord.setSaasid("333");
        dealRecordService.save(buyRecord);

        /*~~~~~~~~~~~~~~平台币账户添加币~~~~~~~~~~~~~*/
        List<Accountadd> list = new ArrayList<Accountadd>();
        // 热账户增加 BCT
        list.add(AccountUtil.getAccountAdd(exchangeExaccount.getId(), new BigDecimal("+" + coldMoney), 1, 1,58,
                transactionNum));
        // 冷账户减少 BBGO
        list.add(AccountUtil.getAccountAdd(platExaccount.getId(), new BigDecimal("-" + coldMoney), 2, 1,58,
                transactionNum));
        messageProducer.toAccount(JSON.toJSONString(list));

    }

    @Override
    public void surplusUSDTExchange(String customerId) {
        QueryFilter filter = new QueryFilter(OutInfo.class);
        filter.addFilter("status=",0);
        filter.addFilter("customerId=",customerId);
        // 查询所有未出局的用户
        OutInfo outInfo = outInfoService.get(filter);
        if (outInfo != null) {
            JSONObject paramMap = new JSONObject();
            paramMap.put("outInfo",outInfo);
            messageProducer.toSurplusUSDTAward(JSONObject.toJSONString(paramMap));
        }
    }

    @Override
    public void surplusBBGOExchange(String customerId, String exchangeCode) {
        QueryFilter filter = new QueryFilter(ExDigitalmoneyAccount.class);
        filter.addFilter("coinCode=", "BBGO");
        filter.addFilter("coldMoney > ", 0);
        filter.addFilter("customerId = ", customerId);
        ExDigitalmoneyAccount exAccount = exDigitalmoneyAccountService.get(filter);
        if (exAccount != null) {
            JSONObject paramMap = new JSONObject();
            paramMap.put("exAccount", exAccount);
            paramMap.put("exchangeCode", exchangeCode);
            messageProducer.toSurplusBBGOAward(JSONObject.toJSONString(paramMap));
        }
    }

    @Override
    public void surplusUSDTExchangeTwo() {
        QueryFilter filter = new QueryFilter(OutInfo.class);
        filter.addFilter("status=",1);
        filter.addFilter("saasid= ", "111");
        // 查询所有已出局的用户
        List<OutInfo> outInfoList = outInfoService.find(filter);
        if (outInfoList != null && outInfoList.size() > 0) {
            for (OutInfo outInfo : outInfoList) {
                JSONObject paramMap = new JSONObject();
                paramMap.put("outInfo",outInfo);
                messageProducer.toSurplusUSDTAwardTwo(JSONObject.toJSONString(paramMap));
            }
        }

    }

    @Override
    public void surplusBBGOExchangeTwo(Map<String, String> map) {
        String exchangeCode = map.get("exchangeCode"); // 要兑换的币种
        QueryFilter filter = new QueryFilter(DealRecord.class);
        filter.addFilter("dealType=", 7);
        filter.addFilter("coinCode=", exchangeCode);
        filter.addFilter("saasid=", "333");
        List<DealRecord> recordList = dealRecordService.find(filter);
        if (recordList != null && recordList.size() > 0) {
            for (DealRecord record : recordList) {
                JSONObject paramMap = new JSONObject();
                paramMap.put("dealRecord", record);
                paramMap.put("exchangeCode", exchangeCode);
                messageProducer.toSurplusBBGOAwardTwo(JSONObject.toJSONString(paramMap));
            }
        }

    }

    @Override
    public void mqSurplusUSDTExchangeTwo(String jsonMap) {
        JSONObject jsonObject = JSONObject.parseObject(jsonMap);
        OutInfo outInfo =  JSONObject.parseObject(jsonObject.getString("outInfo"), OutInfo.class);
        // 当前平台币code
        String platformCode = RedisStaticUtil.getAppConfigValue(RulesConfig.RulesCoinKey, "coinCode");
        // 当前平台币价格
        String value = RedisStaticUtil.getAppConfigValue(RulesConfig.RulesCoinKey, "issuePrice");
        BigDecimal platformPrice = new BigDecimal(value);
        // 兑换数量
        BigDecimal bbgoMoney = outInfo.getHotMoney().divide(platformPrice, 8, BigDecimal.ROUND_HALF_UP);
        // 流水号
        String transactionNum = IdGenerate.transactionNum("XO");
        // 更新outInfo
        OutInfo info = new OutInfo();
        info.setId(outInfo.getId());
        info.setStatus(0);
        info.setSaasid("");
        outInfoService.update(info);
        // 获取币账户信息--查redis缓存
        ExDigitalmoneyAccountRedis platExaccount = UserRedisUtils.getAccountRedis(outInfo.getCustomerId().toString(), platformCode);
        ExDigitalmoneyAccountRedis usdtExaccount = UserRedisUtils.getAccountRedis(outInfo.getCustomerId().toString(), "USDT");
        /*~~~~~~~~~~~~~~平台币账户添加币~~~~~~~~~~~~~*/
        List<Accountadd> list = new ArrayList<Accountadd>();
        // 热账户减少---分红（58）
        list.add(AccountUtil.getAccountAdd(platExaccount.getId(), new BigDecimal("-" + bbgoMoney), 1, 1,58,
                transactionNum));
        // 冻结增加
        list.add(AccountUtil.getAccountAdd(usdtExaccount.getId(), new BigDecimal("+" + outInfo.getBaseMoney()), 2, 1,58,
                transactionNum));
        messageProducer.toAccount(JSON.toJSONString(list));
    }

    @Override
    public void mqSurplusBBGOExchangeTwo(String jsonMap) {
        JSONObject jsonObject = JSONObject.parseObject(jsonMap);
        // 兑换的币种
        String exchangeCode = jsonObject.get("exchangeCode").toString();
        DealRecord dealRecord =  JSONObject.parseObject(jsonObject.getString("dealRecord"), DealRecord.class);
        DealRecord record = new DealRecord();
        record.setId(dealRecord.getId());
        record.setSaasid("");
        dealRecordService.update(record);
        // 当前平台币code
        String platformCode = RedisStaticUtil.getAppConfigValue(RulesConfig.RulesCoinKey, "coinCode");
        // 获取币账户信息--查redis缓存--平台币
        ExDigitalmoneyAccountRedis platExaccount = UserRedisUtils.getAccountRedis(dealRecord.getCustomerId().toString(), platformCode);
        // 获取币账户信息--查redis缓存--兑换币
        ExDigitalmoneyAccountRedis exchangeExaccount = UserRedisUtils.getAccountRedis(dealRecord.getCustomerId().toString(), exchangeCode);
        // 流水号
        String transactionNum = IdGenerate.transactionNum("XE");
        /*~~~~~~~~~~~~~~平台币账户添加币~~~~~~~~~~~~~*/
        List<Accountadd> list = new ArrayList<Accountadd>();
        // 热账户减少 BCT
        list.add(AccountUtil.getAccountAdd(exchangeExaccount.getId(), new BigDecimal("-" + dealRecord.getDealMoney()), 1, 1,58,
                transactionNum));
        // 冷账户增加 BBGO
        list.add(AccountUtil.getAccountAdd(platExaccount.getId(), new BigDecimal("+" + dealRecord.getDealMoney()), 2, 1,58,
                transactionNum));
        messageProducer.toAccount(JSON.toJSONString(list));
    }

    @Override
    public void surplusUSDTExchangeTwo(String customerId) {
        QueryFilter filter = new QueryFilter(OutInfo.class);
        filter.addFilter("status=",1);
        filter.addFilter("customerId=",customerId);
        filter.addFilter("saasid=","111");
        // 查询所有已出局出局的用户
        OutInfo outInfo = outInfoService.get(filter);
        if (outInfo != null) {
            JSONObject paramMap = new JSONObject();
            paramMap.put("outInfo",outInfo);
            messageProducer.toSurplusUSDTAwardTwo(JSONObject.toJSONString(paramMap));
        }
    }

    @Override
    public void surplusBBGOExchangeTwo(String customerId, String exchangeCode) {
        QueryFilter filter = new QueryFilter(DealRecord.class);
        filter.addFilter("customerId=", customerId);
        filter.addFilter("dealType=", 7);
        filter.addFilter("coinCode=", exchangeCode);
        filter.addFilter("saasid=", "333");
        DealRecord record = dealRecordService.get(filter);
        if (record != null) {
            JSONObject paramMap = new JSONObject();
            paramMap.put("dealRecord", record);
            paramMap.put("exchangeCode", exchangeCode);
            messageProducer.toSurplusBBGOAwardTwo(JSONObject.toJSONString(paramMap));
        }
    }

    /**
     * 获取当前天比例
     * */
    private BigDecimal getAwardRatio() {
        BigDecimal ratio = new BigDecimal("0");
        String configkey = "";
        SimpleDateFormat dateFm = new SimpleDateFormat("EEEE", Locale.CHINA);
        String dateStr = dateFm.format(new Date());
        switch (dateStr) {
            case "星期一" :
                configkey = "mondayGainsRatio";
                break;
            case "星期二":
                configkey = "tuesdayGainsRatio";
                break;
            case "星期三":
                configkey = "wednesdayGainsRatio";
                break;
            case "星期四":
                configkey = "thursdayGainsRatio";
                break;
            case "星期五":
                configkey = "fridayGainsRatio";
                break;
            case "星期六":
                configkey = "saturdayGainsRatio";
                break;
            case "星期日":
                configkey = "weekdayGainsRatio";
                break;
        }
        String jsonStr = redisService.get(StringConstant.CONFIG_CACHE + ":" + RulesConfig.StaticGainsKey);
        JSONArray obj = JSON.parseArray(jsonStr);
        for(Object o:obj) {
            JSONObject oo = JSON.parseObject(o.toString());
            if (oo.getString("configkey").equals(configkey)) {
                ratio = new BigDecimal(oo.getString("value"));
                break;
            }
        }
        return ratio;
    }

    /**
     * 发放见点奖
     * */
    @Override
    public void giveOutSeeAward() {
        Date startDate = new Date();
        /*~~~~~~~~~~~~~~~~~~~~~~~~~见点奖发放--start~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
        // 获取平台设置的最高代数---见点奖
        String tierNum = RedisStaticUtil.getAppConfigValue(RulesConfig.SeeAwardKey, "maxNumberPlies");
        // 获取见点奖发放比例
        String ratio = RedisStaticUtil.getAppConfigValue(RulesConfig.SeeAwardKey, "awardRatio");
        BigDecimal awardRatio = new BigDecimal(ratio).divide(new BigDecimal("100"));
        // 获取新增转入的用户数---充币转入+用户理财
        Map<String, Object> param = new HashMap<>(2);
        int[] dealTypes = {8,13};
        param.put("dealTypes", dealTypes);
        param.put("dateType", "yesterday");
        List<DealRecord> recordList = dealRecordService.findNewDealRecord(param);
        // 获取能发送见点奖的父级有效用户
        JSONObject paramMap = new JSONObject();
        Map<String,String> map = new HashMap<>(2);
        for (DealRecord dealRecord : recordList) {
            paramMap.clear();
            paramMap.put("customerId",dealRecord.getCustomerId().toString());
            paramMap.put("tierNum",tierNum);
            paramMap.put("awardRatio", awardRatio);
            paramMap.put("actualMoney", dealRecord.getNewShiftToMoney());
            messageProducer.toOutSeeAward(JSONObject.toJSONString(paramMap));
        }
        /*~~~~~~~~~~~~~~~~~~~~~~~~~见点奖发放--end~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
        Date endDate = new Date();
        /*~~~~~~~~~~~~~定时器执行日志---添加~~~~~~~~~~*/
        taskLogService.saveLog("giveOutSeeAward", 0, startDate, endDate);
    }

    @Override
    public void mqOutSeeAward(String jsonMap) {
        if (jsonMap == null || "".equals(jsonMap)) {
            return;
        }
        JSONObject jsonObject = JSONObject.parseObject(jsonMap);
        String customerId = jsonObject.getString("customerId");
        String tierNum = jsonObject.getString("tierNum");
        // 预计基数---新增投资金额
        BigDecimal actualMoney = new BigDecimal(jsonObject.getString("actualMoney"));
        BigDecimal awardRatio = new BigDecimal(jsonObject.getString("awardRatio"));

        Map<String, String> paramMap = new HashMap<>(2);
        Map<String,String> map = new HashMap<>(2);
        paramMap.put("customerId", customerId);
        paramMap.put("tierNum", tierNum);
        List<OutInfo> outInfoList = outInfoService.findParentUserList(paramMap);
        for (OutInfo outInfo : outInfoList) {
            // 校验是否开启动态奖励
            if (!isOpenDynamic(outInfo.getCustomerId())) {
                continue;
            }
            // 需求父级直推达到一定人数才能获取该人的见点奖（直推几人拿几代）
            map.clear();
            map.put("customerId",outInfo.getCustomerId().toString());
            map.put("level", "1");
            List<OutInfo> sonOutInfo = outInfoService.findSonUserList(map);
            // 直推人数
            int directNum = sonOutInfo != null?sonOutInfo.size() : 0;
            if (directNum >= outInfo.getLevel()) {
                String site = DealEnum.SITE2.getIndex();
                String type = DealEnum.TYPE2.getIndex();
                /*~~~~~~~~~~~~~~~~~~~~创建收益相关业务(重要)~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
                outInfoService.createBusiness(outInfo, actualMoney,
                        awardRatio, site, type);
            }
        }
    }

    /**
     * 发放管理奖
     * */
    @Override
    public void giveOutManageAward() {
        Date startDate = new Date();
        /*~~~~~~~~~~~~~~~~~~~~~~~~~管理奖发放--start~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
        // 获取平台设置的代数---管理奖
        String tierNum = RedisStaticUtil.getAppConfigValue(RulesConfig.ManageAwardKey, "maxNumberPlies");
        // 获取一代发放比例
        String oneRatio = RedisStaticUtil.getAppConfigValue(RulesConfig.ManageAwardKey, "oneAwardRatio");
        BigDecimal oneAwardRatio = new BigDecimal(oneRatio).divide(new BigDecimal("100"));
        // 获取二代发放比例
        String twoRatio = RedisStaticUtil.getAppConfigValue(RulesConfig.ManageAwardKey, "twoAwardRatio");
        BigDecimal twoAwardRatio = new BigDecimal(twoRatio).divide(new BigDecimal("100"));
        // 获取三点代到设置代数发放比例
        String threeRatio = RedisStaticUtil.getAppConfigValue(RulesConfig.ManageAwardKey, "threeAwardRatio");
        BigDecimal threeAwardRatio = new BigDecimal(threeRatio).divide(new BigDecimal("100"));
        // 获取新增静态收益的用户数
        Map<String, Object> param = new HashMap<>(2);
        param.put("dealType", "1");
        param.put("dateType", "today");
        List<DealRecord> recordList = dealRecordService.findNewDealRecord(param);
        JSONObject paramMap = new JSONObject();
        Map<String,String> map = new HashMap<>(2);
        for (DealRecord dealRecord : recordList) {
            paramMap.clear();
            paramMap.put("customerId",dealRecord.getCustomerId().toString());
            paramMap.put("tierNum",tierNum);
            paramMap.put("oneAwardRatio", oneAwardRatio);
            paramMap.put("twoAwardRatio", twoAwardRatio);
            paramMap.put("threeAwardRatio", threeAwardRatio);
            paramMap.put("actualMoney", dealRecord.getNewShiftToMoney());
            messageProducer.toOutManageAward(JSONObject.toJSONString(paramMap));
        }
        /*~~~~~~~~~~~~~~~~~~~~~~~~~管理奖发放--end~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
        Date endDate = new Date();
        /*~~~~~~~~~~~~~定时器执行日志---添加~~~~~~~~~~*/
        taskLogService.saveLog("giveOutManageAward", 0, startDate, endDate);
    }

    /*
    * 指定用户发送管理奖
    * */
    @Override
    public void giveOutManageAward(String startTime) {
        Date startDate = new Date();
        /*~~~~~~~~~~~~~~~~~~~~~~~~~管理奖发放--start~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
        // 获取平台设置的代数---管理奖
        String tierNum = RedisStaticUtil.getAppConfigValue(RulesConfig.ManageAwardKey, "maxNumberPlies");
        // 获取一代发放比例
        String oneRatio = RedisStaticUtil.getAppConfigValue(RulesConfig.ManageAwardKey, "oneAwardRatio");
        BigDecimal oneAwardRatio = new BigDecimal(oneRatio).divide(new BigDecimal("100"));
        // 获取二代发放比例
        String twoRatio = RedisStaticUtil.getAppConfigValue(RulesConfig.ManageAwardKey, "twoAwardRatio");
        BigDecimal twoAwardRatio = new BigDecimal(twoRatio).divide(new BigDecimal("100"));
        // 获取三点代到设置代数发放比例
        String threeRatio = RedisStaticUtil.getAppConfigValue(RulesConfig.ManageAwardKey, "threeAwardRatio");
        BigDecimal threeAwardRatio = new BigDecimal(threeRatio).divide(new BigDecimal("100"));
        // 获取新增静态收益的用户数
        QueryFilter filter = new QueryFilter(DealRecord.class);
        filter.addFilter("dealType=", 1);
        filter.addFilter("created >", startTime);
        List<DealRecord> recordList = dealRecordService.find(filter);
        JSONObject paramMap = new JSONObject();
        Map<String,String> map = new HashMap<>(2);
        for (DealRecord dealRecord : recordList) {
            paramMap.clear();
            paramMap.put("customerId",dealRecord.getCustomerId().toString());
            paramMap.put("tierNum",tierNum);
            paramMap.put("oneAwardRatio", oneAwardRatio);
            paramMap.put("twoAwardRatio", twoAwardRatio);
            paramMap.put("threeAwardRatio", threeAwardRatio);
            paramMap.put("actualMoney", dealRecord.getDealMoney());
            messageProducer.toOutManageAward(JSONObject.toJSONString(paramMap));
        }
        /*~~~~~~~~~~~~~~~~~~~~~~~~~管理奖发放--end~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
        Date endDate = new Date();
        /*~~~~~~~~~~~~~定时器执行日志---添加~~~~~~~~~~*/
        taskLogService.saveLog("giveOutManageAward", 0, startDate, endDate);
    }

    @Override
    public void mqOutManageAward(String jsonMap) {
        if (jsonMap == null || "".equals(jsonMap)) {
            return;
        }
        JSONObject jsonObject = JSONObject.parseObject(jsonMap);
        String customerId = jsonObject.getString("customerId");
        String tierNum = jsonObject.getString("tierNum");
        BigDecimal oneAwardRatio = new BigDecimal(jsonObject.getString("oneAwardRatio"));
        BigDecimal twoAwardRatio = new BigDecimal(jsonObject.getString("twoAwardRatio"));
        BigDecimal threeAwardRatio = new BigDecimal(jsonObject.getString("threeAwardRatio"));
        BigDecimal actualMoney = new BigDecimal(jsonObject.getString("actualMoney"));
        Map<String, String> paramMap = new HashMap<>(2);
        Map<String,String> map = new HashMap<>(2);
        paramMap.put("customerId",customerId);
        paramMap.put("tierNum",tierNum);
        List<OutInfo> outInfoList = outInfoService.findParentUserListTwo(paramMap);
        List<List<OutInfo>> list = splitListForNum(outInfoList, 15);
        CompletableFuture[] futures = new CompletableFuture[15];
        for (int i = 0; i < list.size(); i++) {
            int finalI = i;
            futures[i] = CompletableFuture.supplyAsync(() -> execute(list.get(finalI),
                    oneAwardRatio, twoAwardRatio, threeAwardRatio,
                    actualMoney));
        }
        CompletableFuture.anyOf(futures).join();
        System.out.println(new Date()+"---mq管理奖消费成功");
    }

    /**
     * 多线程执行
     * */
    public Integer execute (List<OutInfo> outInfoList,
                            BigDecimal oneAwardRatio, BigDecimal twoAwardRatio,
                            BigDecimal threeAwardRatio, BigDecimal actualMoney) {
        Map<String,String> map = new HashMap<>(2);
        for (OutInfo outInfo : outInfoList) {
            // 校验是否开启动态奖励
            if (!isOpenDynamic(outInfo.getCustomerId())) {
                continue;
            }
            // 需求父级直推达到一定人数才能获取该人的管理（直推几人拿几代）
            map.clear();
            map.put("customerId",outInfo.getCustomerId().toString());
            map.put("level", "1");
            // 直推人数
            int directNum = outInfoService.getSonUserCount(map);
            if (directNum >= outInfo.getLevel()) {
                BigDecimal awardRatio;
                if (outInfo.getLevel() == 1) {
                    awardRatio = oneAwardRatio;
                } else if (outInfo.getLevel() == 2) {
                    awardRatio = twoAwardRatio;
                } else {
                    awardRatio = threeAwardRatio;
                }
                // 预计基数---新增静态收益
                //BigDecimal actualMoney = dealRecord.getNewShiftToMoney();
                String site = DealEnum.SITE3.getIndex();
                String type = DealEnum.TYPE3.getIndex();
                /*~~~~~~~~~~~~~~~~~~~~创建收益相关业务(重要)~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
                outInfoService.createBusiness(outInfo, actualMoney,
                        awardRatio, site, type);
            }
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
    /**
     * 发放级别奖
     * */
    @Override
    public void giveOutGradeAward() {
        Date startDate = new Date();
        // 获取配置的等级信息
        QueryFilter configFilter = new QueryFilter(LevelConfig.class);
        configFilter.setOrderby("sort asc");
        List<LevelConfig> configList = levelConfigService.find(configFilter);
        // 获取新增静态收益的用户数
        Map<String, Object> param = new HashMap<>(2);
        param.put("dealType", "1");
        param.put("dateType", "today");
        int countRecord = dealRecordService.getCountNewDealRecord(param);
        int rate = countRecord/pageSize;
        for (int i = 0; i <= rate; i++) {
            param.put("offset", i*pageSize);
            param.put("limit",pageSize);
            List<DealRecord> recordList = dealRecordService.findNewDealRecord(param);
            ThreadPool.exe(new Runnable() {
                @Override
                public void run() {
                    // 获取平台设置的代数---管理奖
                    String tierNum = RedisStaticUtil.getAppConfigValue(RulesConfig.ManageAwardKey, "maxNumberPlies");
                    Map<String, String> paramMap = new HashMap<>(2);
                    for (DealRecord dealRecord : recordList) {
                        paramMap.clear();
                        // 获取当前用户的等级
                        QueryFilter filter = new QueryFilter(CustomerLevel.class);
                        filter.addFilter("customerId=", dealRecord.getCustomerId().toString());
                        CustomerLevel customerLevel = customerLevelService.get(filter);
                        // 查询大于对应层数的父级用户
                        paramMap.put("customerId",dealRecord.getCustomerId().toString());
                        paramMap.put("upTierNum",tierNum);
                        List<OutInfo> outInfoList = outInfoService.findParentUserList(paramMap);
//                        for (OutInfo outInfo : outInfoList) {
//                            // 校验是否开启动态奖励
//                            if (!isOpenDynamic(outInfo.getCustomerId())) {
//                                continue;
//                            }
//                            // 获取当前用户的对应等级的分红比例 --- 平级以上则不能获得等级奖
//                            if (outInfo.getSort() > 0 && customerLevel.getSort() < outInfo.getSort()) {
//                                LevelConfig config = this.getConfig(outInfo.getSort(),configList);
//                                BigDecimal awardRatio = config.getLevelAward().divide(new BigDecimal("100"));
//                                // 预计基数---新增静态收益
//                                BigDecimal actualMoney = dealRecord.getNewShiftToMoney();
//                                String site = DealEnum.SITE4.getIndex();
//                                String type = DealEnum.TYPE4.getIndex();
//                                /*~~~~~~~~~~~~~~~~~~~~创建收益相关业务(重要)~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
//                                outInfoService.createBusiness(outInfo, actualMoney,
//                                        awardRatio, site, type);
//                            }
//                        }
                        /***************需求变更 ---- 新需求***************/
                        /** 加上团队概念 例： C1(v1)、B1(v2)、B2(v3)、B3(v1)、B4(v2)、B5(v3)
                         ** B1(v2)、B2(v3)、B3(v1)、B4(v2)、B5(v3) 是C1的父级 则只有B1和B2能拿到C1的级别奖，
                         ** 从B3往后都拿不到C1的级别奖（注：之前逻辑是只有B3拿不到C1的级别奖，B4和B5能拿到）
                         ***/
                        int maxSort = 0;
                        for (int i = 0; i < outInfoList.size(); i++) {
                            OutInfo outInfo = outInfoList.get(i);
                            // 校验是否开启动态奖励
                            if (!isOpenDynamic(outInfo.getCustomerId())) {
                                continue;
                            }
                            // 平级以上则不能获得等级奖 ---带烧伤
                            if (outInfo.getSort() > 0 && customerLevel.getSort() < outInfo.getSort()) {
                                /*if (i != 0) {
                                    // 获取前一个父的信息
                                    OutInfo upOutInfo = outInfoList.get(i-1);
                                    if (upOutInfo.getSort() >= outInfo.getSort()) {
                                        continue;
                                    } else {
                                        if (outInfo.getSort() > maxSort) {
                                            maxSort = outInfo.getSort();
                                        } else {
                                            continue;
                                        }
                                    }
                                }*/
                                if (i != 0) {
                                    // 获取前一个父的信息
                                    OutInfo upOutInfo = outInfoList.get(i-1);
                                    if (upOutInfo.getSort() >= outInfo.getSort()) {
                                        break;
                                    }
                                }
                                //单前父级用户等级比例
                                LevelConfig fConfig = getConfig(outInfo.getSort(),configList);
                                BigDecimal awardRatio = fConfig.getLevelAward().divide(new BigDecimal("100"));
                                // 基数用户等级比例
                                BigDecimal bRatio = new BigDecimal("0");
                                if (customerLevel.getSort() != 0) {
                                    LevelConfig bConfig = getConfig(customerLevel.getSort(),configList);
                                    bRatio = bConfig.getLevelAward().divide(new BigDecimal("100"));
                                    // 防止出现负数
                                    if (awardRatio.subtract(bRatio).compareTo(BigDecimal.ZERO) == 1) {
                                        awardRatio = awardRatio.subtract(bRatio);
                                    }
                                }
                                // 预计基数---新增静态收益
                                BigDecimal actualMoney = dealRecord.getNewShiftToMoney();
                                String site = DealEnum.SITE4.getIndex();
                                String type = DealEnum.TYPE4.getIndex();
                                /*~~~~~~~~~~~~~~~~~~~~创建收益相关业务(重要)~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
                                outInfoService.createBusiness(outInfo, actualMoney,
                                        awardRatio, site, type);
                                /*System.out.println("级别奖来源---提供者："+dealRecord.getCustomerId().toString()+"---收益人："+outInfo.getCustomerId()+
                                    "---收益基数："+actualMoney+"---收益比例："+awardRatio+"---时间："+ new Date());*/
                            }
                        }
                    }
                }
            });
        }
        Date endDate = new Date();
        /*~~~~~~~~~~~~~定时器执行日志---添加~~~~~~~~~~*/
        taskLogService.saveLog("giveOutGradeAward", 0, startDate, endDate);
    }

    /**
     * 社区奖励周释放
     * */
    @Override
    public void giveOutWeekTeamAward() {
        Date startDate = new Date();
        List<TeamLevelConfig> configList = teamLevelConfigService.findAll();
        List<OutInfo> outInfoList = outInfoService.findGiveOutTeamAwardList();
        // 当前平台币code
        for (OutInfo outInfo : outInfoList) {
            // 校验是否开启动态奖励
            if (!isOpenDynamic(outInfo.getCustomerId())) {
                continue;
            }
            String site = DealEnum.SITE9.getIndex();
            String type = DealEnum.TYPE9.getIndex();
            // 获取团队等级
            Integer teamSort = outInfo.getTeamSort();
            TeamLevelConfig config = this.getTeamConfig(teamSort, configList);
            // 获取周释放比例
            BigDecimal ratio = config.getWeekGrantRatio().divide(new BigDecimal("100"));
            this.giveOutAward(outInfo, ratio, site, type);
        }
        Date endDate = new Date();
        /*~~~~~~~~~~~~~定时器执行日志---添加~~~~~~~~~~*/
        taskLogService.saveLog("giveOutWeekTeamAward", 0, startDate, endDate);
    }

    /**
     * 给指定用户发放周释放
     * */
    @Override
    public void giveOutWeekTeamAwardTwo(String customerIds) {
        Date startDate = new Date();
        String[] customerIdList = customerIds.split(",");
        List<TeamLevelConfig> configList = teamLevelConfigService.findAll();
        List<OutInfo> outInfoList = outInfoService.findGiveOutTeamAwardList();
        for (OutInfo outInfo : outInfoList) {
            boolean flag = false;
            for (String s : customerIdList) {
               if (s.equals(outInfo.getCustomerId().toString())) {
                   flag = true;
               }
            }
            if (!flag) { continue; }
            // 校验是否开启动态奖励
            if (!isOpenDynamic(outInfo.getCustomerId())) {
                continue;
            }
            String site = DealEnum.SITE9.getIndex();
            String type = DealEnum.TYPE9.getIndex();
            // 获取团队等级
            Integer teamSort = outInfo.getTeamSort();
            TeamLevelConfig config = this.getTeamConfig(teamSort, configList);
            // 获取周释放比例
            BigDecimal ratio = config.getWeekGrantRatio().divide(new BigDecimal("100"));
            this.giveOutAward(outInfo, ratio, site, type);
        }
        Date endDate = new Date();
        /*~~~~~~~~~~~~~定时器执行日志---添加~~~~~~~~~~*/
        taskLogService.saveLog("giveOutWeekTeamAwardTwo", 0, startDate, endDate);
    }

    /**
     * 社区奖励月释放
     * */
    @Override
    public void giveOutMonthTeamAward() {
        Date startDate = new Date();
        List<TeamLevelConfig> configList = teamLevelConfigService.findAll();
        List<OutInfo> outInfoList = outInfoService.findGiveOutTeamAwardList();
        // 当前平台币code
        for (OutInfo outInfo : outInfoList) {
            // 校验是否开启动态奖励
            if (!isOpenDynamic(outInfo.getCustomerId())) {
                continue;
            }
            String site = DealEnum.SITE10.getIndex();
            String type = DealEnum.TYPE10.getIndex();
            // 获取团队等级
            Integer teamSort = outInfo.getTeamSort();
            TeamLevelConfig config = this.getTeamConfig(teamSort, configList);
            // 获取周释放比例
            BigDecimal ratio = config.getMonthGrantRatio().divide(new BigDecimal("100"));
            this.giveOutAward(outInfo, ratio, site, type);
        }
        Date endDate = new Date();
        /*~~~~~~~~~~~~~定时器执行日志---添加~~~~~~~~~~*/
        taskLogService.saveLog("giveOutMonthTeamAward", 0, startDate, endDate);
    }
    /**
     * 社区奖励年释放
     * */
    @Override
    public void giveOutYearTeamAward() {
        Date startDate = new Date();
        List<TeamLevelConfig> configList = teamLevelConfigService.findAll();
        List<OutInfo> outInfoList = outInfoService.findGiveOutTeamAwardList();
        // 当前平台币code
        for (OutInfo outInfo : outInfoList) {
            // 校验是否开启动态奖励
            if (!isOpenDynamic(outInfo.getCustomerId())) {
                continue;
            }
            String site = DealEnum.SITE11.getIndex();
            String type = DealEnum.TYPE11.getIndex();
            // 获取团队等级
            Integer teamSort = outInfo.getTeamSort();
            TeamLevelConfig config = this.getTeamConfig(teamSort, configList);
            // 获取周释放比例
            BigDecimal ratio = config.getYearGrantRatio().divide(new BigDecimal("100"));
            this.giveOutAward(outInfo, ratio, site, type);
        }
        Date endDate = new Date();
        /*~~~~~~~~~~~~~定时器执行日志---添加~~~~~~~~~~*/
        taskLogService.saveLog("giveOutMonthTeamAward", 0, startDate, endDate);
    }

    /**
     * 更新奖励是否释放
     * 规则： 1、2 级社区，每月团队新增业绩要达到20%发放
     *       3、4 一直发放
     * */
    @Override
    public void giveOutTeamAwardRule() {
        Date startDate = new Date();
        // 规则更改---以自然月为单位更新亮灯规则
        // 1、筛选需要考核的用户---每月月底考核
        // startTime 开始奖励时间属于当前月，则用户不需考核，但需要更新这个月的业绩
        // 今天是否是这个月最后一天
        boolean isLastDay = this.getLastDayOfMonth(this.getDateMonth(new Date())).equals(DateUtil.getFormatDateTime(new Date(), "yyyy-MM-dd"));
        if (!isLastDay) {
            return;
        }
        List<TeamLevelConfig> configList = teamLevelConfigService.findAll();
        List<ReleaseRuleDetails> releaseRuleDetailsList = releaseRuleDetailsService.findReleaseRuleDetailsList();
        // 获取当前执行时间
        String thisDate = DateUtil.getFormatDateTime(new Date(), "yyyy-MM-dd");
        for (ReleaseRuleDetails ruleDetails : releaseRuleDetailsList) {
            // isExamine true 考核 false 不考核
            boolean isExamine = this.getDateMonth(ruleDetails.getStartTime()) != this.getDateMonth(new Date());
            if (isExamine) {
                JSONObject paramMap = new JSONObject();
                paramMap.put("details", ruleDetails);
                paramMap.put("configList", configList);
                paramMap.put("thisDate", thisDate);
                messageProducer.teamAwardRule(JSONObject.toJSONString(paramMap));
            }
        }
        Date endDate = new Date();
        /*~~~~~~~~~~~~~定时器执行日志---添加~~~~~~~~~~*/
        taskLogService.saveLog("giveOuTeamAwardRule", 0, startDate, endDate);
    }

    @Override
    public void mqTeamAwardRule(String jsonMap) {
        // 当前时间
        System.out.println("---当前亮灯规则执行时间:"+ DateUtil.getNewDate());
        JSONObject jsonObject = JSONObject.parseObject(jsonMap);
        String thisDate = jsonObject.getString("thisDate");
                // 用户规则基础信息
        ReleaseRuleDetails details =  JSONObject.parseObject(jsonObject.getString("details"),ReleaseRuleDetails.class);
        // 社区等级配置
        List<TeamLevelConfig> configList = JSONArray.parseArray(jsonObject.getString("configList"), TeamLevelConfig.class);
        // 判断个人资产是否达到释放标准
        QueryFilter filter = new QueryFilter(OutInfo.class);
        filter.addFilter("customerId=", details.getCustomerId());
        filter.addFilter("status=", 0);
        OutInfo outInfo = outInfoService.get(filter);
        if (outInfo == null) {
            return;
        }
        Map<String, String> map = new HashMap<>();
        QueryFilter queryFilter = new QueryFilter(CustomerLevel.class);
        queryFilter.addFilter("customerId=", details.getCustomerId());
        CustomerLevel level = customerLevelService.get(queryFilter);
        TeamLevelConfig config = this.getTeamConfig(level.getTeamSort(), configList);
        Boolean flag = false; //灯开关标识 关 false 开 true
        BigDecimal newAsset = null;
        if (outInfo.getBaseMoney().compareTo(config.getOwnAsset()) != -1) {
            // 1 2级 多出来条件
            if (1 == level.getTeamSort() || 2 == level.getTeamSort()) {
                map.clear();
                map.put("thisDate", thisDate);
                Long customerId = details.getCustomerId();
                String startTime = DateUtil.getFormatDateTime(details.getStartTime(),"yyyy-MM-dd") + " 00:00:00";
                map.put("customerId", customerId.toString());
               /* map.put("startTime", startTime);
                *//*重点记录：这块查的是团队所有人新增的业绩 不管团队下的人是否出局*//*
                DealRecord record = dealRecordService.findNewlyTeamAsset(map);*/
                // 团队本月业绩
                List<String> thisTime = getCurrentMonthTime(0);
                map.put("startTime", thisTime.get(0));
                map.put("endTime", thisTime.get(1));
                DealRecord record = dealRecordService.findDateTeamAssetTwo(map);
                // 团队上个月业绩
                // map.put("dateType", "lastMonth");
                List<String> lastTime = getCurrentMonthTime(-1);
                map.put("startTime", lastTime.get(0));
                map.put("endTime", lastTime.get(1));
                DealRecord lastRecord = dealRecordService.findDateTeamAssetTwo(map);
                // 现在起始团队业绩为上月业绩
                BigDecimal lastMonthTeamAward = lastRecord.getTeamAsset();
                if (record != null) {
                    System.out.println("~~~~~本月团队业绩:"+customerId.toString()+"---:"+record.getTeamAsset());
                    System.out.println("~~~~~上月团队业绩:"+customerId.toString()+"---:"+lastMonthTeamAward);
                    // 本月团队业绩
                    BigDecimal addTeamAsset = record.getTeamAsset();
                    newAsset = addTeamAsset;
                    // 上月业绩为0，新增业绩不为0, 则灯亮
                    if (lastMonthTeamAward.compareTo(BigDecimal.ZERO) == 0
                            && addTeamAsset.compareTo(BigDecimal.ZERO) > 0) {
                        flag = true;
                    } else if (addTeamAsset.compareTo(BigDecimal.ZERO) == 0) {
                        // 新增团队业绩为0，则灯灭
                        flag = false;
                    } else {
                        // 新增业绩是上月业绩的20%, 则灯亮
                        // 新增团队业绩百分比----基数*0.2=新增
                        BigDecimal percentage = config.getEveryMonthTeamRatio();
                        BigDecimal ratio = addTeamAsset.divide(lastMonthTeamAward,4,BigDecimal.ROUND_HALF_UP)
                                .multiply(new BigDecimal(100));
                        if (ratio.compareTo(percentage) > -1) {
                            flag = true;
                        }
                    }
                    // 疑虑： 之前规则，申请团队第一次通过，起始值StartTeamAward == 0
                    //       之后规则，申请团队，起始值为上个月业绩，
                    //       修改后规则，当前用户申请审核通过和等级升级后，当前月不考核
                    /*if (details.getStartTeamAward().compareTo(BigDecimal.ZERO) != 0) {

                    } else {
                        // 升级时存储的团队业绩为 0 则说明 是申请团队业绩进来
                        flag = true;
                    }*/
                }
            } else {
                flag = true;
            }
        }
        if (flag) {
            // 考核通过
            // 1 2级更新释放规则
            if (newAsset != null) {
                Date now = new Date();
                details.setStartTime(now);
                details.setEndTime(DateUtils.addDays(now, 31));
                details.setStartTeamAward(newAsset);
                details.setLastMonthTeamAward(newAsset);
                releaseRuleDetailsService.update(details);
            }
        } else {
            if (newAsset != null) {
                // 考核不通过
                details.setLastMonthTeamAward(newAsset);
                details.setStartTime(new Date());
                releaseRuleDetailsService.update(details);
            }
        }
        // 开启开关标识
        level.setIsTeamAward(flag ? 1 : 0);
        customerLevelService.update(level);
    }

    /**
     * 统一释放
     * */
    private void giveOutAward(OutInfo outInfo, BigDecimal ratio,String site, String type) {
        String platformCode = RedisStaticUtil.getAppConfigValue(RulesConfig.RulesCoinKey, "coinCode");
        // 获取释放比例
        BigDecimal awardRatio = ratio;
        // 将要发的奖励
        BigDecimal newAward = new BigDecimal("0");
        // 实际基数---社区奖励数量
        BigDecimal actualMoney = new BigDecimal("0");
        // 获取总的社区奖励数
        Map<String, Object> map = new HashMap<>();
        map.put("customerId", outInfo.getCustomerId());
        map.put("dealType", 5);
        actualMoney = dealRecordService.countDealMoney(map);
        if (actualMoney.compareTo(new BigDecimal("0")) == 0) {
            return;
        }

        /*~~~~~~~亮灯规则测试时使用~~~~~~~~*/
        /*// 校验奖励是否发放
        QueryFilter filter = new QueryFilter(DealRecord.class);
        filter.addFilter("customerId=", outInfo.getCustomerId());
        filter.addFilter("dealType=", 9);
        filter.addFilter("created >=", "2020-10-05 00:00:00");
        filter.addFilter("created <", "2020-10-05 23:59:59");
        DealRecord record = dealRecordService.get(filter);
        if (record != null) {
            return;
        }*/

        // 判断设区奖励是否发放完
        // 共建设区奖励(已释放的)--- 周释放、月释放、年释放
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("customerId", outInfo.getCustomerId());
        int[] releaseTypes = {9,10,11};
        paramMap.put("dealTypes", releaseTypes);
        BigDecimal releaseAward = dealRecordService.countDealMoney(paramMap);
        // 将要释放奖励
        newAward = actualMoney.multiply(awardRatio).setScale(8, BigDecimal.ROUND_HALF_UP);
        // 将要释放+已释放的奖励
        BigDecimal newReleaseAward = newAward.add(releaseAward);
        // 将要释放+已释放的奖励 >= 共建设区奖励
        if (newReleaseAward.compareTo(actualMoney) >-1) {
            // 即将释放的超过了总的 注：将剩下的全部释放 所以先除比例
            actualMoney = actualMoney.subtract(releaseAward).divide(awardRatio,8,BigDecimal.ROUND_HALF_UP);
            newAward = actualMoney.subtract(releaseAward);
        }
        /*~~~~~~~~~~~~~~~~~~~~创建收益相关业务(重要)~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
        JsonResult result = outInfoService.createBusiness(outInfo, actualMoney,
                awardRatio, site, type);
        if (result.getSuccess() && "1010".equals(result.getCode())) {
            // 校验是否出局---出局后实际释放社区奖励
            BigDecimal outMoney = new BigDecimal(result.getObj().toString()).multiply(awardRatio);
            newAward = outMoney;
        }
        // 获取币账户信息--查redis缓存
        ExDigitalmoneyAccountRedis platExaccount = UserRedisUtils.getAccountRedis(outInfo.getCustomerId().toString(),platformCode);
        List<Accountadd> list = new ArrayList<Accountadd>();
        // 流水号
        String transactionNum = IdGenerate.transactionNum(site);
        // 冷账户账户减少---分红（58）
        list.add(AccountUtil.getAccountAdd(platExaccount.getId(), new BigDecimal("-" + newAward), 2, 1,58,
                transactionNum));
        messageProducer.toAccount(JSON.toJSONString(list));

    }

    /**
     * 获取后台社区等级参数
     * */
    private TeamLevelConfig getTeamConfig(Integer sort, List<TeamLevelConfig> configList) {
        TeamLevelConfig config = null;
        for (TeamLevelConfig teamLevelConfig : configList) {
            if (sort.equals(teamLevelConfig.getTeamSort())) {
                config = teamLevelConfig;
                break;
            }
        }
        return config;
    }

    /**
     * 获取后台等级参数
     * */
    private LevelConfig getConfig(Integer sort, List<LevelConfig> configList) {
        LevelConfig config = null;
        for (LevelConfig levelConfig : configList) {
            if (sort.equals(levelConfig.getSort())) {
                config = levelConfig;
                break;
            }
        }
        return config;
    }

    /**~~~~~~~~~~~~~~~~~~~~~APP接口调用实现~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    /**
     * 收益统计
     * */
    @Override
    public JsonResult findAwardStatistics(Long customerId) {
        JSONObject obj = new JSONObject();
        Map<String, Object> paramMap = new HashMap<>();
        String objJson = redisService.get("APPAWARD:"+customerId);
        if (objJson != null && !"".equals(objJson)) {
            obj = JSONObject.parseObject(objJson);
        } else {
            obj = this.awardStatistics(customerId);
        }
        return new JsonResult(true).setObj(obj).setCode("1000");
    }

    /**
     * 收益明细
     * */
    @Override
    public FrontPage findAwardDetails(Map<String, String> map) {
        Page<DealRecord> page = PageFactory.getPage(map);
        Map<String, Object> params = new HashMap<>();
        int[] dealTypes = {1,2,3,4,9,10,11};
        params.put("customerId", map.get("customerId"));
        params.put("dealTypes",dealTypes);
        List<DealRecord> recordList = dealRecordService.findPageBySql(params);
        return new FrontPage(recordList, page.getTotal(), page.getPages(), page.getPageSize());
    }


    @Override
    public void saveTiBiRecord() {
        QueryFilter filter = new QueryFilter(DealRecord.class);
        filter.addFilter("dealType=", DealEnum.TYPE14.getIndex());
        filter.addFilter("dealStatus=", 1);
        List<DealRecord> recordList = dealRecordService.find(filter);
        for (DealRecord record : recordList) {
            QueryFilter filter1 = new QueryFilter(ExDmTransaction.class);
            filter1.addFilter("transactionNum=",record.getTransactionNum());
            filter1.addFilter("transactionType=",2);//(1充币 ，2提币)
            ExDmTransaction dm = dmTransactionService.get(filter1);
            // status 1待审核 2已完成 3以否决
            if (dm != null && dm.getStatus() == 2) {
                record.setDealStatus(2);
                dealRecordService.update(record);
            } else {
                if (dm != null && dm.getStatus() == 3) {
                    record.setDealStatus(3);
                    // 用户提币（状态码 dealType：14）---用户驳回理由
                    record.setRemark(dm.getRejectionReason());
                    dealRecordService.update(record);
                }
            }
        }
    }

    /**
     * 校验该用户是否开启动态收益
     * */
    private Boolean isOpenDynamic(Long customerId) {
        Boolean flag = true;
        AppCustomer appCustomer = appCustomerService.get(customerId);
        if (appCustomer.getIsGag() == 1) {
            flag = false;
        }
        return flag;
    }

    @Override
    public void saveChargeRecord() {
        synchronized (rlock) {
            String str = redisService.get(ChargeRecordKey);
            if (str == null) {
                str = "0";
            }
            QueryFilter filter = new QueryFilter(ExDmTransaction.class);
            filter.addFilter("status=",2); //'1待审核 2已完成 3以否决',
            filter.addFilter("id > ", str);
            filter.addFilter("optType=","4");
            filter.setPage(1);
            filter.setPageSize(2000);
            filter.setOrderby("id asc");
            Page<ExDmTransaction> pages = dmTransactionService.findPage(filter);
            List <ExDmTransaction> ls= pages.getResult();
            if(ls != null && ls.size() > 0){
                try {
                    for (ExDmTransaction ext : ls) {
                        str = ext.getId().toString();
                        Map<String, Object> recordMaps = new HashMap<String, Object>();
                        recordMaps.put("customerId", ext.getCustomerId());
                        recordMaps.put("accountId", ext.getAccountId());
                        recordMaps.put("coinCode", ext.getCoinCode());
                        recordMaps.put("money", ext.getTransactionMoney());
                        recordMaps.put("transactionNum", ext.getTransactionNum());
                        recordMaps.put("chargeType","2");
                        ThreadPool.exe(new ChargeRecordRunnable(recordMaps));
                    }
                } catch (Exception e){
                    e.printStackTrace();
                } finally {
                    redisService.save(ChargeRecordKey,str);
                }
            }
        }
    }

    // 统计平台资产
    @Override
    public void statsPlatformTotal() {
        Date startDate = new Date();
        // 平台总奖励BBGO数量
        BigDecimal allNum = new BigDecimal("0");
        // 今日新增BBGO数量
        BigDecimal todayAddNum = new BigDecimal("0");
        // 昨日总量（注：表示今天之前的总量, 总奖励BBGO数量 - 新增BBGO数量）
        BigDecimal ayerAllNum = new BigDecimal("0");
        // 平台总兑换量
        BigDecimal convertAllNum = new BigDecimal("0");
        // 今日新增兑换量
        BigDecimal todayConvertAddNum = new BigDecimal("0");
        // 昨日兑换量 (注：表示今天之前已兑换的数量， 平台总兑换量 - 今日新增兑换量)
        BigDecimal ayerConvertNum = new BigDecimal("0");
        // 昨日剩余总量 (注： 昨日总量 - 昨日兑换量)
        BigDecimal ayerSurplusNum = new BigDecimal("0");
        // 今日总量 （注：今日新增+昨日剩余量）
        BigDecimal todayAllNum = new BigDecimal("0");

        Map<String, Object> paramMap = new HashMap<>();
        int[] realTypes = {1,2,3,4,9,10,11};
        paramMap.put("dealTypes", realTypes);
        allNum = dealRecordService.countDealMoney(paramMap);

        paramMap.put("dateType","today");
        // 今日新增
        todayAddNum = dealRecordService.countDealMoney(paramMap);
        // 昨日总量
        ayerAllNum = allNum.subtract(todayAddNum);
        // 昨日兑换量
        paramMap.clear();
        paramMap.put("dealType", 12);
        convertAllNum = dealRecordService.countDealMoney(paramMap);
        paramMap.put("dateType","today");
        todayConvertAddNum = dealRecordService.countDealMoney(paramMap);
        // 昨日兑换量
        ayerConvertNum = convertAllNum.subtract(todayConvertAddNum);
        // 昨日剩余总量
        ayerSurplusNum = ayerAllNum.subtract(ayerConvertNum);
        // 今日总量
        todayAllNum = todayAddNum.add(ayerSurplusNum);

        QueryFilter filter = new QueryFilter(PlatformTotal.class);
        filter.setOrderby("id DESC");
        PlatformTotal total = platformTotalService.get(filter);
        // 平台币code
        String coinCode = RedisStaticUtil.getAppConfigValue(RulesConfig.RulesCoinKey, "coinCode");
        total = total == null ? new PlatformTotal() : total;
        total.setCoinCode(coinCode);
        total.setTodayAddNum(todayAddNum);
        total.setAyerAllNum(ayerAllNum);
        total.setAyerConvertNum(ayerConvertNum);
        total.setAyerSurplusNum(ayerSurplusNum);
        total.setTodayAllNum(todayAllNum);
        String today = DateUtil.getNewDate();
        if (total.getId() == null) {
            platformTotalService.save(total);
        } else {
            String created = DateUtil.dateToString(total.getCreated());
            if (created.split(" ")[0].equals(today.split(" ")[0])) {
                platformTotalService.update(total);
            } else {
                total.setId(null);
                platformTotalService.save(total);
            }
        }
        Date endDate = new Date();
        /*~~~~~~~~~~~~~定时器执行日志---添加~~~~~~~~~~*/
        taskLogService.saveLog("statsPlatformTotal", 0, startDate, endDate);
    }

    @Override
    public JSONObject awardStatistics(Long customerId) {
        QueryFilter filter = new QueryFilter(OutInfo.class);
        filter.addFilter("customerId=", customerId);
        filter.addFilter("status=", 0);
        OutInfo outInfo = outInfoService.get(filter);
        long s1 = System.currentTimeMillis();
        JSONObject obj = new JSONObject();
        Map<String, Object> paramMap = new HashMap<>();
        // 累计收益（所有）--- 包含出局的
        obj.clear();
        paramMap.clear();
        paramMap.put("customerId", customerId);
        int[] realTypes = {1,2,3,4,9,10,11};
        paramMap.put("dealTypes", realTypes);
        BigDecimal realAward = dealRecordService.countDealMoney(paramMap);
        obj.put("realAward",realAward);
        // 共建设区奖励---总奖励
        paramMap.clear();
        paramMap.put("customerId", customerId);
        paramMap.put("dealType", 5);
        BigDecimal allTeamAward = dealRecordService.countDealMoney(paramMap);
        obj.put("allTeamAward",allTeamAward);
        // 共建设区奖励---已释放
        paramMap.clear();
        paramMap.put("customerId", customerId);
        int[] allReleaseTypes = {9,10,11};
        paramMap.put("dealTypes", allReleaseTypes);
        BigDecimal hasBeenReleasedAward = dealRecordService.countDealMoney(paramMap);
        obj.put("hasBeenReleasedAward",hasBeenReleasedAward);
        // 共建设区奖励---待释放
        BigDecimal toBeReleasedAward = hasBeenReleasedAward != null?allTeamAward.subtract(hasBeenReleasedAward):allTeamAward;
        obj.put("toBeReleasedAward",toBeReleasedAward);
        if (outInfo == null) {
            // 保存三小时
            redisService.save("APPAWARD:"+customerId, obj.toJSONString(),10800);
            return obj;
        }
        // 本局预估收益
        // 平台币价格
        String issuePrice = RedisStaticUtil.getAppConfigValue(RulesConfig.RulesCoinKey, "issuePrice");
        BigDecimal predictAward = outInfo.getAllMoney().divide(new BigDecimal(issuePrice),8, BigDecimal.ROUND_HALF_UP);
        obj.put("predictAward",predictAward);
        obj.put("baseMoney", outInfo.getBaseMoney());
        obj.put("outMultiple", outInfo.getOutMultiple());
        obj.put("issuePrice", issuePrice);

        /*// 本局累计收益
        paramMap.clear();
        paramMap.put("customerId", customerId);
        paramMap.put("outInfoId", outInfo.getId());
        int[] thisRealAwardTypes = {1,2,3,4,9,10,11};
        paramMap.put("dealTypes",thisRealAwardTypes);
        BigDecimal thisRealAward = dealRecordService.countDealMoney(paramMap);
        obj.put("thisRealAward",thisRealAward);*/

        // 本局累计消耗额度
        paramMap.clear();
        paramMap.put("customerId", customerId);
        paramMap.put("outInfoId", outInfo.getId());
        int[] thisRealAwardTypes = {1,2,3,4,9,10,11};
        paramMap.put("dealTypes",thisRealAwardTypes);
        BigDecimal thisActualMoney = dealRecordService.countActualMoney(paramMap);
        obj.put("thisActualMoney",thisActualMoney);

        // 本局静态收益
        paramMap.clear();
        paramMap.put("customerId", customerId);
        paramMap.put("dealType", 1);
        paramMap.put("outInfoId", outInfo.getId());
        BigDecimal staticAward = dealRecordService.countDealMoney(paramMap);
        obj.put("staticAward",staticAward);
        // 本局动态收益---见点奖、管理奖、级别奖
        paramMap.clear();
        paramMap.put("customerId", customerId);
        int[] dynamicTypes = {2,3,4};
        paramMap.put("dealTypes", dynamicTypes);
        paramMap.put("outInfoId", outInfo.getId());
        BigDecimal dynamicAward = dealRecordService.countDealMoney(paramMap);
        obj.put("dynamicAward",dynamicAward);

        // 本局共建设区奖励--- 周释放、月释放、年释放
        paramMap.clear();
        paramMap.put("customerId", customerId);
        int[] releaseTypes = {9,10,11};
        paramMap.put("dealTypes", releaseTypes);
        paramMap.put("outInfoId", outInfo.getId());
        BigDecimal releaseAward = dealRecordService.countDealMoney(paramMap);
        obj.put("releaseAward",releaseAward);

        // 本局静态收益---昨天收益
        paramMap.clear();
        paramMap.put("customerId", customerId);
        paramMap.put("outInfoId", outInfo.getId());
        paramMap.put("dateType","yesterday");
        paramMap.put("dealType", 1);
        BigDecimal yesterdayStaticAward = dealRecordService.countDealMoney(paramMap);
        obj.put("yesterdayStaticAward",yesterdayStaticAward);

        // 本局静态收益---今日收益
        paramMap.put("dateType","today");
        BigDecimal thisTodayStaticAward = dealRecordService.countDealMoney(paramMap);
        obj.put("thisTodayStaticAward",thisTodayStaticAward);

        // 本局静态收益---本月收益
        paramMap.put("dateType","thisMonth");
        List<String> thisTime = getCurrentMonthTime(0);
        paramMap.put("startTime", thisTime.get(0));
        paramMap.put("endTime", thisTime.get(1));
        BigDecimal thisMonthStaticAward = dealRecordService.countDealMoney(paramMap);
        obj.put("thisMonthStaticAward",thisMonthStaticAward);

        // 本局动态收益--见点奖
        paramMap.clear();
        paramMap.put("customerId", customerId);
        paramMap.put("outInfoId", outInfo.getId());
        paramMap.put("dealType", 2);
        BigDecimal dynamicSeeAward = dealRecordService.countDealMoney(paramMap);
        obj.put("dynamicSeeAward",dynamicSeeAward);

        // 本局动态收益--管理奖
        paramMap.put("dealType", 3);
        BigDecimal dynamicManageAward = dealRecordService.countDealMoney(paramMap);
        obj.put("dynamicManageAward",dynamicManageAward);

        // 本局动态收益--级别奖
        paramMap.put("dealType", 4);
        BigDecimal dynamicRankAward = dealRecordService.countDealMoney(paramMap);
        obj.put("dynamicRankAward",dynamicRankAward);

        // 本局动态收益--昨日收益（见点奖、管理奖、级别奖）
        paramMap.clear();
        paramMap.put("customerId", customerId);
        paramMap.put("outInfoId", outInfo.getId());
        int[] yesterdayDynamicTypes = {2,3,4};
        paramMap.put("dealTypes", yesterdayDynamicTypes);
        paramMap.put("dateType","yesterday");
        BigDecimal yesterdayDynamicAward = dealRecordService.countDealMoney(paramMap);
        obj.put("yesterdayDynamicAward",yesterdayDynamicAward);

        // 本局动态收益--今日收益（见点奖、管理奖、级别奖）
        paramMap.put("dateType","today");
        BigDecimal thisTodayDynamicAward = dealRecordService.countDealMoney(paramMap);
        obj.put("thisTodayDynamicAward",thisTodayDynamicAward);

        // 本局动态收益--本月收益（见点奖、管理奖、级别奖）
        paramMap.put("dateType","thisMonth");
        paramMap.put("startTime", thisTime.get(0));
        paramMap.put("endTime", thisTime.get(1));
        BigDecimal thisMonthDynamicAward = dealRecordService.countDealMoney(paramMap);
        obj.put("thisMonthDynamicAward",thisMonthDynamicAward);

        // 本局本周释放收益
        paramMap.clear();
        paramMap.put("customerId", customerId);
        paramMap.put("outInfoId", outInfo.getId());
        int[] types = {9,10,11};
        paramMap.put("dealTypes", types);
        paramMap.put("dateType","thisWeek");
        BigDecimal thisWeekAward = dealRecordService.countDealMoney(paramMap);
        obj.put("thisWeekAward",thisWeekAward);

        // 本局上月释放收益
        paramMap.put("dateType","lastMonth");
        List<String> lastTime = getCurrentMonthTime(0);
        paramMap.put("startTime", lastTime.get(0));
        paramMap.put("endTime", lastTime.get(1));
        BigDecimal lastMonthAward = dealRecordService.countDealMoney(paramMap);
        obj.put("lastMonthAward",lastMonthAward);

        // 本局本月释放收益
        paramMap.put("dateType","thisMonth");
        paramMap.put("startTime", thisTime.get(0));
        paramMap.put("endTime", thisTime.get(1));
        BigDecimal thisMonthAward = dealRecordService.countDealMoney(paramMap);
        obj.put("thisMonthAward",thisMonthAward);
        // 保存三小时
        redisService.save("APPAWARD:"+customerId, obj.toJSONString(), 10800);
        log.info("~~~~~app接口：统计我的收益共用时 ：" + (System.currentTimeMillis()-s1) + "毫秒");
        // 团队新增业绩
        BigDecimal newTeamPer = new BigDecimal("0");
        // 达标时间
        String reachDate = "";
        // 获取社区等级
//        QueryFilter userFilter = new QueryFilter(CustomerLevel.class);
//        userFilter.addFilter("customerId=", customerId);
//        CustomerLevel customerLevel = customerLevelService.get(userFilter);
//        if (customerLevel != null && customerLevel.getTeamSort() > 0 && customerLevel.getIsTeamAward() == 1) {
//            // 获取亮灯信息
//            QueryFilter ruleFiler = new QueryFilter(ReleaseRuleDetails.class);
//            ruleFiler.addFilter("customerId=", customerId);
//            ReleaseRuleDetails details = releaseRuleDetailsService.get(ruleFiler);
//            String startTime = DateUtil.getFormatDateTime(details.getStartTime(),"yyyy-MM-dd") + " 00:00:00";
//            Map<String, String> map = new HashMap<>();
//            map.put("customerId", customerId.toString());
//            map.put("startTime", startTime);
//            reachDate = startTime;
//            /*重点记录：这块查的是团队所有人新增的业绩 不管团队下的人是否出局*/
//            DealRecord record = dealRecordService.findNewlyTeamAsset(map);
//            if (record != null) {
//                newTeamPer = record.getTeamAsset();
//            }
//        }
        obj.put("newTeamPer",newTeamPer);
        obj.put("reachDate",reachDate);
        return obj;
    }

    @Override
    public void payAndExtractTotal() {
        usdtTotalService.payAndExtractTotal();
        /*~~~~~~~~~~~~~定时器执行日志---添加~~~~~~~~~~*/
        taskLogService.saveLog("payAndExtractTotal", 0, new Date(), new Date());
    }


    @Override
    public void mqUpdateOutInfo(String jsonMap) {
        if (jsonMap == null || "".equals(jsonMap)) {
            return;
        }
        JSONObject jsonObject = JSONObject.parseObject(jsonMap);
        String infoId = jsonObject.getString("infoId");
        // 是否出局
        String isOut = jsonObject.getString("isOut");
        // 实际额度
        String actual = jsonObject.getString("actualExpendLimit");
        BigDecimal actualExpendLimit = new BigDecimal(actual);
        OutInfo outInfo = outInfoService.get(Long.parseLong(infoId));
        OutInfo newInfo = new OutInfo();
        newInfo.setId(outInfo.getId());
        newInfo.setHotMoney(outInfo.getHotMoney().subtract(actualExpendLimit));
        newInfo.setColdMoney(outInfo.getColdMoney().add(actualExpendLimit));
        newInfo.setStatus(Integer.parseInt(isOut));
        outInfoService.update(newInfo);
    }

    /**
     * 获取月份的最后一天
     * */
    public String getLastDayOfMonth(int month) {
        Calendar calendar = Calendar.getInstance();
        // 设置月份
        calendar.set(Calendar.MONTH, month - 1);
        // 获取某月最大天数
        int lastDay=0;
        //2月的平年瑞年天数
        if(month==2) {
            lastDay = calendar.getLeastMaximum(Calendar.DAY_OF_MONTH);
        }else {
            lastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        }
        // 设置日历中月份的最大天数
        calendar.set(Calendar.DAY_OF_MONTH, lastDay);
        // 格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String lastMonthDay = sdf.format(calendar.getTime());
        return lastMonthDay;
    }

    /**
     * 获取自定时间月份
     * */
    private int getDateMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        //获得当前bai时间的月份，月份从0开始所以结果要加1
        int month = calendar.get(Calendar.MONTH) + 1;
        return month;
    }

    /**
     * 获取指定月份的开始和结束时间
     * month 月份 -1 上一个月 0 当前月 1 下个月
     * */
    private List<String> getCurrentMonthTime(int month) {
        List<String> str = new ArrayList<>();
        Calendar c=Calendar.getInstance();
        c.add(Calendar.MONTH, month);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
        String gtimelast = sdf.format(c.getTime()); //上月
        int lastMonthMaxDay=c.getActualMaximum(Calendar.DAY_OF_MONTH);
        c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), lastMonthMaxDay, 23, 59, 59);

        //按格式输出
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-01  00:00:00");
        String gtime1 = sdf2.format(c.getTime()); //上月第一天
        str.add(gtime1);
        String gtime = sdf.format(c.getTime()); //上月最后一天
        str.add(gtime);
        return str;
    }
    public static void main(String[] args) {
        /*Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        //获得当前bai时间的月份，月份从0开始所以结果要加1
        int month = calendar.get(Calendar.MONTH) + 1;
        System.out.println(DateUtil.getFormatDateTime(new Date(), "yyyy-MM-dd"));
        // SimpleDateFormat dateFm = new SimpleDateFormat("yyyy年MM月dd日 EEEE", Locale.ENGLISH);
        SimpleDateFormat dateFm = new SimpleDateFormat("EEEE", Locale.CHINA);
        System.out.println(dateFm.format(date));*/


    }
}
