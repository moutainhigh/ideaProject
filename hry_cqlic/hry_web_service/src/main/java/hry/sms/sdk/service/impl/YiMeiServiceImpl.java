package hry.sms.sdk.service.impl;

import org.springframework.stereotype.Service;

import hry.bean.JsonResult;
import hry.core.sms.SmsParam;
import hry.core.sms.SmsSendUtil;
import hry.manage.remote.model.SmsSendParam;
import hry.util.sys.ContextUtil;
import hry.sms.sdk.service.SdkService;
import hry.sms.send.model.AppSmsSend;
import hry.sms.utils.ym.YiMeiUtils;
import hry.web.remote.RemoteAppConfigService;

@Service("yiMeiService")
public class YiMeiServiceImpl implements SdkService{

	@Override
	public boolean sendSms(AppSmsSend appSmsSend, SmsParam smsParam,String phone) {
		//根据短信类型获得短信模板
		RemoteAppConfigService remoteAppConfigService  = (RemoteAppConfigService) ContextUtil.getBean("remoteAppConfigService");
		String smsOpen = remoteAppConfigService.getValueByKey("smsOpen");
		String hxrt_username = remoteAppConfigService.getValueByKey("sms_username");
		String hxrt_password = remoteAppConfigService.getValueByKey("sms_password");
		if("0".equals(smsOpen)){
			String value = remoteAppConfigService.getValueByKey(smsParam.getHrySmstype());
			String sendContent = SmsSendUtil.replaceKey(value, smsParam);
			String sendSms = YiMeiUtils.sendSms(hxrt_username,hxrt_password,phone,smsParam.getHryCode(),sendContent);
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

	@Override
	public JsonResult checkCard(String name, String idCard) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean sendSmsHai(AppSmsSend appSmsSend, SmsParam smsParam, String Phone) {
		// TODO Auto-generated method stub
		RemoteAppConfigService remoteAppConfigService  = (RemoteAppConfigService) ContextUtil.getBean("remoteAppConfigService");
		String smsOpen = remoteAppConfigService.getValueByKey("smsOpen");
		String hxrt_username = remoteAppConfigService.getValueByKey("sms_username");
		String hxrt_password = remoteAppConfigService.getValueByKey("sms_password");
		if("0".equals(smsOpen)){
			String value = remoteAppConfigService.getValueByKey(smsParam.getHrySmstype());
			String sendContent = SmsSendUtil.replaceKey(value, smsParam);
			String sendSms = YiMeiUtils.sendSms(hxrt_username,hxrt_password,Phone,smsParam.getHryCode(),sendContent);
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

	@Override
	public boolean sendSms(AppSmsSend appSmsSend, SmsSendParam smsSendParam) {
		// TODO Auto-generated method stub
		return false;
	}

}
