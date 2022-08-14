package hry.licqb.remote;

import com.alibaba.fastjson.JSON;
import hry.bean.JsonResult;
import hry.exchange.account.model.ExDigitalmoneyAccount;
import hry.exchange.account.model.ExDmHotAccountRecord;
import hry.exchange.account.service.ExDigitalmoneyAccountService;
import hry.exchange.account.service.ExDmHotAccountRecordService;
import hry.front.redis.model.UserRedis;
import hry.licqb.award.RulesConfig;
import hry.licqb.award.model.OutInfo;
import hry.licqb.award.model.Upgrade;
import hry.licqb.award.service.OutInfoService;
import hry.licqb.exchange.model.ExchangeRecord;
import hry.licqb.exchange.service.ExchangeRecordService;
import hry.licqb.level.model.CustomerLevel;
import hry.licqb.level.model.LevelConfig;
import hry.licqb.level.model.TeamLevelConfig;
import hry.licqb.level.service.CustomerLevelService;
import hry.licqb.level.service.LevelConfigService;
import hry.licqb.level.service.TeamLevelConfigService;
import hry.licqb.record.model.*;
import hry.licqb.record.service.*;
import hry.licqb.util.AccountUtil;
import hry.licqb.util.DealEnum;
import hry.licqb.util.RedisStaticUtil;
import hry.mq.producer.service.MessageProducer;
import hry.redis.common.dao.RedisUtil;
import hry.redis.common.utils.RedisService;
import hry.trade.redis.model.Accountadd;
import hry.trade.redis.model.ExDigitalmoneyAccountRedis;
import hry.util.QueryFilter;
import hry.util.StringUtil;
import hry.util.idgenerate.IdGenerate;
import hry.util.idgenerate.NumConstant;
import org.apache.commons.lang3.time.DateUtils;
import util.UserRedisUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.CompletableFuture;

/**
 * @author zhouming
 * @date 2019/8/16 15:26
 * @Version 1.0
 */
public class RemoteLevelServiceImpl implements RemoteLevelService{
    @Resource
    private CustomerLevelService customerLevelService;
    @Resource
    private CustomerLevelRecordService customerLevelRecordService;
    @Resource
    private LevelConfigService levelConfigService;
    @Resource
    private OutInfoService outInfoService;
    @Resource
    private TeamLevelConfigService teamLevelConfigService;
    @Resource
    private MessageProducer messageProducer;
    @Resource
    private DealRecordService dealRecordService;
    @Resource
    private ReleaseRuleDetailsService releaseRuleDetailsService;
    @Resource
    private ApplyTeamAwardService applyTeamAwardService;
    @Resource
    private RedisService redisService;
    @Resource
    private ExDigitalmoneyAccountService exDigitalmoneyAccountService;
    @Resource
    private CustomerFreezeService customerFreezeService;
    @Resource
    private UsdtTotalService usdtTotalService;
    @Resource
    private ExDmHotAccountRecordService exDmHotAccountRecordService;
    @Resource
    private ExchangeRecordService exchangeRecordService;
    /**
     * 用户个人等级升级
     * */
    @Override
    public void upgradeUserGrade(Long customerId) throws Exception {
        /*~~~~~~~~~个人等级配置信息~~~~~~~*/
        // 获取配置的等级信息
        QueryFilter configFilter = new QueryFilter(LevelConfig.class);
        configFilter.setOrderby("sort asc");
        List<LevelConfig> configList = levelConfigService.find(configFilter);
        // 查该用户有效父级用户
        Map<String, String> map = new HashMap<>();
        map.put("customerId", customerId.toString());
        List<OutInfo> outInfoList = outInfoService.findParentUserList(map);
        Map<String, String> paramMap = new HashMap<>(2);
        if (outInfoList != null) {
            for (OutInfo info : outInfoList) {
                // 查询直推人数
                // 旧---Integer direcUserNum = outInfoService.findDirecUserNum(outInfo.getCustomerId());
                // 旧2--Integer direcUserNum = outInfoService.findDirecUserNumTwo(outInfo.getCustomerId());
                // 查询用户伞下团队业绩
                // 旧---BigDecimal teamAsset = outInfoService.findTeamAsset(outInfo.getCustomerId());
                // 旧2--String userId = "%-"+outInfo.getCustomerId()+",%";
                // 旧2--BigDecimal teamAsset = outInfoService.findTeamAssetTwo(userId);
                // 从redis取值
                Integer direcUserNum;
                BigDecimal teamAsset;
                Map<String, String> redisMap = redisService.getMap("USERDETAIL:"+info.getCustomerId());
                if (redisMap != null && redisMap.size() > 0) {
                    direcUserNum = Integer.parseInt(redisMap.get("direcUserNum"));
                    teamAsset = new BigDecimal(redisMap.get("teamAsset"));
                    System.out.println("个人等级升级：redis取值，查询直推人数--"+direcUserNum+"用户伞下团队业绩--"+teamAsset);
                } else {
                    direcUserNum = outInfoService.findDirecUserNumTwo(info.getCustomerId());
                    String userId = "%-"+info.getCustomerId()+",%";
                    teamAsset = outInfoService.findTeamAssetTwo(userId);
                    System.out.println(info.getCustomerId()+"个人等级升级：数据库取值，查询直推人数--"+direcUserNum+"用户伞下团队业绩--"+teamAsset);
                }
                // 当前等级
                Integer nowSort = info.getSort();
                // 实际等级
                Integer realitySort = this.decideUpOrDown(info.getCustomerId(), direcUserNum, teamAsset, configList);
                System.out.println(info.getCustomerId()+"个人实际等级："+realitySort);
                /*~~~~~~~~校验个人等级是否升级---start~~~~~~~~~~*/
                if (nowSort.intValue() != realitySort.intValue()) {
                    // 获取用户等级
                    QueryFilter filter = new QueryFilter(CustomerLevel.class);
                    filter.addFilter("customerId=",info.getCustomerId());
                    CustomerLevel level = customerLevelService.get(filter);
                    // 添加用户级别变动记录
                    CustomerLevelRecord record = new CustomerLevelRecord();
                    record.setOldLevelId(level.getLevelId());
                    record.setOldLevelName(level.getLevelName());
                    record.setCustomerId(info.getCustomerId());
                    record.setOldSort(level.getSort());
                    if (realitySort == 0) {
                        CustomerLevel newLevel = new CustomerLevel();
                        newLevel.setId(level.getId());
                        newLevel.setSort(0);
                        newLevel.setLevelName("无等级");
                        newLevel.setLevelId(Long.parseLong("0"));
                        customerLevelService.update(newLevel);
                    } else {
                        LevelConfig nowConfig = this.getConfig(realitySort,configList);
                        CustomerLevel newLevel = new CustomerLevel();
                        newLevel.setId(level.getId());
                        newLevel.setLevelId(nowConfig.getId());
                        newLevel.setLevelName(nowConfig.getLevelName());
                        newLevel.setSort(nowConfig.getSort());
                        customerLevelService.update(newLevel);
                    }
                    record.setNewLevelId(level.getLevelId());
                    record.setNewLevelName(level.getLevelName());
                    record.setNewSort(level.getSort());
                    record.setLevelType(1);
                    customerLevelRecordService.save(record);
                }

                /*~~~~~~~~校验社区等级是否升级---start 不需要 社区等级升级与个人等级无关~~~~~~~~~~*/
                /*messageProducer.toUserUpTeamGrade(info.getCustomerId().toString());*/
            }
        }

    }

    /**
     * 用户团队等级升级
     * */
    @Override
    public void upgradeUserTeamGrade(Long customerId) {

        // 获取团队配置的等级信息
        QueryFilter teamConfigFilter = new QueryFilter(TeamLevelConfig.class);
        teamConfigFilter.setOrderby("teamSort asc");
        List<TeamLevelConfig> teamConfigList = teamLevelConfigService.find(teamConfigFilter);

        // 查该用户有效父级用户
        Map<String, String> map = new HashMap<>();
        map.put("customerId", customerId.toString());
        List<OutInfo> outInfoList = outInfoService.findParentUserList(map);
        Map<String, String> paramMap = new HashMap<>(2);
        // 查询自己投资信息
        OutInfo outInfo = outInfoService.getOutInfo(customerId);
        if (outInfoList != null) {
            // 加上自己
            outInfoList.add(0,outInfo);
            for (OutInfo info : outInfoList) {
                if (info != null) {
                    // 查询直推人数
                    // 旧---Integer direcUserNum = outInfoService.findDirecUserNum(outInfo.getCustomerId());
                    // 旧2---Integer direcUserNum = outInfoService.findDirecUserNumTwo(info.getCustomerId());

                    // 查询用户伞下团队业绩
                    // 旧---BigDecimal teamAsset = outInfoService.findTeamAsset(outInfo.getCustomerId());
                    // 旧2---String userId = "%-"+info.getCustomerId()+",%";
                    // 旧2---BigDecimal teamAsset = outInfoService.findTeamAssetTwo(userId);
                    Integer direcUserNum;
                    BigDecimal teamAsset;
                    Map<String, String> redisMap = redisService.getMap("USERDETAIL:"+info.getCustomerId());
                    if (redisMap != null && redisMap.size() > 0) {
                        direcUserNum = Integer.parseInt(redisMap.get("direcUserNum"));
                        teamAsset = new BigDecimal(redisMap.get("teamAsset"));
                        System.out.println("用户Id:"+info.getCustomerId()+"redis取值，查询直推人数--"+direcUserNum+"用户伞下团队业绩--"+teamAsset);
                    } else {
                        direcUserNum = outInfoService.findDirecUserNumTwo(info.getCustomerId());
                        String userId = "%-"+info.getCustomerId()+",%";
                        teamAsset = outInfoService.findTeamAssetTwo(userId);
                        System.out.println("用户Id:"+info.getCustomerId()+"--数据库取值，查询直推人数--"+direcUserNum+"用户伞下团队业绩--"+teamAsset);
                    }
                    // 社区等级升级---社区只有升级没有降级
                    // 个人资产
                    BigDecimal myInvest = info.getBaseMoney();
                    // 获取用户当前等级
                    Integer myLevel = info.getTeamSort() != null ? info.getTeamSort() : 0;
                    // 如果当前等级为顶级，则不用计算了
                    // 获取最高等级
                    Integer maxLevel = teamConfigList.get(teamConfigList.size()-1).getTeamSort();
                    if (myLevel.compareTo(maxLevel) == 0) { continue; }
                    // 实际等级
                    Integer realityTeamSort = this.teamLevelUpOrDown(myLevel, info.getCustomerId(),myInvest, direcUserNum, teamAsset, teamConfigList);
                    // 添加分布式锁
                    Boolean lock = redisService.lock("UserTeamGrade:"+info.getCustomerId());
                    if (lock) {
                        // 新 修改后 realityTeamSort 不是实际等级 如果说符合等级 就是对应的等级sort 不是则为0
                        // 实际等级要大于单前等级---升级
                        if (realityTeamSort.intValue() > myLevel.intValue()) {
                            // 校验社区等级是否已升级过--通过升级记录判断
                            // redis记录用户等级
                            String redisTeamSort = redisService.get("TEAMGRADE:"+info.getCustomerId());
                            if (redisTeamSort == null || "".equals(redisTeamSort) || redisTeamSort.compareTo(String.valueOf(realityTeamSort)) != 0) {
                                // 当前平台币code
                                String platformCode = RedisStaticUtil.getAppConfigValue(RulesConfig.RulesCoinKey, "coinCode");
                                // 获取平台币账户信息--查redis缓存
                                ExDigitalmoneyAccountRedis platExaccount = UserRedisUtils.getAccountRedis(info.getCustomerId().toString(),platformCode);
                                // 连续升级---循环的意义（社区升级得发平台币，和个人等级不一样）
                                for (int i = myLevel; i < realityTeamSort; i++) {
                                    // 要升级等级
                                    int willLevel = i + 1;
                                    // redis记录用户等级
                                    redisService.save("TEAMGRADE:"+info.getCustomerId(), String.valueOf(willLevel));
                                    // 流水号
                                    String transactionNum = IdGenerate.transactionNum(DealEnum.TYPE5.getIndex());
                                    TeamLevelConfig config = this.getTeamConfig(willLevel, teamConfigList);
                                    /*~~~~~~~~~~~~~~添加升级记录（包含奖励记录）~~~~~~~~~~~~~~*/
                                    CustomerLevelRecord record = new CustomerLevelRecord();
                                    record.setCustomerId(info.getCustomerId());
                                    record.setLevelType(2);
                                    if (myLevel == 0){
                                        record.setOldLevelId(Long.parseLong("0"));
                                        record.setOldLevelName("无社区等级");
                                        record.setOldSort(0);
                                    } else {
                                        TeamLevelConfig oldConfig = this.getTeamConfig(myLevel, teamConfigList);
                                        record.setOldLevelId(oldConfig.getId());
                                        record.setOldLevelName(oldConfig.getTeamLevelName());
                                        record.setOldSort(oldConfig.getTeamSort());
                                    }
                                    TeamLevelConfig newConfig = this.getTeamConfig(willLevel, teamConfigList);
                                    record.setNewLevelId(newConfig.getId());
                                    record.setNewLevelName(newConfig.getTeamLevelName());
                                    record.setNewSort(newConfig.getTeamSort());
                                    record.setTeamAwardNum(config.getTeamAwardNum());
                                    customerLevelRecordService.save(record);
                                    /*~~~~~~~~~~~~~添加收益记录~~~~~~~~~~~~~~*/
                                    DealRecord dealRecord = new DealRecord();
                                    dealRecord.setOutInfoId(info.getId());
                                    dealRecord.setAccountId(info.getAccountId());
                                    dealRecord.setCustomerId(info.getCustomerId());
                                    dealRecord.setTransactionNum(transactionNum);
                                    dealRecord.setCoinCode(platformCode);
                                    dealRecord.setDealType(Integer.parseInt(DealEnum.TYPE5.getIndex()));
                                    dealRecord.setDealMoney(config.getTeamAwardNum());
                                    dealRecord.setRemark(DealEnum.SITE5.getName());
                                    dealRecordService.save(dealRecord);
                                    // 奖励平台币---进入冻结
                                    List<Accountadd> list = new ArrayList<Accountadd>();
                                    // 冷账户增加---锁仓冻结（55）
                                    list.add(AccountUtil.getAccountAdd(platExaccount.getId(), new BigDecimal("+" + config.getTeamAwardNum()), 2, 1, 55,
                                            transactionNum));
                                    messageProducer.toAccount(JSON.toJSONString(list));
                                }
                                // 获取用户等级
                                QueryFilter levelFilter = new QueryFilter(CustomerLevel.class);
                                levelFilter.addFilter("customerId=",info.getCustomerId());
                                CustomerLevel level = customerLevelService.get(levelFilter);
                                // 修改用户等级
                                TeamLevelConfig config = this.getTeamConfig(realityTeamSort, teamConfigList);
                                CustomerLevel newLevel = new CustomerLevel();
                                newLevel.setId(level.getId());
                                newLevel.setTeamSort(realityTeamSort);
                                newLevel.setTeamLevelName(config.getTeamLevelName());
                                newLevel.setTeamLevelId(config.getId());
                                // newLevel.setIsTeamAward(1); ---等级升级需要改变释放状态
                                // 应该不需要的，是否释放由两个地方控制，
                                // 一是后台申请审核通过，二是每月底释放规则的判定
                                customerLevelService.update(newLevel);

                                // 添加用户释放基础信息
                                QueryFilter filter1 = new QueryFilter(ReleaseRuleDetails.class);
                                filter1.addFilter("customerId=", info.getCustomerId());
                                ReleaseRuleDetails details = releaseRuleDetailsService.get(filter1);
                                // 查看用户是否有申请通过记录（无申请通过记录 则开始业绩（startTeamAward）为 0）
                                QueryFilter applyFilter = new QueryFilter(ApplyTeamAward.class);
                                applyFilter.addFilter("customerId=", info.getCustomerId());
                                applyFilter.addFilter("auditStatus=", 1);
                                ApplyTeamAward applyTeamAward = applyTeamAwardService.get(applyFilter);
                                if (applyTeamAward == null) {
                                    teamAsset = new BigDecimal("0");
                                }
                                if (details == null) {
                                    details = new ReleaseRuleDetails();
                                    details.setCustomerId(info.getCustomerId());
                                    details.setCustomerLevelId(level.getId());
                                    details.setTeamLevelId(newLevel.getTeamLevelId());
                                    details.setTeamLevelName(newLevel.getTeamLevelName());
                                    details.setTeamSort(newLevel.getTeamSort());
                                    details.setStartTeamAward(teamAsset);
                                    details.setStartTime(new Date());
                                    details.setEndTime(new Date());
                                    releaseRuleDetailsService.save(details);
                                } else {
                                    details.setCustomerId(info.getCustomerId());
                                    details.setCustomerLevelId(level.getId());
                                    details.setTeamLevelId(newLevel.getTeamLevelId());
                                    details.setTeamLevelName(newLevel.getTeamLevelName());
                                    details.setTeamSort(newLevel.getTeamSort());
                                    details.setStartTeamAward(teamAsset);
                                    Date now = new Date();
                                    details.setStartTime(now);
                                    details.setEndTime(DateUtils.addDays(now, 31));
                                    releaseRuleDetailsService.update(details);
                                }
                            }
                        }
                        redisService.unLock("UserTeamGrade:"+info.getCustomerId());
                    }
                }
            }
        }
    }

    @Override
    public void updateReleaseRule(Long customerId) {

    }

    /**
     * 获取用户当前实际等级
     * @param customerId 用户ID
     * @param direcUserNum 直推人数
     * @param teamAsset 团队业绩
     * @param configList 等级配置
     * */
    private Integer decideUpOrDown (Long customerId,Integer direcUserNum, BigDecimal teamAsset, List<LevelConfig> configList) {
        Integer realitySort = 0;
        Map<String, String> map = new HashMap<>(2);
        map.put("customerId", customerId.toString());
        for (LevelConfig config : configList) {
            if (direcUserNum.compareTo(config.getDirectRecommendNum()) > -1
                    && teamAsset.compareTo(config.getTeamPerformance()) > -1) {
                if (config.getSort() > 1) {
                    // 查询当前等级 用户实际下级满足条件条数
                    map.put("sort", config.getSort().toString());
                    Integer wireNum = outInfoService.findWireNum(map);
                    if (wireNum.compareTo(config.getNextRecommendNum()) > -1) {
                        realitySort = config.getSort();
                    }
                    // 校验是否出现跳级， 如出现跳级直接退出等级循环
/*                    if ((config.getSort() - realitySort) != 1) { break; }
                    map.put("sort", config.getSort().toString());
                    int wireNumTwo = 0;
                    // 查询直推有效人
                    List<OutInfo> outInfos = outInfoService.findDirectUser(customerId);
                    Long statTime = System.currentTimeMillis();*/

//                    for (OutInfo outInfo : outInfos) {
//                        // 直推用户团队下符合等级数量
//                        map.put("customerId", outInfo.getCustomerId().toString());
//                        Integer num = outInfoService.findWireNumTwo(map);
//                        if (num > 0) {
//                            wireNumTwo++;
//                        }
//                        if (wireNumTwo >= config.getNextRecommendNum()) {
//                            realitySort = config.getSort();
//                            break;
//                        }
//                    }
/*                    // 并发计算推荐人数
                    List<List<OutInfo>> list = splitListForNum(outInfos, 10);
                    CompletableFuture[] futures = new CompletableFuture[10];
                    for (int i = 0; i < list.size(); i++) {
                        int finalI = i;
                        futures[i] = CompletableFuture.supplyAsync(() -> execute3(list.get(finalI), map));
                    }
                    CompletableFuture.allOf(futures).join();
                    try {
                        for (CompletableFuture future : futures) {
                            Integer two = Integer.parseInt(future.get().toString());
                            if (two > 0) {
                                wireNumTwo = wireNumTwo + two;
                            }
                            if (wireNumTwo >=  config.getNextRecommendNum()) {
                                realitySort = config.getSort();
                                break;
                            }
                        }
                    } catch (Exception e) {
                        e.getLocalizedMessage();
                        System.out.println("*****个人等级并发查询下级等级个数异常");
                    }*/

                } else {
                    // 通过以上条件必定后 等级必定是一级
                    realitySort = 1;
                }
            }
        }
        return realitySort;
    }

    private Integer execute3(List<OutInfo> outInfos, Map<String, String> map) {
        int wireNumTwo = 0;
        for (OutInfo outInfo : outInfos) {
            // 直推用户团队下符合等级数量
            map.put("customerId", outInfo.getCustomerId().toString());
            Integer num = outInfoService.findWireNumTwo(map);
            if (num > 0) {
                wireNumTwo++;
            }
        }
        return wireNumTwo;
    }

    /**
    * 获取后台等级参数
    * */
    private LevelConfig getConfig(Integer sort, List<LevelConfig> configList) {
        LevelConfig config = null;
        for (LevelConfig levelConfig : configList) {
            if (sort.equals(levelConfig.getSort())) {
                config = levelConfig;
                break;
            }
        }
        return config;
    }

    /**
     * 获取用户当前实际社区等级升级
     * @param newTeamLevel 当前等级
     * @param customerId 用户ID
     * @param baseMoney 个人资产
     * @param direcUserNum 直推人数
     * @param teamAsset 团队业绩
     * @param teamConfigList 社区等级配置
     * */
    private Integer teamLevelUpOrDown (Integer newTeamLevel, Long customerId, BigDecimal baseMoney, Integer direcUserNum, BigDecimal teamAsset, List<TeamLevelConfig> teamConfigList) {
        Integer realitySort = 0;
        Map<String, String> map = new HashMap<>(2);
        map.put("customerId", customerId.toString());
        // 根据当前等级计算是否符合下一等级
        for (int i = newTeamLevel; i < teamConfigList.size(); i++) {
            Integer sort = execute(customerId, teamConfigList.get(i), direcUserNum,baseMoney, teamAsset, newTeamLevel);
            // sort 是否比当前等级大
            if (sort.compareTo(newTeamLevel) == 1) {
                realitySort = sort;
            } else {
                break;
            }
        }
        /*CompletableFuture[] futures = new CompletableFuture[4];
        for (int i = 0; i < teamConfigList.size(); i++) {
            int finalI = i;
            futures[i] = CompletableFuture.supplyAsync(() -> {
                return execute(customerId, teamConfigList.get(finalI), direcUserNum,
                            baseMoney, teamAsset, newTeamLevel);
            });
        }
        CompletableFuture.allOf(futures).join();
        try {
            for (CompletableFuture future : futures) {
                if (Integer.parseInt(future.get().toString()) > realitySort) {
                    realitySort = Integer.parseInt(future.get().toString());
                }
            }
        } catch (Exception e) {e.getLocalizedMessage();}*/
        /*for (TeamLevelConfig teamConfig : teamConfigList) {
            // 个人资产
            if (baseMoney.compareTo(teamConfig.getOwnAsset()) > -1) {
                // 直推人数
                if (direcUserNum.compareTo(teamConfig.getDirectRecommendNum()) > -1) {
                    // 团队业绩
                    if (teamAsset.compareTo(teamConfig.getTeamPerformance()) > -1) {
                        // 查询当前等级 用户实际下级满足条件条数  --- 下级级以上推荐人数 社区等级和个人等级对应
                        // 社区等级升级需求：升社区等级二时，下级级以上推荐人数 是1级社区等级个数
                        // 当社区等级升三级四级时 下级级以上推荐人数 是 1级 2级个人等级个数
                        // 以二级为分界线 当前等级小于二级 单前等级大于二级则直接越过第二级条件
                        if (newTeamLevel < 2) {
                            if (teamConfig.getTeamSort() == 2) {
                                map.remove("sort");
                                map.put("teamSort", teamConfig.getTeamSort().toString());
                            } else {
                                map.remove("teamSort");
                                // 只有升为 2级是才能继续升
                                if (realitySort >= 2) {
                                    if (teamConfig.getTeamSort() == 3) {
                                        map.put("sort", "2");
                                    } else if (teamConfig.getTeamSort() == 4) {
                                        map.put("sort", "3");
                                    }
                                } else {
                                    // 设置无效等级---能进入这一步 必定是一等级
                                    // 注：社区等级一的 下级级以上推荐人数 必须设置为0
                                    map.put("sort", "9999");
                                }
                            }
                        } else {
                            if (teamConfig.getTeamSort() == 3) {
                                map.put("sort", "2");
                            } else if (teamConfig.getTeamSort() == 4) {
                                map.put("sort", "3");
                            } else {
                                // 设置无效等级 能进入这一步 必定是一等级
                                // 注：社区等级一的 下级级以上推荐人数 必须设置为 0
                                map.put("sort", "9999");
                            }
                        }

                        int wireNumTwo = 0;
                        int flag = 0;
                        // 查询直推有效人
                        List<OutInfo> outInfos = outInfoService.findDirectUser(customerId);
                        for (OutInfo outInfo : outInfos) {
                            flag++;
                            // 直推用户团队下符合等级数量
                            map.put("customerId", outInfo.getCustomerId().toString());
                            Integer num = outInfoService.findWireNumTwo(map);
                            if (num > 0) {
                                wireNumTwo++;
                            }
                            if (wireNumTwo >= teamConfig.getNextRecommendNum()) {
                                realitySort = teamConfig.getTeamSort();
                                break;
                            }
                        }
                        System.out.println(customerId+"-----"+flag + "---" + realitySort);
                        *//*旧 --Integer wireNum = outInfoService.findWireNum(map);
                        if (wireNum.compareTo(teamConfig.getNextRecommendNum()) > -1) {
                            realitySort = teamConfig.getTeamSort();
                        }*//*
                    }
                }
            }
        }*/
        return realitySort;
    }

    public Integer execute(Long customerId, TeamLevelConfig teamConfig, Integer direcUserNum, BigDecimal baseMoney, BigDecimal teamAsset, Integer newTeamLevel) {
        Map<String, String> map = new HashMap<>();
        Integer realitySort = 0;
        // 个人资产
        if (baseMoney.compareTo(teamConfig.getOwnAsset()) > -1) {
            // 直推人数
            if (direcUserNum.compareTo(teamConfig.getDirectRecommendNum()) > -1) {
                // 团队业绩
                if (teamAsset.compareTo(teamConfig.getTeamPerformance()) > -1) {
                    // 查询当前等级 用户实际下级满足条件条数  --- 下级级以上推荐人数 社区等级和个人等级对应
                    // 社区等级升级需求：升社区等级二时，下级级以上推荐人数 是1级社区等级个数
                    // 当社区等级升三级四级时 下级级以上推荐人数 是 1级 2级个人等级个数
                    // 以二级为分界线 当前等级小于二级 单前等级大于二级则直接越过第二级条件
                    // 新规则 3 4 等级中的下级级以上推荐人数 是对应的 2 3社区等级个数
                    /*if (newTeamLevel < 2) {
                        if (teamConfig.getTeamSort() == 2) {
                            map.remove("sort");
                            map.put("teamSort", teamConfig.getTeamSort().toString());
                        } else {
                            if (teamConfig.getTeamSort() == 3) {
                                map.put("sort", "2");
                            } else if (teamConfig.getTeamSort() == 4) {
                                map.put("sort", "3");
                            } else {
                                // 应该直接给当前等级
                                realitySort = teamConfig.getTeamSort();
                            }
                            *//*map.remove("teamSort");
                            // 只有升为 2级是才能继续升
                            if (realitySort >= 2) {
                                if (teamConfig.getTeamSort() == 3) {
                                    map.put("sort", "2");
                                } else if (teamConfig.getTeamSort() == 4) {
                                    map.put("sort", "3");
                                }
                            } else {
                                // 设置无效等级---能进入这一步 必定是一等级
                                // 注：社区等级一的 下级级以上推荐人数 必须设置为0
                                map.put("sort", "9999");
                            }*//*
                        }
                    } else {
                        if (teamConfig.getTeamSort() == 3) {
                            map.put("sort", "2");
                        } else if (teamConfig.getTeamSort() == 4) {
                            map.put("sort", "3");
                        } else {
                            // 直接给1等级
                            realitySort = teamConfig.getTeamSort();
                        }
                    }*/
                    if (teamConfig.getTeamSort() == 2) {
                        map.put("teamSort", "2");
                    } else if (teamConfig.getTeamSort() == 3) {
                        map.put("teamSort", "3");
                    } else if (teamConfig.getTeamSort() == 4) {
                        map.put("teamSort", "4");
                    } else {
                        realitySort = 1;
                    }
                    if (realitySort == 0) {
                        Boolean flag = redisService.lock("teamUp:"+customerId);
                        if (flag) {
                            // redis查询是否已统计
                            String teamUpClass = redisService.get("teamUpClass:"+customerId);
                            if (StringUtil.isNull(teamUpClass)) {
                                redisService.unLock("teamUp:"+customerId);
                                System.out.println("redis查用户，"+ customerId +"--"+ teamConfig.getTeamSort() +"当前等级"+newTeamLevel+"实际等级："+realitySort);
                                return Integer.parseInt(teamUpClass);
                            }
                            int wireNumTwo = 0;
                            // 查询直推有效人
                            List<OutInfo> outInfos = outInfoService.findDirectUser(customerId);
                            Long statTime = System.currentTimeMillis();
                            for (OutInfo outInfo : outInfos) {
                                // 直推用户团队下符合等级数量
                                map.put("customerId", outInfo.getCustomerId().toString());
                                Integer num = outInfoService.findWireNumTwo(map);
                                if (num > 0) {
                                    wireNumTwo++;
                                }
                                if (wireNumTwo >= teamConfig.getNextRecommendNum()) {
                                    realitySort = teamConfig.getTeamSort();
                                    break;
                                }
                            }
                            Long endTime = System.currentTimeMillis();
                            System.out.println(outInfos.size() + "个用户，"+ customerId +"--"+ teamConfig.getTeamSort() +"当前等级"+newTeamLevel+",用户下查询团队人数用时"+"---"+(endTime-statTime)+"ms"+"实际等级："+realitySort);
                            redisService.save("teamUpClass:"+customerId, realitySort.toString(), 3*60*60);
                            redisService.unLock("teamUp:"+customerId);
                        }
                            /*// 查询直推有效人
                            List<OutInfo> outInfos = outInfoService.findDirectUser(customerId);
                            Long statTime = System.currentTimeMillis();
                            // 异步线程执行
                            CompletableFuture[] futures = new CompletableFuture[outInfos.size()];
                            List<List<OutInfo>> list = splitListForNum(outInfos, outInfos.size());
                            for (int i = 0; i < list.size(); i++) {
                                int finalI = i;
                                futures[i] = CompletableFuture.supplyAsync(() -> execute2(list.get(finalI), map));
                            }
                            CompletableFuture.allOf(futures).join();
                            Long endTime = System.currentTimeMillis();
                            try {
                                int wireNumTwo = 0;
                                for (CompletableFuture future : futures) {
                                    if (Integer.parseInt(future.get().toString()) > 0) {
                                        wireNumTwo++;
                                    }
                                    if (wireNumTwo >= teamConfig.getNextRecommendNum()) {
                                        realitySort = teamConfig.getTeamSort();
                                        break;
                                    }
                                }
                            } catch (Exception e) {e.getLocalizedMessage();}*/
                    }
                    /*旧 --Integer wireNum = outInfoService.findWireNum(map);
                    if (wireNum.compareTo(teamConfig.getNextRecommendNum()) > -1) {
                        realitySort = teamConfig.getTeamSort();
                    }*/
                }
            }
        }
        return realitySort;
    }

    private Integer execute2(List<OutInfo> outInfos, Map<String, String> map) {
        // 直推用户团队下符合等级数量
        map.put("customerId", outInfos.get(0).getCustomerId().toString());
        Integer num = outInfoService.findWireNumTwo(map);
        return num;
    }

    /**
     * 分割list
     * */
    private List<List<OutInfo>> splitListForNum(List<OutInfo> source, int num){
        List<List<OutInfo>> result = new ArrayList<List<OutInfo>>();
        int remaider = source.size()%num;  //(先计算出余数)
        int number = source.size()/num;  //然后是商
        int offset = 0;//偏移量
        for(int i = 0; i < num; i ++){
            List<OutInfo> value = null;
            if(remaider>0){
                value = source.subList(i*number+offset, (i+1)*number+offset+1);
                remaider--;
                offset++;
            }else{
                value = source.subList(i*number+offset, (i+1)*number+offset);
            }
            result.add(value);
        }
        return result;
    }

    /**
     * 获取后台社区等级参数
     * */
    private TeamLevelConfig getTeamConfig(Integer sort, List<TeamLevelConfig> configList) {
        TeamLevelConfig config = null;
        for (TeamLevelConfig teamLevelConfig : configList) {
            if (sort.equals(teamLevelConfig.getTeamSort())) {
                config = teamLevelConfig;
                break;
            }
        }
        return config;
    }

    @Override
    public void customerOut(Long customerId) {
        outInfoService.userOut(9999L,customerId, "USDT");
    }

    public static void main(String[] args) {
        List<LevelConfig> configList = new ArrayList<>();
        LevelConfig config1 = new LevelConfig();
        config1.setSort(1);
        config1.setDirectRecommendNum(3);
        config1.setNextRecommendNum(0);
        config1.setTeamPerformance(new BigDecimal(1000));

        LevelConfig config2 = new LevelConfig();
        config2.setSort(2);
        config2.setDirectRecommendNum(5);
        config2.setNextRecommendNum(3);
        config2.setTeamPerformance(new BigDecimal(3000));

        LevelConfig config3 = new LevelConfig();
        config3.setSort(3);
        config3.setDirectRecommendNum(8);
        config3.setNextRecommendNum(6);
        config3.setTeamPerformance(new BigDecimal(8000));
        configList.add(config1);
        configList.add(config2);
        configList.add(config3);

//        Upgrade upgrade = new Upgrade();
//        upgrade.setUserNum(10);
//        upgrade.setLevelNum(4);
//        upgrade.setAssetSum(new BigDecimal(300000));

/*        LevelConfig minConfig = config1;
        LevelConfig manConfig = config3;*/

/*        Integer nowSort = 1;
        Integer realitySort = 0;*/


        List<TeamLevelConfig> teamConfigList = new ArrayList<>();
        TeamLevelConfig team1 = new TeamLevelConfig();
        team1.setDirectRecommendNum(10);
        team1.setTeamPerformance(new BigDecimal(50000));
        team1.setTeamSort(1);
        team1.setOwnAsset(new BigDecimal(1000));
        team1.setNextRecommendNum(0);

        TeamLevelConfig team2 = new TeamLevelConfig();
        team2.setDirectRecommendNum(10);
        team2.setTeamPerformance(new BigDecimal(80000));
        team2.setTeamSort(2);
        team2.setOwnAsset(new BigDecimal(5000));
        team2.setNextRecommendNum(5);

        TeamLevelConfig team3 = new TeamLevelConfig();
        team3.setDirectRecommendNum(10);
        team3.setTeamPerformance(new BigDecimal(100000));
        team3.setTeamSort(3);
        team3.setOwnAsset(new BigDecimal(8000));
        team3.setNextRecommendNum(8);

        TeamLevelConfig team4 = new TeamLevelConfig();
        team4.setDirectRecommendNum(10);
        team4.setTeamPerformance(new BigDecimal(120000));
        team4.setTeamSort(4);
        team4.setOwnAsset(new BigDecimal(10000));
        team4.setNextRecommendNum(10);

        teamConfigList.add(team1);
        teamConfigList.add(team2);
        teamConfigList.add(team3);
        teamConfigList.add(team4);

        BigDecimal baseMoney = new BigDecimal(8000);
        Upgrade upgrade = new Upgrade();
        upgrade.setUserNum(10);
        upgrade.setLevelNum(8);
        upgrade.setAssetSum(new BigDecimal(300000));

        Integer minSort = teamConfigList.get(0).getTeamSort();
        TeamLevelConfig minConfig = team1;
        Integer realitySort = 0;
        /*for (TeamLevelConfig config : teamConfigList) {
            if (config.getTeamSort().intValue() == minConfig.getTeamSort().intValue()) {
                if (upgrade.getUserNum().intValue() >= config.getDirectRecommendNum().intValue()) {
                    if (upgrade.getAssetSum().intValue() >= config.getTeamPerformance().intValue()) {
                        if (baseMoney.compareTo(config.getOwnAsset()) > -1) {
                            realitySort = config.getTeamSort();
                        }
                    }
                }
            } else {
                if (upgrade.getUserNum().intValue() >= config.getDirectRecommendNum().intValue()) {
                    if (upgrade.getLevelNum().intValue() >= config.getNextRecommendNum().intValue()) {
                        if (upgrade.getAssetSum().compareTo(config.getTeamPerformance()) > -1 ) {
                            if (baseMoney.compareTo(config.getOwnAsset()) > -1) {
                                realitySort = config.getTeamSort();
                            }
                        }
                    }
                }
            }
        }*/
//        Date now = new Date();
//        Date startDate = DateUtils.addDays(now, 31);
//        System.out.println("输出的时间：---"+startDate);
    }


    @Override
    public void changeAccount(Map<String, Object> map) {
        String customerId = map.get("customerId").toString();
        String coinCode = map.get("coinCode").toString();
        // 1 热账户 2 冷账户
        String accountType = map.get("accountType").toString();
        // plusorminus + -
        String plusorminus = map.get("plusorminus").toString();
        // 金额
        String hotmoney = map.get("hotmoney").toString();
        // 流水号
        String transactionNum = map.get("transactionNum").toString();

        // 获取币账户信息--查redis缓存
        RedisUtil<UserRedis> redisUtil = new RedisUtil<UserRedis>(UserRedis.class);
        UserRedis userRedis = redisUtil.get(customerId);
        // 获得币账户
        ExDigitalmoneyAccountRedis exaccount = UserRedisUtils.getAccount(userRedis.getDmAccountId(coinCode).toString(),coinCode);
        if (transactionNum == null || "".equals(transactionNum) ) {
            transactionNum = IdGenerate.transactionNum(NumConstant.Ex_Dm_Transaction);
        }
        List<Accountadd> list = new ArrayList<Accountadd>();
        if ("1".equals(accountType)) {
            if ("plus".equals(plusorminus)) {
                // 热账户增加（55）
                list.add(AccountUtil.getAccountAdd(exaccount.getId(), new BigDecimal("+" + hotmoney), 1, 1,55,
                        transactionNum));
            } else if ("minus".equals(plusorminus)) {
                // 热账户减少
                list.add(AccountUtil.getAccountAdd(exaccount.getId(), new BigDecimal("-" + hotmoney), 1, 1,55,
                        transactionNum));
            }
        } else if ("2".equals(accountType)) {
            if ("plus".equals(plusorminus)) {
                // 冷账户增加（55）
                list.add(AccountUtil.getAccountAdd(exaccount.getId(), new BigDecimal("+" + hotmoney), 2, 1,55,
                        transactionNum));
            } else if ("minus".equals(plusorminus)) {
                // 冷账户减少
                list.add(AccountUtil.getAccountAdd(exaccount.getId(), new BigDecimal("-" + hotmoney), 2, 1,55,
                        transactionNum));
            }
        }
        messageProducer.toAccount(JSON.toJSONString(list));

    }


    @Override
    public void findPingzhang() {
        QueryFilter filter = new QueryFilter(ExDigitalmoneyAccount.class);
        filter.addFilter("coinCode=", "BBGO");
        filter.addFilter("hotMoney < ", 0);
        List<ExDigitalmoneyAccount> accountList = exDigitalmoneyAccountService.find(filter);
        if (accountList != null && accountList.size() > 0) {
            String transactionNum = IdGenerate.transactionNum(NumConstant.Ex_Dm_Transaction);
            for (ExDigitalmoneyAccount account : accountList) {
                BigDecimal BBGOhotMoney = account.getHotMoney();
                // 汇率
                BigDecimal ratio = new BigDecimal("0.018");
                BigDecimal USDTHotMoney = BBGOhotMoney.multiply(new BigDecimal(-1)).multiply(ratio).setScale(10, BigDecimal.ROUND_HALF_UP);
                // 平账
                // 获取币账户信息--查redis缓存
                RedisUtil<UserRedis> redisUtil = new RedisUtil<UserRedis>(UserRedis.class);
                UserRedis userRedis = redisUtil.get(account.getCustomerId().toString());
                List<Accountadd> list = new ArrayList<Accountadd>();
                // 获得币账户
                ExDigitalmoneyAccountRedis exaccount = UserRedisUtils.getAccount(userRedis.getDmAccountId("BBGO").toString(), "BBGO");
                // BBGO热账户增加
                list.add(AccountUtil.getAccountAdd(exaccount.getId(), BBGOhotMoney.multiply(new BigDecimal("-1")), 1, 1,55,
                        transactionNum));
                // USDT热账户减少
                ExDigitalmoneyAccountRedis usdtExaccount = UserRedisUtils.getAccount(userRedis.getDmAccountId("USDT").toString(), "USDT");
                list.add(AccountUtil.getAccountAdd(usdtExaccount.getId(), USDTHotMoney.multiply(new BigDecimal("-1")), 1, 1,55,
                        transactionNum));
                messageProducer.toAccount(JSON.toJSONString(list));
            }
        }
    }

    @Override
    public void usdtPingzhang() {
        QueryFilter filter = new QueryFilter(ExDigitalmoneyAccount.class);
        filter.addFilter("coinCode=", "USDT");
        filter.addFilter("hotMoney < ", 0);
        List<ExDigitalmoneyAccount> accountList = exDigitalmoneyAccountService.find(filter);
        if (accountList != null && accountList.size() > 0) {
            System.out.println("USDT合计平账："+accountList.size()+"个");
            String transactionNum = IdGenerate.transactionNum(NumConstant.Ex_Dm_Transaction);
            for (ExDigitalmoneyAccount account : accountList) {
                Long customerId = account.getCustomerId();
                BigDecimal usdtMoney = account.getHotMoney();
                if (!"7112".equals(customerId) || !"19819".equals(customerId) || !"17709".equals(customerId)) {
                    // 入局表修改e
                    BigDecimal money = usdtMoney.multiply(new BigDecimal("4"));
                    QueryFilter filter1 = new QueryFilter(OutInfo.class);
                    filter1.addFilter("customerId=", customerId);
                    filter1.addFilter("status=", 0);
                    OutInfo outInfo = outInfoService.get(filter1);
                    outInfo.setBaseMoney(outInfo.getBaseMoney().add(usdtMoney));
                    outInfo.setAllMoney(outInfo.getAllMoney().add(money));
                    outInfo.setHotMoney(outInfo.getHotMoney().add(money));
                    outInfoService.update(outInfo);
                    // 投资记录表修改
                    QueryFilter filter2 = new QueryFilter(CustomerFreeze.class);
                    filter2.addFilter("customerId=", customerId);
                    CustomerFreeze freeze = customerFreezeService.get(filter2);
                    freeze.setFreezeMoney(freeze.getFreezeMoney().add(usdtMoney));
                    customerFreezeService.update(freeze);
                }
                /*
                * 账户维护
                * */
                // 获取币账户信息--查redis缓存
                RedisUtil<UserRedis> redisUtil = new RedisUtil<UserRedis>(UserRedis.class);
                UserRedis userRedis = redisUtil.get(customerId.toString());
                List<Accountadd> list = new ArrayList<Accountadd>();
                // 获得币账户
                ExDigitalmoneyAccountRedis exaccount = UserRedisUtils.getAccount(userRedis.getDmAccountId("USDT").toString(), "USDT");
                list.add(AccountUtil.getAccountAdd(exaccount.getId(), usdtMoney.multiply(new BigDecimal("-1")), 1, 1,55,
                        transactionNum));
                list.add(AccountUtil.getAccountAdd(exaccount.getId(), usdtMoney, 2, 1,55,
                        transactionNum));
                messageProducer.toAccount(JSON.toJSONString(list));
            }
        }
    }

    @Override
    public void levelPingZhang() {
        QueryFilter filter = new QueryFilter(CustomerLevelRecord.class);
        filter.addFilter("newSort >= ", 2);
        filter.addFilter("created > ", "2020-11-18 00:00:00");
        filter.addFilter("created < ", "2020-11-19 23:59:59");
        filter.setOrderby("newSort Desc");
        List<CustomerLevelRecord> recordList = customerLevelRecordService.find(filter);
        if (recordList != null && recordList.size() > 0) {
            System.out.println("USDT合计平账："+recordList.size()+"个");
            String transactionNum = IdGenerate.transactionNum(NumConstant.Ex_Dm_Transaction);
            for (CustomerLevelRecord record : recordList) {
                Long customerId = record.getCustomerId();
                Integer newSort = record.getNewSort();
                Integer oldSort = record.getOldSort();
                String oldLevelName = record.getOldLevelName();
                Long oldLevelId = record.getOldLevelId();
                // 修改用户社区等级
                QueryFilter filter1 = new QueryFilter(CustomerLevel.class);
                filter1.addFilter("customerId=", customerId);
                CustomerLevel level = customerLevelService.get(filter1);
                level.setTeamLevelId(oldLevelId);
                level.setTeamLevelName(oldLevelName);
                level.setTeamSort(oldSort);
                customerLevelService.update(level);
                /*
                * 平账减去对应冻结BBGO数量
                * */
                int num = newSort - oldSort;
                BigDecimal coldMoney = record.getTeamAwardNum();
                if (num > 1) {
                    coldMoney = coldMoney.add(new BigDecimal("8000000"));
                }
                // 获取币账户信息--查redis缓存
                RedisUtil<UserRedis> redisUtil = new RedisUtil<UserRedis>(UserRedis.class);
                UserRedis userRedis = redisUtil.get(customerId.toString());
                List<Accountadd> list = new ArrayList<Accountadd>();
                // 获得币账户
                ExDigitalmoneyAccountRedis exaccount = UserRedisUtils.getAccount(userRedis.getDmAccountId("BBGO").toString(), "BBGO");
                list.add(AccountUtil.getAccountAdd(exaccount.getId(), coldMoney.multiply(new BigDecimal("-1")), 2, 1,55,
                        transactionNum));
                messageProducer.toAccount(JSON.toJSONString(list));
            }
        }
    }

    @Override
    public void coldBusinessPingZhang() {
        List<UserAccount> accountList = usdtTotalService.coldBusinessPingZhang();
        if (accountList != null && accountList.size() > 0) {
            System.out.println("币账户冻结数量与业务冻结数量不一致，不一致条数："+accountList.size());
            String transactionNum = IdGenerate.transactionNum(NumConstant.Ex_Entrust); // 前缀 10
            for (UserAccount userAccount : accountList) {
                Long customerId = userAccount.getCustomerId();
                // 获取相差数量
                BigDecimal differMoney = userAccount.getFreezeMoney().subtract(userAccount.getColdMoney());
                // 获取币账户信息--查redis缓存
                RedisUtil<UserRedis> redisUtil = new RedisUtil<UserRedis>(UserRedis.class);
                UserRedis userRedis = redisUtil.get(customerId.toString());
                List<Accountadd> list = new ArrayList<Accountadd>();
                // 获得币账户
                ExDigitalmoneyAccountRedis exaccount = UserRedisUtils.getAccount(userRedis.getDmAccountId("USDT").toString(), "USDT");
                list.add(AccountUtil.getAccountAdd(exaccount.getId(), differMoney, 2, 1,55,
                        transactionNum));
                messageProducer.toAccount(JSON.toJSONString(list));
            }
        }
    }

    @Override
    public void exchangeColdPingZhang() {
        QueryFilter filter = new QueryFilter(ExDigitalmoneyAccount.class);
        filter.addFilter("coinCode = ", "SD");
        filter.addFilter("hotMoney >", 0);
        List<ExDigitalmoneyAccount> accountList = exDigitalmoneyAccountService.find(filter);
        if (accountList != null && accountList.size() > 0) {
            System.out.println("SD合计平账："+accountList.size()+"个");
            String transactionNum = IdGenerate.transactionNum("SD"+NumConstant.Ex_Dm_Transaction);
            for (ExDigitalmoneyAccount account : accountList) {
                Long customerId = account.getCustomerId();
                BigDecimal SDMoney = account.getHotMoney();
                BigDecimal BBGOMoney = SDMoney.divide(new BigDecimal("0.023"),10, BigDecimal.ROUND_HALF_UP);
                /*
                 * 账户维护
                 * */
                // 获取币账户信息--查redis缓存
                RedisUtil<UserRedis> redisUtil = new RedisUtil<UserRedis>(UserRedis.class);
                UserRedis userRedis = redisUtil.get(customerId.toString());
                List<Accountadd> list = new ArrayList<Accountadd>();
                // 获得币账户
                ExDigitalmoneyAccountRedis SDccount = UserRedisUtils.getAccount(userRedis.getDmAccountId("SD").toString(), "SD");
                ExDigitalmoneyAccountRedis BBGOccount = UserRedisUtils.getAccount(userRedis.getDmAccountId("BBGO").toString(), "BBGO");
                list.add(AccountUtil.getAccountAdd(SDccount.getId(), SDMoney.multiply(new BigDecimal("-1")), 1, 1,55,
                        transactionNum));
                list.add(AccountUtil.getAccountAdd(BBGOccount.getId(), BBGOMoney, 1, 1,55,
                        transactionNum));
                messageProducer.toAccount(JSON.toJSONString(list));
            }
        }
    }

    @Override
    public void exchangeColdPingZhangTwo() {
        QueryFilter filter = new QueryFilter(ExDigitalmoneyAccount.class);
        filter.addFilter("coinCode = ", "BBGO");
        filter.addFilter("hotMoney <", 0);
        List<ExDigitalmoneyAccount> accountList = exDigitalmoneyAccountService.find(filter);
        if (accountList != null && accountList.size() > 0) {
            System.out.println("BBGO合计平账："+accountList.size()+"个");
            String transactionNum = IdGenerate.transactionNum("BBGO-SD"+NumConstant.Ex_Dm_Transaction);
            for (ExDigitalmoneyAccount account : accountList) {
                int num = 1;
                // 查询记录有多少比负数
                QueryFilter filter2 = new QueryFilter(ExDmHotAccountRecord.class);
                filter2.addFilter("customerId=", account.getCustomerId());
                filter2.addFilter("coinCode=", "BBGO");
                filter2.addFilter("balanceMoney <",0);
                filter2.addFilter("created > ", "2021-03-08 09:00:00");
                filter2.addFilter("created < ", "2021-03-08 17:00:00");
                List<ExDmHotAccountRecord> recordList = exDmHotAccountRecordService.find(filter2);
                if (recordList != null && recordList.size() > 0) {
                    num = num + recordList.size();
                }
                // 查询每个用户兑换记录
                QueryFilter filter3 = new QueryFilter(ExchangeRecord.class);
                filter3.addFilter("customerId=", account.getCustomerId());
                filter3.setOrderby("created DESC");
                List<ExchangeRecord> exchangeRecordList = exchangeRecordService.find(filter3);
                for (int i = 0; i < num; i ++) {
                    ExchangeRecord exchangeRecord = exchangeRecordList.get(i);
                    /*
                     * 账户维护
                     * */
                    // 获取币账户信息--查redis缓存
                    RedisUtil<UserRedis> redisUtil = new RedisUtil<UserRedis>(UserRedis.class);
                    UserRedis userRedis = redisUtil.get(account.getCustomerId().toString());
                    List<Accountadd> list = new ArrayList<Accountadd>();
                    // 获得币账户
                    ExDigitalmoneyAccountRedis SDccount = UserRedisUtils.getAccount(userRedis.getDmAccountId("SD").toString(), "SD");
                    ExDigitalmoneyAccountRedis BBGOccount = UserRedisUtils.getAccount(userRedis.getDmAccountId("BBGO").toString(), "BBGO");
                    list.add(AccountUtil.getAccountAdd(SDccount.getId(), exchangeRecord.getGainNum().multiply(new BigDecimal("-1")), 1, 1,55,
                            transactionNum));
                    list.add(AccountUtil.getAccountAdd(BBGOccount.getId(), exchangeRecord.getPayNum(), 1, 1,55,
                            transactionNum));
                    messageProducer.toAccount(JSON.toJSONString(list));
                }
            }
        }
    }
}
