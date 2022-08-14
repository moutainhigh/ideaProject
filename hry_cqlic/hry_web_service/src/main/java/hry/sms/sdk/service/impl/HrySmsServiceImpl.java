package hry.sms.sdk.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import hry.sms.utils.hx.HttpSend;
import hry.util.properties.PropertiesUtils;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;

import hry.bean.JsonResult;
import hry.core.sms.SmsParam;
import hry.core.sms.SmsSendUtil;
import hry.manage.remote.model.SmsSendParam;
import hry.sms.sdk.service.SdkService;
import hry.sms.send.model.AppSmsSend;
import hry.util.sms.HrySmsCallable;
import hry.util.sys.ContextUtil;
import hry.web.remote.RemoteAppConfigService;

@Service("hrySmsService")
public class HrySmsServiceImpl implements SdkService {
    private static Logger logger = Logger.getLogger(HrySmsServiceImpl.class);

    ExecutorService executor = Executors.newCachedThreadPool();
    //提交地址
    private static final String INTERNATIONAL_URL = "http://sms.yunpian.com/v2/sms/single_send.json";

    @Override
    public boolean sendSms(AppSmsSend appSmsSend, SmsParam smsParam, String phone) {
        //根据短信类型获得短信模板
        RemoteAppConfigService remoteAppConfigService = (RemoteAppConfigService) ContextUtil.getBean("remoteAppConfigService");
        String smsOpen = remoteAppConfigService.getValueByKey("smsOpen");
        String yunpianKey = remoteAppConfigService.getValueByKey("sms_apiKey");
        if ("0".equals(smsOpen)) {//接口是否开启

            Map<String, String> params = new HashMap<String, String>();
            String sendContent="";
            if(smsParam!=null){
                String value = remoteAppConfigService.getValueByKey(smsParam.getHrySmstype());
                sendContent = SmsSendUtil.replaceKey(value, smsParam);
                params.put("code", smsParam.getHryCode());
            }else{
                sendContent=appSmsSend.getSendContent();
            }
            params.put("text", sendContent);
            params.put("mobile", phone.replace(" ", ""));
            HrySmsCallable hrySmsCallable = new HrySmsCallable(params);

            Future<JsonResult> result = executor.submit(hrySmsCallable);
            JsonResult jsonResult = new JsonResult();
            try {
                jsonResult = result.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

            //修改为您要发送的手机号
            if (jsonResult != null) {
                JSONObject myJsonObject = new JSONObject(jsonResult.getMsg());
                String code = myJsonObject.getString("resultCode");
                if (code.equals("8888")) {
                    appSmsSend.setReceiveStatus("1");
                    appSmsSend.setThirdPartyResult("发送成功！");
                    appSmsSend.setSendContent(sendContent);
                    return true;
                } else  {
                    appSmsSend.setReceiveStatus("0");
                    appSmsSend.setThirdPartyResult(myJsonObject.toString());
                    appSmsSend.setSendContent(sendContent);
                    return false;
                }
            } else {
                appSmsSend.setThirdPartyResult("发送失败，调用接口异常！");
                appSmsSend.setSendContent(sendContent);
                return false;
            }
        } else {
            appSmsSend.setThirdPartyResult("【系统短信功能未开启】");
            return false;
        }
    }

    @Override
    public JsonResult checkCard(String name, String idCard) {
        return null;
    }

    @Override
    public boolean sendSmsHai(AppSmsSend appSmsSend, SmsParam smsParam, String Phone) throws IOException {
        //根据短信类型获得短信模板
        RemoteAppConfigService remoteAppConfigService = (RemoteAppConfigService) ContextUtil.getBean("remoteAppConfigService");
        String smsOpen = remoteAppConfigService.getValueByKey("smsOpen");
        String yunpianKey = remoteAppConfigService.getValueByKey("sms_apiKey");
        if ("0".equals(smsOpen)) {//接口是否开启
            String value = remoteAppConfigService.getValueByKey(smsParam.getHrySmstype());
            String sendContent = SmsSendUtil.replaceKey(value, smsParam);

            Map<String, String> params = new HashMap<String, String>();
            params.put("text", sendContent);
            params.put("code", smsParam.getHryCode());
            params.put("mobile", Phone.replace(" ", ""));
            HrySmsCallable hrySmsCallable = new HrySmsCallable(params);

            Future<JsonResult> result = executor.submit(hrySmsCallable);
            JsonResult jsonResult = new JsonResult();
            try {
                jsonResult = result.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            
            System.out.println("手机号Phone===="+Phone);
            System.out.println("短信返回值Code===="+jsonResult.getCode());
            System.out.println("短信返回值Msg===="+jsonResult.getMsg());

            //修改为您要发送的手机号
            if (jsonResult != null) {
                JSONObject myJsonObject = new JSONObject(jsonResult.getMsg());
                String code = myJsonObject.getString("resultCode");
                if (code.equals("8888")) {
                    appSmsSend.setReceiveStatus("1");
                    appSmsSend.setThirdPartyResult("发送成功！");
                    appSmsSend.setSendContent(sendContent);
                    return true;
                } else  {
                    appSmsSend.setReceiveStatus("0");
                    appSmsSend.setThirdPartyResult(myJsonObject.toString());
                    appSmsSend.setSendContent(sendContent);
                    return false;
                }
            } else {
                appSmsSend.setThirdPartyResult("发送失败，调用接口异常！");
                appSmsSend.setSendContent(sendContent);
                return false;
            }
        } else {
            appSmsSend.setThirdPartyResult("【系统短信功能未开启】");
            return false;
        }
    }

    /**
     * 发送短信-新版
     * @param appSmsSend
     * @param smsSendParam
     * @return
     */
    @Override
    public boolean sendSms (AppSmsSend appSmsSend, SmsSendParam smsSendParam) {
        //获取第三方短信开启状态
        RemoteAppConfigService remoteAppConfigService = (RemoteAppConfigService) ContextUtil.getBean("remoteAppConfigService");
        String smsOpen = remoteAppConfigService.getValueByKey("smsOpen");
        String yunpianKey = PropertiesUtils.APP.getProperty("app.yupiankey");
        // 接口是否开启
        if ("0".equals(smsOpen)) {
            // 获取短信内容
            String sendContent = appSmsSend.getSendContent();

            // 设置短信发送参数
            appSmsSend.setPostParam(JSON.toJSONString(smsSendParam));
            if (StringUtils.isEmpty(sendContent)) {
                appSmsSend.setReceiveStatus("0");
                appSmsSend.setThirdPartyResult("短信模板未设置或未开启");
                return false;
            }
            Map<String, String> params = new HashMap<String, String>();
            params.put("apikey", yunpianKey);
            params.put("text", sendContent);
            params.put("code", smsSendParam.getHryCode());
            params.put("mobile", smsSendParam.getHryCountry() + smsSendParam.getHrySmsPhoneNum());
            /*HrySmsCallable hrySmsCallable = new HrySmsCallable(params);
            Future<JsonResult> result = executor.submit(hrySmsCallable);*/
            String sendSms= HttpSend.yunpianPost(INTERNATIONAL_URL, params);

            //修改为您要发送的手机号
            if(sendSms!=null){
                logger.error(sendSms);
                JSONObject myJsonObject = new JSONObject(sendSms);
                int code = myJsonObject.getInt("code");
                System.out.println("resmap.get(code)11=="+code);
                if(code==0){
                    appSmsSend.setReceiveStatus("1");
                    appSmsSend.setThirdPartyResult("发送成功！");
                    appSmsSend.setSendContent(sendContent);
                    return true;
                }else if(code > 0){
                    System.out.println("resmap.get(code)22=="+code);
                    appSmsSend.setReceiveStatus("0");
                    appSmsSend.setThirdPartyResult((String) myJsonObject.get("detail"));
                    //appSmsSend.setThirdPartyResult("调用API时发生错误，需要开发者进行相应的处理。");
                    appSmsSend.setSendContent(sendContent);
                    return false;
                }else if(-50<code && code<=-1){
                    appSmsSend.setReceiveStatus("0");
                    appSmsSend.setThirdPartyResult(sendSms);
                    appSmsSend.setThirdPartyResult("权限验证失败，需要开发者进行相应的处理。");
                    appSmsSend.setSendContent(sendContent);
                    return false;
                }else if(code <= -50){
                    appSmsSend.setReceiveStatus("0");
                    appSmsSend.setThirdPartyResult(sendSms);
                    appSmsSend.setThirdPartyResult("系统内部错误，请联系技术支持，调查问题原因并获得解决方案。");
                    appSmsSend.setSendContent(sendContent);
                    return false;
                }
            }else{
                appSmsSend.setThirdPartyResult("发送失败，调用接口异常！");
                appSmsSend.setSendContent(sendContent);
                return false;
            }
        } else {
            appSmsSend.setThirdPartyResult("系统短信功能未开启");
            return false;
        }
        return false;
    }
}
