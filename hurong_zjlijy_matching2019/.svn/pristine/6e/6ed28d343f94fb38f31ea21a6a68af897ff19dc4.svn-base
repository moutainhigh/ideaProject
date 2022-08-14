/**
 * Copyright:   北京互融时代软件有限公司
 *
 * @author: Wu Shuiming
 * @version: V1.0
 * @Date: 2016年3月24日 下午2:04:29
 */
package hry.trade.entrust.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.annotation.Resource;

import hry.trade.model.AccountSyncMessage;
import hry.trade.mq.service.MessageProducer;
import hry.util.BaseConfUtil;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;

import hry.core.constant.StringConstant;
import hry.core.mvc.model.log.AppException;
import hry.core.thread.ThreadPool;
import hry.front.redis.model.UserRedis;
import hry.redis.common.dao.RedisUtil;
import hry.redis.common.utils.RedisService;
import hry.redis.common.utils.RedisTradeService;
import hry.trade.MQmanager.MQEnter;
import hry.trade.account.service.AppAccountService;
import hry.trade.account.service.ExDigitalmoneyAccountService;
import hry.trade.comparator.AccountaddComparator;
import hry.trade.entrust.dao.ExEntrustDao;
import hry.trade.entrust.model.ExEntrust;
import hry.trade.entrust.model.ExOrderInfo;
import hry.trade.entrust.service.ExOrderInfoService;
import hry.trade.entrust.service.TradeService;
import hry.trade.init.TradeInitData;
import hry.trade.model.Coin;
import hry.trade.model.PeriodLastKLineListRunable;
import hry.trade.model.TradeRedis;
import hry.trade.mq.service.AppExceptionService;
import hry.trade.redis.model.Accountadd;
import hry.trade.redis.model.AppAccountRedis;
import hry.trade.redis.model.EntrustTrade;
import hry.trade.redis.model.ExDigitalmoneyAccountRedis;
import hry.trade.redis.model.ExchangeDataCacheRedis;
import hry.util.sys.ContextUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * <p>
 * TODO
 * </p>
 *
 * @author: Gao Mimi
 * @Date : 2016年4月12日 下午4:45:50
 */
@Service("tradeService")
public class TradeServiceImpl implements TradeService {

    private final Logger log = Logger.getLogger(TradeServiceImpl.class);

    @Resource
    public ExDigitalmoneyAccountService exDigitalmoneyAccountService;
    @Resource
    public AppAccountService appAccountService;

    @Resource
    public ExOrderInfoService exOrderInfoService;
    @Resource
    public RedisService redisService;

    @Resource
    private MessageProducer messageProducer;

    @Resource
    public ExEntrustDao exEntrustDao;

    public void canceltype(EntrustTrade entrustTrade1) {
        if (null == entrustTrade1.getEntrustNum()) {
            long start = System.currentTimeMillis();
            if (null != entrustTrade1.getCoinCode()) {
                if (null != entrustTrade1.getCancelKeepN()) {
                    Integer i = 0;
                    Map<String,Object> map = new HashMap<String,Object>();
                    map.put("customerId", entrustTrade1.getCustomerId().toString());
                    map.put("fixPriceCoinCode", entrustTrade1.getFixPriceCoinCode());
                    map.put("coinCode", entrustTrade1.getCoinCode());
                    map.put("type", entrustTrade1.getType());
                    List<ExEntrust> listex = exEntrustDao.getEntrustingByCustomerId(map);
                    if (null == listex || listex.size() == 0) {

                        return;
                    }

                    for (ExEntrust l : listex) {
                        if (i + entrustTrade1.getCancelKeepN() >= listex.size()) {
                            break;
                        }
                        EntrustTrade entrustTrade = new EntrustTrade();
                        entrustTrade.setEntrustNum(l.getEntrustNum());
                        entrustTrade.setCoinCode(l.getCoinCode());
                        entrustTrade.setType(l.getType());
                        entrustTrade.setFixPriceCoinCode(l.getFixPriceCoinCode());
                        entrustTrade.setEntrustPrice(BigDecimal.valueOf(Double.parseDouble(l.getEntrustPrice().toString())).stripTrailingZeros());
                        entrustTrade.setEntrustPrice(l.getEntrustPrice().setScale(10, BigDecimal.ROUND_HALF_EVEN));
                        cancelExEntrust(entrustTrade);
                        i++;

                    }

                    long end = System.currentTimeMillis();
                    log.info(entrustTrade1.getCoinCode() + "-" + entrustTrade1.getFixPriceCoinCode() + "(" + (i.toString()) + ")全部单一币种mq撤销总耗时：" + (end - start) + "毫秒");


                } else {
                    Integer i = 0;
                    //如果光判断这个也是有问题的，如果满了，但是单个撤销了几个，个数就少于满的，但实际上还有不在缓存里的
                    //	if(list.size()>=EntrustByUser.ingMAXsize-1){  //说明已经满了，需要从数据那全部的未成交委托单
                    //		log.info("list.size()>=EntrustByUser.ingMAXsize");

                    Map<String,Object> map = new HashMap<String,Object>();
                    map.put("customerId", entrustTrade1.getCustomerId().toString());
                    map.put("fixPriceCoinCode", entrustTrade1.getFixPriceCoinCode());
                    map.put("coinCode", entrustTrade1.getCoinCode());
                    map.put("type", entrustTrade1.getType());
                    List<ExEntrust> listex = exEntrustDao.getEntrustingByCustomerId(map);
                    if (null == listex || listex.size() == 0) {

                        return;
                    }
                    for (ExEntrust l : listex) {

                        EntrustTrade entrustTrade = new EntrustTrade();
                        entrustTrade.setEntrustNum(l.getEntrustNum());
                        entrustTrade.setCoinCode(l.getCoinCode());
                        entrustTrade.setType(l.getType());
                        entrustTrade.setFixPriceCoinCode(l.getFixPriceCoinCode());
                        entrustTrade.setEntrustPrice(BigDecimal.valueOf(Double.parseDouble(l.getEntrustPrice().toString())).stripTrailingZeros());
                        entrustTrade.setEntrustPrice(l.getEntrustPrice().setScale(10, BigDecimal.ROUND_HALF_EVEN));
                        cancelExEntrust(entrustTrade);
                        i++;

                    }

                    long end = System.currentTimeMillis();
                    log.info(entrustTrade1.getCoinCode() + "-" + entrustTrade1.getFixPriceCoinCode() + "(" + (i.toString()) + ")全部单一币种mq撤销总耗时：" + (end - start) + "毫秒");
                }
            } else {
                Integer i = 0;
                Map<String,Object> map = new HashMap<String,Object>();
                map.put("customerId", entrustTrade1.getCustomerId().toString());
                List<ExEntrust> listex = exEntrustDao.getEntrustingByCustomerId(map);
                if (null == listex || listex.size() == 0) {
                    return;
                }
                for (ExEntrust l : listex) {

                    EntrustTrade entrustTrade = new EntrustTrade();
                    entrustTrade.setEntrustNum(l.getEntrustNum());
                    entrustTrade.setCoinCode(l.getCoinCode());
                    entrustTrade.setType(l.getType());
                    entrustTrade.setFixPriceCoinCode(l.getFixPriceCoinCode());
                    entrustTrade.setEntrustPrice(BigDecimal.valueOf(Double.parseDouble(l.getEntrustPrice().toString())).stripTrailingZeros());
                    entrustTrade.setEntrustPrice(l.getEntrustPrice().setScale(10, BigDecimal.ROUND_HALF_EVEN));
                    cancelExEntrust(entrustTrade);
                    i++;

                }
                long end = System.currentTimeMillis();
                log.info("(" + (i.toString()) + ")全部撤销总耗时：" + (end - start) + "毫秒");
            }


        } else {

            long start = System.currentTimeMillis();
            entrustTrade1.setEntrustPrice(entrustTrade1.getEntrustPrice().setScale(10, BigDecimal.ROUND_HALF_EVEN));
            cancelExEntrust(entrustTrade1);
            long end = System.currentTimeMillis();
            log.info("单个mq撤销总耗时：" + (end - start) + "毫秒");

        }

    }

    @Override
    public void matchExtrustToOrderQueue(String exentrust) {

        EntrustTrade entrust = JSON.parseObject(exentrust, EntrustTrade.class);
        if (null == entrust.getIsType()) {
            entrust.setIsType(0);  //如果没穿默认是主交易区
        }
        if (TradeInitData.isIsInit()) {
            if (null == entrust) {
                log.error("entrust为空");
                return;

            }
            if (null == entrust.getEntrustTime()) { // 撤销
                canceltype(entrust);
            } else { // 匹配

                if (!culHotmoney(entrust)) return;

                entrust.setEntrustTime(new Date());

                if (null == entrust.getEntrustNum()) {
                    if (entrust.getType() == 1) {
                        entrust.setEntrustNum("WB" + UUID.randomUUID().toString().replace("-", ""));
                    } else {
                        entrust.setEntrustNum("WS" + UUID.randomUUID().toString().replace("-", ""));
                    }
                }


                entrust.setInEntrustNum(transactionNum(entrust.getEntrustTime()));
                matchExtrustToOrder(entrust);

            }
        } else {
            log.error("缓存同步中，交易被拒绝：" + entrust.getEntrustNum());
        }

    }

    public boolean culHotmoney(EntrustTrade entrust) {

        RedisUtil<UserRedis> redisUtil = new RedisUtil<UserRedis>(UserRedis.class);
        UserRedis userRedis = redisUtil.get(entrust.getCustomerId().toString());

        String code = entrust.getCoinCode();// 交易币
        String priceCode = entrust.getFixPriceCoinCode(); // 定价币
        if (entrust.getEntrustWay().intValue() == 1) {
            if (entrust.getType() == 1) {// 如果买 判断定价币
                if (entrust.getFixPriceType().compareTo(0) == 0) {
                    AppAccountRedis accountRedis = appAccountService.getAppAccountByRedis(userRedis.getAccountId().toString());

                    if (accountRedis.getHotMoney().compareTo(entrust.getEntrustPrice().multiply(entrust.getEntrustCount())) < 0) {
                        /*
                         * rt[0] = CodeConstant.CODE_FAILED; rt[1] = priceCode +
                         * "不足"; return rt;
                         */
                        log.info("钱余额不足");
                        return false;
                    }
                } else {

                    ExDigitalmoneyAccountRedis ear = exDigitalmoneyAccountService.getExDigitalmoneyAccountByRedis(userRedis.getDmAccountId(priceCode).toString());

                    if (ear.getHotMoney().compareTo(entrust.getEntrustPrice().multiply(entrust.getEntrustCount())) < 0) {
                        /*
                         * rt[0] = CodeConstant.CODE_FAILED; rt[1] = priceCode +
                         * "不足"; return rt;
                         */
                        log.info("币余额不足");
                        return false;
                    }
                }
            }

            if (entrust.getType() == 2) {// 如果卖 判断交易币
                ExDigitalmoneyAccountRedis ear = exDigitalmoneyAccountService.getExDigitalmoneyAccountByRedis(userRedis.getDmAccountId(code).toString());

                if (ear.getHotMoney().compareTo(entrust.getEntrustCount()) < 0) {
                    /*
                     * rt[0] = CodeConstant.CODE_FAILED; rt[1] = code + "不足";
                     * return rt;
                     */
                    return false;
                }
            }
            if (entrust.getEntrustWay().compareTo(1) == 0) {
                if (entrust.getEntrustPrice().compareTo(new BigDecimal(0)) <= 0 || entrust.getEntrustCount().compareTo(new BigDecimal(0)) <= 0) {
                    return false;
                }
            }
        } else {

            if (entrust.getType() == 1) {// 如果买 判断定价币
                if (entrust.getFixPriceType().compareTo(0) == 0) {
                    AppAccountRedis accountRedis = appAccountService.getAppAccountByRedis(userRedis.getAccountId().toString());

                    if (accountRedis.getHotMoney().compareTo(entrust.getEntrustSum()) < 0) {

                        log.info("钱余额不足");
                        return false;
                    }
                } else {

                    ExDigitalmoneyAccountRedis ear = exDigitalmoneyAccountService.getExDigitalmoneyAccountByRedis(userRedis.getDmAccountId(priceCode).toString());

                    if (ear.getHotMoney().compareTo(entrust.getEntrustSum()) < 0) {

                        log.info("币余额不足");
                        return false;
                    }
                }
            }

            if (entrust.getType() == 2) {// 如果卖 判断交易币
                ExDigitalmoneyAccountRedis ear = exDigitalmoneyAccountService.getExDigitalmoneyAccountByRedis(userRedis.getDmAccountId(code).toString());

                if (ear.getHotMoney().compareTo(entrust.getEntrustCount()) < 0) {

                    return false;
                }
            }

        }


        if (entrust.getEntrustPrice().compareTo(new BigDecimal("9999999999")) == 1 || entrust.getEntrustCount().compareTo(new BigDecimal("9999999999")) == 1 || entrust.getEntrustSum().compareTo(new BigDecimal("9999999999")) == 1) {
            log.info("超过了数据库的长度，总价或者价格或者量");
            return false;
        }
        if (entrust.getEntrustPrice().compareTo(new BigDecimal("0")) == -1 || entrust.getEntrustCount().compareTo(new BigDecimal("0")) == -1 || entrust.getEntrustSum().compareTo(new BigDecimal("0")) == -1) {
            log.info("数据不能为负数");
            return false;
        }
        return true;
    }

    public String transactionNum(Date date) {
        String randomStr = RandomStringUtils.random(3, false, true);
        SimpleDateFormat format = new SimpleDateFormat("yyMMddHHmmss");
        String time = format.format(date);
        String v = redisService.get(time);
        if (!StringUtils.isEmpty(v)) {
            Integer aa = Integer.valueOf(v) + 1;
            String bb = aa.toString();
            if (aa.compareTo(10) == -1) {
                bb = "00" + aa.toString();
            } else if (aa.compareTo(100) == -1) {
                bb = "0" + aa.toString();
            }
            redisService.save(time, aa.toString(), 1);
            return time + bb + randomStr;
        } else {
            redisService.save(time, "1", 1);
            return time + "001" + randomStr;
        }

    }

    public EntrustTrade getById(List<EntrustTrade> list, Integer start, Integer end, Long id) {
        Integer k = (start + end) / 2;
        EntrustTrade et = list.get(k);
        if (et.getId().compareTo(id) == 0) {
            list.remove(k);
            return et;
        } else if (et.getId().compareTo(id) == 1) {
            getById(list, start, k, id);
        } else if (et.getId().compareTo(id) == -1) {
            getById(list, k, end, id);
        }
        return null;
    }

    public void cancelExEntrust(EntrustTrade exEntrust) {
        String key = TradeRedis.getHeader(exEntrust.getCoinCode(), exEntrust.getFixPriceCoinCode(), exEntrust.getType()) + ":" + exEntrust.getEntrustPrice();
        String entrustredis = TradeRedis.getTradeStringData(key);
        if (StringUtils.isEmpty(entrustredis) || entrustredis.equals("[]")) {
            log.info("撤销失败，keylist为空" + key);
            return;
        }
        List<EntrustTrade> list = JSON.parseArray(entrustredis, EntrustTrade.class);
        int k = 0;
        for (EntrustTrade l : list) {
            if (exEntrust.getEntrustNum().equals(l.getEntrustNum())) {
                exEntrust = l;
                list.remove(l);
                k = 1;
                break;
            }
        }
        if (k == 0) {
            log.info("撤销失败，因为已经没找到这个委托");
            return;
        }
        if (null == exEntrust) {
            log.info("撤销失败，因为已经没找到这个委托");
            return;
        }
        if (exEntrust.getStatus() >= 2) {
            log.info("撤销失败，因为已经之前已经撤销过");
            return;
        }
        if (exEntrust.getStatus().equals(1)) {
            exEntrust.setStatus(3);
        } else if (exEntrust.getStatus().equals(0)) {
            exEntrust.setStatus(4);
        }
        List<Accountadd> aaddlists = new ArrayList<Accountadd>();
        String transactionNum = exEntrust.getEntrustNum();
        if (exEntrust.getType().equals(1)) {
            // 如果是真实货币
            if (exEntrust.getFixPriceType().equals(0)) {
                BigDecimal unfreezeMoney = exEntrust.getEntrustSum().subtract(exEntrust.getTransactionSum());
                Accountadd accountadd1 = getAccountadd(0, exEntrust.getAccountId(), unfreezeMoney, 1, 12, transactionNum);
                aaddlists.add(accountadd1);
                Accountadd accountadd2 = getAccountadd(0, exEntrust.getAccountId(), fu(unfreezeMoney), 2, 12, transactionNum);
                aaddlists.add(accountadd2);

            } else {
                BigDecimal unfreezeMoney = exEntrust.getEntrustSum().subtract(exEntrust.getTransactionSum());
                Accountadd accountadd1 = getAccountadd(1, exEntrust.getAccountId(), unfreezeMoney, 1, 12, transactionNum);
                aaddlists.add(accountadd1);
                Accountadd accountadd2 = getAccountadd(1, exEntrust.getAccountId(), fu(unfreezeMoney), 2, 12, transactionNum);
                aaddlists.add(accountadd2);

            }
        }
        // 卖币都一样
        if (exEntrust.getType().equals(2)) {
            BigDecimal unfreezeMoney = exEntrust.getSurplusEntrustCount();
            Accountadd accountadd1 = getAccountadd(1, exEntrust.getCoinAccountId(), unfreezeMoney, 1, 12, transactionNum);
            aaddlists.add(accountadd1);
            Accountadd accountadd2 = getAccountadd(1, exEntrust.getCoinAccountId(), fu(unfreezeMoney), 2, 12, transactionNum);
            aaddlists.add(accountadd2);
        }
        TradeRedis.cancelEntrust(exEntrust, list, key, aaddlists);

    }

    public void matchExtrustToOrder(EntrustTrade exEntrust) {
        long start = System.currentTimeMillis();
        // type类型 1 ： 买 2 ： 卖
        if (null == exEntrust.getAppointOpponentType()) {
            if (exEntrust.getType().equals(1)) {
                buyExchange(exEntrust);
            } else if (exEntrust.getType().equals(2)) {
                sellExchange(exEntrust);
            }
            long end = System.currentTimeMillis();
            long time = end - start;
            if (time > 20) {
                log.info("匹配总耗时：" + (time) + "毫秒");
            }
        } else if (exEntrust.getAppointOpponentType().intValue() == 1) { //制定委托单交易
		/*	appointMatchExtrust(exEntrust);
			long end = System.currentTimeMillis();
			long time=end - start;
			if(time>20){
				log.info("k线机器人匹配总耗时：" + (time) + "毫秒");
			}*/
        }


    }

    @Override
    public void appointMatchExtrust(EntrustTrade exEntrust, Jedis jedis) {

        EntrustTrade opponentExEntrust = new EntrustTrade();
        opponentExEntrust.setEntrustNum("opponentExEntrust");
        opponentExEntrust.setEntrustPrice(exEntrust.getEntrustPrice());
        opponentExEntrust.setType(exEntrust.getType().intValue() == 1 ? 2 : 1);
        opponentExEntrust.setCoinCode(exEntrust.getCoinCode());
        opponentExEntrust.setFixPriceCoinCode(exEntrust.getFixPriceCoinCode());
        opponentExEntrust.setFixPriceType(exEntrust.getFixPriceType());
        opponentExEntrust.setEntrustPrice(exEntrust.getEntrustPrice().setScale(10, BigDecimal.ROUND_HALF_EVEN));
        opponentExEntrust.setEntrustCount(exEntrust.getEntrustCount());
        opponentExEntrust.setTransactionFeeRate(exEntrust.getTransactionFeeRate());
        opponentExEntrust.setCustomerId(exEntrust.getCustomerId());
        appointExchange(opponentExEntrust, exEntrust, exEntrust.getType());

    }

    public void appointExchange(EntrustTrade opponentExEntrust, EntrustTrade exEntrust, Integer type) {

        List<ExOrderInfo> arr = new ArrayList<ExOrderInfo>();
        if (type.equals(1)) {// 买家限价
            ExOrderInfo exOrderInfo = exOrderInfoService.createExOrderInfo(1, exEntrust, opponentExEntrust, exEntrust.getEntrustCount(), exEntrust.getEntrustPrice());
            exOrderInfo.setInOrOutTransaction("buy");
            arr.add(exOrderInfo);
        } else {
            ExOrderInfo exOrderInfo = exOrderInfoService.createExOrderInfo(1, opponentExEntrust, exEntrust, exEntrust.getEntrustCount(), exEntrust.getEntrustPrice());
            exOrderInfo.setInOrOutTransaction("sell");
            arr.add(exOrderInfo);
        }

        JedisPool jedisPool = (JedisPool) ContextUtil.getBean("jedisPool");
        Jedis jedis = jedisPool.getResource();
        try {
			/*redis.clients.jedis.Transaction transaction = jedis.multi();
			transaction.set(TradeRedis.getTradeDealOrderInfoChangeNum(transaction), JSON.toJSONString(arr));// 成交信息
			List<Object> list =transaction.exec();*/


            String keyFront = TradeRedis.getHeaderFront(exEntrust);
            TradeRedis.setExchangeDataCache(arr, keyFront, jedis); //设置成交列表，最新成交价
            PeriodLastKLineListRunable periodLastKLineListRunable = new PeriodLastKLineListRunable(arr);
            ThreadPool.exe(periodLastKLineListRunable);

            RedisService redisService = (RedisService) ContextUtil.getBean("redisService");
            String keycoin = exEntrust.getCoinCode() + "_" + exEntrust.getFixPriceCoinCode() + ":klineRobotCount";
            String klineRobotCounts = redisService.get(keycoin);
            BigDecimal klineRobotCount = null;
            if (StringUtils.isEmpty(klineRobotCounts)) {
                klineRobotCount = new BigDecimal("0");

            } else {
                klineRobotCount = new BigDecimal(klineRobotCounts);
            }
            klineRobotCount = klineRobotCount.add(exEntrust.getEntrustCount());
            jedis.set(keycoin, klineRobotCount.toString());

        } finally {
            jedis.close();
        }


        RedisTradeService redisTradeService = (RedisTradeService) ContextUtil.getBean("redisTradeService");
        redisTradeService.save(getEntrustTimeFlag(exEntrust.getCoinCode(), exEntrust.getFixPriceCoinCode()), "1");
    }

    public static String getEntrustTimeFlag(String coinCode, String fixPriceCoinCode) {
        String header = coinCode + "_" + fixPriceCoinCode + ":isTimgEntrsutFlag";
        return header;

    }

    public void appointSellExchange(EntrustTrade opponentExEntrust, EntrustTrade sellentrust) {
        if (sellentrust.getEntrustWay().equals(1)) {
            // 获取能够匹配的委托单
            BigDecimal buyonePrice = null;
            try {
                opponentExEntrust.getEntrustPrice().setScale(10, BigDecimal.ROUND_HALF_EVEN);
                String key = TradeRedis.getHeader(opponentExEntrust.getCoinCode(), opponentExEntrust.getFixPriceCoinCode(), opponentExEntrust.getType()) + ":" + opponentExEntrust.getEntrustPrice();
                String entrustredis = TradeRedis.getTradeStringData(key);
                if (StringUtils.isEmpty(entrustredis) || entrustredis.equals("[]")) {
                    log.info("配对失败" + key);
                    return;
                }
                List<EntrustTrade> list = JSON.parseArray(entrustredis, EntrustTrade.class);
                int k = 0;
                int i = -1;
                EntrustTrade entrustTrade = new EntrustTrade(); //
                for (EntrustTrade l : list) {
                    i++;
                    if (opponentExEntrust.getEntrustNum().equals(l.getEntrustNum())) {
                        entrustTrade = l;
                        k = 1;
                        break;
                    }
                }
                if (k == 0) {
                    log.info("配对失败");
                    return;
                }
                // 如果指定的对手单的量比输入的量要小那就不部分匹配，直接放弃
                if (entrustTrade.getSurplusEntrustCount().compareTo(sellentrust.getSurplusEntrustCount()) < 0) {
                    return;
                }

                Map<String,List<EntrustTrade>> maping = new HashMap<String,List<EntrustTrade>>();
                maping.put(key, list);
                List<EntrustTrade> listed = new ArrayList<EntrustTrade>();
                EntrustTrade buyexEntrust = entrustTrade;
                matching(buyexEntrust, sellentrust, "sell");
                listed.add(buyexEntrust);
                if (buyexEntrust.getStatus().equals(2)) {
                    list.remove(i);
                }
                if (!sellentrust.getStatus().equals(2)) {
                    return; //如果没完成那就不对，直接弃掉
                }
                TradeRedis.matchOneEnd(dealFundEntrust(sellentrust), sellentrust, maping, listed, buyonePrice);


            } catch (Exception e) {
                e.printStackTrace();
                throw e;
            } finally {
                TradeRedis.eoinfolists = new ArrayList<ExOrderInfo>();
                TradeRedis.aaddlists = new ArrayList<Accountadd>();
            }

        }
    }

    /**
     *
     * <p>
     * (1)买家限价，卖家限价 (2)买家限价，卖家市价 (3)买家市价，卖家限价 (4)买家市价，卖家市价 暂不考虑
     * </p>
     *
     * @author: Gao Mimi
     * @param: @param
     *             exEntrust
     * @param: @param
     *             saasId
     * @return: void
     * @Date : 2016年4月19日 下午5:21:18
     * @throws:
     */
    public void buyExchange(EntrustTrade buyexEntrust) {
        // System.out.println("buyExchange==" + buyexEntrust.getEntrustNum());
        if (buyexEntrust.getEntrustWay().equals(1)) {// 买家限价
            // 获取能够匹配的委托单
            long start = System.currentTimeMillis();
            /*
             * BigDecimal sellonePriceold =
             * TradeRedis.getMatchOnePrice(buyexEntrust); if (null ==
             * sellonePriceold ||
             * sellonePriceold.compareTo(buyexEntrust.getEntrustPrice()) == 1) {
             * // 比卖一价还小，那就没必要去差keys dealFundNoMatch(buyexEntrust); return; }
             */
            List<BigDecimal> keyslist = TradeRedis.getMatchkeys(buyexEntrust);// 查所有的keys
            long end = System.currentTimeMillis();
            long time = end - start;
            if (time > 3) {
                log.info("取key并排序：" + (time) + "毫秒");
            }

            // 获取能够匹配的委托单
            BigDecimal sellonePrice = null;
            long start1 = System.currentTimeMillis();
            if (null != keyslist && keyslist.size() > 0) {
                try {
                    List<EntrustTrade> listed = new ArrayList<EntrustTrade>();
                    Map<String,List<EntrustTrade>> maping = new HashMap<String,List<EntrustTrade>>();

                    outterLoop:
                    for (BigDecimal keybig : keyslist) {
                        String keyall = TradeRedis.getHeaderMatch(buyexEntrust) + ":" + keybig.toString();
                        List<EntrustTrade> list = TradeRedis.getMatchEntrustTradeBykey(keyall);
                        if (null != list && list.size() > 0) {
                            maping.put(keyall, list);
                            int size = list.size();
                            int i = 0;
                            while (i < size) {
                                EntrustTrade sellentrust = list.get(i);
                                if (sellentrust.getEntrustWay().intValue() == 1 && sellentrust.getEntrustPrice().compareTo(buyexEntrust.getEntrustPrice()) == 1) {
                                    sellonePrice = sellentrust.getEntrustPrice();
                                    break outterLoop;
                                }
                                matching(buyexEntrust, sellentrust, "buy");
                                if (sellentrust.getStatus().equals(2)) {
                                    list.remove(i);
                                    i--;
                                    size--;
                                }
                                listed.add(sellentrust); // 完成的
                                // 如果匹配完了走出循环
                                if (buyexEntrust.getStatus().equals(2)) {
                                    if (sellentrust.getEntrustWay().intValue() == 1) {
                                        if (!sellentrust.getStatus().equals(2)) {
                                            sellonePrice = sellentrust.getEntrustPrice();
                                        } else {
                                            if (i + 1 < size) {
                                                EntrustTrade sellentrustsellone = list.get(i + 1);
                                                sellonePrice = sellentrustsellone.getEntrustPrice();
                                            } else {
                                                sellonePrice = null;
                                            }

                                        }
                                    }

                                    break outterLoop;
                                }
                                i++;
                            }
                        }
                    }

                    long end1 = System.currentTimeMillis();
                    // log.info("业务逻辑：" + (end1 - start1) + "毫秒");
                    if (buyexEntrust.getStatus().equals(0)) {
                        long start2 = System.currentTimeMillis();
                        dealFundNoMatch(buyexEntrust);
                        long end2 = System.currentTimeMillis();
						/* log.info("匹配失败业务逻辑end：" + (end2 - start2) +
						 "毫秒");*/
                    } else {
                        long start2 = System.currentTimeMillis();
                        TradeRedis.matchOneEnd(dealFundEntrust(buyexEntrust), buyexEntrust, maping, listed, sellonePrice);
                        long end2 = System.currentTimeMillis();
					/*	 log.info("匹配成功业务逻辑end：" + (end2 - start2) +
						 "毫秒");*/
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    throw e;
                } finally {
                    TradeRedis.eoinfolists = new ArrayList<ExOrderInfo>();
                    TradeRedis.aaddlists = new ArrayList<Accountadd>();
                }

            } else {
                // 保存
                long start3 = System.currentTimeMillis();
                dealFundNoMatch(buyexEntrust);
                long end3 = System.currentTimeMillis();
                //	log.info("匹配失败业务逻辑end：" + (end3 - start3) + "毫秒");
            }
        } else if (buyexEntrust.getEntrustWay().equals(2)) {


            // 买家市价

            // 获取能够匹配的委托单
            long start = System.currentTimeMillis();
            List<BigDecimal> keyslist = TradeRedis.getMatchkeys(buyexEntrust);// 查所有的keys为限价对手单
            long end = System.currentTimeMillis();
            long time = end - start;
            if (time > 3) {
                log.info("取key并排序：" + (time) + "毫秒");
            }
            // 获取能够匹配的委托单
            BigDecimal sellonePrice = null;
            long start1 = System.currentTimeMillis();
            if (null != keyslist && keyslist.size() > 0) {
                try {
                    List<EntrustTrade> listed = new ArrayList<EntrustTrade>();
                    Map<String,List<EntrustTrade>> maping = new HashMap<String,List<EntrustTrade>>();

                    outterLoop:
                    for (BigDecimal keybig : keyslist) {
                        String keyall = TradeRedis.getHeaderMatch(buyexEntrust) + ":" + keybig.toString();
                        List<EntrustTrade> list = TradeRedis.getMatchEntrustTradeBykey(keyall);
                        if (null != list && list.size() > 0) {
                            maping.put(keyall, list);
                            int size = list.size();
                            int i = 0;
                            while (i < size) {
                                EntrustTrade sellentrust = list.get(i);
                                matching(buyexEntrust, sellentrust, "buy");
                                if (sellentrust.getStatus().equals(2)) {
                                    list.remove(i);
                                    i--;
                                    size--;
                                }
                                listed.add(sellentrust); // 完成的
                                // 如果匹配完了走出循环
                                if (buyexEntrust.getStatus().equals(2)) {
                                    if (!sellentrust.getStatus().equals(2)) {
                                        sellonePrice = sellentrust.getEntrustPrice();
                                    } else {
                                        if (i + 1 < size) {
                                            EntrustTrade sellentrustsellone = list.get(i + 1);
                                            sellonePrice = sellentrustsellone.getEntrustPrice();
                                        } else {
                                            sellonePrice = null;
                                        }

                                    }
                                    break outterLoop;
                                }
                                i++;
                            }
                        }
                    }

                    if (buyexEntrust.getStatus().equals(0)) {
                        dealFundNoMatch(buyexEntrust);
                    } else {
                        TradeRedis.matchOneEnd(dealFundEntrust(buyexEntrust), buyexEntrust, maping, listed, sellonePrice);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    throw e;
                } finally {
                    TradeRedis.eoinfolists = new ArrayList<ExOrderInfo>();
                    TradeRedis.aaddlists = new ArrayList<Accountadd>();
                }

            } else {
                // 保存
                dealFundNoMatch(buyexEntrust);
            }

        }

    }

    /**
     *
     * <p>
     * (1)卖家限价，买家限价 (3)卖家限价，买家市价 (2)卖家市价，买家限价 (4)卖家市价，买家市价 暂不考虑
     * </p>
     *
     * @author: Gao Mimi
     * @param: @param
     *             exEntrust
     * @param: @param
     *             saasId
     * @return: void
     * @Date : 2016年4月19日 下午5:21:18
     * @throws:
     */
    public void sellExchange(EntrustTrade sellentrust) {
        // System.out.println("sellExchange==" + sellentrust.getEntrustNum());
        if (sellentrust.getEntrustWay().equals(1)) {// 卖家限价 //必须相等才匹配
            /*
             * BigDecimal onePrice = TradeRedis.getMatchOnePrice(sellentrust);
             * if (null == onePrice ||
             * onePrice.compareTo(sellentrust.getEntrustPrice()) == -1) { //
             * 比买一价还大，那就没必要去差keys dealFundNoMatch(sellentrust); return; }
             */
            List<BigDecimal> keyslist = TradeRedis.getMatchkeys(sellentrust);
            if (null != keyslist && keyslist.size() > 0) {
                try {
                    List<EntrustTrade> listed = new ArrayList<EntrustTrade>();
                    Map<String,List<EntrustTrade>> maping = new HashMap<String,List<EntrustTrade>>();
                    BigDecimal buyonePrice = null;
                    outterLoop:
                    for (BigDecimal keybig : keyslist) {
                        String keyall = TradeRedis.getHeaderMatch(sellentrust) + ":" + keybig.toString();
                        List<EntrustTrade> list = TradeRedis.getMatchEntrustTradeBykey(keyall);
                        if (null != list && list.size() > 0) {
                            maping.put(keyall, list);
                            int size = list.size();
                            int i = 0;
                            while (i < size) {
                                EntrustTrade buyexEntrust = list.get(i);
                                if (buyexEntrust.getEntrustWay().intValue() == 1 && buyexEntrust.getEntrustPrice().compareTo(sellentrust.getEntrustPrice()) == -1) {
                                    buyonePrice = buyexEntrust.getEntrustPrice();
                                    break outterLoop;
                                }
                                matching(buyexEntrust, sellentrust, "sell");
                                if (buyexEntrust.getStatus().equals(2)) {
                                    list.remove(i);
                                    i--;
                                    size--;
                                }
                                listed.add(buyexEntrust);
                                // 如果匹配完了走出循环
                                if (sellentrust.getStatus().equals(2)) {
                                    if (buyexEntrust.getEntrustWay().intValue() == 1) {
                                        if (!buyexEntrust.getStatus().equals(2)) {
                                            buyonePrice = buyexEntrust.getEntrustPrice();
                                        } else {
                                            if (i + 1 < size) {
                                                EntrustTrade buyexEntrustone = list.get(i + 1);
                                                buyonePrice = buyexEntrustone.getEntrustPrice();
                                            } else {
                                                buyonePrice = null;
                                            }
                                        }
                                    }

                                    break outterLoop;
                                }
                                i++;
                            }
                        }
                    }
                    if (sellentrust.getStatus().equals(0)) {
                        dealFundNoMatch(sellentrust);
                    } else {
                        TradeRedis.matchOneEnd(dealFundEntrust(sellentrust), sellentrust, maping, listed, buyonePrice);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    throw e;
                } finally {
                    TradeRedis.eoinfolists = new ArrayList<ExOrderInfo>();
                    TradeRedis.aaddlists = new ArrayList<Accountadd>();
                }
                // 处理资金问题，进入资金处理队列todo
            } else {
                // 保存
                dealFundNoMatch(sellentrust);

            }
        } else if (sellentrust.getEntrustWay().equals(2)) {

            // 卖家限价 //必须相等才匹配
            List<BigDecimal> keyslist = TradeRedis.getMatchkeys(sellentrust);  //查询出限价的对手单
            if (null != keyslist && keyslist.size() > 0) {
                try {
                    List<EntrustTrade> listed = new ArrayList<EntrustTrade>();
                    Map<String,List<EntrustTrade>> maping = new HashMap<String,List<EntrustTrade>>();
                    BigDecimal buyonePrice = null;
                    outterLoop:
                    for (BigDecimal keybig : keyslist) {
                        String keyall = TradeRedis.getHeaderMatch(sellentrust) + ":" + keybig.toString();
                        List<EntrustTrade> list = TradeRedis.getMatchEntrustTradeBykey(keyall);
                        if (null != list && list.size() > 0) {
                            maping.put(keyall, list);
                            int size = list.size();
                            int i = 0;
                            while (i < size) {
                                EntrustTrade buyexEntrust = list.get(i);
                                if (buyexEntrust.getEntrustPrice().compareTo(sellentrust.getEntrustPrice()) == -1) {
                                    buyonePrice = buyexEntrust.getEntrustPrice();
                                    break outterLoop;
                                }
                                matching(buyexEntrust, sellentrust, "sell");
                                if (buyexEntrust.getStatus().equals(2)) {
                                    list.remove(i);
                                    i--;
                                    size--;
                                }
                                listed.add(buyexEntrust);
                                // 如果匹配完了走出循环
                                if (sellentrust.getStatus().equals(2)) {
                                    if (!buyexEntrust.getStatus().equals(2)) {
                                        buyonePrice = buyexEntrust.getEntrustPrice();
                                    } else {
                                        if (i + 1 < size) {
                                            EntrustTrade buyexEntrustone = list.get(i + 1);
                                            buyonePrice = buyexEntrustone.getEntrustPrice();
                                        } else {
                                            buyonePrice = null;
                                        }
                                    }
                                    break outterLoop;
                                }
                                i++;
                            }
                        }
                    }
                    if (sellentrust.getStatus().equals(0)) {
                        dealFundNoMatch(sellentrust);
                    } else {
                        TradeRedis.matchOneEnd(dealFundEntrust(sellentrust), sellentrust, maping, listed, buyonePrice);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    throw e;
                } finally {
                    TradeRedis.eoinfolists = new ArrayList<ExOrderInfo>();
                    TradeRedis.aaddlists = new ArrayList<Accountadd>();
                }
                // 处理资金问题，进入资金处理队列todo
            } else {
                // 保存
                dealFundNoMatch(sellentrust);

            }

        }
    }

    public EntrustTrade newCreateCancelEntrustTrade(EntrustTrade ing) {
        EntrustTrade entrustTrade = new EntrustTrade();
        entrustTrade.setEntrustNum(ing.getEntrustNum());
        entrustTrade.setCoinCode(ing.getCoinCode());
        entrustTrade.setType(ing.getType());
        entrustTrade.setFixPriceCoinCode(ing.getFixPriceCoinCode());
        entrustTrade.setEntrustPrice(ing.getEntrustPrice());
        return entrustTrade;
    }

    public void matching(EntrustTrade buyexEntrust, EntrustTrade sellentrust, String initiative) {


        if (buyexEntrust.getSource().intValue() == 2) {  //深度机器人成交价格比当前行情*（1-差值率）
            String key = buyexEntrust.getCoinCode() + "_" + sellentrust.getFixPriceCoinCode();
            String currentPrice = TradeRedis.getStringData(key + ":" + ExchangeDataCacheRedis.CurrentExchangPrice);
            if (null == buyexEntrust.getOneDiffRate()) {
                EntrustTrade entrustTrade = newCreateCancelEntrustTrade(buyexEntrust);
                MQEnter.pushExEntrustMQ(entrustTrade);
                System.out.println("买方差值率为空matching撤销");
                return;
            }

            if (buyexEntrust.getEntrustPrice().compareTo(new BigDecimal(currentPrice).multiply((new BigDecimal("100").subtract(buyexEntrust.getOneDiffRate()))).divide(new BigDecimal("100"))) == 1) {
                EntrustTrade entrustTrade = newCreateCancelEntrustTrade(buyexEntrust);
                MQEnter.pushExEntrustMQ(entrustTrade);
                System.out.println("matching撤销");
                return;
            }

            BigDecimal tradeCount = buyexEntrust.getSurplusEntrustCount().compareTo(sellentrust.getSurplusEntrustCount()) <= 0 ? buyexEntrust.getSurplusEntrustCount() : sellentrust.getSurplusEntrustCount();
            // 本次交易数量不能为0
            if (tradeCount.compareTo(BigDecimal.ZERO) == 0) {
                return;
            } else {
                RedisService redisService = (RedisService) ContextUtil.getBean("redisService");
                String keycoin = buyexEntrust.getCoinCode() + "_" + buyexEntrust.getFixPriceCoinCode() + ":deeprobot:buydeeprobotCounted";
                String deepobotCounts = redisService.get(keycoin);
                BigDecimal klineRobotCount = null;
                if (StringUtils.isEmpty(deepobotCounts)) {
                    klineRobotCount = new BigDecimal("0");

                } else {
                    klineRobotCount = new BigDecimal(deepobotCounts);
                }
                klineRobotCount = klineRobotCount.add(tradeCount);
                redisService.save(keycoin, klineRobotCount.toString());

            }
        }
        if (sellentrust.getSource().intValue() == 2) {  //深度机器人成交价格
            String key = sellentrust.getCoinCode() + "_" + sellentrust.getFixPriceCoinCode();
            String currentPrice = TradeRedis.getStringData(key + ":" + ExchangeDataCacheRedis.CurrentExchangPrice);
            if (null == sellentrust.getOneDiffRate()) {
                EntrustTrade entrustTrade = newCreateCancelEntrustTrade(sellentrust);
                MQEnter.pushExEntrustMQ(entrustTrade);
                System.out.println("卖方差值率为空matching撤销");
                return;
            }
            if (sellentrust.getEntrustPrice().compareTo(new BigDecimal(currentPrice).multiply((new BigDecimal("100").add(sellentrust.getOneDiffRate()))).divide(new BigDecimal("100"))) == -1) {

                EntrustTrade entrustTrade = newCreateCancelEntrustTrade(sellentrust);
                MQEnter.pushExEntrustMQ(entrustTrade);
                System.out.println("matching撤销");
                return;
            }
            BigDecimal tradeCount = new BigDecimal("0");
            if (buyexEntrust.getEntrustWay().equals(1)) {
                tradeCount = buyexEntrust.getSurplusEntrustCount().compareTo(sellentrust.getSurplusEntrustCount()) <= 0 ? buyexEntrust.getSurplusEntrustCount() : sellentrust.getSurplusEntrustCount();

            } else { //买方为市价
                // 买家剩余委托金额
                BigDecimal buysurplusEntrusMoney = buyexEntrust.getEntrustSum().subtract(buyexEntrust.getTransactionSum());
                // 卖家剩余委托总金额
                BigDecimal sellsurplusEntrusMoney = sellentrust.getSurplusEntrustCount().multiply(sellentrust.getEntrustPrice());
                if (buysurplusEntrusMoney.compareTo(sellsurplusEntrusMoney) <= 0) {
                    Coin coin = getCoinFromreds(buyexEntrust.getCoinCode(), buyexEntrust.getFixPriceCoinCode());
                    tradeCount = buysurplusEntrusMoney.divide(sellentrust.getEntrustPrice(), coin.getKeepDecimalForCoin(), BigDecimal.ROUND_DOWN);
                }
                if (buysurplusEntrusMoney.compareTo(sellsurplusEntrusMoney) == 1) {
                    tradeCount = sellentrust.getSurplusEntrustCount();
                }
            }    // 本次交易数量不能为0
            if (tradeCount.compareTo(BigDecimal.ZERO) == 0) {
                return;
            } else {
                RedisService redisService = (RedisService) ContextUtil.getBean("redisService");
                String keycoin = buyexEntrust.getCoinCode() + "_" + buyexEntrust.getFixPriceCoinCode() + ":deeprobot:selldeeprobotCounted";
                String deepobotCounts = redisService.get(keycoin);
                BigDecimal klineRobotCount = null;
                if (StringUtils.isEmpty(deepobotCounts)) {
                    klineRobotCount = new BigDecimal("0");

                } else {
                    klineRobotCount = new BigDecimal(deepobotCounts);
                }
                klineRobotCount = klineRobotCount.add(tradeCount);
                redisService.save(keycoin, klineRobotCount.toString());

            }
        }


        // 买家限价（必须相等才匹配）
        if (buyexEntrust.getEntrustWay().equals(1)) {
            // (1)买家限价，卖家限价
            if (sellentrust.getEntrustWay().equals(1)) {
                oneCase(buyexEntrust, sellentrust, initiative);
                // (2)买家限价，卖家市价
            } else if (sellentrust.getEntrustWay().equals(2)) {
                twoCase(buyexEntrust, sellentrust, initiative);
            }
            // 买家市价 只要未完成的卖家都可以
        } else if (buyexEntrust.getEntrustWay().equals(2)) {
            // (3)买家市价，卖家限价
            if (sellentrust.getEntrustWay().equals(1)) {
                threeCase(buyexEntrust, sellentrust);
                // (4)买家市价，卖家市价
            } else if (sellentrust.getEntrustWay().equals(2)) {
                fourCase(buyexEntrust, sellentrust);
            }
        }
    }

    // (1)买家限价，卖家限价
    public void oneCase(EntrustTrade buyexEntrust, EntrustTrade sellentrust, String initiative) {
        // 谁小取谁，获取本次交易币的个数
        BigDecimal tradeCount = buyexEntrust.getSurplusEntrustCount().compareTo(sellentrust.getSurplusEntrustCount()) <= 0 ? buyexEntrust.getSurplusEntrustCount() : sellentrust.getSurplusEntrustCount();
        // 本次交易数量不能为0
        if (tradeCount.compareTo(BigDecimal.ZERO) == 0) {
            return;
        }
        // 交易单价为买单的交易价
        BigDecimal tradePrice = buyexEntrust.getEntrustPrice();
        // 本次交易单价不能为0
        if (tradePrice.compareTo(BigDecimal.ZERO) == 0) {
            return;
        }
        if (sellentrust.getEntrustWay().intValue() == 1 && sellentrust.getEntrustPrice().compareTo(BigDecimal.ZERO) == 0) {
            return;
        }

        // 不相等的情况说明是有浮动的，得求最优成交价（卖家限价，并且价格不相等）
        if (sellentrust.getEntrustWay().intValue() == 1 && buyexEntrust.getEntrustPrice().compareTo(sellentrust.getEntrustPrice()) != 0) {
            BigDecimal[] array = new BigDecimal[4];
            // 买家上限浮动的价格
            array[0] = buyexEntrust.getEntrustPrice().add(buyexEntrust.getFloatUpPrice());
            // 买家下限浮动的价格
            array[1] = buyexEntrust.getEntrustPrice().subtract(buyexEntrust.getFloatDownPrice());
            // 卖家上限浮动的价格
            array[2] = sellentrust.getEntrustPrice().add(sellentrust.getFloatUpPrice());
            // 卖家下限浮动的价格
            array[3] = sellentrust.getEntrustPrice().subtract(sellentrust.getFloatDownPrice());
            java.util.Arrays.sort(array);
            if (initiative.equals("buy")) {// 买主动
                tradePrice = array[1];// 买家当然是价格越低越好
            } else if (initiative.equals("sell")) {// 卖主动
                tradePrice = array[2];// 卖家当然然是价格越低高越好
            }

        }

        dealmatchend(buyexEntrust, sellentrust, tradeCount, tradePrice, initiative);
    }

    // (2)买家限价，卖家市价
    public void twoCase(EntrustTrade buyexEntrust, EntrustTrade sellentrust, String initiative) {
        oneCase(buyexEntrust, sellentrust, initiative);
    }

    public Coin getCoinFromreds(String coinCode, String fixPriceCoinCode) {

        String str = redisService.get("cn:coinInfoList");
        if (!StringUtils.isEmpty(str)) {
            List<Coin> coins = JSON.parseArray(str, Coin.class);
            if (coins != null && coins.size() > 0) {
                for (Coin coin : coins) {
                    if (coinCode.equals(coin.getCoinCode()) && fixPriceCoinCode.equals(coin.getFixPriceCoinCode())) {
                        return coin;
                    }
                }
            }
        }
        return null;

    }

    // (3)买家市价，卖家限价
    public void threeCase(EntrustTrade buyexEntrust, EntrustTrade sellentrust) {

        // 买家剩余委托金额
        BigDecimal buysurplusEntrusMoney = buyexEntrust.getEntrustSum().subtract(buyexEntrust.getTransactionSum());
        // 卖家剩余委托总金额
        BigDecimal sellsurplusEntrusMoney = sellentrust.getSurplusEntrustCount().multiply(sellentrust.getEntrustPrice());

        BigDecimal tradeCount = new BigDecimal("0");
        if (buysurplusEntrusMoney.compareTo(sellsurplusEntrusMoney) <= 0) {
            Coin coin = getCoinFromreds(buyexEntrust.getCoinCode(), buyexEntrust.getFixPriceCoinCode());
            tradeCount = buysurplusEntrusMoney.divide(sellentrust.getEntrustPrice(), coin.getKeepDecimalForCoin(), BigDecimal.ROUND_DOWN);
        }
        if (buysurplusEntrusMoney.compareTo(sellsurplusEntrusMoney) == 1) {
            tradeCount = sellentrust.getSurplusEntrustCount();
        }
        if (tradeCount.compareTo(new BigDecimal(0)) <= 0) {
            return;
        }
        BigDecimal tradePrice = sellentrust.getEntrustPrice();

        if (tradePrice.compareTo(new BigDecimal(0)) <= 0) {
            return;
        }

        if (buysurplusEntrusMoney.compareTo(sellsurplusEntrusMoney) <= 0) {
            buyexEntrust.setStatus(2);
        }
        dealmatchend(buyexEntrust, sellentrust, tradeCount, tradePrice, "buy");
    }

    // (4)买家市价，卖家市价
    public void fourCase(EntrustTrade buyexEntrust, EntrustTrade sellentrust) {
        /*
         *
         * String tradePricestring =
         * ExchangeDataCache.getStringData(buyexEntrust.getWebsite() + ":" +
         * buyexEntrust.getCurrencyType() + ":" + buyexEntrust.getCoinCode() +
         * ":" + ExchangeDataCache.CurrentExchangPrice);
         *
         * if (null != tradePricestring && new
         * BigDecimal(tradePricestring).compareTo(new BigDecimal("0")) != 0) {
         * BigDecimal tradePrice = new BigDecimal(tradePricestring); if
         * (tradePrice.compareTo(new BigDecimal(0)) == 0) { return; } //
         * 买家剩余委托金额 BigDecimal buysurplusEntrusMoney =
         * buyexEntrust.getEntrustSum().subtract(buyexEntrust.getTransactionSum(
         * )); // 卖家剩余委托总金额 BigDecimal sellsurplusEntrusMoney =
         * sellentrust.getSurplusEntrustCount().multiply(tradePrice); BigDecimal
         * tradeCount = new BigDecimal("0"); if
         * (buysurplusEntrusMoney.compareTo(sellsurplusEntrusMoney) <= 0) {
         * tradeCount = buysurplusEntrusMoney.divide(tradePrice, 4,
         * BigDecimal.ROUND_DOWN); buyexEntrust.setStatus(2); } if
         * (buysurplusEntrusMoney.compareTo(sellsurplusEntrusMoney) == 1) {
         * tradeCount = sellentrust.getSurplusEntrustCount();
         *
         * } if (tradeCount.compareTo(new BigDecimal(0)) == 0) { return; }
         * dealmatchend( buyexEntrust, sellentrust, tradeCount, tradePrice,
         * "buy");
         *
         * }
         */
    }

    public void dealmatchend(EntrustTrade buyexEntrust, EntrustTrade sellentrust, BigDecimal tradeCount, BigDecimal tradePrice, String initiative) {

        ExOrderInfo exOrderInfo = exOrderInfoService.createExOrderInfo(1, buyexEntrust, sellentrust, tradeCount, tradePrice);
        exOrderInfo.setInOrOutTransaction(initiative.equals("buy") ? "sell" : "buy");
        updatebuyExEntrust(buyexEntrust, sellentrust, exOrderInfo);
        updatesellExEntrust(buyexEntrust, sellentrust, exOrderInfo);
        deductMoney(exOrderInfo, buyexEntrust, sellentrust);
    }

    public void deductMoney(ExOrderInfo exOrderInfo, EntrustTrade buyexEntrust, EntrustTrade sellentrust) {
        if (buyexEntrust.getFixPriceType() == 0) {// 如果是定价是真实货币
            this.deductMoneyByaccount(exOrderInfo, buyexEntrust, sellentrust);
        } else { // 如果定价是虚拟货币
            this.deductMoneyByExDigita(exOrderInfo, buyexEntrust, sellentrust);
        }
    }

    public Accountadd getAccountadd(Integer acccountType, Long accountId, BigDecimal money, Integer monteyType, Integer remarks, String transactionNum) {
        Accountadd accountadd = new Accountadd();
        accountadd.setAcccountType(acccountType);
        accountadd.setMoney(money);
        accountadd.setAccountId(accountId);
        accountadd.setMonteyType(monteyType);
        accountadd.setTransactionNum(transactionNum);
        accountadd.setRemarks(remarks);
        // accountadd.setRemarks(remarks);
        return accountadd;
    }

    public BigDecimal fu(BigDecimal money) {
        return new BigDecimal("0").subtract(money);
    }

    public void deductMoneyByaccount(ExOrderInfo exOrderInfo, EntrustTrade buyexEntrust, EntrustTrade sellentrust) {
        List<Accountadd> aaddlists = new ArrayList<Accountadd>();
        BigDecimal buychangHotMoney = BigDecimal.ZERO;
        BigDecimal buychangColdMoney = BigDecimal.ZERO;
        // 买家人民币账户变动，添加一条冷钱包记录 unfreezeAccountThemBuyTranstion.
        String transactionNumbuy = buyexEntrust.getEntrustNum() + "," + exOrderInfo.getOrderNum();
        String transactionNumsell = sellentrust.getEntrustNum() + "," + exOrderInfo.getOrderNum();
        Accountadd accountadd5 = getAccountadd(0, buyexEntrust.getAccountId(), fu(exOrderInfo.getTransactionSum()), 2, 2, transactionNumbuy);
        aaddlists.add(accountadd5);
        // 买家是市价
        if (buyexEntrust.getEntrustWay().equals(2) && buyexEntrust.getStatus().equals(2)) { // 买家是市价
            BigDecimal surpSum = buyexEntrust.getEntrustSum().subtract(buyexEntrust.getTransactionSum());
            // 剩余委托金额
            if (surpSum.compareTo(BigDecimal.ZERO) > 0) {
                Accountadd accountadd2 = getAccountadd(0, buyexEntrust.getAccountId(), fu(surpSum), 2, 3, transactionNumbuy);
                aaddlists.add(accountadd2);
                Accountadd accountadd1 = getAccountadd(0, buyexEntrust.getAccountId(), surpSum, 1, 4, transactionNumbuy);
                aaddlists.add(accountadd1);
            }
            // 买家是限价
        } else if ((buyexEntrust.getEntrustWay().equals(1)) && buyexEntrust.getStatus().equals(2)) {
            if (buyexEntrust.getEntrustSum().compareTo(buyexEntrust.getTransactionSum()) == 1) {
                BigDecimal surpSum = buyexEntrust.getEntrustSum().subtract(buyexEntrust.getTransactionSum());
                Accountadd accountadd3 = getAccountadd(0, buyexEntrust.getAccountId(), fu(surpSum), 2, 5, transactionNumbuy);
                aaddlists.add(accountadd3);
                Accountadd accountadd4 = getAccountadd(0, buyexEntrust.getAccountId(), surpSum, 1, 6, transactionNumbuy);
                aaddlists.add(accountadd4);
            }
        }

        BigDecimal sellchangHotMoney = BigDecimal.ZERO;
        // 卖家资金变化
        BigDecimal incomeMoney = exOrderInfo.getTransactionSum();
        sellchangHotMoney = sellchangHotMoney.add(incomeMoney);
        Accountadd accountadd7 = getAccountadd(0, sellentrust.getAccountId(), incomeMoney, 1, 7, transactionNumsell);
        aaddlists.add(accountadd7);
        // 卖家手续费
        if (exOrderInfo.getTransactionSellFee().compareTo(new BigDecimal("0")) == 1) {
            Accountadd accountadd8 = getAccountadd(0, sellentrust.getAccountId(), fu(exOrderInfo.getTransactionSellFee()), 1, 8, transactionNumsell);
            aaddlists.add(accountadd8);
        }

        BigDecimal buycoinchangHotMoney = BigDecimal.ZERO;
        BigDecimal sellcoinchangColdMoney = BigDecimal.ZERO;
        // 买家获得币

        BigDecimal incomecoin = exOrderInfo.getTransactionCount();
        Accountadd coinaccountadd1 = getAccountadd(1, buyexEntrust.getCoinAccountId(), incomecoin, 1, 9, transactionNumbuy);
        aaddlists.add(coinaccountadd1);
        // "交易成功，买家手续费"
        if (exOrderInfo.getTransactionBuyFee().compareTo(new BigDecimal("0")) == 1) {
            Accountadd accountadd6 = getAccountadd(1, buyexEntrust.getCoinAccountId(), fu(exOrderInfo.getTransactionBuyFee()), 1, 10, transactionNumbuy);
            aaddlists.add(accountadd6);
        }
        // 卖家支出币
        Accountadd coinaccountadd2 = getAccountadd(1, sellentrust.getCoinAccountId(), fu(incomecoin), 2, 11, transactionNumsell);
        aaddlists.add(coinaccountadd2);

        //因为加平台币手续费把之前的初始化方法加手续费的注释掉了，所以放这里来加了
        buyexEntrust.setTransactionFee(buyexEntrust.getTransactionFee().add(exOrderInfo.getTransactionBuyFee()));
        sellentrust.setTransactionFee(sellentrust.getTransactionFee().add(exOrderInfo.getTransactionSellFee()));
        // 缓存成交信息
        TradeRedis.matchOneAndOneEnd(exOrderInfo, aaddlists);

    }

    public void deductMoneyByExDigita(ExOrderInfo exOrderInfo, EntrustTrade buyexEntrust, EntrustTrade sellentrust) {
        List<Accountadd> aaddlists = new ArrayList<Accountadd>();
        BigDecimal buychangHotMoney = BigDecimal.ZERO;
        BigDecimal buychangColdMoney = BigDecimal.ZERO;
        // 买家人民币账户变动，添加一条冷钱包记录 unfreezeAccountThemBuyTranstion.
        String transactionNumbuy = buyexEntrust.getEntrustNum() + "," + exOrderInfo.getOrderNum();
        String transactionNumsell = sellentrust.getEntrustNum() + "," + exOrderInfo.getOrderNum();
        Accountadd accountadd5 = getAccountadd(1, buyexEntrust.getAccountId(), fu(exOrderInfo.getTransactionSum()), 2, 2, transactionNumbuy);
        aaddlists.add(accountadd5);
        // 买家是市价
        if (buyexEntrust.getEntrustWay().equals(2) && buyexEntrust.getStatus().equals(2)) { // 买家是市价
            BigDecimal surpSum = buyexEntrust.getEntrustSum().subtract(buyexEntrust.getTransactionSum());
            // 剩余委托金额
            if (surpSum.compareTo(BigDecimal.ZERO) > 0) {
                Accountadd accountadd2 = getAccountadd(1, buyexEntrust.getAccountId(), fu(surpSum), 2, 3, transactionNumbuy);
                aaddlists.add(accountadd2);
                Accountadd accountadd1 = getAccountadd(1, buyexEntrust.getAccountId(), surpSum, 1, 4, transactionNumbuy);
                aaddlists.add(accountadd1);
            }
            // 买家是限价
        } else if ((buyexEntrust.getEntrustWay().equals(1)) && buyexEntrust.getStatus().equals(2)) {
            if (buyexEntrust.getEntrustSum().compareTo(buyexEntrust.getTransactionSum()) == 1) {
                BigDecimal surpSum = buyexEntrust.getEntrustSum().subtract(buyexEntrust.getTransactionSum());
                Accountadd accountadd3 = getAccountadd(1, buyexEntrust.getAccountId(), fu(surpSum), 2, 5, transactionNumbuy);
                aaddlists.add(accountadd3);
                Accountadd accountadd4 = getAccountadd(1, buyexEntrust.getAccountId(), surpSum, 1, 6, transactionNumbuy);
                aaddlists.add(accountadd4);
            }
        }

        BigDecimal sellchangHotMoney = BigDecimal.ZERO;
        // 卖家资金变化
        BigDecimal incomeMoney = exOrderInfo.getTransactionSum();
        sellchangHotMoney = sellchangHotMoney.add(incomeMoney);
        Accountadd accountadd7 = getAccountadd(1, sellentrust.getAccountId(), incomeMoney, 1, 7, transactionNumsell);
        aaddlists.add(accountadd7);
        //start----- 卖家手续费
        //下单：0正常手续费收取1，平台币收取手续费）
        if (exOrderInfo.getTransactionSellFee().compareTo(new BigDecimal("0")) == 1) {
            int falg = 0;//0正常手续费，1手续费收平台币
            if (null != sellentrust.getIsOpenCoinFee() && sellentrust.getIsOpenCoinFee().compareTo(0) == 1) {  //1，平台币收取手续费
                //算出要支出多少平台币
                String paltCoin = sellentrust.getPlatCoin();
                exOrderInfo.setSellPlatCoin(paltCoin);
                BigDecimal chargePlat = null;
                //定价币和平台币的当前价
                String key = exOrderInfo.getFixPriceCoinCode() + "_" + paltCoin + ":" + ExchangeDataCacheRedis.CurrentExchangPrice;
                String currentExchangPrice = redisService.get(key);
                if (!StringUtils.isEmpty(currentExchangPrice)) {
                    chargePlat = new BigDecimal(currentExchangPrice).multiply(exOrderInfo.getTransactionSellFee());
                    if (!StringUtils.isEmpty(sellentrust.getTransactionFeeRateDiscount())) {
                        chargePlat = chargePlat.multiply(new BigDecimal(sellentrust.getTransactionFeeRateDiscount())).divide(new BigDecimal("100"));

                    }
                    //先判断有没有足够的平台币够不够
                    UserRedis userRedis = UserRedisInitUtil.getUserRedis(sellentrust.getCustomerId());
                    ExDigitalmoneyAccountRedis coinaccount = exDigitalmoneyAccountService.getExDigitalmoneyAccountByRedis(userRedis.getDmAccountId(paltCoin).toString());
                    //平台币够的情况
                    if (coinaccount.getHotMoney().compareTo(chargePlat) >= 0) {
                        exOrderInfo.setTransactionSellFee(new BigDecimal("0"));
                        exOrderInfo.setTransactionSellFeePlat(chargePlat);
                        exOrderInfo.setFixPriceCoinCodePrice(new BigDecimal(currentExchangPrice));
                        sellentrust.setTransactionFeePlat(sellentrust.getTransactionFeePlat().add(chargePlat));
                        Accountadd accountadd8 = getAccountadd(1, coinaccount.getId(), fu(exOrderInfo.getTransactionSellFeePlat()), 1, 8, transactionNumsell);
                        aaddlists.add(accountadd8);
                        falg = 1;
                    }
                }
            }

            if (falg == 0) {
                sellentrust.setTransactionFee(sellentrust.getTransactionFee().add(exOrderInfo.getTransactionSellFee()));
                exOrderInfo.setTransactionSellFeePlat(new BigDecimal("0"));
                Accountadd accountadd8 = getAccountadd(1, sellentrust.getAccountId(), fu(exOrderInfo.getTransactionSellFee()), 1, 8, transactionNumsell);
                aaddlists.add(accountadd8);
            }

        }
        //end----- 卖家手续费
        BigDecimal buycoinchangHotMoney = BigDecimal.ZERO;
        BigDecimal sellcoinchangColdMoney = BigDecimal.ZERO;
        // 买家获得币
        BigDecimal incomecoin = exOrderInfo.getTransactionCount();
        Accountadd coinaccountadd1 = getAccountadd(1, buyexEntrust.getCoinAccountId(), incomecoin, 1, 9, transactionNumbuy);
        aaddlists.add(coinaccountadd1);
        // 买家支出手续费
        if (exOrderInfo.getTransactionBuyFee().compareTo(new BigDecimal("0")) == 1) {
            int falg = 0;//0正常手续费，1手续费收平台币
            if (null != buyexEntrust.getIsOpenCoinFee() && buyexEntrust.getIsOpenCoinFee().compareTo(0) == 1) {  //1，平台币收取手续费
                //算出要支出多少平台币
                String paltCoin = buyexEntrust.getPlatCoin();
                exOrderInfo.setBuyPlatCoin(paltCoin);
                BigDecimal chargePlat = null;
                //定价币和平台币的当前价
                String key = exOrderInfo.getCoinCode() + "_" + paltCoin + ":" + ExchangeDataCacheRedis.CurrentExchangPrice;
                String currentExchangPrice = redisService.get(key);
                if (!StringUtils.isEmpty(currentExchangPrice)) {
                    chargePlat = new BigDecimal(currentExchangPrice).multiply(exOrderInfo.getTransactionBuyFee());
                    if (!StringUtils.isEmpty(buyexEntrust.getTransactionFeeRateDiscount())) {
                        chargePlat = chargePlat.multiply(new BigDecimal(buyexEntrust.getTransactionFeeRateDiscount())).divide(new BigDecimal("100"));

                    }
                    //先判断有没有足够的平台币够不够
                    UserRedis userRedis = UserRedisInitUtil.getUserRedis(buyexEntrust.getCustomerId());
                    ExDigitalmoneyAccountRedis coinaccount = exDigitalmoneyAccountService.getExDigitalmoneyAccountByRedis(userRedis.getDmAccountId(paltCoin).toString());
                    //平台币够的情况
                    if (coinaccount.getHotMoney().compareTo(chargePlat) >= 0) {
                        exOrderInfo.setTransactionBuyFee(new BigDecimal("0"));
                        exOrderInfo.setTransactionBuyFeePlat(chargePlat);
                        exOrderInfo.setCoinCodePrice(new BigDecimal(currentExchangPrice));
                        buyexEntrust.setTransactionFeePlat(buyexEntrust.getTransactionFeePlat().add(chargePlat));
                        Accountadd accountadd8 = getAccountadd(1, coinaccount.getId(), fu(exOrderInfo.getTransactionBuyFeePlat()), 1, 8, transactionNumsell);
                        aaddlists.add(accountadd8);
                        falg = 1;
                    }
                }
            }

            if (falg == 0) {
                buyexEntrust.setTransactionFee(buyexEntrust.getTransactionFee().add(exOrderInfo.getTransactionBuyFee()));
                exOrderInfo.setTransactionBuyFeePlat(new BigDecimal("0"));
                Accountadd accountadd6 = getAccountadd(1, buyexEntrust.getCoinAccountId(), fu(exOrderInfo.getTransactionBuyFee()), 1, 10, transactionNumbuy);
                aaddlists.add(accountadd6);
            }
        }
        if (null == exOrderInfo.getTransactionBuyFeePlat()) {
            exOrderInfo.setTransactionBuyFeePlat(new BigDecimal("0"));
        }
        if (null == exOrderInfo.getTransactionSellFeePlat()) {
            exOrderInfo.setTransactionSellFeePlat(new BigDecimal("0"));
        }
        // 卖家支出币
        Accountadd coinaccountadd2 = getAccountadd(1, sellentrust.getCoinAccountId(), fu(incomecoin), 2, 11, transactionNumsell);
        aaddlists.add(coinaccountadd2);

        // 缓存成交信息
        TradeRedis.matchOneAndOneEnd(exOrderInfo, aaddlists);

    }

    public void updatebuyExEntrust(EntrustTrade buyExEntrust, EntrustTrade sellentrust, ExOrderInfo exOrderInfo) {

        buyExEntrust.setSurplusEntrustCount(buyExEntrust.getSurplusEntrustCount().subtract(exOrderInfo.getTransactionCount()));
        //	buyExEntrust.setTransactionFee(buyExEntrust.getTransactionFee().add(exOrderInfo.getTransactionBuyFee()));
        buyExEntrust.setTransactionSum(buyExEntrust.getTransactionSum().add(exOrderInfo.getTransactionSum()));
        // 平均价格
        buyExEntrust.setProcessedPrice(buyExEntrust.getTransactionSum().divide(buyExEntrust.getEntrustCount().subtract(buyExEntrust.getSurplusEntrustCount()), 10, BigDecimal.ROUND_HALF_UP));

        // 如果是市价
        if (buyExEntrust.getEntrustWay().equals(2)) {

            if (!buyExEntrust.getStatus().equals(2)) {
                buyExEntrust.setStatus(1);
            }

            // }
        } else {// 是限价，还是普通价格优先都有剩余个数
            // 剩余个数为0，说明已完成
            if (buyExEntrust.getSurplusEntrustCount().compareTo(BigDecimal.ZERO) <= 0) {
                buyExEntrust.setStatus(2);
            } else {
                buyExEntrust.setStatus(1);

            }
        }
    }

    public void updatesellExEntrust(EntrustTrade buyExEntrust, EntrustTrade sellentrust, ExOrderInfo exOrderInfo) {
        sellentrust.setSurplusEntrustCount(sellentrust.getSurplusEntrustCount().subtract(exOrderInfo.getTransactionCount()));
//		sellentrust.setTransactionFee(sellentrust.getTransactionFee().add(exOrderInfo.getTransactionSellFee()));
        sellentrust.setTransactionSum(sellentrust.getTransactionSum().add(exOrderInfo.getTransactionSum()));

        // 平均价格
        sellentrust.setProcessedPrice(sellentrust.getTransactionSum().divide(sellentrust.getEntrustCount().subtract(sellentrust.getSurplusEntrustCount()), 8, BigDecimal.ROUND_HALF_UP));

        // 剩余个数为0，说明已完成（卖家不管是限价还是市价就会有余额这个值）
        sellentrust.setStatus(1);
        if (sellentrust.getSurplusEntrustCount().compareTo(new BigDecimal(0)) <= 0) {
            sellentrust.setStatus(2);
        }
    }

    public void dealFundNoMatch(EntrustTrade exEntrust) {
        long start3 = System.currentTimeMillis();
        TradeRedis.NoMatchEnd(exEntrust, dealFundEntrust(exEntrust));
        long end3 = System.currentTimeMillis();
        // log.info("匹配失败业务逻辑end：" + (end3 - start3) + "毫秒");
    }

    public List<Accountadd> dealFundEntrust(EntrustTrade exEntrust) {
        List<Accountadd> aaddlists = new ArrayList<Accountadd>();
        String transactionNum = exEntrust.getEntrustNum();
        if (exEntrust.getType().equals(1)) {// 买单
            if (exEntrust.getFixPriceType().equals(0)) { // 定价真实货币
                BigDecimal freezeMoney = exEntrust.getEntrustSum();
                // 重新计算冷热钱包的总额
                Accountadd accountadd1 = getAccountadd(0, exEntrust.getAccountId(), fu(freezeMoney), 1, 1, transactionNum);
                aaddlists.add(accountadd1);
                Accountadd accountadd2 = getAccountadd(0, exEntrust.getAccountId(), freezeMoney, 2, 1, transactionNum);
                aaddlists.add(accountadd2);
            } else {// 定价虚拟货币
                BigDecimal freezeMoney = exEntrust.getEntrustSum();
                Accountadd accountadd1 = getAccountadd(1, exEntrust.getAccountId(), fu(freezeMoney), 1, 1, transactionNum);
                aaddlists.add(accountadd1);
                Accountadd accountadd2 = getAccountadd(1, exEntrust.getAccountId(), freezeMoney, 2, 1, transactionNum);
                aaddlists.add(accountadd2);
            }

        } else if (exEntrust.getType().equals(2)) {//
            BigDecimal freezeMoney = exEntrust.getEntrustCount();
            Accountadd accountadd1 = getAccountadd(1, exEntrust.getCoinAccountId(), fu(freezeMoney), 1, 1, transactionNum);
            aaddlists.add(accountadd1);
            Accountadd accountadd2 = getAccountadd(1, exEntrust.getCoinAccountId(), freezeMoney, 2, 1, transactionNum);
            aaddlists.add(accountadd2);
        }

        return aaddlists;
    }

    public String accountOrderNum(Date date, Integer type) {
        String randomStr = RandomStringUtils.random(3, false, true);
        SimpleDateFormat format = new SimpleDateFormat("yyMMddHHmmss");
        String times = format.format(date);
        String time = type + "account" + times;
        String v = redisService.get(time);
        if (!StringUtils.isEmpty(v)) {
            Integer aa = Integer.valueOf(v) + 1;
            String bb = aa.toString();
            if (aa.compareTo(10) == -1) {
                bb = "000000" + aa.toString();
            } else if (aa.compareTo(100) == -1) {
                bb = "00000" + aa.toString();
            } else if (aa.compareTo(1000) == -1) {
                bb = "0000" + aa.toString();
            } else if (aa.compareTo(10000) == -1) {
                bb = "000" + aa.toString();
            } else if (aa.compareTo(10000) == -1) {
                bb = "000" + aa.toString();
            } else if (aa.compareTo(100000) == -1) {
                bb = "00" + aa.toString();
            } else if (aa.compareTo(1000000) == -1) {
                bb = "0" + aa.toString();
            }
            redisService.save(time, aa.toString(), 1);
            return times + bb + randomStr;
        } else {
            redisService.save(time, "1", 1);
            return times + "0000001" + randomStr;
        }

    }

    @Override
    public Boolean accountaddQueue(String accoutadds) {
        Boolean flag = true;
        JedisPool jedisPool = (JedisPool) ContextUtil.getBean("jedisPool");
        Jedis jedis = jedisPool.getResource();

        /**==============-------社交-------==============**/
        /**账户推送**/
        Long customerId = null;
        String platCoin = BaseConfUtil.getConfigSingle("platCoin", "configCache:basefinanceConfig");
        /**==============-------社交-------==============**/

        try {
            redis.clients.jedis.Transaction transaction = jedis.multi();
            List<Accountadd> accountaddlist = JSON.parseArray(accoutadds, Accountadd.class);

            for (Accountadd accountadd : accountaddlist) {

                if (null == accountadd.getAccountId()) {
                    log.info("账户有为空的==" + accoutadds);
                    return flag;
                }
                if (null == accountadd.getMoney()) {
                    log.info("money为空==" + accoutadds);
                    return flag;
                }
            }

            AccountaddComparator accountaddComparator = new AccountaddComparator();
            Collections.sort(accountaddlist, accountaddComparator);
            Long coinaccountId = null;
            ExDigitalmoneyAccountRedis coinaccount = null;
            Long accountId = null;
            AppAccountRedis appAccount = null;
            for (Accountadd accountadd : accountaddlist) {
                Integer type = null;
                if (accountadd.getAcccountType().equals(1)) {
                    if (null == coinaccountId || accountadd.getAccountId().compareTo(coinaccountId) != 0) {
                        if (null != coinaccount) {
                            RuntimeSchema<ExDigitalmoneyAccountRedis> schema = RuntimeSchema.createFrom(ExDigitalmoneyAccountRedis.class);
                            byte[] bytes = ProtostuffIOUtil.toByteArray(coinaccount, schema, LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
                            String key = "RedisDB:" + ExDigitalmoneyAccountRedis.class.getName().replace(".", ":") + ":" + coinaccount.getId();
                            transaction.del(key.getBytes());
                            transaction.set(key.getBytes(), bytes);
                        }

                        coinaccount = exDigitalmoneyAccountService.getExDigitalmoneyAccountByRedis(accountadd.getAccountId().toString());
                        coinaccountId = accountadd.getAccountId();

                        /**==============-------社交-------==============**/
                        /**平台币账户推送**/
                        String coinCode = coinaccount.getCoinCode();
                        if (!StringUtils.isEmpty(platCoin) && platCoin.equals(coinCode)){
                            customerId = coinaccount.getCustomerId();
                        }
                        /**==============-------社交-------==============**/

                    }
                    if (null != coinaccount) {
                        if (accountadd.getMonteyType().equals(1)) {
                            accountadd.setBalanceMoney(coinaccount.getHotMoney());
                            coinaccount.setHotMoney(coinaccount.getHotMoney().add(accountadd.getMoney()));
                            type = 1;
                        } else {
                            accountadd.setBalanceMoney(coinaccount.getColdMoney());
                            coinaccount.setColdMoney(coinaccount.getColdMoney().add(accountadd.getMoney()));
                            type = 2;
                        }

                        //	exDigitalmoneyAccountService.setExDigitalmoneyAccounttoRedis(coinaccount);
                    } else {
                        log.info("mq:redis资金账户没有查到==" + accountadd.getAccountId());
                    }

                } else {
                    if (null == accountId || accountadd.getAccountId().compareTo(accountId) != 0) {
                        if (null != appAccount) {
                            RuntimeSchema<AppAccountRedis> schema = RuntimeSchema.createFrom(AppAccountRedis.class);
                            byte[] bytes = ProtostuffIOUtil.toByteArray(appAccount, schema, LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
                            String key = "RedisDB:" + AppAccountRedis.class.getName().replace(".", ":") + ":" + appAccount.getId();
                            transaction.del(key.getBytes());
                            transaction.set(key.getBytes(), bytes);

                        }
                        appAccount = appAccountService.getAppAccountByRedis(accountadd.getAccountId().toString());

                        accountId = accountadd.getAccountId();
                    }

                    if (null != appAccount) {
                        if (accountadd.getMonteyType().equals(1)) {
                            accountadd.setBalanceMoney(appAccount.getHotMoney());
                            appAccount.setHotMoney(appAccount.getHotMoney().add(accountadd.getMoney()));
                            type = 3;
                        } else {
                            accountadd.setBalanceMoney(appAccount.getColdMoney());
                            appAccount.setColdMoney(appAccount.getColdMoney().add(accountadd.getMoney()));
                            type = 4;
                        }
                    } else {

                        log.info("mq:redis虚拟账户没有查到==" + accountadd.getAccountId());
                    }

                }

                accountadd.setOrderNum(accountOrderNum(new Date(), type));
            }

            transaction.set(TradeRedis.getTradeDealAccountChangeNum(transaction), JSON.toJSONString(accountaddlist));
            if (null != coinaccount) {
                RuntimeSchema<ExDigitalmoneyAccountRedis> schema = RuntimeSchema.createFrom(ExDigitalmoneyAccountRedis.class);
                byte[] bytes = ProtostuffIOUtil.toByteArray(coinaccount, schema, LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
                String key = "RedisDB:" + ExDigitalmoneyAccountRedis.class.getName().replace(".", ":") + ":" + coinaccount.getId();
                transaction.del(key.getBytes());
                transaction.set(key.getBytes(), bytes);
            }

            if (null != appAccount) {
                RuntimeSchema<AppAccountRedis> schema1 = RuntimeSchema.createFrom(AppAccountRedis.class);
                byte[] bytes1 = ProtostuffIOUtil.toByteArray(appAccount, schema1, LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
                String key1 = "RedisDB:" + AppAccountRedis.class.getName().replace(".", ":") + ":" + appAccount.getId();
                transaction.del(key1.getBytes());
                transaction.set(key1.getBytes(), bytes1);
            }


            List<Object> list = transaction.exec();
            if (null == list || list.size() == 0) {
                throw new RuntimeException();
            }

            /**==============-------社交-------==============**/
            /**平台币账户推送**/
            Map<String,String> moduleMap = redisService.getMap("APP:MODULE");
            if (moduleMap != null && "true".equalsIgnoreCase(moduleMap.get("app.module.social"))) {
                if (!StringUtils.isEmpty(platCoin) && customerId != null){
                    AccountSyncMessage accountSyncMessage = new AccountSyncMessage();
                    accountSyncMessage.setCustomerId(customerId);
                    accountSyncMessage.setCoinCode(platCoin);
                    messageProducer.syncAccount(JSON.toJSONString(accountSyncMessage));
                }

            }
            /**==============-------社交-------==============**/


        } catch (Exception e) {
            AppException exceptionLog = new AppException();
            exceptionLog.setName("mq==accountaddQueue==");
            AppExceptionService appExceptionService = (AppExceptionService) ContextUtil.getBean("appExceptionService");
            appExceptionService.save(exceptionLog);
            System.out.println("mq==accountaddQueue==" + accoutadds);

            throw e;
        } finally {
            jedis.close();
        }

        return flag;
        //	return flag;

        /*
         * // 添加资金记录 RedisService redisService = (RedisService)
         * ContextUtil.getBean("redisService"); String v =
         * redisService.get(ExchangeDataCacheRedis.AccountAddS);
         * List<Accountadd> list = JSON.parseArray(v, Accountadd.class); if
         * (null == list) { list = new ArrayList<Accountadd>(); }
         * list.addAll(accountaddlist);
         * redisService.save(ExchangeDataCacheRedis.AccountAddS,
         * JSON.toJSONString(list));
         */

    }

    public String getFinanceByKey(String key) {
        String val = "";
        String string = redisService.get(StringConstant.CONFIG_CACHE + ":financeConfig");
        JSONArray obj = JSON.parseArray(string);
        for (Object o : obj) {
            JSONObject oo = JSON.parseObject(o.toString());
            if (oo.getString("configkey").equals(key)) {
                val = oo.getString("value");
            }
        }
        return val;
    }
}
