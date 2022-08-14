package hry.sms.sdk.service.impl;

import hry.bean.JsonResult;
import hry.core.sms.SmsParam;
import hry.core.sms.SmsSendUtil;
import hry.manage.remote.model.SmsSendParam;
import hry.util.sys.ContextUtil;
import hry.sms.sdk.service.SdkService;
import hry.sms.send.model.AppSmsSend;
import hry.sms.utils.hx.HttpSend;
import hry.sms.utils.ym.YunPianUtils;
import hry.web.remote.RemoteAppConfigService;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import java.net.URLEncoder;
/**
 * 云片短信接口
 * <p> TODO</p>
 * @author:         Zhang Lei 
 * @Date :          2017年2月27日 上午10:25:11
 */
@Service("yunPianService")
public class YunpianServiceImpl implements SdkService{
	private static Logger logger = Logger.getLogger(YunpianServiceImpl.class);
	//提交地址
	private static final String INTERNATIONAL_URL="http://sms.yunpian.com/v2/sms/single_send.json";
	
	@Override
	public boolean sendSms(AppSmsSend appSmsSend,SmsParam smsParam,String phone) {
		//根据短信类型获得短信模板
		RemoteAppConfigService remoteAppConfigService  = (RemoteAppConfigService) ContextUtil.getBean("remoteAppConfigService");
		String smsOpen = remoteAppConfigService.getValueByKey("smsOpen");
		String yunpianKey = remoteAppConfigService.getValueByKey("sms_apiKey");
		if("0".equals(smsOpen)){//接口是否开启
			String value = remoteAppConfigService.getValueByKey(smsParam.getHrySmstype());
			String sendContent = SmsSendUtil.replaceKey(value, smsParam);
			/*String encodedContent = null;
			try {
				encodedContent = URLEncoder.encode(sendContent, "utf-8");
			} catch (UnsupportedEncodingException e) {
				logger.error("云片短信接口,获取替换短信内容异常！！！");
				e.printStackTrace();
				return false;
			}*/
			
			
			Map<String, String> params = new HashMap<String, String>();
			logger.error("sdk===="+yunpianKey);
	        params.put("apikey", yunpianKey);
	        params.put("text", sendContent);
	        params.put("mobile", phone.replace(" ", ""));
	        String sendSms=HttpSend.yunpianPost(INTERNATIONAL_URL, params);
			
			//修改为您要发送的手机号
			if(sendSms!=null){
				logger.error(sendSms);
				JSONObject myJsonObject = new JSONObject(sendSms);
				int code = myJsonObject.getInt("code");
				if(code==0){
					appSmsSend.setReceiveStatus("1");
					appSmsSend.setThirdPartyResult("发送成功！");
					appSmsSend.setSendContent(sendContent);
					return true;
				}else if(code > 0){
					appSmsSend.setReceiveStatus("0");
					appSmsSend.setThirdPartyResult(sendSms);
					appSmsSend.setThirdPartyResult("调用API时发生错误，需要开发者进行相应的处理。");
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
		}else{
			appSmsSend.setThirdPartyResult("【系统短信功能未开启】");
			return false;
		}
		return false;
	}

	@Override
	public JsonResult checkCard(String name, String idCard) {
		return null;
	}

	/*@Override
	public boolean sendSmsHai(AppSmsSend appSmsSend, SmsParam smsParam, String Phone) throws IOException {
		//根据短信类型获得短信模板
				RemoteAppConfigService remoteAppConfigService  = (RemoteAppConfigService) ContextUtil.getBean("remoteAppConfigService");
				String smsOpen = remoteAppConfigService.getValueByKey("smsOpen");
				String hxrt_username = remoteAppConfigService.getValueByKey("sms_username");
				String hxrt_password = remoteAppConfigService.getValueByKey("sms_password");
				if("0".equals(smsOpen)){
					String value = remoteAppConfigService.getValueByKey(smsParam.getHrySmstype());
					String sendContent = SmsSendUtil.replaceKey(value, smsParam);
					String sendSms = YunPianUtils.sendSms(hxrt_username,hxrt_password,Phone,smsParam.getHryCode(),sendContent);
					//String sendSms = SmsHxUtils.sendSms(hxrt_username,hxrt_password,smsParam.getHryMobilephone().replaceAll(" ", ""),smsParam.getHryCode(),sendContent);
					appSmsSend.setThirdPartyResult(sendSms);
					appSmsSend.setSendContent(sendContent);
					if(sendSms.equals("0")){
						return true;
					}else{
						return false;
					}
				}else{
					appSmsSend.setThirdPartyResult("【系统短信功能未开启】");
					return false;
				}
	}
	
	*/
	
	
	@Override
	public boolean sendSmsHai(AppSmsSend appSmsSend, SmsParam smsParam, String Phone) {/*
		// TODO Auto-generated method stu
		RemoteAppConfigService remoteAppConfigService  = (RemoteAppConfigService) ContextUtil.getBean("remoteAppConfigService");
		String smsOpen = remoteAppConfigService.getValueByKey("smsOpen");
		String apikey = remoteAppConfigService.getValueByKey("sms_apiKey");
		if("0".equals(smsOpen)){
				String value = remoteAppConfigService.getValueByKey(smsParam.getHrySmstype());
				String sendContent = SmsSendUtil.replaceKey(value, smsParam);
	
				String sendSms = YunPianUtils.sendSms(apikey, sendContent, Phone.replace(" ", ""));
				appSmsSend.setThirdPartyResult(sendSms);
				appSmsSend.setSendContent(sendContent);
				Map<String, String> map = new HashMap<String, String>();
				sendSms = sendSms.substring(1, sendSms.length()-1);//去掉前后括号
				String[] arraydata = sendSms.split(",");//按“，”将其分为字符数组
				String trim = arraydata[1].trim();
				int start = trim.indexOf(":");
				String name = trim.substring(start);
				String addressqu = name.substring(name.lastIndexOf(":")+1);
				String replace = addressqu.replace("\"","");
				if(replace.equals("发送成功")){
					appSmsSend.setReceiveStatus("1");
					appSmsSend.setThirdPartyResult("发送成功！");
					return true;
				}else{
					appSmsSend.setReceiveStatus("0");
					appSmsSend.setThirdPartyResult(sendSms);
					appSmsSend.setThirdPartyResult("系统内部错误，请联系技术支持，调查问题原因并获得解决方案。");
					appSmsSend.setSendContent(sendContent);
					return false;
			    }
		   }else{
				appSmsSend.setThirdPartyResult("【系统短信功能未开启】");
				return false;
		  }
	  */

		//根据短信类型获得短信模板
		RemoteAppConfigService remoteAppConfigService  = (RemoteAppConfigService) ContextUtil.getBean("remoteAppConfigService");
		String smsOpen = remoteAppConfigService.getValueByKey("smsOpen");
		String yunpianKey = remoteAppConfigService.getValueByKey("sms_apiKey");
		if("0".equals(smsOpen)){//接口是否开启
			String value = remoteAppConfigService.getValueByKey(smsParam.getHrySmstype());
			String sendContent = SmsSendUtil.replaceKey(value, smsParam);
			/*String encodedContent = null;
			try {
				encodedContent = URLEncoder.encode(sendContent, "utf-8");
			} catch (UnsupportedEncodingException e) {
				logger.error("云片短信接口,获取替换短信内容异常！！！");
				e.printStackTrace();
				return false;
			}*/
			
			
			Map<String, String> params = new HashMap<String, String>();
			logger.error("sdk===="+yunpianKey);
	        params.put("apikey", yunpianKey);
	        params.put("text", sendContent);
	        params.put("mobile", Phone.replace(" ", ""));
	        String sendSms=HttpSend.yunpianPost(INTERNATIONAL_URL, params);
			
			//修改为您要发送的手机号
			if(sendSms!=null){
				logger.error(sendSms);
				JSONObject myJsonObject = new JSONObject(sendSms);
				int code = myJsonObject.getInt("code");
				if(code==0){
					appSmsSend.setReceiveStatus("1");
					appSmsSend.setThirdPartyResult("发送成功！");
					appSmsSend.setSendContent(sendContent);
					return true;
				}else if(code > 0){
					appSmsSend.setReceiveStatus("0");
					appSmsSend.setThirdPartyResult(sendSms);
					appSmsSend.setThirdPartyResult("调用API时发生错误，需要开发者进行相应的处理。");
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
		}else{
			appSmsSend.setThirdPartyResult("【系统短信功能未开启】");
			return false;
		}
		return false;
		}

	public static void main(String[] args) {
		Map<String, String> params = new HashMap<String, String>();
        params.put("apikey", "18957d06c31b9aeda1fe9810c0c11dc4");
        params.put("text", "【7ebit】Your verification code is 123456");
        params.put("mobile", "+8615926553196".replace(" ", ""));//+1 2029962812
        String sendSms=HttpSend.yunpianPost(INTERNATIONAL_URL, params);
        logger.error(sendSms);
	}

	@Override
	public boolean sendSms(AppSmsSend appSmsSend, SmsSendParam smsSendParam) {
		// TODO Auto-generated method stub
		return false;
	}
}
