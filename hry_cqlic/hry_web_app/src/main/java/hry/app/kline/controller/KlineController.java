package hry.app.kline.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import hry.app.jwt.TokenUtil;
import hry.app.kline.model.*;
import hry.bean.ApiJsonResult;
import hry.lend.constant.DealConstant;
import hry.lend.constant.ExchangeLendKey;
import hry.lend.model.trade.ExLendConfig;
import hry.manage.remote.RemoteBaseInfoService;
import hry.manage.remote.RemoteLendService;
import hry.manage.remote.RemoteManageService;
import hry.manage.remote.model.*;
import hry.redis.common.utils.RedisService;
import hry.util.SortListUtil;
import hry.util.common.BaseConfUtil;
import hry.util.common.Constant;
import hry.util.common.ContextUtil;
import hry.util.common.SpringUtil;
import hry.util.date.DateUtil;
import hry.util.http.HttpConnectionUtil;
import hry.util.klinedata.TransactionOrder;
import hry.util.properties.PropertiesUtils;
import hry.util.properties.StringConstant;
import hry.util.springmvcPropertyeditor.DateTimePropertyEditorSupport;
import hry.util.springmvcPropertyeditor.StringPropertyEditorSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;

/**
 * @author: Liu Shilei
 * @Date : 2018-09-26
 */
@Api(value = "K线数据类", description = "K线数据类", tags = "K线数据类")
@Controller
@RequestMapping("/klinevtwo")
public class KlineController {

    private final static Logger logger = Logger.getLogger(KlineController.class);

    // 数据库中保留k线的数据条数
    public static final int HOLD_COUNT = 800;
    // 默认查询1分钟K线周期
    private final int PERIOD = 1;

    @InitBinder
    public void initBinder(ServletRequestDataBinder binder) {
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
    private RemoteManageService remoteManageService;

    @Resource
    private RemoteBaseInfoService remoteBaseInfoService;

    @Resource
    private RemoteLendService remoteLendService;

    /**
     * 第一次加载KLINE
     *
     * @param request
     * @return
     */
    @ApiOperation(value = "node调用-第一次加载KLINE", httpMethod = "GET", response = HashMap.class, notes = "node调用-第一次加载KLINE")
    @GetMapping("/con")
    @ResponseBody
    public HashMap<String, Object> con(HttpServletRequest request) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        ReqMsgSubscribe reqMsgSubscribe = reqMsgSubscribe(request);
        ReqKLine reqKLine = reqKLine(request);
        map.put("reqMsgSubscribe", reqMsgSubscribe);
        map.put("reqKLine", reqKLine);
        return map;
    }

    /**
     * 定时推送消息
     *
     * @param request
     * @return
     */
    @ApiOperation(value = "node调用-定时推送消息", httpMethod = "GET", response = HashMap.class, notes = "node调用-定时推送消息")
    @GetMapping("/message")
    @ResponseBody
    public HashMap<String, Object> message(HttpServletRequest request) {
        // 闭盘就不推送数据了
        boolean openTrade = isOpenTrade(new Date());
        if (!openTrade) {
            return null;
        }
        HashMap<String, Object> map = new HashMap<String, Object>();
        // 去redis查询产品数量
        String productListStr = redisService.get(ContextUtil.getWebsite() + ":productFixList");
        if (!StringUtils.isEmpty(productListStr)) {
            List<String> productList = JSON.parseArray(productListStr, String.class);
            map.put("productList", productList);// put所有的产品数量

            Map<String, List<MarketDetail>> marketDetail = marketDetail(request, productList);
            Map<String, List<LastKLine>> lastKLine = lastKLine(request, productList);
            map.put("lastKLine", lastKLine);
            map.put("marketDetail", marketDetail);
        }
        return map;
    }

    /**
     * <p>
     * 是否是开闭盘时间
     * </p>
     *
     * @author: Liu Shilei
     * @param: @return
     * @return: boolean
     * @Date : 2016年9月21日 下午6:15:31
     * @throws:
     */
    private boolean isOpenTrade(Date date) {
        String str = redisService.get("appholidayConfig");
        if (!StringUtils.isEmpty(str)) {
            // 判断否是节假日
            List<AppHolidayConfig> list = JSON.parseArray(str, AppHolidayConfig.class);
            if (list != null && list.size() > 0) {
                for (AppHolidayConfig ahc : list) {
                    if (date.getTime() > ahc.getBeginDate().getTime() && date.getTime() < ahc.getEndDate().getTime()) {
                        return false;
                    }
                }
            }
        }

        // 计算是否是开闭盘
        String financeByKey = "";
        String string = redisService.get(StringConstant.CONFIG_CACHE + ":financeConfig");
        JSONArray obj = JSON.parseArray(string);
        for (Object o : obj) {
            JSONObject oo = JSON.parseObject(o.toString());
            if (oo.getString("configkey").equals("openAndclosePlateTime")) {
                financeByKey = oo.getString("value");
            }
        }

        if (!StringUtils.isEmpty(financeByKey)) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            int hours = calendar.get(Calendar.HOUR_OF_DAY);
            int minutes = calendar.get(Calendar.MINUTE);
            String[] split = financeByKey.split(",");
            boolean flag = true;
            for (int i = 0; i < split.length; i++) {
                if (i % 2 == 0) {
                    int h = new Integer(split[i].split(":")[0]).intValue();
                    int m = new Integer(split[i].split(":")[1]).intValue();
                    if (hours == h) {
                        if (minutes < m) {
                            flag = false;
                        }
                    }
                    if (hours < h) {
                        flag = false;
                    }
                }
                if (i % 2 == 1) {
                    int h = new Integer(split[i].split(":")[0]).intValue();
                    int m = new Integer(split[i].split(":")[1]).intValue();

                    if (hours == h) {
                        if (minutes > m) {
                            flag = false;
                        }
                    }
                    if (hours > h) {
                        flag = false;
                    }
                }

                if (!flag) {
                    return flag;
                }
            }
            return flag;
        } else {// 如果缓存为空 直接返回true 让K线正常执行
            return true;
        }

    }

    /**
     * 返回con 中的一个对象
     *
     * @param request
     * @return
     */
    private ReqMsgSubscribe reqMsgSubscribe(HttpServletRequest request) {
        ReqMsgSubscribe reqMsgSubscribe = new ReqMsgSubscribe();
        // 设置请求时间
        reqMsgSubscribe.setRequestIndex(new Date().getTime() + "");
        return reqMsgSubscribe;
    }

    /**
     * 增加当前时间的最新成交节点
     *
     * @author: Liu Shilei
     * @param: @param
     * periodKData
     * @return: void
     * @Date : 2016年10月25日 下午4:47:52
     * @throws:
     */
    private void addKlineLastData(List<TransactionOrder> periodKData, Map<String, Date> periodDate, String period, String symbol) {
        TransactionOrder lastKline = new TransactionOrder();
        String periodLastKLineListStr = redisService.get(symbol + ":PeriodLastKLineList");
        if (!org.apache.commons.lang3.StringUtils.isEmpty(periodLastKLineListStr)) {
            List<LastKLinePayload> periodList = JSON.parseArray(periodLastKLineListStr, LastKLinePayload.class);
            if (periodList != null) {
                for (LastKLinePayload l : periodList) {
                    if (l.getPeriod().equals(period)) {
                        Long nowTime = periodDate.get(l.getPeriod()).getTime() / 1000;
                        if (nowTime.compareTo(l.getTime()) != 0) {//当前时间和最新缓存不在同一区间
                            lastKline.setTransactionTime(DateUtil.dateToString(periodDate.get(period)));
                            lastKline.setStartPrice(l.getPriceLast());
                            lastKline.setMaxPrice(l.getPriceLast());
                            lastKline.setMinPrice(l.getPriceLast());
                            lastKline.setEndPrice(l.getPriceLast());
                            lastKline.setTransactionCount(new BigDecimal(0));
                            break;
                        } else {//当前时间和最新缓存在同一区间
                            lastKline.setTransactionTime(DateUtil.dateToString(periodDate.get(period)));
                            lastKline.setStartPrice(l.getPriceOpen());
                            lastKline.setMaxPrice(l.getPriceHigh());
                            lastKline.setMinPrice(l.getPriceLow());
                            lastKline.setEndPrice(l.getPriceLast());
                            lastKline.setTransactionCount(l.getAmount());
                            break;
                        }
                    }
                }
            }
        } else {
            lastKline.setTransactionTime(DateUtil.dateToString(periodDate.get(period)));
            if (periodKData != null && periodKData.size() > 0) {
                TransactionOrder first = periodKData.get(0);
                lastKline.setStartPrice(first.getStartPrice());
                lastKline.setMaxPrice(first.getMaxPrice());
                lastKline.setMinPrice(first.getMinPrice());
                lastKline.setEndPrice(first.getEndPrice());
                lastKline.setTransactionCount(first.getTransactionCount());
            } else {
                BigDecimal zero = BigDecimal.ZERO;
                lastKline.setStartPrice(zero);
                lastKline.setMaxPrice(zero);
                lastKline.setMinPrice(zero);
                lastKline.setEndPrice(zero);
                lastKline.setTransactionCount(zero);
            }
        }
        if (periodKData != null) {
            periodKData.add(lastKline);
        }
    }

    /**
     * 返回con 中的reqKLine对象
     *
     * @param request
     * @return 获取k线推送数据 第一次加载获取1分钟k线数据 默认分时和1分钟一样
     */
    private ReqKLine reqKLine(HttpServletRequest request) {
        ReqKLine reqKLine = new ReqKLine();
        // 设置请求时间
        reqKLine.setRequestIndex(new Date().getTime() + "");
        ReqKLinePayload reqKLinePayload = new ReqKLinePayload();

        // 获得币种
        String symbol = request.getParameter("symbol");
        reqKLinePayload.setSymbolId(symbol);

        int t = PERIOD;
        String period = request.getParameter("period");
        if (!StringUtils.isEmpty(period) && !"undefined".equals(period)) {
            reqKLinePayload.setPeriod(period);
        }
        if (!StringUtils.isEmpty(period)) {
            switch (period) {
                case "1min":
                    t = 1;
                    break;
                case "5min":
                    t = 5;
                    break;
                case "15min":
                    t = 15;
                    break;
                case "30min":
                    t = 30;
                    break;
                case "60min":
                    t = 60;
                    break;
                case "1day":
                    t = 1440;
                    break;
                case "1week":
                    t = 10080;
                    break; // 10080为分钟
                case "1mon":
                    t = 30000;
                    break; // 30000 只做为一个月的代号，没有实际意义
                default:
                    t = PERIOD;
                    break;
            }
        } else {
            period = "1min";
        }

        // 最多查询最新的800条数据
        String coinCode = symbol;
        String time = String.valueOf(t);
        String table = coinCode + ":klinedata:TransactionOrder_" + coinCode + "_" + time;
        List<TransactionOrder> periodKData = JSON.parseArray(redisService.get(table), TransactionOrder.class);
        if (periodKData == null) {
            periodKData = new ArrayList<TransactionOrder>();
        }
        if (periodKData != null && periodKData.size() > HOLD_COUNT) {
            periodKData = periodKData.subList(0, HOLD_COUNT);
        }
        // 对800条数据按时间正序排序
        SortListUtil<TransactionOrder> sort = new SortListUtil<TransactionOrder>();
        if (periodKData != null && periodKData.size() > 0) {
            sort.Sort(periodKData, "getTransactionTime", "asc");
        }

        // 获得当前时间所在的时间区间
        Map<String, Date> periodDate = DateUtil.getPeriodDate(new Date());
        // 增加当前lastKline节点
        addKlineLastData(periodKData, periodDate, period, symbol);

        // 拼接时间参数
        Random random = new Random();
        // 时间
        Long[] times = new Long[periodKData.size()];
        // 开盘价
        BigDecimal[] priceOpen = new BigDecimal[periodKData.size()];
        // 最高价
        BigDecimal[] priceHigh = new BigDecimal[periodKData.size()];
        // 最低价
        BigDecimal[] priceLow = new BigDecimal[periodKData.size()];
        // 收盘价
        BigDecimal[] priceLast = new BigDecimal[periodKData.size()];
        // 成交量
        BigDecimal[] amount = new BigDecimal[periodKData.size()];
        BigDecimal[] volume = new BigDecimal[periodKData.size()];
        BigDecimal[] count = new BigDecimal[periodKData.size()];

        for (int i = 0; i < periodKData.size(); i++) {
            TransactionOrder transactionOrder = periodKData.get(i);
            if (transactionOrder != null && transactionOrder.getTransactionTime() != null) {
                times[i] = DateUtil.stringToDate(transactionOrder.getTransactionTime()).getTime() / 1000;
            } else {
                logger.info("序列化" + transactionOrder.toString());
                logger.info("时间" + transactionOrder.getTransactionTime());
                logger.info("ID" + transactionOrder.getId());
                continue;
            }

            priceOpen[i] = transactionOrder.getStartPrice();
            priceHigh[i] = transactionOrder.getMaxPrice();
            priceLow[i] = transactionOrder.getMinPrice();
            priceLast[i] = transactionOrder.getEndPrice();
            amount[i] = transactionOrder.getTransactionCount();
            volume[i] = new BigDecimal(random.nextInt(6000000));
            count[i] = new BigDecimal(random.nextInt(1000));
        }

        reqKLinePayload.setTime(times);
        reqKLinePayload.setPriceOpen(priceOpen);
        reqKLinePayload.setPriceHigh(priceHigh);
        reqKLinePayload.setPriceLow(priceLow);
        reqKLinePayload.setPriceLast(priceLast);
        reqKLinePayload.setAmount(amount);
        reqKLinePayload.setVolume(volume);
        reqKLinePayload.setCount(count);

        reqKLine.setPayload(reqKLinePayload);
        return reqKLine;
    }

    private Map<String, Date> getPeriodDate(Date date) {
        Map<String, Date> map = new HashMap<String, Date>();
        // "1min","5min","15min","30min","60min","1day","1week","1mon"
        map.put("1min", DateUtil.stringToDate(DateUtil.getFormatDateTime(date, "yyyy-MM-dd HH:mm")));
        // 当前时间的所在5分区间
        map.put("5min", DateUtil.getPeriodMin(date, 5));
        // 当前时间的所在15分区间
        map.put("15min", DateUtil.getPeriodMin(date, 15));
        // 当前时间的所在30分区间
        map.put("30min", DateUtil.getPeriodMin(date, 30));
        // 当前时间的所在的小时
        map.put("60min", DateUtil.stringToDate(DateUtil.getFormatDateTime(date, "yyyy-MM-dd HH")));
        // 当前时间的所在天
        map.put("1day", DateUtil.stringToDate(DateUtil.getFormatDateTime(date, "yyyy-MM-dd")));
        // 当前时间区间所在的星期一
        Calendar cweek = Calendar.getInstance(Locale.CHINA);
        cweek.setTime(date);
        cweek.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        cweek.set(Calendar.HOUR_OF_DAY, 0);
        cweek.set(Calendar.MINUTE, 0);
        map.put("1week", cweek.getTime());
        // 当前时间所在月的第一天
        Calendar cmon = Calendar.getInstance(Locale.CHINA);
        cmon.setTime(date);
        cmon.set(Calendar.DAY_OF_MONTH, 1);
        cmon.set(Calendar.HOUR_OF_DAY, 0);
        cmon.set(Calendar.MINUTE, 0);
        map.put("1mon", cmon.getTime());
        return map;
    }

    /**
     * 每秒推最后一条KLINE数据,8种 时间区间一次全推 查全部币种的每个时间区间 总条数为币种数*8个时间区间
     *
     * @param request
     * @return
     */
    private Map<String, List<LastKLine>> lastKLine(HttpServletRequest request, List<String> productList) {
        // 获得当前时间所在的时间区间
        Map<String, Date> periodDate = DateUtil.getPeriodDate2(new Date());
        Map<String, List<LastKLine>> map = new HashMap<String, List<LastKLine>>();
        for (String coinCode : productList) {
            if ("true".equals(PropertiesUtils.APP.getProperty("app.okcoin"))) {
                if ("BTC".equals(coinCode) || "LTC".equals(coinCode)) {
                    String url = "https://www.okcoin.cn/api/v1/ticker.do";
                    if (ContextUtil.EN.equals(ContextUtil.getWebsite())) {
                        url = "https://www.okcoin.com/api/v1/ticker.do";
                    }
                    String param = "symbol=";
                    if ("BTC".equals(coinCode)) {
                        param += "btc_" + ContextUtil.getCurrencyType();
                    }
                    if ("LTC".equals(coinCode)) {
                        param += "ltc_" + ContextUtil.getCurrencyType();
                    }
                    String send = HttpConnectionUtil.getSend(url, param);
                    JSONObject data = (JSONObject) JSON.parse(send);
                    JSONObject ticker = (JSONObject) data.get("ticker");
                    String last = (String) ticker.get("last");
                }
            }
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
                            l.setTime(periodDate.get(l.getPeriod()).getTime() / 1000);
                        }
                        list.add(lastKLine);
                        lastKLine.setPayload(l);
                    }
                }
            }
            map.put(coinCode, list);
        }
        return map;
    }

    /**
     * 每秒推最新成交的数据, 推出所有币的成交记录
     *
     * @param request
     * @return
     */
    private Map<String, List<MarketDetail>> marketDetail(HttpServletRequest request, List<String> productList) {
        String langCode = "zh_CN";
        /*获得usdt对人民币价格*/
        BigDecimal usdttormb = new BigDecimal(6.6);
        String financeConfig = redisService.get("CointoMoney:" + langCode);
        String coinSymbol = "";
        if (!StringUtils.isEmpty(financeConfig)) {
            ExCointoMoney exCointoMoney = JSONObject.parseObject(financeConfig, ExCointoMoney.class);
            usdttormb = new BigDecimal(exCointoMoney.getRate());
            coinSymbol = exCointoMoney.getCoinSymbol();
        }

        // 做市字段
        String theSeat = request.getParameter("theSeat");
        String key = "pushEntrusMarket";
        // 如果是做市
        if ("theSeat".equals(theSeat)) {
            key = "pushtheSeatEntrustCenter";
        }

        Map<String, List<MarketDetail>> map = new HashMap<String, List<MarketDetail>>();
        for (String coinCode : productList) {
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
                        e.printStackTrace();
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

                marketDetail.setPayload(payload);
                list.add(marketDetail);
            }
            map.put(coinCode, list);
        }
        return map;

    }


    /**
     * indexv1 node调用
     *
     * @param request
     * @return
     */
    @ApiOperation(value = "node调用-查询有哪些交易区", httpMethod = "GET", response = HashMap.class, notes = "node调用-查询有哪些交易区")
    @GetMapping(value = "/indexv1node")
    @ResponseBody
    public JSONArray indexv1node(HttpServletRequest request) {
        Locale locale = LocaleContextHolder.getLocale();
        JSONArray jsonArray = new JSONArray();
        try {
            /*获得usdt对人民币价格*/
            //获取实时价格
            String usdtPrice = redisService.hget("hry:coinPrice", "USDT");

            /*获得usdt对人民币价格*/
            BigDecimal usdttormb = new BigDecimal(StringUtils.isEmpty(usdtPrice) ? "6.8" : usdtPrice);
            //法币符号
            String coinSymbol = "";
            String rate = "";

            String financeConfig = redisService.get("CointoMoney:" + locale.toString());
            if (!StringUtils.isEmpty(financeConfig)) {
                ExCointoMoney exCointoMoney = JSONObject.parseObject(financeConfig, ExCointoMoney.class);
                usdttormb = new BigDecimal(exCointoMoney.getRate()).multiply(usdttormb);
                coinSymbol = exCointoMoney.getCoinSymbol();
                rate = exCointoMoney.getRate();
            }

            // 查询交易区数据
            List<String> tradeArea = remoteManageService.getTredeArea();
            if (tradeArea != null && tradeArea.size() > 0) {
                // 去redis查询产品数量
                String productListStr = redisService.get("cn:coinInfoList");
                if (!StringUtils.isEmpty(productListStr)) {
                    List<Coin> productList = JSON.parseArray(productListStr, Coin.class);
                    /*//统计交易区数量
                    Set<String> coinarea = new LinkedHashSet<String>();
                    //增加交易区
                    for (Coin coin : productList) {
                        coinarea.add(coin.getFixPriceCoinCode());
                    }

                    if (coinarea.size() > 0) {*/
                    /*Iterator<String> it = coinarea.iterator();
                    while (it.hasNext()) {*/
                    for (String area : tradeArea) {
                        //String areaname = it.next();
                        String areaname = area;

                        JSONObject obj = new JSONObject();

                        //创建list
                        ArrayList<JSONObject> list = new ArrayList<JSONObject>();
                        //遍历
                        for (Coin coin : productList) {
                            if (areaname.equals(coin.getFixPriceCoinCode())) {
                                JSONObject data = createData(coin);
                                String fixPrice = redisService.hget("hry:coinPrice", coin.getFixPriceCoinCode());
                                data.put("usdttormb", usdttormb);
                                data.put("fixPrice", fixPrice);
                                data.put("coinrate", rate);
                                list.add(data);
                            }
                        }
                        if (list != null || list.size() > 0) {
                            obj.put("areaname", areaname);
                            obj.put("areanameview", areaname);
                            obj.put("data", list);
                            //加入map中
                            jsonArray.add(obj);
                        }
                    }
                    //}
                }
            }
            return jsonArray;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonArray;
    }

    @ApiOperation(value = "node调用-新首页数据推送  view_v1版本", httpMethod = "GET", response = HashMap.class, notes = "node调用-新首页数据推送  view_v1版本")
    @RequestMapping(value = "/indexv1")
    @ResponseBody
    public Map<String, JSONArray> indexv1(HttpServletRequest request) {
        Map<String,JSONArray> map = new HashMap<>(2);
        JSONArray commonArray = new JSONArray();
        JSONArray lendArray = new JSONArray();
        try {
            Locale locale = LocaleContextHolder.getLocale();

            //获取实时价格
            String usdtPrice = redisService.hget("hry:coinPrice","USDT");

            /*获得usdt对人民币价格*/
            BigDecimal usdttormb = new BigDecimal(StringUtils.isEmpty(usdtPrice) ? "6.8" : usdtPrice);
            //法币符号
            String coinSymbol = "";

            String financeConfig = redisService.get("CointoMoney:" + locale.toString());
            if (!StringUtils.isEmpty(financeConfig)) {
                ExCointoMoney exCointoMoney = JSONObject.parseObject(financeConfig, ExCointoMoney.class);
                usdttormb = new BigDecimal(exCointoMoney.getRate()).multiply(usdttormb);
                coinSymbol = exCointoMoney.getCoinSymbol();
            }

            // 去redis查询产品数量
            String productListStr = redisService.get("cn:coinInfoList");
            if (!StringUtils.isEmpty(productListStr)) {
                List<Coin> productList = JSON.parseArray(productListStr, Coin.class);
                commonArray = getProductList(usdttormb, coinSymbol, productList);

                List<Coin> lendCoin = new ArrayList<>();
                Set<String> coins = redisService.hkeys(ExchangeLendKey.LEND_CONFIG);
                for (Coin coin : productList) {
                    String coinCode = coin.getCoinCode()+"_"+coin.getFixPriceCoinCode();
                    if(coins.contains(coinCode)){
                        String configStr = redisService.hget(ExchangeLendKey.LEND_CONFIG, coinCode);
                        if(!StringUtils.isEmpty(configStr)){
                            ExLendConfig config = JSON.parseObject(configStr, ExLendConfig.class);
                            if(config.getStatus().equals(DealConstant.OPEN)) {
                                lendCoin.add(coin);
                            }
                        }
                    }
                }
                lendArray = getProductList(usdttormb, coinSymbol, lendCoin);

            }
            map.put("common",commonArray);
            map.put("lend",lendArray);
            return map;

        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }

    private JSONArray getProductList(BigDecimal usdttormb, String coinSymbol, List<Coin> productList) {
        JSONArray jsonArray = new JSONArray();
        //排序
        productList.sort((coin1,coin2) -> coin1.getCoinCode().compareTo(coin2.getCoinCode()));

        //统计交易区数量
        Set<String> coinarea = new LinkedHashSet<String>();
        User user = null;
        Session session = SecurityUtils.getSubject().getSession(false);
        if (session != null) {
            //查询用户自选区
            user = (User) session.getAttribute("user");
        }

        //用户自选交易对
        List<String> mycoins = null;
        if (user != null) {
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
                            JSONObject data = createData(coin);
                            data.put("usdttormb", usdttormb);
                            data.put("coinSymbol", coinSymbol);
                            data.put("isShoucang", true);
                            // 获取该交易对最新5分钟数据
                            String Code = coin.getCoinCode() + "_" + coin.getFixPriceCoinCode();
                            String points = getLineData(Code);
                            data.put("points", points);
                            ExCointoCoin exCoinToCoin = JSONObject.parseObject(redisService.hget("ex:cointoCoin",coin.getCoinCode() + ":" + coin.getFixPriceCoinCode()),ExCointoCoin.class);
                            if(exCoinToCoin!=null){
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
        for (Coin coin : productList) {
            coinarea.add(coin.getFixPriceCoinCode());
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
                        JSONObject data = createData(coin);
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
                        String points = getLineData(coinCode);
                        data.put("points", points);
                        //每个交易对自己的位数
                        ExCointoCoin exCoinToCoin = JSONObject.parseObject(redisService.hget("ex:cointoCoin",coin.getCoinCode() + ":" + coin.getFixPriceCoinCode()),ExCointoCoin.class);
                        if(exCoinToCoin!=null){
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
     * 首页行情数据
     *
     * @param langCode 语种
     * @return
     */
    @ApiOperation(value = "获取首页行情数据", httpMethod = "GET", response = ApiJsonResult.class, notes = "获取首页行情数据")
    @GetMapping(value = "/index")
    @ResponseBody
    public JSONArray index(
            @ApiParam(name = "langCode", value = "语种", required = true) @RequestParam("langCode") String langCode,
            HttpServletRequest request) {
        //获取实时价格
        String usdtPrice = redisService.hget("hry:coinPrice", "USDT");
        /*获得usdt对人民币价格*/
        BigDecimal usdttormb = new BigDecimal(StringUtils.isEmpty(usdtPrice) ? "6.8" : usdtPrice);
        //法币符号
        String coinSymbol = "";

        String financeConfig = redisService.get("CointoMoney:" + langCode);
        if (!StringUtils.isEmpty(financeConfig)) {
            ExCointoMoney exCointoMoney = JSONObject.parseObject(financeConfig, ExCointoMoney.class);
            usdttormb = new BigDecimal(exCointoMoney.getRate()).multiply(usdttormb);
            coinSymbol = exCointoMoney.getCoinSymbol();
        }

        JSONArray jsonArray = new JSONArray();

        // 去redis查询产品数量
        String productListStr = redisService.get("cn:coinInfoList");
        if (!StringUtils.isEmpty(productListStr)) {
            List<Coin> productList = JSON.parseArray(productListStr, Coin.class);
            //排序
            productList.sort((coin1, coin2) -> coin1.getCoinCode().compareTo(coin2.getCoinCode()));

            //统计交易区数量
            //Set<String> coinarea = new LinkedHashSet<String>();
            User user = TokenUtil.getUser(request);

            //用户自选交易对
            List<String> mycoins = null;
            JSONObject myobj = new JSONObject();
            if (user != null) {
                mycoins = remoteBaseInfoService.findCustomerCollection(user.getCustomerId());
                myobj.put("areaname", SpringUtil.diff("zixuan"));
                myobj.put("areanameview", SpringUtil.diff("zixuan"));

                //创建list
                ArrayList<JSONObject> list = new ArrayList<JSONObject>();
                if (mycoins != null && mycoins.size() > 0) {
                    for (String coinCode : mycoins) {
                        //遍历
                        for (Coin coin : productList) {
                            if (coinCode.equals(coin.getCoinCode() + "_" + coin.getFixPriceCoinCode())) {
                                JSONObject data = createData(coin);
                                data.put("usdttormb", usdttormb);
                                data.put("coinSymbol", HtmlUtils.htmlUnescape(coinSymbol));
                                data.put("isShoucang", true);
                                // 获取该交易对最新5分钟数据
                                String Code = coin.getCoinCode() + "_" + coin.getFixPriceCoinCode();
                                String points = getLineData(Code);
                                data.put("points", points);
                                ExCointoCoin exCoinToCoin = JSONObject.parseObject(redisService.hget("ex:cointoCoin", coin.getCoinCode() + ":" + coin.getFixPriceCoinCode()), ExCointoCoin.class);
                                if (exCoinToCoin != null) {
                                    data.put("keepDecimalCoinCode", exCoinToCoin.getKeepDecimalFixPrice());
                                }
                                list.add(data);
                            }
                        }
                    }
                }
                myobj.put("data", list);
            }
            /*//增加交易区
            for (Coin coin : productList) {
                coinarea.add(coin.getFixPriceCoinCode());
            }*/
            // 获取交易区
            List<String> tradeArea = remoteManageService.getTredeArea();
            if (tradeArea != null && tradeArea.size() > 0) {
                //if (coinarea.size() > 0) {
                    /*Iterator<String> it = coinarea.iterator();
                    while (it.hasNext()) {
                        String areaname = it.next();*/
                for (String areaname : tradeArea) {
                    JSONObject obj = new JSONObject();

                    //创建list
                    ArrayList<JSONObject> list = new ArrayList<JSONObject>();
                    //遍历
                    for (Coin coin : productList) {
                        if (areaname.equals(coin.getFixPriceCoinCode())) {
                            JSONObject data = createData(coin);
                            data.put("usdttormb", usdttormb);
                            data.put("coinSymbol", HtmlUtils.htmlUnescape(coinSymbol));
                            if (mycoins != null && mycoins.size() > 0) {
                                for (String coinCode : mycoins) {
                                    if (coinCode.equals(coin.getCoinCode() + "_" + coin.getFixPriceCoinCode())) {
                                        data.put("isShoucang", true);
                                    }
                                }
                            }
                            // 获取该交易对最新5分钟数据
                            String coinCode = coin.getCoinCode() + "_" + coin.getFixPriceCoinCode();
                            String points = getLineData(coinCode);
                            data.put("points", points);
                            //每个交易对自己的位数
                            ExCointoCoin exCoinToCoin = JSONObject.parseObject(redisService.hget("ex:cointoCoin", coin.getCoinCode() + ":" + coin.getFixPriceCoinCode()), ExCointoCoin.class);
                            if (exCoinToCoin != null) {
                                data.put("keepDecimalCoinCode", exCoinToCoin.getKeepDecimalFixPrice());
                            }

                            list.add(data);
                        }
                    }
                    if (list != null && list.size() > 0) {
                        obj.put("areaname", areaname);
                        obj.put("areanameview", areaname);
                        obj.put("data", list);
                        //加入map中
                        jsonArray.add(obj);
                    }
                }
                //}
            }
            jsonArray.add(myobj);
        }
        return jsonArray;
    }

    @ApiOperation(value = "初始化高级交易大厅数据", httpMethod = "GET", response = Map.class, notes = "初始化高级交易大厅数据")
    @GetMapping("/tradingview")
    @ResponseBody
    public Map<String, Object> tradingview(
            @ApiParam(name = "symbol", value = "交易对币种名称", required = false) @RequestParam(value = "symbol", required = false) String symbol,
            @ApiParam(name = "langCode", value = "语种", required = true) @RequestParam("langCode") String langCode,
            @ApiParam(name = "isType", value = "杠杆还是高级,1杠杆交易区，0高级交易区", required = true) @RequestParam(value = "isType",required = false) String isType,
            HttpServletRequest request) {
        try {
            Map<String, Object> returnMap = new HashMap<>();
            User user = TokenUtil.getUser(request);

            if (null != user) {
                JedisPool jedisPool = (JedisPool) hry.util.sys.ContextUtil.getBean("jedisPool");
                try (Jedis jedis = jedisPool.getResource()) {
                    String isOne = jedis.hget(ExchangeLendKey.FIRST_TIME, user.getCustomerId().toString());
                    if (StringUtils.isEmpty(isOne)) {
                        returnMap.put("firstIn", true);
                        String arrays = jedis.get("configCache:baseConfig" + langCode);
                        if (!org.springframework.util.StringUtils.isEmpty(arrays)) {
                            JSONArray jsonArray = JSONArray.parseArray(arrays);
                            for (Object obj : jsonArray) {
                                String key = String.valueOf(JSON.parseObject(obj.toString()).get("configkey"));
                                if(key.equals("lend")){
                                    returnMap.put("layoutHtml", JSON.parseObject(obj.toString()).get("value"));
                                    break;
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            // 重定向到默认币种
            if (StringUtils.isEmpty(symbol) || !symbol.contains("_")) {
                String str = redisService.get("cn:coinInfoList");
                if (!StringUtils.isEmpty(str)) {
                    List<Coin> coins = JSON.parseArray(str, Coin.class);
                    Set<String> lendCoins = redisService.hkeys(ExchangeLendKey.LEND_CONFIG);
                    if (coins != null && coins.size() > 0) {
                        for(Coin coin :coins){
                            String coinCode = coin.getCoinCode() + "_" + coin.getFixPriceCoinCode();
                            if(!StringUtils.isEmpty(isType) && "1".equals(isType)){
                                if (!coins.contains(coinCode)){
                                    continue;
                                }
                            }
                            returnMap.put("showCoin", coin.getCoinCode());
                            returnMap.put("showFixPriceCoin", coin.getFixPriceCoinCode());
                            returnMap.put("defaultCoin", coin.getCoinCode() + "_" + coin.getFixPriceCoinCode());
                            return returnMap;
                        }

                    }
                }
            }

            if (!StringUtils.isEmpty(symbol)) {
                List<AppBanner> list = this.listBanner();
                for (AppBanner appBanner : list) {
                    String piclan = appBanner.getLangCode();
                    if (!langCode.equals(piclan)) {
                        continue;
                    }
                    if (appBanner.getWhereUse() != null ) {
                        if ("5".equals(appBanner.getWhereUse())) {
                            returnMap.put("tradingTopPic", appBanner.getPicturePath());
                        }
                        if ("7".equals(appBanner.getWhereUse())) {
                            returnMap.put("lendTopPic", appBanner.getPicturePath());
                        }
                    }
                }


                String[] split = symbol.split("_");
                returnMap.put("showCoin", split[0]);
                returnMap.put("showFixPriceCoin", split[1]);
                returnMap.put("fixCoin", split[1]);

                //获取交易对信息
                String coinToCoin = redisService.hget("ex:cointoCoin", symbol.replace("_", ":"));
                ExCointoCoin exCointoCoin = null;
                if (!StringUtils.isEmpty(coinToCoin)) {
                    exCointoCoin = JSONObject.parseObject(coinToCoin, ExCointoCoin.class);
                    returnMap.put("coinToCoinKeepDecimalForCoin", exCointoCoin.getKeepDecimalFixPrice());
                    returnMap.put("CoinToCoinKeepDecimalForCurrency", exCointoCoin.getKeepDecimalCoinCode());
                }

                String str = redisService.get("cn:coinInfoList");
                if (user != null) {
                    returnMap.put("isRealState", user.getIsReal());
                    try {
                        //查询市值
                        Map<String, String> info = remoteLendService.getLengCoinCodeForRmbCenter(user.getCustomerId());
                        returnMap.put("info", info);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    BigDecimal hotMoney = remoteManageService.getHotMoney(user.getCustomerId().toString(), split[1]);
                    returnMap.put("hotMoney", hotMoney);
                }
                // 查交易币的位数
                if (!StringUtils.isEmpty(str)) {
                    List<Coin> coins = JSON.parseArray(str, Coin.class);
                    if (coins != null && coins.size() > 0) {
                        for (Coin coin : coins) {
                            if (split[0].equals(coin.getCoinCode()) && split[1].equals(coin.getFixPriceCoinCode())) {
                                returnMap.put("keepDecimalForCoin", coin.getKeepDecimalForCoin());
                                returnMap.put("sellMin", coin.getSellMinCoin());
                                returnMap.put("sellMax", coin.getOneTimeOrderNum());
                                returnMap.put("keepDecimalForCurrency", coin.getKeepDecimalForCurrency());

                                //交易币的信息
                                ExProductManage exProduct = JSONObject.parseObject(redisService.hget("ex:Product", coin.getCoinCode()), ExProductManage.class);
                                returnMap.put("exProduct", exProduct);
                            }
                        }
                    }
                }
            }
            //获取实时价格
            String usdtPrice = redisService.hget("hry:coinPrice", "USDT");

            /*获得usdt对人民币价格*/
            BigDecimal usdttormb = new BigDecimal(StringUtils.isEmpty(usdtPrice) ? "6.8" : usdtPrice);

            //法币符号
            String coinSymbol = "";
            String financeConfig = redisService.get("CointoMoney:" + langCode);
            if (!StringUtils.isEmpty(financeConfig)) {
                ExCointoMoney exCointoMoney = JSONObject.parseObject(financeConfig, ExCointoMoney.class);
                usdttormb = new BigDecimal(exCointoMoney.getRate()).multiply(usdttormb);
                coinSymbol = exCointoMoney.getCoinCode();
                returnMap.put("usdttormb", usdttormb);
                returnMap.put("coinSymbol", HtmlUtils.htmlUnescape(coinSymbol));
                returnMap.put("coinCodeSymbol", exCointoMoney.getCoinSymbol());
                returnMap.put("coinRate", exCointoMoney.getRate());

                String fixPrice = redisService.hget("hry:coinPrice", symbol.split("_")[1]);
                if (StringUtils.isEmpty(fixPrice)) {
                    fixPrice = "0";
                }
                returnMap.put("fixPrice", new BigDecimal(fixPrice).multiply(new BigDecimal(exCointoMoney.getRate())));
            }
            //24小时成交量
            String H24COunt = redisService.get(symbol + ":new24hoursamout");
            if (null != H24COunt) {
                if (H24COunt.contains(".")) {
                    H24COunt = H24COunt.substring(0, H24COunt.indexOf("."));
                    returnMap.put("H24COunt", H24COunt);
                } else {
                    returnMap.put("H24COunt", H24COunt);
                }
            }

            // websocket用到，暂时注释掉
        /*HttpSession session = request.getSession();
        if (null == user) {
            session.setAttribute("uid", "0" + RandomStringUtils.random(12, false, true));
        } else {
            session.setAttribute("uid", user.getCustomerId());
        }
        session.setAttribute("coinCode", symbol);*/

            Map<String, Object> root = BaseConfUtil.getConfigByKey(Constant.baseConfig, langCode);
            returnMap.put("SEOTitle", "-" + root.get("SEOTitle"));
            return returnMap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 查询自选数据
     *
     * @param request
     * @return
     */
    @ApiOperation(value = "查询自选数据", httpMethod = "GET", response = JSONArray.class, notes = "查询自选数据")
    @GetMapping(value = "/zixuan")
    @ResponseBody
    public JSONArray zixuan(
            @ApiParam(name = "langCode", value = "语种", required = true) @RequestParam("langCode") String langCode,
            @ApiParam(name = "type", value = "杠杆or普通", required = true) @RequestParam(name = "type", defaultValue = "0") String type,
            HttpServletRequest request) {
        JSONArray jsonArray = new JSONArray();
        try {
            /*获得usdt对人民币价格*/
            BigDecimal usdttormb = new BigDecimal(1);
            //法币符号
            String coinSymbol = "";

            String financeConfig = redisService.get("CointoMoney:" + langCode);
            if (!StringUtils.isEmpty(financeConfig)) {
                ExCointoMoney exCointoMoney = JSONObject.parseObject(financeConfig, ExCointoMoney.class);
                usdttormb = new BigDecimal(exCointoMoney.getRate());
                coinSymbol = exCointoMoney.getCoinSymbol();
            }

            // 去redis查询产品数量
            String productListStr = redisService.get("cn:coinInfoList");
            if (!StringUtils.isEmpty(productListStr)) {
                List<Coin> productList = JSON.parseArray(productListStr, Coin.class);

                User user = TokenUtil.getUser(request);
                //用户自选交易对
                List<String> mycoins = null;
                if (user != null) {
                    mycoins = remoteBaseInfoService.findCustomerCollection(user.getCustomerId());
                    JSONObject obj = new JSONObject();
                    obj.put("areaname", SpringUtil.diff("zixuan"));
                    obj.put("areanameview", SpringUtil.diff("zixuan"));

                    //创建list
                    ArrayList<JSONObject> list = new ArrayList<JSONObject>();

                    List<String> lendCoin = new ArrayList<>();
                    if (type.equals("1")) {
                        JedisPool jedisPool = (JedisPool) hry.util.sys.ContextUtil.getBean("jedisPool");
                        try (Jedis jedis = jedisPool.getResource()) {
                            Map<String, String> coinCodes = jedis.hgetAll(ExchangeLendKey.LEND_CONFIG);
                            for (String key : coinCodes.keySet()) {
                                ExLendConfig config = JSON.parseObject(coinCodes.get(key), ExLendConfig.class);
                                if (config.getStatus().equals(DealConstant.OPEN)) {
                                    lendCoin.add(key);
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    if (mycoins != null && mycoins.size() > 0) {
                        for (String coinCode : mycoins) {
                            //遍历
                            for (Coin coin : productList) {
                                String coinCodeX = coin.getCoinCode() + "_" + coin.getFixPriceCoinCode();
                                if ((type.equals("0") && coinCode.equals(coinCodeX)) || (type.equals("1") && coinCode.equals(coinCodeX) && lendCoin.contains(coinCodeX))) {
                                    JSONObject data = zixuancreateData(coin);
                                    data.put("usdttormb", usdttormb);
                                    data.put("coinSymbol", HtmlUtils.htmlUnescape(coinSymbol));
                                    data.put("isShoucang", true);
                                    // 获取该交易对最新5分钟数据
                                    String Code = coin.getCoinCode() + "_" + coin.getFixPriceCoinCode();
                                    String points = getLineData(Code);
                                    data.put("points", points);
                                    list.add(data);
                                }
                            }
                        }
                    }
                    obj.put("data", list);
                    //加入map中
                    jsonArray.add(obj);
                }
            }
            return jsonArray;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonArray;
    }

    /**
     * 添加收藏
     *
     * @param request
     * @return
     */
    @ApiOperation(value = "添加收藏", httpMethod = "GET", response = ApiJsonResult.class, notes = "添加收藏")
    @GetMapping("/addCustomerCollection")
    @ResponseBody
    public ApiJsonResult addCustomerCollection(
            @ApiParam(name = "coinCode", value = "币种类型", required = true) @RequestParam("coinCode") String coinCode,
            HttpServletRequest request) {
        ApiJsonResult jsonResult = new ApiJsonResult();
        User user = TokenUtil.getUser(request);
        if (user != null) {
            remoteBaseInfoService.addCustomerCollection(user.getCustomerId(), coinCode);
            jsonResult.setSuccess(true);
            jsonResult.setMsg(SpringUtil.diff("success"));
            return jsonResult;
        }
        jsonResult.setSuccess(false);
        jsonResult.setMsg(SpringUtil.diff("before_login"));
        return jsonResult;
    }

    /**
     * 移除收藏
     *
     * @param request
     * @return
     */
    @ApiOperation(value = "移除收藏", httpMethod = "GET", response = ApiJsonResult.class, notes = "移除收藏")
    @GetMapping("/removeCustomerCollection")
    @ResponseBody
    public ApiJsonResult removeCustomerCollection(
            @ApiParam(name = "coinCode", value = "币种类型", required = true) @RequestParam("coinCode") String coinCode,
            HttpServletRequest request) {
        ApiJsonResult jsonResult = new ApiJsonResult();
        User user = TokenUtil.getUser(request);
        if (user != null) {
            remoteBaseInfoService.deleteCustomerCollection(user.getCustomerId(), coinCode);
            jsonResult.setSuccess(true);
            jsonResult.setMsg(SpringUtil.diff("success"));
            return jsonResult;
        }
        jsonResult.setSuccess(false);
        jsonResult.setMsg(SpringUtil.diff("before_login"));
        return jsonResult;
    }

    // 查询查询系统基础配置
    private List<AppBanner> listBanner() {
        List<AppBanner> list = new ArrayList<AppBanner>();
        String text = redisService.get(StringConstant.CACHE_BANNER);
        if (!StringUtils.isEmpty(text)) {
            list = JSON.parseArray(text, AppBanner.class);
        }
        return list;
    }

    /**
     * 创建交易区的一个交易对的data
     *
     * @return
     */
    private JSONObject createData(Coin coin) {
        //交易币的位数
        Integer keepCoin = coin.getKeepDecimalForCoin();
        //定价币的位数
        Integer keepCurrency = coin.getKeepDecimalForCurrency();
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

        JSONObject data = new JSONObject();
        data.put("coinCode", coin.getCoinCode() + "_" + coin.getFixPriceCoinCode());
        data.put("name", coin.getCoinCode() + "_" + coin.getFixPriceCoinCode());
        data.put("picturePath", coin.getPicturePath());

        /*//交易对位数
        ExCointoCoin exCointoCoin = JSONObject.parseObject(redisService.hget("ex:cointoCoin", coin.getCoinCode() + ":" + coin.getFixPriceCoinCode()), ExCointoCoin.class);
        data.put("keepDecimalFixPrice", "8");
        if (exCointoCoin != null) {
            data.put("keepDecimalFixPrice", exCointoCoin.getKeepDecimalFixPrice());
        }*/
        //定价币对USDT价格
        String usdtprice = redisService.get(coin.getFixPriceCoinCode() + "_USDT" + ":currentExchangPrice");

        String currentExchangPrice_str = redisService.get(coin.getCoinCode() + "_" + coin.getFixPriceCoinCode() + ":currentExchangPrice");
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
        String coinStr = redisService.get("cn:coinInfoList2");
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

        String str = redisService.get(coin.getCoinCode() + "_" + coin.getFixPriceCoinCode() + ":PeriodLastKLineList");
        if (str != null) {
            JSONArray jsonv = JSON.parseArray(str);

            if (jsonv.getString(5) != null) {
                JSONObject jsonv_ = JSON.parseObject(jsonv.getString(5));
                if ("1day".equals(jsonv_.getString("period"))) {
                    BigDecimal currentExchangPrice = new BigDecimal(0);
                    //上一笔交易价格
                    String orders = redisService.get(coin.getCoinCode() + "_" + coin.getFixPriceCoinCode() + ":pushNewListRecordMarketDesc");
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
                    String new24hoursvol_str = redisService.get(coin.getCoinCode() + "_" + coin.getFixPriceCoinCode() + ":new24hoursamout");
                    if (!StringUtils.isEmpty(new24hoursvol_str)) {
                        data.put("transactionSum", decimalFormatCoin.format(new BigDecimal(new24hoursvol_str)));
                    } else {
                        data.put("transactionSum", decimalFormatCoin.format(new BigDecimal(0)));
                    }

                    data.put("maxPrice", jsonv_.getString("priceHigh"));
                    data.put("minPrice", jsonv_.getString("priceLow"));

                    //最高价成交价转usdt
                    if ("USDT".equals(coin.getFixPriceCoinCode())) {
                        //logger.error(jsonv_.toJSONString());
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
        return data;
    }

    /**
     * 获取折线数据
     *
     * @param coinCode 交易对名称
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
     * 自选创建节点
     *
     * @param coin
     * @return
     */
    private JSONObject zixuancreateData(Coin coin) {
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

            //定价币对USDT价格
            String usdtprice = redisService.get(coin.getFixPriceCoinCode() + "_USDT" + ":currentExchangPrice");

            String currentExchangPrice_str = redisService.get(coin.getCoinCode() + "_" + coin.getFixPriceCoinCode() + ":currentExchangPrice");
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
                currentExchangPrice_str = "0";
            }

            // 昨日收盘价
            String coinStr = redisService.get("cn:coinInfoList2");
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

            String str = redisService.get(coin.getCoinCode() + "_" + coin.getFixPriceCoinCode() + ":PeriodLastKLineList");
            if (str != null) {
                JSONArray jsonv = JSON.parseArray(str);
                if (jsonv.getString(5) != null) {
                    JSONObject jsonv_ = JSON.parseObject(jsonv.getString(5));
                    if ("1day".equals(jsonv_.getString("period"))) {
                        BigDecimal currentExchangPrice = new BigDecimal(currentExchangPrice_str);
                        data.put("maxPrice", jsonv_.getString("priceHigh"));
                        data.put("minPrice", jsonv_.getString("priceLow"));

                        //最高价成交价转usdt
                        if ("USDT".equals(coin.getFixPriceCoinCode())) {
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
        }
        return data;
    }

    /**
     * 新首页数据排序
     *
     * @param request
     * @return
     */
    @ApiOperation(value = "新首页数据排序/查询", httpMethod = "POST", response = JSONArray.class, notes = "新首页数据排序/查询")
    @PostMapping("/dataSort")
    @ResponseBody
    public JSONArray dataSort(
            @ApiParam(name = "areaname", value = "排序交易区", required = true) @RequestParam("areaname") String areaname,
            @ApiParam(name = "sortCloum", value = "排序字段", required = true) @RequestParam("sortCloum") String sortCloum,
            @ApiParam(name = "sorts", value = "升序还是降序", required = true) @RequestParam("sorts") String sorts,
            @ApiParam(name = "search", value = "查询条件", required = true) @RequestParam("search") String search,
            @ApiParam(name = "langCode", value = "语种", required = true) @RequestParam("langCode") String langCode,
            HttpServletRequest request) {
        JSONArray jsonArray = new JSONArray();
        try {
            //获取实时价格
            String usdtPrice = redisService.hget("hry:coinPrice", "USDT");

            /*获得usdt对人民币价格*/
            BigDecimal usdttormb = new BigDecimal(StringUtils.isEmpty(usdtPrice) ? "6.8" : usdtPrice);
            //法币符号
            String coinSymbol = "";
            String rate = "";

            String financeConfig = redisService.get("CointoMoney:" + langCode);
            if (!StringUtils.isEmpty(financeConfig)) {
                ExCointoMoney exCointoMoney = JSONObject.parseObject(financeConfig, ExCointoMoney.class);
                usdttormb = new BigDecimal(exCointoMoney.getRate()).multiply(usdttormb);
                coinSymbol = exCointoMoney.getCoinSymbol();
                rate = exCointoMoney.getRate();
            }

            boolean flag = true;
            if ("desc".equals(sorts)) {
                flag = false;
            } else {
                flag = true;
            }

            // 去redis查询产品数量
            String productListStr = redisService.get("cn:coinInfoList");
            if (!StringUtils.isEmpty(productListStr)) {
                List<Coin> productList = JSON.parseArray(productListStr, Coin.class);
                User user = null;
                Session session = SecurityUtils.getSubject().getSession(false);
                if (session != null) {
                    //查询用户自选区
                    user = (User) session.getAttribute("user");
                }
                //用户自选交易对
                List<String> mycoins = null;
                if (user != null) {
                    mycoins = remoteBaseInfoService.findCustomerCollection(user.getCustomerId());
                }
                //查询用户自选区
                if ("mycollection".equals(areaname)) {
                    //创建list
                    ArrayList<JSONObject> list = new ArrayList<JSONObject>();
                    if (mycoins != null && mycoins.size() > 0) {
                        for (String coinCode : mycoins) {
                            //遍历
                            for (Coin coin : productList) {
                                if (coinCode.equals(coin.getCoinCode() + "_" + coin.getFixPriceCoinCode())) {
                                    JSONObject data = createData(coin);
                                    data.put("coinSymbol", HtmlUtils.htmlUnescape(coinSymbol));
                                    data.put("usdttormb", usdttormb);
                                    data.put("coinrate", rate);
                                    data.put("isShoucang", true);
                                    // 获取该交易对最新5分钟数据
                                    String points = getLineData(coinCode);
                                    data.put("points", points);
                                    if (search != null && !"".equals(search)) {
                                        if (data.getString("coinCode").contains(search)) {
                                            list.add(data);
                                        }
                                    } else {
                                        list.add(data);
                                    }
                                }
                            }
                        }
                    }
                    //加入map中
                    jsonArray.addAll(list);
                    //如果排序列不为空，就进行排序
                    if (!"".equals(sortCloum) && sortCloum != null) {
                        jsonArray = SortListUtil.sortJsonArrayTest(jsonArray, sortCloum, flag);
                    }
                } else {
                    //创建list
                    ArrayList<JSONObject> list = new ArrayList<JSONObject>();
                    //遍历
                    for (Coin coin : productList) {
                        if (areaname.equals(coin.getFixPriceCoinCode())) {
                            JSONObject data = createData(coin);
                            data.put("usdttormb", usdttormb);
                            data.put("coinSymbol", HtmlUtils.htmlUnescape(coinSymbol));
                            // 获取该交易对最新5分钟数据
                            String points = getLineData(coin.getCoinCode() + "_" + coin.getFixPriceCoinCode());
                            //每个交易对自己的位数
                            ExCointoCoin exCoinToCoin = JSONObject.parseObject(redisService.hget("ex:cointoCoin", coin.getCoinCode() + ":" + coin.getFixPriceCoinCode()), ExCointoCoin.class);
                            if (exCoinToCoin != null) {
                                data.put("keepDecimalCoinCode", exCoinToCoin.getKeepDecimalCoinCode());
                            }
                            data.put("points", points);
                            if (mycoins != null && mycoins.size() > 0) {
                                for (String coinCode : mycoins) {
                                    if (coinCode.equals(coin.getCoinCode() + "_" + coin.getFixPriceCoinCode())) {
                                        data.put("isShoucang", true);
                                    }
                                }
                            }
                            //进行查询
                            if (search != null && !"".equals(search)) {
                                if (data.getString("coinCode").contains(search)) {
                                    list.add(data);
                                }
                            } else {
                                list.add(data);
                            }
                        }
                    }
                    //加入map中
                    jsonArray.addAll(list);
                    //如果排序列不为空，就进行排序
                    if (!"".equals(sortCloum) && sortCloum != null) {
                        jsonArray = SortListUtil.sortJsonArrayTest(jsonArray, sortCloum, flag);
                    }
                }
            }
            return jsonArray;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonArray;
    }

}
