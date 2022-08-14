package hry.admin.check.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import hry.bean.JsonResult;
import hry.calculate.remote.settlement.RemoteAppReportSettlementCheckService;
import hry.core.annotation.base.MethodName;
import hry.redis.common.utils.RedisService;
import hry.util.SpringUtil;
import hry.util.springmvcPropertyeditor.DateTimePropertyEditorSupport;
import hry.util.springmvcPropertyeditor.StringPropertyEditorSupport;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/reidscheck")
public class RedisCheckController {

    private final static Logger log = Logger.getLogger(RedisCheckController.class);


    @Autowired
    private RedisService redisService;

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


    @MethodName(name = "基于sureold对单个客户余额核算展示有错误正确的的客户信息")
    @RequestMapping(value = "/culAccountByCustomerErrorAndRightInfosureold/{ids}")
    @ResponseBody
    public List<Map<String, Object>> culAccountByCustomerErrorAndRightInfosureold(@PathVariable String[] ids) {
        System.out.println("进如admin..................");
        RemoteAppReportSettlementCheckService remoteAppReportSettlementCheckService = SpringUtil.getBean("remoteAppReportSettlementCheckService");
        List<Map<String, Object>> list = remoteAppReportSettlementCheckService.culRedisAndSqlAccountByCustomer(ids, true);
        return list;
    }

    @MethodName(name = "基于sureold对单个客户余额核算并保存到数据库")
    @RequestMapping(value = "/culAccountByCustomersureold/{ids}")
    @ResponseBody
    public JsonResult culAccountByCustomersureold(@PathVariable String[] ids) {
        JsonResult jsonResult = new JsonResult();
        RemoteAppReportSettlementCheckService remoteAppReportSettlementCheckService = SpringUtil.getBean("remoteAppReportSettlementCheckService");
        remoteAppReportSettlementCheckService.culRedisAndSqlToSqlAccountByCustomer(ids);
        jsonResult.setSuccess(true);
        return jsonResult;

    }


    @MethodName(name = "基于sureold全部余额核算有错误的客户信息ToRedis")
    @RequestMapping(value = "/culSureOldAccountAllCustomerErrorInfoToRedis")
    @ResponseBody
    public JsonResult culSureOldAccountAllCustomerErrorInfoToRedis(HttpServletRequest request) {
        String daysstr = request.getParameter("days");
        RemoteAppReportSettlementCheckService remoteAppReportSettlementCheckService = SpringUtil.getBean("remoteAppReportSettlementCheckService");
        List<Map<String, Object>> list = remoteAppReportSettlementCheckService.culRedisAndSqlSureOldAccountAllCustomerErrorInfo(StringUtils.isEmpty(daysstr) ? null : Integer.valueOf(daysstr));
        JsonResult jsonResult = new JsonResult();
        jsonResult.setSuccess(true);
        jsonResult.setMsg("计算完成已经保存到Redis，一共" + list.size() + "条");
        return jsonResult;
    }


    @MethodName(name="基于sureold对单个客户余额核算展示有错误的客户信息")
    @RequestMapping(value="/culAccountByCustomerErrorInfosureold/{ids}")
    @ResponseBody
    public List<Map<String,Object>> culAccountByCustomerErrorInfosureold(@PathVariable String[] ids){
        RemoteAppReportSettlementCheckService remoteAppReportSettlementCheckService = SpringUtil.getBean("remoteAppReportSettlementCheckService");
        System.out.println("开始核算"+ids);
        List<Map<String,Object>> list=remoteAppReportSettlementCheckService.culRedisAndSqlAccountByCustomer(ids,false);
        return list;
    }

    @MethodName(name="全部余额核算展示有错误的客户信息Redis")
    @RequestMapping(value="/culAccountAllCustomerErrorInfo")
    @ResponseBody
    public JSONArray culAccountAllCustomerErrorInfo(HttpServletRequest request){
        String str = redisService.get("user_fund_check_all_redisansql_6.0");
        JSONArray list= JSON.parseArray(str);
        return list;
    }

    @MethodName(name = "跳到余额详情页面")
    @RequestMapping(value = "/yueinfoview/{id}")
    public ModelAndView yueinfoview(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView("admin/customer/redischeckyueinfo");
        mv.addObject("customerId", id);
        return mv;
    }

    @MethodName(name = "余额核算错误信息")
    @RequestMapping(value = "/yueinfoerrorview/{id}")
    public ModelAndView yueinfoerrorview(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView("admin/customer/redischeckyueinfoerror");
        mv.addObject("customerId", id);
        return mv;
    }


}
