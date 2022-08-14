package hry.app.trade;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import hry.app.kline.model.ExCointoCoin;
import hry.app.kline.model.LastKLine;
import hry.app.kline.model.LastKLinePayload;
import hry.app.kline.netty.controller.WebSocketClient;
import hry.app.kline.netty.model.ResponseData;
import hry.bean.ApiJsonResult;
import hry.bean.JsonResult;
import hry.lend.constant.LendConfig;
import hry.manage.remote.RemoteManageService;
import hry.manage.remote.model.CoinAccount;
import hry.manage.remote.model.User;
import hry.redis.common.utils.RedisService;
import hry.util.ObjectUtil;
import hry.util.PropertiesUtils;
import hry.util.SessionUtils;
import hry.util.common.SpringUtil;
import hry.util.date.DateUtil;
import hry.util.klinedata.KlineDataUtil;
import hry.util.klinedata.TransactionOrder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@Api(value= "交易大厅-node调用接口", description ="交易大厅-node调用接口", tags = "交易大厅-node调用接口")
public class TradingViewController {

    private static Logger log = LoggerFactory.getLogger(TradingViewController.class);

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    @Resource
    private RedisService redisService;

    @RequestMapping("/config")
    @ApiOperation(value = "", httpMethod = "GET", notes = "")
    public Map<String, Object> config() {
        return LendConfig.getKlineConfig();
    }

    @ApiOperation(value = "", httpMethod = "GET", notes = "")
    @RequestMapping("/symbols")
    public Map<String, Object> symbols(HttpServletRequest request) {
        String resolution = request.getParameter("resolution");
        String symbol = request.getParameter("symbol");
        RedisService redisService = SpringUtil.getBean("redisService");
        //交易币保留位数
        Integer keepDecimalForCoin = 2;
        String coinCode = symbol.split("_")[1];
        String value = redisService.get("cn:productinfoListall");
        if (!StringUtils.isEmpty(value)) {
            JSONArray array = JSON.parseArray(value);
            if (array != null) {
                for (int i = 0; i < array.size(); i++) {
                    JSONObject jsonObject = array.getJSONObject(i);
                    if (coinCode.equals(jsonObject.getString("coinCode"))) {
                        keepDecimalForCoin = jsonObject.getInteger("keepDecimalForCoin");
                    }
                }
            }
        }

        String coinToCoin = redisService.hget("ex:cointoCoin", symbol.replace("_", ":"));
        ExCointoCoin exCointoCoin = null;
        if (!StringUtils.isEmpty(coinToCoin)) {
            exCointoCoin = JSONObject.parseObject(coinToCoin, ExCointoCoin.class);
            keepDecimalForCoin = exCointoCoin.getKeepDecimalFixPrice();
        }
        Map<String, Object> map = new HashMap<String, Object>(16);
        //map.put("description", "Apple Inc.");
        map.put("symbol", symbol);
        map.put("exchange-listed", "");
        map.put("exchange-traded", "");
        map.put("has_intraday", true);
        map.put("has_no_volume", false);
        map.put("minmov", 1);
        map.put("minmov2", 2);
        map.put("name", "");
        map.put("pointvalue", resolution);
        map.put("pricescale", Math.pow(10, keepDecimalForCoin));
        map.put("session", "24x7");
        map.put("supported_resolutions", new String[]{"1", "5", "15", "30", "60", "1D", "1W"});//,"1M"
        map.put("ticker", symbol);
        map.put("timezone", "Asia/Shanghai");//America/New_York
        map.put("type", "stock");
        map.put("has_weekly_and_monthly", true);
        return map;
    }

    @ApiOperation(value = "", httpMethod = "GET", notes = "")
    @RequestMapping("/symbol_info")
    public Map<String, Object> symbol_info(HttpServletRequest request) {
        String symbol = request.getParameter("symbol");
        RedisService redisService = SpringUtil.getBean("redisService");
        Integer keepDecimalForCoin = 2;
        String coinCode = symbol.split("_")[1];
        String value = redisService.get("cn:productinfoListall");
        if (!StringUtils.isEmpty(value)) {
            JSONArray array = JSON.parseArray(value);
            if (array != null) {
                for (int i = 0; i < array.size(); i++) {
                    JSONObject jsonObject = array.getJSONObject(i);
                    if (coinCode.equals(jsonObject.getString("coinCode"))) {
                        keepDecimalForCoin = jsonObject.getInteger("keepDecimalForCoin");
                    }
                }
            }
        }
        Map<String, Object> map = new HashMap<String, Object>(17);
        map.put("symbol", symbol);// new String[]{"AAPL", "MSFT", "SPX"}
        map.put("description", new String[]{"Apple Inc", "Microsoft corp", "S&P 500 index"});
        map.put("exchange-listed", "");
        map.put("exchange-traded", "");
        map.put("has_intraday", true);
        map.put("has_no_volume", false);
        map.put("minmov", 1);
        map.put("minmov2", 0);
        map.put("name", "");
        map.put("pointvalue", 1);
        map.put("pricescale", Math.pow(10, keepDecimalForCoin));
        map.put("session", "24x7");
        map.put("supported-resolutions", new String[]{"1", "5", "15", "30", "60", "1D", "1W"});//,"1M"
        map.put("ticker", symbol);
        map.put("timezone", "Asia/Shanghai");
        map.put("type", "stock");
        return map;
    }

    @ApiOperation(value = "", httpMethod = "GET", notes = "")
    @RequestMapping("/time")
    public Long time() {
        return System.currentTimeMillis() / 1000;
    }

    @ApiOperation(value = "", httpMethod = "GET", notes = "")
    @RequestMapping("/marks")
    public Map<String, Object> marks() {
        Map<String, Object> map = new HashMap<String, Object>(7);
        map.put("color", new String[]{"red", "blue", "green", "red", "blue", "green"});
        map.put("id", new int[]{0, 1, 2, 3, 4, 5});
        map.put("label", "");
        map.put("labelFontColor", new String[]{"white", "white", "red", "#FFFFFF", "white", "#000"});
        map.put("minSize", new int[]{14, 28, 7, 40, 7, 14});
        map.put("time", new long[]{1523577600, 1523232000, 1522972800, 1522972800, 1522281600, 1520985600});
        return map;
    }

    @ApiOperation(value = "", httpMethod = "GET", notes = "")
    @RequestMapping("/getHotMoney")
    public JsonResult getHotMoney(HttpServletRequest request, HttpServletResponse response) {
        JsonResult jsonResult = new JsonResult();
        User user = SessionUtils.getUser(request);
        if (user != null) {
            RemoteManageService remoteManageService = SpringUtil.getBean("remoteManageService");
            BigDecimal[] sum = remoteManageService.getSum(user.getCustomerId());
            // 查询币账户
            List<CoinAccount> findCoinAccount = remoteManageService.findCoinAccount(user.getCustomerId());
            jsonResult.setSuccess(true);
            jsonResult.setObj(findCoinAccount);
        }


        return jsonResult;
    }

    @ApiOperation(value = "", httpMethod = "GET", notes = "")
    @RequestMapping("/timescale_marks")
    public Map<String, Object> timescale_marks() {
        Map<String, Object> map = new HashMap<String, Object>(7);
        map.put("0", JSON.toJSONString("{id:\"tsm1\",color:\"red\",label:\"A\",time:1523577600,tooltip:\"\"}"));
        map.put("1", JSON.toJSONString("{id:\"tsm2\",color:\"blue\",label:\"D\",time:1523232000,tooltip:\"\"}"));
        map.put("2", JSON.toJSONString("{id:\"tsm3\",color:\"green\",label:\"D\",time:1522972800,tooltip:\"\"}"));
        map.put("3", JSON.toJSONString("{id:\"tsm4\",color:\"#999999\",label:\"E\",time:1522281600,tooltip:\"\"}"));
        map.put("4", JSON.toJSONString("{id:\"tsm7\",color:\"red\",label:\"E\",time:1520985600,tooltip:\"\"}"));
        return map;
    }

    @ApiOperation(value = "", httpMethod = "GET", notes = "")
    @RequestMapping("/historyold")
    public Map<String, Object> historyold(HttpServletRequest request) {
        Map<String, Object> map = null;
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String symbol = request.getParameter("symbol");
        String resolution = request.getParameter("resolution");
        try {
            if ("1".equals(PropertiesUtils.APP.getProperty("kkcoin"))) {
                String key = "BTC_bitCNY";

                if ("1".equals(resolution)) {
                    key += ":1m";
                } else if ("5".equals(resolution)) {
                    key += ":5m";
                } else if ("15".equals(resolution)) {
                    key += ":15m";
                } else if ("30".equals(resolution)) {
                    key += ":30m";
                } else if ("60".equals(resolution)) {
                    key += ":1h";
                } else if ("1D".equals(resolution)) {
                    key += ":1d";
                } else if ("D".equals(resolution)) {
                    key += ":1w";
                }

                RedisService redisService = SpringUtil.getBean("redisService");
                String val = redisService.get(key);
                if (!StringUtils.isEmpty(val)) {
                    JSONArray json = JSON.parseArray(val);

                    //到什么时间
                    String to = request.getParameter("to");

                    List<Object> list = (List<Object>) json.get(0);
                    Long oneTime = (Long) list.get(0);

                    if (Long.valueOf(to) * 1000 < oneTime) {
                        map = new HashMap<String, Object>(7);

                        map.put("c", new BigDecimal[]{});//闭
                        map.put("h", new BigDecimal[]{});//最高
                        map.put("l", new BigDecimal[]{});//最低
                        map.put("o", new BigDecimal[]{});//开
                        map.put("t", new BigDecimal[]{});
                        map.put("v", new BigDecimal[]{});
                        map.put("s", "no_data");
                        return map;
                    }

                    map = new HashMap<String, Object>(json.size());

                    //闭
                    BigDecimal[] c = new BigDecimal[json.size()];
                    //最高
                    BigDecimal[] h = new BigDecimal[json.size()];
                    //最低
                    BigDecimal[] l = new BigDecimal[json.size()];
                    //开
                    BigDecimal[] o = new BigDecimal[json.size()];
                    //时间
                    Long[] t = new Long[json.size()];
                    //数量
                    BigDecimal[] v = new BigDecimal[json.size()];

                    int count = 0;
                    for (int i = 0; i < json.size(); i++) {

                        List<Object> listn = (List<Object>) json.get(i);

                        c[count] = new BigDecimal(listn.get(2).toString());
                        h[count] = new BigDecimal(listn.get(3).toString());
                        l[count] = new BigDecimal(listn.get(4).toString());
                        o[count] = new BigDecimal(listn.get(1).toString());

                        t[count] = ((Long) listn.get(0)) / 1000;
                        v[count] = new BigDecimal(listn.get(5).toString());

                        count++;
                    }

                    map.put("c", c);//闭
                    map.put("h", h);//最高
                    map.put("l", l);//最低
                    map.put("o", o);//开
                    map.put("s", "ok");// ok正常 error、no_data休市
                    map.put("t", t);
                    map.put("v", v);

                    return map;
                }

            } else {
                String key = symbol + ":klinedata:TransactionOrder_" + symbol + "_";
                if ("1".equals(resolution)) {
                    key += "1";
                } else if ("5".equals(resolution)) {
                    key += "5";
                } else if ("15".equals(resolution)) {
                    key += "15";
                } else if ("30".equals(resolution)) {
                    key += "30";
                } else if ("60".equals(resolution)) {
                    key += "60";
                } else if ("1D".equals(resolution)) {
                    key += "1440";
                } else if ("1W".equals(resolution)) {
                    key += "10080";
                }
                RedisService redisService = SpringUtil.getBean("redisService");

                String val = redisService.get(key);
                if (!StringUtils.isEmpty(val)) {
                    JSONArray json = (JSONArray) JSONArray.parse(val);

                    //到什么时间
                    String to = request.getParameter("to");

                    TransactionOrder one = ObjectUtil.bean2bean(json.get(json.size() - 1), TransactionOrder.class);
                    Date done = s.parse(one.getTransactionTime());
                    Long oneTime = done.getTime();

                    if (Long.valueOf(to) * 1000 < oneTime) {
                        map = new HashMap<String, Object>(7);

                        map.put("c", new BigDecimal[]{});//闭
                        map.put("h", new BigDecimal[]{});//最高
                        map.put("l", new BigDecimal[]{});//最低
                        map.put("o", new BigDecimal[]{});//开
                        map.put("t", new BigDecimal[]{});
                        map.put("v", new BigDecimal[]{});
                        map.put("s", "no_data");
                        return map;
                    }

                    map = new HashMap<String, Object>(json.size());

                    //闭
                    BigDecimal[] c = new BigDecimal[json.size()];
                    //最高
                    BigDecimal[] h = new BigDecimal[json.size()];
                    //最低
                    BigDecimal[] l = new BigDecimal[json.size()];
                    //开
                    BigDecimal[] o = new BigDecimal[json.size()];
                    //时间
                    Long[] t = new Long[json.size()];
                    //数量
                    BigDecimal[] v = new BigDecimal[json.size()];

                    int count = 0;
                    for (int i = json.size() - 1; i >= 0; i--) {

                        TransactionOrder tod = ObjectUtil.bean2bean(json.get(i), TransactionOrder.class);

                        c[count] = tod.getEndPrice();
                        h[count] = tod.getMaxPrice();
                        l[count] = tod.getMinPrice();
                        o[count] = tod.getStartPrice();

                        Date d = s.parse(tod.getTransactionTime());

                        t[count] = d.getTime() / 1000;
                        v[count] = tod.getTransactionCount();

                        count++;
                    }

                    map.put("c", c);//闭
                    map.put("h", h);//最高
                    map.put("l", l);//最低
                    map.put("o", o);//开
                    map.put("s", "ok");// ok正常 error、no_data休市
                    map.put("t", t);
                    map.put("v", v);

                    return map;
                } else {
                    map = new HashMap<String, Object>(7);

                    map.put("c", new BigDecimal[]{});//闭
                    map.put("h", new BigDecimal[]{});//最高
                    map.put("l", new BigDecimal[]{});//最低
                    map.put("o", new BigDecimal[]{});//开
                    map.put("t", new BigDecimal[]{});
                    map.put("v", new BigDecimal[]{});
                    map.put("s", "no_data");
                    return map;
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 拖动k线数据获取
     *
     * @param symbol
     * @param resolution
     * @param to
     * @param from
     * @param timeDL
     * @return
     */
    @ApiOperation(value = "", httpMethod = "GET", notes = "")
    @RequestMapping("/newHistory")
    public Map<String, Object> new_history(String symbol, String resolution, String to, String from, String timeDL) {
        if(StringUtils.isEmpty(timeDL)){
           return null;
        }
        Map<String, Object> map = new HashMap<>();
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        System.out.println("币种：" + symbol);

        //图表是否拖动过
        boolean firstLoad = false;
        try {
            int period = 1;
            if ("5".equals(resolution)) {
                period = 5;
            } else if ("15".equals(resolution)) {
                period = 15;
            } else if ("30".equals(resolution)) {
                period = 30;
            } else if ("60".equals(resolution)) {
                period = 60;
            } else if ("1D".equals(resolution)) {
                period = 1440;
            } else if ("1W".equals(resolution)) {
                period = 1400;
            }
            List<ResponseData> list = new ArrayList();
            List<TransactionOrder> dataList = KlineDataUtil.findData(symbol, period, from, to, timeDL);
            if (dataList != null && dataList.size() > 0) {
                dataList.forEach(data -> {
                    ResponseData klineData = new ResponseData();
                    klineData.setClose(data.getEndPrice());
                    klineData.setCount(data.getTransactionCount());
                    klineData.setHigh(data.getMaxPrice());
                    klineData.setLow(data.getMinPrice());
                    klineData.setOpen(data.getStartPrice());
                    klineData.setQuote_vol(data.getTransactionCount());

                    klineData.setType("candle.M5.btcusdt");
                    try {
                        Date date = sdf.parse(data.getTransactionTime());
                        klineData.setSeq(new BigDecimal(date.getTime()));
                        klineData.setId(new BigDecimal(date.getTime() / 1000));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    list.add(klineData);
                });

            }
            //添加最后一个节点
           // ResponseData responseData = new PushKlineThread().lastKLine(symbol);
//            list.add(responseData);

            list.sort((a, b) -> {
                if (org.springframework.util.StringUtils.isEmpty(a.getId())) {
                    return 1;
                }
                return a.getId().subtract(b.getId()).compareTo(new BigDecimal(0));
            });
            map.put("data", list);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 新版K线加载数据方法
     */
    @ApiOperation(value = "", httpMethod = "GET", notes = "")
    @RequestMapping("/history")
    public Map<String, Object> history(HttpServletRequest request) {
        Map<String, Object> map;
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String symbol = request.getParameter("symbol");
        System.out.println("币种：" + symbol);
        String resolution = request.getParameter("resolution");
        //到什么时间
        String to = request.getParameter("to");
        String from = request.getParameter("from");
        //最后一节点的时间
        String timeDL = request.getParameter("timeDL");
        //图表是否拖动过
        boolean firstLoad = false;
        try {
            int period = 1;
            if ("5".equals(resolution)) {
                period = 5;
            } else if ("15".equals(resolution)) {
                period = 15;
            } else if ("30".equals(resolution)) {
                period = 30;
            } else if ("60".equals(resolution)) {
                period = 60;
            } else if ("1D".equals(resolution)) {
                period = 1440;
            } else if ("1W".equals(resolution)) {
                period = 1400;
            }

            List<TransactionOrder> dataList = KlineDataUtil.findData(symbol, period, from, to, timeDL);
            if (dataList != null && dataList.size() > 0) {
                map = new HashMap<>(dataList.size());
                //当前时间
                Date thisHourDate = DateUtil.stringToDate(DateUtil.dateToString(new Date(), "yyyy-MM-dd HH:00:00"));
                //from起始时间
                Date toHourDate = DateUtil.stringToDate(DateUtil.dateToString(new Date(Long.valueOf(to) * 1000), "yyyy-MM-dd HH:00:00"));
                //同一个小时说明图表还未拖动
                if (thisHourDate.getTime() == toHourDate.getTime()) {
                    firstLoad = true;
                }
                int countList;
                if (firstLoad) {
                    if ("1".equals(resolution)) {
                        countList = dataList.size() + 1;
                    } else {
                        countList = dataList.size();
                    }
                } else {
                    countList = dataList.size();
                }
                //闭
                BigDecimal[] c = new BigDecimal[countList];
                //最高
                BigDecimal[] h = new BigDecimal[countList];
                //最低
                BigDecimal[] l = new BigDecimal[countList];
                //开
                BigDecimal[] o = new BigDecimal[countList];
                //时间
                Long[] t = new Long[countList];
                //数量
                BigDecimal[] v = new BigDecimal[countList];
                int count = 0;
                for (int i = dataList.size() - 1; i >= 0; i--) {
                    TransactionOrder tod = dataList.get(i);
                    c[count] = tod.getEndPrice();
                    h[count] = tod.getMaxPrice();
                    l[count] = tod.getMinPrice();
                    o[count] = tod.getStartPrice();

                    Date d = s.parse(tod.getTransactionTime());
                    t[count] = d.getTime() / 1000;
                    v[count] = tod.getTransactionCount();
                    count++;
                }

                //补一个当前时间节点
                if (firstLoad) {
                    Map<String, List<LastKLine>> mapFirst = lastKLine(symbol);
                    List<LastKLine> lastKLines = mapFirst.get(symbol);
                    for (int m = 0; m < lastKLines.size(); m++) {
                        LastKLinePayload payload = lastKLines.get(m).getPayload();
                        if ("1min".equals(payload.getPeriod()) && "1".equals(resolution)) {
                            c[dataList.size()] = payload.getPriceLast();
                            h[dataList.size()] = payload.getPriceHigh();
                            l[dataList.size()] = payload.getPriceLow();
                            o[dataList.size()] = payload.getPriceOpen();
                            t[dataList.size()] = payload.getTime();
                            v[dataList.size()] = payload.getAmount();
                            break;
                        }
                    }
                }
                map.put("c", c);//闭
                map.put("h", h);//最高
                map.put("l", l);//最低
                map.put("o", o);//开
                map.put("s", "ok");// ok正常 error、no_data休市
                map.put("t", t);
                map.put("v", v);
                return map;
            } else {
                map = new HashMap<>(7);
                map.put("c", new BigDecimal[]{});//闭
                map.put("h", new BigDecimal[]{});//最高
                map.put("l", new BigDecimal[]{});//最低
                map.put("o", new BigDecimal[]{});//开
                map.put("t", new BigDecimal[]{});
                map.put("v", new BigDecimal[]{});
                map.put("s", "no_data");
                return map;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 补一个最新节点
     */
    private Map<String, List<LastKLine>> lastKLine(String coinCode) {
        // 获得当前时间所在的时间区间
        Map<String, Date> periodDate = hry.util.date.DateUtil.getPeriodDate2(new Date());

        Map<String, List<LastKLine>> map = new HashMap<String, List<LastKLine>>();
        ArrayList<LastKLine> list = new ArrayList<LastKLine>();
        String periodLastKLineListStr = redisService.get(coinCode + ":PeriodLastKLineList");
        if (!org.apache.commons.lang3.StringUtils.isEmpty(periodLastKLineListStr)) {
            List<LastKLinePayload> periodList = JSON.parseArray(periodLastKLineListStr, LastKLinePayload.class);
            if (periodList != null) {
                for (LastKLinePayload l : periodList) {
                    l.setSymbolId(coinCode);
                    LastKLine lastKLine = new LastKLine();
                    lastKLine.setSymbolId(coinCode);
                    // 以当前时间做最新时间区间
                    // 价格可以用历史价格
                    Long nowTime = periodDate.get(l.getPeriod()).getTime() / 1000;
                    if (nowTime.compareTo(l.getTime()) != 0) {// 如果redis中的最新成交时间和当前时间不相同，说明当前时间没有成交单，则当前成交数据用历史成交的收盘价
                        l.set_id(nowTime);
                        l.setTime(nowTime);
                        l.setPriceHigh(l.getPriceLast());
                        l.setPriceOpen(l.getPriceLast());
                        l.setPriceLow(l.getPriceLast());
                        l.setPriceLast(l.getPriceLast());
                        l.setAmount(new BigDecimal(0));
                    } else {
                        l.set_id(periodDate.get(l.getPeriod()).getTime() / 1000);
                        l.setTime(periodDate.get(l.getPeriod()).getTime() / 1000);
                        l.setPriceHigh(l.getPriceHigh());
                        l.setPriceOpen(l.getPriceOpen());
                        l.setPriceLow(l.getPriceLow());
                        l.setPriceLast(l.getPriceLast());
                        l.setAmount(l.getAmount());
                    }
                    list.add(lastKLine);
                    lastKLine.setPayload(l);
                }
            }
        }
        map.put(coinCode, list);
        return map;
    }
}
