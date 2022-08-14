package hry.lcqb.record.controller;

import hry.app.jwt.TokenUtil;
import hry.app.remote.RemoteUserService;
import hry.bean.ApiJsonResult;
import hry.bean.FrontPage;
import hry.bean.JsonResult;
import hry.licqb.remote.RemoteAwardService;
import hry.licqb.remote.RemoteExchangeService;
import hry.licqb.remote.RemoteLevelService;
import hry.licqb.remote.RemoteRecordService;
import hry.manage.remote.model.User;
import hry.util.StringUtil;
import hry.util.common.SpringUtil;
import hry.util.springmvcPropertyeditor.DateTimePropertyEditorSupport;
import hry.util.springmvcPropertyeditor.StringPropertyEditorSupport;
import io.swagger.annotations.*;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * @author zhouming
 * @date 2019/8/29 10:33
 * @Version 1.0
 */
@Controller
@RequestMapping("/record")
@Api(value = "李超钱包---记录相关接口", description = "李超钱包---记录相关接口", tags = "李超钱包---记录相关接口")
public class RecordController {
    /**
     * 注册类型属性编辑器
     * @param binder
     */
    @InitBinder
    public void initBinder(ServletRequestDataBinder binder) {
        // 系统注入的只能是基本类型，如int，char，String
        /**
         * 自动转换日期类型的字段格式
         */
        binder.registerCustomEditor(Date.class, new DateTimePropertyEditorSupport());
        /**
         * 防止XSS攻击，并且带去左右空格功能
         */
        binder.registerCustomEditor(String.class, new StringPropertyEditorSupport(true, false));
    }

    /**
     * 我的社区信息
     * */
    @ApiOperation(value = "我的社区相关记录统计", httpMethod = "POST", response = ApiJsonResult.class, notes = "我的社区相关记录统计")
    @RequestMapping("/user/findMyTeam")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
    @RequiresAuthentication
    public JsonResult findMyTeam(HttpServletRequest request) {
        User user = TokenUtil.getUser(request);
        if (user != null) {
            RemoteRecordService remoteRecordService = SpringUtil.getBean("remoteRecordService");
            JsonResult result = remoteRecordService.findMyTeam(user.getCustomerId());
        return result;
        } else {
            return new JsonResult().setSuccess(false).setMsg(SpringUtil.diff("before_login"));
        }
    }
    /**
     * 我的社区人数统计
     * */
    @ApiOperation(value = "社区人数统计", httpMethod = "POST", response = ApiJsonResult.class, notes = "社区人数统计")
    @RequestMapping("/user/findRecommendUser")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
    @RequiresAuthentication
    public JsonResult findRecommendUser(HttpServletRequest request) {
        User user = TokenUtil.getUser(request);
        if (user != null) {
            RemoteRecordService remoteRecordService = SpringUtil.getBean("remoteRecordService");
            JsonResult result = remoteRecordService.findRecommendUser(user.getCustomerId());
            return result;
        } else {
            return new JsonResult().setSuccess(false).setMsg(SpringUtil.diff("before_login"));
        }
    }

    /**
     * 获取用户的直推用户信息
     * */
    @ApiOperation(value = "直推用户信息", httpMethod = "POST", response = ApiJsonResult.class, notes = "直推用户信息")
    @RequestMapping("/user/findOneCommendUser")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
    @RequiresAuthentication
    public FrontPage findOneCommendUser(HttpServletRequest request,
                                        @ApiParam(name = "level", value = "层级数", required = true) @RequestParam("level") String level,
                                        @ApiParam(name = "limit", value = "每页条数", required = true) @RequestParam("limit") String limit,
                                        @ApiParam(name = "offset", value = "(页码-1)*每页条数,如第1页，每次10条侧传(1-1)*10=0", required = true) @RequestParam("offset") String offset) {
        User user = TokenUtil.getUser(request);
        if (user == null) {
            return null;
        }
        RemoteRecordService remoteRecordService = SpringUtil.getBean("remoteRecordService");
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("limit", limit);
        paramMap.put("offset", offset);
        paramMap.put("level", level);
        paramMap.put("customerId", user.getCustomerId().toString());
        return remoteRecordService.findOneCommendUserInfo(paramMap);
    }

    /**
     * 更具用户Id查询用户下对应等级用户数量
     * */
    @ApiOperation(value = "用户伞下对应等级数", httpMethod = "POST", response = ApiJsonResult.class, notes = "用户伞下对应等级数")
    @RequestMapping("/user/findCountUserLevelNum")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
    @RequiresAuthentication
    public JsonResult findCountUserLevelNum(HttpServletRequest request,
                                            @ApiParam(name = "customerId", value = "用户id", required = true) @RequestParam("customerId") String customerId){
        User user = TokenUtil.getUser(request);
        if (user != null) {
            RemoteRecordService remoteRecordService = SpringUtil.getBean("remoteRecordService");
            JsonResult result = remoteRecordService.findCountUserLevelNum(Long.parseLong(customerId));
            return result;
        } else {
            return new JsonResult().setSuccess(false).setMsg(SpringUtil.diff("before_login"));
        }
    }

    /**
     * 申请共建社区奖励条件/状态
     * */
    @ApiOperation(value = "申请共建社区奖励条件/状态", httpMethod = "POST", response = ApiJsonResult.class, notes = "申请共建社区奖励条件/状态")
    @RequestMapping("/user/applyTeamAward")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
    @RequiresAuthentication
    public JsonResult applyTeamAward(HttpServletRequest request){
        User user = TokenUtil.getUser(request);
        if (user != null) {
            RemoteRecordService remoteRecordService = SpringUtil.getBean("remoteRecordService");
            return remoteRecordService.applyTeamAward(user.getCustomerId());
        } else {
            return new JsonResult().setSuccess(false).setMsg(SpringUtil.diff("before_login"));
        }
    }
    /**
     * 保存共建社区奖励申请
     * */
    @ApiOperation(value = "保存共建社区奖励申请", httpMethod = "POST", response = ApiJsonResult.class, notes = "保存共建社区奖励申请")
    @RequestMapping("/user/saveApplyTeamAward")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
    @RequiresAuthentication
    public JsonResult saveApplyTeamAward(HttpServletRequest request,
                                         @ApiParam(name = "socialType", value = "社交方式 1 QQ 2 微信 3 facebook", required = false) @RequestParam("socialType") String socialType,
                                         @ApiParam(name = "socialAccount", value = "社交账号", required = false) @RequestParam("socialAccount") String socialAccount,
                                         @ApiParam(name = "socialGroupImg", value = "社交图片", required = false) @RequestParam("socialGroupImg") String socialGroupImg,
                                         @ApiParam(name = "applyType", value = "申请步骤 1: 填写社交账号 2:上传社交图片 3:再次申请", required = true) @RequestParam("applyType") String applyType) {
        User user = TokenUtil.getUser(request);
        if (user != null) {
            RemoteRecordService remoteRecordService = SpringUtil.getBean("remoteRecordService");
            Map<String, String> paramMap = new HashMap<>();
            if ("1".equals(applyType)) {
                if (!StringUtil.isNull(socialType)){
                    return new JsonResult().setSuccess(false).setMsg(SpringUtil.diff("shejiaofangshibunengweikong"));
                }
                if (!StringUtil.isNull(socialAccount)){
                    return new JsonResult().setSuccess(false).setMsg(SpringUtil.diff("shejiaozhanghaobunengweikong"));
                }
                paramMap.put("socialType",socialType);
                paramMap.put("socialAccount",socialAccount);
            } else if ("2".equals(applyType)) {
                if (!StringUtil.isNull(socialGroupImg)){
                    return new JsonResult().setSuccess(false).setMsg(SpringUtil.diff("shejiaotupianbunengweikong"));
                }
                paramMap.put("socialGroupImg",socialGroupImg);
            }
            paramMap.put("applyType",applyType);
            paramMap.put("customerId",user.getCustomerId().toString());
            return remoteRecordService.saveApplyTeamAward(paramMap);
        } else {
            return new JsonResult().setSuccess(false).setMsg(SpringUtil.diff("before_login"));
        }
    }

    /**
     * 用户收益排行榜
     * */
    @ApiOperation(value = "用户收益排行榜", httpMethod = "POST", response = ApiJsonResult.class, notes = "用户收益排行榜")
    @RequestMapping("/findEarningsTopTen")
    @ResponseBody
    public JsonResult findEarningsTopTen() {
        RemoteRecordService remoteRecordService = SpringUtil.getBean("remoteRecordService");
        return remoteRecordService.findEarningsTopTen();
    }

    /**
     * 获取用户等级相关信息
     * */
    @ApiOperation(value = "获取用户等级相关信息", httpMethod = "POST", response = ApiJsonResult.class, notes = "获取用户等级相关信息")
    @RequestMapping("/user/myLevelInfo")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
    @RequiresAuthentication
    public JsonResult myLevelInfo(HttpServletRequest request) {
        User user = TokenUtil.getUser(request);
        RemoteRecordService remoteRecordService = SpringUtil.getBean("remoteRecordService");
        return remoteRecordService.myLevelInfo(user.getCustomerId());
    }

    /**
     * 测试奖励
     * */
    @ApiOperation(value = "测试奖励", httpMethod = "POST", response = ApiJsonResult.class, notes = "手动测试奖励")
    @RequestMapping("/handleAward")
    @ResponseBody
    public JsonResult handleAward(HttpServletRequest request,
                                  @ApiParam(name = "testType",
                                  value = "测试类型 1 静态奖励; 2 见点奖; 3 管理奖" +
                                          "4 级别奖; 5 周释放; 6 月释放; 7 年释放 8 释放周期(亮灯规则)",
                                  required = false) @RequestParam("testType") String testType,
                                  @ApiParam(name = "customerId", value = "用户ID（非必填）", required = false) @RequestParam("customerId") String customerId,
                                  @ApiParam(name = "safetyCode", value = "安全校验码", required = true) @RequestParam("safetyCode") String safetyCode,
                                  @ApiParam(name = "accountType", value = "账户类型 1、热账户 2 冷账户", required = false) @RequestParam("accountType") String accountType,
                                  @ApiParam(name = "hotmoney", value = "数量", required = false) @RequestParam("hotmoney") String hotmoney,
                                  @ApiParam(name = "plusorminus", value = "加or减", required = false) @RequestParam("plusorminus") String plusorminus,
                                  @ApiParam(name = "transactionNum", value = "流水号", required = false) @RequestParam("transactionNum") String transactionNum,
                                  @ApiParam(name = "coinCode", value = "流水号", required = false) @RequestParam("coinCode") String coinCode) {
        if (!"224BAAAEE725034CA5960F62F957FAA7".equals(safetyCode)) {
            return new JsonResult(false);
        }
        RemoteAwardService remoteAwardService = hry.util.SpringUtil.getBean("remoteAwardService");
        RemoteLevelService remoteLevelService = hry.util.SpringUtil.getBean("remoteLevelService");
        RemoteExchangeService remoteExchangeService = hry.util.SpringUtil.getBean("remoteExchangeService");
        String testName = "";
        switch (testType) {
            case "1":
                testName = "静态奖励";
                // 静态奖励
                remoteAwardService.giveOutStaticAward();
                break;
            case "2":
                testName = "见点奖";
                // 见点奖
                remoteAwardService.giveOutSeeAward();
                break;
            case "3":
                testName = "管理奖";
                // 管理奖
                remoteAwardService.giveOutManageAward();
                break;
            case "31":
                testName = "指定用户管理奖";
                // 管理奖
                remoteAwardService.giveOutManageAward(customerId);
                break;
            case "4":
                testName = "级别奖";
                // 级别奖
                remoteAwardService.giveOutGradeAward();
                break;
            case "5":
                testName = "周释放";
                // 周释放
                remoteAwardService.giveOutWeekTeamAward();
                break;
            case "51":
                testName = "指定用户周释放";
                // 周释放
                remoteAwardService.giveOutWeekTeamAwardTwo(customerId);
                break;
            case "6":
                testName = "月释放";
                // 月释放
                remoteAwardService.giveOutMonthTeamAward();
                break;
            case "7":
                testName = "年释放";
                // 年释放
                remoteAwardService.giveOutYearTeamAward();
                break;
            case "8":
                testName = "释放周期(亮灯规则)";
                // 更新释放周期
                remoteAwardService.giveOutTeamAwardRule();
                break;
            case "19":
                testName = "个人等级升级";
                try {
                    remoteLevelService.upgradeUserGrade(Long.parseLong(customerId));
                } catch (Exception e) {
                    e.getLocalizedMessage();
                }
                break;
            case "29":
                testName = "团队等级升级";
                try {
                    remoteLevelService.upgradeUserTeamGrade(Long.parseLong(customerId));
                } catch (Exception e) {
                    e.getLocalizedMessage();
                }
                break;
            case "99":
                try {
                    remoteLevelService.customerOut(Long.parseLong(customerId));
                } catch (Exception e) {
                    e.getLocalizedMessage();
                }
                break;
            case "199":
                try {
                    remoteAwardService.statsPlatformTotal();
                } catch (Exception e) {
                    e.getLocalizedMessage();
                }
                break;
            case "299":
                try {
                    remoteAwardService.payAndExtractTotal();
                } catch (Exception e) {
                    e.getLocalizedMessage();
                }
                break;
            case "399":
                try {
                    remoteExchangeService.payAndExtractExchangeTotal();
                } catch (Exception e) {
                    e.getLocalizedMessage();
                }
                break;
            case "999":
                try {
                    Map<String, Object> map = new HashMap<>();
                    map.put("customerId", customerId);
                    map.put("coinCode", coinCode);
                    map.put("accountType", accountType);
                    map.put("plusorminus", plusorminus);
                    map.put("hotmoney", hotmoney);
                    map.put("transactionNum", transactionNum);
                    remoteLevelService.changeAccount(map);
                } catch (Exception e) {
                    e.getLocalizedMessage();
                }
                break;
            case "998":
                try {
                    remoteLevelService.findPingzhang();
                } catch (Exception e) {
                    e.getLocalizedMessage();
                }
                break;
            case "997":
                try {
                    remoteLevelService.usdtPingzhang();
                } catch (Exception e) {
                    e.getLocalizedMessage();
                }
                break;
            case "996":
                try {
                    remoteLevelService.levelPingZhang();
                } catch (Exception e) {
                    e.getLocalizedMessage();
                }
                break;
            case "995":
                try {
                    remoteLevelService.coldBusinessPingZhang();
                } catch (Exception e) {
                    e.getLocalizedMessage();
                }
                break;
            case "994":
                try {
                    remoteLevelService.exchangeColdPingZhang();
                } catch (Exception e) {
                    e.getLocalizedMessage();
                }
                break;
            case "993":
                try {
                    remoteLevelService.exchangeColdPingZhangTwo();
                } catch (Exception e) {
                    e.getLocalizedMessage();
                }
                break;
            case "888":
                try {
                    remoteAwardService.surplusUSDTExchange();
                } catch (Exception e) {
                    e.getLocalizedMessage();
                }
                break;
            case "887":
                try {
                    Map<String, String> map = new HashMap<>();
                    map.put("exchangeCode", coinCode);
                    remoteAwardService.surplusBBGOExchange(map);
                } catch (Exception e) {
                    e.getLocalizedMessage();
                }
                break;
            case "886":
                try {
                    remoteAwardService.surplusUSDTExchange(customerId);
                } catch (Exception e) {
                    e.getLocalizedMessage();
                }
                break;
            case "885":
                try {
                    remoteAwardService.surplusBBGOExchange(customerId, coinCode);
                } catch (Exception e) {
                    e.getLocalizedMessage();
                }
                break;
            case "788":
                try {
                    remoteAwardService.surplusUSDTExchangeTwo();
                } catch (Exception e) {
                    e.getLocalizedMessage();
                }
                break;
            case "787":
                try {
                    Map<String, String> map = new HashMap<>();
                    map.put("exchangeCode", coinCode);
                    remoteAwardService.surplusBBGOExchangeTwo(map);
                } catch (Exception e) {
                    e.getLocalizedMessage();
                }
                break;
            case "786":
                try {
                    remoteAwardService.surplusUSDTExchangeTwo(customerId);
                } catch (Exception e) {
                    e.getLocalizedMessage();
                }
                break;
            case "785":
                try {
                    remoteAwardService.surplusBBGOExchangeTwo(customerId, coinCode);
                } catch (Exception e) {
                    e.getLocalizedMessage();
                }
                break;
            default:
                return new JsonResult("输入正确的测试类型");

        }
        // 抓取手动充币记录
        //remoteAwardService.saveChargeRecord();
        //RemoteUserService remoteUserService = hry.util.SpringUtil.getBean("remoteUserService");
        //remoteUserService.addNumber();
        //RemoteRecordService remoteRecordService = SpringUtil.getBean("remoteRecordService");
        //JsonResult result = remoteRecordService.tiBiCheck(Long.parseLong("100003"),"USDT");
        return new JsonResult(true).setMsg(testName+"测试成功");
    }



}
