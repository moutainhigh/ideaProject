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
import hry.klg.remote.RemoteKlgService;
import hry.manage.remote.model.User;
import hry.util.springmvcPropertyeditor.DateTimePropertyEditorSupport;
import hry.util.springmvcPropertyeditor.StringPropertyEditorSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Controller
@RequestMapping("/klg/account")
@Api(value = "KLG-账户中心-我的资产", description = "KLG-账户中心-我的资产", tags = "KLG-账户中心-我的资产")
public class KlgAccountController {
	
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
     * 我的资产
     * @param request
     * @return
     */
    @ApiOperation(value = "我的资产", httpMethod = "POST", response = ApiJsonResult.class, notes = "我的资产")
    @PostMapping("/user/myAccount")
    @ResponseBody
    @RequiresAuthentication //是否必须传token
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header")
    })
    public JsonResult myAccount(HttpServletRequest request) {

        JsonResult jsonResult = new JsonResult();
        Map<String, String> params = HttpServletRequestUtils.getParams(request);
        //params.put("customerId", "481474");
        User user = TokenUtil.getUser(request);
    	params.put("customerId", user.getCustomerId().toString());
    	RemoteKlgService remoteKlgService = hry.util.common.SpringUtil.getBean("remoteKlgService");
        jsonResult = remoteKlgService.myAccount(params);
        return jsonResult;
    }
    
    /**
     * 获取平台币价钱
     * @param request
     * @return
     */
    @ApiOperation(value = "获取平台币价钱", httpMethod = "POST", response = ApiJsonResult.class, notes = "获取平台币价钱")
    @PostMapping("/getPlatformCoinPrice")
    @ResponseBody
    public JsonResult getPlatformCoinPrice(HttpServletRequest request) {

        JsonResult jsonResult = new JsonResult();
        Map<String, String> params = HttpServletRequestUtils.getParams(request);
    	RemoteKlgService remoteKlgService = hry.util.common.SpringUtil.getBean("remoteKlgService");
        jsonResult = remoteKlgService.getPlatformCoinPrice();
        return jsonResult;
    }
    /**
     * 获取平台币Code
     * @param request
     * @return
     */
    @ApiOperation(value = "获取平台币code", httpMethod = "POST", response = ApiJsonResult.class, notes = "获取平台币code")
    @PostMapping("/getPlatformCode")
    @ResponseBody
    public JsonResult getPlatformCode(HttpServletRequest request) {
    	
    	JsonResult jsonResult = new JsonResult();
    	Map<String, String> params = HttpServletRequestUtils.getParams(request);
    	RemoteKlgService remoteKlgService = hry.util.common.SpringUtil.getBean("remoteKlgService");
    	jsonResult = remoteKlgService.getPlatformCode();
    	return jsonResult;
    }
    
    /**
     * 根据coinCode查询货币信息
     * @param request
     * @return
     */
    @ApiOperation(value = "根据coinCode查询货币信息", httpMethod = "POST", response = ApiJsonResult.class, notes = "根据coinCode查询货币信息")
    @PostMapping("/user/getAccountByCoinCode")
    @ResponseBody
    @RequiresAuthentication //是否必须传token
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header")
    })
    public JsonResult getAccountByCoinCode(
    		@ApiParam(name = "coinCode", value = "币种编码",required = true) @RequestParam(required = true) String coinCode,
    		HttpServletRequest request) {

        JsonResult jsonResult = new JsonResult();
        Map<String, String> params = HttpServletRequestUtils.getParams(request);
        //params.put("customerId", "481474");
        User user = TokenUtil.getUser(request);
    	params.put("customerId", user.getCustomerId().toString());
    	RemoteKlgService remoteKlgService = hry.util.common.SpringUtil.getBean("remoteKlgService");
        jsonResult = remoteKlgService.getAccountByCoinCode(params);
        return jsonResult;
    }
    
    /**
     * 获取排队时间间隔
     * @param request
     * @return
     */
    @ApiOperation(value = "获取排队时间间隔", httpMethod = "POST", response = ApiJsonResult.class, notes = "获取排队时间间隔")
    @PostMapping("/getLineUpTime")
    @ResponseBody
    public JsonResult getLineUpTime(HttpServletRequest request) {
    	
    	JsonResult jsonResult = new JsonResult();
    	Map<String, String> params = HttpServletRequestUtils.getParams(request);
    	RemoteKlgService remoteKlgService = hry.util.common.SpringUtil.getBean("remoteKlgService");
    	jsonResult = remoteKlgService.getLineUpTime();
    	return jsonResult;
    }
    
    /**
     * 获取所有配置信息
     * @param request
     * @return
     */
    @ApiOperation(value = "获取所有配置信息", httpMethod = "POST", response = ApiJsonResult.class, notes = "获取所有配置信息")
    @PostMapping("/getKlgConfig")
    @ResponseBody
    public JsonResult getKlgConfig(HttpServletRequest request) {
    	
    	JsonResult jsonResult = new JsonResult();
    	Map<String, String> params = HttpServletRequestUtils.getParams(request);
    	RemoteKlgService remoteKlgService = hry.util.common.SpringUtil.getBean("remoteKlgService");
    	jsonResult = remoteKlgService.getKlgConfig();
    	return jsonResult;
    }
	

}
