package hry.app.user.controller;

import com.alibaba.fastjson.JSONObject;
import hry.app.jwt.TokenUtil;
import hry.bean.ApiJsonResult;
import hry.manage.remote.RemoteAppTransactionManageService;
import hry.manage.remote.RemoteManageService;
import hry.manage.remote.model.*;
import hry.redis.common.utils.RedisService;
import hry.util.common.SpringUtil;
import hry.util.springmvcPropertyeditor.DateTimePropertyEditorSupport;
import hry.util.springmvcPropertyeditor.StringPropertyEditorSupport;
import io.swagger.annotations.*;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/rmbWithdraw")
@Api(value = "个人中心-我要提现(必须传token)", description = "个人中心-我要提现(必须传token)", tags = "个人中心-我要提现(必须传token)")
public class RmbWithdrawController {

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

    @Resource
    private RemoteManageService remoteManageService;

    @ApiOperation(value = "个人中心-我要提现页面数据初始化", httpMethod = "POST", response = Map.class, notes = "个人中心-我要提现页面数据初始化")
    @PostMapping("/user/initPageData")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
    @ResponseBody
    @RequiresAuthentication
    public Map<String, Object> initPageData (HttpServletRequest request) {
        Map<String, Object> returnMap = new HashMap<>();
        User user = TokenUtil.getUser(request);
        if (user != null) {
            //查询后台参数配置
            String config = redisService.get("configCache:all");
            if (!StringUtils.isEmpty(config)) {
                JSONObject parseObject = JSONObject.parseObject(config);
                returnMap.put("languageCode", parseObject.get("language_code"));//当前币种

                BigDecimal oldMoney = remoteManageService.getOldMoney(user.getCustomerId(), new SimpleDateFormat("yyyy-MM-dd").format(new Date().getTime()));
                AppAccountManage appAccount = null;
                //查询虚拟账户
                appAccount = remoteManageService.getAppAccountManage(user.getCustomerId());
                returnMap.put("appAccount", appAccount);

                //查询银行卡列表
                List<AppBankCardManage> list = remoteAppTransactionManageService.findByCustomerId(user.getCustomerId());
                returnMap.put("list", list);
                returnMap.put("username", user.getUsername());

                //查询后台参数配置
                if (!StringUtils.isEmpty(config)) {
                    returnMap.put("oldMoney", oldMoney.toString());//当天已经提现的金额
                    returnMap.put("onlineWithdrawFeeRate", parseObject.get("onlineWithdrawFeeRate"));//提现手续费率
                    returnMap.put("maxWithdrawMoney", parseObject.get("maxWithdrawMoney"));//当天最多提现金额
                    returnMap.put("maxWithdrawMoneyOneTime", parseObject.get("maxWithdrawMoneyOneTime"));//单笔最多提现金额(元)
                }
            }
        }
        return returnMap;
    }

    //提现
    @ApiOperation(value = "个人中心-我要提现-确认提现", httpMethod = "POST", response = ApiJsonResult.class, notes = "个人中心-我要提现-确认提现")
    @PostMapping("/user/confirmWithdraw")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
    @ResponseBody
    @RequiresAuthentication
    public ApiJsonResult confirmWithdraw (
            @ApiParam(name = "bankCardId", value = "银行卡id", required = true) @RequestParam("custromerAccountNumber") String bankCardId,
            @ApiParam(name = "transactionMoney", value = "提现金额", required = true) @RequestParam("transactionMoney") String transactionMoney,
            HttpServletRequest request) {
        ApiJsonResult jsonResult = new ApiJsonResult();
        jsonResult.setSuccess(false);
        String accountpwSmsCode = null;
        String withdrawCode = null;
        String accountPassWord = null;
        String encryString = null;

        try {
            User user = TokenUtil.getUser(request);
            if (user != null) {
                AppTransactionManage appTransaction = new AppTransactionManage();
                appTransaction.setTransactionMoney(new BigDecimal(transactionMoney));
                RemoteResult remoteResult = remoteAppTransactionManageService.rmbwithdraw(accountPassWord, bankCardId, withdrawCode, accountpwSmsCode, user, encryString, appTransaction);
                jsonResult.setSuccess(remoteResult.getSuccess());
                jsonResult.setMsg(remoteResult.getMsg());
                return jsonResult;
            }
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.setSuccess(false);
            jsonResult.setMsg(SpringUtil.diff("yichang"));
            return jsonResult;
        }
        return jsonResult;
    }
}
