package hry.ico.remote;

import com.alibaba.fastjson.JSON;
import hry.bean.JsonResult;
import hry.bean.ObjectUtil;
import hry.exchange.account.service.ExDmHotAccountRecordService;
import hry.front.redis.model.UserRedis;
import hry.ico.model.*;
import hry.ico.model.util.IcoAccountAtioPo;
import hry.ico.model.util.IcoAwardPo;
import hry.ico.model.util.RecommenderOrder;
import hry.ico.remote.model.RulesConfig;
import hry.ico.service.*;
import hry.manage.remote.RemoteManageService;
import hry.manage.remote.model.RemoteResult;
import hry.message.model.MessageType;
import hry.message.model.RemoteMessage;
import hry.mq.producer.service.MessageProducer;
import hry.redis.common.dao.RedisUtil;
import hry.redis.common.utils.RedisService;
import hry.trade.redis.model.Accountadd;
import hry.trade.redis.model.ExDigitalmoneyAccountRedis;
import hry.util.QueryFilter;
import hry.util.date.DateUtil;
import util.BigDecimalUtil;
import util.ToAccountUtil;
import util.UserRedisUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 奖励业务
 */
public class RemoteIcoRewardServiceImpl implements  RemoteIcoRewardService {
    //锁仓比率 40%
    private final double RATIO=0.4;
    @Resource
    private IcoLockRewardService lockRewardService;
    @Resource
    private IcoLockRecordService icoLockRecordService;
    @Resource
    private IcoAccountService icoAccountService;
    @Resource
    private RemoteManageService remoteManageService;
    @Resource
    private RedisService redisService;
    @Resource
    private IcoAwardRecordService icoAwardRecordService;
    @Resource
    private IcoTransactionRecordService icoTransactionRecordService;
    @Resource
    private MessageProducer messageProducer;

    @Resource
    private  IcoSendExpService icoSendExpService;
    @Resource
    private IcoDividendManualRecordService dividendManualRecordService;
    @Resource
    private  CoinCodeKeepDigitService coinCodeKeepDigitService;
    @Resource
    private IcoRepairDataService icoRepairDataService;

    @Override
    public boolean experienceAward(String msgText) {

        //2019年3月19日  需求改的去除（改为立即发放经验。）
      /*  try {
            IcoAwardPo icoAwardPo=JSON.parseObject(msgText,IcoAwardPo.class);
            Long customerId =icoAwardPo.getCustomerId();
            IcoLockReward icoLockReward=lockRewardService.get(new QueryFilter(IcoLockReward.class).addFilter("customerId=",customerId));
            Long  now= icoAwardPo.getTime();
            if(now==null||now.longValue()==0l){
                now=System.currentTimeMillis();
            }
            if(icoLockReward==null){//第一次操作
                icoLockReward=new IcoLockReward();
                icoLockReward.setCountingTime(now);
                //icoLockReward.setCountingTime(System.currentTimeMillis());
                icoLockReward.setCustomerId(customerId);
                lockRewardService.save(icoLockReward);
                return true;
            }
           //Long now=System.currentTimeMillis();//当前日期
            Long countingTime= icoLockReward.getCountingTime();//上次计时间
            BigDecimal currentLockSum= icoAwardPo.getCurrentLockSum();
            int hour=differHourNum(countingTime,now);//计算上次相差小时数
            long experience=currentLockSum.multiply(new BigDecimal(hour)).longValue();//获得的经验
            *//**
             * 给用户添加或减少经验值
             * @param customerId  用户id
             * @param account_type  交易类型（0101 锁仓奖励，0102 注册赠送，0201 锁仓扣除，0202 释放扣除）
             * @param experience  经验值（无正负）
             * @param money 持币数（交易类型属于 0102时 传null）
             * @param upgradeNote  备注
             * @return
             *//*
            //System.out.println("=====要发送的经验"+experience+"====上次时间countingTime"+countingTime+"===当前时间"+now+"===小时数"+hour);
            if(experience>0l){
                RemoteResult result=remoteManageService.clearingExperience(customerId,
                        "0101",experience,currentLockSum,"锁仓奖励");
            }
            icoLockReward.setCountingTime(System.currentTimeMillis()/1000*1000);
            lockRewardService.update(icoLockReward);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;*//*  try {
            IcoAwardPo icoAwardPo=JSON.parseObject(msgText,IcoAwardPo.class);
            Long customerId =icoAwardPo.getCustomerId();
            IcoLockReward icoLockReward=lockRewardService.get(new QueryFilter(IcoLockReward.class).addFilter("customerId=",customerId));
            Long  now= icoAwardPo.getTime();
            if(now==null||now.longValue()==0l){
                now=System.currentTimeMillis();
            }
            if(icoLockReward==null){//第一次操作
                icoLockReward=new IcoLockReward();
                icoLockReward.setCountingTime(now);
                //icoLockReward.setCountingTime(System.currentTimeMillis());
                icoLockReward.setCustomerId(customerId);
                lockRewardService.save(icoLockReward);
                return true;
            }
           //Long now=System.currentTimeMillis();//当前日期
            Long countingTime= icoLockReward.getCountingTime();//上次计时间
            BigDecimal currentLockSum= icoAwardPo.getCurrentLockSum();
            int hour=differHourNum(countingTime,now);//计算上次相差小时数
            long experience=currentLockSum.multiply(new BigDecimal(hour)).longValue();//获得的经验
            *//**
             * 给用户添加或减少经验值
             * @param customerId  用户id
             * @param account_type  交易类型（0101 锁仓奖励，0102 注册赠送，0201 锁仓扣除，0202 释放扣除）
             * @param experience  经验值（无正负）
             * @param money 持币数（交易类型属于 0102时 传null）
             * @param upgradeNote  备注
             * @return
             *//*
            //System.out.println("=====要发送的经验"+experience+"====上次时间countingTime"+countingTime+"===当前时间"+now+"===小时数"+hour);
            if(experience>0l){
                RemoteResult result=remoteManageService.clearingExperience(customerId,
                        "0101",experience,currentLockSum,"锁仓奖励");
            }
            icoLockReward.setCountingTime(System.currentTimeMillis()/1000*1000);
            lockRewardService.update(icoLockReward);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }*/
        return true;
    }

    /**
     * 购买平台币交易推荐与首持奖励
     */
    @Override
    public JsonResult recommendReward(String msgText) {
        RecommenderOrder reward=JSON.parseObject(msgText,RecommenderOrder.class);
        Long superior_id = reward.getSuperiorCustomerId();//上级Id
        Long customerId = reward.getCustomerId();//我的Id
        BigDecimal award_radix=reward.getNumber();//奖励基数（购买平台的币数量）
        //msgText  是否首持   金额  to用户
        Map<String,String> cfMap=redisService.getMap(RulesConfig.RulesCoinLikeKey);//平台规则
        String coinCode=cfMap.get("coinCode");//平台Code
        String award_num=String.valueOf(System.currentTimeMillis());//流水ID
        RedisUtil<UserRedis> redisUtil = new RedisUtil<UserRedis>(UserRedis.class);
        UserRedis userRedis = redisUtil.get(superior_id.toString());
        ExDigitalmoneyAccountRedis exAccount = UserRedisUtils.getAccount(userRedis.getDmAccountId(coinCode).toString(),coinCode);
        Long accountId = userRedis.getDmAccountId(exAccount.getCoinCode());
        List<Accountadd> listLock=new ArrayList<>();
        BigDecimal platformNum=new BigDecimal(0);
        if(reward.getMyCount()==0){//我第一次购买 给上家发送首持固定奖励
            BigDecimal  firstPrize=new BigDecimal(cfMap.get("firstPrize"));//首持固定值
            platformNum=firstPrize;
            exAccount.setHotMoney(exAccount.getHotMoney().add(firstPrize));
            Accountadd accountadd= ToAccountUtil.ncomeAssets(accountId,firstPrize);
            listLock.add(accountadd);
            IcoAwardRecord firstRecord=new IcoAwardRecord();//首持奖励发放
            firstRecord.setAward_num(award_num);
            firstRecord.setCoinCode(coinCode);
            firstRecord.setAward_type("1");
            firstRecord.setCustomer_id(superior_id);//用户Id
            firstRecord.setReferrals_id(customerId);//下级Id
            firstRecord.setAward_radix(award_radix);//奖励基数
            firstRecord.setAward_quantity(firstPrize);//奖励数量
            firstRecord.setStatus("2");//成功
            //交易流水
            IcoTransactionRecord transactionRecord=new IcoTransactionRecord();
            transactionRecord.setCustomerId(superior_id);
            transactionRecord.setProjectNumber(award_num);//流水号
            transactionRecord.setCoinCode(coinCode);
            transactionRecord.setColdMoney(exAccount.getColdMoney());
            transactionRecord.setHotMoney(exAccount.getHotMoney());
            transactionRecord.setType(32);// 11.锁仓12.释放21转账31分红32推荐奖励 41.充币42.提币51.买入52.卖出
            transactionRecord.setState(202);//201.支出202.收入
            transactionRecord.setIsShow(1);
            transactionRecord.setTransactionCount(firstPrize);
            transactionRecord.setRemark("首推奖励");
            icoAwardRecordService.save(firstRecord);
            transactionRecord.setForeignKey(firstRecord.getId());
            icoTransactionRecordService.save(transactionRecord);
        }
        BigDecimal  recommendReward=new BigDecimal(cfMap.get("recommendReward"))
                .divide(new BigDecimal(100),8,BigDecimal.ROUND_DOWN);//推荐
        BigDecimal award_quantity=recommendReward.multiply(award_radix);
        platformNum=platformNum.add(award_quantity);
        exAccount.setHotMoney(exAccount.getHotMoney().add(award_quantity));//交易的5%推荐奖励
        Accountadd accountadd= ToAccountUtil.ncomeAssets(accountId,award_quantity);
        listLock.add(accountadd);
        IcoAwardRecord icoAwardRecord=new IcoAwardRecord();//推荐奖励发放
        icoAwardRecord.setAward_num(award_num);
        icoAwardRecord.setCoinCode(coinCode);
        icoAwardRecord.setAward_type("2");//（1 首持奖励， 2  推荐奖励）
        icoAwardRecord.setCustomer_id(superior_id);//用户Id
        icoAwardRecord.setReferrals_id(customerId);//下级Id
        icoAwardRecord.setAward_radix(award_radix);//奖励基数
        icoAwardRecord.setAward_quantity(award_quantity);//奖励数量
        icoAwardRecord.setStatus("2");//成功
        icoAwardRecordService.save(icoAwardRecord);
        //交易流水
        IcoTransactionRecord transactionRecord=new IcoTransactionRecord();
        transactionRecord.setCustomerId(superior_id);
        transactionRecord.setProjectNumber(award_num);//流水号
        transactionRecord.setCoinCode(coinCode);
        transactionRecord.setColdMoney(exAccount.getColdMoney());
        transactionRecord.setHotMoney(exAccount.getHotMoney());
        transactionRecord.setType(32);// 11.锁仓12.释放21转账31分红32推荐奖励 41.充币42.提币51.买入52.卖出
        transactionRecord.setState(202);//201.支出202.收入
        transactionRecord.setTransactionCount(award_quantity);
        transactionRecord.setRemark("推荐奖励");
        transactionRecord.setIsShow(1);
        transactionRecord.setForeignKey(icoAwardRecord.getId());
        icoTransactionRecordService.save(transactionRecord);

        RemoteMessage message=new RemoteMessage();
        message.setMsgKey(MessageType.Message_Reward_Distribution.getKey());//消息类型 模板KEY//
        message.setMsgType("1,2");// 1.站内信，2.短信,
        message.setUserId(superior_id.toString());
        messageProducer.toMessageWarn(JSON.toJSONString(message));//消息提醒

        messageProducer.toPlatformCurrency(platformNum.multiply(new BigDecimal(-1)).stripTrailingZeros().toPlainString());//平台币支出
        messageProducer.toAccount(JSON.toJSONString(listLock));
        return new JsonResult().setSuccess(true).setMsg("成功");
    }

    //计算俩时间的小时数差
    private static int differHourNum(long time1,long time2){
        long dif=((time2/1000)-(time1/1000))/(60*60);
        //long dif=(time2-time1)/(60*1000);
        return ((Long) dif).intValue();
    }

    //2019年3月28日 修改 根据锁仓数*天数*24小时发经验
    @Override
    public  JsonResult sendExp(){
       System.out.println("同步经数据验信息");
       List<IcoLockRecord> lockRecords= icoLockRecordService.find(new QueryFilter(IcoLockRecord.class).addFilter("state=","0"));
       if(lockRecords!=null&&lockRecords.size()>0){
                Long now=System.currentTimeMillis();
           for (IcoLockRecord lockRecord: lockRecords) {
               Long customerId=lockRecord.getCustomerId();
            //   IcoLockReward icoLockReward=lockRewardService.get(new QueryFilter(IcoLockReward.class).addFilter("customerId=",customerId));
              // if(icoLockReward!=null){
                   BigDecimal number=lockRecord.getNumber();
                   Long relesaDate= lockRecord.getReleaseDate().getTime(); //释放时间
                 //  Long countingTime= icoLockReward.getCountingTime();//上次计时间
                  // int hour=differHourNum(countingTime,relesaDate);//计算上次相差小时数
                   int hour=lockRecord.getLockDay().intValue()*24;//计算上次相差小时数
                   long experience=number.multiply(new BigDecimal(hour)).longValue();//获得的经验
                   IcoSendExp icoSendExp=new IcoSendExp();
                   icoSendExp.setCountingTime(now);
                   icoSendExp.setCustomerId(customerId.toString());
                   icoSendExp.setHour(hour);
                   icoSendExp.setNumber(number);
                   icoSendExp.setReleaseDate(relesaDate);
                   icoSendExp.setExperience(experience);
                   icoSendExp.setLockId(lockRecord.getId());
                   icoSendExpService.save(icoSendExp);
              // }
           }
       }
       return  new JsonResult().setSuccess(true).setMsg("操作成功");
    }

    /**
     * 发放分红
     * 后台能够填写分红币种，
     * 分红原因，分红数量，
     * 能够选择给等级多少以上的用户发分红，
     * 然后按照锁仓比来给用户分配分红币数量
     *
     *
     * @param hashMap
     * @return
     */
    @Override
    public JsonResult grantDividend(HashMap<String, String> hashMap) {
        String reason=hashMap.get("reason");//分红原因
        String coinCode=hashMap.get("coinCode");//分红币种
        BigDecimal dividendNum=new BigDecimal(hashMap.get("dividendNum"));//分红总量
        Integer levelSort=hashMap.get("levelSort")==null?null:Integer.valueOf(hashMap.get("levelSort"));//等级排序
        List<IcoAccountAtioPo>  accountAtioPos=icoAccountService.getAccountAtioBylevelSort(levelSort);
        if(accountAtioPos!=null&&accountAtioPos.size()>0){

            RedisUtil<UserRedis> redisUtil = new RedisUtil<UserRedis>(UserRedis.class);
//            int atioSize=accountAtioPos.size();
            for (IcoAccountAtioPo atioPo:accountAtioPos) {
                List<Accountadd> listLock=new ArrayList<>();
                Long customerId=atioPo.getCustomerId();
                //锁仓比= （自身锁仓数/所有用户的总锁仓数)*40%*(100%+等级加持百分比)
                BigDecimal  accountAtio= new BigDecimal(atioPo.getAccountAtio());//自身占比；
                BigDecimal additionRatio=(new BigDecimal(atioPo.getAdditionRatio()).add(new BigDecimal(100))).divide(new BigDecimal(100),8,BigDecimal.ROUND_DOWN);//等级加成比率
                accountAtio=BigDecimalUtil.bigDecimalScaleDigit(accountAtio.multiply(additionRatio).multiply(new BigDecimal(RATIO)),6);
                //TODO 根据后台配置保留位数
                //保留的小数位
                //int keepDigit=coinCodeKeepDigitService.getCoinCodeKeepDigit(coinCode);
                int keepDigit=4;
                BigDecimal sendNum= BigDecimalUtil.bigDecimalScaleDigit(accountAtio.multiply(dividendNum),keepDigit);

                UserRedis userRedis = redisUtil.get(customerId.toString());
                if(userRedis==null){
                    continue;
                }
                ExDigitalmoneyAccountRedis exAccount = UserRedisUtils.getAccount(userRedis.getDmAccountId(coinCode).toString(),coinCode);
                Long accountId = userRedis.getDmAccountId(exAccount.getCoinCode());
                exAccount.setHotMoney(exAccount.getHotMoney().add(sendNum));
                Accountadd accountadd= ToAccountUtil.ncomeAssets(accountId,sendNum);
                listLock.add(accountadd);
                IcoDividendManualRecord info=new IcoDividendManualRecord();//记录
                info.setAccountAtio(accountAtio);//自身占比
                info.setCoinCode(coinCode);
                info.setCustomerId(customerId);//用户Id
                info.setDividendNum(dividendNum);//分红总量/基数
                info.setReason(reason);//分红原因
                info.setNumber(sendNum);
                dividendManualRecordService.save(info);
                //交易流水
                IcoTransactionRecord transactionRecord=new IcoTransactionRecord();
                transactionRecord.setCustomerId(customerId);
                transactionRecord.setProjectNumber(String.valueOf(System.currentTimeMillis()));//流水号
                transactionRecord.setCoinCode(coinCode);
                transactionRecord.setColdMoney(exAccount.getColdMoney());
                transactionRecord.setHotMoney(exAccount.getHotMoney());
                transactionRecord.setType(33);// 11.锁仓12.释放21转账31分红32推荐奖励33后台分红奖励 41.充币42.提币51.买入52.卖出
                transactionRecord.setState(202);//201.支出202.收入
                transactionRecord.setTransactionCount(sendNum);
                transactionRecord.setRemark("后台分红发放");
                transactionRecord.setIsShow(1);
                transactionRecord.setForeignKey(info.getId());
                icoTransactionRecordService.save(transactionRecord);

                messageProducer.toAccount(JSON.toJSONString(listLock));
            }
        }
        return new JsonResult().setSuccess(true).setMsg("success");
    }

    /**
     * 数据修复
     * @return
     */
    @Override
    public JsonResult repairData() {
          return  repairData2();
    }

    //2019年4月3日11点08分 修复多发分红被锁仓和被提币问题
    private  JsonResult repairData2(){
        RedisUtil<UserRedis> redisUtil = new RedisUtil<UserRedis>(UserRedis.class);
        String [] ids={"580","581","582","583"};//要修正的订单
        QueryFilter q=new QueryFilter(IcoLockRecord.class);
       // q.addFilter("id_in",ids);
        q.addFilter("id_in","580,581,582,583");
        q.addFilter("state=","0");//未释放
        List<IcoLockRecord> ls=icoLockRecordService.find(q);
        if(ls!=null&&ls.size()>0){
            for (IcoLockRecord lock:ls) {
                List<Accountadd> listLock = new ArrayList<>();
                Long customerId=lock.getCustomerId();
                String coinCode=lock.getCoinCode();
                UserRedis userRedis = redisUtil.get(String.valueOf(customerId));
                Long accountId = userRedis.getDmAccountId(coinCode);
                BigDecimal number=lock.getNumber();
                lock.setActualReleaseNum(number);//实际释放数量
                lock.setReleaseDeductNum(new BigDecimal(0));//释放扣币数量
                lock.setReleaseType(1);
                ExDigitalmoneyAccountRedis exAccount = UserRedisUtils.getAccount(userRedis.getDmAccountId(coinCode).toString(), coinCode);
                BigDecimal  hotMoney= exAccount.getHotMoney();//当前可用为 负
                if(hotMoney.compareTo(BigDecimal.ZERO)==0||hotMoney.compareTo(BigDecimal.ZERO)==1){
                    continue;
                }
                BigDecimal releaseDeduct= (hotMoney.multiply(new BigDecimal(-1))).multiply(new BigDecimal(lock.getLockDay().intValue()*24));
                lock.setReleaseDeduct(releaseDeduct);
                lock.setReleaseDeductType(1);
                List<Accountadd> accountadds = ToAccountUtil.unblockedAssets(accountId, number);//释放 冻结转可用
                listLock.addAll(accountadds);
                String orderNumber = String.valueOf(System.currentTimeMillis());
                IcoTransactionRecord transactionRecord = new IcoTransactionRecord();
                transactionRecord.setCustomerId(customerId);
                transactionRecord.setProjectNumber(orderNumber);//流水号
                transactionRecord.setCoinCode(coinCode);
                transactionRecord.setColdMoney(exAccount.getColdMoney());
                transactionRecord.setHotMoney(exAccount.getHotMoney());
                transactionRecord.setType(12);// 11.锁仓12.释放13锁仓扣除21转账31分红32推荐奖励 41.充币42.提币51.买入52.卖出
                transactionRecord.setState(202);//201.支出202.收入
                transactionRecord.setTransactionCount(number);
                transactionRecord.setRemark("释放存储转流通(修复数据扣除)");
                transactionRecord.setIsShow(1);
                transactionRecord.setForeignKey(lock.getId());
                IcoAccount icoAccount=icoAccountService.get(new QueryFilter(IcoAccount.class).addFilter("customerId=",customerId));
                boolean f = icoLockRecordService.releaseLockRecord(lock);
                if (f) {
                    boolean account= icoAccountService.updateByAccountId( icoAccount.getId(),icoAccount.getVersion(), number.multiply(new BigDecimal(-1)));
                    if(!account){
                        throw new RuntimeException();
                    }
                    icoTransactionRecordService.save(transactionRecord);//交易流水
                    messageProducer.toAccount(JSON.toJSONString(listLock));

                    //释放扣除经验
                    RemoteResult result=remoteManageService.clearingExperience(Long.valueOf(customerId),"0202",releaseDeduct.longValue(),number,"释放扣除(修复数据扣除)");
                    if(!result.getSuccess()){
                        System.out.println("释放扣除经验异常");
                    }
//                    return new JsonResult().setSuccess(true).setMsg("shifangchenggong");//释放成功
                    System.out.println("修复数据释放成功-"+lock.getId());
                }
            }

            return new JsonResult().setSuccess(true).setMsg("执行完成");
        }
        String [] cusId={"481498","481480"};//提币的用户 账户归0
        for(String str:cusId){
            List<Accountadd> listLock = new ArrayList<>();
            UserRedis userRedis = redisUtil.get(str);
            Long accountId = userRedis.getDmAccountId("NGEL");
            ExDigitalmoneyAccountRedis exAccount = UserRedisUtils.getAccount(userRedis.getDmAccountId("NGEL").toString(), "NGEL");
            BigDecimal  hotMoney= exAccount.getHotMoney();//当前可用为 负
            if(hotMoney.compareTo(BigDecimal.ZERO)==0||hotMoney.compareTo(BigDecimal.ZERO)==1){
                continue;
            }
            hotMoney=hotMoney.multiply(new BigDecimal(-1));
            Accountadd accountadds = ToAccountUtil.ncomeAssets(accountId, hotMoney);//收入可用
            listLock.add(accountadds);
            messageProducer.toAccount(JSON.toJSONString(listLock));
            System.out.println("修复数据释放成功-"+str);
        }

        return new JsonResult().setSuccess(false).setMsg("无数据");
    }






    //2019年4月1日 修复分红多发问题
    private JsonResult repairData1(){
        List<IcoRepairData> list=icoRepairDataService.find(new QueryFilter(IcoRepairData.class).addFilter("state=",0));
        if(list!=null&&list.size()>0){
            RedisUtil<UserRedis> redisUtil = new RedisUtil<UserRedis>(UserRedis.class);
            for (IcoRepairData data:list) {
                List<Accountadd> listLock=new ArrayList<>();
                String coinCode= data.getCoinCode();
                BigDecimal sendNum=data.getSum();
                sendNum=sendNum.subtract(data.getNumber());
                UserRedis userRedis = redisUtil.get(data.getCustomerId().toString());
                if(userRedis==null){
                    continue;
                }
                ExDigitalmoneyAccountRedis exAccount = UserRedisUtils.getAccount(userRedis.getDmAccountId(coinCode).toString(),coinCode);
                Long accountId = userRedis.getDmAccountId(exAccount.getCoinCode());
                Accountadd accountadd= ToAccountUtil.expenditureHotAssets(accountId,sendNum);
                listLock.add(accountadd);
                messageProducer.toAccount(JSON.toJSONString(listLock));
                data.setState(1);
                icoRepairDataService.update(data);
            }
            return new JsonResult().setSuccess(true).setMsg("执行完成");
        }
        return new JsonResult().setSuccess(false).setMsg("无数据");
    }
    public static void main(String[] args) {
     /*      int a = differHourNum(1552270740000l,1552273200000l);
        System.out.println(a);
*/

    }
}
