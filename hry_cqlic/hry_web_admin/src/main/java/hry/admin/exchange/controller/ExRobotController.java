/**
 * Copyright:
 *
 * @author: liushilei
 * @version: V1.0
 * @Date: 2018-06-12 16:33:44
 */
package hry.admin.exchange.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import hry.admin.customer.model.AppCustomer;
import hry.admin.customer.model.AppPersonInfo;
import hry.admin.customer.service.AppCustomerService;
import hry.admin.customer.service.AppPersonInfoService;
import hry.admin.exchange.model.*;
import hry.admin.exchange.service.*;
import hry.admin.web.model.AppConfig;
import hry.admin.web.service.AppConfigService;
import hry.bean.JsonResult;
import hry.bean.PageResult;
import hry.core.annotation.CommonLog;
import hry.core.annotation.MyRequiresPermissions;
import hry.core.annotation.base.MethodName;
import hry.core.mvc.controller.base.BaseController;
import hry.core.mvc.service.base.BaseService;
import hry.core.shiro.PasswordHelper;
import hry.front.redis.model.UserRedis;
import hry.listener.ServerManagement;
import hry.redis.common.dao.RedisUtil;
import hry.redis.common.utils.RedisService;
import hry.trade.redis.model.AppAccountRedis;
import hry.trade.redis.model.ExDigitalmoneyAccountRedis;
import hry.util.*;
import hry.util.httpRequest.HttpConnectionUtil;
import hry.util.rsa.FXHParam;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.regex.Pattern;

/**
 * Copyright:   ?????????
 *
 * @author: liushilei
 * @version: V1.0
 * @Date: 2018-06-12 16:33:44
 */
@Controller
@RequestMapping("/exchange/exrobot")
public class ExRobotController extends BaseController<ExRobot, Long> {
    private static Logger logger = Logger.getLogger(ExRobotController.class);
    public static String exRobotKey = "HRY:EXCHANGE:exRobot";

    @Resource(name = "exRobotService")
    @Override
    public void setService(BaseService<ExRobot, Long> service) {
        super.service = service;
    }

    @Resource
    private AppPersonInfoService appPersonInfoService;

    @Resource
    private ExRobotLogService exRobotLogService;

    @Resource
    private AppCustomerService appCustomerService;

    @Resource
    private ExCointoCoinService exCointoCoinService;

    @Resource
    private ConmonApiService conmonApiService;

    @Resource
    private RedisService redisService;

    @Resource
    private AppConfigService appConfigService;

    @Resource
    private ExProductService exProductService;

    @Resource
    private ExEntrustService exEntrustService;

    ExecutorService executor = Executors.newSingleThreadExecutor();

    @RequestMapping(value = "/see/{id}")
    public ModelAndView see(@PathVariable Long id) {
        ExRobot exRobot = service.get(id);
        ModelAndView mav = new ModelAndView("/admin/exchange/exrobotsee");
        mav.addObject("model", exRobot);
        return mav;
    }


    @RequestMapping(value = "/add")
    @ResponseBody
    public JsonResult add(HttpServletRequest request, ExRobot exRobot) {
        AppPersonInfo appPersonInfo = new AppPersonInfo();
        String email = exRobot.getAutoUsername();
        QueryFilter appPersonInfofilter = new QueryFilter(AppPersonInfo.class);
        appPersonInfofilter.addFilter("email=", email);
        AppPersonInfo appPersonInfoemail = appPersonInfoService.get(appPersonInfofilter);
        if (null == appPersonInfoemail) {
            String phone = exRobot.getAutoUsername();  //?????????????????????
            QueryFilter phoneappPersonInfofilter = new QueryFilter(AppPersonInfo.class);
            phoneappPersonInfofilter.addFilter("mobilePhone=", phone);
            AppPersonInfo appPersonInfophone = appPersonInfoService.get(phoneappPersonInfofilter);
            if (null == appPersonInfophone) {
                return returnJsonResult("?????????????????????", false);
            } else {
                appPersonInfo = appPersonInfophone;
            }
        } else {
            appPersonInfo = appPersonInfoemail;
        }
        AppCustomer appCustomer = appCustomerService.get(appPersonInfo.getCustomerId());
        exRobot.setAutoUsername(appCustomer.getUsername());
        exRobot.setCustomerId(appCustomer.getId());

        String coinCode = exRobot.getCoinCode();
        //??????????????????????????????  BTC/USDT
        if (coinCode != null) {
            String[] split = coinCode.split("/");
            if (split.length == 2) {
                exRobot.setCoinCode(split[0]);
                exRobot.setFixPriceCoinCode(split[1]);
                QueryFilter exCointoCoinFilter = new QueryFilter(ExCointoCoin.class);
                exCointoCoinFilter.addFilter("coinCode=", split[0]);
                exCointoCoinFilter.addFilter("fixPriceCoinCode=", split[1]);
                ExCointoCoin exCointoCoin = exCointoCoinService.get(exCointoCoinFilter);
                exRobot.setUpFloatPer(null);
                if (exRobot.getAtuoPriceType().intValue() == 2) {
                    BigDecimal upFloatPer = (exRobot.getAutoPriceFloat().add(new BigDecimal(100))).multiply(new BigDecimal(100).subtract(exRobot.getAutoPriceFloat()));
                    if (upFloatPer.compareTo(BigDecimal.ZERO) == 0) {
                        upFloatPer = exCointoCoin.getAveragePrice();
                    }
                    exRobot.setUpFloatPer(new BigDecimal(10000).divide(upFloatPer, 20, BigDecimal.ROUND_DOWN));
                    exRobot.setAutoPrice(new BigDecimal(0));
                }
                exRobot.setFixPriceType(exCointoCoin.getFixPriceType());
            }
        }
        JsonResult jsonResult = super.save(exRobot);
        if (jsonResult.getSuccess()) {
            //??????redis????????????
            ((ExRobotService) service).updateExRobotToRedis();
        }
        return jsonResult;
    }

    @RequestMapping(value = "/modifysee/{id}")
    public ModelAndView modifysee(@PathVariable Long id) {
        ExRobot exRobot = service.get(id);
        ModelAndView mav = new ModelAndView("/admin/exchange/exrobotmodify");
        if (!StringUtils.isEmpty(exRobot.getCustomerId())) {
            QueryFilter queryFilter = new QueryFilter(ExRobot.class);
            queryFilter.addFilter("id=", exRobot.getCustomerId());
            AppCustomer appCustomer = appCustomerService.get(queryFilter);

            QueryFilter f = new QueryFilter(ExRobot.class);
            f.addFilter("customerId=", appCustomer.getId());
            AppPersonInfo appPersonInfo = appPersonInfoService.get(f);

            if (!StringUtils.isEmpty(appPersonInfo.getEmail())) {
                mav.addObject("autousername", appPersonInfo.getEmail());
            } else {
                mav.addObject("autousername", appPersonInfo.getMobilePhone());
            }
        }

        mav.addObject("model", exRobot);
        return mav;
    }

    @RequestMapping(value = "/modify")
    @ResponseBody
    @CommonLog(name = "???????????????")
    public JsonResult modify(HttpServletRequest request, ExRobot exRobot) {
        String coinCode = exRobot.getCoinCode();
        exRobot.setCoinCode(coinCode.split("/")[0]);
        exRobot.setFixPriceCoinCode(coinCode.split("/")[1]);
        if (exRobot.getAtuoPriceType().intValue() == 3) {
            exRobot.setAutoPrice(new BigDecimal("0"));
            exRobot.setAutoPriceFloat(new BigDecimal(0));
        }
        exRobot.setAutoCountFloat(new BigDecimal("50"));  //???????????????????????????50%


        if (exRobot.getAtuoPriceType().intValue() == 2) {
            BigDecimal upFloatPer = (exRobot.getAutoPriceFloat().add(new BigDecimal(100))).multiply(new BigDecimal(100).subtract(exRobot.getAutoPriceFloat()));
            if (upFloatPer.compareTo(BigDecimal.ZERO) != 0) {
                exRobot.setUpFloatPer(new BigDecimal(10000).divide(upFloatPer, 20, BigDecimal.ROUND_DOWN));
            }

        exRobot.setAutoPrice(new BigDecimal(0));
    }
        return super.update(exRobot);
    }


    @RequestMapping(value = "/remove")
    @ResponseBody
    public JsonResult remove(String ids) {
        return super.deleteBatch(ids);
    }

    @RequestMapping(value = "/setAccount")
    @ResponseBody
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
            ExRobot exRobot = service.get(Long.valueOf(id));
            exRobot.setAutoUsername(appCustomer.getUsername());
            exRobot.setCustomerId(finalAppPersonInfoemail.getCustomerId());
            service.update(exRobot);
        });

        return jsonResult.setSuccess(true).setMsg("????????????");
    }


    @RequestMapping(value = "/list")
    @ResponseBody
    public PageResult list(HttpServletRequest request) {
        Integer robotType = Integer.valueOf(request.getParameter("robotType").trim());

        QueryFilter filter = new QueryFilter(ExRobot.class, request);
        filter.addFilter("robotType=", robotType);
        filter.setOrderby("fixPriceCoinCode ASC,coinCode ASC");
        if(!StringUtils.isEmpty(request.getParameter("isSratAuto"))){
            filter.addFilter("isSratAuto=", request.getParameter("isSratAuto"));
        }

        PageResult page = super.findPage(filter);
        List<ExRobot> list = (List<ExRobot>) page.getRows();
        for (ExRobot ex : list) {
            QueryFilter appCustomerFilter = new QueryFilter(AppCustomer.class);
            appCustomerFilter.addFilter("id=", ex.getCustomerId());
            AppCustomer appCustomer = appCustomerService.get(appCustomerFilter);
            Long customerId = null;
            if (appCustomer != null) {
                customerId = appCustomer.getId();
            }

            QueryFilter appPersonInfofilter = new QueryFilter(AppPersonInfo.class);
            appPersonInfofilter.addFilter("customerId=", customerId);
            AppPersonInfo appPersonInfoemail = appPersonInfoService.get(appPersonInfofilter);
            if (null != appPersonInfoemail) {
                ex.setMobilePhone(appPersonInfoemail.getMobilePhone());
                ex.setEmail(appPersonInfoemail.getEmail());
            }

            //??????????????????
            String priceNew = redisService.get(ex.getCoinCode()+"_"+ex.getFixPriceCoinCode()+":currentExchangPrice");
            if(StringUtils.isEmpty(priceNew)){
                priceNew ="0";
            }
            ex.setNowPrice(new BigDecimal(priceNew));



            //5????????????
            // ????????????????????????5????????????
            String coinCode = ex.getCoinCode() + "_" + ex.getFixPriceCoinCode();
            String points = getLineData(coinCode);
            ex.setKlineData(points);


            //???????????????
            String keycoin = redisService.get(ex.getCoinCode() + "_" + ex.getFixPriceCoinCode() + ":klineRobotCount");
            if (StringUtils.isEmpty(keycoin)) {
                keycoin = "0";
            }

            ex.setBuyEntNum(new BigDecimal(keycoin));
            //???????????????
            ex.setSellEntNum(new BigDecimal(keycoin));

        }
        Map<String, String> map = redisService.getMap(exRobotKey);
        if (map.isEmpty()) {
            //??????redis????????????
            ((ExRobotService) service).updateExRobotToRedis();
        }
        return page;
    }


    /**
     * ??????????????????
     *
     * @param coinCode ???????????????
     * @return
     */
    private String getLineData(String coinCode) {
        String returnStr = "";
        if (!StringUtils.isEmpty(coinCode)) {
            String key = coinCode + ":klinedata:TransactionOrder_" + coinCode + "_5";
            String lineData = redisService.get(key);
            if (!StringUtils.isEmpty(lineData)) {
                List<Map> linkConf = JSONArray.parseArray(lineData, Map.class);
                if (linkConf != null && linkConf.size() > 0) {
                    int sum = 0;
                    for (Map map : linkConf) {
                        if (sum < 30) {
                            returnStr += "," + (map.get("endPrice") == null ? "0" : map.get("endPrice").toString());
                        } else {
                            break;
                        }
                        sum++;
                    }
                    return returnStr.substring(1);
                }
            }
        }
        return "";
    }

    @RequestMapping(value = "/monitorList")
    @ResponseBody
    public PageResult monitorList(HttpServletRequest request) {
        Integer robotType = Integer.valueOf(request.getParameter("robotType"));

        QueryFilter filter = new QueryFilter(ExRobot.class, request);
        filter.addFilter("robotType=", robotType);
        filter.setOrderby("fixPriceCoinCode ASC,coinCode ASC");
        PageResult page = super.findPage(filter);
        List<ExRobot> list = (List<ExRobot>) page.getRows();
        for (ExRobot ex : list) {
            QueryFilter appCustomerFilter = new QueryFilter(AppCustomer.class);
            appCustomerFilter.addFilter("userName=", ex.getAutoUsername());
            AppCustomer appCustomer = appCustomerService.get(appCustomerFilter);
            Long customerId = null;
            if (appCustomer != null) {
                customerId = appCustomer.getId();
            }

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
        Map<String, String> map = redisService.getMap(exRobotKey);
        if (map.isEmpty()) {
            //??????redis????????????
            ((ExRobotService) service).updateExRobotToRedis();
        }
        return page;
    }

    @RequestMapping(value = "/accountInfoList")
    @ResponseBody
    public PageResult accountInfoList(HttpServletRequest request) {
        Integer robotType = Integer.valueOf(request.getParameter("robotType"));

        QueryFilter filter = new QueryFilter(ExRobot.class, request);
        filter.addFilter("robotType=", robotType);
        filter.setOrderby("fixPriceCoinCode ASC,coinCode ASC");

        PageResult page = super.findPage(filter);
        List<ExRobot> list = (List<ExRobot>) page.getRows();
        for (ExRobot ex : list) {
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
        Map<String, String> map = redisService.getMap(exRobotKey);
        if (map.isEmpty()) {
            //??????redis????????????
            ((ExRobotService) service).updateExRobotToRedis();
        }
        return page;
    }

    private JsonResult returnJsonResult(String msg, Boolean flag) {
        JsonResult jsonResult = new JsonResult();
        jsonResult.setMsg(msg);
        jsonResult.setSuccess(flag);
        return jsonResult;
    }

    @CommonLog(name = "K????????????-??????")
    @MethodName(name = "????????????")
    @RequestMapping("/startAuto")
    @ResponseBody
    public JsonResult startAuto(HttpServletRequest request) {
        String ids = request.getParameter("ids");
        try {
            for (String id : ids.split(",")) {
                if (id != null) {
                    QueryFilter exRobotfilter = new QueryFilter(ExRobot.class);
                    exRobotfilter.addFilter("id=", id);
                    //??????????????????
                    ExRobot exRobot = ((ExRobotService) service).get(exRobotfilter);
                    if (exRobot == null) {
                        return returnJsonResult("???????????????????????????", false);
                    }
                    if (exRobot.getIsSratAuto() == 1) {
                        return returnJsonResult("?????????????????????!", false);
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
                    if (null == exCointoCoin ||  exCointoCoin.getState().intValue()!=1) {
                        return returnJsonResult("?????????????????????:" + exCointoCoin.getCoinCode() + "_" + exCointoCoin.getFixPriceCoinCode(), false);
                    }


                    //???????????????????????????????????????????????????
                    String apiret = null;
                    if (exRobot.getAtuoPriceType().equals(3)) {
                        apiret = conmonApiService.getCurrentPriceByApi(exRobot);
                        if (null == apiret) {
                            return returnJsonResult("?????????????????????????????????????????????", false);
                        }
                        if ( "????????????".equals(apiret) || apiret.contains("???????????????")) {
                            return returnJsonResult(apiret, false);
                        }
                    }else if(exRobot.getAtuoPriceType().equals(2)){
                        String key = exRobot.getCoinCode()+"_"+exRobot.getFixPriceCoinCode()+":currentExchangPrice";
                        String nowPrice = redisService.get(key);
                        if(StringUtils.isEmpty(nowPrice)){
                            redisService.save(key,exCointoCoin.getAveragePrice().toString());
                        }
                    }

                    //????????????????????????????????????????????????????????????????????????
                    ((ExRobotService) service).startAuto(Long.valueOf(id));

                    //??????redis????????????
                    ((ExRobotService) service).updateExRobotToRedis();

                }
            }
            return returnJsonResult("???????????????????????????", true);

        } catch (Exception e) {
            e.printStackTrace();
            return returnJsonResult("???????????????", false);
        }

    }


    @MethodName(name = "??????????????????")
    @RequestMapping("/resetData")
    @ResponseBody
    public JsonResult resetData(HttpServletRequest request) {
        String ids = request.getParameter("ids");
        try {
            List<ExRobot> list = new ArrayList<>();
            for (String id : ids.split(",")) {
                if (id != null) {
                    QueryFilter exRobotfilter = new QueryFilter(ExRobot.class);
                    exRobotfilter.addFilter("id=", id);
                    //??????????????????
                    ExRobot ex = ((ExRobotService) service).get(exRobotfilter);

                    QueryFilter appCustomerFilter = new QueryFilter(AppCustomer.class);
                    appCustomerFilter.addFilter("id=", ex.getCustomerId());
                    AppCustomer appCustomer = appCustomerService.get(appCustomerFilter);
                    Long customerId = null;
                    if (appCustomer != null) {
                        customerId = appCustomer.getId();
                    }

                    QueryFilter appPersonInfofilter = new QueryFilter(AppPersonInfo.class);
                    appPersonInfofilter.addFilter("customerId=", customerId);
                    AppPersonInfo appPersonInfoemail = appPersonInfoService.get(appPersonInfofilter);
                    if (null != appPersonInfoemail) {
                        ex.setMobilePhone(appPersonInfoemail.getMobilePhone());
                        ex.setEmail(appPersonInfoemail.getEmail());
                    }

                    //??????????????????
                    // ??????????????????
                    BigDecimal[] prices = null;
                    String pushNewListRecordMarket = redisService.get(ex.getCoinCode() + "_" + ex.getFixPriceCoinCode() + ":pushNewListRecordMarketDesc");
                    BigDecimal priceNew = BigDecimal.ZERO;
                    if (!StringUtils.isEmpty(pushNewListRecordMarket)) {
                        MarketTrades marketTrades = JSON.parseObject(pushNewListRecordMarket, MarketTrades.class);
                        // ????????????
                        if (marketTrades != null) {
                            List<MarketTradesSub> trades = marketTrades.getTrades();
                            if (trades != null && trades.size() > 0) {
                                // ????????????????????????
                                priceNew = trades.get(0).getPrice();

                                prices = new BigDecimal[trades.size()];


                                for (int i = 0; i < trades.size(); i++) {
                                    MarketTradesSub marketTradesSub = trades.get(i);
                                    prices[i] = marketTradesSub.getPrice();


                                }

                            }
                        }
                    }
                    ex.setNowPrice(priceNew);


                    //5????????????
                    // ????????????????????????5????????????
                    String coinCode = ex.getCoinCode() + "_" + ex.getFixPriceCoinCode();
                    String points = getLineData(coinCode);
                    ex.setKlineData(points);


                    //???????????????
                    String keycoin = redisService.get(ex.getCoinCode() + "_" + ex.getFixPriceCoinCode() + ":klineRobotCount");
                    if (StringUtils.isEmpty(keycoin)) {
                        keycoin = "0";
                    }

                    ex.setBuyEntNum(new BigDecimal(keycoin));
                    //???????????????
                    ex.setSellEntNum(new BigDecimal(keycoin));

                    list.add(ex);
                }

            }


            return returnJsonResult("???????????????????????????", true);

        } catch (Exception e) {
            e.printStackTrace();
            return returnJsonResult("???????????????", false);
        }

    }


    @MethodName(name = "??????K???")
    @RequestMapping("/cleanKline")
    @ResponseBody
    public JsonResult cleanKline(HttpServletRequest request) {
        String ids = request.getParameter("ids");
        try {
            if (!StringUtils.isEmpty(ids))
                for (String id : ids.split(",")) {
                    if (id != null) {
                        QueryFilter exRobotfilter = new QueryFilter(ExRobot.class);
                        exRobotfilter.addFilter("id=", id);
                        //??????????????????
                        ExRobot exRobot = ((ExRobotService) service).get(exRobotfilter);
                        if (exRobot == null) {
                            return returnJsonResult("???????????????????????????", false);
                        }
                        if ("1".equals(exRobot.getIsSratAuto())) {
                            return returnJsonResult("????????????????????????????????????K???", false);
                        }
                        String code = exRobot.getCoinCode() + "_" + exRobot.getFixPriceCoinCode();
                        // BCH_BTC:klinedata:TransactionOrder_BCH_BTC_1
                        redisService.delete(code + ":klinedata:TransactionOrder_" + code + "_" + 1);
                        redisService.delete(code + ":klinedata:TransactionOrder_" + code + "_" + 1440);
                        redisService.delete(code + ":klinedata:TransactionOrder_" + code + "_" + 15);
                        redisService.delete(code + ":klinedata:TransactionOrder_" + code + "_" + 30);
                        redisService.delete(code + ":klinedata:TransactionOrder_" + code + "_" + 5);
                        redisService.delete(code + ":klinedata:TransactionOrder_" + code + "_" + 60);
                        // ??????vue???kline??????
                        // ZRX_BTC:newklinedata:TransactionOrder_ZRX_BTC_1
                        redisService.delete(code + ":newklinedata:TransactionOrder_" + code + "_" + 1);
                        redisService.delete(code + ":newklinedata:TransactionOrder_" + code + "_" + 1440);
                        redisService.delete(code + ":newklinedata:TransactionOrder_" + code + "_" + 15);
                        redisService.delete(code + ":newklinedata:TransactionOrder_" + code + "_" + 30);
                        redisService.delete(code + ":newklinedata:TransactionOrder_" + code + "_" + 5);
                        redisService.delete(code + ":newklinedata:TransactionOrder_" + code + "_" + 60);
                    }
                }
            return returnJsonResult("???????????????????????????", true);

        } catch (Exception e) {
            e.printStackTrace();
            return returnJsonResult("???????????????", false);
        }

    }

    @MethodName(name = "????????????????????????????????????")
    @RequestMapping("/selectlist")
    @ResponseBody
    public List<ExCointoCoin> selectlist(HttpServletRequest request) {
        List<ExCointoCoin> list = exCointoCoinService.findByList();
        //??????????????????????????????  BTC/USDT
        if (list != null && list.size() > 0) {
            for (ExCointoCoin ex : list) {
                ex.setCoinCode(ex.getCoinCode() + "_" + ex.getFixPriceCoinCode());
            }
        }
        return list;
    }

    @CommonLog(name = "K????????????-??????")
    @MethodName(name = "??????????????????")
    @RequestMapping(value = "/closeAuto/{ids}")
    @ResponseBody
    public JsonResult closeAuto(@PathVariable String ids) {
        try {
            String[] split = ids.split(",");
            for (String id : split) {
                //??????????????????
                QueryFilter queryFilter = new QueryFilter(ExRobot.class);
                queryFilter.addFilter("id=", id);
                ExRobot exRobot = service.get(queryFilter);
                redisService.delete(exRobot.getCoinCode() + "_" + exRobot.getFixPriceCoinCode() + ":klineRobotCount");

                ExRobotLog exRobotLog = new ExRobotLog();
                exRobotLog.setCloseTime(new Date());
                exRobotLog.setCoinCode(exRobot.getCoinCode());
                exRobotLog.setFixCoin(exRobot.getFixPriceCoinCode());
                exRobotLog.setRemark("???????????????");
                exRobotLogService.save(exRobotLog);
            }

            Long[] idArray = (Long[]) ConvertUtils.convert(split, Long.class);
            ((ExRobotService) service).closeAutoByIds(idArray);

            //??????redis????????????
            ((ExRobotService) service).updateExRobotToRedis();

            return returnJsonResult("", true);
        } catch (Exception e) {
            e.printStackTrace();
            return returnJsonResult("????????????????????????!", false);
        }
    }

    @MethodName(name = "????????????")
    @RequestMapping(value = "/testApi/{id}")
    @ResponseBody
    public JsonResult testApi(@PathVariable String id,HttpServletRequest request) {
        try {
            String coinCode = request.getParameter("coinCode");
            String fixPriceCoinCode = request.getParameter("fixPriceCoinCode");
            QueryFilter exRobotfilter = new QueryFilter(ExRobot.class);
            exRobotfilter.addFilter("id=", id);
            //??????????????????
            ExRobot exRobot = ((ExRobotService) service).get(exRobotfilter);

            //
            if(null == exRobot){
                QueryFilter exRobotfilter2 = new QueryFilter(ExRobot.class);
                exRobotfilter2.addFilter("coinCode=", coinCode);
                exRobotfilter2.addFilter("fixPriceCoinCode=",fixPriceCoinCode);
                exRobot = ((ExRobotService) service).get(exRobotfilter2);
            }
            if (exRobot.getAtuoPriceType().equals(3)) {
                //???????????????????????????????????????????????????
                String apiret = null;
                if (exRobot.getAtuoPriceType().equals(3)) {
                    apiret = conmonApiService.getCurrentPriceByApi(exRobot);
                    if (null == apiret) {
                        return returnJsonResult("?????????????????????????????????????????????", false);
                    }
                    if ( "????????????".equals(apiret) || apiret.contains("???????????????")) {
                        return returnJsonResult(apiret, false);
                    }

                }
                return returnJsonResult("????????????????????????????????????" + apiret, true);
            } else {
                if (exRobot.getAtuoPriceType().equals(2)) {
                    return returnJsonResult("???????????????????????????", false);
                }
                if (exRobot.getAtuoPriceType().equals(1)) {
                    return returnJsonResult("???????????????????????????", false);
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
            return returnJsonResult("??????????????????!", false);
        }
        return null;


    }

    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }


    @MethodName(name = "????????????,?????????")
    @RequestMapping(value = "/testApiMoreCoin/{id}")
    @ResponseBody
    public JsonResult testApiMoreCoin(@PathVariable String id) {
        try {
            FXHParam fxhParam = new FXHParam();
            fxhParam.setCoinCode("BCH");
            fxhParam.setFixCoin("BTC");

            JSONObject.parseArray(redisService.get("configCache:klinedataconfig")).forEach(app -> {
                AppConfig appConfig = JSONObject.parseObject(app.toString(), AppConfig.class);
                switch (appConfig.getConfigkey()) {
                    case "klinedataurl":
                        fxhParam.setPayUrl(appConfig.getValue() + "/api/python/hryrCurrencyData");
                        break;
                    case "businessNumber":
                        fxhParam.setCompanyCode(appConfig.getValue());
                        break;
                    case "publickey":
                        fxhParam.setPublicKey(appConfig.getValue());
                        break;
                    case "privatekey":
                        fxhParam.setPrivateKey(appConfig.getValue());
                        break;
                }
            });

            Map<String, Object> map = new HashMap<>(16);
            map.put("ordernumber", UUIDUtil.getUUID());
            map.put("belonged", "?????????6.0");
            map.put("coinPair", "");
            String paramjson = JSONObject.toJSONString(map);
            Map<String, String> paramMap = new HashMap<>(16);
            paramMap.put("companyCode", fxhParam.getCompanyCode());
            paramMap.put("sign", paramjson);
            String returnMsg = HttpConnectionUtil.doPostQuery(fxhParam.getPayUrl(), paramMap);
            //??????returnMsg ?????????data???????????????
            logger.error(returnMsg);
        } catch (Exception e) {
            e.printStackTrace();
            return returnJsonResult("??????????????????!", false);
        }
        return null;


    }

    public static void main(String[] args) {
        {
            try {
                FXHParam fxhParam = new FXHParam();
                fxhParam.setCompanyCode("fd8c07d139154648a5e37831712f000f");
                fxhParam.setPayUrl("http://192.168.0.86/api/python/hryrCoinPairPriceData");

                Map<String, Object> map = new HashMap<>(16);
                map.put("ordernumber", UUIDUtil.getUUID());
                map.put("belonged", "?????????6.0");
                map.put("coinPair", "BTC_USDT,BCH_BTC");
                String paramjson = JSONObject.toJSONString(map);
                Map<String, String> paramMap = new HashMap<>(16);
                paramMap.put("companyCode", fxhParam.getCompanyCode());
                paramMap.put("sign", paramjson);
                String returnMsg = HttpConnectionUtil.doPostQuery(fxhParam.getPayUrl(), paramMap);
                //??????returnMsg ?????????data???????????????
                logger.error(returnMsg);
            } catch (Exception e) {
                e.printStackTrace();
            }


        }
    }


}
