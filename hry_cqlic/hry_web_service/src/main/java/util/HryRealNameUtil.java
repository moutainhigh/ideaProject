package util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import hry.bean.JsonResult;
import hry.manage.remote.model.AppConfig;
import hry.redis.common.utils.RedisService;
import hry.util.SpringUtil;
import hry.util.httpRequest.HttpConnectionUtil;
import hry.util.rsa.FXHParam;
import hry.util.rsa.RSAUtil;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Copyright:   互融云
 *
 * @author: StayBlank
 * @version: V6.0
 * @Date: 2018/11/20 14:11
 */
public class HryRealNameUtil {

    public static JsonResult juheConfigTest(String name,String cardId) {
        SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        SimpleDateFormat format = new SimpleDateFormat("yyMMddHHmmssSSS");
        String time = format.format(new Date(System.currentTimeMillis()));
        String randomStr = RandomStringUtils.random(4, false, true);

        JsonResult jsonResult = new JsonResult();
        //最新价
        RedisService redisService = SpringUtil.getBean("redisService");
        String start = sim.format(new Date(System.currentTimeMillis() - 13000));
        String end = sim.format(new Date(System.currentTimeMillis()));

        FXHParam fxhParam = new FXHParam();


        JSONObject.parseArray(redisService.get("configCache:juheConfig")).forEach(app -> {
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
        //获取平台名称
        String appname = getAppName(redisService, "configCache:extraParamConfig", "managerName");
        Map<String, Object> map = new HashMap<>(16);
        map.put("ordernumber", time + randomStr);
        map.put("belonged", appname);
        map.put("name",name);
        map.put("idCard", cardId);
        map.put("type", "nophoto");

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
                if ("8888".equals(jsonObject.getString("resultCode"))) {
                    String result = RSAUtil.decryptByPublicKey(jsonObject.getString("data"), fxhParam.getPublicKey());
                    jsonResult.setSuccess(true);
                    jsonResult.setMsg(result);
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
    //获取平台名称
    public static String getAppName(RedisService redisService, String s, String managerName) {
        //获取平台名称
        AtomicReference<String> name = new AtomicReference<>("");
        JSONArray configarray = JSONObject.parseArray(redisService.get(s));
        configarray.forEach(app -> {
            JSONObject appConfig = JSONObject.parseObject(app.toString());
            if (managerName.equals(appConfig.getString("configkey"))) {
                name.set(appConfig.getString("value"));
            }
        });
        return name.get();
    }
}
