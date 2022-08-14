package hry.app.ico;

import hry.app.jwt.TokenUtil;
import hry.bean.FrontPage;
import hry.bean.JsonResult;
import hry.ico.remote.RemoteIcoService;
import hry.ico.remote.model.RulesConfig;
import hry.manage.remote.model.User;
import hry.util.CheckPayPassword;
import hry.util.StringUtil;
import hry.util.common.SpringUtil;
import hry.util.date.DateUtil;
import hry.util.springmvcPropertyeditor.DateTimePropertyEditorSupport;
import hry.util.springmvcPropertyeditor.StringPropertyEditorSupport;
import io.swagger.annotations.*;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

@RestController
@RequestMapping(value = "/ico/user")
@RequiresAuthentication
@Api(value = "App操作类", description = "ico - 南权哲业务", tags = "ico - 南权哲业务")
public class AppICoController {
    /**
     * 注册类型属性编辑器
     *
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

    @Resource
    private RemoteIcoService remoteIcoService;


    @RequestMapping(value = "/getPlatformCurrencyInfo", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ApiOperation(value = "获取平台币可售信息", httpMethod = "POST", response = JsonResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
    @ResponseBody
    @RequiresAuthentication
    public JsonResult getPlatformCurrencyInfo(HttpServletRequest request) {
        User user = TokenUtil.getUser(request);
        if (user != null) {
            return   remoteIcoService.getPlatformCurrencyInfo();
        }
        return new JsonResult().setSuccess(false).setMsg(SpringUtil.diff("before_login"));
    }

    @RequestMapping(value = "/getPlatformRule", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ApiOperation(value = "平台配置", httpMethod = "POST", response = JsonResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
    @ResponseBody
    @RequiresAuthentication
    public JsonResult getPlatformRule(HttpServletRequest request) {
        User user = TokenUtil.getUser(request);
        if (user != null) {
            return   remoteIcoService.getPlatformRule(null);
        }
        return new JsonResult().setSuccess(false).setMsg(SpringUtil.diff("before_login"));
    }



    @RequestMapping(value = "/getIcoAccountInfo", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ApiOperation(value = "获取用户ICO账户信息", httpMethod = "POST", response = JsonResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
    @ResponseBody
    @RequiresAuthentication
    public JsonResult getIcoAccountInfo(HttpServletRequest request) {
        User user = TokenUtil.getUser(request);
        if (user != null) {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("customerId", user.getCustomerId().toString());
            return   remoteIcoService.getIcoAccountInfo(map);
        }
        return new JsonResult().setSuccess(false).setMsg(SpringUtil.diff("before_login"));
    }

    @RequestMapping(value = "/getIcoBuyOrderRecord", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ApiOperation(value = "最新购买平台记录", httpMethod = "POST", response = JsonResult.class,notes = "pageSize：页条数 page：当前页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
    @ResponseBody
    @RequiresAuthentication
    public FrontPage getIcoBuyOrderRecord(HttpServletRequest request,@ApiParam(name = "page", value = "当前页", required = true) @RequestParam Integer page,
                                          @ApiParam(name = "pageSize", value = "页条数", required = true) @RequestParam Integer pageSize) {
        User user = TokenUtil.getUser(request);
        if (user != null) {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("page", String.valueOf(page));
            map.put("pageSize",String.valueOf(pageSize));
            return   remoteIcoService.getIcoBuyOrderRecord(map);
        }
        return new FrontPage(null, 0, 1, 10);
    }

    @RequestMapping(value = "/getMyIcoBuyOrderRecord", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ApiOperation(value = "我的订单（购买平台记录）", httpMethod = "POST", response = JsonResult.class,notes = "pageSize：页条数 page：当前页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
    @ResponseBody
    @RequiresAuthentication
    public FrontPage getMyIcoBuyOrderRecord(HttpServletRequest request,@ApiParam(name = "page", value = "当前页", required = true) @RequestParam Integer page,
                                          @ApiParam(name = "pageSize", value = "页条数", required = true) @RequestParam Integer pageSize) {
        User user = TokenUtil.getUser(request);
        if (user != null) {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("customerId", user.getCustomerId().toString());
            map.put("page", String.valueOf(page));
            map.put("pageSize",String.valueOf(pageSize));
            return   remoteIcoService.getIcoBuyOrderRecord(map);
        }
        return new FrontPage(null, 0, 1, 10);
    }



    @RequestMapping(value = "/getMyRecommendationDetails", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ApiOperation(value = "我的推广明细（团队资产等）", httpMethod = "POST", response = JsonResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
    @ResponseBody
    @RequiresAuthentication
    public JsonResult getMyRecommendationDetails(HttpServletRequest request) {
        User user = TokenUtil.getUser(request);
        if (user != null) {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("customerId", user.getCustomerId().toString());
           return   remoteIcoService.getMyRecommendationDetails(map);
        }
        return new JsonResult().setSuccess(false).setMsg(SpringUtil.diff("before_login"));
    }

    @RequestMapping(value = "/getMyRecommendationList", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ApiOperation(value = "我的推荐列表", httpMethod = "POST", response = JsonResult.class,notes = "pageSize：页条数 page：当前页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
    @ResponseBody
    @RequiresAuthentication
    public FrontPage getMyRecommendationList(HttpServletRequest request,@ApiParam(name = "page", value = "当前页", required = true) @RequestParam Integer page,
                                            @ApiParam(name = "pageSize", value = "页条数", required = true) @RequestParam Integer pageSize) {
        User user = TokenUtil.getUser(request);
        if (user != null) {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("customerId", user.getCustomerId().toString());
            map.put("page", String.valueOf(page));
            map.put("pageSize",String.valueOf(pageSize));
            return   remoteIcoService.getMyRecommendationList(map);
        }
        return new FrontPage(null, 0, 1, 10);
    }

    @RequestMapping(value = "/getLockDetailed", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ApiOperation(value = "获取锁仓/释放(明细)", httpMethod = "POST", response = JsonResult.class, notes = "pageSize：页条数 page：当前页 state：状态(0.锁仓明细1.释放明细) starTime 开始时间 endTime 结束时间 monthTime yyyy-MM（月份查询）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
    @ResponseBody
    @RequiresAuthentication
    public FrontPage getLockDetailed(HttpServletRequest request,@ApiParam(name = "page", value = "当前页", required = true) @RequestParam Integer page,
                                          @ApiParam(name = "pageSize", value = "页条数", required = true) @RequestParam Integer pageSize,
                                     @ApiParam(name = "state", value = "状态", required = false) @RequestParam(required =  false) Integer state,
                                     @ApiParam(name = "starTime", value = "开始时间", required = false) @RequestParam(required = false) String starTime,
                                     @ApiParam(name = "endTime", value = "结束时间", required = false) @RequestParam(required = false) String endTime,
                                     @ApiParam(name = "monthTime", value = "月份日期（yyyy-MM）", required = false) @RequestParam(required = false) String monthTime) {
        User user = TokenUtil.getUser(request);
        if (user != null) {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("customerId", user.getCustomerId().toString());
            map.put("page", String.valueOf(page));
            if(state!=null){
                map.put("state", String.valueOf(state));//状态
            }
            map.put("pageSize",String.valueOf(pageSize));
            boolean fl=false;
            if(StringUtil.isNull(monthTime)){
                starTime=monthTime+"-01";
                endTime=DateUtil.addMonth(starTime,1,"yyyy-MM-dd");
                fl=true;
            }
            if(StringUtil.isNull(starTime)){
                map.put("starTime",starTime);
            }
            if(StringUtil.isNull(endTime)){
                if(!fl){
                    endTime= DateUtil.addDay(endTime,1,"yyyy-MM-dd");
                }
                map.put("endTime",endTime);
            }
            return   remoteIcoService.getLockDetailed(map);
        }
        return new FrontPage(null, 0, 1, 10);
    }
    @RequestMapping(value = "/getMyTransactionflow", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ApiOperation(value = "交易流水(账户明细)", httpMethod = "POST", response = JsonResult.class, notes = "pageSize：页条数 page：当前页 coinCode:币种Code type：类型(11.锁仓12.释放 13锁仓扣币21转账入22转账出31分红32推荐奖励 41.充币42.提币51.买入52.卖出) starTime 开始时间 endTime 结束时间 monthTime yyyy-MM（月份查询）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
    @ResponseBody
    @RequiresAuthentication
    public FrontPage getMyTransactionflow(HttpServletRequest request,@ApiParam(name = "page", value = "当前页", required = true) @RequestParam Integer page,
                                     @ApiParam(name = "pageSize", value = "页条数", required = true) @RequestParam Integer pageSize,
                                     @ApiParam(name = "type", value = "类型", required = false) @RequestParam(required = false) Integer type,
                                     @ApiParam(name = "coinCode", value = "币种Code", required = false) @RequestParam(required = false) String coinCode,
                                     @ApiParam(name = "starTime", value = "开始时间", required = false) @RequestParam(required = false) String starTime,
                                     @ApiParam(name = "endTime", value = "结束时间", required = false) @RequestParam(required = false) String endTime,
                                     @ApiParam(name = "minNum", value = "最低数量", required = false) @RequestParam(required = false) String minNum,
                                     @ApiParam(name = "maxNum", value = "最大数量", required = false) @RequestParam(required = false) String maxNum,
                                     @ApiParam(name = "monthTime", value = "月份日期（yyyy-MM）", required = false) @RequestParam(required = false) String monthTime) {
        User user = TokenUtil.getUser(request);
        if (user != null) {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("customerId", user.getCustomerId().toString());
            map.put("page", String.valueOf(page));
            map.put("pageSize",String.valueOf(pageSize));
            boolean fl=false;
            if(StringUtil.isNull(monthTime)){
                starTime=monthTime+"-01";
                endTime=DateUtil.addMonth(starTime,1,"yyyy-MM-dd");
                fl=true;
            }
            if(StringUtil.isNull(starTime)){
                map.put("starTime",starTime);
            }
            if(StringUtil.isNull(endTime)){
                if(!fl){
                    endTime= DateUtil.addDay(endTime,1,"yyyy-MM-dd");
                }
                map.put("endTime",endTime);
            }
            if(type!=null){
                 map.put("type", String.valueOf(type));//状态
            }
            if(StringUtil.isNull(coinCode)){
                map.put("coinCode", coinCode);//币种code
            }
            if(StringUtil.isNull(minNum)){
                map.put("minNum", minNum);//最小值
            }
            if(StringUtil.isNull(maxNum)){
                map.put("maxNum", maxNum);//最大值
            }
            return   remoteIcoService.getMyTransactionflow(map);
        }
        return new FrontPage(null, 0, 1, 10);
    }

    @RequestMapping(value = "/getTransactionDetail", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ApiOperation(value = "账单明细", httpMethod = "POST", response = JsonResult.class, notes = "transactionId：交易流水Id")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
    @ResponseBody
    @RequiresAuthentication
    public JsonResult getTransactionDetail(HttpServletRequest request, @ApiParam(name = "transactionId", value = "交易流水Id", required = true) @RequestParam String transactionId) {
        User user = TokenUtil.getUser(request);
        if (user != null) {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("transactionId", transactionId);
            map.put("customerId", user.getCustomerId().toString());
            return   remoteIcoService.getTransactionDetail(map);
        }
        return new JsonResult().setSuccess(false).setMsg(SpringUtil.diff("before_login"));
    }

    @RequestMapping(value = "/calculatePaymentAmount", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ApiOperation(value = "计算购买平台币数量", httpMethod = "POST", response = JsonResult.class, notes = "number：购买数量")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
    @ResponseBody
    @RequiresAuthentication
    public JsonResult calculatePaymentAmount(HttpServletRequest request, @ApiParam(name = "number", value = "购买数量", required = true) @RequestParam String number) {
        User user = TokenUtil.getUser(request);
        if (user != null) {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("number", number);
            return   remoteIcoService.calculatePaymentAmount(map);
        }
        return new JsonResult().setSuccess(false).setMsg(SpringUtil.diff("before_login"));
    }

    @RequestMapping(value = "/purchasePlatformAccount", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ApiOperation(value = "购买平台币操作", httpMethod = "POST", response = JsonResult.class, notes = "number：购买数量 password:支付密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
    @ResponseBody
    @RequiresAuthentication
    public JsonResult purchasePlatformAccount(HttpServletRequest request, @ApiParam(name = "number", value = "购买数量", required = true) @RequestParam(required = true) String number,
                                              @ApiParam(name = "password", value = "支付密码", required = true) @RequestParam String password) {
        User user = TokenUtil.getUser(request);
        if (user != null) {
            //  校验支付密码
            boolean check= CheckPayPassword.checkPayPassword(password,user);
            if(!check){
                return new JsonResult().setSuccess(false).setMsg(SpringUtil.diff("mimacuowu"));
            }
            if(new BigDecimal(number).compareTo(BigDecimal.ONE)<0){
                return new JsonResult().setSuccess(false).setMsg("购买数量不正确");
            }
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("number", number);
            map.put("customerId", user.getCustomerId().toString());
            JsonResult jsonResult=remoteIcoService.purchasePlatformAccount(map);
            return  jsonResult;
        }
        return new JsonResult().setSuccess(false).setMsg(SpringUtil.diff("before_login"));
    }

    @RequestMapping(value = "/getLockDeductionInfo", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ApiOperation(value = "获取锁仓扣除经验值/itx币  [优先扣经验，经验不足扣币]", httpMethod = "POST", response = JsonResult.class, notes = "无")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
    @ResponseBody
    @RequiresAuthentication
    public JsonResult getLockDeductionInfo(HttpServletRequest request) {
        User user = TokenUtil.getUser(request);
        if (user != null) {
            JsonResult jsonResult=remoteIcoService.getLockDeductionInfo(user.getCustomerId());
            return  jsonResult;
        }
        return new JsonResult().setSuccess(false).setMsg(SpringUtil.diff("before_login"));
    }

    @RequestMapping(value = "/toLockDepot", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ApiOperation(value = "锁仓操作", httpMethod = "POST", response = JsonResult.class, notes = "lockDay:锁仓天数  number：锁仓数量 password:支付密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
    @ResponseBody
    @RequiresAuthentication
    public JsonResult toLockDepot(HttpServletRequest request, @ApiParam(name = "number", value = "购买数量", required = true) @RequestParam String number,
                                  @ApiParam(name = "password", value = "支付密码", required = true) @RequestParam String password,
                                  @ApiParam(name = "lockDay", value = "锁仓天数", required = true) @RequestParam String lockDay) {
        User user = TokenUtil.getUser(request);
        if (user != null) {
            //  校验支付密码
            boolean check= CheckPayPassword.checkPayPassword(password,user);
            if(!check){
                return new JsonResult().setSuccess(false).setMsg(SpringUtil.diff("mimacuowu"));
            }
            int day=Integer.parseInt(lockDay);
            if(day<0||day>365){
                return new JsonResult().setSuccess(false).setMsg("参数错误");
            }
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("number", number);
            map.put("lockDay", lockDay);
            map.put("customerId", user.getCustomerId().toString());
            JsonResult jsonResult=remoteIcoService.toLockDepot(map);
            return  jsonResult;
        }
        return new JsonResult().setSuccess(false).setMsg(SpringUtil.diff("before_login"));
    }

    @RequestMapping(value = "/appendLockDepot", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ApiOperation(value = "追加锁仓天数操作", httpMethod = "POST", response = JsonResult.class, notes = "lockId:锁仓Id  addLockDay：追加锁仓天数 password:支付密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
    @ResponseBody
    @RequiresAuthentication
    public JsonResult appendLockDepot(HttpServletRequest request, @ApiParam(name = "lockId", value = "锁仓Id", required = true) @RequestParam String lockId,
                                  @ApiParam(name = "password", value = "支付密码", required = true) @RequestParam String password,
                                  @ApiParam(name = "addLockDay", value = "追加锁仓天数", required = true) @RequestParam String addLockDay) {
        User user = TokenUtil.getUser(request);
        if (user != null) {
            //  校验支付密码
            boolean check= CheckPayPassword.checkPayPassword(password,user);
            if(!check){
                return new JsonResult().setSuccess(false).setMsg(SpringUtil.diff("mimacuowu"));
            }
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("lockId", lockId);
            map.put("addLockDay", addLockDay);
            map.put("customerId", user.getCustomerId().toString());
            JsonResult jsonResult=remoteIcoService.appendLockDepot(map);
            return  jsonResult;
        }
        return new JsonResult().setSuccess(false).setMsg(SpringUtil.diff("before_login"));
    }
    @RequestMapping(value = "/getReleaseDeductionInfo", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ApiOperation(value = "获取释放扣除经验/不足不可操作", httpMethod = "POST", response = JsonResult.class, notes = "lockId:锁仓Id")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
    @ResponseBody
    @RequiresAuthentication
    public JsonResult getReleaseDeductionInfo(HttpServletRequest request,
                                              @ApiParam(name = "lockId", value = "锁仓Id", required = true) @RequestParam(required = true) String lockId) {
        User user = TokenUtil.getUser(request);
        if (user != null) {
            HashMap<String,String> hashMap=new HashMap<>();
            hashMap.put("customerId",user.getCustomerId().toString());
            hashMap.put("lockId",lockId);
            JsonResult jsonResult=remoteIcoService.getReleaseDeductionInfo(hashMap);
            return  jsonResult;
        }
        return new JsonResult().setSuccess(false).setMsg(SpringUtil.diff("before_login"));
    }

    @RequestMapping(value = "/releaseOperation", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ApiOperation(value = "释放操作", httpMethod = "POST", response = JsonResult.class, notes = "lockId:锁仓Id  password:支付密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
    @ResponseBody
    @RequiresAuthentication
    public JsonResult toLockDepot(HttpServletRequest request,@ApiParam(name = "password", value = "支付密码", required = true) @RequestParam String password,
                                  @ApiParam(name = "lockId", value = "锁仓Id", required = true) @RequestParam String lockId) {
        User user = TokenUtil.getUser(request);
        if (user != null) {
            //  校验支付密码
            boolean check= CheckPayPassword.checkPayPassword(password,user);
            if(!check){
                return new JsonResult().setSuccess(false).setMsg(SpringUtil.diff("mimacuowu"));
            }
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("lockId", lockId);
            map.put("customerId", user.getCustomerId().toString());
            JsonResult jsonResult=remoteIcoService.releaseOperation(map);
            return  jsonResult;
        }
        return new JsonResult().setSuccess(false).setMsg(SpringUtil.diff("before_login"));
    }

    @RequestMapping(value = "/transferAccounts", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ApiOperation(value = "转账操作", httpMethod = "POST", response = JsonResult.class, notes = "toPublickKey:收入方的币种地址 coinCode:转账币种  number：转账的数量 password:支付密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
    @ResponseBody
    @RequiresAuthentication
    public JsonResult transferAccounts(HttpServletRequest request, @ApiParam(name = "number", value = "转账的数量", required = true) @RequestParam String number,
                                  @ApiParam(name = "password", value = "支付密码", required = true) @RequestParam String password,
                                  @ApiParam(name = "coinCode", value = "转账币种", required = true) @RequestParam String coinCode,
                                       @ApiParam(name = "toPublickKey", value = "收入方的币种地址", required = true) @RequestParam String toPublickKey) {
        User user = TokenUtil.getUser(request);
        if (user != null) {
            //  校验支付密码
            boolean check= CheckPayPassword.checkPayPassword(password,user);
            if(!check){
                return new JsonResult().setSuccess(false).setMsg(SpringUtil.diff("mimacuowu"));
            }
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("number", number);//转账的数量
            map.put("coinCode", coinCode);//转账的币种
            map.put("toPublickKey", toPublickKey);//收入方的币种地址
            map.put("customerId", user.getCustomerId().toString());
            JsonResult jsonResult=remoteIcoService.transferAccounts(map);
            return  jsonResult;
        }
        return new JsonResult().setSuccess(false).setMsg(SpringUtil.diff("before_login"));
    }

    @RequestMapping(value = "/queryExperience", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ApiOperation(value = "用户经验等级", httpMethod = "POST", response = JsonResult.class, notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
    @ResponseBody
    @RequiresAuthentication
    public JsonResult queryExperience(HttpServletRequest request) {
        User user = TokenUtil.getUser(request);
        if (user != null) {
            return   remoteIcoService.seeCustomerLevelAccount(user.getCustomerId());
        }
        return new JsonResult().setSuccess(false).setMsg(SpringUtil.diff("before_login"));
    }

    @RequestMapping(value = "/queryExperiencesDetail", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ApiOperation(value = "经验明细", httpMethod = "POST", response = JsonResult.class, notes = "pageSize：页条数. page：当前页（1至N）. date：时间(格式为 2019-01 ) . type ：交易类型（0101 锁仓奖励，0102 注册赠送，0201 锁仓扣除，0202 释放扣除）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
    @ResponseBody
    @RequiresAuthentication
    public FrontPage queryExperiencesDetail(HttpServletRequest request,@ApiParam(name = "page", value = "当前页", required = true) @RequestParam Integer page,
                                     @ApiParam(name = "pageSize", value = "页条数", required = true) @RequestParam Integer pageSize,
                                     @ApiParam(name = "date", value = "时间", required = true) @RequestParam String date,
                                     @ApiParam(name = "type", value = "交易类型", required = false) @RequestParam(required = false) String type) {
        User user = TokenUtil.getUser(request);
        if (user != null) {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("customerId", user.getCustomerId().toString());
            map.put("page", String.valueOf(page));
            map.put("pageSize",String.valueOf(pageSize));
            map.put("date", date);//时间
            map.put("type", type);//交易类型

            return   remoteIcoService.queryExperiencesDetail(map);
        }
        return new FrontPage(null, 0, 1, 10);
    }

    @RequestMapping(value = "/queryDividendRecord", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ApiOperation(value = "分红记录", httpMethod = "POST", response = JsonResult.class, notes = "pageSize：页条数. page：当前页（1至N）. startTime：开始时间(格式为 2019-01-01 ) . endTime：结束时间(格式为 2019-01-01 ). coinCode ：币种名称")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
    @ResponseBody
    @RequiresAuthentication
    public FrontPage queryDividendRecord(HttpServletRequest request,@ApiParam(name = "page", value = "当前页", required = true) @RequestParam Integer page,
                                         @ApiParam(name = "pageSize", value = "页条数", required = true) @RequestParam Integer pageSize,
                                         @ApiParam(name = "startTime", value = "开始时间", required = false) @RequestParam(required = false) String startTime,
                                         @ApiParam(name = "endTime", value = "结束时间", required = false) @RequestParam(required = false) String endTime,
                                         @ApiParam(name = "coinCode", value = "币种名称", required = false) @RequestParam(required = false) String coinCode) {
        User user = TokenUtil.getUser(request);
        if (user != null) {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("customerId", user.getCustomerId().toString());
            map.put("page", String.valueOf(page));
            map.put("pageSize",String.valueOf(pageSize));
            if(StringUtil.isNull(coinCode)){
                map.put("coinCode", coinCode);//币种code
            }
            if(StringUtil.isNull(startTime)){
                map.put("startTime", startTime);//开始时间
            }
            if(StringUtil.isNull(endTime)){
                map.put("endTime", endTime);//结束时间
            }
            return   remoteIcoService.queryDividendRecord(map);
        }
        return new FrontPage(null, 0, 1, 10);
    }


    @RequestMapping(value = "/predictUserLevel", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ApiOperation(value = "预计用户晋升等级", httpMethod = "POST", response = JsonResult.class, notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
    @ResponseBody
    @RequiresAuthentication
    public JsonResult predictUserLevel(HttpServletRequest request) {
        User user = TokenUtil.getUser(request);
        if (user != null) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("customerId", user.getCustomerId().toString());
            return   remoteIcoService.predictUserLevel(map);
        }
        return new JsonResult().setSuccess(false).setMsg(SpringUtil.diff("before_login"));
    }


}
