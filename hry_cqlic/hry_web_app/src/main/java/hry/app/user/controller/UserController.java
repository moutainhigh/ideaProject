package hry.app.user.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import hry.app.jwt.TokenUtil;
import hry.app.kline.model.ExCointoCoin;
import hry.app.remote.RemoteUserService;
import hry.app.user.model.ExProduct;
import hry.bean.ApiJsonResult;
import hry.bean.JsonResult;
import hry.core.mvc.model.page.HttpServletRequestUtils;
import hry.ico.remote.RemoteIcoService;
import hry.ico.remote.model.IcoRulesConfig;
import hry.ico.remote.model.RulesConfig;
import hry.lend.remote.RemoteLendViewService;
import hry.licqb.remote.RemoteAssetService;
import hry.manage.remote.RemoteManageService;
import hry.manage.remote.model.*;
import hry.manage.remote.model.base.FrontPage;
import hry.manage.remote.model.commend.CommendMoney;
import hry.manage.remote.model.commend.CommendUser;
import hry.redis.common.utils.RedisService;
import hry.util.SortListUtil;
import hry.util.common.SpringUtil;
import hry.util.properties.PropertiesUtils;
import hry.util.springmvcPropertyeditor.DateTimePropertyEditorSupport;
import hry.util.springmvcPropertyeditor.StringPropertyEditorSupport;
import io.swagger.annotations.*;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

@Controller
@RequestMapping("/user")
@Api(value = "用户信息类(必须传token)", description = "用户信息类(必须传token)", tags = "用户信息类(必须传token)")
public class UserController {

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
    private RemoteLendViewService remoteLendViewService;

    @Resource
    private RemoteManageService remoteManageService;

    @Resource
    private RemoteUserService remoteUserService;
    @Resource
    private RemoteIcoService remoteIcoService;

    /**
     * 获取登录用户信息
     *
     * @param request
     * @return
     */
    @ApiOperation(value = "获取用户信息", httpMethod = "POST", notes = "获取用户信息")
    @PostMapping("/getUser")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
    @ResponseBody
    @RequiresAuthentication
    public ApiJsonResult<User> getUser(HttpServletRequest request) {
        User user = TokenUtil.getUser(request);
        ApiJsonResult<User> userJsonResult = new ApiJsonResult<User>();
        if (user == null) {
            userJsonResult.setSuccess(false);
            userJsonResult.setMsg(SpringUtil.diff("before_login"));
            return userJsonResult;
        }
        user = remoteUserService.getUser(user.getNickName());
        TokenUtil.updateUser(user);
        userJsonResult.setSuccess(true);
        userJsonResult.setObj(user);
        return userJsonResult;
    }

    /**
     * 获取用户账户信息
     *
     * @return
     */
    @ApiOperation(value = "获取用户账户信息", httpMethod = "POST", response = Map.class, notes = "获取用户账户信息")
    @PostMapping("/account")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
    @ResponseBody
    @RequiresAuthentication
    public Map<String, Object> account(@ApiParam(name = "langCode", value = "语种", required = true) @RequestParam("langCode") String langCode, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        User user = TokenUtil.getUser(request);
        if (user != null) {
            String rate = "1";
            // 是否开启平台币作为手续费
            map.put("isOpenCoinFee", user.getIsOpenCoinFee());

            // 总资产
            // 查询账户金额
            BigDecimal[] sum = remoteManageService.getSum(user.getCustomerId());
            map.put("sumArr", sum);

            String config = redisService.get("configCache:all");
            //获取实时价格
            String usdtPrice = redisService.hget("hry:coinPrice", "USDT");

            /*获得usdt对人民币价格*/
            BigDecimal usdttormb = new BigDecimal(StringUtils.isEmpty(usdtPrice) ? "0" : usdtPrice);
            if (!StringUtils.isEmpty(config)) {
                JSONObject parseObject = JSONObject.parseObject(config);
                //法币符号
                String coinSymbol = "";
                String financeConfig = redisService.get("CointoMoney:" + langCode);
                if (!StringUtils.isEmpty(financeConfig)) {
                    ExCointoMoney exCointoMoney = JSONObject.parseObject(financeConfig, ExCointoMoney.class);
                    coinSymbol = exCointoMoney.getCoinSymbol();
                    coinSymbol = HtmlUtils.htmlUnescape(coinSymbol);
                    map.put("usdttormb", usdttormb);
                    map.put("coinSymbol", coinSymbol);

                    rate = exCointoMoney.getRate();
                    map.put("rate", exCointoMoney.getRate());

                    // 获取平台币支付手续费折扣率
                    BigDecimal coinFeeDiscount = parseObject.getBigDecimal("coinFeeDiscount");
                    map.put("coinFeeDiscount", coinFeeDiscount);

                    // 是否开启语言
                    map.put("isOpenLanguage", parseObject.get("isOpenLanguage"));

                    // 当前币种
                    map.put("languageCode", parseObject.get("language_code"));

                    // 资产列表
                    List<CoinAccount> findCoinAccount = remoteManageService.findCoinAccount(user.getCustomerId());
                    SortListUtil sortList = new SortListUtil();
                    sortList.Sort(findCoinAccount, "getCoinCode", "asc");
                    if (findCoinAccount != null && findCoinAccount.size() > 0) {
                        for (CoinAccount coinAccount : findCoinAccount) {
                            // 从redis查图片路径
                            ExProduct exProduct = getExproduct(coinAccount.getCoinCode());
                            if (exProduct != null) {
                                coinAccount.setPicturePath(exProduct.getPicturePath());
                            }

                            //获取实时价格
                            String price = redisService.hget("hry:coinPrice", coinAccount.getCoinCode());
                            List<BigDecimal> coinPrice = new ArrayList<>();
                            if (StringUtils.isEmpty(price)) {
                                coinPrice.add(new BigDecimal("0").setScale(exProduct == null ? 8 : exProduct.getKeepDecimalForCoin(), BigDecimal.ROUND_HALF_UP));
                            } else {
                                if (coinAccount.getCoinCode().equals("USDT")) {
                                    System.out.println("=================usdtPrice :" + usdtPrice + "====rate：" + exCointoMoney.getRate() + "=======================");
                                    coinPrice.add((new BigDecimal(usdtPrice).divide(new BigDecimal(exCointoMoney.getRate()),8, BigDecimal.ROUND_HALF_UP)));
                                } else {
                                    //不为空就直接取
                                    coinPrice.add((new BigDecimal(price).divide(new BigDecimal(exCointoMoney.getRate()),8, BigDecimal.ROUND_HALF_UP)));
                                }
                            }
                            if (coinPrice != null && coinPrice.size() > 0) {
                                coinAccount.setRealTimePrice(coinPrice.get(0).toString());
                                System.out.println("=================" + coinAccount.getCoinCode() + ":" + coinPrice.get(0).toString() + "=======================");
                                coinAccount.setKeepDecimalForCoin(exProduct == null ? 8 : exProduct.getKeepDecimalForCoin());  //币的位数
                                coinAccount.setKeepDecimalForMoney(exProduct == null ? 8 : exProduct.getKeepDecimalForCurrency());  //折合法币位数
                            }
                        }
                    }
                    BigDecimal cnyMoney = new BigDecimal(0);
                    map.put("coinAccountList", findCoinAccount);
                    if (findCoinAccount != null && findCoinAccount.size() > 0) {
                        for (CoinAccount coinAccount : findCoinAccount) {
                            BigDecimal hotmoney = coinAccount.getHotMoney() == null ? BigDecimal.ZERO : coinAccount.getHotMoney();
                            BigDecimal colmoney = coinAccount.getColdMoney() == null ? BigDecimal.ZERO : coinAccount.getColdMoney();
                            String realTimePrice = StringUtils.isEmpty(coinAccount.getRealTimePrice()) ? "0" : coinAccount.getRealTimePrice();
                            cnyMoney = cnyMoney.add((hotmoney.add(colmoney)).multiply(new BigDecimal(realTimePrice)));
                        }
                    }
                    map.put("cnyMoney", cnyMoney);
                    BigDecimal usdtMoney = new BigDecimal(0);
                    if (findCoinAccount != null && findCoinAccount.size() > 0) {
                        for (CoinAccount coinAccount : findCoinAccount) {
                            BigDecimal hotmoney = coinAccount.getHotMoney() == null ? BigDecimal.ZERO : coinAccount.getHotMoney();
                            BigDecimal colmoney = coinAccount.getColdMoney() == null ? BigDecimal.ZERO : coinAccount.getColdMoney();
                            String realTimePrice = StringUtils.isEmpty(coinAccount.getRealTimePrice()) ? "0" : coinAccount.getRealTimePrice();
                            usdtMoney = usdtMoney.add((hotmoney.add(colmoney)).multiply(new BigDecimal(realTimePrice)).divide(usdttormb,8,BigDecimal.ROUND_HALF_UP));
                        }
                    }
                    //map.put("usdtMoney", usdtMoney.multiply(new BigDecimal(exCointoMoney.getRate())).setScale(8, BigDecimal.ROUND_HALF_UP));
                    // 新获取USDT个数 直接算
                    Map<String,String> params = new HashMap<>();
                    params.put("customerId", user.getCustomerId().toString());
                    RemoteAssetService remoteAssetService = SpringUtil.getBean("remoteAssetService");
                    JsonResult result = remoteAssetService.myAccount(params);
                    map.put("usdtMoney",JSONObject.parseObject(result.getObj().toString()).get("totalAssets"));
                }
            }
        }
        return map;
    }


    /**
     * 获取用户账户信息
     *
     * @return
     */
    @ApiOperation(value = "获取用户账户信息(兑换成韩币)", httpMethod = "POST", response = Map.class, notes = "获取用户账户信息")
    @PostMapping("/accountToKRW")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
    @ResponseBody
    @RequiresAuthentication
    public Map<String, Object> accountToKRW(@ApiParam(name = "langCode", value = "语种", required = true) @RequestParam("langCode") String langCode, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        User user = TokenUtil.getUser(request);
        langCode="kor";
        if (user != null) {
            String rate = "1";
            // 是否开启平台币作为手续费
            map.put("isOpenCoinFee", user.getIsOpenCoinFee());

            // 总资产
            // 查询账户金额
            BigDecimal[] sum = remoteManageService.getSum(user.getCustomerId());
            map.put("sumArr", sum);

            String config = redisService.get("configCache:all");
            //获取实时价格
            String usdtPrice = redisService.hget("hry:coinPrice", "USDT");

            /*获得usdt对人民币价格*/
            BigDecimal usdttormb = new BigDecimal(StringUtils.isEmpty(usdtPrice) ? "0" : usdtPrice);
            if (!StringUtils.isEmpty(config)) {
                JSONObject parseObject = JSONObject.parseObject(config);
                //法币符号
                String coinSymbol = "";
                String financeConfig = redisService.get("CointoMoney:" + langCode);
                if (!StringUtils.isEmpty(financeConfig)) {
                    ExCointoMoney exCointoMoney = JSONObject.parseObject(financeConfig, ExCointoMoney.class);
                    coinSymbol = exCointoMoney.getCoinSymbol();
                    coinSymbol = HtmlUtils.htmlUnescape(coinSymbol);
                    map.put("usdttormb", usdttormb);
                    map.put("coinSymbol", coinSymbol);

                    rate = exCointoMoney.getRate();
                    map.put("rate", exCointoMoney.getRate());

                    // 获取平台币支付手续费折扣率
                    BigDecimal coinFeeDiscount = parseObject.getBigDecimal("coinFeeDiscount");
                    map.put("coinFeeDiscount", coinFeeDiscount);

                    // 是否开启语言
                    map.put("isOpenLanguage", parseObject.get("isOpenLanguage"));

                    // 当前币种
                    map.put("languageCode", parseObject.get("language_code"));

                    // 资产列表
                    List<CoinAccount> findCoinAccount = remoteManageService.findCoinAccount(user.getCustomerId());
                    SortListUtil sortList = new SortListUtil();
                    sortList.Sort(findCoinAccount, "getCoinCode", "asc");
                    if (findCoinAccount != null && findCoinAccount.size() > 0) {
                        JsonResult platformRule = remoteIcoService.getPlatformRule("");
                        for (CoinAccount coinAccount : findCoinAccount) {
                            // 从redis查图片路径
                            ExProduct exProduct = getExproduct(coinAccount.getCoinCode());
                            if (exProduct != null) {
                                coinAccount.setPicturePath(exProduct.getPicturePath());
                            }

                            //获取实时价格
                            String price = redisService.hget("hry:coinPrice", coinAccount.getCoinCode());
                            List<BigDecimal> coinPrice = new ArrayList<>();
                            if (StringUtils.isEmpty(price)) {
                                coinPrice.add(new BigDecimal("0").setScale(exProduct == null ? 2 : exProduct.getKeepDecimalForCoin(), BigDecimal.ROUND_HALF_UP));
                            } else {
                                if (coinAccount.getCoinCode().equals("USDT")) {
                                    System.out.println("=================usdtPrice :" + usdtPrice + "====rate：" + exCointoMoney.getRate() + "=======================");
                                    coinPrice.add((new BigDecimal(usdtPrice).multiply(new BigDecimal(exCointoMoney.getRate()))).setScale(exProduct == null ? 2 : exProduct.getKeepDecimalForCoin(), BigDecimal.ROUND_HALF_UP));
                                } else {
                                    //不为空就直接取
                                    coinPrice.add((new BigDecimal(price).multiply(new BigDecimal(exCointoMoney.getRate()))).setScale(exProduct == null ? 2 : exProduct.getKeepDecimalForCoin(), BigDecimal.ROUND_HALF_UP));
                                }
                            }
                            if (coinPrice != null && coinPrice.size() > 0) {
                                coinAccount.setRealTimePrice(coinPrice.get(0).toString());
                                System.out.println("=================" + coinAccount.getCoinCode() + ":" + coinPrice.get(0).toString() + "=======================");
                                coinAccount.setKeepDecimalForCoin(exProduct == null ? 2 : exProduct.getKeepDecimalForCoin());  //币的位数
                                coinAccount.setKeepDecimalForMoney(exProduct == null ? 2 : exProduct.getKeepDecimalForCurrency());  //折合法币位数
                            }
                            //判断币种修改兑换韩币的比例 realTimePrice
                            if(coinAccount.getCoinCode().equals("ETH")){
                                String buy_price=redisService.getMap(RulesConfig.RedisMarketKey,"buy_price");//购买价格
                                coinAccount.setRealTimePrice(buy_price);
                            }
                            if(platformRule.getSuccess()){
                                IcoRulesConfig icoRulesConfig =(IcoRulesConfig) platformRule.getObj();
                                if(coinAccount.getCoinCode().equals(icoRulesConfig.getCoinCode())){
                                    coinAccount.setRealTimePrice(icoRulesConfig.getIssuePrice());
                                }
                            }

                        }
                    }
                    BigDecimal cnyMoney = new BigDecimal(0);
                    map.put("coinAccountList", findCoinAccount);
                    if (findCoinAccount != null && findCoinAccount.size() > 0) {
                        for (CoinAccount coinAccount : findCoinAccount) {
                            BigDecimal hotmoney = coinAccount.getHotMoney() == null ? BigDecimal.ZERO : coinAccount.getHotMoney();
                            BigDecimal colmoney = coinAccount.getColdMoney() == null ? BigDecimal.ZERO : coinAccount.getColdMoney();
                            String realTimePrice = StringUtils.isEmpty(coinAccount.getRealTimePrice()) ? "0" : coinAccount.getRealTimePrice();
                            cnyMoney = cnyMoney.add((hotmoney.add(colmoney)).multiply(new BigDecimal(realTimePrice)));
                        }
                    }
                    map.put("cnyMoney", cnyMoney);
                }
            }
        }
        return map;
    }


    private ExProduct getExproduct(String coinCode) {
        String exProductStr = redisService.hget("ex:Product", coinCode);
        return StringUtils.isEmpty(exProductStr) ? null : JSONObject.parseObject(exProductStr, ExProduct.class);
    }

    @ApiOperation(value = "设置平台币当交易手续费", httpMethod = "POST", response = JsonResult.class, notes = "设置平台币当交易手续费")
    @PostMapping("/setPlatCoinAsFees")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
    @ResponseBody
    @RequiresAuthentication
    public JsonResult setPlatCoinAsFees(
            @ApiParam(name = "isChecked", value = "是否勾选平台币作交易手续费，勾选为1，未勾选0", required = true) @RequestParam("isChecked") String isChecked,
            HttpServletRequest request) {
        User user = TokenUtil.getUser(request);
        JsonResult result = remoteManageService.updateIsOpenTransFeeState(user.getCustomerId(), isChecked);
        if (result.getSuccess()) {
            user.setIsOpenCoinFee(new Integer(isChecked));
            TokenUtil.updateUser(user);
        }
        return result;
    }

    @ApiOperation(value = "设置常用语言", httpMethod = "POST", response = JsonResult.class, notes = "设置常用语言")
    @PostMapping("/setCommonLanage")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
    @ResponseBody
    @RequiresAuthentication
    public JsonResult setCommonLanage(
            @ApiParam(name = "commonLanage", value = "常用语言", required = true) @RequestParam("commonLanage") String commonLanage,
            HttpServletRequest request) {
        User user = TokenUtil.getUser(request);
        JsonResult result = remoteManageService.setCommonLanage(user.getCustomerId(), commonLanage);
        if (result.getSuccess()) {
            user.setCommonLanguage(commonLanage);
            TokenUtil.updateUser(user);
            result.setObj(user);
        }
        return result;
    }

    /**
     * 个人中心-邀请方式
     *
     * @return
     */
    @ApiOperation(value = "个人中心-邀请方式", httpMethod = "POST", response = Map.class, notes = "个人中心-邀请方式")
    @PostMapping("/CommendType")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
    @ResponseBody
    @RequiresAuthentication
    public Map<String, Object> CommendType(HttpServletRequest request) {
        Map<String, Object> returnMap = new HashMap<>();
        User user = TokenUtil.getUser(request);
        if (user != null) {
            String property = PropertiesUtils.APP.getProperty("app.vueurl");
            RemoteResult selectCommend = remoteManageService.selectCommend(user.getUsername(), property);
            if (selectCommend != null && selectCommend.getSuccess()) {
                returnMap.put("info", selectCommend.getObj());
            }
        }
        return returnMap;
    }

    /**
     * 个人中心-推荐好友个数列表
     *
     * @return
     */
    @ApiOperation(value = "个人中心-推荐好友个数列表", httpMethod = "POST", notes = "个人中心-推荐好友个数列表")
    @PostMapping("/commendFriendNumList")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
    @ResponseBody
    @RequiresAuthentication
    public ApiJsonResult<List<CommendUser>> commendFriendNumList(HttpServletRequest request) {
        ApiJsonResult<List<CommendUser>> jsonResult = new ApiJsonResult<List<CommendUser>>();
        User user = TokenUtil.getUser(request);
        if (user != null) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("customerId", user.getCustomerId().toString());
            List<CommendUser> list = remoteManageService.culCommendCount(map);
            jsonResult.setSuccess(true);
            jsonResult.setObj(list);
            return jsonResult;
        }
        jsonResult.setSuccess(false);
        jsonResult.setMsg(SpringUtil.diff("before_login"));
        return jsonResult;
    }

    /**
     * 查看推荐好友列表
     *
     * @return
     */
    @ApiOperation(value = "个人中心-推荐好友-查看推荐好友列表", httpMethod = "POST", notes = "个人中心-推荐好友-查看推荐好友列表")
    @PostMapping("/commendFriendList")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
    @ResponseBody
    @RequiresAuthentication
    public FrontPage commendFriendList(
            @ApiParam(name = "limit", value = "每页条数", required = true) @RequestParam("limit") String limit,
            @ApiParam(name = "offset", value = "(页码-1)*每页条数,如第1页，每次10条侧传(1-1)*10=0", required = true) @RequestParam("offset") String offset,
            @ApiParam(name = "number", value = "几级推荐，从1开始", required = true) @RequestParam("number") String number,
            HttpServletRequest request) {
        try {
            User user = TokenUtil.getUser(request);
            if (user != null) {
                Map<String, String> params = HttpServletRequestUtils.getParams(request);
                params.put("id", user.getCustomerId().toString());
                params.put("number", number);
                return remoteManageService.findConmmendForntPageBySql(params);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 个人中心-推荐返佣列表
     *
     * @param request
     * @return
     */
    @ApiOperation(value = "个人中心-推荐返佣列表", httpMethod = "POST", notes = "个人中心-推荐返佣列表")
    @PostMapping("/commendRebate")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
    @ResponseBody
    @RequiresAuthentication
    public ApiJsonResult<Object> commendRebate(HttpServletRequest request) {
        ApiJsonResult<Object> jsonResult = new ApiJsonResult<>();
        User user = TokenUtil.getUser(request);
        if (user != null) {
            String appName = PropertiesUtils.APP.getProperty("app.appName");
            if(appName != null && "DFC".equals(appName)){
            	Map<String, Object> map = new HashMap<>();
    	        long current=System.currentTimeMillis();//当前时间毫秒数
    	        long zero=current/(1000*3600*24)*(1000*3600*24)-TimeZone.getDefault().getRawOffset();//今天零点零分零秒的毫秒数
    	        long twelve=zero+24*60*60*1000-1;//今天23点59分59秒的毫秒数
    	        SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	        map.put("beginTime",fm.format(new Date(zero)));//当天的0点0时0分时间
    	        map.put("endTime",fm.format(new Date(twelve)));//当天23点59分59秒
    	        map.put("custromerId", user.getCustomerId());//用户id
    	         List<Map<String, Object>> countCommendMoney = remoteManageService.countCommendMoney(map);
    	         jsonResult.setObj(countCommendMoney);
            }else {
            	 List<CommendMoney> selectCommendfind = remoteManageService.selectCommendfind(user.getCustomerId());
            	 jsonResult.setObj(selectCommendfind);
			}
           
            jsonResult.setSuccess(true);
            return jsonResult;
        }
        jsonResult.setSuccess(false);
        jsonResult.setMsg(SpringUtil.diff("before_login"));
        return jsonResult;
    }

    /**
     * 获取个人账户资金
     *
     * @return
     */
    @ApiOperation(value = "获取个人账户资金", httpMethod = "POST", response = ApiJsonResult.class, notes = "获取个人账户资金")
    @PostMapping("/getAccountInfo")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
    @ResponseBody
    @RequiresAuthentication
    public ApiJsonResult getAccountInfo(
            @ApiParam(name = "coinCode", value = "币种类型", required = true) @RequestParam("coinCode") String coinCode,
            @ApiParam(name = "type", value = "类型1杠杆 0普通", required = false) @RequestParam(value = "type", required = false, defaultValue = "0") String type,
            HttpServletRequest request) {
        ApiJsonResult jsonResult = new ApiJsonResult();
        if (!StringUtils.isEmpty(coinCode) && coinCode.contains("_")) {
            User user = TokenUtil.getUser(request);
            if (user != null) {
                if (type.equals("1")) {
                    JsonResult jsonResult1 = remoteLendViewService.getAccountInfo(coinCode, user.getCustomerId());
                    BeanUtils.copyProperties(jsonResult1, jsonResult);
                    return jsonResult;
                } else {
                    RemoteResult result = remoteManageService.getAccountInfo(coinCode, user.getCustomerId());
                    if (result.getSuccess()) {
                        jsonResult.setSuccess(true);
                        jsonResult.setObj(JSON.parseObject(result.getObj().toString()));
                        return jsonResult;
                    }
                }
            } else {
                jsonResult.setSuccess(false);
                jsonResult.setMsg(SpringUtil.diff("before_login"));
                return jsonResult;
            }
        }
        jsonResult.setSuccess(false);
        return jsonResult;
    }

    /**
     * app端-是否实名和密码设置
     *
     * @param request
     * @return
     */
    @ApiOperation(value = "app端-是否实名和密码设置", httpMethod = "POST", response = ApiJsonResult.class, notes = "app端-是否实名和密码设置")
    @PostMapping("/isrealandpwd")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
    @ResponseBody
    @RequiresAuthentication
    public ApiJsonResult isrealandpwd(HttpServletRequest request) {
        ApiJsonResult jsonResult = new ApiJsonResult();
        User user = TokenUtil.getUser(request);
        if (user != null) {
            Map<String, Object> map = new HashMap<String, Object>();
            // 查询后台参数配置
            String config = redisService.get("configCache:all");
            if (!StringUtils.isEmpty(config)) {
                JSONObject parseObject = JSONObject.parseObject(config);
                map.put("maxWithdrawMoney", parseObject.get("maxWithdrawMoney"));// 当天最多提现金额
                map.put("maxWithdrawMoneyOneTime", parseObject.get("maxWithdrawMoneyOneTime"));// 单笔最多提现金额(元)
                String languageCode = (String) parseObject.get("language_code");// 法币类型
                map.put("languageCode", languageCode);
                //手续费率
                String isTrade = parseObject.get("isTrade").toString();
                String isChongbi = parseObject.get("isChongbi").toString();
                String isTibi = parseObject.get("isTibi").toString();
                map.put("isTibi", isTibi);
                map.put("isChongbi", isChongbi);
                map.put("isTrade", isTrade);

                jsonResult.setSuccess(true);
                jsonResult.setObj(map);
                return jsonResult;
            }
            jsonResult.setSuccess(false);
            return jsonResult;
        }
        jsonResult.setSuccess(false);
        jsonResult.setMsg(SpringUtil.diff("before_login"));
        return jsonResult;
    }
}
