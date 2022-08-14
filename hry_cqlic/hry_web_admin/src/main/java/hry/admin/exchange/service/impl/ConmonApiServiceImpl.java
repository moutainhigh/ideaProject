package hry.admin.exchange.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import hry.admin.exchange.model.CommenApiUrl;
import hry.admin.exchange.model.ExRobot;
import hry.admin.exchange.service.ConmonApiService;
import hry.admin.web.model.AppConfig;
import hry.bean.JsonResult;
import hry.core.thread.ThreadPool;
//import hry.interfaces.CommonSendUtil;
//import hry.interfaces.commonRequest.HryPythonApiCommonRequest;
//import hry.interfaces.commonResponse.HryPythonApiCommonResponse;
import hry.listener.ServerManagement;
import hry.redis.common.utils.RedisService;
import hry.util.RSAUtil;
import hry.util.SpringUtil;
import hry.util.UUIDUtil;
import hry.util.httpRequest.HttpConnectionUtil;
import hry.util.rsa.FXHParam;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Service("conmonApiService")
public class ConmonApiServiceImpl implements ConmonApiService {
    private static Logger logger = Logger.getLogger(ConmonApiServiceImpl.class);
    @Resource
    private RedisService redisService;
    ExecutorService executor = Executors.newFixedThreadPool(5);

    public String getKkcoinCurrentPriceByApi(ExRobot exRobot) {
        CloseableHttpClient client = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String coinCode = exRobot.getCoinCode();
        String fixPriceCoinCode = exRobot.getFixPriceCoinCode();
        String parm = coinCode + "_" + fixPriceCoinCode;
        String priceSource = exRobot.getPriceSource();//kkcoin
        try {
            String url = CommenApiUrl.kkcoin_urlPrice;
            url = url + parm;
            HttpGet request = new HttpGet(url);
            response = client.execute(request);
            if (response.getEntity() != null) {
                String responseContent = EntityUtils.toString(response.getEntity());
                JSONArray jsonv5 = JSON.parseArray(responseContent);
                if (jsonv5.size() == 0) {
                    logger.error(parm + "----kkcoin没有此交易对");
                    return null;
                } else {
                    Object a = jsonv5.get(0);
                    String pricestr = a.toString().split(",")[1];
                    String price = pricestr.substring(1, pricestr.length() - 1);
                    logger.error(parm + "==price==" + price);
                    return price;

                }

            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                response.close();
                client.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    public JsonResult getHryCurrentPriceByApi(ExRobot exRobot) {
        RedisService redisService = SpringUtil.getBean("redisService");
        JsonResult jsonResult = new JsonResult();

        FXHParam fxhParam = new FXHParam();
        fxhParam.setCoinCode(exRobot.getCoinCode());
        fxhParam.setFixCoin(exRobot.getFixPriceCoinCode());
        String config = redisService.get("configCache:klinedataconfig");

        JSONArray arrays = JSONObject.parseArray(config);
        arrays.forEach(app -> {
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
            //paramMap.put("sign", JSONObject.toJSONString(map));

            logger.error("请求参数:" + paramMap);
            String returnMsg = HttpConnectionUtil.doPostQuery(fxhParam.getPayUrl(), paramMap);
            logger.error("返回参数" + returnMsg);
            if (StringUtils.isEmpty(returnMsg)) {
                jsonResult.setMsg("连接失败");
                jsonResult.setSuccess(false);
            } else {
                JSONObject jsonObject = JSONObject.parseObject(returnMsg);
                if ("8888".equals(jsonObject.getString("resultCode"))) {
                    String price = RSAUtil.decryptByPublicKey(jsonObject.getString("data"), fxhParam.getPublicKey());
                    //String price = jsonObject.getString("data");
                    logger.error("解密后价格" + price);

                    jsonResult.setSuccess(true);
                    jsonResult.setObj(price);
                    jsonResult.setMsg("连接成功");
                } else {
                    jsonResult.setSuccess(false);
                    jsonResult.setMsg("外部行情接口返回结果:" + jsonObject.getString("reason"));
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsonResult;
    }


    public String getOkcoinCurrentPriceByApi(ExRobot exRobot) {
        CloseableHttpClient client = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String coinCode = exRobot.getCoinCode();
        String fixPriceCoinCode = exRobot.getFixPriceCoinCode();
        String parm = coinCode + "_" + fixPriceCoinCode;
        String priceSource = exRobot.getPriceSource();//okcoin
        try {
            String url = CommenApiUrl.okcoin_urlPrice;
            url = url + parm.toLowerCase();
            HttpGet request = new HttpGet(url);
            response = client.execute(request);
            if (response.getEntity() != null) {
                String responseContent = EntityUtils.toString(response.getEntity());
                JSONObject jsonv5 = JSON.parseObject(responseContent);
                if (jsonv5.get("error_code").toString().equals("1007")) {
                    logger.error(parm + "---okcoin没有此交易对");
                    return null;
                } else {
                    String pricestr = jsonv5.get("last").toString();
                    logger.error(parm + "==price==" + pricestr);
                    return pricestr;
                }

            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                response.close();
                client.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    public String getBittrexCurrentPriceByApi(ExRobot exRobot) {
        CloseableHttpClient client = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String str = null;
        String coinCode = exRobot.getCoinCode();
        String fixPriceCoinCode = exRobot.getFixPriceCoinCode();
        String parm = fixPriceCoinCode + "-" + coinCode;
        try {
            String url = CommenApiUrl.bittrex_urlPrice;
            HttpGet request = new HttpGet(url + parm);
            response = client.execute(request);
            if (response.getEntity() != null) {
                String responseContent = EntityUtils.toString(response.getEntity());
                JSONObject data = JSONObject.parseObject(responseContent);
                if ("true".equals(data.getString("success"))) {
                    JSONObject dataobj = data.getJSONObject("result");
                    String pricestr = dataobj.getString("Last");
                    logger.error(parm + "==bittrex--price==" + pricestr);
                    return pricestr;
                    //result.setObj(list);
                } else {
                    logger.error(parm + "---bittrex没有此交易对");
                    return null;
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                response.close();
                client.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;

    }

    @Override
    public String getCurrentPriceByApi(ExRobot exRobot) {
        if (null != exRobot.getPriceSource()) {
            if (exRobot.getPriceSource().equals("okcoin")) {
                return getOkcoinCurrentPriceByApi(exRobot);
            } else if (exRobot.getPriceSource().equals("kkcoin")) {
                return getKkcoinCurrentPriceByApi(exRobot);
            } else if (exRobot.getPriceSource().equals("bittrex")) {
                return getBittrexCurrentPriceByApi(exRobot);
            } else if (exRobot.getPriceSource().equals("hry")) {
                JsonResult jsonResult = getHryCurrentPriceByApi(exRobot);
                if (jsonResult.getSuccess()) {
                    return jsonResult.getObj().toString();
                }
                return jsonResult.getMsg();
            }
        }
        return null;


    }


}
