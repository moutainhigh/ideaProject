package hry.admin.exchange.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import hry.admin.ico.rulesconfig.model.RulesConfig;
import hry.admin.web.model.AppConfig;
import hry.bean.JsonResult;
import hry.redis.common.utils.RedisService;
import hry.util.RSAUtil;
import hry.util.RedisStaticUtil;
import hry.util.SpringUtil;
import hry.util.UUIDUtil;
import hry.util.httpRequest.HttpConnectionUtil;
import hry.util.rsa.FXHParam;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 拉取实时价格
 */
public class HryCurrentCoinPriceRunable implements Runnable {
    private String coinCode;

    public HryCurrentCoinPriceRunable(String coinCode) {
        this.coinCode = coinCode;
    }

    @Override
    public void run() {
        JsonResult jsonResult = new JsonResult();

        FXHParam fxhParam = new FXHParam();
        fxhParam.setCoinCode(coinCode);

        RedisService redisService = SpringUtil.getBean("redisService");
        JSONArray config = JSONObject.parseArray(redisService.get("configCache:realtimepriceconfig"));

        config.forEach( app -> {
            AppConfig appConfig = JSONObject.parseObject(app.toString(),AppConfig.class);
            switch (appConfig.getConfigkey()){
                case "klinedataurl" : fxhParam.setPayUrl(appConfig.getValue()); break;
                case "businessNumber" : fxhParam.setCompanyCode(appConfig.getValue()); break;
                case "publickey" : fxhParam.setPublicKey(appConfig.getValue()); break;
                case "privatekey" : fxhParam.setPrivateKey(appConfig.getValue()); break;
            }
        });

        Map<String, Object> map = new HashMap<>(16);
        map.put("ordernumber", UUIDUtil.getUUID());
        map.put("belonged", "交易所6.0");
        map.put("frontWord", fxhParam.getCoinCode());

        try {
            String sign = RSAUtil.encryptByPrivateKey(JSONObject.toJSONString(map), fxhParam.getPrivateKey());
            Map<String, String> paramMap = new HashMap<>(16);
            paramMap.put("companyCode", fxhParam.getCompanyCode());
            paramMap.put("sign", sign);

            String returnMsg = HttpConnectionUtil.doPostQuery(fxhParam.getPayUrl(), paramMap);
            if(StringUtils.isEmpty(returnMsg)){
                jsonResult.setMsg("连接失败");
                jsonResult.setSuccess(false);
            }else {
                JSONObject jsonObject = JSONObject.parseObject(returnMsg);
                if("8888".equals(jsonObject.getString("resultCode"))){
                    String data = RSAUtil.decryptByPublicKey(jsonObject.getString("data"), fxhParam.getPublicKey());
                    JSONArray jsonArray = JSONArray.parseArray(data);
                    if(jsonArray!=null){
                        for(Object object : jsonArray){
                            JSONObject jsonObject1 = JSONObject.parseObject(object.toString());
                            String price = jsonObject1.getString("price");
                            if (!StringUtils.isEmpty(price)) {
                                redisService.hset("hry:coinPrice", coinCode, price);
                            }
                        }
                    }

                    jsonResult.setSuccess(true);
                    jsonResult.setMsg("连接成功");
                }else {
                    jsonResult.setSuccess(false);
                    jsonResult.setMsg("外部行情接口返回结果:"+jsonObject.getString("reason"));
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
