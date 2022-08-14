/**
 * Copyright:
 *
 * @author: tianpengyu
 * @version: V1.0
 * @Date: 2018-09-12 20:31:39
 */
package hry.admin.exchange.controller;


import com.alibaba.fastjson.JSONObject;
import hry.admin.customer.model.AppCustomer;
import hry.admin.customer.model.AppPersonInfo;
import hry.admin.customer.service.AppCustomerService;
import hry.admin.customer.service.AppPersonInfoService;
import hry.admin.exchange.model.ExCointoCoin;
import hry.admin.exchange.model.ExRobot;
import hry.admin.exchange.model.ExRobotDeep;
import hry.admin.exchange.model.ExRobotLog;
import hry.admin.exchange.service.ExCointoCoinService;
import hry.admin.exchange.service.ExRobotDeepService;
import hry.admin.exchange.service.ExRobotLogService;
import hry.admin.exchange.service.impl.HryCurrentPriceByApiRunable;
import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.annotation.CommonLog;
import hry.core.annotation.base.MethodName;
import hry.core.mvc.controller.base.BaseController;
import hry.core.mvc.service.base.BaseService;
import hry.core.shiro.PasswordHelper;
import hry.front.redis.model.UserRedis;
import hry.redis.common.dao.RedisUtil;
import hry.redis.common.utils.RedisService;
import hry.trade.redis.model.AppAccountRedis;
import hry.trade.redis.model.EntrustByUser;
import hry.trade.redis.model.EntrustTrade;
import hry.trade.redis.model.ExDigitalmoneyAccountRedis;
import hry.util.QueryFilter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import hry.util.UserRedisUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Copyright:   互融云
 * @author: tianpengyu
 * @version: V1.0
 * @Date: 2018-09-12 20:31:39
 */
@Controller
@RequestMapping("/exchange/exrobotdeep")
public class ExRobotDeepController extends BaseController<ExRobotDeep, Long> {
    public static String exRobotKey = "HRY:EXCHANGE:exRobot";
    ExecutorService executor = Executors.newSingleThreadExecutor();

    @Resource(name = "exRobotDeepService")
    @Override
    public void setService(BaseService<ExRobotDeep, Long> service) {
        super.service = service;
    }

    @Resource
    private RedisService redisService;
    @Resource
    private ExRobotLogService exRobotLogService;
    @Resource
    private AppCustomerService appCustomerService;
    @Resource
    private AppPersonInfoService appPersonInfoService;
    @Resource
    private ExCointoCoinService exCointoCoinService;


    @RequestMapping(value = "/see/{id}")
    public ModelAndView see(@PathVariable Long id) {
        ExRobotDeep exRobotDeep = service.get(id);
        ModelAndView mav = new ModelAndView("/admin/exchange/exrobotdeepsee");
        mav.addObject("model", exRobotDeep);
        return mav;
    }


    @RequestMapping(value = "/add")
    @ResponseBody
    public JsonResult add(HttpServletRequest request, ExRobotDeep exRobotDeep) {
        return super.save(exRobotDeep);
    }

    @RequestMapping(value = "/modifysee/{id}")
    public ModelAndView modifysee(@PathVariable Long id) {
        ExRobotDeep exRobotDeep = service.get(id);
        ModelAndView mav = new ModelAndView("/admin/exchange/exrobotdeepmodify");
        mav.addObject("model", exRobotDeep);

        return mav;
    }

    @RequestMapping(value = "/modify")
    @ResponseBody
    @CommonLog(name = "深度机器人修改")
    public JsonResult modify(HttpServletRequest request, ExRobotDeep exRobotDeep) {
        String coinCode = exRobotDeep.getCoinCode().split("/")[0];
        String fixCoin = exRobotDeep.getCoinCode().split("/")[1];
        exRobotDeep.setCoinCode(coinCode);
        exRobotDeep.setFixPriceCoinCode(fixCoin);

        ExRobotLog exRobotLog = new ExRobotLog();
        exRobotLog.setRemark("开启深度机器人");

        return super.update(exRobotDeep);
    }


    @RequestMapping(value = "/remove")
    @ResponseBody
    public JsonResult remove(String ids) {
        return super.deleteBatch(ids);
    }

    @RequestMapping(value = "/list")
    @ResponseBody
    public PageResult list(HttpServletRequest request) {
        QueryFilter filter = new QueryFilter(ExRobotDeep.class, request);
        filter.setOrderby("fixPriceCoinCode asc,coinCode asc");
        if(!StringUtils.isEmpty(request.getParameter("isSratAuto"))){
            filter.addFilter("isSratAuto=", request.getParameter("isSratAuto"));
        }
        PageResult pageResult = super.findPage(filter);
        List<ExRobotDeep> list = pageResult.getRows();
        if(null != list && list.size()>0)
        list.forEach(exRobotDeep -> {
            if(exRobotDeep!=null && null !=exRobotDeep.getCustomerId()) {
                //查询机器人当前委托
                Map<String, BigDecimal> map = getTotal(exRobotDeep.getCustomerId().toString(),exRobotDeep.getCoinCode(),exRobotDeep.getFixPriceCoinCode());
                if(map!=null && map.size()>0){
                    exRobotDeep.setSellingNumer(map.get("selling"));
                    exRobotDeep.setBuyingNumer(map.get("buying"));
                }
            }
            System.out.println(JSONObject.toJSONString(exRobotDeep));

            //查询机器人已成交委托
            String buykeycoin = exRobotDeep.getCoinCode() + "_" + exRobotDeep.getFixPriceCoinCode()+":deeprobot:buydeeprobotCounted";
            String sellkeycoin = exRobotDeep.getCoinCode() + "_" + exRobotDeep.getFixPriceCoinCode()+":deeprobot:selldeeprobotCounted";

            String buyed = redisService.get(buykeycoin);
            String selled = redisService.get(sellkeycoin);
            if(! StringUtils.isEmpty(buyed)){
                exRobotDeep.setBuyedNumer(new BigDecimal(buyed));
            }
            if(! StringUtils.isEmpty(selled)){
                exRobotDeep.setSelledNumer(new BigDecimal(selled));
            }
        });
        pageResult.setRows(list);
        return pageResult;
    }

    //获取机器人当前委托
    public Map<String,BigDecimal> getTotal(String customerId,String coinCode,String fixCoin){
        Map<String,BigDecimal> map = new HashMap<>(3);
        RedisUtil redisUtilEntrustByUser = new RedisUtil(EntrustByUser.class);
        EntrustByUser ebu = (EntrustByUser) redisUtilEntrustByUser.get(customerId);
        List<EntrustTrade> listing=new ArrayList<EntrustTrade>();
        if(null!=ebu){
            Map<String, List<EntrustTrade>> getEntrustingmap=ebu.getEntrustingmap();
            listing=getEntrustingmap.get(coinCode+"_"+fixCoin);
        }
        BigDecimal buying=new BigDecimal("0");
        BigDecimal selling=new BigDecimal("0");
        if(listing!=null && listing.size()>0)
        for(EntrustTrade ing:listing){
            if(ing.getType().intValue()==1){
                selling=selling.add(ing.getSurplusEntrustCount());
            }
            if(ing.getType().intValue()==2){
                buying=buying.add(ing.getSurplusEntrustCount());
            }
        }
        map.put("selling",selling);
        map.put("buying",buying);
        return map;
    }

    @MethodName(name = "开启交易")
    @RequestMapping("/startAuto")
    @ResponseBody
    @CommonLog(name = "开启深度机器人交易")
    public JsonResult startAuto(HttpServletRequest request) {
        String ids = request.getParameter("ids");
        try {
            for (String id : ids.split(",")) {
                if (id != null) {
                    QueryFilter exRobotfilter = new QueryFilter(ExRobot.class);
                    exRobotfilter.addFilter("id=", id);
                    //获取开启对象
                    ExRobotDeep exRobot = ((ExRobotDeepService) service).get(exRobotfilter);
                    if (exRobot == null) {
                        return returnJsonResult("自动交易账号不存在", false);
                    }
                    if (exRobot.getIsSratAuto() == 1) {
                        return returnJsonResult("自动交易已开启!", false);
                    }
                    if (StringUtils.isEmpty(exRobot.getBuyDeep()) || StringUtils.isEmpty(exRobot.getSellDeep()) || StringUtils.isEmpty(exRobot.getEveryEntrustCount()) || StringUtils.isEmpty(exRobot.getStopLine())) {
                        return returnJsonResult("请先配置该机器人!", false);
                    }

                    QueryFilter appCustomerFilter = new QueryFilter(AppCustomer.class);
                    appCustomerFilter.addFilter("id=", exRobot.getCustomerId());
                    AppCustomer appCustomer = appCustomerService.get(appCustomerFilter);
                    if (appCustomer == null) {
                        return returnJsonResult("未绑定交易账号", false);
                    }

                    //查看交易对是否开启
                    QueryFilter queryFilter = new QueryFilter(ExCointoCoin.class);
                    queryFilter.addFilter("coinCode=", exRobot.getCoinCode());
                    queryFilter.addFilter("fixPriceCoinCode=", exRobot.getFixPriceCoinCode());
                    ExCointoCoin exCointoCoin = exCointoCoinService.get(queryFilter);
                    if (null == exCointoCoin || "0".equals(exCointoCoin.getState())) {
                        return returnJsonResult("该交易对未开启:" + exRobot.getCoinCode() + "_" + exRobot.getFixPriceCoinCode(), false);
                    }

                    //如果是用第三方，要测试下接口通不通

                    /*JsonResult jsonResult = call(exRobot.getCoinCode(), exRobot.getFixPriceCoinCode());
                    if (!jsonResult.getSuccess()) {
                        return jsonResult;
                    }*/

                    //自动交易的账号和密码都没有问题的话则开启自动交易
                    exRobot.setIsSratAuto(1);
                    service.update(exRobot);


                    //每次开启都增加一条机器人日志
                    ExRobotLog exRobotLog = new ExRobotLog();
                    exRobotLog.setRemark("1");
                    exRobotLog.setCoinCode(exCointoCoin.getCoinCode());
                    exRobotLog.setFixCoin(exCointoCoin.getFixPriceCoinCode());
                    exRobotLog.setOpenTime(new Date());
                    exRobotLog.setRobotId(exRobot.getId().toString());
                    exRobotLog.setEmail(exRobot.getEmail());
                    exRobotLog.setPhone(exRobot.getMobilePhone());
                    exRobotLogService.save(exRobotLog);

                }
            }
            return returnJsonResult("开启自动交易成功！", true);

        } catch (Exception e) {
            e.printStackTrace();
            return returnJsonResult("开启失败！", false);
        }

    }


    //根据交易对获取价格
    public JsonResult call(String coinCode, String fixCoin) {
        HryCurrentPriceByApiRunable runable = new HryCurrentPriceByApiRunable(coinCode, fixCoin);
        Future<JsonResult> resultFuture = executor.submit(runable);
        JsonResult jsonResult = null;
        try {
            jsonResult = resultFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return jsonResult;
    }

    @MethodName(name = "关闭自动交易")
    @RequestMapping(value = "/closeAuto/{ids}")
    @ResponseBody
    @CommonLog(name = "关闭深度机器人交易")
    public JsonResult closeAuto(@PathVariable String ids) {
        try {
            String[] split = ids.split(",");
            for (String id : split) {
                //删除下单总量
                QueryFilter queryFilter = new QueryFilter(ExRobotDeep.class);
                queryFilter.addFilter("id=", id);
                ExRobotDeep exRobot = service.get(queryFilter);
                exRobot.setIsSratAuto(0);
                service.update(exRobot);

                QueryFilter queryFilter1 = new QueryFilter(ExRobotLog.class);
                queryFilter1.addFilter("robotId=", exRobot.getId());
                queryFilter1.addFilter("remark=", "1");
                ExRobotLog exRobotLog = exRobotLogService.get(queryFilter1);
                if(null == exRobotLog){
                    exRobotLog = new ExRobotLog();
                }
                exRobotLog.setCloseTime(new Date());
                exRobotLog.setRemark("2");


                Map<String, BigDecimal> map = getTotal(exRobot.getCustomerId().toString(),exRobot.getCoinCode(),exRobot.getFixPriceCoinCode());
                if(map!=null && map.size()>0){
                    exRobot.setSellingNumer(map.get("selling"));
                    exRobot.setBuyingNumer(map.get("buying"));
                }

                //查询机器人已成交委托
                String buykeycoin = exRobot.getCoinCode() + "_" + exRobot.getFixPriceCoinCode()+":deeprobot:buydeeprobotCounted";
                String sellkeycoin = exRobot.getCoinCode() + "_" + exRobot.getFixPriceCoinCode()+":deeprobot:selldeeprobotCounted";


                String buyed = redisService.get(buykeycoin);
                buyed = StringUtils.isEmpty(buyed) ? "0" : buyed;
                String selled = redisService.get(sellkeycoin);
                selled = StringUtils.isEmpty(selled) ? "0" : selled;
                exRobotLog.setBuyTotalNum(new BigDecimal(buyed));
                exRobotLog.setSellTotalNum(new BigDecimal(selled));


                exRobotLogService.update(exRobotLog);


            }


            return returnJsonResult("", true);
        } catch (Exception e) {
            e.printStackTrace();
            return returnJsonResult("自动交易关闭失败!", false);
        }
    }




    //批量设定账号
    @RequestMapping(value = "/setAccount")
    @ResponseBody
    @CommonLog(name = "批量设定机器人账号")
    public JsonResult setAccount(HttpServletRequest request) {
        JsonResult jsonResult = new JsonResult();
        String ids = request.getParameter("id");
        String account = request.getParameter("account");
        String pwd = request.getParameter("pwd");

        if (StringUtils.isEmpty(account)) {
            jsonResult.setMsg("账号不能为空");
            jsonResult.setSuccess(false);
            return jsonResult;
        }
        if (StringUtils.isEmpty(pwd)) {
            jsonResult.setMsg("密码不能为空");
            jsonResult.setSuccess(false);
            return jsonResult;
        }

        QueryFilter appPersonInfofilter = new QueryFilter(AppPersonInfo.class);
        appPersonInfofilter.addFilter("email=", account);
        AppPersonInfo appPersonInfoemail = appPersonInfoService.get(appPersonInfofilter);
        if (null == appPersonInfoemail) {

            QueryFilter phoneappPersonInfofilter = new QueryFilter(AppPersonInfo.class);
            phoneappPersonInfofilter.addFilter("mobilePhone=", account);
            appPersonInfoemail = appPersonInfoService.get(phoneappPersonInfofilter);
            if (null == appPersonInfoemail) {
                return returnJsonResult("自动交易账号不存在", false);
            }
        }

        AppCustomer appCustomer = appCustomerService.get(appPersonInfoemail.getCustomerId());

        String encryString = new PasswordHelper().encryString(pwd, appCustomer.getSalt());
        if (!appCustomer.getPassWord().equals(encryString)) {
            jsonResult.setMsg("密码不正确");
            jsonResult.setSuccess(false);
            return jsonResult;
        }

        AppPersonInfo finalAppPersonInfoemail = appPersonInfoemail; //好奇怪的写法
        Arrays.asList(ids.split(",")).forEach(id -> {
            ExRobotDeep exRobot = service.get(Long.valueOf(id));
            exRobot.setAutoUsername(appCustomer.getUsername());
            exRobot.setCustomerId(finalAppPersonInfoemail.getCustomerId());
            service.update(exRobot);
        });

        return jsonResult.setSuccess(true).setMsg("设定成功");
    }

    private JsonResult returnJsonResult(String msg, Boolean flag) {
        JsonResult jsonResult = new JsonResult();
        jsonResult.setMsg(msg);
        jsonResult.setSuccess(flag);
        return jsonResult;
    }

    @RequestMapping(value = "/accountInfoList")
    @ResponseBody
    public PageResult accountInfoList(HttpServletRequest request) {
        Integer robotType = Integer.valueOf(request.getParameter("robotType"));

        QueryFilter filter = new QueryFilter(ExRobotDeep.class, request);
        filter.addFilter("robotType=", robotType);
        PageResult page = super.findPage(filter);
        List<ExRobotDeep> list = (List<ExRobotDeep>) page.getRows();
        for (ExRobotDeep ex : list) {
            QueryFilter appCustomerFilter = new QueryFilter(AppCustomer.class);
            appCustomerFilter.addFilter("id=", ex.getCustomerId());
            AppCustomer appCustomer = appCustomerService.get(appCustomerFilter);
            Long customerId = null;
            if (appCustomer != null) {
                customerId = appCustomer.getId();
                QueryFilter appPersonInfofilter = new QueryFilter(AppPersonInfo.class);
                appPersonInfofilter.addFilter("customerId=", customerId);
                AppPersonInfo appPersonInfoemail = appPersonInfoService.get(appPersonInfofilter);
                if (null != appPersonInfoemail) {
                    ex.setMobilePhone(appPersonInfoemail.getMobilePhone());
                    ex.setEmail(appPersonInfoemail.getEmail());
                }
                //取缓存余额
                RedisUtil<UserRedis> redisUtil = new RedisUtil<UserRedis>(UserRedis.class);
                UserRedis userRedis = redisUtil.get(customerId.toString());
                if (null != userRedis) {
                    // 获取资金账户，判断资金账户余额
                    AppAccountRedis accountRedis = UserRedisUtils.getAccount(userRedis.getAccountId().toString());

                    // 获取币账户
                    Map<String, Long> dmAccountId = userRedis.getDmAccountId();
                    for (Map.Entry<String, Long> entry : dmAccountId.entrySet()) {
                        if (entry.getKey() != null && entry.getValue() != null) {
                            ExDigitalmoneyAccountRedis ear = UserRedisUtils.getAccount(entry.getValue().toString(), entry.getKey());
                            if (ear != null && ear.getCoinCode().equals(ex.getCoinCode())) {
                                ex.setCoinCodeNumer(ear.getHotMoney());
                            }
                            if (ear != null && ear.getCoinCode().equals(ex.getFixPriceCoinCode())) {
                                ex.setFixCoinCodeNumer(ear.getHotMoney());
                            }
                        }
                    }
                }


            }


        }
		/*Map<String, String> map = redisService.getMap(exRobotKey);
		if (map.isEmpty()) {
			//更新redis中的数据
			((exRobotDeepService) service).updateExRobotToRedis();
		}*/
        return page;
    }


}
