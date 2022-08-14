package hry.app.kline.netty.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import hry.app.kline.model.*;
import hry.app.kline.netty.config.NettyConfig;
import hry.app.kline.netty.model.ResponseData;
import hry.app.lend.websocket.model.kline.MessageStatus;
import hry.lend.constant.DealConstant;
import hry.lend.constant.ExchangeLendKey;
import hry.lend.model.trade.ExLendConfig;
import hry.manage.remote.RemoteBaseInfoService;
import hry.manage.remote.RemoteManageService;
import hry.manage.remote.model.Coin;
import hry.manage.remote.model.Coin2;
import hry.manage.remote.model.ExCointoMoney;
import hry.manage.remote.model.User;
import hry.redis.common.utils.RedisService;
import hry.util.SortListUtil;
import hry.util.common.ContextUtil;
import hry.util.common.SpringUtil;
import hry.util.date.DateUtil;
import hry.util.klinedata.TransactionOrder;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;

@RestController
@RequestMapping("")
public class WebSocketClient {
    @Resource
    private RedisService redisService;

    @Resource(name = "jedisPool")
    private JedisPool jedisPool;

    /**
     * 推送交易数据
     */
    public void pushTrade() {
          /*
               交易区数据
             */
        Map<String, JSONArray> area = indexv1();
        Map exCointoCoinS = redisService.hgetall("ex:cointoCoin");
        exCointoCoinS.forEach((K, V) -> {
            String coinName = (String) K;
            coinName = coinName.replace(":","_");
            ExCointoCoin exCointoCoin = JSONObject.parseObject((String) V, ExCointoCoin.class);
            //最新节点数据
            ResponseData responseData = lastKLine(coinName);
            Map<String, Object> klineMap = new HashMap<>();
            klineMap.put("type", "node");
            klineMap.put("data", responseData);

            /**
             * 推送深度数据
             */
            Map<String, List<BigDecimal[]>> deep = moreMarketDetail(coinName);

            JSONArray lendArray = area.get("lend");
            JSONArray commonArray = area.get("common");

            /*委托单数据
             */
            List<MarketDetail> buySellData = marketDetail(coinName);

            Map<String, Object> all = new HashMap<>();
            all.put("deep", deep);
            all.put("buySellData", buySellData);


            if (NettyConfig.tradingGroup.size() > 0) {
                String finalCoinName = coinName;
                NettyConfig.tradingGroup.iterator().forEachRemaining(channel -> {
                    MessageStatus messageStatus = TradingWebSockeHandler.tradingInfo.get(channel);
                    if (messageStatus == null) {
                        return;
                    }

                    String coin = messageStatus.getCoinCode();
                    if (!StringUtils.isEmpty(coin) && finalCoinName.equals(coin)) {
                        if (!"trading".equals(messageStatus.getCmd())) {
                            TextWebSocketFrame textWebSocketFrame = new TextWebSocketFrame(JSONObject.toJSONString(klineMap));
                            channel.writeAndFlush(textWebSocketFrame);
                        } else {
                            String type = messageStatus.getType();
                            if (!StringUtils.isEmpty(type) && type.equals("1")) {
                                all.put("area", lendArray);
                            } else {
                                all.put("area", commonArray);
                            }
                            TextWebSocketFrame textWebSocketFrame = new TextWebSocketFrame(JSONObject.toJSONString(all));
                            channel.writeAndFlush(textWebSocketFrame);
                        }

                    }

                });
            }

        });


    }

    /**
     * 补一个最新节点
     */
    public ResponseData lastKLine(String coinCode) {
        ResponseData responseData = new ResponseData();

        RedisService redisService = SpringUtil.getBean("redisService");
        String periodLastKLineListStr = redisService.get(coinCode + ":PeriodLastKLineList");
        if (!org.apache.commons.lang3.StringUtils.isEmpty(periodLastKLineListStr)) {
            List<LastKLinePayload> periodList = JSON.parseArray(periodLastKLineListStr, LastKLinePayload.class);
            if (periodList != null) {
                for (LastKLinePayload l : periodList) {

                    responseData.setOpen(l.getPriceOpen());
                    responseData.setLow(l.getPriceLow());
                    responseData.setHigh(l.getPriceHigh());
                    responseData.setCount(l.getAmount());
                    responseData.setClose(l.getPriceLast());
                    responseData.setBase_vol(l.getAmount());
                    responseData.setQuote_vol(l.getVolume());
                    responseData.setSeq(new BigDecimal(l.getTime()));
                    responseData.setId(new BigDecimal(l.getTime()));
                    responseData.setType("node");
                    return responseData;
                }
            }
        }

        return null;
    }


    /**
     * 推送有哪些交易区
     *
     * @return
     */
    public Map<String, JSONArray> indexv1() {

        Jedis jedis = null;
        Map<String, JSONArray> map = new HashMap<>(2);
        JSONArray commonArray = new JSONArray();
        JSONArray lendArray = new JSONArray();
        try {
            jedis = jedisPool.getResource();

            Locale locale = LocaleContextHolder.getLocale();


            //获取实时价格
            String usdtPrice = jedis.hget("hry:coinPrice", "USDT");

            /*获得usdt对人民币价格*/
            BigDecimal usdttormb = new BigDecimal(StringUtils.isEmpty(usdtPrice) ? "6.8" : usdtPrice);
            //法币符号
            String coinSymbol = "";

            String financeConfig = jedis.get("CointoMoney:" + locale.toString());
            if (!StringUtils.isEmpty(financeConfig)) {
                ExCointoMoney exCointoMoney = JSONObject.parseObject(financeConfig, ExCointoMoney.class);
                usdttormb = new BigDecimal(exCointoMoney.getRate()).multiply(usdttormb);
                coinSymbol = exCointoMoney.getCoinSymbol();

            }


            // 去redis查询产品数量
            String productListStr = jedis.get("cn:coinInfoList");

            if (!StringUtils.isEmpty(productListStr)) {
                List<Coin> productList = JSON.parseArray(productListStr, Coin.class);

                commonArray = getProductList(jedis, usdttormb, coinSymbol, productList);

                List<Coin> lendCoin = new ArrayList<>();
                Set<String> coins = jedis.hkeys(ExchangeLendKey.LEND_CONFIG);
                for (Coin coin : productList) {
                    String coinCode = coin.getCoinCode() + "_" + coin.getFixPriceCoinCode();
                    if (coins.contains(coinCode)) {
                        String configStr = jedis.hget(ExchangeLendKey.LEND_CONFIG, coinCode);
                        if (!StringUtils.isEmpty(configStr)) {
                            ExLendConfig config = JSON.parseObject(configStr, ExLendConfig.class);
                            if (config.getStatus().equals(DealConstant.OPEN)) {
                                lendCoin.add(coin);
                            }
                        }
                    }
                }
                lendArray = getProductList(jedis, usdttormb, coinSymbol, lendCoin);

            }
            map.put("common", commonArray);
            map.put("lend", lendArray);
            return map;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }

        return map;


    }

    private JSONArray getProductList(Jedis jedis, BigDecimal usdttormb, String coinSymbol, List<Coin> productList) {
        JSONArray jsonArray = new JSONArray();
        //排序
        productList.sort((coin1, coin2) -> coin1.getCoinCode().compareTo(coin2.getCoinCode()));

        //统计交易区数量
        Set<String> coinarea = new LinkedHashSet<String>();
        User user = null;
       /* Session session = SecurityUtils.getSubject().getSession(false);
        if (session != null) {
            //查询用户自选区
            user = (User) session.getAttribute("user");
        }*/

        //用户自选交易对
        List<String> mycoins = null;
        if (user != null) {
            RemoteBaseInfoService remoteBaseInfoService = SpringUtil.getBean("remoteBaseInfoService");
            mycoins = remoteBaseInfoService.findCustomerCollection(user.getCustomerId());
            JSONObject obj = new JSONObject();
            obj.put("areaname", SpringUtil.diff("zixuan"));
            obj.put("areanameview", SpringUtil.diff("zixuan"));

            //创建list
            ArrayList<JSONObject> list = new ArrayList<JSONObject>();
            if (mycoins != null && mycoins.size() > 0) {
                for (String coinCode : mycoins) {
                    //遍历
                    for (Coin coin : productList) {
                        if (coinCode.equals(coin.getCoinCode() + "_" + coin.getFixPriceCoinCode())) {
                            JSONObject data = createData(jedis, coin);
                            data.put("usdttormb", usdttormb);
                            data.put("coinSymbol", coinSymbol);
                            data.put("isShoucang", true);
                            // 获取该交易对最新5分钟数据
                            String Code = coin.getCoinCode() + "_" + coin.getFixPriceCoinCode();
                            String points = getLineData(jedis, Code);
                            data.put("points", points);
                            ExCointoCoin exCoinToCoin = JSONObject.parseObject(jedis.hget("ex:cointoCoin", coin.getCoinCode() + ":" + coin.getFixPriceCoinCode()), ExCointoCoin.class);
                            if (exCoinToCoin != null) {
                                data.put("keepDecimalCoinCode", exCoinToCoin.getKeepDecimalFixPrice());
                            }
                            list.add(data);
                        }
                    }
                }
            }

            obj.put("data", list);
            //加入map中
            jsonArray.add(obj);
        }
        //增加交易区
        /*for (Coin coin : productList) {
            coinarea.add(coin.getFixPriceCoinCode());
        }*/
        RemoteManageService remoteManageService = SpringUtil.getBean("remoteManageService");
        List<String> tradeArea = remoteManageService.getTredeArea();
        for (String coinname : tradeArea) {
            coinarea.add(coinname);
        }

        if (coinarea.size() > 0) {
            Iterator<String> it = coinarea.iterator();
            while (it.hasNext()) {
                String areaname = it.next();

                JSONObject obj = new JSONObject();
                obj.put("areaname", areaname);
                obj.put("areanameview", areaname);

                //创建list
                ArrayList<JSONObject> list = new ArrayList<JSONObject>();
                //遍历
                for (Coin coin : productList) {
                    if (areaname.equals(coin.getFixPriceCoinCode())) {
                        JSONObject data = createData(jedis, coin);
                        data.put("usdttormb", usdttormb);
                        data.put("coinSymbol", coinSymbol);
                        if (mycoins != null && mycoins.size() > 0) {
                            for (String coinCode : mycoins) {
                                if (coinCode.equals(coin.getCoinCode() + "_" + coin.getFixPriceCoinCode())) {
                                    data.put("isShoucang", true);
                                }
                            }
                        }
                        // 获取该交易对最新5分钟数据
                        String coinCode = coin.getCoinCode() + "_" + coin.getFixPriceCoinCode();
                        String points = getLineData(jedis, coinCode);
                        data.put("points", points);
                        //每个交易对自己的位数
                        ExCointoCoin exCoinToCoin = JSONObject.parseObject(jedis.hget("ex:cointoCoin", coin.getCoinCode() + ":" + coin.getFixPriceCoinCode()), ExCointoCoin.class);
                        if (exCoinToCoin != null) {
                            data.put("keepDecimalCoinCode", exCoinToCoin.getKeepDecimalFixPrice());
                        }

                        list.add(data);
                    }
                }
                obj.put("data", list);
                //加入map中
                jsonArray.add(obj);
            }
        }
        return jsonArray;
    }


    /**
     * 获取折线数据
     *
     * @param coinCode 交易对名称
     * @return
     */
    private String getLineData(Jedis jedis, String coinCode) {
        String returnStr = "";
        if (!StringUtils.isEmpty(coinCode)) {
            String key = coinCode + ":klinedata:TransactionOrder_" + coinCode + "_5";
            String lineData = jedis.get(key);
            if (!StringUtils.isEmpty(lineData)) {
                List<Map> linkConf = JSONArray.parseArray(lineData, Map.class);
                if (linkConf != null && linkConf.size() > 0) {
                    List<Map> newList = new ArrayList<>();
                    if (linkConf.size() > 30) {
                        newList = linkConf.subList(0, 30);
                    } else {
                        newList.addAll(linkConf);
                    }
                    if (newList != null) {
                        Collections.reverse(newList);
                        for (Map map : newList) {
                            returnStr += "," + (map.get("endPrice") == null ? "0" : map.get("endPrice").toString());
                        }
                    }
                    return returnStr.substring(1);
                }
            }
        }
        return "";
    }


    /**
     * 每秒推最新成交的数据, 推出所有币的成交记录
     *
     * @return
     */
    private List<MarketDetail> marketDetail(String coinCode) {
        if (StringUtils.isEmpty(coinCode) || !coinCode.contains("_")) {
            return Collections.emptyList();
        }
        Locale locale = LocaleContextHolder.getLocale();

        /*获得usdt对人民币价格*/
        BigDecimal usdttormb = new BigDecimal(6.6);
        String financeConfig = redisService.get("CointoMoney:" + locale.toString());
        String coinSymbol = "";
        if (!StringUtils.isEmpty(financeConfig)) {
            ExCointoMoney exCointoMoney = JSONObject.parseObject(financeConfig, ExCointoMoney.class);
            usdttormb = new BigDecimal(exCointoMoney.getRate());
            coinSymbol = exCointoMoney.getCoinSymbol();

        }

        // 做市字段
        String key = "pushEntrusMarket";

        Map<String, List<MarketDetail>> map = new HashMap<String, List<MarketDetail>>();
        ArrayList<MarketDetail> list = new ArrayList<MarketDetail>();
        for (int dep = 0; dep < 1; dep++) {
            MarketDetail marketDetail = new MarketDetail();
            marketDetail.setMsgType("marketDetail" + dep);
            marketDetail.setSymbolId(coinCode);
            Random random = new Random();
            Date nowDate = new Date();
            String nowDateStr = DateUtil.dateToString(nowDate, "yyyy-MM-dd HH:mm:ss");
            int intValue = Long.valueOf(DateUtil.stringToDate(nowDateStr).getTime() / 1000).intValue();
            marketDetail.set_id(intValue);
            marketDetail.setIdCur(intValue);

            MarketPayload payload = new MarketPayload();


            // payload.setYestdayPriceLast(yestdayPriceLast);

            // -----------------------------委托数据start------------------------------------
            // 委托卖
            MarketPayloadAsks marketPayloadAsks = new MarketPayloadAsks();
            BigDecimal[] aprices = null;
            BigDecimal[] aamounts = null;
            BigDecimal[] alevels = null;

            // 委托买
            MarketPayloadBids marketPayloadBids = new MarketPayloadBids();
            BigDecimal[] bprices = null;
            BigDecimal[] bamounts = null;
            BigDecimal[] blevels = null;

            String pushEntrusMarket;
            if (dep == 0) {
                pushEntrusMarket = redisService.get(coinCode + ":" + key);
            } else {
                pushEntrusMarket = redisService.get(coinCode + ":" + key + dep);
            }

            if (!StringUtils.isEmpty(pushEntrusMarket)) {
                // 获得委托数据
                MarketDepths marketDepths = JSON.parseObject(pushEntrusMarket, MarketDepths.class);

                Map<String, List<BigDecimal[]>> depths = marketDepths.getDepths();
                if (depths != null && !depths.isEmpty()) {
                    List<BigDecimal[]> askslist = depths.get("asks");
                    SortListUtil<BigDecimal[]> sortList = new SortListUtil<BigDecimal[]>();
                    sortList.SortBigDecimalArray(askslist, 0, null);

                    if (askslist != null && !askslist.isEmpty()) {

                        aprices = new BigDecimal[askslist.size()];
                        aamounts = new BigDecimal[askslist.size()];
                        alevels = new BigDecimal[askslist.size()];

                        for (int i = 0; i < askslist.size(); i++) {
                            BigDecimal[] b = askslist.get(i);
                            aprices[i] = b[0];
                            aamounts[i] = b[1];
                            alevels[i] = BigDecimal.ONE;
                        }
                    }

                    List<BigDecimal[]> bidslist = depths.get("bids");
                    if (bidslist != null && !bidslist.isEmpty()) {
                        bprices = new BigDecimal[bidslist.size()];
                        bamounts = new BigDecimal[bidslist.size()];
                        blevels = new BigDecimal[bidslist.size()];

                        for (int i = 0; i < bidslist.size(); i++) {
                            BigDecimal[] b = bidslist.get(i);
                            bprices[i] = b[0];
                            bamounts[i] = b[1];
                            blevels[i] = new BigDecimal(1);
                        }

                    }

                }
            }
            marketPayloadAsks.setPrice(aprices);
            marketPayloadAsks.setLevel(alevels);
            marketPayloadAsks.setAmount(aamounts);

            marketPayloadBids.setPrice(bprices);
            marketPayloadBids.setLevel(blevels);
            marketPayloadBids.setAmount(bamounts);


            // -----------------------------委托数据end------------------------------------

            MarketPayloadTrades marketPayloadTrades = new MarketPayloadTrades();
            BigDecimal[] prices = null;
            BigDecimal[] amounts = null;
            // 交易类型
            BigDecimal[] directions = null;
            Long[] times = null;

            // 获得交易数据
            String pushNewListRecordMarket = redisService.get(coinCode + ":pushNewListRecordMarketDesc");
            BigDecimal priceNew = BigDecimal.ZERO;
            if (!StringUtils.isEmpty(pushNewListRecordMarket)) {
                MarketTrades marketTrades = JSON.parseObject(pushNewListRecordMarket, MarketTrades.class);
                // 最新价格
                if (marketTrades != null) {
                    List<MarketTradesSub> trades = marketTrades.getTrades();
                    if (trades != null && trades.size() > 0) {
                        // 第一条为最新价格
                        priceNew = trades.get(0).getPrice();

                        prices = new BigDecimal[trades.size()];
                        amounts = new BigDecimal[trades.size()];
                        directions = new BigDecimal[trades.size()];
                        times = new Long[trades.size()];

                        for (int i = 0; i < trades.size(); i++) {
                            MarketTradesSub marketTradesSub = trades.get(i);
                            prices[i] = marketTradesSub.getPrice();
                            amounts[i] = marketTradesSub.getAmount();
                            times[i] = marketTradesSub.getTime();
                            if ("buy".equals(marketTradesSub.getType())) {
                                directions[i] = new BigDecimal(2);// 绿色
                            } else {
                                directions[i] = new BigDecimal(1);// 红色
                            }
                        }

                    }
                }
            }

            marketPayloadTrades.setPrice(prices);
            marketPayloadTrades.setAmount(amounts);
            marketPayloadTrades.setDirection(directions);
            marketPayloadTrades.setTime(times);

            // 获得每个时间段的最新价 最高价，开盘价，收盘价
            String periodLastKLineListStr = redisService.get(coinCode + ":PeriodLastKLineList");
            // 获得最新价
            String index = redisService.get(ContextUtil.getWebsite() + ":" + ContextUtil.getCurrencyType() + ":" + coinCode + ":pushIndex");
            BigDecimal ts = null;
            if (!StringUtils.isEmpty(index)) {
                try {
                    JSONObject jb = JSON.parseObject(index);
                    JSONObject data = (JSONObject) jb.get("data");
                    String transactionSum = (String) data.get("transactionCount");
                    ts = new BigDecimal(transactionSum);
                } catch (Exception e) {
                }
            }
            if (!StringUtils.isEmpty(periodLastKLineListStr)) {
                List<LastKLinePayload> periodList = JSON.parseArray(periodLastKLineListStr, LastKLinePayload.class);
                if (periodList != null) {
                    for (LastKLinePayload l : periodList) {
                        if ("1day".equals(l.getPeriod())) {
                            payload.setPriceOpen(l.getPriceOpen());
                            payload.setPriceHigh(l.getPriceHigh());
                            payload.setPriceLow(l.getPriceLow());
                            payload.setPriceLast(l.getPriceLast());
                            payload.setLevel(l.getPriceLast());
                            payload.setAmount(ts);
                            payload.setTotalAmount(l.getDayTotalDealAmount());
                            break;
                        }
                    }
                }
            }
            payload.setPriceNew(priceNew);

            payload.setTotalVolume(new BigDecimal(random.nextInt(5000000)));
            payload.setAmp(null);

            payload.setAsks(marketPayloadAsks);
            payload.setBids(marketPayloadBids);
            payload.setTrades(marketPayloadTrades);

            /*转成人民币价格*/
            payload.setUsdtToRmb(usdttormb);
            String fixPriceCoinCode = coinCode.split("_")[1];
            if ("USDT".equals(fixPriceCoinCode)) {
                payload.setUsdtCount(payload.getPriceNew());
            } else {
                //如果当前币对usdt有价格
                String usdtprice = redisService.get(fixPriceCoinCode + "_USDT" + ":currentExchangPrice");
                if (!StringUtils.isEmpty(usdtprice)) {
                    payload.setUsdtCount(payload.getPriceNew().multiply(new BigDecimal(usdtprice)));
                } else {
                    payload.setUsdtCount(new BigDecimal(0));
                }
            }

            // 昨日收盘价
            String table = coinCode + ":klinedata:TransactionOrder_" + coinCode + "_1440";
            List<TransactionOrder> orders = JSON.parseArray(redisService.get(table), TransactionOrder.class);


            String coinStr = redisService.get("cn:coinInfoList2");
            if (!StringUtils.isEmpty(coinStr)) {
                List<Coin> coins = JSON.parseArray(coinStr, Coin.class);
                for (Coin c : coins) {
                    if (coinCode.equals(c.getCoinCode() + "_" + c.getFixPriceCoinCode())) {
                        if (!StringUtils.isEmpty(c.getYesterdayPrice())) {
                            payload.setYestdayPriceLast(new BigDecimal(c.getYesterdayPrice()));
                        } else {
                            payload.setYestdayPriceLast(new BigDecimal(1));
                        }
                    }
                }
            }

//				}
            marketDetail.setPayload(payload);
            list.add(marketDetail);

        }
        map.put(coinCode, list);
        return list;

    }


    /**
     * 推送深度数据
     *
     * @return
     */
    public Map<String, List<BigDecimal[]>> moreMarketDetail(String coinCode) {
        String key = "pushEntrusMarket100";
        String productListStr = redisService.get(ContextUtil.getWebsite() + ":productFixList");
        Map<String, Map<String, List<BigDecimal[]>>> finalmap = new HashMap<>();
        if (!StringUtils.isEmpty(productListStr)) {
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
            }
            return depth;
        }
        return null;
    }


    /**
     * 创建交易区的一个交易对的data
     *
     * @return
     */
    private JSONObject createData(Jedis jedis, Coin coin) {
        JSONObject data = new JSONObject();
        try {
            //交易币的位数
            Integer keepCoin = coin.getKeepDecimalForCoin() == null ? 2 : coin.getKeepDecimalForCoin();
            //定价币的位数
            Integer keepCurrency = coin.getKeepDecimalForCurrency() == null ? 2 : coin.getKeepDecimalForCurrency();
            int zeroLength = 2;
            //交易币的格式化
            String keepCoinFormat = "0.00";
            //定价币的格式化
            String keepCurrencyFormat = "0.00";
            if (keepCoin > zeroLength) {
                keepCoinFormat = "0.";
                for (int i = 1; i <= keepCoin; i++) {
                    keepCoinFormat = keepCoinFormat += "0";
                }
            }
            if (keepCurrency > zeroLength) {
                keepCurrencyFormat = "0.";
                for (int i = 1; i <= keepCurrency; i++) {
                    keepCurrencyFormat = keepCurrencyFormat += "0";
                }
            }
            DecimalFormat decimalFormatCoin = new DecimalFormat(keepCoinFormat);
            DecimalFormat decimalFormatCurrency = new DecimalFormat(keepCurrencyFormat);


            data.put("coinCode", coin.getCoinCode() + "_" + coin.getFixPriceCoinCode());
            data.put("name", coin.getCoinCode() + "_" + coin.getFixPriceCoinCode());
            data.put("picturePath", coin.getPicturePath());

		/*//交易对位数
		ExCointoCoin exCointoCoin = JSONObject.parseObject(redisService.hget("ex:cointoCoin",coin.getCoinCode()+":"+coin.getFixPriceCoinCode()),ExCointoCoin.class);
		data.put("keepDecimalFixPrice", exCointoCoin.getKeepDecimalFixPrice());*/

            //定价币对USDT价格
            String usdtprice = jedis.get(coin.getFixPriceCoinCode() + "_USDT" + ":currentExchangPrice");

            String currentExchangPrice_str = jedis.get(coin.getCoinCode() + "_" + coin.getFixPriceCoinCode() + ":currentExchangPrice");
            if (!StringUtils.isEmpty(currentExchangPrice_str)) {
                data.put("currentExchangPrice", decimalFormatCurrency.format(new BigDecimal(currentExchangPrice_str)));

                //最新成交价转usdt
                if ("USDT".equals(coin.getFixPriceCoinCode())) {
                    data.put("usdtcount", new BigDecimal(currentExchangPrice_str));
                } else {
                    if (!StringUtils.isEmpty(usdtprice)) {
                        data.put("usdtcount", new BigDecimal(currentExchangPrice_str).multiply(new BigDecimal(usdtprice)));
                    } else {
                        data.put("usdtcount", 0);
                    }
                }
            } else {
                data.put("usdtcount", 0);
                data.put("currentExchangPrice", 0);
            }

            // 昨日收盘价
            String coinStr = jedis.get("cn:coinInfoList2");
            String coinCode = coin.getCoinCode() + "_" + coin.getFixPriceCoinCode();
            BigDecimal yesterdayPrice = new BigDecimal(0);
            if (!StringUtils.isEmpty(coinStr)) {
                List<Coin2> coins = JSON.parseArray(coinStr, Coin2.class);
                for (Coin2 c : coins) {
                    if (coinCode.equals(c.getCoinCode() + "_" + c.getFixPriceCoinCode())) {
                        if (!StringUtils.isEmpty(c.getYesterdayPrice())) {
                            yesterdayPrice = new BigDecimal(c.getYesterdayPrice());
                        }
                    }
                }
            }
            data.put("yesterdayPrice", decimalFormatCurrency.format(yesterdayPrice));

            String str = jedis.get(coin.getCoinCode() + "_" + coin.getFixPriceCoinCode() + ":PeriodLastKLineList");
            if (str != null) {
                JSONArray jsonv = JSON.parseArray(str);
                //System.out.println(jsonv.getString(5));
                if (jsonv.getString(5) != null) {
                    JSONObject jsonv_ = JSON.parseObject(jsonv.getString(5));
                    if ("1day".equals(jsonv_.getString("period"))) {

                        BigDecimal currentExchangPrice = new BigDecimal(0);
                        //上一笔交易价格
                        String orders = jedis.get(coin.getCoinCode() + "_" + coin.getFixPriceCoinCode() + ":pushNewListRecordMarketDesc");
                        if (!StringUtils.isEmpty(orders)) {
                            MarketTrades marketTrades = JSON.parseObject(orders, MarketTrades.class);
                            // 最新价格
                            if (marketTrades != null) {
                                List<MarketTradesSub> trades = marketTrades.getTrades();
                                if (trades != null && trades.size() > 0) {
                                    if (trades.size() > 1) {
                                        MarketTradesSub marketTradesSub0 = trades.get(0);
                                        data.put("currentExchangPrice", decimalFormatCurrency.format(marketTradesSub0.getPrice()));
                                        currentExchangPrice = marketTradesSub0.getPrice();

                                        MarketTradesSub marketTradesSub1 = trades.get(1);
                                        data.put("lastExchangPrice", decimalFormatCurrency.format(marketTradesSub1.getPrice()));
                                    } else {

                                        MarketTradesSub marketTradesSub0 = trades.get(0);
                                        data.put("currentExchangPrice", decimalFormatCurrency.format(marketTradesSub0.getPrice()));
                                        currentExchangPrice = marketTradesSub0.getPrice();

                                        data.put("lastExchangPrice", decimalFormatCurrency.format(marketTradesSub0.getPrice()));
                                    }
                                } else {
                                    data.put("lastExchangPrice", "1");
                                }
                            } else {
                                data.put("lastExchangPrice", "1");
                            }
                        } else {
                            data.put("lastExchangPrice", "1");
                        }
                        //24小时成交总量
                        String new24hoursvol_str = jedis.get(coin.getCoinCode() + "_" + coin.getFixPriceCoinCode() + ":new24hoursamout");
                        if (!StringUtils.isEmpty(new24hoursvol_str)) {
                            data.put("transactionSum", decimalFormatCoin.format(new BigDecimal(new24hoursvol_str)));
                        } else {

                            data.put("transactionSum", decimalFormatCoin.format(new BigDecimal(0)));

                        }

                        data.put("maxPrice", jsonv_.getString("priceHigh"));
                        data.put("minPrice", jsonv_.getString("priceLow"));


                        //最高价成交价转usdt
                        if ("USDT".equals(coin.getFixPriceCoinCode())) {
                            //System.out.println(jsonv_.toJSONString());
                            data.put("usdt_maxPrice", new BigDecimal(jsonv_.getString("priceHigh") == null ? "0" : jsonv_.getString("priceHigh")));
                            data.put("usdt_minPrice", new BigDecimal(jsonv_.getString("priceLow") == null ? "0" : jsonv_.getString("priceLow")));
                            data.put("usdt_yesterdayPrice", decimalFormatCurrency.format(yesterdayPrice));
                        } else {
                            if (!StringUtils.isEmpty(usdtprice)) {
                                data.put("usdt_maxPrice", new BigDecimal(jsonv_.getString("priceHigh")).multiply(new BigDecimal(usdtprice)));
                                data.put("usdt_minPrice", new BigDecimal(jsonv_.getString("priceLow")).multiply(new BigDecimal(usdtprice)));
                                data.put("usdt_yesterdayPrice", yesterdayPrice.multiply(new BigDecimal(usdtprice)));
                            } else {
                                data.put("usdt_maxPrice", 0);
                                data.put("usdt_minPrice", 0);
                                data.put("usdt_yesterdayPrice", 0);
                            }
                        }

                        // 开盘价
                        data.put("openPrice", decimalFormatCurrency.format(new BigDecimal(jsonv_.getString("priceOpen") == null ? "0" : jsonv_.getString("priceOpen"))));

                        if (BigDecimal.ZERO.compareTo(yesterdayPrice) != 0) {
                            if (BigDecimal.ZERO.compareTo(currentExchangPrice) != 0) {
                                BigDecimal divide = (currentExchangPrice.subtract(yesterdayPrice)).divide(yesterdayPrice, 5, BigDecimal.ROUND_HALF_DOWN).multiply(new BigDecimal(100));
                                data.put("RiseAndFall", divide);
                            } else {
                                data.put("RiseAndFall", 0);
                            }
                        } else {
                            data.put("RiseAndFall", 0);
                        }


                    } else {
                        data.put("lastExchangPrice", 0);
                        data.put("transactionSum", 0);
                        data.put("maxPrice", 0);
                        data.put("minPrice", 0);
                        // 开盘价
                        data.put("openPrice", new BigDecimal(0));
                        data.put("lastEndPrice", 0);
                        data.put("RiseAndFall", 0);
                    }
                } else {
                    data.put("lastExchangPrice", 0);
                    data.put("transactionSum", 0);
                    data.put("maxPrice", 0);
                    data.put("minPrice", 0);
                    // 开盘价
                    data.put("openPrice", new BigDecimal(0));

                    data.put("lastEndPrice", 0);
                    data.put("RiseAndFall", 0);
                }
            } else {
                data.put("lastExchangPrice", 0);
                data.put("transactionSum", 0);
                data.put("maxPrice", 0);
                data.put("minPrice", 0);
                // 开盘价
                data.put("openPrice", new BigDecimal(0));

                data.put("lastEndPrice", 0);
                data.put("RiseAndFall", 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
            //log.error(e);
        }


        return data;

    }


}
