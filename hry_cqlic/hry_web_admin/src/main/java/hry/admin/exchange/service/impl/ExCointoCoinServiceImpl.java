/**
 * Copyright:
 *
 * @author: liushilei
 * @version: V1.0
 * @Date: 2018-06-12 15:52:02
 */
package hry.admin.exchange.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import hry.admin.exchange.ExchangeDataCache;
import hry.admin.exchange.controller.PullThirdNewPriceCallable;
import hry.admin.exchange.dao.ExCointoCoinDao;
import hry.admin.exchange.model.*;
import hry.admin.exchange.service.*;
import hry.admin.web.model.AppConfig;
import hry.admin.web.service.AppConfigService;
import hry.bean.JsonResult;
import hry.core.constant.StringConstant;
import hry.core.mvc.dao.base.BaseDao;
import hry.core.mvc.service.base.impl.BaseServiceImpl;

import javax.annotation.Resource;

import hry.listener.ServerManagement;
import hry.redis.common.utils.RedisService;
import hry.util.QueryFilter;
import hry.util.klinedata.TransactionOrder;
import hry.util.properties.PropertiesUtils;
import hry.util.sys.ContextUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.util.StringUtil;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p> ExCointoCoinService </p>
 * @author: liushilei
 * @Date :          2018-06-12 15:52:02  
 */
@Service("exCointoCoinService")
public class ExCointoCoinServiceImpl extends BaseServiceImpl<ExCointoCoin, Long> implements ExCointoCoinService {
    private static Logger logger = Logger.getLogger(ExCointoCoinServiceImpl.class);

    @Resource(name = "exCointoCoinDao")
    @Override
    public void setDao(BaseDao<ExCointoCoin, Long> dao) {
        super.dao = dao;
    }

    @Resource
    private ExProductService exProductService;

    @Resource
    AppConfigService appConfigService;

    @Resource
    private RedisService redisService;
    @Resource
    private ExRobotLogService exRobotLogService;


    @Resource
    private ExRobotService exRobotService;
    @Resource
    private ExRobotDeepService exRobotDeepService;

    @Override  //初始化交易对到redis
    public void newinitRedisCode() {
        QueryFilter queryFilter = new QueryFilter(ExCointoCoin.class);
        queryFilter.addFilter("state=", 1);
        List<ExCointoCoin> list = super.find(queryFilter);
        list.forEach(exCointoCoin -> {
            redisService.hset("ex:cointoCoin", exCointoCoin.getCoinCode() + ":" + exCointoCoin.getFixPriceCoinCode(), JSONObject.toJSONString(exCointoCoin));
        });
    }

    @Override
    public void initRedisCode() {
        QueryFilter queryFilter = new QueryFilter(ExCointoCoin.class);
        queryFilter.addFilter("state=", 1);
        List<ExCointoCoin> list = super.find(queryFilter);
        //币详细信息list缓存
        //刷新产品列表缓存CoinInfoList
        ArrayList<String> codeList = new ArrayList<String>();
        List<Coin> listCoin = new ArrayList<Coin>();

          List<Coin2> listcoins =new ArrayList<>();
A:        for (ExCointoCoin eExCointoCoin : list) {
            codeList.add(eExCointoCoin.getCoinCode() + "_" + eExCointoCoin.getFixPriceCoinCode());
            //组装coin对象
            JSONObject c = new JSONObject();
            QueryFilter filter = new QueryFilter(ExProduct.class);
            filter.addFilter("coinCode=", eExCointoCoin.getCoinCode());
            ExProduct product = exProductService.get(filter);

            JSONObject c2c = JSON.parseObject(JSON.toJSONString(eExCointoCoin));
B:            for (Map.Entry<String, Object> entry : c2c.entrySet()) {
                c.put(entry.getKey(), entry.getValue());
            }

            JSONObject p = JSON.parseObject(JSON.toJSONString(product));
            if (p == null) {
                logger.error("没有这个币" + eExCointoCoin.getCoinCode());
                continue;
            }

            for (Map.Entry<String, Object> entry : p.entrySet()) {
                c.put(entry.getKey(), entry.getValue());
            }
            c.put("oneTimeOrderNum", eExCointoCoin.getOneTimeOrderNum());
            //如果是虚拟币
            if (eExCointoCoin.getFixPriceType() != null && eExCointoCoin.getFixPriceType() == 1) {
                QueryFilter queryProduct = new QueryFilter(ExProduct.class);
                queryProduct.addFilter("coinCode=", eExCointoCoin.getFixPriceCoinCode());
                ExProduct exProduct = exProductService.get(queryProduct);
                //c.put("keepDecimalForCurrency",exProduct.getKeepDecimalForCoin() );

                c.put("keepDecimalForCurrency", eExCointoCoin.getKeepDecimalFixPrice());
                c.put("keepDecimalForCoin", eExCointoCoin.getKeepDecimalCoinCode());
            } else {//真实货币
                RedisService redisService = (RedisService) ContextUtil.getBean("redisService");
                String config = redisService.get("configCache:all");
                if (!StringUtils.isEmpty(config)) {
                    JSONObject parseObject = JSONObject.parseObject(config);
                    c.put("keepDecimalForCurrency", null == parseObject.get("keepDecimalForRmb") ? "2" : parseObject.get("keepDecimalForRmb"));
                } else {
                    String appConfigv = appConfigService.getBykeyfromDB("keepDecimalForRmb");
                    c.put("keepDecimalForCurrency", null == appConfigv ? "2" : appConfigv);

                }
            }

            Coin coin = JSON.toJavaObject(c, Coin.class);
            listCoin.add(coin);



            // 更新昨日收盘价到cn:coinInfoList中
            String coinStr = redisService.get("cn:coinInfoList2");

            if (!StringUtils.isEmpty(coinStr)) {
                List<Coin2> coins = JSON.parseArray(coinStr, Coin2.class);
                boolean has = false;
C:              for(Coin2 coin2 : coins){
                    if(coin2.getCoinCode().equals(eExCointoCoin.getCoinCode()) && coin2.getFixPriceCoinCode().equals(eExCointoCoin.getFixPriceCoinCode())){
                        has=true;
                        break C;
                    }

                }
                if(!has){
                    Coin2 coin2 = new Coin2();
                    coin2.setYesterdayPrice(eExCointoCoin.getAveragePrice().toString());
                    coin2.setCoinCode(eExCointoCoin.getCoinCode());
                    coin2.setFixPriceCoinCode(eExCointoCoin.getFixPriceCoinCode());
                    coins.add(coin2);
                    redisService.save("cn:coinInfoList2", JSON.toJSONString(coins));
                }
            }else { //如果是空的就初始化

                Coin2 mycoin2 = new Coin2();
                mycoin2.setYesterdayPrice(eExCointoCoin.getAveragePrice().toString());
                mycoin2.setCoinCode(eExCointoCoin.getCoinCode());
                mycoin2.setFixPriceCoinCode(eExCointoCoin.getFixPriceCoinCode());
                listcoins.add(mycoin2);
            }

        }
        if(listcoins.size()>0){
            redisService.save("cn:coinInfoList2", JSON.toJSONString(listcoins));
        }

        //缓存到所有的站点中productListKey值
        Map<String, String> mapLoadWeb = PropertiesUtils.getLoadWeb();
        for (String Website : mapLoadWeb.keySet()) {
            ExchangeDataCache.setStringData(Website + ":productFixList", JSON.toJSONString(codeList));
            ExchangeDataCache.setStringData("cn:coinInfoList", JSON.toJSONString(listCoin));
        }
    }


    @Override
    @Transactional
    public List<ExCointoCoin> getBylist(String toProductcoinCode, String fromProductcoinCode, Integer issueState) {
        QueryFilter filter = new QueryFilter(ExCointoCoin.class);
        if (!StringUtil.isEmpty(toProductcoinCode)) {
            filter.addFilter("coinCode=", toProductcoinCode);
        }
        if (!StringUtil.isEmpty(fromProductcoinCode)) {
            filter.addFilter("fixPriceCoinCode=", fromProductcoinCode);
        }
        if (null != issueState) {
            filter.addFilter("state=", issueState);
        }

        String saasId = PropertiesUtils.APP.getProperty("app.saasId");
        filter.setSaasId(saasId);
        List<ExCointoCoin> list = super.find(filter);
        return list;
    }

    @Override
    public List<ExCointoCoin> findByList() {
        QueryFilter queryFilter = new QueryFilter(ExCointoCoin.class);
        queryFilter.addFilter("state=", 1);
        queryFilter.setOrderby("fixPriceCoinCode asc,coinCode asc");
        List<ExCointoCoin> list = super.find(queryFilter);
        return list;
    }
    @Override
    public void openNewCointoCoin(ExCointoCoin exCointoCoin) {
        RedisService redisService = (RedisService) ContextUtil.getBean("redisService");
        String key = redisService.get(exCointoCoin.getCoinCode() + "_" + exCointoCoin.getFixPriceCoinCode() + ":klinedata:TransactionOrder_" + exCointoCoin.getCoinCode() + "_" + exCointoCoin.getFixPriceCoinCode() + "_1");
        if(StringUtils.isEmpty(key)){
            //拉取第三方价格,拉取有就覆盖redis以及数据库,没有就用开盘价,然后生成K线
            try {
                SimpleDateFormat sim = new SimpleDateFormat("yyyyMMddHHmm");
                SimpleDateFormat sim1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                SimpleDateFormat sim2 = new SimpleDateFormat("yyyy-MM-dd");

                Date date = new Date();

                List<TransactionOrder> list = new ArrayList<TransactionOrder>();
                List<LastKLinePayload> listData = new ArrayList<LastKLinePayload>();

                String keyData = exCointoCoin.getCoinCode() + "_" + exCointoCoin.getFixPriceCoinCode() + ":PeriodLastKLineList";

                PullThirdNewPriceCallable pull = new PullThirdNewPriceCallable(exCointoCoin.getCoinCode(), exCointoCoin.getFixPriceCoinCode());
                JsonResult call = pull.call();

                int minute = Integer.valueOf(sim1.format(date).split(":")[1]); // 18:12 就得到12

                if(call.getSuccess()){
                    //拉取第三方的最新价格
                    BigDecimal nowPrice = (BigDecimal) call.getObj();

                    //修改数据库开盘价
                    exCointoCoin.setYesterdayPrice(nowPrice.toString());
                    update(exCointoCoin);

                    //修改redis开盘价
                    updateCoinInfoList2(exCointoCoin);

                    //修改new值 例如 BCH_BTC:PeriodLastKLineList
                    for(int i=0; i<8; i++){
                        LastKLinePayload l = new LastKLinePayload();

                        common(i, date, sim, sim1, sim2, l, null, nowPrice, list, listData, redisService, key, keyData, minute);

                        l.setId(date.getTime() / 1000);
                        l.setPriceHigh(nowPrice);
                        l.setPriceLast(nowPrice);
                        l.setPriceLow(nowPrice);
                        l.setPriceOpen(nowPrice);
                        l.setStartTime(sim1.format(date));
                        l.setTime(date.getTime() / 1000);
                        l.setVolume(new BigDecimal("0"));
                        l.setAmount(new BigDecimal("0"));
                        l.setCount(new BigDecimal("0"));
                        l.setDayTotalDealAmount(new BigDecimal("0"));

                        listData.add(l);
                    }
                    redisService.save(keyData, JSONArray.toJSONString(listData));

                }else{
                    //修改new值 例如 BCH_BTC:PeriodLastKLineList
                    for(int i=0; i<8; i++){
                        LastKLinePayload l = new LastKLinePayload();

                        common(i, date, sim, sim1, sim2, l, exCointoCoin, new BigDecimal(0), list, listData, redisService, key, keyData, minute);

                        l.setId(date.getTime() / 1000);
                        l.setPriceHigh(exCointoCoin.getAveragePrice());
                        l.setPriceLast(exCointoCoin.getAveragePrice());
                        l.setPriceLow(exCointoCoin.getAveragePrice());
                        l.setPriceOpen(exCointoCoin.getAveragePrice());
                        l.setStartTime(sim1.format(date));
                        l.setTime(date.getTime() / 1000);
                        l.setVolume(new BigDecimal("0"));
                        l.setCount(new BigDecimal("0"));
                        l.setAmount(new BigDecimal("0"));
                        l.setDayTotalDealAmount(new BigDecimal("0"));

                        listData.add(l);
                    }
                    redisService.save(keyData, JSONArray.toJSONString(listData));
                }
            } catch (Exception e) {
                try{
                    String keyData = exCointoCoin.getCoinCode() + "_" + exCointoCoin.getFixPriceCoinCode() + ":PeriodLastKLineList";

                    SimpleDateFormat sim = new SimpleDateFormat("yyyyMMddHHmm");
                    SimpleDateFormat sim1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                    SimpleDateFormat sim2 = new SimpleDateFormat("yyyy-MM-dd");
                    Date date = new Date();

                    List<TransactionOrder> list = new ArrayList<TransactionOrder>();
                    List<LastKLinePayload> listData = new ArrayList<LastKLinePayload>();

                    int minute = Integer.valueOf(sim1.format(date).split(":")[1]); // 18:12 就得到12

                    //修改new值 例如 BCH_BTC:PeriodLastKLineList
                    for(int i=0; i<8; i++){
                        LastKLinePayload l = new LastKLinePayload();

                        common(i, date, sim, sim1, sim2, l, exCointoCoin, new BigDecimal(0), list, listData, redisService, key, keyData, minute);

                        l.setId(date.getTime() / 1000);
                        l.setPriceHigh(exCointoCoin.getAveragePrice());
                        l.setPriceLast(exCointoCoin.getAveragePrice());
                        l.setPriceLow(exCointoCoin.getAveragePrice());
                        l.setPriceOpen(exCointoCoin.getAveragePrice());
                        l.setStartTime(sim1.format(date));
                        l.setTime(date.getTime() / 1000);
                        l.setVolume(new BigDecimal("0"));
                        l.setCount(new BigDecimal("0"));
                        l.setAmount(new BigDecimal("0"));
                        l.setDayTotalDealAmount(new BigDecimal("0"));

                        listData.add(l);

                    }
                    redisService.save(keyData, JSONArray.toJSONString(listData));
                }catch(Exception e1){

                }
            }
        }


        //缓存发行价，即昨日收盘价
        String coinsKey  = "cn:coinInfoList2";
        String conStr = ExchangeDataCache.getStringData(coinsKey);
        if(!StringUtils.isEmpty(conStr)){
            String code = exCointoCoin.getCoinCode()+exCointoCoin.getFixPriceCoinCode();
            List<Coin2> list = JSONObject.parseArray(conStr, Coin2.class);
            boolean flag = false;
            for(Coin2 c : list){
                if(code.equals(c.getCoinCode()+c.getFixPriceCoinCode())){
                    c.setYesterdayPrice(exCointoCoin.getAveragePrice().toString());
                    flag = true;
                }
            }
            if(!flag){
                Coin2 coin2 = new Coin2();
                coin2.setCoinCode(exCointoCoin.getCoinCode());
                coin2.setFixPriceCoinCode(exCointoCoin.getFixPriceCoinCode());
                coin2.setYesterdayPrice(exCointoCoin.getAveragePrice().toString());
                list.add(coin2);
            }
            ExchangeDataCache.setStringData(coinsKey, JSON.toJSONString(list));
        }else{
            List<Coin2>  list = new ArrayList<Coin2>();

            Coin2 coin2 = new Coin2();
            coin2.setCoinCode(exCointoCoin.getCoinCode());
            coin2.setFixPriceCoinCode(exCointoCoin.getFixPriceCoinCode());
            coin2.setYesterdayPrice(exCointoCoin.getAveragePrice().toString());

            list.add(coin2);
            ExchangeDataCache.setStringData(coinsKey, JSON.toJSONString(list));
        }
    }

    @Override
    public void closeCointoCoin(ExCointoCoin exCointoCoin) {
        //关闭深度机器人
        QueryFilter queryFilter = new QueryFilter(ExRobotDeep.class);
        queryFilter.addFilter("coinCode=",exCointoCoin.getCoinCode());
        queryFilter.addFilter("fixPriceCoinCode=",exCointoCoin.getFixPriceCoinCode());
        ExRobotDeep exdeepRobot = exRobotDeepService.get(queryFilter);
        if(exdeepRobot!=null){
            exdeepRobot.setIsSratAuto(0);
            exRobotDeepService.update(exdeepRobot);
        }
        //关闭k线机器人

        QueryFilter queryFilter1 = new QueryFilter(ExRobot.class);
        queryFilter1.addFilter("coinCode=",exCointoCoin.getCoinCode());
        queryFilter1.addFilter("fixPriceCoinCode=",exCointoCoin.getFixPriceCoinCode());
        ExRobot exRobot = exRobotService.get(queryFilter);
        if(exdeepRobot!=null){
            exRobot.setIsSratAuto(0);
            exRobotService.update(exRobot);
        }

        //保存机器人日志
        saveRobotLog(exCointoCoin, exdeepRobot);
    }

    @Override
    public List<ExCointoCoin> findCoinCodes() {
        return ((ExCointoCoinDao)dao).findCoinCodes();
    }

    private void saveRobotLog(ExCointoCoin exCointoCoin, ExRobotDeep exdeepRobot) {
        ExRobotLog exRobotLog = new ExRobotLog();
        exRobotLog.setCloseTime(new Date());
        exRobotLog.setCoinCode(exCointoCoin.getCoinCode());
        exRobotLog.setRemark("关闭交易对");
        exRobotLog.setFixCoin(exCointoCoin.getFixPriceCoinCode());
        exRobotLog.setPhone(exdeepRobot.getMobilePhone());
        exRobotLog.setEmail(exdeepRobot.getEmail());
        exRobotLogService.save(exRobotLog);
    }

    /**
     * 修改new值 例如 BCH_BTC:PeriodLastKLineList
     * @param i
     * @param date
     * @param sim
     * @param sim1
     * @param sim2
     * @param l
     * @param exCointoCoin
     * @param nowPrice
     * @param list
     * @param listData
     * @param redisService
     * @param key
     * @param keyData
     * @param minute
     * @throws ParseException
     */
    public void common (int i, Date date, SimpleDateFormat sim, SimpleDateFormat sim1, SimpleDateFormat sim2, LastKLinePayload l, ExCointoCoin exCointoCoin, BigDecimal nowPrice,
                        List<TransactionOrder> list, List<LastKLinePayload> listData, RedisService redisService, String key, String keyData, int minute) throws ParseException {
        if(i==0){
            l.setPeriod("1min");

            long time = (date.getTime() / 1000 + 60) * 1000;
            l.setStartTime(sim1.format(date));
            l.setEndTime(sim1.format(time));

            TransactionOrder t = new TransactionOrder();

            if(nowPrice.compareTo(new BigDecimal(0)) == 0){
                //生成1分钟K线第一个节点
                t.setStartPrice(exCointoCoin.getAveragePrice());
                t.setEndPrice(exCointoCoin.getAveragePrice());
                t.setMaxPrice(exCointoCoin.getAveragePrice());
                t.setMinPrice(exCointoCoin.getAveragePrice());
                t.setId(sim.format(date));
                t.setTransactionCount(new BigDecimal(0));
                t.setTransactionTime(sim1.format(date));
                t.setTransactionEndTime(sim1.format(time));
            }else{
                //生成1分钟K线第一个节点
                t.setStartPrice(nowPrice);
                t.setEndPrice(nowPrice);
                t.setMaxPrice(nowPrice);
                t.setMinPrice(nowPrice);
                t.setId(sim.format(date));
                t.setTransactionCount(new BigDecimal(0));
                t.setTransactionTime(sim1.format(date));
                t.setTransactionEndTime(sim1.format(time));
            }
            list.add(t);

            redisService.save(key, JSONArray.toJSONString(list));
        }else if(i==1){
            l.setPeriod("5min");

            int minuteNew = minute - minute % 5; // 得到10=12-12%2
            String startMin5 = sim1.format(date).split(":")[0] + ":" + minuteNew;
            long time = (sim1.parse(startMin5).getTime() / 1000 + 60 * 5) * 1000;
            l.setStartTime(startMin5);
            l.setEndTime(sim1.format(time));
        }else if(i==2){
            l.setPeriod("15min");

            int j = minute - minute % 15;
            String m15 = j == 0 ? m15 = "00" : j + "";
            String startMin15 = sim1.format(date).split(":")[0] + ":" + m15;
            long time = (sim1.parse(startMin15).getTime() / 1000 + 60 * 15) * 1000;
            l.setStartTime(startMin15);
            l.setEndTime(sim1.format(time));
        }else if(i==3){
            l.setPeriod("30min");

            int j = minute - minute % 30;
            String m30 = j == 0 ? m30 = "00" : j + "";
            String startMin30 = sim1.format(date).split(":")[0] + ":" + m30;
            long time = (sim1.parse(startMin30).getTime() / 1000 + 60 * 30) * 1000;
            l.setStartTime(startMin30);
            l.setEndTime(sim1.format(time));
        }else if(i==4){
            l.setPeriod("60min");

            int j = minute - minute % 60;
            String m60 = j == 0 ? m60 = "00" : j + "";
            String startMin60 = sim1.format(date).split(":")[0] + ":" + m60;
            long time = (sim1.parse(startMin60).getTime() / 1000 + 60 * 60) * 1000;
            l.setStartTime(startMin60);
            l.setEndTime(sim1.format(time));
        }else if(i==5){
            l.setPeriod("1day");

            String dayStartTime = sim2.format(date) + " 00:00";

            long time = (sim1.parse(dayStartTime).getTime() / 1000 + 60 * 60 * 24) * 1000;

            l.setStartTime(dayStartTime);
            l.setEndTime(sim1.format(time));
        }else if(i==6){
            l.setPeriod("1week");

            String dayStartTime = sim2.format(date) + " 00:00";
            Date d = sim1.parse(dayStartTime);

            String start = sim1.format(DateUtil.getFirstDayOfWeek(d));
            String end = sim1.format(DateUtil.getLastDayOfWeek(d));

            l.setStartTime(start);
            l.setEndTime(end);
        }else if(i==7){
            l.setPeriod("1mon");

            //开始时间
            String start = sim2.format(date) + " 00:00";
            String[] str = start.split("-");
            start = str[0] + "-" + str[1] + "-01 00:00";

            //结束时间
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.MONTH, 1);
            Date date1 = cal.getTime();
            String format = sim2.format(date1);
            String[] split = format.split("-");
            String end = split[0] + "-" + split[1] + "-01 00:00";

            l.setStartTime(start);
            l.setEndTime(end);
        }
    }
    /**
     * 修改redis cn:coinInfoList2值
     */
    public void updateCoinInfoList2(ExCointoCoin exCointoCoin){
        String coinsKey  = "cn:coinInfoList2";
        String conStr = ExchangeDataCache.getStringData(coinsKey);
        if(!StringUtils.isEmpty(conStr)){
            String code = exCointoCoin.getCoinCode()+exCointoCoin.getFixPriceCoinCode();
            List<Coin2> list = JSONObject.parseArray(conStr, Coin2.class);
            boolean flag = false;
            for(Coin2 c : list){
                if(code.equals(c.getCoinCode()+c.getFixPriceCoinCode())){
                    c.setYesterdayPrice(exCointoCoin.getYesterdayPrice());
                    flag = true;
                }
            }
            if(!flag){
                Coin2 coin2 = new Coin2();
                coin2.setCoinCode(exCointoCoin.getCoinCode());
                coin2.setFixPriceCoinCode(exCointoCoin.getFixPriceCoinCode());
                coin2.setYesterdayPrice(exCointoCoin.getYesterdayPrice());
                list.add(coin2);
            }
            ExchangeDataCache.setStringData(coinsKey, JSON.toJSONString(list));
        }else{
            List<Coin2>  list = new ArrayList<Coin2>();

            Coin2 coin2 = new Coin2();
            coin2.setCoinCode(exCointoCoin.getCoinCode());
            coin2.setFixPriceCoinCode(exCointoCoin.getFixPriceCoinCode());
            coin2.setYesterdayPrice(exCointoCoin.getYesterdayPrice());

            list.add(coin2);
            ExchangeDataCache.setStringData(coinsKey, JSON.toJSONString(list));
        }
    }
}
