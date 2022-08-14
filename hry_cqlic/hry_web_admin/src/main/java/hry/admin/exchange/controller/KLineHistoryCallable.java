package hry.admin.exchange.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

import org.apache.commons.lang3.RandomStringUtils;
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
public class KLineHistoryCallable implements Callable<JsonResult>{
	
	private String tradeCoinCode; // 交易币
	
	private String priceCoinCode; // 定价币
	
	private String startTime; // 开始时间
	
	private String endTime; // 结束时间
	
	public KLineHistoryCallable(String startTime, String endTime, String tradeCoinCode, String priceCoinCode){
		this.tradeCoinCode = tradeCoinCode;
		this.priceCoinCode = priceCoinCode;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	@Override
	public JsonResult call() throws Exception {
		
		SimpleDateFormat format = new SimpleDateFormat("yyMMddHHmmssSSS");
		String time = format.format(new Date(System.currentTimeMillis()));
		String randomStr = RandomStringUtils.random(4, false, true);

        JsonResult jsonResult = new JsonResult();
        //最新价
        RedisService redisService = SpringUtil.getBean("redisService");

        FXHParam fxhParam = new FXHParam();
        fxhParam.setCoinCode(tradeCoinCode);
        fxhParam.setFixCoin(priceCoinCode);
        fxhParam.setStartTime(startTime);
        fxhParam.setEndTime(endTime);

        JSONObject.parseArray(redisService.get("configCache:hisklinedataconfig")).forEach(app -> {
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
        map.put("ordernumber", time + randomStr);
        map.put("belonged", "交易所6.0");
        map.put("word", tradeCoinCode + "_" + priceCoinCode);
        map.put("startdate", startTime);
        map.put("enddate", endTime);

        try {
            String sign = RSAUtil.encryptByPrivateKey(JSONObject.toJSONString(map), fxhParam.getPrivateKey());
            Map<String, String> paramMap = new HashMap<>();
            paramMap.put("companyCode", fxhParam.getCompanyCode());
            paramMap.put("sign", sign);

            String returnMsg = HttpConnectionUtil.doPostQuery(fxhParam.getPayUrl(), paramMap);
            if (StringUtils.isEmpty(returnMsg)) {
            	jsonResult.setSuccess(false);
            } else {
                JSONObject jsonObject = JSONObject.parseObject(returnMsg);
                if ("8888".equals(jsonObject.getString("resultCode"))) {
                    String result = RSAUtil.decryptByPublicKey(jsonObject.getString("data"), fxhParam.getPublicKey());
                    jsonResult.setSuccess(true);
                    jsonResult.setObj(result);
                } else {
                	jsonResult.setSuccess(false);
                	jsonResult.setMsg(jsonObject.getString("reason"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonResult;
	}
}
