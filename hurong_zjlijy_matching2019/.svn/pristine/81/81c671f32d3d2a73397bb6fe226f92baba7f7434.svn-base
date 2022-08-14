package hry.minigrobot.robot;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import hry.core.constant.StringConstant;
import hry.core.thread.ThreadPool;
import hry.front.redis.model.UserRedis;
import hry.minigrobot.robot.dao.ExCustomerMiningRobotDao;
import hry.minigrobot.robot.model.ExCustomerMiningRobot;
import hry.redis.common.utils.RedisService;
import hry.redis.common.utils.RedisTradeService;
import hry.trade.entrust.dao.ExOrderInfoDao;
import hry.trade.entrust.model.ExOrderInfo;
import hry.trade.entrust.service.ExEntrustService;
import hry.trade.entrust.service.ExOrderInfoService;
import hry.trade.entrust.service.TradeService;
import hry.trade.entrust.service.impl.UserRedisInitUtil;
import hry.trade.model.Coin;
import hry.trade.model.CoinKeepDecimal;
import hry.trade.model.PeriodLastKLineListRunable;
import hry.trade.model.TradeRedis;
import hry.trade.model.affiliated.AffiliatedMain;
import hry.trade.mq.service.MessageProducer;
import hry.trade.redis.model.EntrustTrade;
import hry.trade.redis.model.ExchangeDataCacheRedis;
import hry.util.idgenerate.IdGenerate;
import hry.util.idgenerate.NumConstant;
import hry.util.properties.PropertiesUtils;
import hry.util.sys.ContextUtil;
import org.apache.commons.lang.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import tk.mybatis.mapper.util.StringUtil;

import javax.json.JsonObject;
import java.math.BigDecimal;
import java.util.*;

public class MiningRobotJob {

    public static  String  MaxDayCount="mingingplanrobot:maxDayCount";



    public static  BigDecimal SplitFrequency=new BigDecimal(1000);  //默认拆分次数

    public void appointMatchExtrust(EntrustTrade exEntrust,BigDecimal fee) {
        exEntrust.setEntrustNum("minigRobotExEntrust");
        EntrustTrade opponentExEntrust=new EntrustTrade();
        opponentExEntrust.setEntrustNum("minigRobotExEntrust");
        opponentExEntrust.setEntrustPrice(exEntrust.getEntrustPrice());
        opponentExEntrust.setType(exEntrust.getType().intValue()==1?2:1);
        opponentExEntrust.setCoinCode(exEntrust.getCoinCode());
        opponentExEntrust.setFixPriceCoinCode(exEntrust.getFixPriceCoinCode());
        opponentExEntrust.setFixPriceType(exEntrust.getFixPriceType());
        opponentExEntrust.setEntrustPrice(exEntrust.getEntrustPrice().setScale(10, BigDecimal.ROUND_HALF_EVEN));
        opponentExEntrust.setEntrustCount(exEntrust.getEntrustCount());
        opponentExEntrust.setTransactionFeeRate(exEntrust.getTransactionFeeRate());
        opponentExEntrust.setCustomerId(exEntrust.getCustomerId());

        appointExchange(opponentExEntrust,exEntrust,exEntrust.getType(), fee);

    }

    public void appointExchange(EntrustTrade opponentExEntrust, EntrustTrade exEntrust,Integer type,BigDecimal fee) {
        ExOrderInfoService exOrderInfoService= (ExOrderInfoService)ContextUtil.getBean("exOrderInfoService");
        ExOrderInfoDao exOrderInfoDao= (ExOrderInfoDao)ContextUtil.getBean("exOrderInfoDao");

        opponentExEntrust.setTransactionFeeRate(BigDecimal.ZERO);
        exEntrust.setTransactionFeeRate(BigDecimal.ZERO);
        List<ExOrderInfo> arr = new ArrayList<ExOrderInfo>();
        if (type.equals(1)){// 买家限价
            ExOrderInfo exOrderInfo = exOrderInfoService.createExOrderInfo(1, exEntrust, opponentExEntrust, exEntrust.getEntrustCount(), exEntrust.getEntrustPrice());
            exOrderInfo.setInOrOutTransaction( "buy");
            exOrderInfo.setTransactionBuyFee(exEntrust.getTransactionFee());
            exOrderInfo.setTransactionSellFee(fee);
            exOrderInfo.setTransactionBuyFeeRate(new BigDecimal("0"));
            exOrderInfo.setTransactionSellFeeRate(new BigDecimal("0"));
            exOrderInfo.setIfMining(1);
            arr.add(exOrderInfo);
        }else{
            ExOrderInfo exOrderInfo = exOrderInfoService.createExOrderInfo(1, opponentExEntrust, exEntrust, exEntrust.getEntrustCount(), exEntrust.getEntrustPrice());
            exOrderInfo.setInOrOutTransaction( "sell");
            exOrderInfo.setTransactionSellFee(exEntrust.getTransactionFee());
            exOrderInfo.setTransactionBuyFee(fee);
            exOrderInfo.setTransactionBuyFeeRate(new BigDecimal("0"));
            exOrderInfo.setTransactionSellFeeRate(new BigDecimal("0"));
            exOrderInfo.setIfMining(1);
            arr.add(exOrderInfo);
        }

        JedisPool jedisPool = (JedisPool) ContextUtil.getBean("jedisPool");
        Jedis jedis=jedisPool.getResource();
        try {



            String keyFront = TradeRedis.getHeaderFront(exEntrust);
            setExchangeDataCache(arr,keyFront,jedis); //设置成交列表，最新成交价

            redis.clients.jedis.Transaction transaction = jedis.multi();
            transaction.set(TradeRedis.getTradeDealOrderInfoChangeNum(transaction), JSON.toJSONString(arr));// 成交信息
            List<Object> list =transaction.exec();

            PeriodLastKLineListRunable periodLastKLineListRunable = new PeriodLastKLineListRunable(arr);
            ThreadPool.exe(periodLastKLineListRunable);
            RedisService redisService = (RedisService) ContextUtil.getBean("redisService");

        }finally {
            jedis.close();
        }


        RedisTradeService redisTradeService = (RedisTradeService) ContextUtil.getBean("redisTradeService");
        redisTradeService.save(getEntrustTimeFlag(exEntrust.getCoinCode(), exEntrust.getFixPriceCoinCode()),"1");
    }
    public static void setExchangeDataCache(List<ExOrderInfo> listExOrderInfo,String header,Jedis jedis) {
        ExOrderInfo exOrderInfo=listExOrderInfo.get(listExOrderInfo.size()-1); //最新成交
        // 设置当前最新成交价
      //  TradeRedis.setStringData(header + ":" + ExchangeDataCacheRedis.CurrentExchangPrice, exOrderInfo.getTransactionPrice().setScale(10, BigDecimal.ROUND_HALF_UP).toString());
        // 保存这条成交记录到re
        String v=jedis.get(header + ":" + ExchangeDataCacheRedis.LastOrderRecords);
        List<ExOrderInfo> list = null;
        if(!StringUtil.isEmpty(v)){
            list = JSON.parseArray(v, ExOrderInfo.class);
        }else{
            list=new ArrayList<ExOrderInfo>();
        }
        list.addAll(listExOrderInfo);
        if(list.size()>ExchangeDataCacheRedis.LastOrderRecordsLmit){
            List<ExOrderInfo> sublist=list.subList(list.size()-ExchangeDataCacheRedis.LastOrderRecordsLmit, list.size());
            jedis.set(header + ":" +ExchangeDataCacheRedis.LastOrderRecords, JSON.toJSONString(sublist));
        }else{
            jedis.set(header + ":" +ExchangeDataCacheRedis.LastOrderRecords, JSON.toJSONString(list));

        }


    }
      public  void executeRobotmq(){

          long sleeptiem = getTime().longValue();

          try {
              Thread.sleep(sleeptiem);
          } catch (InterruptedException e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
          }
          MessageProducer messageProducer = (MessageProducer) ContextUtil.getBean("messageProducer");
          messageProducer.mimingCustomerRobot("111");
      }
     /* //定时器，凌晨执行
     public void openStopByMax(){
         System.out.println("每日凌晨开启因为超过最大值停止的挖矿计划");
         //开启因为超过最大值停止的挖坑计划
         ExCustomerMiningRobotDao exCustomerMiningRobotDao = (ExCustomerMiningRobotDao) ContextUtil.getBean("exCustomerMiningRobotDao");
         exCustomerMiningRobotDao.updateRobotStatusToOpenByid();
        //删掉当日所有的用户已挖矿给数据，归0
         JedisPool jedisPool = (JedisPool) ContextUtil.getBean("jedisPool");
         Jedis jedis=null;
         try {
             jedis=jedisPool.getResource();
             Set<String> set= jedis.hkeys(MaxDayCount);
             Iterator<String> it = set.iterator();
             while (it.hasNext()) {
                 jedis.hdel(MaxDayCount,it.next());
             }




         }catch(Exception e) {

         }finally {
             jedis.close();
         }


     }*/

     //定时器，定时下单
       public void executeRobot(){
           long start=System.currentTimeMillis();
           System.out.println("进入客户挖矿计划机器人");
           ExCustomerMiningRobotDao exCustomerMiningRobotDao = (ExCustomerMiningRobotDao) ContextUtil.getBean("exCustomerMiningRobotDao");
           List<ExCustomerMiningRobot> list= exCustomerMiningRobotDao.getCumstoemrMingingRobotList();
           if(null==list||list.size()==0){
               System.out.println("没有挖矿计划需呀被执行");
               return ;
           }
          BigDecimal configMaxDayCount= getConfigMaxDayCount();
       //   BigDecimal configMaxDayCount= new BigDecimal("100");
           if(null==configMaxDayCount){
               System.out.println("没有设置最大当日最大产出值");
               return ;
           }
           System.out.println("执行计划条数："+list.size());
           for(ExCustomerMiningRobot emr:list){
               if(emr.getCoinCodeCurrMoney().compareTo(BigDecimal.ZERO)<=0||emr.getFixPriceCoinCodeCurrMoney().compareTo(BigDecimal.ZERO)<=0){
                   emr.setRobotStatus(4);
                   exCustomerMiningRobotDao.updateByPrimaryKey(emr);
                   System.out.println("当前币种为负数");
                   continue;
               }
               //拿到当前的当日最大产出量
               JedisPool jedisPool = (JedisPool) ContextUtil.getBean("jedisPool");
               Jedis jedis=jedisPool.getResource();
               BigDecimal counted=new BigDecimal("0");
               try {

                   String countedstr = jedis.hget(MaxDayCount, emr.getCustomerId() .toString());
                   if(StringUtils.isNotEmpty(countedstr)){
                       counted=new BigDecimal(countedstr);
                   }


                   String currentPrice = TradeRedis.getStringData(emr.getCoinCode() + "_" + emr.getFixPriceCoinCode() + ":" + ExchangeDataCacheRedis.CurrentExchangPrice);
                   BigDecimal autoPriceSJ= null;
                   String coinCodeSJ = emr.getCoinCode() + "_" + emr.getFixPriceCoinCode();
                   if(StringUtils.isNotEmpty(currentPrice)){
                       autoPriceSJ=new BigDecimal(currentPrice);
                   }else{
                       System.out.println(coinCodeSJ+"没有当前价格");
                       continue;
                   }


                   BigDecimal trueNumSJ= emr.getCoinCodeIniMoney();
                   BigDecimal robotOrderNum=new BigDecimal(emr.getRobotOrderNum());//拆分次数，默认100次
                   int surRobotOrderNum=  emr.getRobotOrderNum()-emr.getRobotOrderNumed();


                   //消耗
                   BigDecimal transactionFeeCoinCode=emr.getCoinCodeIniMoney().divide(robotOrderNum,10,BigDecimal.ROUND_HALF_UP);
                   BigDecimal transactionFeeFixCoinCode=emr.getFixPriceCoinCodeIniMoney().divide(robotOrderNum,10,BigDecimal.ROUND_HALF_UP);

                   if(surRobotOrderNum==1){
                       transactionFeeCoinCode= emr.getCoinCodeCurrMoney();
                       transactionFeeFixCoinCode=emr.getFixPriceCoinCodeCurrMoney();
                       //如果已经消耗完成就是设置为完成状态
                       emr.setRobotStatus(4);
                   }

                   emr.setRobotOrderNumed(emr.getRobotOrderNumed()+1);

                   //产出
                   BigDecimal convertTransactionFeeCoinCode=transactionFeeCoinCode.multiply(emr.getCoinCodeConvertPrice());
                   BigDecimal convertTransactionFeeFixCoinCode=transactionFeeFixCoinCode.multiply(emr.getFixPriceCoinCodeConvertPrice());
                   //本次产出
                   BigDecimal thisCount=convertTransactionFeeCoinCode.add(convertTransactionFeeFixCoinCode);

                   System.out.println("====================挖矿机器人本次挖出【"+thisCount+"】==================================");
                   counted=counted.add(thisCount);

                   if(counted.compareTo(configMaxDayCount)>=0){
                       System.out.println("后：用户id："+emr.getCustomerId()+"已经达到当日最大量:"+counted+"-thisCount="+thisCount+"设置当日最大量为："+configMaxDayCount);
                       Map<String,Object> map1=new HashMap<String,Object>();
                       map1.put("id",emr.getId());
                       map1.put("robotStatus",3);
                       exCustomerMiningRobotDao.updateRobotStatusByid(map1);
                       continue;

                   }

                   emr.setRobotCommissionCharges(emr.getRobotCommissionCharges().add(thisCount));
                   emr.setDistributionAmount(emr.getDistributionAmount().add(thisCount));
                   emr.setCoinCodeCurrMoney(emr.getCoinCodeCurrMoney().subtract(transactionFeeCoinCode));
                   emr.setFixPriceCoinCodeCurrMoney(emr.getFixPriceCoinCodeCurrMoney().subtract(transactionFeeFixCoinCode));



                   double  double1 =Math.random();
                   if (double1 <0.5) {// 这里向下浮动吧下卖单
                       // 卖
                       EntrustTrade exEntrustSell = addExEntrust(1, 2, emr.getCustomerId(), autoPriceSJ,"", coinCodeSJ, trueNumSJ, "cny", "cn");
                       if(exEntrustSell.getEntrustPrice().compareTo(new BigDecimal("0"))>0){
                           //因为按照次数去除以出来数量  所以 买方得买方币种手续费  卖方得卖方手续费
                           //例如 BCH(0.06)/USDT(10) 次数是220次 那么BCH每次数量是 0.0002721个 USDT是0.045
                           //这样 买方应该是 0.0002721 才对 不然分红会出问题
                           exEntrustSell.setTransactionFee(transactionFeeFixCoinCode);
                           appointMatchExtrust(exEntrustSell,transactionFeeCoinCode);
                       }

                       //	}
                   } else {
                       // 买
                       EntrustTrade exEntrustBuy = addExEntrust(1, 1, emr.getCustomerId(), autoPriceSJ,"", coinCodeSJ, trueNumSJ, "cny", "cn");
                       if(exEntrustBuy.getEntrustPrice().compareTo(new BigDecimal("0"))>0){
                           //因为按照次数去除以出来数量  所以 买方得买方币种手续费  卖方得卖方手续费
                           //例如 BCH(0.06)/USDT(10) 次数是220次 那么BCH每次数量是 0.0002721个 USDT是0.045
                           //这样 买方应该是 0.0002721 才对 不然分红会出问题
                           exEntrustBuy.setTransactionFee(transactionFeeCoinCode);
                           appointMatchExtrust(exEntrustBuy,transactionFeeFixCoinCode);
                       }

                   }


                   //更新用户的消耗和产量
                   Map<String,Object> map=new HashMap<String,Object>();
                   map.put("id",emr.getId());
                   map.put("coinCodeCurrMoney",emr.getCoinCodeCurrMoney());
                   map.put("fixPriceCoinCodeCurrMoney",emr.getFixPriceCoinCodeCurrMoney());
                   map.put("robotCommissionCharges",emr.getRobotCommissionCharges());
                   map.put("distributionAmount",emr.getDistributionAmount());
                   map.put("robotStatus",emr.getRobotStatus());
                   map.put("robotOrderNumed",emr.getRobotOrderNumed());
                   map.put("isDistributeCharges","0");
                   exCustomerMiningRobotDao.updateByid(map);
                   //用户单日Ai挖矿总量
                   jedis.hset(MaxDayCount, emr.getCustomerId() + "",counted.toString());






               }catch(Exception e) {

               }finally {
                  jedis.close();
               }



               try {
                   Thread.sleep(5);
               } catch (InterruptedException e) {
                   // TODO Auto-generated catch block
                   e.printStackTrace();
               }
           }

           long end=System.currentTimeMillis();
           System.out.println("客户挖矿计划机器人耗时"+(end -start));

       }


    /*public void  culPlatCount(BigDecimal thisCount,Jedis jedis){

        //当日平台AI挖矿总量
        BigDecimal allcountedbyday=thisCount;
        String allcountedbydaystr = jedis.hget(MaxDayCount,MaxDayCount_All);
        if(StringUtils.isNotEmpty(allcountedbydaystr)){
            allcountedbyday=allcountedbyday.add(new BigDecimal(allcountedbydaystr));
        }
        jedis.hset(MaxDayCount, MaxDayCount_All,allcountedbyday.toString());

        //平台AI总挖矿总量
        BigDecimal allcounted=thisCount;
        String curDayCountkey="mingingplanrobot:curDayCount";
        String allcountedstr = jedis.get(curDayCountkey);
        if(StringUtils.isNotEmpty(allcountedstr)){
            allcounted=allcounted.add(new BigDecimal(allcountedstr));
        }
        jedis.set(curDayCountkey,allcounted.toString());
    }*/
    public EntrustTrade addExEntrust(Integer fixPriceType, Integer type, Long customerId, BigDecimal price,
                                     String autoUsername, String coinCode, BigDecimal entrustCount, String currencyType, String website) {

        long start1 = System.currentTimeMillis();
        EntrustTrade exEntrust = new EntrustTrade();
        String[] rtd = coinCode.split("_");
        if (rtd.length == 1) {
            return null;
        } else {
            exEntrust.setFixPriceCoinCode(rtd[1]);
            exEntrust.setCoinCode(rtd[0]);
        }
        exEntrust.setFixPriceType(fixPriceType);
        exEntrust.setType(type);
        if (null == price || null == entrustCount) {
            System.out.println(exEntrust.getCoinCode() + "===" + exEntrust.getFixPriceCoinCode() + "==" + type);
            return null;
        }
        if (price.compareTo(new BigDecimal(0)) <= 0 || entrustCount.compareTo(new BigDecimal(0)) <= 0) {
            return null;
        }

        Integer[] keepDec = getCoinToCoinKeep(exEntrust.getCoinCode(), exEntrust.getFixPriceCoinCode());
        int keepDecimalForCoin = keepDec[1];
        int keepDecimalForCurrency = keepDec[0];
        exEntrust.setEntrustWay(1);// 1.限价--> 表示以固定的价格 , 2.市价--->
        exEntrust.setEntrustPrice(price.setScale(keepDecimalForCurrency, BigDecimal.ROUND_HALF_DOWN));
        exEntrust.setEntrustCount(entrustCount.setScale(keepDecimalForCoin, BigDecimal.ROUND_HALF_DOWN));
        exEntrust.setCustomerId(customerId);
        exEntrust.setSource(11);
        exEntrust.setUserName(autoUsername);
        exEntrust.setTrueName("挖矿计划");
        //initExEntrust(exEntrust);  //暂时注释掉

        return exEntrust;
    }
    public void initExEntrust(EntrustTrade exEntrust) {
        Coin productCommon = getCoinFromreds(exEntrust.getCoinCode(), exEntrust.getFixPriceCoinCode());
        String saasId = PropertiesUtils.APP.getProperty("app.saasId");
        if (null == exEntrust.getEntrustTime()) {
            exEntrust.setEntrustTime(new Date());
        }

        exEntrust.setEntrustNum(IdGenerate.transactionNum(NumConstant.Ex_Entrust));
        // String transactionNum =
        // IdGenerate.transactionNum(NumConstant.Ex_Entrust);
        String robotIsFee = PropertiesUtils.APP.getProperty("app.robotIsFee");
        if (exEntrust.getType() == 1) {
            exEntrust.setEntrustNum("WB" + UUID.randomUUID());
            if (!tk.mybatis.mapper.util.StringUtil.isEmpty(robotIsFee) && robotIsFee.equals("true")) {
                exEntrust.setTransactionFeeRate(
                        null == productCommon.getBuyFeeRate() ? new BigDecimal("0") : productCommon.getBuyFeeRate());
            } else {
                exEntrust.setTransactionFeeRate(new BigDecimal("0"));
            }

        } else {
            exEntrust.setEntrustNum("WS" + UUID.randomUUID());
            if (!tk.mybatis.mapper.util.StringUtil.isEmpty(robotIsFee) && robotIsFee.equals("true")) {
                exEntrust.setTransactionFeeRate(
                        null == productCommon.getSellFeeRate() ? new BigDecimal("0") : productCommon.getSellFeeRate());
            } else {
                exEntrust.setTransactionFeeRate(new BigDecimal("0"));
            }
        }
        if (!tk.mybatis.mapper.util.StringUtil.isEmpty(robotIsFee) && robotIsFee.equals("true")) {
            /*
             * AppPersonInfo
             * appPersonInfo=commonDao.getAppPersonInfoBycustomerId(exEntrust.
             * getCustomerId());
             * exEntrust.setIsOpenCoinFee(appPersonInfo.getIsOpenCoinFee());
             */
            if (null != exEntrust.getIsOpenCoinFee() && exEntrust.getIsOpenCoinFee().intValue() == 1) {
                String coinFeeDiscount = getBasefinanceConfigByKey("coinFeeDiscount");
                String platCoin = getBasefinanceConfigByKey("platCoin");

                if(!tk.mybatis.mapper.util.StringUtil.isEmpty(coinFeeDiscount)&&!StringUtil.isEmpty(platCoin)){
                    exEntrust.setTransactionFeeRateDiscount(coinFeeDiscount.toString());
                    exEntrust.setPlatCoin(platCoin);
                }else{
                    exEntrust.setIsOpenCoinFee(0);
                }


            } else {
                exEntrust.setIsOpenCoinFee(0);
            }
        } else {

            exEntrust.setIsOpenCoinFee(0);
        }
        exEntrust.setTransactionFeePlat(new BigDecimal("0"));
        exEntrust.setTransactionFee(new BigDecimal("0"));
        exEntrust.setCustomerId(exEntrust.getCustomerId());
        UserRedis userRedis = UserRedisInitUtil.getUserRedis(exEntrust.getCustomerId());

        // 获得缓存中所有的币账户id
        if (exEntrust.getFixPriceType().equals(0)) { // 真实货币
            exEntrust.setAccountId(userRedis.getAccountId());
        } else {
            exEntrust.setAccountId(userRedis.getDmAccountId(exEntrust.getFixPriceCoinCode()));
        }
        exEntrust.setCoinAccountId(userRedis.getDmAccountId(exEntrust.getCoinCode()));
        /*
         * exEntrust.setAccountId(Long.valueOf("22434"));
         * exEntrust.setCoinAccountId(Long.valueOf("52687"));
         */
        exEntrust.setStatus(0);
        exEntrust.setTransactionSum(
                null == exEntrust.getTransactionSum() ? new BigDecimal("0") : exEntrust.getTransactionSum());
        exEntrust.setEntrustSum((null != exEntrust.getEntrustPrice() && null != exEntrust.getEntrustCount())
                && !new BigDecimal("0").equals(exEntrust.getEntrustPrice())
                && !new BigDecimal("0").equals(exEntrust.getEntrustCount())
                ? exEntrust.getEntrustPrice().multiply(exEntrust.getEntrustCount())
                : exEntrust.getEntrustSum());
        exEntrust.setEntrustCount(
                null == exEntrust.getEntrustCount() ? new BigDecimal("0") : exEntrust.getEntrustCount());
        exEntrust.setSurplusEntrustCount(exEntrust.getEntrustCount());
        if (null == exEntrust.getEntrustCount()) {
            exEntrust.setEntrustCount(new BigDecimal("0"));
        }
        if (null == exEntrust.getEntrustPrice()) {
            exEntrust.setEntrustPrice(new BigDecimal("0"));
        }

        if (null == exEntrust.getEntrustWay()) {

            exEntrust.setEntrustWay(1);// 默认限价
        }
        if (null == exEntrust.getFloatDownPrice()) {
            exEntrust.setFloatDownPrice(new BigDecimal("0"));
            if (exEntrust.getEntrustWay() == 1 && exEntrust.getType() == 1) {
                exEntrust.setFloatDownPrice(exEntrust.getEntrustPrice());
            }
            if (exEntrust.getEntrustWay() == 1 && exEntrust.getType() == 2) {
                exEntrust.setFloatUpPrice((new BigDecimal("999999")));
            }
        }
        if (null == exEntrust.getFloatUpPrice()) {

            exEntrust.setFloatUpPrice(new BigDecimal("0"));
        }

    }

    public String getBasefinanceConfigByKey(String key) {
        RedisService redisService = (RedisService) ContextUtil.getBean("redisService");
        String val="";
        String string=redisService.get(StringConstant.CONFIG_CACHE+":basefinanceConfig");
        JSONArray obj= JSON.parseArray(string);
        for(Object o:obj){
            JSONObject oo=JSON.parseObject(o.toString());
            if(oo.getString("configkey").equals(key)){
                val=oo.getString("value");
            }
        }
        return val;
    }

    public Coin getCoinFromreds(String coinCode, String fixPriceCoinCode) {
        RedisService redisService = (RedisService) ContextUtil.getBean("redisService");
        String str = redisService.get("cn:coinInfoList");
        if (!org.apache.commons.lang3.StringUtils.isEmpty(str)) {
            List<Coin> coins = JSON.parseArray(str, Coin.class);
            if (coins != null && coins.size() > 0) {
                for (Coin coin : coins) {
                    if (coinCode.equals(coin.getCoinCode()) && fixPriceCoinCode.equals(coin.getFixPriceCoinCode())) {
                        return coin;
                    }
                    if(coin.getRose().compareTo(new BigDecimal("20"))==1){
                        coin.setRose(new BigDecimal("20"));
                    }
                    if(coin.getDecline().compareTo(new BigDecimal("20"))==1){
                        coin.setDecline(new BigDecimal("20"));

                    }
                }
            }
        }

        System.out.println("cn:coinInfoList为空，机器人没手续费");
        return null;

    }
    public Integer[] getCoinToCoinKeep(String coinCode, String fixPriceCoinCode) {
        RedisService redisService = (RedisService) ContextUtil.getBean("redisService");
        int keepDecimalForCoin = 4;
        int keepDecimalForCurrency = 4;

        CoinKeepDecimal coinKeepDecimal = null;
        String str = redisService.get("cn:coinInfoList");
        if (!org.apache.commons.lang3.StringUtils.isEmpty(str)) {
            List<CoinKeepDecimal> coins = JSON.parseArray(str, CoinKeepDecimal.class);
            if (coins != null && coins.size() > 0) {
                for (CoinKeepDecimal coin : coins) {
                    if (coinCode.equals(coin.getCoinCode()) && fixPriceCoinCode.equals(coin.getFixPriceCoinCode())) {
                        coinKeepDecimal = coin;
                    }
                }
            }
        }
        if (null != coinKeepDecimal) {
            keepDecimalForCoin = coinKeepDecimal.getKeepDecimalForCoin();
            keepDecimalForCurrency = coinKeepDecimal.getKeepDecimalForCurrency();
        }
        Integer[] keepDec = new Integer[2];
        keepDec[0] = keepDecimalForCurrency;
        keepDec[1] = keepDecimalForCoin;

        return keepDec;
    }

    public BigDecimal getTime() {
        BigDecimal[] a = getFloatNum1(new BigDecimal("3000"), new BigDecimal("90"));
        return a[1];
    }public BigDecimal[] getFloatNum1(BigDecimal number, BigDecimal floatNum) {
        BigDecimal[] price = new BigDecimal[2];
        BigDecimal truePrice = number;
        // 随便给个数字，让其compare不等于0
        price[0] = new BigDecimal(2);
        // 获取浮动值 刷币价格 * (浮动比例 * 随机小数 )
        double num = Math.random();// 获取随机小数
        BigDecimal fudongNum = number.multiply(floatNum).divide(new BigDecimal("100")).multiply(new BigDecimal(num));

        int number1 = (int) (Math.random() * 2);
        if (number1 == 0) {// 这里向下浮动吧
            truePrice = number.subtract(fudongNum);
            price[0] = new BigDecimal(-1); // 向下
        } else {// 这里向上浮动吧
            truePrice = number.add(fudongNum);
            price[0] = new BigDecimal(1); // 向上
        }
        price[1] = truePrice;
        if (null == truePrice) {
            System.out.println("truePrice=" + truePrice);
        }
        return price;
    }
    public BigDecimal getConfigMaxDayCount(){
        RedisService redisService = (RedisService) ContextUtil.getBean("redisService");
        String string=redisService.get(StringConstant.CONFIG_CACHE+":basefinanceConfig");
        JSONArray obj=JSON.parseArray(string);
        String planLimitMoney = "";
        BigDecimal planLimitMoneyBig =null;
        for (Object o : obj) {
            JSONObject oo=JSON.parseObject(o.toString());
            if(oo.getString("configkey").equals("planLimitMoney")){
                String val = oo.getString("value");
                if(val != null && !"".equals(val)){
                    planLimitMoney = val;
                    planLimitMoneyBig=new BigDecimal(planLimitMoney);
                    break;
                }
            }
        }

        return planLimitMoneyBig;
    }
    public static String getEntrustTimeFlag(String coinCode, String fixPriceCoinCode) {
        String header = coinCode + "_" + fixPriceCoinCode+":isTimgEntrsutFlag";
        return header;

    }
}
