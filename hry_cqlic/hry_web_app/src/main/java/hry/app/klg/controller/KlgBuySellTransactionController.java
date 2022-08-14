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
import hry.bean.FrontPage;
import hry.bean.JsonResult;
import hry.core.mvc.model.page.HttpServletRequestUtils;
import hry.klg.remote.RemoteKlgBuySellTransactionService;
import hry.klg.remote.RemoteKlgService;
import hry.manage.remote.model.User;
import hry.util.common.SpringUtil;
import hry.util.springmvcPropertyeditor.DateTimePropertyEditorSupport;
import hry.util.springmvcPropertyeditor.StringPropertyEditorSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Controller
@RequestMapping("/buyTransaction")
@Api(value = "KLG-用户买卖单业务", description = "KLG-用户买卖单业务", tags = "KLG-用户买卖单业务")
public class KlgBuySellTransactionController {
	
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
     * 买家补缴尾款
     * @param request
     * @param transactionNum
     * @return
     */
    @ApiOperation(value = "买家补缴尾款", httpMethod = "POST", response = ApiJsonResult.class, notes = "买家补缴尾款")
    @PostMapping("/user/payBuyTransaction")
    @ResponseBody
    @RequiresAuthentication //是否必须传token
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header")
    })
    public JsonResult payBuyTransaction(HttpServletRequest request,
       @ApiParam(name = "buyTransactionId", value = "订单号", example = "",required = true) @RequestParam String buyTransactionId) {

        JsonResult jsonResult = new JsonResult();
        Map<String, String> params = HttpServletRequestUtils.getParams(request);
        //params.put("customerId", "481474");
        User user = TokenUtil.getUser(request);
    	params.put("customerId", user.getCustomerId().toString());
        RemoteKlgBuySellTransactionService remoteKlgBuySellTransactionService = hry.util.common.SpringUtil.getBean("remoteKlgBuySellTransactionService");
        jsonResult = remoteKlgBuySellTransactionService.payBackBuyTransaction(params);
       /* if(jsonResult.getMsg()!=null&&!jsonResult.getMsg().equals("")){
        	jsonResult.setMsg(SpringUtil.diff(jsonResult.getMsg()));
        }*/
        return jsonResult;
    }
    
    /**
     * 抢单
     * @param request
     * @param transactionNum
     * @return
     */
    @ApiOperation(value = "抢单", httpMethod = "POST", response = ApiJsonResult.class, notes = "抢单")
    @PostMapping("/user/robBuyTransaction")
    @ResponseBody
    @RequiresAuthentication //是否必须传token
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header")
    })
    public JsonResult obBuyTransaction(HttpServletRequest request,
       @ApiParam(name = "robBuySmeNum", value = "抢单数量", example = "",required = true) @RequestParam String robBuySmeNum) {

        JsonResult jsonResult = new JsonResult();
        User user = TokenUtil.getUser(request);
      //判断是否实名
        if (user.getStates() != 2) {
        	jsonResult.setMsg(hry.util.common.SpringUtil.diff("qingxianshimingrenzheng"));
        	jsonResult.setSuccess(false);
            return jsonResult;
        }
        Map<String, String> params = HttpServletRequestUtils.getParams(request);
        //params.put("customerId", "481474");
        
    	params.put("customerId", user.getCustomerId().toString());
        RemoteKlgBuySellTransactionService remoteKlgBuySellTransactionService = hry.util.common.SpringUtil.getBean("remoteKlgBuySellTransactionService");
        jsonResult = remoteKlgBuySellTransactionService.robBuyTransaction(params);
        /*if(jsonResult.getMsg()!=null&&!jsonResult.getMsg().equals("")){
        	jsonResult.setMsg(SpringUtil.diff(jsonResult.getMsg()));
        }*/
        return jsonResult;
    }
    
    /**
     * 查询用户买单
     * @param request
     * @return
     */
    @ApiOperation(value = "查询用户买单", httpMethod = "POST", response = ApiJsonResult.class, notes = "查询用户买单")
    @PostMapping("/user/findBuyList")
    @ResponseBody
    @RequiresAuthentication //是否必须传token
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header")
    })
    public FrontPage findBuyList(
    		@ApiParam(name = "limit", value = "每页条数", required = true) @RequestParam("limit") String limit,
    		@ApiParam(name = "offset", value = "(页码-1)*每页条数,如第1页，每次10条侧传(1-1)*10=0",required = true) @RequestParam("offset") String offset,
    		@ApiParam(name = "status", value = "订单状态 1排队中 2待支付 3待收款 4已成交 5已作废",required = true) @RequestParam String status,
    		HttpServletRequest request) {
        Map<String, String> params = HttpServletRequestUtils.getParams(request);
        //params.put("customerId", "481474");
        User user = TokenUtil.getUser(request);
    	params.put("customerId", user.getCustomerId().toString());
        params.put("pageSize",limit);
        params.put("page",offset);
    	RemoteKlgService remoteKlgService = hry.util.common.SpringUtil.getBean("remoteKlgService");
        return remoteKlgService.fingBuyListByCustomerId(params);
    }
    
    /**
     * 查询用户卖单
     * @param request
     * @return
     */
    @ApiOperation(value = "查询用户卖单", httpMethod = "POST", response = ApiJsonResult.class, notes = "查询用户卖单")
    @PostMapping("/user/findSellList")
    @ResponseBody
    @RequiresAuthentication //是否必须传token
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header")
    })
    public FrontPage findSellList(
    		@ApiParam(name = "limit", value = "每页条数", required = true) @RequestParam("limit") String limit,
    		@ApiParam(name = "offset", value = "(页码-1)*每页条数,如第1页，每次10条侧传(1-1)*10=0",required = true) @RequestParam("offset") String offset,
    		@ApiParam(name = "status", value = "订单状态 1排队中 2排队成功 3待收款 4已完成",required = true) @RequestParam String status,
    		HttpServletRequest request) {
    	Map<String, String> params = HttpServletRequestUtils.getParams(request);
    	//params.put("customerId", "481474");
    	User user = TokenUtil.getUser(request);
    	params.put("customerId", user.getCustomerId().toString());
        params.put("pageSize",limit);
        params.put("page",offset);
    	RemoteKlgService remoteKlgService = hry.util.common.SpringUtil.getBean("remoteKlgService");
    	return remoteKlgService.fingSellListByCustomerId(params);
    }
}
