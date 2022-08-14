package hry.listener;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import hry.admin.customer.service.AppCustomerService;
import hry.admin.exchange.model.Coin2;
import hry.admin.exchange.service.ExDmTransactionService;
import hry.admin.exchange.service.ExOrderInfoService;
import hry.admin.index.model.AppYesterdayData;
import hry.admin.index.service.AppYesterdayDataService;
import hry.redis.common.utils.RedisService;
import hry.util.SpringUtil;
import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.List;

public class HomeManagement {
    private static Logger logger = Logger.getLogger(HomeManagement.class);

    public void getYesterdayData(){
        logger.info("定时器触发开始。。。。");
        RedisService redisService = SpringUtil.getBean("redisService");
        AppYesterdayDataService yesterdayDataService = SpringUtil.getBean("appYesterdayDataService");
        List<AppYesterdayData> yesterdayDataList = yesterdayDataService.findAll();
        AppYesterdayData yesterdayData = null;
        if (yesterdayDataList != null && yesterdayDataList.size() > 0) {
            yesterdayData = yesterdayDataList.get(0);
        } else {
            yesterdayData = new AppYesterdayData();
        }

        // 昨日新增用户
        AppCustomerService appCustomerService = SpringUtil.getBean("appCustomerService");
        String customers = appCustomerService.getYesterdayCoutomers();
        if (customers != null) {
            yesterdayData.setNewCustomer(new BigDecimal(customers));
            redisService.save("HomePage:new_customer", customers);
        }

        // 昨日新增交易量
        ExOrderInfoService exOrderInfoService = SpringUtil.getBean("exOrderInfoService");
        BigDecimal trades = exOrderInfoService.getYesterdayTreads("yesterday");
        if (trades != null) {
            yesterdayData.setNewTrades(trades);
            redisService.save("HomePage:new_trade", trades.toString());
        }

        // 截止昨日交易量
        // 获取之前的交易量
        String historyData = redisService.get("HomePage:yesterday_trade");
        if (!StringUtils.isEmpty(historyData)) {
            BigDecimal his = new BigDecimal(historyData);
            his = his.add(trades);
            yesterdayData.setTotalTradeMoney(his);
            redisService.save("HomePage:yesterday_trade", his.setScale(0).toString());
        }

        // 昨日充币数
        ExDmTransactionService exDmTransactionService = SpringUtil.getBean("exDmTransactionService");
        BigDecimal post = exDmTransactionService.getYesterdayPostOrGet("post");
        if (post != null) {
            yesterdayData.setNewPost(post);
            redisService.save("HomePage:new_postCoin", post.toString());
        }

        // 昨日提币数
        BigDecimal get = exDmTransactionService.getYesterdayPostOrGet("get");
        if (get != null) {
            yesterdayData.setNewGet(get);
            redisService.save("HomePage:new_getCoin", get.toString());
        }

        // 昨日挖矿数
        String yesterMiningNum = redisService.get("Mining:yesterMining");
        if (!StringUtils.isEmpty(yesterMiningNum)) {
            yesterdayData.setNewMining(new BigDecimal(yesterMiningNum));
            redisService.save("HomePage:new_mining", yesterMiningNum);
        } else {
            redisService.save("HomePage:new_mining", "0");
        }

        // 昨日分红数
        BigDecimal exchangPrice = new BigDecimal(1);
        String config = redisService.get("configCache:all");
        if (!StringUtils.isEmpty(config)) {
            JSONObject parseObject = JSONObject.parseObject(config);
            String compromiseCoin = (String) parseObject.get("compromiseCoin");//折合币种
            String yesterDayNum = redisService.get("Mining:yesterDay");
            BigDecimal yesterdayNum = new BigDecimal(0);
            if(!"USDT".equals(compromiseCoin)){
                exchangPrice = getCurrentExchangPrice(compromiseCoin,"USDT");//查询每个币种折合USDT的价格
            }
            if(!StringUtils.isEmpty(yesterDayNum)){
                yesterdayNum = new BigDecimal(yesterDayNum).multiply(exchangPrice).setScale(0, BigDecimal.ROUND_HALF_UP);
            }
            yesterdayData.setNewDividends(yesterdayNum);
            redisService.save("HomePage:new_dividend", yesterdayNum.toString());
        }

        // 将统计数据保存到数据库中
        if (yesterdayData.getId() == null) {
            yesterdayDataService.save(yesterdayData);
        } else {
            yesterdayDataService.update(yesterdayData);
        }


    }

    /**
     * @Function: MiningController.java
     * @Description: 去对应币种与usdt的昨日收盘价格进行兑换
     * @author: zjj
     * @date: 2018年8月9日 上午11:43:12
     */
    private BigDecimal getCurrentExchangPrice(String coincode, String fixPriceCoinCode) {
        RedisService redisService = SpringUtil.getBean("redisService");
        String coinStr = redisService.get("cn:coinInfoList2");
        String coinCode = coincode + "_" + fixPriceCoinCode;
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
        return yesterdayPrice;
    }

    /**
     * 不足用0在左边补位
     * @param str
     * @param strLength
     * @return
     */
    private String addZeroForNum (String str, int strLength) {
        int strLen = str.length();
        StringBuffer sb = null;
        while (strLen < strLength) {
            sb = new StringBuffer();
            sb.append("0").append(str);// 左补0
            str = sb.toString();
            strLen = str.length();
        }
        return str;
    }
}
