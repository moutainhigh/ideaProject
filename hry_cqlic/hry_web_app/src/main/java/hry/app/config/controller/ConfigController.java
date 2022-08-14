package hry.app.config.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import hry.app.kline.model.MarketTrades;
import hry.app.kline.model.MarketTradesSub;
import hry.app.user.model.ExProduct;
import hry.bean.ApiJsonResult;
import hry.lcqb.RulesConfig;
import hry.manage.remote.RemoteAppArticleService;
import hry.manage.remote.model.AppBanner;
import hry.manage.remote.model.Coin2;
import hry.manage.remote.model.ExCointoMoney;
import hry.manage.remote.model.FeixiaohaoPriceVo;
import hry.redis.common.utils.RedisService;
import hry.util.*;
import hry.util.common.BaseConfUtil;
import hry.util.common.Constant;
import hry.util.klinedata.TransactionOrder;
import hry.util.properties.StringConstant;
import hry.util.springmvcPropertyeditor.DateTimePropertyEditorSupport;
import hry.util.springmvcPropertyeditor.StringPropertyEditorSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.*;

@Controller
@RequestMapping(value = "/config")
@Api(value = "系统配置类", description = "系统配置类", tags = "系统配置类")
public class ConfigController {

    /**
     * 注册类型属性编辑器
     *
     * @param binder
     */
    @InitBinder
    public void initBinder (ServletRequestDataBinder binder) {

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
    private RemoteAppArticleService remoteAppArticleService;

    @RequestMapping(value = "/findWordsByLang", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ApiOperation(value = "通过语种获取该语种的多语言词汇", httpMethod = "GET", notes = "通过语种获取该语种的多语言词汇")
    @ResponseBody
    public ApiJsonResult findWordsByLang (
            @ApiParam(name = "sourceType", value = "类型，手机端：app；电脑端：pc", required = true) @RequestParam("sourceType") String sourceType,
            @ApiParam(name = "langCode", value = "语种", required = true) @RequestParam("langCode") String langCode) {
        // 获取语种简称
        Map<String, String> langMap = new HashMap<>();
        ApiJsonResult jsonResult = new ApiJsonResult();
        jsonResult.setSuccess(false);
        boolean isTrue=StringUtil.isNull(langCode);
        if (isTrue) {
            if ("pc".equals(sourceType)) {
                langMap = HryCache.pc_cache_language.get(langCode);
            } else if ("app".equals(sourceType)) {
                langMap = HryCache.app_cache_language.get(langCode);
            }
            if (!langMap.isEmpty()) {
                Set<String> keySet = langMap.keySet();
                for (String key : keySet) {
                    String val = langMap.get(key);
                    langMap.put(key, HtmlUtils.htmlUnescape(val));
                }
            }
        }
        jsonResult.setSuccess(true);
        jsonResult.setObj(langMap);
        return jsonResult;
    }

    private List<AppBanner> listBanner () {
        List<AppBanner> list = new ArrayList<AppBanner>();
        String text = redisService.get(StringConstant.CACHE_BANNER);
        if (!StringUtils.isEmpty(text)) {
            list = JSON.parseArray(text, AppBanner.class);
        }
        return list;
    }

    @GetMapping(value = "/getSiteIcon")
    @ApiOperation(value = "获取站点logo", httpMethod = "GET", notes = "获取站点logo")
    @ResponseBody
    public String getSiteIcon(HttpServletRequest request) {
        String config = redisService.get("configCache:all");
        if (!StringUtils.isEmpty(config)) {
            JSONObject parseObject = JSONObject.parseObject(config);
            if (!parseObject.isEmpty()) {
                return StringUtils.isEmpty(parseObject.getString("siteIcon")) ? "" : parseObject.getString("siteIcon");
            }
        }
        return "";
    }

    /**
     * 获取banner图
     *
     * @return
     */
    @GetMapping(value = "/banner")
    @ApiOperation(value = "获取banner图", httpMethod = "GET", notes = "获取banner图")
    @ResponseBody
    public ApiJsonResult<List<AppBanner>> banner (
            @ApiParam(name = "langCode", value = "语种", required = true) @RequestParam("langCode") String langCode,
            @ApiParam(name = "source", value = "来源，电脑端：0,手机端：1", required = true) @RequestParam("source") Integer source) {
        ApiJsonResult<List<AppBanner>> appBannerJsonResult = new ApiJsonResult<List<AppBanner>>();
        List<AppBanner> list = this.listBanner();
        List<AppBanner> list2 = new ArrayList<AppBanner>();
        for (AppBanner appBanner : list) {
            String piclan = appBanner.getLangCode();
            Integer type = appBanner.getApplicationType();
            String webUrl = appBanner.getRemark2();
            if (appBanner.getSort() == null) {
                appBanner.setSort(0);
            }
            if ("1".equals(appBanner.getWhereUse()) && langCode.equals(piclan) && type == source) {
                appBanner.setRemark2(HtmlUtils.htmlUnescape(webUrl));
                list2.add(appBanner);
            }
        }
        Collections.sort(list2);
        appBannerJsonResult.setObj(list2).setSuccess(true);
        return appBannerJsonResult;
    }

    /**
     * 获取首页宣传图和app下载图
     *
     * @return
     */
    @GetMapping(value = "/indexImg")
    @ApiOperation(value = "获取首页宣传图和app下载图", httpMethod = "GET", notes = "获取首页宣传图和app下载图")
    @ResponseBody
    public ApiJsonResult indexImg (@ApiParam(name = "langCode", value = "语种", required = true) @RequestParam("langCode") String langCode) {
        ApiJsonResult apiJsonResult = new ApiJsonResult();
        Map<String, String> map = new HashMap<>();
        List<AppBanner> list = this.listBanner();
        for (AppBanner appBanner : list) {
            String piclan = appBanner.getLangCode();
            if (!langCode.toString().equals(piclan)) {
                continue;
            }
            if (appBanner.getWhereUse() != null && appBanner.getWhereUse().equals("2")) {
                map.put("promiseBgImg", appBanner.getPicturePath());
            }
            //app下载图片
            if (appBanner.getWhereUse() != null && appBanner.getWhereUse().equals("3")) {
                map.put("apploadBgImg", appBanner.getPicturePath());
            }
        }
        Map<String, Object> root = BaseConfUtil.getConfigByKey(Constant.baseConfig, langCode);
        if (!root.isEmpty()) {
            //是否开启多语言
            map.put("isOpenLanguage", root.get("isOpenLanguage") + "");
            //seo标题
            map.put("SEOTitle", "-" + root.get("SEOTitle"));
            map.put("siteLogo", "" + root.get("siteLogo"));
            map.put("siteCopyright", "" + root.get("siteCopyright"));
        }
        // 从redis中获取系统语种信息
        String langList = redisService.hget("new_app_dic", "sysLanguage");
        Map<String, Object> appQRCode = BaseConfUtil.getConfigByKey("configCache:appQRCodeConfig");
        //安卓图片
        map.put("androImg", appQRCode.get("android_RQCode") + "");
        //ios图片
        map.put("iosImg", appQRCode.get("ios_RQCode") + "");

        apiJsonResult.setObj(JSONObject.toJSONString(map));
        apiJsonResult.setSuccess(true);
        return apiJsonResult;
    }

    /**
     * 查询银行列表接口
     * @return
     */
    @GetMapping("/bankList")
    @ApiOperation(value = "查询银行列表接口", httpMethod = "GET",  notes = "查询银行列表接口")
    @ResponseBody
    public ApiJsonResult bankList () {
        ApiJsonResult jsonResult = new ApiJsonResult();
        String bankList = redisService.hget("new_app_dic", "bankType");
        if (!StringUtils.isEmpty(bankList)) {
            jsonResult.setSuccess(true);
            jsonResult.setObj(bankList);
            return jsonResult;
        }
        jsonResult.setSuccess(false);
        return jsonResult;
    }

    /**
     * 查询地区列表接口，传key值则查询市列表
     * @return
     */
    @GetMapping("/areaList")
    @ApiOperation(value = "查询地区列表接口，传key值则查询市列表", httpMethod = "GET", notes = "查询地区列表接口，传key值则查询市列表")
    @ResponseBody
    public ApiJsonResult areaList(@ApiParam(name = "key", value = "省的key值", required = true) @RequestParam("key") String key) {
        ApiJsonResult jsonResult = new ApiJsonResult();
        String value = redisService.get(StringConstant.AREA_CACHE);
        if (!StringUtils.isEmpty(key)) {
            JSONArray jsona = JSONArray.parseArray(value);
            String json_n = "";
            for (int i = 0; i < jsona.size(); i++) {
                String jsonvalur = jsona.getString(i);
                if (jsonvalur.contains(key)) {
                    JSONObject jsono = jsona.getJSONObject(i);
                    json_n = jsono.get("cities").toString().replace("[", "").replace("]", "");
                }
            }
            jsonResult.setObj(json_n);
        } else {
            jsonResult.setObj(value);
        }
        jsonResult.setSuccess(true);
        return  jsonResult;
    }

    /**
     * 从redis中获取系统语种字典
     *
     * @param request
     * @return
     */
    @ApiOperation(value = "获取系统语种列表信息", httpMethod = "GET", notes = "获取系统语种列表信息")
    @GetMapping("/getSysLangDicInfo")
    @ResponseBody
    public ApiJsonResult getSysLangDicInfo(HttpServletRequest request) {
        ApiJsonResult jsonResult = new ApiJsonResult();
        // 从redis中获取系统语种信息
        String langList = redisService.hget("new_app_dic", "sysLanguage");
        jsonResult.setSuccess(true);
        jsonResult.setObj(langList);
        return jsonResult;
    }

    /**
     * 获取挖矿分红banner图
     * @return
     */
    @ApiOperation(value = "获取挖矿分红banner图", httpMethod = "GET", notes = "获取挖矿分红banner图")
    @GetMapping("/minibanner")
    @ResponseBody
    public ApiJsonResult<List<AppBanner>> minibanner(
            @ApiParam(name = "langCode", value = "语种", required = true) @RequestParam("langCode") String langCode,
            @ApiParam(name = "source", value = "来源（1为手机端,0为电脑端）", required = true) @RequestParam("source") Integer source) {
        ApiJsonResult<List<AppBanner>> jsonresult = new ApiJsonResult<List<AppBanner>>();
        List<AppBanner> list = this.listBanner();
        List<AppBanner> list2 = new ArrayList<AppBanner>();
        if (list != null && list.size() > 0) {
            for (AppBanner appBanner : list) {
                String piclan = appBanner.getLangCode();
                Integer type = appBanner.getApplicationType();
                if ("6".equals(appBanner.getWhereUse()) && piclan.equals(langCode) && type == source) {
                    if (appBanner.getSort() == null) {
                        appBanner.setSort(0);
                    }
                    list2.add(appBanner);
                }
            }
            if (list2 != null && list2.size() > 0) {
                Collections.sort(list2);
                jsonresult.setSuccess(true);
                jsonresult.setObj(list2);
                return jsonresult;
            }
        }
        jsonresult.setSuccess(false);
        return jsonresult;
    }

    /**
     * 加载footer中的站点地图信息
     *
     * @param request
     * @return
     */
    @ApiOperation(value = "加载footer中的站点地图信息", httpMethod = "GET", notes = "加载footer中的站点地图信息")
    @GetMapping("/loadSiteMapInfo")
    @ResponseBody
    public List<Map<String, Object>> loadSiteMapInfo(
            @ApiParam(name = "langCode", value = "语种", required = true) @RequestParam("langCode") String langCode,
            HttpServletRequest request) {
        // 调用远程查询对应语种的文章类别名称信息
        return remoteAppArticleService.loadSiteMapInfo(langCode);
    }

    @ApiOperation(value = "加载下载app页面信息信息", httpMethod = "GET", notes = "加载下载app页面信息信息")
    @GetMapping("/toAppDownLoad")
    @ResponseBody
    public Map<String, Object> toAppDownLoad(
            @ApiParam(name = "langCode", value = "语种", required = true) @RequestParam("langCode") String langCode,
            HttpServletRequest request) {
        Map<String, Object> returnMap = new HashMap<>();
        List<AppBanner> list = this.listBanner();
        for (AppBanner appBanner : list) {
            String piclan = appBanner.getLangCode();
            if (!langCode.equals(piclan)) {
                continue;
            }
            if (appBanner.getWhereUse() != null && appBanner.getWhereUse().equals("4")) {
                returnMap.put("appDownLoadImg", appBanner.getPicturePath());
                break;
            }
        }

        Map<String, Object> root = BaseConfUtil.getConfigByKey(Constant.baseConfig, langCode);
        Map<String, Object> appQRCode = BaseConfUtil.getConfigByKey("configCache:appQRCodeConfig");
        returnMap.put("SEOTitle", "-" + root.get("SEOTitle"));
        returnMap.put("siteLogo", root.get("siteLogo"));
        returnMap.put("siteCopyright", root.get("siteCopyright"));
        returnMap.put("androImg", appQRCode.get("android_RQCode"));
        returnMap.put("iosImg", appQRCode.get("ios_RQCode"));
        returnMap.put("appVersion", root.get("appVersion"));
        returnMap.put("exchangeIosUrl", appQRCode.get("exchangeIosUrl"));
        returnMap.put("exchangeAndroidUrl", appQRCode.get("exchangeAndroidUrl"));
        return returnMap;
    }

    @ApiOperation(value = "获取固定配置信息", httpMethod = "GET", notes = "获取固定配置信息")
    @GetMapping("/getConfigInfo")
    @ResponseBody
    public Map<String, Object> getConfigInfo(
            @ApiParam(name = "langCode", value = "语种", required = true) @RequestParam("langCode") String langCode,
            HttpServletRequest request){
        Map<String, Object> returnMap = new HashMap<>();
        Map<String, Object> root = BaseConfUtil.getConfigByKey(Constant.baseConfig, langCode);
        if (root != null) {
            if (root.get("SEOTitle") != null) {
                String SEOTitle = root.get("SEOTitle").toString();
                returnMap.put("SEOTitle", "-" + SEOTitle);
            }
            if (root.get("siteLogo") != null) {
                String siteLogo = root.get("siteLogo").toString();
                returnMap.put("siteLogo", siteLogo.split("~")[0]);
            }
            if (root.get("siteCopyright") != null) {
                String siteCopyright = root.get("siteCopyright").toString();
                returnMap.put("siteCopyright", siteCopyright.split("~")[0]);
            }
            if (root.get("siteName") != null) {
                String siteName = root.get("siteName").toString();
                returnMap.put("siteName", siteName.split("~")[0]);
            }

            if (root.get("servicePhone") != null) {
                String servicePhone = root.get("servicePhone").toString();
                returnMap.put("servicePhone", servicePhone);
            }
            if (root.get("serviceEmail") != null) {
                String serviceEmail = root.get("serviceEmail").toString();
                returnMap.put("serviceEmail", serviceEmail.split("~")[0]);
            }
            if (root.get("serviceQQ") != null) {
                String serviceQQ = root.get("serviceQQ").toString();
                returnMap.put("serviceQQ", serviceQQ.split("~")[0]);
            }
            if (root.get("companyAdress") != null) {
                String companyAdress = root.get("companyAdress").toString();
                returnMap.put("companyAdress", companyAdress.split("~")[0]);
            }
            if (root.get("workHours") != null) {
                String workHours = root.get("workHours").toString();
                returnMap.put("workHours", workHours);
            }
            if (root.get("weChat") != null) {
                String weChat = root.get("weChat").toString();
                returnMap.put("weChat", weChat);
            }
            if (root.get("contactInformation") != null) {//联系方式
                String contactInformation = root.get("contactInformation").toString();
                returnMap.put("contactInformation", contactInformation);
            }
        }
        return returnMap;
    }

    @ApiOperation(value = "首页走势图", httpMethod = "GET", notes = "首页走势图")
    @GetMapping("/hryIndex")
    @ResponseBody
    public ApiJsonResult hryIndex(HttpServletRequest request){
        ApiJsonResult jsonResult = new ApiJsonResult();
        String config = redisService.get("configCache:all");
        if (!StringUtils.isEmpty(config)) {
            JSONObject parseObject = JSONObject.parseObject(config);
            String platCoin = (String) parseObject.get("platCoin");//平台币
            String compromiseCoin = (String) parseObject.get("compromiseCoin");//折合币种
            String key = platCoin + "_" + compromiseCoin;
            String coinData = redisService.get(key + ":klinedata:TransactionOrder_" + key + "_1");
            if (!StringUtils.isEmpty(coinData)) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("platCoin", platCoin);
                map.put("compromiseCoin", compromiseCoin);

                JSONArray pNow = JSONArray.parseArray(coinData);
                List<TransactionOrder> listNow = ObjectUtil.beanList(pNow, TransactionOrder.class);
                StringBuilder sb = new StringBuilder();
                int count = 0;
                if (listNow.size() > 500) {
                    count = 500;
                } else {
                    count = listNow.size();
                }
                for (int i = count - 1; i > 0; i--) {
                    sb.append(listNow.get(i).getStartPrice() + ",");
                }
                String sbs = sb.delete(sb.length() - 1, sb.length()).toString();
                map.put("sbs", sbs);
                int keepDecimalForCurrency = 0;
                String strs = redisService.get("cn:coinInfoList");
                if (!StringUtils.isEmpty(strs)) {
                    JSONArray array = JSON.parseArray(strs);
                    if (array != null) {
                        for (int i = 0; i < array.size(); i++) {
                            JSONObject jsonObject = array.getJSONObject(i);
                            if (platCoin.equals(jsonObject.getString("coinCode")) && compromiseCoin.equals(jsonObject.getString("fixPriceCoinCode"))) {
                                keepDecimalForCurrency = jsonObject.getInteger("keepDecimalForCurrency");
                                break;
                            }
                        }
                    }
                }

                //互融币指数
                //最新价
                String newPrice = redisService.get(key + ":currentExchangPrice");
                if (StringUtils.isEmpty(newPrice)) {
                    map.put("newPrice", new BigDecimal(0));
                } else {
                    map.put("newPrice", new BigDecimal(newPrice).setScale(keepDecimalForCurrency, BigDecimal.ROUND_HALF_UP));
                }
                //开
                //mav.addObject("open", listNow.get(0).getStartPrice());
                //高
                map.put("high", listNow.get(0).getMaxPrice().setScale(keepDecimalForCurrency, BigDecimal.ROUND_HALF_UP));
                //低
                map.put("low", listNow.get(0).getMinPrice().setScale(keepDecimalForCurrency, BigDecimal.ROUND_HALF_UP));
                //收
                //mav.addObject("close", listNow.get(0).getEndPrice());
                //日涨跌
                String coinStr = redisService.get("cn:coinInfoList2");
                BigDecimal yesterdayPrice = new BigDecimal(0);
                if (!StringUtils.isEmpty(coinStr)) {
                    List<Coin2> coins = JSON.parseArray(coinStr, Coin2.class);
                    for (Coin2 c : coins) {
                        if (key.equals(c.getCoinCode() + "_" + c.getFixPriceCoinCode())) {
                            if (!StringUtils.isEmpty(c.getYesterdayPrice())) {
                                yesterdayPrice = new BigDecimal(c.getYesterdayPrice());
                            }
                        }
                    }
                }
                String str = redisService.get(key + ":PeriodLastKLineList");
                if (!StringUtils.isEmpty(key)) {
                    JSONArray jsonv = JSON.parseArray(str);
                    if (jsonv != null && jsonv.getString(5) != null) {
                        JSONObject jsonv_ = JSON.parseObject(jsonv.getString(5));
                        if ("1day".equals(jsonv_.getString("period"))) {
                            BigDecimal currentExchangPrice = new BigDecimal(0);
                            //上一笔交易价格
                            String orders = redisService.get(key + ":pushNewListRecordMarketDesc");
                            if (!StringUtils.isEmpty(orders)) {
                                MarketTrades marketTrades = JSON.parseObject(orders, MarketTrades.class);
                                // 最新价格
                                if (marketTrades != null) {
                                    List<MarketTradesSub> trades = marketTrades.getTrades();
                                    if (trades != null && trades.size() > 0) {
                                        if (trades.size() > 1) {
                                            MarketTradesSub marketTradesSub0 = trades.get(0);
                                            currentExchangPrice = marketTradesSub0.getPrice();
                                        } else {
                                            MarketTradesSub marketTradesSub0 = trades.get(0);
                                            currentExchangPrice = marketTradesSub0.getPrice();
                                        }
                                    }
                                }
                            }
                            if (BigDecimal.ZERO.compareTo(yesterdayPrice) != 0) {
                                if (BigDecimal.ZERO.compareTo(currentExchangPrice) != 0) {
                                    BigDecimal divide = (currentExchangPrice.subtract(yesterdayPrice)).divide(yesterdayPrice, 5, BigDecimal.ROUND_HALF_DOWN).multiply(new BigDecimal(100));
                                    map.put("riseAndFall", divide.setScale(3, BigDecimal.ROUND_HALF_UP));
                                } else {
                                    map.put("riseAndFall", new BigDecimal(0));
                                }
                            } else {
                                map.put("riseAndFall", new BigDecimal(0));
                            }
                        }
                    }
                }
                //24小时成交总量
                String new24hoursvol_str = redisService.get(key + ":new24hoursamout");
                if (!StringUtils.isEmpty(new24hoursvol_str)) {
                    map.put("transactionSum", new BigDecimal(new24hoursvol_str).setScale(0, BigDecimal.ROUND_HALF_UP));
                } else {
                    map.put("transactionSum", new BigDecimal(0));

                }
                jsonResult.setSuccess(true);
                jsonResult.setObj(map);
                return jsonResult;
            }
        }
        jsonResult.setSuccess(false);
        return jsonResult;
    }

    @ApiOperation(value = "数币行情列表", httpMethod = "GET", response = ApiJsonResult.class,
            notes = "name 币种编码，circulation 流通量，Increase 涨幅，marketvalue 市值，price 价格，turnover 24H成交额，picturePath 币种logo")
    @GetMapping("/coinListInfo")
    @ResponseBody
    public List<Map<String, Object>> coinListInfo(HttpServletRequest request,
                                                  @ApiParam(name = "langCode", value = "语种", required = true) @RequestParam("langCode") String langCode){
        List<Map<String, Object>> coinInfoList = new ArrayList<>();
        // 获取 平台币
        String platCoin = RedisStaticUtil.getAppConfigValue(RulesConfig.RulesCoinKey, "coinCode");
        /*// 获取 平台币市价(RMB)
        String platformMarkPriceStr = BaseConfUtil.getConfigSingle("platformMarkPrice", "configCache:basefinanceConfig");
        BigDecimal platformMarkPrice = new BigDecimal(platformMarkPriceStr);
        BigDecimal codePrice = BigDecimal.ZERO;*/
        // 如果是其它币，取非小号
        RedisService redisService = SpringUtil.getBean("redisService");
        String result = redisService.get("cn:hrySaaSCoinInfo");
        String usdtIncrease = "";
        if (StringUtil.isNull(result)) {
            List<FeixiaohaoPriceVo> list = JSON.parseArray(result, FeixiaohaoPriceVo.class);
            for(FeixiaohaoPriceVo fxh : list){
                Map<String, Object> coinMap = new HashMap<>();
                coinMap.put("name",fxh.getName());
                //coinMap.put("price",fxh.getPrice());
                //法币符号
                String coinSymbol = "";
                String rate = "";
                String financeConfig = redisService.get("CointoMoney:" + langCode);
                if (!StringUtils.isEmpty(financeConfig)) {
                    ExCointoMoney exCointoMoney = JSONObject.parseObject(financeConfig, ExCointoMoney.class);
                    coinSymbol = exCointoMoney.getCoinSymbol();
                    rate = exCointoMoney.getRate();
                    coinSymbol = HtmlUtils.htmlUnescape(coinSymbol);
                }
                coinMap.put("coinSymbol", coinSymbol);
                // 换成单币种获取的价格
                String price = redisService.hget("hry:coinPrice", fxh.getName());
                if ("zh_CN".equals(langCode)) {
                    coinMap.put("price",price);
                } else {
                    if (!"".equals(rate) && !"0".equals(rate)) {
                        coinMap.put("price",new BigDecimal(price).divide(new BigDecimal(rate),8,BigDecimal.ROUND_HALF_UP));
                    }
                }
                if ("USDT".equals(fxh.getName())) {
                    usdtIncrease = fxh.getIncrease();
                }
                coinMap.put("increase",fxh.getIncrease());
                coinMap.put("circulation",fxh.getCirculation());
                coinMap.put("marketvalue",fxh.getMarketvalue());
                coinMap.put("turnover",fxh.getTurnover());
                ExProduct exProduct = JSONObject.parseObject(redisService.hget("ex:Product", fxh.getName()), ExProduct.class);
                if (exProduct != null) {
                    coinMap.put("picturePath",exProduct.getPicturePath());
                }
                coinInfoList.add(coinMap);
            }
        }

        // 获取USDT的涨跌幅 赋值给 平台币
        for (Map<String, Object> map : coinInfoList) {
            if (map.get("name").equals(platCoin)) {
                map.put("increase", usdtIncrease);
                // 当前平台币价格
                String value = RedisStaticUtil.getAppConfigValue(RulesConfig.RulesCoinKey, "issuePrice");
                map.put(platCoin+"_USDT", value);
            } else {
                map.put(map.get("name")+"_USDT", 1);
            }
        }
        return coinInfoList;
    }
}
