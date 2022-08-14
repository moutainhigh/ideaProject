package hry.licqb.remote;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import hry.bean.FrontPage;
import hry.bean.JsonResult;
import hry.core.thread.ThreadPool;
import hry.exchange.product.model.ExProduct;
import hry.exchange.product.service.ExProductService;
import hry.front.redis.model.UserRedis;
import hry.licqb.award.RulesConfig;
import hry.licqb.award.model.OutInfo;
import hry.licqb.award.service.OutInfoService;
import hry.licqb.record.model.CustomerFreeze;
import hry.licqb.record.model.DealRecord;
import hry.licqb.record.service.CustomerFreezeService;
import hry.licqb.record.service.DealRecordService;
import hry.licqb.thread.AgainPutIntoRunnable;
import hry.licqb.thread.ChargeRecordRunnable;
import hry.licqb.util.AccountUtil;
import hry.licqb.util.DealEnum;
import hry.licqb.util.RedisStaticUtil;
import hry.mq.producer.service.MessageProducer;
import hry.redis.common.dao.RedisUtil;
import hry.redis.common.utils.RedisService;
import hry.trade.redis.model.Accountadd;
import hry.trade.redis.model.ExDigitalmoneyAccountRedis;
import hry.util.PageFactory;
import hry.util.QueryFilter;
import hry.util.date.DateUtil;
import hry.util.idgenerate.IdGenerate;
import hry.util.idgenerate.NumConstant;
import org.springframework.util.StringUtils;
import util.UserRedisUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author zhouming
 * @date 2019/8/30 14:20
 * @Version 1.0
 */
public class RemoteAssetServiceImpl implements RemoteAssetService {

    @Resource
    private MessageProducer messageProducer;
    @Resource
    private DealRecordService dealRecordService;
    @Resource
    private OutInfoService outInfoService;
    @Resource
    private RedisService redisService;
    @Resource
    private ExProductService exProductService;
    @Resource
    private CustomerFreezeService customerFreezeService;

    @Override
    public JsonResult getPlatformConfig() {
        JSONObject obj = new JSONObject();
        // 平台币名称
        String coinName = RedisStaticUtil.getAppConfigValue(RulesConfig.RulesCoinKey, "coinName");
        // 平台币code
        String coinCode = RedisStaticUtil.getAppConfigValue(RulesConfig.RulesCoinKey, "coinCode");
        // 平台币价格
        String issuePrice = RedisStaticUtil.getAppConfigValue(RulesConfig.RulesCoinKey, "issuePrice");
        // 提币开始时间
        String extractStartTime = RedisStaticUtil.getAppConfigValue(RulesConfig.ExtractTimeKey, "extractStartTime");
        // 提币结束时间
        String extractEndTime = RedisStaticUtil.getAppConfigValue(RulesConfig.ExtractTimeKey, "extractEndTime");
        // 提币次数
        String extractNum = RedisStaticUtil.getAppConfigValue(RulesConfig.ExtractTimeKey, "extractNum");
        // 最小投资金额
        String minInvest = RedisStaticUtil.getAppConfigValue(RulesConfig.InvestRangeKey, "minInvest");
        // 最大投资金额
        String maxInvest = RedisStaticUtil.getAppConfigValue(RulesConfig.InvestRangeKey, "maxInvest");
        obj.put("coinName",coinName);
        obj.put("coinCode",coinCode);
        obj.put("issuePrice",issuePrice);
        obj.put("extractStartTime",extractStartTime);
        obj.put("extractEndTime",extractEndTime);
        obj.put("extractNum",extractNum);
        obj.put("minInvest",minInvest);
        obj.put("maxInvest",maxInvest);
        return new JsonResult(true).setObj(obj);
    }

    @Override
    public JsonResult getAccountByCoinCode(Long customerId, String coinCode) {
        JSONObject obj = new JSONObject();
        // 获得币账户
        ExDigitalmoneyAccountRedis exaccount = this.selectAccount(customerId, coinCode);
        if(exaccount != null){
            obj.put("hotMoney", exaccount.getHotMoney().compareTo(new BigDecimal(0))==0?new BigDecimal(0):exaccount.getHotMoney());
            obj.put("coldMoney", exaccount.getColdMoney().compareTo(new BigDecimal(0))==0?new BigDecimal(0):exaccount.getColdMoney());
        }
        return new JsonResult(true).setObj(obj);
    }

    @Override
    public JsonResult myAccount(Map<String, String> paramMap) {
        JSONObject obj = new JSONObject();
        // 用户id
        Long customerId = Long.valueOf(paramMap.get("customerId"));
        // 平台币code
        String coinCode = RedisStaticUtil.getAppConfigValue(RulesConfig.RulesCoinKey, "coinCode");
        // 平台币价格 多少USDT (目前获取指定的平台币汇率)
        String issuePrice = RedisStaticUtil.getAppConfigValue(RulesConfig.RulesCoinKey, "issuePrice");
        // 获取平台币币账户信息
        ExDigitalmoneyAccountRedis platExaccount = this.selectAccount(customerId, coinCode);
        // 获取平台币热账户
        BigDecimal platHotMoney = platExaccount.getHotMoney();
        // 获取平台币冷账户
        BigDecimal platColdMoney = platExaccount.getColdMoney();
        // 平台币个数
        BigDecimal platSum = platHotMoney.add(platColdMoney);

        //获取USDT币账户信息
        ExDigitalmoneyAccountRedis usdtExaccount = this.selectAccount(customerId, "USDT");
        // 获取USDT热账户
        BigDecimal usdtHotMoney = usdtExaccount.getHotMoney();
        // 获取USDT冷账户
        BigDecimal usdtColdMoney = usdtExaccount.getColdMoney();
        // USDT个数
        BigDecimal usdtSum = usdtHotMoney.add(usdtColdMoney);

        // 改用账户总资产
        BigDecimal totalAssets = usdtSum.add(platSum.multiply(new BigDecimal(issuePrice)));
        if(totalAssets == null){
            totalAssets = new BigDecimal(0);
        }
        obj.put("totalAssets",totalAssets);
        return new JsonResult(true).setObj(obj);
    }

    @Override
    public JsonResult againInvest(Map<String, String> paramMap) {
        // 理财时间校验
        // 理财开始时间
        String investStartTime = RedisStaticUtil.getAppConfigValue(RulesConfig.InvestTimeKey, "investStartTime");
        // 理财结束时间
        String investEndTime = RedisStaticUtil.getAppConfigValue(RulesConfig.InvestTimeKey, "investEndTime");
        // 当前时间
        String nowDate = DateUtil.dateToString(new Date()).split(" ")[1];
        Boolean time = hourMinuteBetween(nowDate, investStartTime, investEndTime);
        if (!time) {
            return new JsonResult(false).setMsg("qingzaizhidignshijianlicai");
        }
        String customerId = paramMap.get("customerId");
        boolean flag = redisService.lock("AGAININVEST:"+customerId);
        if (!flag) {
            redisService.unLock("AGAININVEST:"+customerId);
            return new JsonResult(false).setMsg("req_error"); //操作失败
        }
        // 校验账户是否正确冻结
        CustomerFreeze freeze = customerFreezeService.getCustomerFreeze(Long.parseLong(customerId));
        if (freeze != null) {
            redisService.unLock("AGAININVEST:"+customerId);
            // 异步平账
            ThreadPool.exe(new AgainPutIntoRunnable(Long.parseLong(customerId)));
            return new JsonResult(false).setMsg("zhanghuzijinyichang"); // 账户资金异常
        }
        // 投资金额
        String investNum = paramMap.get("investNum");
        if (new BigDecimal(investNum).compareTo(BigDecimal.ZERO) == -1) {
            redisService.unLock("AGAININVEST:"+customerId);
            return new JsonResult(false).setMsg("qingshuruzhengquetouzijine");
        }
        // 校验投资金额是否合法（USDT）
        ExDigitalmoneyAccountRedis usdtAccount = this.selectAccount(Long.parseLong(customerId),"USDT");
        if (new BigDecimal(investNum).compareTo(usdtAccount.getHotMoney()) == 1) {
            redisService.unLock("AGAININVEST:"+customerId);
            return new JsonResult(false).setMsg("zhanghuyuebuzu");
        }

        // 获取最小理财金额
        String minManageMoney = RedisStaticUtil.getAppConfigValue(RulesConfig.InvestRangeKey, "minManageMoney");
        if (new BigDecimal(investNum).compareTo(new BigDecimal(minManageMoney)) == -1) {
            redisService.unLock("AGAININVEST:"+customerId);
            return new JsonResult(false).setMsg("jinediyuzuixiaolicaishu");
        }
        // 理财数应是最小理财倍数
        BigDecimal multiple = new BigDecimal(investNum).divide(new BigDecimal(minManageMoney),8,BigDecimal.ROUND_HALF_UP);
        if (new BigDecimal(multiple.intValue()).compareTo(multiple) != 0 ) {
            redisService.unLock("AGAININVEST:"+customerId);
            return new JsonResult(false).setMsg("licaibeishu");
        }

        QueryFilter filter = new QueryFilter(OutInfo.class);
        filter.addFilter("customerId=", customerId);
        OutInfo outInfo = outInfoService.get(filter);
        // 获取最大投资额
        String maxInvest = RedisStaticUtil.getAppConfigValue(RulesConfig.InvestRangeKey, "maxInvest");
        if (outInfo != null) {
            if (new BigDecimal(investNum).add(outInfo.getBaseMoney()).compareTo(new BigDecimal(maxInvest)) == 1) {
                redisService.unLock("AGAININVEST:"+customerId);
                return new JsonResult(false).setMsg("touzijinechaoxian");
            }
        }
        // 理财USDT (业务：只能投资USDT)
        String transactionNum = NumConstant.Ex_Dm_Transaction;
        Map<String, Object> maps = new HashMap<String, Object>();
        maps.put("customerId", Long.valueOf(customerId));
        maps.put("accountId", usdtAccount.getId());
        maps.put("coinCode", "USDT");
        maps.put("money", investNum);
        maps.put("transactionNum", IdGenerate.transactionNum(transactionNum));
        maps.put("dealType", DealEnum.TYPE13.getIndex());
        maps.put("dealRemark", DealEnum.SITE13.getName());
        outInfoService.putIntoStorageMoney(maps);
        redisService.unLock("AGAININVEST:"+customerId);
        return new JsonResult(true).setCode("8888").setMsg("futouchenggong");
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
    public JsonResult findexchangeData(Long customerId) {
        JSONObject obj = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        // 获取平台币
        String platCode = RedisStaticUtil.getAppConfigValue(RulesConfig.RulesCoinKey, "coinCode");
        // 获取币种资料集合
        QueryFilter queryFilter = new QueryFilter(ExProduct.class);
        queryFilter.addFilter("issueState=","1");
        queryFilter.setOrderby("coinCode asc");
        List<ExProduct> productList = exProductService.find(queryFilter);
        for (ExProduct exProduct : productList) {
            JSONObject object = new JSONObject();
            String price = redisService.hget("hry:coinPrice", exProduct.getCoinCode());
            object.put("coinCode",exProduct.getCoinCode());
            object.put("price", price);
            jsonArray.add(object);
        }
        // 币-人民币信息
        obj.put("codeList", jsonArray);
        // 个人资产
        ExDigitalmoneyAccountRedis platAccount = this.selectAccount(customerId, platCode);
        obj.put("hotMoney", platAccount.getHotMoney());
        return new JsonResult(true).setObj(obj);
    }

    @Override
    public JsonResult exchangeCode(Map<String, String> paramMap) {
        // 兑换时间校验
        // 兑换开始时间
        String exchangeStartTime = RedisStaticUtil.getAppConfigValue(RulesConfig.ExchangeTimeKey, "exchangeStartTime");
        // 兑换结束时间
        String exchangeEndTime = RedisStaticUtil.getAppConfigValue(RulesConfig.ExchangeTimeKey, "exchangeEndTime");
        // 当前时间
        String nowDate = DateUtil.dateToString(new Date()).split(" ")[1];
        Boolean flag = hourMinuteBetween(nowDate, exchangeStartTime, exchangeEndTime);
        if (!flag) {
            return new JsonResult(false).setMsg("qingzaizhidignshijianduihuan");
        }
        // 将要换的币
        String code = paramMap.get("buyCode");
        if (!"USDT".equals(code)) {
            return new JsonResult(false).setMsg("该兑换只能兑换USDT");
        }
        Boolean lock = redisService.lock("exchangeCode:"+paramMap.get("customerId"));
        if (lock) {
            // 使用币
            String sellCode = paramMap.get("sellCode");
            // 将要换的币
            String buyCode = paramMap.get("buyCode");
            // 兑换数量
            BigDecimal exchangeNum = new BigDecimal(paramMap.get("exchangeNum"));
            // 用户Id
            Long customerId = Long.parseLong(paramMap.get("customerId"));
            // 获取平台比价格(目前获取指定的平台币汇率)多少USDT --- 备注：后期如果改动为兑换多币种 则以人民币计算
            String issuePrice = RedisStaticUtil.getAppConfigValue(RulesConfig.RulesCoinKey, "issuePrice");
            // 获取使用币的币账户信息(平台币)
            ExDigitalmoneyAccountRedis sellAccount = this.selectAccount(customerId, sellCode);
            // 最大兑换数量---平台币
            BigDecimal maxExchangeNum = sellAccount.getHotMoney();
            if (exchangeNum.compareTo(BigDecimal.ZERO) == -1) {
                redisService.unLock("exchangeCode:"+paramMap.get("customerId"));
                return new JsonResult(false).setMsg("duihuanshuliangyouwu").setCode("1001");
            }
            if (exchangeNum.compareTo(maxExchangeNum) == 1) {
                redisService.unLock("exchangeCode:"+paramMap.get("customerId"));
                return new JsonResult(false).setMsg("chaoguozuidaduihuanshuliang").setCode("1002");
            }
            // 后台校验平台币
            if (exchangeNum.compareTo(sellAccount.getHotMoney()) == 1) {
                redisService.unLock("exchangeCode:"+paramMap.get("customerId"));
                return new JsonResult(false).setMsg("duihuanshuliangyouwu").setCode("1003");
            }
            // 实际获得币数量
            BigDecimal buyNum = exchangeNum.multiply(new BigDecimal(issuePrice));
            // 获取兑换币的的币账户信息
            ExDigitalmoneyAccountRedis buyAccount = this.selectAccount(customerId, buyCode);
            List<Accountadd> list = new ArrayList<Accountadd>();
            // 使用币热账户减少---平台币(sellCode)
            String sellNum = IdGenerate.transactionNum(DealEnum.SITE12.getIndex());
            list.add(AccountUtil.getAccountAdd(sellAccount.getId(), new BigDecimal("-" + exchangeNum), 1, 1, 55,
                    sellNum));
            // 兑换币热账户增加---buyCode
            String buyNumber = IdGenerate.transactionNum(DealEnum.SITE7.getIndex());
            list.add(AccountUtil.getAccountAdd(buyAccount.getId(), new BigDecimal("+" + buyNum), 1, 1, 55,
                    buyNumber));
            messageProducer.toAccount(JSON.toJSONString(list));

            // 添加使用币兑出记录
            DealRecord sellRecord = new DealRecord();
            sellRecord.setCustomerId(customerId);
            sellRecord.setAccountId(sellAccount.getId());
            sellRecord.setCodeValue(new BigDecimal(issuePrice));
            sellRecord.setDealType(Integer.parseInt(DealEnum.TYPE12.getIndex()));
            sellRecord.setDealMoney(exchangeNum);
            sellRecord.setTransactionNum(sellNum);
            sellRecord.setCoinCode(sellCode);
            sellRecord.setRemark(DealEnum.SITE12.getName());
            dealRecordService.save(sellRecord);

            // 添加兑换币兑入记录
            DealRecord buyRecord = new DealRecord();
            buyRecord.setCustomerId(customerId);
            buyRecord.setAccountId(sellAccount.getId());
            buyRecord.setCodeValue(new BigDecimal(issuePrice));
            buyRecord.setDealType(Integer.parseInt(DealEnum.TYPE7.getIndex()));
            buyRecord.setDealMoney(buyNum);
            buyRecord.setTransactionNum(buyNumber);
            buyRecord.setCoinCode(buyCode);
            buyRecord.setRemark(DealEnum.SITE7.getName());
            dealRecordService.save(buyRecord);
            redisService.unLock("exchangeCode:"+paramMap.get("customerId"));
        }

        return new JsonResult(true).setMsg("duihuanchenggong");
    }

    /**
     *  校验时间
     * */
    private Boolean hourMinuteBetween (String nowDate, String startDate, String endDate) {
        Boolean flag = false;
        try {
            SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
            Date now = format.parse(nowDate);
            Date start = format.parse(startDate);
            Date end = format.parse(endDate);
            long nowTime = now.getTime();
            long startTime = start.getTime();
            long endTime = end.getTime();
            flag = nowTime >= startTime && nowTime <= endTime;
        } catch (Exception e) {
            e.getLocalizedMessage();
            flag = false;
        }
        return flag;
    }

    @Override
    public FrontPage findDealDetails(Map<String, String> paramMap) {
        Page<DealRecord> page = PageFactory.getPage(paramMap);
        Map<String, Object> map = new HashMap<>();
        map.put("customerId",paramMap.get("customerId"));
        map.put("coinCode",paramMap.get("coinCode"));
        String dealType = paramMap.get("dealType");
        if(!StringUtils.isEmpty(dealType)){
            map.put("dealType", dealType);
        } else {
            // 平台币code
            String coinCode = RedisStaticUtil.getAppConfigValue(RulesConfig.RulesCoinKey, "coinCode");
            if (coinCode.equals(paramMap.get("coinCode"))) {
                int[] dealTypes = {1,2,3,4,12,9,10,11};
                map.put("dealTypes", dealTypes);
            } else {
                int[] dealTypes = {6,7,8,13,14,15};
                map.put("dealTypes", dealTypes);
            }
        }
        List<DealRecord> recordList = dealRecordService.findPageBySql(map);
        return new FrontPage(recordList, page.getTotal(), page.getPages(), page.getPageSize());
    }

    @Override
    public JsonResult findUserAssetNum(Long customerId) {
        JSONObject obj = new JSONObject();
        // 最小投资金额
        String minInvest = RedisStaticUtil.getAppConfigValue(RulesConfig.InvestRangeKey, "minInvest");
        // 最大投资金额
        String maxInvest = RedisStaticUtil.getAppConfigValue(RulesConfig.InvestRangeKey, "maxInvest");
        BigDecimal min = new BigDecimal(minInvest);
        BigDecimal max = new BigDecimal(maxInvest);
        QueryFilter filter = new QueryFilter(OutInfo.class);
        filter.addFilter("customerId=",customerId);
        filter.addFilter("status=",0);
        OutInfo outInfo = outInfoService.get(filter);
        if (outInfo != null) {
            BigDecimal baseMoney = outInfo.getBaseMoney();
            BigDecimal maxMoney = new BigDecimal(maxInvest);
            max = new BigDecimal(maxInvest).subtract(baseMoney);
        } else {
            ExDigitalmoneyAccountRedis account = this.selectAccount(customerId, "USDT");
            max = new BigDecimal(maxInvest).subtract(account.getColdMoney());
        }
        obj.put("minInvest",min);
        obj.put("maxInvest",max);
        return new JsonResult(true).setObj(obj);
    }
}
