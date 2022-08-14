/**
 * Copyright:
 *
 * @author: javalx
 * @version: V1.0
 * @Date: 2019-06-10 10:50:48
 */
package hry.social.manage.remote.miningreward;

import com.alibaba.fastjson.JSONArray;
import hry.bean.FrontPage;
import hry.front.redis.model.UserRedis;
import hry.manage.remote.model.Coin;
import hry.manage.remote.model.CoinAccount;
import hry.manage.remote.model.RemoteResult;
import hry.redis.common.dao.RedisUtil;
import hry.redis.common.utils.RedisService;
import hry.social.force.service.SocialCalculateForceService;
import hry.social.manage.remote.api.miningreward.RemoteMiningRewardService;
import hry.social.manage.remote.model.miningreward.*;
import hry.social.miningreward.service.SocialMiningRewardRecordService;
import hry.social.traderecord.service.SocialCoinTradeRecordService;
import hry.trade.redis.model.ExDigitalmoneyAccountRedis;
import hry.util.BaseConfUtil;
import hry.util.SocialUtil;
import hry.util.UserRedisUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * <p> RemoteMiningRewardServiceImpl </p>
 *
 * @author: javalx
 * @Date :          2019-06-10 10:50:48
 */
public class RemoteMiningRewardServiceImpl implements RemoteMiningRewardService {

    @Resource
    private SocialMiningRewardRecordService socialMiningRewardRecordService;

    @Resource
    private SocialCalculateForceService socialCalculateForceService;
    @Resource
    private RedisService redisService;
    @Resource
    private SocialCoinTradeRecordService socialCoinTradeRecordService;

    /**
     * 查询当前用户果园账户
     *
     * @param customerId
     * @return
     */
    @Override
    public List<CoinAccount> getMiningRewardAccount(Long customerId) {
        ArrayList<CoinAccount> list = new ArrayList<CoinAccount>();
        // 查redis缓存
        RedisUtil<UserRedis> redisUtil = new RedisUtil<UserRedis>(UserRedis.class);
        UserRedis userRedis = redisUtil.get(customerId.toString());
        if (userRedis == null || "".equals(userRedis)) {
            return list;
        }

        // 获得缓存中平台币账户id
        Map<String,Long> dmAccMap = userRedis.getDmAccountId();
        // 获取 平台币
        String platCoin = BaseConfUtil.getConfigSingle("platCoin", "configCache:basefinanceConfig");
        Long dmAccId = dmAccMap.get(platCoin);
        // 获得缓存币账户
        ExDigitalmoneyAccountRedis dmAccount = UserRedisUtils.getAccount(dmAccId.toString(), platCoin);
        if (dmAccount == null) {
            return list;
        }
        Map<String,String> productMap = redisService.getMap(SocialUtil.PRODUCT_KEY);
        String platCoinStr = productMap.get(platCoin);
        Integer keepNum = 8;
        CoinAccount coinAccount = new CoinAccount();
        if (platCoinStr != null) {
            Coin coin = JSONArray.parseObject(platCoinStr, Coin.class);
            keepNum = coin.getKeepDecimalForCoin();
            coinAccount.setName(coin.getName());
        }
        coinAccount.setCoinCode(dmAccount.getCoinCode());
        coinAccount.setColdMoney(dmAccount.getColdMoney().setScale(keepNum, BigDecimal.ROUND_HALF_DOWN));
        coinAccount.setHotMoney(dmAccount.getHotMoney().setScale(keepNum, BigDecimal.ROUND_HALF_DOWN));
        list.add(coinAccount);

//        // 获得缓存中所有的币账户id
//        Map<String,Long> dmAccMap = userRedis.getDmAccountId();
//        Map<String,String> orchardConfMap = redisService.getMap(SocialUtil.MINING_REWARD_RULE_CONF);
//        Map<String,String> productMap = redisService.getMap(SocialUtil.PRODUCT_KEY);
//        Set<String> coinSet = orchardConfMap.keySet();
//        for (String coinCode : coinSet) {
//            if (StringUtils.isEmpty(coinCode) || StringUtils.isEmpty(coinCode)) {
//                continue;
//            }
//            Long dmAccId = dmAccMap.get(coinCode);
//            if (dmAccId == null) {
//                continue;
//            }
//            // 获得缓存币账户
//            ExDigitalmoneyAccountRedis dmAccount = UserRedisUtils.getAccount(dmAccId.toString(), coinCode);
//            if (dmAccount != null && coinCode.equals(dmAccount.getCoinCode())) {
//                String coinStr = productMap.get(coinCode);
//                Integer keepNum = 8;
//                CoinAccount coinAccount = new CoinAccount();
//                if (coinStr != null) {
//                    Coin coin = JSONArray.parseObject(coinStr, Coin.class);
//                    keepNum = coin.getKeepDecimalForCoin();
//                    coinAccount.setName(coin.getName());
//                }
//                coinAccount.setCoinCode(dmAccount.getCoinCode());
//                coinAccount.setColdMoney(dmAccount.getColdMoney().setScale(keepNum, BigDecimal.ROUND_HALF_DOWN));
//                coinAccount.setHotMoney(dmAccount.getHotMoney().setScale(keepNum, BigDecimal.ROUND_HALF_DOWN));
//                list.add(coinAccount);
//            }
//        }
        return list;
    }

    /**
     * 查询登录用户的果子可采摘信息
     *
     * @param customerId
     * @return
     */
    @Override
    public List<SocialPickData> getMyPicked(Long customerId) {
//        List<SocialPickData> pickList = new ArrayList<SocialPickData>();
//
//        String cidStr = customerId.toString();
//        String cacheKey = SocialUtil.SOCIAL_GATHERED_CACHE + cidStr;
//        Map<String,String> cacheRewardMap = null;
//        cacheRewardMap = redisService.getMap(cacheKey);
//        if (cacheRewardMap == null) {
//            return pickList;
//        }
//        SocialPickData socialPickData = null;
//        for (Map.Entry<String,String> entry : cacheRewardMap.entrySet()) {
//            String jsonStr = entry.getValue();
//            SocialPickRedis socialPickRedis = JSON.parseObject(jsonStr, SocialPickRedis.class);
//            //好友可采摘状态则查询是否有采摘记录
//            if (socialPickRedis != null) {
//                Long pickId = socialPickRedis.getId();
//                String onlySelf = socialPickRedis.getOnlySelf();
//                socialPickData = new SocialPickData();
//                socialPickData.setCustomerId(socialPickRedis.getCustomerId());
//                socialPickData.setId(pickId);
//                socialPickData.setResidualNum(socialPickRedis.getResidualNum());
//                socialPickData.setRewardSource(socialPickRedis.getRewardSource());//奖励来源
//                socialPickData.setRewardType(socialPickRedis.getRewardType());//奖励类型
//                socialPickData.setImgNum(socialPickRedis.getImgNum());//图片类型
//                socialPickData.setImgPath(socialPickRedis.getImgPath());//图片
//                socialPickData.setCoinCode(socialPickRedis.getCoinCode());// 奖励币种
//                pickList.add(socialPickData);
//                pickList.add(socialPickData);
//            }
//        }
//        return pickList;
        return socialMiningRewardRecordService.getPalPicked(customerId, customerId);
    }

    /**
     * 查询好友的果子可采摘信息
     *
     * @param customerId
     * @param gatheredId
     * @return
     */
    @Override
    public List<SocialPickData> getPalPicked(Long customerId, Long gatheredId) throws Exception {
        return socialMiningRewardRecordService.getPalPicked(customerId, gatheredId);
    }

    /**
     * 采摘果子
     *
     * @param customerId
     * @param gatheredId
     * @return
     */
    @Override
    public RemoteResult gather(Long customerId, Long gatheredId, Long id) {
        return socialMiningRewardRecordService.gather(customerId, gatheredId, id);
    }

    /**
     * 更新奖励状态
     *
     * @param id
     */
    @Override
    public void updateStates(Long id) {
        socialCalculateForceService.updateStates(id);
    }

    /**
     * 采摘PK
     *
     * @param friendId
     * @param customerId
     * @return
     */
    @Override
    public Map<String,Object> gatherVs(Long friendId, Long customerId) {
        SocialMiningRewardRecord myGatherInfo = socialMiningRewardRecordService.getGatherInfo(customerId, friendId);
        SocialMiningRewardRecord friendGatherInfo = socialMiningRewardRecordService.getGatherInfo(friendId, customerId);
        Map<String,Object> map = new HashMap();
        map.put("myNumber", friendGatherInfo.getNum());         // 我被收次数
        map.put("palNumber", myGatherInfo.getNum());            // 好友被收次数
        map.put("myAmount", friendGatherInfo.getGatherNum());   // 我被收量
        map.put("palAmount", myGatherInfo.getGatherNum());      // 好友被收量
        return map;
    }

    /**
     * 查看好友收取动态
     *
     * @param params
     * @return
     */
    @Override
    public FrontPage findPalGatherReward(Map<String,String> params) {
        return socialMiningRewardRecordService.findPalGatherReward(params);
    }

    /**
     * 查询动态（首页）
     *
     * @param params
     * @return
     */
    @Override
    public FrontPage miningRewardRecord(Map<String,String> params) {
        String id = params.get("customerId");
        FrontPage frontPage = socialMiningRewardRecordService.miningRewardRecord(params);
        List<SocialMiningGatherRecord> list = (List<SocialMiningGatherRecord>) frontPage.getRows();
        for (SocialMiningGatherRecord smgr : list) {
            Long gatherId = smgr.getGatherId();
            Long customerId = smgr.getCustomerId();
            //状态 0：自己收自己，1：自己收别人，2：自己被别人收
            if (customerId.longValue() == gatherId.longValue()) {
                smgr.setStates(0);
            } else if (Long.parseLong(id) == gatherId.longValue()) {
                smgr.setStates(1);
            } else {
                smgr.setStates(2);
            }
        }
        return frontPage;
    }

    /**
     * 查询数币交易流水（首页）
     *
     * @param params
     * @return
     */
    @Override
    public FrontPage coinTraderList(Map<String,String> params) {
        return socialCoinTradeRecordService.coinTraderList(params);
    }

    /**
     * 查询果园配置币种
     *
     * @return
     */
    @Override
    public List<String> findMiningCode(Integer rewardType) {
        return socialMiningRewardRecordService.findMiningCode(rewardType);
    }
}
