package hry.trade.robot.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import hry.bean.JsonResult;
import hry.core.constant.StringConstant;
import hry.core.mvc.model.AppConfig;
import hry.core.thread.ThreadPool;

import hry.redis.common.utils.RedisService;
import hry.trade.entrust.dao.CommonDao;
import hry.trade.model.CommenApiUrl;
import hry.trade.robot.model.ExRobot;
import hry.util.UUIDUtil;
import hry.util.httpRequest.HttpConnectionUtil;
import hry.util.rsa.FXHParam;
import hry.trade.robot.service.ConmonApiService;
import hry.util.rsa.RSAUtil;

import org.apache.commons.httpclient.util.DateUtil;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Service("conmonApiService")
public class ConmonApiServiceImpl implements ConmonApiService {
    @Resource
    private RedisService redisService;
    @Resource
    public CommonDao commonDao;

    @Override
    public void getKkcoinCurrentPriceByApi(ExRobot exRobot) {
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
                    System.out.println(parm + "----kkcoin没有此交易对");
                } else {
                    Object a = jsonv5.get(0);
                    String pricestr = a.toString().split(",")[1];
                    String price = pricestr.substring(1, pricestr.length() - 1);
                    System.out.println(parm + "==price==" + price);
                    redisService.save(coinCode + "_" + fixPriceCoinCode + ":thirdApi:currentExchangPrice", price, 60);

                }

            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                client.close();
                response.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void getHryCurrentPriceByApi(ExRobot exRobot) {
        FXHParam fxhParam = new FXHParam();
        fxhParam.setCoinCode(exRobot.getCoinCode());
        fxhParam.setFixCoin(exRobot.getFixPriceCoinCode());

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
            //paramMap.put("sign", JSONObject.toJSONString(map));

            String returnMsg = HttpConnectionUtil.doPostQuery(fxhParam.getPayUrl(), paramMap);
        //    System.out.println("返回参数" + returnMsg);
            JSONObject jsonObject = JSONObject.parseObject(returnMsg);
            if ("8888".equals(jsonObject.getString("resultCode"))) {
                String price = RSAUtil.decryptByPublicKey(jsonObject.getString("data"), fxhParam.getPublicKey());
                //String price = jsonObject.getString("data");
            //    System.out.println("解密后价格" + price);
                redisService.save(exRobot.getCoinCode() + "_" + exRobot.getFixPriceCoinCode() + ":thirdApi:currentExchangPriceTime",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                redisService.save(exRobot.getCoinCode() + "_" + exRobot.getFixPriceCoinCode() + ":thirdApi:currentExchangPrice", price);
            }else{
            	
              System.out.println("取第三方价格失败" + returnMsg);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void getOkcoinCurrentPriceByApi(ExRobot exRobot) {
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
                    System.out.println(parm + "---okcoin没有此交易对");
                } else {
                    String pricestr = jsonv5.get("last").toString();
                    System.out.println(parm + "==price==" + pricestr);
                    redisService.save(coinCode + "_" + fixPriceCoinCode + ":thirdApi:currentExchangPrice", pricestr, 60);

                }

            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                client.close();
                response.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void getBittrexCurrentPriceByApi(ExRobot exRobot) {
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
                    redisService.save(coinCode + "_" + fixPriceCoinCode + ":thirdApi:currentExchangPrice", dataobj.getString("Last"));
                    System.out.println(parm + "==bittrex--price==" + pricestr);
                    //result.setObj(list);
                } else {
                    System.out.println(parm + "---bittrex没有此交易对");
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                client.close();
                response.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void getOkexCurrentPriceByApi(ExRobot exRobot) {
        CloseableHttpClient client = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String coinCode = exRobot.getCoinCode();
        String fixPriceCoinCode = exRobot.getFixPriceCoinCode();
        String parm = fixPriceCoinCode + "_" + coinCode;
        try {
            String url = CommenApiUrl.okex_urlPrice;
            HttpGet request = new HttpGet(url + parm);

            response = client.execute(request);
            if (response.getEntity() != null) {
                String responseContent = EntityUtils.toString(response.getEntity());
                JSONObject data = JSONObject.parseObject(responseContent);
                if (!StringUtils.isEmpty(data.getString("ticker"))) {
                    JSONObject dataobj = data.getJSONObject("ticker");
                    String pricestr = dataobj.getString("last");
                    redisService.save(coinCode + "_" + fixPriceCoinCode + ":thirdApi:currentExchangPrice", dataobj.getString("last"));
                    System.out.println(parm + "==okex--price==" + pricestr);
                    //result.setObj(list);
                } else {
                    System.out.println(parm + "---okex没有此交易对");
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                client.close();
                response.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void getzbCurrentPriceByApi(ExRobot exRobot) {

        CloseableHttpClient client = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String coinCode = exRobot.getCoinCode();
        String fixPriceCoinCode = exRobot.getFixPriceCoinCode();
        String parm = coinCode + "_" + fixPriceCoinCode;
        try {
            String url = CommenApiUrl.zb_urlPrice;
            HttpGet request = new HttpGet(url + parm);

            response = client.execute(request);
            if (response.getEntity() != null) {
                String responseContent = EntityUtils.toString(response.getEntity());
                JSONObject data = JSONObject.parseObject(responseContent);
                if (!StringUtils.isEmpty(data.getString("ticker"))) {
                    JSONObject dataobj = data.getJSONObject("ticker");
                    String pricestr = dataobj.getString("last");
                    redisService.save(coinCode + "_" + fixPriceCoinCode + ":thirdApi:currentExchangPrice", dataobj.getString("last"));
                    System.out.println(parm + "==zb--price==" + pricestr);
                    //result.setObj(list);
                } else {
                    System.out.println(parm + "---zb没有此交易对");
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                client.close();
                response.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

    @Override
    public void getCurrentPriceByApi() {
        List<ExRobot> getExRobotPriceSourcelist = commonDao.getExRobotPriceSource();
        for (ExRobot exRobot : getExRobotPriceSourcelist) {
            if (null != exRobot.getPriceSource()) {
                if (exRobot.getPriceSource().equals("okcoin")) {
                    OkcoinCurrentPriceByApiRunable runable = new OkcoinCurrentPriceByApiRunable(exRobot);
                    ThreadPool.exe(runable);
                    getOkcoinCurrentPriceByApi(exRobot);
                } else if (exRobot.getPriceSource().equals("kkcoin")) {
                    KkcoinCurrentPriceByApiRunable runable = new KkcoinCurrentPriceByApiRunable(exRobot);
                    ThreadPool.exe(runable);
                } else if (exRobot.getPriceSource().equals("bittrex")) {
                    BittrexCurrentPriceByApiRunable runable = new BittrexCurrentPriceByApiRunable(exRobot);
                    ThreadPool.exe(runable);
                } else if (exRobot.getPriceSource().equals("okex")) {
                    OkexCurrentPriceByApiRunable runable = new OkexCurrentPriceByApiRunable(exRobot);
                    ThreadPool.exe(runable);
                } else if (exRobot.getPriceSource().equals("zb")) {
                    ZbCurrentPriceByApiRunable runable = new ZbCurrentPriceByApiRunable(exRobot);
                    ThreadPool.exe(runable);
                }else if (exRobot.getPriceSource().equals("hry")) {
                    HryCurrentPriceByApiRunable runable = new HryCurrentPriceByApiRunable(exRobot);
                    ThreadPool.exe(runable);
                }
            }


        }


    }


}
