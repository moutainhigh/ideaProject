package hry.app.user.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import hry.app.jwt.TokenUtil;
import hry.app.kline.model.ExCointoCoin;
import hry.bean.ApiJsonResult;
import hry.calculate.remote.mining.RemoteExMiningRewardTimerService;
import hry.manage.remote.model.User;
import hry.redis.common.utils.RedisService;
import hry.reward.model.ExMining;
import hry.reward.model.ExMiningBonus;
import hry.reward.model.page.FrontPage;
import hry.trade.redis.model.ExchangeDataCacheRedis;
import hry.util.HttpServletRequestUtils;
import hry.util.common.SpringUtil;
import hry.util.date.DateUtil;
import io.swagger.annotations.*;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/miningAndBouns")
@Api(value = "挖矿分红", description = "挖矿分红", tags = "挖矿分红")
public class MiningAndBounsController {

    @Resource
    private RedisService redisService;

    @Resource
    private RemoteExMiningRewardTimerService remoteExMiningRewardTimerService;

    /**
     * @Function: MiningController.java
     * @Description: 进入挖矿分红详情页
     * @author: zjj
     * @date: 2018年8月20日 上午11:18:05
     */
    @ApiOperation(value = "进入挖矿分红详情页", httpMethod = "POST", notes = "进入挖矿分红详情页")
    @PostMapping("/tominingAndBonusDetail")
    @ResponseBody
    public Map<String, Object> tominingAndBonusDetail (HttpServletRequest request) {
        Map<String, Object> returnMap = new HashMap<>();

        //查看昨日分红详情
        String yesterDayBonusInfo = redisService.get("Mining:yesterDayBonusInfo");
        List<ExMiningBonus> findBonusDetail = JSONObject.parseArray(yesterDayBonusInfo, ExMiningBonus.class);
        returnMap.put("yesterdayList", findBonusDetail);

        //查看今日分红详情
        String todayBonusInfo = redisService.get("Mining:todayBonusInfo");
        List<ExMiningBonus> bonusDetail = JSONObject.parseArray(todayBonusInfo, ExMiningBonus.class);
        returnMap.put("nowdayList", bonusDetail);

        String config = redisService.get("configCache:all");
        if (!StringUtils.isEmpty(config)) {
            JSONObject parseObject = JSONObject.parseObject(config);
            // 获取平台币
            String platCoin = parseObject.getString("platCoin");
            returnMap.put("platCoin", platCoin);

            // 挖矿分红详情页展示流通量数据
            BigDecimal exchangPrice = new BigDecimal(1);
            BigDecimal CostNum = new BigDecimal(0);
            String compromiseCoin = parseObject.getString("compromiseCoin");//折合币种
            String platformCost = parseObject.getString("platformCost");//风险储备金支出

            if (!"USDT".equals(compromiseCoin)) {
                exchangPrice = getCurrentExchangPrice(compromiseCoin, "USDT");//查询每个币种折合USDT的价格
            }

            String nowDay = redisService.get("Mining:nowDay") == null ? "0" : redisService.get("Mining:nowDay");
            String nowHour = redisService.get("Mining:nowHour") == null ? "0" : redisService.get("Mining:nowHour");
            String yesterDayNum = redisService.get("Mining:yesterDay");
            String totalCirculationNum = redisService.get("Mining:totalCirculation");
            String yesterMiningNum = redisService.get("Mining:yesterMining");
            String reserveNum = redisService.get("Mining:reserveNum") == null ? "0" : redisService.get("Mining:reserveNum");
            String backDestoryNum = redisService.get("Mining:backDestoryNum") == null ? "0" : redisService.get("Mining:backDestoryNum");

            if (!StringUtils.isEmpty(exchangPrice)) {
                nowDay = new BigDecimal(nowDay).divide(exchangPrice, 6, BigDecimal.ROUND_DOWN).toString();
                nowHour = new BigDecimal(nowHour).divide(exchangPrice, 6, BigDecimal.ROUND_DOWN).toString();
                reserveNum = new BigDecimal(reserveNum).divide(exchangPrice, 6, BigDecimal.ROUND_DOWN).toString();
                if (!StringUtils.isEmpty(yesterDayNum)) {
                    yesterDayNum = new BigDecimal(yesterDayNum).divide(exchangPrice, 6, BigDecimal.ROUND_DOWN).toString();
                }
            }

            if (!StringUtils.isEmpty(platformCost)) {
                BigDecimal compromiseCoinPrice = getCurrentExchangPrice(compromiseCoin, "USDT");//查询每个币种折合USDT的价格
                if (compromiseCoinPrice != null) {
                    CostNum = compromiseCoinPrice.multiply(new BigDecimal(platformCost));
                    reserveNum = new BigDecimal(reserveNum).subtract(new BigDecimal(platformCost)).toString();
                    BigDecimal price = getCurrentExchangPrice(platCoin, "USDT");
                    if (StringUtils.isEmpty(price)) {//平台币定价
                        String coinToCoin = redisService.hget("ex:cointoCoin", platCoin + ":" + compromiseCoin);
                        if (!StringUtils.isEmpty(coinToCoin)) {
                            ExCointoCoin exCointoCoin = JSONObject.parseObject(coinToCoin, ExCointoCoin.class);
                            price = exCointoCoin.getAveragePrice();
                        } else {//防止没有那个币种交易对而报错
                            price = new BigDecimal(1);
                        }
                    }
                    CostNum = CostNum.divide(price, 6, BigDecimal.ROUND_DOWN);
                    backDestoryNum = new BigDecimal(backDestoryNum).add(CostNum).toString();
                }
            }

            returnMap.put("totalCirculationNum", totalCirculationNum == null ? "0" : totalCirculationNum);
            returnMap.put("yesterMiningNum", yesterMiningNum == null ? "0" : yesterMiningNum);
            returnMap.put("yesterDayNum", yesterDayNum == null ? "0" : yesterDayNum);
            returnMap.put("compromiseCoin", compromiseCoin);
            returnMap.put("nowDay", nowDay == null ? "0" : nowDay);
            returnMap.put("nowHour", nowHour == null ? "0" : nowHour);
            returnMap.put("backDestoryNum", backDestoryNum == null ? "0" : backDestoryNum);
            returnMap.put("reserveNum", reserveNum == null ? "0" : reserveNum);
        }
        return returnMap;
    }

    /**
     * @Function: MiningController.java
     * @Description:
     * @author: zjj
     * @date: 2018年8月9日 上午11:43:12
     */
    private BigDecimal getCurrentExchangPrice (String coinCode, String fixPriceCoinCode) {
        String header = coinCode + "_" + fixPriceCoinCode;
        String key = header + ":" + ExchangeDataCacheRedis.CurrentExchangPrice;
        String v = redisService.get(key);
        if (StringUtils.isEmpty(v) || new BigDecimal(v).compareTo(new BigDecimal("0")) == 0) {
            return null;
        } else {
            return new BigDecimal(v);
        }
    }

    /**
     * 个人中心-我的挖矿记录
     * @return
     */
    @ApiOperation(value = "个人中心-我的挖矿记录", httpMethod = "POST", notes = "个人中心-我的挖矿记录")
    @PostMapping("/user/miningList")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
    @ResponseBody
    @RequiresAuthentication
    public FrontPage miningList(
            @ApiParam(name = "limit", value = "每页条数", required = true) @RequestParam("limit") String limit,
            @ApiParam(name = "offset", value = "(页码-1)*每页条数,如第1页，每次10条侧传(1-1)*10=0", required = true) @RequestParam("offset") String offset,
            @ApiParam(name = "langCode", value = "语种", required = true) @RequestParam("langCode") String langCode,
            @ApiParam(name = "status", value = "状态，直接写1", required = true) @RequestParam("status") String status,
            @ApiParam(name = "dealStartTime_GT", value = "查询时间", required = true) @RequestParam("dealStartTime_GT") String dealStartTime_GT,
            HttpServletRequest request) {
        User user = TokenUtil.getUser(request);
        Map<String, String> params = HttpServletRequestUtils.getParams(request);
        params.put("customerId", user.getCustomerId().toString());
        FrontPage page = remoteExMiningRewardTimerService.findMyMiningList(params);
        List<ExMining> list = page.getRows();
        for (int i = 0; i < list.size(); i++) {
            ExMining exming = list.get(i);
            exming.setCreated_long(exming.getTransactionTime().getTime());
            if (!StringUtils.isEmpty(exming.getModified())) {
                exming.setModified_long(exming.getModified().getTime());
            }
        }
        return page;
    }

    /**
     * 个人中心-我的分红记录
     * @param request
     * @return
     */
    @ApiOperation(value = "个人中心-我的分红记录", httpMethod = "POST", notes = "个人中心-我的分红记录")
    @PostMapping("/user/bonusList")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token",required = true,dataType = "String",paramType = "header"),
    })
    @ResponseBody
    @RequiresAuthentication
    public FrontPage bonusList(
            @ApiParam(name = "limit", value = "每页条数", required = true) @RequestParam("limit") String limit,
            @ApiParam(name = "offset", value = "(页码-1)*每页条数,如第1页，每次10条侧传(1-1)*10=0", required = true) @RequestParam("offset") String offset,
            @ApiParam(name = "langCode", value = "语种", required = true) @RequestParam("langCode") String langCode,
            @ApiParam(name = "coinCode", value = "币种", required = true) @RequestParam("coinCode") String coinCode,
            @ApiParam(name = "bonusStartTime_GT", value = "查询时间", required = true) @RequestParam("bonusStartTime_GT") String bonusStartTime_GT,
            HttpServletRequest request) {
        User user = TokenUtil.getUser(request);
        Map<String, String> params = HttpServletRequestUtils.getParams(request);
        params.put("customerId", user.getCustomerId().toString());
        FrontPage page = remoteExMiningRewardTimerService.findMyBonusList(params);
        List<ExMiningBonus> list = page.getRows();
        for(int i=0;i<list.size();i++){
            ExMiningBonus exming = list.get(i);
            exming.setBonusNumber(exming.getBonusNumber().setScale(8,BigDecimal.ROUND_DOWN));
            if (!StringUtils.isEmpty(bonusStartTime_GT)) {
                exming.setCreated(DateUtil.stringToDate(bonusStartTime_GT));
            }
        }
        return page;
    }

    /**
     * @Function: MiningController.java
     * @Description: 个人中心-查询币种
     * @author: zjj
     * @date: 2018年8月9日 上午11:18:05
     */
    @ApiOperation(value = "个人中心-查询币种", httpMethod = "POST", notes = "个人中心-查询币种")
    @PostMapping("/findCoinCode")
    @ResponseBody
    public ApiJsonResult findCoinCode(
            @ApiParam(name = "langCode", value = "语种", required = true) @RequestParam("langCode") String langCode,
            HttpServletRequest request) {
        ApiJsonResult jsonResult = new ApiJsonResult();
        try {
            String string = redisService.get("cn:productList");
            List<String> productList = JSON.parseArray(string, String.class);
            return jsonResult.setSuccess(true).setObj(productList);
        } catch (Exception e) {
            e.printStackTrace();
            return jsonResult.setMsg(SpringUtil.diff("yichang"));
        }
    }

    /**
     * @Function: MiningController.java
     * @Description: 初始化本小时待分红详情
     * @author: zjj
     * @date: 2018年8月9日 上午11:18:05
     */
    @ApiOperation(value = "初始化本小时待分红详情", httpMethod = "POST", notes = "初始化本小时待分红详情")
    @PostMapping("/initNowhourData")
    @ResponseBody
    public Map<String, Object> initNowhourData(HttpServletRequest request) {
        Map<String, Object> returnMap = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH");
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("dealStartTime_GT", sdf.format(new Date())+":00:00");
        List<ExMiningBonus> bonusDetail = remoteExMiningRewardTimerService.findBonusDetail(map);
        returnMap.put("list",bonusDetail);
        return returnMap;
    }

    /**
     * @Function: MiningController.java
     * @Description: 查看今天待分红详情
     * @author: zjj
     * @date: 2018年8月9日 上午11:18:05
     */
    @ApiOperation(value = "进入挖矿分红详情页", httpMethod = "POST", notes = "进入挖矿分红详情页")
    @RequestMapping("/initNowdayData")
    @ResponseBody
    public Map<String, Object> initNowdayData(HttpServletRequest request) {
        Map<String, Object> returnMap = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("dealStartTime_GT", sdf.format(new Date())+" 00:00:00");
        List<ExMiningBonus> bonusDetail = remoteExMiningRewardTimerService.findBonusDetail(map);
        returnMap.put("list",bonusDetail);
        return returnMap;
    }
}
