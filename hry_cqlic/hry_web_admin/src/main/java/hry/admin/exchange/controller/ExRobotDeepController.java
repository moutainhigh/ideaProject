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
 * Copyright:   ?????????
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
    @CommonLog(name = "?????????????????????")
    public JsonResult modify(HttpServletRequest request, ExRobotDeep exRobotDeep) {
        String coinCode = exRobotDeep.getCoinCode().split("/")[0];
        String fixCoin = exRobotDeep.getCoinCode().split("/")[1];
        exRobotDeep.setCoinCode(coinCode);
        exRobotDeep.setFixPriceCoinCode(fixCoin);

        ExRobotLog exRobotLog = new ExRobotLog();
        exRobotLog.setRemark("?????????????????????");

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
                //???????????????????????????
                Map<String, BigDecimal> map = getTotal(exRobotDeep.getCustomerId().toString(),exRobotDeep.getCoinCode(),exRobotDeep.getFixPriceCoinCode());
                if(map!=null && map.size()>0){
                    exRobotDeep.setSellingNumer(map.get("selling"));
                    exRobotDeep.setBuyingNumer(map.get("buying"));
                }
            }
            System.out.println(JSONObject.toJSONString(exRobotDeep));

            //??????????????????????????????
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

    //???????????????????????????
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

    @MethodName(name = "????????????")
    @RequestMapping("/startAuto")
    @ResponseBody
    @CommonLog(name = "???????????????????????????")
    public JsonResult startAuto(HttpServletRequest request) {
        String ids = request.getParameter("ids");
        try {
            for (String id : ids.split(",")) {
                if (id != null) {
                    QueryFilter exRobotfilter = new QueryFilter(ExRobot.class);
                    exRobotfilter.addFilter("id=", id);
                    //??????????????????
                    ExRobotDeep exRobot = ((ExRobotDeepService) service).get(exRobotfilter);
                    if (exRobot == null) {
                        return returnJsonResult("???????????????????????????", false);
                    }
                    if (exRobot.getIsSratAuto() == 1) {
                        return returnJsonResult("?????????????????????!", false);
                    }
                    if (StringUtils.isEmpty(exRobot.getBuyDeep()) || StringUtils.isEmpty(exRobot.getSellDeep()) || StringUtils.isEmpty(exRobot.getEveryEntrustCount()) || StringUtils.isEmpty(exRobot.getStopLine())) {
                        return returnJsonResult("????????????????????????!", false);
                    }

                    QueryFilter appCustomerFilter = new QueryFilter(AppCustomer.class);
                    appCustomerFilter.addFilter("id=", exRobot.getCustomerId());
                    AppCustomer appCustomer = appCustomerService.get(appCustomerFilter);
                    if (appCustomer == null) {
                        return returnJsonResult("?????????????????????", false);
                    }

                    //???????????????????????????
                    QueryFilter queryFilter = new QueryFilter(ExCointoCoin.class);
                    queryFilter.addFilter("coinCode=", exRobot.getCoinCode());
                    queryFilter.addFilter("fixPriceCoinCode=", exRobot.getFixPriceCoinCode());
                    ExCointoCoin exCointoCoin = exCointoCoinService.get(queryFilter);
                    if (null == exCointoCoin || "0".equals(exCointoCoin.getState())) {
                        return returnJsonResult("?????????????????????:" + exRobot.getCoinCode() + "_" + exRobot.getFixPriceCoinCode(), false);
                    }

                    //???????????????????????????????????????????????????

                    /*JsonResult jsonResult = call(exRobot.getCoinCode(), exRobot.getFixPriceCoinCode());
                    if (!jsonResult.getSuccess()) {
                        return jsonResult;
                    }*/

                    //????????????????????????????????????????????????????????????????????????
                    exRobot.setIsSratAuto(1);
                    service.update(exRobot);


                    //??????????????????????????????????????????
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
            return returnJsonResult("???????????????????????????", true);

        } catch (Exception e) {
            e.printStackTrace();
            return returnJsonResult("???????????????", false);
        }

    }


    //???????????????????????????
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

    @MethodName(name = "??????????????????")
    @RequestMapping(value = "/closeAuto/{ids}")
    @ResponseBody
    @CommonLog(name = "???????????????????????????")
    public JsonResult closeAuto(@PathVariable String ids) {
        try {
            String[] split = ids.split(",");
            for (String id : split) {
                //??????????????????
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

                //??????????????????????????????
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
            return returnJsonResult("????????????????????????!", false);
        }
    }




    //??????????????????
    @RequestMapping(value = "/setAccount")
    @ResponseBody
    @CommonLog(name = "???????????????????????????")
    public JsonResult setAccount(HttpServletRequest request) {
        JsonResult jsonResult = new JsonResult();
        String ids = request.getParameter("id");
        String account = request.getParameter("account");
        String pwd = request.getParameter("pwd");

        if (StringUtils.isEmpty(account)) {
            jsonResult.setMsg("??????????????????");
            jsonResult.setSuccess(false);
            return jsonResult;
        }
        if (StringUtils.isEmpty(pwd)) {
            jsonResult.setMsg("??????????????????");
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
                return returnJsonResult("???????????????????????????", false);
            }
        }

        AppCustomer appCustomer = appCustomerService.get(appPersonInfoemail.getCustomerId());

        String encryString = new PasswordHelper().encryString(pwd, appCustomer.getSalt());
        if (!appCustomer.getPassWord().equals(encryString)) {
            jsonResult.setMsg("???????????????");
            jsonResult.setSuccess(false);
            return jsonResult;
        }

        AppPersonInfo finalAppPersonInfoemail = appPersonInfoemail; //??????????????????
        Arrays.asList(ids.split(",")).forEach(id -> {
            ExRobotDeep exRobot = service.get(Long.valueOf(id));
            exRobot.setAutoUsername(appCustomer.getUsername());
            exRobot.setCustomerId(finalAppPersonInfoemail.getCustomerId());
            service.update(exRobot);
        });

        return jsonResult.setSuccess(true).setMsg("????????????");
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
                //???????????????
                RedisUtil<UserRedis> redisUtil = new RedisUtil<UserRedis>(UserRedis.class);
                UserRedis userRedis = redisUtil.get(customerId.toString());
                if (null != userRedis) {
                    // ?????????????????????????????????????????????
                    AppAccountRedis accountRedis = UserRedisUtils.getAccount(userRedis.getAccountId().toString());

                    // ???????????????
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
			//??????redis????????????
			((exRobotDeepService) service).updateExRobotToRedis();
		}*/
        return page;
    }


}
