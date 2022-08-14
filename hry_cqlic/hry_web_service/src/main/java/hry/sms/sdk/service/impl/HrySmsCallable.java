package hry.sms.sdk.service.impl;

import com.alibaba.fastjson.JSONObject;
import hry.bean.JsonResult;
import hry.core.mvc.model.AppConfig;
import hry.redis.common.utils.RedisService;
import hry.util.SpringUtil;
import hry.util.httpRequest.HttpConnectionUtil;
import hry.util.rsa.FXHParam;
import hry.util.rsa.RSAUtil;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

public class HrySmsCallable  implements Callable {
    private Map<String, String> params;

    public HrySmsCallable(Map<String, String> params) {
        this.params = params;
    }

    @Override
    public JsonResult call() throws Exception {
        {
            SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            SimpleDateFormat format = new SimpleDateFormat("yyMMddHHmmssSSS");
            String time = format.format(new Date(System.currentTimeMillis()));
            String randomStr = RandomStringUtils.random(4, false, true);

            JsonResult jsonResult = new JsonResult();
            //最新价
            RedisService redisService = SpringUtil.getBean("redisService");


            FXHParam fxhParam = new FXHParam();

            JSONObject.parseArray(redisService.get("configCache:smsConfig")).forEach(app -> {
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
            map.put("phone", params.get("mobile"));
            map.put("msg", params.get("text"));

            map.put("signName", "互融云软");
            map.put("templateCode", "SMS_146605017");

            Map<String, String> templateParam = new HashMap<>();

            templateParam.put("HRY_CODE", params.get("code"));
            map.put("templateParam", JSONObject.toJSONString(templateParam));


            try {
                String sign = RSAUtil.encryptByPrivateKey(JSONObject.toJSONString(map), fxhParam.getPrivateKey());
                Map<String, String> paramMap = new HashMap<>();
                paramMap.put("companyCode", fxhParam.getCompanyCode());
                paramMap.put("sign", sign);

                String returnMsg = HttpConnectionUtil.doPostQuery(fxhParam.getPayUrl(), paramMap);
                if (StringUtils.isEmpty(returnMsg)) {
                    jsonResult.setSuccess(false);
                    jsonResult.setMsg(returnMsg);
                } else {
                    JSONObject jsonObject = JSONObject.parseObject(returnMsg);
                    if ("00000".equals(jsonObject.getString("resultCode"))) {
                        String result = RSAUtil.decryptByPublicKey(jsonObject.getString("data"), fxhParam.getPublicKey());
                        jsonResult.setSuccess(true);
                        jsonResult.setMsg(returnMsg);
                    } else {
                        jsonResult.setSuccess(false);
                        jsonResult.setMsg(returnMsg);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return jsonResult;

        }
    }
}
