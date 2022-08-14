/**
 * Copyright:
 *
 * @author: javalx
 * @version: V1.0
 * @Date: 2019-06-03 19:58:03
 */
package hry.social.manage.remote.task;

import com.alibaba.fastjson.JSON;
import hry.manage.remote.model.AppDic;
import hry.manage.remote.model.RemoteResult;
import hry.mq.consumer.social.miningreward.MiningSyncCacheMessage;
import hry.redis.common.utils.RedisService;
import hry.social.force.dao.SocialCalculateForceDao;
import hry.social.force.dao.SocialForceCoinAdditionDao;
import hry.social.force.dao.SocialPermanentForceDao;
import hry.social.force.dao.SocialTerminableForceDao;
import hry.social.manage.remote.api.task.RemoteTaskService;
import hry.social.manage.remote.model.force.SocialForceCoinAddition;
import hry.social.manage.remote.model.force.SocialPermanentForce;
import hry.social.manage.remote.model.force.SocialTerminableForce;
import hry.social.manage.remote.model.miningreward.SocialMiningRewardRecord;
import hry.social.manage.remote.model.task.SocialTaskItem;
import hry.social.manage.remote.model.task.SocialTaskReward;
import hry.social.manage.remote.model.vip.SocialCustomerVip;
import hry.social.miningreward.dao.SocialMiningRewardRecordDao;
import hry.social.mq.producer.service.MessageProducer;
import hry.social.task.dao.SocialTaskItemDao;
import hry.social.task.dao.SocialTaskRewardDao;
import hry.social.vip.dao.SocialCustomerVipDao;
import hry.util.SpringUtil;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * <p> RemoteTaskServiceImpl </p>
 *
 * @author: javalx
 * @Date :          2019-06-03 19:58:03
 */
public class RemoteTaskServiceImpl implements RemoteTaskService {

    @Resource
    public RedisService redisService;
    @Resource
    public SocialTaskItemDao socialTaskItemDao;
    @Resource
    public SocialTaskRewardDao socialTaskRewardDao;
    @Resource
    public SocialCustomerVipDao socialCustomerVipDao;
    @Resource
    public SocialMiningRewardRecordDao socialMiningRewardRecordDao;
    @Resource
    public SocialPermanentForceDao socialPermanentForceDao;
    @Resource
    public SocialTerminableForceDao socialTerminableForceDao;
    @Resource
    public SocialForceCoinAdditionDao socialForceCoinAdditionDao;
    @Resource
    public SocialCalculateForceDao socialCalculateForceDao;
    @Resource
    private MessageProducer messageProducer;
    /*
     * 查询任务类型列表
     * */
    @Override
    public List<AppDic> categoryList() {
        List<AppDic> appDics = socialTaskItemDao.categoryList("taskType");
        return appDics;
    }

    /*
     * 查询任务项列表
     * */
    @Override
    public List<SocialTaskItem> itemList() {
        SocialTaskItem socialTaskItem = new SocialTaskItem();
        socialTaskItem.setStates(1);
        List<SocialTaskItem> socialTaskItems = socialTaskItemDao.select(socialTaskItem);
        return socialTaskItems;
    }

    /*
     * 根据任务类型Key获取任务列表
     * */
    @Override
    public List<SocialTaskItem> repetitionList(Long customerId, String categoryKey) {
        SocialTaskItem socialTaskItem = new SocialTaskItem();
        socialTaskItem.setCategoryKey(categoryKey);
        socialTaskItem.setStates(1);
        List<SocialTaskItem> socialTaskItems = socialTaskItemDao.select(socialTaskItem);
        for (SocialTaskItem sti : socialTaskItems) {
            // 奖励类型(0:一次性奖励，1：按次数奖励，2：按时间奖励)
            Integer rewardType = sti.getRewardType();
            String taskMark = sti.getTaskMark();
            int hasFinish = 0;
            switch (rewardType.intValue()) {
                case 0:
                    hasFinish = socialTaskRewardDao.hasFinish(customerId, taskMark);
                    break;
                case 1:
                    hasFinish = socialTaskRewardDao.hasOnseFinish(customerId, taskMark);
                    break;
                case 2:
                    break;
            }
            sti.setHasFinish(hasFinish);
        }
        return socialTaskItems;
    }

    /*
     * 根据任务标识获取任务项详情
     * */
    @Override
    public SocialTaskItem invite(String identifying) {
        SocialTaskItem socialTaskItem = new SocialTaskItem();
        socialTaskItem.setTaskMark(identifying);
        SocialTaskItem socialTaskItem1 = socialTaskItemDao.selectOne(socialTaskItem);
        return socialTaskItem1;
    }

    /**
     * 任务奖励处理
     *
     * @param customerId
     * @param taskMark
     * @return
     */
    @Override
    public SocialTaskReward dealTaskReward(Long customerId, String taskMark) {
        SocialTaskReward socialTaskReward = null;
        if (taskMark != null && customerId != null) {
            SocialTaskItem socialTaskItem = socialTaskItemDao.getByTaskMark(taskMark);
            if (null == socialTaskItem) {
                return socialTaskReward;
            }
            Long taskId = socialTaskItem.getId();
            Integer states = socialTaskItem.getStates();                //状态（0：关闭，1:开启）
            BigDecimal dailyUpper = socialTaskItem.getDailyUpper();     //每天奖励上限
            Integer rewardType = socialTaskItem.getRewardType();        //考核类型(0:一次性奖励，1：按次数奖励，2：按时间奖励)
            Integer numberCaps = socialTaskItem.getNumberCaps();

            // 是否已完成该任务（0否，1是）
            int hashEnd = 1;

            // 是否已达到该任务总奖励上线（0否，1是）
            int hasMax = 0;
            if (numberCaps != null && numberCaps > 0) {
                //校验是否超过总奖励次数上限
                hasMax = socialTaskRewardDao.getHasMax(customerId, taskMark, numberCaps);
            }
            if (hasMax == 1) {
                return socialTaskReward;
            }
            if (states.intValue() == 1) {
                switch (rewardType.intValue()) {
                    case 0:     //一次性奖励
                        hashEnd = socialTaskRewardDao.hashOnce(customerId, taskMark);
                        break;
                    case 1:     //按次数奖励
                        hashEnd = socialTaskRewardDao.hashNums(customerId, taskMark, dailyUpper.intValue());
                        break;
                    default:    //按时间奖励
                        break;
                }
                if (hashEnd == 0) {
                    socialTaskReward = taskReward(customerId, socialTaskItem);
                }
            }
        }
        return socialTaskReward;
    }

    /**
     * 查询用户邀请信息
     *
     * @param customerId
     * @param inviteCode
     * @return
     */
    @Override
    public RemoteResult getInviteInfo(Long customerId, String inviteCode) {
        RemoteResult remoteResult = new RemoteResult();
        Map<String,Object> resultMap = new HashMap<>();
        RedisService redisService = SpringUtil.getBean("redisService");
        Map<String,String> taskMap = redisService.getMap("DIC:task_mark");
        String invite = taskMap.get("invite");
        SocialTaskItem socialTaskItem = socialTaskItemDao.getByTaskMark(invite);
        BigDecimal rewardNum = null;
        if (socialTaskItem != null) {
            rewardNum= socialTaskItem.getRewardNum();
        }
        int inviteNum = socialTaskItemDao.getInviteNum(inviteCode);
        rewardNum = (rewardNum != null && rewardNum.compareTo(BigDecimal.ZERO) > 0) ? rewardNum : BigDecimal.ZERO;
        BigDecimal totalReward = BigDecimal.ZERO;
        if (inviteNum > 0) {
            totalReward = new BigDecimal(inviteNum).multiply(rewardNum);
        }
        resultMap.put("inviteNum", String.valueOf(inviteNum));
        resultMap.put("inviteReward", rewardNum.toPlainString());
        resultMap.put("totalReward", totalReward.toPlainString());
        remoteResult.setObj(resultMap);
        remoteResult.setSuccess(true);
        return remoteResult;
    }

    /**
     * 奖励
     *
     * @param customerId
     * @param socialTaskItem
     */
    public SocialTaskReward taskReward(Long customerId, SocialTaskItem socialTaskItem) {
        BigDecimal rewardNum = socialTaskItem.getRewardNum();
        Integer rewardWay = socialTaskItem.getRewardWay();
        String rewardCoin = socialTaskItem.getRewardCoin();
        String itemName = socialTaskItem.getItemName();
        String taskMark = socialTaskItem.getTaskMark();

        SocialTaskReward str = new SocialTaskReward();
        str.setCategoryKey(socialTaskItem.getCategoryKey());
        str.setTaskMark(taskMark);
        str.setRewardType(socialTaskItem.getRewardType());
        str.setItemName(itemName);
        str.setRewardWay(rewardWay);
        str.setRewardNum(rewardNum);
        str.setCustomerId(customerId);
        str.setCoinCode(rewardCoin);
        socialTaskRewardDao.insertSelective(str);
        int rewardWayInt = rewardWay.intValue();// 奖励方式(0:奖励永久算力，1：奖励时效性算力，2：奖励算力加成，3：奖励数币)
        if (rewardWayInt == 3) {//数币奖励  奖励方式(0:奖励永久算力，1：奖励时效性算力，2：奖励算力加成，3：奖励数币)
            SocialCustomerVip vipByCustomerId = socialCustomerVipDao.getVipInfo(customerId);
            BigDecimal additionRatio = BigDecimal.ONE;
            if (vipByCustomerId != null) {
                additionRatio = additionRatio.add(vipByCustomerId.getAdditionRatio().divide(new BigDecimal(100)));
            }
            rewardNum = rewardNum.multiply(additionRatio);
            //生成奖励记录
            SocialMiningRewardRecord smrr = new SocialMiningRewardRecord();
            smrr.setCoinCode(rewardCoin);
            smrr.setRewardNum(rewardNum);
            smrr.setCustomerId(customerId);
            smrr.setRewardType(2);
            smrr.setRewardSource(taskMark);
//            Map<String,String> map = redisService.getMap("DIC:reward_type");
//            if (map != null) {
//                String rewardSource = map.get(String.valueOf(smrr.getRewardType()));
//                smrr.setRewardSource(rewardSource);
//            } else {
//                smrr.setRewardSource("task_reward");
//                smrr.setRewardSource("任务奖励");
//                smrr.setRewardSource(itemName);
//            }
            smrr.setStates(0);
            smrr.setGatherNum(BigDecimal.ZERO);
            socialMiningRewardRecordDao.insertSelective(smrr);

            //异步向REDIS中补充果子信息，并推送给app端
            //同步果园奖励缓存--社交
            MiningSyncCacheMessage mscm = new MiningSyncCacheMessage();
            mscm.setCustomerId(customerId);
            messageProducer.syncCacheReward(JSON.toJSONString(mscm));
//            ThreadPool.exe(new SyncPickedRunnable(customerId));
        } else {//算力奖励
            switch (rewardWayInt) {
                case 0:     // 奖励方式(0:奖励永久算力，1：奖励时效性算力，2：奖励算力加成，3：奖励数币)
                    //新增永久算力值
                    SocialPermanentForce spf = new SocialPermanentForce();
                    spf.setCustomerId(customerId);
                    spf.setType(1);
                    spf.setPermanent(rewardNum);
                    spf.setRemark(itemName);
                    socialPermanentForceDao.insertSelective(spf);
                    break;
                case 1:     // 奖励方式(0:奖励永久算力，1：奖励时效性算力，2：奖励算力加成，3：奖励数币)
                    //新增时效性算力值
                    SocialTerminableForce stf = new SocialTerminableForce();
                    stf.setCustomerId(customerId);
                    stf.setType(1);
                    stf.setTerminable(rewardNum);
                    Date endTime = timeDeal(socialTaskItem.getAging(), socialTaskItem.getTimeUnit());
                    stf.setEndTime(endTime);
                    stf.setRemark(itemName);
                    socialTerminableForceDao.insertSelective(stf);
                    break;
                case 2:     // 奖励方式(0:奖励永久算力，1：奖励时效性算力，2：奖励算力加成，3：奖励数币)
                    //新增加成算力值
                    SocialForceCoinAddition saf = new SocialForceCoinAddition();
                    saf.setCustomerId(customerId);
                    saf.setSourceType(1);
                    saf.setAdditionType(1);
                    saf.setAddition(socialTaskItem.getPercent());
                    Date endTime2 = timeDeal(socialTaskItem.getAging(), socialTaskItem.getTimeUnit());
                    saf.setEndTime(endTime2);
                    saf.setRemark(itemName);
                    socialForceCoinAdditionDao.insertSelective(saf);
                    break;
                default:
                    break;
            }
            //统计刷新算力
            calculateForce(customerId);
        }
        return str;
    }

    /**
     * /统计刷新算力
     *
     * @param customerId
     */
    @Override
    public void calculateForce(Long customerId) {
        //统计算力
        BigDecimal perpetualTotal = socialCalculateForceDao.perpetualTotal(customerId).stripTrailingZeros(); //个人总的永久算力
        BigDecimal terminablenTotal = socialCalculateForceDao.terminableForce(customerId).stripTrailingZeros(); //个人总的时效算力
        BigDecimal addPercentTotal = socialCalculateForceDao.additionTotal(customerId).stripTrailingZeros(); //个人总的加成比例
        BigDecimal addTotal = perpetualTotal.add(terminablenTotal).multiply(addPercentTotal.divide(new BigDecimal(100))).stripTrailingZeros(); //个人总的加成算力
        BigDecimal total = perpetualTotal.add(terminablenTotal).add(addTotal).stripTrailingZeros(); //个人总算力
        System.out.println("个人总的永久算力 : " + perpetualTotal);
        System.out.println("个人总的时效算力 : " + terminablenTotal);
        System.out.println("个人总的加成比例 : " + addPercentTotal);
        System.out.println("个人总的加成算力 : " + addTotal);
        System.out.println("个人总算力===== : " + total);
        socialCalculateForceDao.updateForce(customerId, perpetualTotal, terminablenTotal, addTotal, total);
    }

    /**
     * 计算到期时间
     *
     * @param aging 时效数
     * @param unit  时效单位（0：年，1：月，2：周，3：日）
     * @return
     */
    Date timeDeal(int aging, int unit) {
        int calendarUN = 0;
        switch (unit) {
            case 0:
                calendarUN = Calendar.YEAR;
                break;
            case 1:
                calendarUN = Calendar.MONTH;
                break;
            case 2:
                calendarUN = Calendar.DATE;
                aging = aging * 7;
                break;
            case 3:
                calendarUN = Calendar.DATE;
                break;
            default:
                break;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());    //设置起时间
        cal.add(calendarUN, aging);
        Date time = cal.getTime();
        System.out.println("输出::" + time);
        return time;
    }


}
