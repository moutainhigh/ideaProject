package hry.app.lend.websocket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import hry.app.lend.model.LendRedis;
import hry.app.lend.websocket.model.AccountData;
import hry.app.lend.websocket.model.HallData;
import hry.app.lend.websocket.model.MessageEntrust;
import hry.app.lend.websocket.util.HallSessionUtil;
import hry.app.lend.websocket.util.ThreadPoolPushData;
import hry.lend.constant.DealConstant;
import hry.lend.constant.ExchangeLendKey;
import hry.lend.constant.LendConfig;
import hry.lend.model.kline.KlineData;
import hry.lend.model.kline.KlineTimes;
import hry.lend.model.trade.ExLendConfig;
import hry.lend.model.trade.LendEntrust;
import hry.lend.remote.RemoteLendConfigService;
import hry.lend.remote.RemoteLendMoneyService;
import hry.lend.util.BeanUtil;
import hry.lend.util.LendUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.*;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import javax.annotation.Resource;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * @author <a href="mailto:HelloHeSir@gmail.com">Mr_He</a>
 * @createTime 2018/8/7 14:44
 * @Description: 交易大厅数据推送处理器
 * uid
 * coinCode     is not blank
 * marginToken  is not blank
 * 1 后期数据分离计算
 * 2 推送频率控制
 */
@Service
public class TradingHallSocketHandler implements WebSocketHandler {
    private static final Logger log = LoggerFactory.getLogger(TradingHallSocketHandler.class);

    /**
     * 交易对<marginToken,session>
     */
    private static Map<String, Map<String, WebSocketSession>> sessionMap = new ConcurrentHashMap<>();

    /**
     * 左侧交易区以及数据
     */
    private static Map<String, List<HallData.LeftCoinData>> leftCoin = new HashMap<>();
    private static List<String> leftCoinTab = new LinkedList<>();

    @Resource
    private RemoteLendMoneyService remoteLendMoneyService;

    @Resource
    private RemoteLendConfigService remoteLendConfigService;

    /**
     * 建立连接后
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String coinCode = String.valueOf(session.getAttributes().get("coinCode"));
        String marginToken = String.valueOf(session.getAttributes().get("marginToken"));
        setSession(session, coinCode, marginToken);
        session.getAttributes().put("messageEntrust", new MessageEntrust(3, 3, coinCode.split("_")[1]));

        pushOneUserData(session);

        soutCurrentPersonCount();
    }

    /**
     * 打印当前在线人数
     */
    private void soutCurrentPersonCount() {
        ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(1, new BasicThreadFactory.Builder().namingPattern("socket-connect-after-%d").build());
        executorService.execute(() -> {
            int size = sessionMap.size();
            int count = 0;
            for (String key : sessionMap.keySet()) {
                int uCount = sessionMap.get(key).size();
                if (uCount == 0) {
                    sessionMap.remove(key);
                } else {
                    count += uCount;
                }
            }
            log.info("[TradingHall]当前在线人数[{}]  推送交易对数量[{}]", count, size);
        });
    }

    /**
     * 接受消息
     */
    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        if (message.getPayloadLength() == 0) {
            return;
        }
        Map<String, Object> attributes = session.getAttributes();

        MessageEntrust newMessageEntrust = JSON.parseObject(message.getPayload().toString(), MessageEntrust.class);
        MessageEntrust oldMessageEntrust = (MessageEntrust) attributes.get("messageEntrust");
        BeanUtil.copyPropertiesIgnoreNull(newMessageEntrust, oldMessageEntrust);
        attributes.put("messageEntrust", oldMessageEntrust);

        if (StringUtils.isNotEmpty(newMessageEntrust.getCoinCode())) {
            String oldCoinCode = String.valueOf(attributes.get("coinCode"));
            String newCoinCode = newMessageEntrust.getCoinCode();
            if (!oldCoinCode.equals(newCoinCode)) {
                attributes.put("coinCode", newCoinCode);
                String marginToken = String.valueOf(attributes.get("marginToken"));
                sessionMap.get(oldCoinCode).remove(marginToken);

                setSession(session, newCoinCode, marginToken);
            }
        }
        pushOneUserData(session);
    }

    private void setSession(WebSocketSession session, String coinCode, String marginToken) {
        if (sessionMap.containsKey(coinCode)) {
            sessionMap.get(coinCode).put(marginToken, session);
        } else {
            Map<String, WebSocketSession> map = new ConcurrentHashMap<>();
            map.put(marginToken, session);
            sessionMap.put(coinCode, map);
        }
    }

    /**
     * 消息传输错误处理
     */
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        System.out.println("传输错误=========");
//        session.close();
    }

    /**
     * 关闭连接后
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        String coinCode = String.valueOf(session.getAttributes().get("coinCode"));
        String marginToken = String.valueOf(session.getAttributes().get("marginToken"));
        Map<String, WebSocketSession> sessionMap = TradingHallSocketHandler.sessionMap.get(coinCode);
        if (null != sessionMap) {
            sessionMap.remove(marginToken);
        }
        //清redis session
        try (Jedis jedis = LendRedis.JEDIS_POOL.getResource()) {
            HallSessionUtil.delSession(jedis, marginToken);
        } catch (Exception e) {
            e.printStackTrace();
        }
        soutCurrentPersonCount();

    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    /**
     * 实时数据 高频推送
     */
    public void sendMessageToUserAll() {
        for (final Map.Entry<String, Map<String, WebSocketSession>> entry : sessionMap.entrySet()) {
            if (entry.getValue().size() > 0) {
                ThreadPoolPushData.exe(() -> {
                    //获得交易大厅数据
                    final HallData hallData = getEntrustOrder(entry.getKey());
                    ThreadPoolPushData.exe(() -> {
                        for (WebSocketSession session : entry.getValue().values()) {
                            String coinCode = String.valueOf(session.getAttributes().get("coinCode"));
                            hallData.setCoinCode(coinCode);

                            push(hallData, session);
                        }
                    });
                });
            }
        }
    }

    private void push(HallData hallData, WebSocketSession session) {
        synchronized (session.getId()) {
            if (session.isOpen()) {
                try {
                    session.sendMessage(new TextMessage(JSON.toJSONString(hallData)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 低频数据推送入口
     */
    public void pushLoginData() {
        for (Map<String, WebSocketSession> socketSessionMap : sessionMap.values()) {
            ThreadPoolPushData.exe(() -> {
                for (WebSocketSession session : socketSessionMap.values()) {
                    pushOneUserData(session);
                }
            });
        }
    }

    /**
     * 单用户推送
     */
    private void pushOneUserData(WebSocketSession session) {
        try (Jedis jedis = LendRedis.JEDIS_POOL.getResource()) {
            HallData hallData = new HallData();
            hallData.setCoinCode(String.valueOf(session.getAttributes().get("coinCode")));
            addLeftCoin(session, hallData);
            addAccountData(session, hallData, jedis);

            push(hallData, session);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 左侧推送 延迟推送
     */
    private void addLeftCoin(WebSocketSession session, HallData hallData) {
        MessageEntrust messageEntrust = (MessageEntrust) session.getAttributes().get("messageEntrust");
        String defaultPriCoin = messageEntrust.getLeftCoin();
        hallData.setLeftPrice(leftCoin.get(defaultPriCoin));
        hallData.setLeftCoinTab(leftCoinTab);
    }

    /**
     * 个人数据
     */
    private void addAccountData(WebSocketSession session, HallData hallData, Jedis jedis) {
        Object uidObj = session.getAttributes().get("uid");
        if (null == uidObj) {
            return;
        }
        String uid = String.valueOf(uidObj);
        String coinCode = String.valueOf(session.getAttributes().get("coinCode"));

        AccountData data = new AccountData();
        String accountStr = jedis.hget(ExchangeLendKey.getLendAccountKey(coinCode), uid);
        if (accountStr != null) {
            JSONObject account = JSONObject.parseObject(accountStr);
            data.setTradeMoney(new BigDecimal(account.get("tradeMoney") + ""));
            data.setPriMoney(new BigDecimal(account.get("priMoney") + ""));
        }
        MessageEntrust messageEntrust = (MessageEntrust) session.getAttributes().get("messageEntrust");
        /*List<LendEntrust> currentEntrust = remoteLendMoneyService.findCurrentEntrust(uid, coinCode, messageEntrust.getCurrentType());
        data.setCurrentEntrust(currentEntrust);
        List<LendEntrust> historyEntrust = remoteLendMoneyService.findHistoryEntrust(uid, coinCode, messageEntrust.getHistoryType());
        data.setHistoryEntrust(historyEntrust);
*/
        hallData.setAccountData(data);

    }

    /**
     * 交易大厅数据
     */
    private HallData getEntrustOrder(String coinCode) {
        HallData data = new HallData();
        try (Jedis jedis = LendRedis.JEDIS_POOL.getResource()) {
            addBuySellEntrust(coinCode, data, jedis);
            addOrderRecord(coinCode, data, jedis);

            addCoinData(coinCode, data, jedis);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    /**
     * 计算入口
     */
    public void calculateData(){
        try(Jedis jedis = LendRedis.JEDIS_POOL.getResource()){
            addLeftCoinData(jedis);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 左侧交易区数据全部计算
     */
    private void addLeftCoinData(Jedis jedis) {
        Map<String, String> configs = jedis.hgetAll(ExchangeLendKey.LEND_CONFIG);
        for (String coin : configs.keySet()) {
            String priCoin = coin.split("_")[1];
            List<String> coinCodes = remoteLendConfigService.findCoinCodeByPriCoin(priCoin);
            List<HallData.LeftCoinData> coinPrice = new LinkedList<>();
            for (String coinCode : coinCodes) {
                HallData.LeftCoinData coinData = new HallData.LeftCoinData();
                coinData.setCoinCode(coinCode);
                BigDecimal newPrice = LendUtil.getNewPrice(jedis, coinCode);
                coinData.setPrice(newPrice);
                coinPrice.add(coinData);
            }
            leftCoin.put(priCoin, coinPrice);
            if (!leftCoinTab.contains(priCoin)) {
                leftCoinTab.add(priCoin);
            }
        }
    }

    /**
     * 基本信息
     */
    private void addCoinData(String coinCode, HallData data, Jedis jedis) {
        //小数位
        String lendconfigStr = jedis.hget(ExchangeLendKey.LEND_CONFIG, coinCode);
        HallData.KeepDecimal decimal = new HallData.KeepDecimal();
        if (StringUtils.isNotEmpty(lendconfigStr)) {
            ExLendConfig config = JSON.parseObject(lendconfigStr, ExLendConfig.class);
            decimal.setPriDecimal(config.getKeepDecimalForCoin());
            decimal.setTradeDecimal(config.getKeepDecimalForCurrency());
        }
        data.setDecimal(decimal);

        //基本信息
        HallData.CoinData coinData = new HallData.CoinData();
        List<String> lastDay = jedis.lrange(ExchangeLendKey.getKlineData(coinCode, KlineTimes.ONE_DAY.getMsg()), 0, 1);
        if (lastDay.size() > 0) {
            //今日高低
            KlineData currentData = JSON.parseObject(lastDay.get(0), KlineData.class);
            coinData.setMaxPrice(currentData.getMaxPrice());
            coinData.setMinPrice(currentData.getMinPrice());
            if (lastDay.size() > 1) {
                //昨日收盘价
                BigDecimal lastEndPrice = JSON.parseObject(lastDay.get(1), KlineData.class).getEndPrice();
                BigDecimal upRatio = data.getNewPrice().subtract(lastEndPrice).divide(lastEndPrice, LendConfig.RATIO_KEEP_DECIMAL_HUNDRED, BigDecimal.ROUND_DOWN).multiply(new BigDecimal(100));
                coinData.setUpRatio(upRatio);
            }
        }
        //24h成交量
        coinData.setUpDayCount(new BigDecimal(226.65));
        //折合
        coinData.setCoinToMoney(new BigDecimal(15.68));

        data.setCoinData(coinData);
    }

    /**
     * 最新成交记录
     * 频率 III
     */
    private void addOrderRecord(String coinCode, HallData data, Jedis jedis) {
        List<String> orderList = jedis.lrange(ExchangeLendKey.getorderRecordKey(coinCode), 0, LendConfig.SHOW_ORDER_RECORD_COUNT - 1);
        data.setOrderList(orderList);
    }

    /**
     * 买卖单挂单数据、深度图、最新价
     * 频率  III
     */
    private void addBuySellEntrust(String coinCode, HallData data, Jedis jedis) {
        //最新价
        String price = jedis.get(ExchangeLendKey.getLendNewPrice(coinCode));
        if (StringUtils.isEmpty(price)) {
            price = "0";
        }
        BigDecimal newPrice = new BigDecimal(price);
        data.setNewPrice(newPrice);

        int size = LendConfig.SHOW_ENTRUST_ORDER_COUNT > LendConfig.SHOW_DEPTH_MAP_COUNT ? LendConfig.SHOW_ENTRUST_ORDER_COUNT : LendConfig.SHOW_DEPTH_MAP_COUNT;
        List<BigDecimal[]> buy = new ArrayList<>(size);
        List<BigDecimal[]> sell = new ArrayList<>(size);
        HallData.DepthMap depthMap = new HallData.DepthMap();

        int idx = 0;
        int end = size;
        BigDecimal buyUpCount = new BigDecimal(0);
        BigDecimal sellUpCount = new BigDecimal(0);
        while (true) {
            Set<Tuple> buyEntrust = jedis.zrevrangeWithScores(ExchangeLendKey.getTransactionRedisKeyByType(coinCode, DealConstant.BUY_TYPE), idx, end - 1);
            Set<Tuple> sellEntrust = jedis.zrangeWithScores(ExchangeLendKey.getTransactionRedisKeyByType(coinCode, DealConstant.SELL_TYPE), idx, end - 1);
            if (buyEntrust.size() == 0 && sellEntrust.size() == 0) {
                break;
            }
            buyUpCount = addEntrustOrder(buy, buyEntrust, size, buyUpCount);
            sellUpCount = addEntrustOrder(sell, sellEntrust, size, sellUpCount);
            if (buy.size() >= size || sell.size() >= size) {
                break;
            }
            idx = (end + 1);
            end += LendConfig.CALCULATE_ENTRUST_ORDER;
        }
        if (buy.size() > LendConfig.SHOW_ENTRUST_ORDER_COUNT || sell.size() > LendConfig.SHOW_ENTRUST_ORDER_COUNT) {
            if (buy.size() == 0) {
                data.setAsks(new ArrayList<>());
            } else {
                if (buy.size() < LendConfig.SHOW_ENTRUST_ORDER_COUNT) {
                    data.setAsks(buy);
                } else {
                    data.setAsks(buy.subList(0, LendConfig.SHOW_ENTRUST_ORDER_COUNT));
                }
            }
            if (sell.size() == 0) {
                data.setBids(new ArrayList<>());
            } else {
                if (sell.size() < LendConfig.SHOW_ENTRUST_ORDER_COUNT) {
                    data.setBids(sell);
                } else {
                    data.setBids(sell.subList(0, LendConfig.SHOW_ENTRUST_ORDER_COUNT));
                }
            }
        } else {
            data.setAsks(buy);
            data.setBids(sell);
        }
        List<BigDecimal[]> buyDepth = getDepathMap(size, buy);
        List<BigDecimal[]> sellDepth = getDepathMap(size, sell);
        if (buyDepth.size() > LendConfig.SHOW_DEPTH_MAP_COUNT || sellDepth.size() > LendConfig.SHOW_DEPTH_MAP_COUNT) {
            List<BigDecimal[]> asks = buyDepth.subList(0, LendConfig.SHOW_DEPTH_MAP_COUNT);
            Collections.reverse(asks);
            depthMap.setAsks(asks);
            depthMap.setBids(sellDepth.subList(0, LendConfig.SHOW_DEPTH_MAP_COUNT));
        } else {
            Collections.reverse(buyDepth);
            depthMap.setAsks(buyDepth);
            depthMap.setBids(sellDepth);
        }
        data.setDepthMap(depthMap);
    }

    /**
     * 获得深度图数据
     */
    private List<BigDecimal[]> getDepathMap(int size, List<BigDecimal[]> buy) {
        List<BigDecimal[]> buyDepth = new ArrayList<>(size);
        for (BigDecimal[] aBuy : buy) {
            BigDecimal[] datas = new BigDecimal[2];
            datas[0] = aBuy[0];
            datas[1] = aBuy[1];
            buyDepth.add(datas);
        }
        return buyDepth;
    }

    /**
     * 买卖单挂单数据
     */
    private BigDecimal addEntrustOrder(List<BigDecimal[]> buy, Set<Tuple> buyEntrust, int size, BigDecimal upCount) {
        for (Tuple tuple : buyEntrust) {
            BigDecimal count = new BigDecimal(tuple.getElement().split("=")[2]);
            upCount = upCount.add(count);
            BigDecimal score = new BigDecimal(tuple.getScore()).divide(LendConfig.TEN_POW_NUMBER, LendConfig.KEEP_DECIMAL, BigDecimal.ROUND_DOWN);
            if (buy.size() > 0) {
                BigDecimal[] decimals = buy.get(buy.size() - 1);
                if (decimals[0].compareTo(score) == 0) {
                    decimals[1] = decimals[1].add(count);
                    decimals[2] = decimals[2].add(count);
                    continue;
                }
            }
            BigDecimal[] asks = new BigDecimal[3];
            //价格
            asks[0] = score;
            //叠加数量
            asks[1] = upCount;
            //委托数量
            asks[2] = count;
            buy.add(asks);
            if (buy.size() >= size) {
                break;
            }
        }
        return upCount;
    }

}
