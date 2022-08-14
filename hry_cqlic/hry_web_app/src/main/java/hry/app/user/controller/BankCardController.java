package hry.app.user.controller;

import hry.app.jwt.TokenUtil;
import hry.bean.ApiJsonResult;
import hry.core.annotation.CommonLog;
import hry.manage.remote.RemoteAppTransactionManageService;
import hry.manage.remote.model.AppBankCardManage;
import hry.manage.remote.model.RemoteResult;
import hry.manage.remote.model.User;
import hry.util.common.SpringUtil;
import hry.util.shiro.PasswordHelper;
import hry.util.springmvcPropertyeditor.DateTimePropertyEditorSupport;
import hry.util.springmvcPropertyeditor.StringPropertyEditorSupport;
import io.swagger.annotations.*;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/bankcard")
@Api(value = "个人中心-银行卡管理(必须传token)", description = "银行卡管理(必须传token)" ,tags = "银行卡管理(必须传token)")
public class BankCardController {

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
    private RemoteAppTransactionManageService remoteAppTransactionManageService;


    @ApiOperation(value = "我的银行卡查询接口", httpMethod = "POST", notes = "我的银行卡查询接口")
    @PostMapping("/user/findbankcard")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
    @ResponseBody
    @RequiresAuthentication
    public ApiJsonResult<List<AppBankCardManage>> findBankCard(HttpServletRequest request) {
        ApiJsonResult<List<AppBankCardManage>> jsonResult = new ApiJsonResult();
        //获取登录用户
        User user = TokenUtil.getUser(request);
        if (user != null) {
            List<AppBankCardManage> list = remoteAppTransactionManageService.findByCustomerId(user.getCustomerId());
            jsonResult.setObj(list);
            return jsonResult;
        }
        jsonResult.setMsg(SpringUtil.diff("before_login"));
        return jsonResult;
    }

    /**
     * 保存银行卡信息
     * @param cardBank
     * @param bankProvince
     * @param bankAddress
     * @param subBank
     * @param surName
     * @param trueName
     * @param cardNumber
     * @param request
     * @return
     */
    @CommonLog(name = "保存银行卡信息")
    @ApiOperation(value = "保存银行卡信息", httpMethod = "POST", notes = "保存银行卡信息")
    @PostMapping("/user/saveBankCard")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
    @ResponseBody
    @RequiresAuthentication
    public ApiJsonResult saveBankCard (
            @ApiParam(name = "cardBank", value = "开户银行名称", required = true) @RequestParam("cardBank") String cardBank,
            @ApiParam(name = "bankProvince", value = "银行卡所在地-省", required = true) @RequestParam("bankProvince") String bankProvince,
            @ApiParam(name = "bankAddress", value = "银行卡所在地-市", required = true) @RequestParam("bankAddress") String bankAddress,
            @ApiParam(name = "subBank", value = "开户支行", required = true) @RequestParam("subBank") String subBank,
            @ApiParam(name = "surName", value = "开户人姓氏", required = true) @RequestParam("surName") String surName,
            @ApiParam(name = "trueName", value = "开户人名字", required = true) @RequestParam("trueName") String trueName,
            @ApiParam(name = "cardNumber", value = "银行卡账号", required = true) @RequestParam("cardNumber") String cardNumber,
            HttpServletRequest request) {
        ApiJsonResult jsonResult = new ApiJsonResult();
        //获取登录用户
        User user = TokenUtil.getUser(request);
        if (user != null) {
            // 封装银行卡信息
            AppBankCardManage appBankCardManage = new AppBankCardManage();
            appBankCardManage.setCardBank(cardBank);
            appBankCardManage.setBankProvince(bankProvince);
            appBankCardManage.setBankAddress(bankAddress);
            appBankCardManage.setSubBank(subBank);
            appBankCardManage.setSurName(surName);
            appBankCardManage.setTrueName(trueName);
            appBankCardManage.setCardNumber(cardNumber);
            appBankCardManage.setUserName(user.getMobile());
            appBankCardManage.setCardName(appBankCardManage.getSurName() + appBankCardManage.getTrueName());

            try {
                RemoteResult remoteResult = remoteAppTransactionManageService.saveBankCard(user, appBankCardManage);
                jsonResult.setSuccess(remoteResult.getSuccess());
                jsonResult.setMsg(SpringUtil.diff(remoteResult.getMsg()));
                jsonResult.setObj(remoteResult.getObj());
                return jsonResult;
            } catch (Exception e) {
                e.printStackTrace();
                jsonResult.setSuccess(false);
                jsonResult.setMsg(SpringUtil.diff("yichang"));
                return jsonResult;
            }
        }
        jsonResult.setMsg(SpringUtil.diff("before_login"));
        return jsonResult;
    }

    /**
     * 删除银行卡
     * @param cardId
     * @param request
     * @return
     */
    @CommonLog(name = "删除银行卡")
    @ApiOperation(value = "删除银行卡", httpMethod = "POST", response = ApiJsonResult.class, notes = "删除银行卡")
    @RequestMapping("/user/removeBankCard")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
    @ResponseBody
    @RequiresAuthentication
    public ApiJsonResult removeBankCard (
            @ApiParam(name = "cardId", value = "银行卡id", required = true) @RequestParam("cardId") Long cardId,
            @ApiParam(name = "accountPwd", value = "资金密码", required = true) @RequestParam("accountPwd") String accountPwd,
            HttpServletRequest request) {
        ApiJsonResult jsonResult = new ApiJsonResult();
        //获取登录用户
        User user = TokenUtil.getUser(request);
        if (user == null) {
            jsonResult.setSuccess(false);
            jsonResult.setMsg(SpringUtil.diff("before_login"));
            return jsonResult;
        }
        //验证交易密码
        PasswordHelper passwordHelper = new PasswordHelper();
        String encryAccountPw = passwordHelper.encryString(accountPwd, user.getSalt());
        if (!encryAccountPw.equals(user.getAccountPassWord())) {
            jsonResult.setSuccess(false);
            jsonResult.setMsg(SpringUtil.diff("mimacuowu"));
            return jsonResult;
        }

        RemoteResult remoteresult = remoteAppTransactionManageService.delete(cardId, user.getCustomerId().toString());
        jsonResult.setSuccess(remoteresult.getSuccess());
        jsonResult.setMsg(SpringUtil.diff(remoteresult.getMsg()));
        return jsonResult;
    }

    @ApiOperation(value = "验证卡号是否存在", httpMethod = "POST", response = ApiJsonResult.class, notes = "验证卡号是否存在")
    @PostMapping("/user/validCardNumIsExist")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
    @ResponseBody
    @RequiresAuthentication
    public ApiJsonResult validCardNumIsExist (
            @ApiParam(name = "cardNumber", value = "卡号", required = true) @RequestParam("cardNumber") String cardNumber,
            @ApiParam(name = "type", value = "类型", required = true) @RequestParam Integer type,
            HttpServletRequest request) {
        ApiJsonResult jsonResult = new ApiJsonResult();
        User user = TokenUtil.getUser(request);
        if (user == null) {
            jsonResult.setSuccess(false);
            jsonResult.setMsg(SpringUtil.diff("before_login"));
            return jsonResult;
        }

        int flag = remoteAppTransactionManageService.findSaveFlag(cardNumber, user.getCustomerId().toString(), type);
        if (flag == 0) {
            jsonResult.setSuccess(true);
            jsonResult.setMsg("");
            return jsonResult;
        } else {
            jsonResult.setSuccess(false);
            jsonResult.setMsg("");
            return jsonResult;
        }
    }


}
