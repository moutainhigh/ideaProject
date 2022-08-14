package hry.sms.remote;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;

import hry.bean.JsonResult;
import hry.core.sms.SmsParam;
import hry.core.sms.SmsSendUtil;
import hry.manage.remote.RemoteSmsService;
import hry.manage.remote.model.SmsSendParam;
import hry.sms.sdk.service.SdkService;
import hry.sms.send.model.AppSmsSend;
import hry.sms.send.service.AppSmsSendService;
import hry.util.QueryFilter;
import hry.util.properties.PropertiesUtils;
import hry.util.sys.ContextUtil;
import hry.web.app.model.AppSmsTemplate;
import hry.web.app.service.AppSmsTemplateService;

public class RemoteSmsServiceImpl implements RemoteSmsService {
	private static Logger logger = Logger.getLogger(RemoteSmsServiceImpl.class);
	@Override
	public void sendsmsHai(String params, String phoneType, String phone) {

		String param = params;
		logger.error("发送短信，接收到的请求参数：" + param);
		SmsParam smsParam = JSON.parseObject(param, SmsParam.class);

		if (SmsSendUtil.WITHDRAW_RMBORCOIN.equals(smsParam.getHrySmstype()) || SmsSendUtil.WITHDRAW_RMBORCOIN_FRONT.equals(smsParam.getHrySmstype()) || SmsSendUtil.SMS_RMBWITHDRAW_INVALID.equals(smsParam.getHrySmstype()) || SmsSendUtil.SMS_COINWITHDRAW_INVALID.equals(smsParam.getHrySmstype()) || SmsSendUtil.SMS_RMBDEPOSIT_INVALID.equals(smsParam.getHrySmstype())) {
			// 汉字需要转码
			String code = smsParam.getHryCode();
			logger.error("发送短信，接收到的请求参数HryCode（提现）：" + code);

			if ("BTC".equals(code)) {
				smsParam.setHryCode("比特币");
			} else if ("LTC".equals(code)) {
				smsParam.setHryCode("莱特币");
			} else if ("CRTC".equals(code)) {
				smsParam.setHryCode("联合学分");
			}
		}

		// 内部验证密码
		String smsKey = smsParam.getSmsKey();
		// 获得sendId
		Long sendId = smsParam.getSendId();
		logger.error("收到短信发送请求sendId=" + sendId);
		try {
			Thread.sleep(5000);// 休息5秒

			JsonResult jsonResult = new JsonResult();
			AppSmsSendService appSmsSendService = (AppSmsSendService) ContextUtil.getBean("appSmsSendService");
			AppSmsSend appSmsSend = new AppSmsSend();

			// 标记为已接收到些条记录
			appSmsSend.setReceiveStatus("1");
			// 判断密钥
			if (!StringUtils.isEmpty(smsKey) && "hurongyuseen".equals(smsKey)) {
				String serviceName = "";
				SdkService sdkService;
				if (phoneType.equals("+86")) {
					serviceName = PropertiesUtils.APP.getProperty("app.smsServiceName");
				} else {
					serviceName = PropertiesUtils.APP.getProperty("app.smsHaiName");

				}
				// 短信直接走saas平台
				serviceName = "hrySmsService";
				if (!StringUtils.isEmpty(serviceName)) {
					sdkService = (SdkService) ContextUtil.getBean(serviceName);
				} else {
					sdkService = (SdkService) ContextUtil.getBean("sdkService");
				}

				// 设置短信发送类型
				appSmsSend.setType(SmsSendUtil.getSendTypeValue(smsParam.getHrySmstype()));
				appSmsSend.setPostParam(JSON.toJSONString(smsParam));
				//boolean sendSmsHai = sdkService.sendSmsHai(appSmsSend, smsParam, URLEncoder.encode(phoneType + phone));
				boolean sendSmsHai = sdkService.sendSmsHai(appSmsSend, smsParam, phoneType + phone);
				// 如果发送标记为成功,则标记为已发送
				if (sendSmsHai) {
					appSmsSend.setSendStatus("1");
				}
				jsonResult.setSuccess(sendSmsHai);
			} else {
				jsonResult.setSuccess(false);
				jsonResult.setMsg("非法请求，密码不正确");
			}
			appSmsSendService.save(appSmsSend);

		} catch (InterruptedException | IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void sendsms(String params, String phone) {

		String param = params;
		//logger.error("发送短信，接收到的请求参数：" + param);
		SmsParam smsParam = JSON.parseObject(param, SmsParam.class);

		if (SmsSendUtil.WITHDRAW_RMBORCOIN.equals(smsParam.getHrySmstype()) || SmsSendUtil.WITHDRAW_RMBORCOIN_FRONT.equals(smsParam.getHrySmstype()) || SmsSendUtil.SMS_RMBWITHDRAW_INVALID.equals(smsParam.getHrySmstype()) || SmsSendUtil.SMS_COINWITHDRAW_INVALID.equals(smsParam.getHrySmstype()) || SmsSendUtil.SMS_RMBDEPOSIT_INVALID.equals(smsParam.getHrySmstype())) {
			// 汉字需要转码
			String code = smsParam.getHryCode();
			logger.error("发送短信，接收到的请求参数HryCode（提现）：" + code);

			if ("BTC".equals(code)) {
				smsParam.setHryCode("比特币");
			} else if ("LTC".equals(code)) {
				smsParam.setHryCode("莱特币");
			} else if ("CRTC".equals(code)) {
				smsParam.setHryCode("联合学分");
			}
		}

		// 内部验证密码
		String smsKey = smsParam.getSmsKey();
		String hrySmstype = smsParam.getHrySmstype();
		// 获得sendId
		Long sendId = smsParam.getSendId();
		logger.error("收到短信发送请求sendId=" + sendId);
		try {
			JsonResult jsonResult = new JsonResult();
			AppSmsSendService appSmsSendService = (AppSmsSendService) ContextUtil.getBean("appSmsSendService");
			AppSmsSend appSmsSend = new AppSmsSend();

			// 标记为已接收到些条记录
			appSmsSend.setReceiveStatus("1");
			// 判断密钥
			if (!StringUtils.isEmpty(smsKey) && "hurongyuseen".equals(smsKey)) {
				String serviceName = "";
				SdkService sdkService;

				serviceName = PropertiesUtils.APP.getProperty("app.smsServiceName");
				serviceName = "hrySmsService";
				if (!StringUtils.isEmpty(serviceName)) {
					sdkService = (SdkService) ContextUtil.getBean(serviceName);
				} else {
					sdkService = (SdkService) ContextUtil.getBean("sdkService");
				}

				// 设置短信发送类型
				appSmsSend.setType(SmsSendUtil.getSendTypeValue(hrySmstype));
				appSmsSend.setPostParam(JSON.toJSONString(smsParam));
				boolean sendSms = sdkService.sendSms(appSmsSend, smsParam, phone);
				// 如果发送标记为成功,则标记为已发送
				if (sendSms) {
					appSmsSend.setSendStatus("1");
				}
				jsonResult.setSuccess(sendSms);
			} else {
				jsonResult.setSuccess(false);
				jsonResult.setMsg("非法请求，密码不正确");
			}
			appSmsSendService.save(appSmsSend);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void sendSmsRemind(String telephone, String key, Map<String, String> map, String locale) {
		if(locale==null||locale.equals("")){
			locale="zh_CN";
		}
		if(map==null){
			map=new HashMap<String, String>();
		}
		AppSmsSend appSmsSend = new AppSmsSend();
		// 标记为已接收到些条记录
		appSmsSend.setReceiveStatus("1");
		AppSmsTemplateService appSmsTemplateService = (AppSmsTemplateService)ContextUtil.getBean("appSmsTemplateService");
		QueryFilter filter = new QueryFilter(AppSmsTemplate.class);
		filter.addFilter("messageCategory=",locale);
		filter.addFilter("mkey=",key);
		filter.addFilter("isOpen=",1);
		List<AppSmsTemplate> appSmsTemplateList = appSmsTemplateService.find(filter);
		if (appSmsTemplateList != null && appSmsTemplateList.size() > 0) {
			// 设置短信发送类型
			appSmsSend.setType(appSmsTemplateList.get(0).getName());
			map.put("telephone",telephone);
			map.put("mkey",key);
			appSmsSend.setPostParam(JSON.toJSONString(map));
			//设置模板
			String content=appSmsTemplateList.get(0).getDescribes();
			String sentContent=getContentByKey(content,map);
			appSmsSend.setSendContent(sentContent);
			SdkService sdkService = (SdkService) ContextUtil.getBean("hrySmsService");
			boolean sendSms = sdkService.sendSms(appSmsSend,null, telephone);
			// 如果发送标记为成功,则标记为已发送
			if (sendSms) {
				appSmsSend.setSendStatus("1");
			}
			AppSmsSendService appSmsSendService = (AppSmsSendService) ContextUtil.getBean("appSmsSendService");
			appSmsSendService.save(appSmsSend);
		} else {
			logger.error("短信模板未找到！");
			return;
		}


	}

	private String getContentByKey(String content,Map<String, String> map) {
		if(map==null||map.isEmpty()){
			return  content;
		}
		Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<String, String> entry = iterator.next();
			content=content.replace(entry.getKey(),entry.getValue());
		}
		return content;
	}
	
	/**
	 * 发送短信-新版
	 * @param param
	 */
	@Override
	public void sendSms (String param) {
		SmsSendParam smsSendParam = JSON.parseObject(param, SmsSendParam.class);
		// 内部验证密码
		String smsKey = smsSendParam.getSmsKey();
		try {
			JsonResult jsonResult = new JsonResult();
			AppSmsSendService appSmsSendService = (AppSmsSendService) ContextUtil.getBean("appSmsSendService");
			AppSmsSend appSmsSend = new AppSmsSend();

			// 标记为已接收到些条记录
			appSmsSend.setReceiveStatus("1");
			// 判断密钥
			if (!StringUtils.isEmpty(smsKey) && "hurongyuseen".equals(smsKey)) {

				String isThirdMessage = PropertiesUtils.APP.getProperty("app.isThirdMessage");
				SdkService sdkService = null;
				AppSmsTemplate smsTemplate = null;
				if("true".equals(isThirdMessage)){
					sdkService = (SdkService)ContextUtil.getBean("subMessageService");
					// 设置短信内容
					appSmsSend.setSendContent(getThirdSmsTemplateContent(smsSendParam).split("-")[0]);
					appSmsSend.setThirdTemplateId(getThirdSmsTemplateContent(smsSendParam).split("-")[1]);
				}else{
					sdkService = (SdkService)ContextUtil.getBean("hrySmsService");
					// 设置短信内容
					smsTemplate = getSmsTemplateContent(smsSendParam);
					appSmsSend.setSendContent(smsTemplate.getDescribes());
				}


				// 设置短信发送类型
				appSmsSend.setType(smsTemplate.getName());


				// 发送手机号
				appSmsSend.setMobileNum(smsSendParam.getHrySmsPhoneNum());
				appSmsSend.setPostParam(JSON.toJSONString(smsSendParam));
				boolean b = sdkService.sendSms(appSmsSend, smsSendParam);
				// 如果发送标记为成功,则标记为已发送
				if (b) {
					appSmsSend.setSendStatus("1");
					jsonResult.setSuccess(true);
				} else {
					jsonResult.setSuccess(false);
				}

			} else {
				jsonResult.setSuccess(false);
				jsonResult.setMsg("非法请求");
			}
			// 保存发送记录
			appSmsSendService.save(appSmsSend);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 获取短信内容
	 * @param smsSendParam
	 * @return
	 */
	private AppSmsTemplate getSmsTemplateContent(SmsSendParam smsSendParam) {
		String smsType = smsSendParam.getHrySmstype();
		String smsLang = smsSendParam.getHrySmsLang();
		String param = smsSendParam.getHryParams();
		// 获取模板
		AppSmsTemplateService appSmsTemplateService = (AppSmsTemplateService) ContextUtil.getBean("appSmsTemplateService");
		AppSmsTemplate smsTemplate = appSmsTemplateService.getSmsTemplate(smsType, smsLang);
		String content = "";
		if (smsTemplate != null) {
			content = smsTemplate.getDescribes();
		}else{
			smsTemplate = appSmsTemplateService.getSmsTemplate(smsType, "zh_CN");
			content = smsTemplate.getDescribes();
		}
		content = relaceContent("HRY_CODE", content, StringUtils.isEmpty(param) ? null : param.split(","));
		smsTemplate.setDescribes(content);
		return smsTemplate;
	}

	/**
	 * 获取短信内容
	 * @param smsSendParam
	 * @return
	 */
	private String getThirdSmsTemplateContent(SmsSendParam smsSendParam) {
		String smsType = smsSendParam.getHrySmstype();
		String smsLang = smsSendParam.getHrySmsLang();
		String param = smsSendParam.getHryParams();
		// 获取模板
		AppSmsTemplateService appSmsTemplateService = (AppSmsTemplateService) ContextUtil.getBean("appSmsTemplateService");
		AppSmsTemplate smsTemplate = appSmsTemplateService.getSmsTemplate(smsType, smsLang);
		String content = "";
		String thirdTemplateId = "";
		if (smsTemplate != null) {
			content = smsTemplate.getDescribes();
			thirdTemplateId = "";
		}
		content = relaceContent("@var(code)", content, StringUtils.isEmpty(param) ? null : param.split(","))+"-"+thirdTemplateId;
		return content;
	}

	/**
	 * 替换内容
	 * @param target 替换目标内容
	 * @param content 替换内容
	 * @param replaceVal 替换值
	 * @return
	 */
	private String relaceContent(String target, String content, String... replaceVal) {
		try {
			if (StringUtils.isEmpty(target)) {
				return content;
			}
			if (replaceVal == null) {
				return content;
			}
			Pattern p = Pattern.compile(target);
			Matcher m = p.matcher(content);
			StringBuffer sb = new StringBuffer();
			int n = 0;
			while (m.find()) {
				m.appendReplacement(sb, replaceVal[n]);
				n++;
			}
			m.appendTail(sb);
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	
}
