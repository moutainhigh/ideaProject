package hry.ico.remote;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import hry.bean.FrontPage;
import hry.bean.JsonResult;
import hry.bean.ObjectUtil;
import hry.bean.PageResult;
import hry.calculate.util.DateUtil;
import hry.customer.commend.model.AppCommendUser;
import hry.customer.commend.service.AppCommendUserService;
import hry.customer.person.model.AppPersonInfo;
import hry.customer.person.service.AppPersonInfoService;
import hry.customer.user.model.AppCustomer;
import hry.customer.user.service.AppCustomerService;
import hry.exchange.account.model.ExDigitalmoneyAccount;
import hry.exchange.account.service.ExDigitalmoneyAccountService;
import hry.front.redis.model.UserRedis;
import hry.ico.dao.IcoAccountDao;
import hry.ico.model.*;
import hry.ico.model.util.IcoAwardPo;
import hry.ico.remote.model.*;
import hry.ico.service.*;
import hry.manage.remote.RemoteManageService;
import hry.manage.remote.model.AppConfig;
import hry.manage.remote.model.CoinAccount;
import hry.manage.remote.model.RemoteResult;
import hry.message.model.MessageType;
import hry.message.model.RemoteMessage;
import hry.mq.producer.service.MessageProducer;
import hry.redis.common.dao.RedisUtil;
import hry.redis.common.utils.RedisService;
import hry.trade.redis.model.Accountadd;
import hry.trade.redis.model.ExDigitalmoneyAccountRedis;
import hry.util.PageFactory;
import hry.util.QueryFilter;
import hry.util.StringUtil;
import org.apache.log4j.Logger;
import org.springframework.util.DigestUtils;
import util.BigDecimalUtil;
import util.ToAccountUtil;
import util.UserRedisUtils;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 *lzy
 */
public class RemoteIcoServiceImpl implements RemoteIcoService {
    private final Logger logger = Logger.getLogger(RemoteIcoServiceImpl.class);
    //锁仓比率 40%
    private final double RATIO=0.4;
    private final static String configCache="configCache:";
    private static String payCoinCode="ETH";
    private static Object buyPlatform = new Object();
    @Resource
    private RedisService redisService;
    @Resource
    private IcoUpgradeConfigService upgradeConfigService;
    @Resource
    private IcoAccountService icoAccountService;
    @Resource
    private IcoTransactionRecordService icoTransactionRecordService;
    @Resource
    private IcoBuyOrderService icoBuyOrderService;
    @Resource
    private IcoLockRecordService icoLockRecordService;
    @Resource
    private RemoteManageService remoteManageService;
    @Resource
    private IcoTransferAccountsService icoTransferAccountsService;
    @Resource
    private AppCommendUserService appCommendUserService;
    @Resource
    private ExDigitalmoneyAccountService exDigitalmoneyAccountService;
    @Resource
    private MessageProducer messageProducer;
    @Resource
    private IcoCustomerLevelService icoCustomerLevelService;
    @Resource
    private AppPersonInfoService appPersonInfoService;
    @Resource
    private IcoExperienceService icoExperienceService;
    @Resource
    private IcoDividendRecordService icoDividendRecordService;
    @Resource
    private IDelayedOrderService iDelayedOrder;
    @Resource
    private IcoLockRewardService icoLockRewardService;
    @Resource
    private IcoAwardRecordService icoAwardRecordService;
    @Resource
    private AppCustomerService appCustomerService;
    @Resource
    private IcoBillDetailsService icoBillDetailsService;
    @Resource
    private CoinCodeKeepDigitService coinCodeKeepDigitService;
    @Resource
    private  IcoLockAppendRecordService appendRecordService;

    //获取系统规则配置
    @Override
    public JsonResult getPlatformRule(String rulekeyStr) {
        IcoRulesConfig rulesConfig=new IcoRulesConfig();
        Map<String,String> cfMap=redisService.getMap(RulesConfig.RulesCoinLikeKey);
        rulesConfig=ObjectUtil.bean2bean(cfMap,IcoRulesConfig.class);
        return new JsonResult().setObj(rulesConfig).setSuccess(true);
    }

    @Override
    public JsonResult getPlatformCurrencyInfo() {
        RemotePlatformCurrencyInfo currencyInfo=null;
        JsonResult jsonResult= getPlatformRule(RulesConfig.RulesCoinKey);
        if(jsonResult.getSuccess()){
            currencyInfo=ObjectUtil.bean2bean(jsonResult.getObj(),RemotePlatformCurrencyInfo.class);
        }
        String PLATFORM_NUMBER=redisService.get(RulesConfig.PLATFORM_NUMBER);//平台币可售数量
        if(PLATFORM_NUMBER==null||PLATFORM_NUMBER.equals("")){
            PLATFORM_NUMBER="0";
        }
        currencyInfo.setSaleNum(PLATFORM_NUMBER);
        return new JsonResult().setObj(currencyInfo).setSuccess(true);
    }

    /**
     * 当前是否ico阶段
     * @return
     */
    @Override
    public JsonResult isCheckICOTime() {
        JsonResult jsonResult=getPlatformRule(RulesConfig.RulesICOKey);//Ico设置
        if(jsonResult.getSuccess()){
            IcoRulesConfig rulesConfig= ObjectUtil.bean2bean(jsonResult.getObj(),IcoRulesConfig.class);
            if("0".equals( rulesConfig.getIsLock())){//是否自动锁 0是1否
                String startTime= rulesConfig.getIcoLockStartTime();//开始时间
                String endTime= rulesConfig.getIcoLockEndTime();//结束时间
                boolean fl= DateUtil.isDateInterval(startTime,endTime);
                if(fl){
                    return jsonResult;
                }
            }
        }
        return new JsonResult().setSuccess(false);
    }

    /**
     * 获取等级配置
     * @return
     */
    @Override
    public JsonResult getUpgradeConfigList() {
        JsonResult result=new JsonResult();
        List<RemoteIcoUpgradeConfig> resList=new ArrayList<>();
        List<IcoUpgradeConfig> list= upgradeConfigService.findAll();
        if(list!=null&&list.size()>0){
            resList=ObjectUtil.beanList(list,RemoteIcoUpgradeConfig.class);
        }
        result.setSuccess(true);
        result.setObj(resList);
        result.setMsg("success");//成功
        return result;
    }
    /**
     * 计算购买平台币
     * @param hashMap
     * @return
     */
    @Override
    public JsonResult calculatePaymentAmount(HashMap<String, String> hashMap) {
        RemotePaymentAmount remotePaymentAmount=new RemotePaymentAmount();
          String number=hashMap.get("number");//购买数
          String coinCode=payCoinCode;//"ETH";//hashMap.get("coinCode");//支付币种ETH
         JsonResult ruleResult=getPlatformRule(RulesConfig.RulesCoinKey);//
         if(!ruleResult.getSuccess()){
             return new JsonResult().setSuccess(false).setMsg("pingtaiguizehuoqucuowu");//平台规则获取错误
         }
          IcoRulesConfig rulesConfig=ObjectUtil.bean2bean(ruleResult.getObj(),IcoRulesConfig.class);
          remotePaymentAmount.setPayCoinCode(coinCode);
          remotePaymentAmount.setBuyCoinCode(rulesConfig.getCoinCode());//平台规则获取平台币名称
          BigDecimal num=getPayNumber(new BigDecimal(number).multiply(new BigDecimal(rulesConfig.getIssuePrice())));
          remotePaymentAmount.setBuyPrice(rulesConfig.getIssuePrice());
          remotePaymentAmount.setPayNumber(BigDecimalUtil.bigDecimalToString(num));
       return new JsonResult().setSuccess(true).setMsg("success").setObj(remotePaymentAmount);
    }

    /**
     * 计算出折合数量 ETH
     * @param number  总价值韩币（数量*货币价格）
     * @return
     */
    private BigDecimal getPayNumber(BigDecimal number){
       String buy_price=redisService.getMap(RulesConfig.RedisMarketKey,"buy_price");//购买价格
        if(buy_price==null){
            throw  new RuntimeException();
        }
        return  number.divide(new BigDecimal(buy_price),8,BigDecimal.ROUND_DOWN);
    }

    /**
     *购买平台币
     */
    @Override
    public JsonResult purchasePlatformAccount(HashMap<String, String> hashMap) {
        String customerId=hashMap.get("customerId");//用户Id
        String coinCode="ETH";//hashMap.get("coinCode");//币种
        BigDecimal number=new BigDecimal(hashMap.get("number"));//购买数
        JsonResult jsonResult=calculatePaymentAmount(hashMap);
        if(!jsonResult.getSuccess()){
            return jsonResult;
        }
            RemotePaymentAmount rePay=ObjectUtil.bean2bean(jsonResult.getObj(),RemotePaymentAmount.class);
            BigDecimal payNumber=new BigDecimal(rePay.getPayNumber());//支付数量ETH
            String buyCoinCode=rePay.getBuyCoinCode();//购买平台币币种名
            String buyPrice=rePay.getBuyPrice();//购买平台币定价
            RedisUtil<UserRedis> redisUtil = new RedisUtil<UserRedis>(UserRedis.class);
            UserRedis userRedis = redisUtil.get(customerId);
            List<Accountadd>listLock=new ArrayList<>();
            Long orderById=null;
        synchronized (buyPlatform){
             String PLATFORM_NUMBER=redisService.get(RulesConfig.PLATFORM_NUMBER);//平台币剩余数量
            if(PLATFORM_NUMBER==null||PLATFORM_NUMBER.equals("")){
                PLATFORM_NUMBER = "0";
            }
            if(new BigDecimal(PLATFORM_NUMBER).compareTo(number)==-1){
                return new JsonResult().setSuccess(false).setMsg("pingtaibibuzu");//平台币不足
            }
            // 获取资金账户，判断资金账户余额
            ExDigitalmoneyAccountRedis exAccount = UserRedisUtils.getAccount(userRedis.getDmAccountId(coinCode).toString(),coinCode);
            if(exAccount.getHotMoney().compareTo(payNumber)==-1){
                return new JsonResult().setSuccess(false).setMsg("zijinbuzu");//资金不足
            }
            Long payAccountId = userRedis.getDmAccountId(exAccount.getCoinCode());
            Accountadd payAccountadd=ToAccountUtil.expenditureHotAssets(payAccountId,payNumber);
            exAccount.setHotMoney(exAccount.getHotMoney().subtract(payNumber));

            listLock.add(payAccountadd);//扣除购买平台币支付的ETH
            // 获取资金账户，判断资金账户余额
            ExDigitalmoneyAccountRedis exAccount1 = UserRedisUtils.getAccount(userRedis.getDmAccountId(buyCoinCode).toString(),buyCoinCode);
            /*//ico账户
            IcoAccount icoAccount= icoAccountService.get(new QueryFilter(IcoAccount.class).addFilter("customerId=",customerId));
           */
            Long buyAccountId = userRedis.getDmAccountId(exAccount1.getCoinCode());
            Accountadd buyAccountadd=ToAccountUtil.ncomeAssets(buyAccountId,number);
            exAccount1.setHotMoney(exAccount1.getHotMoney().add(number));
            listLock.add(buyAccountadd);//购买平台币添加ITX
            PLATFORM_NUMBER=BigDecimalUtil.bigDecimalToString(new BigDecimal(PLATFORM_NUMBER).subtract(number));
            String orderNumber=String.valueOf(System.currentTimeMillis());//流水号
            IcoBuyOrder icoBuyOrder=new IcoBuyOrder();//购买平台记录
            icoBuyOrder.setOrderNumber(orderNumber);////流水号
            icoBuyOrder.setCustomerId(Long.valueOf(customerId));
            icoBuyOrder.setBuyCoinCode(buyCoinCode);//购买币种
            icoBuyOrder.setPayCoinCode(coinCode);//支付币种
            icoBuyOrder.setBuyPrice(new BigDecimal(buyPrice));//定价
            icoBuyOrder.setPayNumber(payNumber);//支付ETH数量
            icoBuyOrder.setBuyNumber(number);//购买ITX数量

            //交易流水
            IcoTransactionRecord transactionRecord=new IcoTransactionRecord();
            transactionRecord.setCustomerId(Long.valueOf(customerId));
            transactionRecord.setProjectNumber(orderNumber);//流水号
            transactionRecord.setCoinCode(exAccount.getCoinCode());
            transactionRecord.setColdMoney(exAccount.getColdMoney());
            transactionRecord.setHotMoney(exAccount.getHotMoney());
            transactionRecord.setType(51);// 11.锁仓12.释放21转账31分红32推荐奖励 41.充币42.提币51.买入52.卖出
            transactionRecord.setState(201);//201.支出202.收入
            transactionRecord.setTransactionCount(payNumber);
            transactionRecord.setRemark("购买平台币支付");
            transactionRecord.setIsShow(1);
            IcoTransactionRecord transactionRecord2=new IcoTransactionRecord();
            transactionRecord2.setCustomerId(Long.valueOf(customerId));
            transactionRecord2.setProjectNumber(orderNumber);//流水号
            transactionRecord2.setCoinCode(exAccount1.getCoinCode());
            transactionRecord2.setColdMoney(exAccount1.getColdMoney());
            transactionRecord2.setHotMoney(exAccount1.getHotMoney());
            transactionRecord2.setType(51);// 11.锁仓12.释放21转账31分红32推荐奖励 41.充币42.提币51.买入52.卖出
            transactionRecord2.setState(202);//201.支出202.收入
            transactionRecord2.setTransactionCount(number);
            transactionRecord2.setRemark("购买平台币获得");
            transactionRecord2.setIsShow(1);
            icoBuyOrderService.save(icoBuyOrder);
            orderById=icoBuyOrder.getId();//订单ID
            transactionRecord2.setForeignKey(icoBuyOrder.getId());//业务外键Id
            transactionRecord.setForeignKey(icoBuyOrder.getId());//业务外键Id
            icoTransactionRecordService.save(transactionRecord2);
            icoTransactionRecordService.save(transactionRecord);
           /* icoAccount.setHotMoney(icoAccount.getHotMoney().add(number));//购买ITX数量
            icoAccountService.update(icoAccount);*/
            messageProducer.toPlatformCurrency(number.multiply(new BigDecimal(-1)).stripTrailingZeros().toPlainString());
            //redisService.save(RulesConfig.PLATFORM_NUMBER,PLATFORM_NUMBER);
            messageProducer.toAccount(JSON.toJSONString(listLock));
        }
        return new JsonResult().setSuccess(true).setMsg("success").setObj(orderById);//成功
    }

    /**
     * 最新购买平台记录
     * @param hashMap
     * @return
     */
    @Override
    public FrontPage getIcoBuyOrderRecord(HashMap<String, String> hashMap) {
       // String customerId=hashMap.get("customerId");
        return icoBuyOrderService.findPageBySql(hashMap);
    }

    /**
     * 我的推荐列表
     * @param hashMap
     * @return
     */
    @Override
    public FrontPage getMyRecommendationList(HashMap<String, String> hashMap) {
        String coinCode= redisService.getMap(RulesConfig.RulesCoinLikeKey,"coinCode");
        int keepDigit=coinCodeKeepDigitService.getCoinCodeKeepDigit(coinCode);//小数位
        hashMap.put("keepDigit",String.valueOf(keepDigit));
        FrontPage frontPage=icoBuyOrderService.finRecommendBySql(hashMap);
        return frontPage;
    }

    /**
     * 我的交易流水
     * @param hashMap
     * @return
     */
    @Override
    public FrontPage getMyTransactionflow(HashMap<String, String> hashMap) {
        String customerId=hashMap.get("customerId");
        String type=hashMap.get("type");//类型
        String coinCode=hashMap.get("coinCode");//币种Code
        String page=hashMap.get("page")==null?"0":hashMap.get("page");
        String pageSize=hashMap.get("pageSize")==null?"10":hashMap.get("pageSize");
        String starTime=hashMap.get("starTime");//开始时间
        String endTime=hashMap.get("endTime");//结束时间
        String maxNum=hashMap.get("maxNum");//最大值
        String minNum=hashMap.get("minNum");//最小值
        if(Integer.parseInt(pageSize)>100){
            pageSize="10";
        }
        QueryFilter queryFilter=new QueryFilter(IcoTransactionRecord.class);
        queryFilter.addFilter("customerId=",customerId);
        if(StringUtil.isNull(type)){
            queryFilter.addFilter("type=",type);
        }
        if(StringUtil.isNull(starTime)){
            queryFilter.addFilter("created>=",starTime);
        }
        if(StringUtil.isNull(endTime)){
            queryFilter.addFilter("created<=",endTime);
        }
        if(StringUtil.isNull(minNum)){
            queryFilter.addFilter("transactionCount>=",minNum);
        }
        if(StringUtil.isNull(maxNum)){
            queryFilter.addFilter("transactionCount<=",maxNum);
        }

        if(StringUtil.isNull(coinCode)){
            queryFilter.addFilter("coinCode=",coinCode);
        }
       // queryFilter.addFilter("isShow=",1);
        queryFilter.setOrderby("created desc");
        queryFilter.setPage(Integer.parseInt(page));
        queryFilter.setPageSize(Integer.parseInt(pageSize));
        return icoTransactionRecordService.findPageBySql(hashMap);
       // Page<IcoTransactionRecord> orderPage= icoTransactionRecordService.findPage(queryFilter);
      /*  Page<IcoTransactionRecord> orderPage= icoTransactionRecor dService.findPage(queryFilter);
        if(orderPage.size()>0){
            List<RemoteIcoTransactionRecord> list=ObjectUtil.beanList(orderPage.getResult(),RemoteIcoTransactionRecord.class);
            FrontPage frontPage = new FrontPage(list, orderPage.getTotal(), orderPage.getPages(), orderPage.getPageSize());
            return frontPage;
        }
        return new FrontPage(null, 0, 1, 10);*/
    }

    /**
     * 我的账单明细
     * @param hashMap
     * @return
     */
    @Override
    public JsonResult getTransactionDetail(HashMap<String, String> hashMap) {
        String transactionId=hashMap.get("transactionId");//流水Id
        String customerId=hashMap.get("customerId");//用户Id
        IcoTransactionRecord icoTransactionRecord=null;
        if(transactionId.contains("_CB")){//充币
            String [] strings=transactionId.split("_");
            hashMap.put("transactionId",strings[0]);
            icoTransactionRecord= icoTransactionRecordService.getCBTransaction(hashMap);
        }else{
             icoTransactionRecord= icoTransactionRecordService.get(new QueryFilter(IcoTransactionRecord.class)
                     .addFilter("id=",transactionId).addFilter("customerId=",customerId));
        }
        if(icoTransactionRecord==null){
            return new JsonResult().setSuccess(false).setMsg("liushuizhangdanbucunzai");//流水账单不存在
        }
        RemoteIcoTransactionRecord remote=ObjectUtil.bean2bean(icoTransactionRecord,RemoteIcoTransactionRecord.class);
        Long foreignKey=icoTransactionRecord.getForeignKey();//Long.valueOf(hashMap.get("foreignKey"));//业务流水Id
        Integer type=icoTransactionRecord.getType();//Integer.parseInt(hashMap.get("type"));//流水类型
        Object data=icoBillDetailsService.getBillDetails(type,foreignKey);
        remote.setObj(ObjectUtil.bean2bean(data,Map.class));
        return new JsonResult().setSuccess(true).setMsg("success").setObj(remote);
    }

    /**
     * 我的推广明细（团队资产等）
     * @param hashMap
     * @return
     */
    @Override
    public JsonResult getMyRecommendationDetails(HashMap<String, String> hashMap) {
        String customerId=hashMap.get("customerId");
        RemoteRecommendationDetails recommendationDetails=new RemoteRecommendationDetails();
        JsonResult jsonResult= getIcoAccountInfo(hashMap);
        int keepDigit=0;//保留位数
        if(jsonResult.getSuccess()){
            RemoteIcoAccount remoteIcoAccount=ObjectUtil.bean2bean(jsonResult.getObj(),RemoteIcoAccount.class);
            recommendationDetails.setColdMoney(remoteIcoAccount.getColdMoney());
            recommendationDetails.setHotMoney(remoteIcoAccount.getHotMoney());
            recommendationDetails.setTotalMoney(remoteIcoAccount.getTotalMoney());
            keepDigit=remoteIcoAccount.getKeepDigit();
            recommendationDetails.setKeepDigit(keepDigit);
        }
        int teamMember= appCommendUserService.countOneLevel(customerId);//一级所有为团队人数
        recommendationDetails.setTeamMember(teamMember);//团队人数
        String recommendedLock=icoAccountService.recommendedLockSum(Long.valueOf(customerId));
        if(recommendedLock!=null){
            recommendationDetails.setRecommendedLock(BigDecimalUtil.
                    bigDecimalScaleDigitToString(new BigDecimal(recommendedLock),keepDigit));//推荐锁仓
        }
        String  recommendedAward= icoAwardRecordService.recommendedLockSum(Long.valueOf(customerId));
        if(recommendedAward!=null){
            recommendationDetails.setRecommendedAward(BigDecimalUtil.
                    bigDecimalScaleDigitToString(new BigDecimal(recommendedAward),keepDigit));
        }
        return new JsonResult().setSuccess(true).setObj(recommendationDetails);
    }

    /**
     * 锁仓操作
     * @param hashMap
     * @return
     */
    @Override
    public JsonResult toLockDepot(HashMap<String, String> hashMap) {
         String cu=hashMap.get("customerId");
         Long customerId=Long.valueOf(cu);//用户Id
         Integer lockDay=Integer.valueOf(hashMap.get("lockDay"));//锁仓天数
         BigDecimal number=new BigDecimal(hashMap.get("number"));//锁仓数量
         boolean isIco=Boolean.valueOf(hashMap.get("isIco"));
         JsonResult ruleResult=getPlatformRule(RulesConfig.RulesCoinKey);//
         if(!ruleResult.getSuccess()){
              return new JsonResult().setSuccess(false).setMsg("平台规则获取错误");
         }
         IcoRulesConfig rulesConfig=ObjectUtil.bean2bean(ruleResult.getObj(),IcoRulesConfig.class);
         String coinCode=rulesConfig.getCoinCode();//平台币种
         return  toLockStorage(customerId,lockDay,number,coinCode,isIco);
    }
    //追加锁仓操作
    @Override
    public JsonResult appendLockDepot(HashMap<String, String> hashMap) {
        String cu=hashMap.get("customerId");
        Long customerId=Long.valueOf(cu);
        Integer addLockDay=Integer.valueOf(hashMap.get("addLockDay"));//追加锁仓天数
        Long lockId=Long.valueOf(hashMap.get("lockId"));//锁仓Id
        QueryFilter queryFilter=new QueryFilter(IcoLockRecord.class);
        queryFilter.addFilter("id=",lockId);
        queryFilter.addFilter("state=","0");//(0.待释放1.已释放)
        queryFilter.addFilter("customerId=",customerId);
        IcoLockRecord icoLockRecord=icoLockRecordService.get(queryFilter);
        if(icoLockRecord==null){
            return  new JsonResult().setSuccess(false).setMsg("dingdanyibeishifang");//订单已被释放
        }
        BigDecimal number=icoLockRecord.getNumber();//锁仓数量
        Date releaseDate=icoLockRecord.getReleaseDate();//释放时间
        boolean f=icoLockRecordService.appendLockRecord(icoLockRecord,addLockDay);
        if(!f){
            return  new JsonResult().setSuccess(false).setMsg("appendError");//追加失败
        }
        //追加锁仓发经验
        Integer hour=addLockDay.intValue()*24;
        BigDecimal exPerience=number.multiply(new BigDecimal(hour));//追加发送的经验
        releaseDate=hry.util.date.DateUtil.addDaysToDate(releaseDate,addLockDay);//新的释放时间
        IcoLockAppendRecord appendRecord=new IcoLockAppendRecord();//追加记录
        appendRecord.setAppendDay(addLockDay);//追加天数
        appendRecord.setCoinCode(icoLockRecord.getCoinCode());//币种
        appendRecord.setLockId(lockId);//锁仓Id
        appendRecord.setNumber(number);//原锁仓数量
        appendRecord.setReleaseDate(releaseDate);//释放时间
        appendRecord.setExperience(exPerience.longValue());//追加获得的经验
        appendRecordService.save(appendRecord);
        remoteManageService.clearingExperience(customerId,"0101",exPerience.longValue(),number,"追加锁仓天数奖励");
        return new JsonResult().setSuccess(true).setMsg("success");
    }

    /**
     * 释放操作
     * @param hashMap
     * @return
     */
    @Override
    public JsonResult releaseOperation(HashMap<String, String> hashMap) {
        Long customerId=Long.valueOf(hashMap.get("customerId"));//用户Id
        Long lockId=Long.valueOf(hashMap.get("lockId"));//锁仓Id
        QueryFilter queryFilter=new QueryFilter(IcoLockRecord.class);
        queryFilter.addFilter("id=",lockId);
        queryFilter.addFilter("state=","0");//(0.待释放1.已释放)
        queryFilter.addFilter("customerId=",customerId);
        IcoLockRecord icoLockRecord=icoLockRecordService.get(queryFilter);
        if(icoLockRecord==null){
            return  new JsonResult().setSuccess(false).setMsg("dingdanyibeishifang");//订单已被释放
        }
         int type= icoLockRecord.getType();
        if(type==1){
            return  new JsonResult().setSuccess(false).setMsg("icobukeshifang");//Ico阶段不可手动释放
        }
        RemoteReleaseDeduction releaseDeduction= getReleaseDeductionInfo(icoLockRecord);
        Long releaseDeduct=releaseDeduction.getReleaseDeduct();
        icoLockRecord.setReleaseDeductType(1);//1.经验扣除2.itx扣除
        icoLockRecord.setReleaseDeduct(BigDecimal.valueOf(releaseDeduct));//经验值
        icoLockRecord.setReleaseType(1);//1提前释放
        // 提前释放扣除币  锁仓币数的比率  /跟天数计算
        BigDecimal subtractNum=releaseDeduction.getSubtractNum();//释放扣除的币个数
        BigDecimal number = icoLockRecord.getNumber();//锁仓数量
        BigDecimal actualReleaseNum=number.subtract(subtractNum);//锁仓数量-扣除币数量=实际释放数量
//        icoLockRecord.setNumber(actualReleaseNum);
        icoLockRecord.setActualReleaseNum(actualReleaseNum);//实际释放数量
        icoLockRecord.setReleaseDeductNum(subtractNum);//释放扣币数量
        JsonResult releaseResult= release(icoLockRecord);
        if(releaseResult.getSuccess()){
            //释放扣除经验
            RemoteResult result=remoteManageService.clearingExperience(Long.valueOf(customerId),"0202",releaseDeduct,number,"释放扣除");
            if(!result.getSuccess()){
                logger.error("释放扣除经验异常");
                //return new JsonResult().setSuccess(false).setObj(result.getObj());
            }
        }
        return releaseResult;
    }

    //定时器释放任务
    public JsonResult releaseMQ(String msgText){
        IcoLockRecord lockRecord=JSON.parseObject(msgText,IcoLockRecord.class);
        return  release(lockRecord);
    }
    /**
     * 释放任务
     * @param icoLockRecord
     * @return
     */
    private JsonResult release(IcoLockRecord icoLockRecord) {
     //   System.out.println("释放操作+【"+JSON.toJSONString(icoLockRecord));
        Long customerId = icoLockRecord.getCustomerId();
        String coinCode = icoLockRecord.getCoinCode();//锁仓币种
        BigDecimal number = icoLockRecord.getNumber();//锁仓数量
        BigDecimal releaseDeductNum=icoLockRecord.getReleaseDeductNum();//释放扣币数量
        BigDecimal actualReleaseNum=icoLockRecord.getActualReleaseNum();//实际释放数量
        if(releaseDeductNum==null){//释放扣除为0
            releaseDeductNum=new BigDecimal(0);
            icoLockRecord.setReleaseDeductNum(releaseDeductNum);
        }if(actualReleaseNum==null){//为空实际释放为锁仓量
            actualReleaseNum=number;
            icoLockRecord.setActualReleaseNum(actualReleaseNum);
        }
        RedisUtil<UserRedis> redisUtil = new RedisUtil<UserRedis>(UserRedis.class);
        UserRedis userRedis = redisUtil.get(String.valueOf(customerId));
       // System.out.println("userRedis+【"+userRedis);
        Long accountId = userRedis.getDmAccountId(coinCode);
       // System.out.println("accountId+【"+accountId);
        // 获取资金账户，判断资金账户余额
        ExDigitalmoneyAccountRedis exAccount = UserRedisUtils.getAccount(userRedis.getDmAccountId(coinCode).toString(), coinCode);
        List<Accountadd> listLock = new ArrayList<>();
       // System.out.println("exAccount+【"+exAccount);
       // System.out.println("number+【"+number);
        List<Accountadd> accountadds = ToAccountUtil.unblockedAssets(accountId, number);//释放 冻结转可用
        listLock.addAll(accountadds);
        exAccount.setHotMoney(exAccount.getHotMoney().add(number));
        exAccount.setColdMoney(exAccount.getColdMoney().subtract(number));

        if(!(releaseDeductNum.compareTo(BigDecimal.ZERO)==0)){
            //释放扣除币
            Accountadd accountaddss = ToAccountUtil.expenditureHotAssets(accountId, releaseDeductNum);//扣除
            listLock.add(accountaddss);
            exAccount.setHotMoney(exAccount.getHotMoney().subtract(releaseDeductNum));
        }
        String orderNumber = String.valueOf(System.currentTimeMillis());
        IcoTransactionRecord transactionRecord = new IcoTransactionRecord();
        transactionRecord.setCustomerId(customerId);
        transactionRecord.setProjectNumber(orderNumber);//流水号
        transactionRecord.setCoinCode(coinCode);
        transactionRecord.setColdMoney(exAccount.getColdMoney());
        transactionRecord.setHotMoney(exAccount.getHotMoney());
        transactionRecord.setType(12);// 11.锁仓12.释放13锁仓扣除21转账31分红32推荐奖励 41.充币42.提币51.买入52.卖出
        transactionRecord.setState(202);//201.支出202.收入
        transactionRecord.setTransactionCount(actualReleaseNum);
        transactionRecord.setRemark("释放存储转流通");
        transactionRecord.setIsShow(1);
        transactionRecord.setForeignKey(icoLockRecord.getId());
        IcoAccount icoAccount=icoAccountService.get(new QueryFilter(IcoAccount.class).addFilter("customerId=",customerId));
        boolean f = icoLockRecordService.releaseLockRecord(icoLockRecord);
         if (f) {
             boolean account= icoAccountService.updateByAccountId( icoAccount.getId(),icoAccount.getVersion(), number.multiply(new BigDecimal(-1)));
             if(!account){
                logger.error("释放异常");
                 throw new RuntimeException();
             }
             icoTransactionRecordService.save(transactionRecord);//交易流水
             messageProducer.toAccount(JSON.toJSONString(listLock));
            return new JsonResult().setSuccess(true).setMsg("shifangchenggong");//释放成功
        }
        return  new JsonResult().setSuccess(false).setMsg("shifangshibai");//释放成功
    }

    /**
     * 释放扣除经验
     * 例如锁了100天，在第9天的时候解仓就扣除91%的币，
     * 只给用户9%。并且先行发放的经验值也是按相同比例扣除。
     * 点击释放后弹出提示释放锁扣除的币和经验值，弹出确定和取消的窗口，
     * 必须倒计时过10秒后才能点击确定。
     * 如果币的计算结果有小数位，保留4位小数，
     * 如果经验值有小数位直接舍去
     * @return
     */
    @Override
    public JsonResult getReleaseDeductionInfo(HashMap<String,String> hashMap) {
        String customerId=hashMap.get("customerId");
        String lockId=hashMap.get("lockId");
        QueryFilter queryFilter=new QueryFilter(IcoLockRecord.class);
        queryFilter.addFilter("id=",lockId);
        queryFilter.addFilter("customerId=",customerId);
        queryFilter.addFilter("state=","0");//(0.待释放1.已释放)
        IcoLockRecord icoLockRecord=icoLockRecordService.get(queryFilter);
        if(icoLockRecord==null){
            return  new JsonResult().setSuccess(false).setMsg("dingdanyibeishifang");//订单已被释放
        }
        int type= icoLockRecord.getType();
        if(type==1){
            return  new JsonResult().setSuccess(false).setMsg("icobukeshifang");//Ico阶段不可手动释放
        }
        // 调用获取用户当前经验值总数
        JsonResult levelResult= seeCustomerLevelAccount(Long.valueOf(customerId));
        if(!levelResult.getSuccess()){
            return levelResult;
        }
        IcoCustomerLevel level=ObjectUtil.bean2bean( levelResult.getObj(),IcoCustomerLevel.class);
        Long userExperience=level.getExperience();//用户当前经验值
        RemoteReleaseDeduction releaseDeduction =getReleaseDeductionInfo(icoLockRecord);
        Long deductionExp=releaseDeduction.getReleaseDeduct(); //释放扣除经验
        if(deductionExp>userExperience){
            return new JsonResult().setSuccess(false).setMsg("jingyanbuzubukecaozuo").setObj(releaseDeduction);//经验不足不可操作
        }
        return new JsonResult().setSuccess(true).setMsg("success").setObj(releaseDeduction);
    }

    //新获取释放扣除的币和扣除的经验
    private  RemoteReleaseDeduction getReleaseDeductionInfo(IcoLockRecord icoLockRecord){
        RemoteReleaseDeduction releaseDeduction =new RemoteReleaseDeduction();
        //获取释放扣除itx数量
        BigDecimal subtractNum=icoLockRecord.getNumber();
        Long releaseDeduct=0l;//释放扣除经验
        Date now =new Date();//当前时间
        Date releaseDate=icoLockRecord.getReleaseDate();//释放时间
        Integer lockDay= icoLockRecord.getLockDay();//锁仓天数
        try {
            Integer day=DateUtil.daysBetween(now,releaseDate);
            if(day.intValue()==icoLockRecord.getLockDay().intValue()){
                releaseDeduction.setSubtractNum(subtractNum);//扣除的数量
                releaseDeduct=new BigDecimal(24*day).multiply(subtractNum).longValue();
                releaseDeduction.setReleaseDeduct(releaseDeduct);
                releaseDeduction.setReleaseDeductType(1);//释放扣除类型（1.经验扣除2.itx扣除）
                return releaseDeduction;
            }
            BigDecimal ratio=new BigDecimal(day).divide(new BigDecimal(lockDay),2,BigDecimal.ROUND_DOWN);
            releaseDeduct= new BigDecimal(24*day).multiply(icoLockRecord.getNumber())
                    .setScale(0,BigDecimal.ROUND_DOWN).longValue();
            subtractNum=BigDecimalUtil.bigDecimalScaleDigit(subtractNum.multiply(ratio),4);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        releaseDeduction.setSubtractNum(subtractNum);//扣除的数量
        releaseDeduction.setReleaseDeduct(releaseDeduct);
        releaseDeduction.setReleaseDeductType(1);//释放扣除类型（1.经验扣除2.itx扣除）
        return releaseDeduction;
    }
    /**
     *  流通转存储（锁仓）
     */
    @Override
    public JsonResult toLockStorage(Long customerId , Integer lockDay, BigDecimal number,String coinCode,boolean isIco){
        RedisUtil<UserRedis> redisUtil = new RedisUtil<UserRedis>(UserRedis.class);
        UserRedis userRedis = redisUtil.get(String.valueOf(customerId));
        // 获取资金账户，判断资金账户余额
        ExDigitalmoneyAccountRedis exAccount = UserRedisUtils.getAccount(userRedis.getDmAccountId(coinCode).toString(),coinCode);
        if(exAccount.getHotMoney().compareTo(number)==-1){
            return new JsonResult().setSuccess(false).setMsg("zijinbuzu");//资金不足
        }
        JsonResult jsonResult=getLockDeductionInfo(customerId);//获得锁仓扣除经验或itx币
        if(!jsonResult.getSuccess()){
            return  jsonResult;
        }
        RemoteLockDeduction remoteLockDeduction=ObjectUtil.bean2bean(jsonResult.getObj(),RemoteLockDeduction.class);
        Integer lockDeductType=remoteLockDeduction.getLockDeductType();//扣除类型1.经验扣除2.itx扣除
        BigDecimal lockDeduct=remoteLockDeduction.getLockDeduct();//扣除数量
        List<Accountadd>listLock=new ArrayList<>();
        Long accountId = userRedis.getDmAccountId(exAccount.getCoinCode());
        List<Accountadd> accountadds=ToAccountUtil.frozenAssets(accountId,number);
        listLock.addAll(accountadds);
        exAccount.setHotMoney(exAccount.getHotMoney().subtract(number));
        exAccount.setColdMoney(exAccount.getColdMoney().add(number));
        String orderNumber=String.valueOf(System.currentTimeMillis());
       IcoTransactionRecord transactionRecord2=new IcoTransactionRecord();
        transactionRecord2.setCustomerId(customerId);
        transactionRecord2.setProjectNumber(orderNumber);//流水号
        transactionRecord2.setCoinCode(exAccount.getCoinCode());
        transactionRecord2.setColdMoney(exAccount.getColdMoney());
        transactionRecord2.setHotMoney(exAccount.getHotMoney());
        transactionRecord2.setType(11);// 11.锁仓12.释放13锁仓扣除21转账31分红32推荐奖励 41.充币42.提币51.买入52.卖出
        transactionRecord2.setState(201);//201.支出202.收入
        transactionRecord2.setTransactionCount(number);
        transactionRecord2.setIsShow(1);
        transactionRecord2.setRemark("流通转入存储");
        IcoLockRecord icoLockRecord=new IcoLockRecord();
        icoLockRecord.setCustomerId(customerId);
        icoLockRecord.setCoinCode(coinCode);
        icoLockRecord.setNumber(number);//锁仓金额
        icoLockRecord.setLockDay(lockDay);//锁仓天数
        icoLockRecord.setState(0);
        icoLockRecord.setReleaseDate(hry.util.date.DateUtil.addDaysToDate(new Date(),lockDay));//释放日期
        //TODO 用于测试单为改为分钟
          // icoLockRecord.setReleaseDate(hry.util.date.DateUtil.addMinToDate(new Date(),lockDay));//释放日期

        icoLockRecord.setLockDeduct(lockDeduct);//扣除数量
        icoLockRecord.setLockDeductType(lockDeductType);//扣除类型
        if(isIco){
            icoLockRecord.setType(1);//(1.ico阶段0.非ico)
        }
        IcoAccount icoAccount=icoAccountService.get(new QueryFilter(IcoAccount.class).addFilter("customerId=",customerId));

        icoLockRecord.setCurrentLockSum(icoAccount.getStorageMoney());
        if(lockDeductType.intValue()==2){//抠币
            if(exAccount.getHotMoney().compareTo(lockDeduct)==-1){
                return new JsonResult().setSuccess(false).setMsg("锁仓扣除不足");
            }
            exAccount.setHotMoney(exAccount.getHotMoney().subtract(lockDeduct));
        }else if(lockDeductType.intValue()==1){//扣经验
            //调用扣除账户经验
            /**
             * 给用户添加或减少经验值
             * @param customerId  用户id
             * @param account_type  交易类型（0101 锁仓奖励，0102 注册赠送，0201 锁仓扣除，0202 释放扣除0203月末扣除）
             * @param experience  经验值（无正负）
             * @param money 持币数（交易类型属于 0102时 传null）
             * @param upgradeNote  备注
             * @return
             */
            RemoteResult result=remoteManageService.clearingExperience(Long.valueOf(customerId),"0201",lockDeduct.longValue(),number,"锁仓扣除");
            if(!result.getSuccess()){
                return new JsonResult().setSuccess(false).setObj(result.getObj());
            }
        }
        //2019年3月19日 改为锁仓及时发经验
        Integer hour=lockDay.intValue()*24;
        BigDecimal exPerience=number.multiply(new BigDecimal(hour));
        RemoteResult rewardResult=remoteManageService.clearingExperience(Long.valueOf(customerId),"0101",exPerience.longValue(),number,"锁仓成功奖励");
        if(!rewardResult.getSuccess()){
            return new JsonResult().setSuccess(false).setObj(rewardResult.getObj());
        }
        boolean f=icoAccountService.updateByAccountId(icoAccount.getId(),icoAccount.getVersion(),number);
        if(!f){
            return new JsonResult().setSuccess(false).setMsg("suocangshibai");//锁仓失败
        }
        icoLockRecordService.save(icoLockRecord);//锁仓记录

        if(lockDeductType.intValue()==2){//抠币
            IcoTransactionRecord transactionRecord3=new IcoTransactionRecord();
            transactionRecord3.setCustomerId(customerId);
            transactionRecord3.setProjectNumber(String.valueOf(System.currentTimeMillis()));//流水号
            transactionRecord3.setCoinCode(exAccount.getCoinCode());
            transactionRecord3.setColdMoney(exAccount.getColdMoney());
            transactionRecord3.setHotMoney(exAccount.getHotMoney());
            transactionRecord3.setType(13);// 11.锁仓12.释放13.锁仓扣币21转账31分红32推荐奖励 41.充币42.提币51.买入52.卖出
            transactionRecord3.setState(201);//201.支出202.收入
            transactionRecord3.setTransactionCount(lockDeduct);
            transactionRecord3.setRemark("锁仓扣除币");
            transactionRecord3.setForeignKey(icoLockRecord.getId());
            icoTransactionRecordService.save(transactionRecord3);//交易流水
            Accountadd accountDeduct=ToAccountUtil.expenditureHotAssets(accountId,lockDeduct);
            listLock.add(accountDeduct);//扣除itx
        }
        transactionRecord2.setForeignKey(icoLockRecord.getId());
        icoTransactionRecordService.save(transactionRecord2);//交易流水
        //TODO 测试使用 实际业务最小为1天不需要入队
        // iDelayedOrder.orderDelay(icoLockRecord);//限时队列

        messageProducer.toAccount(JSON.toJSONString(listLock));

        RemoteMessage message=new RemoteMessage();
        message.setMsgKey(MessageType.Message_Lock_Remind.getKey());//消息类型 模板KEY//
        message.setMsgType("1,2");// 1.站内信，2.短信,
        message.setUserId(customerId.toString());
        messageProducer.toMessageWarn(JSON.toJSONString(message));//消息提醒
        return new JsonResult().setSuccess(true).setObj("success");
    }


    /**
     * 获取锁仓明细
     * @param hashMap
     * @return
     */
    @Override
    public FrontPage getLockDetailed(HashMap<String, String> hashMap) {
        String customerId=hashMap.get("customerId");
        String state=hashMap.get("state");//状态(0.待释放1.已释放)
        String page=hashMap.get("page")==null?"0":hashMap.get("page");
        String pageSize=hashMap.get("pageSize")==null?"10":hashMap.get("pageSize");

        String starTime=hashMap.get("starTime");//开始时间
        String endTime=hashMap.get("endTime");//结束时间
        if(Integer.parseInt(pageSize)>100){
            pageSize="10";
        }
        QueryFilter queryFilter=new QueryFilter(IcoLockRecord.class);
        queryFilter.addFilter("customerId=",customerId);
        if(StringUtil.isNull(state)){
            queryFilter.addFilter("state=",state);
        }
        if(StringUtil.isNull(starTime)){
            queryFilter.addFilter("created>=",starTime);
        }
        if(StringUtil.isNull(endTime)){
            queryFilter.addFilter("created<=",endTime);
        }
        queryFilter.setOrderby("created desc");
        queryFilter.setPage(Integer.parseInt(page));
        queryFilter.setPageSize(Integer.parseInt(pageSize));
        Page<IcoLockRecord> orderPage= icoLockRecordService.findPage(queryFilter);
        if(orderPage.size()>0){
            List<RemoteIcoLockRecord> list=ObjectUtil.beanList(orderPage.getResult(),RemoteIcoLockRecord.class);
           // if("0".equals(state)){//待释放获取 扣除的经验
                if(list!=null&&list.size()>0){
                    //非ico阶段获取常规扣除经验值
                    JsonResult jsonResult= getPlatformRule(RulesConfig.RulesCommonKey);
                    BigDecimal releaseExperienceDeduct=null;
                    if(jsonResult.getSuccess()){
                        IcoRulesConfig rulesConfig = ObjectUtil.bean2bean(jsonResult.getObj(), IcoRulesConfig.class);
                        releaseExperienceDeduct=new BigDecimal(rulesConfig.getReleaseExperienceDeduct());
                    }
                    for (RemoteIcoLockRecord lockRecord:list ) {
                        if(lockRecord.getState()==0&&lockRecord.getType()==0){
                             lockRecord.setReleaseDeductType(1);//扣除类型
                             lockRecord.setReleaseDeduct(releaseExperienceDeduct);//释放扣除的经验
                        }
                    }
                }
           // }
            FrontPage frontPage = new FrontPage(list, orderPage.getTotal(), orderPage.getPages(), orderPage.getPageSize());
            return frontPage;
        }
        return new FrontPage(null, 0, 1, 10);
    }
    /**
     * 锁仓扣除信息(优先扣经验，经验不足扣币)
     * @param
     * @return
     */
    @Override
    public JsonResult getLockDeductionInfo(Long customerId) {
        //String customerId=hashMap.get("customerId");//用户Id
        RemoteLockDeduction remoteLockDeduction=new RemoteLockDeduction();
        JsonResult isIco=isCheckICOTime();
        Long deductionItx=null; //扣除币量
        Long deductionExp=null; //扣除经验
        Integer lockDeductType=1; //扣除类型（优先扣除经验）
        // 调用获取用户当前经验值总数
        JsonResult levelResult= seeCustomerLevelAccount(customerId);
        if(!levelResult.getSuccess()){
            return levelResult;
        }
        IcoCustomerLevel level=ObjectUtil.bean2bean( levelResult.getObj(),IcoCustomerLevel.class);
        Long userExperience=level.getExperience();//用户当前经验值
        if(isIco.getSuccess()){
            //ico阶段获取ico扣除经验值
            JsonResult jsonResult=getPlatformRule(RulesConfig.RulesICOKey);
            IcoRulesConfig rulesConfig=ObjectUtil.bean2bean(jsonResult.getObj(),IcoRulesConfig.class);
            deductionItx= Long.valueOf(rulesConfig.getIcoLockItxDeduct());
            deductionExp= Long.valueOf(rulesConfig.getIcoLockExperienceDeduct());
        }else{
            //非ico阶段获取常规扣除经验值
            JsonResult jsonResult= getPlatformRule(RulesConfig.RulesCommonKey);
            IcoRulesConfig rulesConfig=ObjectUtil.bean2bean(jsonResult.getObj(),IcoRulesConfig.class);
            deductionItx= Long.valueOf(rulesConfig.getItxDeduct());
            deductionExp= Long.valueOf(rulesConfig.getLockExperienceDeduct());
        }
        if(userExperience>=deductionExp){//当前经验足够扣除
            remoteLockDeduction.setLockDeduct(new BigDecimal(deductionExp));
        }else{
            lockDeductType=2;//扣除币itx
            remoteLockDeduction.setLockDeduct(new BigDecimal(deductionItx));
        }
        remoteLockDeduction.setLockDeductType(lockDeductType);
        return new JsonResult().setSuccess(true).setObj(remoteLockDeduction).setMsg("success");
    }

    /**
     * 获取ico币账户信息
     * @param hashMap
     * @return
     */
    @Override
    public JsonResult getIcoAccountInfo(HashMap<String, String> hashMap) {
        String customerId=hashMap.get("customerId");//用户Id
        RemoteIcoAccount result=new RemoteIcoAccount();
        // 查redis缓存
        RedisUtil<UserRedis> redisUtil = new RedisUtil<UserRedis>(UserRedis.class);
        UserRedis userRedis = redisUtil.get(customerId.toString());
        IcoAccount icoAccount= icoAccountService.get(new QueryFilter(IcoAccount.class).addFilter("customerId=",customerId));
        String coinCode=icoAccount.getCoinCode();
        ExDigitalmoneyAccountRedis exaccount = UserRedisUtils.getAccount(userRedis.getDmAccountId(coinCode).toString(), coinCode);
        //保留的小数位
        int keepDigit=coinCodeKeepDigitService.getCoinCodeKeepDigit(coinCode);
        result.setKeepDigit(keepDigit);
        result.setCoinCode(exaccount.getCoinCode());
        result.setHotMoney( BigDecimalUtil.bigDecimalScaleDigit(exaccount.getHotMoney(),keepDigit));//可用
        result.setColdMoney( BigDecimalUtil.bigDecimalScaleDigit(exaccount.getColdMoney(),keepDigit));//冻结
        result.setStorageMoney(BigDecimalUtil.bigDecimalScaleDigit(icoAccount.getStorageMoney(),keepDigit));
        result.setTotalMoney(BigDecimalUtil.bigDecimalScaleDigit(exaccount.getHotMoney().add(exaccount.getColdMoney()),keepDigit));//总数
        //自身锁仓数/所有用户的总锁仓数)*40%*(100%+等级加持百分比)=锁仓比
       JsonResult levelAccount= seeCustomerLevelAccount(Long.valueOf(customerId));
       if(levelAccount.getSuccess()){
           RemoteIcoCustomerLevel remoteIcoCustomerLevel = ObjectUtil.bean2bean(levelAccount.getObj(), RemoteIcoCustomerLevel.class);

           BigDecimal additionRatio= (new BigDecimal(remoteIcoCustomerLevel.getAdditionRatio()).add(new BigDecimal(100)))
                   .divide(new BigDecimal(100),8,BigDecimal.ROUND_DOWN);//等级加成比率
           //自身锁仓占比 6位小数
           String atio=icoAccountService.getMemberLockAtio(Long.valueOf(customerId));
           //RATIO 40%  100%+等级加持百分比
           BigDecimal accountAtio=new BigDecimal(atio).multiply(additionRatio).multiply(new BigDecimal(RATIO));
           result.setAccountAtio(accountAtio.setScale(6,BigDecimal.ROUND_DOWN));//自身锁仓比率
       }
        return new JsonResult().setSuccess(true).setObj(result);
    }

    /**
     * 转账接口
     * @param hashMap
     * @return
     */
    @Override
    public JsonResult transferAccounts(HashMap<String, String> hashMap) {
        String customerId=hashMap.get("customerId");//用户Id
        String coinCode=hashMap.get("coinCode");//转账的币种
        BigDecimal number=new BigDecimal(hashMap.get("number"));//转账的数量
        String toPublickKey=hashMap.get("toPublickKey");//收入方的币种地址
        if(toPublickKey==null||"".equals(toPublickKey)){
            return new JsonResult().setSuccess(false).setMsg("shourufangdizhicuowu");//收入方的地址错误
        }
        // 查redis缓存
        RedisUtil<UserRedis> redisUtil = new RedisUtil<UserRedis>(UserRedis.class);
        UserRedis userRedis = redisUtil.get(customerId);
        ExDigitalmoneyAccountRedis exaccount = UserRedisUtils.getAccount(userRedis.getDmAccountId(coinCode).toString(), coinCode);
         if(exaccount==null){
               return new JsonResult().setSuccess(false).setMsg("bizhanghubucunzai");//币种账户不存在
          }
        if(exaccount.getHotMoney().compareTo(number)==-1){//资金不足
            return new JsonResult().setSuccess(false).setMsg("zijinbuzu");//资金不足
        }
        exaccount.setHotMoney(exaccount.getHotMoney().subtract(number));
        ExDigitalmoneyAccount exDigitalmoneyAccount=exDigitalmoneyAccountService.get(new QueryFilter(ExDigitalmoneyAccount.class).addFilter("publicKey=",toPublickKey).addFilter("coinCode=",coinCode));
        if(exDigitalmoneyAccount==null){
            return new JsonResult().setSuccess(false).setMsg("shourufangdizhicuowu");//收入方的地址错误
        }
        String toCustomerId=exDigitalmoneyAccount.getCustomerId().toString();
        if(toCustomerId.equals(customerId)){
            return new JsonResult().setSuccess(false).setMsg("bunengxiangzijizhuanzhang");//不能向自己转账
        }
        List<Accountadd>listLock=new ArrayList<>();

        UserRedis toUserRedis = redisUtil.get(toCustomerId);
        ExDigitalmoneyAccountRedis toExaccount = UserRedisUtils.getAccount(toUserRedis.getDmAccountId(coinCode).toString(), coinCode);
        toExaccount.setHotMoney(toExaccount.getHotMoney().add(number));
        Long accountId = userRedis.getDmAccountId(coinCode);
        Long toAccountId = toUserRedis.getDmAccountId(coinCode);//收入方账户ID
        Accountadd accountadd=ToAccountUtil.expenditureHotAssets(accountId,number);//支出
        Accountadd toAccountadd=ToAccountUtil.ncomeAssets(toAccountId,number);//收入
        listLock.add(accountadd);
        listLock.add(toAccountadd);

        String serialNumber=String.valueOf(System.currentTimeMillis());
        IcoTransactionRecord transactionRecord=new IcoTransactionRecord();
        transactionRecord.setCustomerId(Long.valueOf(customerId));
        transactionRecord.setProjectNumber(serialNumber);//流水号
        transactionRecord.setCoinCode(exaccount.getCoinCode());
        transactionRecord.setColdMoney(exaccount.getColdMoney());
        transactionRecord.setHotMoney(exaccount.getHotMoney());
        transactionRecord.setType(21);// 11.锁仓12.释放13.锁仓扣币21转账入22转账出31分红32推荐奖励 41.充币42.提币51.买入52.卖出
        transactionRecord.setState(201);//201.支出202.收入
        transactionRecord.setTransactionCount(number);
        transactionRecord.setIsShow(1);
        transactionRecord.setRemark("转账支出");

        IcoTransactionRecord transactionRecord2=new IcoTransactionRecord();
        transactionRecord2.setCustomerId(Long.valueOf(toCustomerId));
        transactionRecord2.setProjectNumber(serialNumber);//流水号
        transactionRecord2.setCoinCode(toExaccount.getCoinCode());
        transactionRecord2.setColdMoney(toExaccount.getColdMoney());
        transactionRecord2.setHotMoney(toExaccount.getHotMoney());
        transactionRecord2.setType(22);// 11.锁仓12.释放13.锁仓扣币21转账入22转账出31分红32推荐奖励 41.充币42.提币51.买入52.卖出
        transactionRecord2.setState(202);//201.支出202.收入
        transactionRecord2.setTransactionCount(number);
        transactionRecord2.setIsShow(1);
        transactionRecord2.setRemark("转账收入");
        IcoTransferAccounts transferAccounts=new IcoTransferAccounts();
        transferAccounts.setSerialNumber(serialNumber);
        transferAccounts.setCoinCode(coinCode);
        transferAccounts.setCustomerId(Long.valueOf(customerId));
        transferAccounts.setToCustomerId(Long.valueOf(toCustomerId));
        transferAccounts.setMoney(number);
        transferAccounts.setRemark("转账记录");
        icoTransferAccountsService.save(transferAccounts);//转账记录表
        transactionRecord.setForeignKey(transferAccounts.getId());
        transactionRecord2.setForeignKey(transferAccounts.getId());
        icoTransactionRecordService.save(transactionRecord);//交易流水
        icoTransactionRecordService.save(transactionRecord2);//交易流水

        messageProducer.toAccount(JSON.toJSONString(listLock));
        RemoteMessage message=new RemoteMessage();
        message.setMsgKey(MessageType.Message_RollOut_Reminder.getKey());//消息类型 模板KEY//
        message.setMsgType("1,2");// 1.站内信，2.短信,
        message.setUserId(customerId);
        messageProducer.toMessageWarn(JSON.toJSONString(message));//消息提醒
        message.setMsgKey(MessageType.Message_RoolIn_Reminder.getKey());//消息类型 模板KEY//
        message.setMsgType("1,2");// 1.站内信，2.短信,
        message.setUserId(toCustomerId);
        messageProducer.toMessageWarn(JSON.toJSONString(message));//消息提醒

        return new JsonResult().setSuccess(true).setMsg("success");
    }


    @Override
    public  JsonResult addCustomerLevelAccount(Long customerId){
        try{
            IcoCustomerLevel icoCustomerLevel = new IcoCustomerLevel();
            RemoteResult remoteResult = remoteManageService.countLevel(0L);
            if (remoteResult.getSuccess()){
                IcoUpgradeConfig icoUpgradeConfig =(IcoUpgradeConfig) remoteResult.getObj();
                icoCustomerLevel.setLevel_id(icoUpgradeConfig.getId());
                icoCustomerLevel.setGradeName(icoUpgradeConfig.getGradeName());
                icoCustomerLevel.setSort(icoUpgradeConfig.getSort());
            }
            icoCustomerLevel.setCustomer_id(customerId);
            icoCustomerLevel.setExperience(0L);
            icoCustomerLevelService.save(icoCustomerLevel);
            return new JsonResult().setSuccess(true).setObj(icoCustomerLevel).setMsg("success");
        }catch (Exception e){
            e.printStackTrace();
            return new JsonResult().setSuccess(false).setMsg("tianjiadengjizhanghushibai");//添加等级账户失败
        }
    }
    /**
     * 查看用户推荐等级
     * @return
     */
    public  JsonResult seeCustomerLevelAccount(Long customerId){
        try{
            QueryFilter queryFilter = new QueryFilter(IcoCustomerLevel.class);
            queryFilter.addFilter("customer_id=",customerId);
            IcoCustomerLevel icoCustomerLevel = icoCustomerLevelService.get(queryFilter);
            //一个用户只有一个账户等级
            RemoteIcoCustomerLevel remoteIcoCustomerLevel = ObjectUtil.bean2bean(icoCustomerLevel, RemoteIcoCustomerLevel.class);
            Long levelId= icoCustomerLevel.getLevel_id();
            IcoUpgradeConfig upgradeConfig= upgradeConfigService.get(levelId);
            if(upgradeConfig!=null){ //等级加成比率
                remoteIcoCustomerLevel.setAdditionRatio(upgradeConfig.getAdditionRatio());
            }
            return new JsonResult().setSuccess(true).setObj(remoteIcoCustomerLevel).setMsg("success");
        }catch (Exception e){
            e.printStackTrace();
            return new JsonResult().setSuccess(false).setMsg("xitongyichang");//系统异常

        }
    }


    @Override
    public  FrontPage queryExperiencesDetail(HashMap<String, String> map){

        //----------------------查询开始------------------------------
        String date=map.get("date");//时间
        if(StringUtil.isNull(date)){
            String startTime=date+"-01";
            map.put("startTime",startTime);
           try{
               SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
               Date dt = sdf.parse(startTime);
               Calendar rightNow = Calendar.getInstance();
               rightNow.setTime(dt);
               rightNow.add(Calendar.MONTH, 1);
               Date dt1 = rightNow.getTime();
               String endTime = sdf.format(dt1);
               map.put("endTime",endTime);
           }catch (Exception e){
               e.printStackTrace();
           }

        }
        FrontPage frontPage = icoExperienceService.queryExperiencesDetail(map);
        return frontPage;

    }


    /**
     * 获取分红记录
     * @param hashMap
     * @return
     */
    @Override
    public FrontPage queryDividendRecord(HashMap<String, String> hashMap){
        String startTime=(String)hashMap.get("startTime");//开始时间
        String endTime=(String)hashMap.get("endTime");//结束时间
        String coinCode=(String)hashMap.get("coinCode");//结束时间
        if(StringUtil.isNull(coinCode)){
            hashMap.put("coinCode","%"+coinCode+"%");
        }
        if(startTime==null||endTime==null){//默认查询当天的
            String now= DateUtil.getNowDate();
            hashMap.put("startTime",now);
            hashMap.put("endTime",now);
        }
        FrontPage frontPage = icoDividendRecordService.queryDividendRecord(hashMap);
        return frontPage;
    }



    /**
     * 获取用户预测等级和当前等级
     * @param map
     * @return
     */
    @Override
    public JsonResult predictUserLevel(Map<String, Object> map){
        try{
            Long customerId = Long.valueOf(map.get("customerId").toString());
            //返回值
            HashMap<String, Object> hashMap = new HashMap<>();
            //获取推荐人信息
            AppCustomer appCustomer = appCustomerService.get(customerId);
            QueryFilter queryFilterAppCommendUser = new QueryFilter(AppCommendUser.class);
            queryFilterAppCommendUser.addFilter("uid=",customerId);
            List<AppCommendUser> appCommendUsers = appCommendUserService.find(queryFilterAppCommendUser);
            String pname="";//没查到上级信息
            if(null != appCommendUsers && appCommendUsers.size() > 0 ){
                QueryFilter queryFilterAppPersonInfo = new QueryFilter(AppPersonInfo.class);
                queryFilterAppPersonInfo.addFilter("customerId=",appCommendUsers.get(0).getPid());
                List<AppPersonInfo> appPersonInfos = appPersonInfoService.find(queryFilterAppPersonInfo);
                if(null != appPersonInfos && appPersonInfos.size() > 0 ){
                    AppPersonInfo appPersonInfo = appPersonInfos.get(0);
                    if(null != appPersonInfo.getMobilePhone() && !appPersonInfo.getMobilePhone().equals("") ){
                        pname=appPersonInfo.getMobilePhone();
                    }else {
                        pname=appPersonInfo.getEmail();
                    }
                }
            }
            //获取用户当前等级和经验
            JsonResult jsonResult = this.seeCustomerLevelAccount(customerId);
            RemoteIcoCustomerLevel remoteIcoCustomerLevel = (RemoteIcoCustomerLevel)jsonResult.getObj();
            QueryFilter queryFilterIcoAccount = new QueryFilter(IcoAccount.class);
            queryFilterIcoAccount.addFilter("customerId=",customerId);
            IcoAccount icoAccount = icoAccountService.get(queryFilterIcoAccount);
            //当前经验
            Long nowExperience = remoteIcoCustomerLevel.getExperience();
            hashMap.put("created",appCustomer.getCreated());//注册时间
            hashMap.put("modified",icoAccount.getModified());//更新时间
            hashMap.put("nowGradeName",remoteIcoCustomerLevel.getGradeName());
            hashMap.put("pname",pname);//上级
            hashMap.put("nowExperience",nowExperience);//当前经验
            return new JsonResult().setSuccess(true).setObj(hashMap).setMsg("success");

        }catch (Exception e){
            return new JsonResult().setSuccess(false).setMsg("xitongyichang");//系统异常

        }
    }


    //计算俩时间的小时数差
    private static int differHourNum(long time1,long time2){
//        long dif=(time2-time1)/(60*60*1000);
        long dif=((time2/1000)-(time1/1000))/(60*60);
        return ((Long) dif).intValue();
    }


 /*   public static void main(String[] args) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String datec = df.format(1552291200000L);// new Date()为获取当前系统时间，也可使用当前时间戳
        System.out.println(datec);
        //        System.out.println(new Date(1552290314000l));
        String dater = df.format(1552463114000L);// new Date()为获取当前系统时间，也可使用当前时间戳
        System.out.println(dater);

//        SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
//
//        String date1 = df1.format(1551686884167L);// new Date()为获取当前系统时间，也可使用当前时间戳
//        int datec = Integer.valueOf("0000");
//        System.out.println("11111:  "+date1);
//        System.out.println(datec);
        int hour=differHourNum(1552291200000L,1552463114000L);//计算上次相差小时数
        System.out.println("hour:  "+hour);
        SimpleDateFormat dfymd = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        String formatc = dfymd.format(1552291200000L);
        String formatr = dfymd.format(1552463114000L);
        //非当天到期。更新时间的分秒比释放时间的分秒小。小时数减1
        System.out.println(formatc);
        System.out.println(formatr);
        System.out.println(!formatc.equals(formatr));
//获取分秒

        if (!formatc.equals(formatr)){
            SimpleDateFormat dfms = new SimpleDateFormat("mmss");//设置日期格式
            int datecs = Integer.valueOf(dfms.format(1552291200000L));
            //如果更新时间的分秒比释放时间的分秒更早，则需要再减1个小时
            int daters = Integer.valueOf(dfms.format(1552463114000L));
            System.out.println("datecs:  "+datecs+"        daters: "+daters);

            System.out.println("T");
        }else{
            System.out.println("F");
        }

    }*/
}
