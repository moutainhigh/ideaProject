package hry.lcqb.exchange.controller;

import hry.app.jwt.TokenUtil;
import hry.bean.ApiJsonResult;
import hry.bean.FrontPage;
import hry.bean.JsonResult;
import hry.core.mvc.model.page.HttpServletRequestUtils;
import hry.core.shiro.PasswordHelper;
import hry.licqb.remote.RemoteExchangeService;
import hry.manage.remote.model.User;
import hry.util.common.SpringUtil;
import io.swagger.annotations.*;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Locale;
import java.util.Map;

/**
 * @author zhouming
 * @date 2020/12/22 14:35
 * @Version 1.0
 */
@Controller
@RequestMapping("/exchange")
@Api(value = "李超钱包---币种兑换相关接口", description = "李超钱包---币种兑换相关接口", tags = "李超钱包---币种兑换相关接口")
public class ExchangeController {

    /**
     * 获取兑换项目配置列表
     * */
    @ApiOperation(value = "获取兑换项目配置列表", httpMethod = "POST", response = ApiJsonResult.class, notes = "获取兑换项目配置列表")
    @RequestMapping("/user/findExchangeConfigList")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
    @RequiresAuthentication
    public FrontPage findExchangeConfigList(HttpServletRequest request,
                                      @ApiParam(name = "limit", value = "每页条数", required = true) @RequestParam("limit") String limit,
                                      @ApiParam(name = "offset", value = "(页码-1)*每页条数,如第1页，每次10条侧传(1-1)*10=0", required = true) @RequestParam("offset") String offset) {
        User user = TokenUtil.getUser(request);
        if (user == null) {
            return null;
        }
        // 装换传入的参数
        Map<String, String> params = HttpServletRequestUtils.getParams(request);
        // 获取语种
        Locale locale = LocaleContextHolder.getLocale();
        params.put("locale", locale.toString());
        RemoteExchangeService remoteExchangeService = SpringUtil.getBean("remoteExchangeService");
        return remoteExchangeService.findExchangeConfigList(params);
    }

    /**
     * 获取兑换项目配置信息
     * */
    @ApiOperation(value = "获取兑换项目配置信息", httpMethod = "POST", response = ApiJsonResult.class, notes = "获取兑换项目配置信息")
    @RequestMapping("/user/getExchangeConfigInfo")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
    @RequiresAuthentication
    public JsonResult getExchangeConfigInfo(HttpServletRequest request,
                                  @ApiParam(name = "configId", value = "兑换项目配置id", required = true) @RequestParam("configId") String configId) {
        User user = TokenUtil.getUser(request);
        if (user == null) {
            return new JsonResult().setSuccess(false).setMsg(SpringUtil.diff("before_login"));
        }
        // 获取语种
        Locale locale = LocaleContextHolder.getLocale();
        RemoteExchangeService remoteExchangeService = SpringUtil.getBean("remoteExchangeService");
        return remoteExchangeService.getExchangeConfig(configId, locale.toString());
    }


    /**
     * 用户兑换
     * */
    @ApiOperation(value = "币种兑换", httpMethod = "POST", response = ApiJsonResult.class, notes = "币种兑换")
    @RequestMapping("/user/toExchange")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
    @RequiresAuthentication
    public JsonResult toExchange(HttpServletRequest request,
                                 @ApiParam(name = "configId", value = "兑换项目id", required = true) @RequestParam("configId") Long configId,
                                 @ApiParam(name = "exchangeNum", value = "兑换数量", required = true) @RequestParam("exchangeNum") String exchangeNum,
                                 @ApiParam(name = "accountPw", value = "资金密码", required = true) @RequestParam("accountPw") String accountPw
                                 ) {
        User user = TokenUtil.getUser(request);
        if (user == null) {
            return new JsonResult().setSuccess(false).setMsg(SpringUtil.diff("before_login"));
        }
        if (new BigDecimal(exchangeNum).compareTo(BigDecimal.ZERO) < 1) {
            return new JsonResult().setSuccess(false).setMsg(SpringUtil.diff("duihuanshuliangyouwu"));
        }
        //验证交易密码
        PasswordHelper passwordHelper = new PasswordHelper();
        String encryAccountPw = passwordHelper.encryString(accountPw, user.getSalt());
        if (!encryAccountPw.equals(user.getAccountPassWord())) {
            return new JsonResult(false).setMsg(SpringUtil.diff("mimacuowu"));
        }
        // 装换传入的参数
        Map<String, String> params = HttpServletRequestUtils.getParams(request);
        params.put("customerId", user.getCustomerId().toString());
        RemoteExchangeService remoteExchangeService = SpringUtil.getBean("remoteExchangeService");
        JsonResult result = remoteExchangeService.toExchange(params);
        return result;
    }

    /**
     * 获取用户兑换记录
     * */
    @ApiOperation(value = "获取用户兑换记录", httpMethod = "POST", response = ApiJsonResult.class, notes = "获取用户兑换记录")
    @RequestMapping("/user/findUserToExchangeRecord")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
    @RequiresAuthentication
    public FrontPage findUserToExchangeRecord(HttpServletRequest request,
                                  @ApiParam(name = "limit", value = "每页条数", required = true) @RequestParam("limit") String limit,
                                  @ApiParam(name = "offset", value = "(页码-1)*每页条数,如第1页，每次10条侧传(1-1)*10=0", required = true) @RequestParam("offset") String offset) {
        User user = TokenUtil.getUser(request);
        if (user == null) {
            return null;
        }
        // 装换传入的参数
        Map<String, String> params = HttpServletRequestUtils.getParams(request);
        params.put("customerId", user.getCustomerId().toString());
        RemoteExchangeService remoteExchangeService = SpringUtil.getBean("remoteExchangeService");
        return remoteExchangeService.findUserToExchangeRecord(params);
    }
}
