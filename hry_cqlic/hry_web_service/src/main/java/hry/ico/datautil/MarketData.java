package hry.ico.datautil;

import com.alibaba.fastjson.JSON;
import hry.ico.remote.model.RulesConfig;
import hry.redis.common.utils.RedisService;
import hry.util.SpringUtil;

import java.util.HashMap;
import java.util.Map;

public class MarketData {
    /**     公有API
     * 　　每1秒20请求。
     * 　　率超过限制时,API的使用将是有限的。
     * 　　私有API
     * 　　每1秒10的请求。
     * 　　率超过限制时,API的使用将是有限的5分钟。
     */
    private  static boolean flag=true;

    private  static  String apiPath="/public/ticker/ETH";
    public  void getMarketDataToRedis(){
        RedisService redisService= SpringUtil.getBean("redisService");
        // TODO 调用行情数据
        Api_Client api = new Api_Client("api connect key",
                "api secret key");
        HashMap<String, String> rgParams = new HashMap<String, String>();
        try {
           /* String result = "{\"status\":\"0000\",\"data\":{\"opening_price\":\"132400\",\"closing_price\":\"132800\",\"min_price\":\"130400\",\"max_price\":\"135400\",\"average_price\":\"132833.3431\",\"units_traded\":\"91900.8181364\",\"volume_1day\":\"91900.8181364\",\"volume_7day\":\"595947.43269993067879593\",\"buy_price\":\"132700\",\"sell_price\":\"132800\",\"24H_fluctate\":\"400\",\"24H_fluctate_rate\":\"0.30\",\"date\":\"1549956613798\"}}";
            if(flag){
                result= api.callApi(apiPath, null);
            }*/
            String   result= api.callApi(apiPath, null);
            Map<String,Object> map=JSON.parseObject(result,Map.class);
            if(map.get("status").equals("0000")){//成功
                Map<String,String> data=JSON.parseObject(map.get("data").toString(),Map.class);
                redisService.saveMap(RulesConfig.RedisMarketKey,data);
            }
        }catch (Exception e){
            flag=false;
            e.printStackTrace();
        }

    }
}
