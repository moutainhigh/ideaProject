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
import hry.util.RemoteQueryFilter;
import hry.util.sys.ContextUtil;
import hry.customer.person.model.AppPersonInfo;
import hry.customer.remote.RemoteAppPersonInfoService;
import hry.manage.remote.model.SmsSendParam;
import hry.sms.sdk.service.SdkService;
import hry.sms.send.model.AppSmsSend;
import hry.sms.utils.hx.HttpSend;
import hry.sms.utils.juhe.JuheUtils;
import hry.web.remote.RemoteAppConfigService;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * <p> TODO</p>
 * @author:         Yuan Zhicheng
 * @Date :          2016年5月18日 下午2:25:52 
 */
@Service("ChuanglanSMSsdkService")
public class ChuanglanSMSSdkServiceImpl implements SdkService{
	private static Logger logger = Logger.getLogger(ChuanglanSMSSdkServiceImpl.class);
	private static final String INTERNATIONAL_URL="http://222.73.117.140:8044/mt";
	
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
			
			//String sendSms = SmsHxUtils.sendSms(hxrt_username,hxrt_password,smsParam.getHryMobilephone(),smsParam.getHryCode(),sendContent);
			String encodedContent = null;
			
			try {
				encodedContent = URLEncoder.encode(sendContent, "utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				return false;
			}
			
			if(!smsParam.getHryMobilephone().startsWith("0")){
				
				RemoteAppPersonInfoService remoteAppPersonInfoService = (RemoteAppPersonInfoService) ContextUtil.getBean("remoteAppPersonInfoService");
				RemoteQueryFilter remoteQueryFilter = new RemoteQueryFilter(AppPersonInfo.class);
				remoteQueryFilter.addFilter("mobilePhone=", smsParam.getHryMobilephone());
				AppPersonInfo appPersonInfo = remoteAppPersonInfoService.get(remoteQueryFilter);
				smsParam.setHryMobilephone(appPersonInfo.getCountry()+smsParam.getHryMobilephone());
			}
			
			StringBuffer strBuf = new StringBuffer();
			strBuf.append("un=").append(hxrt_username);
			strBuf.append("&pw=").append(hxrt_password);
			strBuf.append("&da=").append(smsParam.getHryMobilephone().replaceAll(" ", ""));
			strBuf.append("&sm=").append(encodedContent);
			strBuf.append("&dc=15&rd=1&rf=2&tf=3");
			logger.error("intersms==="+strBuf.toString());
			String sendSms=HttpSend.postSend(INTERNATIONAL_URL, strBuf.toString());
					
			//{"success": true, "id":"16103114271000128850"}
			JSONObject ret=(JSONObject)JSON.parse(sendSms);
			
			appSmsSend.setThirdPartyResult(sendSms);
			appSmsSend.setSendContent(sendContent);
			if((boolean) ret.get("success")){
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
		
/*		String checkCard = "{"
		+    "\"reason\": \"成功1\", "
		+    "    \"result\": {"
		+    "        \"realname\": \"董好帅\","
		+    "        \"idcard\": \"330329199001020012\","
		+    "        \"res\": 2 "
		+    "    },"
		+    "    \"error_code\": 0"
		+    "}";*/
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
	public boolean sendSmsHai(AppSmsSend appSmsSend, SmsParam smsParam, String Phone) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean sendSms(AppSmsSend appSmsSend, SmsSendParam smsSendParam) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
