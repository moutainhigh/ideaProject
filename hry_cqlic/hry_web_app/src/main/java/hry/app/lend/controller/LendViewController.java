package hry.app.lend.controller;

import hry.app.lend.aspect.LoginValid;
import hry.app.lend.model.LendUser;
import hry.core.mvc.model.page.HttpServletRequestUtils;
import hry.lend.model.page.FrontPage;
import hry.lend.model.view.ViewInOrOutLayout;
import hry.lend.model.view.ViewLendManage;
import hry.lend.model.view.ViewRepayLayout;
import hry.lend.remote.RemoteLendViewService;
import io.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author <a href="mailto:HelloHeSir@gmail.com">Mr_He</a>
 * @Copyright (c)</ b> HeC<br/>
 * @createTime 2018/10/30 15:39
 * @Description: 页面数据展示
 */
@RestController
@RequestMapping("/user/lend-view")
@Api(value = "杠杆相关接口", description = "展示相关", tags = "杠杆相关接口-展示相关")
public class LendViewController {

    @Resource
    private RemoteLendViewService remoteLendViewService;

    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "令牌",required = true,dataType = "String",paramType = "header")
    })
    @RequiresAuthentication
    @RequestMapping(value = "/lendManage", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ApiOperation(value = "杠杆管理页面", httpMethod = "POST", response = ViewLendManage.class)
    @LoginValid
    public ViewLendManage lendManage(HttpServletRequest request,
                                     @ApiParam(value = "交易对BTC_USDT") String coinCode,
                                     @ApiParam(hidden = true) LendUser user) {
        return remoteLendViewService.getLendManage(coinCode, user.getCustomerId());
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "令牌",required = true,dataType = "String",paramType = "header")
    })
    @RequiresAuthentication
    @RequestMapping(value = "/repayManage", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ApiOperation(value = "还款管理页面", httpMethod = "POST", response = FrontPage.class)
    @LoginValid
    public FrontPage repayManage(HttpServletRequest request,
                                 @ApiParam(name = "coinCode", value = "杠杆账户BTC_USDT") String coinCode,
                                 @ApiParam(name = "code", value = "币种") String code,
                                 @ApiParam(name = "limit", value = "每页条数", required = true) @RequestParam("limit") String limit,
                                 @ApiParam(name = "offset", value = "(页码-1)*每页条数,如第1页，每次10条侧传(1-1)*10=0", required = true) @RequestParam("offset") String offset,
                                 @ApiParam(value = "还款状态1/2/3/4 还款中 已结清 爆仓中 已爆仓") Integer status,
                                 @ApiParam(value = "订单号") String orderNum,
                                 @ApiParam(hidden = true) LendUser user) {
        Map<String, String> params = HttpServletRequestUtils.getParams(request);
        params.put("customerId", user.getCustomerId().toString());
        params.put("status", String.valueOf(status));
        params.put("coinCode", coinCode);
        params.put("code", code);
        params.put("orderNum", orderNum);
        return remoteLendViewService.findRepayManage(params);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "令牌",required = true,dataType = "String",paramType = "header")
    })
    @RequiresAuthentication
    @RequestMapping(value = "/repayLayout", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ApiOperation(value = "还款弹窗", httpMethod = "POST", response = ViewRepayLayout.class)
    @LoginValid
    public ViewRepayLayout repayLayout(HttpServletRequest request,
                                       @ApiParam(value = "借款单号", required = true) String lendNum,
                                       @ApiParam(hidden = true) LendUser user) {
        if (StringUtils.isNotEmpty(lendNum)) {
            return remoteLendViewService.repayLayout(lendNum, user.getCustomerId());
        }
        return null;
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "令牌",required = true,dataType = "String",paramType = "header")
    })
    @RequiresAuthentication
    @RequestMapping(value = "/inOrOutMoneyLayout", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ApiOperation(value = "转入或转出弹窗", httpMethod = "POST", response = ViewInOrOutLayout.class)
    @LoginValid
    public ViewInOrOutLayout inOrOutMoneyLayout(HttpServletRequest request, @ApiParam(name = "coinCode", value = "交易对BTC_USDT", required = true) String coinCode,
                                                @ApiParam(name = "type", value = "类型1转入 2转出", required = true) Integer type,
                                                @ApiParam(hidden = true) LendUser user) {
        if (StringUtils.isNotEmpty(coinCode) && null != type) {
            return remoteLendViewService.inOrOutMoneyLayout(coinCode, type, user.getCustomerId());
        }
        return null;
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "令牌",required = true,dataType = "String",paramType = "header")
    })
    @RequiresAuthentication
    @RequestMapping(value = "/lendAsset", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ApiOperation(value = "杠杆资产页", httpMethod = "POST", response = FrontPage.class)
    @LoginValid
    public FrontPage lendAsset(HttpServletRequest request,
                               @ApiParam(hidden = true) LendUser user,
                               @ApiParam(name = "coinCode", value = "查询杠杆账户交易对BTC_USDT") String coinCode,
                               @ApiParam(name = "limit", value = "每页条数", required = true) @RequestParam(defaultValue = "10") String limit,
                               @ApiParam(name = "offset", value = "(页码-1)*每页条数,如第1页，每次10条侧传(1-1)*10=0", required = true) @RequestParam(defaultValue = "0") String offset,
                               @ApiParam(name = "isHidden", value = "隐藏小额资产 true or false") @RequestParam(defaultValue = "false") Boolean isHidden,
                               @ApiParam(name = "langCode", value = "语种", required = true) @RequestParam("langCode") String langCode) {
        if (null != user && null != user.getCustomerId()) {
            Map<String, String> params = HttpServletRequestUtils.getParams(request);
            params.put("isHidden", isHidden.toString());
            params.put("customerId", String.valueOf(user.getCustomerId()));
            params.put("coinCode", coinCode);
            params.put("langCode", langCode);
            return remoteLendViewService.getLendAsset(params);
        }
        return null;
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "令牌",required = true,dataType = "String",paramType = "header")
    })
    @RequiresAuthentication
    @RequestMapping(value = "/fundRecord", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ApiOperation(value = "财务记录页", httpMethod = "POST", response = FrontPage.class)
    @LoginValid
    public FrontPage fundRecord(HttpServletRequest request,
                                @ApiParam(hidden = true) LendUser user,
                                @ApiParam(name = "limit", value = "每页条数", required = true) @RequestParam("limit") String limit,
                                @ApiParam(name = "offset", value = "(页码-1)*每页条数,如第1页，每次10条侧传(1-1)*10=0", required = true) @RequestParam("offset") String offset,
                                @ApiParam(name = "time", value = "期限 1=WEEK 1=MONTH 3=MONTH 10=MONTH") String time,
                                @ApiParam(name = "coinCode", value = "账户币种") String coinCode,
                                @ApiParam(value = "订单号") String orderNum,
                                @ApiParam(name = "type", value = "类型") String type) {
        if (null != user && null != user.getCustomerId()) {
            Map<String, String> params = HttpServletRequestUtils.getParams(request);
            params.put("customerId", String.valueOf(user.getCustomerId()));
            if (StringUtils.isEmpty(time)) {
                time = "1=WEEK";
            }
            params.put("time", time.split("=")[0]);
            params.put("timeType", time.split("=")[1]);
            params.put("coinCode", coinCode);
            params.put("orderNum", orderNum);
            if (StringUtils.isNotEmpty(type)) {
                params.put("type", type);
            }
            return remoteLendViewService.getFundRecord(params);
        }
        return null;
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "令牌",required = true,dataType = "String",paramType = "header")
    })
    @RequiresAuthentication
    @RequestMapping(value = "/getConvert", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ApiOperation(value = "净资产折合", httpMethod = "POST")
    @LoginValid
    public Map<String, Object> getAccountConvert(HttpServletRequest request,
                                                 @ApiParam(hidden = true) LendUser user,
                                                 @ApiParam(name = "langCode", value = "语种", required = true) @RequestParam("langCode") String langCode) {
        return remoteLendViewService.getAccountConvert(user.getCustomerId(),langCode);
    }

}
