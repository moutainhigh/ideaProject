package hry.app.trade;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import hry.app.kline.model.MarketDepths;
import hry.app.kline.model.MarketDetail;
import hry.app.kline.model.MarketTrades;
import hry.app.kline.model.MarketTradesSub;
import hry.bean.ApiJsonResult;
import hry.manage.remote.model.Coin2;
import hry.redis.common.utils.RedisService;
import hry.util.common.BaseConfUtil;
import hry.util.common.Constant;
import hry.util.common.ContextUtil;
import hry.util.common.SpringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.*;

/**
 * TradeMarketController
 *
 * @author: denghf
 * @version: V1.0
 * @Date: 2018/3/14 16:52
 */
@Controller
@RequestMapping("/tradeMarket")
@Api(value= "币币交易", description ="币币交易", tags = "币币交易")
public class TradeMarketController {

    @Resource
    private RedisService redisService;


    /**
     * 获取交易对
     *
     * @return
     */
    @ApiOperation(value = "获取交易对", httpMethod = "GET", response = ApiJsonResult.class, notes = "获取交易对")
    @GetMapping("/tradePair")
    @ResponseBody
    public ApiJsonResult tradePair() {
        ApiJsonResult jsonResult = new ApiJsonResult();
        String val = redisService.get("cn:productFixList");
        jsonResult.setSuccess(true);
        jsonResult.setObj(val);
        return jsonResult;
    }

    /**
     * 获取交易对 最新价格数据
     *
     * @return
     */
    @ApiOperation(value = "node调用-获取交易对 最新价格数据", httpMethod = "GET", response = ApiJsonResult.class, notes = "node调用-获取交易对 最新价格数据")
    @GetMapping("/coinPrice")
    @ResponseBody
    public ApiJsonResult coinPrice(HttpServletRequest request) {
        Map<String, Map<String, Object>> mapAll = new HashMap<String, Map<String, Object>>();
        ApiJsonResult j = new ApiJsonResult();
        //昨日收盘价
        BigDecimal yesterdayPrice = new BigDecimal(0);
        String coinStr = redisService.get("cn:coinInfoList2");
        if(!StringUtils.isEmpty(coinStr)){
            List<Coin2> coins = JSON.parseArray(coinStr, Coin2.class);
            String coinCode = "";
            for (int i = 0; i < coins.size(); i++) {
                Map<String, Object> map = new HashMap<String, Object>();
                coinCode = coins.get(i).getCoinCode() + "_" + coins.get(i).getFixPriceCoinCode();
                yesterdayPrice = new BigDecimal(coins.get(i).getYesterdayPrice());
                String str = redisService.get(coinCode + ":PeriodLastKLineList");
                if (str != null) {
                    JSONArray jsonv = JSON.parseArray(str);
                    if (jsonv.getString(5) != null) {
                        JSONObject jsonv_ = JSON.parseObject(jsonv.getString(5));
                        if ("1day".equals(jsonv_.getString("period"))) {
                            BigDecimal currentExchangPrice = new BigDecimal(0);
                            //上一笔交易价格
                            String orders = redisService.get(coinCode + ":pushNewListRecordMarketDesc");
                            if (!StringUtils.isEmpty(orders)) {
                                MarketTrades marketTrades = JSON.parseObject(orders, MarketTrades.class);
                                // 最新价格
                                if (marketTrades != null) {
                                    List<MarketTradesSub> trades = marketTrades.getTrades();
                                    if (trades != null && trades.size()>0) {
                                        MarketTradesSub marketTradesSub0 = trades.get(0);
                                        map.put("currentExchangPrice", marketTradesSub0.getPrice());
                                        currentExchangPrice = marketTradesSub0.getPrice();
                                    }
                                }
                            }
                            //当日成交总量
                            map.put("transactionSum", jsonv_.getString("amount"));
                            map.put("maxPrice", jsonv_.getString("priceHigh"));
                            map.put("minPrice", jsonv_.getString("priceLow"));
                            // 开盘价
                            map.put("openPrice", new BigDecimal(jsonv_.getString("priceOpen")));

                            if(BigDecimal.ZERO.compareTo(yesterdayPrice)!=0){
                                if(BigDecimal.ZERO.compareTo(currentExchangPrice)!=0){
                                    BigDecimal divide = (currentExchangPrice.subtract(yesterdayPrice)).divide(yesterdayPrice,5,BigDecimal.ROUND_HALF_DOWN).multiply(new BigDecimal(100));
                                    map.put("riseAndFall",divide);
                                }else{
                                    map.put("riseAndFall",0);
                                }
                            }else{
                                map.put("riseAndFall",0);
                            }
                            //买一价
                            String buyonePrice = redisService.get(coinCode + ":buyonePrice");
                            map.put("buyonePrice", buyonePrice);
                            //卖一价
                            String sellonePrice = redisService.get(coinCode + ":sellonePrice");
                            map.put("sellonePrice", sellonePrice);
                        } else {
                            map.put("currentExchangPrice", 0);
                            map.put("lastExchangPrice", 0);
                            map.put("transactionSum", 0);
                            map.put("maxPrice", 0);
                            map.put("minPrice", 0);
                            // 开盘价
                            map.put("openPrice", new BigDecimal(0));
                            map.put("buyonePrice", 0);
                            map.put("sellonePrice", 0);
                        }
                    } else {
                        map.put("currentExchangPrice", 0);
                        map.put("lastExchangPrice", 0);
                        map.put("transactionSum", 0);
                        map.put("maxPrice", 0);
                        map.put("minPrice", 0);
                        // 开盘价
                        map.put("openPrice", new BigDecimal(0));
                        map.put("buyonePrice", 0);
                        map.put("sellonePrice", 0);
                    }
                } else {
                    map.put("currentExchangPrice", 0);
                    map.put("lastExchangPrice", 0);
                    map.put("transactionSum", 0);
                    map.put("maxPrice", 0);
                    map.put("minPrice", 0);
                    // 开盘价
                    map.put("openPrice", new BigDecimal(0));
                    map.put("buyonePrice", 0);
                    map.put("sellonePrice", 0);
                }
                mapAll.put(coinCode, map);
            }
        }
        j.setSuccess(true);
        j.setObj(mapAll);
        return j;
    }

    /**
     * 买卖5价
     *
     * @return
     */
    @ApiOperation(value = "node调用-买卖5价", httpMethod = "GET", response = ApiJsonResult.class, notes = "node调用-买卖5价")
    @GetMapping("/purchaseOrder")
    @ResponseBody
    public ApiJsonResult purchaseOrder(HttpServletRequest request) {
        Map<String, Map<String, Object>> mapAll = new HashMap<String, Map<String, Object>>();
        ApiJsonResult j = new ApiJsonResult();
        Map<String, Object> map = new HashMap<String, Object>();
        RedisService redisService = SpringUtil.getBean("redisService");
        String coinCodeVal = redisService.get("cn:productFixList");
        if(!StringUtils.isEmpty(coinCodeVal)){
            List<String> list = JSON.parseArray(coinCodeVal, String.class);
            for (int i = 0; i < list.size(); i++) {
                String str = redisService.get(list.get(i) + ":pushEntrusMarket");
                JSONObject jsonObject = JSON.parseObject(str);
                if(null != jsonObject ){
                    String depths = jsonObject.getString("depths");
                    JSONObject jsonDepths = JSON.parseObject(depths);
                    //卖5
                    JSONArray asks = jsonDepths.getJSONArray("asks");
                    //买5
                    JSONArray bids = jsonDepths.getJSONArray("bids");
                    map.put("buy", bids);
                    map.put("sell", asks);
                    mapAll.put(list.get(i), map);
                }
            }
            j.setSuccess(true);
            j.setObj(mapAll);
            return j;
        }
        j.setSuccess(false);
        return j;
    }

    /**
     * 获取更多的委托深度
     * @param request
     * @param
     * @return
     */
    @ApiOperation(value = "node调用-获取更多的委托深度", httpMethod = "GET", response = Map.class, notes = "node调用-获取更多的委托深度")
    @GetMapping("/moreMarketDetail")
    @ResponseBody
    public Map<String, Map<String, List<BigDecimal[]>>> moreMarketDetail(HttpServletRequest request) {
        String key = "pushEntrusMarket100";
        String productListStr = redisService.get(ContextUtil.getWebsite() + ":productFixList");
        Map<String, Map<String, List<BigDecimal[]>>> finalmap = new HashMap<>();
        if (!StringUtils.isEmpty(productListStr)) {
            List<String> productList = JSON.parseArray(productListStr, String.class);
            for (String coinCode : productList) {
                Map<String, List<BigDecimal[]>> depth = new HashMap<>();
                List<BigDecimal[]> askslist1 = new ArrayList<>();
                List<BigDecimal[]> bidslist1 = new ArrayList<>();
                ArrayList<MarketDetail> list = new ArrayList<MarketDetail>();

                //获取深度数据
                String pushEntrusMarket = redisService.get(coinCode + ":" + key);
                if (!StringUtils.isEmpty(pushEntrusMarket)) {
                    // 获得委托数据
                    MarketDepths marketDepths = JSON.parseObject(pushEntrusMarket, MarketDepths.class);
                    Map<String, List<BigDecimal[]>> depths = marketDepths.getDepths();

                    List<BigDecimal[]> askslist = depths.get("asks");
                    List<BigDecimal[]> bidslist = depths.get("bids");
                    //排序
                    Collections.sort(askslist, new Comparator<BigDecimal[]>() {
                        @Override
                        public int compare(BigDecimal[] o1, BigDecimal[] o2) {
                            return o1[0].compareTo(o2[0]);
                        }
                    });
                    Collections.sort(bidslist, new Comparator<BigDecimal[]>() {
                        @Override
                        public int compare(BigDecimal[] o1, BigDecimal[] o2) {
                            return o2[0].compareTo(o1[0]);
                        }
                    });
                    BigDecimal c = new BigDecimal("0");
                    for (int i = 0; i < askslist.size(); i++) {
                        c = askslist.get(i)[1].add(c);
                        askslist.get(i)[1] = c;
                        askslist1.add(askslist.get(i));
                    }
                    c = new BigDecimal("0");
                    for (int i = 0; i < bidslist.size(); i++) {
                        c = bidslist.get(i)[1].add(c);
                        bidslist.get(i)[1] = c;
                        bidslist1.add(bidslist.get(i));
                    }
                    depth.put("asks", askslist1);
                    depth.put("bids", bidslist1);
                    finalmap.put(coinCode, depth);
                }
            }
            return finalmap;
        }
        return null;
    }

    @ApiOperation(value = "初始化基础交易数据", httpMethod = "GET", response = Map.class, notes = "初始化基础交易数据")
    @RequestMapping("/initTradeMarket")
    @ResponseBody
    public Map<String, Object> initTradeMarket(
            @ApiParam(name = "coinCode", value = "交易币种名称", required = true) @RequestParam("coinCode") String coinCode,
            @ApiParam(name = "langCode", value = "语种", required = true) @RequestParam("langCode") String langCode,
            HttpServletRequest request) {
        Map<String, Object> returnMap = new HashMap<>();
        if (StringUtils.isEmpty(coinCode)) {
            String val = redisService.get("cn:productFixList");
            if (!StringUtils.isEmpty(val)) {
                JSONArray jsonv = JSON.parseArray(val);
                if (jsonv.size() != 0) {
                    returnMap.put("defaultCoinCode", jsonv.get(0));
                }
            }
            return returnMap;
        }

        String sellonePrice = redisService.get(coinCode + ":sellonePrice");
        returnMap.put("sellonePrice", sellonePrice);
        String buyonePrice = redisService.get(coinCode + ":buyonePrice");
        returnMap.put("buyonePrice", buyonePrice);
        returnMap.put("coinCode", coinCode);

        String[] spl = coinCode.split("_");
        String str = redisService.get("cn:coinInfoList");
        if (!StringUtils.isEmpty(str)) {
            JSONArray jsonArray = JSONArray.parseArray(str);
            if (jsonArray.size() > 0) {
                for (int i = 0; i < jsonArray.size(); i++) {
                    JSONObject jsonObject = JSONObject.parseObject(jsonArray.getString(i));
                    if (jsonObject.getString("coinCode") != null
                            && jsonObject.getString("coinCode").equals(spl[0])
                            && jsonObject.getString("fixPriceCoinCode") != null
                            && jsonObject.getString("fixPriceCoinCode").equals(spl[1])) {
                        returnMap.put("buyFeeRate", jsonObject.getBigDecimal("buyFeeRate"));
                        returnMap.put("sellFeeRate", jsonObject.getBigDecimal("sellFeeRate"));
                        returnMap.put("sellMinCoin", jsonObject.getBigDecimal("sellMinCoin"));
                        returnMap.put("oneTimeOrderNum", jsonObject.getBigDecimal("oneTimeOrderNum"));
                        returnMap.put("keepDecimalForCoin", jsonObject.getBigDecimal("keepDecimalForCoin"));
                        returnMap.put("keepDecimalForCurrency", jsonObject.getBigDecimal("keepDecimalForCurrency"));
                        returnMap.put("picPath", jsonObject.getString("picturePath"));
                    }
                }
            }
        }
        Map<String, Object> root = BaseConfUtil.getConfigByKey(Constant.baseConfig, langCode);
        returnMap.put("SEOTitle", "-" + root.get("SEOTitle"));
        return returnMap;
    }
}
