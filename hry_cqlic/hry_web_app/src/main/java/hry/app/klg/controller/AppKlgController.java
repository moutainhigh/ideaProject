
package hry.app.klg.controller;

import com.alibaba.fastjson.JSONObject;
import hry.app.jwt.TokenUtil;
import hry.bean.ApiJsonResult;
import hry.bean.FrontPage;
import hry.bean.JsonResult;
import hry.core.annotation.NoLogin;
import hry.klg.remote.RemoteKlgService;
import hry.manage.remote.model.User;
import hry.util.CheckPayPassword;
import hry.util.SpringUtil;
import hry.util.StringUtil;
import hry.util.http.HttpsRequest;
import hry.util.properties.PropertiesUtils;
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
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/klg")
@Api(value = "App操作类", description = "KLG - 快乐购预约买卖", tags = "KLG- 快乐购预约买卖")
public class AppKlgController {
    @Resource
    private RemoteKlgService  remoteKlgService;
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
     * 预约买入 支付20%
     * @param request
     * @param number
     * @param password
     * @param isTrusteeship
     * @return
     */
    @ApiOperation(value = "预约买入", httpMethod = "POST", response = ApiJsonResult.class, notes = "预约买入")
    @RequestMapping("/user/appointmentPurchase")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
    @RequiresAuthentication
    public JsonResult appointmentPurchase(HttpServletRequest request,
                                          @ApiParam(name = "number", value = "购买数量",required = true) @RequestParam(required = true) String number,/*
                                          @ApiParam(name = "password", value = "支付密码",required = true) @RequestParam(required = true) String password,*/
                                          @ApiParam(name = "isTrusteeship", value = "是否托管  1否 2是",required = false) @RequestParam(required = false,defaultValue = "1") String isTrusteeship) {
        JsonResult jsonResult = new JsonResult();
        User user = TokenUtil.getUser(request);
        //  校验支付密码
     /*   boolean check= CheckPayPassword.checkPayPassword(password,user);
        if(!check){
            return new JsonResult().setSuccess(false).setMsg(hry.util.common.SpringUtil.diff("mimacuowu"));
        }*/
      //判断是否实名
        if (user.getStates() != 2) {
        	jsonResult.setMsg(hry.util.common.SpringUtil.diff("qingxianshimingrenzheng"));
        	jsonResult.setSuccess(false);
            return jsonResult;
        }
        String  customerId=user.getCustomerId().toString();
        RemoteKlgService remoteKlgService= hry.util.SpringUtil.getBean("remoteKlgService");
        Map<String,String > params=new HashMap<String,String>();
        params.put("isTrusteeship",isTrusteeship);//是否托管
        params.put("number",number);//购买数量
        params.put("customerId",customerId);//用户Id
        jsonResult=remoteKlgService.appointmentPurchase(params);
        return jsonResult;
    }

    @ApiOperation(value = "预约卖出", httpMethod = "POST", response = ApiJsonResult.class, notes = "预约卖出")
    @PostMapping("/user/appointmentSell")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
    @RequiresAuthentication
    public JsonResult appointmentSell(HttpServletRequest request,
                                      @ApiParam(name = "number", value = "购买数量",required = true) @RequestParam(required = true) String number,
                                      @ApiParam(name = "sellDay", value = "卖出时长",required = true) @RequestParam(required = true) String sellDay
            /*,
                                      @ApiParam(name = "password", value = "支付密码",required = true) @RequestParam(required = true) String password*/) {
        User user = TokenUtil.getUser(request);
        //  校验支付密码
   /*     boolean check= CheckPayPassword.checkPayPassword(password,user);
        if(!check){
            return new JsonResult().setSuccess(false).setMsg(hry.util.common.SpringUtil.diff("mimacuowu"));
        }
*/
        String  customerId=user.getCustomerId().toString();
        JsonResult jsonResult = new JsonResult();
      //判断是否实名
        if (user.getStates() != 2) {
        	jsonResult.setMsg(hry.util.common.SpringUtil.diff("qingxianshimingrenzheng"));
        	jsonResult.setSuccess(false);
            return jsonResult;
        }
        RemoteKlgService remoteKlgService= SpringUtil.getBean("remoteKlgService");
        Map<String,String > params=new HashMap<String,String>();
        params.put("number",number);//卖出数量
        params.put("customerId",customerId);//用户Id
        params.put("sellDay",sellDay);//卖出时长
        jsonResult=remoteKlgService.appointmentSell(params);
        return jsonResult;
    }

    @RequestMapping(value = "/user/transferAccounts", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ApiOperation(value = "转账操作", httpMethod = "POST", response = JsonResult.class, notes = "toAccount:收入方账户 coinCode:转账币种  number：转账的数量 password:支付密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
    @ResponseBody
    @RequiresAuthentication
    public JsonResult transferAccounts(HttpServletRequest request, @ApiParam(name = "number", value = "转账的数量", required = true) @RequestParam String number,
                                       @ApiParam(name = "password", value = "支付密码", required = true) @RequestParam String password,
                                       @ApiParam(name = "coinCode", value = "转账币种", required = true) @RequestParam String coinCode,
                                       @ApiParam(name = "toAccount", value = "收入方账户", required = true) @RequestParam String toAccount) {
        User user = TokenUtil.getUser(request);
        if (user != null) {
            //  校验支付密码
            boolean check= CheckPayPassword.checkPayPassword(password,user);
            if(!check){
                return new JsonResult().setSuccess(false).setMsg(hry.util.common.SpringUtil.diff("mimacuowu"));
            }
            RemoteKlgService remoteKlgService= SpringUtil.getBean("remoteKlgService");
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("number", number);//转账的数量
            map.put("coinCode", coinCode);//转账的币种
            map.put("toAccount", toAccount);//收入方账户
            map.put("customerId", user.getCustomerId().toString());
            JsonResult jsonResult=remoteKlgService.transferAccounts(map);
            return  jsonResult;
        }
        return new JsonResult().setSuccess(false).setMsg(hry.util.common.SpringUtil.diff("before_login"));
    }



    @ApiOperation(value = "获取卖出信息", httpMethod = "POST", response = ApiJsonResult.class, notes = "获取卖出信息")
    @PostMapping("/user/getMySellInfo")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
    @ResponseBody
    public JsonResult getMySellInfo(HttpServletRequest request) {
        User user = TokenUtil.getUser(request);
        String  customerId=user.getCustomerId().toString();
        JsonResult jsonResult = new JsonResult();
        RemoteKlgService remoteKlgService= SpringUtil.getBean("remoteKlgService");
        Map<String,String > params=new HashMap<String,String>();
        params.put("customerId",customerId);//用户Id
        jsonResult=remoteKlgService.getMySellInfo(params);
        return jsonResult;
    }

    @ApiOperation(value = "获取买入信息", httpMethod = "POST", response = ApiJsonResult.class, notes = "获取买入信息")
    @PostMapping("/user/getMyPurchaseInfo")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
    @ResponseBody
    public JsonResult getMyPurchaseInfo(HttpServletRequest request) {
        User user = TokenUtil.getUser(request);
        String  customerId=user.getCustomerId().toString();
        JsonResult jsonResult = new JsonResult();
        RemoteKlgService remoteKlgService= SpringUtil.getBean("remoteKlgService");
        Map<String,String > params=new HashMap<String,String>();
        params.put("customerId",customerId);//用户Id
        jsonResult=remoteKlgService.getMyPurchaseInfo(params);
        return jsonResult;
    }


    @ApiOperation(value = "获取可升级列表", httpMethod = "POST", response = ApiJsonResult.class, notes = "获取买入信息")
    @PostMapping("/user/getUpgradeInfoList")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
    @ResponseBody
    public JsonResult getUpgradeInfoList(HttpServletRequest request) {
        User user = TokenUtil.getUser(request);
        String  customerId=user.getCustomerId().toString();
        JsonResult jsonResult = new JsonResult();
        RemoteKlgService remoteKlgService= SpringUtil.getBean("remoteKlgService");
        Map<String,String > params=new HashMap<String,String>();
        params.put("customerId",customerId);//用户Id
        jsonResult=remoteKlgService.getUpgradeInfoList(params);
        return jsonResult;
    }
    @ApiOperation(value = "会员升级操作", httpMethod = "POST", response = ApiJsonResult.class, notes = "获取买入信息")
    @PostMapping("/user/upgradeUserLevel")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
    @ResponseBody
    public JsonResult upgradeUserLevel(@ApiParam(name = "levelId", value = "选中升级的等级ID", required = true) @RequestParam String levelId,
                                       HttpServletRequest request) {//
        User user = TokenUtil.getUser(request);
        String  customerId=user.getCustomerId().toString();
        JsonResult jsonResult = new JsonResult();
        RemoteKlgService remoteKlgService= SpringUtil.getBean("remoteKlgService");
        Map<String,String > params=new HashMap<String,String>();
        params.put("customerId",customerId);//用户Id
        params.put("levelId",levelId);//选中升级的等级ID
        jsonResult=remoteKlgService.upgradeUserLevel(params);
        return jsonResult;
    }



    @PostMapping("/getBuyInfoList")
    @ApiOperation(value = "预约买入记录", httpMethod = "POST", response = JsonResult.class,notes = "pageSize：页条数 page：当前页")
    @ResponseBody
    public FrontPage getBuyInfoList(HttpServletRequest request, @ApiParam(name = "page", value = "当前页", required = true) @RequestParam Integer page,
                                          @ApiParam(name = "pageSize", value = "页条数", required = true) @RequestParam Integer pageSize) {
         HashMap<String, String> map = new HashMap<String, String>();
            map.put("page", String.valueOf(page));
            map.put("pageSize",String.valueOf(pageSize));
            return   remoteKlgService.getBuyInfoList(map);
    }
    @PostMapping("/getSellInfoList")
    @ApiOperation(value = "预约卖出记录", httpMethod = "POST", response = JsonResult.class,notes = "pageSize：页条数 page：当前页")
    @ResponseBody
    public FrontPage getSellInfoList(HttpServletRequest request, @ApiParam(name = "page", value = "当前页", required = true) @RequestParam Integer page,
                                    @ApiParam(name = "pageSize", value = "页条数", required = true) @RequestParam Integer pageSize) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("page", String.valueOf(page));
        map.put("pageSize",String.valueOf(pageSize));
        return   remoteKlgService.getSellInfoList(map);
    }

    @PostMapping("/user/getMyTransactionflow")
    @ApiOperation(value = "我的交易流水", httpMethod = "POST", response = JsonResult.class,notes = "pageSize：页条数 page：当前页")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
    public FrontPage getMyTransactionflow(HttpServletRequest request, @ApiParam(name = "page", value = "当前页", required = true) @RequestParam Integer page,
                                     @ApiParam(name = "pageSize", value = "页条数", required = true) @RequestParam Integer pageSize,
                                          @ApiParam(name = "coinCode", value = "币种名称", required = false) @RequestParam String coinCode) {
        HashMap<String, String> map = new HashMap<String, String>();
        User user = TokenUtil.getUser(request);
        String  customerId=user.getCustomerId().toString();
        map.put("page", String.valueOf(page));
        map.put("pageSize",String.valueOf(pageSize));
        map.put("customerId",customerId);
         if(StringUtil.isNull(coinCode)){
             map.put("coinCode",coinCode);
         }
        return   remoteKlgService.getMyTransactionflow(map);
    }

    @ApiOperation(value = "获取用户等级信息", httpMethod = "POST", response = ApiJsonResult.class, notes = "获取用户等级信息")
    @PostMapping("/user/getCustomerLevelInfo")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
    @ResponseBody
    public JsonResult getCustomerLevelInfo(HttpServletRequest request) {//
        User user = TokenUtil.getUser(request);
        String  customerId=user.getCustomerId().toString();
        JsonResult jsonResult = new JsonResult();
        RemoteKlgService remoteKlgService= SpringUtil.getBean("remoteKlgService");
        Map<String,String > params=new HashMap<String,String>();
        params.put("customerId",customerId);//用户Id
        jsonResult=remoteKlgService.getCustomerLevelInfo(params);
        return jsonResult;
    }

    @ApiOperation(value = "转出交易所获得充币地址", httpMethod = "POST", response = ApiJsonResult.class, notes = "获取用户等级信息")
    @PostMapping("/user/getExchangeCoinAddress")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
    @ResponseBody
    public JsonResult getExchangeCoinAddress(HttpServletRequest request, @ApiParam(name = "coinCode", value = "币种名称", required = true) @RequestParam String coinCode) {//
        User user = TokenUtil.getUser(request);
        String  busNumber=user.getBusNumber();
        String url = PropertiesUtils.APP.getProperty("app.exchangeip");//交易所IP
        String result=null;
        System.out.println("转出交易所获得充币地址");
        try {
            System.out.println("请求获取交易所充币地址==url:"+url + "/coinorder/getCoinAddressByUserName?busNumber=" + busNumber + "&coinCode=" + coinCode);
           result = HttpsRequest.post(url + "/sdk/getCoinAddressByUserName?busNumber=" + busNumber + "&coinCode=" + coinCode);
            System.out.println("请求回调"+result);
        }catch (Exception e){
            e.printStackTrace();
            return new JsonResult().setSuccess(false).setMsg(hry.util.common.SpringUtil.diff("klg_wangluoyichang"));
        }
        JSONObject parseObject = null;
        //TODO 解析 生成的地址信息
        if (!StringUtils.isEmpty(result)) {
            try {
                parseObject = JSONObject.parseObject(result);
            }catch (Exception e){
                e.printStackTrace();
                return new JsonResult().setSuccess(false).setMsg("回调数据异常");
            }
        }
        if(parseObject!=null){
            String success=parseObject.get("success").toString();
            String msg=parseObject.get("msg")==null?null:parseObject.get("msg").toString();
            JsonResult resul=new JsonResult();
            if(success.equals("true")){
                String obj=parseObject.get("obj").toString();
                resul.setSuccess(true).setObj(obj);
            }else{
                String code=parseObject.get("code").toString();
                if("400".equals(code)){
                    msg=hry.util.common.SpringUtil.diff("klg_jiaoyisuoweikaifang");//币种不存在
                }
                resul.setCode(code);
                resul.setSuccess(false);
            }
            resul.setMsg(msg);
            return resul;
        }
        return new JsonResult().setSuccess(false).setMsg(hry.util.common.SpringUtil.diff("klg_wangluoyichang"));//网络异常稍后重试
    }

    public static void main(String[] args) {
        String busNumber="dad";
        String coinCode="USDT";
        String url="http://172.16.10.89:6003/service";
        String result= HttpsRequest.post(url + "/sdk/getCoinAddressByUserName?busNumber=" + busNumber + "&coinCode=" + coinCode);
        System.out.println(result);
    }
}

