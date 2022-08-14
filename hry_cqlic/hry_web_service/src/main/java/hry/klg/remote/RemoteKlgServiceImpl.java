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
    //购买支付币种
    private final static String PAYCOINCODE="USDT";
    //转账
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
     * 获取平台规则
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
     * 是否开盘时间
     * @return
     */
    @Override
    public JsonResult isCheckOpenTime() {
        KlgRulesConfig rulesConfig= (KlgRulesConfig)getPlatformRule1sConfig(null);
        if("1".equals( rulesConfig.getIsOpen())){//是否开启 0是1否
            String startTime= rulesConfig.getStartTime();//开始时间
                String endTime= rulesConfig.getEndTime();//结束时间
                boolean fl= DateUtil.isCheackTime(startTime,endTime);
                if(fl){
                    return new JsonResult().setSuccess(true).setObj(rulesConfig);
                }
          }
        return new JsonResult().setSuccess(false);
    }

    /**
     * 验证是否金额正确
     * @param customerId
     * @param number
     * @return
     */
    @Override
    public Boolean isCheckLimitMoney(Long customerId, BigDecimal number) {
        boolean check=false;
        KlgCustomerLevel customerLevel=klgCustomerLevelService.getLevelConfigByCustomerId(customerId);
        Long levelId=customerLevel.getLevelId();//等级Id
        RemoteLevelConfig levelConfig=getLevelConfigByLevelId(levelId);
        if(levelConfig!=null){
//            BigDecimal buyNum=levelConfig.getBuyNum();//购买限制
            String[] buyNums=levelConfig.getBuyNums().split(",");//购买限制
            boolean flg= checkDowngradeDay(customerId);
            if(flg){//超过15天 购买金额为 1星的数量
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
     * 验证是否超过15天
     * @param customerId
     * @return
     */
    private  boolean checkDowngradeDay(Long customerId){
        Integer limitday= Integer.valueOf((String)getPlatformRule1sConfig("downgradeDays"));//间隔
         boolean flag=klgBuyTransactionService.checkIntervalByday(customerId,limitday);
        if(flag){//配置超出间隔天数// 默认降级
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
                return new JsonResult().setSuccess(false).setMsg("zijinbuzu");//资金不足
            }
            List<PlatformAccountadd> platformAccounts=new ArrayList<>();

          //  KlgPlatformAccountRecord accountRecord=new KlgPlatformAccountRecord();
           // KlgPlatformAccountRecord toAccountRecord=new KlgPlatformAccountRecord();
        //    String serialNumber= IdGenerate.transactionNum(NumConstant.Ex_Dm_Transaction);
      /*      accountRecord.setMoney(new BigDecimal(money));
            accountRecord.setNowMoney(new BigDecimal(num));
            accountRecord.setRemark("转出");
            accountRecord.setType(102);
            accountRecord.setAccountId(account);
            accountRecord.setSerialNumber(serialNumber);

            toAccountRecord.setMoney(new BigDecimal(money));
            toAccountRecord.setNowMoney(new BigDecimal(tonum));
            toAccountRecord.setRemark("转入");
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
     * 转账接口
     * @param hashMap
     * @return
     */
    @Override
    public JsonResult transferAccounts(HashMap<String, String> hashMap) {
        String customerId=hashMap.get("customerId");//用户Id
        String coinCode=hashMap.get("coinCode");//转账的币种
        BigDecimal number=new BigDecimal(hashMap.get("number"));//转账的数量
        String toAccount=hashMap.get("toAccount");//收入方的币种地址
        QueryFilter filter = new QueryFilter(AppPersonInfo.class);
        filter.or("email=",toAccount);
        filter.or("mobilePhone=",toAccount);
        AppPersonInfo _appPersonInfo = appPersonInfoService.get(filter);
        if(toAccount==null||_appPersonInfo==null){
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
        String toCustomerId=_appPersonInfo.getCustomerId().toString();
        if(toCustomerId.equals(customerId)){
            return new JsonResult().setSuccess(false).setMsg("bunengxiangzijizhuanzhang");//不能向自己转账
        }
        List<Accountadd> listLock=new ArrayList<>();

        UserRedis toUserRedis = redisUtil.get(toCustomerId);
        ExDigitalmoneyAccountRedis toExaccount = UserRedisUtils.getAccount(toUserRedis.getDmAccountId(coinCode).toString(), coinCode);
        toExaccount.setHotMoney(toExaccount.getHotMoney().add(number));
        Long accountId = userRedis.getDmAccountId(coinCode);
        Long toAccountId = toUserRedis.getDmAccountId(coinCode);//收入方账户ID
        Accountadd accountadd= ToAccountUtil.expenditureHotAssets(accountId,number);//支出
        Accountadd toAccountadd=ToAccountUtil.ncomeAssets(toAccountId,number);//收入
        listLock.add(accountadd);
        listLock.add(toAccountadd);

        String serialNumber=IdGenerate.transactionNum(NumConstant.Ex_Dm_Transaction);

        KlgAssetsRecord klgAssetsRecord=new KlgAssetsRecord();//交易流水
        klgAssetsRecord.setCustomerId(Long.parseLong(customerId));//用户Id
        klgAssetsRecord.setAccountId(accountId);//币账户ID
        klgAssetsRecord.setSerialNumber(serialNumber);//流水号
        klgAssetsRecord.setAccountType(1);  //账户类型 1.热账户 2.冷账户
        klgAssetsRecord.setCoinCode(coinCode);//币种Code
        klgAssetsRecord.setChangeCount(number); //交易量
        klgAssetsRecord.setChangeType(2);////变动类型 1加 2减
        TriggerPointEnum triggerPoint=TriggerPointEnum.TransferAccounts;//默认 转账
        klgAssetsRecord.setTriggerPoint(triggerPoint.getKey());//触发点
        klgAssetsRecord.setTriggerSerialNumber(serialNumber); //触发点流水号
        klgAssetsRecord.setRemark("转账");

        KlgAssetsRecord klgAssetsRecord2=new KlgAssetsRecord();//交易流水
        klgAssetsRecord2.setCustomerId(Long.parseLong(toCustomerId));//用户Id
        klgAssetsRecord2.setAccountId(toAccountId);//币账户ID
        klgAssetsRecord2.setSerialNumber(serialNumber);//流水号
        klgAssetsRecord2.setAccountType(1);  //账户类型 1.热账户 2.冷账户
        klgAssetsRecord2.setCoinCode(coinCode);//币种Code
        klgAssetsRecord2.setChangeCount(number); //交易量
        klgAssetsRecord2.setChangeType(1);////变动类型 1加 2减
        klgAssetsRecord2.setTriggerPoint(triggerPoint.getKey());//触发点
        klgAssetsRecord2.setTriggerSerialNumber(serialNumber); //触发点流水号
        klgAssetsRecord2.setRemark("转账");
        KlgTransferAccounts transferAccounts=new KlgTransferAccounts();
        transferAccounts.setSerialNumber(serialNumber);
        transferAccounts.setCoinCode(coinCode);
        transferAccounts.setCustomerId(Long.valueOf(customerId));
        transferAccounts.setToCustomerId(Long.valueOf(toCustomerId));
        transferAccounts.setMoney(number);
        transferAccounts.setRemark("转账记录");
        klgTransferAccountsService.save(transferAccounts);//转账记录表
        klgAssetsRecordService.save(klgAssetsRecord);//交易流水
        klgAssetsRecordService.save(klgAssetsRecord2);//交易流水*/

        Map<String, String> paramM = new HashMap<>();
        paramM.put("${number}", hashMap.get("number"));//金额
        String toPhone=toAccount.replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2");
        paramM.put("${toAccount}", toPhone);//金额
        RemoteMessage message=new RemoteMessage();
        message.setParam(paramM);
        message.setMsgKey(MessageType.MESSAGE_KLG_ACCOUNT_CONFIRMOUT.getKey());//消息类型 模板KEY//
        message.setMsgType("1,2");// 1.站内信，2.短信,
        message.setUserId(customerId.toString());
        messageProducer.toMessageWarn(JSON.toJSONString(message));//消息提醒

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
                KlgPlatformAccountRecord accountRecord=new KlgPlatformAccountRecord();//平台流水记录
                accountRecord.setMoney(new BigDecimal(m));
                accountRecord.setNowMoney(new BigDecimal(num));
                accountRecord.setType(accountadd.getType());
                accountRecord.setAccountId(accountadd.getAccountType());
                accountRecord.setSerialNumber(accountadd.getSerialNumber());
                accountRecord.setRemark(accountadd.getRemark());
                platformAccountRecordService.save(accountRecord);//记录流水
                platformAccountService.updatePlatformAccount(money,type);//入库
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
     * 预约购买
     * 支付20%金额
     * @param hMap
     * @return
     */
    @Override
    public JsonResult appointmentPurchase(Map<String, String> hMap) {
        String customerId=hMap.get("customerId");//用户Id
        String password=hMap.get("password");//密码
        String isTrusteeship=hMap.get("isTrusteeship");//是否托管 1否 2是
        if(isTrusteeship==null)
            isTrusteeship="1";
        String number=hMap.get("number");//购买数量
        String price=(String) getPlatformRule1sConfig("issuePrice");//购买单价
        String marginRatio=(String) getPlatformRule1sConfig("marginRatio");//保证金比例
        BigDecimal ratio=new BigDecimal(marginRatio).divide(new BigDecimal(100));
        KlgCustomerLevel customerLevel=klgCustomerLevelService.getLevelConfigByCustomerId(Long.parseLong(customerId));
        Long levelId=customerLevel.getLevelId();//等级Id
        // 查redis缓存
        RedisUtil<UserRedis> redisUtil = new RedisUtil<UserRedis>(UserRedis.class);
        UserRedis userRedis = redisUtil.get(customerId);
        ExDigitalmoneyAccountRedis exaccount = UserRedisUtils.getAccount(userRedis.getDmAccountId(PAYCOINCODE).toString(), PAYCOINCODE);
        if(exaccount==null){
            return new JsonResult().setSuccess(false).setMsg("bizhanghubucunzai");//币种账户不存在
        }
        BigDecimal totalMoney=new BigDecimal(number).multiply(new BigDecimal(price));//总需支付
        BigDecimal firstMoney=totalMoney.multiply(ratio);//支付金额
        if(exaccount.getHotMoney().compareTo(firstMoney)==-1){
            return new JsonResult().setSuccess(false).setMsg("zijinbuzu");//资金不足
        }
        List<Accountadd> listLock=new ArrayList<>();//用户账户
       // List<PlatformAccountadd> platformAccounts=new ArrayList<>();//平台账户
        Long accountId = userRedis.getDmAccountId(PAYCOINCODE);//
        List<Accountadd> accountadd= ToAccountUtil.frozenAssets(accountId,firstMoney);//冻结
        listLock.addAll(accountadd);
        KlgBuyTransaction buyTransaction=new KlgBuyTransaction();
        String transactionNum=IdGenerate.transactionNum(NumConstant.Ex_Dm_Transaction);
        buyTransaction.setCustomerGrade(levelId.intValue());
        buyTransaction.setCustomerId(Long.parseLong(customerId));
        buyTransaction.setTransactionNum(transactionNum);
        buyTransaction.setAccountId(accountId);
        buyTransaction.setCoinCode(exaccount.getCoinCode());
        buyTransaction.setSmeMoney(new BigDecimal(number));//买入平台币数量
        buyTransaction.setUsdtMoney(totalMoney);//需要支付USDT金额',
        buyTransaction.setFirstMoney(firstMoney);//保证金
        buyTransaction.setLastMoney(totalMoney.subtract(firstMoney));//尾款
        buyTransaction.setTrusteeshipStatus(Integer.parseInt(isTrusteeship));//是否托管 1否 2是
        buyTransaction.setRushOrders(1);//是否抢单：1否 2是',
        buyTransaction.setStatus(1);//订单状态：1排队中 2排队成功待支付 3待收款 4已成交 5已作废 ',
        buyTransactionService.save(buyTransaction);

        //添加大奖抽奖信息
        remoteKlgLuckDrawService.appFirstBuyCustomer(Long.parseLong(customerId));

        KlgAssetsRecord klgAssetsRecord=new KlgAssetsRecord();//交易流水
        klgAssetsRecord.setCustomerId(Long.parseLong(customerId));//用户Id
        klgAssetsRecord.setAccountId(accountId);//币账户ID
        klgAssetsRecord.setSerialNumber(IdGenerate.transactionNum(NumConstant.Ex_Dm_Transaction));//流水号
        klgAssetsRecord.setAccountType(1);  //账户类型 1.热账户 2.冷账户
        klgAssetsRecord.setCoinCode(PAYCOINCODE);//币种Code
        klgAssetsRecord.setChangeCount(firstMoney); //交易量
        klgAssetsRecord.setChangeType(2);////变动类型 1加 2减
        klgAssetsRecord.setTriggerSerialNumber(transactionNum);
        klgAssetsRecord.setRemark("预约买入冻结");
        klgAssetsRecord.setTriggerPoint(TriggerPointEnum.AppointmentPurchase.getKey());//触发点
        klgAssetsRecordService.save(klgAssetsRecord);//流水
        klgAssetsRecord.setId(null);
        klgAssetsRecord.setAccountType(2);
        klgAssetsRecord.setChangeType(1);
        klgAssetsRecordService.save(klgAssetsRecord);//流水
        //PlatformAccountadd platformAccountadd=PlatformAccountUtil.accountAdd(KlgPlatformCurrency.USDTConfiscationAccount.getKey(),firstMoney.stripTrailingZeros().toPlainString());//平台保证金账户
      //  platformAccounts.add(platformAccountadd);
        messageProducer.toAccount(JSON.toJSONString(listLock));
      //  messageProducer.toAccount(JSON.toJSONString(platformAccounts));
        Map<String, String> paramM = new HashMap<>();
        paramM.put("${firstMoney}", firstMoney.toString());
        paramM.put("${lastMoney}", buyTransaction.getLastMoney().toString());
        RemoteMessage message=new RemoteMessage();
        message.setParam(paramM);
        message.setMsgKey(MessageType.MESSAGE_KLG_BANGZHENGJINPAY.getKey());//消息类型 模板KEY//
        message.setMsgType("1,2");// 1.站内信，2.短信,
        message.setUserId(customerId.toString());
        messageProducer.toMessageWarn(JSON.toJSONString(message));//消息提醒
        return new JsonResult().setSuccess(true).setMsg("success");
    }

    /**
     * 预约卖出
     * @param hMap
     * @return
     */
    @Override
    public JsonResult appointmentSell(Map<String, String> hMap) {
        String customerId=hMap.get("customerId");//用户Id
        String number=hMap.get("number");//卖出数量
        Integer sellDay=Integer.parseInt(hMap.get("sellDay"));//卖出时长
        String price=(String) getPlatformRule1sConfig("issuePrice");//当前市价
        KlgCustomerLevel customerLevel=klgCustomerLevelService.getLevelConfigByCustomerId(Long.parseLong(customerId));
        Long levelId=customerLevel.getLevelId();//等级Id
        RemoteLevelConfig levelConfig=getLevelConfigByLevelId(levelId);
        Integer minTime=levelConfig.getSellTime();//最小时长
        Integer maxTime=levelConfig.getMaxSellTime();//最大时长
        if(sellDay>maxTime||sellDay<minTime){
            return new JsonResult().setSuccess(false).setMsg("maichushihcangbuzhengque");//卖出时长不正确
        }
//         BigDecimal sellMoney=levelConfig.getBuyNum();//购买限制
         BigDecimal sellMoney=new BigDecimal(number);//购买数量
       /*   if(sellMoney.compareTo(new BigDecimal(number))!=0){
            return new JsonResult().setSuccess(false).setMsg("klg_caochumaichuxianzhi");//超出卖出限制
        }*/
        KlgRulesConfig rulesConfig=(KlgRulesConfig)getPlatformRule1sConfig(null);
        String rulesCoinCode=rulesConfig.getCoinCode();//平台币
        // 查redis缓存
        RedisUtil<UserRedis> redisUtil = new RedisUtil<UserRedis>(UserRedis.class);
        UserRedis userRedis = redisUtil.get(customerId);
        ExDigitalmoneyAccountRedis exaccount = UserRedisUtils.getAccount(userRedis.getDmAccountId(rulesCoinCode).toString(), rulesCoinCode);
        if(exaccount==null){
            return new JsonResult().setSuccess(false).setMsg("bizhanghubucunzai");//币种账户不存在
        }
        if(exaccount.getHotMoney().compareTo(new BigDecimal(number))==-1){
            return new JsonResult().setSuccess(false).setMsg("zijinbuzu");//资金不足
        }
        Long accountId = userRedis.getDmAccountId(rulesCoinCode);//
        List<Accountadd> listLock=new ArrayList<>();//用户账户
        List<Accountadd> accountadd= ToAccountUtil.frozenAssets(accountId,new BigDecimal(number));//冻结
        listLock.addAll(accountadd);
        KlgSellTransaction sellTransaction=new KlgSellTransaction();
        String transactionNum=IdGenerate.transactionNum(NumConstant.Ex_Dm_Transaction);


        BigDecimal candyNum=levelConfig.getCandyNum();//基础糖果比率
        BigDecimal addCandyNum=levelConfig.getAddCandyNum();//递增糖果比率
        candyNum=candyNum.divide(new BigDecimal(100),4,BigDecimal.ROUND_DOWN);
        addCandyNum=addCandyNum.divide(new BigDecimal(100),4,BigDecimal.ROUND_DOWN);
        BigDecimal meGain=new BigDecimal(rulesConfig.getMeGain()).divide(new BigDecimal(100));//本人获得的USDT
        //2019年7月5日14点26分新公式
        //基础卖出天数*卖出数量*基础比率+ 卖出数量（输入卖出天数-基础卖出天数）*递增比率

        //BigDecimal candySmeMoney=sellMoney.multiply(candyNum).multiply(new BigDecimal(sellDay));//应获糖果总金额(SME）',
        BigDecimal candySmeMoney=(sellMoney.multiply(candyNum).multiply(new BigDecimal(minTime))).add(sellMoney.multiply(addCandyNum).multiply(new BigDecimal(sellDay-minTime)));//应获糖果总金额(SME）',
        BigDecimal usdtMoney=(candySmeMoney.multiply(new BigDecimal(price))).multiply(meGain); //卖出后获取瓜分后总USDT



        sellTransaction.setCustomerGrade(levelId.intValue());//排单时用户等级
        sellTransaction.setCustomerId(Long.parseLong(customerId));
        sellTransaction.setTransactionNum(transactionNum);
        sellTransaction.setCandySmeMoney(candySmeMoney);
        sellTransaction.setUsdtMoney(usdtMoney);
        sellTransaction.setAccountId(accountId);
//        sellTransaction.setAccountId(accountId);
//        sellTransaction.setCoinCode(rulesCoinCode);//币种
        sellTransaction.setCoinCode(PAYCOINCODE);//币种  2019年4月26日15点38分币种为USDT
        sellTransaction.setSmeMoney(new BigDecimal(number));//卖出数量
        sellTransaction.setSmeusdtRate(new BigDecimal(price));//排队时SME-USDT汇率'
        sellTransaction.setStatus(1);//1排队中 2排队成功 3待收款 4已完成
        sellTransaction.setTimeStamp(System.currentTimeMillis());//排队开始时间戳
        sellTransaction.setOneselfRate(new BigDecimal(rulesConfig.getMeGain()));//'本人获取糖果比例'
        sellTransaction.setPlatformRate(new BigDecimal(rulesConfig.getPlatformGain()));//平台获取糖果比例
        sellTransaction.setSeePointRate(new BigDecimal(rulesConfig.getSeePointAward()));//'见点糖果比例'
        sellTransaction.setGradationRate(new BigDecimal(rulesConfig.getGradationAward()));//'级差糖果比例'
        sellTransaction.setPrizeRate(new BigDecimal(rulesConfig.getGrandPrizeFund()));//'周奖糖果比例'
        Date endTime=hry.util.date.DateUtil.addDay(new Date(),sellDay);
        sellTransaction.setSellDay(sellDay);//卖出时长
        sellTransaction.setSellEndTime(endTime);//卖出结束时间
        klgSellTransactionService.save(sellTransaction);

        KlgAssetsRecord klgAssetsRecord=new KlgAssetsRecord();//交易流水
        klgAssetsRecord.setCustomerId(Long.parseLong(customerId));//用户Id
        klgAssetsRecord.setAccountId(accountId);//币账户ID
        klgAssetsRecord.setSerialNumber(IdGenerate.transactionNum(NumConstant.Ex_Dm_Transaction));//流水号
        klgAssetsRecord.setAccountType(1);  //账户类型 1.热账户 2.冷账户
        klgAssetsRecord.setCoinCode(rulesCoinCode);//币种Code
        klgAssetsRecord.setChangeCount(new BigDecimal(number)); //交易量
        klgAssetsRecord.setChangeType(2);////变动类型 1加 2减
        klgAssetsRecord.setTriggerSerialNumber(transactionNum);
        klgAssetsRecord.setRemark("预约卖出冻结");
        klgAssetsRecord.setTriggerPoint(TriggerPointEnum.AppointmentSell.getKey());//触发点
        klgAssetsRecordService.save(klgAssetsRecord);//流水
        klgAssetsRecord.setId(null);
        klgAssetsRecord.setAccountType(2);
        klgAssetsRecord.setChangeType(1);
        klgAssetsRecordService.save(klgAssetsRecord);//流水

        Map<String, String> paramM = new HashMap<>();
        paramM.put("${smeMoney}", sellTransaction.getSmeMoney().toString());
        RemoteMessage message=new RemoteMessage();
        message.setParam(paramM);
        message.setMsgKey(MessageType.MESSAGE_KLG_SELL_QUEUE.getKey());//消息类型 模板KEY//
        message.setMsgType("1,2");// 1.站内信，2.短信,
        message.setUserId(customerId.toString());
        messageProducer.toMessageWarn(JSON.toJSONString(message));//消息提醒

        messageProducer.toAccount(JSON.toJSONString(listLock));
        return new JsonResult().setSuccess(true).setMsg("success");
    }

    /**
     * 根据等级Id获取配置
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
     * 获取预约购买信息
     * @param hMap
     * @return
     */
    @Override
    public JsonResult getMyPurchaseInfo(Map<String, String> hMap) {
        Long customerId=Long.parseLong(hMap.get("customerId"));//用户ID
        RemotePurchaseInfo result=new RemotePurchaseInfo();
        String issuePrice =(String) getPlatformRule1sConfig("issuePrice");//价格
        String marginRatio =(String) getPlatformRule1sConfig("marginRatio");//保证金比例
        KlgCustomerLevel customerLevel=klgCustomerLevelService.getLevelConfigByCustomerId(customerId);
//        BigDecimal buyNum=new BigDecimal(0);//购买限制
        String[] buyNums=null;//购买限制数量
        boolean flg= checkDowngradeDay(customerId);//是否超过15天
        if(flg){
            QueryFilter query=new QueryFilter(KlgLevelConfig.class);
            query.setOrderby("sort asc");
            KlgLevelConfig levelConfig= klgLevelConfigService.get(query);
//            buyNum=levelConfig.getBuyNum();
            buyNums=levelConfig.getBuyNums()==null?null:levelConfig.getBuyNums().split(",");
        }else{
            Long levelId=customerLevel.getLevelId();//等级Id
            RemoteLevelConfig levelConfig=getLevelConfigByLevelId(levelId);
//            buyNum=levelConfig.getBuyNum();//购买限制
            buyNums=levelConfig.getBuyNums()==null?null:levelConfig.getBuyNums().split(",");
            // 获取上次购买数量
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
//        result.setPurchaseNum(buyNum.stripTrailingZeros().toPlainString());//购买数量
        result.setPurchasePrice(issuePrice);//市场价
        result.setBuyNums(buyNums);//购买数量限制
        result.setMarginRatio(marginRatio);//保证金比率
//        result.setPurchaseMoney(BigDecimalUtil.bigDecimalToString(purchaseMoney));//交易额
//        result.setMarginMoney(BigDecimalUtil.bigDecimalToString(marginMoney));//保证金
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
    获取上次预约买入数量
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
     * 获取预约卖出信息
     * @param hMap
     * @return
     */
    @Override
    public JsonResult getMySellInfo(Map<String, String> hMap) {
        Long customerId=Long.parseLong(hMap.get("customerId"));//用户ID
        RemoteSellInfo result=new RemoteSellInfo();
        String issuePrice =(String) getPlatformRule1sConfig("issuePrice");//价格
        String profitProportion =(String) getPlatformRule1sConfig("meGain");//获取糖果比例
        KlgCustomerLevel customerLevel=klgCustomerLevelService.getLevelConfigByCustomerId(customerId);
        Long levelId=customerLevel.getLevelId();//等级Id
        RemoteLevelConfig levelConfig=getLevelConfigByLevelId(levelId);
//        BigDecimal sellNum=levelConfig.getBuyNum();//购买限制
//        BigDecimal sellMoney=sellNum.multiply(new BigDecimal(issuePrice));
//        result.setSellMoney(BigDecimalUtil.bigDecimalToString(sellMoney));//交易额
//        result.setSellNum(BigDecimalUtil.bigDecimalToString(sellNum));//购买数量
        result.setSellPrice(issuePrice);//市场价
        result.setCandyProportion(levelConfig.getCandyNum());//基础糖果比例
        result.setAddCandyNum(levelConfig.getAddCandyNum());//递增比率
        result.setProfitProportion(new BigDecimal(profitProportion));//本人获取糖果比例
        result.setSellMaxDay(levelConfig.getMaxSellTime());//最大时长
        result.setSellMinDay(levelConfig.getSellTime());//最小时长
        return new JsonResult().setSuccess(true).setObj(result);
    }
    /**
     * 获取可升等级列表    //TODO 预约购买一次就能升1星
     * 升级必须预约
     * @param hMap
     * @return
     */
    @Override
    public JsonResult getUpgradeInfoList(Map<String, String> hMap) {
        Long customerId=Long.parseLong(hMap.get("customerId"));//用户iD
        List<RemoteAscendingGrade> ls=new ArrayList<>();//可升等级列表
        boolean isUp=false;
        Integer interval=buyTransactionService.getBuyInterval(customerId);//上次预约购买时间
        if(interval==null){//没有预约过
            return new JsonResult().setSuccess(true).setMsg("success").setObj(ls);
        }
        //推荐总人数
        List<KlgLevelCount> klgLevelCounts=klgTeamlevelService.countSubordinateByCustomer(customerId);
        KlgCustomerLevel customerLevel =klgCustomerLevelService.getLevelConfigByCustomerId(customerId);
        QueryFilter query=new QueryFilter(KlgLevelConfig.class);
        query.setOrderby("sort asc");
        List<KlgLevelConfig> levelConfigList=klgLevelConfigService.find(query);//所有的等级配置
        Integer mySort=customerLevel.getSort();//用户当前等级
        if(klgLevelCounts!=null&&klgLevelCounts.size()>0){
            Integer upSort=levelConfigList.get(1).getSort();//1星的sort  TODO 预约购买一次就能升1星
            if(upSort.intValue()>mySort.intValue()){ //0星升级1星时候
                isUp=interval==null?false:true;
            }
            for (KlgLevelConfig config:levelConfigList) {
                if(config.getSort().intValue()<=mySort.intValue()){
                        continue;
                }
                if(isUp){//TODO 预约购买一次就能升1星
                    ls.add(new RemoteAscendingGrade(config.getId(),config.getLevelName()));
                    isUp=false;
                    continue;
                }
                Integer recommendNum= config.getRecommendNum()==null?0:config.getRecommendNum();//推荐数量
                Integer recommendSort=config.getRecommendSort()==null?0:config.getRecommendSort();//推荐等级排序
                for (KlgLevelCount levelCount:klgLevelCounts) {
                    Integer count=levelCount.getCount();//个数
                    Integer sort=levelCount.getSort()==null?0:levelCount.getSort();//等级排序
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
     * 会员升级操作
     * @param hMap
     * @return
     */
    @Override
    public JsonResult upgradeUserLevel(Map<String, String> hMap) {
        Long customerId=Long.parseLong(hMap.get("customerId"));//用户iD
        Long leveId=Long.parseLong(hMap.get("levelId"));//选中升级的等级ID
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
            return new JsonResult().setSuccess(false).setMsg("klg_dengjicanshucuowu");//升级等级参数错误
        }
        return  klgCustomerLevelService.upgradeUserLevel(leveId,customerId);
//        return new JsonResult().setSuccess(true).setMsg("success");
    }

    /**
     * 我的交易流水
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
     * 平台买单记录
     * @param hashMap
     * @return
     */
    @Override
    public FrontPage getBuyInfoList(HashMap<String, String> hashMap) {
        hashMap.put("status","1");
        return klgBuyTransactionService.findPageBySql(hashMap);
    }
    /**
     * 平台卖单记录
     * @param hashMap
     * @return
     */
    @Override
    public FrontPage getSellInfoList(HashMap<String, String> hashMap) {
        hashMap.put("status","1");
        return klgSellTransactionService.findPageBySql(hashMap);
    }

    /**
     * 用户买单记录
     * @param hashMap
     * @return
     */
    @Override
    public FrontPage fingBuyListByCustomerId(Map<String, String> hashMap) {
        return klgBuyTransactionService.findPageBySql(hashMap);
    }
    /**
     * 用户卖单记录
     * @param hashMap
     * @return
     */
    @Override
    public FrontPage fingSellListByCustomerId(Map<String, String> hashMap) {
        return klgSellTransactionService.findPageBySql(hashMap);
    }
    /**
     * 获取用户当前等级信息
     * @param hashMap
     * @return
     */
    @Override
    public JsonResult getCustomerLevelInfo(Map<String, String> hashMap) {
        Long customerId=Long.parseLong(hashMap.get("customerId"));
        KlgCustomerLevel klgCustomerLevel=klgCustomerLevelService.getLevelConfigByCustomerId(customerId);
        if(klgCustomerLevel!=null){
            boolean flg= checkDowngradeDay(customerId);
            if(flg){//超过15天 购买金额为 1星的数量  等级为 1星
                QueryFilter query=new QueryFilter(KlgLevelConfig.class);
                query.setOrderby("sort asc");
                KlgLevelConfig levelConfig= klgLevelConfigService.get(query);
                klgCustomerLevel.setLevelName(levelConfig.getLevelName());//显示1星
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
		Long customerId = Long.valueOf(params.get("customerId"));// 用户id
		//查询sme价格
		String issuePrice = (String) remoteKlgService.getPlatformRule1sConfig("issuePrice");
		/** 查询平台币Code */
		String ptbCoinCode = (String) remoteKlgService.getPlatformRule1sConfig("coinCode");

		//查询Usdt数量
		ExDigitalmoneyAccountRedis exaccountUsdt = this.selectAccount(customerId, "USDT");
		// 获取usdt热账户
		BigDecimal usdtHotMoney = exaccountUsdt.getHotMoney();
		// 获取usdt冷账户
		BigDecimal usdtColdMoney = exaccountUsdt.getColdMoney();

		BigDecimal usdtSum = usdtHotMoney.add(usdtColdMoney);

		//查询sme数量
		ExDigitalmoneyAccountRedis exaccountSme = this.selectAccount(customerId, ptbCoinCode);
		// 获取sme热账户
		BigDecimal smeHotMoney = exaccountSme.getHotMoney();
		// 获取sme冷账户
		BigDecimal smeColdMoney = exaccountSme.getColdMoney();

		BigDecimal smeSum = smeHotMoney.add(smeColdMoney);

		//查询冻结保证金数量
		BigDecimal firstSum = klgBuyTransactionService.getBuyFirstMoneySum(customerId);
		if(firstSum==null){
			firstSum = new BigDecimal(0);
		}
		//这个usdt总资产
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
	public JsonResult getPlatformCoinPrice() {
		// TODO Auto-generated method stub
		//查询sme价格
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
		Long customerId = Long.valueOf(hMap.get("customerId"));// 用户id
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
