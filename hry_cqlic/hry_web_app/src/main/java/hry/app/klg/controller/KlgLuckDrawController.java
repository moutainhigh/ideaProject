package hry.app.klg.controller;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import hry.app.jwt.TokenUtil;
import hry.bean.ApiJsonResult;
import hry.bean.JsonResult;
import hry.core.mvc.model.page.HttpServletRequestUtils;
import hry.klg.remote.RemoteKlgLuckDrawService;
import hry.klg.remote.RemoteKlgRewardService;
import hry.manage.remote.model.User;
import hry.manage.remote.model.base.FrontPage;
import hry.util.springmvcPropertyeditor.DateTimePropertyEditorSupport;
import hry.util.springmvcPropertyeditor.StringPropertyEditorSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Controller
@RequestMapping("/luckdraw")
@Api(value = "KLG-大奖基金", description = "KLG-大奖基金", tags = "KLG-大奖基金")
public class KlgLuckDrawController {
	
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
    
    /**
     * 查询上期中奖名单
     * @param request
     * @return
     */
    @ApiOperation(value = "查询上期中奖名单", httpMethod = "POST", response = ApiJsonResult.class, notes = "查询上期中奖名单")
    @PostMapping("/findSelectionList")
    @ResponseBody
    public FrontPage findSelectionList(
    		@ApiParam(name = "limit", value = "每页条数", required = true) @RequestParam("limit") String limit,
    		@ApiParam(name = "offset", value = "(页码-1)*每页条数,如第1页，每次10条侧传(1-1)*10=0",required = true) @RequestParam("offset") String offset,
    		HttpServletRequest request) {

        JsonResult jsonResult = new JsonResult();
        Map<String, String> params = HttpServletRequestUtils.getParams(request);
        RemoteKlgLuckDrawService remoteKlgLuckDrawService = hry.util.common.SpringUtil.getBean("remoteKlgLuckDrawService");
        return remoteKlgLuckDrawService.findSelectionList(params);
    }
    
    /**
     * 查询上期开奖信息
     * @param request
     * @param transactionNum
     * @return
     */
    @ApiOperation(value = "查询上期开奖信息", httpMethod = "POST", response = ApiJsonResult.class, notes = "查询上期开奖信息")
    @PostMapping("/getLastIssue")
    @ResponseBody
    public JsonResult getLastIssue(HttpServletRequest request) {

        JsonResult jsonResult = new JsonResult();
        Map<String, String> params = HttpServletRequestUtils.getParams(request);
        RemoteKlgLuckDrawService remoteKlgLuckDrawService = hry.util.common.SpringUtil.getBean("remoteKlgLuckDrawService");
        jsonResult = remoteKlgLuckDrawService.getLastIssue(params);
        return jsonResult;
    }
    
    /**
     * 查询我的抽奖号码
     * @param request
     * @return
     */
    @ApiOperation(value = "查询我的抽奖号码", httpMethod = "POST", response = ApiJsonResult.class, notes = "查询我的抽奖号码")
    @PostMapping("/user/findMyLuckDrawList")
    @ResponseBody
    @RequiresAuthentication //是否必须传token
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header")
    })
    public FrontPage findMyLuckDrawList(
    		@ApiParam(name = "limit", value = "每页条数", required = true) @RequestParam("limit") String limit,
    		@ApiParam(name = "offset", value = "(页码-1)*每页条数,如第1页，每次10条侧传(1-1)*10=0",required = true) @RequestParam("offset") String offset,
    		HttpServletRequest request) {
        Map<String, String> params = HttpServletRequestUtils.getParams(request);
        User user = TokenUtil.getUser(request);
    	params.put("customerId", user.getCustomerId().toString());
        RemoteKlgLuckDrawService remoteKlgLuckDrawService = hry.util.common.SpringUtil.getBean("remoteKlgLuckDrawService");
        return remoteKlgLuckDrawService.findMyLuckDrawList(params);
    }
    
    /**
     * 查询我的中奖纪录
     * @param request
     * @return
     */
    @ApiOperation(value = "查询我的中奖纪录", httpMethod = "POST", response = ApiJsonResult.class, notes = "查询我的中奖纪录")
    @PostMapping("/user/findMyprizeList")
    @ResponseBody
    @RequiresAuthentication //是否必须传token
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header")
    })
    public FrontPage findMyprizeList(
    		@ApiParam(name = "limit", value = "每页条数", required = true) @RequestParam("limit") String limit,
    		@ApiParam(name = "offset", value = "(页码-1)*每页条数,如第1页，每次10条侧传(1-1)*10=0",required = true) @RequestParam("offset") String offset,
    		HttpServletRequest request) {
        Map<String, String> params = HttpServletRequestUtils.getParams(request);
        User user = TokenUtil.getUser(request);
    	params.put("customerId", user.getCustomerId().toString());
        RemoteKlgLuckDrawService remoteKlgLuckDrawService = hry.util.common.SpringUtil.getBean("remoteKlgLuckDrawService");
        return remoteKlgLuckDrawService.findMyprizeList(params);
    }
    
    /**
     * 查询我的本期抽奖号码
     * @param request
     * @return
     */
    @ApiOperation(value = "查询我的本期抽奖号码", httpMethod = "POST", response = ApiJsonResult.class, notes = "查询我的本期抽奖号码")
    @PostMapping("/user/findMyThisLuckDrawList")
    @ResponseBody
    @RequiresAuthentication //是否必须传token
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header")
    })
    public FrontPage findMyThisLuckDrawList(
    		@ApiParam(name = "limit", value = "每页条数", required = true) @RequestParam("limit") String limit,
    		@ApiParam(name = "offset", value = "(页码-1)*每页条数,如第1页，每次10条侧传(1-1)*10=0",required = true) @RequestParam("offset") String offset,
    		HttpServletRequest request) {
        Map<String, String> params = HttpServletRequestUtils.getParams(request);
        User user = TokenUtil.getUser(request);
    	params.put("customerId", user.getCustomerId().toString());
        RemoteKlgLuckDrawService remoteKlgLuckDrawService = hry.util.common.SpringUtil.getBean("remoteKlgLuckDrawService");
        return remoteKlgLuckDrawService.findMyThisLuckDrawList(params);
    }
    
    /**
     * 查询本周剩余抽奖次数
     * @param request
     * @return
     */
    @ApiOperation(value = "查询本周剩余抽奖次数", httpMethod = "POST", response = ApiJsonResult.class, notes = "查询本周剩余抽奖次数")
    @PostMapping("/user/getMySurplusLuckdrawCount")
    @ResponseBody
    @RequiresAuthentication //是否必须传token
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header")
    })
    public JsonResult getMySurplusLuckdrawCount(HttpServletRequest request) {

        JsonResult jsonResult = new JsonResult();
        Map<String, String> params = HttpServletRequestUtils.getParams(request);
        User user = TokenUtil.getUser(request);
    	params.put("customerId", user.getCustomerId().toString());
        RemoteKlgLuckDrawService remoteKlgLuckDrawService = hry.util.common.SpringUtil.getBean("remoteKlgLuckDrawService");
        jsonResult = remoteKlgLuckDrawService.getMySurplusLuckdrawCount(params);
        return jsonResult;
    }
    
    /**
     * 添加抽奖号码
     * @param request
     * @return
     */
    @ApiOperation(value = "添加抽奖号码", httpMethod = "POST", response = ApiJsonResult.class, notes = "添加抽奖号码")
    @PostMapping("/user/addLuckDrawNum")
    @ResponseBody
    @RequiresAuthentication //是否必须传token
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header")
    })
    public JsonResult addLuckDrawNum(
    		@ApiParam(name = "number", value = "抽奖号码", required = true) @RequestParam("number") String number,
    		HttpServletRequest request) {

        JsonResult jsonResult = new JsonResult();
        Map<String, String> params = HttpServletRequestUtils.getParams(request);
        User user = TokenUtil.getUser(request);
    	params.put("customerId", user.getCustomerId().toString());
        RemoteKlgLuckDrawService remoteKlgLuckDrawService = hry.util.common.SpringUtil.getBean("remoteKlgLuckDrawService");
        jsonResult = remoteKlgLuckDrawService.addLuckDrawNum(params);
        return jsonResult;
    }

}
