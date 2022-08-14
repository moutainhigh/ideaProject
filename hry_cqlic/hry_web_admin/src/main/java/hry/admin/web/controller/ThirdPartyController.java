package hry.admin.web.controller;

import com.alibaba.fastjson.JSONObject;
import hry.admin.web.model.AppConfig;
import hry.bean.JsonResult;
import hry.listener.ServerManagement;
import hry.redis.common.utils.RedisService;
import hry.util.RSAUtil;
import hry.util.UUIDUtil;
import hry.util.httpRequest.HttpConnectionUtil;
import hry.util.rsa.FXHParam;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/web/thirdParty")
public class ThirdPartyController {
    private static Logger logger = Logger.getLogger(ThirdPartyController.class);
    @Resource
    private RedisService redisService;


    @RequestMapping("/getBalance")
    public JsonResult getBalance(){
        JsonResult jsonResult = new JsonResult();
        FXHParam fxhParam = new FXHParam();
        fxhParam.setCoinCode("BTC");
        fxhParam.setFixCoin("USDT");

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
                    jsonResult.setMsg("连接成功");
                }else {
                    jsonResult.setSuccess(false);
                    jsonResult.setMsg("外部行情接口返回结果:"+jsonObject.getString("reason"));
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsonResult;
    }
}
