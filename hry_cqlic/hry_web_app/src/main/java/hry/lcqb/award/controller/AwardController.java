package hry.lcqb.award.controller;

import hry.app.jwt.TokenUtil;
import hry.bean.ApiJsonResult;
import hry.bean.FrontPage;
import hry.bean.JsonResult;
import hry.licqb.remote.RemoteAwardService;
import hry.manage.remote.model.User;
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
 * @date 2019/8/28 9:56
 * @Version 1.0
 */
@Controller
@RequestMapping("/award")
@Api(value = "李超钱包---收益相关接口", description = "李超钱包---收益相关接口", tags = "李超钱包---收益相关接口")
public class AwardController {
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
     * 收益统计---静态收益，动态收益，和共建社区奖励
     * */
    @ApiOperation(value = "收益统计", httpMethod = "POST", response = ApiJsonResult.class, notes = "收益统计")
    @RequestMapping("/user/findAwardStatistics")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
    @RequiresAuthentication
    public JsonResult findAwardStatistics(HttpServletRequest request) {
        User user = TokenUtil.getUser(request);
        if (user != null) {
            RemoteAwardService remoteAwardService = SpringUtil.getBean("remoteAwardService");
            JsonResult result = remoteAwardService.findAwardStatistics(user.getCustomerId());
            return result;
        } else {
            return new JsonResult().setSuccess(false).setMsg(SpringUtil.diff("before_login"));
        }
    }

    /**
     * 收益明细
     * */
    @ApiOperation(value = "收益明细", httpMethod = "POST", response = ApiJsonResult.class, notes = "收益明细")
    @RequestMapping("/user/findAwardDetails")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
    @RequiresAuthentication
    public FrontPage findAwardDetails(HttpServletRequest request,
                                      @ApiParam(name = "limit", value = "每页条数", required = true) @RequestParam("limit") String limit,
                                      @ApiParam(name = "offset", value = "(页码-1)*每页条数,如第1页，每次10条侧传(1-1)*10=0", required = true) @RequestParam("offset") String offset) {
        User user = TokenUtil.getUser(request);
        if (user == null) {
            return null;
        }
        RemoteAwardService remoteAwardService = SpringUtil.getBean("remoteAwardService");
        Locale locale = LocaleContextHolder.getLocale();
        Map<String, String> params = new HashMap<>();
        params.put("limit",limit);
        params.put("offset",offset);
        params.put("locale",locale.toString());
        params.put("customerId",user.getCustomerId().toString());
        return remoteAwardService.findAwardDetails(params);
    }
}
