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
import hry.klg.remote.RemoteKlgBuySellTransactionService;
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
@RequestMapping("/reward")
@Api(value = "KLG-账户中心-我的奖励", description = "KLG-账户中心-我的奖励", tags = "KLG-账户中心-我的奖励")
public class KlgRewardController {
	
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
     * 查询奖励记录
     * @param request
     * @return
     */
    @ApiOperation(value = "查询奖励记录", httpMethod = "POST", response = ApiJsonResult.class, notes = "查询奖励记录")
    @PostMapping("/user/findRewardList")
    @ResponseBody
    @RequiresAuthentication //是否必须传token
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header")
    })
    public FrontPage findRewardList(
    		@ApiParam(name = "limit", value = "每页条数", required = true) @RequestParam("limit") String limit,
    		@ApiParam(name = "offset", value = "(页码-1)*每页条数,如第1页，每次10条侧传(1-1)*10=0",required = true) @RequestParam("offset") String offset,
    		HttpServletRequest request) {

        JsonResult jsonResult = new JsonResult();
        Map<String, String> params = HttpServletRequestUtils.getParams(request);
        //params.put("customerId", "481474");
        User user = TokenUtil.getUser(request);
    	params.put("customerId", user.getCustomerId().toString());
    	RemoteKlgRewardService remoteKlgRewardService = hry.util.common.SpringUtil.getBean("remoteKlgRewardService");
        return remoteKlgRewardService.findRewardList(params);
    }
    
    /**
     * 我的奖励页面
     * @param request
     * @param transactionNum
     * @return
     */
    @ApiOperation(value = "我的奖励页面", httpMethod = "POST", response = ApiJsonResult.class, notes = "我的奖励页面")
    @PostMapping("/user/myReward")
    @ResponseBody
    @RequiresAuthentication //是否必须传token
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header")
    })
    public JsonResult myReward(HttpServletRequest request) {

        JsonResult jsonResult = new JsonResult();
        Map<String, String> params = HttpServletRequestUtils.getParams(request);
        //params.put("customerId", "481474");
        User user = TokenUtil.getUser(request);
    	params.put("customerId", user.getCustomerId().toString());
    	RemoteKlgRewardService remoteKlgRewardService = hry.util.common.SpringUtil.getBean("remoteKlgRewardService");
        jsonResult = remoteKlgRewardService.myReward(params);
        return jsonResult;
    }

}
