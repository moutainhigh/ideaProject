package hry.admin.exchange.controller;

import com.alibaba.fastjson.JSONObject;
import hry.admin.exchange.model.ExRobot;
import hry.admin.web.model.AppConfig;
import hry.bean.JsonResult;
import hry.redis.common.utils.RedisService;
import hry.util.RSAUtil;
import hry.util.SpringUtil;
import hry.util.UUIDUtil;
import hry.util.httpRequest.HttpConnectionUtil;
import hry.util.rsa.FXHParam;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

public class RobotPriceCallable implements Callable<JsonResult> {
    private ExRobot ex;

    public RobotPriceCallable(ExRobot exRobot) {
        this.ex = exRobot;
    }

    @Override
    public JsonResult call() throws Exception {
        JsonResult jsonResult = new JsonResult();
        //最新价
        RedisService redisService = SpringUtil.getBean("redisService");

        FXHParam fxhParam = new FXHParam();
        fxhParam.setCoinCode(ex.getCoinCode());
        fxhParam.setFixCoin(ex.getFixPriceCoinCode());

        JSONObject.parseArray(redisService.get("configCache:klinedataconfig")).forEach(app -> {
            AppConfig appConfig = JSONObject.parseObject(app.toString(), AppConfig.class);
            switch (appConfig.getConfigkey()) {
                case "klinedataurl":
                    fxhParam.setPayUrl(appConfig.getValue());
                    break;
                case "businessNumber":
                    fxhParam.setCompanyCode(appConfig.getValue());
                    break;
                case "publickey":
                    fxhParam.setPublicKey(appConfig.getValue());
                    break;
                case "privatekey":
                    fxhParam.setPrivateKey(appConfig.getValue());
                    break;
            }
        });

        Map<String, Object> map = new HashMap<>(16);
        map.put("ordernumber", UUIDUtil.getUUID());
        map.put("belonged", "交易所6.0");
        map.put("frontWord", fxhParam.getCoinCode());
        map.put("afterWord", fxhParam.getFixCoin());

        try {
            String sign = RSAUtil.encryptByPrivateKey(JSONObject.toJSONString(map), fxhParam.getPrivateKey());
            Map<String, String> paramMap = new HashMap<>(16);
            paramMap.put("companyCode", fxhParam.getCompanyCode());
            paramMap.put("sign", sign);

            String returnMsg = HttpConnectionUtil.doPostQuery(fxhParam.getPayUrl(), paramMap);
            if (StringUtils.isEmpty(returnMsg)) {
                ex.setNowPrice(new BigDecimal("0"));
            } else {
                JSONObject jsonObject = JSONObject.parseObject(returnMsg);
                if ("00000".equals(jsonObject.getString("resultCode"))) {
                    String price = RSAUtil.decryptByPublicKey(jsonObject.getString("data"), fxhParam.getPublicKey());
                    ex.setNowPrice(new BigDecimal(price));
                    jsonResult.setObj(price);
                } else {
                    ex.setNowPrice(new BigDecimal("0"));
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsonResult;
    }
}
