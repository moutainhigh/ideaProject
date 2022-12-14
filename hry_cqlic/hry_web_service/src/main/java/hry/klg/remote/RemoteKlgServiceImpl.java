/**
 * 111
 */

package hry.klg.remote;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import hry.bean.FrontPage;
import hry.bean.JsonResult;
import hry.bean.ObjectUtil;
import hry.calculate.util.DateUtil;
import hry.customer.person.model.AppPersonInfo;
import hry.customer.person.service.AppPersonInfoService;
import hry.exchange.account.service.ExDigitalmoneyAccountService;
import hry.front.redis.model.UserRedis;
import hry.klg.assetsrecord.model.KlgAssetsRecord;
import hry.klg.assetsrecord.service.KlgAssetsRecordService;
import hry.klg.enums.TriggerPointEnum;
import hry.klg.level.model.KlgCustomerLevel;
import hry.klg.level.model.KlgLevelConfig;
import hry.klg.level.model.KlgLevelCount;
import hry.klg.level.service.KlgCustomerLevelService;
import hry.klg.level.service.KlgLevelConfigService;
import hry.klg.level.service.KlgTeamlevelService;
import hry.klg.model.KlgRulesConfig;
import hry.klg.model.PlatformAccountadd;
import hry.klg.model.RemoteLevelConfig;
import hry.klg.model.RulesConfig;
import hry.klg.platform.model.AccountUtil;
import hry.klg.platform.model.KlgPlatformAccountRecord;
import hry.klg.platform.model.PlatformAccountUtil;
import hry.klg.platform.service.KlgPlatformAccountRecordService;
import hry.klg.platform.service.KlgPlatformAccountService;
import hry.klg.remote.model.*;
import hry.klg.transaction.model.KlgBuyTransaction;
import hry.klg.transaction.model.KlgSellTransaction;
import hry.klg.transaction.service.KlgBuyTransactionService;
import hry.klg.transaction.service.KlgSellTransactionService;
import hry.klg.transfer.model.KlgTransferAccounts;
import hry.klg.transfer.service.KlgTransferAccountsService;
import hry.message.model.MessageType;
import hry.message.model.RemoteMessage;
import hry.mq.producer.service.MessageProducer;
import hry.redis.common.dao.RedisUtil;
import hry.redis.common.utils.RedisService;
import hry.trade.redis.model.Accountadd;
import hry.trade.redis.model.ExDigitalmoneyAccountRedis;
import hry.util.QueryFilter;
import hry.util.StringUtil;
import hry.util.idgenerate.IdGenerate;
import hry.util.idgenerate.NumConstant;
import hry.util.sys.ContextUtil;
import net.sf.json.JSONObject;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import util.ToAccountUtil;
import util.UserRedisUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

public class RemoteKlgServiceImpl implements  RemoteKlgService {
    //??????????????????
    private final static String PAYCOINCODE="USDT";
    //??????
    private final static  String TRANSFER="TRANSFER:";

    @Resource
    private RedisService redisService;
    @Resource
    private MessageProducer messageProducer;
    @Resource
    private KlgPlatformAccountRecordService platformAccountRecordService;
    @Resource
    private KlgPlatformAccountService platformAccountService;
    @Resource
    private ExDigitalmoneyAccountService exDigitalmoneyAccountService;
    @Resource
    private KlgBuyTransactionService buyTransactionService;
    @Resource
    private KlgSellTransactionService klgSellTransactionService;
    @Resource
    private KlgCustomerLevelService klgCustomerLevelService;
    @Resource
    private KlgLevelConfigService klgLevelConfigService;
    @Resource
    private KlgAssetsRecordService klgAssetsRecordService;
    @Resource
    private KlgTransferAccountsService klgTransferAccountsService;
    @Resource
    private KlgTeamlevelService klgTeamlevelService;
    @Resource
    private KlgBuyTransactionService klgBuyTransactionService;
    @Resource
	private RemoteKlgService remoteKlgService;
    @Resource
    private AppPersonInfoService appPersonInfoService;
    @Resource
    private RemoteKlgLuckDrawService remoteKlgLuckDrawService;
    /**
     * ??????????????????
     * @return
     */
    @Override
    public Object getPlatformRule1sConfig(String key) {
        KlgRulesConfig rulesConfig=new KlgRulesConfig();
        if(StringUtil.isNull(key)){
            if("isOpen".equals(key)){
                String isOpen=redisService.get(RulesConfig.RulesIsOpen);
                return isOpen==null?"0":"1";
            }
           return redisService.getMap(RulesConfig.RulesCoinLikeKey,key);
        }
        Map<String,String> cfMap=redisService.getMap(RulesConfig.RulesCoinLikeKey);
        String isOpen=redisService.get(RulesConfig.RulesIsOpen)==null?"0":"1";
        rulesConfig= ObjectUtil.bean2bean(cfMap,KlgRulesConfig.class);
        rulesConfig.setIsOpen(isOpen);
        return rulesConfig;
    }

    /**
     * ??????????????????
     * @return
     */
    @Override
    public JsonResult isCheckOpenTime() {
        KlgRulesConfig rulesConfig= (KlgRulesConfig)getPlatformRule1sConfig(null);
        if("1".equals( rulesConfig.getIsOpen())){//???????????? 0???1???
            String startTime= rulesConfig.getStartTime();//????????????
                String endTime= rulesConfig.getEndTime();//????????????
                boolean fl= DateUtil.isCheackTime(startTime,endTime);
                if(fl){
                    return new JsonResult().setSuccess(true).setObj(rulesConfig);
                }
          }
        return new JsonResult().setSuccess(false);
    }

    /**
     * ????????????????????????
     * @param customerId
     * @param number
     * @return
     */
    @Override
    public Boolean isCheckLimitMoney(Long customerId, BigDecimal number) {
        boolean check=false;
        KlgCustomerLevel customerLevel=klgCustomerLevelService.getLevelConfigByCustomerId(customerId);
        Long levelId=customerLevel.getLevelId();//??????Id
        RemoteLevelConfig levelConfig=getLevelConfigByLevelId(levelId);
        if(levelConfig!=null){
//            BigDecimal buyNum=levelConfig.getBuyNum();//????????????
            String[] buyNums=levelConfig.getBuyNums().split(",");//????????????
            boolean flg= checkDowngradeDay(customerId);
            if(flg){//??????15??? ??????????????? 1????????????
                QueryFilter query=new QueryFilter(KlgLevelConfig.class);
                query.setOrderby("sort asc");
                KlgLevelConfig levelConfig1= klgLevelConfigService.get(query);
                buyNums=levelConfig1.getBuyNums().split(",");
            }else{
                 String oldNum=getOldPurchaseNum(customerId);
                if(oldNum!=null){
                       String[] st= getBuyNums(buyNums,oldNum);
                      buyNums=st;
                }
            }
            if(buyNums!=null){
                List<String> str=Arrays.asList(buyNums);
                String num=number.stripTrailingZeros().toPlainString();
                return str.contains(num);
            }
        }
        return check;
    }

    /**
     * ??????????????????15???
     * @param customerId
     * @return
     */
    private  boolean checkDowngradeDay(Long customerId){
        Integer limitday= Integer.valueOf((String)getPlatformRule1sConfig("downgradeDays"));//??????
         boolean flag=klgBuyTransactionService.checkIntervalByday(customerId,limitday);
        if(flag){//????????????????????????// ????????????
            return true;
        }
        return false;
    }
    @Override
    public JsonResult platformTransfer(Map<String, String> hMap) {
        String  account= hMap.get("account");
        String toAccount=  hMap.get("toAccount");
        String money=  hMap.get("money");
        Boolean lock =redisService.lock(TRANSFER+account);
        if(!lock){
            return new JsonResult().setSuccess(false);
        }
        try {
            String num= platformAccountService.getPlatformAccountByAccountType(account);
            String tonum= platformAccountService.getPlatformAccountByAccountType(toAccount);
            if(new BigDecimal(num).compareTo(new BigDecimal(money))==-1){
                return new JsonResult().setSuccess(false).setMsg("zijinbuzu");//????????????
            }
            List<PlatformAccountadd> platformAccounts=new ArrayList<>();

          //  KlgPlatformAccountRecord accountRecord=new KlgPlatformAccountRecord();
           // KlgPlatformAccountRecord toAccountRecord=new KlgPlatformAccountRecord();
        //    String serialNumber= IdGenerate.transactionNum(NumConstant.Ex_Dm_Transaction);
      /*      accountRecord.setMoney(new BigDecimal(money));
            accountRecord.setNowMoney(new BigDecimal(num));
            accountRecord.setRemark("??????");
            accountRecord.setType(102);
            accountRecord.setAccountId(account);
            accountRecord.setSerialNumber(serialNumber);

            toAccountRecord.setMoney(new BigDecimal(money));
            toAccountRecord.setNowMoney(new BigDecimal(tonum));
            toAccountRecord.setRemark("??????");
            toAccountRecord.setType(101);
            toAccountRecord.setAccountId(toAccount);
            toAccountRecord.setSerialNumber(serialNumber);
            platformAccountRecordService.save(accountRecord);
            platformAccountRecordService.save(toAccountRecord);*/

            List<PlatformAccountadd> pAccounts=PlatformAccountUtil.ransfer(account,toAccount,money);
            platformAccounts.addAll(pAccounts);
            messageProducer.toPlatformCurrency(JSON.toJSONString(platformAccounts));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            redisService.unLock(TRANSFER+account);
        }
        return new JsonResult().setSuccess(true);
    }

    /**
     * ????????????
     * @param hashMap
     * @return
     */
    @Override
    public JsonResult transferAccounts(HashMap<String, String> hashMap) {
        String customerId=hashMap.get("customerId");//??????Id
        String coinCode=hashMap.get("coinCode");//???????????????
        BigDecimal number=new BigDecimal(hashMap.get("number"));//???????????????
        String toAccount=hashMap.get("toAccount");//????????????????????????
        QueryFilter filter = new QueryFilter(AppPersonInfo.class);
        filter.or("email=",toAccount);
        filter.or("mobilePhone=",toAccount);
        AppPersonInfo _appPersonInfo = appPersonInfoService.get(filter);
        if(toAccount==null||_appPersonInfo==null){
            return new JsonResult().setSuccess(false).setMsg("shourufangdizhicuowu");//????????????????????????
        }
        // ???redis??????
        RedisUtil<UserRedis> redisUtil = new RedisUtil<UserRedis>(UserRedis.class);
        UserRedis userRedis = redisUtil.get(customerId);
        ExDigitalmoneyAccountRedis exaccount = UserRedisUtils.getAccount(userRedis.getDmAccountId(coinCode).toString(), coinCode);
        if(exaccount==null){
            return new JsonResult().setSuccess(false).setMsg("bizhanghubucunzai");//?????????????????????
        }
        if(exaccount.getHotMoney().compareTo(number)==-1){//????????????
            return new JsonResult().setSuccess(false).setMsg("zijinbuzu");//????????????
        }
        exaccount.setHotMoney(exaccount.getHotMoney().subtract(number));
        String toCustomerId=_appPersonInfo.getCustomerId().toString();
        if(toCustomerId.equals(customerId)){
            return new JsonResult().setSuccess(false).setMsg("bunengxiangzijizhuanzhang");//?????????????????????
        }
        List<Accountadd> listLock=new ArrayList<>();

        UserRedis toUserRedis = redisUtil.get(toCustomerId);
        ExDigitalmoneyAccountRedis toExaccount = UserRedisUtils.getAccount(toUserRedis.getDmAccountId(coinCode).toString(), coinCode);
        toExaccount.setHotMoney(toExaccount.getHotMoney().add(number));
        Long accountId = userRedis.getDmAccountId(coinCode);
        Long toAccountId = toUserRedis.getDmAccountId(coinCode);//???????????????ID
        Accountadd accountadd= ToAccountUtil.expenditureHotAssets(accountId,number);//??????
        Accountadd toAccountadd=ToAccountUtil.ncomeAssets(toAccountId,number);//??????
        listLock.add(accountadd);
        listLock.add(toAccountadd);

        String serialNumber=IdGenerate.transactionNum(NumConstant.Ex_Dm_Transaction);

        KlgAssetsRecord klgAssetsRecord=new KlgAssetsRecord();//????????????
        klgAssetsRecord.setCustomerId(Long.parseLong(customerId));//??????Id
        klgAssetsRecord.setAccountId(accountId);//?????????ID
        klgAssetsRecord.setSerialNumber(serialNumber);//?????????
        klgAssetsRecord.setAccountType(1);  //???????????? 1.????????? 2.?????????
        klgAssetsRecord.setCoinCode(coinCode);//??????Code
        klgAssetsRecord.setChangeCount(number); //?????????
        klgAssetsRecord.setChangeType(2);////???????????? 1??? 2???
        TriggerPointEnum triggerPoint=TriggerPointEnum.TransferAccounts;//?????? ??????
        klgAssetsRecord.setTriggerPoint(triggerPoint.getKey());//?????????
        klgAssetsRecord.setTriggerSerialNumber(serialNumber); //??????????????????
        klgAssetsRecord.setRemark("??????");

        KlgAssetsRecord klgAssetsRecord2=new KlgAssetsRecord();//????????????
        klgAssetsRecord2.setCustomerId(Long.parseLong(toCustomerId));//??????Id
        klgAssetsRecord2.setAccountId(toAccountId);//?????????ID
        klgAssetsRecord2.setSerialNumber(serialNumber);//?????????
        klgAssetsRecord2.setAccountType(1);  //???????????? 1.????????? 2.?????????
        klgAssetsRecord2.setCoinCode(coinCode);//??????Code
        klgAssetsRecord2.setChangeCount(number); //?????????
        klgAssetsRecord2.setChangeType(1);////???????????? 1??? 2???
        klgAssetsRecord2.setTriggerPoint(triggerPoint.getKey());//?????????
        klgAssetsRecord2.setTriggerSerialNumber(serialNumber); //??????????????????
        klgAssetsRecord2.setRemark("??????");
        KlgTransferAccounts transferAccounts=new KlgTransferAccounts();
        transferAccounts.setSerialNumber(serialNumber);
        transferAccounts.setCoinCode(coinCode);
        transferAccounts.setCustomerId(Long.valueOf(customerId));
        transferAccounts.setToCustomerId(Long.valueOf(toCustomerId));
        transferAccounts.setMoney(number);
        transferAccounts.setRemark("????????????");
        klgTransferAccountsService.save(transferAccounts);//???????????????
        klgAssetsRecordService.save(klgAssetsRecord);//????????????
        klgAssetsRecordService.save(klgAssetsRecord2);//????????????*/

        Map<String, String> paramM = new HashMap<>();
        paramM.put("${number}", hashMap.get("number"));//??????
        String toPhone=toAccount.replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2");
        paramM.put("${toAccount}", toPhone);//??????
        RemoteMessage message=new RemoteMessage();
        message.setParam(paramM);
        message.setMsgKey(MessageType.MESSAGE_KLG_ACCOUNT_CONFIRMOUT.getKey());//???????????? ??????KEY//
        message.setMsgType("1,2");// 1.????????????2.??????,
        message.setUserId(customerId.toString());
        messageProducer.toMessageWarn(JSON.toJSONString(message));//????????????

        messageProducer.toAccount(JSON.toJSONString(listLock));
        return new JsonResult().setSuccess(true).setMsg("success");
    }

    @Override
    public Object getPlatformAccount(String accountType) {
        String num= platformAccountService.getPlatformAccountByAccountType(accountType);
        return  num;
    }

    @Override
    public JsonResult PlatformAccountAddQueue(String accoutadds) {
        JedisPool jedisPool = (JedisPool) ContextUtil.getBean("jedisPool");
        Jedis jedis=jedisPool.getResource();
        try {
            List<AccountUtil> utils=new ArrayList<>();
            List<PlatformAccountadd> accountaddlist = JSON.parseArray(accoutadds, PlatformAccountadd.class);
            for (PlatformAccountadd accountadd:accountaddlist) {
                String type=accountadd.getAccountType();
                String m=accountadd.getMoney();
                String num= (String) getPlatformAccount(type);
                String  money= new BigDecimal(num).add(new BigDecimal(m)).stripTrailingZeros().toPlainString();
                String redisKey=RulesConfig.PLATFORM_NUMBER+type;
                utils.add(new AccountUtil(redisKey,money));
                KlgPlatformAccountRecord accountRecord=new KlgPlatformAccountRecord();//??????????????????
                accountRecord.setMoney(new BigDecimal(m));
                accountRecord.setNowMoney(new BigDecimal(num));
                accountRecord.setType(accountadd.getType());
                accountRecord.setAccountId(accountadd.getAccountType());
                accountRecord.setSerialNumber(accountadd.getSerialNumber());
                accountRecord.setRemark(accountadd.getRemark());
                platformAccountRecordService.save(accountRecord);//????????????
                platformAccountService.updatePlatformAccount(money,type);//??????
            }
            if(utils!=null&&utils.size()>0){
                redis.clients.jedis.Transaction tx = jedis.multi();
                for (AccountUtil acu:utils) {
                    tx.set(acu.getRedisAccountKey(),acu.getMoney());
                }
                List<Object> list =tx.exec();
                if(null==list||list.size()==0){
                     throw new RuntimeException();
                }
            }
        }catch(Exception e	) {
                throw e;
        }finally {
            jedis.close();
        }
        return new JsonResult().setSuccess(true);
    }

    /**
     * ????????????
     * ??????20%??????
     * @param hMap
     * @return
     */
    @Override
    public JsonResult appointmentPurchase(Map<String, String> hMap) {
        String customerId=hMap.get("customerId");//??????Id
        String password=hMap.get("password");//??????
        String isTrusteeship=hMap.get("isTrusteeship");//???????????? 1??? 2???
        if(isTrusteeship==null)
            isTrusteeship="1";
        String number=hMap.get("number");//????????????
        String price=(String) getPlatformRule1sConfig("issuePrice");//????????????
        String marginRatio=(String) getPlatformRule1sConfig("marginRatio");//???????????????
        BigDecimal ratio=new BigDecimal(marginRatio).divide(new BigDecimal(100));
        KlgCustomerLevel customerLevel=klgCustomerLevelService.getLevelConfigByCustomerId(Long.parseLong(customerId));
        Long levelId=customerLevel.getLevelId();//??????Id
        // ???redis??????
        RedisUtil<UserRedis> redisUtil = new RedisUtil<UserRedis>(UserRedis.class);
        UserRedis userRedis = redisUtil.get(customerId);
        ExDigitalmoneyAccountRedis exaccount = UserRedisUtils.getAccount(userRedis.getDmAccountId(PAYCOINCODE).toString(), PAYCOINCODE);
        if(exaccount==null){
            return new JsonResult().setSuccess(false).setMsg("bizhanghubucunzai");//?????????????????????
        }
        BigDecimal totalMoney=new BigDecimal(number).multiply(new BigDecimal(price));//????????????
        BigDecimal firstMoney=totalMoney.multiply(ratio);//????????????
        if(exaccount.getHotMoney().compareTo(firstMoney)==-1){
            return new JsonResult().setSuccess(false).setMsg("zijinbuzu");//????????????
        }
        List<Accountadd> listLock=new ArrayList<>();//????????????
       // List<PlatformAccountadd> platformAccounts=new ArrayList<>();//????????????
        Long accountId = userRedis.getDmAccountId(PAYCOINCODE);//
        List<Accountadd> accountadd= ToAccountUtil.frozenAssets(accountId,firstMoney);//??????
        listLock.addAll(accountadd);
        KlgBuyTransaction buyTransaction=new KlgBuyTransaction();
        String transactionNum=IdGenerate.transactionNum(NumConstant.Ex_Dm_Transaction);
        buyTransaction.setCustomerGrade(levelId.intValue());
        buyTransaction.setCustomerId(Long.parseLong(customerId));
        buyTransaction.setTransactionNum(transactionNum);
        buyTransaction.setAccountId(accountId);
        buyTransaction.setCoinCode(exaccount.getCoinCode());
        buyTransaction.setSmeMoney(new BigDecimal(number));//?????????????????????
        buyTransaction.setUsdtMoney(totalMoney);//????????????USDT??????',
        buyTransaction.setFirstMoney(firstMoney);//?????????
        buyTransaction.setLastMoney(totalMoney.subtract(firstMoney));//??????
        buyTransaction.setTrusteeshipStatus(Integer.parseInt(isTrusteeship));//???????????? 1??? 2???
        buyTransaction.setRushOrders(1);//???????????????1??? 2???',
        buyTransaction.setStatus(1);//???????????????1????????? 2????????????????????? 3????????? 4????????? 5????????? ',
        buyTransactionService.save(buyTransaction);

        //????????????????????????
        remoteKlgLuckDrawService.appFirstBuyCustomer(Long.parseLong(customerId));

        KlgAssetsRecord klgAssetsRecord=new KlgAssetsRecord();//????????????
        klgAssetsRecord.setCustomerId(Long.parseLong(customerId));//??????Id
        klgAssetsRecord.setAccountId(accountId);//?????????ID
        klgAssetsRecord.setSerialNumber(IdGenerate.transactionNum(NumConstant.Ex_Dm_Transaction));//?????????
        klgAssetsRecord.setAccountType(1);  //???????????? 1.????????? 2.?????????
        klgAssetsRecord.setCoinCode(PAYCOINCODE);//??????Code
        klgAssetsRecord.setChangeCount(firstMoney); //?????????
        klgAssetsRecord.setChangeType(2);////???????????? 1??? 2???
        klgAssetsRecord.setTriggerSerialNumber(transactionNum);
        klgAssetsRecord.setRemark("??????????????????");
        klgAssetsRecord.setTriggerPoint(TriggerPointEnum.AppointmentPurchase.getKey());//?????????
        klgAssetsRecordService.save(klgAssetsRecord);//??????
        klgAssetsRecord.setId(null);
        klgAssetsRecord.setAccountType(2);
        klgAssetsRecord.setChangeType(1);
        klgAssetsRecordService.save(klgAssetsRecord);//??????
        //PlatformAccountadd platformAccountadd=PlatformAccountUtil.accountAdd(KlgPlatformCurrency.USDTConfiscationAccount.getKey(),firstMoney.stripTrailingZeros().toPlainString());//?????????????????????
      //  platformAccounts.add(platformAccountadd);
        messageProducer.toAccount(JSON.toJSONString(listLock));
      //  messageProducer.toAccount(JSON.toJSONString(platformAccounts));
        Map<String, String> paramM = new HashMap<>();
        paramM.put("${firstMoney}", firstMoney.toString());
        paramM.put("${lastMoney}", buyTransaction.getLastMoney().toString());
        RemoteMessage message=new RemoteMessage();
        message.setParam(paramM);
        message.setMsgKey(MessageType.MESSAGE_KLG_BANGZHENGJINPAY.getKey());//???????????? ??????KEY//
        message.setMsgType("1,2");// 1.????????????2.??????,
        message.setUserId(customerId.toString());
        messageProducer.toMessageWarn(JSON.toJSONString(message));//????????????
        return new JsonResult().setSuccess(true).setMsg("success");
    }

    /**
     * ????????????
     * @param hMap
     * @return
     */
    @Override
    public JsonResult appointmentSell(Map<String, String> hMap) {
        String customerId=hMap.get("customerId");//??????Id
        String number=hMap.get("number");//????????????
        Integer sellDay=Integer.parseInt(hMap.get("sellDay"));//????????????
        String price=(String) getPlatformRule1sConfig("issuePrice");//????????????
        KlgCustomerLevel customerLevel=klgCustomerLevelService.getLevelConfigByCustomerId(Long.parseLong(customerId));
        Long levelId=customerLevel.getLevelId();//??????Id
        RemoteLevelConfig levelConfig=getLevelConfigByLevelId(levelId);
        Integer minTime=levelConfig.getSellTime();//????????????
        Integer maxTime=levelConfig.getMaxSellTime();//????????????
        if(sellDay>maxTime||sellDay<minTime){
            return new JsonResult().setSuccess(false).setMsg("maichushihcangbuzhengque");//?????????????????????
        }
//         BigDecimal sellMoney=levelConfig.getBuyNum();//????????????
         BigDecimal sellMoney=new BigDecimal(number);//????????????
       /*   if(sellMoney.compareTo(new BigDecimal(number))!=0){
            return new JsonResult().setSuccess(false).setMsg("klg_caochumaichuxianzhi");//??????????????????
        }*/
        KlgRulesConfig rulesConfig=(KlgRulesConfig)getPlatformRule1sConfig(null);
        String rulesCoinCode=rulesConfig.getCoinCode();//?????????
        // ???redis??????
        RedisUtil<UserRedis> redisUtil = new RedisUtil<UserRedis>(UserRedis.class);
        UserRedis userRedis = redisUtil.get(customerId);
        ExDigitalmoneyAccountRedis exaccount = UserRedisUtils.getAccount(userRedis.getDmAccountId(rulesCoinCode).toString(), rulesCoinCode);
        if(exaccount==null){
            return new JsonResult().setSuccess(false).setMsg("bizhanghubucunzai");//?????????????????????
        }
        if(exaccount.getHotMoney().compareTo(new BigDecimal(number))==-1){
            return new JsonResult().setSuccess(false).setMsg("zijinbuzu");//????????????
        }
        Long accountId = userRedis.getDmAccountId(rulesCoinCode);//
        List<Accountadd> listLock=new ArrayList<>();//????????????
        List<Accountadd> accountadd= ToAccountUtil.frozenAssets(accountId,new BigDecimal(number));//??????
        listLock.addAll(accountadd);
        KlgSellTransaction sellTransaction=new KlgSellTransaction();
        String transactionNum=IdGenerate.transactionNum(NumConstant.Ex_Dm_Transaction);


        BigDecimal candyNum=levelConfig.getCandyNum();//??????????????????
        BigDecimal addCandyNum=levelConfig.getAddCandyNum();//??????????????????
        candyNum=candyNum.divide(new BigDecimal(100),4,BigDecimal.ROUND_DOWN);
        addCandyNum=addCandyNum.divide(new BigDecimal(100),4,BigDecimal.ROUND_DOWN);
        BigDecimal meGain=new BigDecimal(rulesConfig.getMeGain()).divide(new BigDecimal(100));//???????????????USDT
        //2019???7???5???14???26????????????
        //??????????????????*????????????*????????????+ ?????????????????????????????????-?????????????????????*????????????

        //BigDecimal candySmeMoney=sellMoney.multiply(candyNum).multiply(new BigDecimal(sellDay));//?????????????????????(SME???',
        BigDecimal candySmeMoney=(sellMoney.multiply(candyNum).multiply(new BigDecimal(minTime))).add(sellMoney.multiply(addCandyNum).multiply(new BigDecimal(sellDay-minTime)));//?????????????????????(SME???',
        BigDecimal usdtMoney=(candySmeMoney.multiply(new BigDecimal(price))).multiply(meGain); //???????????????????????????USDT



        sellTransaction.setCustomerGrade(levelId.intValue());//?????????????????????
        sellTransaction.setCustomerId(Long.parseLong(customerId));
        sellTransaction.setTransactionNum(transactionNum);
        sellTransaction.setCandySmeMoney(candySmeMoney);
        sellTransaction.setUsdtMoney(usdtMoney);
        sellTransaction.setAccountId(accountId);
//        sellTransaction.setAccountId(accountId);
//        sellTransaction.setCoinCode(rulesCoinCode);//??????
        sellTransaction.setCoinCode(PAYCOINCODE);//??????  2019???4???26???15???38????????????USDT
        sellTransaction.setSmeMoney(new BigDecimal(number));//????????????
        sellTransaction.setSmeusdtRate(new BigDecimal(price));//?????????SME-USDT??????'
        sellTransaction.setStatus(1);//1????????? 2???????????? 3????????? 4?????????
        sellTransaction.setTimeStamp(System.currentTimeMillis());//?????????????????????
        sellTransaction.setOneselfRate(new BigDecimal(rulesConfig.getMeGain()));//'????????????????????????'
        sellTransaction.setPlatformRate(new BigDecimal(rulesConfig.getPlatformGain()));//????????????????????????
        sellTransaction.setSeePointRate(new BigDecimal(rulesConfig.getSeePointAward()));//'??????????????????'
        sellTransaction.setGradationRate(new BigDecimal(rulesConfig.getGradationAward()));//'??????????????????'
        sellTransaction.setPrizeRate(new BigDecimal(rulesConfig.getGrandPrizeFund()));//'??????????????????'
        Date endTime=hry.util.date.DateUtil.addDay(new Date(),sellDay);
        sellTransaction.setSellDay(sellDay);//????????????
        sellTransaction.setSellEndTime(endTime);//??????????????????
        klgSellTransactionService.save(sellTransaction);

        KlgAssetsRecord klgAssetsRecord=new KlgAssetsRecord();//????????????
        klgAssetsRecord.setCustomerId(Long.parseLong(customerId));//??????Id
        klgAssetsRecord.setAccountId(accountId);//?????????ID
        klgAssetsRecord.setSerialNumber(IdGenerate.transactionNum(NumConstant.Ex_Dm_Transaction));//?????????
        klgAssetsRecord.setAccountType(1);  //???????????? 1.????????? 2.?????????
        klgAssetsRecord.setCoinCode(rulesCoinCode);//??????Code
        klgAssetsRecord.setChangeCount(new BigDecimal(number)); //?????????
        klgAssetsRecord.setChangeType(2);////???????????? 1??? 2???
        klgAssetsRecord.setTriggerSerialNumber(transactionNum);
        klgAssetsRecord.setRemark("??????????????????");
        klgAssetsRecord.setTriggerPoint(TriggerPointEnum.AppointmentSell.getKey());//?????????
        klgAssetsRecordService.save(klgAssetsRecord);//??????
        klgAssetsRecord.setId(null);
        klgAssetsRecord.setAccountType(2);
        klgAssetsRecord.setChangeType(1);
        klgAssetsRecordService.save(klgAssetsRecord);//??????

        Map<String, String> paramM = new HashMap<>();
        paramM.put("${smeMoney}", sellTransaction.getSmeMoney().toString());
        RemoteMessage message=new RemoteMessage();
        message.setParam(paramM);
        message.setMsgKey(MessageType.MESSAGE_KLG_SELL_QUEUE.getKey());//???????????? ??????KEY//
        message.setMsgType("1,2");// 1.????????????2.??????,
        message.setUserId(customerId.toString());
        messageProducer.toMessageWarn(JSON.toJSONString(message));//????????????

        messageProducer.toAccount(JSON.toJSONString(listLock));
        return new JsonResult().setSuccess(true).setMsg("success");
    }

    /**
     * ????????????Id????????????
     * @param leveId
     * @return
     */
    @Override
    public RemoteLevelConfig getLevelConfigByLevelId(Long leveId) {
        KlgLevelConfig klgLevelConfig=klgLevelConfigService.getLevelConfigByLevelId(leveId);
        if(klgLevelConfig!=null){
            return  ObjectUtil.bean2bean(klgLevelConfig,RemoteLevelConfig.class);
        }
        return null;
    }
    /**
     * ????????????????????????
     * @param hMap
     * @return
     */
    @Override
    public JsonResult getMyPurchaseInfo(Map<String, String> hMap) {
        Long customerId=Long.parseLong(hMap.get("customerId"));//??????ID
        RemotePurchaseInfo result=new RemotePurchaseInfo();
        String issuePrice =(String) getPlatformRule1sConfig("issuePrice");//??????
        String marginRatio =(String) getPlatformRule1sConfig("marginRatio");//???????????????
        KlgCustomerLevel customerLevel=klgCustomerLevelService.getLevelConfigByCustomerId(customerId);
//        BigDecimal buyNum=new BigDecimal(0);//????????????
        String[] buyNums=null;//??????????????????
        boolean flg= checkDowngradeDay(customerId);//????????????15???
        if(flg){
            QueryFilter query=new QueryFilter(KlgLevelConfig.class);
            query.setOrderby("sort asc");
            KlgLevelConfig levelConfig= klgLevelConfigService.get(query);
//            buyNum=levelConfig.getBuyNum();
            buyNums=levelConfig.getBuyNums()==null?null:levelConfig.getBuyNums().split(",");
        }else{
            Long levelId=customerLevel.getLevelId();//??????Id
            RemoteLevelConfig levelConfig=getLevelConfigByLevelId(levelId);
//            buyNum=levelConfig.getBuyNum();//????????????
            buyNums=levelConfig.getBuyNums()==null?null:levelConfig.getBuyNums().split(",");
            // ????????????????????????
            String oldNum=getOldPurchaseNum(customerId);
            if(oldNum!=null){
                if(buyNums!=null){
                    buyNums=getBuyNums(buyNums,oldNum);
                }
            }
        }
//        BigDecimal purchaseMoney=buyNum.multiply(new BigDecimal(issuePrice));
//        BigDecimal marginMoney=new BigDecimal(marginRatio).divide(new BigDecimal(100),8,BigDecimal.ROUND_DOWN);
//        marginMoney=purchaseMoney.multiply(marginMoney);
//        result.setPurchaseNum(buyNum.stripTrailingZeros().toPlainString());//????????????
        result.setPurchasePrice(issuePrice);//?????????
        result.setBuyNums(buyNums);//??????????????????
        result.setMarginRatio(marginRatio);//???????????????
//        result.setPurchaseMoney(BigDecimalUtil.bigDecimalToString(purchaseMoney));//?????????
//        result.setMarginMoney(BigDecimalUtil.bigDecimalToString(marginMoney));//?????????
        return new JsonResult().setSuccess(true).setObj(result);
    }
    private String [] getBuyNums(String[] buyNums,String oldNum){
        List<String> ls=new ArrayList<>();
        if(buyNums!=null){
            BigDecimal old=new BigDecimal(oldNum);
            for (int i=0;i<buyNums.length;i++){
                    String va=buyNums[i];
                    if(new BigDecimal(va).compareTo(old)>=0){
                        ls.add(va);
                    }
            }
        }
        return ls.toArray(new String[ls.size()]);
    }

/*    public static void main(String[] args) {
        String [] buyNums={"500","1000","2000"};
      String [] aa= new RemoteKlgServiceImpl().getBuyNums(buyNums,"100");
        System.out.println(Arrays.toString(aa));
    }*/

    /*
    ??????????????????????????????
     */
    @Override
    public String getOldPurchaseNum(Long customerId) {
            QueryFilter queryFilter=new QueryFilter(KlgBuyTransaction.class);
            queryFilter.addFilter("customerId=",customerId.toString());
            queryFilter.setOrderby("created desc");
             KlgBuyTransaction buyTransaction= klgBuyTransactionService.get(queryFilter);
             if(buyTransaction!=null){
                    return  buyTransaction.getSmeMoney().stripTrailingZeros().toPlainString();
             }
        return null;
    }

    /**
     * ????????????????????????
     * @param hMap
     * @return
     */
    @Override
    public JsonResult getMySellInfo(Map<String, String> hMap) {
        Long customerId=Long.parseLong(hMap.get("customerId"));//??????ID
        RemoteSellInfo result=new RemoteSellInfo();
        String issuePrice =(String) getPlatformRule1sConfig("issuePrice");//??????
        String profitProportion =(String) getPlatformRule1sConfig("meGain");//??????????????????
        KlgCustomerLevel customerLevel=klgCustomerLevelService.getLevelConfigByCustomerId(customerId);
        Long levelId=customerLevel.getLevelId();//??????Id
        RemoteLevelConfig levelConfig=getLevelConfigByLevelId(levelId);
//        BigDecimal sellNum=levelConfig.getBuyNum();//????????????
//        BigDecimal sellMoney=sellNum.multiply(new BigDecimal(issuePrice));
//        result.setSellMoney(BigDecimalUtil.bigDecimalToString(sellMoney));//?????????
//        result.setSellNum(BigDecimalUtil.bigDecimalToString(sellNum));//????????????
        result.setSellPrice(issuePrice);//?????????
        result.setCandyProportion(levelConfig.getCandyNum());//??????????????????
        result.setAddCandyNum(levelConfig.getAddCandyNum());//????????????
        result.setProfitProportion(new BigDecimal(profitProportion));//????????????????????????
        result.setSellMaxDay(levelConfig.getMaxSellTime());//????????????
        result.setSellMinDay(levelConfig.getSellTime());//????????????
        return new JsonResult().setSuccess(true).setObj(result);
    }
    /**
     * ????????????????????????    //TODO ???????????????????????????1???
     * ??????????????????
     * @param hMap
     * @return
     */
    @Override
    public JsonResult getUpgradeInfoList(Map<String, String> hMap) {
        Long customerId=Long.parseLong(hMap.get("customerId"));//??????iD
        List<RemoteAscendingGrade> ls=new ArrayList<>();//??????????????????
        boolean isUp=false;
        Integer interval=buyTransactionService.getBuyInterval(customerId);//????????????????????????
        if(interval==null){//???????????????
            return new JsonResult().setSuccess(true).setMsg("success").setObj(ls);
        }
        //???????????????
        List<KlgLevelCount> klgLevelCounts=klgTeamlevelService.countSubordinateByCustomer(customerId);
        KlgCustomerLevel customerLevel =klgCustomerLevelService.getLevelConfigByCustomerId(customerId);
        QueryFilter query=new QueryFilter(KlgLevelConfig.class);
        query.setOrderby("sort asc");
        List<KlgLevelConfig> levelConfigList=klgLevelConfigService.find(query);//?????????????????????
        Integer mySort=customerLevel.getSort();//??????????????????
        if(klgLevelCounts!=null&&klgLevelCounts.size()>0){
            Integer upSort=levelConfigList.get(1).getSort();//1??????sort  TODO ???????????????????????????1???
            if(upSort.intValue()>mySort.intValue()){ //0?????????1?????????
                isUp=interval==null?false:true;
            }
            for (KlgLevelConfig config:levelConfigList) {
                if(config.getSort().intValue()<=mySort.intValue()){
                        continue;
                }
                if(isUp){//TODO ???????????????????????????1???
                    ls.add(new RemoteAscendingGrade(config.getId(),config.getLevelName()));
                    isUp=false;
                    continue;
                }
                Integer recommendNum= config.getRecommendNum()==null?0:config.getRecommendNum();//????????????
                Integer recommendSort=config.getRecommendSort()==null?0:config.getRecommendSort();//??????????????????
                for (KlgLevelCount levelCount:klgLevelCounts) {
                    Integer count=levelCount.getCount();//??????
                    Integer sort=levelCount.getSort()==null?0:levelCount.getSort();//????????????
                    if(sort>=recommendSort){
                            if(count>=recommendNum){
                                ls.add(new RemoteAscendingGrade(config.getId(),config.getLevelName()));
                            }
                    }
                }
            }
        }
        return new JsonResult().setSuccess(true).setMsg("success").setObj(ls);
    }

    /**
     * ??????????????????
     * @param hMap
     * @return
     */
    @Override
    public JsonResult upgradeUserLevel(Map<String, String> hMap) {
        Long customerId=Long.parseLong(hMap.get("customerId"));//??????iD
        Long leveId=Long.parseLong(hMap.get("levelId"));//?????????????????????ID
        JsonResult jsonResult= getUpgradeInfoList(hMap);
        if(!jsonResult.getSuccess()){
            return jsonResult;
        }
        boolean f=false;
        List<RemoteAscendingGrade> ls=ObjectUtil.beanList(jsonResult.getObj(),RemoteAscendingGrade.class);
        if(ls!=null&&ls.size()>0){
            for (RemoteAscendingGrade ascendingGrade:ls) {
                if(leveId.equals(ascendingGrade.getLeveId())){
                    f=true;
                }
            }
        }
        if(!f){
            return new JsonResult().setSuccess(false).setMsg("klg_dengjicanshucuowu");//????????????????????????
        }
        return  klgCustomerLevelService.upgradeUserLevel(leveId,customerId);
//        return new JsonResult().setSuccess(true).setMsg("success");
    }

    /**
     * ??????????????????
     * @param hashMap
     * @return
     */
    @Override
    public FrontPage getMyTransactionflow(HashMap<String, String> hashMap) {
        String page=hashMap.get("page");
        String pageSize=hashMap.get("pageSize");
        String customerId=hashMap.get("customerId");
        String coinCode=hashMap.get("coinCode");
        QueryFilter queryFilter=new QueryFilter(KlgAssetsRecord.class);
        queryFilter.setPage(Integer.parseInt(page));
        queryFilter.setPageSize(Integer.parseInt(pageSize));
        if(StringUtil.isNull(customerId)){
            queryFilter.addFilter("customerId=",customerId);
        }
        if(StringUtil.isNull(coinCode)){
            queryFilter.addFilter("coinCode=",coinCode);
        }
      queryFilter.setOrderby("created desc");
      Page<KlgAssetsRecord> orderPage= klgAssetsRecordService.findPage(queryFilter);
       if(orderPage.size()>0){
            List<RemoteAssetsRecord> list=ObjectUtil.beanList(orderPage.getResult(),RemoteAssetsRecord.class);
            FrontPage frontPage = new FrontPage(list, orderPage.getTotal(), orderPage.getPages(), orderPage.getPageSize());
            return frontPage;
       }
       return new FrontPage(null, 0, 1, 10);
    }

    /**
     * ??????????????????
     * @param hashMap
     * @return
     */
    @Override
    public FrontPage getBuyInfoList(HashMap<String, String> hashMap) {
        hashMap.put("status","1");
        return klgBuyTransactionService.findPageBySql(hashMap);
    }
    /**
     * ??????????????????
     * @param hashMap
     * @return
     */
    @Override
    public FrontPage getSellInfoList(HashMap<String, String> hashMap) {
        hashMap.put("status","1");
        return klgSellTransactionService.findPageBySql(hashMap);
    }

    /**
     * ??????????????????
     * @param hashMap
     * @return
     */
    @Override
    public FrontPage fingBuyListByCustomerId(Map<String, String> hashMap) {
        return klgBuyTransactionService.findPageBySql(hashMap);
    }
    /**
     * ??????????????????
     * @param hashMap
     * @return
     */
    @Override
    public FrontPage fingSellListByCustomerId(Map<String, String> hashMap) {
        return klgSellTransactionService.findPageBySql(hashMap);
    }
    /**
     * ??????????????????????????????
     * @param hashMap
     * @return
     */
    @Override
    public JsonResult getCustomerLevelInfo(Map<String, String> hashMap) {
        Long customerId=Long.parseLong(hashMap.get("customerId"));
        KlgCustomerLevel klgCustomerLevel=klgCustomerLevelService.getLevelConfigByCustomerId(customerId);
        if(klgCustomerLevel!=null){
            boolean flg= checkDowngradeDay(customerId);
            if(flg){//??????15??? ??????????????? 1????????????  ????????? 1???
                QueryFilter query=new QueryFilter(KlgLevelConfig.class);
                query.setOrderby("sort asc");
                KlgLevelConfig levelConfig= klgLevelConfigService.get(query);
                klgCustomerLevel.setLevelName(levelConfig.getLevelName());//??????1???
            }
            RemoteCustomerLevel result=ObjectUtil.bean2bean(klgCustomerLevel,RemoteCustomerLevel.class);
            return new JsonResult().setSuccess(true).setMsg("success").setObj(result);
        }
        return new JsonResult().setSuccess(true).setMsg("success");
    }
	@Override
	public JsonResult myAccount(Map<String, String> params) {
		// TODO Auto-generated method stub
		JsonResult jsonResult = new JsonResult();
		Long customerId = Long.valueOf(params.get("customerId"));// ??????id
		//??????sme??????
		String issuePrice = (String) remoteKlgService.getPlatformRule1sConfig("issuePrice");
		/** ???????????????Code */
		String ptbCoinCode = (String) remoteKlgService.getPlatformRule1sConfig("coinCode");

		//??????Usdt??????
		ExDigitalmoneyAccountRedis exaccountUsdt = this.selectAccount(customerId, "USDT");
		// ??????usdt?????????
		BigDecimal usdtHotMoney = exaccountUsdt.getHotMoney();
		// ??????usdt?????????
		BigDecimal usdtColdMoney = exaccountUsdt.getColdMoney();

		BigDecimal usdtSum = usdtHotMoney.add(usdtColdMoney);

		//??????sme??????
		ExDigitalmoneyAccountRedis exaccountSme = this.selectAccount(customerId, ptbCoinCode);
		// ??????sme?????????
		BigDecimal smeHotMoney = exaccountSme.getHotMoney();
		// ??????sme?????????
		BigDecimal smeColdMoney = exaccountSme.getColdMoney();

		BigDecimal smeSum = smeHotMoney.add(smeColdMoney);

		//???????????????????????????
		BigDecimal firstSum = klgBuyTransactionService.getBuyFirstMoneySum(customerId);
		if(firstSum==null){
			firstSum = new BigDecimal(0);
		}
		//??????usdt?????????
		BigDecimal usdtConversion = usdtSum.add(smeSum.multiply(new BigDecimal(issuePrice)));
		if(usdtConversion==null){
			usdtConversion = new BigDecimal(0);

		}

		Map<String, Object> mapResult = new HashMap<>();
		mapResult.put("usdtConversion", usdtConversion.setScale(4, BigDecimal.ROUND_HALF_DOWN));
		mapResult.put("firstSum", firstSum.setScale(4, BigDecimal.ROUND_HALF_DOWN));
		JSONObject json = JSONObject.fromObject(mapResult);
		return jsonResult.setSuccess(true).setObj(json);
	}

	/**
	 * ?????????????????????
	 *
	 * @param customerId
	 * @param coinCode
	 * @return
	 */
	private ExDigitalmoneyAccountRedis selectAccount(Long customerId, String coinCode) {
		// ???redis??????
		RedisUtil<UserRedis> redisUtil = new RedisUtil<UserRedis>(UserRedis.class);
		UserRedis userRedis = redisUtil.get(customerId.toString());
		// ???????????????
		ExDigitalmoneyAccountRedis exaccount = UserRedisUtils.getAccount(userRedis.getDmAccountId(coinCode).toString(),
				coinCode);
		return exaccount;
	}

	@Override
	public JsonResult getPlatformCoinPrice() {
		// TODO Auto-generated method stub
		//??????sme??????
		String issuePrice = (String) remoteKlgService.getPlatformRule1sConfig("issuePrice");
		return new JsonResult().setSuccess(true).setObj(new BigDecimal(issuePrice));
	}
	@Override
	public JsonResult getPlatformCode() {
		// TODO Auto-generated method stub
		String ptbCoinCode = (String) remoteKlgService.getPlatformRule1sConfig("coinCode");
		return new JsonResult().setSuccess(true).setObj(ptbCoinCode);
	}

	@Override
	public JsonResult getAccountByCoinCode(Map<String, String> hMap) {
		// TODO Auto-generated method stub
		JsonResult jsonResult = new JsonResult();
		Long customerId = Long.valueOf(hMap.get("customerId"));// ??????id
		String coinCode = hMap.get("coinCode");
		Map<String, Object> resultMap = new HashMap<>();
		if(coinCode!=null&&!coinCode.equals("")){
			ExDigitalmoneyAccountRedis exaccount = this.selectAccount(customerId, coinCode);
			if(exaccount!=null){
				resultMap.put("hotMoney", exaccount.getHotMoney().compareTo(new BigDecimal(0))==0?new BigDecimal(0):exaccount.getHotMoney());
				resultMap.put("coldMoney", exaccount.getColdMoney().compareTo(new BigDecimal(0))==0?new BigDecimal(0):exaccount.getColdMoney());
			}
		}
		JSONObject json = JSONObject.fromObject(resultMap);
		return jsonResult.setSuccess(true).setObj(json);
	}

	@Override
	public JsonResult getLineUpTime() {
		// TODO Auto-generated method stub
		String lineUpTime = (String) remoteKlgService.getPlatformRule1sConfig("lineUpTime");
		return new JsonResult().setSuccess(true).setObj(lineUpTime);
	}

	@Override
	public JsonResult getKlgConfig() {
		// TODO Auto-generated method stub
		Object config = remoteKlgService.getPlatformRule1sConfig(null);
		JSONObject json = JSONObject.fromObject(config);
		return new JsonResult().setSuccess(true).setObj(json);
	}
}
