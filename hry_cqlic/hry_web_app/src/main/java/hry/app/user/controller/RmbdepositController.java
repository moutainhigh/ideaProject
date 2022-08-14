package hry.app.user.controller;

import com.alibaba.fastjson.JSONObject;
import hry.app.jwt.TokenUtil;
import hry.bean.ApiJsonResult;
import hry.bean.JsonResult;
import hry.core.mvc.model.page.HttpServletRequestUtils;
import hry.manage.remote.RemoteAppTransactionManageService;
import hry.manage.remote.model.AppConfig;
import hry.manage.remote.model.AppTransactionManage;
import hry.manage.remote.model.RemoteResult;
import hry.manage.remote.model.User;
import hry.manage.remote.model.base.FrontPage;
import hry.redis.common.utils.RedisService;
import hry.util.common.SpringUtil;
import hry.util.springmvcPropertyeditor.DateTimePropertyEditorSupport;
import hry.util.springmvcPropertyeditor.StringPropertyEditorSupport;
import io.swagger.annotations.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * RechargeController.java
 *
 * @author denghf
 * 2017年7月10日 下午2:21:16
 */
@Controller
@RequestMapping(value = "/rmbdeposit")
@Api(value = "个人中心 --> 我要充值", description = "个人中心 --> 我要充值", tags = "个人中心 --> 我要充值")
public class RmbdepositController {

    /**
     * 注册类型属性编辑器
     *
     * @param binder
     */
    @InitBinder
    public void initBinder (ServletRequestDataBinder binder) {

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
    private RedisService redisService;

    @Resource
    private RemoteAppTransactionManageService remoteAppTransactionManageService;

    /**
     * 个人中心-我要充值页面数据初始化
     * @param request
     * @return
     */
    @ApiOperation(value = "个人中心-我要充值页面数据初始化", httpMethod = "POST", response = Map.class, notes = "个人中心-我要充值页面数据初始化")
    @PostMapping(value = "/user/initDeposit")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
    @ResponseBody
    @RequiresAuthentication
    public Map<String, Object> initDeposit(
            @ApiParam(name = "langCode", value = "语种", required = true) @RequestParam("langCode") String langCode,
            HttpServletRequest request) {
        Map<String, Object> returnMap = new HashMap<>();
        User user = TokenUtil.getUser(request);

        //查询后台参数配置
        String config = redisService.get("configCache:all");
        if (!StringUtils.isEmpty(config)) {
            JSONObject parseObject = JSONObject.parseObject(config);

            //判断是否开启强制手机认证
            String isOpenVerify = "";
            if (parseObject.get("isOpenVerify") != null) {
                isOpenVerify = parseObject.get("isOpenVerify").toString();
            }
            if (null != isOpenVerify && (!"".equals(isOpenVerify)) && isOpenVerify.equals("0")) {
                if (user.getGoogleState() == 0 && user.getPhoneState() == 0) {
                    returnMap.put("phoneState", user.getPhoneState());
                    return returnMap;
                }
            }

            //查询后台参数配置
            if (!StringUtils.isEmpty(config)) {
                returnMap.put("rechargeFeeRate", parseObject.get("rechargeFeeRate"));//充值手续费率
                returnMap.put("minRechargeMoney", parseObject.get("minRechargeMoney"));//最小充值金额
                returnMap.put("languageCode", parseObject.get("language_code"));//当前币种
            }
            //线上充值所需要的参数
            String randomStr = RandomStringUtils.random(2, false, true);
            returnMap.put("random", "0." + randomStr);
            returnMap.put("randomStr", "." + randomStr);

            //金如悦 查询后台配置 是否开启第三方充值
            List<AppConfig> configInfo = remoteAppTransactionManageService.getConfigInfo("isOpenThird");
            if (configInfo.size() == 0) {
                returnMap.put("isOpenThird", "1");
            } else {
                String value = configInfo.get(0).getValue();
                returnMap.put("isOpenThird", value);
            }
        }
        return returnMap;
    }

    /**
     * 获取充值银行列表
     *
     * @return
     */
    @ApiOperation(value = "个人中心我要充值银行列表", httpMethod = "POST", notes = "个人中心我要充值银行列表")
    @RequestMapping(value = "/selectRedisBank")
    @ResponseBody
    public ApiJsonResult selectRedisBank () {
        String bankList = redisService.hget("new_app_dic", "bankType");
        if (bankList != null && !bankList.equals("")) {
            return new ApiJsonResult().setSuccess(true).setObj(bankList);
        }
        return new ApiJsonResult().setSuccess(false);
    }

    /**
     * 生成银行汇款单
     *
     * @param surname
     * @param remitter
     * @param bankCode
     * @param bankAmount
     * @param request
     * @return
     */
    @ApiOperation(value = "个人中心-我要充值-生成银行汇款单", httpMethod = "POST", response = ApiJsonResult.class, notes = "个人中心-我要充值-生成银行汇款单")
    @PostMapping("/user/rmbdeposit")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
    @ResponseBody
    @RequiresAuthentication
    public ApiJsonResult rmbdeposit (
            @ApiParam(name = "remitter", value = "汇款人姓氏", required = true) @RequestParam("remitter") String remitter,
            @ApiParam(name = "surname", value = "汇款人姓名字", required = true) @RequestParam("surname") String surname,
            @ApiParam(name = "bankCode", value = "银行卡号", required = true) @RequestParam("bankCode") String bankCode,
            @ApiParam(name = "bankAmount", value = "充值金额", required = true) @RequestParam("bankAmount") String bankAmount,
            @ApiParam(name = "selectedBankName", value = "充值银行", required = true) @RequestParam("selectedBankName") String selectedBankName,
            HttpServletRequest request) {
        ApiJsonResult jsonResult = new ApiJsonResult();
        User user = TokenUtil.getUser(request);

        if (user != null) {
            AppTransactionManage appTransaction = new AppTransactionManage();
            RemoteResult remoteResult = remoteAppTransactionManageService.generateRmbdeposit(surname, remitter, bankCode, bankAmount, selectedBankName, appTransaction, user);
            if (remoteResult.getSuccess()) {
                jsonResult.setSuccess(true);
                jsonResult.setObj(remoteResult.getObj());
                jsonResult.setMsg(remoteResult.getMsg());
                return jsonResult;
            } else {
                jsonResult.setSuccess(false);
                jsonResult.setMsg(remoteResult.getMsg());
                return jsonResult;
            }
        } else {
            jsonResult.setSuccess(false);
            jsonResult.setMsg(SpringUtil.diff("before_login"));
            return jsonResult;
        }
    }

    /**
     * 查询充值,提现记录
     *
     * @return
     */
    @ApiOperation(value = "实体:AppTransactionManage 个人中心我要充值充值列表或我要提现提现记录列表", httpMethod = "POST", notes = "个人中心我要充值充值列表或我要提现提现记录列表")
    @RequestMapping("/user/list")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
    @ResponseBody
    @RequiresAuthentication
    public FrontPage list (HttpServletRequest request,
                           @ApiParam(name = "limit", value = "每页条数", required = true) @RequestParam("limit") String limit,
                           @ApiParam(name = "offset", value = "(页码-1)*每页条数,如第1页，每次10条侧传(1-1)*10=0", required = true) @RequestParam("offset") String offset,
                           @ApiParam(name = "transactionType", value = "chongzhi  充值，tixian 提现", required = true) @RequestParam("transactionType") String transactionType,
                           @ApiParam(name = "status", value = "0 全部，1 待审核 ，2已通过 ，3已否决", required = true) @RequestParam("status") String status
    ) {
        User user = TokenUtil.getUser(request);

        Map<String, String> params = HttpServletRequestUtils.getParams(request);
        params.put("customerId", user.getCustomerId().toString());
        if ("all".equals(transactionType)) {//all查全部
            params.put("transactionType", null);
        }
        if ("0".equals(status)) {//0查全部
            params.put("status", null);
        }
        return remoteAppTransactionManageService.findTransaction(params);
    }


}
