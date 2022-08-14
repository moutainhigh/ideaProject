package hry.admin.exchange.service.impl;

import com.alibaba.fastjson.JSONObject;
import hry.admin.exchange.model.ExRobot;
import hry.admin.web.model.AppConfig;
import hry.bean.JsonResult;
import hry.listener.ServerManagement;
import hry.redis.common.utils.RedisService;
import hry.util.RSAUtil;
import hry.util.SpringUtil;
import hry.util.UUIDUtil;
import hry.util.httpRequest.HttpConnectionUtil;
import hry.util.rsa.FXHParam;
import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;


public class HryCurrentPriceByApiNoOauthRunable implements Callable<JsonResult> {
	private static Logger logger = Logger.getLogger(HryCurrentPriceByApiNoOauthRunable.class);

	private ExRobot exRobot;


	public HryCurrentPriceByApiNoOauthRunable(ExRobot exRobot){
		this.exRobot=exRobot;
	}


	@Override
	public JsonResult call() throws Exception {
		RedisService redisService = SpringUtil.getBean("redisService");
		JsonResult jsonResult = new JsonResult();

		FXHParam fxhParam = new FXHParam();
		fxhParam.setCoinCode(exRobot.getCoinCode());
		fxhParam.setFixCoin(exRobot.getFixPriceCoinCode());

		JSONObject.parseArray(redisService.get("configCache:klinedataconfig")).forEach(app -> {
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
		map.put("afterWord", fxhParam.getFixCoin());

		try {
			String sign = RSAUtil.encryptByPrivateKey(JSONObject.toJSONString(map), fxhParam.getPrivateKey());
			Map<String, String> paramMap = new HashMap<>(16);
			paramMap.put("companyCode", fxhParam.getCompanyCode());
			paramMap.put("sign", sign);

			logger.error("请求参数:"+paramMap);
			String returnMsg = HttpConnectionUtil.doPostQuery(fxhParam.getPayUrl(), paramMap);
			logger.error("返回参数"+returnMsg);
			if(StringUtils.isEmpty(returnMsg)){
				jsonResult.setMsg("连接失败");
				jsonResult.setSuccess(false);
			}else {
				JSONObject jsonObject = JSONObject.parseObject(returnMsg);
				if("8888".equals(jsonObject.getString("resultCode"))){
					String price = RSAUtil.decryptByPublicKey(jsonObject.getString("data"), fxhParam.getPublicKey());
					logger.error("解密后价格"+price);

					jsonResult.setSuccess(true);
					jsonResult.setObj(price);
					jsonResult.setMsg("连接成功");
				}else {
					jsonResult.setSuccess(false);
					jsonResult.setMsg("外部行情接口返回结果:"+jsonObject.getString("resultCode"));
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return jsonResult;
	}
}
