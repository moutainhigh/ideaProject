/**
 * Copyright:
 *
 * @author: javalx
 * @version: V1.0
 * @Date: 2019-06-10 11:05:17
 */
package hry.social.miningreward.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.Page;
import hry.bean.FrontPage;
import hry.manage.remote.model.Coin;
import hry.manage.remote.model.RemoteResult;
import hry.model.social.miningreward.SocialPickRedis;
import hry.mq.consumer.social.miningreward.MiningSyncCacheMessage;
import hry.redis.common.utils.RedisService;
import hry.social.manage.remote.model.miningreward.*;
import hry.bean.PageResult;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;

import javax.annotation.Resource;

import hry.core.thread.ThreadPool;
import hry.social.manage.remote.model.runnable.SelfPickedRunnable;
import hry.social.miningreward.dao.SocialMiningGatherRecordDao;
import hry.social.miningreward.dao.SocialMiningRewardConfigDao;
import hry.social.miningreward.dao.SocialMiningRewardRecordDao;
import hry.social.miningreward.service.SocialMiningRewardRecordService;
import hry.social.mq.producer.service.MessageProducer;
import hry.social.mq.producer.service.MiningRewardMessage;
import hry.util.PageFactory;
import hry.util.QueryFilter;
import hry.util.SocialUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.*;

/**
 * <p> SocialMiningRewardRecordService </p>
 *
 * @author: javalx
 * @Date :          2019-06-10 11:05:17
 */
@Service("socialMiningRewardRecordService")
public class SocialMiningRewardRecordServiceImpl extends BaseServiceImpl<SocialMiningRewardRecord,Long> implements SocialMiningRewardRecordService {

    @Resource(name = "socialMiningRewardRecordDao")
    @Override
    public void setDao(BaseDao<SocialMiningRewardRecord,Long> dao) {
        super.dao = dao;
    }

    @Resource
    private SocialMiningGatherRecordDao socialMiningGatherRecordDao;

    @Resource
    private SocialMiningRewardConfigDao socialMiningRewardConfigDao;

    @Resource
    private RedisService redisService;
    @Resource
    private MessageProducer messageProducer;

    /**
     * 分页查询
     *
     * @param filter
     * @return
     */
    @Override
    public PageResult findPageList(QueryFilter filter) {
        //----------------------分页查询头部外壳------------------------------
        Page<SocialMiningRewardRecord> page = PageFactory.getPage(filter);
        //----------------------分页查询头部外壳------------------------------
        //----------------------查询开始------------------------------
        String customerId = filter.getRequest().getParameter("customerId_EQ");
        String states = filter.getRequest().getParameter("states_EQ");
        String rewardType = filter.getRequest().getParameter("rewardType_EQ");
        String coinCode = filter.getRequest().getParameter("coinCode");
        String mobilePhone = filter.getRequest().getParameter("mobilePhone_like");
        String surname = filter.getRequest().getParameter("surname_like");
        String trueName = filter.getRequest().getParameter("trueName_like");
        String createdGT = filter.getRequest().getParameter("created_GT");
        String createdLT = filter.getRequest().getParameter("created_LT");
        Map<String,Object> map = new HashMap<String,Object>();

        if (!StringUtils.isEmpty(customerId)) {
            map.put("customerId", customerId);
        }
        if (!StringUtils.isEmpty(states)) {
            map.put("states", states);
        }
        if (!StringUtils.isEmpty(coinCode)) {
            map.put("coinCode", coinCode);
        }
        if (!StringUtils.isEmpty(mobilePhone)) {
            map.put("mobilePhone", "%" + mobilePhone + "%");
        }
        if (!StringUtils.isEmpty(surname)) {
            map.put("surname", "%" + surname + "%");
        }
        if (!StringUtils.isEmpty(trueName)) {
            map.put("trueName", "%" + trueName + "%");
        }
        if (!StringUtils.isEmpty(rewardType)) {
            map.put("rewardType", rewardType);
        }
        if (!StringUtils.isEmpty(createdGT)) {
            map.put("createdGT", createdGT);
        }
        if (!StringUtils.isEmpty(createdLT)) {
            map.put("createdLT", createdLT);
        }
        ((SocialMiningRewardRecordDao) dao).findPageList(map);
        return new PageResult(page, filter.getPage(), filter.getPageSize());
    }


    @Override
    public SocialMiningRewardRecord footing(QueryFilter filter) {
        SocialMiningRewardRecord str = null;
        //----------------------查询开始------------------------------
        String customerId = filter.getRequest().getParameter("customerId_EQ");
        String states = filter.getRequest().getParameter("states_EQ");
        String rewardType = filter.getRequest().getParameter("rewardType_EQ");
        String coinCode = filter.getRequest().getParameter("coinCode");
        String mobilePhone = filter.getRequest().getParameter("mobilePhone_like");
        String surname = filter.getRequest().getParameter("surname_like");
        String trueName = filter.getRequest().getParameter("trueName_like");
        String createdGT = filter.getRequest().getParameter("created_GT");
        String createdLT = filter.getRequest().getParameter("created_LT");
        Map<String,Object> map = new HashMap<String,Object>();

        if (!StringUtils.isEmpty(customerId)) {
            map.put("customerId", customerId);
        }
        if (!StringUtils.isEmpty(states)) {
            map.put("states", states);
        }
        if (!StringUtils.isEmpty(mobilePhone)) {
            map.put("mobilePhone", "%" + mobilePhone + "%");
        }
        if (!StringUtils.isEmpty(surname)) {
            map.put("surname", "%" + surname + "%");
        }
        if (!StringUtils.isEmpty(trueName)) {
            map.put("trueName", "%" + trueName + "%");
        }
        if (!StringUtils.isEmpty(rewardType)) {
            map.put("rewardType", rewardType);
        }
        if (!StringUtils.isEmpty(coinCode)) {
            map.put("coinCode", coinCode);
        }
        if (!StringUtils.isEmpty(createdGT)) {
            map.put("createdGT", createdGT);
        }
        if (!StringUtils.isEmpty(createdLT)) {
            map.put("createdLT", createdLT);
        }
        str = ((SocialMiningRewardRecordDao) dao).footing(map);
        return str;
    }

    /**
     * 查询好友的果子可采摘信息
     *
     * @param customerId 用户ID
     * @param gatheredId 好友ID
     * @return
     */
    @Override
    public List<SocialPickData> getPalPicked(Long customerId, Long gatheredId) {
        List<SocialPickData> pickList = new ArrayList<SocialPickData>();
        String cidStr = gatheredId.toString();
        String cacheKey = SocialUtil.SOCIAL_GATHERED_CACHE + cidStr;
        Map<String,String> cacheRewardMap = null;
        cacheRewardMap = redisService.getMap(cacheKey);
        if (cacheRewardMap == null) {
            return pickList;
        }
        SocialPickData socialPickData = null;
        for (Map.Entry<String,String> entry : cacheRewardMap.entrySet()) {
            String jsonStr = entry.getValue();
            SocialPickRedis socialPickRedis = JSON.parseObject(jsonStr, SocialPickRedis.class);
            //好友可采摘状态则查询是否有采摘记录
            if (socialPickRedis != null) {
                Long pickId = socialPickRedis.getId();
                String onlySelf = socialPickRedis.getOnlySelf();
                int hasPick = 1;
                if (!SocialUtil.GATHERE_ONLYSELF.equals(onlySelf)) {     //是否为保底值(是则直接置为不可收取状态 : 0，不是则查询是否有对应的收取记录)
                    //查询是否有对应的收取记录
                    int hasdata = socialMiningGatherRecordDao.hasPick(customerId, pickId);
                    hasPick = hasPick - hasdata;
                }
                socialPickData = new SocialPickData();
                socialPickData.setCustomerId(socialPickRedis.getCustomerId()); //奖励所属用户id
                socialPickData.setId(pickId); //奖励记录id
                socialPickData.setResidualNum(socialPickRedis.getResidualNum()); //剩余果子数
                socialPickData.setIsAbleGather(hasPick); //是否可收取，0:否;1:是
                socialPickData.setIsSelfGather(0); //是否本人收取完了，0:否;1:是
                socialPickData.setRewardSource(socialPickRedis.getRewardSource()); //奖励来源
                socialPickData.setRewardType(socialPickRedis.getRewardType()); //奖励类型
                socialPickData.setImgNum(socialPickRedis.getImgNum()); //图片类型
                socialPickData.setImgPath(socialPickRedis.getImgPath());//图片
                socialPickData.setCoinCode(socialPickRedis.getCoinCode());// 奖励币种
                pickList.add(socialPickData);
            }
        }
        return pickList;
    }

    /**
     * 采摘果子
     *
     * @param customerId 收取用户ID
     * @param gatheredId 被收取用户ID
     * @param id         被收取奖励ID
     * @return
     */
    @Override
    public RemoteResult gather(Long customerId, Long gatheredId, Long id) {
        System.out.println("参数输出::参数输出:::参数输出:::参数输出::参数输出=================== customerId : " + customerId +" ==== gatheredId : " + gatheredId + " ==== id : " + id);
        RemoteResult result = new RemoteResult();
        List<SocialPickData> pushData = new ArrayList<>();
        String cidStr = gatheredId.toString();
        String cacheKey = SocialUtil.SOCIAL_GATHERED_CACHE + cidStr;
        String key = id.toString();
        String coinCode = null; //币种
        BigDecimal gatherNum = null; //收取量
        String rewardSource = null; //奖励来源

        BigDecimal residualNum = null;  //收取前剩余量
        Integer selfStat = 2;        //自己收取状态（1是自己收取的，0是好友收取）
        BigDecimal gatherRatio = null; //好友收取率
        String lockId = "SYNC_GATHER:" + id.toString();
        boolean lock = redisService.lock(lockId);
        if (lock) {
            try {
                // 已收取标记（redis）
                String hasGatherCache = SocialUtil.GATHERED_CACHE + ":" + id.toString() + ":" + customerId.toString();
                String rewardJson = redisService.hget(cacheKey, key);
                if (StringUtils.isEmpty(rewardJson)) {
                    System.out.println("========== 缓存奖励为空---SMRRSI :: 260---奖励已被收取或失效 ==========");
                    // 缓存中取出来为空则，将该果子存入缓存，以免多次点击偷取
                    redisService.save(hasGatherCache, id.toString() + "_HRY_" + customerId.toString());
                    // TODO 放入推送
                    SocialPickData socialPickData = new SocialPickData();
                    socialPickData.setId(id);
                    socialPickData.setIsSelfGather(1);  //本人已收取完了，推送到前台（好友首页）删除该果子的显示
                    socialPickData.setIsNewCache(0);    //是否新增的果子
                    result.setMsg("reward_has_expiry_error");
                    result.setSuccess(false);
                    result.setObj(pushData);
                    return result;
                }
                String gatheredInfo = redisService.get(hasGatherCache);
                //查询是否有对应的收取记录
                int hasdata = socialMiningGatherRecordDao.hasPick(customerId, id);
                if (!StringUtils.isEmpty(gatheredInfo) || hasdata > 0) {
                    if (hasdata > 0) {
                        if(customerId.longValue() == gatheredId.longValue()){
                            // 删除已缓存的奖励
                            redisService.hdel(cacheKey, key);
                        }
                        // 删除已收取标记
                        redisService.delete(hasGatherCache);
                        // TODO 放入推送
                        SocialPickData socialPickData = new SocialPickData();
                        socialPickData.setId(id);
                        socialPickData.setIsSelfGather(1);  //本人已收取完了，推送到前台（好友首页）删除该果子的显示
                        socialPickData.setIsNewCache(0);    //是否新增的果子
                        pushData.add(socialPickData);
                    }
                    System.err.println("已收取信息：==== 收取人ID: " + customerId + " ==== 已收取奖励ID: " + id);
                    result.setMsg("repeate_collecte_error");
                    result.setSuccess(false);
                    result.setObj(pushData);
                    return result;
                }

                SocialPickRedis socialPickRedis = JSON.parseObject(rewardJson, SocialPickRedis.class);
                coinCode = socialPickRedis.getCoinCode();
                rewardSource = socialPickRedis.getRewardSource();
                Integer rewardType = socialPickRedis.getRewardType();
                residualNum = socialPickRedis.getResidualNum();        //剩余量
                Map<String,String> mrccMap = new HashMap<>();
                //奖励类型(1:算力奖励 2:任务奖励 3:矿机奖励)
                Map<String,String> rewardMap = redisService.getMap("DIC:reward_type");
                if (rewardMap == null) {
                    System.out.println("====奖励类型缓存错误---SMRRSI :: 276---奖励类型缓存错误====");
                    result.setMsg("req_error");
                    result.setSuccess(false);
                    result.setObj(pushData);
                    return result;
                }
                String rewardTypeCache = rewardMap.get(rewardType.toString());
                if (rewardTypeCache == null) {
                    System.out.println("====奖励类型缓存错误---SMRRSI :: 283---奖励类型缓存错误====");
                    result.setMsg("req_error");
                    result.setSuccess(false);
                    result.setObj(pushData);
                    return result;
                }
                rewardTypeCache = "SOCIAL:CACHE:" + rewardTypeCache.toUpperCase() + "_RULE_CONF";
                String msg = "mining_not_be_collect";
                mrccMap = redisService.getMap(rewardTypeCache);
                String mrcStr = mrccMap.get(coinCode);
                if (mrcStr == null) {
                    System.out.println("====果园奖励配置错误---SMRRSI :: 293---果园奖励配置错误====");
                    result.setMsg("req_error");
                    result.setSuccess(false);
                    result.setObj(pushData);
                    return result;
                }
                SocialMiningRuleConfig smrc = JSONArray.parseObject(mrcStr, SocialMiningRuleConfig.class);
                Integer stealStatus = smrc.getStealStatus();

                if (customerId.longValue() == gatheredId.longValue()) {     //收取自己的果子
                    //获取果子收取量(全部收取完了)
                    gatherNum = residualNum;
                    selfStat = 1;

                    //收取完的果子信息先失效，并修改为好友不可收取
                    socialPickRedis.setOnlySelf("1");
                    // 删除已缓存的奖励
                    redisService.hdel(cacheKey, key);

                    SocialPickData socialPickData = new SocialPickData();
                    socialPickData.setId(id);
                    socialPickData.setIsSelfGather(1);  //本人已收取完了，推送到前台（好友首页）删除该果子的显示
                    socialPickData.setIsNewCache(0);    //是否新增的果子
                    socialPickData.setRewardSource(socialPickRedis.getRewardSource()); //奖励类型
                    socialPickData.setRewardType(socialPickRedis.getRewardType());//奖励类型
                    socialPickData.setImgNum(socialPickRedis.getImgNum());//图片类型
                    socialPickData.setImgNum(socialPickRedis.getImgPath());//图片类型
                    pushData.add(socialPickData);
                } else {    //收取好友的果子
                    if ("1".equals(socialPickRedis.getOnlySelf())) {
                        System.out.println("====余量不足无法偷取---SMRRSI :: 323---余量不足无法偷取====");
                        result.setMsg("has_collecte_error");
                        result.setSuccess(false);
                        result.setObj(pushData);
                        return result;
                    }
                    if (stealStatus.intValue() == 0) {
                        System.out.println("====奖励不能偷取---SMRRSI :: 329---奖励不能偷取====");
                        result.setMsg(msg);
                        result.setSuccess(false);
                        result.setObj(pushData);
                        return result;
                    }
                    selfStat = 0;
                    gatherRatio = smrc.getGatherRatio(); //朋友收取率
                    BigDecimal keepRatio = smrc.getKeepRatio(); //个人保底率
                    Map<String,String> productMap = redisService.getMap(SocialUtil.PRODUCT_KEY);
                    String coinStr = productMap.get(coinCode);
                    Integer keepNum = 8;
                    if (coinStr != null) {
                        Coin coin = JSONArray.parseObject(coinStr, Coin.class);
                        if (coin != null && coin.getKeepDecimalForCoin() != null) {
                            keepNum = coin.getKeepDecimalForCoin();
                        }
                    }
                    BigDecimal awardCoinNumber = socialPickRedis.getAwardCoinNum();   //奖励量
                    BigDecimal guaranteedNum = awardCoinNumber.multiply(keepRatio).divide(new BigDecimal("100"), keepNum, BigDecimal.ROUND_HALF_DOWN);    //保底量
                    BigDecimal allocated = residualNum.subtract(guaranteedNum);  //剩余减保底,可分配的
                    BigDecimal toAllocated = residualNum.multiply(gatherRatio).divide(new BigDecimal("100"), keepNum, BigDecimal.ROUND_HALF_DOWN); //剩余*收取规则,需分配的
                    int r = toAllocated.compareTo(allocated);
                    boolean bl = r >= 0;
                    gatherNum = bl ? allocated : toAllocated;
                    if (bl) {//实际收取量小于规则计算的应收取量
                        socialPickRedis.setOnlySelf("1");
                    }
                    BigDecimal subtract = residualNum.subtract(gatherNum);
                    socialPickRedis.setResidualNum(subtract);
                    redisService.hset(cacheKey, key, JSON.toJSONString(socialPickRedis));

                    SocialPickData socialPickData = new SocialPickData();
                    socialPickData.setId(id);
                    socialPickData.setCustomerId(gatheredId);
                    socialPickData.setGatheredId(customerId);
                    socialPickData.setResidualNum(subtract);
                    socialPickData.setIsAbleGather(0);  //是否可收取，0否1是
                    socialPickData.setIsSelfGather(0);  //好友收取，推送到前台（个人首页&好友首页）更新该果子的剩余量（好友首页置灰显示）
                    socialPickData.setIsNewCache(0);    //是否新增的果子
                    socialPickData.setRewardSource(socialPickRedis.getRewardSource()); //奖励类型
                    socialPickData.setRewardType(socialPickRedis.getRewardType());//奖励类型
                    socialPickData.setImgNum(socialPickRedis.getImgNum());//图片类型
                    socialPickData.setImgPath(socialPickRedis.getImgPath());//图片
                    pushData.add(socialPickData);
                }
                if (gatherNum.compareTo(BigDecimal.ZERO) <= 0) {
                    System.out.println("====收取量小于等于0---SMRRSI :: 388---收取量小于等于0====");
                    result.setMsg("has_after_collect__error");
                    result.setSuccess(false);
                    result.setObj(pushData);
                    return result;
                }
                //发送消息实体封装
                MiningRewardMessage mrm = new MiningRewardMessage();
                mrm.setCoinCode(coinCode);
                mrm.setCustomerId(gatheredId);
                mrm.setGatherId(customerId);
                mrm.setGatherNum(gatherNum);
                mrm.setGatherRatio(gatherRatio);
                mrm.setGatherType(selfStat);
                mrm.setRewardId(id);
                mrm.setResidualNum(residualNum);
                mrm.setRewardSource(rewardSource);

                //发送消息
                messageProducer.toGather(JSON.toJSONString(mrm));
                System.out.println("发送果园奖励收取消息 ==== >>> " + JSON.toJSONString(mrm));
                redisService.save(hasGatherCache, id.toString() + "_HRY_" + customerId.toString());
                //异步向REDIS中补充果子信息，并推送给app端
                //同步果园奖励缓存--社交
                MiningSyncCacheMessage mscm = new MiningSyncCacheMessage();
                mscm.setCustomerId(customerId);
                messageProducer.syncCacheReward(JSON.toJSONString(mscm));
                result.setSuccess(true);
                result.setObj(pushData);
                return result;
            } catch (Exception e) {
                System.out.println("====报错报错报错---SMRRSI :: 381---报错报错报错====");
                e.printStackTrace();
                result.setMsg("sys_busy_error");
                result.setObj(pushData);
                return result;
            } finally {
                redisService.unLock(lockId);
            }
        } else {
            System.out.println("====未获取到锁---SMRRSI :: 376---未获取到锁====");
            result.setMsg("sys_busy_error");
            result.setObj(pushData);
            return result;
        }

    }

    /**
     * 查询收取好友信息
     *
     * @param customerId
     * @param friendId
     * @return
     */
    @Override
    public SocialMiningRewardRecord getGatherInfo(Long customerId, Long friendId) {
        return socialMiningGatherRecordDao.getGatherInfo(customerId, friendId);
    }

    /**
     * 好友动态分页查询
     *
     * @param params
     * @return
     */
    @Override
    public FrontPage findPalGatherReward(Map<String,String> params) {
        Page page = PageFactory.getPage(params);
        //查询方法
        List<SocialMiningGatherRecord> list = socialMiningGatherRecordDao.findFrontPage(params);
        return new FrontPage(list, page.getTotal(), page.getPages(), page.getPageSize());
    }

    /**
     * 首页动态分页查询
     *
     * @param params
     * @return
     */
    @Override
    public FrontPage miningRewardRecord(Map<String,String> params) {
        Page page = PageFactory.getPage(params);
        //查询方法
        List<SocialMiningGatherRecord> list = socialMiningGatherRecordDao.miningRewardRecord(params);
        return new FrontPage(list, page.getTotal(), page.getPages(), page.getPageSize());
    }

    /**
     * 查询果园配置币种
     *
     * @return
     */
    @Override
    public List<String> findMiningCode(Integer rewardType) {
        return socialMiningRewardConfigDao.findMiningCode(rewardType);
    }

}
