package hry.app.user.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import hry.app.jwt.TokenUtil;
import hry.app.user.model.ExProduct;
import hry.bean.ApiJsonResult;
import hry.bean.JsonResult;
import hry.core.mvc.model.page.HttpServletRequestUtils;
import hry.core.shiro.PasswordHelper;
import hry.licqb.remote.RemoteRecordService;
import hry.manage.remote.RemoteAppTransactionManageService;
import hry.manage.remote.RemoteManageService;
import hry.manage.remote.model.ExDigitalmoneyAccountManage;
import hry.manage.remote.model.ExDmCustomerPublicKeyManage;
import hry.manage.remote.model.RemoteResult;
import hry.manage.remote.model.User;
import hry.manage.remote.model.base.FrontPage;
import hry.redis.common.utils.RedisService;
import hry.util.SortListUtil;
import hry.util.common.SpringUtil;
import hry.util.springmvcPropertyeditor.DateTimePropertyEditorSupport;
import hry.util.springmvcPropertyeditor.StringPropertyEditorSupport;
import io.swagger.annotations.*;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.*;

@Controller
@RequestMapping("/coinorder")
@Api(value = "业务类(必须传token)", description = "数字货币业务类(必须传token)", tags = "数字货币业务类(必须传token)")
public class CoinOrderController {

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

    @Resource
    private RedisService redisService;

    @Resource
    private RemoteAppTransactionManageService remoteAppTransactionManageService;

    @Resource
    private RemoteManageService remoteManageService;

    @Resource
    private RemoteRecordService remoteRecordService;

    /**
     * 充币记录
     * @param request
     * @return
     */
    @ApiOperation(value = "个人中心-我要充币记录分页接口", httpMethod = "POST", response = FrontPage.class, notes = "个人中心-我要充币记录分页接口")
    @PostMapping("/user/recharge")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
    @ResponseBody
    @RequiresAuthentication
    public FrontPage recharge(
            @ApiParam(name = "limit", value = "每页条数", required = true) @RequestParam("limit") String limit,
            @ApiParam(name = "offset", value = "(页码-1)*每页条数,如第1页，每次10条侧传(1-1)*10=0",required = true) @RequestParam("offset") String offset,
            @ApiParam(name = "status", value = "订单类型(0查全部,1等待，2成功,3失败)",required = true) @RequestParam("status") String status,
            @ApiParam(name = "coinCode", value = "币种，例如:BTC、USDT等)",required = false) @RequestParam(value = "coinCode", required = false) String coinCode,
            HttpServletRequest request) {
        User user = TokenUtil.getUser(request);
        Map<String, String> params = HttpServletRequestUtils.getParams(request);
        params.put("customerId", user.getCustomerId().toString());
        params.put("transactionType", "1");
        if (!StringUtils.isEmpty(coinCode)) {
            params.put("coinCode", coinCode);
        }
        params.put("status", status);
        if ("0".equals(status)) {// 0查全部
            params.put("status", null);
        }
        return remoteAppTransactionManageService.findExdmtransactionRecord(params);
    }



    /**
     * 提币记录
     * @return
     */
    @ApiOperation(value = "个人中心-我要提币记录分页接口", httpMethod = "POST", response = FrontPage.class, notes = "个人中心-我要提币记录分页接口")
    @RequestMapping("/user/withdraw")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
    @ResponseBody
    @RequiresAuthentication
    public FrontPage withdraw(
            @ApiParam(name = "limit", value = "每页条数", required = true) @RequestParam("limit") String limit,
            @ApiParam(name = "offset", value = "(页码-1)*每页条数,如第1页，每次10条侧传(1-1)*10=0",required = true) @RequestParam("offset") String offset,
            @ApiParam(name = "status", value = "订单类型(0查全部,1等待，2成功,3失败)",required = true) @RequestParam("status") String status,
            @ApiParam(name = "coinCode", value = "币种，例如:BTC、USDT等)",required = false) @RequestParam(value = "coinCode", required = false) String coinCode,
            HttpServletRequest request) {
        User user = TokenUtil.getUser(request);

        Map<String, String> params = HttpServletRequestUtils.getParams(request);
        params.put("customerId", user.getCustomerId().toString());
        params.put("transactionType", "2");
        if (!StringUtils.isEmpty(coinCode)) {
            params.put("coinCode", coinCode);
        }
        params.put("status", "");
        if (!"0".equals(status)) {
            params.put("status", status);
        }
        return remoteAppTransactionManageService.findExdmtransactionRecord(params);
    }

    /**
     * 个人中心-我要充币页面数据
     *
     * @param request
     * @return
     */
    @ApiOperation(value = "个人中心-我要充币页面数据", httpMethod = "POST", response = Map.class, notes = "个人中心-我要充币页面数据")
    @PostMapping("/user/initMyPostPageData")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
    @ResponseBody
    @RequiresAuthentication
    public Map<String, Object> initMyPostPageData(HttpServletRequest request) {
        Map<String, Object> returnMap = new HashMap<>();
        Locale locale = LocaleContextHolder.getLocale();
        User user = TokenUtil.getUser(request);
        if (user != null) {
            RedisService redisService = (RedisService) SpringUtil.getBean("redisService");
            String config = redisService.get("configCache:all");
            if (!StringUtils.isEmpty(config)) {
                JSONObject parseObject = JSONObject.parseObject(config);
                List<ExDigitalmoneyAccountManage> list = remoteAppTransactionManageService.listexd(user.getCustomerId(), "");
                if (list != null && list.size() > 0) {
                    if (locale != null) {
                        /*if (!"zh_CN".equals(locale.toString())) {
                            Iterator<ExDigitalmoneyAccountManage> iterator = list.iterator();
                            while (iterator.hasNext()){
                                openchongbi(remoteManageService, iterator);
                                //if(iterator.hasNext()){
                                    iterator.next().setCoinName(iterator.next().getCoinCode());
                              //  }
                            }
                        }else {
                            Iterator<ExDigitalmoneyAccountManage> iterator = list.iterator();
                            while (iterator.hasNext()){
                                openchongbi(remoteManageService, iterator);
                            }
                        }*/
                        Iterator<ExDigitalmoneyAccountManage> iterator = list.iterator();
                        //while (iterator.hasNext()){
                            openchongbi(remoteManageService, iterator);
                      //  }
                    }
                    new SortListUtil<ExDigitalmoneyAccountManage>().Sort(list, "getCoinCode", "asc");
                    returnMap.put("list", list);
                    if (list != null && list.size() > 0) {
                        returnMap.put("firstHot", list.get(0).getHotMoney());
                        returnMap.put("firstCold", list.get(0).getColdMoney());
                        returnMap.put("publicKey", list.get(0).getPublicKey());
                        returnMap.put("coincode", list.get(0).getCoinCode());
                    }
                }
            }
        }
        return returnMap;
    }

    private void openchongbi(RemoteManageService remoteManageService, Iterator<ExDigitalmoneyAccountManage> iterator) {
        while (iterator.hasNext()){
            boolean f =!remoteManageService.openChongbi(iterator.next().getCoinCode());
            if (f) {
                iterator.remove();
                return;
            }
        }
    }


    /**
     * 个人中心-我要提币页面数据
     *
     * @param request
     * @return
     */
    @ApiOperation(value = "个人中心-我要提币页面数据", httpMethod = "POST", response = Map.class, notes = "个人中心-我要提币页面数据")
    @PostMapping("/user/initMyGetPageData")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
    @ResponseBody
    @RequiresAuthentication
    public Map<String, Object> initMyGetPageData (HttpServletRequest request) {
        Map<String, Object> returnMap = new HashMap<>();
        Locale locale = LocaleContextHolder.getLocale();
        User user = TokenUtil.getUser(request);
        if (user != null) {
            String config = redisService.get("configCache:all");
            if (!StringUtils.isEmpty(config)) {
                JSONObject parseObject = JSONObject.parseObject(config);
                List<String> openTibiList = remoteManageService.findOpenTibi();
                List<ExDigitalmoneyAccountManage> list = remoteAppTransactionManageService.listexd(user.getCustomerId(), "");
                if (list != null && list.size() > 0) {
                    for (int i = 0; i < list.size(); i++) {
                        if (locale != null) {
                            if (!"zh_CN".equals(locale.toString())) {
                                list.get(i).setCoinName(list.get(i).getCoinCode());
                            }
                        }
                        if (!openTibiList.contains(list.get(i).getCoinCode())) {
                            list.remove(i);
                            i = i - 1;
                            continue;
                        }
                    }
                    new SortListUtil<ExDigitalmoneyAccountManage>().Sort(list, "getCoinCode", "asc");
                    returnMap.put("list", list);
                    if (list != null && list.size() > 0) {
                        returnMap.put("firstHot", list.get(0).getHotMoney());
                        returnMap.put("firstCold", list.get(0).getColdMoney());
                        returnMap.put("publicKey", list.get(0).getPublicKey());
                        returnMap.put("coinType", list.get(0).getCoinCode());
                        returnMap.put("currencyType", list.get(0).getCurrencyType());
                        returnMap.put("coincode", list.get(0).getCoinCode());
                        returnMap.put("paceFeeRate", list.get(0).getPaceCurrecy());
                        returnMap.put("leastPaceNum", list.get(0).getLeastPaceNum());
                        returnMap.put("oneDayPaceNum", list.get(0).getOneDayPaceNum());

                        String coinCode = list.get(0).getCoinCode();
                        String value = redisService.get("currecyType:" + coinCode);
                        returnMap.put("paceType", value);
                        List<ExDmCustomerPublicKeyManage> list2 = remoteAppTransactionManageService.getList(user.getCustomerId(), list.get(0).getCoinCode());
                        if (list2 != null && list2.size() > 0) {
                            returnMap.put("list2", list2);
                        }
                    }
                }
            }
        }
        return returnMap;
    }

    /**
     * 个人中心-我要充币-获取币地址
     *
     * @param request
     * @return
     */
    @ApiOperation(value = "个人中心-我要充币-获取币地址", httpMethod = "POST", response = ApiJsonResult.class, notes = "个人中心-我要充币-获取币地址")
    @PostMapping("/user/getPublicKey")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
    @ResponseBody
    @RequiresAuthentication
    public ApiJsonResult getPublicKey(
            @ApiParam(name = "accountId", value = "资金账户id", required = true) @RequestParam("accountId") String accountId,
            HttpServletRequest request) {
        ApiJsonResult jsonResult = new ApiJsonResult();
        User user = TokenUtil.getUser(request);
        if (user == null) {
            jsonResult.setSuccess(false);
            jsonResult.setMsg(SpringUtil.diff("before_login"));
            return jsonResult;
        }

        if (!StringUtils.isEmpty(accountId)) {
            String cusId = user.getCustomerId().toString();
            RemoteResult remoteResult = remoteManageService.getPublicKey(Long.valueOf(accountId), cusId);
            if (remoteResult != null && remoteResult.getSuccess()) {
                jsonResult.setSuccess(true);
                jsonResult.setObj(remoteResult.getObj());
                return jsonResult;
            } else {
                jsonResult.setSuccess(false);
                jsonResult.setMsg(remoteResult.getMsg());
                return jsonResult;
            }
        }
        jsonResult.setSuccess(false);
        jsonResult.setMsg("accountId is null");
        return jsonResult;
    }

    /**
     * 个人中心-我要充币-创建币地址
     *
     * @param request
     * @return
     */
    @ApiOperation(value = "个人中心-我要充币-创建币地址", httpMethod = "POST", response = ApiJsonResult.class, notes = "个人中心-我要充币-创建币地址")
    @PostMapping("/user/createPublicKey")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
    @ResponseBody
    @RequiresAuthentication
    public ApiJsonResult createPublicKey (
            @ApiParam(name = "accountId", value = "资金账户id", required = true) @RequestParam("accountId") String accountId,
            HttpServletRequest request) {
        ApiJsonResult jsonResult = new ApiJsonResult();
        if (!StringUtils.isEmpty(accountId)) {
            RemoteResult remoteResult = remoteManageService.createPublicKey(Long.valueOf(accountId));
            if (remoteResult != null && remoteResult.getSuccess()) {
                jsonResult.setSuccess(true);
                jsonResult.setObj(remoteResult.getObj());
                jsonResult.setMsg(SpringUtil.diff("createSuccess"));
                return jsonResult;
            } else {
                jsonResult.setSuccess(false);
                jsonResult.setMsg(SpringUtil.diff(remoteResult.getMsg()));
                return jsonResult;
            }
        }
        jsonResult.setSuccess(false);
        jsonResult.setMsg("accountId is null");
        return jsonResult;
    }

    /**
     * 个人中心-我要提币-查询提币费率
     *
     * @param request
     * @return
     */
    @ApiOperation(value = "个人中心-我要提币-查询提币费率", httpMethod = "POST", response = ApiJsonResult.class, notes = "个人中心-我要提币-查询提币费率")
    @PostMapping("/user/findcurrcy")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
    @ResponseBody
    @RequiresAuthentication
    public ApiJsonResult findcurrcy(
            @ApiParam(name = "coinCode", value = "币种类型", required = true) @RequestParam("coinCode") String coinCode,
            HttpServletRequest request) {
        ApiJsonResult jsonResult = new ApiJsonResult();
        List<Object> list = new ArrayList<>();
        ExProduct exProduct = JSONObject.parseObject(redisService.hget("ex:Product", coinCode), ExProduct.class);
        if (exProduct != null) {
            String currecy = (null == exProduct.getPaceCurrecy()) ? "0" : exProduct.getPaceCurrecy();
            String paceType=exProduct.getPaceType();
//            if(currecy.contains(",")){原判断bug
            if(paceType.equals("2")){//新判断
                list.add(currecy);
            } else {
                list.add(new BigDecimal(currecy).divide(new BigDecimal(100), 8, BigDecimal.ROUND_HALF_DOWN)); // 提币费率
            }
            list.add(exProduct.getPaceType()); // 提币费率类型
            list.add(exProduct.getKeepDecimalForCoin());//币的保留位数
        }
        jsonResult.setSuccess(true);
        jsonResult.setObj(list);
        return jsonResult;
    }

    /**
     * 个人中心-我要提币-查询钱包地址
     *
     * @param request
     * @return
     */
    @ApiOperation(value = "个人中心-我要提币-查询钱包地址", httpMethod = "POST", notes = "个人中心-我要提币-查询钱包地址")
    @PostMapping("/user/findPublicKey")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
    @ResponseBody
    @RequiresAuthentication
    public ApiJsonResult<List<ExDmCustomerPublicKeyManage>> findPublicKey (
            @ApiParam(name = "coinCode", value = "币种类型", required = true) @RequestParam("coinCode") String coinCode,
            HttpServletRequest request) {
        ApiJsonResult<List<ExDmCustomerPublicKeyManage>> jsonResult = new ApiJsonResult<List<ExDmCustomerPublicKeyManage>>();
        User user = TokenUtil.getUser(request);
        if (user != null) {
            List<ExDmCustomerPublicKeyManage> list = remoteAppTransactionManageService.getList(user.getCustomerId(), coinCode);
            jsonResult.setSuccess(true);
            jsonResult.setObj(list);
            return jsonResult;
        }
        jsonResult.setSuccess(false);
        jsonResult.setMsg(SpringUtil.diff("before_login"));
        return jsonResult;
    }

    /**
     * 个人中心-我要提币-确认提现
     * @param request
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "个人中心-我要提币-确认提现", httpMethod = "POST", response = ApiJsonResult.class, notes = "个人中心-我要提币-确认提现")
    @PostMapping("/user/submitOrder")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
    @ResponseBody
    @RequiresAuthentication
    public synchronized ApiJsonResult submitOrder (
            @ApiParam(name = "coinType", value = "币种类型", required = true) @RequestParam("coinType") String coinType,
            @ApiParam(name = "accountPw", value = "资金密码", required = true) @RequestParam("accountPw") String accountPw,
            @ApiParam(name = "currencyType", value = "货币类型", required = true) @RequestParam("currencyType") String currencyType,
            @ApiParam(name = "btcNum", value = "提现数量", required = true) @RequestParam("btcNum") String btcNum,
            @ApiParam(name = "btcKey", value = "钱包地址", required = true) @RequestParam("btcKey") String btcKey,
            @ApiParam(name = "shouxufei", value = "手续费", required = true) @RequestParam("shouxufei") String shouxufei,
            HttpServletRequest request) throws Exception {
        ApiJsonResult jsonResult = new ApiJsonResult();
        User user = TokenUtil.getUser(request);
        if (user != null) {
            String isStop = redisService.get("deal:stop");
            if (!StringUtils.isEmpty(isStop)) {
                jsonResult.setSuccess(false);
                jsonResult.setMsg(SpringUtil.diff("tibishibai"));
                return jsonResult;
            }
            // 李超项目---提币校验
            JsonResult result = remoteRecordService.tiBiCheck(user.getCustomerId(),coinType);
            if (!result.getSuccess()) {
                jsonResult.setSuccess(false);
                jsonResult.setMsg(result.getMsg());
                return jsonResult;
            }

            //验证交易密码
            PasswordHelper passwordHelper = new PasswordHelper();
            String encryAccountPw = passwordHelper.encryString(accountPw, user.getSalt());
            if (!encryAccountPw.equals(user.getAccountPassWord())) {
                jsonResult.setSuccess(false);
                jsonResult.setMsg(SpringUtil.diff("mimacuowu"));
                return jsonResult;
            }
            String lock = "submitOrder:" + user.getCustomerId();
            try {
                if (redisService.lock(lock)) {
                    // System.out.println("getOrder 提币订单 btcNum："+ btcNum+"===btcKey"+btcKey);
                    RemoteResult remoteResult = remoteAppTransactionManageService.getOrder(user, coinType, btcNum, currencyType, user.getUsername(), btcKey, shouxufei);
                    // System.out.println("getOrder 提币订单 回调："+ JSON.toJSONString(remoteResult));
                    if (remoteResult.getSuccess()) {
                        jsonResult.setSuccess(true);
                        jsonResult.setObj(remoteResult.getObj());
                        return jsonResult;
                    } else {
                        jsonResult.setSuccess(false);
                        jsonResult.setMsg(SpringUtil.diff(remoteResult.getMsg()));
                        return jsonResult;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                jsonResult.setSuccess(false);
                jsonResult.setMsg(SpringUtil.diff("klg_wangluoyichang"));
                return jsonResult;
            } finally {
                redisService.unLock(lock);//解锁
            }
        }
        jsonResult.setSuccess(false);
        jsonResult.setMsg(SpringUtil.diff("before_login"));
        return jsonResult;
    }

    /**
     * 充提币记录查询-手机端用
     * @return
     */
    @ApiOperation(value = "充提币记录查询-手机端用", httpMethod = "POST", notes = "充提币记录查询-手机端用")
    @RequestMapping("/user/chargeCoinListForApp")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
    @ResponseBody
    @RequiresAuthentication
    public FrontPage chargeCoinListForApp(
            @ApiParam(name = "limit", value = "每页条数", required = true) @RequestParam("limit") String limit,
            @ApiParam(name = "offset", value = "(页码-1)*每页条数,如第1页，每次10条侧传(1-1)*10=0",required = true) @RequestParam("offset") String offset,
            @ApiParam(name = "status", value = "订单类型(0查全部,1等待，2成功,3失败)",required = true) @RequestParam("status") String status,
            @ApiParam(name = "optType", value = "操作类型（1-充币，2-提币）", required = false) @RequestParam(value = "optType",required = false) String optType,
            @ApiParam(name = "coinCode", value = "币种，例如:BTC、USDT等)",required = false) @RequestParam(value = "coinCode", required = false) String coinCode,
            HttpServletRequest request) {
        User user = TokenUtil.getUser(request);
        Map<String, String> params = HttpServletRequestUtils.getParams(request);
        params.put("customerId", user.getCustomerId().toString());
        if (!StringUtils.isEmpty(coinCode)) {
            params.put("coinCode", coinCode);
        }
        if (!StringUtils.isEmpty(optType)) {
            params.put("transactionType", optType);
        }
        params.put("status", "");
        if (!"0".equals(status)) {
            params.put("status", status);
        }
        return remoteAppTransactionManageService.findExdmtransactionRecord(params);
    }
}
