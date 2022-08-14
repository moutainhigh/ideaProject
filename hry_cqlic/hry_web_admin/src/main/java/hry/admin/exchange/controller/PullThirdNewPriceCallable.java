package hry.admin.exchange.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;

import hry.admin.web.model.AppConfig;
import hry.bean.JsonResult;
import hry.redis.common.utils.RedisService;
import hry.util.RSAUtil;
import hry.util.SpringUtil;
import hry.util.httpRequest.HttpConnectionUtil;
import hry.util.rsa.FXHParam;

/**
 * 拉取第三方价格
 * PullThirdNewPriceCallable.java
 * @author denghf
 * 2018年9月12日 下午1:27:33
 */
public class PullThirdNewPriceCallable implements Callable<JsonResult>{
	
	private String tradeCoinCode; // 交易币
	
	private String priceCoinCode; // 定价币
	
	public PullThirdNewPriceCallable(String tradeCoinCode, String priceCoinCode){
		this.tradeCoinCode = tradeCoinCode;
		this.priceCoinCode = priceCoinCode;
	}

	@Override
	public JsonResult call() throws Exception {

        JsonResult jsonResult = new JsonResult();
        //最新价
        RedisService redisService = SpringUtil.getBean("redisService");

        FXHParam fxhParam = new FXHParam();
        fxhParam.setCoinCode(tradeCoinCode);
        fxhParam.setFixCoin(priceCoinCode);

        JSONObject.parseArray(redisService.get("configCache:klinedataconfig")).forEach(app -> {
            AppConfig appConfig = JSONObject.parseObject(app.toString(), AppConfig.class);
            switch (appConfig.getConfigkey()) {
                case "klinedataurl":
                    fxhParam.setPayUrl(appConfig.getValue() + "api/python/feixiaohaoparamData");
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
        map.put("ordernumber", "RandomUtil.getRandom(32)");
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
            	jsonResult.setSuccess(false);
            } else {
                JSONObject jsonObject = JSONObject.parseObject(returnMsg);
                if ("00000".equals(jsonObject.getString("resultCode"))) {
                    String price = RSAUtil.decryptByPublicKey(jsonObject.getString("data"), fxhParam.getPublicKey());
                    jsonResult.setSuccess(false);
                    jsonResult.setObj(price);
                } else {
                	jsonResult.setSuccess(false);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonResult;
	}
}
