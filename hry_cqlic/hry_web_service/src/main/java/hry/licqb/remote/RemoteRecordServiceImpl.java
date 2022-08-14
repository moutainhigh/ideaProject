package hry.licqb.remote;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import hry.bean.FrontPage;
import hry.bean.JsonResult;
import hry.customer.commend.model.AppCommendUser;
import hry.customer.commend.service.AppCommendUserService;
import hry.licqb.award.RulesConfig;
import hry.licqb.award.model.OutInfo;
import hry.licqb.award.service.OutInfoService;
import hry.licqb.level.model.CustomerLevel;
import hry.licqb.level.service.CustomerLevelService;
import hry.licqb.record.model.ApplyTeamAward;
import hry.licqb.record.model.CommendInfo;
import hry.licqb.record.model.DealRecord;
import hry.licqb.record.model.ReleaseRuleDetails;
import hry.licqb.record.service.ApplyTeamAwardService;
import hry.licqb.record.service.DealRecordService;
import hry.licqb.record.service.ReleaseRuleDetailsService;
import hry.licqb.util.RedisStaticUtil;
import hry.util.PageFactory;
import hry.util.QueryFilter;
import hry.util.date.DateUtil;
import io.swagger.models.auth.In;
import org.apache.log4j.Logger;

import javax.annotation.Resource;
import javax.json.Json;
import javax.json.JsonObject;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author zhouming
 * @date 2019/8/29 10:39
 * @Version 1.0
 */
public class RemoteRecordServiceImpl implements RemoteRecordService {
    private final static Logger log = Logger.getLogger(RemoteRecordServiceImpl.class);

    @Resource
    private CustomerLevelService customerLevelService;
    @Resource
    private DealRecordService dealRecordService;
    @Resource
    private ReleaseRuleDetailsService releaseRuleDetailsService;
    @Resource
    private ApplyTeamAwardService applyTeamAwardService;
    @Resource
    private OutInfoService outInfoService;
    @Resource
    private AppCommendUserService appCommendUserService;
    /**
     * 我的社区统计
     * */
    @Override
    public JsonResult findMyTeam(Long customerId) {
        long s1 = System.currentTimeMillis();
        JSONObject obj = new JSONObject();
        // 获取该用户社区等级
        QueryFilter filter = new QueryFilter(CustomerLevel.class);
        filter.addFilter("customerId=", customerId);
        CustomerLevel customerLevel = customerLevelService.get(filter);
        // teamSort 0 暂未达到申请社区奖励的条件
        Integer teamSort = 0;
        // 等级名称
        String teamName = "";
        // 社区总人数
        Integer userNum = 0;
        // 社区总资产(有效用户数)
        BigDecimal teamAsset = new BigDecimal("0");
        // 本月新增人数
        Integer newlyUserNum = 0;
        // 上月业绩 （上月完成等级时用户的团队业绩）
        BigDecimal lastMonthAsset = new BigDecimal("0");
        // 本月新增业绩 （对应上个月时间）
        BigDecimal newlyMonthAsset = new BigDecimal("0");

        // 上月总拥金
        BigDecimal lastMonthAward = new BigDecimal("0");
        // 本月总拥金
        BigDecimal thisMonthAward = new BigDecimal("0");

        if (customerLevel != null ) {

            teamSort = customerLevel.getTeamSort();
            teamName = customerLevel.getTeamLevelName();

            Map<String, String> map = new HashMap<>();
            map.put("customerId", customerId.toString());
            Map<String,Object> resultMap = dealRecordService.countTeamUser(map);
            if (resultMap != null) {
                // 换成所有推荐人数
                // userNum = Integer.parseInt(resultMap.get("userNum").toString());
                QueryFilter userFilter = new QueryFilter(AppCommendUser.class);
                userFilter.addFilter("uid=", customerId);
                AppCommendUser appCommendUser = appCommendUserService.get(userFilter);
                if (appCommendUser != null) {
                    userNum = appCommendUser.getOneNumber()
                            + appCommendUser.getTwoNumber()
                            + appCommendUser.getThreeNumber()
                            + appCommendUser.getLaterNumber();
                }
                teamAsset = new BigDecimal(resultMap.get("teamAsset").toString());
            }

            // 获取当前月时间
            List<String> time = getCurrentMonthTime(0);
            map.put("startTime", time.get(0));
            map.put("endTime", time.get(1));
            Map<String,Object> resultMap1 = dealRecordService.countTeamUser(map);
            if (resultMap1 != null) {
                newlyUserNum = Integer.parseInt(resultMap.get("userNum").toString());
            }

           /* QueryFilter filter1 = new QueryFilter(ReleaseRuleDetails.class);
            filter1.addFilter("customerId = ", customerId);
            ReleaseRuleDetails details = releaseRuleDetailsService.get(filter1);
            String startTime = "";
            if (details != null){
                lastMonthAsset  = details.getStartTeamAward();
                startTime = DateUtil.getFormatDateTime(details.getStartTime(),"yyyy-MM-dd") + " 00:00:00";
            }*/
            /*map.put("startTime", startTime);
            *//*重点记录：这块查的是团队所有人新增的业绩 不管团队下的人是否出局*//*
            DealRecord record = dealRecordService.findNewlyTeamAsset(map);
            if (record != null) {
                newlyMonthAsset = record.getTeamAsset();
            }*/
            /*map.put("dateType", "thisMonth");*/
            // 待优化sql--已优化
            DealRecord record = dealRecordService.findDateTeamAsset(map);
            newlyMonthAsset = record.getTeamAsset();

            /* map.put("dateType", "lastMonth");*/
            List<String> lastTime = getCurrentMonthTime(-1);
            map.put("startTime", lastTime.get(0));
            map.put("endTime", lastTime.get(1));
            DealRecord lastRecord = dealRecordService.findDateTeamAsset(map);
            lastMonthAsset = lastRecord.getTeamAsset();

            Map<String, Object> map1 = new HashMap<>();
            map1.put("customerId", customerId);
            int[] dealTypes = {1,2,3,4,9,10,11};
            map1.put("dealTypes", dealTypes);
            // map1.put("dateType", "lastMonth");
            map1.put("startTime", lastTime.get(0));
            map1.put("endTime", lastTime.get(1));
            // 个人获得的上个月累计奖励
            lastMonthAward  = dealRecordService.countDealMoney(map1);;
            // 个人获得的本月累计奖励
            //map1.put("dateType", "thisMonth");
            map1.put("startTime", time.get(0));
            map1.put("endTime", time.get(1));
            thisMonthAward  = dealRecordService.countDealMoney(map1);
        }
        obj.put("teamSort", teamSort);
        obj.put("teamName", teamName);
        obj.put("userNum", userNum);
        obj.put("teamAsset", teamAsset);
        obj.put("newlyUserNum", newlyUserNum);
        obj.put("lastMonthAsset", lastMonthAsset);
        obj.put("newlyMonthAsset", newlyMonthAsset);
        obj.put("lastMonthAward", lastMonthAward);
        obj.put("thisMonthAward", thisMonthAward);
        log.info("~~~~~Api我的团队方法: findMyTeam 执行时间时间 ：" + (System.currentTimeMillis()-s1) + "毫秒");
        return new JsonResult(true).setObj(obj);
    }

    @Override
    public JsonResult findRecommendUser(Long customerId) {
        JSONObject obj = new JSONObject();
        // 直推人数
        String oneCommendUser = "0";
        // 二代人数
        String twoCommendUser = "0";
        // 三代以上人数
        String threeCommendUser = "0";
        Map<String, String> map = new HashMap<>();
        map.put("customerId", customerId.toString());
        map.put("level", "1");
        Map<String,Object> oneCommend = dealRecordService.countTeamUser(map);
        if (oneCommend != null) {
            oneCommendUser = oneCommend.get("userNum").toString();
        }
        map.put("level", "2");
        Map<String,Object> twoCommend = dealRecordService.countTeamUser(map);
        if (twoCommend != null) {
            twoCommendUser = twoCommend.get("userNum").toString();
        }
        map.put("level", "3");
        Map<String,Object> threeCommend = dealRecordService.countTeamUser(map);
        if (twoCommend != null) {
            threeCommendUser = threeCommend.get("userNum").toString();
        }
        obj.put("oneCommendUser",oneCommendUser);
        obj.put("twoCommendUser",twoCommendUser);
        obj.put("threeCommendUser",threeCommendUser);
        return new JsonResult(true).setObj(obj);
    }

    @Override
    public FrontPage findOneCommendUserInfo(Map<String, String> map) {
        Page<CommendInfo> page = PageFactory.getPage(map);
        List<CommendInfo> list = dealRecordService.findCommendUserInfo(map);
        return new FrontPage(list, page.getTotal(), page.getPages(), page.getPageSize());
    }

    @Override
    public JsonResult findCountUserLevelNum(Long customerId) {
        JSONObject obj = new JSONObject();
        Map<String,String> map = new HashMap<>();
        map.put("customerId", customerId.toString());
        // 获取一级用户数
        map.put("sort", "1");
        Integer oneUserLevelNum = dealRecordService.findUserLevelNum(map);
        obj.put("oneUserLevelNum", oneUserLevelNum != null?oneUserLevelNum:0);
        // 获取二级用户数
        map.put("sort", "2");
        Integer twoUserLevelNum = dealRecordService.findUserLevelNum(map);
        obj.put("twoUserLevelNum", twoUserLevelNum != null?twoUserLevelNum:0);
        // 获取三级以上用户数
        map.put("sort", "3");
        Integer threeUserLevelNum = dealRecordService.findUserLevelNum(map);
        obj.put("threeUserLevelNum", threeUserLevelNum != null?threeUserLevelNum:0);
        return new JsonResult(true).setObj(obj);
    }

    /**
     * 申请社区共建奖励 (查看是否达到申请条件，申请、审核状态)
     * */
    @Override
    public JsonResult applyTeamAward(Long customerId) {
        JSONObject obj = new JSONObject();
        QueryFilter filter = new QueryFilter(CustomerLevel.class);
        filter.addFilter("customerId=",customerId);
        CustomerLevel level = customerLevelService.get(filter);
        if (level == null || level.getTeamSort() == 0) {
            return new JsonResult(false).setMsg("未到达等级申请要求").setCode("0001");
        }
        // applyStatus '申请步骤：1 填写社交账户 2 上传社交群图片 3申请完成'
        // auditStatus '审核状态：0 审核中 1 审核通过 2审核拒绝'
        // isTeamAward 0 关灯 1 开灯
        QueryFilter filter1 = new QueryFilter(ApplyTeamAward.class);
        filter1.addFilter("customerId=",customerId);
        ApplyTeamAward applyTeamAward = applyTeamAwardService.get(filter1);
        if (applyTeamAward == null || applyTeamAward.getApplyStatus() == 1) {
            obj.put("applyStatus","1");
        } else {
            obj.put("applyStatus",applyTeamAward.getApplyStatus());
            if (applyTeamAward.getApplyStatus() == 3) {
                obj.put("auditStatus", applyTeamAward.getAuditStatus());
                obj.put("auditExplain", applyTeamAward.getAuditExplain());
                if (applyTeamAward.getAuditStatus() == 1) {
                    obj.put("isTeamAward", level.getIsTeamAward());
                }
            }
        }
        return new JsonResult(true).setObj(obj);
    }

    @Override
    public JsonResult saveApplyTeamAward(Map<String, String> map) {
        String customerId = map.get("customerId");
        String applyType = map.get("applyType");
        QueryFilter filter = new QueryFilter(ApplyTeamAward.class);
        filter.addFilter("customerId=", customerId);
        ApplyTeamAward applyTeamAward = applyTeamAwardService.get(filter);
        if (applyTeamAward == null) {
            ApplyTeamAward apply = new ApplyTeamAward();
            apply.setCustomerId(Long.parseLong(customerId));
            apply.setApplyStatus(2);
            apply.setSocialType(Integer.parseInt(map.get("socialType")));
            apply.setSocialAccount(map.get("socialAccount"));
            applyTeamAwardService.save(apply);
        } else {
            if ("1".equals(applyType)) {
                applyTeamAward.setApplyStatus(2);
                applyTeamAward.setSocialType(Integer.parseInt(map.get("socialType")));
                applyTeamAward.setSocialAccount(map.get("socialAccount"));
            } else if ("2".equals(applyType)) {
                applyTeamAward.setApplyStatus(3);
                applyTeamAward.setSocialGroupImg(map.get("socialGroupImg"));
            } else if ("3".equals(applyType)){
                applyTeamAward.setApplyStatus(1);
                applyTeamAward.setAuditStatus(0);
            }
            applyTeamAwardService.update(applyTeamAward);
        }
        return new JsonResult(true).setMsg("tijiaochenggong");
    }
    @Override
    public JsonResult findEarningsTopTen() {
        List<Map<String, String>> list =  dealRecordService.findEarningsTopTen();
        return new JsonResult(true).setObj(list);
    }

    @Override
    public JsonResult myLevelInfo(Long customerId) {
        QueryFilter filter = new QueryFilter(CustomerLevel.class);
        filter.addFilter("customerId=", customerId);
        CustomerLevel customerLevel = customerLevelService.get(filter);
        QueryFilter outFilter = new QueryFilter(OutInfo.class);
        outFilter.addFilter("customerId=", customerId);
        List<OutInfo> list = outInfoService.find(outFilter);
        // 判断是否投资 1：投资 0 未投资
        Integer isInvested = list != null && list.size() > 0?1:0;
        if (isInvested == 1) {
            // 判断是否出局 2 : 出局
            outFilter.addFilter("status=", 0);
            List<OutInfo> outList = outInfoService.find(outFilter);
            if (outList == null || outList.size() <= 0) {
                isInvested = 2;
            }
        }
        if (customerLevel == null) {
            customerLevel = new CustomerLevel();
            customerLevel.setCustomerId(customerId);
        }
        customerLevel.setIsInvested(isInvested);
        return new JsonResult(true).setObj(customerLevel);
    }

    @Override
    public JsonResult tiBiCheck(Long customerId,String coinCode) {
        // 提币开始时间
        String extractStartTime = RedisStaticUtil.getAppConfigValue(RulesConfig.ExtractTimeKey, "extractStartTime");
        // 提币结束时间
        String extractEndTime = RedisStaticUtil.getAppConfigValue(RulesConfig.ExtractTimeKey, "extractEndTime");
        // 提币次数
        String extractNum = RedisStaticUtil.getAppConfigValue(RulesConfig.ExtractTimeKey, "extractNum");
        // 当前时间
        String nowDate = DateUtil.dateToString(new Date()).split(" ")[1];
        Boolean flag = hourMinuteBetween(nowDate, extractStartTime, extractEndTime);
        if (!flag) {
            return new JsonResult(false).setMsg("buzaitibishijiannei");
        }
        String startDate = DateUtil.dateToString(new Date()).split(" ")[0] + " 00:00:00";
        String endDate = DateUtil.dateToString(new Date()).split(" ")[0] + " 23:59:59";
        QueryFilter filter = new QueryFilter(DealRecord.class);
        filter.addFilter("customerId=",customerId);
        filter.addFilter("coinCode=", coinCode);
        filter.addFilter("dealType=", 14);
        filter.addFilter("dealStatus_in", "1,2");
        filter.addFilter("created >= ", startDate);
        filter.addFilter("created <= ", endDate);
        List<DealRecord> dealRecordList = dealRecordService.find(filter);
        int number = dealRecordList != null && dealRecordList.size() > 0? dealRecordList.size(): 0;
        if (number >= Integer.parseInt(extractNum)) {
            return new JsonResult(false).setMsg("tibicishuyidadaoshangxian");
        }
        return new JsonResult(true);
    }

    private Boolean hourMinuteBetween (String nowDate, String startDate, String endDate) {
        Boolean flag = false;
        try {
            SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
            Date now = format.parse(nowDate);
            Date start = format.parse(startDate);
            Date end = format.parse(endDate);
            long nowTime = now.getTime();
            long startTime = start.getTime();
            long endTime = end.getTime();
            flag = nowTime >= startTime && nowTime <= endTime;
        } catch (Exception e) {
            e.getLocalizedMessage();
            flag = false;
        }
        return flag;
    }

    @Override
    public JsonResult separateTable() {
        dealRecordService.separateTable();
        return new JsonResult(true);
    }

    /**
     * 获取指定月份的开始和结束时间
     * month 月份 -1 上一个月 0 当前月 1 下个月
     * */
    private List<String> getCurrentMonthTime(int month) {
        List<String> str = new ArrayList<>();
        Calendar c=Calendar.getInstance();
        c.add(Calendar.MONTH, month);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
        String gtimelast = sdf.format(c.getTime()); //上月
        int lastMonthMaxDay=c.getActualMaximum(Calendar.DAY_OF_MONTH);
        c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), lastMonthMaxDay, 23, 59, 59);

        //按格式输出
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-01  00:00:00");
        String gtime1 = sdf2.format(c.getTime()); //上月第一天
        str.add(gtime1);
        String gtime = sdf.format(c.getTime()); //上月最后一天
        str.add(gtime);
        return str;
    }
}
