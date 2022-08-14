package hry.lcqb.asset.controller;

import com.alibaba.fastjson.JSON;
import hry.app.jwt.TokenUtil;
import hry.bean.ApiJsonResult;
import hry.bean.FrontPage;
import hry.bean.JsonResult;
import hry.core.shiro.PasswordHelper;
import hry.licqb.remote.RemoteAssetService;
import hry.manage.remote.model.User;
import hry.util.common.SpringUtil;
import hry.util.springmvcPropertyeditor.DateTimePropertyEditorSupport;
import hry.util.springmvcPropertyeditor.StringPropertyEditorSupport;
import io.swagger.annotations.*;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.nutz.http.Http;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhouming
 * @date 2019/8/30 14:15
 * @Version 1.0
 */
@Controller
@RequestMapping("/asset")
@Api(value = "李超钱包---资产相关接口", description = "李超钱包---资产相关接口", tags = "李超钱包---资产相关接口")
public class AssetController {
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

    @ApiOperation(value = "我的资产", httpMethod = "POST", response = ApiJsonResult.class, notes = "我的资产")
    @PostMapping("/user/myAccount")
    @ResponseBody
    @RequiresAuthentication //是否必须传token
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header")
    })
    public JsonResult myAccount(HttpServletRequest request) {
        User user = TokenUtil.getUser(request);
        if (user != null) {
            Map<String,String> params = new HashMap<>();
            params.put("customerId", user.getCustomerId().toString());
            RemoteAssetService remoteAssetService = SpringUtil.getBean("remoteAssetService");
            return remoteAssetService.myAccount(params);
        } else {
            return new JsonResult().setSuccess(false).setMsg(SpringUtil.diff("before_login"));
        }
    }

    /**
     * 获取平台配置信息
     * */
    @ApiOperation(value = "获取平台配置信息", httpMethod = "POST", response = ApiJsonResult.class, notes = "获取平台配置信息")
    @PostMapping("/getPlatformConfig")
    @ResponseBody
    public JsonResult getPlatformConfig(HttpServletRequest request) {
        RemoteAssetService remoteAssetService = SpringUtil.getBean("remoteAssetService");
        return remoteAssetService.getPlatformConfig();
    }

    /**
     * 根据code获取个人币种信息
     * */
    @ApiOperation(value = "根据code获取个人币种信息", httpMethod = "POST", response = ApiJsonResult.class, notes = "根据code获取个人币种信息")
    @PostMapping("/user/getAccountByCoinCode")
    @ResponseBody
    @RequiresAuthentication //是否必须传token
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header")
    })
    public JsonResult getAccountByCoinCode(HttpServletRequest request,
                                           @ApiParam(name = "coinCode", value = "币种编码",required = true) @RequestParam(required = true) String coinCode) {
        User user = TokenUtil.getUser(request);
        if (user != null) {
            RemoteAssetService remoteAssetService = SpringUtil.getBean("remoteAssetService");
            return remoteAssetService.getAccountByCoinCode(user.getCustomerId(), coinCode);
        } else {
            return new JsonResult().setSuccess(false).setMsg(SpringUtil.diff("before_login"));
        }
    }

    /**
     * 用户理财
     * */
    @ApiOperation(value = "用户理财", httpMethod = "POST", response = ApiJsonResult.class, notes = "用户理财")
    @PostMapping("/user/againInvest")
    @ResponseBody
    @RequiresAuthentication //是否必须传token
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header")
    })
    public JsonResult againInvest(HttpServletRequest request,
                                  @ApiParam(name = "investNum", value = "投资数量",required = true) @RequestParam(required = true) String investNum){
        User user = TokenUtil.getUser(request);
        if (user != null) {
            RemoteAssetService remoteAssetService = SpringUtil.getBean("remoteAssetService");
            Map<String, String> map = new HashMap<>();
            map.put("customerId", user.getCustomerId().toString());
            map.put("investNum",investNum);
            return remoteAssetService.againInvest(map);
        } else {
            return new JsonResult().setSuccess(false).setMsg(SpringUtil.diff("before_login"));
        }
    }

    /**
     * 兑换页面所需数据
     * */
    @ApiOperation(value = "兑换页面所需数据", httpMethod = "POST", response = ApiJsonResult.class, notes = "兑换页面所需数据")
    @PostMapping("/user/findexchangeData")
    @ResponseBody
    @RequiresAuthentication //是否必须传token
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header")
    })
    public JsonResult findexchangeData(HttpServletRequest request) {
        User user = TokenUtil.getUser(request);
        RemoteAssetService remoteAssetService = SpringUtil.getBean("remoteAssetService");
        JsonResult jsonResult = remoteAssetService.findexchangeData(user.getCustomerId());
        return jsonResult;
    }
    /**
     * 用户兑换
     * */
    @ApiOperation(value = "币币兑换 (目前只支持平台币兑换USDT)", httpMethod = "POST", response = ApiJsonResult.class, notes = "币币兑换 (目前只支持平台币兑换USDT)")
    @PostMapping("/user/exchangeCode")
    @ResponseBody
    @RequiresAuthentication //是否必须传token
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header")
    })
    public JsonResult exchangeCode (HttpServletRequest request,
                                    @ApiParam(name = "sellCode", value = "使用币code",required = true) @RequestParam(required = true) String sellCode,
                                    @ApiParam(name = "buyCode", value = "换币code",required = true) @RequestParam(required = true) String buyCode,
                                    @ApiParam(name = "accountPw", value = "资金密码", required = true) @RequestParam("accountPw") String accountPw,
                                    @ApiParam(name = "exchangeNum", value = "兑换数量",required = true) @RequestParam(required = true) String exchangeNum) {
        User user = TokenUtil.getUser(request);
        if (user != null) {
            JsonResult jsonResult = new JsonResult("false");
            //验证交易密码
            PasswordHelper passwordHelper = new PasswordHelper();
            String encryAccountPw = passwordHelper.encryString(accountPw, user.getSalt());
            if (!encryAccountPw.equals(user.getAccountPassWord())) {
                jsonResult.setSuccess(false);
                jsonResult.setMsg(SpringUtil.diff("mimacuowu"));
                return jsonResult;
            }
            RemoteAssetService remoteAssetService = SpringUtil.getBean("remoteAssetService");
            Map<String, String> map = new HashMap<>();
            map.put("customerId", user.getCustomerId().toString());
            map.put("sellCode", sellCode);
            map.put("buyCode", buyCode);
            map.put("exchangeNum", exchangeNum);
            return remoteAssetService.exchangeCode(map);
        } else {
            return new JsonResult().setSuccess(false).setMsg(SpringUtil.diff("before_login"));
        }
    }


    /**
     * 财务记录
     * */
    @ApiOperation(value = "财务记录", httpMethod = "POST", response = ApiJsonResult.class, notes = "财务记录")
    @PostMapping("/user/findDealDetails")
    @ResponseBody
    @RequiresAuthentication //是否必须传token
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header")
    })
    public FrontPage findDealDetails(HttpServletRequest request,
                                 @ApiParam(name = "limit", value = "每页条数", required = true) @RequestParam("limit") String limit,
                                 @ApiParam(name = "offset", value = "(页码-1)*每页条数,如第1页，每次10条侧传(1-1)*10=0", required = true) @RequestParam("offset") String offset,
                                 @ApiParam(name = "coinCode", value = "币种代码", required = true) @RequestParam("coinCode") String coinCode,
                                 @ApiParam(name = "dealType", value = "交易类型", required = true) @RequestParam("dealType") String dealType) {
        User user = TokenUtil.getUser(request);
        RemoteAssetService remoteAssetService = SpringUtil.getBean("remoteAssetService");
        Map<String, String> map = new HashMap<>();
        map.put("customerId", user.getCustomerId().toString());
        map.put("limit", limit);
        map.put("offset", offset);
        map.put("coinCode", coinCode);
        map.put("dealType", dealType);
        return remoteAssetService.findDealDetails(map);
    }

    /**
     * 用户剩余实际投资数量
     * */
    @ApiOperation(value = "用户剩余实际投资数量", httpMethod = "POST", response = ApiJsonResult.class, notes = "用户剩余实际投资数量")
    @PostMapping("/user/findUserAssetNum")
    @ResponseBody
    @RequiresAuthentication //是否必须传token
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header")
    })
    public JsonResult findUserAssetNum(HttpServletRequest request){
        User user = TokenUtil.getUser(request);
        RemoteAssetService remoteAssetService = SpringUtil.getBean("remoteAssetService");
        return remoteAssetService.findUserAssetNum(user.getCustomerId());
    }
}
