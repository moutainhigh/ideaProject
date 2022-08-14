/**
 * Copyright:   北京互融时代软件有限公司
 * @author:      Liu Shilei
 * @version:      V1.0 
 * @Date:        2016年5月18日 下午2:25:52
 */
package hry.sms.sdk.service.impl;

import hry.bean.JsonResult;
import hry.core.sms.SmsParam;
import hry.core.sms.SmsSendUtil;
import hry.manage.remote.model.SmsSendParam;
import hry.util.sys.ContextUtil;
import hry.sms.sdk.service.SdkService;
import hry.sms.send.model.AppSmsSend;
import hry.sms.utils.hx.SmsHxUtils;
import hry.sms.utils.juhe.JuheUtils;
import hry.web.remote.RemoteAppConfigService;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.net.UnknownHostException;

/**
 * <p> TODO</p>
 * @author:         Liu Shilei 
 * @Date :          2016年5月18日 下午2:25:52 
 */
@Service("sdkService")
public class SdkServiceImpl implements SdkService{

	private static Logger logger = Logger.getLogger(SdkServiceImpl.class);
	
	@Override
	public boolean sendSms(AppSmsSend appSmsSend,SmsParam smsParam,String phone) {
		
		//根据短信类型获得短信模板
		RemoteAppConfigService remoteAppConfigService  = (RemoteAppConfigService) ContextUtil.getBean("remoteAppConfigService");
		String smsOpen = remoteAppConfigService.getValueByKey("smsOpen");
		String hxrt_username = remoteAppConfigService.getValueByKey("sms_username");
		String hxrt_password = remoteAppConfigService.getValueByKey("sms_password");
		if("0".equals(smsOpen)){
			String value = remoteAppConfigService.getValueByKey(smsParam.getHrySmstype());
			String sendContent = SmsSendUtil.replaceKey(value, smsParam);
			String sendSms ="";
			try {
				sendSms = SmsHxUtils.sendSms(hxrt_username,hxrt_password,phone,smsParam.getHryCode(),sendContent);
			}catch (UnknownHostException u){

				//如果短信发送失败就再发一次
				logger.error("发送短信错误一次");
				try {
					Thread.sleep(4000);
					sendSms = SmsHxUtils.sendSms(hxrt_username,hxrt_password,phone,smsParam.getHryCode(),sendContent);
				}catch (UnknownHostException u1){
					logger.error("发送短信错误两次次");
					u1.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			logger.info(sendSms);
			System.out.println("sendSms = " + sendSms);
			appSmsSend.setThirdPartyResult(sendSms);
			appSmsSend.setSendContent(sendContent);
			if(sendSms != null && sendSms.contains("message=短信发送成功")){
				return true;
			}else{
				return false;
			}
		}else{
			appSmsSend.setThirdPartyResult("【系统短信功能未开启】");
			return false;
		}
	}

	
	
	@Override
	public boolean sendSmsHai(AppSmsSend appSmsSend,SmsParam smsParam,String Phone) {
		
		//根据短信类型获得短信模板
		RemoteAppConfigService remoteAppConfigService  = (RemoteAppConfigService) ContextUtil.getBean("remoteAppConfigService");
		String smsOpen = remoteAppConfigService.getValueByKey("smsOpen");
		String hxrt_username = remoteAppConfigService.getValueByKey("sms_username");
		String hxrt_password = remoteAppConfigService.getValueByKey("sms_password");
		if("0".equals(smsOpen)){
			String value = remoteAppConfigService.getValueByKey(smsParam.getHrySmstype());
			String sendContent = SmsSendUtil.replaceKey(value, smsParam);
			String sendSms = null;
			try {
				sendSms = SmsHxUtils.sendSms(hxrt_username,hxrt_password,Phone,smsParam.getHryCode(),sendContent);
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
			appSmsSend.setThirdPartyResult(sendSms);
			appSmsSend.setSendContent(sendContent);
			if(sendSms.contains("message=短信发送成功")){
				return true;
			}else{
				return false;
			}
		}else{
			appSmsSend.setThirdPartyResult("【系统短信功能未开启】");
			return false;
		}
	}
	
	
	@Override
	public JsonResult checkCard(String name, String idCard) {
		JsonResult jsonResult = new JsonResult();
		RemoteAppConfigService remoteAppConfigService  = (RemoteAppConfigService) ContextUtil.getBean("remoteAppConfigService");
		String juhe_cardKey = remoteAppConfigService.getValueByKey("juhe_cardKey");
		String juhe_cardUrl = remoteAppConfigService.getValueByKey("juhe_cardUrl");
		String checkCard = JuheUtils.checkCard(juhe_cardUrl, juhe_cardKey, idCard, name);
		
		jsonResult.setMsg(checkCard);
		try {
			JSONObject parseObject = JSON.parseObject(checkCard);
			JSONObject result = (JSONObject) parseObject.get("result");
			Integer res = (Integer) result.get("res");
			if(1==res.intValue()){/*1：匹配 2：不匹配*/
				jsonResult.setSuccess(true);
			}
		} catch (Exception e) {
		}
		return jsonResult;
	}



	@Override
	public boolean sendSms(AppSmsSend appSmsSend, SmsSendParam smsSendParam) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
