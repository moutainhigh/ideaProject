package hry.trade.model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import hry.exchange.kline2.model.LastKLinePayload;
import hry.trade.entrust.model.ExOrderInfo;
import hry.util.date.DateUtil;
import hry.util.sys.ContextUtil;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class RedisLastKLine {

    public static void main(String[] args) throws ParseException {

        BigDecimal bigDecimal = new BigDecimal("-1");
        System.out.println(bigDecimal.compareTo(BigDecimal.ZERO));

    }

    /**
     * 保存最近24小时的成交订单
     * @param exOrderInfo
     */
    public static void push24hours(List<ExOrderInfo> exOrderInfo){
        //24小时之前的界线
        long splitTime = DateUtil.addDaysToDate(new Date(), -1).getTime();

        //long splitTime = new Date().getTime()-(1000*60*2);
        JedisPool jedisPool = (JedisPool) ContextUtil.getBean("jedisPool");
        Jedis jedis = null ;
        try {

            jedis = jedisPool.getResource();


            //获得最后一条记录
            ExOrderInfo order = exOrderInfo.get(exOrderInfo.size()-1);
            //redis key值
            String rediskey = order.getCoinCode()+"_"+order.getFixPriceCoinCode()+":new24hours";
            String volkey = order.getCoinCode()+"_"+order.getFixPriceCoinCode()+":new24hoursvol";
            String amoutkey = order.getCoinCode()+"_"+order.getFixPriceCoinCode()+":new24hoursamout";
            //24小时成交总额
            String volstr = jedis.get(volkey);
            //24小时成交总量
            String amoutstr = jedis.get(amoutkey);
            //最近24小时成交总额
            BigDecimal vol = new BigDecimal(0);
            BigDecimal amout = new BigDecimal(0);
            if(!StringUtils.isEmpty(volstr)){
                vol = new BigDecimal(volstr);
            }
            if(!StringUtils.isEmpty(amoutstr)) {
                amout = new BigDecimal(amoutstr);
            }

            //出队总额
            BigDecimal out = new BigDecimal(0);
            //出队总量
            BigDecimal outAmout = new BigDecimal(0);
            //出队总长
            Long llen = jedis.llen(rediskey);

            long pageSize = 10;

            if(llen<=pageSize){//总长小于100,1页出完，直接range到最后-1
                //获得redis list
                List<String> list = jedis.lrange(rediskey, 0, -1);
                if(list!=null&&list.size()>0){
                    int length = list.size()-1;
                    for(int i = length ; i>=0; i--){
                        String str = list.get(i);
                        JSONObject obj = JSON.parseObject(str);
                        long longValue = obj.getLongValue("transactionTime");
                        if(longValue<splitTime){//如果成交时间不在24小时界线内
                            jedis.rpop(rediskey);
                            out = out.add(new BigDecimal(String.valueOf(obj.get("transactionSum"))));
                            outAmout = outAmout.add(new BigDecimal(String.valueOf(obj.get("transactionCount"))));
                        }else{
                            break;
                        }
                    }
                }
            }else{//分页出队
                long page;
                if(llen%pageSize==0){
                    page = llen/pageSize;
                }else{
                    page = llen/pageSize+1;
                }
                one:for(int x = 1 ; x <= page ; x++){
                    long start = (x*pageSize)*-1;
                    long end  = -1 - ((x-1)*pageSize);
                    //查每一页数据
                    List<String> list = jedis.lrange(rediskey, start, end);
                    int length = list.size()-1;
                    for(int i = length ; i>=0; i--){
                        String str = list.get(i);
                        JSONObject obj = JSON.parseObject(str);
                        long longValue = obj.getLongValue("transactionTime");
                        //如果成交时间不在24小时界线内
                        if(longValue<splitTime){
                            jedis.rpop(rediskey);
                            out = out.add(new BigDecimal(String.valueOf(obj.get("transactionSum"))));
                            outAmout = outAmout.add(new BigDecimal(String.valueOf(obj.get("transactionCount"))));
                        }else{
                            break one;
                        }
                    }
                }
            }



            //入队总额
            BigDecimal in = new BigDecimal(0);
            //入队总量
            BigDecimal inAmout = new BigDecimal(0);

            //入队
            for(ExOrderInfo info : exOrderInfo){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("transactionPrice", info.getTransactionPrice());
                jsonObject.put("transactionCount", info.getTransactionCount());
                jsonObject.put("transactionSum", info.getTransactionSum());
                jsonObject.put("transactionTime", info.getTransactionTime().getTime());
                jsonObject.put("transactionTimeStr", DateUtil.dateToString(info.getTransactionTime()));
                jedis.lpush(rediskey, jsonObject.toJSONString());
                in = in.add(info.getTransactionSum());
                inAmout = inAmout.add(info.getTransactionCount());
            }

            //保存总交易额
            vol= vol.add(in).subtract(out);
            ////防负数，应该和那个redis事务BUG有关
            if(vol.compareTo(BigDecimal.ZERO)<0) {
                vol = vol.multiply(new BigDecimal(-1));
            }
            jedis.set(volkey, vol.toString());
            //保存总交易量
            amout = amout.add(inAmout).subtract(outAmout);
            //防负数，应该和那个redis事务BUG有关
            if(amout.compareTo(BigDecimal.ZERO)<0) {
                amout = amout.multiply(new BigDecimal(-1));
            }
            jedis.set(amoutkey, amout.toString());

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            jedis.close();
        }
    }

    /**
     * 保存分期最后一个节点的数据
     *
     * @param exOrderInfo
     */
    public static void savePeriodLastKLineList(List<ExOrderInfo> exOrderInfo) {
    	JedisPool jedisPool = (JedisPool) ContextUtil.getBean("jedisPool");
		Jedis jedis=jedisPool.getResource();
		try {
		        if(exOrderInfo==null||exOrderInfo.size()==0){
		            return ;
		        }
		        //加到最近24小时成交记录管道中
		        push24hours(exOrderInfo);

		        //获得最后一笔成交单做为本次委托的成交价
		        ExOrderInfo order = exOrderInfo.get(exOrderInfo.size()-1);
		        //本次成交的总数量
		        BigDecimal amout = new BigDecimal(0);
		        //本次成交总额
		        BigDecimal totalMoney = new BigDecimal(0);
		        if(exOrderInfo.size()>1){
		            for(ExOrderInfo o : exOrderInfo){
		                amout = amout.add(o.getTransactionCount());
		                totalMoney = totalMoney.add(o.getTransactionSum());
		            }
		        }else{
		            amout = order.getTransactionCount();
		            totalMoney = order.getTransactionSum();
		        }


		        //时间区间
		        String[] periods = { "1min", "5min", "15min", "30min", "60min", "1day", "1week", "1mon" };

		        // 获得当前成交订单的交易时间区间值
		        Map<String, Date> periodDate = DateUtil.getPeriodDate2(order.getTransactionTime());

		        // 查询实时时间区间
		        String redisList = jedis.get(order.getCoinCode()+"_"+order.getFixPriceCoinCode()+":PeriodLastKLineList");
		        // 转对象
		        List<LastKLinePayload> periodList = JSON.parseArray(redisList, LastKLinePayload.class);

		        // 如果不存在则第一次生成 实时时间区间
		        if (periodList == null) {
		            periodList = new ArrayList<LastKLinePayload>();
		            for (String p : periods) {// 遍历数组
		                LastKLinePayload lastKLinePayload = new LastKLinePayload();
		                lastKLinePayload.setPeriod(p);
		                //第一次创建，所有价格都是当前第一笔交易的价格
		                lastKLinePayload.setPriceOpen(order.getTransactionPrice());
		                lastKLinePayload.setPriceHigh(order.getTransactionPrice());
		                lastKLinePayload.setPriceLow(order.getTransactionPrice());
		                lastKLinePayload.setPriceLast(order.getTransactionPrice());
		                lastKLinePayload.setAmount(amout);
		                if ("1min".equals(p)) {
		                    lastKLinePayload.set_id(periodDate.get("1min").getTime() / 1000);
		                    //开始时间时间戳
		                    lastKLinePayload.setTime(periodDate.get("1min").getTime() / 1000);
		                    //开始时间
		                    lastKLinePayload.setStartTime(DateUtil.dateToString(periodDate.get("1min"),"yyyy-MM-dd HH:mm"));
		                    //结束时间
		                    lastKLinePayload.setEndTime(DateUtil.dateToString(DateUtil.addMinToDate(periodDate.get("1min"), 1),"yyyy-MM-dd HH:mm"));
		                }
		                if ("5min".equals(p)) {
		                    lastKLinePayload.set_id(periodDate.get("5min").getTime() / 1000);
		                    lastKLinePayload.setTime(periodDate.get("5min").getTime() / 1000);
		                    lastKLinePayload.setStartTime(DateUtil.dateToString(periodDate.get("5min"),"yyyy-MM-dd HH:mm"));
		                    lastKLinePayload.setEndTime(DateUtil.dateToString(DateUtil.addMinToDate(periodDate.get("5min"), 5),"yyyy-MM-dd HH:mm"));
		                }
		                if ("15min".equals(p)) {
		                    lastKLinePayload.set_id(periodDate.get("15min").getTime() / 1000);
		                    lastKLinePayload.setTime(periodDate.get("15min").getTime() / 1000);
		                    lastKLinePayload.setStartTime(DateUtil.dateToString(periodDate.get("15min"),"yyyy-MM-dd HH:mm"));
		                    lastKLinePayload.setEndTime(DateUtil.dateToString(DateUtil.addMinToDate(periodDate.get("15min"), 15),"yyyy-MM-dd HH:mm"));
		                }
		                if ("30min".equals(p)) {
		                    lastKLinePayload.set_id(periodDate.get("30min").getTime() / 1000);
		                    lastKLinePayload.setTime(periodDate.get("30min").getTime() / 1000);
		                    lastKLinePayload.setStartTime(DateUtil.dateToString(periodDate.get("30min"),"yyyy-MM-dd HH:mm"));
		                    lastKLinePayload.setEndTime(DateUtil.dateToString(DateUtil.addMinToDate(periodDate.get("30min"), 30),"yyyy-MM-dd HH:mm"));
		                }
		                if ("60min".equals(p)) {
		                    lastKLinePayload.set_id(periodDate.get("60min").getTime() / 1000);
		                    lastKLinePayload.setTime(periodDate.get("60min").getTime() / 1000);
		                    lastKLinePayload.setStartTime(DateUtil.dateToString(periodDate.get("60min"),"yyyy-MM-dd HH:mm"));
		                    lastKLinePayload.setEndTime(DateUtil.dateToString(DateUtil.addMinToDate(periodDate.get("60min"), 60),"yyyy-MM-dd HH:mm"));
		                }
		                if ("1day".equals(p)) {
		                    lastKLinePayload.set_id(periodDate.get("1day").getTime() / 1000);
		                    lastKLinePayload.setTime(periodDate.get("1day").getTime() / 1000);
		                    lastKLinePayload.setStartTime(DateUtil.dateToString(periodDate.get("1day"),"yyyy-MM-dd HH:mm"));
		                    lastKLinePayload.setEndTime(DateUtil.dateToString(DateUtil.addDaysToDate(periodDate.get("1day"), 1),"yyyy-MM-dd HH:mm"));
		                    lastKLinePayload.setDayTotalDealAmount(new BigDecimal(0).add((order.getTransactionCount().multiply(order.getTransactionPrice()))));
		                }
		                if ("1week".equals(p)) {
		                    lastKLinePayload.set_id(periodDate.get("1week").getTime() / 1000);
		                    lastKLinePayload.setTime(periodDate.get("1week").getTime() / 1000);
		                    lastKLinePayload.setStartTime(DateUtil.dateToString(periodDate.get("1week"),"yyyy-MM-dd HH:mm"));
		                    lastKLinePayload.setEndTime(DateUtil.dateToString(DateUtil.addDaysToDate(periodDate.get("1week"), 7),"yyyy-MM-dd HH:mm"));
		                }
		                if ("1mon".equals(p)) {
		                    lastKLinePayload.set_id(periodDate.get("1mon").getTime() / 1000);
		                    lastKLinePayload.setTime(periodDate.get("1mon").getTime() / 1000);
		                    lastKLinePayload.setStartTime(DateUtil.dateToString(periodDate.get("1mon"),"yyyy-MM-dd HH:mm"));
		                    lastKLinePayload.setEndTime(DateUtil.dateToString(DateUtil.addMonth(periodDate.get("1mon"), 1),"yyyy-MM-dd HH:mm"));
		                }
		                periodList.add(lastKLinePayload);
		            }
		            jedis.set(order.getCoinCode()+"_"+order.getFixPriceCoinCode()+":PeriodLastKLineList", JSON.toJSONString(periodList));
		        } else {// 如果实时时间区间存在,则更新

		            for (LastKLinePayload lastKLinePayload : periodList) {
		                String period = lastKLinePayload.getPeriod();

		                //复制一个对象
		                LastKLinePayload lkp = new LastKLinePayload();
		                lkp.setPriceOpen(lastKLinePayload.getPriceOpen());
		                lkp.setPriceHigh(lastKLinePayload.getPriceHigh());
		                lkp.setPriceLow(lastKLinePayload.getPriceLow());
		                lkp.setPriceLast(lastKLinePayload.getPriceLast());
		                lkp.setAmount(lastKLinePayload.getAmount());
		                lkp.setStartTime(lastKLinePayload.getStartTime());
		                lkp.setEndTime(lastKLinePayload.getEndTime());
		                lkp.setPeriod(period);

		                //刷新当前时间所在1分钟时间区间的数据
		                if ("1min".equals(period)) {
		                    boolean type = flushLastKlinePayLoad(periodDate, lastKLinePayload, order,amout, totalMoney,period);
		                    if(type){//如果不在一个时区
		                        backups(order, lkp, period,jedis);
		                    }
		                }
		                if ("5min".equals(period)) {
		                    boolean type = flushLastKlinePayLoad(periodDate, lastKLinePayload, order,amout,totalMoney, period);
		                    if(type){
		                        backups(order, lkp, period,jedis);
		                    }
		                }
		                if ("15min".equals(period)) {
		                    boolean type = flushLastKlinePayLoad(periodDate, lastKLinePayload, order,amout,totalMoney, period);
		                    if(type){
		                        backups(order, lkp, period,jedis);
		                    }
		                }
		                if ("30min".equals(period)) {
		                    boolean type = flushLastKlinePayLoad(periodDate, lastKLinePayload, order,amout,totalMoney, period);
		                    if(type){
		                        backups(order, lkp, period,jedis);
		                    }
		                }
		                if ("60min".equals(period)) {
		                    boolean type = flushLastKlinePayLoad(periodDate, lastKLinePayload, order,amout, totalMoney,period);
		                    if(type){
		                        backups(order, lkp, period,jedis);
		                    }
		                }
		                if ("1day".equals(period)) {
		                    boolean type = flushLastKlinePayLoad(periodDate, lastKLinePayload, order,amout,totalMoney, period);
		                    if(type){
		                        backups(order, lkp, period,jedis);
		                    }
		                }
		                if ("1week".equals(period)) {
		                    boolean type = flushLastKlinePayLoad(periodDate, lastKLinePayload, order,amout,totalMoney, period);
		                    if(type){
		                        backups(order, lkp, period,jedis);
		                    }
		                }
		                if ("1mon".equals(period)) {
		                    boolean type = flushLastKlinePayLoad(periodDate, lastKLinePayload, order,amout,totalMoney, period);
		                    if(type){
		                        backups(order, lkp, period,jedis);
		                    }
		                }
		            }

		            // 更新缓存
		            jedis.set(order.getCoinCode()+"_"+order.getFixPriceCoinCode()+":PeriodLastKLineList", JSON.toJSONString(periodList));
		        }

		}finally {
			jedis.close();
		}
    }

    public static void  backups(ExOrderInfo order,LastKLinePayload lkp , String period,Jedis jedis){

        String backup = jedis.get(order.getCoinCode()+"_"+order.getFixPriceCoinCode()+":PeriodLastKLineList_backups");
        if(!StringUtils.isEmpty(backup)){
            JSONObject obj = JSON.parseObject(backup);
            obj.put(period, lkp);
            jedis.set(order.getCoinCode()+"_"+order.getFixPriceCoinCode()+":PeriodLastKLineList_backups", JSON.toJSONString(obj));
        }else{
            JSONObject obj  = new JSONObject();
            obj.put(period, lkp);
            jedis.set(order.getCoinCode()+"_"+order.getFixPriceCoinCode()+":PeriodLastKLineList_backups", JSON.toJSONString(obj));
        }

    }


    /**
     * 刷新lastKline
     *
     * @param periodDate  Map   当时时间所在的各个时间区间
     * @param lastKLinePayload
     * @param exOrderInfo
     * @return  boolean  false 匹配上在一个时区，true 没匹配上不在一个时间
     */
    public static boolean flushLastKlinePayLoad(Map<String, Date> periodDate, LastKLinePayload lastKLinePayload, ExOrderInfo exOrderInfo,BigDecimal amout, BigDecimal totalMoney,String period) {

        // 获得当前这笔交易订单的所有时间区间与其比较
        Date date = periodDate.get(period);

        //System.out.println("@@@@@"+DateUtil.dateToString(date));


        if("1day".equals(period)){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String d1 = sdf.format(new Date(lastKLinePayload.getTime()*1000));//当前一天时间区间的开始时间
            String d2 = sdf.format(exOrderInfo.getTransactionTime());
            if (!d1.equals(d2)) {
                lastKLinePayload.setDayTotalDealAmount(totalMoney);
            }else{
                //System.out.println("当日交易总额前："+lastKLinePayload.getDayTotalDealAmount());
                lastKLinePayload.setDayTotalDealAmount(lastKLinePayload.getDayTotalDealAmount().add(totalMoney));
            }
            //System.out.println("当日交易总额后："+lastKLinePayload.getDayTotalDealAmount());
        }


        if (lastKLinePayload.getTime().compareTo(date.getTime() / 1000) == 0) {// 如果发现在同一个区间的话则只进行比较,比较最高价，最低价，设置收盘价为当前这笔订单的价格
            if (exOrderInfo.getTransactionPrice().compareTo(lastKLinePayload.getPriceHigh()) > 0) {// 比较成交价格是否大于最高价
                lastKLinePayload.setPriceHigh(exOrderInfo.getTransactionPrice());
            }
            if (exOrderInfo.getTransactionPrice().compareTo(lastKLinePayload.getPriceLow()) < 0) {// 比较成交价格是否小于最高价
                lastKLinePayload.setPriceLow(exOrderInfo.getTransactionPrice());
            }
            lastKLinePayload.setPriceLast(exOrderInfo.getTransactionPrice());// 刷新最新成交价格
            lastKLinePayload.setAmount(lastKLinePayload.getAmount().add(amout));// 累加成交量

            //匹配上了，在一个时区
            return false;

        } else {// 如果发现不在同一个区间的话则重新刷新这个缓存区间的时间值,同时重新所有数据
            lastKLinePayload.setPriceOpen(exOrderInfo.getTransactionPrice());
            lastKLinePayload.setPriceHigh(exOrderInfo.getTransactionPrice());
            lastKLinePayload.setPriceLow(exOrderInfo.getTransactionPrice());
            lastKLinePayload.setPriceLast(exOrderInfo.getTransactionPrice());
            lastKLinePayload.setAmount(amout);
            lastKLinePayload.set_id(date.getTime() / 1000);
            lastKLinePayload.setTime(date.getTime() / 1000);
            if ("1min".equals(period)) {
                lastKLinePayload.setStartTime(DateUtil.dateToString(periodDate.get("1min"),"yyyy-MM-dd HH:mm"));
                lastKLinePayload.setEndTime(DateUtil.dateToString(DateUtil.addMinToDate(periodDate.get("1min"), 1),"yyyy-MM-dd HH:mm"));
            }
            if ("5min".equals(period)) {
                lastKLinePayload.setStartTime(DateUtil.dateToString(periodDate.get("5min"),"yyyy-MM-dd HH:mm"));
                lastKLinePayload.setEndTime(DateUtil.dateToString(DateUtil.addMinToDate(periodDate.get("5min"), 5),"yyyy-MM-dd HH:mm"));
            }
            if ("15min".equals(period)) {
                lastKLinePayload.setStartTime(DateUtil.dateToString(periodDate.get("15min"),"yyyy-MM-dd HH:mm"));
                lastKLinePayload.setEndTime(DateUtil.dateToString(DateUtil.addMinToDate(periodDate.get("15min"), 15),"yyyy-MM-dd HH:mm"));
            }
            if ("30min".equals(period)) {
                lastKLinePayload.setStartTime(DateUtil.dateToString(periodDate.get("30min"),"yyyy-MM-dd HH:mm"));
                lastKLinePayload.setEndTime(DateUtil.dateToString(DateUtil.addMinToDate(periodDate.get("30min"), 30),"yyyy-MM-dd HH:mm"));
            }
            if ("60min".equals(period)) {
                lastKLinePayload.setStartTime(DateUtil.dateToString(periodDate.get("60min"),"yyyy-MM-dd HH:mm"));
                lastKLinePayload.setEndTime(DateUtil.dateToString(DateUtil.addMinToDate(periodDate.get("60min"), 60),"yyyy-MM-dd HH:mm"));
            }
            if ("1day".equals(period)) {
                lastKLinePayload.setStartTime(DateUtil.dateToString(periodDate.get("1day"),"yyyy-MM-dd HH:mm"));
                lastKLinePayload.setEndTime(DateUtil.dateToString(DateUtil.addDaysToDate(periodDate.get("1day"), 1),"yyyy-MM-dd HH:mm"));
            }
            if ("1week".equals(period)) {
                lastKLinePayload.setStartTime(DateUtil.dateToString(periodDate.get("1week"),"yyyy-MM-dd HH:mm"));
                lastKLinePayload.setEndTime(DateUtil.dateToString(DateUtil.addDaysToDate(periodDate.get("1week"), 7),"yyyy-MM-dd HH:mm"));
            }
            if ("1mon".equals(period)) {
                lastKLinePayload.setStartTime(DateUtil.dateToString(periodDate.get("1mon"),"yyyy-MM-dd HH:mm"));
                lastKLinePayload.setEndTime(DateUtil.dateToString(DateUtil.addMonth(periodDate.get("1mon"), 1),"yyyy-MM-dd HH:mm"));
            }


            //未匹配上，不在一个时区
            return true;

        }

    }


}
