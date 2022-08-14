/**
 * Copyright:
 *
 * @author: javalx
 * @version: V1.0
 * @Date: 2019-06-11 14:44:12
 */
package hry.social.manage.remote.force;

import com.alibaba.fastjson.JSONArray;
import hry.front.redis.model.UserRedis;
import hry.manage.remote.model.Coin;
import hry.manage.remote.model.CoinAccount;
import hry.manage.remote.model.RemoteResult;
import hry.redis.common.dao.RedisUtil;
import hry.redis.common.utils.RedisService;
import hry.social.force.service.SocialCalculateForceService;
import hry.social.force.service.SocialForceCoinAdditionService;
import hry.social.force.service.SocialPermanentForceService;
import hry.social.force.service.SocialTerminableForceService;
import hry.social.manage.remote.api.force.RemoteForceService;
import hry.social.manage.remote.model.force.SocialCalculateForce;
import hry.social.manage.remote.model.force.SocialPermanentForce;
import hry.social.manage.remote.model.force.SocialTerminableForce;
import hry.social.manage.remote.model.miningreward.SocialMiningRewardConfig;
import hry.social.manage.remote.model.vip.SocialCustomerVip;
import hry.social.manage.remote.model.vip.SocialVipInfo;
import hry.social.mill.dao.SocialMillTradeRecordDao;
import hry.social.mill.service.SocialMillTradeRecordService;
import hry.social.miningreward.service.SocialMiningRewardConfigService;
import hry.social.vip.dao.SocialCustomerVipDao;
import hry.social.vip.service.SocialCustomerVipService;
import hry.trade.redis.model.ExDigitalmoneyAccountRedis;
import hry.util.QueryFilter;
import hry.util.SocialUtil;
import hry.util.UserRedisUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p> RemoteForceServiceImpl </p>
 *
 * @author: javalx
 * @Date :          2019-06-11 14:44:12
 */
public class RemoteForceServiceImpl implements RemoteForceService {

    @Resource
    private SocialCalculateForceService socialCalculateForceService;

    @Resource
    private SocialForceCoinAdditionService socialForceCoinAdditionService;

    @Resource
    private SocialPermanentForceService socialPermanentForceService;

    @Resource
    private SocialTerminableForceService socialTerminableForceService;

    @Resource
    private SocialCustomerVipService socialCustomerVipService;

    @Resource
    private SocialMiningRewardConfigService socialMiningRewardConfigService;

    @Resource
    private SocialMillTradeRecordService socialMillTradeRecordService;

    @Resource
    private RedisService redisService;

    /**
     * 查询当前用户算力值
     *
     * @param customerId
     * @return
     */
    @Override
    public BigDecimal getForce(Long customerId) {
        BigDecimal realtimeForce = socialCalculateForceService.getRealtimeForce(customerId);
        realtimeForce = realtimeForce == null ? BigDecimal.ZERO : realtimeForce.stripTrailingZeros();
        return realtimeForce;
    }

    /**
     * 查询个人算力分布信息
     *
     * @param customerId
     * @return
     */
    @Override
    public RemoteResult getForceInfo(Long customerId) {
        RemoteResult remoteResult = new RemoteResult();
        QueryFilter qf = new QueryFilter(SocialCalculateForce.class);
        qf.addFilter("customerId=", customerId);
        SocialCalculateForce scf = socialCalculateForceService.get(qf);
        BigDecimal force = scf.getTotalForce();
        int rankNum = socialCalculateForceService.getRankNum(force, customerId);
        BigDecimal allTotal = socialCalculateForceService.getAllTotal();
        BigDecimal firstForce = socialCalculateForceService.getFirstForce();

        BigDecimal percent = allTotal.compareTo(BigDecimal.ZERO) > 0 ? force.divide(allTotal, 4, BigDecimal.ROUND_HALF_DOWN).multiply(new BigDecimal(100)) : BigDecimal.ZERO;
        String dailyOutput = redisService.get(SocialUtil.MINING_REWARD_YESTERDAY_OUT);
        BigDecimal alloutput = StringUtils.isEmpty(dailyOutput) ? BigDecimal.ZERO : new BigDecimal(dailyOutput);
        BigDecimal output = alloutput.multiply(percent).divide(new BigDecimal(100), 5, BigDecimal.ROUND_HALF_DOWN);
        scf.setRankNum(rankNum);
        scf.setAllTotal(allTotal);
        scf.setFirstForce(firstForce);
        scf.setPercent(percent);
        scf.setAlloutput(alloutput);
        scf.setOutput(output);
        remoteResult.setSuccess(true);
        remoteResult.setObj(scf);
        return remoteResult;
    }

    /**
     * 查询个人永久算力信息
     *
     * @param customerId
     * @return
     */
    @Override
    public BigDecimal perpetualTotal(Long customerId) {
        QueryFilter qf = new QueryFilter(SocialCalculateForce.class);
        qf.addFilter("customerId=", customerId);
        SocialCalculateForce scf = socialCalculateForceService.get(qf);
        return scf.getPerpetualForce();
    }

    /**
     * 查询个人时效算力信息
     *
     * @param customerId
     * @return
     */
    @Override
    public BigDecimal terminablenTotal(Long customerId) {
        QueryFilter qf = new QueryFilter(SocialCalculateForce.class);
        qf.addFilter("customerId=", customerId);
        SocialCalculateForce scf = socialCalculateForceService.get(qf);
        return scf.getTerminableForce();
    }

    /**
     * 查询个人会员加成信息
     *
     * @param customerId
     * @return
     */
    @Override
    public BigDecimal findAddition(Long customerId) {
        BigDecimal addition = socialForceCoinAdditionService.findAddition(customerId);
        return addition;
    }

    /**
     * 查询个人任务加成信息
     *
     * @param customerId
     * @return
     */
    @Override
    public BigDecimal findTaskFroce(Long customerId) {
        BigDecimal taskFroce = socialCalculateForceService.findTaskFroce(customerId);
        return taskFroce;
    }

    /**
     * 查询个人矿机加成信息
     *
     * @param customerId
     * @return
     */
    @Override
    public BigDecimal findMillFroce(Long customerId) {
        BigDecimal millFroce = socialCalculateForceService.findMillFroce(customerId);
        return millFroce;
    }

    /**
     * 查询个人永久算力获取记录
     *
     * @param customerId
     * @return
     */
    @Override
    public List<SocialPermanentForce> findPermanentList(Long customerId) {
        QueryFilter qf = new QueryFilter(SocialPermanentForce.class);
        qf.addFilter("customerId=", customerId);
        List<SocialPermanentForce> spf = socialPermanentForceService.find(qf);
        return spf;
    }

    /**
     * 有效的时效性算力记录
     *
     * @param customerId
     * @return
     */
    @Override
    public List<SocialTerminableForce> findTerminablen(Long customerId) {
        List<SocialTerminableForce> stf = null;
        stf = socialTerminableForceService.findTerminablen(customerId);
        stf = stf != null ? stf : new ArrayList<>();
        return stf;
    }

    /**
     * 失效的时效性算力记录
     *
     * @param customerId
     * @return
     */
    @Override
    public List<SocialTerminableForce> findDisTerminable(Long customerId) {
        List<SocialTerminableForce> stf = null;
        stf = socialTerminableForceService.findDisTerminable(customerId);
        stf = stf != null ? stf : new ArrayList<>();
        return stf;
    }

    /**
     * 获取会员信息（加成）
     *
     * @param customerId
     * @return
     */
    @Override
    public SocialCustomerVip getVipInfo(Long customerId) {
        return socialCustomerVipService.getVipInfo(customerId);
    }

    /**
     * 查询全网排名前100的用户
     *
     * @return
     */
    @Override
    public List<SocialCalculateForce> getTop() {
        return socialCalculateForceService.getTop();
    }

    /**
     * 查询币种对应的产出
     *
     * @param coinCode
     * @return
     */
    @Override
    public RemoteResult getCoinOut(String coinCode, Long customerId) {
        RemoteResult remoteResult = new RemoteResult();
        Map<String,String> map = new HashMap<>();
        // 获取实时定时任务设置的币种对应的奖励数
        BigDecimal rewardNum = BigDecimal.ZERO;
        SocialMiningRewardConfig smrc = socialMiningRewardConfigService.getCoinConfig(coinCode);
        if (smrc != null) {
            BigDecimal baseNum = smrc.getBaseNum();
            Integer interValue = smrc.getInterValue();
            Integer timeUnit = smrc.getTimeUnit();
            // 间隔时间单位 1:分 2:时 3:天 4:周 5:月
            BigDecimal divide = BigDecimal.ZERO;
            switch (timeUnit.intValue()) {
                case 1:
                    divide = new BigDecimal(1440).divide(new BigDecimal(interValue), 0, BigDecimal.ROUND_HALF_DOWN);
                    rewardNum = baseNum.multiply(divide);
                    break;
                case 2:
                    divide = new BigDecimal(24).divide(new BigDecimal(interValue), 0, BigDecimal.ROUND_HALF_DOWN);
                    rewardNum = baseNum.multiply(divide);
                    break;
                case 3:
                    divide = new BigDecimal(interValue);
                    rewardNum = baseNum.divide(divide, 6, BigDecimal.ROUND_HALF_DOWN);
                    break;
                case 4:
                    divide = new BigDecimal(interValue).multiply(new BigDecimal(7));
                    rewardNum = baseNum.divide(divide, 6, BigDecimal.ROUND_HALF_DOWN);
                    break;
                case 5:
                    divide = new BigDecimal(interValue).multiply(new BigDecimal(30));
                    rewardNum = baseNum.divide(divide, 6, BigDecimal.ROUND_HALF_DOWN);
                    break;
            }
        }
        // 获取全网算力占比
        QueryFilter qf = new QueryFilter(SocialCalculateForce.class);
        qf.addFilter("customerId=", customerId);
        SocialCalculateForce scf = socialCalculateForceService.get(qf);
        BigDecimal force = scf.getTotalForce();
        BigDecimal allTotal = socialCalculateForceService.getAllTotal();
        BigDecimal percent = allTotal.compareTo(BigDecimal.ZERO) > 0 ? force.divide(allTotal, 4, BigDecimal.ROUND_HALF_DOWN) : BigDecimal.ZERO;
        rewardNum = rewardNum.multiply(percent);
        // 获取有效的矿机奖励
        BigDecimal coinMillReward = socialMillTradeRecordService.getCoinReward(coinCode);
        coinMillReward = coinMillReward==null? BigDecimal.ZERO : coinMillReward;
        // 获取会员加成比例
        BigDecimal additionRatio = socialCustomerVipService.getVipAdd(customerId);
        additionRatio = additionRatio == null ? BigDecimal.ONE : BigDecimal.ONE.add(additionRatio.divide(new BigDecimal(100)));
        coinMillReward = coinMillReward.multiply(additionRatio);
        map.put("miningReward", rewardNum.stripTrailingZeros().toPlainString());
        map.put("millReward", coinMillReward.stripTrailingZeros().toPlainString());

        // 查redis缓存
        Map<String,String> productMap = redisService.getMap(SocialUtil.PRODUCT_KEY);
        RedisUtil<UserRedis> redisUtil = new RedisUtil<UserRedis>(UserRedis.class);
        UserRedis userRedis = redisUtil.get(customerId.toString());
        if (userRedis == null || "".equals(userRedis)) {
            return remoteResult.setMsg("req_error");
        }
        // 获得缓存中所有的币账户id
        Map<String,Long> dmAccMap = userRedis.getDmAccountId();
        Long dmAccId = dmAccMap.get(coinCode);
        if (dmAccId == null) {
            return remoteResult.setMsg("req_error");
        }
        // 获得缓存币账户
        ExDigitalmoneyAccountRedis dmAccount = UserRedisUtils.getAccount(dmAccId.toString(), coinCode);
        if (dmAccount == null) {
            return remoteResult.setMsg("req_error");
        }
        String coinStr = productMap.get(coinCode);
        Integer keepNum = 8;
        if (coinStr != null) {
            Coin coin = JSONArray.parseObject(coinStr, Coin.class);
            keepNum = coin.getKeepDecimalForCoin();
        }
        BigDecimal hotMoney = dmAccount.getHotMoney().setScale(keepNum, BigDecimal.ROUND_HALF_DOWN);
        BigDecimal ColdMoney = dmAccount.getColdMoney().setScale(keepNum, BigDecimal.ROUND_HALF_DOWN);
        map.put("hotMoney", hotMoney.stripTrailingZeros().toPlainString());
        map.put("ColdMoney", ColdMoney.stripTrailingZeros().toPlainString());
        Integer rankNum = socialCalculateForceService.getAccountRankNum(coinCode,hotMoney,customerId);
        map.put("rankNum", rankNum.toString());
        remoteResult.setObj(map);
        remoteResult.setSuccess(true);
        return remoteResult;
    }
}
