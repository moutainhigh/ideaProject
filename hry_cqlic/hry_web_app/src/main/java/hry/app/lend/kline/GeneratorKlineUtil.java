package hry.app.lend.kline;

import com.alibaba.fastjson.JSON;
import hry.app.lend.model.LendRedis;
import hry.lend.constant.ExchangeLendKey;
import hry.lend.constant.LendConfig;
import hry.lend.model.kline.KlineData;
import redis.clients.jedis.Jedis;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:HelloHeSir@gmail.com">Mr_He</a>
 * @Copyright (c)</ b> HeC<br/>
 * @createTime 2018/11/6 10:41
 * @Description: 杠杆k线数据生成器
 */
public class GeneratorKlineUtil {

    /**
     * 生成器入口
     * @param coinCode 交易对
     * @param resolution 时间
     * @param timeDL 头时间
     * @param to
     */
    public static Map<String,Object> generator(String coinCode,String resolution,String timeDL,String to){
        Map<String, Object> map = new HashMap<>(8);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try (Jedis jedis = LendRedis.JEDIS_POOL.getResource()) {
            String key = ExchangeLendKey.getKlineData(coinCode.replace("-", "_"), resolution);
            List<String> klineDataAll = jedis.lrange(key, 0, LendConfig.KLINE_NODE_LOAD);
            if(klineDataAll.size()>0) {
                String currentDateStr = klineDataAll.get(0);
                KlineData currentDate = JSON.parseObject(currentDateStr, KlineData.class);
                long time = format.parse(currentDate.getTransactionTime()).getTime();
                if (Long.valueOf(to) * 1000 < time) {
                    map = getStringObjectMap();
                    return map;
                }
                int size = klineDataAll.size();
                //闭
                BigDecimal[] c = new BigDecimal[size];
                //最高
                BigDecimal[] h = new BigDecimal[size];
                //最低
                BigDecimal[] l = new BigDecimal[size];
                //开
                BigDecimal[] o = new BigDecimal[size];
                //时间
                Long[] t = new Long[size];
                //数量
                BigDecimal[] v = new BigDecimal[size];

                size -= 1;
                for (String dataStr : klineDataAll) {
                    KlineData data = JSON.parseObject(dataStr, KlineData.class);
                    c[size] = data.getEndPrice();
                    h[size] = data.getMaxPrice();
                    l[size] = data.getMinPrice();
                    o[size] = data.getStartPrice();
                    t[size] = format.parse(data.getTransactionEndTime()).getTime() / 1000;
                    v[size] = data.getTransactionCount();
                    size--;
                }
                map.put("c", c);
                map.put("h", h);
                map.put("l", l);
                map.put("o", o);
                map.put("s", "ok");
                map.put("t", t);
                map.put("v", v);

                return map;
            }else{
                map = getStringObjectMap();
                return map;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Map<String, Object> getStringObjectMap() {
        Map<String, Object> map = new HashMap<>(7);
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