/**
 * Copyright:
 *
 * @author: javalx
 * @version: V1.0
 * @Date: 2019-06-10 11:13:59
 */
package hry.social.miningreward.service.impl;

import com.alibaba.fastjson.JSON;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSONArray;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;
import hry.front.redis.model.UserRedis;
import hry.manage.remote.RemoteManageService;
import hry.manage.remote.model.User;
import hry.model.message.AppException;
import hry.model.social.miningreward.SocialMiningGatherRecord;
import hry.model.social.miningreward.SocialMiningRewardRecord;
import hry.model.social.miningreward.SocialPickData;
import hry.model.social.miningreward.SocialPickRedis;
import hry.model.social.traderecord.SocialCoinTradeRecord;
import hry.mq.consumer.social.miningreward.CoinRewardMessage;
import hry.mq.consumer.social.miningreward.MiningRewardMessage;
import hry.mq.consumer.social.miningreward.MiningSyncCacheMessage;
import hry.mq.message.SocialMessageProducer;
import hry.mq.message.service.AppExceptionService;
import hry.redis.common.dao.RedisUtil;
import hry.redis.common.utils.RedisService;
import hry.social.manage.remote.model.miningreward.SocialMiningRuleConfig;
import hry.social.miningreward.dao.SocialMiningGatherRecordDao;
import hry.social.miningreward.dao.SocialMiningRewardRecordDao;
import hry.social.miningreward.service.SocialMiningGatherRecordService;
import hry.social.traderecord.dao.SocialCoinTradeRecordDao;
import hry.social.transaction.model.ExDmTransaction;
import hry.social.transaction.service.ExDmTransactionService;
import hry.trade.redis.model.Accountadd;
import hry.trade.redis.model.ExDigitalmoneyAccountRedis;
import hry.util.HttpClientNodeUtil;
import hry.util.NoticeTemplateUtil;
import hry.util.SocialUtil;
import hry.util.UserRedisUtils;
import hry.trade.model.AccountRemarkEnum;
import hry.util.idgenerate.IdGenerate;
import hry.util.idgenerate.NumConstant;
import hry.util.sys.ContextUtil;
import io.swagger.models.auth.In;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.*;

/**
 * <p> SocialMiningGatherRecordService </p>
 *
 * @author: javalx
 * @Date :          2019-06-10 11:13:59
 */
@Service("socialMiningGatherRecordService")
public class SocialMiningGatherRecordServiceImpl extends BaseServiceImpl<SocialMiningGatherRecord,Long> implements SocialMiningGatherRecordService {

    @Resource(name = "socialMiningGatherRecordDao")
    @Override
    public void setDao(BaseDao<SocialMiningGatherRecord,Long> dao) {
        super.dao = dao;
    }

    private final Logger log = Logger.getLogger(SocialMiningGatherRecord.class);

    @Resource
    SocialMiningRewardRecordDao socialMiningRewardRecordDao;
    @Resource
    SocialMiningGatherRecordDao socialMiningGatherRecordDao;

    @Resource
    SocialCoinTradeRecordDao socialCoinTradeRecordDao;

    @Resource
    private SocialMessageProducer socialMessageProducer;

    @Resource
    private RedisService redisService;

    @Resource
    private RemoteManageService remoteManageService;

    @Resource
    private ExDmTransactionService exDmTransactionService;

    /**
     * 果园奖励收取消费处理
     *
     * @param message
     */
    @Override
    public void miningReward(String message) {
        try {
            System.out.println("果子收取MQ信息====>>>" + message);
            if (null == message) {
                return;
            }
            MiningRewardMessage miningRewardMessage = JSON.parseObject(message, MiningRewardMessage.class);
            Integer gatherType = miningRewardMessage.getGatherType();
            BigDecimal gatherRatio = miningRewardMessage.getGatherRatio();
            BigDecimal gatherNum = miningRewardMessage.getGatherNum();
            Long gatherId = miningRewardMessage.getGatherId();
            Long rewardId = miningRewardMessage.getRewardId();
            String coinCode = miningRewardMessage.getCoinCode();
            BigDecimal residualNum = miningRewardMessage.getResidualNum();
            Long customerId = miningRewardMessage.getCustomerId();
            String rewardSource = miningRewardMessage.getRewardSource();

            //更新奖励信息
            SocialMiningRewardRecord smrr = socialMiningRewardRecordDao.selectByPrimaryKey(rewardId);
            if (gatherType == null || smrr == null) {
                System.out.println("====收取类型：" + gatherType + " ---SMGRSI :: 120--- 收取类型====");
                System.out.println("====奖励信息：" + smrr + " ---SMGRSI :: 121--- 奖励信息====");
                System.out.println("====已有收取记录---SMGRSI :: 122---已有收取记录====");
                redisService.delete(SocialUtil.GATHERED_CACHE + ":" + rewardId.toString() + ":" + gatherId.toString());
                return;
            }

            //判断是否已收取
            int hasPick = socialMiningGatherRecordDao.hasPick(gatherId, rewardId);
            if (hasPick > 0) {
                System.out.println("====已有收取记录---SMGRSI :: 129---已有收取记录====");
                redisService.delete(SocialUtil.GATHERED_CACHE + ":" + rewardId.toString() + ":" + gatherId.toString());
                return;
            }

            SocialMiningGatherRecord smgr = new SocialMiningGatherRecord();
            smgr.setCoinCode(coinCode);
            smgr.setRewardId(rewardId);
            smgr.setGatherNum(gatherNum);
            smgr.setGatherId(gatherId);
            smgr.setGatherType(gatherType);
            smgr.setGatherTime(new Date());
            smgr.setCustomerId(customerId);
            socialMiningGatherRecordDao.insertSelective(smgr);
            // 新增流水记录
            SocialCoinTradeRecord sctr = new SocialCoinTradeRecord();
            //收支类型（0：收入 ， 1：支出）
            sctr.setCategory(0);
            //类型（0：奖励，1：充币，2：提币，3：支付）
            //类型（0：奖励(收)，1：充币(收)，2：提币(支)，3：购买平台币(收、支)，4转账(收、支)， 5打赏(收、支)，6上链(支)，7捐赠(支)）
            sctr.setType(0);
            //币种
            sctr.setCoinCode(coinCode);
            sctr.setStates(2);
            sctr.setSource(rewardSource);
            sctr.setNumber(gatherNum);
            sctr.setCustomerId(gatherId);
            //溯源标识(根据type和sourceNum可以找到具体的业务流水)
            sctr.setSourceNum(smgr.getId().toString());
            socialCoinTradeRecordDao.insertSelective(sctr);
            if (gatherType.intValue() == 1) {
                smrr.setGatherNum(gatherNum);
                smrr.setStates(2);
                smrr.setGatherTime(new Date());
                socialMiningRewardRecordDao.updateByPrimaryKeySelective(smrr);
            }

            // 发送账户消息维护消息=======================================
            RedisUtil<UserRedis> redisUtil = new RedisUtil<UserRedis>(UserRedis.class);
            UserRedis toUserRedis = redisUtil.get(gatherId.toString());
            ExDigitalmoneyAccountRedis digAccount = null;
            if (toUserRedis != null) {
                digAccount = UserRedisUtils.getAccount(toUserRedis.getDmAccountId(coinCode).toString(), coinCode);
            }
            if (digAccount == null) {
                digAccount = exDmTransactionService.getAccount4DB(customerId, coinCode);
            }
            Long digAccountId = digAccount.getId();
            User user = remoteManageService.getUserByCustomerId(customerId);
            String commonLanguage = user.getCommonLanguage();
            commonLanguage = StringUtils.isEmpty(commonLanguage) ? "zh_CN" : commonLanguage;
            // 交易所流水
            ExDmTransaction exDmTransaction = new ExDmTransaction();
            exDmTransaction.setCustomerId(customerId);
            String transactionNum = NumConstant.Ex_Dm_Transaction;
            exDmTransaction.setTransactionNum(IdGenerate.transactionNum(transactionNum));
            exDmTransaction.setAccountId(digAccountId);
            exDmTransaction.setTransactionType(1);  // 交易类型(1币收入 ，2币支出)
            exDmTransaction.setTransactionMoney(gatherNum);
            exDmTransaction.setCustomerName(user.getNickName());
            exDmTransaction.setStatus(2);   // 状态 1待审核 --2已完成 -- 3以否决
            exDmTransaction.setCoinCode(coinCode);
            exDmTransaction.setCurrencyType(commonLanguage);
            exDmTransaction.setFee(BigDecimal.ZERO);
            exDmTransaction.setOrderNo(sctr.getId().toString());
            exDmTransaction.setOptType(AccountRemarkEnum.TYPE500.getIndex());//TYPE500("数币奖励", 500), TYPE503("购买平台币", 503), TYPE505("朋友圈打赏", 505), TYPE506("许愿上链", 506), TYPE507("区块链公益捐赠", 507), TYPE508("购买矿机", 508), TYPE509("购买会员", 509);
            // 保存订单
            exDmTransactionService.save(exDmTransaction);

            // 发送充币mq通知缓存
            Accountadd accountTo = new Accountadd();
            accountTo.setAccountId(digAccountId);
            accountTo.setMoney(gatherNum);
            accountTo.setMonteyType(1);
            accountTo.setAcccountType(1);
            accountTo.setRemarks(27);
            accountTo.setTransactionNum(exDmTransaction.getTransactionNum());
            List<Accountadd> list = new ArrayList<Accountadd>();
            list.add(accountTo);
            socialMessageProducer.toAccount(JSON.toJSONString(list));

            redisService.delete(SocialUtil.GATHERED_CACHE + ":" + rewardId.toString() + ":" + gatherId.toString());
            // -----------------------end------------------------------
        } catch (Exception e) {
            System.out.println("====报错报错报错---SMGRSI :: 269---报错报错报错====");
            e.printStackTrace();
            AppException exceptionLog = new AppException();
            exceptionLog.setName("mq=======gather=======");
            AppExceptionService appExceptionService = (AppExceptionService) ContextUtil.getBean("appExceptionService");
            appExceptionService.save(exceptionLog);
            System.out.println("mq=======gather=======" + message);
            throw e;
        }
    }

    /**
     * 外部奖励消费处理
     *
     * @param message
     */
    @Override
    public void outReward(String message) {
        try {
            System.out.println("外部奖励MQ信息====>>>" + message);
            if (null == message) {
                return;
            }
            CoinRewardMessage crm = JSON.parseObject(message, CoinRewardMessage.class);
            String coinCode = crm.getCoinCode();//奖励币种
            Long customerId = crm.getCustomerId();//奖励用户ID
            Integer rewardType = crm.getRewardType();//奖励类型
            BigDecimal rewardNum = crm.getRewardNum();//奖励数量
            //奖励取舍后小于等于0则不计算奖励
            if (rewardNum.compareTo(BigDecimal.ZERO) <= 0) {
                return;
            }
            //生成奖励记录
            SocialMiningRewardRecord smrr = new SocialMiningRewardRecord();
            smrr.setCoinCode(coinCode);
            smrr.setCustomerId(customerId);
            smrr.setRewardNum(rewardNum);
            smrr.setRewardType(rewardType);
            smrr.setStates(0);
            Map<String,String> map = redisService.getMap("DIC:reward_type");
            if (map != null) {
                String rewardSource = map.get(String.valueOf(rewardType));
                smrr.setRewardSource(rewardSource);
            } else {
                if (rewardType.intValue() == 4) {
                    smrr.setRewardSource("shopping_reward");
                } else {
                    smrr.setRewardSource("distribution_reward");
                }
            }
            smrr.setBaseNum(BigDecimal.ZERO);
            smrr.setForceRatio(BigDecimal.ZERO);
            smrr.setGatherNum(BigDecimal.ZERO);
            socialMiningRewardRecordDao.insertSelective(smrr);
            //异步向REDIS中补充果子信息，并推送给app端
            //同步果园奖励缓存--社交
            MiningSyncCacheMessage mscm = new MiningSyncCacheMessage();
            mscm.setCustomerId(customerId);
            socialMessageProducer.syncCacheReward(JSON.toJSONString(mscm));
//            ThreadPool.exe(new SyncPickedRunnable(customerId));
            // -----------------------end------------------------------
        } catch (Exception e) {
            System.out.println("====报错报错报错---SMGRSI :: 269---报错报错报错====");
            e.printStackTrace();
            AppException exceptionLog = new AppException();
            exceptionLog.setName("mq=======gather=======");
            AppExceptionService appExceptionService = (AppExceptionService) ContextUtil.getBean("appExceptionService");
            appExceptionService.save(exceptionLog);
            System.out.println("mq=======gather=======" + message);
            throw e;
        }
    }

    /**
     * 同步奖励缓存并推送奖励信息
     *
     * @param message
     */
    @Override
    public void syncCacheReward(String message) {
        System.out.println("同步奖励缓存并推送奖励MQ信息====>>>" + message);
        if (null == message) {
            return;
        }
        //推送数据集
        List<SocialPickData> pushData = new ArrayList<>();
        RedisService redisService = (RedisService) ContextUtil.getBean("redisService");
        MiningSyncCacheMessage mscm = JSON.parseObject(message, MiningSyncCacheMessage.class);
        Long customerId = mscm.getCustomerId();
        String cidStr = customerId.toString();
        String cacheKey = SocialUtil.SOCIAL_GATHERED_CACHE + cidStr;
        Map<String,String> cacheRewardMap = null;
        boolean lock = redisService.lock(cacheKey);
        if (lock) {
            try {
                cacheRewardMap = redisService.getMap(cacheKey);
                int limt = (cacheRewardMap == null) ? 10 : (10 - cacheRewardMap.size());
                if (limt > 0) {//limt=0 说明没有需要放入缓存中的
                    List<SocialPickRedis> list = socialMiningRewardRecordDao.findPicked(customerId, limt);
                    if (list != null && list.size() > 0) {
                        for (SocialPickRedis spr : list) {
                            Long id = spr.getId();
                            String coinCode = spr.getCoinCode();
                            Integer rewardType = spr.getRewardType();

                            String key = id.toString();
                            String cacheReward = cacheRewardMap.get(key);
                            // 如果redis中的不为空则跳过
                            if (!StringUtils.isEmpty(cacheReward)) {
                                socialMiningRewardRecordDao.updateStates(id);
                                continue;
                            }
                            // 更新状态标明已放入缓存中
                            String imgPath = null;
                            Map<String,String> mrccMap = null;
                            //奖励类型(1:算力奖励 2:任务奖励 3:矿机奖励……)
                            Map<String,String> rewardMap = redisService.getMap("DIC:reward_type");
                            if (rewardMap == null) {
                                System.out.println("====奖励类型缓存错误====");
                                break;
                            }
                            String rewardTypeCache = rewardMap.get(rewardType.toString());
                            if (rewardTypeCache == null) {
                                System.out.println("====奖励类型缓存错误====");
                                continue;
                            }
                            rewardTypeCache = "SOCIAL:CACHE:" + rewardTypeCache.toUpperCase() + "_RULE_CONF";
                            mrccMap = redisService.getMap(rewardTypeCache);
                            if (mrccMap == null) {
                                System.out.println("====果园奖励配置错误====");
                                continue;
                            }
                            String mrcStr = mrccMap.get(coinCode);
                            if (mrcStr == null) {
                                System.out.println("====果园奖励配置错误====");
                                continue;
                            }
                            SocialMiningRuleConfig smrc = JSONArray.parseObject(mrcStr, SocialMiningRuleConfig.class);
                            imgPath = smrc.getImage();
                            spr.setImgNum("10");
                            spr.setImgPath(imgPath);
                            //TODO 设置超时时间
                            String validity_time = redisService.hget("SOCIAL:CACHE:REWARD_CONF", "validity_time");
                            if (!StringUtils.isEmpty(validity_time)) {
                                long validityTime = Long.parseLong(validity_time);
                                if (validityTime > 0) {
                                    Calendar calendar = Calendar.getInstance();
                                    calendar.clear();
                                    Date created = spr.getCreated();
                                    calendar.setTime(created);
                                    long millis = calendar.getTimeInMillis();
                                    long expiry = millis + validityTime * 3600 * 1000;
                                    spr.setExpiryTime(expiry);
                                }
                            }
                            redisService.hset(cacheKey, key, JSON.toJSONString(spr));
                            //需要推送的果子数据
                            SocialPickData socialPickData = new SocialPickData();
                            socialPickData.setId(id);
                            socialPickData.setCustomerId(spr.getCustomerId());
                            socialPickData.setResidualNum(spr.getResidualNum());
                            socialPickData.setIsAbleGather(1);  //是否可收取，0否1是
                            socialPickData.setIsSelfGather(0);  //是否本人收取完了，0否1是
                            socialPickData.setIsNewCache(1);    //是否新增的果子
                            socialPickData.setRewardSource(spr.getRewardSource()); //奖励来源
                            socialPickData.setRewardType(spr.getRewardType());//奖励类型
                            socialPickData.setImgNum("10");//结晶图片类型
                            socialPickData.setImgPath(imgPath);//结晶图片
                            socialPickData.setCoinCode(spr.getCoinCode());
                            pushData.add(socialPickData);
                            socialMiningRewardRecordDao.updateStates(id);
                        }
                    }
                }
                redisService.unLock(cacheKey);
            } catch (Exception e) {
                System.out.println("================================ 缓存果园奖励失败，回滚缓存 ================================");
                System.out.println("================================ 缓存果园奖励失败，回滚缓存 ================================");
                System.out.println("================================ 缓存果园奖励失败，回滚缓存 ================================");
                e.printStackTrace();
                //回滚缓存
                for (SocialPickData spd : pushData) {
                    String key = spd.getId().toString();
                    redisService.hdel(cacheKey, key);
                }
                pushData.clear();
                return;
            } finally {
                redisService.unLock(cacheKey);
            }
        }
        //推送果子消息啊
        if (pushData != null && pushData.size() > 0) {
            Map<String,Object> map = new HashMap();
            map.put("destination", customerId.toString());
            map.put("pushData", pushData);
            String pushUrl = NoticeTemplateUtil.getPushUrl(customerId);
            try {
                if (!StringUtils.isEmpty(pushUrl)) {
                    pushUrl = pushUrl + "/fruitPush";
                    String post = HttpClientNodeUtil.sendHttpPost(pushUrl, JSON.toJSONString(map));
                }
            } catch (Exception e) {
                System.out.println("push socialPick datas failed");
            }
        }
    }

    /**
     * 清除过期缓存奖励
     */
    @Override
    public void cleanupCacheReward() {
        // 先失效数据库中的奖励
        Map<String,String> map = redisService.getMap("SOCIAL:CACHE:REWARD_CONF");
        if (map != null) {
            String validity_time = map.get("validity_time");
            if (!StringUtils.isEmpty(validity_time)){
                try {
                    Integer expiryHour = Integer.valueOf(validity_time);
                    socialMiningRewardRecordDao.expiryReward(expiryHour);
                }catch (Exception e){
                }
            }
        }

        List<Long> list = socialMiningGatherRecordDao.getAllUser();
        for (Long customerId : list) {
            String cidStr = customerId.toString();
            String cacheKey = SocialUtil.SOCIAL_GATHERED_CACHE + cidStr;
            Map<String,String> cacheRewardMap = null;
            boolean isLocked = false;
            try {
                boolean lock = redisService.lock(cacheKey);
                Random random = new Random();
                int lockCount = 0;
                while (!lock && lockCount <= 10) {
                    // 短暂休眠后轮询获取锁，避免可能的活锁
                    lockCount++;
                    log.info("cleanupCacheReward : get lock " + cacheKey + " waiting... " + lockCount);
                    Thread.sleep(30, random.nextInt(30));
                    lock = redisService.lock(cacheKey);
                }
                if (lock) {
                    isLocked = true;
                    cacheRewardMap = redisService.getMap(cacheKey);
                    if (cacheRewardMap == null || cacheRewardMap.size() <= 0) {
                        redisService.unLock(cacheKey);
                        continue;
                    }
                    for (Map.Entry<String,String> entry : cacheRewardMap.entrySet()) {
                        String key = entry.getKey();
                        String value = entry.getValue();
                        SocialPickRedis socialPickRedis = JSON.parseObject(value, SocialPickRedis.class);
                        long nowTime = System.currentTimeMillis();
                        Long expiryTime = socialPickRedis.getExpiryTime();
                        long expiryVal = expiryTime.longValue();
                        if (nowTime > expiryVal) {
                            String lockId = "SYNC_GATHER:" + key;
                            boolean rewardlock = redisService.lock(lockId);
                            if (rewardlock) {
                                redisService.hdel(cacheKey, key);
                                Long id = socialPickRedis.getId();
                                socialMiningRewardRecordDao.expiryStates(id);
                                redisService.unLock(lockId);
                            }
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("================================ 缓存果园奖励清除失败 ================================");
                e.printStackTrace();
                continue;
            } finally {
                if (isLocked) {
                    redisService.unLock(cacheKey);
                }
            }
        }

    }

}
